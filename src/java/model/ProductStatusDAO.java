package model;

import entity.ProductStatus;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

public class ProductStatusDAO extends DBContext{
    /**
     * Get All Products Status From Database
     *
     * @param
     * @return Vector Product List
     */
    public Vector<ProductStatus> getProductStatusList() {

        // Create vector to store all Categories
        Vector<ProductStatus> productStatus = new Vector<>();

        // Query Statement to get all Categories in Database 
        String sqlQuery = "select * from ProductStatus";

        // Resultset to store all Categories 
        ResultSet rs = getData(sqlQuery);

        // Get all categories store to vector
        try {
            while (rs.next()) {
                // Get and store all attribute of each Product
                int productID = rs.getInt(1);
                String statusName = rs.getString(2);
                // Add Product to vector 
                productStatus.add(new ProductStatus(productID, statusName));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return productStatus;
    }
}
