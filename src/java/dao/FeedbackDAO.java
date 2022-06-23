/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entity.Feedback;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Admin
 */
public interface FeedbackDAO {

    public ArrayList<Feedback> getFeedBackByPID(int productID) throws Exception;

    public List<Feedback> getFeedbacks() throws Exception;

    List<Feedback> getFeedbacksByProductId(int start, int end) throws Exception;

    ArrayList<Feedback> getFeedbacksSort(int sellerID, String sort, String namesort, int start, int end) throws Exception;

    Feedback getFeedbacksById(int id) throws Exception;

    List<Feedback> getFeedbacksBySellerId(int productId) throws Exception;

    List<Feedback> getFeedbacksByUserId(int userId) throws Exception;

    List<Feedback> getFeedbacksByOrderId(int orderId) throws Exception;

    boolean addFeedback(Feedback theFeedback) throws Exception;

    int countTotalFeedback() throws Exception;

    int totalPage(int index) throws Exception;

    int getTotalPageFeedbacks() throws Exception;
}
