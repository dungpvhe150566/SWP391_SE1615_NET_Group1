/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dao.OrderDetailDAO;
import dao.OrdersDAO;
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
import dao.ShipDAO;
import dao.ShipInfoDAO;
import entity.Orders;
import entity.ShipInfo;

/**
 *
 * @author Pham Van Trong
 */
@WebServlet(name = "CheckOutControllner", urlPatterns = {"/Checkout"})
public class CheckOutControllner extends HttpServlet {

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
         request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        try ( PrintWriter out = response.getWriter()) {
//            int shiptId = Integer.parseInt(request.getParameter("shiptId"));
//             int productID = Integer.parseInt(request.getParameter("productID"));
            HttpSession session = request.getSession();
            
            List<entity.Ship> listShips = new ShipDAO().getAllShips();
            Map<Integer, Cart> carts = (Map<Integer, Cart>) session.getAttribute("carts");
            if (carts == null) {
                carts = new LinkedHashMap<>();
            }

            //tinh tong tien
            double totalMoney = 0;
            for (Map.Entry<Integer, Cart> entry : carts.entrySet()) {
                Integer productId = entry.getKey();
                Cart cart = entry.getValue();

                totalMoney += (cart.getAmount()* cart.getProduct().getOriginalPrice());

            }
            double totalMoneys = 0;
            for (Map.Entry<Integer, Cart> entry : carts.entrySet()) {
                Integer productId = entry.getKey();
                Cart cart = entry.getValue();

                totalMoney += (cart.getAmount()* cart.getProduct().getOriginalPrice() + 50000);

            }
            // tinh gia shipping
//            int shipID = Integer.parseInt(request.getParameter("shipID"));
//            ShipDAO shipDAO = new ShipDAO();
//            List<Ship> listShip = shipDAO.getAllShips();
//            request.setAttribute("carts", carts);
//            session.setAttribute("listShip", listShip);
//              Ship ship =  (Ship) new ShipDAO().getPricebyIDShips(shiptId);
//            carts.put(productID, Cart.builder().ship(ship).build());
//           request.setAttribute("listShips", listShips);
        
            request.setAttribute("totalMoney", totalMoney);
//            request.setAttribute("totalMoneys", totalMoney);
            request.getRequestDispatcher("checkout.jsp").forward(request, response);
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
         request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        String name = request.getParameter("name");
        String phone = request.getParameter("phone");
        String address = request.getParameter("address");
        String note = request.getParameter("note");
        int CityId = Integer.parseInt(request.getParameter("CityId"));
//        int OrderID = Integer.parseInt(request.getParameter("OrderID"));
 //trả về id tự tăng của bản ghi vừa lưu vào database
        
        HttpSession session = request.getSession();
        Map<Integer, Cart> carts = (Map<Integer, Cart>) session.getAttribute("carts");
        if (carts == null) {
            carts = new LinkedHashMap<>();
        }

        //tinh tong tien
        double totalPrice = 0;
        for (Map.Entry<Integer, Cart> entry : carts.entrySet()) {
            Integer productId = entry.getKey();
            Cart cart = entry.getValue();

            totalPrice += (cart.getAmount()* cart.getProduct().getOriginalPrice() + 50000);

        }
        Orders order = Orders.builder()
                .UserID(1)
                .TotalPrice((float) totalPrice)
                .Note(note)
                .build();
        int orderId = new OrdersDAO().createReturnId(order);
        //Lưu OrderDetail

        new OrderDetailDAO().saveCart(orderId, carts);
       
        
         //lưu vào database
        //Lưu Shipping
        ShipInfo shipping = ShipInfo.builder()
                .Order_ID(orderId)
                .CustomerName(name)
                .ShippingAddress(address)
                .ShipCityID(CityId)
                .PhoneNum(phone)
                .Note(note)
                .build();
        int shippingId = new ShipInfoDAO().createReturnId(shipping);
        
        session.removeAttribute("carts");
        response.sendRedirect("thankyou.jsp");
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
