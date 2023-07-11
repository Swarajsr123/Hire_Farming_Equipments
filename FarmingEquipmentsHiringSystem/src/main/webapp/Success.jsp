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
	%><h2 class="h2">Your Registration Done Successfully....</h2>
	<br/>
	<a href="home">Home</a>
<%}
if(request.getParameter("type").toString().trim().equals("RegUser"))
{
	%><h2 class="h2">Your Registration Done Successfully....</h2>
	<br/>
	<a href="home">Home</a>
<%}
else if(request.getParameter("type").toString().trim().equals("RegProd"))
{
	%><h2 class="h2">Equipment Registered Successfully....</h2>
	<br/>
	<a href="distributor">Home</a>
<%
}
else if(request.getParameter("type").toString().trim().equals("paymentApproved"))
{
	%><h2 class="h2">Payment Approved Successfully....</h2>
	<br/>
	<a href="viewPendingPayments">Home</a>
<%
}
else if(request.getParameter("type").toString().trim().equals("incomeApproved"))
{
	%><h2 class="h2">Income Request Approved Successfully....</h2>
	<br/>
	<a href="viewPendingIncome">Home</a>
<%
}
else if(request.getParameter("type").toString().trim().equals("returnProd"))
{
	%><h2 class="h2">Product Returned Successfully....</h2>
	<br/>
	<a href="farmer">Home</a>
<%
}
else if(request.getParameter("type").toString().trim().equals("FarmReg"))
{
	%><h2 class="h2">Farm Registered Successfully....</h2>
	<br/>
	<a href="farmer">Home</a>
<%
}
else if(request.getParameter("type").toString().trim().equals("sendEnq"))
{
	%><h2 class="h2">Enquiry Sent Successfully....</h2>
	<br/>
	<a href="farmer">Home</a>
<%
}
else if(request.getParameter("type").toString().trim().equals("RegPayment"))
{
	%><h2 class="h2">Payment Receipt Uploaded Successfully....</h2>
	<br/>
	<a href="farmer">Home</a>
<%
}
else if(request.getParameter("type").toString().trim().equals("RegIncome"))
{
	%><h2 class="h2">Income Registered Successfully....</h2>
	<br/>
	<a href="farmer">Home</a>
<%
}
else if(request.getParameter("type").toString().trim().equals("ProdPerReg"))
{
	%><h2 class="h2">Product Percentage Registered Successfully....</h2>
	<br/>
	<a href="admin">Home</a>
<%
}
else if(request.getParameter("type").toString().trim().equals("ProdUpdate"))
{
	%><h2 class="h2">Equipment Updated Successfully....</h2>
	<br/>
	<a href="viewProducts">Home</a>
<%
}
else if(request.getParameter("type").toString().trim().equals("placeOrder"))
{
	%><h2 class="h2">Order Placed Successfully....</h2>
	<br/>
	<a href="<%=session.getAttribute("utype").toString().trim() %>">Home</a>
<%
}
else if(request.getParameter("type").toString().trim().equals("OrderProcess"))
{
	%><h2 class="h2">Order Processed Successfully....</h2>
	<br/>
	<a href="<%=session.getAttribute("utype").toString().trim() %>">Home</a>
<%
}
else if(request.getParameter("type").toString().trim().equals("DocReg"))
{
	%><h2 class="h2">Document Registration Done Successfully....</h2>
	<br/>
	<a href="userHome">Home</a>
<%
}
else if(request.getParameter("type").toString().trim().equals("Req"))
{
	%><h2 class="h2">Request Sent Successfully....</h2>
	<br/>
	<a href="/userHome">Home</a>
<%
}
else if(request.getParameter("type").toString().trim().equals("ChangePass"))
{
	%><h2 class="h2">Password Changed Successfully....</h2>
	<br/>
	<a href="/<%=session.getAttribute("utype").toString().trim() %>">Home</a>
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