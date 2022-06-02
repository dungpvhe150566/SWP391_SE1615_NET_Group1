/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entity.Category;
import entity.Product;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Vector;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.CategoryDAO;
import model.ProductDAO;

/**
 *
 * @author Dung
 */
public class ProductsController extends HttpServlet {

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
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            String service = request.getParameter("do");
            String categoryID = request.getParameter("CategoryID");

            if (service != null) {
                if (service.equals("deleteProduct")) {
                    try {
                        int productID = Integer.parseInt(request.getParameter("productID"));
                        (new ProductDAO()).deleteProduct(productID);
                    } catch(NumberFormatException e) {};
                }
                
                if (service.equals("deleteProducts")) {
                    try {
                        String[] arrProductID = request.getParameterValues("productID");
                        (new ProductDAO()).deleteProducts(arrProductID);
                    } catch(NumberFormatException e) {};
                }
            }

            Vector<Product> productList = new Vector<Product>();

            if (categoryID != null && !categoryID.equals("0")) {
                productList = (new ProductDAO()).getProductListByCategoryID(categoryID);
            } else {
                productList = (new ProductDAO()).getProductList();
            }

            Vector<Category> categoryList = categoryList = (new CategoryDAO()).getAllCategory();

            request.setAttribute("categoryList", categoryList);
            request.setAttribute("productList", productList);
            
            request.getRequestDispatcher("products.jsp").forward(request, response);
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
