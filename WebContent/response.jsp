<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="javax.persistence.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Response</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description" content="">
<meta name="author" content="">
<link rel="shortcut icon" href="/favicon.ico" type="image/x-icon" />

<link href="assets/css/bootstrap.css" rel="stylesheet">
<link href="assets/css/bootstrap-responsive.css" rel="stylesheet">
<link rel="stylesheet" type="text/css" href="header.css" />
</head>
<body>
	<!-- Nav bar
  ================================================ -->
	<div class="navbar navbar-inverse navbar-fixed-top">
		<div class="navbar-inner">
			<div class="container">
				<div class="nav-collapse pull-right">
					<ul class="nav nav-pills">
					</ul>
				</div>
			</div>
		</div>
	</div>
	<!-- Page Header
 ================================================= -->
	<div class="jumbotron" style="">
		<div class="container">
			<div class="row-fluid" style="padding: 10px">
				<div class="span2">
					<h1>yourLawyer</h1>
				</div>
				<div class="span10">
					<h1>
						<small>Make free documents, verify, share with lawyers</small>
					</h1>
				</div>
			</div>
		</div>
	</div>
	<%!String head;
	String message;
	String link = "";
	String linkname = "";
	int id = 0;%>
	<%
		try {
			id = Integer.parseInt(request.getParameter("id"));
		} catch (Exception e) {
			id = 0;
		}
		switch (id) {
		case 1:
			// case 1 : After registration 
			head = "Successfull";
			message = "Please check your email";
			link = "";
			break;
		case 2:
			// case 2 : Notify successfull registration
			head = "Registration successfull";
			message = "Welcome to yourLawyer";
			link = "./login.html";
			linkname = "Login";
			break;
		case 3:
			// case 3 : Document generated successfully
			head = "Document generated successfully";
			message = "";
			link = "./some";
			linkname = "View Document";
			break;
		case 4:
			//case 4 : registration unsuccessfull
			head = "Registration Unsuccessfull";
			message = "This email id already exist";
			link = "./contact-us.html";
			linkname = "Cantact Admin";
			break;
		case 5:
			//case 5 : registration unsuccessfull
			head = "Registration Unsuccessfull";
			message = "Activation failed";
			link = "./contact-us.html";
			linkname = "Cantact Admin";
			break;

		default:
			head = "";
			message = "";
			link = "./login.html";
			linkname = "Home";
			break;
		}
	%>
	<div class="container" style="">

		<div class="row-fluid" style="padding: 10px">
			<center>
				<div class="well">
					<h1 class=""><%=head%></h1>
					<br>
					<p class=""><%=message%></p>
					<%
						if (link != "") {
					%>
					<a class="btn btn-success btn-large" href="<%=link%>"><%=linkname%></a>
					<%
						}
					%>
				</div>
			</center>
		</div>
	</div>

</body>
</html>