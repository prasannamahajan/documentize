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
 
  List allDocument;
 String start=request.getParameter("page");
 if(start==null)
	 start="a";
 int documentId;
 String documentName;
 int size=0,size1,size2,size3;
 Query query = em.createNativeQuery("Select doc.documentId,doc.documentName from Document doc where doc.documentName LIKE " +"'"+start.toString()+"%'" +" ORDER BY doc.documentName");
 etx.begin();
 allDocument = query.getResultList();
 etx.commit();
 
 /*For printing in 3 divs*/
 size = allDocument.size();
 size1=size2=size3=size/3;
 int mod=size%3;
 if(mod==1){size1++;}
 if(mod==2){size1++;size2++;}
 
 Iterator it = allDocument.iterator();

 
%>
<div class="container" style="">
	<div class="row-fluid" style="padding:10px" >
	<div class="pagination span12">
  <ul>
    <li><a href="./alldocument.jsp?page=a">A</a></li>
    <li><a href="./alldocument.jsp?page=b">B</a></li>
	<li><a href="./alldocument.jsp?page=c">C</a></li>
	<li><a href="./alldocument.jsp?page=d">D</a></li>
	<li><a href="./alldocument.jsp?page=e">E</a></li>
	<li><a href="./alldocument.jsp?page=f">F</a></li>
	<li><a href="./alldocument.jsp?page=g">G</a></li>
	<li><a href="./alldocument.jsp?page=h">H</a></li>
	<li><a href="./alldocument.jsp?page=i">I</a></li>
	<li><a href="./alldocument.jsp?page=j">J</a></li>
	<li><a href="./alldocument.jsp?page=k">K</a></li>
	<li><a href="./alldocument.jsp?page=l">L</a></li>
	<li><a href="./alldocument.jsp?page=m">M</a></li>
	<li><a href="./alldocument.jsp?page=n">N</a></li>
	<li><a href="./alldocument.jsp?page=o">O</a></li>
	<li><a href="./alldocument.jsp?page=p">P</a></li>
	<li><a href="./alldocument.jsp?page=q">Q</a></li>
	<li><a href="./alldocument.jsp?page=r">R</a></li>
	<li><a href="./alldocument.jsp?page=s">S</a></li>
	<li><a href="./alldocument.jsp?page=t">T</a></li>
	<li><a href="./alldocument.jsp?page=u">U</a></li>
	<li><a href="./alldocument.jsp?page=v">V</a></li>
	<li><a href="./alldocument.jsp?page=w">W</a></li>
	<li><a href="./alldocument.jsp?page=x">X</a></li>
	<li><a href="./alldocument.jsp?page=y">Y</a></li>
	<li><a href="./alldocument.jsp?page=z">Z</a></li>
  </ul>
</div>
	<div class="span4">
	<% for(int i=0;i<size;i++)
	{
		if(it.hasNext())
		{
			Object[] obj = (Object[]) it.next();
			documentId = (Integer)obj[0];
			documentName = (String)obj[1];
	%>
	<p><a href="../documents/document.jsp?id=<%=documentId%>"><%=documentName%></a></p>
	<%} }%>
		
	</div>
	<div class="span4">
	<% for(int i=0;i<size1;i++)
	{
		if(it.hasNext())
		{
			Object[] obj = (Object[]) it.next();
			documentId = (Integer)obj[0];
			documentName = (String)obj[1];
	%>
	<p><a href="../documents/document.jsp?id=<%=documentId%>"><%=documentName%></a></p>
	<%} }%>
		
	</div>
	<div class="span4">
	<% for(int i=0;i<size3;i++)
	{
		if(it.hasNext())
		{
			Object[] obj = (Object[]) it.next();
			documentId = (Integer)obj[0];
			documentName = (String)obj[1];
	%>
	<p><a href="../documents/document.jsp?id=<%=documentId%>"><%=documentName%></a></p>
	<%} }%>
		
	</div>			
	
	
	</div>
	</div>
</body>
</html>