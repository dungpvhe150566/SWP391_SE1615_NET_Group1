<%-- 
    Document   : userprofile
    Created on : Jun 14, 2022, 9:01:16 PM
    Author     : Dung
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <!-- Favicon -->
        <link href="img/favicon.ico" rel="icon">
        <!-- Font Awesome -->
        <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.10.0/css/all.min.css" rel="stylesheet">
        <!-- Libraries Stylesheet -->
        <link href="lib/owlcarousel/assets/owl.carousel.min.css" rel="stylesheet">
        <!-- Customized Bootstrap Stylesheet -->
        <link href="css/style.css" rel="stylesheet">
        <!-- fontawesome.com -->
        <script src="https://kit.fontawesome.com/bdc207907f.js" crossorigin="anonymous"></script>
    <body>
        <!-- Topbar Start -->
        <div class="container-fluid">
            <div class="row bg-secondary py-2 px-xl-5">
                <div class="col-lg-6 d-none d-lg-block">
                    <div class="d-inline-flex align-items-center">
                        <a class="text-dark" href="dontrollner-dasboard">Admin</a>
                        <span class="text-muted px-2">|</span>
                        <a class="text-dark" href="">Help</a>
                        <span class="text-muted px-2">|</span>
                        <a class="text-dark" href="">Support</a>
                    </div>
                </div>
                <div class="col-lg-6 text-center text-lg-right">
                    <div class="d-inline-flex align-items-center">
                        <a class="text-dark px-2" href="">
                            <i class="fab fa-facebook-f"></i>
                        </a>
                        <a class="text-dark px-2" href="">
                            <i class="fab fa-twitter"></i>
                        </a>
                        <a class="text-dark px-2" href="">
                            <i class="fab fa-linkedin-in"></i>
                        </a>
                        <a class="text-dark px-2" href="">
                            <i class="fab fa-instagram"></i>
                        </a>
                        <a class="text-dark pl-2" href="">
                            <i class="fab fa-youtube"></i>
                        </a>
                    </div>
                </div>
            </div>
            <div class="row align-items-center py-3 px-xl-5">
                <div class="col-lg-3 d-none d-lg-block">
                    <a href="HomeController" class="text-decoration-none">
                        <h1 class="m-0 display-5 font-weight-semi-bold"><span class="text-primary font-weight-bold border px-3 mr-1">E</span>Shopper</h1>
                    </a>
                </div>
                <div class="col-lg-6 col-6 text-left">
                    <!--Search By Name-->
                    <form action="ShopController" id="searchByProductName" method="post">
                        <input type="hidden" name="do" value="searchByName">
                        <div class="input-group">
                            <input type="text" name="productName" class="form-control" placeholder="${productName!=null? productName:' Search by name'}">
                            <div class="input-group-append">
                                <span class="input-group-text bg-transparent text-primary">
                                    <a onclick="document.getElementById('searchByProductName').submit();" ><i class="fa fa-search"></i></a> 
                                </span>
                            </div>
                        </div>
                    </form>
                </div>
                <c:if test="${sessionScope.user!=null}">
                    <div class="nav-item dropdown">
                        <a href="#" class="nav-link" data-toggle="dropdown">
                            <img src="image/Other/ava1.jpg" alt="Image" class="img-fluid mr-3 mt-1" style="width: 30px;">
                            ${sessionScope.user.getUsername()}</a>
                        <div class="dropdown-menu rounded-0 m-0">
                            <a href="" class="dropdown-item"><i class="fas fa-id-card"></i> YOUR PROFILE</a>
                            <a href="logout" class="dropdown-item"><i class="fas fa-sign-out-alt"></i> LOG OUT</a>
                        </div>
                    </div>
                    <a href="Cart" >
                        <i class="fas fa-shopping-cart text-primary"></i>
                        <span class="badge">${sessionScope.carts.size()}</span>
                    </a>
                    <div class="nav-item dropdown ">
                        <a href="" class="btn border nav-link" data-toggle="dropdown">
                            <i class="fas fa-bell text-primary"></i>
                            <span class="badge">0</span>
                        </a>
                        <div class="dropdown-menu rounded-0 m-0">
                            <p>Notification1</p>
                            <p>Notification2</p>
                            <p>Notification3</p>
                        </div>
                    </div>
                </c:if>
                <c:if test="${sessionScope.user==null}">
                    <div class="col-lg-3 col-6 text-right">
                        <a href="Cart">
                            <i class="fas fa-shopping-cart text-primary"></i>
                            <span class="badge">${sessionScope.carts.size()}</span>
                        </a>
                    </div>
                </c:if>
            </div>
        </div>
        <!-- Topbar End -->

        <div class="pt-2" style="background-color: #f5f5f5">
            <div class="container mt-3">
                <div class="main-body">
                    <div class="row gutters-sm">


                        <div class="col-md-9 mb-3">
                            <form action="editO" method="post">
                                <ul class="list-group list-group-flush">
                                    <li class="list-group-item">
                                        <div class="row">
                                            <div class="col-md-12 my-auto">
                                                <h5 class="mb-0">Edit order</h5>
                                            </div>

                                    <li class="list-group-item">
                                        <div class="row  mb-3 mt-3">


                                            <div class="row mt-3">
                                                <div class="col-md-4 text-right my-auto">
                                                    <span>Id</span>
                                                </div>
                                                <div class="col-md-8" style="color: black;">
                                                    <input class="form-control" type="text" name="id" value="${id}" readonly="">
                                                </div>
                                            </div>
                                            <div class="row mt-3">
                                                <div class="col-md-4 text-right my-auto">
                                                    <span>Total</span>
                                                </div>
                                                <div class="col-md-8" style="color: black;">
                                                    <input class="form-control" type="text" name="total" value="${total}">
                                                </div>
                                            </div>
                                            <div class="row mt-3">
                                                <div class="col-md-4 text-right my-auto">
                                                    <span>Note</span>
                                                </div>
                                                <div class="col-md-8" style="color: black;">
                                                    <input class="form-control" type="text" name="note" value="${note}">
                                                </div>
                                            </div>
                                            <div class="row mt-3">
                                                <div class="col-md-4 text-right my-auto">
                                                    <span>Status</span>
                                                </div>
                                                <div class="col-md-8" style="color: black;">
                                                    <input class="form-control" type="text" name="status" value="${status}" >
                                                </div>
                                            </div>
                                            <!-- Add more information here!-->
                                            <div class="row mt-3">
                                                <div class="col-md-8" style="color: black;">
                                                    
                                                    <button type="submit" class="btn btn-primary">Save</button>
                                                </div>
                                            </div>
                                        </div>
                                    </li>
                                </ul>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </body>

    <!-- JavaScript Libraries -->
    <script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.bundle.min.js"></script>
    <script src="lib/easing/easing.min.js"></script>
    <script src="lib/owlcarousel/owl.carousel.min.js"></script>
    <!-- Contact Javascript File -->
    <script src="mail/jqBootstrapValidation.min.js"></script>
    <script src="mail/contact.js"></script>
    <!-- Template Javascript -->
    <script src="js/main.js"></script>
</html>
