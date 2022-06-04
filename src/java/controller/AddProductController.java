/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entity.Category;
import entity.Manufacturer;
import entity.Product;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.Vector;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import model.CategoryDAO;
import model.ManufacturerDAO;
import model.ProductDAO;

/**
 *
 * @author Dung
 */
@WebServlet(name = "AddProductController", urlPatterns = {"/addproduct"})
@MultipartConfig(
        fileSizeThreshold = 1024 * 1024 * 1, // 1 MB
        maxFileSize = 1024 * 1024 * 10, // 10 MB
        maxRequestSize = 1024 * 1024 * 100 // 100 MB
)
public class AddProductController extends HttpServlet {

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
            String message = "";

            if (service != null) {
                if (service.equals("addproduct")) {
                    Part filePart = request.getPart("fileInput");
                    String fileName = filePart.getSubmittedFileName();

                    //Get and store all attribute of each Product
                    try {
                        String productName = request.getParameter("product_name");
                        String description = request.getParameter("description");
                        int originalPrice = Integer.parseInt(request.getParameter("original_price"));
                        int sellPrice = Integer.parseInt(request.getParameter("sell_price"));
                        int salePercent = Integer.parseInt(request.getParameter("sale_percent"));
                        String imageLink = fileName;
                        int category = Integer.parseInt(request.getParameter("category"));
                        int seller = 1;
                        int amount = Integer.parseInt(request.getParameter("amount"));
                        int statusID = 1;
                        int manufacture = Integer.parseInt(request.getParameter("manufacture"));
                        float height = Float.parseFloat(request.getParameter("height"));
                        float width = Float.parseFloat(request.getParameter("width"));
                        float weight = Float.parseFloat(request.getParameter("weight"));

                        Product pro = new Product(productName, description, originalPrice, sellPrice, salePercent, imageLink, category, seller, amount, statusID, manufacture, height, width, weight);

                        (new ProductDAO()).addProduct(pro);

                        message = "<p style=\"color: green\">Succesful</p>";
                    } catch (NumberFormatException e) {
                        message = "<p style=\"color: red\">Wrong format input</p>";
                        e.printStackTrace();
                    }

                }
            }

            Vector<Category> categoryList = (new CategoryDAO()).getAllCategory();
            Vector<Manufacturer> manufacturerList = (new ManufacturerDAO()).getManufacturerList();

            request.setAttribute("message", message);
            request.setAttribute("categoryList", categoryList);
            request.setAttribute("manufacturerList", manufacturerList);

            request.getRequestDispatcher("add-product.jsp").forward(request, response);
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
