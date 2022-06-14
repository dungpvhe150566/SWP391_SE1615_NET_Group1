package dao;

import entity.Cart;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class OrderDetailDAO extends DBContext {

    public void saveCart(int orderId, Map<Integer, Cart> carts) {
        try {

            String sql = "INSERT INTO [dbo].[Order_Detail]\n"
                    + "           ([Order_ID]\n"
                    + "           ,[ProductID]\n"
                    + "           ,[ProductName]\n"
                    + "           ,[ProductPrice]\n"
                    + "           ,[Quantity])\n"
                    + "     VALUES\n"
                    + "           (?,?,?,?,?)";

            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, orderId);
            for (Map.Entry<Integer, Cart> entry : carts.entrySet()) {
                Integer productId = entry.getKey();
                Cart cart = entry.getValue();
                ps.setInt(2, cart.getProduct().getProductID());
                ps.setString(3, cart.getProduct().getProductName());
                ps.setInt(4, cart.getProduct().getOriginalPrice());
                ps.setInt(5, cart.getAmount());
                ps.executeUpdate();
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

}
