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
                        <div class="col-md-9">
                            <div id="orderList"> 
                                <div class="col-12 tm-block-col">
                                    <div class="tm-bg-primary-dark tm-block tm-block-taller tm-block-scroll">
                                        <h2 class="tm-block-title">Orders List</h2>
                                        <form action="searchO">
                                            <input type="text" name="total">
                                            <input type="submit" value="Search">
                                        </form>
                                        <table class="table">
                                            <tr>
                                                <td>Id</td>
                                                <td>UserID</td>
                                                <td>TotalPrice</td>
                                                <td>Note</td>
                                                <td>Status</td>
                                                <td>DayBuy</td>
                                                <td>Details</td>
                                                <td>Edit</td>
                                                <td>Delete</td>
                                            </tr>
                                            <c:forEach items="${listO}" var="o">
                                                <tr>
                                                    <td>${o.ID}</td>
                                                    <td>${o.userID}</td>
                                                    <td>${o.totalPrice}</td>
                                                    <td>${o.note}</td>
                                                    <td>${o.status}</td>
                                                    <td>${o.dayBuy}</td>
                                                    <td><a href="orderdetail?id=${o.ID}">Order Details</a></td>
                                                    <td><a href="editO?id=${o.ID}&&userID=${o.userID}&&total=${o.totalPrice}&&note=${o.note}&&status=${o.status}">Edit</a></td>
                                                    <td><a href="deleteO?id=${o.ID}">Delete</a></td>
                                                </tr>
                                            </c:forEach>
                                        </table>
                                    </div>
                                </div>
                                <div id="content" class="col-11 justify-content-center">
                                    <nav aria-label="Page navigation example">
                                        <ul class="pagination">
                                            <c:if test="${tag > 1}">
                                                <li style="padding:10px" class="page-item disabled"><a href="order?index=${tag-1}">Previous</a></li>
                                                </c:if>
                                                <c:forEach begin="1" end="${endP}" var="i">  
                                                <li class="page-item"><a class="page-link ${tag == i?"active":""}" href="order?index=${i}"class="page-link">${i}</a></li>                                   
                                                </c:forEach>   
                                                <c:if test="${tag<endP}">
                                                <li style="padding:10px" class="page-item disabled"><a href="order?index=${tag+1}">Next</a></li>
                                                </c:if>
                                        </ul>
                                    </nav>
                                </div>
                                <input id="statusID" type="hidden" value="">
                            </div>
                            <div id="lds-ellipsis" class="lds-ellipsis"><div></div><div></div><div></div><div></div></div>
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
