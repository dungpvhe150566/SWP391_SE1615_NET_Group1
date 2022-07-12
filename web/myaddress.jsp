<%-- 
    Document   : userprofile
    Created on : Jun 14, 2022, 9:01:16 PM
    Author     : Dung
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"> 
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

        <script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyB8xJ9PxnU_GzDixMc-om06stZUsDSiTSw&sensor=true&callback=initMap" async defer></script>
    <body>

        <!-- Add address form start !-->
        <div id="form-address">
            <div class="layer-gray row container-fluid text-center my-auto mx-auto" id="addAddress"></div>
            <form name="addressForm" action="myaddress"  onsubmit="return validateForm()" method="post">
                <input type="hidden" name="userAddressID" id="userAddressID" value="">
                <input type="hidden" name="do" id="do" value="">
                <div class="row container-fluid">
                    <div class="addAddress my-auto mx-auto pl-2 pr-2 pt-2 pb-2">
                        <div class="row mt-3">
                            <div class="col-md-12">
                                <h4 id="title">New Address</h4>
                            </div>
                        </div>
                        <div class="row mt-3">
                            <div class="col-md-6">
                                <input name="shipName" id="shipName" class="form-control" maxlength="500" type="text" placeholder="Your Name">
                            </div>
                            <div class="col-md-6">
                                <input name="phone" id="phone" class="form-control" maxlength="20" type="text" placeholder="Your Phone Number">
                            </div>
                        </div>
                        <div class="row mt-3">
                            <div class="col-md-12">
                                <select name="shipCity" class="form-control custom-select">
                                    <option value="-1">-- Select Your City --</option>
                                    <c:forEach items="${arrShip}" var="ship">
                                        <option value="${ship.getId()}">${ship.getCityName()}</option>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>
                        <div class="row mt-3">
                            <div class="col-md-12">
                                <input name="shipAddress" onchange="showAddress()" id="address" class="form-control" maxlength="1000" type="text" placeholder="Address">
                            </div>
                        </div>
                        <div class="row mt-3">
                            <div class="col-md-12">
                                <div style="width: 100%"><iframe id="maps" width="100%" height="200" frameborder="0" scrolling="no" marginheight="0" marginwidth="0" src="https://maps.google.com/maps?width=100%25&amp;height=600&amp;hl=en&amp;q=Ha%20Noi&amp;t=&amp;z=14&amp;ie=UTF8&amp;iwloc=B&amp;output=embed"><a href="https://www.gps.ie/farm-gps/">agricultural gps</a></iframe></div>
                            </div>
                        </div>
                        <div class="row mt-3 mb-3">
                            <div class="col-md-4">
                            </div>
                            <div class="col-md-4">
                                <button style="width: 100%" type="button" onclick="hideFormAddress()" class="btn btn-secondary">Back</button>
                            </div>
                            <div class="col-md-4">
                                <button style="width: 100%" type="submit" class="btn btn-primary">Save</button>
                            </div>
                        </div>
                    </div>
                </div> 
            </form>
        </div>
        <!-- Add address form end !-->

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
                                                <i class="fas fa-user" style="color: black"></i>
                                            </div>
                                            <div class="col-md-9">
                                                <h6 class="mb-0">User Profile</h6>
                                            </div>
                                        </div>
                                    </li>
                                    <li class="list-group-item d-flex justify-content-between align-items-center flex-wrap active">
                                        <div class="row">
                                            <div class="col-md-3">
                                                <i class="fas fa-address-book" style="color: black"></i>
                                            </div>
                                            <div class="col-md-9" onclick="window.location.href = 'myaddress'">
                                                <h6 class="mb-0">User Address</h6>
                                            </div>
                                        </div>
                                    </li>
                                    <li class="list-group-item d-flex justify-content-between align-items-center flex-wrap">
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

                        <div class="col-md-9 mb-3">
                            <ul class="list-group list-group-flush">
                                <li class="list-group-item">
                                    <div class="row">
                                        <div class="col-md-9 my-auto">
                                            <h5 class="mb-0">My Address</h5>
                                        </div>
                                        <div class="col-md-3 text-right">
                                            <button type="submit" class="btn btn-primary align-content-end" onclick="showFormAddress()"><i class="fas fa-plus"></i> Add Address</button>
                                        </div>
                                    </div>
                                </li>


                                <c:if test="${fn:length(arrUserAddress) == 0}">
                                    <li class="list-group-item">
                                        <div class="row ml-0" style="background-color: #ffffff; width: 100%; height:100vh">
                                            <div class="my-auto mx-auto" style="height: 25%">
                                                <img src="image/Other/order.png" alt="order" style="max-height:100%; max-width:100%;">
                                                <div class="text-center">
                                                    <span>No address yet</span>
                                                </div>
                                            </div>
                                        </div>
                                    </li>
                                </c:if>

                                <c:forEach items="${arrUserAddress}" var="userAddress">
                                    <li class="list-group-item">
                                        <div class="row  mb-3 mt-3">
                                            <div class="col-md-9">
                                                <div class="row">
                                                    <div class="col-md-4 text-right">
                                                        <span>Name</span>
                                                    </div>
                                                    <div class="col-md-8" style="color: black;">${userAddress.getShipName()}</div>
                                                </div>
                                                <br>
                                                <div class="row">
                                                    <div class="col-md-4 text-right">
                                                        <span>Phone</span>
                                                    </div>
                                                    <div class="col-md-8" style="color: black;">${userAddress.getPhoneNum()}</div>
                                                </div>
                                                <div class="row">
                                                    <div class="col-md-4 text-right">
                                                        <span>Address</span>
                                                    </div>
                                                    <div class="col-md-8" style="color: black;">${userAddress.getShipAddress()}</div>
                                                </div>
                                            </div>
                                            <div class="col-md-3 text-right">
                                                <div>
                                                    <a onclick="deleteAddress(${userAddress.getID()})" href="#">Delete</a>
                                                    <a onclick="showFormEdit('${userAddress.getShipName()}', '${userAddress.getPhoneNum()}', '${userAddress.getID()}')" href="#" style="margin-left: 20px">Edit</a>
                                                </div>
                                                <br>
                                                <div>
                                                    <button type="submit" class="btn btn-secondary">Set Default</button>
                                                </div>
                                            </div>
                                        </div>
                                    </li>
                                </c:forEach>
                            </ul>
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
        #form-address {
            display: none;
        }
        .layer-gray {
            display: block;
            z-index: 1000;
            position: fixed;
            background-color: gray;
            opacity: 0.5;
            min-height: 100vh;
        }

        .addAddress {
            display: inline-block;
            position: fixed;
            top: 50%;
            left: 50%;
            transform: translate(-50%, -50%);
            width: 500px;
            z-index: 1001;
            box-sizing: border-box;
            border-radius: 3px;
            background-color: #fff;
        }

        .addAddress .row {
            margin-left: 20px;
            margin-right: 20px;
        }
    </style>

    <script>
                                                        function showFormAddress() {
                                                            document.getElementById('title').innerHTML = "New Addresss";
                                                            document.getElementById('do').value = "add";
                                                            document.getElementById('shipName').value = "";
                                                            document.getElementById('phone').value = "";
                                                            document.getElementById('form-address').style.display = "block";
                                                        }

                                                        function hideFormAddress() {
                                                            document.getElementById('form-address').style.display = "none";
                                                        }

                                                        function showAddress() {
                                                            var iframe = document.getElementById("maps");
                                                            var input = document.getElementById("address").value;

                                                            input = input.replace(" ", "%20");
                                                            console.log(iframe.src);
                                                            iframe.src = "https://maps.google.com/maps?width=100%25&height=600&hl=en&q=" + input + "&t=&z=14&ie=UTF8&iwloc=B&output=embed";
                                                            console.log(iframe.src);
                                                        }

                                                        function showFormEdit(name, phone, userAddressID) {
                                                            document.getElementById('title').innerHTML = "Edit Addresss";
                                                            document.getElementById('do').value = "edit";
                                                            document.getElementById('shipName').value = name;
                                                            document.getElementById('phone').value = phone;
                                                            document.getElementById('userAddressID').value = userAddressID;
                                                            document.getElementById('form-address').style.display = "block";
                                                        }

                                                        function deleteAddress(addressID) {
                                                            if (confirm("Are you sure to delete this addess ?")) {
                                                                window.location.href = "myaddress?do=delete&id=" + addressID;
                                                            }
                                                        }

                                                        function validateForm() {
                                                            var x = document.forms["addressForm"]["shipName"].value;
                                                            if (x == "") {
                                                                alert("Name must be filled out");
                                                                return false;
                                                            }
                                                            
                                                            x = document.forms["addressForm"]["phone"].value;
                                                            if (x == "") {
                                                                alert("Phone must be filled out");
                                                                return false;
                                                            }
                                                            
                                                            x = document.forms["addressForm"]["shipCity"].value;
                                                            if (x == "-1") {
                                                                alert("Select a city !");
                                                                return false;
                                                            }
                                                            
                                                            x = document.forms["addressForm"]["shipAddress"].value;
                                                            if (x == "") {
                                                                alert("Address must be filled out");
                                                                return false;
                                                            }
                                                            
                                                            return true;
                                                        }
    </script>
</html>
