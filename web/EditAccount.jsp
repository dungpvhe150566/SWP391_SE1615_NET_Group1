<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <!--Favicon-->
        <title>Edit Account</title>
        <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto|Varela+Round">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css">
        <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
        <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"></script>
    </head>
    <body>
        <!-- Edit Product -->
        <div id="editEmployeeModal">
            <div class="modal-dialog">
                <div class="modal-content">
                    <form action="EditAccountController" method="POST">
                        <div class="modal-header">						
                            <h4 class="modal-title">Edit Account</h4>
                            <a type="button" class="close" href="AccountManagerController" aria-hidden="true">&times;</a>                                                         
                        </div>
                        <div class="modal-body">	

                            <div class="form-group">
                                <label>UserName</label>
                                <input type="text" class="form-control" required name="user" value="${user}"readonly>
                            </div>

                            <div class="form-group">
                                <label>Email</label>
                                <input type="text" class="form-control" required name="email" value="${email}"readonly>
                            </div>
                            <div class="form-group">
                                <label>Seller</label>
                                <input type="checkbox" class="form-control" name="Seller" value="1" ${Seller == 1 ? "checked" : ""} style="height: 30px;">
                            </div>
                            <div class="form-group">
                                <label>Admin</label>
                                <input type="checkbox" class="form-control" name="Admin" value="1" ${Admin == 1 ? "checked" : ""} style="height: 30px;">
                            </div>
                        </div>
                        <div class="modal-footer">
                            <a type="button" class="btn btn-default" href="AccountManagerController">Cancel</a>
                            <input type="submit" class="btn btn-info" value="Save">
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </body>
</html>