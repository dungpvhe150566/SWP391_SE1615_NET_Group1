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

import dao.impl.BlogDAOImpl;
import entity.Blog;
import entity.Users;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
   * This class makes handling requirements for users to view the favorite blog list
*View favorite blog list
<p>
   * Error: Error occurs will be received and processed and handled errors
   * Page <p>
   *
   * @Author haipm
   */
public class ViewlistfavoriteController extends HttpServlet {

    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try {
            HttpSession session = request.getSession();
            Users a = (Users) session.getAttribute("user");

            String index = request.getParameter("index");
            int indexpage = 0;
            if (index == null) {
                index = "1";
            }
            indexpage = Integer.parseInt(index);
            BlogDAOImpl dao = new BlogDAOImpl();
            List<Blog> list = dao.pagingFavorite(indexpage,a.getUserID());
            request.setAttribute("listP", list);
            request.setAttribute("user", a);
            request.setAttribute("indexpage", indexpage);
            request.getRequestDispatcher("Viewlistfavorite.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
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
