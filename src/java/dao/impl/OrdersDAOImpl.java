package dao.impl;

import dao.DBContext;
import entity.OrderStatus;
import dao.OrdersDAO;
import entity.Orders;
import entity.ShipInfo;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

public class OrdersDAOImpl extends DBContext implements OrdersDAO {

    public Vector<Orders> getOrdersList() throws Exception {
        String query = "SELECT * FROM Orders";
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;
        Vector<Orders> vectorOrder = new Vector<>();
        try {
            connection = getConnection();
            preparedStatement = connection.prepareStatement(query);
            rs = preparedStatement.executeQuery();

            while (rs.next()) {
                vectorOrder.add(new Orders(
                        rs.getInt("ID"),
                        rs.getInt("UserID"),
                        rs.getInt("TotalPrice"),
                        rs.getString("Note"),
                        rs.getInt("Status"),
                        rs.getString("DayBuy")
                ));
            }
            return vectorOrder;
        } catch (Exception e) {
            throw e;
        } finally {
            closeRS(rs);
            closePrepareStatement(preparedStatement);
            closeConnection(connection);
        }
    }

    public Vector<Orders> getOrdersList(int userID) throws Exception {
        String query = "SELECT * FROM Orders WHERE UserID = " + userID;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;
        Vector<Orders> vectorOrder = new Vector<>();
        try {
            connection = getConnection();
            preparedStatement = connection.prepareStatement(query);
            rs = preparedStatement.executeQuery();

            while (rs.next()) {
                vectorOrder.add(new Orders(
                        rs.getInt("ID"),
                        rs.getInt("UserID"),
                        rs.getInt("TotalPrice"),
                        rs.getString("Note"),
                        rs.getInt("Status"),
                        rs.getString("DayBuy")
                ));
            }
            return vectorOrder;
        } catch (Exception e) {
            throw e;
        } finally {
            closeRS(rs);
            closePrepareStatement(preparedStatement);
            closeConnection(connection);
        }
    }

    @Override
    public Vector<Orders> getOrdersList(int startRow, int endRow) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Orders getOrder(int orderID) throws Exception {
        String query = "SELECT * FROM Orders where ID = " + orderID;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;
        Orders order = new Orders();
        try {
            connection = getConnection();
            preparedStatement = connection.prepareStatement(query);
            rs = preparedStatement.executeQuery();

            while (rs.next()) {
                order = new Orders(
                        rs.getInt("ID"),
                        rs.getInt("UserID"),
                        rs.getInt("TotalPrice"),
                        rs.getString("Note"),
                        rs.getInt("Status"),
                        rs.getString("DayBuy"),
                        ((new OrderDetailDAOImpl()).getOrderDetailByOrderID(rs.getInt("ID"))));
            }
            
            return order;
        } catch (Exception e) {
            throw e;
        } finally {
            closeRS(rs);
            closePrepareStatement(preparedStatement);
            closeConnection(connection);
        }
    }

    @Override
    public Vector<Orders> getOrdersList(int startRow, int endRow, int userID, int statusID, String date, String sortBy) throws Exception {
        String statusQuery = "";
        if (statusID != 0) {
            statusQuery = " AND Status = " + statusID;
        }

        String sortByQuery = "";
        if (sortBy != null && sortBy.length() > 0) {
            sortByQuery = " ORDER BY " + sortBy + " ASC ";
        }

        String dateQuery = "";
        if (date.length() > 0) {
            dateQuery = " AND CONVERT(DATE, DayBuy) = '" + date + "'";
        }

        String query = "SELECT * \n"
                + "FROM (\n"
                + "	SELECT [ID]\n"
                + "      ,[UserID]\n"
                + "      ,[TotalPrice]\n"
                + "      ,[Note]\n"
                + "      ,[Status]\n"
                + "      ,[DayBuy],\n"
                + "      ROW_NUMBER() OVER (ORDER BY ID) AS RowNum\n"
                + "	FROM Orders WHERE UserID=" + userID + statusQuery + dateQuery + ") as CTEResults\n"
                + "WHERE CTEResults.RowNum BETWEEN " + startRow + " AND " + endRow + sortByQuery;

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;
        Vector<Orders> vectorOrder = new Vector<>();
        try {
            connection = getConnection();
            preparedStatement = connection.prepareStatement(query);
            rs = preparedStatement.executeQuery();

            while (rs.next()) {
                vectorOrder.add(new Orders(
                        rs.getInt("ID"),
                        rs.getInt("UserID"),
                        rs.getInt("TotalPrice"),
                        rs.getString("Note"),
                        rs.getInt("Status"),
                        rs.getString("DayBuy"),
                        ((new OrderDetailDAOImpl()).getOrderDetailByOrderID(rs.getInt("ID")))
                ));
            }
            return vectorOrder;
        } catch (Exception e) {
            throw e;
        } finally {
            closeRS(rs);
            closePrepareStatement(preparedStatement);
            closeConnection(connection);
        }
    }

