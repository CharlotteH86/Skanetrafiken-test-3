<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import="mvc.Bean"%>
<!DOCTYPE html>
<html>


<head>

<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<meta name="author" content="">

<title>Skånetrafiken</title>

<!-- Bootstrap core CSS -->
<link href="bootstrap/vendor/bootstrap/css/bootstrap.min.css"
	rel="stylesheet">

</head>

<body>

	<!-- Navigation -->
	<nav class="navbar navbar-expand-lg navbar-dark bg-dark static-top">
		<div class="container"></div>
			<a class="navbar-brand" href="#">Start Bootstrap</a>
			</nav>
			


			<!-- Page Content -->
			<div class="container">
				<div class="row">
					<div class="col-lg-12 text-center">

<p class="lead">
						<%
						Bean bean = (Bean) request.getAttribute("bean");
						out.print("Sökning: " +"<p>"+bean.getNames()+"</p>");
						%>
</p>
						
					</div>
				</div>
			</div>
			

			<!-- Bootstrap core JavaScript -->
			<script
				src="/lib/startbootstrap-bare-gh-pages/vendor/jquery/jquery.slim.min.js"></script>
			<script
				src="/lib/startbootstrap-bare-gh-pages/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
</body>

</html>