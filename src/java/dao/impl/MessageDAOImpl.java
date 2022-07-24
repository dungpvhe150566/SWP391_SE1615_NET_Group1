/*
 * This class to select data(Message Table) from DB
 * 
 */
package dao.impl;

import dao.DBContext;
import dao.MessageDAO;
import entity.Message;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class MessageDAOImpl extends DBContext implements MessageDAO {
    
    /**
     * Get All Message From Database follow userID 
     * @param: userID
     * @return ArrayList Message 
     */
    public ArrayList<Message> getMessageList(String userID) throws Exception {
        // Create vector to store all Categories
        ArrayList<Message> messageList = new ArrayList<>();

        // Query Statement to get all Categories in Database 
        String sqlQuery = "select m.ID,m.UserID,u.Username,m.RoomID,m.Date,m.Message from Message m join Users u on m.UserID = u.UserID\n"
                + "  where RoomID in (select RoomID from Message where UserID=?)\n"
                + "  order by m.Date";

        // Resultset to store all Categories 
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;
        // Get all categories store to vector
        try {
            connection = getConnection();
            preparedStatement = connection.prepareStatement(sqlQuery);
            preparedStatement.setString(1, userID);
            rs = preparedStatement.executeQuery();
            while (rs.next()) {
                // Get and store all attribute of each Product
                int Id = rs.getInt("ID");
                int userId = rs.getInt("UserID");
                String userName = rs.getString("Username");
                int roomId = rs.getInt("RoomID");
                String date = rs.getString("Date");
                String message = rs.getString("Message");
                messageList.add(new Message(Id, userId, userName, message, roomId, date));
            }
        } catch (SQLException ex) {
            throw ex;
        } finally {
            closeRS(rs);
            closePrepareStatement(preparedStatement);
            closeConnection(connection);
        }
        return messageList;
    }

    /**
     * Add a Message to Database  
     * @param: userID
     * @return number record added
     */
    public int addMessage(Message message) throws Exception {
        int n = 0;

        String preSql = "INSERT INTO [dbo].[Message]\n"
                + "           ([UserID]\n"
                + "           ,[RoomID]\n"
                + "           ,[Date]\n"
                + "           ,[Message]) VALUES (?,?,?,?)";

        Connection conn = null;
        PreparedStatement prepare = null;
        try {
//            PreparedStatement pre = conn.prepareStatement(preSql);

            conn = getConnection();
            prepare = conn.prepareStatement(preSql);

            prepare.setInt(1, message.getUserID());
            prepare.setInt(2, message.getRoomId());
            prepare.setString(3, message.getDate());
            prepare.setString(4, message.getMessage());

            n = prepare.executeUpdate();

//            n = pre.executeUpdate();
        } finally {
            closePrepareStatement(prepare);
            closeConnection(conn);
        }

        return n;
    }

    /**
     * Get RoomID of Message From Database follow userID 
     * @param: userID
     * @return RoomID  
     */
    public int getRoomID(int userID) throws Exception {
        int n = 0;

        String preSql = "SELECT distinct [RoomID] FROM [dbo].[Message] where UserID=?";

        Connection conn = null;
        PreparedStatement prepare = null;
        ResultSet rs = null;
        try {
//            PreparedStatement pre = conn.prepareStatement(preSql);

            conn = getConnection();
            prepare = conn.prepareStatement(preSql);
            prepare.setInt(1, userID);
            rs = prepare.executeQuery();
            if (rs.next()) {
                n = rs.getInt(1);
            }
        } finally {
            closeRS(rs);
            closePrepareStatement(prepare);
            closeConnection(conn);
        }

        return n;
    }

    public static void main(String[] args) {
        MessageDAO messageDAO = new MessageDAOImpl();
        try {
//            ArrayList<Message> messagesList = messageDAO.getMessageList("1");
//            for (Message message : messagesList) {
//                System.out.println(message);
//            }

//        int n = messageDAO.addMessage(new Message(1, "I want to buy a laptop", 1, "12-13-2022"));
            System.out.println(messageDAO.getRoomID(3));
        } catch (Exception ex) {
        }
    }

    /**
     * Get All Room Message(#admin) From Database  
     * @param: 
     * @return ArrayList Message(RoomID distinct) 
     */
    public ArrayList<Message> getUserList() throws Exception {
        // Create vector to store all Categories
        ArrayList<Message> messageList = new ArrayList<>();

        // Query Statement to get all Categories in Database 
        String sqlQuery = "select distinct (m.UserID),u.Username,m.RoomID from Message m join Users u on m.UserID = u.UserID where m.UserID!=3 order by RoomID";

        // Resultset to store all Categories 
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;
        // Get all categories store to vector
        try {
            connection = getConnection();
            preparedStatement = connection.prepareStatement(sqlQuery);
            rs = preparedStatement.executeQuery();
            while (rs.next()) {
                // Get and store all attribute of each Product
                int userId = rs.getInt("UserID");
                int roomId = rs.getInt("RoomID");
                String userName = rs.getString("Username");
                messageList.add(new Message(userId, roomId, userName));
            }
        } catch (SQLException ex) {
            throw ex;
        } finally {
            closeRS(rs);
            closePrepareStatement(preparedStatement);
            closeConnection(connection);
        }
        return messageList;
    }
    
    /**
     * Get All Room Message(!#admin) From Database  
     * @param: 
     * @return ArrayList Message(RoomID distinct) 
     */
    public ArrayList<Message> getUserList2() throws Exception {
        // Create vector to store all Categories
        ArrayList<Message> messageList = new ArrayList<>();

        // Query Statement to get all Categories in Database 
        String sqlQuery = "select distinct (m.UserID),u.Username,m.RoomID from Message m join Users u on m.UserID = u.UserID order by RoomID";

        // Resultset to store all Categories 
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;
        // Get all categories store to vector
        try {
            connection = getConnection();
            preparedStatement = connection.prepareStatement(sqlQuery);
            rs = preparedStatement.executeQuery();
            while (rs.next()) {
                // Get and store all attribute of each Product
                int userId = rs.getInt("UserID");
                int roomId = rs.getInt("RoomID");
                String userName = rs.getString("Username");
                messageList.add(new Message(userId, roomId, userName));
            }
        } catch (SQLException ex) {
            throw ex;
        } finally {
            closeRS(rs);
            closePrepareStatement(preparedStatement);
            closeConnection(connection);
        }
        return messageList;
    }

}
