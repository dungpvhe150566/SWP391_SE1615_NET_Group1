package dao.impl;

import dao.DBContext;
import dao.ProductStatusDAO;
import entity.ProductStatus;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

public class ProductStatusDAOImpl extends DBContext implements ProductStatusDAO{
    /**
     * Get All Products Status From Database
     *
     * @param
     * @return Vector Product List
     */
    public Vector<ProductStatus> getProductStatusList() throws Exception{

        // Create vector to store all Categories
        Vector<ProductStatus> productStatus = new Vector<>();

        // Query Statement to get all Categories in Database 
        String sqlQuery = "select * from ProductStatus";

        // Resultset to store all Categories 
//        ResultSet rs = getData(sqlQuery);
        Connection conn = null;
            PreparedStatement prepare = null;
            ResultSet rs = null;
        // Get all categories store to vector
        try {
            conn = getConnection();
                prepare = conn.prepareStatement(sqlQuery);
                rs = prepare.executeQuery();
            while (rs.next()) {
                // Get and store all attribute of each Product
                int productID = rs.getInt(1);
                String statusName = rs.getString(2);
                // Add Product to vector 
                productStatus.add(new ProductStatus(productID, statusName));
            }
        } catch (SQLException ex) {
            throw ex;
        }
        finally {
            closeRS(rs);
            closePrepareStatement(prepare);
            closeConnection(conn);
        }
        return productStatus;
    }
}
