/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao.impl;

import dao.DBContext;
import dao.ShipDAO;
import dao.impl.CategoryDAOImpl;
import entity.Ship;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Pham Van Trong
 */
public class ShipDAOImpl extends DBContext implements ShipDAO {

    public List<Ship> getAllShips() throws Exception {
        List<Ship> list = new ArrayList<>();
        try {
            String sql = "select * from Ship";
            Connection conn = getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Ship product = Ship.builder()
                        .id(rs.getInt(1))
                        .CityName(rs.getString(2))
                        .ShipPrice(rs.getInt(3))
                        .build();
                list.add(product);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return list;
    }

    public List<Ship> getPricebyIDShips(int Shipid) throws Exception {
        List<Ship> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;
        try {
            String sql = "select * from Ship where id = ?";

            preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(1, Shipid);
            rs = preparedStatement.executeQuery();
            while (rs.next()) {
                Ship product = Ship.builder()
                        .id(rs.getInt(1))
                        .CityName(rs.getString(2))
                        .ShipPrice(rs.getInt(3))
                        .build();
                list.add(product);
            }
        } catch (Exception ex) {
            throw ex;
        } finally {
            closeRS(rs);
            closePrepareStatement(preparedStatement);
            closeConnection(conn);
        }
        return list;
    }

    public static void main(String[] args) {
        // insert 
        ShipDAOImpl er = new ShipDAOImpl();

        try {
            //        String sql = "select * from Suppliers";
            er.getAllShips();
        } catch (Exception ex) {
            Logger.getLogger(ShipDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
