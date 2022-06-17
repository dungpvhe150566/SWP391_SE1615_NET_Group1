/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entity.Ship;
import java.util.List;

/**
 *
 * @author Admin
 */
public interface ShipDAO {
    
    List<Ship> getAllShips() throws Exception;
    
    List<Ship> getPricebyIDShips(int Shipid) throws Exception;
}
