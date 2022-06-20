package dao.impl;

import dao.CategoryDAO;
import dao.DBContext;
import entity.Category;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Vector;

public class CategoryDAOImpl extends DBContext implements CategoryDAO{
    
    public ArrayList<Category> getAllCategory() throws Exception{
        
        // Create vector to store all Categories
        ArrayList<Category> categories = new ArrayList<>();
        
        // Create value atribute of each Category
        int categoryID;
        String categoryName;
        String icon;
        
        // Query Statement to get all Categories in Database 
        String sqlQuery = "select * from Category";
        
        // Resultset to store all Categories 
        Connection conn = null;
        PreparedStatement prepare = null;
        ResultSet rs = null;
        // Get all categories store to vector
        try {
            conn = getConnection();
            prepare = conn.prepareStatement(sqlQuery);
            rs = prepare.executeQuery();
            while (rs.next()) {
                categoryID = rs.getInt(1);
                categoryName = rs.getString(2);
                icon = rs.getString(3);
                categories.add(new Category(categoryID, categoryName, icon));
            }
        } catch (SQLException ex) {
            throw ex;
        }
        finally {
            closeRS(rs);
            closePrepareStatement(prepare);
            closeConnection(conn);
        }
        return categories;
    }
}