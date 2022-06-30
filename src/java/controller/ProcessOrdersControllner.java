/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dao.impl.OrdersDAOImpl;
import dao.impl.ProductDAOImpl;
import entity.Orders;
import entity.Product;
import entity.Users;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import util.NumberHelper;

/**
 *
 * @author Pham Van Trong
 */
@WebServlet(name = "ProcessOrdersControllner", urlPatterns = {"/ProcessOrders"})
public class ProcessOrdersControllner extends HttpServlet {

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
            Users account = (Users) request.getSession().getAttribute("account");
//            List<Product> listProduct = new ProductDAOImpl().getAll();
            int check = NumberHelper.getInt(request.getParameter("check"));
             String keyword = request.getParameter("key");
            List<Orders> listOrderWatting = null;
//            if (check == 0) {
//                listOrderWatting = new OrdersDAOImpl().getAllSucces();
//            } else {
//                if (check == 1) {
//                    listOrderWatting = new OrdersDAOImpl().getAllOrderNotAcceptYet();
//                } else if(check == 2) {
//                    listOrderWatting = new OrdersDAOImpl().getAllOrderShipping();
//                } else{
//                     listOrderWatting = new OrdersDAOImpl().getAllOrderPackaging();
//                    }
//            }
             listOrderWatting = new OrdersDAOImpl().search(keyword);
            if (check == 0) {
                listOrderWatting = new OrdersDAOImpl().getAllSucces();
            }
            if (check == 1) {
                listOrderWatting = new OrdersDAOImpl().getAllOrderNotAcceptYet();
            }
            if (check == 2) {
                listOrderWatting = new OrdersDAOImpl().getAllOrderShipping();
            }
            if (check == 3) {
                listOrderWatting = new OrdersDAOImpl().getAllOrderPackaging();
            }
           
               
            
             
          
             
            request.setAttribute("key", keyword);
           
            request.setAttribute("listOrderWatting", listOrderWatting);
            request.setAttribute("check", check);
            request.getRequestDispatcher("processOrder.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
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
