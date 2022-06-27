
package controller;

import dao.impl.OrderStatusDAOImpl;
import dao.impl.OrdersDAOImpl;
import entity.OrderStatus;
import entity.Orders;
import entity.Users;
import java.io.IOException;
import java.util.Vector;
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
@WebServlet(name = "MyOrderController", urlPatterns = {"/myorder"})
public class MyOrderController extends HttpServlet {

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
            OrdersDAOImpl orderDAO = new OrdersDAOImpl();

            //Get User has been login from session
            Users user = (Users) request.getSession().getAttribute("user");
            if (user == null) {
                throw new Exception("Please login first");
            }
            int userID = user.getUserID();

            //Get Status ID from request
            int statusID = 0;
            if (request.getParameter("status") != null) {
                statusID = Integer.parseInt(request.getParameter("status"));
            }

            int page = 1;
            if (request.getParameter("page") != null) {
                page = Integer.parseInt(request.getParameter("page"));
            }

            String sortBy = "";
            if (request.getParameter("sortby") != null) {
                sortBy = request.getParameter("sortby");
            }
            
            String date = "";
            if (request.getParameter("date") != null) {
                date = request.getParameter("date");
            }

            int numOfRecord = 5;
            int endRow = page * numOfRecord;
            int startRow = endRow - numOfRecord + 1;

            Vector<Orders> vecOrder = orderDAO.getOrdersList(startRow, endRow, userID, statusID, date, sortBy);

            int totalPage = orderDAO.getTotalPage(userID, statusID, date, numOfRecord);
            int pageNumbers[] = new int[totalPage];

            for (int i = 0; i < pageNumbers.length; i++) {
                pageNumbers[i] = i + 1;
            }

            Vector<OrderStatus> vecOrderStatus = (new OrderStatusDAOImpl()).getOrderStatusList();

            request.setAttribute("date", date);
            request.setAttribute("user", user);
            request.setAttribute("statusID", statusID);
            request.setAttribute("pageNumbers", pageNumbers);
            request.setAttribute("page", page);
            request.setAttribute("vecOrderStatus", vecOrderStatus);
            request.setAttribute("vecOrder", vecOrder);
            request.getRequestDispatcher("myorder.jsp").forward(request, response);
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
