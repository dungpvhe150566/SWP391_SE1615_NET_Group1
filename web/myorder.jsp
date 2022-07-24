<%-- 
    Document   : My Order
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
                            <a href="profile" class="dropdown-item"><i class="fas fa-id-card"></i> YOUR PROFILE</a>
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
                                                <i class="fas fa-address-book" style="color: black"></i>
                                            </div>
                                            <div class="col-md-9" onclick="window.location.href = 'profile'">
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
                            <ul class="mb-3 nav nav-tabs justify-content-center nav-pills nav-fill" style="background-color: #ffffff;">
                                <li class="nav-item" style="width: 14%">
                                    <a href="myorder?page=${page}&status=0&date=${date}" class="nav-link ${statusID == 0 ? "active":""}">All</a>
                                </li>
                                <c:forEach items="${vecOrderStatus}" var="status">
                                    <li class="nav-item">
                                        <a href="myorder?page=${page}&status=${status.getID()}&date=${date}" class="nav-link ${status.getID() == statusID ? "active":""}">${status.getName()}</a>
                                    </li>
                                </c:forEach>
                            </ul>

                            <c:if test="${statusID == 0}">
                                <div class="input-group mb-3">
                                    <input type="date" name="date" id="date" class="form-control" value="${date}">
                                    <div class="input-group-append" onclick="search('',1,0)">
                                        <span class="input-group-text text-primary">
                                            <a><i class="fa fa-search"></i></a> 
                                        </span>
                                    </div>
                                </div>
                            </c:if>

                            <c:if test="${fn:length(vecOrder) == 0}">
                                <div class="row ml-0" style="background-color: #ffffff; width: 100%; height:85%">
                                    <div class="my-auto mx-auto" style="height: 25%">
                                        <img src="image/Other/order.png" alt="order" style="max-height:100%; max-width:100%;">
                                        <div class="text-center">
                                            <span>No orders yet</span>
                                        </div>
                                    </div>
                                </div>
                            </c:if>
                            <div id="orderList"> 
                                <c:forEach items="${vecOrder}" var="order">
                                    <div class="pb-1" style="background-color: #ffffff">
                                        <ul class="list-group list-group-flush ml-2 mr-2">
                                            <li class="list-group-item">
                                                <div class="row">
                                                    <div class="col-md-8">
                                                        <p class="my-auto">${vecOrderStatus[order.getStatus()-1].getName()}</p>
                                                    </div>
                                                    <div class="col-md-4">
                                                        <p class="my-auto text-right">${order.getDayBuy()}</p>
                                                    </div>
                                                </div>
                                            </li>
                                            <c:forEach items="${order.getOrderDetail()}" var="orderDetail">
                                                <li class="list-group-item" onclick="viewDetails(${order.getID()})">
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
                                </c:forEach>

                                <input id="statusID" type="hidden" value="${statusID}">
                            </div>
                            <div id="lds-ellipsis" class="lds-ellipsis"><div></div><div></div><div></div><div></div></div>
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
        .lds-ellipsis {
            display: none;
            position: relative;
            margin-left: 45%;
            width: 80px;
            height: 80px;
        }
        .lds-ellipsis div {
            position: absolute;
            top: 33px;
            width: 13px;
            height: 13px;
            border-radius: 50%;
            background: #d19c97;
            animation-timing-function: cubic-bezier(0, 1, 1, 0);
        }
        .lds-ellipsis div:nth-child(1) {
            left: 8px;
            animation: lds-ellipsis1 0.6s infinite;
        }
        .lds-ellipsis div:nth-child(2) {
            left: 8px;
            animation: lds-ellipsis2 0.6s infinite;
        }
        .lds-ellipsis div:nth-child(3) {
            left: 32px;
            animation: lds-ellipsis2 0.6s infinite;
        }
        .lds-ellipsis div:nth-child(4) {
            left: 56px;
            animation: lds-ellipsis3 0.6s infinite;
        }
        @keyframes lds-ellipsis1 {
            0% {
                transform: scale(0);
            }
            100% {
                transform: scale(1);
            }
        }
        @keyframes lds-ellipsis3 {
            0% {
                transform: scale(1);
            }
            100% {
                transform: scale(0);
            }
        }
        @keyframes lds-ellipsis2 {
            0% {
                transform: translate(0, 0);
            }
            100% {
                transform: translate(24px, 0);
            }
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
                                                        var date = document.getElementById('date').value;
                                                        var statusID = document.getElementById('statusID').value;
                                                        $.ajax({
                                                            type: "GET",
                                                            url: "Update/myorder",
                                                            data: {
                                                                isAjax: "1",
                                                                status: statusID,
                                                                date: date
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
                                                                setTimeout(loadData, 500);
                                                            }
                                                        }
                                                    }

                                                    function viewDetails(orderID) {
                                                        window.location.href = "myorderdetails?orderID=" + orderID;
                                                    }
    </script>
</html>
