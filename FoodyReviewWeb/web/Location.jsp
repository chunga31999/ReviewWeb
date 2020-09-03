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
                <div class="col-sm-4 well">
                    <div class="well">
                        <img src="image/${local.img}" style="width: 200px;height: 200px">
                        <div class="caption">
                            <h3>${local.name}</h3>
                            <p>${local.address}</p>
                            <button type="button" class="btn btn-default">
                                <span class="glyphicon glyphicon-thumbs-up"></span> Like
                            </button>
                        </div>
                    </div>
                    <div class="well">
                        <p>Thông tin</p>
                        <p>
                            Đánh giá <span class="label label-warning glyphicon glyphicon-star">${local.score}</span>
                        </p>
                        <p>Giờ mở cửa: ${local.timeOpen}-${local.timeClose}</p>
                        <p>Giá từ: ${local.minPrice}-${local.maxPrice}</p>
                    </div>
                    <!-- Trigger the modal with a button -->
                    <button type="button" class="btn btn-info btn-lg" data-toggle="modal" data-target="#myModal">Đánh giá</button>

                    <!-- Modal -->
                    <div class="modal fade text-left" id="myModal" role="dialog">
                        <div class="modal-dialog">

                            <!-- Modal content-->
                            <div class="modal-content">
                                <div class="modal-header">
                                    <button type="button" class="close" data-dismiss="modal">&times;</button>
                                    <h4 class="modal-title">Đánh giá</h4>
                                </div>
                                <div class="modal-body">
                                    <form role="form" action="LocationDetail" method="post" enctype="multipart/form-data">
                                        <input type="hidden" name="localID" value="${local.id}">
                                        <input type="radio" name="rate" value="1"> 1 (Rất tệ)
                                        <input type="radio" name="rate" value="2"> 2 (Tệ)
                                        <input type="radio" name="rate" value="3"> 3 (Tạm được)   
                                        <input type="radio" name="rate" value="4"> 4 (Tốt)
                                        <input type="radio" name="rate" value="5" checked=""> 5 (Rất tốt)
                                        <div class="form-group">
                                            <label for="comment">Bình luận:</label>
                                            <input type="text" name="comment" class="form-control" placeholder="Enter comment" required>
                                        </div>
                                        <div class="form-group">
                                            <label>Ảnh: </label>
                                            <input type="file" class="form-control-file" name="photo">
                                        </div>
                                        <button class="btn btn-lg btn-primary btn-block" type="submit">Đánh giá</button>
                                    </form>
                                </div>
                                <div class="modal-footer">
                                    <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                                </div>
                            </div>

                        </div>
                    </div>
                </div>
                <div class="col-sm-8">

                    <div class="row">
                        <div class="col-sm-12">
                            <div class="panel panel-default text-left">
                                <div class="panel-body">
                                    <p contenteditable="true">Đánh giá người dùng</p>
                                    <p> ${local.score}
                                       <c:forEach begin="0" end="5" var="i">
                                            <c:if test="${i < local.score}">
                                                <span class="glyphicon glyphicon-star" style="color: #ffcc00"></span>   
                                            </c:if>
                                            <c:if test="${i > local.score}">
                                                <span class="glyphicon glyphicon-star"></span>
                                            </c:if>
                                        </c:forEach>
                                    </p>
                                    <p> 1
                                       <c:forEach begin="0" end="5" var="i">
                                            <c:if test="${i < 1}">
                                                <span class="glyphicon glyphicon-star" style="color: #ffcc00"></span>   
                                            </c:if>
                                            <c:if test="${i > 1}">
                                                <span class="glyphicon glyphicon-star"></span>
                                            </c:if>
                                        </c:forEach>
                                        ${dem1}
                                    </p>
                                    <p> 2
                                       <c:forEach begin="0" end="5" var="i">
                                            <c:if test="${i < 2}">
                                                <span class="glyphicon glyphicon-star" style="color: #ffcc00"></span>   
                                            </c:if>
                                            <c:if test="${i > 2}">
                                                <span class="glyphicon glyphicon-star"></span>
                                            </c:if>
                                        </c:forEach>
                                                ${dem2}
                                    <p> 3
                                       <c:forEach begin="0" end="5" var="i">
                                            <c:if test="${i < 3}">
                                                <span class="glyphicon glyphicon-star" style="color: #ffcc00"></span>   
                                            </c:if>
                                            <c:if test="${i > 3}">
                                                <span class="glyphicon glyphicon-star"></span>
                                            </c:if>
                                        </c:forEach>
                                                ${dem3}
                                    </p>
                                    <p> 4
                                       <c:forEach begin="0" end="5" var="i">
                                            <c:if test="${i < 4}">
                                                <span class="glyphicon glyphicon-star" style="color: #ffcc00"></span>   
                                            </c:if>
                                            <c:if test="${i > 4}">
                                                <span class="glyphicon glyphicon-star"></span>
                                            </c:if>
                                        </c:forEach>
                                                ${dem4}
                                    </p>
                                     <p> 5
                                       <c:forEach begin="0" end="5" var="i">
                                            <c:if test="${i < 5}">
                                                <span class="glyphicon glyphicon-star" style="color: #ffcc00"></span>   
                                            </c:if>
                                            <c:if test="${i > 5}">
                                                <span class="glyphicon glyphicon-star"></span>
                                            </c:if>
                                        </c:forEach>
                                                ${dem5}
                                    </p>
                                </div>
                            </div>
                        </div>
                    </div>
                   
                    <c:forEach items="${requestScope.rateL}" var="x">
                        
                        <div class="row">
                            <div class="col-sm-3">
                                <div class="well">
                                    <p>${x.username}</p>
                                </div>
                            </div>
                            <div class="col-sm-9">
                                <div class="well text-left">
                                    <p>
                                        <c:forEach begin="0" end="5" var="i">
                                            
                                            <c:if test="${i < x.score}">
                                                <span class="glyphicon glyphicon-star" style="color: #ffcc00"></span>   
                                            </c:if>
                                            <c:if test="${i > x.score}">
                                                <span class="glyphicon glyphicon-star"></span>
                                            </c:if>
                                        </c:forEach>
                                    </p>
                                    <p>${x.comment}</p>
                                    <img src="image/${x.img}" style="width: 200px;height: 200px">
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
