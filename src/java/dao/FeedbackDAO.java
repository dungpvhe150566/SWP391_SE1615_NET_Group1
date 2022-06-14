package dao;

import entity.Feedback;
import entity.Product;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class FeedbackDAO extends DBContext {

    PreparedStatement ps = null; //...
    ResultSet rs = null; //Nhận kết quả trả về
    public Vector<Feedback> getFeedBackByPID(int productID) {
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
        ResultSet rs = getData(sqlQuery);

        // Get all categories store to vector
        try {
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
        }
        return feedbacks;
    }

    public List<Feedback> getFeedbacks() {
        String query = "SELECT * FROM Feedback";
        try {
            List<Feedback> lsFeedback = new ArrayList<>();
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();
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
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Get a feedback by id
     *
     * @param id the id of the feedback
     * @return a feedback with the specified id
     */
    public Feedback getFeedbacksById(int id) {
        String query = "SELECT * FROM Feedback WHERE ID = ?";
        try {
            Feedback f;
            ps = conn.prepareStatement(query);
            ps.setInt(1, id);
            rs = ps.executeQuery();
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
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Get a list of feedback by product id
     *
     * @param productId the id of the product
     * @return a list of feedback
     */
    public List<Feedback> getFeedbacksByProductId(int productId) {
        String query = "SELECT * FROM Feedback f "
                + "join Users u on f.UserID=u.UserID"
                + " join Product p on p.ProductID=f.ProductID WHERE f.ProductID = ?";
        try {
            List<Feedback> lsFeedback = new ArrayList<>();
            ps = conn.prepareStatement(query);
            ps.setInt(1, productId);
            rs = ps.executeQuery();
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
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Get a list of feedback by user id
     *
     * @param userId the id of the user
     * @return a list of feedback
     */
    public List<Feedback> getFeedbacksByUserId(int userId) {
        String query = "SELECT * FROM Feedback WHERE UserID = ?";
        try {
            List<Feedback> lsFeedback = new ArrayList<>();
            ps = conn.prepareStatement(query);
            ps.setInt(1, userId);
            rs = ps.executeQuery();
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
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Get a list of feedback by user id and product id
     *
     * @param userId the id of the user
     * @param productId the id of the product
     * @return list of feedback with the user id and product id
     */
    public List<Feedback> getFeedbacksByUserIdAndProductId(int userId, int productId) {
        String query = "SELECT * FROM Feedback WHERE UserID = ?"
                + " AND ProductID = ?";
        try {
            List<Feedback> lsFeedback = new ArrayList<>();
            ps = conn.prepareStatement(query);
            ps.setInt(1, userId);
            ps.setInt(2, productId);
            rs = ps.executeQuery();
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
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Get a feedback by order id
     *
     * @param orderId the id of the order
     * @return a list of feedback with the order id
     */
    public List<Feedback> getFeedbacksByOrderId(int orderId) {
        String query = "SELECT * FROM Feedback WHERE OrderID = ? ";
        try {
            List<Feedback> lsFeedback = new ArrayList<>();
            ps = conn.prepareStatement(query);
            ps.setInt(1, orderId);

            rs = ps.executeQuery();
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
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Add a feedback to the database
     *
     * @param theFeedback to add to database
     * @return true if add successful, else false
     */
    public boolean addFeedback(Feedback theFeedback) {
        String query = "INSERT INTO Feedback VALUES (?, ?, ?, ?, ?);";
        int check = 0;
        try {
            ps = conn.prepareStatement(query);
            //Set data to the "?"
            ps.setInt(1, theFeedback.getUserID());
            ps.setInt(2, theFeedback.getProductID());
            ps.setInt(3, theFeedback.getStar());
            ps.setString(4, theFeedback.getFeedbackDetails());
            ps.setDate(5, theFeedback.getDateFeedback());
            check = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            check = -1;
        }
        return check > 0;
    }

    /**
     * Get total count of all feedback
     *
     * @return total count of all feedback
     */
    public int countTotalFeedback() {
        return getFeedbacks().size();
    }

    public static void main(String[] args) {
        FeedbackDAO feedbackDAO = new FeedbackDAO();
        for (Feedback f : feedbackDAO.getFeedbacksByProductId(2)) {
            System.out.println(f);
        }
    }

}
