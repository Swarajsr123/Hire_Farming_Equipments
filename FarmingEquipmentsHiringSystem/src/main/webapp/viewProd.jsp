
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
  <center><h2>My Registered Equipments</h2></center>
   </div>
   
 <c:forEach var="userdsc" items="${lst}">
	<div class="col-md-4">
		<table class="table table-bordered">
   <tr><td colspan="2">
   <div style="min-height:200px;background-size:contain;background-repeat: no-repeat;; background-image: url('Products/${userdsc.getUserid() }/${userdsc.getCoverphoto() }');"></div>
    </td></tr>
   <tr>
	<th>Title</th>	<td>${userdsc.getTitle() }</td>
	</tr><tr>
	<th>Description</th>	<td>${userdsc.getDesc() }</td>
	</tr><tr> 
 <th>Date</th><td>${userdsc.getDt() }</td>
	</tr><tr>
		<th>Time</th><td>${userdsc.getTm() }</td>
	</tr><tr>	<th>Price/unit</th><td>
	<form method="post" action="ChangePrice">
	 <input type="number" step="any" required name="price" value="${userdsc.getPrice() }" class="form-control"/>/${userdsc.getUnit() }
	 	 <input type="number" step="any" required name="ebprice" value="${userdsc.getEbprice() }" class="form-control"/>/${userdsc.getUnit() }
	 
	 <input type="hidden" value="${userdsc.getProdId() }" name="prodId"/>
	 <input type="submit" value="Update" class="btn btn-primary"/>
	 </form>
	 </td> 
	</tr><tr>
	 <th>Available Stock</th><td>
	 <form method="post" action="ChangeQuantity">
	 <input type="number" step="any" required name="quantity" value="${userdsc.getQuantity() }" class="form-control"/> 
	 <input type="hidden" value="${userdsc.getProdId() }" name="prodId"/>
	 <input type="submit" value="Update" class="btn btn-primary"/>
	 </form>
	 </td> 
	</tr> </table></div>
		</c:forEach> 
		 
		
		
  

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