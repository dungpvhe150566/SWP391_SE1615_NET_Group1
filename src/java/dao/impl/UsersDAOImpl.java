package dao.impl;

import dao.DBContext;
import dao.UserDAO;
import entity.Users;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import static javafx.scene.input.KeyCode.U;
import static jdk.nashorn.internal.runtime.regexp.joni.constants.AsmConstants.S;

/**
 *
 * @author HP
 */
public class UsersDAOImpl extends DBContext implements UserDAO {

    /**
     * get all user
     *
     * @param
     * @return list
     */
    public List<Users> getAll() throws Exception { //Get user data in the database
        List<Users> list = new ArrayList<>();
        String sql = "select * from Users";
        Connection conn = null;
        PreparedStatement prepare = null;
        ResultSet rs = null;
        try {
//            PreparedStatement st = conn.prepareStatement(sql);
//            ResultSet rs = st.executeQuery();

            conn = getConnection();
            prepare = conn.prepareStatement(sql);
            rs = prepare.executeQuery();
            while (rs.next()) {
                Users U = new Users();
                U.setUsername(rs.getString(2));
                U.setEmail(rs.getString(4));
                U.setPassword(rs.getString(3));
                U.setUserID(rs.getInt(1));
                U.setIsAdmin(rs.getInt("isSeller"));
                U.setIsAdmin(rs.getInt("isAdmin"));
                U.setActiveCode(rs.getString("ActiveCode"));
                U.setStatusID(rs.getInt("StatusID"));
                list.add(U);
            }
        } catch (Exception ex) {
            throw ex;
        } finally {
            closeRS(rs);
            closePrepareStatement(prepare);
            closeConnection(conn);
        }
        return list;
    }

