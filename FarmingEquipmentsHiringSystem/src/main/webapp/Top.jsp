 
<!doctype html>
<%@page import="com.models.Cart"%>
<html lang="en">
  <head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <title> </title>
    <!-- web fonts -->
   <!-- web fonts -->
   <link href="//fonts.googleapis.com/css?family=Poppins:300,400,500,600,700,800,900&display=swap" rel="stylesheet">
   <link href="//fonts.googleapis.com/css?family=Hind&display=swap" rel="stylesheet">
   <!-- //web fonts -->
    <!-- //web fonts -->
    <!-- Template CSS -->
    <link rel="stylesheet" href="assets/css/style-starter.css">
  </head>
  <body>

<%
try
{  response.setHeader("Pragma", "No-cache");
response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
response.setDateHeader("Expires", -1);
if(session.getAttribute("userid")==null)
{
	response.sendRedirect("home");
}
%>

<!-- Top Menu 1 -->
<section class="w3l-top-menu-1">
	<div class="top-hd greenback">
		<div class="container">
	<header class="row top-menu-top">
	 
	</header>
</div>
</div>
</section>
<!-- //Top Menu 1 -->
<section class="w3l-bootstrap-header">
  <nav class="navbar navbar-expand-lg navbar-light py-lg-2 py-2">
    <div class="container-fluid">
       <a class="navbar-brand logo" href="/home"><span class="logo">Farming Equipment Hiring System
 </a>
      <!-- if logo is image enable this   
    <a class="navbar-brand" href="#index.html">
        <img src="image-path" alt="Your logo" title="Your logo" style="height:35px;" />
    </a> -->
      <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent"
        aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon fa fa-bars"></span>
      </button>
 <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav mx-auto mt-2">
          <li class="nav-item">
       <a class="nav-link" href="<%=session.getAttribute("utype").toString().trim() %>" >Home</a></li>
								<%if(session.getAttribute("utype").toString().trim().equals("admin"))
                                	{
                                	%>
                                	
                                		<li class="nav-item"><a class="nav-link" href="viewPendingIncome" >Pending Income Request</a></li>
                            	
								 <li>
             <div class="dropdown">
  <button type="button" style="background:transparent;border:none" class="dropdown-toggle nav-item nav-link" data-toggle="dropdown">
    Reports
  </button>
  <div class="dropdown-menu">
    <a class="dropdown-item" href="viewFarmers">Farmers</a>
    <a class="dropdown-item" href="viewDistributors">Equipments Distributors</a>
    </div>
</div> 
            </li>
             
								 <%} else if(session.getAttribute("utype").toString().trim().equals("farmer"))
                            	{
                            	%><li  class="nav-item"><a class="nav-link" href="RegFarmerDetails.jsp">Register Farms</a> </li>
                            	 <li  class="nav-item"><a class="nav-link" href="viewFarms">Farms</a>
                            	 	<li class="nav-item"><a class="nav-link" href="UploadIncome" >Upload Income Proof</a></li>
                            	 	<li class="nav-item"><a class="nav-link" href="sendEnq.jsp" >Send Enquiry</a></li>
                            	 	<li class="nav-item"><a class="nav-link" href="MyOrders" >view Hired Equipments</a></li>
                            	
                            	 <%}     else if(session.getAttribute("utype").toString().trim().equals("distributor"))
                            	{
                            	%>
                            	<li class="nav-item"><a class="nav-link" href="UploadProduct?transid=0" >Register New Equipment</a></li>
                            	
                            	<li class="nav-item"><a class="nav-link" href="viewProducts" >View My Equipments</a></li>
                            	<li class="nav-item"><a class="nav-link" href="viewFarmers" >View Farmers</a></li>
                            	 
                            	 
                            	 <li class="nav-item"><a class="nav-link" href="viewPendingPayments" >Pending Payments</a></li>
                            	
                            	<li class="nav-item"><a class="nav-link" href="demand.jsp" >Equipment Demand</a></li>
                            	 <%}  %>
								<li class="nav-item"><a class="nav-link" href="ChangePass">Change Password</a></li>
								<li class="nav-item"><a class="nav-link" href="logout">Logout</a></li>
        </ul>
        
      </div>
    </div>
  </nav>
</section>
<section class="w3l-about-breadcrum">
  <div class="breadcrum-bg">
    <div class="container py-5">
     
      <h2 class="my-3">Farming Equipments Available on Rent</h2>
     <p>Register and get required farming equipments </p>
    </div>
  </div>
</section>
 <div class="container-fluid">
    <div class="CartPanel">
    <div class="row">
    <div class="col-md-9">
      Logged in as <%=session.getAttribute("username").toString() %>( <%=session.getAttribute("utype").toString() %>)
  <%if(!(session.getAttribute("utype").toString().trim().equals("farmer")||session.getAttribute("utype").toString().trim().equals("admin"))){ %>
    
   <%} %>
    
    </div>
    <div class="col-md-3">
   <%if(!(session.getAttribute("utype").toString().trim().equals("distributor") || session.getAttribute("utype").toString().trim().equals("admin")))
                                	{
                                	%>
    <% int totalItems=0;
    Cart cart=new Cart();
    totalItems=cart.totalItemsInCart(session.getAttribute("userid").toString().trim());
    %>
   <a href="ShowCart"> Total Items in your Cart : <i class="fa fa-cart"></i> <span><%=totalItems %></span> </a>
   <%} %>
   </div>
  </div>  </div>
    </div>
    <!--// end-smoth-scrolling -->
    <%}catch(Exception ex)
{
    	System.out.println("err="+ex.getMessage());
    	 
}%>
 
 
  <!-- move top -->
  <button onclick="topFunction()" id="movetop" title="Go to top">
    <span class="fa fa-angle-up"></span>
  </button>
  <script>
    // When the user scrolls down 20px from the top of the document, show the button
    window.onscroll = function () {
      scrollFunction()
    };

    function scrollFunction() {
      if (document.body.scrollTop > 20 || document.documentElement.scrollTop > 20) {
        document.getElementById("movetop").style.display = "block";
      } else {
        document.getElementById("movetop").style.display = "none";
      }
    }

    // When the user clicks on the button, scroll to the top of the document
    function topFunction() {
      document.body.scrollTop = 0;
      document.documentElement.scrollTop = 0;
    }
  </script>
  <!-- /move top -->
