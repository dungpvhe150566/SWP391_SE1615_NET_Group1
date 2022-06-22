package dao.impl;

import dao.DBContext;
import dao.OrderDetailDAO;
import entity.Cart;
import entity.OrderDetail;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
            conn = getConnection();
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
public OrderDetail getOrderByID(String id) {
        String query = "select * from Order_Detail where Order_ID = ?";
        Connection conn = null;
        PreparedStatement prepare = null;
        OrderDetail OD = null;
        try {
            conn = getConnection();
            prepare = conn.prepareStatement(query);
            prepare.setString(1, id);
            ResultSet rs = prepare.executeQuery();
            while (rs.next()) {
                OD = new OrderDetail();
                OD.setID(rs.getInt(1));
                OD.setOrderID(rs.getInt(2));
                OD.setProductID(rs.getInt(3));
                OD.setProductName(rs.getString(4));
                OD.setProductPrice(rs.getInt(5));
                OD.setQuantity(rs.getInt(1));
            }
        } catch (Exception e) {
        }
        return OD;
    }
    public static void main(String[] args) {
        OrderDetailDAOImpl dao = new OrderDetailDAOImpl();
        OrderDetail OD = dao.getOrderByID("8");
        System.out.println(OD);       
    }
}
