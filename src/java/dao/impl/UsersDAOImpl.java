package dao.impl;

import dao.DBContext;
import dao.UserDAO;
import entity.Users;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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

    public List<Users> searchAccountInManager(String name) {
        List<Users> list = new ArrayList<>();
        String query = "SELECT * FROM Users \n"
                + "WHERE Username LIKE ?";
        Connection conn = null;
        PreparedStatement prepare = null;
        ResultSet rs = null;
        try {
            conn = getConnection();
            prepare = conn.prepareStatement(query);
            prepare.setString(1, "%" + name + "%");
            rs = prepare.executeQuery();
            while (rs.next()) {
                list.add(new Users(rs.getInt(1), rs.getString(2),
                        rs.getString(3), rs.getString(4),
                        rs.getString(5), rs.getInt(6),
                        rs.getInt(7), rs.getInt(8)));
            }
        } catch (Exception e) {
        }
        return list;
    }

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

    public void insert(String email, String username, String password) throws Exception { // insert user information into database
        String query = "insert into Users(Username,Password,email) values (?,?,?) ";
        Connection conn = null;
        PreparedStatement prepare = null;
        try {
            int result = 0;
//            PreparedStatement st = conn.prepareStatement(query);

            conn = getConnection();
            prepare = conn.prepareStatement(query);

            prepare.setString(1, username);
            prepare.setString(2, password);
            prepare.setString(3, email);
//            st.executeUpdate();
            prepare.executeUpdate();
        } catch (Exception e) {
            throw e;
        } finally {
            closePrepareStatement(prepare);
            closeConnection(conn);
        }
    }

    public void deleteAccount(String id) throws Exception {
        String query = "	delete from Orders where UserID = ?\n"
                + "delete from Product where SellerID = ?\n"
                + "delete from Cart where UserID = ?\n"
                + "delete from Feedback where UserID = ?\n"
                + "delete from Blog where SellerID=?\n"
                + "delete from UserAddress where UserID=?\n"
                + "delete from Users where UserID = ?";

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
        return;
    }

    public void updateUser(String id, String user, String password, String email, String isSell, String isAdmin, String activeCode, int status) throws Exception {
        String preSql = "update Users set Username=? ,Password=? "
                + ",email=? ,ActiveCode=? "
                + ",isSeller=? ,isAdmin=? ,"
                + "StatusID=?  where UserID=" + id;

        Connection conn = null;
        PreparedStatement prepare = null;
        try {
//            PreparedStatement ps = conn.prepareStatement(preSql);

            conn = getConnection();
            prepare = conn.prepareStatement(preSql);

            prepare.setString(1, user);
            prepare.setString(2, password);
            prepare.setString(3, email);
            prepare.setString(4, activeCode);
            prepare.setString(5, isSell);
            prepare.setString(6, isAdmin);
            prepare.setInt(7, status);
            prepare.execute();
        } catch (Exception e) {
            throw e;
        } finally {
            closePrepareStatement(prepare);
            closeConnection(conn);
        }

    }

    public List<Users> getAllAccounts() throws Exception {
        List<Users> list = new ArrayList<>();
        String query = "SELECT * FROM Users";

        Connection conn = null;
        PreparedStatement prepare = null;
        ResultSet rs = null;

        try {
//            ps = conn.prepareStatement(query);
//            rs = ps.executeQuery();

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
        }catch (Exception ex) {
            throw ex;
        }
        finally {
            closeRS(rs);
            closePrepareStatement(prepare);
            closeConnection(conn);
        }
        return 0;
    }

    public static void main(String[] args) {
        UsersDAOImpl dao = new UsersDAOImpl();
        try {
            dao.deleteAccount("7");
//        List<Users> list = dao.getAll();
//        for (Users student : list) {
//            System.out.println(student.getUsername());
//        }
// dao.insert("anhem", "olamigo", "anhdungzoo9");
        } catch (Exception ex) {
            Logger.getLogger(UsersDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
