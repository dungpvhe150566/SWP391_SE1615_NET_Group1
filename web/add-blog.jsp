<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
        <meta http-equiv="X-UA-Compatible" content="ie=edge" />
        <title>Add Blog</title>
        <link
            rel="stylesheet"
            href="https://fonts.googleapis.com/css?family=Roboto:400,700"
            />
        <!-- https://fonts.google.com/specimen/Roboto -->
        <link rel="stylesheet" href="css/fontawesome.min.css" />
        <!-- https://fontawesome.com/ -->
        <link rel="stylesheet" href="jquery-ui-datepicker/jquery-ui.min.css" type="text/css" />
        <!-- http://api.jqueryui.com/datepicker/ -->
        <link rel="stylesheet" href="css/bootstrap.min.css" />
        <!-- https://getbootstrap.com/ -->
        <link rel="stylesheet" href="css/templatemo-style.css">
        <!--
            Product Admin CSS Template
            https://templatemo.com/tm-524-product-admin
        -->
    </head>

    <body>
        <%@include file="components/NavbarAdmin.jsp" %>
        <div class="container tm-mt-big tm-mb-big">
            <div class="row">
                <div class="col-xl-9 col-lg-10 col-md-12 col-sm-12 mx-auto">
                    <div class="tm-bg-primary-dark tm-block tm-block-h-auto">
                        <div class="row">
                            <div class="col-12">
                                <h2 class="tm-block-title d-inline-block">Add Product</h2>
                            </div>
                        </div>
                        <form method="post" action="addblog?do=addblog" class="tm-edit-product-form" enctype="multipart/form-data">
                            <input type="hidden" name="do" value="addblog">
                            ${message}
                            <div class="row tm-edit-product-row">
                                <div class="col-xl-6 col-lg-6 col-md-12 mx-auto mb-4">
                                    <div class="form-group">
                                        <label
                                            for="previewImage"
                                            >Image
                                        </label>
                                        <div class="tm-product-img-dummy mx-auto">
                                            <img
                                                onclick="document.getElementById('fileInput').click();"
                                                id="previewImage"
                                                style="max-height: 100%; width: 100%;"
                                                >
                                        </div>
                                    </div>

                                    <div class="custom-file mt-1 mb-3">
                                        <input name="fileInput" id="fileInput" type="file" onchange="showPreview(event)" style="display:none;" />
                                        <input
                                            type="button"
                                            class="btn btn-primary btn-block mx-auto"
                                            value="UPLOAD IMAGE"
                                            onclick="document.getElementById('fileInput').click();"
                                            />
                                    </div>
                                </div>
                                <div class="col-xl-12 col-lg-12 col-md-12">
                                    <div class="form-group mb-3">
                                        <label
                                            for="product_name"
                                            >Title
                                        </label>
                                        <input
                                            id="product_name"
                                            name="title"
                                            type="text"
                                            class="form-control validate"
                                            maxlength="2500"
                                            required
                                            />
                                    </div>
                                    <div class="form-group mb-3">
                                        <label
                                            for="description"
                                            >Content</label
                                        >
                                        <textarea
                                            class="form-control validate"
                                            name="content"
                                            rows="3"
                                            maxlength="2500"
                                            required
                                            ></textarea>
                                    </div>
                                </div>
                                <div class="col-12">
                                    <button type="submit" class="btn btn-primary btn-block text-uppercase">Add Blog Now</button>
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
        <footer class="tm-footer row tm-mt-small">
            <div class="col-12 font-weight-light">
                <p class="text-center text-white mb-0 px-4 small">
                    Copyright &copy; <b>2018</b> All rights reserved. 

                    Design: <a rel="nofollow noopener" href="https://templatemo.com" class="tm-footer-link">Template Mo</a>
                </p>
            </div>
        </footer> 

        <script src="js/jquery-3.3.1.min.js"></script>
        <!-- https://jquery.com/download/ -->
        <script src="jquery-ui-datepicker/jquery-ui.min.js"></script>
        <!-- https://jqueryui.com/download/ -->
        <script src="js/bootstrap.min.js"></script>
        <!-- https://getbootstrap.com/ -->
        <script>
                                                $(function () {
                                                    $("#expire_date").datepicker();
                                                });
        </script>

        <script>
            function showPreview(event) {
                if (event.target.files.length > 0) {
                    var src = URL.createObjectURL(event.target.files[0]);
                    var preview = document.getElementById("previewImage");
                    preview.src = src;
                }
            }
        </script>
    </body>
</html>
