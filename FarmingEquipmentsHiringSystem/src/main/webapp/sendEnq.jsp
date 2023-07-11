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
//response.sendRedirect("viewDistributors1");
   
 %><center><h2>Send Enquiry</h2>
 <form method="post" action="sendEnq">
 <table>
 <tr><td>Title</td><td>
 
 <textarea name="title" class="form-control"></textarea>
 
 </td>
 </tr>
 <tr><td colspan="2">
 <input type="submit" value="Submit" class="btn btn-primary"/>
 </td>
 </tr>
 </table></form>
<%}
catch(Exception ex)
{
	
}
%>
</body>
</html>