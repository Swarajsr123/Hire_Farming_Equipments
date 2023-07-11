package com.farming;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.models.*;

import com.services.RandomString;
 
import com.services.Mail; 

@Controller
public class FarmingController {
	@RequestMapping("/home")
	public String myspring()
	{
		return "index.jsp";
	}
	@RequestMapping("/sendEnq")
	public String sendEnq(HttpServletRequest request,HttpSession ses)
	{
		JavaFuns jf=new JavaFuns();
		int mx=jf.FetchMax("eid", "enquiry");
		String qr="insert into enquiry values("+mx+",'"+request.getParameter("title").trim()+"','"+ses.getAttribute("userid").toString().trim()+"')";
		if(jf.execute(qr)) {}
		return "Success.jsp?type=sendEnq";
	}
	@RequestMapping("/admin")
	public String admin()
	{
		return "admin.jsp";
	}
	@RequestMapping("/retailer")
	public String retailer()
	{
		return "retailer.jsp";
	}
	@RequestMapping("/forgot")
	public String forgot()
	{
		return "Forgot.jsp";
	}
	@RequestMapping("/ChangePass")
	public String ChangePass()
	{
		return "ChangePass.jsp";
	}
	@RequestMapping("/passRecoveryOTPAuth")
	public ModelAndView passRecoveryOTPAuth(UserReg user)
	{
		ModelAndView mv=new ModelAndView();
		try {
			if(user.getSentOTP().equals(user.getOtp()))
			{
				String pass=RandomString.getAlphaNumericString(8);
				user.setPass(pass);
				if(user.updatePass())
				{
					
				}
				
				
			    mv.setViewName("Success.jsp?type=passEmail");
			    
			    Mail mail=new Mail();
			    String msg="Dear "+user.getName()+" \n Your password has been reset to "+pass;
			    System.out.println("pass="+pass);
			    try
			    {
			    	if(mail.sendMail(msg,user.getEmail(), "New password"))
			    	{
			    		
			    	}
			    }
			    catch (Exception e) {
					// TODO: handle exception
				}
			}
			else
			{
				mv.setViewName("Failure.jsp?type=passEmail");
			}
			
		}
		catch (Exception e) {
			// TODO: handle exception
		}
		
	    return mv;
	}
	@RequestMapping("/passRecovery")
	public ModelAndView passRecovery(UserReg user)
	{
		ModelAndView mv=new ModelAndView();
		try {
			if(user.useridAuth())
			{
				String otp=RandomString.getAlphaNumericString(4);
				
			    mv.setViewName("ForgotOTP.jsp");
			    mv.addObject("userid",user.getUserid());
			    mv.addObject("otp",otp);
			    mv.addObject("email",user.getEmail());
			    Mail mail=new Mail();
			    String msg="Dear "+user.getName()+" \n Your one time password is "+otp;
			    System.out.println("otp="+otp);
			    try
			    {
			    	if(mail.sendMail(msg,user.getEmail(), "One Time Password"))
			    	{
			    		
			    	}
			    }
			    catch (Exception e) {
					// TODO: handle exception
				}
			}
			else
			{
				mv.setViewName("Failure.jsp?type=Auth");
			}
			
		}
		catch (Exception e) {
			// TODO: handle exception
		}
		
	    return mv;
	}
	 
