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
import static java.lang.System.out;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import dao.impl.FeedbackDAOImpl;
import dao.impl.ProductDAOImpl;
import dao.impl.UsersDAOImpl;

/**
 *
 * @author Admin
 */
public class ViewAllFeedbackController extends HttpServlet {

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
            // get current user
            HttpSession session = request.getSession();
            Users a = (Users) session.getAttribute("user");
            System.out.println(a.getUserID());

            // get all dao
            ProductDAOImpl productDao = new ProductDAOImpl();
            FeedbackDAOImpl feedbackDao = new FeedbackDAOImpl();
            UsersDAOImpl userDao = new UsersDAOImpl();

            // get all feedback of product of this seller
            List<Product> lsProduct = productDao.getProductBySellID(a.getUserID());
            List<Integer> lsId = lsProduct.stream().map(Product::getProductID).collect(Collectors.toList());
            List<Feedback> lsFeedback = new ArrayList<>();

            for (Product product : lsProduct) {
                System.out.println(product);

            }
            for (int id : lsId) {
                lsFeedback.addAll(feedbackDao.getFeedbacksByProductId(id));
                System.out.println(id);

            }

            for (Feedback feedback : lsFeedback) {
                // get all account that made feedback
                Users accountMadeFeedback = userDao.getAccountByID(
                        String.valueOf(
                                feedback.getUserID()
                        ));
                feedback.setUser(accountMadeFeedback);

                // get all product of feedback
                Product productWithFeedback
                        = productDao.getProductByID(
                                String.valueOf(feedback.getProductID())
                        );
                feedback.setProduct(productWithFeedback);

            }

            request.setAttribute("lsFeedback", lsFeedback);
            request.getRequestDispatcher("ViewAllFeedbacks.jsp").forward(request, response);
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
        processRequest(request, response);
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
