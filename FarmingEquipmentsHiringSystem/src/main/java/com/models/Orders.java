package com.models;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Vector;

import org.springframework.web.multipart.MultipartFile;

import com.services.DBConnector;

 

public class Orders {
	Connection con;
    CallableStatement csmt;
    ResultSet rs;
    private String title,receipt,username,mobile,ordersts,sellerUserid,sellerUsernm,sellerUtype,category,subcategory,page,dt,userid;
    private int orderno,paymentId;
    private double netbill;
    private MultipartFile file;
    public int getPaymentId() {
		return paymentId;
	}
	public void setPaymentId(int paymentId) {
		this.paymentId = paymentId;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public double getNetbill() {
		return netbill;
	}
	public void setNetbill(double netbill) {
		this.netbill = netbill;
	}
	public String getDt() {
		return dt;
	}
	public void setDt(String dt) {
		this.dt = dt;
	}
	private int searchCount,prodId,quantity,cartid;
    private double price,totalprice,totalShopPrice;
   private List<Orders> lstorders = new ArrayList<Orders>();
    
	  
public String getReceipt() {
	return receipt;
}
public void setReceipt(String receipt) {
	this.receipt = receipt;
}
public MultipartFile getFile() {
	return file;
}
public void setFile(MultipartFile file) {
	this.file = file;
}
public String getOrdersts() {
	return ordersts;
}
public void setOrdersts(String ordersts) {
	this.ordersts = ordersts;
}
public String getSellerUserid() {
	return sellerUserid;
}
public void setSellerUserid(String sellerUserid) {
	this.sellerUserid = sellerUserid;
}
public String getSellerUsernm() {
	return sellerUsernm;
}
public void setSellerUsernm(String sellerUsernm) {
	this.sellerUsernm = sellerUsernm;
}
public String getSellerUtype() {
	return sellerUtype;
}
public void setSellerUtype(String sellerUtype) {
	this.sellerUtype = sellerUtype;
}
public int getOrderno() {
	return orderno;
}
public void setOrderno(int orderno) {
	this.orderno = orderno;
}
public String getUserid() {
	return userid;
}
public void setUserid(String userid) {
	this.userid = userid;
}
public int getQuantity() {
	return quantity;
}
public void setQuantity(int quantity) {
	this.quantity = quantity;
}
public int getCartid() {
	return cartid;
}
public void setCartid(int cartid) {
	this.cartid = cartid;
}
public double getTotalprice() {
	return totalprice;
}
public void setTotalprice(double totalprice) {
	this.totalprice = totalprice;
}
public double getTotalShopPrice() {
	return totalShopPrice;
}
public void setTotalShopPrice(double totalShopPrice) {
	this.totalShopPrice = totalShopPrice;
}
public String getTitle() {
	return title;
}
public void setTitle(String title) {
	this.title = title;
}
public String getCategory() {
	return category;
}
public void setCategory(String category) {
	this.category = category;
}
public String getSubcategory() {
	return subcategory;
}
public void setSubcategory(String subcategory) {
	this.subcategory = subcategory;
}
public String getPage() {
	return page;
}
public void setPage(String page) {
	this.page = page;
}
public int getSearchCount() {
	return searchCount;
}
public void setSearchCount(int searchCount) {
	this.searchCount = searchCount;
}
public int getProdId() {
	return prodId;
}
public void setProdId(int prodId) {
	this.prodId = prodId;
}
public double getPrice() {
	return price;
}
public void setPrice(double price) {
	this.price = price;
} 
 
public List<Orders> getLstorders() {
	return lstorders;
}
public void setLstorders(List<Orders> lstorders) {
	this.lstorders = lstorders;
}
public Orders()
{
	
}
public Orders(ResultSet rs) 
{
	try
	{
	title=rs.getString("prodTitle").toString().trim();
	userid=rs.getString("userid").toString().trim();
	category=rs.getString("category").toString().trim();
	subcategory=rs.getString("subcategory").toString().trim();
	dt=rs.getString("dt").toString().trim();
	sellerUserid=rs.getString("sellerUserid").toString().trim();
	sellerUsernm=rs.getString("sellerUserName").toString().trim();
	sellerUtype=rs.getString("sellerType").toString().trim();
	price=rs.getDouble("price");
	totalprice=rs.getDouble("totalprice");
	ordersts=rs.getString("orderstatus");
	prodId=rs.getInt("prodid");
	quantity=rs.getInt("quantity");
	cartid=rs.getInt("cartId");
	System.out.println("title="+title);
	}
	catch (Exception e) {
		// TODO: handle exception
		System.out.println("err="+e.getMessage());
	}
}
public Orders(ResultSet rs,String details) 
{
	try
	{
	title=rs.getString("productname").toString().trim();
	quantity=rs.getInt("quantity");
	orderno=rs.getInt("orderno");
	price=rs.getDouble("price");
	prodId=rs.getInt("productid");
	ordersts=details;
JavaFuns jf=new JavaFuns();
Vector v=jf.getValue("select unit from products where prodId="+rs.getInt("productid"), 1);
 
	if(v.elementAt(0).toString().trim().equals("hr"))
	{
		totalprice=rs.getInt("hrs")*price;
	}
	else if(v.elementAt(0).toString().trim().equals("month"))
	{
		totalprice=rs.getInt("days")*(price/30);
	}
	else if(v.elementAt(0).toString().trim().equals("day"))
	{
		totalprice=rs.getInt("days")*(price);
	}
	else if(v.elementAt(0).toString().trim().equals("year"))
	{
		totalprice=rs.getInt("days")*(price/365);
	}
	if(details.equals("pending"))
	{
		totalprice=0;
	}
	else
	totalprice=Math.round(totalprice);
	netbill=netbill+totalprice;
	//totalprice=rs.getDouble("total");
	System.out.println("title="+title);
	}
	catch (Exception e) {
		// TODO: handle exception
		System.out.println("err="+e.getMessage());
	}
}
public void getOrders(String sts)
{
    try
    {
         DBConnector obj=new  DBConnector();
        con=obj.connect();
        csmt=con.prepareCall("{call getPendingOrders(?,?)}");
       csmt.setString(1,userid);
       csmt.setString(2,sts);
         csmt.execute();
         rs=csmt.getResultSet();
        lstorders=new ArrayList<Orders>();            
        while(rs.next())
        { System.out.println("true");
        lstorders.add(new Orders(rs));
              
        }
    } 
    catch(Exception ex)
    {
        System.out.println("err="+ex.getMessage());
         
    }
}
public void getPendingPayments()
{
    try
    {
         DBConnector obj=new  DBConnector();
        con=obj.connect();
        csmt=con.prepareCall("{call getPendingPayments(?)}");
       csmt.setString(1,userid);
        
         csmt.execute();
         rs=csmt.getResultSet();
        lstorders=new ArrayList<Orders>();            
        while(rs.next())
        { System.out.println("true");
        Orders obj1=new Orders();
        obj1.setPaymentId(rs.getInt("payid"));
        obj1.setUserid(rs.getString("userid"));
        obj1.setUsername(rs.getString("username"));
        obj1.setMobile(rs.getString("mobile"));
        obj1.setOrderno(rs.getInt("orderno"));
        obj1.setNetbill(rs.getDouble("netbill"));
        obj1.setDt(rs.getString("paymentdate"));
        obj1.setDt(rs.getString("paymentdate"));
        obj1.setReceipt(rs.getString("receipt"));
        obj1.setSellerUserid(rs.getString("sellerUserid"));
        lstorders.add(obj1);
              
        }
    } 
    catch(Exception ex)
    {
        System.out.println("err="+ex.getMessage());
         
    }
}
public boolean insertPayment()
{
    try
    { 
         DBConnector obj=new  DBConnector();
        con=obj.connect();
        Date dt1=new Date();
        String mon=String.valueOf((dt1.getMonth()+1));
        String dt11=String.valueOf((dt1.getDate()));
        if(mon.trim().length()==1)
        	mon="0"+mon;
        if(dt11.length()==1)
        	dt11="0"+dt11;
       // dt=dt1.getDate()+"/"+(dt1.getMonth()+1)+"/"+(dt1.getYear()+1900);
        dt=(dt1.getYear()+1900)+"-"+mon+"-"+dt11;
        PreparedStatement csmt=con.prepareStatement("insert into payments values(?,?,?,?,?,?,?,?,?,?)");
        csmt.setInt(1, paymentId);
        csmt.setString(2, userid);
        csmt.setString(3, username);
        csmt.setString(4, mobile);
        csmt.setInt(5, orderno);
        csmt.setDouble(6, netbill);
        csmt.setString(7, dt);
        csmt.setString(8, sellerUserid);
        csmt.setString(9, "pending");
        csmt.setString(10, receipt);
        int n=csmt.executeUpdate();        
     
       try{con.close();}catch(Exception ex){}            
        
        if(n>0)
        {
        	  
            return true;
        }
        else
            return false;

        }
       
     
    catch(Exception ex)
    {
        System.out.println("err="+ex.getMessage());
        return false;
    }
}
	public boolean placeOrder()
    {
        try
        { 
             DBConnector obj=new  DBConnector();
            con=obj.connect();
            Date dt1=new Date();
            String mon=String.valueOf((dt1.getMonth()+1));
            String dt11=String.valueOf((dt1.getDate()));
            if(mon.length()==1)
            	mon="0"+mon;
            if(dt11.length()==1)
            	dt11="0"+dt11;
           // dt=dt1.getDate()+"/"+(dt1.getMonth()+1)+"/"+(dt1.getYear()+1900);
            dt=(dt1.getYear()+1900)+"-"+mon+"-"+dt11;
            csmt=con.prepareCall("{call insertOrders(?,?,?,?,?)}");
            csmt.setString(1, userid);
            csmt.setString(2, dt);
            csmt.setString(3, sellerUserid);
            csmt.setString(4, sellerUsernm);
            csmt.setString(5, sellerUtype);
            csmt.execute();
            rs=csmt.getResultSet();
            int n=0;        
           while(rs.next())
           { System.out.println("true");
           	orderno=rs.getInt("orderno");
           	n++;
           }
           try{con.close();}catch(Exception ex){}            
            
            if(n>0)
            {
            	  
                return true;
            }
            else
                return false;

            }
           
         
        catch(Exception ex)
        {
            System.out.println("err="+ex.getMessage());
            return false;
        }
    }
	public boolean cancelOrder()
    {
        try
        { 
             DBConnector obj=new  DBConnector();
            con=obj.connect();
            Date dt1=new Date();
            dt=dt1.getDate()+"/"+(dt1.getMonth()+1)+"/"+(dt1.getYear()+1900);
            csmt=con.prepareCall("{call removeItemFromCart(?)}");
            csmt.setInt(1, cartid); 
             int n=csmt.executeUpdate(); 
             try{con.close();}catch(Exception ex){}
            if(n>0)
            { 
                return true;
            }
            else
                return false;

            }
           
         
        catch(Exception ex)
        {
            System.out.println("err="+ex.getMessage());
            return false;
        }
    }
	public void getOrderDetails(int orderno1)
	{
	    try
	    {
	         DBConnector obj=new  DBConnector();
	        con=obj.connect();
	        csmt=con.prepareCall("{call getOrderDetails(?)}");
	        csmt.setInt(1, orderno1);
	         csmt.execute();
	         rs=csmt.getResultSet();
	        lstorders=new ArrayList<Orders>();
	       System.out.println("orders="+ordersts);
	        while(rs.next())
	        { System.out.println("true");
	        lstorders.add(new Orders(rs,ordersts));
	              
	        }
	        try{con.close();}catch(Exception ex){}
	    } 
	    catch(Exception ex)
	    {
	        System.out.println("err="+ex.getMessage());
	         
	    }
	}  
}
