<%-- 
    Document   : dasboard
    Created on : 04-06-2022, 14:52:44
    Author     : Pham Van Trong
--%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta http-equiv="X-UA-Compatible" content="ie=edge">
        <title>Product Admin - Dashboard HTML Template</title>
        <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto:400,700">
        <!-- https://fonts.google.com/specimen/Roboto -->
        <link rel="stylesheet" href="css/fontawesome.min.css">
        <!-- https://fontawesome.com/ -->
        <link rel="stylesheet" href="css/bootstrap.min.css">
        <!-- https://getbootstrap.com/ -->
        <link rel="stylesheet" href="css/templatemo-style.css">
        <link
            href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i"
            rel="stylesheet">
        <!--
            Product Admin CSS Template
            https://templatemo.com/tm-524-product-admin
        --><link href="vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">

        <!-- Page level plugin CSS-->
        <link href="vendor/datatables/dataTables.bootstrap4.css" rel="stylesheet">

        <!-- Custom styles for this template-->
        <link href="css/sb-admin.css" rel="stylesheet">
    </head>
    <style>
        .pagination a.active {
            background-color: #4CAF50;
            color: white;
        }
        .pagination a:hover:not(.active) {
            background-color: #FFF44F;
            color: black;
        }
        .search-box{
            position: absolute;
            height: 20px;
            border-radius:40px;
            padding: 10px;
        }
        .search-box:hover > .search-txt{
            width: 180px;
            padding: 0 6px;
        }
        .search-box:hover > .search-btn{
            color: white
        }
        .search-btn{
            color:blue;
            width: 40px;
            height:40px;
            border-radius: 50%;
            transition: 0.4s;
        }
        .search-txt{
            border:none;
            background:whitesmoke;
            outline:none;
            padding:0;
            color:black;
            font-size: 16px;
            transition: 0.4s;
            line-height: 40px;
            width: 0px;
        }
        .form-control::-webkit-input-placeholder {
            color: white;
            width: 250px;
        }
        h1 {
            color: red;
        }
        .outputLI {
            text-align: center;
             color: #0069d9;
        
            }
    </style>
    <body id="reportsPage">

        <div class="" id="home">
            <%@include file="components/NavbarAdmin.jsp" %>

            <div class="container-fluid">
                <form action="ProcessOrders" class="d-flex mx-auto">
                    <input
                        class="form-control me-2"
                        type="search"
                        placeholder="Search"
                        aria-label="Search"
                        name="key"
                        style=" border: 3px solid #05728f;" required pattern="^[^ ].+[^ ]$"
                        />

                    <button class="btn btn-outline-info" type="submit">
                        Search
                    </button>
                </form>
            </div>
            <div class="col-12 tm-block-col">


                <div class="tm-bg-primary-dark tm-block tm-block-taller tm-block-scroll">
                    <h2 class="tm-block-title">Orders List Process</h2>

                    <table class="table">

                        <tr>

                            <td>Customer</td>
                            <td>phone</td>
                            <td>Address</td>
                            <td>TotalPrice (VND)</td>
                            <td>Date</td>
                            <td>Status</td>
                            <c:choose>
                                <c:when test="${check =='2' || check =='0' }">
                                </c:when>    
                                <c:otherwise>
                                    <th>Accept</th>
                                    </c:otherwise>
                                </c:choose>

                            <th>Delete</th>
                        </tr>
                       
                        <c:forEach items="${listOrderWatting}" var="order">
                            <tr>

                                <td>${order.getShipp().getCustomerName()}</td>
                                <td>${order.getShipp().getPhoneNum()}</td>
                                <td>${order.getShipp().getShippingAddress()}</td>
                                <td>${order.totalPrice} </td>
                                <td>${order.getDayBuy()}</td>
                                <td <c:if test="${order.getStatus() ne 1}">style="color: orange"</c:if> >${order.getOrderStatus().getName()}</td>
                                <c:choose>
                                    <c:when test="${check =='2' || check =='0' }">
                                    </c:when>    
                                    <c:when test="${check =='3'}">
                                        <td><a href="accept-orders?id=${order.getID()}" class="btn btn-outline-info">Accept</a></td>
                                    </c:when> 
                                    <c:otherwise>
                                        <td><a href="accept-order?id=${order.getID()}" class="btn btn-outline-info">Accept</a></td>
                                    </c:otherwise>
                                </c:choose>
                                <td><a  href="delete-order?id=${order.getID()}" onclick="return confirm('Are you sure you want to delete this item?');" class="btn btn-outline-danger"><i class="fas fa-trash mr-2"></i>Delete</a></td>
                            </tr>
                        </c:forEach>
                            
                    </table>
                     <c:choose>
                            <c:when test="${listOrderWatting==null || listOrderWatting.size()==0}">
                                <br>
                                <h5 class="outputLI">Not founds</h5>
                            </c:when>
                            <c:otherwise>

                            </c:otherwise>
                        </c:choose>
                </div>
            </div>
        </div>

    </div>
</div>
<footer class="tm-footer row tm-mt-small">
    <div class="col-12 font-weight-light">
        <p class="text-center text-white mb-0 px-4 small">
            Copyright &copy; <b>2018</b> All rights reserved. 

            Design: <a rel="nofollow noopener" href="https://templatemo.com" class="tm-footer-link">Template Mo</a>
        </p>
    </div>
</footer>
</div>
<script src="vendor/jquery/jquery.min.js"></script>
<script src="vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

<!-- Core plugin JavaScript-->
<script src="vendor/jquery-easing/jquery.easing.min.js"></script>

<!-- Page level plugin JavaScript-->
<script src="vendor/chart.js/Chart.min.js"></script>
<script src="vendor/datatables/jquery.dataTables.js"></script>
<script src="vendor/datatables/dataTables.bootstrap4.js"></script>

<!-- Custom scripts for all pages-->
<script src="js/sb-admin.min.js"></script>

<!-- Demo scripts for this page-->
<script src="js/demo/datatables-demo.js"></script>
<script src="js/jquery-3.3.1.min.js"></script>
<!-- https://jquery.com/download/ -->
<script src="js/moment.min.js"></script>
<!-- https://momentjs.com/ -->
<script src="js/Chart.min.js"></script>
<!-- http://www.chartjs.org/docs/latest/ -->
<script src="js/bootstrap.min.js"></script>
<!-- https://getbootstrap.com/ -->
<script src="js/tooplate-scripts.js"></script>
<script
    src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.9.4/Chart.js">
</script>
<script         src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>
<script src="vendor/jquery/jquery.min.js"></script>
<script src="vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

<!-- Core plugin JavaScript-->
<script src="vendor/jquery-easing/jquery.easing.min.js"></script>

<!-- Page level plugin JavaScript-->
<script src="vendor/datatables/jquery.dataTables.js"></script>
<script src="vendor/datatables/dataTables.bootstrap4.js"></script>

<!-- Custom scripts for all pages-->
<script src="js/sb-admin.min.js"></script>

<!-- Demo scripts for this page-->
<script src="js/demo/datatables-demo.js"></script>

</body>
</html>
