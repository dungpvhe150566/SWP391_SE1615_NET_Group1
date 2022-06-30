package dao.impl;

import dao.DBContext;
import dao.OrderDetailDAO;
import entity.Cart;
import entity.OrderDetail;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;

public class OrderDetailDAOImpl extends DBContext implements OrderDetailDAO {

    public void saveCart(int orderId, Map<Integer, Cart> carts) throws Exception {
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
        } finally {
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

    public boolean removeByOrderId(int orderID) throws Exception {
        String query = "DELETE dbo.Order_Detail WHERE Order_ID=?";
        try ( Connection con = getConnection();  PreparedStatement ps = (con != null) ? con.prepareStatement(query) : null;) {
            if (ps != null) {
                ps.setObject(1, orderID);
                return ps.execute();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    
    public ArrayList<OrderDetail> getOrderDetailByOrderID(int orderID) throws Exception{
        String query = "SELECT * FROM [Order_Detail] WHERE [Order_ID] = " + orderID;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;
        ArrayList<OrderDetail> arrOrder = new ArrayList<>();
        try {
            connection = getConnection();
            preparedStatement = connection.prepareStatement(query);
            rs = preparedStatement.executeQuery();

            while (rs.next()) {
                arrOrder.add(new OrderDetail(
                        rs.getInt("ID"),
                        rs.getInt("Order_ID"),
                        rs.getInt("ProductID"),
                        rs.getString("ProductName"),
                        rs.getInt("ProductPrice"),
                        rs.getInt("Quantity")
                ));
            }
            return arrOrder;
        } catch (Exception e) {
            throw e;
        } finally {
            closeRS(rs);
            closePrepareStatement(preparedStatement);
            closeConnection(connection);
        }
    }

//    public static void main(String[] args) {
//        OrderDetailDAOImpl dao = new OrderDetailDAOImpl();
//        try {
//            boolean OD = dao.removeByOrderId(1013);
//        } catch (Exception ex) {
//            Logger.getLogger(OrderDetailDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        System.out.println("ok");
//    }
}
