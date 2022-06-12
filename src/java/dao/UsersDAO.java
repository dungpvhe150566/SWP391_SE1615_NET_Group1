package dao;

import entity.Users;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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

    public static void main(String[] args) {
        UsersDAO dao = new UsersDAO();
        List<Users> list = dao.getAll();
        for (Users student : list) {
            System.out.println(student.getUsername());
        }
        // dao.insert("anhem", "olamigo", "anhdungzoo9");
    }
}
