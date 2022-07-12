/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entity.Orders;
import java.util.Vector;

/**
 *
 * @author Admin
 */
public interface OrdersDAO {
    int createReturnId(Orders order) throws Exception;
    
    public Vector<Orders> getOrdersList() throws Exception;
    public Vector<Orders> getOrdersList(int userID) throws Exception;
    public Vector<Orders> getOrdersList(int startRow, int endRow) throws Exception;
    public Vector<Orders> getOrdersList(int startRow, int endRow, int userID, int statusID, String date, String sortBy) throws Exception;
    public int getTotalPage(int userID, int statusID, String date,int numOfRecord) throws Exception;
    public Orders getOrder(int orderID) throws Exception;
}
