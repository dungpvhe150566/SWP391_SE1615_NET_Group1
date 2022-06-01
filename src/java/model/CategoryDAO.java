package model;

import entity.Category;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

public class CategoryDAO extends DBContext{
    public Vector<Category> getAllCategory(){
        
        // Create vector to store all Categories
        Vector<Category> categories = new Vector<>();
        
        // Create value atribute of each Category
        int categoryID;
        String categoryName;
        String icon;
        
        // Query Statement to get all Categories in Database 
        String sqlQuery = "select * from Category";
        
        // Resultset to store all Categories 
        ResultSet rs = getData(sqlQuery);
        
        // Get all categories store to vector
        try {
            while (rs.next()) {
                categoryID = rs.getInt(1);
                categoryName = rs.getString(2);
                icon = rs.getString(3);
                categories.add(new Category(categoryID, categoryName, icon));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return categories;
    }
}