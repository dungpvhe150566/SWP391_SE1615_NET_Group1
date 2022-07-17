/*
   check out
 */
package controller;

import dao.impl.OrderDetailDAOImpl;
import dao.impl.OrdersDAOImpl;
import entity.Cart;
import entity.Ship;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import dao.impl.ShipDAOImpl;
import dao.impl.ShipInfoDAOImpl;
import entity.Orders;
import entity.ShipInfo;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;

/**
 *
 * @author Pham Van Trong
 */
@WebServlet(name = "CheckOutControllner", urlPatterns = {"/Checkout"})
public class CheckOutControllner extends HttpServlet {
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        try {
            HttpSession session = request.getSession();
            List<entity.Ship> listShips = new ShipDAOImpl().getAllShips();
            Map<Integer, Cart> carts = (Map<Integer, Cart>) session.getAttribute("carts");
            if (carts == null) {
                carts = new LinkedHashMap<>();
            }
            //tinh tong tien
            int totalMoney = 0;
            for (Map.Entry<Integer, Cart> entry : carts.entrySet()) {
                Integer productId = entry.getKey();
                Cart cart = entry.getValue();

                totalMoney += (cart.getAmount() * (cart.getProduct().getOriginalPrice()));

            }
            int totalMoneys = 0;
            for (Map.Entry<Integer, Cart> entry : carts.entrySet()) {
                Integer productId = entry.getKey();
                Cart cart = entry.getValue();

                totalMoney += (cart.getAmount() * cart.getProduct().getOriginalPrice() + 50000);

            }
              request.setAttribute("listShips", listShips);
            request.setAttribute("totalMoney", totalMoney);
            request.getRequestDispatcher("checkout.jsp").forward(request, response);
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
        try {
            request.setCharacterEncoding("UTF-8");
            response.setCharacterEncoding("UTF-8");
            String name = request.getParameter("name");
            String phone = request.getParameter("phone");
            String address = request.getParameter("address");
            String note = request.getParameter("note");
            int CityId = Integer.parseInt(request.getParameter("CityId"));
            //trả về id tự tăng của bản ghi vừa lưu vào database

            HttpSession session = request.getSession();
            Map<Integer, Cart> carts = (Map<Integer, Cart>) session.getAttribute("carts");
            if (carts == null) {
                carts = new LinkedHashMap<>();
            }

            //tinh tong tien
            int totalPrice = 0;
            for (Map.Entry<Integer, Cart> entry : carts.entrySet()) {
                Integer productId = entry.getKey();
                Cart cart = entry.getValue();

                totalPrice += (cart.getAmount() * cart.getProduct().getOriginalPrice() + 50000);

            }
            Orders order = Orders.builder()
                    .UserID(1)
                    .TotalPrice((int) totalPrice)
                    .Note(note.trim())
                    .Status(1)
                    .build();
            int orderId = new OrdersDAOImpl().createReturnId(order);
            //Lưu OrderDetail

            new OrderDetailDAOImpl().saveCart(orderId, carts);

            //lưu vào database
            //Lưu Shipping
            ShipInfo shipping = ShipInfo.builder()
                    .Order_ID(orderId)
                    .CustomerName(name.trim())
                    .ShippingAddress(address.trim())
                    .ShipCityID(CityId)
                    .PhoneNum(phone)
                    .Note(note.trim())
                    .build();
            int shippingId = new ShipInfoDAOImpl().createReturnId(shipping);

            session.removeAttribute("carts");
            response.sendRedirect("thank");
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("ex", e);
            RequestDispatcher dispatcher2 = request.getRequestDispatcher("/error.jsp");
            dispatcher2.forward(request, response);
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
