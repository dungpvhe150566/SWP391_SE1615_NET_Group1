/*
     Dashboard
 */
package controller;

import dao.StatisticalOrdersDAO;
import dao.ViewDao;
import dao.impl.CategoryDAOImpl;
import dao.impl.OrdersDAOImpl;
import dao.impl.ProductDAOImpl;
import dao.impl.UsersDAOImpl;
import entity.Category;
import entity.Orders;
import entity.Product;
import entity.Statistical;
import entity.Users;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Pham Van Trong
 */
@WebServlet(name = "ControllnerDasboard", urlPatterns = {"/dontrollner-dasboard"})
public class DasboardControllner extends HttpServlet {
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, Exception {
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {
             HttpSession session = request.getSession();
             List<Product> listProduct = new ProductDAOImpl().getProductList();
            List<Category> listCategorys = new CategoryDAOImpl().getAllCategory();
             int count = new ViewDao().getView();
             int count2 = new ProductDAOImpl().countProduct();
             int count3 = new  UsersDAOImpl().countAccount();
             request.setAttribute("view", count);
             // Statistical Orders in week 
             String thuu="";
             String dataa="";
             String yearString ="";   
             String monthString ="";
             List<Statistical> listThongKe = new StatisticalOrdersDAO().getAll();
             for (Statistical T : listThongKe) {
                thuu+="\""+T.getThu()+"\", ";
                dataa+=T.getNumOfOrder()+",";
                yearString+=T.getYear();
                monthString+=T.getMonth();
            }
             // Total Product
            String label="";
            String soluong="";
            List<Integer> soluongProduct = new ProductDAOImpl().countProductGroupByCategoryId();
            for (Category C : listCategorys) {
                label+="\""+C.getCategoryName()+"\",";
            }
            label.substring(0,label.length()-1);
            for (Integer integer : soluongProduct) {
                soluong+=(double)Math.round((((double)integer/count2)*100)*100)/100+",";
            }
            // First Revenue in 6 month of year
            List<Double> listRevenue = new ArrayList<>();
            for(int i=1;i<=6;i++){
                double a = new OrdersDAOImpl().calRevenueInMonth(i);
                listRevenue.add(a);
            }
            String revenue="";
            for (Double double1 : listRevenue) {
                revenue+=(double)Math.round(double1*100)/100+",";
            }
             int numOfOrder = new OrdersDAOImpl().countOrderWatting();
            request.setAttribute("numOfOrder", numOfOrder);
            revenue.substring(0, revenue.length()-1);
            soluong.substring(0, soluong.length()-1);
            request.setAttribute("monthString", monthString);
            request.setAttribute("yearString", yearString);
            request.setAttribute("revenue", revenue);
             request.setAttribute("label", label);
            request.setAttribute("soluong", soluong);
            request.setAttribute("account", count3);
            request.setAttribute("product", count2);
            request.setAttribute("view", count);
            request.setAttribute("thu", thuu);
            request.setAttribute("data", dataa);
           request.getRequestDispatcher("dasboard.jsp").forward(request, response);
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
        try {
            processRequest(request, response);
        } catch (Exception ex) {
            Logger.getLogger(DasboardControllner.class.getName()).log(Level.SEVERE, null, ex);
        }
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
            processRequest(request, response);
        } catch (Exception ex) {
            Logger.getLogger(DasboardControllner.class.getName()).log(Level.SEVERE, null, ex);
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
