/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.impl.UsersDAOImpl;
import entity.SendEmail;
import entity.SendEmail1;
import entity.Users;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author viet
 */
@WebServlet(name = "RegisterController", urlPatterns = {"/register"})
public class RegisterController extends HttpServlet {

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
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet RegisterController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet RegisterController at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
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
            String email = request.getParameter("email");   //Lay email
            String username = request.getParameter("username").trim(); //Lay user
            String password = request.getParameter("password").trim(); //Lay password
            String repassword = request.getParameter("repassword");
            String text = "";
            UsersDAOImpl dao = new UsersDAOImpl();
            List<Users> list = dao.getAll();   //Lay ra list user
            for (Users users : list) {
                if (users.getEmail().equalsIgnoreCase(email)) { //Neu email da ton tai chuyen den trang login va bao loi
                    text = "The email already exits";
                    request.setAttribute("err", text);
                    request.setAttribute("email1", email);
                    request.setAttribute("username1", username);
                    request.getRequestDispatcher("login.jsp").forward(request, response);
                } else if (users.getUsername().equalsIgnoreCase(username)) { //Neu username ton tai chuyen den trang login va bao loi
                    text = "The username already exits";
                    request.setAttribute("err", text);
                    request.setAttribute("email1", email);
                    request.setAttribute("username1", username);
                    request.getRequestDispatcher("login.jsp").forward(request, response);
                }
            }
            if (!password.equalsIgnoreCase(repassword)) { //Neu mat khau va confirm mat khau khong match chuyen den login va bao loi
                text = "The password and repassword didn't match";
                request.setAttribute("err", text);
                request.setAttribute("email1", email);
                request.setAttribute("username1", username);
                request.getRequestDispatcher("login.jsp").forward(request, response);
            }
            try {
                SendEmail1 SE = new SendEmail1();  //Truong hop dang nhap thanh cong 
                SE.sendEmail(email); //Gui email
                HttpSession session = request.getSession(); //Day email,username,password len session
//            PrintWriter out = response.getWriter();
//            out.print(email+username+password);
                session.setAttribute("email2", email);
                session.setAttribute("username2", username);
                session.setAttribute("password", password);
                request.getRequestDispatcher("thank1.jsp").forward(request, response); //Chuyen den trang thankyou.jsp
            } catch (Exception e) {
                System.out.println(e);
            }
        } catch (Exception e) {
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
