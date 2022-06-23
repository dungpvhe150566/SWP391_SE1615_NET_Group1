<!DOCTYPE html>
<html lang="en">
    <%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
    <head>
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
        <!-- Topbar Start -->
        <%@include file="components/Navbar.jsp" %>
        <!-- Navbar End -->

        <!-- Page Header Start -->
        <div class="container-fluid mb-5">
            <div class="row border-top px-xl-5">
                <div class="col-lg-12">
                    <div id="header-carousel" class="carousel slide" data-ride="carousel">
                        <div class="carousel-inner">
                            <div class="carousel-item" style="height: 410px;">
                                <img class="img-fluid" src="image/SlideShow/image1.jpg" alt="Image">
                            </div>
                            <div class="carousel-item active" style="height: 410px;">
                                <img class="img-fluid" src="image/SlideShow/image2.jpg" alt="Image">
                            </div>
                            <div class="carousel-item" style="height: 410px;">
                                <img class="img-fluid" src="image/SlideShow/image3.jpg" alt="Image">
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!-- Page Header End -->


        <!-- Shop Start -->
        <div class="container-fluid pt-5">
            <div class="row px-xl-5">
                <!-- Shop Sidebar Start -->
                <div class="col-lg-3 col-md-12">
                    <form action="ShopController">
                        <input type="hidden" name="do" value="${service}" >
                        <input type="hidden" name="productName" value="${productName}" >
                        <input type="hidden" name="categoryID" value="${categoryID}" >
                        <input type="hidden" name="sort" value="${sort}" >
                        <!-- Price Start -->
                        <div class="border-bottom mb-4 pb-4">
                            <h5 class="font-weight-semi-bold mb-4">Filter By Price</h5>

                            <div class="custom-control custom-checkbox d-flex align-items-center justify-content-between mb-3">
                                <input onchange="this.form.submit();" ${listPrices.contains('0-100000000')? 'checked': ''} name="prices" type="checkbox" class="custom-control-input" value="0-100000000" id="price-all">
                                <label class="custom-control-label" for="price-all">All Price</label>
                                <span class="badge border font-weight-normal"></span>
                            </div>
                            <div class="custom-control custom-checkbox d-flex align-items-center justify-content-between mb-3">
                                <input onchange="this.form.submit();" ${listPrices.contains('100000-500000')? 'checked': ''} name="prices" type="checkbox" class="custom-control-input" value="100000-500000" id="price-1">
                                <label class="custom-control-label" for="price-1">$100.000 - $500.000</label>
                                <span class="badge border font-weight-normal"></span>
                            </div>
                            <div class="custom-control custom-checkbox d-flex align-items-center justify-content-between mb-3">
                                <input onchange="this.form.submit();" ${listPrices.contains('500000-1000000')? 'checked': ''} name="prices" type="checkbox" class="custom-control-input" value="500000-1000000" id="price-2">
                                <label class="custom-control-label" for="price-2">$500.000 - $1.000.000</label>
                                <span class="badge border font-weight-normal"></span>
                            </div>
                            <div class="custom-control custom-checkbox d-flex align-items-center justify-content-between mb-3">
                                <input onchange="this.form.submit();" ${listPrices.contains('1000000-5000000')? 'checked': ''} name="prices" type="checkbox" class="custom-control-input" value="1000000-5000000" id="price-3">
                                <label class="custom-control-label" for="price-3">$1.000.000 - $5.000.000</label>
                                <span class="badge border font-weight-normal"></span>
                            </div>
                            <div class="custom-control custom-checkbox d-flex align-items-center justify-content-between mb-3">
                                <input onchange="this.form.submit();" ${listPrices.contains('5000000-10000000')? 'checked': ''} name="prices" type="checkbox" class="custom-control-input" value="5000000-10000000" id="price-4">
                                <label class="custom-control-label" for="price-4" >$5.000.000 - $10.000.000</label>
                                <span class="badge border font-weight-normal"></span>
                            </div>
                            <div class="custom-control custom-checkbox d-flex align-items-center justify-content-between">
                                <input onchange="this.form.submit();" ${listPrices.contains('10000000-800000000')? 'checked': ''} name="prices" type="checkbox" class="custom-control-input"value="10000000-800000000" id="price-5">
                                <label class="custom-control-label" for="price-5">>$10.000.000</label>
                                <span class="badge border font-weight-normal"></span>
                            </div>
                        </div>
                        <!-- Price End -->

                        <!--                    Manufacturer-->
                        <div class="border-bottom mb-4 pb-4">
                            <h5 class="font-weight-semi-bold mb-4">Filter by Manufacturer</h5>
                            <c:set var="i" value="1"/>
                            <div class=" manufactury ">
                                <c:forEach items="${listManufacturers}" var="manufacturer">
                                    <div class="custom-control custom-checkbox d-flex align-items-center justify-content-between mb-3">
                                        <input type="checkbox" ${manufacturers.contains(Integer.toString(manufacturer.getManufacturerID()))?'checked ':' '} 
                                               onchange="this.form.submit();" name="manufacturer" class="custom-control-input" 
                                               id="color-${i}" value="${manufacturer.getManufacturerID()}">
                                        <label class="custom-control-label" for="color-${i}">${manufacturer.getManufacturerName()}</label>
                                        <span class="badge border font-weight-normal">${manufacturer.getCount()}</span>
                                    </div>
                                    <c:set var="i" value="${i}+1"/>
                                </c:forEach>
                            </div>
                        </div>
                        <!--                    Manufacturer End-->
                    </form>
                </div>
                <!-- Shop Sidebar End -->


                <!-- Shop Product Start -->
                <div class="col-lg-9 col-md-12">
                    <div class="row pb-3">
                        <div class="col-12 pb-1">
                            <div class="d-flex align-items-center justify-content-between mb-4">
                                <div class="dropdown ml-4">
                                    <button class="btn border dropdown-toggle" type="button" id="triggerId" data-toggle="dropdown" aria-haspopup="true"
                                            aria-expanded="false">
                                        ${sort==null? 'Sort by': sort}
                                    </button>
                                    <div class="dropdown-menu dropdown-menu-right" aria-labelledby="triggerId">
                                        <a  class="dropdown-item" href="ShopController?do=${service}&productName=${productName}&categoryID=${categoryID}&listPrices=${listPrices}&manufacturers=${manufacturers}&sort=Ascending">Ascending</a>
                                        <a class="dropdown-item" href="ShopController?do=${service}&productName=${productName}&categoryID=${categoryID}&listPrices=${listPrices}&manufacturers=${manufacturers}&sort=Decreasing">Decreasing</a>
                                    </div>
                                </div>
                            </div>
                        </div>
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
                                        <a href="ShopDetailController?do=ViewDetail&categoryID=${product.getCategoryID()}&productID=${product.getProductID()}" class="btn btn-sm text-dark p-0"><i class="fas fa-eye text-primary mr-1"></i>View Detail</a>
                                        <a href="addtocart?productId=${product.getProductID()}" class="btn btn-sm text-dark p-0"><i class="fas fa-shopping-cart text-primary mr-1"></i>Add To Cart</a>
                                        <a href="CompareProducts?productID=${product.getProductID()}" class="btn btn-sm text-dark p-0"><i class="fas fa-search text-primary mr-1"></i>Compare</a>
                                    </div>
                                </div>
                            </div>
                        </c:forEach>
                        <!--                        Pagination ProductList -->
                        <div class="col-12 pb-1">
                            <nav aria-label="Page navigation">
                                <ul class="pagination justify-content-center mb-3">
                                    <li class="page-item ${indexPage==1?'disabled':''}">
                                        <a class="page-link" href="ShopController?do=${service}&indexPage=${indexPage-1}&productName=${productName}&categoryID=${categoryID}&listPrices=${listPrices}&manufacturers=${manufacturers}&sort=${sort}" aria-label="Previous">
                                            <span aria-hidden="true">&laquo;</span>
                                            <span class="sr-only">Previous</span>
                                        </a>
                                    </li>
                                    <c:forEach var="i" begin="1" end="${totalPage}"  >
                                        <li class="page-item ${indexPage==i?'active':''}">
                                            <a class="page-link" href="ShopController?do=${service}&indexPage=${i}&productName=${productName}&categoryID=${categoryID}&listPrices=${listPrices}&manufacturers=${manufacturers}&sort=${sort}">${i}</a></li>
                                        </c:forEach>
                                    <li class="page-item ${indexPage==totalPage?'disabled':''}">
                                        <a class="page-link" href="ShopController?do=${service}&indexPage=${indexPage+1}&productName=${productName}&categoryID=${categoryID}&listPrices=${listPrices}&manufacturers=${manufacturers}&sort=${sort}" aria-label="Next">
                                            <span aria-hidden="true">&raquo;</span>
                                            <span class="sr-only">Next</span>
                                        </a>
                                    </li>
                                </ul>
                            </nav>
                        </div>
                    </div>
                </div>
                <!-- Shop Product End -->
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