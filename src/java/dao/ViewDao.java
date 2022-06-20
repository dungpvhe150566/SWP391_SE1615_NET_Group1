/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dao.DBContext;
import entity.Orders;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ThinkPro
 */
public class ViewDao extends DBContext{

    public  int getView() throws Exception  {
        Connection conn = null;
            PreparedStatement prepare = null;
            ResultSet rs = null;
       String sql = "SELECT * FROM [Viewss]";
        conn = getConnection();
        try (
                 
                PreparedStatement ps = (conn != null) ? conn.prepareStatement(sql) : null;) {
           rs = (ps != null) ? ps.executeQuery() : null;
            if (rs != null && rs.next()) {
                return rs.getInt(1);
            }
        } catch (Exception ex) {
            throw ex;
        }
        finally {
            closeRS(rs);
            closePrepareStatement(prepare);
            closeConnection(conn);
        }
        return 0;
    }

    public boolean updateView(int count) throws Exception {
        Connection conn = null;
            PreparedStatement prepare = null;
            ResultSet rs = null;
        String query = "UPDATE [dbo].[Viewss]\n"
                + "   SET [vieww] = ?";
        int check = 0;
        conn =getConnection();
        try ( 
                PreparedStatement ps = (conn != null) ? conn.prepareStatement(query) : null;) {
            if (ps != null) {
                ps.setObject(1, count);
                
                check = ps.executeUpdate();
            }
        }catch (Exception ex) {
            throw ex;
        }
        finally {
            closeRS(rs);
            closePrepareStatement(prepare);
            closeConnection(conn);
        }
        return check > 0;
    }
//    public static void main(String[] args) {
//        try {
//            int viewg = new ViewDao().getView();
//            System.out.println(viewg);
//        } catch (Exception ex) {
//            Logger.getLogger(ViewDao.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }
}
