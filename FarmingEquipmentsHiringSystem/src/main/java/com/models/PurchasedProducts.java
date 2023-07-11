package com.models;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.services.DBConnector;
public class PurchasedProducts {
	Connection con;
    CallableStatement csmt;
    ResultSet rs;
    private List<Products> lstprod;
    private String prodName,userid,transid;
    private double quantity,price;
    private int prodId,pid;
      
public List<Products> getLstprod() {
		return lstprod;
	}



	public void setLstprod(List<Products> lstprod) {
		this.lstprod = lstprod;
	}



	public String getProdName() {
		return prodName;
	}



	public void setProdName(String prodName) {
		this.prodName = prodName;
	}



	public String getUserid() {
		return userid;
	}



	public void setUserid(String userid) {
		this.userid = userid;
	}



	public double getQuantity() {
		return quantity;
	}



	public void setQuantity(double quantity) {
		this.quantity = quantity;
	}



	public double getPrice() {
		return price;
	}



	public void setPrice(double price) {
		this.price = price;
	}
 
	public int getProdId() {
		return prodId;
	}



	public String getTransid() {
		return transid;
	}



	public void setTransid(String transid) {
		this.transid = transid;
	}



	public void setProdId(int prodId) {
		this.prodId = prodId;
	}



	public int getPid() {
		return pid;
	}



	public void setPid(int pid) {
		this.pid = pid;
	}



public PurchasedProducts() {}
      
       

	public PurchasedProducts(ResultSet rs)
	{
		try
		{
		prodName=rs.getString("prodName").toString().trim();
	    userid=rs.getString("userid").toString().trim();
		 //price=Double.parseDouble(rs.getString("price").toString().trim());
		 prodId=Integer.parseInt(rs.getString("prodId").toString().trim());
		transid= (rs.getString("transId").toString().trim());
		}
		catch (Exception e) {
			// TODO: handle exception
			System.out.println("err="+e.getMessage());
		}
	}
	 
	public boolean registration()
	    {
	        try
	        { 
	             DBConnector obj=new  DBConnector();
	            con=obj.connect();
	            csmt=con.prepareCall("{call insertPurchasedProd(?,?,?,?)}");
	            csmt.setString(1, userid);
	            csmt.setString(2, transid);
	            csmt.setInt(3, prodId);
	            csmt.setString(4, prodName);
	            
	             int n=csmt.executeUpdate(); 
	            if(n>0)
	            {
	                try{con.close();}catch(Exception ex){}
	                System.out.println("true");
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
}
	 
