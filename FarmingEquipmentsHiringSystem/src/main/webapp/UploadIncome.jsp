
 
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

<div class="col-md-6"> <h2>Register Income Details</h2>
   <div class="form-group"> 
    <form method="post" name="frm" action="/RegIncome" enctype="multipart/form-data">
<%
//Vector v=new Vector();
//JavaFuns jf=new JavaFuns();
//v=jf.getValue("select transId,prodName from purchasedProducts where userid='"+session.getAttribute("userid").toString().trim()+"'", 2);
%>
<table class="tblform"><tr><td>Year</td><td>
    <input type="text" name="year" value="<%=request.getAttribute("year").toString().trim() %>" class="form-control" required></input>
     </td></tr>
     <tr>
     <td>Income</td>
     <td>
  <input type="number" step="any" name="income" class="form-control" required></input>
     
 </td></tr>
  
 <tr><td>Proof</td><td>
    <input type="file" name="file" class="form-control" required ></input>
 </td></tr>
 <tr><td colspan="2">
    <input type="submit" class="btn btn-primary" value="Submit" />
    </td></tr>
 
</table></form>
 
</div></div>
 <div class="col-md-6">
 <%
 JavaFuns jf=new JavaFuns();
 Vector v=jf.getValue("select u.userName as name,u.mobile,i.* from userdetails u inner join farmer_income i on u.userid=i.userid and i.userid='"+session.getAttribute("userid").toString().trim()+"'" , 8);
 
 %>
 <table class="table table-bordered">
 <tr>
 <th> User Name</th>
 <th>Mobile No</th>
 <th>Income</th>
 
 <th>Year</th>
  <th>Proof</th>
  <th>Status</th>
 </tr>
 <%for(int i=0;i<v.size();i=i+8){ %>
 <tr>
 <td><%=v.elementAt(i).toString() %></td>
 <td><%=v.elementAt(i+1).toString() %></td>
 <td><%=v.elementAt(i+4).toString() %></td>
 <td><%=v.elementAt(i+5).toString() %></td>
 <td><%=v.elementAt(i+6).toString() %></td>
 <td><%=v.elementAt(i+7).toString() %></td>
 </tr>
 <%} %>
 </table>
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