	@RequestMapping("/ChangePassService")
	public String ChangePassService(Pass eobj,HttpSession ses)
	{ 
		 try
		 { 
			 JavaFuns jf=new JavaFuns();
			 Vector v=jf.getValue("select userid from users where userid='"+ses.getAttribute("userid").toString().trim()+"' and pass='"+eobj.getCurrentpass()+"'", 1);
			 if(v.size()>0)
			 {
			 eobj.setUserid(ses.getAttribute("userid").toString().trim());
			 if(eobj.changePassword())
			 { 
				 return "Success.jsp?type=ChangePass";
			 }
			 else
			 { 
				 return "Failure.jsp?type=ChangePass";
			 }
			 }
			 else
			 {
				 return "Failure.jsp?type=ChangePass";
			 }
		 }
		 catch (Exception e) {
			// TODO: handle exception
			 System.out.println("err="+e.getMessage());
			 return("Failure.jsp?type=Auth");
		}
		 
	}
	@RequestMapping("/distributor")
	public String distributor()
	{
		return "distributor.jsp";
	}
	@RequestMapping("/processor")
	public String processor()
	{
		return "processor.jsp";
	}
	@RequestMapping("/returnProd")
	public String returnProd(HttpServletRequest request,HttpSession ses)
	{
		JavaFuns jf=new JavaFuns();
		 
			 if(jf.execute("update orderedprods set sts='returned'  where userid='"+ses.getAttribute("userid").toString().trim()+"' and productid="+request.getParameter("prodId").trim())) {}
			 /*Mail mail=new Mail();
			 String msg="Dear Farmer, Distributor approved your payment";
			 
			 Vector vector=jf.getValue("select email from users where userid='"+request.getParameter("userid").trim()+"'", 1);
			 try
			 {
				 if(mail.sendMail(msg, vector.elementAt(0).toString().trim(),"Payment Request")) {}
			 }
			 catch (Exception e) {
				System.out.println("mail err="+e.getMessage());
			}*/
			return "Success.jsp?type=returnProd";
		 
		
	}
	@RequestMapping("/FarmDetails")
	public ModelAndView FarmDetails( FarmDetails plant, HttpServletRequest request)
	{
		ModelAndView mv=new ModelAndView();
		try {
		  
		 plant.registration();
		 mv.setViewName("Success.jsp?type=FarmReg");
		 mv.addObject("activity","FarmReg"); 
	 	}
		catch (Exception e) {
			// TODO: handle exception
			System.out.println("err in plantcontroller"+e.getMessage());
		}
		return mv;
	}
	@RequestMapping("/viewFarms")
	public ModelAndView viewFarms(HttpSession ses)
	{
		
		List< FarmDetails> lst = new ArrayList< FarmDetails>();
		 FarmDetails obj=new  FarmDetails();
		obj.setUserid(ses.getAttribute("userid").toString().trim());
		 obj.getFarmDetails();
		 lst=obj.getLstfarms();
		 System.out.println("size="+lst.size());
		ModelAndView mv = new ModelAndView();

		mv.setViewName("viewFarms.jsp");
		mv.addObject("lst", lst); 
		return mv;
		 
	}
	@RequestMapping("/approvePayment")
	public String approvePayment(HttpServletRequest request)
	{
		JavaFuns jf=new JavaFuns();
		if(request.getParameter("sts").toString().trim().equals("approved"))
		{
			if(jf.execute("update payments set sts='approved' where payid="+request.getParameter("id").trim())) {}
			if(jf.execute("update orderedprods set date='"+request.getParameter("date").trim()+"'  where orderno="+request.getParameter("orderno").trim())) {}
			 Mail mail=new Mail();
			 String msg="Dear Farmer, Distributor approved your payment";
			 
			 Vector vector=jf.getValue("select email from users where userid='"+request.getParameter("userid").trim()+"'", 1);
			 try
			 {
				 if(mail.sendMail(msg, vector.elementAt(0).toString().trim(),"Payment Request")) {}
			 }
			 catch (Exception e) {
				System.out.println("mail err="+e.getMessage());
			}
			return "Success.jsp?type=paymentApproved";
		}
		else
		{
			if(jf.execute("update payments set sts='declined' where payid="+request.getParameter("id").trim())) {}
			return "Success.jsp?type=paymentDeclined";
		}
		
	}
	@RequestMapping("/approveIncome")
	public String approveIncome(HttpServletRequest request)
	{
		JavaFuns jf=new JavaFuns();
		if(jf.execute("update farmer_income set sts='approved' where id="+request.getParameter("id").trim())) {}
		return "Success.jsp?type=incomeApproved";
	}
	@RequestMapping("/customer")
	public String customer()
	{
		return "customer.jsp";
	}
	@RequestMapping("/farmer")
	public String farmer()
	{
		return "farmer.jsp";
	}
	@RequestMapping("/registration")
	public String registration()
	{
		return "Registration.jsp";
	}
	@RequestMapping("/Cities")
	public String cities()
	{
		return "Cities.jsp";
	}
	@RequestMapping("/regProdPer")
	public String regProdPer()
	{
		return "regProdPer.jsp";
	}
	@RequestMapping("/RegProductPer")
	public String RegProductPer(HttpServletRequest request)
	{
		try
		{
			JavaFuns jf=new JavaFuns();
			Vector v=jf.getValue("select ifnull(max(pid),1000)+1 as mxid from prodPercent", 1);
			if(jf.execute("delete from prodPercent where utype='"+request.getParameter("utype").trim()+"'"))
			{
				
			}
			
			if(jf.execute("insert into prodPercent values("+Integer.parseInt(v.elementAt(0).toString().trim())+",'"+request.getParameter("utype").trim()+"',"+request.getParameter("percent").trim()+")"))
			{
				
			}
			
		}
		catch (Exception e) {
			// TODO: handle exception
		}
		return "Success.jsp?type=ProdPerReg";
	}
	@RequestMapping("/ProcessOrder")
	public String ProcessOrder(HttpServletRequest request,HttpSession ses)
	{
    	 
    	try {
    		Date dt1=new Date();
            String mon=String.valueOf((dt1.getMonth()+1));
            String dt11=String.valueOf((dt1.getDate()+1));
            if(mon.length()==0)
            	mon="0"+mon;
            if(dt11.length()==0)
            	dt11="0"+dt11;
           // dt=dt1.getDate()+"/"+(dt1.getMonth()+1)+"/"+(dt1.getYear()+1900);
           String dt=(dt1.getYear()+1900)+"-"+mon+"-"+dt11;
    	  JavaFuns jf=new JavaFuns();
    	  if(jf.execute("update orders set orderstatus='processed' where orderno="+request.getParameter("orderNo").trim()))
    	  {
    		  if(jf.execute("update orderedprods set date='"+dt+"' where sts='hired' and orderno="+request.getParameter("orderNo").trim())){}
    	  }
    	  
    	}
    	catch (Exception e) {
    		System.out.println("err in place order="+e.getMessage());
			// TODO: handle exception
		}
    	return "Success.jsp?type=OrderProcess";
	}
	@RequestMapping("/PaidOrder")
	public String PaidOrder(Orders ord,HttpServletRequest request,HttpSession ses)
	{
    	 
    	try {
    	  JavaFuns jf=new JavaFuns();
    	 
    		  
    		  Transactions obj=new Transactions();
    		  obj.setUserid(request.getParameter("userid").trim());
    		  obj.setSellerUserid(ord.getSellerUserid());
    		  obj.setSellerName(ord.getSellerUsernm());
    		  //CallMinnerAPI miner=new CallMinnerAPI();
    		//  APIManager1 miner=new  APIManager1();
    		  Vector v=jf.getValue("select productid,productname,quantity,price,date,userid from orderedprods where orderno="+ord.getOrderno() , 6);
    		  String transids="";
    		  for(int i=0;i<v.size();i=i+6)
    		  {
    			 obj.setProdId(Integer.parseInt(v.elementAt(i).toString().trim()));
    			 obj.setProdName(v.elementAt(i+1).toString().trim());
    			 obj.setPrice(Double.parseDouble(v.elementAt(i+3).toString().trim()));
    			 obj.setDt(v.elementAt(i+4).toString().trim());
    			 obj.setUserid(v.elementAt(i+5).toString().trim());
    			//  String result=miner.insertTrans(obj);
    			  PurchasedProducts purchase=new PurchasedProducts();
        		  purchase.setProdId(obj.getProdId());
        		  purchase.setUserid(obj.getUserid());
        		  Vector v1=jf.getValue("select transId from products where prodId="+obj.getProdId(), 1);
        		  System.out.println("trans="+v1.elementAt(0).toString().trim());
        		  purchase.setTransid(v1.elementAt(0).toString().trim()+",");
        		  purchase.setProdName(obj.getProdName());
        		  if(purchase.registration())
        		  {
        			  
        		  }
    			//  System.out.println("result="+result);
    			  
    		  }
    		  System.out.println("transids="+transids);
    		  
    		  if(jf.execute("update orders set orderstatus='paid', paymentsts='paid' where orderno="+request.getParameter("orderno").trim()))
        	  {   
        	  }
    	  
    	}
    	catch (Exception e) {
    		System.out.println("err in place order="+e.getMessage());
			// TODO: handle exception
		}
    	return "Success.jsp?type=OrderProcess";
	}
	@RequestMapping("/MyOrders")
	public ModelAndView MyOrders(HttpServletRequest request,HttpSession ses)
	{
    	ModelAndView mv=new ModelAndView();
    	
    	try {
    	 MyOrders order=new MyOrders();
    	 order.setUserid(ses.getAttribute("userid").toString().trim());
    	 order.getMyOrders("all");
    	 List<MyOrders> lstorder=new ArrayList<MyOrders>();
    	 lstorder=order.getLstorders();
    	 mv.setViewName("orders.jsp");
    	 mv.addObject("lst",lstorder);
    	}
    	catch (Exception e) {
    		System.out.println("err in place order="+e.getMessage());
			// TODO: handle exception
		}
    	return mv;
	}
	@RequestMapping("/viewOrderDetails")
	public ModelAndView viewOrderDetails(HttpServletRequest request,HttpSession ses)
	{
    	ModelAndView mv=new ModelAndView();
    	
    	try {
    	  Orders order=new  Orders();
    	  order.setOrdersts(request.getParameter("ordersts"));
    	  order.getOrderDetails(Integer.parseInt(request.getParameter("orderNo").trim()));
    	 List<Orders> lstorder=new ArrayList<Orders>();
    	 lstorder=order.getLstorders();
    	 mv.setViewName("OrderDetails.jsp");
    	 mv.addObject("lst",lstorder);
    	 double netbill=0;
    	 for(int i=0;i<lstorder.size();i++)
    	 {
    		 netbill+=lstorder.get(i).getNetbill();
    	 }
    	 mv.addObject("netbill",netbill);
    	 mv.addObject("orderNo",request.getParameter("orderNo"));
    	 mv.addObject("sellerUserid",request.getParameter("sellerUserid"));
    	 
    	}
    	catch (Exception e) {
    		System.out.println("err in place order="+e.getMessage());
			// TODO: handle exception
		}
    	return mv;
	}
	@RequestMapping("/viewPendingPayments")
	public ModelAndView viewPendingPayments(HttpServletRequest request,HttpSession ses)
	{
    	ModelAndView mv=new ModelAndView();
    	
    	try {
    	 Orders obj=new  Orders();
    	 obj.setUserid(ses.getAttribute("userid").toString().trim());
    	  obj.getPendingPayments();
    	  
    	 List<Orders> lstorder=new ArrayList<Orders>();
    	 lstorder=obj.getLstorders();
    	 mv.setViewName("viewPendingPayments.jsp");
    	 mv.addObject("lst",lstorder);
    	}
    	catch (Exception e) {
    		System.out.println("err in place order="+e.getMessage());
			// TODO: handle exception
		}
    	return mv;
	}
	@RequestMapping("/viewPendingIncome")
	public ModelAndView viewPendingIncome(HttpServletRequest request,HttpSession ses)
	{
    	ModelAndView mv=new ModelAndView();
    	
    	try {
    	  Income income=new  Income();
    	  income.getPendingIncomes();
    	  
    	 List<Income> lstorder=new ArrayList<Income>();
    	 lstorder=income.getLstincome();
    	 mv.setViewName("viewPendingIncome.jsp");
    	 mv.addObject("lst",lstorder);
    	}
    	catch (Exception e) {
    		System.out.println("err in place order="+e.getMessage());
			// TODO: handle exception
		}
    	return mv;
	}
	@RequestMapping("/PendingOrders")
	public ModelAndView PendingOrders(HttpServletRequest request,HttpSession ses)
	{
    	ModelAndView mv=new ModelAndView();
    	
    	try {
    	  MyOrders order=new  MyOrders();
    	  order.getPendingOrders(ses.getAttribute("userid").toString().trim());
    	 List<MyOrders> lstorder=new ArrayList<MyOrders>();
    	 lstorder=order.getLstorders();
    	 mv.setViewName("PendingOrders.jsp");
    	 mv.addObject("lst",lstorder);
    	}
    	catch (Exception e) {
    		System.out.println("err in place order="+e.getMessage());
			// TODO: handle exception
		}
    	return mv;
	}
	@RequestMapping("/ProcessedOrders")
	public ModelAndView ProcessedOrders(HttpServletRequest request,HttpSession ses)
	{
    	ModelAndView mv=new ModelAndView();
    	
    	try {
    	  MyOrders order=new  MyOrders();
    	  order.getProcessedOrders(ses.getAttribute("userid").toString().trim());
    	 List<MyOrders> lstorder=new ArrayList<MyOrders>();
    	 lstorder=order.getLstorders();
    	 mv.setViewName("ProcessedOrders.jsp");
    	 mv.addObject("lst",lstorder);
    	}
    	catch (Exception e) {
    		System.out.println("err in place order="+e.getMessage());
			// TODO: handle exception
		}
    	return mv;
	}
	@RequestMapping("/PaidOrders")
	public ModelAndView PaidOrders(HttpServletRequest request,HttpSession ses)
	{
    	ModelAndView mv=new ModelAndView();
    	
    	try {
    	  MyOrders order=new  MyOrders();
    	  order.getPaidOrders(ses.getAttribute("userid").toString().trim());
    	 List<MyOrders> lstorder=new ArrayList<MyOrders>();
    	 lstorder=order.getLstorders();
    	 mv.setViewName("PaidOrders.jsp");
    	 mv.addObject("lst",lstorder);
    	}
    	catch (Exception e) {
    		System.out.println("err in place order="+e.getMessage());
			// TODO: handle exception
		}
    	return mv;
	}
	@RequestMapping("/UploadIncome")
	public ModelAndView UploadIncome(HttpServletRequest request)
	{
		ModelAndView mv=new ModelAndView();
		String year="";
		Date dt=new Date();
		if((dt.getMonth()+1)>3)
		{
			year=(dt.getYear()+1900)+"-"+(dt.getYear()+1900+1);
		}
		else
			year=(dt.getYear()+1900-1)+"-"+(dt.getYear()+1900);
		 
		mv.setViewName("UploadIncome.jsp");
		mv.addObject("year",year);
		return mv;
	}
	@RequestMapping("/UploadProduct")
	public ModelAndView UploadProduct(HttpServletRequest request)
	{
		ModelAndView mv=new ModelAndView();
		String transid=request.getParameter("transid").trim();
		mv.setViewName("UploadProduct.jsp");
		mv.addObject("transid",transid.trim());
		return mv;
	}
	@RequestMapping("/viewFarmers")
	public ModelAndView viewFarmers(HttpSession ses)
	{
		
		List<UserReg> lst = new ArrayList<UserReg>();
		UserReg obj=new UserReg();
		obj.setUtype("farmer");
		 obj.getUsers();
		 lst=obj.getLstuser();

		ModelAndView mv = new ModelAndView();

		mv.setViewName("viewFarmers.jsp");
		mv.addObject("lst", lst); 
		return mv;
		 
	}
	@RequestMapping("/viewFarmers1")
	public ModelAndView viewFarmers1(HttpSession ses)
	{
		
		List<UserReg> lst = new ArrayList<UserReg>();
		UserReg obj=new UserReg();
		obj.setUtype("farmer");
		 obj.getUsers();
		 lst=obj.getLstuser();

		ModelAndView mv = new ModelAndView();

		mv.setViewName("viewFarmers1.jsp");
		mv.addObject("lst", lst); 
		return mv;
		 
	}
	@RequestMapping("viewDistributors")
	public ModelAndView viewDistributors(HttpSession ses)
	{
		
		List<UserReg> lst = new ArrayList<UserReg>();
		UserReg obj=new UserReg();
		obj.setUtype("distributor");
		 obj.getUsers();
		 lst=obj.getLstuser();

		ModelAndView mv = new ModelAndView();

		mv.setViewName("viewDistributors.jsp");
		mv.addObject("lst", lst); 
		return mv;
		 
	}
	@RequestMapping("viewDistributors2")
	public ModelAndView viewDistributors2(HttpSession ses,HttpServletRequest request)
	{
		
		List<UserReg> lst = new ArrayList<UserReg>();
		UserReg obj=new UserReg();
		obj.setUtype("distributor");
		 obj.getUsers1(request.getParameter("userid").trim());
		 lst=obj.getLstuser();

		ModelAndView mv = new ModelAndView();

		mv.setViewName("viewDistributors1.jsp");
		mv.addObject("lst", lst); 
		return mv;
		 
	}
	@RequestMapping("viewDistributors1")
	public ModelAndView viewDistributors1(HttpSession ses)
	{
		
		List<UserReg> lst = new ArrayList<UserReg>();
		UserReg obj=new UserReg();
		obj.setUtype("distributor");
		 obj.getUsers();
		 lst=obj.getLstuser();

		ModelAndView mv = new ModelAndView();

		mv.setViewName("viewDistributors1.jsp");
		mv.addObject("lst", lst); 
		return mv;
		 
	}
	@RequestMapping("viewRetailer")
	public ModelAndView viewRetailer(HttpSession ses)
	{
		
		List<UserReg> lst = new ArrayList<UserReg>();
		UserReg obj=new UserReg();
		obj.setUtype("retailer");
		 obj.getUsers();
		 lst=obj.getLstuser();

		ModelAndView mv = new ModelAndView();

		mv.setViewName("viewRetailer.jsp");
		mv.addObject("lst", lst); 
		return mv;
		 
	}
	@RequestMapping("viewRetailer1")
	public ModelAndView viewRetailer1(HttpSession ses)
	{
		
		List<UserReg> lst = new ArrayList<UserReg>();
		UserReg obj=new UserReg();
		obj.setUtype("retailer");
		 obj.getUsers();
		 lst=obj.getLstuser();

		ModelAndView mv = new ModelAndView();

		mv.setViewName("viewRetailer1.jsp");
		mv.addObject("lst", lst); 
		return mv;
		 
	}
	@RequestMapping("viewProcessors")
	public ModelAndView viewProcessor(HttpSession ses)
	{
		
		List<UserReg> lst = new ArrayList<UserReg>();
		UserReg obj=new UserReg();
		obj.setUtype("processor");
		 obj.getUsers();
		 lst=obj.getLstuser();

		ModelAndView mv = new ModelAndView();

		mv.setViewName("viewProcessors.jsp");
		mv.addObject("lst", lst); 
		return mv;
		 
	}
	@RequestMapping("viewProcessors1")
	public ModelAndView viewProcessor1(HttpSession ses)
	{
		
		List<UserReg> lst = new ArrayList<UserReg>();
		UserReg obj=new UserReg();
		obj.setUtype("processor");
		 obj.getUsers();
		 lst=obj.getLstuser();

		ModelAndView mv = new ModelAndView();

		mv.setViewName("viewProcessors1.jsp");
		mv.addObject("lst", lst); 
		return mv;
		 
	}
	@RequestMapping("/viewCustomers")
	public ModelAndView viewCustomer(HttpSession ses)
	{
		
		List<UserReg> lst = new ArrayList<UserReg>();
		UserReg obj=new UserReg();
		obj.setUtype("customer");
		 obj.getUsers();
		 lst=obj.getLstuser();

		ModelAndView mv = new ModelAndView();

		mv.setViewName("viewCust.jsp");
		mv.addObject("lst", lst); 
		return mv;
		 
	}
	@RequestMapping("/ChangeQuantity")
	public ModelAndView ChangeQuantity(HttpSession ses,Products obj)
	{
		
		List<Products> lst = new ArrayList<Products>();
		obj.updateQuan(); 
		 
		ModelAndView mv = new ModelAndView();

		mv.setViewName("Success.jsp?type=ProdUpdate");
		 
		return mv;
		 
	}
	@RequestMapping("/ChangePrice")
	public ModelAndView ChangePrice(HttpSession ses,Products obj)
	{
		
		List<Products> lst = new ArrayList<Products>();
		obj.updatePrice(); 
		 
		ModelAndView mv = new ModelAndView();

		mv.setViewName("Success.jsp?type=ProdUpdate");
		 
		return mv;
		 
	}
	@RequestMapping("/RegRecomm")
	public ModelAndView RegRecomm(HttpSession ses,HttpServletRequest request)
	{
		
		List<Products> lst = new ArrayList<Products>();
		Products obj=new Products();
		//obj.setUserid(ses.getAttribute("userid").toString().trim());
		 obj.getProducts3(request.getParameter("farmType").trim(),request.getParameter("soilType").trim());
		 lst=obj.getLstprod();

		ModelAndView mv = new ModelAndView();

		mv.setViewName("viewProd2.jsp");
		mv.addObject("lst", lst); 
		return mv;
		 
	}
	@RequestMapping("/viewProducts2")
	public ModelAndView viewFarmerProducts2(HttpSession ses)
	{
		
		List<Products> lst = new ArrayList<Products>();
		Products obj=new Products();
		//obj.setUserid(ses.getAttribute("userid").toString().trim());
		 obj.getProducts2();
		 lst=obj.getLstprod();

		ModelAndView mv = new ModelAndView();

		mv.setViewName("viewProd1.jsp");
		mv.addObject("lst", lst); 
		return mv;
		 
	}
	
