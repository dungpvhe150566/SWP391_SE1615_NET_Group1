package dao.impl;

import dao.DBContext;
import entity.OrderStatus;
import entity.Orders;
import entity.ShipInfo;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

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

//    public static void main(String[] args) {
//        OrdersDAOImpl dao = new OrdersDAOImpl();
//        List<Orders> listO = dao.pagingOrders("7", 1);
//        for (Orders orders : listO) {
//            System.out.println(orders);
//        }
//    }
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

    public int countOrderWatting() throws Exception {
        String query = "select COUNT(*) from Orders WHERE dbo.[Orders].status = 1";
        try ( Connection con = getConnection();  PreparedStatement ps = (con != null) ? con.prepareStatement(query) : null;) {
            if (ps != null) {
                ResultSet rs = ps.executeQuery();
                if (rs != null && rs.next()) {
                    return rs.getInt(1);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(System.out);
        }
        return 0;

    }

    public List<Orders> getAllOrderNotAcceptYet() throws Exception {
        String query = "SELECT dbo.[Orders].*,dbo.ShipInfo.CustomerName,dbo.ShipInfo.PhoneNum,dbo.ShipInfo.ShippingAddress,Order_Status.Name\n"
                + "                 FROM dbo.[Orders] INNER JOIN dbo.ShipInfo\n"
                + "                  ON ShipInfo.Order_ID = [Orders].ID INNER JOIN Order_Status\n"
                + "                  ON Order_Status.ID = dbo.[Orders].status WHERE dbo.[Orders].status =1";
        List<Orders> list = new ArrayList<>();
        try ( Connection con = getConnection();  PreparedStatement ps = (con != null) ? con.prepareStatement(query) : null;) {
            if (ps != null) {
                ResultSet rs = ps.executeQuery();
                while (rs != null && rs.next()) {
                    ShipInfo shipping = ShipInfo.builder()
                            
                            .CustomerName(rs.getString(7))
                            .PhoneNum(rs.getString(8)).ShippingAddress(rs.getString(9)).build();
                    OrderStatus statusOrder = OrderStatus.builder().ID(rs.getInt(5))
                            .Name(rs.getString(10)).build();
                    Orders order = Orders.builder()
                            .ID(rs.getInt(1))
                            .UserID(rs.getInt(2))
                            .TotalPrice(rs.getFloat(3))
                            .Note(rs.getString(4))
                            .Status(rs.getInt(5))
                            .DayBuy(rs.getString(6))
                            .Shipp(shipping)
                            .orderStatus(statusOrder)
                            .build();
                    list.add(order);
                }
                return list;
            }
        } catch (SQLException e) {
            e.printStackTrace(System.out);
        }
        return null;
    }

    public static void main(String[] args) {
        OrdersDAOImpl ott = new OrdersDAOImpl();
        try {
            List<Orders> listO = ott.getAllSucces();
            System.out.println(listO);
        } catch (Exception ex) {
            Logger.getLogger(OrdersDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public List<Orders> getAllSucces() throws Exception {
        String query = "SELECT dbo.[Orders].*,dbo.ShipInfo.CustomerName,dbo.ShipInfo.PhoneNum,dbo.ShipInfo.ShippingAddress,Order_Status.Name\n"
                + "                 FROM dbo.[Orders] INNER JOIN dbo.ShipInfo\n"
                + "                  ON ShipInfo.Order_ID = [Orders].ID INNER JOIN Order_Status\n"
                + "                  ON Order_Status.ID = dbo.[Orders].status WHERE dbo.[Orders].status =5";
        List<Orders> list = new ArrayList<>();
        try ( Connection con = getConnection();  PreparedStatement ps = (con != null) ? con.prepareStatement(query) : null;) {
            if (ps != null) {
                ResultSet rs = ps.executeQuery();
                while (rs != null && rs.next()) {
                    ShipInfo shipping = ShipInfo.builder()
                            .ID(rs.getInt(3))
                            .CustomerName(rs.getString(7))
                            .PhoneNum(rs.getString(8)).ShippingAddress(rs.getString(9)).build();
                    OrderStatus statusOrder = OrderStatus.builder().ID(rs.getInt(5))
                            .Name(rs.getString(10)).build();
                    Orders order = Orders.builder()
                            .ID(rs.getInt(1))
                            .UserID(rs.getInt(2))
                            .TotalPrice(rs.getFloat(3))
                            .Note(rs.getString(4))
                            .Status(rs.getInt(5))
                            .DayBuy(rs.getString(6))
                            .Shipp(shipping)
                            .orderStatus(statusOrder)
                            .build();
                    list.add(order);
                }
                return list;
            }
        } catch (SQLException e) {
            e.printStackTrace(System.out);
        }
        return null;
    }

    public List<Orders> getAllOrderShipping() throws Exception {
        String query = "SELECT dbo.[Orders].*,dbo.ShipInfo.CustomerName,dbo.ShipInfo.PhoneNum,dbo.ShipInfo.ShippingAddress,Order_Status.Name\n"
                + "                 FROM dbo.[Orders] INNER JOIN dbo.ShipInfo\n"
                + "                  ON ShipInfo.Order_ID = [Orders].ID INNER JOIN Order_Status\n"
                + "                  ON Order_Status.ID = dbo.[Orders].status WHERE dbo.[Orders].status =3";
        List<Orders> list = new ArrayList<>();
        try ( Connection con = getConnection();  PreparedStatement ps = (con != null) ? con.prepareStatement(query) : null;) {
            if (ps != null) {
                ResultSet rs = ps.executeQuery();
                while (rs != null && rs.next()) {
                    ShipInfo shipping = ShipInfo.builder()
                            .ID(rs.getInt(3))
                            .CustomerName(rs.getString(7))
                            .PhoneNum(rs.getString(8)).ShippingAddress(rs.getString(9)).build();
                    OrderStatus statusOrder = OrderStatus.builder().ID(rs.getInt(5))
                            .Name(rs.getString(10)).build();
                    Orders order = Orders.builder()
                            .ID(rs.getInt(1))
                            .UserID(rs.getInt(2))
                            .TotalPrice(rs.getInt(3))
                            .Note(rs.getString(4))
                            .Status(rs.getInt(5))
                            .DayBuy(rs.getString(6))
                            .Shipp(shipping)
                            .orderStatus(statusOrder)
                            .build();
                    list.add(order);
                }
                return list;
            }
        } catch (SQLException e) {
            e.printStackTrace(System.out);
        }
        return null;
    }
}
