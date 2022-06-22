package dao.impl;

import dao.DBContext;
import entity.Orders;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class OrdersDAOImpl extends DBContext {

    public int createReturnId(Orders order) throws Exception {
        Connection conn = null;
        PreparedStatement prepare = null;
        ResultSet rs = null;
        try {
            String sql = "INSERT INTO [dbo].[Orders]\n"
                    + "           ([UserID]\n"
                    + "           ,[TotalPrice]\n"
                    + "           ,[Note]\n"
                    + "           ,[Status])\n"
                    + "     VALUES\n"
                    + "           (?,?,?,?)";
            conn = getConnection();
            prepare = conn.prepareStatement(sql);
            prepare = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            prepare.setInt(1, order.getUserID());
            prepare.setDouble(2, order.getTotalPrice());
            prepare.setString(3, order.getNote());
            prepare.setInt(4, order.getStatus());
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

    public int getTotalOrder(String id) {
        String query = "select count(*) from Orders where UserID = ?";
        Connection conn = null;
        PreparedStatement prepare = null;
        try {
            conn = getConnection();
            prepare = conn.prepareStatement(query);
            prepare.setString(1, id);
            ResultSet rs = prepare.executeQuery();
            while (rs.next()) {
                return rs.getInt(1);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return 0;
    }

    public List<Orders> pagingOrders(String id, int index) {
        List<Orders> list = new ArrayList<>();
        String query = "select * from Orders where UserID = ? order by ID  offset ?  rows fetch next 5 rows only";
        Connection conn = null;
        PreparedStatement prepare = null;

        try {
            conn = getConnection();
            prepare = conn.prepareStatement(query);
            prepare.setString(1, id);
            prepare.setInt(2, (index - 1) * 6);
            ResultSet rs = prepare.executeQuery();
            while (rs.next()) {
                Orders O = new Orders();
                O.setDayBuy(rs.getString(6));
                O.setID(rs.getInt(1));
                O.setNote(rs.getString(4));
                O.setStatus(rs.getInt(5));
                O.setTotalPrice(rs.getFloat(3));
                O.setUserID(rs.getInt(2));
                list.add(O);
            }
        } catch (Exception e) {
        }
        return list;
    }

    public static void main(String[] args) {
        OrdersDAOImpl dao = new OrdersDAOImpl();
        List<Orders> listO = dao.pagingOrders("7", 1);
        for (Orders orders : listO) {
            System.out.println(orders);
        }
    }

    public double calRevenueInMonth(int month) throws Exception {
        Connection conn = null;
        PreparedStatement prepare = null;
        ResultSet rs = null;
        String query = "SELECT ISNULL(SUM(TotalPrice),'0')AS total FROM dbo.[Orders] WHERE YEAR(DayBuy)=YEAR(GETDATE()) and MONTH(DayBuy)=?";

        try ( Connection con = getConnection();  PreparedStatement ps = (con != null) ? con.prepareStatement(query) : null;) {
            if (ps != null) {
                ps.setInt(1, month);
                rs = ps.executeQuery();
                if (rs != null && rs.next()) {
                    return rs.getDouble(1);
                }
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
}
