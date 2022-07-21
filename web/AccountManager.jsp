<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <title>Management Account</title>
        <link href="css/Dashboard.css" rel="stylesheet" type="text/css"/>
        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.3.1/css/all.css" integrity="sha384-mzrmE5qonljUremFsqc01SB46JvROS7bZs3IO2EmfFsd15uHvIt+Y8vEf7N7fWAU" crossorigin="anonymous">
        <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto|Varela+Round">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css">
        <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
        <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
        <link href="css/AccountManager.css" rel="stylesheet" type="text/css"/>
        <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"></script>

        <script>
            $(document).ready(function () {
                $('[data-toggle="tooltip"]').tooltip();
            });
            function search() {
                var searchText = document.getElementById("search_Account").value;
                location.href = "searchInAccountController?text=" + searchText;
                return false;
            }
        </script>
    </head>
    <body>

        <div class="container-fluid">
            <div class="row">
                <div class="col-2" style="background-color: #ebebf2;">
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

                </div>
                <div class="col-10">
                    <div class="container">

                        <div class="row">
                            <div class="table-responsive">
                                <div class="table-wrapper">
                                    <h2 style="font-family: Brush Script MT; text-align: center; font-size: 60px">Account manager</h2>
                                    <br>
                                    <table class="table table-striped table-hover">
                                        <div class="d-flex justify-content-center h-100">
                                            <div class="searchbar">
                                                <input class="search_input" type="text" id="search_Account" maxlength="50" placeholder="Search by user name..." value="${txt}">
                                                <button onclick="search();" class="search_icon"><i class="fas fa-search"></i></button>
                                            </div>
                                        </div>
                                        <thead>
                                            <tr>
                                                <th>ID</th>
                                                <th>User Name</th>						
                                                <th>Role</th>
                                                    <c:if test="${sessionScope.user.getIsAdmin() == 1}">
                                                    <th>Action</th>

                                                </c:if>
                                            </tr>
                                        </thead>
                                        <c:if test="${test!=0|| isseller!=0 ||isadmin!=0}">
                                            <p style="color: red;" >${mes}</p>
                                        </c:if>
                                        <c:if test="${test==0|| isseller==0 ||isadmin==0}">
                                            <p style="color: red;" >${mesde}</p>
                                        </c:if>
                                        <c:if test="${test==true1}">
                                            <p style="color: red;" >${mesde1}</p>
                                        </c:if>
                                        <tbody>
                                            <c:forEach items="${list}" var="o">
                                                <tr>
                                                    <td>${o.getUserID()}</td>
                                                    <td><a href="#">${o.getUsername()}</a></td>
                                                    <td style="width: 200px;">
                                                        <c:if test="${o.getIsSeller()==1}">
                                                            <a href="#" class="btn btn-secondary btn-sm active" role="button" aria-pressed="true">Seller</a>
                                                        </c:if>
                                                        <c:if test="${o.getIsAdmin()==1}">
                                                            <a href="#" class="btn btn-secondary btn-sm active" role="button" aria-pressed="true">Admin</a>
                                                        </c:if>
                                                    </td>
                                                    <c:if test="${sessionScope.user.getIsAdmin() == 1}">
                                                        <td>
                                                            <a href="EditAccountController?UserID=${o.getUserID()}"><button type="button" class="btn btn-outline-info  btn-sm">Edit</button></a>
                                                            <form action="deleteAccount" method="POST" style="display:inline">
                                                                <a> <input type="submit" class="btn btn-outline-danger btn-sm" value="Delete" onclick="if (confirm('Are you sure you want to delete?'))
                                                                            form.action = 'DeleteAccountController?UserID=${o.getUserID()}';
                                                                        else
                                                                            return false;"/>  
                                                                </a>   
                                                            </form>
                                                        </td>

                                                    </c:if>

                                                </tr>
                                            </c:forEach>
                                        </tbody>
                                    </table>
                                </div>
                            </div>
                            <c:if test="${totalPage!=0}">
                                <div class="col-12 pb-1">
                                    <nav aria-label="Page navigation">
                                        <ul class="pagination justify-content-center mb-3">
                                            <li class="page-item ${indexPage==1?'disabled':''}">
                                                <a class="page-link" href="AccountManagerController?indexPage=${indexPage-1}" aria-label="Previous">
                                                    <span aria-hidden="true">&laquo;</span>
                                                    <span class="sr-only">Previous</span>
                                                </a>
                                            </li>
                                            <c:forEach var="i" begin="1" end="${totalPage}"  >
                                                <li class="page-item ${indexPage==i?'active':''}">
                                                    <a class="page-link" href="AccountManagerController?indexPage=${i}">${i}</a></li>
                                                </c:forEach>
                                            <li class="page-item ${indexPage==totalPage?'disabled':''}">
                                                <a class="page-link" href="AccountManagerController?indexPage=${indexPage+1}" aria-label="Next">
                                                    <span aria-hidden="true">&raquo;</span>
                                                    <span class="sr-only">Next</span>
                                                </a>  
                                            </li>
                                        </ul>
                                    </nav>
                                </div>
                            </c:if>
                            <c:if test="${totalPage==0}">
                                ${mess}
                            </c:if>  

                        </div>
                    </div>

                </div>  
            </div>
        </div>
    </body>
</html>
