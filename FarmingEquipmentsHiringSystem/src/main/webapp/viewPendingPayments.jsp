
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
  <center><h2>Pending Payments </h2></center>
   <table class="table table-bordered">
 <tr>
 <th> Farmer Name</th>
 <th>Mobile No</th>
 <th>Rent</th>
 
 <th>Date</th>
  <th>Receipt</th>
  
 </tr>
 <c:forEach var="userdsc" items="${lst}">
		 <tr>
		 <td>${userdsc.getOrderno() }</td>
		<td>${userdsc.getUsername() }</td>
		<td>${userdsc.getMobile() }</td>
		<td>${userdsc.getNetbill() }</td>
		<td>${userdsc.getDt() }</td>
		 <td><a target="_blank" href="Receipts/${userdsc.getUserid() }/${userdsc.getReceipt() }">Receipt</a>
		 <td>
		 <a href="approvePayment?sts=approved&id=${userdsc.getPaymentId() }&orderno=${userdsc.getOrderno() }&date=${userdsc.getDt() }&userid=${userdsc.getUserid() }">Approve</a>
		 </td>
		  <td>
		 <a href="approvePayment?id=sts=declined&${userdsc.getPaymentId() }">Decline</a>
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