	@RequestMapping("/viewProducts")
	public ModelAndView viewFarmerProducts(HttpSession ses)
	{
		
		List<Products> lst = new ArrayList<Products>();
		Products obj=new Products();
		obj.setUserid(ses.getAttribute("userid").toString().trim());
		 obj.getProducts();
		 lst=obj.getLstprod();

		ModelAndView mv = new ModelAndView();

		mv.setViewName("viewProd.jsp");
		mv.addObject("lst", lst); 
		return mv;
		 
	}
	@RequestMapping("/ShowCart")
	public ModelAndView ShowCart(Cart cart,HttpSession ses)
	{
		ModelAndView mv=new ModelAndView(); 
		cart.setUserid(ses.getAttribute("userid").toString().trim());
		 cart.getCartDetails();
			List<Cart> lstcart=cart.getLstcart();
			mv.addObject("lst",lstcart);
			mv.setViewName("Cart.jsp");
		 
	//mv.addObject("branch",request.getParameter("branch").toString().trim());
			return mv;
		 
	}
	@RequestMapping("/AddToCart")
	public ModelAndView addToCart(Cart cart,HttpSession ses,HttpServletRequest request)
	{
		ModelAndView mv=new ModelAndView(); 
		cart.setUserid(ses.getAttribute("userid").toString().trim());
		
		if(!cart.checkProductInCartORNOT(cart.getProdId(), ses.getAttribute("userid").toString().trim()))
		{
			if(cart.addToCart(ses))
			{
				// JavaFuns jf=new JavaFuns();
		    	 // if(jf.execute("update products set searchCount=searchCount+1 where id="+cart.getProdId()))
		    	//  {
		    		  
		    	//  }
				
			}	
			List<Products> lst = new ArrayList<Products>();
			Products obj=new Products();
			obj.setUserid(request.getParameter("userid1").toString().trim());
			 obj.getProducts();
			 lst=obj.getLstprod();

		 
			mv.addObject("lst", lst);
				 System.out.println("list size="+lst.size());
				mv.addObject("stf",lst);
				mv.addObject("subcategory","NA");
				mv.addObject("category","NA");
				mv.addObject("availquan",request.getParameter("availquan").toString().trim());
				mv.setViewName(cart.getPage());
		}
		else
		{
			
			cart.getCartDetails();
			List<Cart> lstcart=cart.getLstcart();
			mv.addObject("lst",lstcart);
			mv.setViewName("Cart.jsp");
		}
	//mv.addObject("branch",request.getParameter("branch").toString().trim());
			return mv;
		 
	}
	@RequestMapping("/PlaceOrder")
	public String PlaceOrder(HttpServletRequest request,HttpSession ses)
	{
    	try {
    		JavaFuns jf=new JavaFuns();
    	 Orders order=new Orders();
    	 Vector v1=jf.getValue("select prodId,quantity from cart where userid='"+ses.getAttribute("userid").toString().trim()+"'",2);
    	 for(int i=0;i<v1.size();i=i+2)
    	 {
    		 if(jf.execute("update products set availQuantity=availQuantity-"+v1.elementAt(i+1).toString().trim()+" where prodId="+v1.elementAt(i).toString().trim()))
     	    {}
    	 }
    	 Vector v=jf.getValue("select distinct(sellerUserid),sellerUserName,sellerType  from cart where userid='"+ses.getAttribute("userid").toString().trim()+"'",3);
    	 for(int i=0;i<v.size();i=i+3)
    	 {
    		 order.setUserid(ses.getAttribute("userid").toString().trim());
    		 order.setSellerUserid(v.elementAt(i).toString().trim());
    		 order.setSellerUsernm(v.elementAt(i+1).toString().trim());
    		 order.setSellerUtype(v.elementAt(i+2).toString().trim());
    	   	 if(order.placeOrder()) {}
    	    	 
    	    	}
    	 }
    	 
    	catch (Exception e) {
    		System.out.println("err in place order="+e.getMessage());
			// TODO: handle exception
		}
    	return "Success.jsp?type=placeOrder";
	}
	
	 
	@RequestMapping("/ViewProducts1")
	public ModelAndView viewFarmerProducts1(HttpSession ses,HttpServletRequest request)
	{
		
		List<Products> lst = new ArrayList<Products>();
		Products obj=new Products();
		obj.setUserid(request.getParameter("userid").toString().trim());
		 obj.getProducts();
		 lst=obj.getLstprod();

		ModelAndView mv = new ModelAndView();

		mv.setViewName("viewProd1.jsp");
		mv.addObject("lst", lst); 
		return mv;
		 
	}
	@RequestMapping("/RemoveItem")
	public ModelAndView RemoveItem(Cart cart,HttpSession ses)
	{
		ModelAndView mv=new ModelAndView(); 
		if(cart.removeItems())
		{
			
		}
		cart.setUserid(ses.getAttribute("userid").toString().trim());
		 cart.getCartDetails();
			List<Cart> lstcart=cart.getLstcart();
			mv.addObject("lst",lstcart);
			mv.setViewName("Cart.jsp");
		 
	//mv.addObject("branch",request.getParameter("branch").toString().trim());
			return mv;
		 
	}
	@RequestMapping("/updateCart")
	public ModelAndView updateCart(HttpServletRequest request, HttpSession ses)
	{
		Cart cart=new Cart();
		int cartid=Integer.parseInt(request.getParameter("cartid").toString());
		int quant=Integer.parseInt(request.getParameter("quan").toString());
		cart.setCartid(cartid);
		cart.setQuantity(quant);
		ModelAndView mv=new ModelAndView(); 
		if(cart.updateItems())
		{
			
		}
		cart.setUserid(ses.getAttribute("userid").toString().trim());
		 cart.getCartDetails();
			List<Cart> lstcart=cart.getLstcart();
			mv.addObject("lst",lstcart);
			mv.setViewName("cartAjax.jsp");
		 System.out.println("in cart ajax");
	//mv.addObject("branch",request.getParameter("branch").toString().trim());
			return mv;
		 
	}
	
