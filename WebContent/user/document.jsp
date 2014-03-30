<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="javax.persistence.*,com.lawyer.entity.*,com.lawyer.filter.EntityManagerListener,java.util.List,java.util.Iterator"%>
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
                <a class="" href="./home.html"><i class="icon-home"></i> Home</a>
              </li>
              <li class="active">
                <a href="./documents.html"><i class="icon-file"></i> Document</a>
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
 Query query = em.createNativeQuery("Select doc.documentId,doc.documentName from Document doc where doc.catagory=:catagory");
 List personalDocument;
 List propertyDocument;
 List businessDocument;
 List otherDocument;
 int documentId;
 String documentName;
 
 query.setParameter("catagory","personal");
 etx.begin();
 personalDocument = query.getResultList();
 etx.commit();
 
 query.setParameter("catagory","property");
 etx.begin();
 propertyDocument = query.getResultList();
 etx.commit();
 
 query.setParameter("catagory","business");
 etx.begin();
 businessDocument = query.getResultList();
 etx.commit();
 
 query.setParameter("catagory","other");
 etx.begin();
 otherDocument = query.getResultList();
 etx.commit();
 
 Iterator it = personalDocument.iterator();
%>
<div class="container" style="">
	<div class="row-fluid" style="padding:10px" >
		<div class="span3 well">
		<h4>Personal</h4>
          <p><a href="#">Family & Personal</a></p>
		  <%	for (; it.hasNext();) {
				Object[] obj = (Object[]) it.next();
				documentId = (Integer)obj[0];
				documentName = (String)obj[1];
			%>
			<p><a href="../documents/document.jsp?id=<%=documentId%>"><%=documentName%></a></p>
			<%}%>
</div>
<div class="span3 well">
		<h4>Property & Finance</h4>
            <%	
            it= propertyDocument.iterator();
            for (; it.hasNext();) {
				Object[] obj = (Object[]) it.next();
				documentId = (Integer)obj[0];
				documentName = (String)obj[1];
			%>
			<p><a href="../documents/document.jsp?id=<%=documentId%>"><%=documentName%></a></p>
			<%}%>
</div>
<div class="span3 well">
		<h4>Business</h4>
         <%	
            it= businessDocument.iterator();
            for (; it.hasNext();) {
				Object[] obj = (Object[]) it.next();
				documentId = (Integer)obj[0];
				documentName = (String)obj[1];
			%>
			<p><a href="../documents/document.jsp?id=<%=documentId%>"><%=documentName%></a></p>
			<%}%>
</div>
<div class="span3 well">
		<h4>Other</h4>
		  <%	
		  it = otherDocument.iterator();
		  for (; it.hasNext();) {
				Object[] obj = (Object[]) it.next();
				documentId = (Integer)obj[0];
				documentName = (String)obj[1];
			%>
			<p><a href="../documents/document.jsp?id=<%=documentId%>"><%=documentName%></a></p>
			<%}%>
</div>
	</div>
	</div>
</body>
</html>