<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
        <meta http-equiv="X-UA-Compatible" content="ie=edge" />
        <title>Product Page - Admin HTML Template</title>
        <link
            rel="stylesheet"
            href="https://fonts.googleapis.com/css?family=Roboto:400,700"
            />
        <!-- https://fonts.google.com/specimen/Roboto -->
        <link rel="stylesheet" href="css/fontawesome.min.css" />
        <!-- https://fontawesome.com/ -->
        <link rel="stylesheet" href="css/bootstrap.min.css" />
        <!-- https://getbootstrap.com/ -->
        <link rel="stylesheet" href="css/templatemo-style.css">
        <!--
            Product Admin CSS Template
            https://templatemo.com/tm-524-product-admin
        -->
        <!--Datatables-->
    </head>

    <body id="reportsPage" >
        <%@include file="components/NavbarAdmin.jsp" %>
        <div class="container mt-5">
            <div class="row tm-content-row">
                <div class="col-sm-12 col-md-12 col-lg-12 col-xl-12 tm-block-col">
                    <div class="tm-bg-primary-dark tm-block tm-block-products" style="min-height: 900px">
                        <h2 class="tm-block-title">Order List</h2>
                        <div class="form-outline mb-3">
                            <div class="row">
                                <form action="searchO">
                                <div class="col-xl-10 col-lg-10 col-md-12">
                                    <input class="form-control" type="text" name="total" placeholder="Search for names..">
                                </div>
                                <div class="col-xl-2 col-lg-2 col-md-12">
                                    <button type="submit">Search</button>
                                </div>
                                </form>
                            </div>
                        </div>
                        <form id="products" actiopn="ProductsController" method="POST">
                            <div class="tm-product-table-container">
                                <input type="hidden" value="deleteBlogs" name="do">
                                <table class="table table-hover tm-table-small tm-product-table">
                                    <thead>
                                        <tr>
                                            <td>Id</td>
                                            <td>TotalPrice</td>
                                            <td>Note</td>
                                            <td>Status</td>
                                            <td>Details</td>
                                            <td>Edit</td>
                                            <td>Delete</td>
                                        </tr>
                                    </thead>
                                    <tbody class="product-tb">
                                        <c:forEach items="${listO}" var="o">
                                            <tr class="product-tr">
                                                <th>${o.ID}</th>
                                                <td>${o.totalPrice}</td>
                                                <td>${o.note}</td>
                                                <td>${o.status}</td>
                                                <td><a href="orderdetail?id=${o.ID}">Order Details</a></td>
                                                <td><a href="editO?id=${o.ID}&&userID=${o.userID}&&total=${o.totalPrice}&&note=${o.note}&&status=${o.status}">Edit</a></td>
                                                <td>
                                                    <a href="deleteO?id=${o.ID}" class="tm-product-delete-link">
                                                        <i class="far fa-trash-alt tm-product-delete-icon"></i>
                                                    </a>
                                                </td>
                                            </tr>
                                        </c:forEach>
                                    </tbody>
                                </table>

                            </div>
                            <!-- table container -->

                        </form>
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

        <script src="js/jquery-3.3.1.min.js"></script>
        <!-- https://jquery.com/download/ -->
        <script src="js/bootstrap.min.js"></script>
        <!-- https://getbootstrap.com/ -->

        <style>
            th { white-space: nowrap; overflow: hidden; text-overflow:ellipsis; }
        </style>

    </body>
</html>