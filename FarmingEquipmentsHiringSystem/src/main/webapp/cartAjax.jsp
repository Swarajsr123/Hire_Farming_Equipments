<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
     <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<%System.out.println("in ajax"); %>
<%try{ %>
 <%
    List lst=(List)request.getAttribute("lst");
     if(lst.size()==0)
     {
    	 %><Br/><div class="alert alert-success">
    	 No Record Found!!
    	 </div> <% 
     }else{
    %>
<table class="table table-bordered">
 <tr>
 <th>Product Title</th>
 <th>Unit Price in Rs.</th>
 <th>Quantity</th>
 <th>Price in Rs.</th>
  <th></th>
 </tr>
 <c:forEach var="userdsc" items="${lst}"> 
		<tr><td>${userdsc.getTitle() }</td>  
			<td>${userdsc.getPrice()}</td>
			 <td><input type="number"  required step="any" name="${userdsc.getCartid()}" value="${userdsc.getQuantity()}" onkeyup="makeGetRequestCart(this.value,this.name,${userdsc.getAvailQuantity()})" />
			<input type="hidden" name="availQuantity" value="${userdsc.getAvailQuantity()}"/>
			</td>
			<td>${userdsc.getTotalprice()}</td>
			 <td>
			 <a href="RemoveItem?cartid=${userdsc.getCartid()}">Remove</a>
			 </td>
			</tr>
		</c:forEach>
		<tr><td colspan="5">
		<form action="PlaceOrder"  method="post">
		<input type="submit" class="btn btn-primary" value="Place Order"/>
		</form>
		</td></tr>
		</table>
		<%}}catch(Exception exX)
{
			System.out.println("err in ajazx="+exX.getMessage());
}%>
</body>
</html>