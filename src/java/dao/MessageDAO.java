/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entity.Message;
import java.util.ArrayList;

/**
 *
 * @author Admin
 */
public interface MessageDAO {
    public ArrayList<Message> getMessageList(String userID)throws Exception;
    
    public int addMessage(Message message) throws Exception;
    
    public int getRoomID(int userID) throws Exception;
    
    public ArrayList<Message> getUserList()throws Exception;

    public ArrayList<Message> getUserList2() throws Exception;
}