    /**
     * get all user
     *
     * @param
     * @return list
     */
    public void resetPassword(String pass, String email) {
        String sql = "update [Users] set Password = ? where email = ?";
        Connection conn = null;
        PreparedStatement prepare = null;
        try {
            conn = getConnection();
            prepare = conn.prepareStatement(sql);
            prepare.setString(1, pass);
            prepare.setString(2, email);
            prepare.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    /**
     * search Account In Manager
     *
     * @param String name, int start, int end
     * @return ArrayList
     */
    public ArrayList<Users> searchAccountInManager(String name, int start, int end) {
        ArrayList<Users> list = new ArrayList<>();
        String query = "with x as (	select row_number() over(order by UserID  asc) as row, * from Users WHERE Username LIKE ?\n"
                + "                           ) \n"
                + "                           select * from x where  row between " + start + " and " + end;
        Connection conn = null;
        PreparedStatement prepare = null;
        ResultSet rs = null;
        try {
            conn = getConnection();
            prepare = conn.prepareStatement(query);
            prepare.setString(1, "%" + name + "%");
            rs = prepare.executeQuery();
            while (rs.next()) {
                list.add(new Users(rs.getInt(2), rs.getString(3),
                        rs.getString(4), rs.getString(5),
                        rs.getString(6), rs.getInt(7),
                        rs.getInt(8), rs.getInt(9)));
            }
        } catch (Exception e) {
        }
        return list;
    }

    /**
     * get Account By ID
     *
     * @param int id
     * @return Users
     */
    public Users getAccountByID(int id) throws Exception {
        String query = "SELECT * FROM Users WHERE UserID = ?";
        Connection conn = null;
        PreparedStatement prepare = null;
        ResultSet rs = null;
        try {
//            ps = conn.prepareStatement(query);
//            ps.setString(1, id);
//            rs = ps.executeQuery();

            conn = getConnection();
            prepare = conn.prepareStatement(query);
            prepare.setInt(1, id);
            rs = prepare.executeQuery();
            while (rs.next()) {
                return new Users(rs.getInt(1), rs.getString(2),
                        rs.getString(3), rs.getString(4),
                        rs.getString(5), rs.getInt(6),
                        rs.getInt(7), rs.getInt(8));
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

    /**
     * get Account By ID
     *
     * @param String id
     * @return Users
     */
    public Users getAccountByID(String id) throws Exception {
        String query = "SELECT * FROM Users WHERE UserID = ?";
        Connection conn = null;
        PreparedStatement prepare = null;
        ResultSet rs = null;
        try {
//            ps = conn.prepareStatement(query);
//            ps.setString(1, id);
//            rs = ps.executeQuery();

            conn = getConnection();
            prepare = conn.prepareStatement(query);
            prepare.setString(1, id);
            rs = prepare.executeQuery();
            while (rs.next()) {
                return new Users(rs.getInt(1), rs.getString(2),
                        rs.getString(3), rs.getString(4),
                        rs.getString(5), rs.getInt(6),
                        rs.getInt(7), rs.getInt(8));
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

    /**
     * insert user
     *
     * @param String email, String username, String password
     * @return
     */
    public void insert(String email, String username, String password) throws Exception { // insert user information into database
        String query = "insert into Users(Username,Password,email) values (?,?,?) ";
        Connection conn = null;
        PreparedStatement prepare = null;
        try {
            int result = 0;
            conn = getConnection();
            prepare = conn.prepareStatement(query);
            prepare.setString(1, username);
            prepare.setString(2, password);
            prepare.setString(3, email);
            prepare.executeUpdate();
        } catch (Exception e) {
            throw e;
        } finally {
            closePrepareStatement(prepare);
            closeConnection(conn);
        }
    }

    /**
     * delete Account
     *
     * @param String id
     * @return
     */
    public int checkExistOrder(String id) throws Exception {
        String query = "select count(ID) from orders where userID=?";
        int count = 0;
        Connection conn = null;
        PreparedStatement prepare = null;
        ResultSet rs = null;

        try {
            conn = getConnection();
            prepare = conn.prepareStatement(query);
            prepare.setString(1, id);
            rs = prepare.executeQuery();
            // set count order to variable
            if (rs.next()) {
                count = rs.getInt(1);
            }

        } catch (Exception e) {
            throw e;
        } finally {
            closeRS(rs);
            closePrepareStatement(prepare);
            closeConnection(conn);
        }
        return count;
    }

    /**
     * delete Account
     *
     * @param String id
     * @return
     */
    public void deleteAccount(String id) throws Exception {
        String query = "	delete from Product where SellerID =?\n"
                + "                delete from Cart where UserID = ?\n"
                + "                delete from Feedback where UserID = ?\n"
                + "                delete from Blog where SellerID=?\n"
                + "                delete from UserAddress where UserID=?\n"
                + "				delete from Comment_Blogs where UserID=?\n"
                + "                delete from Users where UserID = ?";

        Connection conn = null;
        PreparedStatement prepare = null;

        try {
//            ps = conn.prepareStatement(query);

            conn = getConnection();
            prepare = conn.prepareStatement(query);
            prepare.setString(1, id);
            prepare.setString(2, id);
            prepare.setString(3, id);
            prepare.setString(4, id);
            prepare.setString(5, id);
            prepare.setString(6, id);
            prepare.setString(7, id);
            prepare.executeUpdate();
        } catch (Exception e) {
            throw e;
        } finally {
            closePrepareStatement(prepare);
            closeConnection(conn);
        }

    }

    /**
     * update Account
     *
     * @param int id, String user, String email, String isSell, String isAdmin
     * @return
     */
    public void updateUser(int id, String user, String email, String isSell, String isAdmin) throws Exception {
        String preSql = "update Users set Username=? \n"
                + "               ,email=?  \n"
                + "                ,isSeller=? ,isAdmin=? \n"
                + "                 where UserID=" + id;

        Connection conn = null;
        PreparedStatement prepare = null;
        try {
//            PreparedStatement ps = conn.prepareStatement(preSql);

            conn = getConnection();
            prepare = conn.prepareStatement(preSql);

            prepare.setString(1, user);
            prepare.setString(2, email);
            prepare.setString(3, isSell);
            prepare.setString(4, isAdmin);
            prepare.execute();
        } catch (Exception e) {
            throw e;
        } finally {
            closePrepareStatement(prepare);
            closeConnection(conn);
        }

    }

    /**
     * get All Accounts
     *
     * @param
     * @return List<Users>
     */
    public List<Users> getAllAccounts() throws Exception {
        List<Users> list = new ArrayList<>();
        String query = "SELECT * FROM Users";

        Connection conn = null;
        PreparedStatement prepare = null;
        ResultSet rs = null;

        try {
            conn = getConnection();
            prepare = conn.prepareStatement(query);
            rs = prepare.executeQuery();

            while (rs.next()) {
                list.add(new Users(rs.getInt(1), rs.getString(2),
                        rs.getString(3), rs.getString(4),
                        rs.getString(5), rs.getInt(6),
                        rs.getInt(7), rs.getInt(8)));
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

    /**
     * count Accounts
     *
     * @param
     * @return int
     */
    public int countAccount() throws Exception {
        String query = "SELECT COUNT(*) FROM  Users";
        Connection conn = null;
        PreparedStatement prepare = null;
        ResultSet rs = null;
        conn = getConnection();
        try (
                PreparedStatement ps = (conn != null) ? conn.prepareStatement(query) : null;) {
            rs = (ps != null) ? ps.executeQuery() : null;
            if (rs != null && rs.next()) {
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

    /**
     * get Users List
     *
     * @param int start, int end
     * @return ArrayList
     */
    public ArrayList<Users> getUsersList(int start, int end) throws Exception {
        // Create ArrayList to store all User
        ArrayList<Users> user = new ArrayList<>();

        // Query Statement to get all Categories in Database 
        String sqlQuery = "with x as (	select row_number() over(order by UserID  asc) as row, * from Users \n"
                + "                ) \n"
                + "                select * from x where  row between " + start + " and " + end;

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;
        try {
            connection = getConnection();
            preparedStatement = connection.prepareStatement(sqlQuery);
            rs = preparedStatement.executeQuery();
            while (rs.next()) {
                // Get and store all attribute of each User
                int UserID = rs.getInt("UserID");
                String Username = rs.getString("Username");
                String Password = rs.getString("Password");
                String email = rs.getString("email");
                String activecode = rs.getString("ActiveCode");
                int sellerID = rs.getInt("isSeller");
                int isAdmin = rs.getInt("isAdmin");
                int StatusID = rs.getInt("StatusID");
                // Add User to Arraylist 
                user.add(new Users(UserID, Username, Password, email, activecode, sellerID, isAdmin, StatusID));
            }
        } catch (Exception ex) {
            throw ex;
        } finally {
            closeRS(rs);
            closePrepareStatement(preparedStatement);
            closeConnection(connection);
        }
        return user;
    }

    /**
     * get total page Users List
     *
     * @param
     * @return int
     */
    public int getTotalPage() throws Exception {

        //  Variable to store the condition values passed to filter products in Database
        int totalPage = 0;

        // Query statement to get total Product in Database
        String sqlQuery = "select count(UserID) from Users";

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
        return (int) Math.ceil((double) totalPage / 6);
    }

    /**
     * get total page search Users List
     *
     * @param
     * @return int
     */
    public int getTotalPageSearch(String name) throws Exception {

        //  Variable to store the condition values passed to filter products in Database
        int totalPage = 0;

        // Query statement to get total Product in Database
        String sqlQuery = "select count(UserID) from Users WHERE Username LIKE ?";

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;
        // Execute query to get total Product
        try {
            connection = getConnection();
            preparedStatement = connection.prepareStatement(sqlQuery);
            preparedStatement.setString(1, "%" + name + "%");

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
        return (int) Math.ceil((double) totalPage / 6);
    }

    public static void main(String[] args) {
        UsersDAOImpl dao = new UsersDAOImpl();
        try {
            dao.deleteAccount("24");

        } catch (Exception ex) {
            Logger.getLogger(UsersDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
