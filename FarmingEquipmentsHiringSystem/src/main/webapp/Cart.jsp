
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
     
<script language="Javascript" type="text/javascript">
 

function createRequestObject() {
    var tmpXmlHttpObject;
    if (window.XMLHttpRequest) {
            tmpXmlHttpObject = new XMLHttpRequest();
    } else if (window.ActiveXObject) {
        tmpXmlHttpObject = new ActiveXObject("Microsoft.XMLHTTP");
    }

    return tmpXmlHttpObject;
}


var http = createRequestObject();

function makeGetRequestCart(val,nm,availq) { 
	if(val==null)
		val=0
	 //alert(availq +" "+val); 
	//alert('in ajax'+ document.getElementByName("availQuantity").value);
    if(val>availq)
    {
    	alert("Out of Stock!!");    	
    }
    else
    {
    	if(val==0)
        {
        	alert("Enter Quantity!!");    	
        }
    	else
    	{ 
	http.open('get', 'updateCart?cartid='+nm+'&quan='+val );
    http.onreadystatechange = processResponseCart;
    http.send(null);
    }}
}

function processResponseCart() {
    if(http.readyState == 4){
        var response = http.responseText;
        document.getElementById('cart1').innerHTML = response;
    }
}
 
</script>
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
<div class="container">
  <div class="jumbotron"> 

     
<div class="row">

<div class="col-md-12"> <h2>My Cart</h2>
    <div id="cart1">
    <%
    List lst=(List)request.getAttribute("lst");
     if(lst.size()==0)
     {
    	 %><Br/><div class="alert alert-success">
    	 No Record Found!!
    	 </div> <% 
     }else{
    	 //System.out.println("quan="+request.getAttribute("availquan").toString().trim());
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
		
		<%} %>
		</div>
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