package model;

import entity.Feedback;
import entity.Product;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

public class FeedbackDAO extends DBContext {

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

    public void addFeedback() {

    }

//    public static void main(String[] args) {
//        FeedbackDAO feedbackDAO = new FeedbackDAO();
//        for (Feedback f : feedbackDAO.getFeedBackByPID(6)) {
//            System.out.println(f);
//        }
//    }

}
