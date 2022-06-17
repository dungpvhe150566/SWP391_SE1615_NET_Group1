/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entity.Feedback;
import java.util.List;
import java.util.Vector;

/**
 *
 * @author Admin
 */
public interface FeedbackDAO {
    public Vector<Feedback> getFeedBackByPID(int productID) throws Exception;
    
    public List<Feedback> getFeedbacks() throws Exception;
    
    Feedback getFeedbacksById(int id) throws Exception;
    
    List<Feedback> getFeedbacksByProductId(int productId) throws Exception;
    
    List<Feedback> getFeedbacksByUserId(int userId) throws Exception;
    
    List<Feedback> getFeedbacksByOrderId(int orderId) throws Exception;
    
    boolean addFeedback(Feedback theFeedback) throws Exception;
    
    int countTotalFeedback() throws Exception;
}
