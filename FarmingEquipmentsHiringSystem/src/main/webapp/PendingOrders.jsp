
<%@page import="java.util.List"%>
<%@ page isELIgnored="false" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<jsp:include page="Top.jsp"></jsp:include>
<% try{ response.setHeader("Pragma", "No-cache");
response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
response.setDateHeader("Expires", -1);
if(session.getAttribute("userid")==null)
{
	response.sendRedirect("home");
}
%>
<div class="container-fluid">
  <div class="jumbotron"> 

     
<div class="row">

<div class="col-md-12"> <h2>Pending Hiring Requests</h2>
    
 <table class="table table-bordered table-responsive">
 <tr>
 <th colspan="2"></th>
 <th>Order No</th>
 <th>User Name</th>
 <th>Products</th>
 <th>Date</th>
 <th>NetBill/(month/day/hr)</th>
  
 <th>Mobile</th>
 <th>Email</th>
 <th>Address</th>
 
 <th>State</th>
 <th>City</th> 
 </tr>
 <c:forEach var="userdsc" items="${lst}">
		<tr>
		<td><a href="ProcessOrder?orderNo=${userdsc.getOrderno()}">Process</a></td>
		<td><a href="viewOrderDetails?ordersts=${userdsc.getOrdersts() }&orderNo=${userdsc.getOrderno()}">view Details</a></td>
		
		<td>${userdsc.getOrderno() }</td> 
		<td>${userdsc.getUsername() }</td> 
			<td>${userdsc.getProd()}</td>
			<td>${userdsc.getDt()}</td>
			<td>${userdsc.getNetbill()}</td>
			 
			<td>${userdsc.getMobile()}</td>
			<td>${userdsc.getEmail()}</td>
			<td>${userdsc.getAddr()}</td>
		 
			<td>${userdsc.getState()}</td>
			<td>${userdsc.getCity()}</td>
		</tr>
		</c:forEach></table>
 </div>
</div>
</div>
<%}
catch(Exception ex)
{
	System.out.println("err="+ex.getMessage());
} %>

</div>
</body>
</html>