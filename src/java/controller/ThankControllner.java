/*
 suggest user buy more product
 */
package controller;

import dao.impl.CategoryDAOImpl;
import dao.impl.ManufacturerDAOImpl;
import dao.impl.ProductDAOImpl;
import entity.Category;
import entity.Manufacturer;
import entity.Product;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Pham Van Trong
 */
@WebServlet(name = "ThankControllner", urlPatterns = {"/thank"})
public class ThankControllner extends HttpServlet {

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
            // Get and Set service from User to 
            String service = request.getParameter("do");
            request.setAttribute("service", service);

            ProductDAOImpl productDao = new ProductDAOImpl();
            CategoryDAOImpl categoryDao = new CategoryDAOImpl();
            ManufacturerDAOImpl manufacturerDAO = new ManufacturerDAOImpl();

//            ProductsList variable stores a list of products
            ArrayList<Product> productsList = null;

//            Get the position of the current Page to paginate products to display to users
//            indexPage variable is the position of the page the user is viewing
//            totalPage is variable is the total number of product pages requested by the user
            int indexPage = 1;
            int totalPage = 0;
//            get the Page position being displayed to the user so that page transitions can be performed
            String index = request.getParameter("indexPage");
            if (index != null) {
                indexPage = Integer.parseInt(index);
            }

//            Filter follow Manufacturers
//            get the ManufacturersID selected from the user
            String[] manufacturersID = request.getParameterValues("manufacturer");
            String msID = request.getParameter("manufacturers");
            if (msID != null && !msID.isEmpty()) {
//                list ManufacturersID have form "[...,...]" so need split 
                manufacturersID = msID.substring(1, msID.length() - 1).split(",");
            }
            if (manufacturersID != null) {
                request.setAttribute("manufacturers", Arrays.toString(manufacturersID));
            }

//            Filter follow Price
//            get the prices selected from the user
            String[] prices = request.getParameterValues("prices");
            String listPrices = request.getParameter("listPrices");
            if (listPrices != null && !listPrices.isEmpty()) {
//                list prices have form "[...,...]" so need split 
                prices = listPrices.substring(1, listPrices.length() - 1).split(",");
            }
            if (prices != null) {
                request.setAttribute("listPrices", Arrays.toString(prices));
            }

//            Sort product follow Price(Ascending/Descending)
            String sort = request.getParameter("sort");
            if (sort != null && !sort.isEmpty()) {
                request.setAttribute("sort", sort);
            }

//            User Search follow Category
            if (service != null && service.equals("searchByCategory")) {
                int categoryID = Integer.parseInt(request.getParameter("categoryID"));
                productsList = productDao.getProductList(categoryID, "", prices,
                        manufacturersID, 6 * (indexPage - 1) + 1, 6 * indexPage, sort);
                totalPage = productDao.getTotalPage(categoryID, "", prices, manufacturersID);
                request.setAttribute("categoryID", categoryID);
            } //            User Search follow ProductName
            else if (service != null && service.equals("searchByName")) {
//                Get ProductName from User Input
                String productName ="";
                productName = request.getParameter("productName").trim();
                request.setAttribute("productName", productName);
            } //            List All Products
            else {
                productsList = productDao.getProductList(0, "", prices,
                        manufacturersID, 6 * (indexPage - 1) + 1, 6 * indexPage, sort);
                totalPage = productDao.getTotalPage(0, "", prices, manufacturersID);
            }

//            set information to display to the user(CategoryList, ProductsList,ManufacturingsList)
            ArrayList<Category> categories = categoryDao.getAllCategory();
            request.setAttribute("products", productsList);
            request.setAttribute("categories", categories);
            ArrayList<Manufacturer> manufacturers = manufacturerDAO.getManufacturerList();
            request.setAttribute("listManufacturers", manufacturers);
            request.setAttribute("indexPage", indexPage);
            request.setAttribute("totalPage", totalPage);

            RequestDispatcher dispatcher = request.getRequestDispatcher("thankyou.jsp");

            dispatcher.forward(request, response);

        } catch (Exception e) {
            request.setAttribute("ex", e);
            RequestDispatcher dispatcher2 = request.getRequestDispatcher("/error.jsp");
            dispatcher2.forward(request, response);
//            e.printStackTrace();
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
