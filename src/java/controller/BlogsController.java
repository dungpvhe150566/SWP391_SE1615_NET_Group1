/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.impl.BlogDAOImpl;
import entity.Blog;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Dung
 */
@WebServlet(name = "BlogsController", urlPatterns = {"/blogs"})
public class BlogsController extends HttpServlet {

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
            BlogDAOImpl blogDAO = new BlogDAOImpl();
            String service = request.getParameter("do");
            
            String message = "";
            if (service != null) {
                //Delete one product 
                if (service.equals("deleteBlog")) {
                    try {
                        int blogID = Integer.parseInt(request.getParameter("blogID"));
                        if (blogDAO.deleteBlog(blogID) > 0) {
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
                if (service.equals("deleteBlogs")) {
                    try {
                        String[] arrBlogID = request.getParameterValues("blogID");
                        if (blogDAO.deleteBlogs(arrBlogID) > 0) {
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
            
            String title = "";
            if (request.getParameter("title") != null) {
                title = request.getParameter("title");
            }
            
            ArrayList<Blog> blogList = null;
            int totalPage;
            int numOfRecord = 6;
            int endRow = page * numOfRecord;
            int startRow = endRow - numOfRecord + 1;
            
            blogList = blogDAO.getBlogList(title, startRow, endRow);
            totalPage = blogDAO.getTotalPage(title, numOfRecord);
            
            int pageNumbers[] = new int[totalPage];
            
            for (int i = 0; i < pageNumbers.length; i++) {
                pageNumbers[i] = i + 1;
            }
            
            request.setAttribute("message", message);
            request.setAttribute("pageNumbers", pageNumbers);
            request.setAttribute("page", page);
            request.setAttribute("title", title);
            request.setAttribute("blogList", blogList);
            
            request.getRequestDispatcher("blogs.jsp").forward(request, response);
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
