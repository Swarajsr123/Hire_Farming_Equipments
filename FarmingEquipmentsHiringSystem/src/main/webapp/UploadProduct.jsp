
 
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

<div class="col-md-6"> <h2>Register Equipments</h2>
   <div class="form-group"> 
    <form method="post" name="frm" action="/RegProduct" enctype="multipart/form-data">
<%
//Vector v=new Vector();
//JavaFuns jf=new JavaFuns();
//v=jf.getValue("select transId,prodName from purchasedProducts where userid='"+session.getAttribute("userid").toString().trim()+"'", 2);
%>
<table class="tblform"><tr><td>Title</td><td>
    <input type="text" name="title" class="form-control" required></input>
     </td></tr>
     <tr>
     <td>Description</td>
     <td>
  <textarea name="desc" class="form-control" required></textarea>
     
 </td></tr>
 <tr>
     <td>Price</td>
     <td>
  <input type="number" step="any" name="price" class="form-control" required></input>
     
 </td></tr>
 <tr>
     <td>Price for Economically Backward Farmers</td>
     <td>
  <input type="number" step="any" name="ebprice" class="form-control" required></input>
     
 </td></tr>
 <tr>
     <td>period</td>
     <td>
   <select name="unit" class="form-control" required>
   <option value="month">/month</option>
   <option value="day">/day</option>
   <option value="year">/year</option>
    <option value="hr">/hour</option> 
    
   </select>
 </td></tr>
 <tr>
     <td>Soil Type</td>
     <td> 
   <select name="soilType" class="form-control" required>
   <option value="any">any</option>
         <option value="Sandy soil">Sandy soil</option>
		            <option value="Clay soil">Clay soil</option>
		            <option value="Peat soil">Peat soil</option>
		            <option value="Chalk soil">Chalk soil</option>
		            <option value="Loam soil">Loam soil</option>
   </select>
 </td></tr>
 <tr>
     <td>Farm Type</td>
     <td> 
   <select name="farmType" class="form-control" required>
   <option value="any">any</option>
   <option value="small">small</option>
   <option value="moderate">moderate</option>
    <option value="big">big</option>  
   </select>
 </td></tr>
 <input type="hidden" name="transid" value="0"/>
  
 <tr>
     <td>Available Quantity</td>
     <td>
  <input type="number" step="any" name="quantity" class="form-control" required></input>
     
 </td></tr>
 <tr><td>Cover Photo</td><td>
    <input type="file" name="file" class="form-control" required ></input>
 </td></tr>
 <tr><td colspan="2">
 <!-- <input type="hidden" value="<%=request.getAttribute("transid").toString().trim() %>" name="transid"/> -->
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