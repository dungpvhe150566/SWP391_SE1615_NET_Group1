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
        <!--
            Product Admin CSS Template
            https://templatemo.com/tm-524-product-admin
        -->
    </head>

    <body id="reportsPage">
        <div class="" id="home">
             <%@include file="components/NavbarAdmin.jsp" %>
            <div class="container">
                <div class="row">
                    <div class="col">
                        <p class="text-white mt-5 mb-5">Welcome back, <b>Admin</b></p>
                    </div>
                </div>
                <!-- row -->
                <div class="row tm-content-row">
                    <div class="col-sm-12 col-md-12 col-lg-6 col-xl-6 tm-block-col">
                        <div class="tm-bg-primary-dark tm-block">
                            <h2 class="tm-block-title">Latest Hits</h2>
                            <canvas id="myChart" style="width:100%;max-width:700px"></canvas>
                        </div>
                    </div>
                    <div class="col-sm-12 col-md-12 col-lg-6 col-xl-6 tm-block-col">
                        <div class="tm-bg-primary-dark tm-block">
                            <h2 class="tm-block-title">Performance</h2>
                           <canvas id="myAreaChart" style="width:100%;max-width:700px"></canvas>
                        </div>
                    </div>
                    <div class="col-sm-12 col-md-12 col-lg-6 col-xl-6 tm-block-col">
                        <div class="tm-bg-primary-dark tm-block tm-block-taller">
                            <h2 class="tm-block-title">Storage Information</h2>
                            <div id="pieChartContainer">
                                <canvas id="pieChart" class="chartjs-render-monitor" width="200" height="200"></canvas>
                            </div>                        
                        </div>
                    </div>
                    <div class="col-sm-12 col-md-12 col-lg-6 col-xl-6 tm-block-col">
                        <div class="tm-bg-primary-dark tm-block tm-block-taller tm-block-overflow">
                            <h2 class="tm-block-title">Notification List</h2>
                            <div class="tm-notification-items">
                                <div class="media tm-notification-item">
                                    <div class="tm-gray-circle"><img src="img/notification-01.jpg" alt="Avatar Image" class="rounded-circle"></div>
                                    <div class="media-body">
                                        <p class="mb-2"><b>Jessica</b> and <b>6 others</b> sent you new <a href="#"
                                                                                                           class="tm-notification-link">product updates</a>. Check new orders.</p>
                                        <span class="tm-small tm-text-color-secondary">6h ago.</span>
                                    </div>
                                </div>

                            </div>
                        </div>
                    </div>
                    <div class="col-12 tm-block-col">
                        <div class="tm-bg-primary-dark tm-block tm-block-taller tm-block-scroll">
                            <h2 class="tm-block-title">Orders List</h2>
                            <table class="table">
                                <thead>
                                    <tr>
                                        <th scope="col">ORDER NO.</th>
                                        <th scope="col">STATUS</th>
                                        <th scope="col">OPERATORS</th>
                                        <th scope="col">LOCATION</th>
                                        <th scope="col">DISTANCE</th>
                                        <th scope="col">START DATE</th>
                                        <th scope="col">EST DELIVERY DUE</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <tr>
                                        <th scope="row"><b>#122349</b></th>
                                        <td>
                                            <div class="tm-status-circle moving">
                                            </div>Moving
                                        </td>
                                        <td><b>Oliver Trag</b></td>
                                        <td><b>London, UK</b></td>
                                        <td><b>485 km</b></td>
                                        <td>16:00, 12 NOV 2018</td>
                                        <td>08:00, 18 NOV 2018</td>
                                    </tr>

                                </tbody>
                            </table>
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
        <script>
            var xValues = [30, 60, 90, 120, 150,180,210, 240, 270, 300, 330, 360];

            new Chart("myChart", {
                type: "line",
                data: {
                    labels: xValues,
                    datasets: [{
                            data: [${view}],
                            borderColor: "red",
                            fill: false
                        }, {
                            data: [1600, 1700, 1700, 1900, 2000, 2700, 4000, 5000, 6000, 7000,6000,440,600],
                            borderColor: "green",
                            fill: false
                        }, {
                            data: [300, 700, 2000, 5000, 6000, 4000, 2000, 1000, 200, 100,6000,440,600],
                            borderColor: "blue",
                            fill: false
                        }]
                },
                options: {
                    legend: {display: false}
                }
            });</script>
        <script>
            // Area Chart Example
            var ctx = document.getElementById("myAreaChart");
            var myLineChart = new Chart(ctx, {
                type: 'line',
                data: {
                    labels: [${thu}],
                    datasets: [{
                            label: "Sessions",
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
    </body>
</html>
