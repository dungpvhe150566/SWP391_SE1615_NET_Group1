<%-- 
    Document   : userprofile
    Created on : Jun 14, 2022, 9:01:16 PM
    Author     : viet
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="css/userprofile.css">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <body>
        <div class="container">
            <div class="main-body">

                <!-- Breadcrumb -->
                <nav aria-label="breadcrumb" class="main-breadcrumb">
                    <ol class="breadcrumb">
                        <li class="breadcrumb-item"><a href="index.html">Home</a></li>
                        <li class="breadcrumb-item active" aria-current="page">User Profile</li>
                    </ol>
                </nav>
                <!-- /Breadcrumb -->

                <div class="row gutters-sm">
                    <div class="col-md-4 mb-3">
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
                                    <h6 class="mb-0">User Profile</h6>
                                    <span class="text-secondary">View/Edit</span>
                                </li>
                                <li class="list-group-item d-flex justify-content-between align-items-center flex-wrap">
                                    <h6 class="mb-0">User Address</h6>
                                    <span class="text-secondary">View/Edit/Delete</span>
                                </li>
                                <li class="list-group-item d-flex justify-content-between align-items-center flex-wrap active">
                                    <h6 class="mb-0">My Orders</h6>
                                    <span class="text-secondary">View</span>
                                </li>
                            </ul>
                        </div>
                    </div>

                    <div class="col-md-8 border">
                        <h4 class="mt-3">My Orders</h4>

                        <div class="dropdown mb-1 float-right">
                            <button style="background-color: gray" class="btn btn-secondary dropdown-toggle" type="button" id="dropdownMenuButton" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                Status
                            </button>
                            <div class="dropdown-menu" aria-labelledby="dropdownMenuButton">
                                <a class="dropdown-item ${statusID == 0 ? "active":""}" href="myorder?page=${page}&status=0&date=${date}">All</a>
                                <c:forEach items="${vecOrderStatus}" var="status">
                                    <a href="myorder?page=${page}&status=${status.getID()}&date=${date}" class="dropdown-item ${status.getID() == statusID ? "active":""}">${status.getName()}</a>
                                </c:forEach>
                            </div>
                        </div>
                        <label for="date">Date: </label>
                        <input type="date" id="date" onchange="search('', ${page}, ${statusID})" value="${date != null ? date : ""}">
                        <table class="table mt-3 table-striped">
                            <thead class="">
                                <tr>
                                    <th scope="col">#</th>
                                    <th onclick="search('TotalPrice', ${page}, ${statusID})" scope="col">Total Price</th>
                                    <th scope="col">Note</th>
                                    <th scope="col">Status</th>
                                    <th onclick="search('DayBuy', ${page}, ${statusID})" scope="col">Date</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:set var="index" value="1"></c:set>
                                <c:forEach items="${vecOrder}" var="order">
                                    <tr>
                                        <td>${index}</td>
                                        <td>${order.getTotalPrice()}</td>
                                        <td>${order.getNote()}</td>
                                        <td>${vecOrderStatus[order.getStatus()-1].getName()}</td>
                                        <td>${order.getDayBuy()}</td>
                                    </tr>
                                    <c:set var="index" value="${index + 1}"></c:set>
                                </c:forEach>
                            </tbody>
                        </table>
                        <ul class="pagination pagination-sm justify-content-end">
                            <li class="page-item mr-0"><a class="page-link" onclick="selectPage(${page > 1 ? page - 1:page}, ${categoryID})">Previous</a></li>
                                <c:forEach items="${pageNumbers}" var="pageNum">
                                <li class="page-item mr-0 ${page.equals(pageNum) ? "active":""}"><a class="page-link" onclick="selectPage(${pageNum}, ${categoryID})">${pageNum}</a></li>
                                    <c:set var="lastPage" value="${pageNum}"></c:set>
                                </c:forEach>
                            <li class="page-item mr-0"><a class="page-link" onclick="selectPage(${page == lastPage ? page:page+1}, ${categoryID})">Next</a></li>
                        </ul>
                    </div>
                </div>
            </div>
        </div>
    </body>

    <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.12.9/dist/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>

    <script>

                                function selectPage(page) {
                                    window.location.href = "myorder?page=" + page;
                                }
                                
                                function search(sortBy, page, status) {
                                    var date = document.getElementById('date').value;
                                    window.location.href = "myorder?page=" + page + "&status=" + status + "&sortby=" + sortBy + "&date=" + date;
                                }
    </script>
</html>
