<%@page import="com.models.*"%>
<%@page import="java.util.List"%>
<%@page import="com.models.GetStateNCities"%>
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

function makeGetRequest(st) {
   // st=document.frm.state.value;
   
    http.open('get', 'Cities?state=' + st);
    http.onreadystatechange = processResponse;
    http.send(null);
}

function processResponse() {
    if(http.readyState == 4){
        var response = http.responseText;
        document.getElementById('cities').innerHTML = response;
    }
}
 
</script>

</head>
<body>
<jsp:include page="Top.jsp"></jsp:include>
<%  response.setHeader("Pragma", "No-cache");
response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
response.setDateHeader("Expires", -1);%>
<div class="container">
<div class="row">

<div class="col-md-6">
<img src="images/Registration.png" width="100%"/>
</div>
<div class="col-md-6">
<div ><center><h2>Enter your farm details to get equipment recommendations</h2></center><br/>
 <form id="frm" action="FarmDetails" method="post">
	 <table class="tblform">
						<tr>					  
	            <td>  Title</td>
		            <td>
		            <input type="text"  required name="title" class="form-control" />          
		            </td>
            </tr>	
            <tr>
     <td>Farm Type</td>
     <td> 
   <select name="farmType" class="form-control" required>
    
   <option value="small">small</option>
   <option value="moderate">moderate</option>
    <option value="big">big</option>  
   </select>
 </td></tr>		
				<tr>					  
	             <td> Soil Type</td>
		            <td> 
		              <input type="hidden" name="userid" value="<%=session.getAttribute("userid").toString().trim() %>"/>
		          
		            <select name="soiltype" class="form-control" required>
		            <option value="Sandy soil">Sandy soil</option>
		            <option value="Clay soil">Clay soil</option>
		            <option value="Peat soil">Peat soil</option>
		            <option value="Chalk soil">Chalk soil</option>
		            <option value="Loam soil">Loam soil</option>
		            </select>
		            </td>
            </tr>
            
				<tr>					  
	            <td>  Address</td>
		            <td>
		            <textarea rows="5" cols="25" required name="addr" class="form-control" ></textarea>          
		            </td>
            </tr>
             <%
									 GetStateNCities obj=new GetStateNCities();
									 obj.getStates();
									 List<States> lst=obj.getLststate();
									 %>
									  <tr>
									 <td>State
									 </td>
									 <td> 
									 <select required name="state" class="form-control" onchange="makeGetRequest(this.value)">
									 <option value=""><--select--></option>
										<%for(int i=0;i<lst.size();i++)
											{%>
									 <option value="<%=lst.get(i).getState() %>"><%=lst.get(i).getState() %></option>											
											<%}%>															  
									 </select>
									 </td>
									 </tr>
									   <tr>
									 <td>City
									 </td>
									 <td> 
									<div id="cities"></div>
									 </td>
									 </tr>
									 <tr>					  
	            <td>  Village</td>
		            <td>
		            <textarea  required name="village" class="form-control" ></textarea>          
		            </td>
            </tr>	 <tr>					  
	            <td>  Pincode</td>
		            <td>
		            <input type="text"  required name="pincode" class="form-control" />          
		            </td>
            </tr>
					    <tr>
				 <td colspan="2"><input type="submit" value="Submit" class="btn btn-primary"/>
				  </td></tr>
				  </table>
		  </form>
</div>
</div>

</div>

</div>
</body>
</html>