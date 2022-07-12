package dao.impl;

import dao.DBContext;
import dao.ShipInfoDAO;
import entity.ShipInfo;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ShipInfoDAOImpl extends DBContext implements ShipInfoDAO {

    public int createReturnId(ShipInfo shipping) throws Exception {
        Connection conn = null;
        PreparedStatement prepare = null;
        ResultSet rs = null;
        try {
            String sql = "INSERT INTO [dbo].[ShipInfo]\n"
                    + "           ([Order_ID]\n"
                    + "           ,[CustomerName]\n"
                    + "           ,[ShippingAddress]\n"
                    + "           ,[ShipCityID]\n"
                    + "           ,[PhoneNum]\n"
                    + "           ,[Note])\n"
                    + "     VALUES\n"
                    + "           (?,?,?,?,?,?)";
            conn = getConnection();
            prepare = conn.prepareStatement(sql);

            prepare = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            prepare.setInt(1, shipping.getOrder_ID());
            prepare.setString(2, shipping.getCustomerName());
            prepare.setString(3, shipping.getShippingAddress());
            prepare.setInt(4, shipping.getShipCityID());
            prepare.setString(5, shipping.getPhoneNum());
            prepare.setString(6, shipping.getNote());
            prepare.executeUpdate();

            rs = prepare.getGeneratedKeys();
            if (rs.next()) {
                return rs.getInt(1);
            }

        } catch (Exception ex) {
            throw ex;
        } finally {
            closeRS(rs);
            closePrepareStatement(prepare);
            closeConnection(conn);
        }
        return 0;
    }

    public boolean removeShip(int id) throws Exception {
        String query = "DELETE dbo.ShipInfo WHERE Order_ID=?";
        try (Connection con = getConnection();
                PreparedStatement ps = (con != null) ? con.prepareStatement(query) : null;) {
            if (ps != null) {
                ps.setObject(1, id);
                return ps.execute();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public ShipInfo getShipInforByOrderID(int orderID) throws Exception {
        String query = "SELECT * FROM ShipInfo where Order_ID = " + orderID;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;
        ShipInfo shipInfo = null;
        try {
            connection = getConnection();
            preparedStatement = connection.prepareStatement(query);
            rs = preparedStatement.executeQuery();

            while (rs.next()) {
                shipInfo = new ShipInfo(
                        rs.getInt("ID"),
                        rs.getInt("Order_ID"),  
                        rs.getString("CustomerName"),
                        rs.getString("ShippingAddress"),
                        rs.getInt("ShipCityID"),
                        rs.getString("PhoneNum"),
                        rs.getString("Note"));
            }

            return shipInfo;
        } catch (Exception e) {
            throw e;
        } finally {
            closeRS(rs);
            closePrepareStatement(preparedStatement);
            closeConnection(connection);
        }
    }
}
