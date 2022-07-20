package dao.impl;

import dao.BlogDAO;
import dao.DBContext;
import entity.Blog;
import entity.CommentBlog;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

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

    /**
     * Dislike a blog
     *
     * @param int user, String blogId
     */
    public void dislikeBlog(int user, String blogId) throws Exception {
        // Query Statement to get all Categories in Database 

        String sql = "delete from Like_Blog where userID=? and BlogID = ?";
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;
        try {
            //open connect
            connection = getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, user);
            preparedStatement.setString(2, blogId);
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            throw ex;
        } finally {
            closeRS(rs);
            closePrepareStatement(preparedStatement);
            closeConnection(connection);
        }
    }

    /**
     * insertlike a blog
     *
     * @param int uid, int bid
     */
    public void insertlike(int uid, int bid) throws Exception {
        // Query Statement to get all Categories in Database 

        String sql = "insert into Like_Blog values(?,?)";
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;
        try {
            //open connect

            connection = getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, uid);
            preparedStatement.setInt(2, bid);
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            throw ex;
        } finally {
            closeRS(rs);
            closePrepareStatement(preparedStatement);
            closeConnection(connection);
        }
    }

    /**
     * all blog in list favorite from database
     *
     * @param int index, int id
     * @return list
     */
    public List<Blog> pagingFavorite(int index, int id) throws Exception {
        // Query Statement to get all Categories in Database 

        String query = "SELECT * FROM Blog b join [Like_Blog] u on b.ID=u.BlogID where u.userID=?\n"
                + "                               order by ID\n"
                + "                               OFFSET ? ROWS  FETCH NEXT 5 ROWS ONLY";
        List<Blog> list = new ArrayList<>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;
        // Get all categories store to vector
        try {            //open connect

            connection = getConnection();
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(2, (index * 5 - 5));
            preparedStatement.setInt(1, id);
            rs = preparedStatement.executeQuery();
            while (rs.next()) {
                list.add(new Blog(rs.getInt("ID"),
                        rs.getString("Title"),
                        rs.getString("Content"),
                        rs.getString("imageLink"),
                        rs.getInt("SellerID"),
                        rs.getString("Modife")));
            }
            return list;
        } catch (SQLException ex) {
            throw ex;
        } finally {
            closeRS(rs);
            closePrepareStatement(preparedStatement);
            closeConnection(connection);
        }
    }

    /**
     * add comment a blog
     *
     * @param CommentBlog theFeedback
     * @return boolean
     */
    public boolean addComment(CommentBlog theFeedback) throws Exception {
        // Query Statement to get all Categories in Database 

        String query = "INSERT INTO Comment_Blogs VALUES (?, ?, ?, ?, ?);";
        int check = 0;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {            //open connect

            connection = getConnection();
            preparedStatement = connection.prepareStatement(query);
            //Set data to the "?"
            preparedStatement.setInt(1, theFeedback.getUserID());
            preparedStatement.setInt(2, theFeedback.getBlogID());
            preparedStatement.setString(3, theFeedback.getUserName());
            preparedStatement.setString(4, theFeedback.getCommentDetail());
            preparedStatement.setDate(5, theFeedback.getDateComment());
            check = preparedStatement.executeUpdate();

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
     * get blog by ID
     *
     * @param String id
     * @return Blog
     */
    public Blog getBlogByID(String id) throws Exception { //Must be int type because when saving to Session, it is still int
        // Query Statement to get all Categories in Database 

        String query = "SELECT * FROM Blog WHERE ID = ?";
        List<Blog> list = new ArrayList<>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;
        try {            //open connect

            connection = getConnection();
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, id);
            rs = preparedStatement.executeQuery();
            while (rs.next()) {
                return (new Blog(rs.getInt("ID"),
                        rs.getString("Title"),
                        rs.getString("Content"),
                        rs.getString("imageLink"),
                        rs.getInt("SellerID"),
                        rs.getString("Modife")));
            }
        } catch (SQLException ex) {
            throw ex;
        } finally {
            closeRS(rs);
            closePrepareStatement(preparedStatement);
            closeConnection(connection);
        }
        return null;
    }

    /**
     * get comment blog by ID
     *
     * @param String id
     * @return Arraylist
     */
    public ArrayList<CommentBlog> getCommentBlog(String id) throws Exception {

        // Create vector to store all Categories
        ArrayList<CommentBlog> blogs = new ArrayList<>();

        // Query Statement to get all Categories in Database 
        String sqlQuery = "select *from Comment_Blogs where BlogID=?";

        // Resultset to store all Categories 
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;
        // Get all categories store to vector
        try {
            connection = getConnection();
            preparedStatement = connection.prepareStatement(sqlQuery);
            preparedStatement.setString(1, id);
            rs = preparedStatement.executeQuery();
            while (rs.next()) {
                // Get and store all attribute of each Product
                blogs.add(new CommentBlog(
                        rs.getInt("CommentID"),
                        rs.getInt("UserID"),
                        rs.getInt("BlogID"),
                        rs.getString("UserName"),
                        rs.getString("CommentDetail"),
                        rs.getDate("DateComment")));
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

    /**
     * get total page
     *
     * @param
     * @return int
     */
    public int totalPage() {
        int total = 0;
        // Query Statement to get all Categories in Database 

        String query = "select count(*)\n"
                + "               from Blog";
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;
        try {            //open connect

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

    /**
     * get list Blog
     *
     * @param int index
     * @return List
     */
    public List<Blog> paging(int index) throws Exception {
        // Query Statement to get all Categories in Database 

        String query = "SELECT * FROM Blog \n"
                + "                order by ID\n"
                + "                OFFSET ? ROWS  FETCH NEXT 5 ROWS ONLY";
        List<Blog> list = new ArrayList<>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;
        // Get all categories store to vector
        try {            //open connect

            connection = getConnection();
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, (index * 5 - 5));
            rs = preparedStatement.executeQuery();
            while (rs.next()) {
                list.add(new Blog(rs.getInt("ID"),
                        rs.getString("Title"),
                        rs.getString("Content"),
                        rs.getString("imageLink"),
                        rs.getInt("SellerID"),
                        rs.getString("Modife")));
            }
            return list;
        } catch (SQLException ex) {
            throw ex;
        } finally {
            closeRS(rs);
            closePrepareStatement(preparedStatement);
            closeConnection(connection);
        }
    }

    /**
     * check like blog
     *
     * @param String user, String blogId
     * @return boolean
     */
    public boolean checkLikeBlog(String user, String blogId) throws Exception {
        // Query Statement to get all Categories in Database 

        String sql = "Select * from Like_Blog where userID = ? and BlogID= ?";
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;
        try {            //open connect

            connection = getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, user);
            preparedStatement.setString(2, blogId);
            rs = preparedStatement.executeQuery();
            while (rs.next()) {

                return true;
            }
        } catch (SQLException ex) {
            throw ex;
        } finally {
            closeRS(rs);
            closePrepareStatement(preparedStatement);
            closeConnection(connection);
        }
        return false;
    }

    /**
     * delete comment blog
     *
     * @param String id
     * @return
     */
    public void deleteCommentbyID(String id) throws Exception {
        // Query Statement to get all Categories in Database 

        String query = "delete from Comment_Blogs where CommentID=?";

        Connection conn = null;
        PreparedStatement prepare = null;

        try {            //open connect

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
     * get totalPagefavorite
     *
     * @param int id
     * @return int
     */
    public int totalPagefavorite(int id) throws Exception {
        int total = 0;
        // Query Statement to get all Categories in Database 

        String query = "Select count(ID) from Blog b join [Like_Blog] u on b.ID=u.BlogID where u.userID=?";
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;
        // Get all Blogs store to vector
        try {            //open connect

            connection = getConnection();
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);
            rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int totalA = rs.getInt(1);
                total = totalA / 5;
                if (totalA % 5 != 0) {
                    total++;
                }
            }
        } catch (SQLException ex) {
            throw ex;
        } finally {
            closeRS(rs);
            closePrepareStatement(preparedStatement);
            closeConnection(connection);
        }
        return total;
    }

    public static void main(String[] args) {
        BlogDAOImpl f = new BlogDAOImpl();
        List<Blog> list = new ArrayList<>();
        try {
            System.out.println(f.totalPagefavorite(4));
        } catch (Exception ex) {
            Logger.getLogger(BlogDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
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
