/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entity.Users;
import java.util.List;

/**
 *
 * @author Admin
 */
public interface UserDAO {
    List<Users> getAll() throws Exception;
    
    Users getAccountByID(String id) throws Exception;
    
    void insert(String email, String username, String password) throws Exception;
    
    void deleteAccount(String id) throws Exception;
    
    void updateUser(String id, String user, String password, String email, String isSell, String isAdmin, String activeCode, int status) throws Exception;

    List<Users> getAllAccounts() throws Exception;
}
