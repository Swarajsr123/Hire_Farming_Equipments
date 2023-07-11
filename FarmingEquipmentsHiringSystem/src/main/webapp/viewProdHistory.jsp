
<%@page import="java.util.Vector"%>
<%@page import="com.models.JavaFuns"%>
<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Supply Chain Management</title>
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
   

       
<div class="row">
 
<div class="col-md-12">
  <center><h2>Product Transaction History</h2></center>
   <table class="table table-bordered">
 <tr>

 <th>Buyer</th>
  <th> Product Name</th>
 <th>Price/unit</th>
 <th>Seller Name</th>
 <th>Date</th>
 <th>Time </th>
  
 </tr>
 <c:forEach var="userdsc" items="${lst}">
		 <tr>
		 <td>${userdsc.getUsername() }(${userdsc.getUtype() })</td>
		
		<td>${userdsc.getProdName() }</td>
		<td>${userdsc.getPrice() }/${userdsc.getUnit() }</td>
		<td>${userdsc.getSellerName() }(${userdsc.getSellertype() })</td>
		<td>${userdsc.getDt() }</td> 
		<td>${userdsc.getTm() }</td> 
		  
		 </tr>
		</c:forEach> 
		 
		
		</table>
  

<%
JavaFuns jf=new JavaFuns();
Vector v=jf.getValue("select title,prodDesc,price,unit,username,usertype from products where prodId="+request.getAttribute("prodId").toString().trim(), 6);
%>
<center><h2>Current Product Details</h2></center>
   <table class="table table-bordered"><tr>
<th>Product Name</th>
<th>Product Description</th>
<th>Price/Unit</th>
<th>Seller Name</th>
</tr>

<% 
for(int i=0;i<v.size();i=i+6)
{%>
	<tr>
	<td><%=v.elementAt(i).toString().trim() %></td>
	<td><%=v.elementAt(i+1).toString().trim() %></td>
	<td><%=v.elementAt(i+2).toString().trim() %>(<%=v.elementAt(i+3).toString().trim() %>)</td>
	<td><%=v.elementAt(i+4).toString().trim() %>(<%=v.elementAt(i+5).toString().trim() %>)</td>
	</tr>
<%}
}
catch(Exception ex)
{
	System.out.println("err in re="+ex.getMessage());
} 
 %>  
</div>
</div>
 


</div>
</body>
</html>