	@RequestMapping("/paybill")
	public String paybill(Orders obj,HttpServletRequest request,HttpSession ses)
	{
		boolean flag=true;
		double p=0;
		 try
		 {
			 JavaFuns jf=new JavaFuns();
			 
			 
			 MultipartFile file=obj.getFile();
		  
		 String filepath=request.getServletContext().getRealPath("/")+"/Receipts/";
		  
		 System.out.println("path="+filepath);
		 File f=new File(filepath);
		 f.mkdir();
		 filepath+="/"+ses.getAttribute("userid").toString().trim();
		 f=new File(filepath);
		 f.mkdir();
		  
			  
			 int mx=jf.FetchMax("payid", "payments");
			 String fileName=mx+"."+ file.getOriginalFilename().split("\\.")[1];
			 file.transferTo(new File(filepath+"/"+fileName));
			 obj.setPaymentId(mx);
			 obj.setReceipt(fileName);
			 obj.setUserid(ses.getAttribute("userid").toString().trim());
			 
		  
			 System.out.println(ses.getAttribute("username").toString().trim());
			 if(obj.insertPayment() )
			 {
				 Mail mail=new Mail();
				 String msg="Dear Distributor, Farmer sent you payment request, please check and approve it from your account";
				 
				 Vector vector=jf.getValue("select email from users where userid='"+obj.getSellerUserid()+"'", 1);
				 try
				 {
					 if(mail.sendMail(msg, vector.elementAt(0).toString().trim(),"Payment Request")) {}
				 }
				 catch (Exception e) {
					// TODO: handle exception
				}
				 return "Success.jsp?type=RegPayment";
			 }
			 else
			 { 
				 return "Failure.jsp?type=RegPayment";
			 }
			 
		 }
		 catch (Exception e) {
			// TODO: handle exception
			 System.out.println("err="+e.getMessage());
			 return("Failure.jsp?type=RegPayment");
		}
	}
	@RequestMapping("/RegIncome")
	public String RegIncome(Income obj,HttpServletRequest request,HttpSession ses)
	{
		boolean flag=true;
		double p=0;
		 try
		 {
			 JavaFuns jf=new JavaFuns();
			 
			 
			 MultipartFile file=obj.getFile();
		  
		 String filepath=request.getServletContext().getRealPath("/")+"/Income/";
		  
		 System.out.println("path="+filepath);
		 File f=new File(filepath);
		 f.mkdir();
		 filepath+="/"+ses.getAttribute("userid").toString().trim();
		 f=new File(filepath);
		 f.mkdir();
		  
			  
			 int mx=jf.FetchMax("id", "farmer_income");
			 String fileName=mx+"."+ file.getOriginalFilename().split("\\.")[1];
			 file.transferTo(new File(filepath+"/"+fileName));
			 obj.setId(mx);
			 obj.setProof(fileName);
			 obj.setUserid(ses.getAttribute("userid").toString().trim());
			
		  
			 System.out.println(ses.getAttribute("username").toString().trim());
			 if(obj.registration() )
			 {
				 
				 return "Success.jsp?type=RegIncome";
			 }
			 else
			 { 
				 return "Failure.jsp?type=RegIncome";
			 }
			 
		 }
		 catch (Exception e) {
			// TODO: handle exception
			 System.out.println("err="+e.getMessage());
			 return("Failure.jsp?type=RegProd");
		}
	}
	@RequestMapping("RegProduct")
	public String RegProduct(Products obj,HttpServletRequest request,HttpSession ses)
	{
		boolean flag=true;
		double p=0;
		 try
		 {
			 JavaFuns jf=new JavaFuns();
			 
			 
			 MultipartFile file=obj.getFile();
		  
		 String filepath=request.getServletContext().getRealPath("/")+"/Products/";
		  
		 System.out.println("path="+filepath);
		 File f=new File(filepath);
		 f.mkdir();
		 filepath+="/"+ses.getAttribute("userid").toString().trim();
		 f=new File(filepath);
		 f.mkdir();
		  
			 obj.getId();
			 int mx=obj.getProdId();
			 String fileName=mx+"."+ file.getOriginalFilename().split("\\.")[1];
			 file.transferTo(new File(filepath+"/"+fileName));
			 obj.setProdId(mx);
			 obj.setCoverphoto(fileName);
			 obj.setUserid(ses.getAttribute("userid").toString().trim());
			Date dt=new Date();
			obj.setDt(dt.getDate()+"/"+(dt.getMonth()+1)+"/"+(dt.getYear()+1900));
			obj.setTm(dt.getHours()+":"+(dt.getMinutes()));
			 
			obj.setUserType(ses.getAttribute("utype").toString().trim());
			obj.setProdType(ses.getAttribute("utype").toString().trim()+"Product");				 
			obj.setUsernm(ses.getAttribute("username").toString().trim());
			System.out.println(ses.getAttribute("username").toString().trim());
			 if(obj.registration() )
			 {
				 
				 return "Success.jsp?type=RegProd";
			 }
			 else
			 { 
				 return "Failure.jsp?type=RegProd";
			 }
			 
		 }
		 catch (Exception e) {
			// TODO: handle exception
			 System.out.println("err="+e.getMessage());
			 return("Failure.jsp?type=RegProd");
		}
	}
	@RequestMapping("RegUser")
	public String RegUser(UserReg obj)
	{
		 try
		 {
			 if(obj.registration() )
			 {
				 
				 return "Success.jsp?type=RegUser";
			 }
			 else
			 { 
				 return "Failure.jsp?type=RegUser";
			 }
		 }
		 catch (Exception e) {
			// TODO: handle exception
			 System.out.println("err="+e.getMessage());
			 return("Failure.jsp?type=RegUser");
		}
	}
	@RequestMapping("/login")
	public String login(HttpServletRequest request)
	{
		 Login obj=new Login();
		 try
		 {
			 javax.servlet.http.HttpSession ses=request.getSession(true);
			 
			 if(obj.chkAuthentication(request.getParameter("txtuserid").trim(), request.getParameter("txtpass").trim()))
			 {
				 ses.setAttribute("userid", obj.getUserid());
				 System.out.println("userid="+obj.getUserid());
				 System.out.println("userid="+obj.getuType());
				 System.out.println("userid="+obj.getUserName());
				 ses.setAttribute("utype", obj.getuType());
				 ses.setAttribute("email", obj.getEmail());
				 ses.setAttribute("username", obj.getUserName());
				 if(obj.getuType().equals("farmer"))
				 {
					 JavaFuns jf=new  JavaFuns();
					 Vector v=jf.getValue("select mobile from userdetails where userid='"+obj.getUserid()+"'", 1);
					 ses.setAttribute("mobile", v.elementAt(0).toString().trim());
				 }
				 return obj.getuType()+".jsp";
			 }
			 else
			 { 
				 return "Failure.jsp?type=Auth";
			 }
		 }
		 catch (Exception e) {
			// TODO: handle exception
			 System.out.println("err="+e.getMessage());
			 return("Failure.jsp?type=Auth");
		}
		 
	}
	@RequestMapping("/logout")
	public String logout(HttpSession session) {
        session.invalidate();
		return "Logout.jsp";
	}
}
