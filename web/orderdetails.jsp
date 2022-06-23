<%-- 
    Document   : orderdetails
    Created on : Jun 20, 2022, 8:45:51 PM
    Author     : viet
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="css/orderdetails.css">
    </head>
    <body>
        <div class="card">
            <div class="title">Order Detail</div>
            <div class="info">
                <div class="row">
                    <div class="col-7">
                        <span id="heading">Order Id</span><br>
                        <span id="details">${OD.orderID}</span>
                    </div>
                    <div class="col-5 pull-right">
                        <span id="heading">Product Id</span><br>
                        <span id="details">${OD.productID}</span>
                    </div>
                </div>      
            </div>      
            <div class="pricing">
                <div class="row">
                    <div class="col-9">
                        <span id="name">Product Name</span>  
                    </div>
                    <div class="col-3">
                        <span id="price">${OD.productName}</span>
                    </div>
                </div>
                <div class="row">
                    <div class="col-9">
                        <span id="name">Product Price</span>
                    </div>
                    <div class="col-3">
                        <span id="price">${OD.productPrice}</span>
                    </div>
                </div>
                <div class="row">
                    <div class="col-9">
                        <span id="name">Quantity</span>
                    </div>
                    <div class="col-3">
                        <span id="price">${OD.quantity}</span>
                    </div>
                </div>
            </div>
    </body>
</html>
