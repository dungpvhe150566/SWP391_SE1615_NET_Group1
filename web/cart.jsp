
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
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
        <!-- Page Header End -->


        <!-- Cart Start -->
        <div class="container-fluid pt-5">

            <div class="row px-xl-5">
                <c:choose> 
                    <c:when test="${sessionScope.carts==null||sessionScope.carts.size()==0}">
                        <h1 class="text-center">Your Cart is Empty</h1>
                    </c:when>
                    <c:otherwise>
                        <div class="col-lg-8 table-responsive mb-5">

                            <table class="table table-bordered text-center mb-0">
                                <thead class="bg-secondary text-dark">
                                    <tr>
                                        <th>Products</th>
                                        <th>Price</th>
                                        <th>Quantity</th>
                                        <th>Total</th>
                                        <th>Remove</th>
                                    </tr>
                                </thead>
                                <tbody class="align-middle">
                                    <c:forEach items="${carts}" var="C">
                                    <form action="update-quantity">
                                        <tr>
                                        <input type="hidden" name="productId" value="${C.value.product.getProductID()}"/>
                                        <td style="
                                            display:inline-block;
                                            white-space: nowrap;
                                            overflow: hidden;
                                            text-overflow: ellipsis;
                                            max-width: 40ch;"class="align-middle"><img src="image/${C.value.product.getImageLink()}"  style="width: 50px;" >${C.value.product.getProductName()}</td>
                                        <td class="align-middle">${C.value.product.getOriginalPrice()} VND</td>
                                        <td class="align-middle">

                                            <input  min='0' type="number" onkeypress="return (event.charCode == 8 || event.charCode == 0) ? null : event.charCode >= 48 && event.charCode <= 57" name ="quantity" onchange="this.form.submit()"  class="form-control form-control-sm bg-secondary text-center" value="${C.value.getAmount()}">

                                        </td>
                                        <td class="align-middle">${C.value.product.getOriginalPrice()*C.value.getAmount()}</td>
                                        <td class="align-middle"><a href="#"onclick="showMess(${C.value.product.getProductID()})" class="btn btn-sm btn-primary"><i class="fa fa-times"></i></a></td>
                                          <!--     <td><a  href="deleteall?carts=${carts}" class="btn btn-outline-danger" ><i class="bi bi-trash"></i>DeleteALL</a></td>-->
                                        </tr>
                                    </form>
                                </c:forEach>

                                </tbody>
                            </table>

                        </div>
                        <div class="col-lg-4">
                           
                            <div class="card border-secondary mb-5">
                                <div class="card-header bg-secondary border-0">
                                    <h4 class="font-weight-semi-bold m-0">Cart Summary</h4>
                                </div>
                                <div class="card-body">
                                    <div class="d-flex justify-content-between mb-3 pt-1">
                                        <h6 class="font-weight-medium">Subtotal</h6>
                                        <h6 class="font-weight-medium">${totalMoney}</h6>
                                    </div>
                                    
                                </div>
                                <div class="card-footer border-secondary bg-transparent">
                                    <div class="d-flex justify-content-between mt-2">
                                        <h5 class="font-weight-bold">Total</h5>
                                        <h5 type="" style="
                                            display:inline-block;
                                            white-space: nowrap;
                                            overflow: hidden;
                                            text-overflow: ellipsis;
                                            max-width: 50ch;"class="font-weight-bold" >${totalMoney}VND</h5>
                                    </div>
                                    <a href="Checkout" class="btn btn-block btn-primary my-3 py-3">Proceed To Checkout</a>
                                </div>
                            </div>
                                    
                        </div>
                    </c:otherwise>
                </c:choose>
            </div>

        </div>
        <!-- Cart End -->


        <!-- Footer Start -->
        <%@include file="components/Footer.jsp" %>


        <!-- JavaScript Libraries -->
        <script>
            function showMess(id) {
                var option = confirm("Are u sure to delete this Product");
                if (option === true) {
                    window.location.href = 'deletecart?productId=' + id;
                }
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