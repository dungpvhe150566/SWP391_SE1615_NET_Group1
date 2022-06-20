/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.CategoryDAO;
import dao.FeedbackDAO;
import entity.Category;
import entity.Feedback;
import entity.Product;
import java.io.IOException;
import java.util.Vector;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import dao.impl.CategoryDAOImpl;
import dao.impl.FeedbackDAOImpl;
import dao.ProductDAO;
import dao.impl.ProductDAOImpl;
import java.util.ArrayList;

/**
 *
 * @author Admin
 */
public class ShopDetailController extends HttpServlet {

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
            String service = request.getParameter("do");

            ProductDAO productDAO = new ProductDAOImpl();
            CategoryDAO categoryDAO = new CategoryDAOImpl();
            FeedbackDAO feedbackDAO = new FeedbackDAOImpl();

            if (service != null && service.equals("ViewDetail")) {

//                Get ProductID from User wanto view detail
                int productID = Integer.parseInt(request.getParameter("productID"));
//                Get Product follow ProductID
                Product product = productDAO.getProductById(productID);
//                Set Product to display for User view detail
                request.setAttribute("product", product);

//                 Get Feedback of this Product follow ProductID
                ArrayList<Feedback> feedbacks = feedbackDAO.getFeedBackByPID(productID);
                request.setAttribute("feedbacks", feedbacks);

//                Get ProductsList same Catrgory with this Product to display for Use
                String categoryID = request.getParameter("categoryID");
                Vector<Product> productsSameCategory = productDAO.getProductListByCategoryID(categoryID);
                request.setAttribute("productsSameCategory", productsSameCategory);
            }

            ArrayList<Category> categories = categoryDAO.getAllCategory();
            request.setAttribute("categories", categories);

            RequestDispatcher dispatcher = request.getRequestDispatcher("/detail.jsp");
            dispatcher.forward(request, response);
        } catch (Exception e) {
            request.setAttribute("ex", e);
            RequestDispatcher dispatcher2 = request.getRequestDispatcher("/error.jsp");
            dispatcher2.forward(request, response);
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
