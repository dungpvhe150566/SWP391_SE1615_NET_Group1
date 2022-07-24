/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chat;

import dao.MessageDAO;
import dao.impl.MessageDAOImpl;
import entity.Message;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

@ServerEndpoint(value = "/chatRoomServer")
public class ChatRoomServerEndpoint {

    static Set<Session> users = Collections.synchronizedSet(new HashSet<>());

    @OnOpen
    public void handleOpen(Session session) {
        users.add(session);
    }

    @OnMessage
    public void handleMessage(String message, Session userSession) throws IOException {
        MessageDAO messageDAO = new MessageDAOImpl();
        String userID = (String) userSession.getUserProperties().get("userID");
        String userName = (String) userSession.getUserProperties().get("userName");
        message = message.trim();
        String time = null;
        if (userID == null) {
            if (message.isEmpty()) {
                userSession.getUserProperties().put("username", "User");
                userSession.getBasicRemote().sendText("System: you are connectd as User");
            } else {
                String[] inforUser = message.split("/");
                String id = inforUser[0];
                String name = inforUser[1];
                userSession.getUserProperties().put("userName", name);
                userSession.getUserProperties().put("userID", id);
                userID = id;
                userName = name;

                int roomID = checkRoomID(id);
                if (roomID != 0) {
                    try {
                        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
                        Date date = new Date();
                        time = dateFormat.format(date).toString();
                        int n = messageDAO.addMessage(new Message(3,
                                "Hello "+userName+" Can I help you ?", roomID, time));
                        n = messageDAO.addMessage(new Message(Integer.parseInt(id),
                                "...", roomID, time));
                        userSession.getBasicRemote().sendText(change2("lehoangchi", "Hello "+userName+" Can I help you ?", time));
                        userSession.getBasicRemote().sendText(change1(userName, "...", time));
                        for (Session session : users) {
                            if (((String) session.getUserProperties().get("userID")).equals("3")) {
                                System.out.println(id+"   "+name);
                                session.getBasicRemote().sendText("addUser"+addUser(Integer.parseInt(id), name));
                            }
                        }
                    } catch (Exception ex) {
                    }
                } else {
                    if (id.equals("3")) {
                        userSession.getUserProperties().put("currentUser", "0");
                        userSession.getBasicRemote().sendText("System: Hello " + name + " you are connectd as Admin");
                    }
                    loadMessage(id, ((String) userSession.getUserProperties().get("userID")).equals("3") ? null : userSession);
                }
            }
        } else {
            // Admin Change Chat User in ChatBox
            if (((String) userSession.getUserProperties().get("userID")).equals("3")
                    && message.contains("change")) {
                message = message.substring(6, message.length());
                for (Session session : users) {
                    if (((String) session.getUserProperties().get("userID")).equals("3")) {
                        userSession.getUserProperties().put("currentUser", message);
                        loadMessage(message, session);
                    }
                }
            } else {
                int uID = 0;
                try {
                    if (((String) userSession.getUserProperties().get("userID")).equals("3")) {
                        System.out.println(message);
                        String[] mess = message.split("userID");
                        message = mess[0];
                        uID = Integer.parseInt(mess[1]);
                    } else {
                        uID = Integer.parseInt((String) userSession.getUserProperties().get("userID"));
                    }
                    int roomID = messageDAO.getRoomID(uID);
                    DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
                    Date date = new Date();
                    time = dateFormat.format(date).toString();
                    int n = messageDAO.addMessage(new Message(Integer.parseInt((String) userSession.getUserProperties().get("userID")),
                            message, roomID, time));
                } catch (Exception ex) {
                }

                for (Session session : users) {
                    if (((String) userSession.getUserProperties().get("userID")).equals("3")) {
                        if (((String) session.getUserProperties().get("userID")).equals("3")) {
                            session.getBasicRemote().sendText(change1(userName, message, time));
                        }
                        if (((String) session.getUserProperties().get("userID")).equals(uID + "")) {
                            session.getBasicRemote().sendText(change2(userName, message, time));
                        }
                    } else {
                        if (((String) session.getUserProperties().get("userID")).equals(
                                (String) userSession.getUserProperties().get("userID"))) {
                            System.out.println(message);
                            session.getBasicRemote().sendText(change1(userName, message, time));
                        }
                        if (session.getUserProperties().get("userID").equals("3")
                                && session.getUserProperties().get("currentUser").equals(uID + "")) {
                            session.getBasicRemote().sendText(change2(userName, message, time));
                        }
                    }
                }
            }
        }
    }

