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

import dao.impl.UsersDAOImpl;
import entity.Users;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * This class will handle user requirements is to search for the user's account
 * in the list Search for user accounts in the list
 * <p>
 * Error: Error occurs will be received and processed and handled errors Page
 * <p>
 *
 * @Author haipm
 */
public class searchInAccountController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("utf-8");
        try {
            String searchText = request.getParameter("text");
            int indexPage = 1;
            //get the Page position being displayed to the user so that page transitions can be performed
            String index = request.getParameter("indexPage");
            if (index != null) {
                indexPage = Integer.parseInt(index);
            }
            request.setAttribute("indexPage", indexPage);
            UsersDAOImpl userDAO = new UsersDAOImpl();
            //Get total PAge of list product(each page have max 6 products)
            int totalPage = userDAO.getTotalPageSearch(searchText.trim());
            request.setAttribute("totalPage", totalPage);

            //Set data to JSP
            UsersDAOImpl UserDAO = new UsersDAOImpl();
            List<Users> listA = UserDAO.searchAccountInManager(searchText.trim(), 6 * (indexPage - 1) + 1, 6 * indexPage);
            // Set data to jsp
            request.setAttribute("list", listA);
            request.setAttribute("mess", "Not found");
            request.setAttribute("txt", searchText.trim());
            request.getRequestDispatcher("AccountManager.jsp").forward(request, response);

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
