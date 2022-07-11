/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.impl;

import dao.DBContext;
import dao.NotificationsDAO;
import entity.Notifications;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Dung
 */
public class NotificationsDAOImpl extends DBContext implements NotificationsDAO{

    @Override
    public ArrayList<Notifications> getListNotifications() throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<Notifications> getListNotificationByUser(int userID) throws Exception {
        // Create vector to store all Categories
        ArrayList<Notifications> arrNotify = new ArrayList<>();

        // Query Statement to get all Categories in Database 
        String sqlQuery = "select * from Notifications where UserID =" + userID;

        // Resultset to store all Categories 
        Connection conn = null;
        PreparedStatement prepare = null;
        ResultSet rs = null;
        // Get all categories store to vector
        try {
            conn = getConnection();
            prepare = conn.prepareStatement(sqlQuery);
            rs = prepare.executeQuery();
            while (rs.next()) {
                int id = rs.getInt(1);
                int orderID = rs.getInt(3);
                String content = rs.getString(4);
                String status = rs.getString(5);
                String time = rs.getString(6);
                arrNotify.add(new Notifications(id, userID, orderID, content, status, time));
            }
        } catch (SQLException ex) {
            throw ex;
        } finally {
            closeRS(rs);
            closePrepareStatement(prepare);
            closeConnection(conn);
        }
        return arrNotify;
    }
    
}
