package dao.impl;

import dao.DBContext;
import dao.OrderDetailDAO;
import entity.Cart;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class OrderDetailDAOImpl extends DBContext implements OrderDetailDAO{

    public void saveCart(int orderId, Map<Integer, Cart> carts) throws Exception{
        Connection conn = null;
        PreparedStatement prepare = null;
        try {

            String sql = "INSERT INTO [dbo].[Order_Detail]\n"
                    + "           ([Order_ID]\n"
                    + "           ,[ProductID]\n"
                    + "           ,[ProductName]\n"
                    + "           ,[ProductPrice]\n"
                    + "           ,[Quantity])\n"
                    + "     VALUES\n"
                    + "           (?,?,?,?,?)";

            prepare = conn.prepareStatement(sql);
            prepare.setInt(1, orderId);
            for (Map.Entry<Integer, Cart> entry : carts.entrySet()) {
                Integer productId = entry.getKey();
                Cart cart = entry.getValue();
                prepare.setInt(2, cart.getProduct().getProductID());
                prepare.setString(3, cart.getProduct().getProductName());
                prepare.setInt(4, cart.getProduct().getOriginalPrice());
                prepare.setInt(5, cart.getAmount());
                prepare.executeUpdate();
            }

        } catch (Exception ex) {
            throw ex;
        }
        finally {
            closePrepareStatement(prepare);
            closeConnection(conn);
        }
    }

}
