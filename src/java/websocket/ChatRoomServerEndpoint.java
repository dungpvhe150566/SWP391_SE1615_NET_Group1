/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package websocket;

import java.io.IOException;
import java.util.Collections;
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
//                session.getUserProperties().put("username", "User");
    }

    @OnMessage
    public void handleMessage(String message, Session userSession) throws IOException {
        String username = (String) userSession.getUserProperties().get("username");
        if (username == null) {
            if (message.isEmpty()) {
                userSession.getUserProperties().put("username", "User");
                userSession.getBasicRemote().sendText("System: you are connectd as User");
            } else {
                userSession.getUserProperties().put("username", message.trim());
                userSession.getBasicRemote().sendText("System: you are connectd as " + message);
            }
        } else {
            for (Session session : users) {
                if (!((String) session.getUserProperties().get("username")).equals("lehoangchi")
                        && (((String)session.getUserProperties().get("username")).equals(
                                (String) userSession.getUserProperties().get("username")))) {
                    session.getBasicRemote().sendText(username + ": " + message);
                }
                if (((String) session.getUserProperties().get("username")).equals("lehoangchi")) {
                    session.getBasicRemote().sendText(username + ": " + message);
                }
            }
        }
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
