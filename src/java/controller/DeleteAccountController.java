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

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import dao.impl.UsersDAOImpl;
import entity.Users;

/**
 * This class makes handling requirements for users to manage the user list
 * Delete users
 * <p>
 * Error: Error occurs will be received and processed and handled errors Page
 * <p>
 *
 * @Author haipm
 */
public class DeleteAccountController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        try {
            //Get UserID from JSP
            String id = request.getParameter("UserID");
            System.out.println("aaaaa" + id);
            //Call DAO
            UsersDAOImpl dao = new UsersDAOImpl();
            Users u = dao.getAccountByID(id);
            //Use function Delete to delete by ID
            int countOrder = dao.checkExistOrder(id);
            System.out.println(countOrder);
            if (countOrder == 0 && u.getIsAdmin() == 0 && u.getIsSeller() == 0) {
                dao.deleteAccount(id);

            } else {
                request.setAttribute("test", countOrder);
                request.setAttribute("isseller", u.getIsSeller());
                request.setAttribute("isadmin", u.getIsAdmin());
                request.setAttribute("mes", "Can't delete account");
                request.getRequestDispatcher("AccountManagerController").forward(request, response);

            }
            //Put data to JSP
            request.setAttribute("test", countOrder);
            request.setAttribute("isseller", u.getIsSeller());
            request.setAttribute("isadmin", u.getIsAdmin());
            request.setAttribute("mesde", "Deleted successfully");
            request.getRequestDispatcher("AccountManagerController").forward(request, response);

        } catch (Exception e) {

            response.sendRedirect("error.jsp");
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
