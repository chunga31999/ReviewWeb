<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>Foody</title>
        <!-- Bootstrap -->
        <link href="bootstrap/bootstrap-3.1.1-dist/css/bootstrap.min.css" rel="stylesheet">
        <link href="css/webStyle.css" rel="stylesheet">
        <script src="jquery/jquery-3.4.1.min.js"></script>
        <script src="bootstrap/bootstrap-3.1.1-dist/js/bootstrap.min.js"></script>
    </head>
    <body>
        <div class="jumbotron" style="background-image: url(image/nenngang.jpg);background-size: 100%;">
            <div class="container text-center">
                <h1>HOLA ăn gì?</h1>      
                <p><span class="glyphicon glyphicon-map-marker"></span>Food and Drink</p>
            </div>
        </div>

        <nav class="navbar navbar-inverse">
            <div class="container-fluid">
                <div class="navbar-header">
                    <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#myNavbar">
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>                        
                    </button>
                    <a class="navbar-brand" href="#" style="padding-top:0px">
                        <img style="height: 50px;" src="image/logo.png">
                    </a>
                </div>
                <div class="collapse navbar-collapse" id="myNavbar">
                    <ul class="nav navbar-nav">
                        <li class="active"><a href="Home">Trang chủ</a></li>
                        <li><a href="List">Địa điểm</a></li>
                        <li><a href="Contact.jsp">Contact</a></li>
                    </ul>
                    <form action="List" method="post" class="navbar-form navbar-left">
                        <div class="form-group">
                            <input type="text" name="search" class="form-control" placeholder="Search">
                        </div>
                        <button type="submit" class="btn btn-default"><span class="glyphicon glyphicon-search"></span></button>
                    </form>
                    <ul class="nav navbar-nav navbar-right">
                        <c:choose>
                            <c:when test="${not empty admin}">
                                <li><a href="Profile"><span class="glyphicon glyphicon-user"></span> ${user.username}</a></li>
                                <li id="fat-menu" class="dropdown">
                                    <a id="drop3" href="#" class="dropdown-toggle" data-toggle="dropdown" role="button">
                                        <span class="caret"></span>
                                    </a>
                                    <ul class="dropdown-menu" aria-labelledby="drop3">
                                        <li><a href="ControllUser">Quản lí người dùng</a></li>
                                        <li class="divider"></li>
                                        <li><a href="SignOut"><span class="glyphicon glyphicon-log-in"></span> SignOut</a></li>
                                    </ul>
                                </li>
                            </c:when>
                            <c:when test="${(empty user)&&(empty admin)}">
                                <li><a href="Login.jsp"><span class="glyphicon glyphicon-log-in"></span> Login</a>
                                </li>
                            </c:when>
                            <c:when test="${user eq ''}">
                                <li><a href="Login.jsp"><span class="glyphicon glyphicon-log-in"></span> Login</a>
                                </li>
                            </c:when>
                            <c:otherwise>
                                <li><a href=""><span class="glyphicon glyphicon-user"></span> ${user.username}</a>
                                </li>
                                <li id="fat-menu" class="dropdown">
                                    <a id="drop3" href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" >
                                        <span class="caret"></span>
                                    </a>
                                    <ul class="dropdown-menu" aria-labelledby="drop3">
                                        <li><a href="AddLocation">Thêm địa điểm</a></li>
                                        <li class="divider"></li>
                                        <li><a href="SignOut"><span class="glyphicon glyphicon-log-in"></span> SignOut</a></li>
                                    </ul>
                                </li>
                            </c:otherwise>

                        </c:choose>
                    </ul>
                </div>

            </div>
        </nav>

        <div class="container text-center">    
            <div class="row">
                <div class="col-sm-8">
                    <c:forEach items="${requestScope.listU}" var="x">
                        <div class="row">
                            <div class="col-sm-9">
                                <div class="well text-left">
                                    <p>Username: ${x.username}</p>
                                    <p>Full name: ${x.detail.getFullname()}</p>
                                    <p>DOB: ${x.detail.getDOB()}</p>
                                </div>
                            </div>
                            <div class="col-sm-3">
                                <div class="well">
                                    <c:if test="${x.status}">
                                        <p><a href="ControllUser?id=${x.id}&status=0"><span class="glyphicon glyphicon-remove"></span>Delete</a></p>
                                    </c:if>
                                    <c:if test="${!x.status}">
                                        <p><a href="ControllUser?id=${x.id}&status=1">Restore</a></p>
                                    </c:if>
                                </div>
                            </div>
                        </div>
                    </c:forEach>

                </div>

            </div>
        </div>

        <footer class="container-fluid text-center">
            <p>Footer Text</p>
        </footer>

    </body>
</html>
