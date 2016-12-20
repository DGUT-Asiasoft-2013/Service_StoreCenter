<%@ page language="java"%>

<%@ include file="common/head.jsp"%>
<html>
<head>
<title>Member Center - Staff</title>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="resources/bootstrap/css/bootstrap.min.css">
<link rel="stylesheet" href="resources/private/css/admin.css">
</head>

<body>
	<nav class="navbar navbar-inverse navbar-fixed-top">
		<div class="container">
			<div class="navbar-header">
				<button type="button" class="navbar-toggle collapsed"
					data-toggle="collapse" data-target="#navbar" aria-expanded="false"
					aria-controls="navbar">
					<span class="sr-only">Toggle navigation</span> <span
						class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
				<a class="navbar-brand" href="#">Member Center</a>
			</div>
			<div id="navbar" class="collapse navbar-collapse">
				<ul class="nav navbar-nav">
					<li class="active"><a href="#">Staff</a></li>
					<li><a href="#about">Members</a></li>
					<li><a href="#contact">Settings</a></li>
				</ul>
			</div>
		</div>
	</nav>

	<div class="container">
		<br/>
		<div class="jumbotron">
			<h1>Hello, world!</h1>
			<p>Here is the admin dashboard.</p>
			<p>
				<a class="btn btn-primary btn-lg" href="api/hello" role="button">API Hello</a>
			</p>
		</div>
	</div>
	<script src="resources/jquery/jquery-3.1.1.min.js"></script>
	<script src="resources/bootstrap/js/bootstrap.min.js"></script>
</body>
</html>