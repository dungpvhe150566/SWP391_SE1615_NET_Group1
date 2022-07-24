/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.impl.BlogDAOImpl;
import entity.Blog;
import entity.Users;
import java.io.IOException;
import java.io.InputStream;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

/**
 *
 * @author Dung
 */
@WebServlet(name = "AddBlogController", urlPatterns = {"/addblog"})
@MultipartConfig(
        fileSizeThreshold = 1024 * 1024 * 1, // 1 MB
        maxFileSize = 1024 * 1024 * 10, // 10 MB
        maxRequestSize = 1024 * 1024 * 100 // 100 MB
)
public class AddBlogController extends HttpServlet {

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
            Users user = (Users) request.getSession().getAttribute("user");
            if (user == null) {
                if (user.getIsAdmin() == 0 && user.getIsSeller()== 0)
                        throw new Exception("Access denied");
                throw new Exception("Please login first");
            }
            
            String service = request.getParameter("do");
            String message = "";

            if (service != null) {
                if (service.equals("addblog")) {
                    Part filePart = request.getPart("fileInput");
                    String fileName = "";

                    InputStream inputStream = null; // input stream of the upload file
                    
                    if (filePart != null) {
                        // obtains input stream of the upload file
                        fileName = filePart.getSubmittedFileName();
                        inputStream = filePart.getInputStream();
                    }
                    
                    //Get and store all attribute of each Blog
                    try {
                        String title = request.getParameter("title").trim();
                        String content = request.getParameter("content").trim();
                        String imageLink = fileName;
                        int sellerID = 1;

                        Blog blog = new Blog(title, content, imageLink, sellerID, inputStream);

                        if ((new BlogDAOImpl()).addBlog(blog) > 0) {
                            message = "<p style=\"color: green\">Succesful</p>";
                        } else {
                            message = "<p style=\"color: green\">Fail to add products</p>";
                        }
                    } catch (NumberFormatException e) {
                        message = "<p style=\"color: red\">Wrong format input</p>";
                        e.printStackTrace();
                    }
                }
            }

            request.setAttribute("message", message);
            request.getRequestDispatcher("add-blog.jsp").forward(request, response);
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
