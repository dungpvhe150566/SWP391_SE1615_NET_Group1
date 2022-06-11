package model;

import entity.Users;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import static javafx.scene.input.KeyCode.U;
import static jdk.nashorn.internal.runtime.regexp.joni.constants.AsmConstants.S;

/**
 *
 * @author HP
 */
public class UsersDAO extends DBContext {

    public List<Users> getAll() { //Get user data in the database
        List<Users> list = new ArrayList<>();
        String sql = "select * from Users";
        try {
            PreparedStatement st = conn.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Users U = new Users();
                U.setUsername(rs.getString(2));
                U.setEmail(rs.getString(4));
                U.setPassword(rs.getString(3));
                U.setUserID(rs.getInt(1));
                list.add(U);
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }
        return list;
    }

    PreparedStatement ps = null; //...
    ResultSet rs = null; //Get the results returned

    public List<Users> getAllAccounts() {
        List<Users> list = new ArrayList<>();
        String query = "SELECT * FROM Users";
        try {
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Users(rs.getInt(1), rs.getString(2),
                        rs.getString(3), rs.getString(4),
                        rs.getString(5), rs.getInt(6),
                        rs.getInt(7), rs.getInt(8)));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public Users getAccountByID(String id) {
        String query = "SELECT * FROM Users WHERE UserID = ?";
        try {
            ps = conn.prepareStatement(query);
            ps.setString(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {
                return new Users(rs.getInt(1), rs.getString(2),
                        rs.getString(3), rs.getString(4),
                        rs.getString(5), rs.getInt(6),
                        rs.getInt(7), rs.getInt(8));
            }
        } catch (Exception e) {
        }
        return null;
    }

    public void insert(String email, String username, String password) { // insert user information into database
        String query = "insert into Users(Username,Password,email) values (?,?,?) ";
        try {
            int result = 0;
            PreparedStatement st = conn.prepareStatement(query);
            st.setString(1, username);
            st.setString(2, password);
            st.setString(3, email);
            st.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void updateUser(String id, String user, String password, String email, String isSell, String isAdmin, String activeCode, int status) {
        String preSql = "update Users set Username=? ,Password=? "
                + ",email=? ,ActiveCode=? "
                + ",isSeller=? ,isAdmin=? ,"
                + "StatusID=?  where UserID=" + id;

        try {
            PreparedStatement ps = conn.prepareStatement(preSql);
            ps.setString(1, user);
            ps.setString(2, password);
            ps.setString(3, email);
            ps.setString(4, activeCode);
            ps.setString(5, isSell);
            ps.setString(6, isAdmin);
            ps.setInt(7, status);
            ps.execute();
        } catch (Exception e) {
            System.out.println(e);

        }

    }

//    public static void main(String[] args) {
//        UsersDAO dao = new UsersDAO();
//        dao.updateUser("6", "nguyentranhoang", "nguyentranhoang", "HoangNTHE150691@fpt.edu.vn", "0", "0", "bbbbb", 1);
//        // dao.insert("anhem", "olamigo", "anhdungzoo9");
//    }
}
