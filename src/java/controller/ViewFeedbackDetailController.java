/**
 * Copyright(C) 2022,Group1-NETSE1615.<p>
 * Shopping Web:
 * <p>
 * Electronic Shop<p>
 *
 * Record of change:
 * <p>
 * DATE Version AUTHOR DESCRIPTION<p>
 * 2022-08-16 01 HaiPM Update Code Convention<p>
 */
package controller;

import entity.Feedback;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import dao.impl.FeedbackDAOImpl;
import dao.impl.ProductDAOImpl;
import dao.impl.UsersDAOImpl;

/**
 * This class makes a handling requirement for a seller to be view detail
 * feedback of the products that the seller manages View Detail Feedback of the
 * products that Seller manages
 * <p>
 * Error: Error occurs will be received and processed and handled errors Page
 * <p>
 *
 * @Author haipm
 */
public class ViewFeedbackDetailController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try {

            // get all dao
            ProductDAOImpl productDao = new ProductDAOImpl();
            FeedbackDAOImpl feedbackDao = new FeedbackDAOImpl();
            UsersDAOImpl userDao = new UsersDAOImpl();

            // get feedback id
            int feedbackId = Integer.parseInt(request.getParameter("id"));

            // get the feedback and set data for the feedback
            Feedback feedback = feedbackDao.getFeedbacksById(feedbackId);
            feedback.setProduct(
                    productDao.getProductByID(
                            String.valueOf(feedback.getProductID())
                    )
            );
            feedback.setUser(
                    userDao.getAccountByID(
                            String.valueOf(feedback.getUserID())
                    )
            );

            // send to jsp page
            request.setAttribute("feedback", feedback);
            request.getRequestDispatcher("ViewFeedbackDetail.jsp").forward(request, response);
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
