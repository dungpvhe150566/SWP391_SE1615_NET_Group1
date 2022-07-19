/**
 * Copyright(C) 2022,Group1-NETSE1615.<p>
 * Shopping Web:
 * <p>
 *
 * Electronic Shop<p>
 *
 * Record of change:
 * <p>
 *
 * DATE Version AUTHOR DESCRIPTION<p>
 * 2022-08-16 01 SangLT Update Code Convention<p>
 */
package controller;

import entity.Category;
import entity.Manufacturer;
import entity.Product;
import java.io.IOException;
import java.util.Arrays;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import dao.impl.CategoryDAOImpl;
import dao.impl.ManufacturerDAOImpl;
import dao.impl.ProductDAOImpl;
import java.util.ArrayList;

/**
 * This class implements user handling from Shop Page to process and display
 * according to user request<p>
 * Bugs: Errors that occur will be received and processed and returned to Error
 * Page<p>
 *
 * @author SangLT
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
        request.setCharacterEncoding("utf-8");
        try {

            /* Get and Set service from User */
            String service = request.getParameter("do");
            request.setAttribute("service", service);

            /* Get and Set of selected page position from user */
            int indexPage = 1;// Set default index page is 1
            String index = request.getParameter("indexPage");// Get page selected by User
            if (index != null) {// In case the user selects page
                indexPage = Integer.parseInt(index);// Convert String page to Integer
            }
            request.setAttribute("indexPage", indexPage);// Set page to View page

            /* Filter Products follow Manufacturers*/
            String[] manufacturersID = request.getParameterValues("manufacturer");// Get list manufacturersID selected by User from View page
            String msID = request.getParameter("manufacturers");
            if (msID != null && !msID.isEmpty()) {// In case users filter by Manufacturer 
                //list ManufacturersID have form "[...,...]" so need split 
                manufacturersID = msID.substring(1, msID.length() - 1).split(",");
            }
            if (manufacturersID != null) {
                // Set manufacturersID selected by User to View page
                request.setAttribute("manufacturers", Arrays.toString(manufacturersID));
            }

            /* Filter Products follow Price */
            String[] prices = request.getParameterValues("prices");
            String listPrices = request.getParameter("listPrices");
            if (listPrices != null && !listPrices.isEmpty()) {// In case users filter by Price 
                //list prices have form "[...,...]" so need split 
                prices = listPrices.substring(1, listPrices.length() - 1).split(",");
            }
            if (prices != null) {
                // Set Price selected by User to View page
                request.setAttribute("listPrices", Arrays.toString(prices));
            }

            /* User Search follow Category */
            int categoryID = 0;
            if (service != null && service.equals("searchByCategory")) {
                categoryID = Integer.parseInt(request.getParameter("categoryID"));
                request.setAttribute("categoryID", categoryID);
            }

            /* User Search follow ProductName */
            String productName = "";
            if (service != null && service.equals("searchByName")) {// In case the user searches by the name of the product
                // Get ProductName User Input from View page
                productName = request.getParameter("productName").trim();
                // Set ProductName User Input to View page
                request.setAttribute("productName", productName);

            }
           
            /* Sort product follow Price(Ascending/Descending) */
            String sort = request.getParameter("sort");// Get type sort clicked by User from View page
            if (sort != null && !sort.isEmpty()) {// In case users search by price increase or decrease
                // Set type sort clicked by User to View page
                request.setAttribute("sort", sort);
            }

            /* Get products according to requests from users */
            ProductDAOImpl productDao = new ProductDAOImpl();// create a DAO object to get the Products table data from the Database
            //Get List Products follow (CategoryID. ProductName, Price, ManufacturerID,Sort)
            ArrayList<Product> productsList = productDao.getProductList(categoryID, productName, prices,
                    manufacturersID, 6 * (indexPage - 1), 6 * indexPage, sort);
            request.setAttribute("products", productsList);// Set list Products to View page

            /* Get total PAge of list product(each page have max 6 products) */
            int totalPage = productDao.getTotalPage(categoryID, productName, prices, manufacturersID);
            request.setAttribute("totalPage", totalPage);// Set total number of Page to View page

            CategoryDAOImpl categoryDao = new CategoryDAOImpl();
//            Set CategoryList to display to the View Page
            ArrayList<Category> categories = categoryDao.getAllCategory();
            request.setAttribute("categories", categories);
//            Set ManufacturerList to display to the View Page
            ManufacturerDAOImpl manufacturerDAO = new ManufacturerDAOImpl();
            ArrayList<Manufacturer> manufacturers = manufacturerDAO.getManufacturerList();
            request.setAttribute("listManufacturers", manufacturers);

            RequestDispatcher dispatcher1 = request.getRequestDispatcher("shop.jsp");
            dispatcher1.forward(request, response);

        } catch (Exception e) {// In case an Exception occurs

            /* Make requests and properties to Error Page */
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
