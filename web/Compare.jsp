<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="en">

    <head>
        <meta charset="utf-8">
        <title>EShopper - Bootstrap Shop Template</title>
        <meta content="width=device-width, initial-scale=1.0" name="viewport">
        <meta content="Free HTML Templates" name="keywords">
        <meta content="Free HTML Templates" name="description">

        <!-- Favicon -->
        <link href="img/favicon.ico" rel="icon">

        <!-- Google Web Fonts -->
        <link rel="preconnect" href="https://fonts.gstatic.com">
        <link
            href="https://fonts.googleapis.com/css2?family=Poppins:wght@100;200;300;400;500;600;700;800;900&display=swap"
            rel="stylesheet">
        <!-- Font Awesome -->
        <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.10.0/css/all.min.css" rel="stylesheet">

        <!-- Libraries Stylesheet -->
        <link href="lib/owlcarousel/assets/owl.carousel.min.css" rel="stylesheet">

        <!-- Customized Bootstrap Stylesheet -->
        <link href="css/tableCompare.css" rel="stylesheet">
        <link href="css/style.css" rel="stylesheet">

        <script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    </head>

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
                        <h1 class="m-0 display-5 font-weight-semi-bold"><span
                                class="text-primary font-weight-bold border px-3 mr-1">E</span>Shopper</h1>
                    </a>
                </div>
                <div class="col-lg-6 col-6 text-left">
                    <form action="ShopController" id="searchByProductName" method="post">
                        <input type="hidden" name="do" value="searchByName">
                        <div class="input-group">
                            <input type="text" name="productName" class="form-control" placeholder="Search by name">
                            <div class="input-group-append">
                                <span class="input-group-text bg-transparent text-primary">
                                    <a onclick="document.getElementById('searchByProductName').submit();"><i
                                            class="fa fa-search"></i></a>
                                </span>
                            </div>
                        </div>
                    </form>
                </div>
                <c:if test="${sessionScope.user!=null}">
                    <div class="nav-item dropdown">
                        <a href="#" class="nav-link" data-toggle="dropdown">
                            <img src="image/Other/ava1.jpg" alt="Image" class="img-fluid mr-3 mt-1"
                                 style="width: 30px;">
                            ${sessionScope.user.getUsername()}</a>
                        <div class="dropdown-menu rounded-0 m-0">
                            <a href="" class="dropdown-item"><i class="fas fa-id-card"></i> YOUR PROFILE</a>
                            <a href="logout" class="dropdown-item"><i class="fas fa-sign-out-alt"></i> LOG OUT</a>
                        </div>
                    </div>
                    <a href="cart.jsp" class="btn border">
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
                        <a href="Cart" class="btn border">
                            <i class="fas fa-shopping-cart text-primary"></i>
                            <span class="badge">${sessionScope.carts.size()}</span>
                        </a>
                    </div>
                </c:if>
            </div>
        </div>
        <!-- Topbar End -->


        <!-- Navbar Start -->
        <div class="container-fluid mb-5">
            <div class="row border-top px-xl-5">
                <div class="col-lg-12">
                    <nav class="navbar navbar-expand-lg bg-light navbar-light py-3 py-lg-0 px-0">
                        <a href="" class="text-decoration-none d-block d-lg-none">
                            <h1 class="m-0 display-5 font-weight-semi-bold"><span
                                    class="text-primary font-weight-bold border px-3 mr-1">E</span>Shopper</h1>
                        </a>
                        <button type="button" class="navbar-toggler" data-toggle="collapse"
                                data-target="#navbarCollapse">
                            <span class="navbar-toggler-icon"></span>
                        </button>
                        <div class="collapse navbar-collapse justify-content-between" id="navbarCollapse">
                            <div class="navbar-nav mr-auto py-0">
                                <a href="HomeController" class="nav-item nav-link active">Home</a>
                                <a href="ShopController" class="nav-item nav-link">Shop</a>
                                <a href="contact.html" class="nav-item nav-link">Contact</a>
                                <a href="ViewAllFeedbackController" class="nav-item nav-link">Feedbacks Management</a>
                                <a href="AccountManagerController" class="nav-item nav-link">Account Management</a>

                            </div>
                            <div class="navbar-nav ml-auto py-0">
                                <c:if test="${sessionScope.user==null}">
                                    <a href="login.jsp" class="nav-item nav-link">Login</a>
                                    <a href="login.jsp" class="nav-item nav-link">Register</a>
                                </c:if>
                            </div>
                        </div>
                    </nav>
                    <div id="header-carousel" class="carousel slide" data-ride="carousel">
                        <div class="carousel-inner">
                            <div class="carousel-item" style="height: 410px;">
                                <img class="img-fluid" src="image/SlideShow/Intro1.jpg" alt="Image">
                            </div>
                            <div class="carousel-item active" style="height: 410px;">
                                <img class="img-fluid" src="image/SlideShow/Intro2.jpg" alt="Image">
                            </div>
                            <div class="carousel-item" style="height: 410px;">
                                <img class="img-fluid" src="image/SlideShow/Intro3.jpg" alt="Image">
                            </div>
                            <div class="carousel-item" style="height: 410px;">
                                <img class="img-fluid" src="image/SlideShow/Intro_final.jpg" alt="Image">
                            </div>
                        </div>
                        <a class="carousel-control-prev" href="#header-carousel" data-slide="prev">
                            <div class="btn btn-dark" style="width: 45px; height: 45px;">
                                <span class="carousel-control-prev-icon mb-n2"></span>
                            </div>
                        </a>
                        <a class="carousel-control-next" href="#header-carousel" data-slide="next">
                            <div class="btn btn-dark" style="width: 45px; height: 45px;">
                                <span class="carousel-control-next-icon mb-n2"></span>
                            </div>
                        </a>
                    </div>
                </div>
            </div>
        </div>
        <!-- Navbar End -->

        <!-- Categories Start -->
        <div class="container ">
            <div class="row"  style="margin-bottom: 50px;">
                <div class="col-md-12 col-sm-12" style="display:inline;">
                    <h3> Products Comparison: <small class="">&nbsp;&nbsp;&nbsp;&nbsp;Search for another product to compare </small></h3>	
                    <hr class=""/>
                    <table id="" class="compare" style="width:50%; float:left; border: 1px;">
                        <thead >
                            <tr>
                                <th></th>
                                <th> ${product.getProductName()} </th>
                            </tr>
                        </thead>
                        <tbody>
                            <tr>
                                <td>&nbsp;</td>
                                <td>
                                    <img  src="image/${product.getImageLink()}" alt="Product picture"/>
                                    <p><strong> ${product.getSellPrice()}VND</strong></p>
                                    <div class="action">
                                        <div  class="but">
                                            <a href="#">Add to cart</a>
                                        </div>
                                        <div class="but">
                                            <a href="ShopDetailController?do=ViewDetail&categoryID=${product.getCategoryID()}&productID=${product.getProductID()}">VIEW</a>
                                        </div>
                                    </div>
                                    </div>
                                </td>
                            </tr>                          
                            <tr>
                                <th>Height</th>
                                <td>${product.getHeight()} </td>
                            </tr>
                            <tr>
                                <th>Width</th>
                                <td>${product.getWidth()} </td>
                            </tr>
                            <tr>
                                <th> <i class="fa fa-balance-scale-left"></i>Weight</th>
                                <td>${product.getWeight()} Kilogram</td>
                            </tr>
                        </tbody>
                    </table>

                    <form style="float:right; display:inline;" class="form-inline my-2 my-lg-0" action="">
                        <input class="form-control mr-sm-2" maxlength="1000" type="search" placeholder="Search a product" aria-label="Search..."
                               oninput="searchByName(this,${product.getCategoryID()},${product.getProductID()})" value="${txtS}" name="txt">
                        <a href="#" ><i class="fa fa-search text-primary"></i></a>
                    </form>
                    <div id="content"  style="">

                    </div>
                    <br><br>
                </div>
            </div>
        </div>


        <!-- Categories End -->

        <!-- Vendor Start -->
        <div class="container col-md-12 col-sm-12">
            <div class="row px-xl-5">
                <div class="col">
                    <div class="owl-carousel vendor-carousel">
                        <c:forEach items="${blogs}" var="blog">
                            <div class="mx-auto vendor-items border row align-items-center " style="height: 300px;">
                                <a class="text-center" href="#"><img src="image/${blog.getImageLink()}"
                                                                     alt="">${blog.getTitle()}</a>
                            </div>
                        </c:forEach>
                    </div>
                </div>
            </div>
        </div>
        <!-- Vendor End -->

        <!-- Featured Start -->
        <div class="container-fluid pt-5">
            <div class="row px-xl-5 pb-3">
                <div class="col-lg-3 col-md-6 col-sm-12 pb-1">
                    <div class="d-flex align-items-center border mb-4" style="padding: 30px;">
                        <h1 class="fa fa-check text-primary m-0 mr-3"></h1>
                        <h5 class="font-weight-semi-bold m-0">Quality Product</h5>
                    </div>
                </div>
                <div class="col-lg-3 col-md-6 col-sm-12 pb-1">
                    <div class="d-flex align-items-center border mb-4" style="padding: 30px;">
                        <h1 class="fa fa-shipping-fast text-primary m-0 mr-2"></h1>
                        <h5 class="font-weight-semi-bold m-0">Free Shipping</h5>
                    </div>
                </div>
                <div class="col-lg-3 col-md-6 col-sm-12 pb-1">
                    <div class="d-flex align-items-center border mb-4" style="padding: 30px;">
                        <h1 class="fas fa-exchange-alt text-primary m-0 mr-3"></h1>
                        <h5 class="font-weight-semi-bold m-0">14-Day Return</h5>
                    </div>
                </div>
                <div class="col-lg-3 col-md-6 col-sm-12 pb-1">
                    <div class="d-flex align-items-center border mb-4" style="padding: 30px;">
                        <h1 class="fa fa-phone-volume text-primary m-0 mr-3"></h1>
                        <h5 class="font-weight-semi-bold m-0">24/7 Support</h5>
                    </div>
                </div>
            </div>
        </div>
        <!-- Featured End -->

        <!-- Footer Start -->
        <%@include file="components/Footer.jsp" %>
        <!-- JavaScript Libraries -->

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
        <script>
                                        function searchByName(param, param1,param2) {
                                            var txtSearch = param.value.trim();
                                            var cate = param1;
                                            var pID=param2;
                                            $.ajax({
                                                url: "/Update/CompareByAjax",
                                                type: "get", //send it through get method
                                                data: {
                                                    txt: txtSearch,
                                                    cateId: cate,
                                                    productID: pID
                                                },
                                                success: function (data) {
                                                    var row = document.getElementById("content");
                                                    row.innerHTML = data;
                                                    console.log(cate);
                                                    console.log(txtSearch);
                                                },
                                                error: function (xhr) {
                                                    //Do Something to handle error
                                                }
                                            });
                                        }
                                        ;
        </script>

    </body>

</html>