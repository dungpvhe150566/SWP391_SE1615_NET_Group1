package controller;

import entity.Category;
import entity.Manufacturer;
import entity.Product;
import entity.ProductStatus;
import java.io.IOException;
import java.util.Vector;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import dao.impl.CategoryDAOImpl;
import dao.impl.ManufacturerDAOImpl;
import dao.impl.ProductDAOImpl;
import dao.impl.ProductStatusDAOImpl;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;

/**
 *
 * @author Dung
 */
@WebServlet(name = "EditProductController", urlPatterns = {"/editproduct"})
@MultipartConfig(
        fileSizeThreshold = 1024 * 1024 * 1, // 1 MB
        maxFileSize = 1024 * 1024 * 10, // 10 MB
        maxRequestSize = 1024 * 1024 * 100 // 100 MB
)
public class EditProductController extends HttpServlet {

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
        request.setCharacterEncoding("UTF-8");
        try {

            String service = request.getParameter("do");
            String message = "";

            if (service != null) {
                Part filePart = request.getPart("fileInput");
                String fileName = filePart.getSubmittedFileName();
                if (fileName.length() == 0) {
                    fileName = request.getParameter("imageLink");
                }

                //Get and store all attribute of each Product
                try {
                    int productID = Integer.parseInt(request.getParameter("productID").trim());
                    String productName = request.getParameter("product_name").trim();
                    String description = request.getParameter("description").trim();
                    int originalPrice = Integer.parseInt(request.getParameter("original_price").trim());
                    int sellPrice = Integer.parseInt(request.getParameter("sell_price").trim());
                    int salePercent = Integer.parseInt(request.getParameter("sale_percent").trim());
                    String imageLink = fileName;
                    int category = Integer.parseInt(request.getParameter("category").trim());
                    int seller = 1;
                    int amount = Integer.parseInt(request.getParameter("amount").trim());
                    int statusID = 1;
                    int manufacture = Integer.parseInt(request.getParameter("manufacture").trim());
                    float height = Float.parseFloat(request.getParameter("height").trim());
                    float width = Float.parseFloat(request.getParameter("width").trim());
                    float weight = Float.parseFloat(request.getParameter("weight").trim());

                    Product pro = new Product(productID, productName, description, originalPrice, sellPrice, salePercent, imageLink, category, seller, amount, statusID, manufacture, height, width, weight);

                    if ((new ProductDAOImpl()).updateProduct(pro) > 0) {
                        message = "<p style=\"color: green\">Succesful</p>";
                    } else {
                        message = "<p style=\"color: green\">Fail to add products</p>";
                    }
                } catch (NumberFormatException e) {
                    message = "<p style=\"color: red\">Wrong format input</p>";
                    e.printStackTrace();
                }
            }

            String productIDText = request.getParameter("productID");
            if (productIDText == null) {
                request.getRequestDispatcher("error.jsp").forward(request, response);
                return;
            } else {
                int productID = Integer.parseInt(productIDText);
                Product pro = (new ProductDAOImpl()).getProductById(productID);
                request.setAttribute("product", pro);
            }

            ArrayList<Category> categoryList = (new CategoryDAOImpl()).getAllCategory();
            ArrayList<Manufacturer> manufacturerList = (new ManufacturerDAOImpl()).getManufacturerList();
            Vector<ProductStatus> productStatusList = (new ProductStatusDAOImpl()).getProductStatusList();

            request.setAttribute("message", message);
            request.setAttribute("categoryList", categoryList);
            request.setAttribute("manufacturerList", manufacturerList);
            request.setAttribute("productStatusList", productStatusList);

            request.getRequestDispatcher("edit-product.jsp").forward(request, response);
        }catch(Exception e){
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
