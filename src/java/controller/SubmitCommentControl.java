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
import entity.CommentBlog;
import entity.Users;
import java.io.IOException;
import static java.lang.System.out;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * This class makes the user requirement to send a comment to a blog Comment for
 * a blog
 * <p>
 * Error: Error occurs will be received and processed and handled errors Page
 * <p>
 *
 * @Author haipm
 */
public class SubmitCommentControl extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("utf-8");

        try {
            BlogDAOImpl BlogDao = new BlogDAOImpl();
            //get BlogId from detail
            String BlogId = request.getParameter("BlogId");
            //get Blog from Id
            Blog p = BlogDao.getBlogByID(BlogId);
            //Set Blog to CommentBlog.jsp
            request.setAttribute("blog", p);
            request.getRequestDispatcher("CommentBlog.jsp").forward(request, response);

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
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("utf-8");

        try {

            // get current user account
            HttpSession session = request.getSession();
            Users currentAccount = (Users) session.getAttribute("user");

            // get BlogDAOImpl
            BlogDAOImpl blogDAO = new BlogDAOImpl();

            // get current blog id
            int BlogId = Integer.parseInt(request.getParameter("blogId"));
            System.out.println(BlogId);

            String Comment = request.getParameter("Comment-text");
            if (Comment.trim().compareTo("") == 0) {
                request.setAttribute("mess", "Please enter feedback detail");
                request.setAttribute("fb", Comment);
                BlogDAOImpl BlogDao = new BlogDAOImpl();
                //get Blog from Id
                Blog p = BlogDao.getBlogByID(BlogId+"");
                //Set Blog to CommentBlog.jsp
                request.setAttribute("blog", p);
                request.getRequestDispatcher("CommentBlog.jsp").forward(request, response);

            } else {
                //get current date
                java.util.Date utilDate = new java.util.Date();
                java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());

                // create comment
                CommentBlog userFeedback = new CommentBlog();
                userFeedback.setBlogID(BlogId);
                userFeedback.setUserID(currentAccount.getUserID());
                userFeedback.setUserName(currentAccount.getUsername());
                out.println(currentAccount.getUserID());
                userFeedback.setCommentDetail(Comment.trim());
                userFeedback.setDateComment(sqlDate);
                System.out.println(sqlDate);
                System.out.println(userFeedback);

                // add comment to database
                boolean addComment = blogDAO.addComment(userFeedback);

                // redirect to Home
                if (addComment) {//DetailBlogController?ID=1
                    request.getRequestDispatcher("DetailBlogController?ID=" + BlogId).forward(request, response);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("error.jsp");
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
