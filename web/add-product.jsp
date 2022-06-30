<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
        <meta http-equiv="X-UA-Compatible" content="ie=edge" />
        <title>Add Product - Dashboard HTML Template</title>
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
                        <form method="post" action="addproduct" class="tm-edit-product-form" enctype="multipart/form-data">
                            <input type="hidden" name="do" value="addproduct">
                            ${message}
                            <div class="row tm-edit-product-row">
                                <div class="col-xl-6 col-lg-6 col-md-12">
                                    <div class="form-group mb-3">
                                        <label
                                            for="product_name"
                                            >Product Name
                                        </label>
                                        <input
                                            id="product_name"
                                            name="product_name"
                                            type="text"
                                            class="form-control validate"
                                            maxlength="1000"
                                            required
                                            />
                                    </div>
                                    <div class="form-group mb-3">
                                        <label
                                            for="description"
                                            >Description</label
                                        >
                                        <textarea
                                            class="form-control validate"
                                            name="description"
                                            rows="3"
                                            maxlength="2000"
                                            required
                                            ></textarea>
                                    </div>
                                    <div class="row">
                                        <div class="form-group mb-3 col-xs-12 col-sm-6">
                                            <label
                                                for="original_price"
                                                >Original Price (VND)
                                            </label>
                                            <input
                                                id="original_price"
                                                name="original_price"
                                                type="number"
                                                onkeyup="if(this.value<0)this.value=0"
                                                min="0"
                                                max="1000000000"
                                                class="form-control validate"
                                                data-large-mode="true"
                                                required
                                                />
                                        </div>
                                        <div class="form-group mb-3 col-xs-12 col-sm-6">
                                            <label
                                                for="sell_price"
                                                >Sell Price (VND)
                                            </label>
                                            <input
                                                id="sell_price"
                                                name="sell_price"
                                                type="number"
                                                onkeyup="if(this.value<0)this.value=0"
                                                min="0"
                                                max="1000000000"
                                                class="form-control validate"
                                                data-large-mode="true"
                                                required
                                                />
                                        </div>
                                    </div>
                                    <div class="form-group mb-3">
                                        <label
                                            for="sale_percent"
                                            >Sale Percent (%)
                                        </label>
                                        <input
                                            id="sale_percent"
                                            name="sale_percent"
                                            type="number"
                                            onkeyup="if(this.value<0)this.value=0"
                                            min="0"
                                            max="100"
                                            class="form-control validate"
                                            data-large-mode="true"
                                            placeholder="100%"
                                            required
                                            />
                                    </div>
                                    <div class="form-group mb-3">
                                        <label
                                            for="category"
                                            >Category</label
                                        >
                                        <select
                                            class="custom-select tm-select-accounts"
                                            id="category"
                                            name="category"
                                            required
                                            >
                                            <c:forEach items="${categoryList}" var="category">
                                                <option value="${category.getCategoryID()}">${category.getCategoryName()}</option>
                                            </c:forEach>
                                        </select>
                                    </div>
                                    <div class="form-group mb-3">
                                        <label
                                            for="manufacture"
                                            >Manufacture</label
                                        >
                                        <select
                                            class="custom-select tm-select-accounts"
                                            id="manufacture"
                                            name="manufacture"
                                            required
                                            >
                                            <c:forEach items="${manufacturerList}" var="manufacturer">
                                                <option value="${manufacturer.getManufacturerID()}">${manufacturer.getManufacturerName()}</option>
                                            </c:forEach>
                                        </select>
                                    </div>
                                    <div class="form-group mb-3">
                                        <label
                                            for="amount"
                                            >Quantity
                                        </label>
                                        <input
                                            id="amount"
                                            name="amount"
                                            onkeyup="if(this.value<0)this.value=0"
                                            min="0"
                                            max="1000"
                                            type="number"
                                            class="form-control validate"
                                            data-large-mode="true"
                                            required
                                            />
                                    </div>
                                </div>
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
                                            value="UPLOAD PRODUCT IMAGE"
                                            onclick="document.getElementById('fileInput').click();"
                                            />
                                    </div>
                                    <div class="row mt-3">
                                        <div class="form-group mb-3 col-xs-12 col-sm-4">
                                            <label
                                                for="height"
                                                >Height
                                            </label>
                                            <input
                                                id="height"
                                                name="height"
                                                type="number"
                                                onkeyup="if(this.value<0)this.value=0"
                                                min="0"
                                                max="1000000"
                                                step="any"
                                                class="form-control validate"
                                                data-large-mode="true"
                                                required
                                                />
                                        </div>
                                        <div class="form-group mb-3 col-xs-12 col-sm-4">
                                            <label
                                                for="width"
                                                >Width
                                            </label>
                                            <input
                                                id="width"
                                                name="width"
                                                type="number"
                                                onkeyup="if(this.value<0)this.value=0"
                                                min="0"
                                                max="1000000"
                                                step="any"
                                                class="form-control validate"
                                                data-large-mode="true"
                                                required
                                                />
                                        </div>
                                        <div class="form-group mb-3 col-xs-12 col-sm-4">
                                            <label
                                                for="weight"
                                                >Weight
                                            </label>
                                            <input
                                                id="weight"
                                                name="weight"
                                                type="number"
                                                onkeyup="if(this.value<0)this.value=0"
                                                min="0"
                                                max="1000000"
                                                step="any"
                                                class="form-control validate"
                                                data-large-mode="true"
                                                required
                                                />
                                        </div>
                                    </div>
                                </div>
                                <div class="col-12">
                                    <button type="submit" class="btn btn-primary btn-block text-uppercase">Add Product Now</button>
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
