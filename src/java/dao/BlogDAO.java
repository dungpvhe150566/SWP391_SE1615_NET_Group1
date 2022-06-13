package dao;

import entity.Blog;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

/**
 *
 * @author Admin
 */
public class BlogDAO extends DBContext{
    
    /**
     * Get All Blogs From Database
     *
     * @param
     * @return Vector Blog List
     */
    public Vector<Blog> getBlogList() {

        // Create vector to store all Categories
        Vector<Blog> blogs = new Vector<>();
        ResultSet rs = null;
        
        // Query Statement to get all Categories in Database 
        String sqlQuery = "select * from Blog";

        // Resultset to store all Categories 
        

        // Get all categories store to vector
        try {
            rs = getData(sqlQuery);
            while (rs.next()) {
                // Get and store all attribute of each Product
                int blogID = rs.getInt(1);
                String blogTitle = rs.getString(2);
                String blogContent = rs.getString(3);
                String blogImageLink = rs.getString(4);
                int sellerID = rs.getInt(5);
                blogs.add(new Blog(blogID, blogTitle, blogContent, blogImageLink, sellerID));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return blogs;
    } 
    
}
