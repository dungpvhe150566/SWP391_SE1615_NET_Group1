<%-- 
    Document   : Order Details
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
                        <div class="col-md-3 mb-3">
                            <div class="card">
                                <div class="card-body">
                                    <div class="d-flex flex-column align-items-center text-center">
                                        <img src="https://bootdey.com/img/Content/avatar/avatar7.png" alt="Admin" class="rounded-circle" width="150">
                                        <div class="mt-3">
                                            <h4>${user.getUsername()}</h4>
                                            <p class="text-secondary mb-1">Full Stack Developer</p>
                                            <p class="text-muted font-size-sm">Bay Area, San Francisco, CA</p>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="card mt-3">
                                <ul class="list-group list-group-flush">
                                    <li class="list-group-item d-flex justify-content-between align-items-center flex-wrap">
                                        <div class="row">
                                            <div class="col-md-3">
                                                <i class="fas fa-user" style="color: black"></i>
                                            </div>
                                            <div class="col-md-9">
                                                <h6 class="mb-0">User Profile</h6>
                                            </div>
                                        </div>
                                    </li>
                                    <li class="list-group-item d-flex justify-content-between align-items-center flex-wrap">
                                        <div class="row">
                                            <div class="col-md-3">
                                                <i class="fas fa-address-book" style="color: black"></i>
                                            </div>
                                            <div class="col-md-9" onclick="window.location.href = 'myaddress'">
                                                <h6 class="mb-0">User Address</h6>
                                            </div>
                                        </div>
                                    </li>
                                    <li class="list-group-item d-flex justify-content-between align-items-center flex-wrap active">
                                        <div class="row">
                                            <div class="col-md-3">
                                                <i class="fas fa-clipboard-list" style="color: black"></i>
                                            </div>
                                            <div class="col-md-9" onclick="window.location.href = 'myorder'">
                                                <h6 class="mb-0">My Orders</h6>
                                            </div>
                                        </div>
                                    </li>
                                    <li class="list-group-item d-flex justify-content-between align-items-center flex-wrap">
                                        <div class="row">
                                            <div class="col-md-3">
                                                <i class="fas fa-clipboard-list" style="color: black"></i>
                                            </div>
                                            <div class="col-md-9" onclick="window.location.href = 'mynotify'">
                                                <h6 class="mb-0">Notification</h6>
                                            </div>
                                        </div>
                                    </li>
                                </ul>
                            </div>
                        </div>

                        <div class="col-md-9">
                            <div id="orderList">
                                <div class="rounded-top border-top pl-5 pr-5 pt-3 pb-1" style="background-color: #fffefb">
                                    <c:if test="${order.getStatus() == 4}">
                                        <div class="d-flex flex-row justify-content-between align-items-center align-content-center">
                                            <span class="dot"></span>
                                            <hr class="flex-fill track-line"><span
                                                class="d-flex justify-content-center align-items-center big-dot dot">
                                                <i class="fa fa-ban text-white"></i></span>
                                        </div>
                                        <div class="d-flex flex-row justify-content-between align-items-center">
                                            <div class="d-flex flex-column align-items-start">
                                                <span>Waiting</span>
                                            </div>
                                            <div class="d-flex flex-column justify-content-center">
                                                <span></span>
                                            </div>
                                            <div class="d-flex flex-column justify-content-center align-items-center">
                                                <span></span></div>
                                            <div class="d-flex flex-column align-items-end">
                                                <span>Canceled</span>
                                            </div>
                                        </div>
                                    </c:if>

                                    <c:if test="${order.getStatus() != 4}">
                                        <div class="d-flex flex-row justify-content-between align-items-center align-content-center">
                                            <span class="dot"></span>
                                            <hr class="flex-fill ${order.getStatus() >= 2 ? "track-line":""}"><span class="${order.getStatus() >= 2 ? "dot":""}"></span>
                                            <hr class="flex-fill ${order.getStatus() >= 3 ? "track-line":""}"><span class="${order.getStatus() >= 3 ? "dot":""}"></span>
                                            <hr class="flex-fill ${order.getStatus() >= 5 ? "track-line":""}"><span
                                                class="d-flex justify-content-center align-items-center ${order.getStatus() >= 5 ? "dot big-dot":""}">
                                                <i class="fa fa-check text-white"></i></span>
                                        </div>

                                        <div class="d-flex flex-row justify-content-between align-items-center">
                                            <div class="d-flex flex-column align-items-start">
                                                <span>Waiting</span>
                                            </div>
                                            <div class="d-flex flex-column justify-content-center">
                                                <span>Packaging</span>
                                            </div>
                                            <div class="d-flex flex-column justify-content-center align-items-center">
                                                <span>Delivering</span></div>
                                            <div class="d-flex flex-column align-items-end">
                                                <span>Completed</span>
                                            </div>
                                        </div>
                                    </c:if>
                                </div>

                                <div class="rounded-top border-top pl-5 pr-5 pt-3 pb-1" style="background-color: #fffefb">
                                    <div class="row  mb-3 mt-3">
                                        <div class="col-md-12">
                                            <div class="row">
                                                <div class="col-md-4 text-right">
                                                    <span>Name</span>
                                                </div>
                                                <div class="col-md-8" style="color: black;">${shipInfo.getCustomerName()}</div>
                                            </div>
                                            <br>
                                            <div class="row">
                                                <div class="col-md-4 text-right">
                                                    <span>Phone</span>
                                                </div>
                                                <div class="col-md-8" style="color: black;">${shipInfo.getPhoneNum()}</div>
                                            </div>
                                            <div class="row">
                                                <div class="col-md-4 text-right">
                                                    <span>Address</span>
                                                </div>
                                                <div class="col-md-8" style="color: black;">${shipInfo.getShippingAddress()}</div>
                                            </div>
                                        </div>
                                    </div>
                                </div>

                                <div class="pb-1" style="background-color: #ffffff">
                                    <ul class="list-group list-group-flush ml-2 mr-2">
                                        <li class="list-group-item">
                                            <p class="my-auto">${vecOrderStatus[order.getStatus()-1].getName()}</p>
                                        </li>
                                        <c:forEach items="${order.getOrderDetail()}" var="orderDetail">
                                            <li class="list-group-item">
                                                <span class="row">
                                                    <div class="row col-md-11 my-auto">
                                                        <div class="col-md-2 my-auto">
                                                            <img src="https://bootdey.com/img/Content/avatar/avatar7.png" alt="Admin" class="border" width="100%">
                                                        </div>
                                                        <div class="col-md-10 pl-0">
                                                            <span>${orderDetail.getProductName()}</span>
                                                            <div>x${orderDetail.getQuantity()}</div>
                                                        </div>
                                                    </div>
                                                    <div class="col-md-1 my-auto">
                                                        <span class="">${orderDetail.getProductPrice()}</span>
                                                    </div>
                                                </span>
                                            </li>
                                        </c:forEach>
                                    </ul>
                                </div>
                                <div class="rounded-top border-top mb-3" style="background-color: #fffefb">
                                    <div class="text-right mt-3 mb-3">
                                        <span class="mr-3">Total Price: d ${order.getTotalPrice()}</span>
                                    </div>
                                    <div class="text-right">
                                        <div class="btn-group mb-3 mt-2" role="group" aria-label="Basic example">
                                            <button type="button" class="btn btn-secondary btn-lg mr-3">Feedback</button>
                                            <button type="button" class="btn btn-primary btn-lg mr-3">Buy Again</button>
                                        </div>
                                    </div>
                                </div>
                                <input id="statusID" type="hidden" value="${statusID}">
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <%@include file="components/Footer.jsp" %>
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

    <style>
        .track-line {
            height: 2px !important;
            background-color: #488978;
            opacity: 1;
        }

        .dot {
            height: 10px;
            width: 10px;
            margin-left: 3px;
            margin-right: 3px;
            margin-top: 0px;
            background-color: #488978;
            border-radius: 50%;
            display: inline-block
        }

        .big-dot {
            height: 25px;
            width: 25px;
            margin-left: 0px;
            margin-right: 0px;
            margin-top: 0px;
            background-color: #488978;
            border-radius: 50%;
            display: inline-block;
        }

        .big-dot i {
            font-size: 12px;
        }

        .card-stepper {
            z-index: 0
        }
    </style>

    <script>
                                                var isLoading = true;

                                                function selectPage(page) {
                                                    window.location.href = "myorder?page=" + page;
                                                }

                                                function search(sortBy, page, status) {
                                                    var date = document.getElementById('date').value;
                                                    window.location.href = "myorder?page=" + page + "&status=" + status + "&sortby=" + sortBy + "&date=" + date;
                                                }

                                                function loadData() {
                                                    document.getElementById('lds-ellipsis').style.display = "none";
                                                    var statusID = document.getElementById('statusID').value;
                                                    $.ajax({
                                                        type: "GET",
                                                        url: "Update/myorder",
                                                        data: {
                                                            isAjax: "1",
                                                            status: statusID
                                                        },
                                                        success: function (responseText) {
                                                            var row = document.getElementById('orderList');
                                                            if (responseText.length <= 0)
                                                                isLoading = false;
                                                            row.innerHTML += responseText;
                                                        }
                                                    });
                                                }

                                                window.onscroll = function (ev) {
                                                    if ((window.innerHeight + window.scrollY) >= document.body.offsetHeight) {
                                                        if (isLoading) {
                                                            document.getElementById("lds-ellipsis").style.display = "inline-block";
                                                            setTimeout(loadData, 1000);
                                                        }
                                                    }
                                                };
    </script>
</html>