</section>
<script src="assets/js/jquery-3.3.1.min.js"></script>
<!-- //footer-28 block -->
</section>
<script>
  $(function () {
    $('.navbar-toggler').click(function () {
      $('body').toggleClass('noscroll');
    })
  });
</script>
<!-- jQuery first, then Popper.js, then Bootstrap JS -->
<script src="https://code.jquery.com/jquery-3.4.1.slim.min.js"
  integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n" crossorigin="anonymous">
</script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"
  integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous">
</script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"
  integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous">
</script>

<!-- Template JavaScript -->
<script src="assets/js/all.js"></script>
<!-- Smooth scrolling -->
<!-- <script src="assets/js/smoothscroll.js"></script> -->
<script src="assets/js/owl.carousel.js"></script>

<!-- script for -->
<script>
  $(document).ready(function () {
    $('.owl-one').owlCarousel({
      loop: true,
      margin: 0,
      nav: true,
      responsiveClass: true,
      autoplay: false,
      autoplayTimeout: 5000,
      autoplaySpeed: 1000,
      autoplayHoverPause: false,
      responsive: {
        0: {
          items: 1,
          nav: false
        },
        480: {
          items: 1,
          nav: false
        },
        667: {
          items: 1,
          nav: true
        },
        1000: {
          items: 1,
          nav: true
        }
      }
    })
  })
</script>
<!-- //script -->

</body>

</html>
  