    @Override
    public int getTotalPage(int userID, int statusID, String date, int numOfRecord) throws Exception {
        int totalPage = 0;
        String statusQuery = "";
        if (statusID != 0) {
            statusQuery = " AND Status = " + statusID;
        }

        String dateQuery = "";
        if (date.length() > 0) {
            dateQuery = " AND CONVERT(DATE, DayBuy) = '" + date + "'";
        }

        // Query statement to get total Orders in Database
        String sqlQuery = "select count(ID) from Orders "
                + " where UserID = " + userID + statusQuery + dateQuery;

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;
        // Execute query to get total Order
        try {
            connection = getConnection();
            preparedStatement = connection.prepareStatement(sqlQuery);
            rs = preparedStatement.executeQuery();
            // set total Product to variable
            if (rs.next()) {
                totalPage = rs.getInt(1);
            }
        } catch (Exception ex) {
            throw ex;
        } finally {
            closeConnection(connection);
            closePrepareStatement(preparedStatement);
            closeRS(rs);
        }
        // convet total product to total page (each page have "numOfRecord" product)
        return (int) Math.ceil((double) totalPage / numOfRecord);
    }

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

    public int getTotalOrder() {
        String query = "select count(*) from Orders";
        Connection conn = null;
        PreparedStatement prepare = null;
        try {
            conn = getConnection();
            prepare = conn.prepareStatement(query);
            ResultSet rs = prepare.executeQuery();
            while (rs.next()) {
                return rs.getInt(1);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return 0;
    }
     public void deleteOrder(String id) {
        String query = "delete from orders where id = ?";
        Connection conn = null;
        PreparedStatement prepare = null;
        try {
            conn = getConnection();
            prepare = conn.prepareStatement(query);
            prepare.setString(1, id);
            prepare.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
     public void EditOrder(String id,String note,String total,String status) {
        String query = "update [orders] set TotalPrice = ?,note=?,[status] = ? where id = ?";
        Connection conn = null;
        PreparedStatement prepare = null;
        try {
            conn = getConnection();
            prepare = conn.prepareStatement(query);
            prepare.setString(1, total);
            prepare.setString(2, note);
            prepare.setString(3, status);
            prepare.setString(4, id);
            prepare.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
     public List<Orders> searchTotal(String total) {
         List<Orders> list = new ArrayList<>();
        String query = "select * from [orders] where TotalPrice < ?";
        Connection conn = null;
        PreparedStatement prepare = null;
        try {
            conn = getConnection();
            prepare = conn.prepareStatement(query);
            prepare.setString(1, total);
            ResultSet rs = prepare.executeQuery();
            while(rs.next()){
                Orders O = new Orders();
                O.setDayBuy(rs.getString(6));
                O.setID(rs.getInt(1));
                O.setNote(rs.getString(4));
                O.setStatus(rs.getInt(5));
                O.setTotalPrice(rs.getInt(3));
                O.setUserID(rs.getInt(2));
                list.add(O);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return list;
    }

    public List<Orders> pagingOrders(int index) {
        List<Orders> list = new ArrayList<>();
        String query = "select * from Orders order by ID  offset ?  rows fetch next 5 rows only";
        Connection conn = null;
        PreparedStatement prepare = null;

        try {
            conn = getConnection();
            prepare = conn.prepareStatement(query);
            prepare.setInt(1, (index - 1) * 6);
            ResultSet rs = prepare.executeQuery();
            while (rs.next()) {
                Orders O = new Orders();
                O.setDayBuy(rs.getString(6));
                O.setID(rs.getInt(1));
                O.setNote(rs.getString(4));
                O.setStatus(rs.getInt(5));
                O.setTotalPrice(rs.getInt(3));
                O.setUserID(rs.getInt(2));
                list.add(O);
            }
        } catch (Exception e) {
        }
        return list;
    }



    public double calRevenueInMonth(int month) throws Exception {
        Connection conn = null;
        PreparedStatement prepare = null;
        ResultSet rs = null;
        String query = "SELECT ISNULL(SUM(TotalPrice),'0')AS total FROM dbo.[Orders] WHERE YEAR(DayBuy)=YEAR(GETDATE()) and MONTH(DayBuy)=?";

        try (Connection con = getConnection(); PreparedStatement ps = (con != null) ? con.prepareStatement(query) : null;) {
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
        String query = "SELECT Count(*)\n"
                + "                     FROM dbo.[Orders] INNER JOIN dbo.ShipInfo\n"
                + "                                ON ShipInfo.Order_ID = [Orders].ID INNER JOIN Order_Status\n"
                + "                               ON Order_Status.ID = dbo.[Orders].status WHERE dbo.[Orders].status =1";
        try (Connection con = getConnection(); PreparedStatement ps = (con != null) ? con.prepareStatement(query) : null;) {
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
        Connection conn = null;
        PreparedStatement prepare = null;
        ResultSet rs = null;
        String query = "SELECT dbo.[Orders].*,dbo.ShipInfo.CustomerName,dbo.ShipInfo.PhoneNum,dbo.ShipInfo.ShippingAddress,Order_Status.Name\n"
                + "                 FROM dbo.[Orders] INNER JOIN dbo.ShipInfo\n"
                + "                  ON ShipInfo.Order_ID = [Orders].ID INNER JOIN Order_Status\n"
                + "                  ON Order_Status.ID = dbo.[Orders].status WHERE dbo.[Orders].status =1";
        List<Orders> list = new ArrayList<>();
        try (Connection con = getConnection(); PreparedStatement ps = (con != null) ? con.prepareStatement(query) : null;) {
            if (ps != null) {
                rs = ps.executeQuery();
                while (rs != null && rs.next()) {
                    ShipInfo shipping = ShipInfo.builder()
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
        } catch (Exception e) {
            throw e;
        } finally {
            closeRS(rs);
            closePrepareStatement(prepare);
            closeConnection(conn);
        }
        return null;
    }

    public static void main(String[] args) {
        OrdersDAOImpl ott = new OrdersDAOImpl();
        List<Orders> listO = ott.searchTotal("1000000");
        for (Orders orders : listO) {
            System.out.println(orders);
        }
    }

    public List<Orders> getAllSucces() throws Exception {
        Connection conn = null;
        PreparedStatement prepare = null;
        ResultSet rs = null;
        String query = "SELECT dbo.[Orders].*,dbo.ShipInfo.CustomerName,dbo.ShipInfo.PhoneNum,dbo.ShipInfo.ShippingAddress,Order_Status.Name\n"
                + "                 FROM dbo.[Orders] INNER JOIN dbo.ShipInfo\n"
                + "                  ON ShipInfo.Order_ID = [Orders].ID INNER JOIN Order_Status\n"
                + "                  ON Order_Status.ID = dbo.[Orders].status WHERE dbo.[Orders].status =5";
        List<Orders> list = new ArrayList<>();
        try (Connection con = getConnection(); PreparedStatement ps = (con != null) ? con.prepareStatement(query) : null;) {
            if (ps != null) {
                rs = ps.executeQuery();
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
        } catch (Exception e) {
            throw e;
        } finally {
            closeRS(rs);
            closePrepareStatement(prepare);
            closeConnection(conn);
        }
        return null;
    }

    public List<Orders> getAllOrderShipping() throws Exception {
        Connection conn = null;
        PreparedStatement prepare = null;
        ResultSet rs = null;
        String query = "SELECT dbo.[Orders].*,dbo.ShipInfo.CustomerName,dbo.ShipInfo.PhoneNum,dbo.ShipInfo.ShippingAddress,Order_Status.Name\n"
                + "                 FROM dbo.[Orders] INNER JOIN dbo.ShipInfo\n"
                + "                  ON ShipInfo.Order_ID = [Orders].ID INNER JOIN Order_Status\n"
                + "                  ON Order_Status.ID = dbo.[Orders].status WHERE dbo.[Orders].status =3";
        List<Orders> list = new ArrayList<>();
        try (Connection con = getConnection(); PreparedStatement ps = (con != null) ? con.prepareStatement(query) : null;) {
            if (ps != null) {
                rs = ps.executeQuery();
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
        } catch (Exception e) {
            throw e;
        } finally {
            closeRS(rs);
            closePrepareStatement(prepare);
            closeConnection(conn);
        }
        return null;
    }

    public boolean remove(int id) throws Exception {
        Connection conn = null;
        PreparedStatement prepare = null;
        ResultSet rs = null;
        String query = "DELETE FROM Orders WHERE ID = ?";
        try (Connection con = getConnection(); PreparedStatement ps = (con != null) ? con.prepareStatement(query) : null;) {
            if (ps != null) {
                ps.setObject(1, id);
                return ps.execute();
            }
        } catch (Exception e) {
            throw e;
        } finally {
            closeRS(rs);
            closePrepareStatement(prepare);
            closeConnection(conn);
        }
        return false;
    }

    public boolean updateStatus(int status, int id) throws Exception {
        Connection conn = null;
        PreparedStatement prepare = null;
        ResultSet rs = null;
        String query = "UPDATE [dbo].[Orders]\n"
                + "   SET [Status] = ?\n"
                + " WHERE ID = ?";
        int check = 0;
        try (Connection con = getConnection(); PreparedStatement ps = (con != null) ? con.prepareStatement(query) : null;) {
            if (ps != null) {
                ps.setObject(1, status);
                ps.setObject(2, id);
                check = ps.executeUpdate();
            }
        } catch (Exception e) {
            throw e;
        } finally {
            closeRS(rs);
            closePrepareStatement(prepare);
            closeConnection(conn);
        }
        return check > 0;
    }

    public List<Orders> getAllOrderPackaging() throws Exception {
        Connection conn = null;
        PreparedStatement prepare = null;
        ResultSet rs = null;
        String query = "SELECT dbo.[Orders].*,dbo.ShipInfo.CustomerName,dbo.ShipInfo.PhoneNum,dbo.ShipInfo.ShippingAddress,Order_Status.Name\n"
                + "                 FROM dbo.[Orders] INNER JOIN dbo.ShipInfo\n"
                + "                  ON ShipInfo.Order_ID = [Orders].ID INNER JOIN Order_Status\n"
                + "                  ON Order_Status.ID = dbo.[Orders].status WHERE dbo.[Orders].status =2";
        List<Orders> list = new ArrayList<>();
        try (Connection con = getConnection(); PreparedStatement ps = (con != null) ? con.prepareStatement(query) : null;) {
            if (ps != null) {
                rs = ps.executeQuery();
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
        } catch (Exception e) {
            throw e;
        } finally {
            closeRS(rs);
            closePrepareStatement(prepare);
            closeConnection(conn);
        }
        return null;
    }

    public List<Orders> search(String keyword) throws Exception {
        Connection conn = null;
        PreparedStatement prepare = null;
        ResultSet rs = null;
        List<Orders> list = new ArrayList<>();
        try {
            String sql = "SELECT dbo.[Orders].*,dbo.ShipInfo.CustomerName,dbo.ShipInfo.PhoneNum,dbo.ShipInfo.ShippingAddress,Order_Status.Name\n"
                    + "                 FROM dbo.[Orders] INNER JOIN dbo.ShipInfo\n"
                    + "                  ON ShipInfo.Order_ID = [Orders].ID INNER JOIN Order_Status\n"
                    + "                  ON Order_Status.ID = dbo.[Orders].status WHERE dbo.ShipInfo.CustomerName like ?";
            conn = getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, "%" + keyword + "%");
            rs = ps.executeQuery();
            while (rs.next()) {
                ShipInfo shipping = ShipInfo.builder()
                        .CustomerName(rs.getString(7).trim())
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
        } catch (Exception e) {
            throw e;
        } finally {
            closeRS(rs);
            closePrepareStatement(prepare);
            closeConnection(conn);
        }
        return list;
    }

}
