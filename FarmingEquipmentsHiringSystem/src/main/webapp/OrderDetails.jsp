
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

<div class="col-md-12"> <h2>Hired Equipments Details<input type="button" value="Back" class="btn btn-primary" onclick="window.history.back();"/></h2>
    
 <table class="table table-bordered table-responsive">
 <tr>
  
 <th>Order No</th>
 <th>Equipment Id</th>
 <th>Equipment Name</th>
 <th>Quantity</th>
 <th>Price in Rs.</th>
 <th>Total Price in Rs.</th>
 
 </tr>
 <c:forEach var="userdsc" items="${lst}">
		<tr>
		 
		<td>${userdsc.getProdId() }</td> 
		<td>${userdsc.getOrderno() }</td> 
		<td>${userdsc.getTitle() }</td> 
			<td>${userdsc.getQuantity()}</td>
			<td>Rs.${userdsc.getPrice()}/-</td>
			<td>Rs.${userdsc.getTotalprice()}/-</td>
			 <c:choose>
  <c:when test="${userdsc.getTotalprice()==0 && userdsc.getOrdersts().trim()!='pending' }">
    <td><a href="returnProd?prodId=${userdsc.getProdId()}">Return</a></td>
  </c:when>
  <c:when test="${userdsc.getTotalprice()>0}">
   </c:when>
  <c:otherwise>
    
  </c:otherwise>
</c:choose>
 
		</tr>
		</c:forEach>
		<tr><th>Netbill</th>
		<td colspan="5"><b>Rs.<%=request.getAttribute("netbill").toString().trim() %>/-</b>
		<form method="post" action="paybill"  enctype="multipart/form-data">
		<input type="hidden" name="orderno" value="<%=request.getAttribute("orderNo").toString().trim() %>"/>
		<input type="hidden" name="sellerUserid" value="<%=request.getAttribute("sellerUserid").toString().trim() %>"/>
		<input type="hidden" name="netbill" value="<%=request.getAttribute("netbill").toString().trim() %>"/>
		<input type="hidden" name="userid" value="<%=session.getAttribute("userid").toString().trim() %>"/>
		<input type="hidden" name="username" value="<%=session.getAttribute("username").toString().trim() %>"/>
		<input type="hidden" name="mobile" value="<%=session.getAttribute("mobile").toString().trim() %>"/>
		<input type="file" name="file" class="form-control"/>
		<input type="submit" value="Submit" class="btn btn-primary"/>
		</form>
		</td>
		</table>
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