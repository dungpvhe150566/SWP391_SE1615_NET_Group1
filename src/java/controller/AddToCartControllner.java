/*
   add to cart
 */
package controller;

import entity.Cart;
import entity.Product;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedHashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import dao.impl.ProductDAOImpl;
import dao.impl.ShipDAOImpl;
import entity.Ship;
import javax.servlet.RequestDispatcher;

/**
 *
 * @author Pham Van Trong
 */
@WebServlet(name = "AddToCartControllner", urlPatterns = {"/addtocart"})
public class AddToCartControllner extends HttpServlet {
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try  {   
             int productId = Integer.parseInt(request.getParameter("productId"));
            //map    productId | cart
            HttpSession session = request.getSession();
            Map<Integer, Cart> carts = (Map<Integer, Cart>) session.getAttribute("carts");
            if (carts == null) {
                carts = new LinkedHashMap<>();
            }
            if (carts.containsKey(productId)) {//sản phẩm đã có trên giỏ hàng
                int oldQuantity = carts.get(productId).getAmount();
                carts.get(productId).setAmount(oldQuantity + 1);
            } else {//sản phẩm chưa có trên giỏ hàng
                Product product = new ProductDAOImpl().getProductById(productId);
                carts.put(productId, Cart.builder().product(product).Amount(1).build());
            }
            session.setAttribute("carts", carts);
            response.sendRedirect("ShopController");
        }catch(Exception e){
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
