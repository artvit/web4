<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <title>Entry exams</title>

    <!-- Bootstrap -->
    <link href="../css/bootstrap.min.css" rel="stylesheet">

    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
    <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
    <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="../js/bootstrap.min.js"></script>
    <script type="text/javascript" src="http://java.com/js/dtjava.js"></script>
    <style>
        body {
            background: url("../background.jpg") repeat;
        }
    </style>
</head>
<body>
<nav class="navbar navbar-default">
    <div class="container-fluid">
        <!-- Brand and toggle get grouped for better mobile display -->
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse"
                    data-target="#collapsing-navbar" aria-expanded="false">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="../index.jsp">Entry exams</a>
        </div>

        <!-- Collect the nav links, forms, and other content for toggling -->
        <div class="collapse navbar-collapse" id="collapsing-navbar">
            <ul class="nav navbar-nav">
                <li class="active"><a href="applic-of-fac.jsp">Applicants of faculty</a></li>
                <li><a href="average.jsp">Average mark</a></li>
                <li><a href="applic-over-average.jsp">Applicants over average</a></li>
                <li><a href="add-applic.jsp">Add applicant</a></li>
            </ul>
        </div>
    </div>
</nav>
<div class="container" role="main">
    <div class="page-header">
        <h1>Show applicants for faculty</h1>
    </div>
    <div class="panel panel-default">
        <div class="panel-body">
            <div class="row">
                <div class="col-sm-6">
                    <form action="controller" method="post">
                        <div class="input-group">
                            <input type="hidden" name="command" value="applicoffac">
                            <input type="text" name="faculty" value="${faculty}" class="form-control" placeholder="Faculty name"/>
                            <span class="input-group-btn">
                                <button class="btn btn-default" type="submit">Search</button>
                            </span>
                        </div>
                    </form>
                </div>
            </div>
            <c:if test="${not empty results}">
                <div>
                    <table class="table table-striped">
                        <thead>
                        <tr>
                            <td>#</td>
                            <td>Name</td>
                            <td>Entered</td>
                            <td>Total</td>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${results}" var="item">
                            <tr>
                                <td>${item.id}</td>
                                <td>${item.name}</td>
                                <td>${item.entered}</td>
                                <td><fmt:formatNumber type="number" maxFractionDigits="2" value="${item.total}" /></td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
            </c:if>
        </div>
    </div>
</div>
</body>
</html>