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
import entity.Product;
import entity.Users;
import java.io.IOException;
import static java.lang.System.out;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import dao.impl.FeedbackDAOImpl;
import dao.impl.ProductDAOImpl;

/**
 * This class will handle the user request to send feedback to a product
 * feedback for a product
 * <p>
 * Error: Error occurs will be received and processed and handled errors Page
 * <p>
 *
 * @Author haipm
 */
public class SubmitFeedbackControl extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("utf-8");

        try {
            ProductDAOImpl productDao = new ProductDAOImpl();//Call ProductDAOImpl
            //get productId from detail
            String productId = request.getParameter("productId");
            //get product from Id
            Product p = productDao.getProductByID(productId);
            //Set product to FeedbackForm.jsp
            request.setAttribute("product", p);
            request.getRequestDispatcher("FeedbackForm.jsp").forward(request, response);

        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("error.jsp");
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
        request.setCharacterEncoding("utf-8");

        try {

            // get current user account
            HttpSession session = request.getSession();
            Users currentAccount = (Users) session.getAttribute("user");

            // get FeedbackDAOImpl
            FeedbackDAOImpl feedbackDAO = new FeedbackDAOImpl();

            // get current product id
            int productId = Integer.parseInt(request.getParameter("productId"));

            // get current product id
            int cateid = Integer.parseInt(request.getParameter("cateID"));

            // get input rating
            String star = request.getParameter("star-value");
            if (star == null) {
                star = "0";
            }
            String feedback = request.getParameter("feedback-text");
            if (feedback.trim().compareTo("") == 0) {
                request.setAttribute("mess", "Please enter feedback detail");
                request.setAttribute("fb", feedback);
                ProductDAOImpl productDao = new ProductDAOImpl();//Call ProductDAOImpl
                //get product from Id
                Product p = productDao.getProductByID(productId + "");
                //Set product to FeedbackForm.jsp
                request.setAttribute("product", p);
                request.getRequestDispatcher("FeedbackForm.jsp").forward(request, response);

            } else {
                //get current date
                java.util.Date utilDate = new java.util.Date();
                java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());

                // create feedback
                Feedback userFeedback = new Feedback();
                userFeedback.setProductID(productId);
                userFeedback.setUserID(currentAccount.getUserID());
                out.println(currentAccount.getUserID());
                userFeedback.setStar(Integer.parseInt(star));
                userFeedback.setFeedbackDetails(feedback.trim());
                userFeedback.setDateFeedback(sqlDate);
                System.out.println(userFeedback);

                // add feedback to database
                boolean addFeedback = feedbackDAO.addFeedback(userFeedback);

                // redirect to Home
                if (addFeedback) {
                    request.getRequestDispatcher("ShopDetailController?do=ViewDetail&categoryID=" + cateid + "&productID=" + productId).forward(request, response);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("error.jsp");
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
