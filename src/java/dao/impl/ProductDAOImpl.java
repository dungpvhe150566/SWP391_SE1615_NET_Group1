package dao.impl;

import dao.DBContext;
import dao.ProductDAO;
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

public class ProductDAOImpl extends DBContext implements ProductDAO {

    /**
     * Get All Products From Database follow CategoryID Product Name Prices of
     * Product Manufacturer ID
     *
     * @param
     * @return Vector have max 6 Product (following Pagination)
     */
    public ArrayList<Product> getProductList(int cID, String productName,
            String[] prices, String[] mID, int start, int end, String sortby) throws Exception {
        // Create vector to store all Categories
        ArrayList<Product> products = new ArrayList<>();

//        Variable to store the condition values passed to filter products in Database
        String price = "";
        String categoryID = "";
        String manufacturersID = "";
        String sort = "(select null)";

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
            categoryID += " and CategoryID = " + cID + " ";
        }

//        Set value of ManufacturerID if passed in
        if (mID != null) {
            String msID = Arrays.toString(mID);
            manufacturersID += " and ManufacturerID in (" + msID.substring(1, msID.length() - 1) + ") ";
        }

        if (sortby != null && !sortby.isEmpty()) {
            if (sortby.equals("Ascending")) {
                sort = " OriginalPrice asc ";
            } else {
                sort = " OriginalPrice desc ";
            }

        }

        // Query Statement to get all Categories in Database 
        String sqlQuery2 = "SELECT  * FROM Product where (ProductName like ?) "
                + categoryID
                + manufacturersID
                + price
                + " ORDER BY " + sort + " \n"
                + " OFFSET ? ROWS FETCH NEXT 6 ROWS ONLY ";

// Resultset to store all Categories 
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;
        // Get all categories store to vector
        try {
            connection = getConnection();
            preparedStatement = connection.prepareStatement(sqlQuery2);
            preparedStatement.setString(1, "%" + productName + "%");
            preparedStatement.setInt(2, start);
//            preparedStatement.setString(2, sort);
            rs = preparedStatement.executeQuery();
            while (rs.next()) {
                // Get and store all attribute of each Product
                int productID = rs.getInt(1);
                String proName = rs.getString(2);
                String description = rs.getString(3);
                int originalPrice = rs.getInt(4);
                int sellPrice = rs.getInt(5);
                int salePercent = rs.getInt(6);
                String imageLink = rs.getString(7);
                int caID = rs.getInt(8);
                int sellerID = rs.getInt(9);
                int amount = rs.getInt(10);
                int statusID = rs.getInt(11);
                int manufacturerID = rs.getInt(12);
                float height = rs.getFloat(13);
                float width = rs.getFloat(14);
                float weight = rs.getFloat(15);
                // Add Product to vector 
                products.add(new Product(productID, proName, description,
                        originalPrice, sellPrice, salePercent, imageLink,
                        caID, sellerID, amount, statusID,
                        manufacturerID, height, width, weight));
            }
        } catch (Exception ex) {
            throw ex;
        } finally {
            closeRS(rs);
            closePrepareStatement(preparedStatement);
            closeConnection(connection);
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
            String[] prices, String[] mID) throws Exception {

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

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;
        // Execute query to get total Product
        try {
            connection = getConnection();
            preparedStatement = connection.prepareStatement(sqlQuery);
            rs = preparedStatement.executeQuery();
            // set total Product to variable
            if (rs.next()) {
                totalPage = rs.getInt(1);
            }
        } catch (Exception ex) {
            throw ex;
        } finally {
            closeConnection(connection);
            closePrepareStatement(preparedStatement);
            closeRS(rs);
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
    public Vector<Product> getProductList() throws Exception {

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
        } finally {
            closeRS(rs);
            closePrepareStatement(prepare);
            closeConnection(conn);
        }
        return products;
    }

    /**
     * Get All Products of Category From Database
     *
     * @param Category ID
     * @return Vector Product List
     */
    public Vector<Product> getProductListByCategoryID(String cID) throws Exception {

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
        } finally {
            closeRS(rs);
            closePrepareStatement(prepare);
            closeConnection(conn);
        }
        return products;
    }

