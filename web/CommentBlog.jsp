<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <!--Favicon-->
        <title>Add Feedback</title>
        <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
        <script src="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
        <script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
        <link href="css/style_1.css" rel="stylesheet" type="text/css"/>
        <style>
            .rating {
                display: inline-block;
                position: relative;
                height: 50px;
                line-height: 50px;
                font-size: 50px;
            }

            .rating label {
                position: absolute;
                top: 0;
                left: 0;
                height: 100%;
                cursor: pointer;
            }

            .rating label:last-child {
                position: static;
            }

            .rating label:nth-child(1) {
                z-index: 5;
            }

            .rating label:nth-child(2) {
                z-index: 4;
            }

            .rating label:nth-child(3) {
                z-index: 3;
            }

            .rating label:nth-child(4) {
                z-index: 2;
            }

            .rating label:nth-child(5) {
                z-index: 1;
            }

            .rating label input {
                position: absolute;
                top: 0;
                left: 0;
                opacity: 0;
            }

            .rating label .icon {
                float: left;
                color: transparent;
            }

            .rating label:last-child .icon {
                color: #000;
            }

            .rating:not(:hover) label input:checked ~ .icon,
            .rating:hover label:hover input ~ .icon {
                color: #09f;
            }

            .rating label input:focus:not(:checked) ~ .icon:last-child {
                color: #000;
                text-shadow: 0 0 5px #09f;
            }
        </style>
        <script>
            /* for submit feedback */
            function submitFeedback() {
                document.getElementById('submit-feedback').submit();
            }

        </script>
    </head>
    <body>
        <div class="container">
            <div class="row mt-3">
                <div class="col-md-6">
                    <img src="image/${blog.getImageLink()}" style="width: 500px">
                    <div class="card-body p-5">
                        <h3 class="title mb-3">${blog.getTitle()}</h3>

                        <hr>
                    </div>
                </div>
                <div class="col-md-6">
                    <div class="card">
                        <div class="feedback-form mt-5 mb-5 ml-5 mr-5">

                            <div class="header">
                                <h3 class="text-success">Add Comment for blog</h3>
                            </div>

                            <div class="feedback-text">
                                <form action="SubmitCommentControl" id="submit-feedback" method="POST" onsubmit="submitFeedback()" >                                    
                                    <label for="Comment-text" class="form-label">Your Comment(*): </label>
                                    <textarea name="Comment-text" maxlength="1000" class="form-control" id="feedback-text" rows="5" placeholder="Your Comment here(*)" required=""></textarea>
                                    <input type="hidden" id="star-value" name="star-value">
                                    <input type="hidden" id="blogId" name="blogId" value="${blog.getID()}">
                                    <input type="submit" class="btn btn-success mt-5" value="Submit">   
                                    <a href="ShopController" class="btn btn-success mt-5" >Home</a>
                                    <c:if test="${fb==""}">
                                         <p style="color: red;" >${mess}</p>
                                        
                                    </c:if> 
                                </form>
                            </div>

                        </div>

                    </div>
                </div>
            </div>

        </div>
    </body>
</html>