/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entity.Cart;
import java.util.Map;

/**
 *
 * @author Admin
 */
public interface OrderDetailDAO {
    void saveCart(int orderId, Map<Integer, Cart> carts) throws Exception;
}
