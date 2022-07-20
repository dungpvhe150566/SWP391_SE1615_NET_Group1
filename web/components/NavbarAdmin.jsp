<%-- 
    Document   : NavbarAdmin
    Created on : 19-06-2022, 18:20:11
    Author     : Pham Van Trong
--%>
<%@page import="java.util.Map"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <script src="https://kit.fontawesome.com/bdc207907f.js" crossorigin="anonymous"></script>
    </head>
    <body>
        <nav class="navbar navbar-expand-xl">
            <div class="container h-100">
                <a class="navbar-brand" href="HomeController">
                    <h1 class="tm-site-title mb-0">Product Admin</h1>
                </a>
                <button
                    class="navbar-toggler ml-auto mr-0"
                    type="button"
                    data-toggle="collapse"
                    data-target="#navbarSupportedContent"
                    aria-controls="navbarSupportedContent"
                    aria-expanded="false"
                    aria-label="Toggle navigation"
                    >
                    <i class="fas fa-bars tm-nav-icon"></i>
                </button>

                <div class="collapse navbar-collapse" id="navbarSupportedContent">
                    <ul class="navbar-nav mx-auto h-100">
                        <li class="nav-item">
                            <a class="nav-link" href="dontrollner-dasboard">
                                <i class="fas fa-tachometer-alt"></i> Dashboard
                                <span class="sr-only">(current)</span>
                            </a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link " href="ProductsController">
                                <i class="fas fa-shopping-cart"></i> Products
                            </a>
                        </li>

                        <li class="nav-item">
                            <a class="nav-link" href="AccountManagerController">
                                <i class="far fa-user"></i> Accounts
                            </a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="ViewAllFeedbackController">
                                <i class="far fa-comment-dots"></i> Feedbacks
                            </a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="blogs">
                                <i class="fas fa-blog"></i> Blogs
                            </a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="order">
                                <i class="fa-thin"></i> Order
                            </a>
                        </li>
                        <li class="nav-item dropdown">
                            <a
                                class="nav-link dropdown-toggle"
                                href="#"
                                id="navbarDropdown"
                                role="button"
                                data-toggle="dropdown"
                                aria-haspopup="true"
                                aria-expanded="false">
                                <i class="fas fa-cog"></i>
                                <span> Settings <i class="fas fa-angle-down"></i> </span>
                            </a>
                            <div class="dropdown-menu" aria-labelledby="navbarDropdown">
                                <a class="dropdown-item" href="#">Profile</a>
                                <a class="dropdown-item" href="#">Billing</a>
                                <a class="dropdown-item" href="#">Customize</a>
                            </div>
                        </li>
                    </ul>
                    <c:if test="${sessionScope.user!=null}">
                       <ul class="navbar-nav">
                        <li class="nav-item">
                            <a class="nav-link d-block"  href="logout">
                                ${sessionScope.user.getUsername()}, <b>Logout</b>
                            </a>
                        </li>
                    </ul>
                    </c:if>
                    <c:if test="${sessionScope.user==null}">
                    <ul class="navbar-nav">
                        <li class="nav-item">
                            <a class="nav-link d-block" href="login.jsp">
                                Admin, <b>Login</b>
                            </a>
                        </li>
                    </ul>
                    </c:if>
                </div>
            </div>
        </nav>
    </body>
</html>
