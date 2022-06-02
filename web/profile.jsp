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

        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
        <link rel="stylesheet" href="https://cdn.datatables.net/1.10.22/css/dataTables.bootstrap4.min.css">
        <script src="https://cdn.datatables.net/1.10.22/js/jquery.dataTables.min.js"></script>
        <script src="https://cdn.datatables.net/1.10.22/js/dataTables.bootstrap4.min.js"></script>
    </head>

    <body>
        <!-- Topbar Start -->
        <%@include file="components/Navbar.jsp" %>
        <!-- Topbar End -->

        <!-- Cart Start -->
        <div class="container-fluid pt-5">
            <div class="row px-xl-5">

                <div class="col-lg-2 border-dark">
                    <figure class="figure flex-fill">
                        <img src="img/cat-1.jpg" class="figure-img img-fluid rounded w-25" alt="A generic square placeholder image with rounded corners in a figure.">
                        <figcaption class="figure-caption figure">Pham Van Dung</figcaption>
                    </figure>
                    <div class="dropdown-divider"></div>

                    <nav class="nav flex-column">
                        <a class="nav-link active" href="#"><i class="bi bi-person-circle"></i> User Info</a>
                        <a class="nav-link" href="#"><i class="bi bi-cart-check"></i> My Order</a>
                        <a class="nav-link" href="#"><i class="bi bi-geo-alt-fill"></i> Address List</a>
                        <a class="nav-link" href="#">Order List</a>
                        <a class="nav-link" href="#">Product List</a>
                    </nav>
                </div>

                <div class="col-lg-10 table-responsive mb-5">
                    <div class="table-scroll mb-3">
                        <table id="sortTable" class="table table-bordered text-center mb-0" cellspacing="0" width="100%">
                            <thead class="bg-secondary text-dark">
                                <tr>
                                    <th class="th-sm"></th>
                                    <th class="th-sm">Products</th>
                                    <th class="th-sm">Price</th>
                                    <th class="th-sm">Amount</th>
                                    <th class="th-sm">Total</th>
                                    <th class="th-sm">Action</th>
                                </tr>
                            </thead>
                            <tbody class="align-middle">
                                <tr>
                                    <td class="align-middle"><input type="checkbox"></td>
                                    <td class="align-middle">Colorful Stylish Shirt</td>
                                    <td class="align-middle">$150</td>
                                    <td class="align-middle">100</td>
                                    <td class="align-middle">$150</td>
                                    <td class="align-middle"><button class="btn btn-sm btn-primary"><i class="fa fa-times"></i></button></td>
                                </tr>
                                <tr>
                                    <td class="align-middle"><input type="checkbox"></td>
                                    <td class="align-middle">Colorful Stylish Shirt</td>
                                    <td class="align-middle">$150</td>
                                    <td class="align-middle">100</td>
                                    <td class="align-middle">$150</td>
                                    <td class="align-middle"><button class="btn btn-sm btn-primary"><i class="fa fa-times"></i></button></td>
                                </tr>
                                <tr>
                                    <td class="align-middle"><input type="checkbox"></td>
                                    <td class="align-middle">Colorful Stylish Shirt</td>
                                    <td class="align-middle">$150</td>
                                    <td class="align-middle">100</td>
                                    <td class="align-middle">$150</td>
                                    <td class="align-middle"><button class="btn btn-sm btn-primary"><i class="fa fa-times"></i></button></td>
                                </tr>
                                <tr>
                                    <td class="align-middle"><input type="checkbox"></td>
                                    <td class="align-middle">Colorful Stylish Shirt</td>
                                    <td class="align-middle">$150</td>
                                    <td class="align-middle">100</td>
                                    <td class="align-middle">$150</td>
                                    <td class="align-middle"><button class="btn btn-sm btn-primary"><i class="fa fa-times"></i></button></td>
                                </tr>
                                <tr>
                                    <td class="align-middle"><input type="checkbox"></td>
                                    <td class="align-middle">Colorful Stylish Shirt</td>
                                    <td class="align-middle">$150</td>
                                    <td class="align-middle">100</td>
                                    <td class="align-middle">$150</td>
                                    <td class="align-middle"><button class="btn btn-sm btn-primary"><i class="fa fa-times"></i></button></td>
                                </tr>
                                <tr>
                                    <td class="align-middle"><input type="checkbox"></td>
                                    <td class="align-middle">Colorful Stylish Shirt</td>
                                    <td class="align-middle">$150</td>
                                    <td class="align-middle">100</td>
                                    <td class="align-middle">$150</td>
                                    <td class="align-middle"><button class="btn btn-sm btn-primary"><i class="fa fa-times"></i></button></td>
                                </tr>
                                <tr>
                                    <td class="align-middle"><input type="checkbox"></td>
                                    <td class="align-middle">Colorful Stylish Shirt</td>
                                    <td class="align-middle">$150</td>
                                    <td class="align-middle">100</td>
                                    <td class="align-middle">$150</td>
                                    <td class="align-middle"><button class="btn btn-sm btn-primary"><i class="fa fa-times"></i></button></td>
                                </tr>
                                <tr>
                                    <td class="align-middle"><input type="checkbox"></td>
                                    <td class="align-middle">Colorful Stylish Shirt</td>
                                    <td class="align-middle">$150</td>
                                    <td class="align-middle">100</td>
                                    <td class="align-middle">$150</td>
                                    <td class="align-middle"><button class="btn btn-sm btn-primary"><i class="fa fa-times"></i></button></td>
                                </tr>
                                <tr>
                                    <td class="align-middle"><input type="checkbox"></td>
                                    <td class="align-middle">Colorful Stylish Shirt</td>
                                    <td class="align-middle">$150</td>
                                    <td class="align-middle">100</td>
                                    <td class="align-middle">$150</td>
                                    <td class="align-middle"><button class="btn btn-sm btn-primary"><i class="fa fa-times"></i></button></td>
                                </tr>
                                <tr>
                                    <td class="align-middle"><input type="checkbox"></td>
                                    <td class="align-middle">Colorful Stylish Shirt</td>
                                    <td class="align-middle">$150</td>
                                    <td class="align-middle">100</td>
                                    <td class="align-middle">$150</td>
                                    <td class="align-middle"><button class="btn btn-sm btn-primary"><i class="fa fa-times"></i></button></td>
                                </tr>
                                <tr>
                                    <td class="align-middle"><input type="checkbox"></td>
                                    <td class="align-middle">Colorful Stylish Shirt</td>
                                    <td class="align-middle">$150</td>
                                    <td class="align-middle">100</td>
                                    <td class="align-middle">$150</td>
                                    <td class="align-middle"><button class="btn btn-sm btn-primary"><i class="fa fa-times"></i></button></td>
                                </tr>
                                <tr>
                                    <td class="align-middle"><input type="checkbox"></td>
                                    <td class="align-middle">Colorful Stylish Shirt</td>
                                    <td class="align-middle">$150</td>
                                    <td class="align-middle">100</td>
                                    <td class="align-middle">$150</td>
                                    <td class="align-middle"><button class="btn btn-sm btn-primary"><i class="fa fa-times"></i></button></td>
                                </tr>
                            </tbody>
                        </table>
                    </div>
                    <button class="btn btn-block btn-primary">Delete Selected Product</button>
                    <button class="btn btn-block btn-primary">Add New Product</button>
                </div>
            </div>
        </div>
        <!-- Cart End -->


        <!-- Footer Start -->
          <%@include file="components/Footer.jsp" %>


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

        <!--Style-->
        <style>
            .table-scroll {
                overflow-y: scroll; 
                height: 70vh;
            }

            .table-scroll::-webkit-scrollbar {
                width: 6px;
                background-color: #F5F5F5;
            } 

            .table-scroll::-webkit-scrollbar-thumb {
                background-color: #d19c97;
            }

            .table-scroll::-webkit-scrollbar-track {
                -webkit-box-shadow: inset 0 0 6px rgba(0,0,0,0.3);
                background-color: #F5F5F5;
            }
        </style>

        <script>
            $('#sortTable').DataTable();
        </script>
    </body>
</html>