package dao.impl;

import dao.BlogDAO;
import dao.DBContext;
import entity.Blog;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
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
    public int totalPage() {
        int total = 0;
        String query = "select count(*)\n" +
"               from Blog";
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;
        // Get all categories store to vector
        try {
            connection = getConnection();
            preparedStatement = connection.prepareStatement(query);
            rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int totalA = rs.getInt(1);
                total = totalA / 5;
                if (totalA % 5 != 0) {
                    total++;
                }
            }
        } catch (Exception e) {
        }
        return total;
    }            

    public List<Blog> paging(int index) {
        String query = "SELECT * FROM Blog \n" +
"                order by ID\n" +
"                OFFSET ? ROWS  FETCH NEXT 5 ROWS ONLY";
        List<Blog> list = new ArrayList<>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;
        // Get all categories store to vector
        try {
            connection = getConnection();
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, (index * 5 - 5));
            rs = preparedStatement.executeQuery();
            while (rs.next()) {
                list.add(new Blog(rs.getInt("ID"),
                        rs.getString("Title"),
                        rs.getString("Content"),
                        rs.getString("imageLink"),
                        rs.getInt("SellerID")));
            }
            return list;
        } catch (Exception e) {
        }
        return null;
    }
    public static void main(String[] args) {
        BlogDAOImpl f = new BlogDAOImpl();
        List<Blog> list = new ArrayList<>();
        list=f.paging(1);
        for (Blog blog : list) {
            System.out.println(blog);
        }
    }
    
}
