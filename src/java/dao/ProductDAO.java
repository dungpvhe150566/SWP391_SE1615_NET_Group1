/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entity.Product;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

/**
 *
 * @author Admin
 */
public interface ProductDAO {
    public ArrayList<Product> getProductList(int cID, String productName,
            String[] prices, String[] mID, int start, int end, String sortby) throws Exception;
//    
    public int getTotalPage(int cID, String productName,
            String[] prices, String[] mID) throws Exception;
    
    public Vector<Product> getProductList() throws Exception;
    
     public Vector<Product> getProductListByCategoryID(String cID) throws Exception;
     
     public Product getProductById(int productId) throws Exception;
     
     public int addProduct(Product pro) throws Exception;
     
     public int updateProduct(Product pro) throws Exception;
     
     public int deleteProduct(int ProductID) throws Exception;
     
     public List<Product> getProductBySellID(int id) throws Exception ;
     
     public Product getProductByID(String id) throws Exception ;
     
     public Vector<Product> searchProductByNameAndCategory(String name, String categoryID) throws Exception;
}
