/**
 * Copyright(C) 2022,Group1-NETSE1615.<p>
 * Shopping Web:
 * <p>
 * Electronic Shop<p>
 *
 * Record of change:
 * <p>
 * DATE Version AUTHOR DESCRIPTION<p>
 * 2022-08-16 01 HaiPM Update Code Convention<p>
 */
package controller;

import entity.Users;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import dao.impl.UsersDAOImpl;
import javax.servlet.http.HttpSession;

/**
 * This class will handle the admin user to edit an account of a user Edit
 * account in the user's account list
 * <p>
 * Error: Error occurs will be received and processed and handled errors Page
 * <p>
 *
 * @Author haipm
 */
public class EditAccountController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet EditAccountController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet EditAccountController at " + request.getContextPath() + "</h1>");
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
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        try {
            //Get ID from jsp
            String id = request.getParameter("UserID");
            UsersDAOImpl dao = new UsersDAOImpl();
            //select users from id 
            Users x = dao.getAccountByID(id);

            //Push data to jsp
            HttpSession session = request.getSession();
            session.setAttribute("userid", x.getUserID());
            request.setAttribute("id", x.getUserID());
            request.setAttribute("user", x.getUsername());
            request.setAttribute("pass", x.getPassword());
            request.setAttribute("email", x.getEmail());
            request.setAttribute("Seller", x.getIsSeller());
            request.setAttribute("Admin", x.getIsAdmin());

            request.getRequestDispatcher("EditAccount.jsp").forward(request, response);
        } catch (Exception e) {
            response.sendRedirect("error.jsp");
        }
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
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        try {
            //Step 1: get data from jsp
            HttpSession session = request.getSession();
            int uid = (int) session.getAttribute("userid");
            String user = request.getParameter("user"); //Get by name
            String password = request.getParameter("pass");
            String email = request.getParameter("email");
            String isSell = request.getParameter("Seller");
            String isAdmin = request.getParameter("Admin");
            if (!"1".equals(isSell)) {
                isSell = "0";
            }
            if (!"1".equals(isAdmin)) {
                isAdmin = "0";
            }
            //Step 2: update to database
            UsersDAOImpl dao = new UsersDAOImpl();
            Users x = dao.getAccountByID(uid);
            boolean t = true;
            if (t = true) {
                dao.updateUser(uid, user, email, isSell, isAdmin);

            }
            //Set data to JSP
            request.setAttribute("mesde1", "Updated successfully");
            request.setAttribute("test1", t);
            request.getRequestDispatcher("AccountManagerController").forward(request, response);

        } catch (Exception e) {
            response.sendRedirect("thankyou.jsp");
        }

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
