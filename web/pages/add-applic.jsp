<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
    <script>
        var i = 1;
        var buttonExists = false;
        function addMark(){
            document.getElementById('forms-group').insertAdjacentHTML('beforeend',
                    '<div id="f' + i + '" class="form-group"> \
                        <label for="inputSubject" class="col-sm-1 control-label">Subject</label> \
                        <div class="col-sm-5"> \
                            <input type="text" name="subject' + i + '" class="form-control" id="inputSubject" placeholder="Subject"> \
                        </div> \
                        <label for="inputMark" class="col-sm-1 control-label">Mark</label> \
                        <div class="col-sm-5"> \
                            <input type="number" name="mark' + i + '" class="form-control" id="inputMark" placeholder="Mark"> \
                        </div> \
                    </div>');
            i += 1;
            if (i >= 3) {
                document.getElementById('add-mark').style.visibility = 'hidden';
            }
            if (i > 1 && !buttonExists) {
                document.getElementById('remove-mark').style.visibility = 'visible';
                buttonExists = true;
            }
        }

        function removeMark(){
            i -= 1;
            document.getElementById('add-mark').style.visibility = 'visible';
            document.getElementById('forms-group').removeChild(document.getElementById('f' + i));
            if (i == 1) {
                document.getElementById('remove-mark').style.visibility = 'hidden';
                buttonExists = false;
            }
        }
    </script>
    <style>
        body {
            background: url("../background.jpg") repeat;
        }
    </style>
</head>
<body>
<nav class="navbar navbar-default">
    <div class="container-fluid">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#collapsing-navbar" aria-expanded="false">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="../index.jsp">Entry exams</a>
        </div>
        <div class="collapse navbar-collapse" id="collapsing-navbar">
            <ul class="nav navbar-nav">
                <li><a href="applic-of-fac.jsp">Applicants of faculty</a></li>
                <li><a href="average.jsp">Average mark</a></li>
                <li><a href="applic-over-average.jsp">Applicants over average</a></li>
                <li class="active"><a href="add-applic.jsp">Add applicant</a></li>
            </ul>
        </div>
    </div>
</nav>
<div class="container" role="main">
    <div class="page-header">
        <h1>Add applicant</h1>
    </div>
    <form id="forms" class="form-horizontal" action="controller" method="post">
        <input type="hidden" name="command" value="addapplic">
        <div class="panel panel-default">
            <div id="whole-form" class="panel-body">
                <div id="forms-group">
                    <div class="form-group">
                        <label for="inputName" class="col-sm-1 control-label">Name</label>
                        <div class="col-sm-11">
                            <input type="text" name="name" class="form-control" id="inputName" placeholder="Applicant name">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="inputFaculty" class="col-sm-1 control-label">Faculty</label>
                        <div class="col-sm-11">
                            <input type="text" name="faculty" class="form-control" id="inputFaculty" placeholder="Faculty">
                        </div>
                    </div>
                    <div id="f0" class="form-group">
                        <label for="inputSubject" class="col-sm-1 control-label">Subject</label>
                        <div class="col-sm-5">
                            <input type="text" name="subject0" class="form-control" id="inputSubject" placeholder="Subject">
                        </div>
                        <label for="inputMark" class="col-sm-1 control-label">Mark</label>
                        <div class="col-sm-5">
                            <input type="number" name="mark0" class="form-control" id="inputMark" placeholder="Mark">
                        </div>
                    </div>
                </div>
                <button id="add-mark" type="button" class="btn btn-sm btn-success" onclick="addMark()">
                    &nbsp<span class="glyphicon glyphicon-plus"></span>&nbsp
                </button>
                <button style="visibility: hidden" id="remove-mark" type="button" class="btn btn-sm btn-danger" onclick="removeMark()">
                    &nbsp<span class="glyphicon glyphicon-minus"></span>&nbsp
                </button>
            </div>
        </div>
        <div class="right">
            <button type="submit" class="right btn btn-lg btn-primary">Add applicant</button>
        </div>
    </form>
</div>
</body>
</html>