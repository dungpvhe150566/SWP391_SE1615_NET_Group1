
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
 *
 * @author Sang
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
        try{

            // Get and Set service from User to 
            String service = request.getParameter("do");
            request.setAttribute("service", service);

//            Get the position of the current Page to paginate products to display to users
//            indexPage variable is the position of the page the user is viewing
//            totalPage is variable is the total number of product pages requested by the user
            int indexPage = 1;
//            get the Page position being displayed to the user so that page transitions can be performed
            String index = request.getParameter("indexPage");
            if (index != null) {
                indexPage = Integer.parseInt(index);
            }
            request.setAttribute("indexPage", indexPage);

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

//            User Search follow Category
            int categoryID = 0;
            if (service != null && service.equals("searchByCategory")) {
                categoryID = Integer.parseInt(request.getParameter("categoryID"));
                request.setAttribute("categoryID", categoryID);
            }

//            User Search follow ProductName
            String productName = "";
            if (service != null && service.equals("searchByName")) {
//                Get ProductName from User Input
                productName = request.getParameter("productName").trim();
                request.setAttribute("productName", productName);
            }

//            Sort product follow Price(Ascending/Descending)
            String sort = request.getParameter("sort");
            if (sort != null && !sort.isEmpty()) {
                request.setAttribute("sort", sort);
            }

            ProductDAOImpl productDao = new ProductDAOImpl();
//            Get List Products follow (CategoryID. ProductName, Price, ManufacturerID,Sort)
            ArrayList<Product> productsList = productDao.getProductList(categoryID, productName, prices,
                    manufacturersID, 6 * (indexPage - 1) + 1, 6 * indexPage, sort);
            request.setAttribute("products", productsList);

//            Get total PAge of list product(each page have max 6 products)
            int totalPage = productDao.getTotalPage(categoryID, productName, prices, manufacturersID);
            request.setAttribute("totalPage", totalPage);

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

        }
        catch(Exception e){
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
