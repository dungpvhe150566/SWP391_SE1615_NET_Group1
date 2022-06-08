package model;

import entity.Product;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ProductDAO extends DBContext {

    /**
     * Get All Products From Database follow CategoryID Product Name Prices of
     * Product Manufacturer ID
     *
     * @param
     * @return Vector have max 6 Product (following Pagination)
     */
    public Vector<Product> getProductList(int cID, String productName,
            String[] prices, String[] mID, int start, int end) {
        // Create vector to store all Categories
        Vector<Product> products = new Vector<>();

//        Variable to store the condition values passed to filter products in Database
        String price = "";
        String categoryID = "";
        String manufacturersID = "";

//        If the product price condition is passed
//        we will cut the chain to get the product price
        if (prices != null) {
            price += " and( ";
            for (int i = 0; i < prices.length; i++) {
                String[] twoPrice = prices[i].split("-");
                price += "OriginalPrice between " + (Integer.parseInt(twoPrice[0].trim()))
                        + " and " + (Integer.parseInt(twoPrice[1].trim()));
                if (prices.length >= 2 && i != prices.length - 1) {
                    price += " or ";
                }
            }
            price += ") ";
        }

//        Set value of CategoryID if passed in
        if (cID != 0) {
            categoryID += " and(CategoryID = " + cID + " ) ";
        }

//        Set value of ManufacturerID if passed in
        if (mID != null) {
            String msID = Arrays.toString(mID);
            manufacturersID += " and (ManufacturerID in (" + msID.substring(1, msID.length() - 1) + ") ) ";
        }

        // Query Statement to get all Categories in Database 
        String sqlQuery = "with x as (	select row_number() over(order by ProductID asc) as row, * from Product "
                + "where (ProductName like '%" + productName + "%') "
                + manufacturersID
                + categoryID
                + price + " ) "
                + "select * from x where  row between  " + start + " and " + end;

        // Resultset to store all Categories 
        ResultSet rs = getData(sqlQuery);

        // Get all categories store to vector
        try {
            while (rs.next()) {
                // Get and store all attribute of each Product
                int productID = rs.getInt(2);
                String proName = rs.getString(3);
                String description = rs.getString(4);
                int originalPrice = rs.getInt(5);
                int sellPrice = rs.getInt(6);
                int salePercent = rs.getInt(7);
                String imageLink = rs.getString(8);
                int caID = rs.getInt(9);
                int sellerID = rs.getInt(10);
                int amount = rs.getInt(11);
                int statusID = rs.getInt(12);
                int manufacturerID = rs.getInt(13);
                float height = rs.getFloat(14);
                float width = rs.getFloat(15);
                float weight = rs.getFloat(16);
                // Add Product to vector 
                products.add(new Product(productID, proName, description,
                        originalPrice, sellPrice, salePercent, imageLink,
                        caID, sellerID, amount, statusID,
                        manufacturerID, height, width, weight));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return products;
    }

    /**
     * Get Total Page Products in Database follow CategoryID Product Name Prices
     * of Product Manufacturer ID
     *
     * @param
     * @return number of Page Products
     */
    public int getTotalPage(int cID, String productName,
            String[] prices, String[] mID) {

        //  Variable to store the condition values passed to filter products in Database
        int totalPage = 0;
        String temp = "";
        String categoryID = "";
        String manufacturersID = "";

//        If the product price condition is passed
//        we will cut the chain to get the product price
        if (prices != null) {
            temp += " and (";
            for (int i = 0; i < prices.length; i++) {
                String[] twoPrice = prices[i].split("-");
                temp += " OriginalPrice between " + (Integer.parseInt(twoPrice[0].trim())) + " and " + (Integer.parseInt(twoPrice[1].trim()));
                if (prices.length >= 2 && i != prices.length - 1) {
                    temp += " or ";
                }
            }
            temp += ")";
        }

//        Set value of CategoryID if passed in
        if (cID != 0) {
            categoryID += " and (CategoryID =" + cID + ") ";
        }

//        Set value of ManufacturerID if passed in
        if (mID != null) {
            String msID = Arrays.toString(mID);
            manufacturersID += " and (ManufacturerID in (" + msID.substring(1, msID.length() - 1) + ")) ";
        }

        // Query statement to get total Product in Database
        String sqlQuery = "select count(ProductID) from Product "
                + " where (ProductName like '%" + productName + "%') "
                + categoryID + manufacturersID + temp;

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

    /**
     * Get All Products From Database
     *
     * @param
     * @return Vector Product List
     */
    public Vector<Product> getProductList() {

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
        String sqlQuery = "select * from Product";

        // Resultset to store all Categories 
        ResultSet rs = getData(sqlQuery);

        // Get all categories store to vector
        try {
            while (rs.next()) {
                // Get and store all attribute of each Product
                productID = rs.getInt(1);
                productName = rs.getString(2);
                description = rs.getString(3);
                originalPrice = rs.getInt(4);
                sellPrice = rs.getInt(5);
                salePercent = rs.getInt(6);
                imageLink = rs.getString(7);
                categoryID = rs.getInt(8);
                sellerID = rs.getInt(9);
                amount = rs.getInt(10);
                statusID = rs.getInt(11);
                manufacturerID = rs.getInt(12);
                height = rs.getFloat(13);
                width = rs.getFloat(14);
                weight = rs.getFloat(15);
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

    /**
     * Get All Products of Category From Database
     *
     * @param Category ID
     * @return Vector Product List
     */
    public Vector<Product> getProductListByCategoryID(String cID) {

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
        String sqlQuery = "select * from Product where CategoryID = " + cID;

        // Resultset to store all Categories 
        ResultSet rs = getData(sqlQuery);

        // Get all categories store to vector
        try {
            while (rs.next()) {
                // Get and store all attribute of each Product
                productID = rs.getInt(1);
                productName = rs.getString(2);
                description = rs.getString(3);
                originalPrice = rs.getInt(4);
                sellPrice = rs.getInt(5);
                salePercent = rs.getInt(6);
                imageLink = rs.getString(7);
                categoryID = rs.getInt(8);
                sellerID = rs.getInt(9);
                amount = rs.getInt(10);
                statusID = rs.getInt(11);
                manufacturerID = rs.getInt(12);
                height = rs.getFloat(13);
                width = rs.getFloat(14);
                weight = rs.getFloat(15);
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

                int SellerID = rs.getInt(9);
                int Amount = rs.getInt(10);
                int StatusID = rs.getInt(11);
                int ManufacturerID = rs.getInt(12);
                float height = rs.getFloat(13);
                float width = rs.getFloat(14);
                float weight = rs.getFloat(15);
                Product product = new Product(productId, ProductName, Description, OriginalPrice, SellPrice, SalePercent, imageLink, CategoryID, SellerID, Amount, StatusID, ManufacturerID, height, width, weight);
                return product;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public int addProduct(Product pro) {
        int n = 0;
        String preSql = "INSERT INTO Product ([ProductName]\n"
                + "           ,[Description]\n"
                + "           ,[OriginalPrice]\n"
                + "           ,[SellPrice]\n"
                + "           ,[SalePercent]\n"
                + "           ,[imageLink]\n"
                + "           ,[CategoryID]\n"
                + "           ,[SellerID]\n"
                + "           ,[Amount]\n"
                + "           ,[StatusID]\n"
                + "           ,[ManufacturerID]\n"
                + "           ,[height]\n"
                + "           ,[width]\n"
                + "           ,[weight])\n"
                + "       VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

        try {
            PreparedStatement pre = conn.prepareStatement(preSql);

            pre.setString(1, pro.getProductName());
            pre.setString(2, pro.getDescription());
            pre.setInt(3, pro.getOriginalPrice());
            pre.setInt(4, pro.getSellPrice());
            pre.setInt(5, pro.getSellPercent());
            pre.setString(6, pro.getImageLink());
            pre.setInt(7, pro.getCategoryID());
            pre.setInt(8, pro.getSellerID());
            pre.setInt(9, pro.getAmount());
            pre.setInt(10, pro.getStatusID());
            pre.setInt(11, pro.getManufacturerID());
            pre.setFloat(12, pro.getHeight());
            pre.setFloat(13, pro.getWidth());
            pre.setFloat(14, pro.getWeight());

            n = pre.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return n;
    }

    public int updateProduct(Product pro) {
        int n = 0;
        String preSql = "UPDATE [ElectronicShop].[dbo].[Product]\n"
                + "   SET [ProductName] = ?\n"
                + "      ,[Description] = ?\n"
                + "      ,[OriginalPrice] = ?\n"
                + "      ,[SellPrice] = ?\n"
                + "      ,[SalePercent] = ?\n"
                + "      ,[imageLink] = ?\n"
                + "      ,[CategoryID] = ?\n"
                + "      ,[SellerID] = ?\n"
                + "      ,[Amount] = ?\n"
                + "      ,[StatusID] = ?\n"
                + "      ,[ManufacturerID] = ?\n"
                + "      ,[height] = ?\n"
                + "      ,[width] = ?\n"
                + "      ,[weight] = ?\n"
                + " WHERE ProductID = " + pro.getProductID();

        try {
            PreparedStatement pre = conn.prepareStatement(preSql);

            pre.setString(1, pro.getProductName());
            pre.setString(2, pro.getDescription());
            pre.setInt(3, pro.getOriginalPrice());
            pre.setInt(4, pro.getSellPrice());
            pre.setInt(5, pro.getSellPercent());
            pre.setString(6, pro.getImageLink());
            pre.setInt(7, pro.getCategoryID());
            pre.setInt(8, pro.getSellerID());
            pre.setInt(9, pro.getAmount());
            pre.setInt(10, pro.getStatusID());
            pre.setInt(11, pro.getManufacturerID());
            pre.setFloat(12, pro.getHeight());
            pre.setFloat(13, pro.getWidth());
            pre.setFloat(14, pro.getWeight());

            n = pre.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return n;
    }

    /**
     * Delete Product From Database follow Product ID
     *
     * @param
     * @return Vector have max 6 Product (following Pagination)
     */
    public int deleteProduct(int ProductID) {
        int n = 0;
        String sql = "delete from Order_Detail where ProductID = " + ProductID + "\n"
                + "	delete from Cart where ProductID = " + ProductID + "\n"
                + "	delete from Feedback_Replies where FeedbackID in (select FeedbackID from Feedback where ProductID = " + ProductID + ")\n"
                + "	delete from Feedback where ProductID = " + ProductID + "\n"
                + "	delete from Product where ProductID = " + ProductID + "";

        try {
            Statement state = conn.createStatement();

            n = state.executeUpdate(sql);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return n;
    }

    public int deleteProducts(String[] arrProductID) {

        String productIDs = "";
        for (String s : arrProductID) {
            productIDs = productIDs.concat(s + " ");
        }
        productIDs = productIDs.trim().replace(" ", ",");

        int n = 0;
        String sql = "delete from Order_Detail where ProductID in (" + productIDs + ")\n"
                + "	delete from Cart where ProductID in (" + productIDs + ")\n"
                + "	delete from Feedback_Replies where FeedbackID in (select FeedbackID from Feedback where ProductID in (" + productIDs + "))\n"
                + "	delete from Feedback where ProductID in (" + productIDs + ")\n"
                + "	delete from Product where ProductID in (" + productIDs + ")";

        try {
            Statement state = conn.createStatement();

            n = state.executeUpdate(sql);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return n;
    }
    PreparedStatement ps = null; //...
    ResultSet rs = null; //Nhận kết quả trả về

    public List<Product> getProductBySellID(int id) { //Must be int type because when saving to Session, it is still int
        List<Product> list = new ArrayList<>();
        String query = "SELECT * FROM Product WHERE SellerID = ?";
        try {
            ps = conn.prepareStatement(query);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Product(rs.getInt("ProductID"), rs.getString("ProductName"), rs.getString("Description"), rs.getInt("SellPrice"), rs.getString("imageLink")));
            }
        } catch (Exception e) {
        }
        return list;
    }

    public Product getProductByID(String id) { //Must be int type because when saving to Session, it is still int
        String query = "SELECT * FROM Product WHERE ProductID = ?";
        try {
            ps = conn.prepareStatement(query);
            ps.setString(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {
                return (new Product(rs.getInt("ProductID"),
                        rs.getString("ProductName"), rs.getString("Description"),
                        rs.getInt("SellPrice"), rs.getString("imageLink")));
            }
        } catch (Exception e) {
        }
        return null;
    }

//    public static void main(String[] args) {
////        ProductDAO dao = new ProductDAO();
////        Vector<Product> products = dao.searchByCustom(1, "", 1, 100);
////        for (Product product : products) {
////            System.out.println(product);
////        }
//    }

}
