package dao.impl;

import dao.BlogDAO;
import dao.DBContext;
import entity.Blog;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Vector;

/**
 *
 * @author Admin
 */
public class BlogDAOImpl extends DBContext implements BlogDAO{
    
    /**
     * Get All Blogs From Database
     *
     * @param
     * @return Vector Blog List
     */
    public ArrayList<Blog> getBlogList() throws Exception{

        // Create vector to store all Categories
        ArrayList<Blog> blogs = new ArrayList<>();
        
        // Query Statement to get all Categories in Database 
        String sqlQuery = "select * from Blog";

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
                // Get and store all attribute of each Product
                int blogID = rs.getInt(1);
                String blogTitle = rs.getString(2);
                String blogContent = rs.getString(3);
                String blogImageLink = rs.getString(4);
                int sellerID = rs.getInt(5);
                blogs.add(new Blog(blogID, blogTitle, blogContent, blogImageLink, sellerID));
            }
        } catch (SQLException ex) {
            throw ex;
        }finally {
            closeRS(rs);
            closePrepareStatement(preparedStatement);
            closeConnection(connection);
        }
        return blogs;
    } 
    
}
