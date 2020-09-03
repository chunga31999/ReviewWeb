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
                        <li><a href="#">Deals</a></li>
                        <li><a href="#">Stores</a></li>
                        <li><a href="#">Contact</a></li>
                    </ul>
                    <form class="navbar-form navbar-left">
                        <div class="form-group">
                            <input type="text" class="form-control" placeholder="Search">
                        </div>
                        <button type="submit" class="btn btn-default"><span class="glyphicon glyphicon-search"></span></button>
                    </form>
                    <ul class="nav navbar-nav navbar-right">
                        <c:choose>
                            <c:when test="${empty user}">
                                <li><a href="Login.jsp"><span class="glyphicon glyphicon-log-in"></span> Login</a></li>
                                </c:when>
                                <c:when test="${user eq ''}">
                                <li><a href="Login.jsp"><span class="glyphicon glyphicon-log-in"></span> Login</a></li>
                                </c:when>
                                <c:otherwise>
                                <li><a href="Profile"><span class="glyphicon glyphicon-user"></span> ${user.username}</a></li>
                                <li id="fat-menu" class="dropdown">
                                    <a id="drop3" href="#Profile" class="dropdown-toggle" data-toggle="dropdown" role="button" >
                                        <span class="caret"></span>
                                    </a>
                                    <ul class="dropdown-menu" aria-labelledby="drop3">
                                        <li><a href="AddLocation">Add Location</a></li>
                                        <li><a href="#">Something else here</a></li>
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
                <div class="col-sm-3 well">
                    <div class="well">
                        <p><a href="#">My Profile</a></p>
                        <img src="bird.jpg" class="img-circle" height="65" width="65" alt="Avatar">
                    </div>
                    <div class="well">
                        <p><a href="#">Interests</a></p>
                        <p>
                            <span class="label label-default">News</span>
                            <span class="label label-primary">W3Schools</span>
                            <span class="label label-success">Labels</span>
                            <span class="label label-info">Football</span>
                            <span class="label label-warning">Gaming</span>
                            <span class="label label-danger">Friends</span>
                        </p>
                    </div>
                    <div class="alert alert-success fade in">
                        <a href="#" class="close" data-dismiss="alert" aria-label="close">×</a>
                        <p><strong>Ey!</strong></p>
                        People are looking at your profile. Find out who.
                    </div>
                    <p><a href="#">Link</a></p>
                    <p><a href="#">Link</a></p>
                    <p><a href="#">Link</a></p>
                </div>
                <div class="col-sm-7">

                    <div class="row">
                        <div class="col-sm-12">
                            <div class="panel panel-default text-left">
                                <div class="panel-body">
                                    <p contenteditable="true">Status: Feeling Blue</p>
                                    <button type="button" class="btn btn-default btn-sm">
                                        <span class="glyphicon glyphicon-thumbs-up"></span> Like
                                    </button>     
                                </div>
                            </div>
                        </div>
                    </div>

                    <div class="row">
                        <div class="col-sm-3">
                            <div class="well">
                                <p>John</p>
                                <img src="bird.jpg" class="img-circle" height="55" width="55" alt="Avatar">
                            </div>
                        </div>
                        <div class="col-sm-9">
                            <div class="well">
                                <p>Just Forgot that I had to mention something about someone to someone about how I forgot something, but now I forgot it. Ahh, forget it! Or wait. I remember.... no I don't.</p>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-sm-3">
                            <div class="well">
                                <p>Bo</p>
                                <img src="bandmember.jpg" class="img-circle" height="55" width="55" alt="Avatar">
                            </div>
                        </div>
                        <div class="col-sm-9">
                            <div class="well">
                                <p>Just Forgot that I had to mention something about someone to someone about how I forgot something, but now I forgot it. Ahh, forget it! Or wait. I remember.... no I don't.</p>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-sm-3">
                            <div class="well">
                                <p>Jane</p>
                                <img src="bandmember.jpg" class="img-circle" height="55" width="55" alt="Avatar">
                            </div>
                        </div>
                        <div class="col-sm-9">
                            <div class="well">
                                <p>Just Forgot that I had to mention something about someone to someone about how I forgot something, but now I forgot it. Ahh, forget it! Or wait. I remember.... no I don't.</p>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-sm-3">
                            <div class="well">
                                <p>Anja</p>
                                <img src="bird.jpg" class="img-circle" height="55" width="55" alt="Avatar">
                            </div>
                        </div>
                        <div class="col-sm-9">
                            <div class="well">
                                <p>Just Forgot that I had to mention something about someone to someone about how I forgot something, but now I forgot it. Ahh, forget it! Or wait. I remember.... no I don't.</p>
                            </div>
                        </div>
                    </div>     
                </div>
                <div class="col-sm-2 well">
                    <div class="thumbnail">
                        <p>Upcoming Events:</p>
                        <img src="paris.jpg" alt="Paris" width="400" height="300">
                        <p><strong>Paris</strong></p>
                        <p>Fri. 27 November 2015</p>
                        <button class="btn btn-primary">Info</button>
                    </div>      
                    <div class="well">
                        <p>ADS</p>
                    </div>
                    <div class="well">
                        <p>ADS</p>
                    </div>
                </div>
            </div>
        </div>

        <footer class="container-fluid text-center">
            <p>Footer Text</p>
        </footer>

    </body>
</html>
