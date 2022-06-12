/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

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
public class ShipDAO extends DBContext{
     public List<Ship> getAllShips() {
        List<Ship> list = new ArrayList<>();
        try {
            String sql = "select * from Ship";
           
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
            Logger.getLogger(CategoryDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
      public List<Ship> getPricebyIDShips(int Shipid) {
        List<Ship> list = new ArrayList<>();
        try {
            String sql = "select * from Ship where id = ?";
           
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, Shipid);
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
            Logger.getLogger(CategoryDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
      
}
