/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entity.ShipInfo;

/**
 *
 * @author Admin
 */
public interface ShipInfoDAO {
    public ShipInfo getShipInforByOrderID(int orderID) throws Exception;
}
