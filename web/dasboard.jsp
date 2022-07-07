<%-- 
    Document   : dasboard
    Created on : 04-06-2022, 14:52:44
    Author     : Pham Van Trong
--%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta http-equiv="X-UA-Compatible" content="ie=edge">
        <title>Product Admin - Dashboard HTML Template</title>
        <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto:400,700">
        <!-- https://fonts.google.com/specimen/Roboto -->
        <link rel="stylesheet" href="css/fontawesome.min.css">
        <!-- https://fontawesome.com/ -->
        <link rel="stylesheet" href="css/bootstrap.min.css">
        <!-- https://getbootstrap.com/ -->
        <link rel="stylesheet" href="css/templatemo-style.css">
        <link
            href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i"
            rel="stylesheet">
        <!--
            Product Admin CSS Template
            https://templatemo.com/tm-524-product-admin
        -->
    </head>
    <style>
        .pagination a.active {
            background-color: #4CAF50;
            color: white;
        }
        .pagination a:hover:not(.active) {
            background-color: #FFF44F;
            color: black;
        }
        .search-box{
            position: absolute;
            height: 20px;
            border-radius:40px;
            padding: 10px;
        }
        .search-box:hover > .search-txt{
            width: 180px;
            padding: 0 6px;
        }
        .search-box:hover > .search-btn{
            color: white
        }
        .search-btn{
            color:blue;
            width: 40px;
            height:40px;
            border-radius: 50%;
            transition: 0.4s;
        }
        .search-txt{
            border:none;
            background:whitesmoke;
            outline:none;
            padding:0;
            color:black;
            font-size: 16px;
            transition: 0.4s;
            line-height: 40px;
            width: 0px;
        }
    </style>
    <body id="reportsPage">

        <div class="" id="home">
            <%@include file="components/NavbarAdmin.jsp" %>
            <div class="container">
                <div class="row">
                    <div class="col">
                        <p class="text-white mt-5 mb-5">Welcome back, <b>Admin</b></p>
                    </div>
                </div>
                <div class="container-fluid">
                    <div class="row">  
                        <div class="col-xl-4 col-md-6 mb-4">
                            <div class="card border-left-primary shadow h-100 py-2">
                                <div class="card-body">
                                    <div class="row no-gutters align-items-center">
                                        <div class="col mr-2">
                                            <div class="text-xs font-weight-bold text-primary text-uppercase mb-1">
                                                Accounts</div>
                                            <div class="h5 mb-0 font-weight-bold text-gray-800">${account}</div>
                                        </div>
                                        <div class="col-auto">
                                            <a href="accounts.jsp">
                                                <i class="fas fa-people-carry fa-2x text-gray-300"></i>
                                            </a>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="col-xl-4 col-md-6 mb-4">
                            <div class="card border-left-success shadow h-100 py-2">
                                <div class="card-body">
                                    <div class="row no-gutters align-items-center">
                                        <div class="col mr-2">
                                            <div class="text-xs font-weight-bold text-success text-uppercase mb-1">
                                                Total products</div>
                                            <div class="h5 mb-0 font-weight-bold text-gray-800">${product}</div>
                                        </div>
                                        <div class="col-auto">
                                            <a href="ProductsController">
                                                <i class="fas fa-project-diagram fa-2x "></i>
                                            </a>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="col-xl-4 col-md-6 mb-4">
                            <div class="card border-left-info shadow h-100 py-2">
                                <div class="card-body">
                                    <div class="row no-gutters align-items-center">
                                        <div class="col mr-2">
                                            <div class="text-xs font-weight-bold text-info text-uppercase mb-1">Access Time
                                            </div>
                                            <div class="row no-gutters align-items-center">
                                                <div class="col-auto">
                                                    <div class="h5 mb-0 mr-3 font-weight-bold text-gray-800">${view}</div>
                                                </div>
                                                <div class="col">
                                                    <div class="progress progress-sm mr-2">
                                                        <div class="progress-bar bg-info" role="progressbar"
                                                             style="width: 50%" aria-valuenow="50" aria-valuemin="0"
                                                             aria-valuemax="100"></div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="col-auto">
                                            <i class="fas fa-caret-square-up fa-2x text-gray-300"></i>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        
                    </div>
                </div>
                <!-- row -->
                <div class="row tm-content-row">
                    <div class="col-sm-12 col-md-12 col-lg-6 col-xl-6 tm-block-col">
                        <div class="tm-bg-primary-dark tm-block">
                            <p class="tm-block-title"   > First 6 months of the year revenue : <b class="claimedRight" maxlength="4" >${yearString}</b></p>
                            <canvas id="myBarChart" style="width:100%;max-width:700px"></canvas>
                        </div>
                    </div>
                    <div class="col-sm-12 col-md-12 col-lg-6 col-xl-6 tm-block-col">
                        <div class="tm-bg-primary-dark tm-block tm-block-taller">
                            <h2 class="tm-block-title"> Statistics of invoice numbers during the week  at  month <b class="claimedRights" max="1" >${monthString}</b> : <b class="claimedRight" maxlength="4" >${yearString}</b></h2>
                            <canvas id="myAreaChart" class ="chartjs-render-monitor" style="width:100%;max-width:700px"></canvas>
                        </div>
                    </div>
                    <div class="col-sm-12 col-md-12 col-lg-6 col-xl-6 tm-block-col">
                        <div class="tm-bg-primary-dark tm-block tm-block-taller">
                            <h2 class="tm-block-title">Storage Information</h2>
                            <div id="pieChartContainer">
                                <canvas id="myPieChart" class="chartjs-render-monitor" style="width:100%;max-width:700px"></canvas>
                            </div>                        
                        </div>
                    </div>
                    <div class="col-sm-12 col-md-12 col-lg-6 col-xl-6 tm-block-col">
                        <div class="tm-bg-primary-dark tm-block tm-block-taller tm-block-overflow">
                            <h2 class="tm-block-title">Notification List  <span class="badge badge-danger">${numOfOrder}</span>
                                <i class="fas fa-bell fa-fw"></i> </h2>
                            <div class="tm-notification-items">
                                <div class="media tm-notification-item">

                                    <div class="media-body">
                                        <p class="mb-2"><b>Order Watting</b>  <a href="ProcessOrders?check=1"
                                                                                 class="tm-notification-link">Detail</a>. Check new orders.</p>

                                    </div>
                                </div>
                                 <div class="media tm-notification-item">

                                    <div class="media-body">
                                        <p class="mb-2"><b>Order Packaging</b>   <a href="ProcessOrders?check=3"
                                                                                 class="tm-notification-link">Detail</a>. Check new orders.</p>

                                    </div>
                                </div>
                                <div class="media tm-notification-item">

                                    <div class="media-body">
                                        <p class="mb-2"><b>Order Shipping</b>   <a href="ProcessOrders?check=2"
                                                                                 class="tm-notification-link">Detail</a>. Check new orders.</p>

                                    </div>
                                </div>
                                <div class="media tm-notification-item">

                                    <div class="media-body">
                                        <p class="mb-2"><b>Order Success</b> and <a href="ProcessOrders?check=0"
                                                                                    class="tm-notification-link">Detail</a>. Check new orders.</p>

                                    </div>
                                </div>


                            </div>
                        </div>
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
    </div>
    <script src="vendor/jquery/jquery.min.js"></script>
    <script src="vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

    <!-- Core plugin JavaScript-->
    <script src="vendor/jquery-easing/jquery.easing.min.js"></script>

    <!-- Page level plugin JavaScript-->
    <script src="vendor/chart.js/Chart.min.js"></script>
    <script src="vendor/datatables/jquery.dataTables.js"></script>
    <script src="vendor/datatables/dataTables.bootstrap4.js"></script>

    <!-- Custom scripts for all pages-->
    <script src="js/sb-admin.min.js"></script>

    <!-- Demo scripts for this page-->
    <script src="js/demo/datatables-demo.js"></script>
    <script src="js/jquery-3.3.1.min.js"></script>
    <!-- https://jquery.com/download/ -->
    <script src="js/moment.min.js"></script>
    <!-- https://momentjs.com/ -->
    <script src="js/Chart.min.js"></script>
    <!-- http://www.chartjs.org/docs/latest/ -->
    <script src="js/bootstrap.min.js"></script>
    <!-- https://getbootstrap.com/ -->
    <script src="js/tooplate-scripts.js"></script>
    <script
        src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.9.4/Chart.js">
    </script>
    <script         src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>
    <script>
            // Bar Chart Example
            var ctx = document.getElementById("myBarChart");
            var myLineChart = new Chart(ctx, {
                type: 'bar',
                data: {
                    labels: ["January ", "February ", "March ", "April ", "May ", "June"],
                    datasets: [{
                            label: "Revenue",
                            backgroundColor: "rgba(2,117,216,1)",
                            borderColor: "rgba(2,117,216,1)",
                            data: [${revenue}],
                        }],
                },
                options: {
                    scales: {
                        xAxes: [{
                                time: {
                                    unit: 'month'
                                },
                                gridLines: {
                                    display: false
                                },
                                ticks: {
                                    maxTicksLimit: 6
                                }
                            }],
                        yAxes: [{
                                ticks: {
                                    min: 0,
                                    max: 900000000,
                                    maxTicksLimit: 5
                                },
                                gridLines: {
                                    display: true
                                }
                            }],
                    },
                    legend: {
                        display: false
                    }
                }
            });
    </script>
    <script>
        // Area Chart Example
        var ctx = document.getElementById("myAreaChart");
        var myLineChart = new Chart(ctx, {
            type: 'line',
            data: {
                labels: [${thu}],
                datasets: [{
                        label: "Total",
                        lineTension: 0.3,
                        backgroundColor: "rgba(2,117,216,0.2)",
                        borderColor: "rgba(2,117,216,1)",
                        pointRadius: 5,
                        pointBackgroundColor: "rgba(2,117,216,1)",
                        pointBorderColor: "rgba(255,255,255,0.8)",
                        pointHoverRadius: 5,
                        pointHoverBackgroundColor: "rgba(2,117,216,1)",
                        pointHitRadius: 50,
                        pointBorderWidth: 2,
                        data: [${data}],
                    }],
            },
            options: {
                scales: {
                    xAxes: [{
                            time: {
                                unit: 'date'
                            },
                            gridLines: {
                                display: false
                            },
                            ticks: {
                                maxTicksLimit: 7
                            }
                        }],
                    yAxes: [{
                            ticks: {
                                min: 0,
                                max: 20,
                                maxTicksLimit: 4
                            },
                            gridLines: {
                                color: "rgba(0, 0, 0, .125)",
                            }
                        }],
                },
                legend: {
                    display: false
                }
            }
        });
    </script>
    <script>
        Chart.defaults.global.defaultFontFamily = '-apple-system,system-ui,BlinkMacSystemFont,"Segoe UI",Roboto,"Helvetica Neue",Arial,sans-serif';
        Chart.defaults.global.defaultFontColor = '#292b2c';

