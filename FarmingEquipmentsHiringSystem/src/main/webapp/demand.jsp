<%@page import="java.util.Vector"%>
<%@page import="com.models.JavaFuns"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Agro-SupplyChain</title>
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
JavaFuns jf=new JavaFuns();
Vector v=jf.getValue("select prodId,title from products", 2);
Vector v2=jf.getValue("select title from enquiry", 1);

 %><div class="container">
 <div class="row">
 <div class="col-md-6"><center><h2>Demanded Products</h2></center>
  <table class="table table-bordered">
  <tr><th>Title</th></tr>
  
 <%for(int i=0;i<v.size();i=i+2) {
	 Vector v1=jf.getValue("select cnt from getprodDemand where productid="+v.elementAt(i).toString().trim(), 1);
	 if(v1.size()>0)
	 {
 %>
 <tr><td>
 <%=v.elementAt(i+1).toString().trim() %> 
   </td></tr>
 <%} }%>
 </table></div>
 <div class="col-md-6"><h2>Equipments Demanded By Farmer</h2>
 <table class="table table-bordered">
  <tr><th>Title</th></tr>
  
 <%for(int i=0;i<v2.size();i++) {
	 
 %>
 <tr><td>
 <%=v2.elementAt(i).toString().trim() %> 
   </td></tr>
 <%}  %>
 </table>
 </div>
 
  </div>
<%}
catch(Exception ex)
{
	
}
%>
</body>
</html>