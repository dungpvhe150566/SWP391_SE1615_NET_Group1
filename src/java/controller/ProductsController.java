package controller;

import entity.Category;
import entity.Product;
import java.io.IOException;
import java.util.Vector;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import dao.impl.CategoryDAOImpl;
import dao.impl.ProductDAOImpl;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;

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
        try {
            /* TODO output your page here. You may use following sample code. */
            String service = request.getParameter("do");
            String categoryID = request.getParameter("CategoryID");
            ProductDAOImpl productDAO = new ProductDAOImpl();
            
            String name = "";
            if (request.getParameter("name") != null) {
                name = request.getParameter("name");
            }
            
            String message = "";

            if (service != null) {
                //Delete one product 
                if (service.equals("deleteProduct")) {
                    try {
                        int productID = Integer.parseInt(request.getParameter("productID"));
                        if ((new ProductDAOImpl()).deleteProduct(productID) > 0) {
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
                        if ((new ProductDAOImpl()).deleteProducts(arrProductID) > 0) {
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

            int page = 1;
            if (request.getParameter("page") != null) {
                page = Integer.parseInt(
                        request.getParameter("page"));
            }

            ArrayList<Product> productList = null;
            int totalPage;
            int numOfRecord = 6;
            int endRow = page * numOfRecord;
            int startRow = endRow - numOfRecord + 1;
            
            if (categoryID != null && !categoryID.equals("0") && !categoryID.equals("undefined")) {
                productList = productDAO.getProductList(Integer.parseInt(categoryID), name, null, null, startRow, endRow, null);
                totalPage = productDAO.getTotalPage(Integer.parseInt(categoryID), name, null, null);
            } else {
                productList = productDAO.getProductList(0, name, null, null, startRow, endRow, null);
                totalPage = productDAO.getTotalPage(0, name, null, null);
            }

            int pageNumbers[] = new int[totalPage];
            
            for (int i = 0; i < pageNumbers.length; i++) {
                pageNumbers[i] = i + 1;
            }

            //Get Category List
            ArrayList<Category> categoryList = (new CategoryDAOImpl()).getAllCategory();

            request.setAttribute("pageNumbers", pageNumbers);
            request.setAttribute("page", page);
            request.setAttribute("name", name);
            request.setAttribute("message", message);
            request.setAttribute("categoryID", categoryID);
            request.setAttribute("categoryList", categoryList);
            request.setAttribute("productList", productList);

            request.getRequestDispatcher("products.jsp").forward(request, response);
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
