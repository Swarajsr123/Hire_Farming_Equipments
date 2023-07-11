
<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
     <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title> </title>
</head>
<body>
<jsp:include page="Top.jsp"></jsp:include>
<% try{ response.setHeader("Pragma", "No-cache");
response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
response.setDateHeader("Expires", -1);
if(session.getAttribute("userid")==null)
{
	response.sendRedirect("qrhome");
}
%>
<div class="container-fluid">
   

       
<div class="row">
 
<div class="col-md-12">
  <center><h2>Pending Income Proofs</h2></center>
   <table class="table table-bordered">
 <tr>
 <th> User Name</th>
 <th>Mobile No</th>
 <th>Income</th>
 
 <th>Year</th>
  <th>Proof</th>
  
 </tr>
 <c:forEach var="userdsc" items="${lst}">
		 <tr>
		<td>${userdsc.getName() }</td>
		<td>${userdsc.getMobile() }</td>
		<td>${userdsc.getIncome() }</td>
		<td>${userdsc.getYear() }</td>
		 <td><a target="_blank" href="Income/${userdsc.getUserid() }/${userdsc.getProof() }">Proof</a>
		 <td>
		 <a href="approveIncome?id=${userdsc.getId() }">Approve</a>
		 </td>
		 </tr>
		</c:forEach> 
		 
		
		</table>
  

<%
}
catch(Exception ex)
{
	
} 
 %>  
</div>
</div>
 


</div>
</body>
</html>