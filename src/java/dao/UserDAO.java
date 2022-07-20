/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entity.Users;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Admin
 */
public interface UserDAO {

    List<Users> getAll() throws Exception;

    int getTotalPage() throws Exception;

    ArrayList<Users> getUsersList(int start, int end) throws Exception;

    Users getAccountByID(int id) throws Exception;

    Users getAccountByID(String id) throws Exception;

    void insert(String email, String username, String password) throws Exception;

    void deleteAccount(String id) throws Exception;

    void updateUser(int id, String user, String email, String isSell, String isAdmin) throws Exception;

    List<Users> getAllAccounts() throws Exception;
}
