package controller;

import dao.impl.OrderStatusDAOImpl;
import dao.impl.OrdersDAOImpl;
import entity.OrderDetail;
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
@WebServlet(name = "MyOrderByAjax", urlPatterns = {"/Update/myorder"})
public class MyOrderByAjax extends HttpServlet {

    public static int page = 2;

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
            Vector<OrderStatus> vecOrderStatus = (new OrderStatusDAOImpl()).getOrderStatusList();
            
            String result = "";

            if (vecOrder.size() > 0) {
                for (Orders order : vecOrder) {
                    result += "<div class=\"pb-1\" style=\"background-color: #ffffff\">\n"
                            + "                                    <ul class=\"list-group list-group-flush ml-2 mr-2\">\n"
                            + "                                        <li class=\"list-group-item\">\n"
                            + "<div class=\"row\">\n"
                            + "                                                    <div class=\"col-md-8\">\n"
                            + "                                                        <p class=\"my-auto\">"+vecOrderStatus.elementAt(order.getStatus() - 1).getName()+"</p>\n"
                            + "                                                    </div>\n"
                            + "                                                    <div class=\"col-md-4\">\n"
                            + "                                                        <p class=\"my-auto text-right\">"+order.getDayBuy()+"</p>\n"
                            + "                                                    </div>\n"
                            + "                                                </div>"
                            + "                                        </li>\n";

                    for (OrderDetail orderDetail : order.getOrderDetail()) {
                        result += "<li class=\"list-group-item\" onclick=\"viewDetails(" + order.getID() + ")\">\n"
                                + "                                                <span class=\"row\">\n"
                                + "                                                    <div class=\"row col-md-11 my-auto\">\n"
                                + "                                                        <div class=\"col-md-2 my-auto\">\n"
                                + "                                                            <img src=\"https://bootdey.com/img/Content/avatar/avatar7.png\" alt=\"Admin\" class=\"border\" width=\"100%\">\n"
                                + "                                                        </div>\n"
                                + "                                                        <div class=\"col-md-10 pl-0\">\n"
                                + "                                                            <span>" + orderDetail.getProductName() + "</span>\n"
                                + "                                                            <div>x" + orderDetail.getQuantity() + "</div>\n"
                                + "                                                        </div>\n"
                                + "                                                    </div>\n"
                                + "                                                    <div class=\"col-md-1 my-auto\">\n"
                                + "                                                        <span class=\"\">" + orderDetail.getProductPrice() + "</span>\n"
                                + "                                                    </div>\n"
                                + "                                                </span>\n"
                                + "                                            </li>\n";
                    }

                    result += "</ul>\n"
                            + "                                </div>\n"
                            + "                                <div class=\"rounded-top border-top mb-3\" style=\"background-color: #fffefb\">\n"
                            + "                                    <div class=\"text-right mt-3 mb-3\">\n"
                            + "                                        <span class=\"mr-3\">Total Price: d " + order.getTotalPrice() + "</span>\n"
                            + "                                    </div>\n"
                            + "                                    <div class=\"text-right\">\n"
                            + "                                        <div class=\"btn-group mb-3 mt-2\" role=\"group\" aria-label=\"Basic example\">\n"
                            + "                                            <button type=\"button\" class=\"btn btn-secondary btn-lg mr-3\">Feedback</button>\n"
                            + "                                            <button type=\"button\" class=\"btn btn-primary btn-lg mr-3\">Buy Again</button>\n"
                            + "                                        </div>\n"
                            + "                                    </div>\n"
                            + "                                </div>\n";
                }

                page++;
            }

            response.getWriter().print(result);
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
