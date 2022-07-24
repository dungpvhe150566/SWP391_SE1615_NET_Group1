/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.impl.UsersDAOImpl;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import entity.Users;

/**
 *
 * @author HP
 */
@WebServlet(name = "ChangeServlet", urlPatterns = {"/change"})
public class ChangeServlet extends HttpServlet {

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
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ChangeServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ChangeServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
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
        response.sendRedirect("change.jsp");
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
            HttpSession session = request.getSession();
            String pass = request.getParameter("opass"); //lay password cu da nhap
            String npass = request.getParameter("npass"); // lay password moi da nhap
            String cpass = request.getParameter("cpass"); // lay password ma nguoi dung nhap lai
            Users U = (Users)session.getAttribute("user"); // lay du lieu user tren session
            if(!U.getPassword().equalsIgnoreCase(pass)){ // neu pass cu nguoi dung nhap khong giong password trong database thi hien len thong bao
                String alert = "You enter the wrong password";
                request.setAttribute("err", alert);
                request.getRequestDispatcher("change.jsp").forward(request, response);
            }
            if(!npass.equalsIgnoreCase(cpass)){ // new password moi da nhap khac voi password nhap lai thi hien ra thong bao
                String alert = "Your confirm password is wrong";
                request.setAttribute("err", alert);
                request.getRequestDispatcher("change.jsp").forward(request, response);
            }
            UsersDAOImpl dao = new UsersDAOImpl(); // neu trong vao truong nao o tren thi su dung ham de reset lai mat khau
            dao.resetPassword(npass, U.getEmail());
            request.getRequestDispatcher("login.jsp").forward(request, response);
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
