<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="javax.persistence.*,com.lawyer.entity.*,com.lawyer.filter.EntityManagerListener,java.util.List,java.util.Iterator"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Lawyers</title>
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
                <a class="" href="./home.html"><i class="icon-home"></i> Home</a>
              </li>
              <li class="">
                <a href="./document.jsp"><i class="icon-file"></i> Document</a>
              </li>
                <li class="">
                <a href="./myprofile.html"><i class="icon-user"></i> My Profile</a>
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
<div class="row-fluid" style="padding:10px" >
 <div class="span2"><h1>yourLawyer</h1></div>
 <div class="span10"><h1><small>Make free documents, verify, share with lawyers</small></h1></div>
  </div>
  </div>
</div>
<%
 EntityManager em = EntityManagerListener.getEntityManager();
 EntityTransaction etx = em.getTransaction();
 TypedQuery<Lawyer> query = em.createNamedQuery("Lawyer.findAllLawyers", Lawyer.class);
 
 etx.begin();
  List<Lawyer> lawyerList = query.getResultList();
  etx.commit();
  
 Iterator it = lawyerList.iterator();

 
%>
<div class="container" style="">
	<div class="row-fluid" style="padding:10px" >
 <%	for (; it.hasNext();) {
				Lawyer lawyer = (Lawyer) it.next();		
%>
<div class="span12 well">
		<div class="span3">
		<img src="../resources/lawyer.jpg" class="img-circle" height="200" width="150">
</div>
<div class="span6">
		<h4><%= lawyer.getFullname() %></h4>
          <strong>Specialization : </strong><p><%= lawyer.getSpecialization() %></p>
		  <p><%= lawyer.getAboutself() %></p>
		  
</div>
<div class="span3">
<address>
  <%= lawyer.getAddress() %>
  <abbr title="Phone">Mobile No : </abbr><%= lawyer.getPhonenumber() %>
</address>
 
<address>
  <strong>Email Me</strong><br>
  <a href="mailto:#"><%= lawyer.getEmail() %></a>
</address>
<a class="btn btn-success btn-medium" href="./sendtolawyer?lawyerid=<%=lawyer.getLawyerId() %>">Send</a>
</div>
</div>			
			
			
			
<%}%>
	

			
	
	
	</div>
	</div>
</body>
</html>