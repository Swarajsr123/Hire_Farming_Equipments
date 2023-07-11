<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<jsp:include page="DefaultTop.jsp"></jsp:include>
<div class="container"><br/><br/>
<%
if(request.getParameter("type").toString().trim().equals("Reg"))
{
	%><h2 class="h2">Your Registration Failed!!</h2>
	<br/>
	<a href="home">Home</a>
<%}
if(request.getParameter("type").toString().trim().equals("RegUser"))
{
	%><h2 class="h2">Your Registration Failed!!</h2>
	<br/>
	<a href="home">Home</a>
<%}
else if(request.getParameter("type").toString().trim().equals("RegProd"))
{
	%><h2 class="h2">Product Registration Failed!!</h2>
	<br/>
	<a href="farmer">Home</a>
<%
} 
else if(request.getParameter("type").toString().trim().equals("GreaterPrice"))
{
	%><h2 class="h2">Price Should be less than or equal to Rs.<%=request.getParameter("p") %>/-</h2>
	<br/>
	<a href="/UploadProduct?transid=0">try again</a>
<%
}
else if(request.getParameter("type").toString().trim().equals("ChangePass"))
{
	%><h2 class="h2">Authentication Failed!!</h2>
	<br/>
	<a href="/<%=session.getAttribute("utype").toString().trim() %>">Home</a>
<%
}
else if(request.getParameter("type").toString().trim().equals("Auth"))
{
	%><h2 class="h2">Authentication Failed!!</h2>
	<br/>
	<a href="/home">Home</a>
<%
}
else if(request.getParameter("type").toString().trim().equals("ReqUpd"))
{
	%><h2 class="h2">Request Updated Successfully....</h2>
	<br/>
	<a href="cityadminHome">Home</a>
<%
}
else if(request.getParameter("type").toString().trim().equals("passEmail"))
{
	%><h2 class="h2">New Password Sent on your registered email id Successfully....</h2>
	<br/>
	<a href="home">Home</a>
<%
}
else if(request.getParameter("type").toString().trim().equals("Doc"))
{
	%><h2 class="h2">Document Uploaded Successfully....</h2>
	<br/>
	<a href="userHome">Home</a>
<%
}
%>
</div>
</body>
</html>