/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entity.Feedback;
import entity.Product;
import entity.Users;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.FeedbackDAO;
import model.ProductDAO;

/**
 *
 * @author Admin
 */
public class SubmitFeedbackControl extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try {
            ProductDAO productDao = new ProductDAO();

            String productId = request.getParameter("productId");

            Product p = productDao.getProductByID(productId);

            request.setAttribute("product", p);
            request.getRequestDispatcher("FeedbackForm.jsp").forward(request, response);

        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("thankyou.jsp");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try {

            // get current user account
            HttpSession session = request.getSession();
            Users currentAccount = (Users) session.getAttribute("acc");

            // get FeedbackDAO
            FeedbackDAO feedbackDAO = new FeedbackDAO();

            // get current product id
            int productId = Integer.parseInt(request.getParameter("productId"));

            // get input rating
            String star = request.getParameter("star-value");
            String feedback = request.getParameter("feedback-text");


            // create feedback
            Feedback userFeedback = new Feedback();
            userFeedback.setProductID(productId);
            userFeedback.setUserID(currentAccount.getUserID());
            userFeedback.setStar(Integer.parseInt(star));
            userFeedback.setFeedbackDetails(feedback);
            System.out.println(userFeedback);

            // add feedback to database
            boolean addFeedback = feedbackDAO.addFeedback(userFeedback);
            
            // redirect to Home
            if(addFeedback) {
                request.getRequestDispatcher("detail.jsp").forward(request, response);
            }           
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("thankyou.jsp");
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
