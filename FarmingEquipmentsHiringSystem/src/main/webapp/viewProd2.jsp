
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
	response.sendRedirect("qrhome");
}
%>
<div class="container-fluid">
   

       
<div class="row">
 
<div class="col-md-12">
  <center><h2>Recommended Equipments</h2></center>
   </div>
   
 <c:forEach var="userdsc" items="${lst}">
	<div class="col-md-4">
		<table class="table table-bordered">
   <tr><td colspan="2">
   <div style="min-height:200px;background-size:contain;background-repeat: no-repeat; background-image: url('Products/${userdsc.getUserid() }/${userdsc.getCoverphoto() }');"></div>
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
	 Rs.${userdsc.getPrice() }/${userdsc.getUnit() }
	 
	 </td> 
	</tr>
	<tr>	<th>Economically Backword Price/unit</th><td>
	 Rs.${userdsc.getEbprice() }/${userdsc.getUnit() }
	 
	 </td> 
	</tr>
	
	<tr>
	 <th>Available Stock</th><td>
	 ${userdsc.getQuantity() } 
	  
	 </td> 
	</tr> 
	<tr>
	<td colspan="1">
	<c:choose>
  <c:when test="${userdsc.getQuantity()>0}">
  
   
	 <form action="AddToCart" method="post">
                                     <input type="hidden" name="prodId" value="${userdsc.getProdId()}"> 
                                        <input type="hidden" name="title" value="${userdsc.title}">
                                         <input type="hidden" name="price" value="${userdsc.price}">
                                         <input type="hidden" name="ebprice" value="${userdsc.ebprice}">
                                         <input type="hidden" name="availquan" value="${userdsc.getQuantity()}">
                                         <input type="hidden" name="userid1" value="${userdsc.userid}">
                                         <input type="hidden" name="selleruid" value="${userdsc.userid}">
                                         <input type="hidden" name="sellerunm" value="${userdsc.usernm}">
                                         <input type="hidden" name="sellerutype" value="${userdsc.userType}">
                                         <input type="hidden" name="page" value="viewProd1.jsp">
                                         <input type="hidden" name="category" value="NA">
                                         <input type="hidden" name="subcategory" value="NA">
                                        <button type="submit" class="hub-cart phub-cart btn">
                                            Add To Cart<i class="fa fa-cart-plus" aria-hidden="true"></i>
                                        </button>
                                        <a href="#" data-toggle="modal" data-target="#myModal1"></a>
                                    </form>
             </c:when> 
  <c:otherwise>Out of Stock
  </c:otherwise>
</c:choose>
	                       
	</td>
	<td>
	<a target="_blank" href="viewDistributors2?userid=${userdsc.userid}">View Distributor Details</a>
	</td>
	<td>
	 </td>
	</tr>
	</table></div>
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