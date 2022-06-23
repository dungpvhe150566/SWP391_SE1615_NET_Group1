<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <style>
            .col-sm-8 p{
                overflow: hidden;
                display: -webkit-box;
                -webkit-line-clamp: 3;
                -webkit-box-orient: vertical;
            }
        </style>
          <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css">
        <jsp:useBean id="a" class="dao.impl.BlogDAOImpl" scope="request"></jsp:useBean>
        </head>
        <body>
            <nav style="margin-bottom: 20px" class="navbar navbar-expand-lg navbar-light bg-light">
            <c:if test="${sessionScope.account == null}">
                <a class="navbar-brand" href="paging">Home</a>
                <a class="navbar-brand" href="Login.jsp">Login</a>
            </c:if>
            <c:if test="${sessionScope.account != null}">
                <a class="navbar-brand" href="paging">Home</a>
                <a class="navbar-brand" href="favorite">Favorite</a>
                <c:if test="${sessionScope.account.isCreator==1}">
                    <a class="navbar-brand" href="Creator.jsp">Creator</a>
                </c:if>
                <a class="navbar-brand" href="logout">Logout</a>
            </c:if>
            <div class="collapse navbar-collapse" id="navbarSupportedContent">
                <ul class="navbar-nav mr-auto">
                    <li class="nav-item active">
                    </li>
                </ul>
                <form class="form-inline my-2 my-lg-0">
                    <input class="form-control mr-sm-2" type="search" placeholder="Search" aria-label="Search">
                    <button class="btn btn-outline-success my-2 my-sm-0" type="submit">Search</button>
                </form>
            </div>
        </nav>
        <div class="container">
            <!--begin of menu-->
            <!--end of menu-->
            <!--begin of list article-->
            <c:forEach items="${listP}" var="o">
                <div class="row">
                    <div class="col-sm-4">
                        <a href="#" class="">
                            <img class="col-sm-12" src="image/${o.getImageLink()}">
                        </a>
                    </div>
                    <div class="col-sm-8">
                        <h3 class="title">${o.title}</h3>
                        <p>${o.getContent()}</p>
                        <c:if test="${sessionScope.account != null}">
                            <a href="like?aid=${o.id}" style="text-decoration: none; margin-right: 10px">
                                <span style="font-size:30px">&#128077;</span>
                            </a>
                        </c:if>
                        <c:if test="${sessionScope.account == null}">
                            <a href="Login.jsp" style="text-decoration: none; margin-right: 10px">
                                <span style="font-size:30px">&#128077;</span>
                            </a>
                        </c:if>
                    </div>
                </div>
            </c:forEach>
            <nav style="margin: 20px auto; width: 500px" aria-label="...">
                <ul class="pagination pagination-lg">
                    <c:forEach begin="1" end="${a.totalPage()}" var="i">
                        <li class="page-item ${indexpage==i?"active":""}" ><a class="page-link" href="PagingController?index=${i}">${i}</a></li>
                        </c:forEach>
                </ul>
            </nav>
            <!--end of list article-->
        </div>
        <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>

    </body>
</html>
