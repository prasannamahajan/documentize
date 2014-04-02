<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="javax.persistence.*,com.lawyer.entity.*,com.lawyer.filter.EntityManagerListener"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Document</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description" content="">
<meta name="author" content="">
<link rel="shortcut icon" href="/favicon.ico" type="image/x-icon" />

<link href="../assets/css/bootstrap.css" rel="stylesheet">
<link href="../assets/css/bootstrap-responsive.css" rel="stylesheet">
<link rel="stylesheet" type="text/css" href="../header.css" />
</head>
<body>
	<!-- Nav bar
  ================================================ -->
	  <div class="navbar navbar-inverse navbar-fixed-top">
      <div class="navbar-inner">
        <div class="container">
          <div class="nav-collapse pull-right">
            <ul class="nav nav-pills">
              <li class="">
                <a class="" href="../user/home.html"><i class="icon-home"></i> Home</a>
              </li>
              <li class="active">
                <a href="../user/documents.jsp"><i class="icon-file"></i> Document</a>
              </li>
                <li class="">
                <a href="../user/myprofile.html"><i class="icon-user"></i> My Profile</a>
              </li> 
                <li class="">
                <a href="../logout"><i class="icon-off"></i> Logout</a>
              </li>
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
	<%!
	Document document = new Document();
	int documentId ;
	%>
	<%
	try{
	documentId = Integer.parseInt(request.getParameter("id"));
	}
	catch(NullPointerException n)
	{
		documentId = -1;
	}
	
	if(documentId != -1)
	{
	EntityManager em = EntityManagerListener.getEntityManager();
	EntityTransaction etx = em.getTransaction();
	etx.begin();
	document = em.find(Document.class,documentId);
	etx.commit();
	%>
<div class="container" style="">
	<div class="row-fluid" style="padding:10px" >
		<div class="span7 offset1 well">
		<h3 class=""><%=document.getDocument_name()%></h3>
         <p class="" style="text-align:justify">
			<%=document.getDocumentDescription().replaceAll("\n","<br>") %></p>

		<br>
		<a class="btn btn-success btn-large" href="./dynamic.html?documentId=<%=documentId%>&documentName=<%=document.getDocument_name()%>">Create</a>
		</div>
	</div>
	</div>
<%}
else
{
%>
<div class="container" style="">
	<div class="row-fluid" style="padding:10px" >
		<p class="text-error">Invalid request</p>
	</div>
	</div>
<%} %>
</body>
</html>