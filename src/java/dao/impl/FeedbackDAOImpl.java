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
import java.util.List;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FeedbackDAOImpl extends DBContext implements FeedbackDAO{

    PreparedStatement ps = null; //...
    ResultSet rs = null; //Nhận kết quả trả về
    public Vector<Feedback> getFeedBackByPID(int productID) throws Exception{
        // Create vector to store all Categories
        Vector<Feedback> feedbacks = new Vector<>();

        // Query Statement to get all Categories in Database 
        String sqlQuery = "select f.ID,\n"
                + "		 u.Username,\n"
                + "		 o.DayBuy,\n"
                + "		 f.Star,\n"
                + "		 f.FeedbackDetail\n"
                + "		 from Feedback f join Orders o on f.OrderID = o.ID join Users u on o.UserID = u.UserID where f.ProductID="+productID;

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
                int feedbackID = rs.getInt(1);
                String userName = rs.getString(2);
                String dayFeedBack = rs.getString(3);
                int star = rs.getInt(4);
                String feedbackDetails = rs.getString(5);
                // Add FeedBack to vector 
                feedbacks.add(new Feedback(feedbackID, userName, dayFeedBack, star, feedbackDetails));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }finally {
            closeRS(rs);
            closePrepareStatement(preparedStatement);
            closeConnection(connection);
        }
        return feedbacks;
    }

    public List<Feedback> getFeedbacks() throws Exception{
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
        }
        finally {
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
    public Feedback getFeedbacksById(int id) throws Exception{
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
                        rs.getInt("ID"),
                        rs.getInt("UserID"),
                        rs.getInt("ProductID"),
                        rs.getInt("Star"),
                        rs.getString("FeedbackDetail")
                );
                return f;
            }

        } catch (Exception e) {
            throw e;
        }
        finally {
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
    public List<Feedback> getFeedbacksByProductId(int productId) throws Exception{
        String query = "SELECT * FROM Feedback f "
                + "join Users u on f.UserID=u.UserID"
                + " join Product p on p.ProductID=f.ProductID WHERE f.ProductID = ?";
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
        }
        finally {
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
    public List<Feedback> getFeedbacksByUserId(int userId) throws Exception{
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
        }finally {
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
        }finally {
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
    public List<Feedback> getFeedbacksByOrderId(int orderId) throws Exception{
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
        }finally {
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
    public boolean addFeedback(Feedback theFeedback) throws Exception{
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
        }finally {
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
    public int countTotalFeedback() throws Exception{
        try {
            return getFeedbacks().size();
        } catch (Exception ex) {
            throw ex;
        }
    }

//    public static void main(String[] args) {
//        FeedbackDAOImpl feedbackDAO = new FeedbackDAOImpl();
//        for (Feedback f : feedbackDAO.getFeedbacksByProductId(2)) {
//            System.out.println(f);
//        }
//    }

}
