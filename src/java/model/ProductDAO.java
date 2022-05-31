package model;

import entity.Product;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ProductDAO extends DBContext {

    public Vector<Product> getProductList(int start, int end) {

        // Create vector to store all Categories
        Vector<Product> products = new Vector<>();

        // Create value atribute of each Category
        int productID;
        String productName;
        String description;
        int originalPrice;
        int sellPrice;
        int salePercent;
        String imageLink;
        int categoryID;
        int sellerID;
        int amount;
        int statusID;
        int manufacturerID;
        float height;
        float width;
        float weight;

        // Query Statement to get all Categories in Database 
        String sqlQuery = "with x as\n"
                + "  (select row_number() over(order by ProductID asc) as row,*\n"
                + "  from Product )\n"
                + "  select * from x where row between " + start + " and " + end;

        // Resultset to store all Categories 
        ResultSet rs = getData(sqlQuery);

        // Get all categories store to vector
        try {
            while (rs.next()) {
                // Get and store all attribute of each Product
                productID = rs.getInt(2);
                productName = rs.getString(3);
                description = rs.getString(4);
                originalPrice = rs.getInt(5);
                sellPrice = rs.getInt(6);
                salePercent = rs.getInt(7);
                imageLink = rs.getString(8);
                categoryID = rs.getInt(9);
                sellerID = rs.getInt(10);
                amount = rs.getInt(11);
                statusID = rs.getInt(12);
                manufacturerID = rs.getInt(13);
                height = rs.getFloat(14);
                width = rs.getFloat(15);
                weight = rs.getFloat(16);
                // Add Product to vector 
                products.add(new Product(productID, productName, description,
                        originalPrice, sellPrice, salePercent, imageLink,
                        categoryID, sellerID, amount, statusID,
                        manufacturerID, height, width, weight));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return products;
    }

    // This method to get Total of Number Product have in Database
    public int getTotalPage(String productName) {
        // create variable to store number of page
        int totalPage = 0;
        String sqlQuery;

        // Query statement to get total Product in Database
        if(productName.isEmpty()){
            sqlQuery = "select count(ProductID) from Product";
        }else{
            sqlQuery = "select count(ProductID) from Product where ProductName like'%"+productName+"%'";
        }

        // Execute query to get total Product
        ResultSet rs = getData(sqlQuery);
        try {
            // set total Product to variable
            if (rs.next()) {
                totalPage = rs.getInt(1);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        // convet total product to total page (each page have 6 product)
        return (int) Math.ceil((double) totalPage / 6);
    }

    public Vector<Product> getSearchByName(String pName, int start, int end) {
        // Create vector to store all Categories
        Vector<Product> products = new Vector<>();

        // Create value atribute of each Category
        int productID;
        String productName;
        String description;
        int originalPrice;
        int sellPrice;
        int salePercent;
        String imageLink;
        int categoryID;
        int sellerID;
        int amount;
        int statusID;
        int manufacturerID;
        float height;
        float width;
        float weight;

        // Query Statement to get all Categories in Database 
        String sqlQuery = "with x as (\n"
                + "	select row_number() over(order by ProductID asc) as row, * from Product where ProductName like '%"+pName+"%') \n"
                + "	select * from x where  row between "+start+" and "+end;

        // Resultset to store all Categories 
        ResultSet rs = getData(sqlQuery);

        // Get all categories store to vector
        try {
            while (rs.next()) {
                // Get and store all attribute of each Product
                productID = rs.getInt(2);
                productName = rs.getString(3);
                description = rs.getString(4);
                originalPrice = rs.getInt(5);
                sellPrice = rs.getInt(6);
                salePercent = rs.getInt(7);
                imageLink = rs.getString(8);
                categoryID = rs.getInt(9);
                sellerID = rs.getInt(10);
                amount = rs.getInt(11);
                statusID = rs.getInt(12);
                manufacturerID = rs.getInt(13);
                height = rs.getFloat(14);
                width = rs.getFloat(15);
                weight = rs.getFloat(16);
                // Add Product to vector 
                products.add(new Product(productID, productName, description,
                        originalPrice, sellPrice, salePercent, imageLink,
                        categoryID, sellerID, amount, statusID,
                        manufacturerID, height, width, weight));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return products;
    }
    
    public Vector<Product> searchByMID(int mID, int start, int end) {
         // Create vector to store all Categories
        Vector<Product> products = new Vector<>();

        // Create value atribute of each Category
        int productID;
        String productName;
        String description;
        int originalPrice;
        int sellPrice;
        int salePercent;
        String imageLink;
        int categoryID;
        int sellerID;
        int amount;
        int statusID;
        int manufacturerID;
        float height;
        float width;
        float weight;

        // Query Statement to get all Categories in Database 
        String sqlQuery = "with x as (\n"
                + "	select row_number() over(order by ProductID asc) as row, * from Product where ManufacturerID="+mID+" ) \n"
                + "	select * from x where  row between "+start+" and "+end;

        // Resultset to store all Categories 
        ResultSet rs = getData(sqlQuery);

        // Get all categories store to vector
        try {
            while (rs.next()) {
                // Get and store all attribute of each Product
                productID = rs.getInt(2);
                productName = rs.getString(3);
                description = rs.getString(4);
                originalPrice = rs.getInt(5);
                sellPrice = rs.getInt(6);
                salePercent = rs.getInt(7);
                imageLink = rs.getString(8);
                categoryID = rs.getInt(9);
                sellerID = rs.getInt(10);
                amount = rs.getInt(11);
                statusID = rs.getInt(12);
                manufacturerID = rs.getInt(13);
                height = rs.getFloat(14);
                width = rs.getFloat(15);
                weight = rs.getFloat(16);
                // Add Product to vector 
                products.add(new Product(productID, productName, description,
                        originalPrice, sellPrice, salePercent, imageLink,
                        categoryID, sellerID, amount, statusID,
                        manufacturerID, height, width, weight));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return products;
    }
    
    public int getTotalPageByMID(int manufacturerID) {
        // create variable to store number of page
        int totalPage = 0;
        String sqlQuery;

        // Query statement to get total Product in Database
      
            sqlQuery = "select count(ProductID) from Product where ManufacturerID ="+manufacturerID;
        

        // Execute query to get total Product
        ResultSet rs = getData(sqlQuery);
        try {
            // set total Product to variable
            if (rs.next()) {
                totalPage = rs.getInt(1);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        // convet total product to total page (each page have 6 product)
        return (int) Math.ceil((double) totalPage / 6);
    }
    
  
    public void addProduct() {

    }

    public void updateProduct() {

    }

    public void deleteProduct() {

    }

    public static void main(String[] args) {
//        ProductDAO dao = new ProductDAO();
//        Vector<Product> products = dao.searchByCustom(1, "", 1, 100);
//        for (Product product : products) {
//            System.out.println(product);
//        }
    }

    public Product getProductById(int productId) {
         try {
            String sql = "select *  from Product  where  ProductID = ?";
            
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, productId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                   int pId = rs.getInt(1);
                String ProductName = rs.getString(2);
                String Description = rs.getNString(3);
                int OriginalPrice = rs.getInt(4);
                int SellPrice = rs.getInt(5);
                  int SalePercent = rs.getInt(6);
                  
                String imageLink = rs.getString(7);
                int CategoryID = rs.getInt(8);
                

                int SellerID= rs.getInt(9); 
                int Amount = rs.getInt(10);
                int StatusID = rs.getInt(11);
                int ManufacturerID = rs.getInt(12);
                float height  = rs.getFloat(13);
                float width  = rs.getFloat(14);
                float weight  = rs.getFloat(15);
                Product product = new Product(productId, ProductName, Description, OriginalPrice, SellPrice, SalePercent, imageLink, CategoryID, SellerID, Amount, StatusID, ManufacturerID, height, width, weight);
                return product;
            }
        } catch (Exception ex) {
           ex.printStackTrace();
        }
        return null;
    }

    

    

}
