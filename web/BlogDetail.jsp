<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
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
        <title>JSP Page</title>
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
        <div class="container">
            <div class="row">
                <div class="col-sm-9">
                    <div class="container">
                        <h3 class="title mb-3" style="text-align: center">${detailBlog.getTitle()}</h3>

                        <article class="gallery-wrap"> 
                            <div class="img-big-wrap">
                                <div> <a href="#"><img src="image/${detailBlog.getImageLink()}"></a></div>
                            </div><br> <!-- slider-product.// -->
                            <div class="img-small-wrap">
                            </div> <!-- slider-nav.// -->
                        </article> <!-- gallery-wrap .end// -->

                        <hr>
                        <dl class="item-property">
                            <dd><p>
                                    ${detailBlog.getContent()}
                                </p></dd>
                        </dl>

                        <hr>
                        <div class="nav nav-tabs justify-content-center border-secondary mb-4">
                            <a href="SubmitCommentControl?BlogId=${detailBlog.getID()}" class="nav-item nav-link">Add Comment</a>
                            <a class="nav-item nav-link" data-toggle="tab" href="#tab-pane-3">Reviews (${comment.size()})</a>
                        </div>
                        <div class="tab-content">
                            <div class="tab-pane fade" id="tab-pane-3">
                                <div class="row">
                                    <div class="col-md-6">
                                        <h4 class="mb-4">${comment.size()} Review for this Blog</h4>


                                        <c:forEach items="${comment}" var="c">

                                            <div class="media mb-4">
                                                <img src="image/Other/ava1.jpg" alt="Image" class="img-fluid mr-3 mt-1" style="width: 45px;">
                                                <div class="media-body">
                                                    <h6>${c.getUserName()}<small> - <i>${c.getDateComment()}</i></small></h6>
                                                    <div class="text-primary mb-2">
                                                    </div>
                                                    <p>${c.getCommentDetail()}</p>

                                                </div>
                                            </div>
                                        </c:forEach>

                                    </div>
                                    <!--Send Review, Question for this Product-->
                                </div>
                            </div>
                        </div>

                    </div>
                </div>
            </div>
        </div>

        <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>
        <%@include file="components/Footer.jsp" %>
    </body>
</html>