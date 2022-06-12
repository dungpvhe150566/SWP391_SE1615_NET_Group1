package dao;

import entity.Product;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DBContext {

    public Connection conn = null;

    public DBContext(String URL, String userName, String password) {
        //URL: connection string: address, port, database of server
        try {
            //Call Driver
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            //Connection
            conn = DriverManager.getConnection(URL, userName, password);
            
            System.out.println("Connected");
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    
    public DBContext() {
        this("jdbc:sqlserver://localhost:1433;databaseName=ElectronicShop", "sa", "123456");
    }
    
    public ResultSet getData(String sql) {
        ResultSet rs = null;
        
        try {
            Statement state = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, 
                    ResultSet.CONCUR_UPDATABLE);
            
            rs = state.executeQuery(sql);                     
        } catch (SQLException ex) {
            
        }     
        
        return rs;
    }
    
    public static void main(String[] args) {
        new DBContext();
    }
}
