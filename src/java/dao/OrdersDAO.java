package dao;

import entity.Orders;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class OrdersDAO extends DBContext {

    public int createReturnId(Orders order) {
        try {
            String sql = "INSERT INTO [dbo].[Orders]\n"
                    + "           ([UserID]\n"
                    + "           ,[TotalPrice]\n"
                    + "           ,[Note])\n"
                    + "            VALUES\n"
                    + "           (?,?,?)";

            PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, order.getUserID());
            ps.setDouble(2, order.getTotalPrice());
            ps.setString(3, order.getNote());
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