    public String addUser(int userID,String userName) {
        return "<div id=\"userID"+userID+"\" class=\"chat_list \">\n"
                + "                                        <div class=\"chat_people\">\n"
                + "                                            <div onclick=\"changeUser("+userID+")\" class=\"chat_img\"> <img src=\"https://ptetutorials.com/images/user-profile.png\"\n"
                + "                                                                                                                  alt=\"sunil\"> </div>\n"
                + "                                            <div class=\"chat_ib\">\n"
                + "                                                <h5>"+userName+"<span class=\"chat_date\">Dec 25</span></h5>\n"
                + "                                                <p>Test, which is a new approach to have all solutions\n"
                + "                                                    astrology under one roof.</p>\n"
                + "                                            </div>\n"
                + "                                        </div>\n"
                + "                                    </div>";
    }

    public void loadMessage(String UserID, Session userSession) {
        MessageDAO messageDAO = new MessageDAOImpl();
        try {
            String txtSender = "";
//            String txtReciver = "";
            ArrayList<Message> messagesList = messageDAO.getMessageList(UserID);
            for (Message message1 : messagesList) {
                if (((String) userSession.getUserProperties().get("userID")).equals(message1.getUserID() + "")) {
                    txtSender += change1(message1.getUserName() + "", message1.getMessage(), message1.getDate());
//                    txtReciver += change2(message1.getUserID()+"",message1.getMessage());
                } else {
                    txtSender += change2(message1.getUserName() + "", message1.getMessage(), message1.getDate());
//                    txtReciver += change1(message1.getUserID()+"",message1.getMessage());
                }
            }
            userSession.getBasicRemote().sendText(txtSender);
        } catch (Exception ex) {
        }
    }

    public int checkRoomID(String userID) {
        MessageDAO messageDAO = new MessageDAOImpl();
        int roomID = 0;
        try {
            ArrayList<Message> messages = messageDAO.getUserList2();
            for (Message message : messages) {
                if (message.getRoomId() == Integer.parseInt(userID)) {
                    return 0;
                }
                roomID = message.getRoomId();
            }
        } catch (Exception ex) {
        }
        return roomID + 1;
    }

    public String change1(String param1, String param2, String time) {

        return "<div class=\"outgoing_msg\"> <div class=\"sent_msg\"> "
                + "<span class=\"time_date\">" + param1 + "</span> "
                + "<p>" + param2 + "</p> "
                + "<span class=\"time_date\">" + time + "</span> "
                + "</div></div>";
    }

    public String change2(String param1, String param2, String time) {
        return "<div class=\"incoming_msg\">\n"
                + "                                <div class=\"incoming_msg_img\"> <img\n"
                + "                                        src=\"https://ptetutorials.com/images/user-profile.png\" alt=\"sunil\"> </div>\n"
                + "                                <div class=\"received_msg\">\n"
                + "                                    <div class=\"received_withd_msg\">\n"
                + "                                        <span class=\"time_date\">" + param1 + "</span>\n"
                + "                                        <p>" + param2 + "</p>\n"
                + "                                        <span class=\"time_date\">" + time + "</span>\n"
                + "                                    </div>\n"
                + "                                </div>\n"
                + "                            </div>";
    }

    @OnClose
    public void handleClose(Session session) {
        users.remove(session);
    }

    @OnError
    public void handleError(Throwable t) {
        t.printStackTrace();
    }

}
