/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entity.Category;
import entity.Manufacturer;
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
import model.ManufacturerDAO;
import model.ProductDAO;

/**
 *
 * @author Admin
 */
public class ShopController extends HttpServlet {

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

            String service = request.getParameter("do");

            ProductDAO productDao = new ProductDAO();
            CategoryDAO categoryDao = new CategoryDAO();
            ManufacturerDAO manufacturerDAO = new ManufacturerDAO();
            
            Vector<Product> products = null;
            
            int indexPage = 1;
            int totalPage = 0;
            String index = request.getParameter("indexPage");
            if (index != null) {
                indexPage = Integer.parseInt(index);
            }
            
            if(service!=null && service.equals("searchByManufacturer")){
                int manufacturerID = Integer.parseInt(request.getParameter("manufacturerID"));
                products = productDao.searchByMID(manufacturerID, 6 * (indexPage - 1) + 1, 6 * indexPage);
                totalPage = productDao.getTotalPageByMID(manufacturerID);
                request.setAttribute("manufacturerID", manufacturerID);
            }else if (service != null && service.equals("searchByName")) {
                String productName = request.getParameter("productName");
                products = productDao.getSearchByName(productName, 6 * (indexPage - 1) + 1, 6 * indexPage);
                totalPage = productDao.getTotalPage(productName);
                request.setAttribute("productName", productName);
            } else {
                products = productDao.getProductList(6 * (indexPage - 1) + 1, 6 * indexPage);
                totalPage = productDao.getTotalPage("");
            }

            request.setAttribute("service", service);
            Vector<Category> categories = categoryDao.getAllCategory();
            request.setAttribute("products", products);
            request.setAttribute("categories", categories);
            Vector<Manufacturer> manufacturers = manufacturerDAO.getManufacturerList();
            request.setAttribute("manufacturers", manufacturers);
            request.setAttribute("indexPage", indexPage);
            request.setAttribute("totalPage", totalPage);

            RequestDispatcher dispatcher = request.getRequestDispatcher("/shop.jsp");
            dispatcher.forward(request, response);

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
