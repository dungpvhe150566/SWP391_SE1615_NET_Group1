package dao.impl;

import dao.BlogDAO;
import dao.DBContext;
import entity.Blog;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

/**
 *
 * @author Admin
 */
public class BlogDAOImpl extends DBContext implements BlogDAO {

    /**
     * Get All Blogs From Database
     *
     * @param
     * @return Vector Blog List
     */
    public ArrayList<Blog> getBlogList() throws Exception {

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
        } finally {
            closeRS(rs);
            closePrepareStatement(preparedStatement);
            closeConnection(connection);
        }
        return blogs;
    }

    public int totalPage() {
        int total = 0;
        String query = "select count(*)\n"
                + "               from Blog";
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
        String query = "SELECT * FROM Blog \n"
                + "                order by ID\n"
                + "                OFFSET ? ROWS  FETCH NEXT 5 ROWS ONLY";
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
        list = f.paging(1);
        for (Blog blog : list) {
            System.out.println(blog);
        }
    }

    /**
     * Get All Blogs From Database follow Title
     *
     * @param
     * @return Vector have max 6 Product (following Pagination)
     */
    public ArrayList<Blog> getBlogList(String title, int start, int end) throws Exception {
        // Create vector to store all Categories
        ArrayList<Blog> blogs = new ArrayList<>();

        // Query Statement to get all Categories in Database 
        String sqlQuery = "with x as (	select row_number() over(order by ID asc) as row, * from Blog "
                + "where (Title like '%" + title.trim() + "%') "
                + " ) "
                + "select * from x where  row between  " + start + " and " + end;

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
                int id = rs.getInt("ID");
                String titleContent = rs.getString("Title");
                String content = rs.getString("Content");
                String image = rs.getString("imageLink");
                int sellerID = rs.getInt("SellerID");
                // Add Product to vector 
                blogs.add(new Blog(id, titleContent, content, image, sellerID));
            }
        } catch (Exception ex) {
            throw ex;
        } finally {
            closeRS(rs);
            closePrepareStatement(preparedStatement);
            closeConnection(connection);
        }
        return blogs;
    }

    /**
     * Get Total Page Blogs From Database follow Title
     *
     * @param
     * @return number of Page Products
     */
    public int getTotalPage(String title, int numOfRecord) throws Exception {

        //  Variable to store the condition values passed to filter products in Database
        int totalPage = 0;

        // Query statement to get total Product in Database
        String sqlQuery = "select count(ID) from Blog "
                + " where (Title like '%" + title + "%') ";

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;
        // Execute query to get total Product
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
        // convet total product to total page (each page have 6 product)
        return (int) Math.ceil((double) totalPage / numOfRecord);
    }

    public int deleteBlog(int blogID) throws Exception {
        int n = 0;
        String sql = "delete from Blog where ID = " + blogID;

//        Connection conn = null;
//        PreparedStatement prepare = null;
//        ResultSet rs = null;
        try {
            Statement state = getConnection().createStatement();

            n = state.executeUpdate(sql);
        } catch (SQLException ex) {
            throw ex;
        }
        return n;
    }

    public int deleteBlogs(String[] arrBlogID) throws Exception {

        String blogIDs = "";
        for (String s : arrBlogID) {
            blogIDs = blogIDs.concat(s + " ");
        }
        blogIDs = blogIDs.trim().replace(" ", ",");

        int n = 0;
        String sql = "delete from Blog where ID in (" + blogIDs + ")";

        try {
            Statement state = getConnection().createStatement();

            n = state.executeUpdate(sql);
        } catch (SQLException ex) {
            throw ex;
        }
        return n;
    }

    public int addBlog(Blog blog) throws Exception {
        int n = 0;
        String preSql = "INSERT INTO [ElectronicShop].[dbo].[Blog]\n"
                + "           ([Title]\n"
                + "           ,[Content]\n"
                + "           ,[imageLink]\n"
                + "           ,[SellerID])\n"
                + "     VALUES\n"
                + "           (?,?,?,?)";

        Connection conn = null;
        PreparedStatement prepare = null;
        try {
//            PreparedStatement pre = conn.prepareStatement(preSql);
            conn = getConnection();
            prepare = conn.prepareStatement(preSql);

            prepare.setString(1, blog.getTitle());
            prepare.setString(2, blog.getContent());
            prepare.setString(3, blog.getImageLink());
            prepare.setInt(4, blog.getSellerID());

            n = prepare.executeUpdate();
        } finally {
            closePrepareStatement(prepare);
            closeConnection(conn);
        }

        return n;
    }

    public int editBlog(Blog blog) throws Exception {
        int n = 0;
        String preSql = "UPDATE [ElectronicShop].[dbo].[Blog]\n"
                + "   SET [Title] = ?\n"
                + "      ,[Content] = ?\n"
                + "      ,[imageLink] = ?\n"
                + " WHERE ID = " + blog.getID();

        Connection conn = null;
        PreparedStatement prepare = null;
        try {
//            PreparedStatement pre = conn.prepareStatement(preSql);
            conn = getConnection();
            prepare = conn.prepareStatement(preSql);

            prepare.setString(1, blog.getTitle());
            prepare.setString(2, blog.getContent());
            prepare.setString(3, blog.getImageLink());

            n = prepare.executeUpdate();
        } finally {
            closePrepareStatement(prepare);
            closeConnection(conn);
        }

        return n;
    }

    public Blog getBlog(int blogID) throws Exception {
        // Create vector to store all Categories
        Blog blog = null;

        // Query Statement to get all Categories in Database 
        String sqlQuery = "select * from Blog where ID =" + blogID;

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
                String blogTitle = rs.getString(2);
                String blogContent = rs.getString(3);
                String blogImageLink = rs.getString(4);
                int sellerID = rs.getInt(5);
                blog = new Blog(blogID, blogTitle, blogContent, blogImageLink, sellerID);
            }
        } catch (SQLException ex) {
            throw ex;
        } finally {
            closeRS(rs);
            closePrepareStatement(preparedStatement);
            closeConnection(connection);
        }
        return blog;
    }
}
