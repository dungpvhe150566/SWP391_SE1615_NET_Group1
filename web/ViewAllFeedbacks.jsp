<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>  
    <head>
        <!-- Required meta tags -->
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">

        <!--Favicon-->
        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.3.1/css/all.css" integrity="sha384-mzrmE5qonljUremFsqc01SB46JvROS7bZs3IO2EmfFsd15uHvIt+Y8vEf7N7fWAU" crossorigin="anonymous">

        <!--Table Styling-->
        <link rel="stylesheet" href="css/OrderTableStyle.css" type="text/css"/>

        <!--Custom Styling-->
        <link rel="stylesheet" href="css/ViewAllFeedbackStyle.css" type="text/css"/>

        <!-- Bootstrap CSS -->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-eOJMYsd53ii+scO/bJGFsiCZc+5NDVN2yr8+0RDqr0Ql0h+rP48ckxlpbzKgwra6" crossorigin="anonymous">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">

        <title>View All Feedbacks</title>

        <style>
            .content{
                margin: auto;
                padding: 15px;
                max-width: 800px;
            }
            .dpx{
                display:flex;
                align-items:center;
                justify-content:space-around;
            }
            h1{
                font-size:28px;
                line-height:28px;
                margin-bottom:15px;
            }
            label{
                display:block;
                line-height:40px;
            }
            .option-input {
                -webkit-appearance: none;
                -moz-appearance: none;
                -ms-appearance: none;
                -o-appearance: none;
                appearance: none;
                position: relative;
                top: 13.33333px;
                right: 0;
                bottom: 0;
                left: 0;
                height: 40px;
                width: 40px;
                transition: all 0.15s ease-out 0s;
                background: #cbd1d8;
                border: none;
                color: #fff;
                cursor: pointer;
                display: inline-block;
                margin-right: 0.5rem;
                outline: none;
                position: relative;
                z-index: 1000;
            }
            .option-input:hover {
                background: #9faab7;
            }
            .option-input:checked {
                background: #40e0d0;
            }
            .option-input:checked::before {
                width: 40px;
                height: 40px;
                display:flex;
                content: '\f00c';
                font-size: 25px;
                font-weight:bold;
                position: absolute;
                align-items:center;
                justify-content:center;
                font-family:'Font Awesome 5 Free';
            }
            .option-input:checked::after {
                -webkit-animation: click-wave 0.65s;
                -moz-animation: click-wave 0.65s;
                animation: click-wave 0.65s;
                background: #40e0d0;
                content: '';
                display: block;
                position: relative;
                z-index: 100;
            }
            .option-input.radio {
                border-radius: 50%;
            }
            .option-input.radio::after {
                border-radius: 50%;
            }

            @keyframes click-wave {
                0% {
                    height: 40px;
                    width: 40px;
                    opacity: 0.35;
                    position: relative;
                }
                100% {
                    height: 200px;
                    width: 200px;
                    margin-left: -80px;
                    margin-top: -80px;
                    opacity: 0;
                }
            }

            .button {
                background-color: #4CAF50;
                border: none;
                color: white;
                padding: 8px 32px;
                text-align: center;
                text-decoration: none;
                display: inline-block;
                font-size: 16px;
                margin: 4px 2px;
                transition-duration: 0.4s;
                cursor: pointer;
                border-radius: 8px;
            }

            .button2 {
                background-color: white; 
                color: black; 
                border: 2px solid #008CBA;
            }

            .button2:hover {
                background-color: #008CBA;
                color: white;
            }
        </style>
    </head>
    <body>

        <div class="container-fluid">
            <div class="row">
                <div class="col-md-2" style="background-color: #ebebf2">
                    <nav class="navbar navbar-expand-lg navbar-light flex-column">
                        <a class="navbar-brand" href="HomeController"><img src="image/Other/AccountDashboard.jpg" width="200px"></a>
                        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                            <span class="navbar-toggler-icon"></span>
                        </button>

                        <ul class="nav flex-column">
                            <li class="nav-item">
                                <a class="nav-link" href="HomeController"><i class="fas fa-home"></i>Home</a>
                                <hr class="line">
                            </li>
                            <c:if test="${sessionScope.user == null}">
                                <li class="nav-item">
                                    <a class="nav-link" href="login">Login</a>
                                </li>
                            </c:if>
                            <c:if test = "${sessionScope.user != null}">
                                <li class="nav-item">
                                    <a class="nav-link" href="#"><i class="fas fa-user-circle"></i>Hello ${sessionScope.user.getUsername()}</a>
                                    <hr class="line">
                                </li>

                                <c:if test="${sessionScope.user.getIsSeller() == 1}">
                                    <li class="nav-item">
                                        <a class="nav-link" href="ProductsController"><i class="fas fa-tasks"></i>Manager Product</a>
                                        <hr class="line">
                                        <a class="nav-link" href="#"><i class="fas fa-tasks"></i>Manager Blog</a>
                                        <hr class="line">
                                        <a class="nav-link" href="ViewAllFeedbackController"><i class="fas fa-tasks"></i>Manage Feedback</a>
                                        <hr class="line">
                                    </li> 
                                </c:if>

                                <c:if test="${sessionScope.user.getIsAdmin() == 1}">
                                    <li class="nav-item">
                                        <a class="nav-link" href="AccountManagerController"><i class="fas fa-tasks"></i>Manager Account</a>
                                        <hr class="line">
                                    </li> 
                                </c:if>
                                <li class="nav-item">
                                    <a class="nav-link" href="#"><i class="fas fa-bell"></i>Notifications (${numberNoti})</a>
                                </li> 
                            </c:if>

                            <li class="nav-item">&nbsp;
                            </li> 
                        </ul>
                        <a class="nav-link" href="logout" style="position: fixed; right: 10px;">LogOut</a>
                    </nav>
                </div>
                <div class="col-md-10">
                    <hr>
                    <div class="row">
                        <form class="sort-form" action="ViewAllFeedbackController" method="POST">
                            <div class='content'>
                                <h1 style="text-align: center">Choose your sort options and order:</h1>
                                <div class="dpx">
                                    <div class='px'>
                                        <label>
                                            <input type="radio" class="option-input radio" id="sort-star" name="sort-order" value="1" ${sopt==1?"checked":""} />
                                            Sort by star
                                        </label>
                                        <label>
                                            <input type="radio" class="option-input radio" id="sort-name" name="sort-order" value="2" ${sopt==2?"checked":""} />
                                            Sort by name
                                        </label>
                                        <label>
                                            <input type="radio" class="option-input radio" id="sort-product" name="sort-order" value="3" ${sopt==3?"checked":""} />
                                            Sort by product
                                        </label>
                                    </div>
                                    <div class='py'>
                                        <label>
                                            <input type="radio" class="option-input radio" id="sort-order" name="sort-by-order" value="1" ${sodr==1?"checked":""} />
                                            Ascending
                                        </label>
                                        <label>
                                            <input type="radio" class="option-input radio" id="sort-order" name="sort-by-order" value="2" ${sodr==2?"checked":""} />
                                            Descending
                                        </label>
                                    </div>
                                </div>
                            </div>
                            <div style="width: 100%; text-align: center;">
                                <input type="submit" id="btn1" value="Sort" class="button button2">
                            </div>

                        </form>
                    </div>
                    <c:if test="${test==true}">
                        <p style="color: red;" >${mesde}</p>
                    </c:if>
                    <div class="row-fluid">
                        <div class="col-md-12">
                            <table id="feedback" style=" border: 1px solid;">
                                <thead >
                                    <tr>
                                        <th style="text-align: center;">User</th>
                                        <th style="text-align: center;">Star</th>
                                        <th style="text-align: center;">Product</th>
                                        <th style="text-align: center;">Detail</th>
                                        <th style="text-align: center;">Delete</th>

                                    </tr>
                                </thead>
                                <tbody>
                                    <c:forEach var="item" items="${lsFeedback}" >
                                        <tr style="padding:2px; border: 1px solid">
                                            <td>${item.getUserName()}</td>
                                            <td>
                                                <c:forEach begin="1" end="${item.getStar()}">
                                                    <span><i class="fa fa-star checked" style="font-size: 10px"></i></span>  
                                                    </c:forEach>
                                            </td>
                                            <td>
                                                ${item.getProductName()}
                                            </td>
                                            <td><a href="ViewFeedbackDetailController?id=${item.getID()}">Detail</a></td>
                                            <td>
                                                <form action="deleteAccount" method="POST" style="display:inline">
                                                    <a> <input type="submit" class="btn btn-outline-danger btn-sm" value="Delete" onclick="if (confirm('Are you sure you want to delete?'))
                                                                form.action = 'DeleteFeedbackController?fbID=${item.getID()}';
                                                            else
                                                                return false;"/>  
                                                    </a>
                                                </form>
                                            </td>
                                        </tr>

                                    </c:forEach>
                                </tbody>
                            </table>
                            <nav style="margin: 20px auto; width: 500px" aria-label="...">
                                <ul class="pagination pagination-lg">
                                    <c:forEach begin="1" end="${uid}" var="i">
                                        <li class="page-item ${indexpage==i?"active":""}" ><a class="page-link" href="ViewAllFeedbackController?index=${i}&sopt=${sopt}&sodr=${sodr}">${i}</a></li>
                                        </c:forEach>
                                </ul>
                            </nav>
                        </div>   
                    </div>

                </div>
            </div>      
            <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/js/bootstrap.bundle.min.js" integrity="sha384-JEW9xMcG8R+pH31jmWH6WWP0WintQrMb4s7ZOdauHnUtxwoG2vI5DkLtS3qm9Ekf" crossorigin="anonymous"></script>

    </body>
</html>
