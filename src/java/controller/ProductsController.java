package controller;

import entity.Category;
import entity.Product;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Vector;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import dao.CategoryDAO;
import dao.ProductDAO;

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
            String message = "";

            if (service != null) {
                //Delete one product 
                if (service.equals("deleteProduct")) {
                    try {
                        int productID = Integer.parseInt(request.getParameter("productID"));
                        if ((new ProductDAO()).deleteProduct(productID) > 0) {
                            message = "<p style=\"color: green\">Succesful</p>";
                        } else {
                            message = "<p style=\"color: red\">Fail to add products</p>";
                        }
                    } catch (NumberFormatException e) {
                        request.getRequestDispatcher("error.jsp").forward(request, response);
                        e.printStackTrace();
                    };
                }

                //Delete products has been checked
                if (service.equals("deleteProducts")) {
                    try {
                        String[] arrProductID = request.getParameterValues("productID");
                        if ((new ProductDAO()).deleteProducts(arrProductID) > 0) {
                            message = "<p style=\"color: green\">Succesful</p>";
                        } else {
                            message = "<p style=\"color: red\">Fail to add products</p>";
                        }
                    } catch (NumberFormatException e) {
                        request.getRequestDispatcher("error.jsp").forward(request, response);
                        e.printStackTrace();
                    };
                }
            }

            Vector<Product> productList = new Vector<Product>();
            //Get Product List if categoryID is exist or categoryID = 0, if not list all product
            if (categoryID != null && !categoryID.equals("0")) {
                productList = (new ProductDAO()).getProductListByCategoryID(categoryID);
            } else {
                productList = (new ProductDAO()).getProductList();
            }

            //Get Category List
            Vector<Category> categoryList = (new CategoryDAO()).getAllCategory();

            request.setAttribute("message", message);
            request.setAttribute("categoryID", categoryID);
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
