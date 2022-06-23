/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entity.Category;
import java.util.ArrayList;

/**
 *
 * @author Admin
 */
public interface CategoryDAO {
    public ArrayList<Category> getAllCategory() throws Exception;
    
    
}
