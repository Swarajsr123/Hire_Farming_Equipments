
 
<%@page import="com.models.JavaFuns"%>
<%@page import="java.util.Vector"%>
<%@page import="java.util.List"%>

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
	response.sendRedirect("index.jsp");
}
%>
<div class="container">
  <div class="jumbotron"> 

     
<div class="row">

<div class="col-md-6"> <h2>Register Products Price Percentage</h2>
   <div class="form-group"> 
    <form method="post" name="frm" action="/RegProductPer">
 
<table class="tblform"><tr><td>User Type</td><td>
     <select name="utype" class="form-control" required>
   <option value="distributor">Distributor</option>
   <option value="retailer">Retailer</option>
    
   </select>
     </td></tr>
      <tr>
     <td>Percentage</td>
     <td>
  <input type="number" step="any" name="percent" class="form-control" required></input>
     
 </td></tr>
  
 <tr><td colspan="2">
    <input type="submit" class="btn btn-primary" value="Submit" />
    </td></tr>
 
</table></form>
 
</div></div>
 <div class="col-md-6">
 <img src="images/products.jpg" width="100%" class="img-responsive"/>
 </div>
</div>
</div>
<%}
catch(Exception ex)
{
	
} %>

</div>
</body>
</html>