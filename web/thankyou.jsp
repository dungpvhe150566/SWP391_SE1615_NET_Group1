<%-- 
   Document   : result
   Created on : Jan 14, 2022, 11:40:17 PM
   Author     : HP
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Thanks you</title>
        <meta charset="utf-8">
        <title>EShopper - Bootstrap Shop Template</title>
        <meta content="width=device-width, initial-scale=1.0" name="viewport">
        <meta content="Free HTML Templates" name="keywords">
        <meta content="Free HTML Templates" name="description">
        <!-- Favicon -->
        <link href="img/favicon.ico" rel="icon">
        <!-- Font Awesome -->
        <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.10.0/css/all.min.css" rel="stylesheet">
        <!-- Libraries Stylesheet -->
        <link href="lib/owlcarousel/assets/owl.carousel.min.css" rel="stylesheet">
        <!-- Customized Bootstrap Stylesheet -->
        <link href="css/style.css" rel="stylesheet">
        
    </head>
    <body>
         <%@include file="components/Navbar.jsp" %>
         <div  class="container-fluid">
             <div class="row">
                  <div class="col-xl-12 col-md-6 mb-4">
                            <div class="card border-left-primary shadow h-100 py-2">
                                <div class="card-body">
                                    <div class="row no-gutters align-items-center">
                                        <div class="col mr-2">
                                            <div class="text-xs font-weight-bold text-primary text-uppercase mb-1">
                                               <h2 class="text-xs font-weight-bold text-primary text-uppercase mb-1" style="text-align: center">The seems products , you are added</h2>  </div>
                                            <div class="h5 mb-0 font-weight-bold text-gray-800"></div>
                                        </div>
                                        <div class="col-auto">
                                            <a >
                                                <i class="fas fa-people-carry fa-2x text-gray-300"></i>
                                            </a>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
             </div>
         </div>
       
        <div class="col-lg-12 col-md-12">
            <div class="row pb-3"> 
              
        <c:forEach items="${products}" var="product">
            <div class="col-lg-4 col-md-6 col-sm-12 pb-1">
                <div class="card product-item border-0 mb-4">
                    <div class="card-header product-img position-relative overflow-hidden bg-transparent border p-0">
                        <img class="img-fluid w-100" src="image/${product.getImageLink()}" alt="">
                    </div>
                    <div class="card-body border-left border-right text-center p-0 pt-4 pb-3">
                        <h6 class="text-truncate mb-3">${product.getProductName()}</h6>
                        <div class="d-flex justify-content-center">
                            <h6>${product.getOriginalPrice()}</h6><h6 class="text-muted ml-2"><del>${product.getSellPrice()}</del></h6>
                        </div>
                    </div>
                    <div class="card-footer d-flex justify-content-between bg-light border">
                        <a href="thank?do=ViewDetail&categoryID=${product.getCategoryID()}&productID=${product.getProductID()}" class="btn btn-sm text-dark p-0"><i class="fas fa-eye text-primary mr-1"></i>View Detail</a>
                        <a href="addtocart?productId=${product.getProductID()}" class="btn btn-sm text-dark p-0"><i class="fas fa-shopping-cart text-primary mr-1"></i>Add To Cart</a>
                    </div>
                </div>
            </div>
        </c:forEach>
        <!--                        Pagination ProductList -->
        <div class="col-12 pb-1">
            <nav aria-label="Page navigation">
                <ul class="pagination justify-content-center mb-3">
                    <li class="page-item ${indexPage==1?'disabled':''}">
                        <a class="page-link" href="thank?do=${service}&indexPage=${indexPage-1}&productName=${productName}&categoryID=${categoryID}&listPrices=${listPrices}&manufacturers=${manufacturers}&sort=${sort}" aria-label="Previous">
                            <span aria-hidden="true">&laquo;</span>
                            <span class="sr-only">Previous</span>
                        </a>
                    </li>
                    <c:forEach var="i" begin="1" end="${totalPage}"  >
                        <li class="page-item ${indexPage==i?'active':''}">
                            <a class="page-link" href="thank?do=${service}&indexPage=${i}&productName=${productName}&categoryID=${categoryID}&listPrices=${listPrices}&manufacturers=${manufacturers}&sort=${sort}">${i}</a></li>
                        </c:forEach>
                    <li class="page-item ${indexPage==totalPage?'disabled':''}">
                        <a class="page-link" href="thank?do=${service}&indexPage=${indexPage+1}&productName=${productName}&categoryID=${categoryID}&listPrices=${listPrices}&manufacturers=${manufacturers}&sort=${sort}" aria-label="Next">
                            <span aria-hidden="true">&raquo;</span>
                            <span class="sr-only">Next</span>
                        </a>
                    </li>
                </ul>
            </nav>
        </div>
       <a style="margin:auto;display:block" href ="ShopController">
                    <div class="card-footer border-secondary bg-transparent">
                        <button style="text-align: center"type="submit" class="btn btn-lg btn-block btn-primary font-weight-bold my-3 py-3">Back To Shopping</button>
                    </div>
                </a>

    </div>
                            </div>
</div>
<!-- Shop Product End -->
</div>
</div>
        </div>
      
<!-- Shop End -->
<!-- Footer Start -->
<%@include file="components/Footer.jsp" %>
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
</body>
</html>
