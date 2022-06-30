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
                <div class="col-sm-12 col-md-12 col-lg-9 col-xl-9 tm-block-col">
                    <div class="tm-bg-primary-dark tm-block tm-block-products" style="min-height: 900px">
                        <h2 class="tm-block-title">Product List</h2>
                        <div class="form-outline mb-3">
                            <div class="row">
                                <div class="col-xl-10 col-lg-10 col-md-12">
                                    <input class="form-control" type="text" id="myInput" placeholder="Search for names.." value="${name}">
                                </div>
                                <div class="col-xl-2 col-lg-2 col-md-12">
                                    <button onclick="selectPage(${page}, ${categoryID})" class="btn-primary" style="width: 100%; height:100%;">Search</button>
                                </div>
                            </div>
                        </div>
                        <form id="products" actiopn="ProductsController" method="POST">
                            <div class="tm-product-table-container">
                                <input type="hidden" value="deleteProducts" name="do">
                                <table id="sortTable" class="table table-hover tm-table-small tm-product-table">
                                    <thead>
                                        <tr>
                                            <th scope="col">&nbsp;</th>
                                            <th scope="col">Product Name</th>
                                            <th scope="col">Sell Price (VND)</th>
                                            <th scope="col">Original Price (VND)</th>
                                            <th scope="col">Quantity</th>
                                            <th scope="col">&nbsp;</th>
                                        </tr>
                                    </thead>
                                    <tbody class="product-tb">
                                        <c:forEach items="${productList}" var="product">
                                            <tr class="product-tr">
                                                <th scope="row"><input type="checkbox" value="${product.getProductID()}" name="productID"></th>
                                                <td class="tm-product-name" onclick="editProduct(${product.getProductID()})">${product.getProductName()}</a></td>
                                                <td>${product.getSellPrice()}</td>
                                                <td>${product.getOriginalPrice()}</td>
                                                <td>${product.getAmount()}</td>
                                                <td>
                                                    <a onclick="return deleteProduct(${product.getProductID()})" class="tm-product-delete-link">
                                                        <i class="far fa-trash-alt tm-product-delete-icon"></i>
                                                    </a>
                                                </td>
                                            </tr>
                                        </c:forEach>
                                    </tbody>
                                </table>

                            </div>

                            <ul class="pagination pagination-sm justify-content-center">
                                <li class="page-item mr-0"><a class="page-link" onclick="selectPage(${page > 1 ? page - 1:page}, ${categoryID})">Previous</a></li>
                                    <c:forEach items="${pageNumbers}" var="pageNum">
                                    <li class="page-item mr-0 ${page.equals(pageNum) ? "active":""}"><a class="page-link" onclick="selectPage(${pageNum}, ${categoryID})">${pageNum}</a></li>
                                        <c:set var="lastPage" value="${pageNum}"></c:set>
                                    </c:forEach>
                                <li class="page-item mr-0"><a class="page-link" onclick="selectPage(${page == lastPage ? page:page+1}, ${categoryID})">Next</a></li>
                            </ul>
                            <!-- table container -->
                            <a
                                href="addproduct"
                                class="btn btn-primary btn-block text-uppercase mb-3 mt-5">Add new product
                            </a>
                            <a class="btn btn-primary btn-block text-uppercase" onclick="return deleteProducts()">
                                Delete selected products
                            </a>
                        </form>
                    </div>

                </div>
                <div class="col-sm-12 col-md-12 col-lg-3 col-xl-3 tm-block-col">
                    <div class="tm-bg-primary-dark tm-block tm-block-product-categories">
                        <h2 class="tm-block-title">Product Categories</h2>

                        <div class="tm-product-table-container">
                            <table class="table tm-table-small tm-product-table">
                                <tbody>
                                    <tr onclick="searchCategory(0)">
                                        <td>All</td>
                                    </tr>
                                    <c:forEach items="${categoryList}" var="category">
                                        <tr onclick="searchCategory(${category.getCategoryID()})">
                                            <td>${category.getCategoryName()}</td>
                                        </tr>
                                    </c:forEach>
                                </tbody>
                            </table>
                        </div>
                        <!-- table container -->
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

        <script>
                                                function searchName() {
                                                    let input = document.getElementById('myInput').value;
                                                    input = input.toLowerCase();
                                                    let x = document.getElementsByClassName('product-tr');

                                                    for (i = 0; i < x.length; i++) {
                                                        if (!x[i].getElementsByTagName("td")[0].innerHTML.toLowerCase().includes(input)) {
                                                            x[i].style.display = "none";
                                                        } else {
                                                            x[i].style.display = "";
                                                        }
                                                    }
                                                }

                                                function searchCategory(categoryID) {
                                                    window.location.href = "ProductsController?CategoryID=" + categoryID;
                                                }

                                                function deleteProduct(productID) {
                                                    if (confirm("Are you sure you want to delete? \nThis will delete all data related to the product.")) {
                                                        window.location.href = "ProductsController?do=deleteProduct&productID=" + productID;
                                                    } else {
                                                        return false;
                                                    }
                                                }

                                                function editProduct(productID) {
                                                    window.location.href = "editproduct?productID=" + productID;
                                                }

                                                function deleteProducts() {
                                                    if (confirm("Are you sure you want to delete? \nThis will delete all data related to the product.")) {
                                                        document.getElementById('products').submit();
                                                    } else {
                                                        return false;
                                                    }
                                                }

                                                function selectPage(page, categoryID) {
                                                    let name = document.getElementById('myInput').value;
                                                    window.location.href = "ProductsController?page=" + page + "&CategoryID=" + categoryID + "&name=" + name;
                                                }
        </script>
    </body>
</html>