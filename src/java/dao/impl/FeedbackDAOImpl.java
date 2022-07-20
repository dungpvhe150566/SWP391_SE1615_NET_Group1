package dao.impl;

import dao.DBContext;
import dao.FeedbackDAO;
import entity.Feedback;
import entity.Product;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FeedbackDAOImpl extends DBContext implements FeedbackDAO {

    PreparedStatement ps = null; //...
    ResultSet rs = null; //Nhận kết quả trả về

    public ArrayList<Feedback> getFeedBackByPID(int productID) throws Exception {
        // Create vector to store all Categories
        ArrayList<Feedback> feedbacks = new ArrayList<>();

        // Query Statement to get all Categories in Database 
        String sqlQuery = "select f.ID,\n"
                + "		 u.Username,\n"
                + "		 f.DateComment,\n"
                + "		 f.Star,\n"
                + "		 f.FeedbackDetail\n"
                + "		 from Feedback f join Users u on f.UserID = u.UserID where f.ProductID=" + productID;

        // Resultset to store all Categories 
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;
        // Get all categories store to vector
        try {
            connection = getConnection();
            preparedStatement = connection.prepareStatement(sqlQuery);
            rs = preparedStatement.executeQuery();
            while (rs.next()) {
                // Get and store all attribute of each Feedback
                int feedbackID = rs.getInt("ID");
                String userName = rs.getString("Username");
                Date dayFeedBack = rs.getDate("DateComment");
                int star = rs.getInt("Star");
                String feedbackDetails = rs.getString("FeedbackDetail");
                // Add FeedBack to vector 
                feedbacks.add(new Feedback(feedbackID, userName, dayFeedBack, star, feedbackDetails));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            closeRS(rs);
            closePrepareStatement(preparedStatement);
            closeConnection(connection);
        }
        return feedbacks;
    }

    /**
     * Get a feedback
     *
     * @param
     * @return list
     */
    public List<Feedback> getFeedbacks() throws Exception {
        String query = "SELECT * FROM Feedback";
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;
        try {
            connection = getConnection();
            preparedStatement = connection.prepareStatement(query);
            rs = preparedStatement.executeQuery();
            List<Feedback> lsFeedback = new ArrayList<>();
            while (rs.next()) {
                Feedback f = new Feedback(
                        rs.getInt("ID"),
                        rs.getInt("UserID"),
                        rs.getInt("ProductID"),
                        rs.getInt("Star"),
                        rs.getString("FeedbackDetail")
                );
                lsFeedback.add(f);
            }
            return lsFeedback;
        } catch (Exception e) {
            throw e;
        } finally {
            closeRS(rs);
            closePrepareStatement(preparedStatement);
            closeConnection(connection);
        }
    }

    /**
     * Get a feedback by id
     *
     * @param id the id of the feedback
     * @return a feedback with the specified id
     */
    public Feedback getFeedbacksById(int id) throws Exception {
        String query = "SELECT * FROM Feedback WHERE ID = ?";
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;
        try {
            Feedback f;
            connection = getConnection();
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);
            rs = preparedStatement.executeQuery();
            if (rs.next()) {
                f = new Feedback(
                        rs.getInt("UserID"),
                        rs.getInt("ProductID"),
                        rs.getInt("Star"),
                        rs.getString("FeedbackDetail"),
                        rs.getDate("DateComment")
                );
                return f;
            }

        } catch (Exception e) {
            throw e;
        } finally {
            closeRS(rs);
            closePrepareStatement(preparedStatement);
            closeConnection(connection);
        }
        return null;
    }

    /**
     * Get a list of feedback by product id
     *
     * @param productId the id of the product
     * @return a list of feedback
     */
    public List<Feedback> getFeedbacksBySellerId(int productId) throws Exception {
        String query = "select *from Feedback f join Product p on "
                + "p.ProductID=f.ProductID join Users u "
                + "on u.UserID=f.UserID where p.SellerID=?";
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;
        try {
            List<Feedback> lsFeedback = new ArrayList<>();
            connection = getConnection();
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, productId);
            rs = preparedStatement.executeQuery();
            while (rs.next()) {
                Feedback f = new Feedback(
                        rs.getInt("ID"),
                        rs.getString("Username"),
                        rs.getString("ProductName"),
                        rs.getInt("Star"),
                        rs.getString("FeedbackDetail")
                );
                lsFeedback.add(f);
            }
            return lsFeedback;
        } catch (Exception e) {
            throw e;
        } finally {
            closeRS(rs);
            closePrepareStatement(preparedStatement);
            closeConnection(connection);
        }
    }

    /**
     * Get a list of feedback sort
     *
     * @param int sellerID, String sort, String namesort, int start, int end
     * @return ArrayList
     */
    public ArrayList<Feedback> getFeedbacksSort(int sellerID, String sort, String namesort, int start, int end) throws Exception {
        String query = "with a as (	select row_number() over(order by ID  asc) as row, ID,Username,ProductName,Star,FeedbackDetail from Feedback f join Product p \n"
                + "                on p.ProductID=f.ProductID join Users u \n"
                + "                on u.UserID=f.UserID where p.SellerID=? \n"
                + "                                            ) \n"
                + "                                             select * from a where  row between " + start + " and " + end + " order by " + namesort + " " + sort;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;
        try {
            ArrayList<Feedback> lsFeedback = new ArrayList<>();
            connection = getConnection();
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, sellerID);
            rs = preparedStatement.executeQuery();
            while (rs.next()) {
                Feedback f = new Feedback(
                        rs.getInt("ID"),
                        rs.getString("Username"),
                        rs.getString("ProductName"),
                        rs.getInt("Star"),
                        rs.getString("FeedbackDetail")
                );
                lsFeedback.add(f);
            }
            return lsFeedback;
        } catch (Exception e) {
            throw e;
        } finally {
            closeRS(rs);
            closePrepareStatement(preparedStatement);
            closeConnection(connection);
        }
    }

    /**
     * Get a list of feedback by user id
     *
     * @param userId the id of the user
     * @return a list of feedback
     */
    public List<Feedback> getFeedbacksByUserId(int userId) throws Exception {
        String query = "SELECT * FROM Feedback WHERE UserID = ?";
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;
        try {
            List<Feedback> lsFeedback = new ArrayList<>();
            connection = getConnection();
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, userId);
            rs = preparedStatement.executeQuery();
            while (rs.next()) {
                Feedback f = new Feedback(
                        rs.getInt("ID"),
                        rs.getInt("UserID"),
                        rs.getInt("ProductID"),
                        rs.getInt("Star"),
                        rs.getString("FeedbackDetail")
                );
                lsFeedback.add(f);
            }
            return lsFeedback;
        } catch (Exception e) {
            throw e;
        } finally {
            closeRS(rs);
            closePrepareStatement(preparedStatement);
            closeConnection(connection);
        }
    }

    /**
     * Get a list of feedback by user id and product id
     *
     * @param userId the id of the user
     * @param productId the id of the product
     * @return list of feedback with the user id and product id
     */
    public List<Feedback> getFeedbacksByUserIdAndProductId(int userId, int productId) throws Exception {
        String query = "SELECT * FROM Feedback WHERE UserID = ?"
                + " AND ProductID = ?";
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;
        try {
            List<Feedback> lsFeedback = new ArrayList<>();
            connection = getConnection();
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, userId);
            rs = preparedStatement.executeQuery();
            while (rs.next()) {
                Feedback f = new Feedback(
                        rs.getInt("ID"),
                        rs.getInt("UserID"),
                        rs.getInt("ProductID"),
                        rs.getInt("Star"),
                        rs.getString("FeedbackDetail")
                );
                lsFeedback.add(f);
            }
            return lsFeedback;
        } catch (Exception e) {
            throw e;
        } finally {
            closeRS(rs);
            closePrepareStatement(preparedStatement);
            closeConnection(connection);
        }
    }

    /**
     * Get a feedback by order id
     *
     * @param orderId the id of the order
     * @return a list of feedback with the order id
     */
    public List<Feedback> getFeedbacksByOrderId(int orderId) throws Exception {
        String query = "SELECT * FROM Feedback WHERE OrderID = ? ";
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;
        try {
            List<Feedback> lsFeedback = new ArrayList<>();
            connection = getConnection();
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, orderId);
            rs = preparedStatement.executeQuery();

            while (rs.next()) {
                Feedback f = new Feedback(
                        rs.getInt("ID"),
                        rs.getInt("UserID"),
                        rs.getInt("ProductID"),
                        rs.getInt("Star"),
                        rs.getString("FeedbackDetail")
                );
                lsFeedback.add(f);
            }
            return lsFeedback;
        } catch (Exception e) {
            throw e;
        } finally {
            closeRS(rs);
            closePrepareStatement(preparedStatement);
            closeConnection(connection);
        }
    }

    /**
     * Add a feedback to the database
     *
     * @param theFeedback to add to database
     * @return true if add successful, else false
     */
    public boolean addFeedback(Feedback theFeedback) throws Exception {
        String query = "INSERT INTO Feedback VALUES (?, ?, ?, ?, ?);";
        int check = 0;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = getConnection();
            preparedStatement = connection.prepareStatement(query);
            //Set data to the "?"
            preparedStatement.setInt(1, theFeedback.getUserID());
            preparedStatement.setInt(2, theFeedback.getProductID());
            preparedStatement.setInt(3, theFeedback.getStar());
            preparedStatement.setString(4, theFeedback.getFeedbackDetails());
            preparedStatement.setDate(5, theFeedback.getDateFeedback());
            check = preparedStatement.executeUpdate();
//            check = ps.executeUpdate();
        } catch (Exception e) {
            check = -1;
            throw e;
        } finally {
            closePrepareStatement(preparedStatement);
            closeConnection(connection);
        }
        return check > 0;
    }

    /**
     * Get total count of all feedback
     *
     * @return total count of all feedback
     */
    public int countTotalFeedback() throws Exception {
        try {
            return getFeedbacks().size();
        } catch (Exception ex) {
            throw ex;
        }
    }

    /**
     * Get feedback by productid
     *
     * @param int start, int end
     * @return list
     */
    public List<Feedback> getFeedbacksByProductId(int start, int end) throws Exception {
        String query = "with a as (	select row_number() over(order by ID  asc) as row, ID,Username,ProductName,Star,FeedbackDetail from Feedback f \n"
                + "                join Users u on f.UserID=u.UserID\n"
                + "                 join Product p on p.ProductID=f.ProductID \n"
                + "                              ) \n"
                + "                              select * from a where  row between " + start + " and " + end;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;
        try {
            List<Feedback> lsFeedback = new ArrayList<>();
            connection = getConnection();
            preparedStatement = connection.prepareStatement(query);
            rs = preparedStatement.executeQuery();
            while (rs.next()) {
                Feedback f = new Feedback(
                        rs.getInt("ID"),
                        rs.getString("Username"),
                        rs.getString("ProductName"),
                        rs.getInt("Star"),
                        rs.getString("FeedbackDetail")
                );
                lsFeedback.add(f);
            }
            return lsFeedback;
        } catch (Exception e) {
            throw e;
        } finally {
            closeRS(rs);
            closePrepareStatement(preparedStatement);
            closeConnection(connection);
        }
    }

    /**
     * get Total Page Feedbacks
     *
     * @param
     * @return int
     */
    public int getTotalPageFeedbacks() throws Exception {

        //  Variable to store the condition values passed to filter feedbacks in Database
        int totalPage = 0;

        // Query statement to get total feedbacks in Database
        String sqlQuery = "select count(ID) from Feedback ";

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;
        // Execute query to get total feedbacks
        try {
            connection = getConnection();
            preparedStatement = connection.prepareStatement(sqlQuery);
            rs = preparedStatement.executeQuery();
            // set total feedbacks to variable
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
        // convet total feedbacks to total page (each page have 6 product)
        return (int) Math.ceil((double) totalPage / 6);
    }

    /**
     * delete Feedback by ID
     *
     * @param String id
     * @return
     */
    public void deleteFeedbackbyID(String id) throws Exception {
        String query = "delete from Feedback where ID=?";

        Connection conn = null;
        PreparedStatement prepare = null;

        try {
            conn = getConnection();
            prepare = conn.prepareStatement(query);
            prepare.setString(1, id);
            prepare.executeUpdate();
        } catch (Exception e) {
            throw e;
        } finally {
            closePrepareStatement(prepare);
            closeConnection(conn);
        }
        return;
    }

    /**
     * total page
     *
     * @param int index
     * @return int
     */
    public int totalPage(int index) throws Exception {
        //  Variable to store the condition values passed to filter feedbacks in Database
        int totalPage = 0;

        // Query statement to get total feedbacks in Database
        String sqlQuery = "select count(ID) from Feedback f join "
                + "Users u on f.UserID=u.UserID join Product p on"
                + " p.ProductID=f.ProductID where p.SellerID=?";

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;
        // Get all categories store to vector
        try {
            connection = getConnection();
            preparedStatement = connection.prepareStatement(sqlQuery);
            preparedStatement.setInt(1, index);

            rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int totalA = rs.getInt(1);
                totalPage = totalA / 6;
                if (totalA % 6 != 0) {
                    totalPage++;
                }
            }
        } catch (Exception ex) {
            throw ex;
        } finally {
            closeConnection(connection);
            closePrepareStatement(preparedStatement);
            closeRS(rs);
        }
        // convet total feedbacks to total page (each page have 6 product)
        return totalPage;
    }

    /**
     * paging view list feedback
     *
     * @param int index, String name, String sort, int sellerID
     * @return list
     */
    public List<Feedback> paging(int index, String name, String sort, int sellerID) throws Exception {
        String query = "with a as (	select row_number() over(order by ID  asc) as row, ID,Username,ProductName,Star,FeedbackDetail from Feedback f join Product p \n"
                + "                on p.ProductID=f.ProductID join Users u \n"
                + "                on u.UserID=f.UserID where p.SellerID=? \n"
                + "                                            ) \n"
                + "                                             select * from a order by " + name + " " + sort + "\n"
                + "                        OFFSET ? ROWS  FETCH NEXT 6 ROWS ONLY";
        List<Feedback> list = new ArrayList<>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;
        // Get all categories store to vector
        try {
            connection = getConnection();
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, sellerID);
            preparedStatement.setInt(2, (index * 6 - 6));
            rs = preparedStatement.executeQuery();
            while (rs.next()) {
                list.add(new Feedback(rs.getInt("ID"),
                        rs.getString("Username"),
                        rs.getString("ProductName"),
                        rs.getInt("Star"),
                        rs.getString("FeedbackDetail")));
            }
            return list;
        } catch (Exception ex) {
            throw ex;
        } finally {
            closeConnection(connection);
            closePrepareStatement(preparedStatement);
            closeRS(rs);
        }
    }

    public static void main(String[] args) {
        FeedbackDAOImpl feedbackDAO = new FeedbackDAOImpl();
        try {
            List<Feedback> lsFeedback = new ArrayList<>();
            lsFeedback = feedbackDAO.paging(1, "star", "desc", 3);
            System.out.println(feedbackDAO.totalPage(3));
            for (Feedback feedback : lsFeedback) {
                System.out.println(feedback);
            }
        } catch (Exception ex) {
            Logger.getLogger(FeedbackDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
