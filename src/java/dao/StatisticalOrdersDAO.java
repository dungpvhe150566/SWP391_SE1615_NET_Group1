/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import entity.Statistical;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Pham Van Trong
 */
public class StatisticalOrdersDAO extends DBContext{
    public List<Statistical> getAll() throws Exception {
        String sql = "SELECT DAY(DayBuy) AS [day],DATENAME(dw,DayBuy) AS [weekday],COUNT(DayBuy)AS numOfOrder,SUM(TotalPrice) AS totalMoney, year(DayBuy) as [Year] ,month(DayBuy) as [month] FROM dbo.[Orders] WHERE Day(GETDATE())-Day(DayBuy)<7  and year(DayBuy) = year(getdate()) and month(DayBuy) = month(getdate())  GROUP BY DayBuy,DAY(DayBuy)";
        List<Statistical> list = new ArrayList<>();
        Connection conn = null;
            PreparedStatement prepare = null;
            ResultSet rs = null;
        try (Connection con = getConnection();
                PreparedStatement ps = (con != null) ? con.prepareStatement(sql) : null;) {
            rs = (ps != null) ? ps.executeQuery() : null;
            while (rs != null && rs.next()) {
                Statistical thongkeOrder = Statistical.builder()
                        .day(rs.getInt(1))
                        .thu(rs.getString(2))
                        .numOfOrder(rs.getInt(3))
                        .totalMoney(rs.getDouble(4))
                        .year(rs.getInt(5))
                        .month(rs.getInt(6))
                        .build();
                list.add(thongkeOrder);
            }
            return list;
        } catch (SQLException e) {
            e.printStackTrace(System.out);
        }
         finally {
            closeRS(rs);
            closePrepareStatement(prepare);
            closeConnection(conn);
        }
        return null;
    }
}
