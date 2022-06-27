package dao.impl;

import dao.DBContext;
import dao.OrderStatusDAO;
import entity.OrderStatus;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Vector;

/**
 *
 * @author Dung
 */
public class OrderStatusDAOImpl extends DBContext implements OrderStatusDAO{

    @Override
    public Vector<OrderStatus> getOrderStatusList() throws Exception {
        String query = "SELECT * FROM Order_Status";
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;
        Vector<OrderStatus> vectorOrderStatus = new Vector<>();
        try {
            connection = getConnection();
            preparedStatement = connection.prepareStatement(query);
            rs = preparedStatement.executeQuery();

            while (rs.next()) {
                vectorOrderStatus.add(new OrderStatus(
                        rs.getInt("ID"),
                        rs.getString("Name")
                ));
            }
            return vectorOrderStatus;
        } catch (Exception e) {
            throw e;
        } finally {
            closeRS(rs);
            closePrepareStatement(preparedStatement);
            closeConnection(connection);
        }
    }

    @Override
    public Vector<OrderStatus> getORderStatusList(int startRow, int endRow) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
