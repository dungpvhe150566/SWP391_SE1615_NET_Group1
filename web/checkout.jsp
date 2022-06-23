<!DOCTYPE html>
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
        <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@100;200;300;400;500;600;700;800;900&display=swap" rel="stylesheet"> 

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
        <div class="container-fluid bg-secondary mb-5">
            <div class="d-flex flex-column align-items-center justify-content-center" style="min-height: 300px">
                <h1 class="font-weight-semi-bold text-uppercase mb-3">Checkout</h1>
                <div class="d-inline-flex">
                    <p class="m-0"><a href="">Home</a></p>
                    <p class="m-0 px-2">-</p>
                    <p class="m-0">Checkout</p>
                </div>
            </div>
        </div>
        <!-- Page Header End -->


        <!-- Checkout Start -->
        <form action="Checkout" method="POST">
            <div class="row px-xl-5">
                <div class="col-lg-8">
                    <div class="mb-4">
                        <h4 class="font-weight-semi-bold mb-4">Billing Address</h4>

                        <div class="row">

                            <div class="col-md-6 form-group">
                                <label>CustomerName</label>
                                <input class="form-control"  id="name" name="name" type="text" placeholder="John"    required >
                            </div>
                            <div class="col-md-6 form-group">
                                <label>Note</label>
                                <input class="form-control"  id="note "name="note" type="text" placeholder="required"  required >
                            </div>

                            <div class="col-md-6 form-group">
                                <label>Phone Number</label>
                                <input maxlength="10" class="form-control"  id="phone"name="phone" type="text"  onkeypress="return isNumberKey(event)"placeholder="+84 1 234 5678"  required pattern="(84|0[3|5|7|8|9])+([0-9]{8})\b" >
                            </div>
                            <div class="col-md-6 form-group">
                                <label>Address Detail</label>
                                <input class="form-control" id="address" name="address" type="text" placeholder="123 Street"  required >
                            </div>

                            <div class="col-md-6 form-group">
                                <label ">City Name:</label>
                                <select name="CityId" class="form-control" >
                                    <c:forEach items="${listShips}" var="C">
                                        <option ${C.getId() == shipping.CityId ?"selected":""} value="${C.getId()}">${C.getCityName()}</option>
                                    </c:forEach>
                                </select>

                            </div>

                            <div hidden=""></div>
                        </div>


                    </div>

                </div>
                <div class="col-lg-4">
                    <div class="card border-secondary mb-5">
                        <div class="card-header bg-secondary border-0">
                            <h4 class="font-weight-semi-bold m-0">Order Total</h4>
                        </div>
                        <div class="card-body">
                            <h5 class="font-weight-medium mb-3">Products</h5>
                            <c:forEach items="${carts}" var="C">


                                <div class="d-flex justify-content-between">
                                    <input type="hidden" name="productId" value="${C.value.product.getProductID()}"/>
                                    <p style="
                                       display:inline-block;
                                       white-space: nowrap;
                                       overflow: hidden;
                                       text-overflow: ellipsis;
                                       max-width: 13ch;">${C.value.product.getProductName()}</p>
                                    <p>${C.value.product.getOriginalPrice()*C.value.getAmount()}</p>
                                </div>
                            </c:forEach>
                            <hr class="mt-0">
                            <div class="d-flex justify-content-between mb-3 pt-1">
                                <h6 class="font-weight-medium">Subtotal</h6>
                                <h6 class="font-weight-medium">${totalMoney}</h6>
                            </div>
                            <div class="d-flex justify-content-between">
                                <h6 class="font-weight-medium">Shipping</h6>
                                <h6 class="font-weight-medium">50.000 VND</h6>
                            </div>
                        </div>
                        <div class="card-footer border-secondary bg-transparent">
                            <div class="d-flex justify-content-between mt-2">
                                <h5 class="font-weight-bold">Total</h5>
                                <h5 class="font-weight-bold">${totalMoney + 50000}  </h5>
                            </div>
                        </div>
                    </div>
                    <div class="card border-secondary mb-5">
                        <div class="card-header bg-secondary border-0">
                            <h4 class="font-weight-semi-bold m-0">Payment</h4>
                        </div>
                        <div class="card-body">

                            <div class="card-footer border-secondary bg-transparent">
                                <button  onclick="myFunction()" type="submit" class="btn btn-lg btn-block btn-primary font-weight-bold my-3 py-3">Place Order</button>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </form>
        <!-- Checkout End -->


        <!-- Footer Start -->
        <%@include file="components/Footer.jsp" %>


        <!-- JavaScript Libraries -->

        <script>
            function isNumberKey(e) {
                var charCode = (e.which) ? e.which : e.keyCode;
                if (charCode > 31 && (charCode < 48 || charCode > 57))
                    return false;
                return true;
            }
        </script>
        <script>
            function myFunction() {
                alert("Your Orders successfully");
            }
        </script>
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