// Pie Chart Example
        var ctx = document.getElementById("myPieChart");
        var myPieChart = new Chart(ctx, {
            type: 'pie',
            data: {
                labels: [${label}],
                datasets: [{
                        data: [${soluong}],
                        backgroundColor: ['#007bff', '#dc3545', '#ffc107', '#28a745', '#fefbd8', '#80ced6'],
                    }],
            },
        });
    </script>
    <script>
        Chart.defaults.global.defaultFontColor = 'white';
        let ctxLine,
                ctxBar,
                ctxPie,
                optionsLine,
                optionsBar,
                optionsPie,
                configLine,
                configBar,
                configPie,
                lineChart,
                barChart, pieChart;
        // DOM is ready
        $(function () {
            drawLineChart(); // Line Chart
            drawBarChart(); // Bar Chart
            drawPieChart(); // Pie Chart

            $(window).resize(function () {
                updateLineChart();
                updateBarChart();
            });
        })
    </script>
    <script>
        $(document).ready(function () {

            $('.claimedRight').each(function (f) {

                var newstr = $(this).text().substring(0, 4);
                $(this).text(newstr);

            });
        })
    </script>
    <script>
        $(document).ready(function () {

            $('.claimedRights').each(function (f) {

                var newstr = $(this).text().substring(0, 1);
                $(this).text(newstr);

            });
        })
    </script>
</body>
</html>