    public Product getProductById(int productId) throws Exception {
        String sql = "select *  from Product  where  ProductID = ?";
        Connection conn = null;
        PreparedStatement prepare = null;
        ResultSet rs = null;
        try {
//            PreparedStatement ps = conn.prepareStatement(sql);
//            ps.setInt(1, productId);
//            ResultSet rs = ps.executeQuery();

            conn = getConnection();
            prepare = conn.prepareStatement(sql);
            prepare.setInt(1, productId);
            rs = prepare.executeQuery();
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
        } finally {
            closeRS(rs);
            closePrepareStatement(prepare);
            closeConnection(conn);
        }

        return null;
    }

    public int addProduct(Product pro) throws Exception {
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

        Connection conn = null;
        PreparedStatement prepare = null;
        try {
//            PreparedStatement pre = conn.prepareStatement(preSql);

            conn = getConnection();
            prepare = conn.prepareStatement(preSql);

            prepare.setString(1, pro.getProductName());
            prepare.setString(2, pro.getDescription());
            prepare.setInt(3, pro.getOriginalPrice());
            prepare.setInt(4, pro.getSellPrice());
            prepare.setInt(5, pro.getSellPercent());
            prepare.setString(6, pro.getImageLink());
            prepare.setInt(7, pro.getCategoryID());
            prepare.setInt(8, pro.getSellerID());
            prepare.setInt(9, pro.getAmount());
            prepare.setInt(10, pro.getStatusID());
            prepare.setInt(11, pro.getManufacturerID());
            prepare.setFloat(12, pro.getHeight());
            prepare.setFloat(13, pro.getWidth());
            prepare.setFloat(14, pro.getWeight());

            n = prepare.executeUpdate();

//            n = pre.executeUpdate();
        } finally {
            closePrepareStatement(prepare);
            closeConnection(conn);
        }

        return n;
    }

    public int updateProduct(Product pro) throws Exception {
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

        Connection conn = null;
        PreparedStatement pre = null;
        ResultSet rs = null;

        try {
//            PreparedStatement pre = conn.prepareStatement(preSql);
            conn = getConnection();
            pre = conn.prepareStatement(preSql);

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
//            n = pre.executeUpdate();
        } catch (SQLException ex) {
            throw ex;
        } finally {
            closePrepareStatement(pre);
            closeConnection(conn);
        }

        return n;
    }

    /**
     * Delete Product From Database follow Product ID
     *
     * @param
     * @return Vector have max 6 Product (following Pagination)
     */
    public int deleteProduct(int ProductID) throws Exception {
        int n = 0;
        String sql = "delete from Order_Detail where ProductID = " + ProductID + "\n"
                + "	delete from Cart where ProductID = " + ProductID + "\n"
                + "	delete from Feedback_Replies where FeedbackID in (select FeedbackID from Feedback where ProductID = " + ProductID + ")\n"
                + "	delete from Feedback where ProductID = " + ProductID + "\n";

//        Connection conn = null;
//        PreparedStatement prepare = null;
//        ResultSet rs = null;
        try {
            Statement state = getConnection().createStatement();

            state.executeUpdate(sql);

            n = state.executeUpdate("delete from Product where ProductID = " + ProductID);
        } catch (SQLException ex) {
            throw ex;
        }
        return n;
    }

    public int deleteProducts(String[] arrProductID) throws Exception {

        String productIDs = "";
        for (String s : arrProductID) {
            productIDs = productIDs.concat(s + " ");
        }
        productIDs = productIDs.trim().replace(" ", ",");

        int n = 0;
        String sql = "delete from Order_Detail where ProductID in (" + productIDs + ")\n"
                + "	delete from Cart where ProductID in (" + productIDs + ")\n"
                + "	delete from Feedback_Replies where FeedbackID in (select FeedbackID from Feedback where ProductID in (" + productIDs + "))\n"
                + "	delete from Feedback where ProductID in (" + productIDs + ")\n";

        try {
            Statement state = getConnection().createStatement();

            state.executeUpdate(sql);
            n = state.executeUpdate("delete from Product where ProductID in (" + productIDs + ")");
        } catch (SQLException ex) {
            throw ex;
        }
        return n;
    }

    PreparedStatement ps = null; //...
    ResultSet rs = null; //Nhận kết quả trả về

