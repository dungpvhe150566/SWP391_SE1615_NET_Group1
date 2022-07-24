/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.impl.BlogDAOImpl;
import dao.impl.ProductOldDAOImpl;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.Blob;
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
@WebServlet(name = "RetrieveImage", urlPatterns = {"/Blogs/getImage"})
public class RetrieveImage extends HttpServlet {

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
            if (service != null && service != "") {
                String productId = request.getParameter("id");
                if (productId != null) {
                    int productID = Integer.parseInt(productId);
                    Blob image = (new ProductOldDAOImpl().getImage(productID));
                    byte byteArray[] = image.getBytes(1, (int) image.length());
                    response.setContentType("image/gif");
                    OutputStream os = response.getOutputStream();
                    os.write(byteArray);
                    os.flush();
                    os.close();
                }
            } else {
                String blogId = request.getParameter("id");
                if (blogId != null) {
                    int blogID = Integer.parseInt(blogId);
                    Blob image = (new BlogDAOImpl().getImage(blogID));
                    byte byteArray[] = image.getBytes(1, (int) image.length());
                    response.setContentType("image/gif");
                    OutputStream os = response.getOutputStream();
                    os.write(byteArray);
                    os.flush();
                    os.close();
                }
            }
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
