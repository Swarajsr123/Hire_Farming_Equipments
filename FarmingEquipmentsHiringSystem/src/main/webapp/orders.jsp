
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
<div class="container">
  <div class="jumbotron"> 

     
<div class="row">

<div class="col-md-12"> <h2>My Orders</h2>
    
 <table class="table table-bordered">
 <tr>
 <th>Order No</th>
 <th>Products</th>
 <th>Date</th>
 
   
 <th>Order Status</th>
 <th></th>
 </tr>
 <c:forEach var="userdsc" items="${lst}">
		<tr><td>${userdsc.getOrderno() }</td>  
			<td>${userdsc.getProd()}</td>
			<td>${userdsc.getDt()}</td>
			 
			  
			<td>${userdsc.getOrdersts()}</td>
			<td><a href="viewOrderDetails?ordersts=${userdsc.getOrdersts() }&sellerUserid=${userdsc.getSellerUserid() }&orderNo=${userdsc.getOrderno()}">view Details</a></td>
		
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