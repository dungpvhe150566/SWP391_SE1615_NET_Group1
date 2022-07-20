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
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import dao.impl.FeedbackDAOImpl;
import dao.impl.ProductDAOImpl;
import dao.impl.UsersDAOImpl;

/**
 * This class makes a handling requirement for users to seller is the view all
 * feedback of the products that the seller manages Sort the feedbacks according
 * to the requirements
 * <p>
 * Error: Error occurs will be received and processed and handled errors Page
 * <p>
 *
 * @Author haipm
 */
public class ViewAllFeedbackController extends HttpServlet {

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
            List<Feedback> lsFeedback = new ArrayList<>();
//            lsFeedback = feedbackDao.getFeedbacksBySellerId(a.getUserID());
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
            String index = request.getParameter("index");
            int indexpage = 0;
            if (index == null) {
                index = "1";
            }
            indexpage = Integer.parseInt(index);
            lsFeedback = feedbackDao.paging(indexpage, "ID", "asc", a.getUserID());
            int total = feedbackDao.totalPage(a.getUserID());
            request.setAttribute("indexpage", indexpage);
            request.setAttribute("uid", total);
            int sortOption = 0;
            int sortOrder = 0;
            if (request.getParameter("sopt") != null || request.getParameter("sodr") != null) {
                sortOption = Integer.parseInt(request.getParameter("sopt"));
                sortOrder = Integer.parseInt(request.getParameter("sodr"));
            }
            if (request.getParameter("sort-order") != null) {
                sortOption = Integer.parseInt(request.getParameter("sort-order"));
            }
            if (request.getParameter("sort-by-order") != null) {
                sortOrder = Integer.parseInt(request.getParameter("sort-by-order"));
            }

            request.setAttribute("sopt", sortOption);
            request.setAttribute("sodr", sortOrder);
            switch (sortOption) {
                // sort by star
                case 1: {
                    if (sortOrder == 1) {
                        // sort ascending
                        lsFeedback = feedbackDao.paging(indexpage, "star", "asc", a.getUserID());
                    } else {
                        // sort descending
                        lsFeedback = feedbackDao.paging(indexpage, "star", "desc", a.getUserID());
                    }
                    break;
                }
                // sort by user
                case 2: {
                    if (sortOrder == 1) {
                        // sort ascending
                        lsFeedback = feedbackDao.paging(indexpage, "username", "asc", a.getUserID());

                    } else {
                        // sort descending
                        lsFeedback = feedbackDao.paging(indexpage, "username", "desc", a.getUserID());
                    }
                    break;
                }
                // sort by product
                case 3: {
                    if (sortOrder == 1) {
                        // sort ascending
                        lsFeedback = feedbackDao.paging(indexpage, "productname", "asc", a.getUserID());

                    } else {
                        // sort descending
                        lsFeedback = feedbackDao.paging(indexpage, "productname", "desc", a.getUserID());
                    }
                    break;
                }

            }
//            }
            //Set data to JSP
            request.setAttribute("lsFeedback", lsFeedback);

            request.getRequestDispatcher("ViewAllFeedbacks.jsp").forward(request, response);
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
