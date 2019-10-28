<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>

<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <title>HomeSmart</title>

    <!-- Core CSS - Include with every page -->
    <link href="<c:url value='/resources/css/bootstrap.min.css' />" rel="stylesheet">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">

    <!-- SB Admin CSS - Include with every page -->
    <link href="<c:url value='/resources/css/sb-admin.css' />" rel="stylesheet">

</head>

<body>

<div id="wrapper">

    <nav class="navbar navbar-default navbar-fixed-top" role="navigation" style="margin-bottom: 0">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".sidebar-collapse">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="<c:url value="/" />">HomeSmart</a>
        </div>
        <!-- /.navbar-header -->

        <ul class="nav navbar-top-links navbar-right">
            <c:if test="${! empty authInfo}">
                <li class="dropdown">
                    <a class="dropdown-toggle" data-toggle="dropdown" href="#">
                        <i class="fa fa-bell fa-fw"></i>  <i class="fa fa-caret-down"></i>
                    </a>
                    <ul class="dropdown-menu dropdown-alerts">
                        <li>
                            <a href="<c:url value="/message/received?recipientId=${authInfo.id}" />">
                                <i class="fa fa-envelope fa-fw"></i> Message Box
                            </a>
                        </li>
                        <li class="divider"></li>
                        <li>
                            <a href="<c:url value="/message/sent?senderId=${authInfo.id}" />">
                                <i class="fa fa-envelope-o fa-fw"></i> Message Sent
                            </a>
                        </li>
                    </ul>
                    <!-- /.dropdown-alerts -->
                </li>
                <!-- /.dropdown -->
            </c:if>
            <li class="dropdown">
                <a class="dropdown-toggle" data-toggle="dropdown" href="#">
                    <i class="fa fa-user fa-fw"></i>  <i class="fa fa-caret-down"></i>
                </a>
                <ul class="dropdown-menu dropdown-user">
                    <c:if test="${! empty authInfo}">
                        <li>
                            <a href="<c:url value="/user/profile/${authInfo.id}" />"><i class="fa fa-user fa-fw"></i> User Profile</a>
                        </li>
                        <li class="divider"></li>
                        <li>
                            <a href="<c:url value="/logout" />"><i class="fa fa-sign-out fa-fw"></i> Logout</a>
                        </li>
                    </c:if>

                    <c:if test="${empty authInfo}">
                        <li>
                            <a href="<c:url value="/login" />"><i class="fa fa-sign-in fa-fw" aria-hidden="true"></i> Login</a>
                        </li>
                        <li class="divider"></li>
                        <li>
                            <a href="<c:url value="/member/step1" />"><i class="fa fa-registered fa-fw" aria-hidden="true"></i> Register</a>
                        </li>
                    </c:if>


                </ul>
                <!-- /.dropdown-user -->
            </li>
            <!-- /.dropdown -->
        </ul>
        <!-- /.navbar-top-links -->
        <form action="<c:url value="/board/search"/>" method="post">

        <div class="navbar-default navbar-static-side" role="navigation">
            <div class="sidebar-collapse">
                <ul class="nav" id="side-menu">
                    <li>
                        <a href="<c:url value="/boards" />"><i class="fa fa-list-ul" aria-hidden="true"></i> List All</a>
                    </li>
                    <li>
                        <a href="#"><i class="fa fa-home"></i> Type<span class="fa arrow"></span></a>
                        <ul class="nav nav-second-level">
                            <div class="form-group">
                                <select class="form-control" name="type" required>
                                    <option value="House">House</option>
                                    <option value="Town House">Town House</option>
                                    <option value="Condo">Condo</option>
                                </select>
                            </div>
                        </ul>
                        <!-- /.nav-second-level -->
                    </li>
                    <li>
                        <a href="#"><i class="fa fa-bath fa-fw"></i> Bathroom<span class="fa arrow"></span></a>
                        <ul class="nav nav-second-level">
                            <div class="form-group">
                                <select class="form-control" name="bathroom" required>
                                    <option value="0">0+</option>
                                    <option value="1">1+</option>
                                    <option value="2">2+</option>
                                    <option value="3">3+</option>
                                    <option value="4">4+</option>
                                    <option value="5">5+</option>
                                    <option value="6">6+</option>
                                </select>
                            </div>
                        </ul>
                        <!-- /.nav-second-level -->
                    </li>
                    <li>
                        <a href="#"><i class="fa fa-bed fa-fw"></i> Bedroom<span class="fa arrow"></span></a>
                        <ul class="nav nav-second-level">
                            <div class="form-group">
                                <select class="form-control" name="bedroom" required>
                                    <option value="0">0+</option>
                                    <option value="1">1+</option>
                                    <option value="2">2+</option>
                                    <option value="3">3+</option>
                                    <option value="4">4+</option>
                                    <option value="5">5+</option>
                                </select>
                            </div>
                        </ul>
                        <!-- /.nav-second-level -->
                    </li>
                    <li>
                        <a href="#"><i class="fa fa-square fa-fw"></i> Den<span class="fa arrow"></span></a>
                        <ul class="nav nav-second-level">
                            <div class="form-group">
                                <select class="form-control" name="den" required>
                                    <option value="0">0</option>
                                    <option value="1">1</option>
                                </select>
                            </div>
                        </ul>
                        <!-- /.nav-second-level -->
                    </li>
                    <li>
                        <a href="#"><i class="fa fa-money fa-fw"></i> Price<span class="fa arrow"></span></a>
                        <ul class="nav nav-second-level">
                            <div class="form-group">
                                <p class="text-center text-success"><strong><i class="fa fa-dollar fa-fw"></i> Min</strong></p>
                                <div class="form-group">
                                    <input class="form-control" name="min" required>
                                </div>

                            </div>
                            <div class="form-group">
                                <p class="text-center text-success"><strong><i class="fa fa-dollar fa-fw"></i> Max</strong></p>
                                <div class="form-group">
                                    <input class="form-control" name="max" required>
                                </div>
                            </div>
                        </ul>
                        <!-- /.nav-second-level -->
                    </li>
                    <button type="submit" class="btn btn-primary btn-lg btn-block"><i class="fa fa-search"></i> Search</button>
                </ul>
                <!-- /#side-menu -->
            </div>
            <!-- /.sidebar-collapse -->
        </div>
        <!-- /.navbar-static-side -->
        </form>

    </nav>

    <div id="page-wrapper">

        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>