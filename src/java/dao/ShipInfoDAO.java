package dao;

import entity.ShipInfo;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ShipInfoDAO extends DBContext {

    public int createReturnId(ShipInfo shipping) {
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

            PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, shipping.getOrder_ID());
            ps.setString(2, shipping.getCustomerName());
            ps.setString(3, shipping.getShippingAddress());
            ps.setInt(4, shipping.getShipCityID());
            ps.setString(5, shipping.getPhoneNum());
            ps.setString(6, shipping.getNote());
            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                return rs.getInt(1);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return 0;
    }

}
