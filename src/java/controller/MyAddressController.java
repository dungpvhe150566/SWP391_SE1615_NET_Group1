package controller;

import dao.impl.ShipDAOImpl;
import dao.impl.UserAddressDAOImpl;
import entity.Ship;
import entity.UserAddress;
import entity.Users;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
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
@WebServlet(name = "MyAddressController", urlPatterns = {"/myaddress"})
public class MyAddressController extends HttpServlet {

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
            String service = request.getParameter("do");
            UserAddressDAOImpl userAddressDAO = new UserAddressDAOImpl();
            ShipDAOImpl shipDAO = new ShipDAOImpl();

            Users user = (Users) request.getSession().getAttribute("user");
            if (user == null) {
                throw new Exception("Please login first");
            }
            int userID = user.getUserID();

            if (service != null && service.equals("add")) {
                String shipName = request.getParameter("shipName");
                String shipAddress = request.getParameter("shipAddress");
                int shipCity = Integer.parseInt(request.getParameter("shipCity"));
                String phoneNumber = request.getParameter("phone");

                userAddressDAO.addUserAddress(new UserAddress(userID, shipName, shipAddress, shipCity, phoneNumber));
            }
            
            if (service != null && service.equals("delete")) {
                int userAddressID = Integer.parseInt(request.getParameter("id"));
                
                userAddressDAO.deleteUserAddress(userAddressID);
            }
            
            if (service != null && service.equals("edit")) {
                int userAddressID = Integer.parseInt(request.getParameter("userAddressID"));
                String shipName = request.getParameter("shipName");
                String shipAddress = request.getParameter("shipAddress");
                int shipCity = Integer.parseInt(request.getParameter("shipCity"));
                String phoneNumber = request.getParameter("phone");
                
                userAddressDAO.editUserAddress(new UserAddress(userAddressID, userID, shipName, shipAddress, shipCity, phoneNumber));
            }

            ArrayList<UserAddress> arrUserAddress = userAddressDAO.getUserAddressListByUserID(userID);
            List<Ship> arrShip = shipDAO.getAllShips();

            request.setAttribute("arrShip", arrShip);
            request.setAttribute("arrUserAddress", arrUserAddress);
            request.getRequestDispatcher("myaddress.jsp").forward(request, response);
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
