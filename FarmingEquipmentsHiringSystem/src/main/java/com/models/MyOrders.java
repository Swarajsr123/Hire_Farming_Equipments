package com.models;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.services.DBConnector;

 

public class MyOrders {
	Connection con;
    CallableStatement csmt;
    ResultSet rs;
    private String prod,paymentmode,sellerUserid,sellerUsernm,sellerUtype,ordersts,paymentsts,dt,userid;
    private String username,addr,city,state,country,mobile,email;
    private int orderno;
    private double netbill;
    
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
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getAddr() {
		return addr;
	}
	public void setAddr(String addr) {
		this.addr = addr;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getDt() {
		return dt;
	}
	public void setDt(String dt) {
		this.dt = dt;
	}
	 
   private List<MyOrders> lstorders = new ArrayList<MyOrders>();
    
	  
public String getProd() {
	return prod;
}
public void setProd(String prod) {
	this.prod = prod;
}
public String getPaymentmode() {
	return paymentmode;
}
public void setPaymentmode(String paymentmode) {
	this.paymentmode = paymentmode;
}
public String getOrdersts() {
	return ordersts;
}
public void setOrdersts(String ordersts) {
	this.ordersts = ordersts;
}
public int getOrderno() {
	return orderno;
}
public void setOrderno(int orderno) {
	this.orderno = orderno;
}

public String getPaymentsts() {
	return paymentsts;
}
public void setPaymentsts(String paymentsts) {
	this.paymentsts = paymentsts;
}
public double getNetbill() {
	return netbill;
}
public void setNetbill(double netbill) {
	this.netbill = netbill;
}
public String getUserid() {
	return userid;
}
public void setUserid(String userid) {
	this.userid = userid;
}
 
public List<MyOrders> getLstorders() {
	return lstorders;
}
public void setLstorders(List<MyOrders> lstorders) {
	this.lstorders = lstorders;
}
public MyOrders()
{
	
}
public MyOrders(ResultSet rs) 
{
	try
	{
		userid=rs.getString("userid").toString().trim();
	prod=rs.getString("products").toString().trim();
	paymentmode=rs.getString("paymentmode").toString().trim();
	ordersts=rs.getString("orderstatus").toString().trim();
	dt=rs.getString("date").toString().trim();
	netbill=rs.getDouble("netbill");
	orderno=rs.getInt("orderno");
	paymentsts=rs.getString("paymentsts").toString().trim();
	sellerUserid=rs.getString("sellerUserid").toString().trim();
	sellerUsernm=rs.getString("sellerUserName").toString().trim();
	sellerUtype=rs.getString("sellerType").toString().trim();
	
	try
	{
		username=rs.getString("userName").toString().trim();
		mobile=rs.getString("mobile").toString().trim();
		email=rs.getString("email").toString().trim();
		addr=rs.getString("addr").toString().trim();
		//country=rs.getString("country").toString().trim();
		state=rs.getString("state").toString().trim();
		city=rs.getString("city").toString().trim();
	}
	catch (Exception e) {
		// TODO: handle exception
	}
	}
	catch (Exception e) {
		// TODO: handle exception
		System.out.println("err="+e.getMessage());
	}
}
public void getMyOrders(String sts)
{
    try
    {
         DBConnector obj=new  DBConnector();
        con=obj.connect();
        csmt=con.prepareCall("{call getMyOrders(?)}");
       csmt.setString(1,userid); 
         csmt.execute();
         rs=csmt.getResultSet();
        lstorders=new ArrayList<MyOrders>();            
        while(rs.next())
        { System.out.println("true");
        lstorders.add(new MyOrders(rs));
              
        }
    } 
    catch(Exception ex)
    {
        System.out.println("err="+ex.getMessage());
         
    }
}
public void getPendingOrders(String uid)
{
    try
    {
         DBConnector obj=new  DBConnector();
        con=obj.connect();
        csmt=con.prepareCall("{call getPendingOrders(?)}");
        csmt.setString(1, uid.trim());
         csmt.execute();
         rs=csmt.getResultSet();
        lstorders=new ArrayList<MyOrders>();            
        while(rs.next())
        { System.out.println("true");
        lstorders.add(new MyOrders(rs));
              
        }
    } 
    catch(Exception ex)
    {
        System.out.println("err="+ex.getMessage());
         
    }
} 
public void getProcessedOrders(String uid)
{
    try
    {
         DBConnector obj=new  DBConnector();
        con=obj.connect();
        csmt=con.prepareCall("{call getProcessedOrders(?)}");
        csmt.setString(1, uid.trim());
         csmt.execute();
         rs=csmt.getResultSet();
        lstorders=new ArrayList<MyOrders>();            
        while(rs.next())
        { System.out.println("true");
        lstorders.add(new MyOrders(rs));
              
        }
    } 
    catch(Exception ex)
    {
        System.out.println("err="+ex.getMessage());
         
    }
} 	 
public void getPaidOrders(String uid)
{
    try
    {
         DBConnector obj=new  DBConnector();
        con=obj.connect();
        csmt=con.prepareCall("{call getPaidOrders(?)}");
        csmt.setString(1, uid.trim());
         csmt.execute();
         rs=csmt.getResultSet();
        lstorders=new ArrayList<MyOrders>();            
        while(rs.next())
        { System.out.println("true");
        lstorders.add(new MyOrders(rs));
              
        }
    } 
    catch(Exception ex)
    {
        System.out.println("err="+ex.getMessage());
         
    }
} 	
}