    public List<Product> getProductBySellID(int id) throws Exception { //Must be int type because when saving to Session, it is still int
        List<Product> list = new ArrayList<>();
        String query = "SELECT * FROM Product WHERE SellerID = ?";
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;
        try {
            connection = getConnection();
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);
            rs = preparedStatement.executeQuery();

//            ps = conn.prepareStatement(query);
//            ps.setInt(1, id);
//            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Product(rs.getInt("ProductID"), rs.getString("ProductName"), rs.getString("Description"), rs.getInt("SellPrice"), rs.getString("imageLink")));
            }
        } catch (Exception e) {
            throw e;
        } finally {
            closeRS(rs);
            closePrepareStatement(preparedStatement);
            closeConnection(connection);
        }
        return list;
    }

    public Product getProductByID(String id) throws Exception { //Must be int type because when saving to Session, it is still int
        String query = "SELECT * FROM Product WHERE ProductID = ?";
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;
        try {
            connection = getConnection();
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, id);
            rs = preparedStatement.executeQuery();

//            ps = conn.prepareStatement(query);
//            ps.setString(1, id);
//            rs = ps.executeQuery();
            while (rs.next()) {
                return (new Product(rs.getInt("ProductID"),
                        rs.getString("ProductName"), rs.getString("Description"),
                        rs.getInt("SellPrice"), rs.getString("imageLink")));
            }
        } catch (Exception e) {
            throw e;
        } finally {
            closeRS(rs);
            closePrepareStatement(preparedStatement);
            closeConnection(connection);
        }
        return null;
    }

    public Vector<Product> searchProductByNameAndCategory(String name, String categoryID) throws Exception {
        String query = "SELECT * FROM Product WHERE CategoryID = ? and ProductName like '%" + name + "%'";
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;
        Vector<Product> vectorProduct = new Vector<>();
        try {
            connection = getConnection();
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, categoryID);
            rs = preparedStatement.executeQuery();

//            ps = conn.prepareStatement(query);
//            ps.setString(1, id);
//            rs = ps.executeQuery();
            while (rs.next()) {
                vectorProduct.add(new Product(rs.getInt("ProductID"),
                        rs.getString("ProductName"),
                        rs.getString("Description"),
                        rs.getInt("OriginalPrice"),
                        rs.getInt("SellPrice"),
                        rs.getInt("SalePercent"),
                        rs.getString("imageLink"),
                        rs.getInt("CategoryID"),
                        rs.getInt("SellerID"),
                        rs.getInt("Amount"),
                        rs.getInt("StatusID"),
                        rs.getInt("ManufacturerID"),
                        rs.getFloat("height"),
                        rs.getFloat("width"),
                        rs.getFloat("weight")));
            }
            return vectorProduct;
        } catch (Exception e) {
            throw e;
        } finally {
            closeRS(rs);
            closePrepareStatement(preparedStatement);
            closeConnection(connection);
        }
    }
     public int countProduct() throws Exception {
        String query = "SELECT COUNT(*) FROM dbo.Product";
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;
        try (Connection con = getConnection();
                PreparedStatement ps = (con != null) ? con.prepareStatement(query) : null;) {
           rs = (ps != null) ? ps.executeQuery() : null;
            if (rs != null && rs.next()) {
                return rs.getInt(1);
            }
        }catch (Exception ex) {
            throw ex;
        }
        finally {
            closeRS(rs);
            closePrepareStatement(preparedStatement);
            closeConnection(connection);
        }
        return 0;
    }
     public List<Integer> countProductGroupByCategoryId() throws Exception {
        String query = "SELECT COUNT(*) FROM dbo.Product GROUP BY CategoryID ORDER BY CategoryID asc";
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;
        List<Integer> list = new ArrayList<>();
        
        try (Connection con = getConnection();
                PreparedStatement ps = (con != null) ? con.prepareStatement(query) : null;) {
            if (ps != null) {
                 rs = (ps != null) ? ps.executeQuery() : null;
                while (rs != null && rs.next()) {

                    list.add(rs.getInt(1));
                }
                return list;
            }
        }catch (Exception ex) {
            throw ex;
        }
        finally {
            closeRS(rs);
            closePrepareStatement(preparedStatement);
            closeConnection(connection);
        }
        return null;
    }

    public static void main(String[] args) {
        ProductDAOImpl dao = new ProductDAOImpl();
        int a = 0;
        try {
            //        Vector<Product> products;
//       try {
//           products = dao.getProductList(1, "",null,null,0, 100,"");
//           for (Product product : products) {
//            System.out.println(product);
//        }
//       } catch (Exception ex) {
//           Logger.getLogger(ProductDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
//       }
            a = dao.getTotalPage(1, "lap", null, null);
            System.out.println(a);
        } catch (Exception ex) {
            return;
        }

    }

}
