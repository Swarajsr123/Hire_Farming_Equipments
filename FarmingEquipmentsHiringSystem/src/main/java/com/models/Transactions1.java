package com.models;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Vector;

import com.services.DBConnector;
 

public class Transactions1 {
private String userid,username,utype,sellertype,unit,transids,sellerUserid,sellerName,prodName,dt,tm,trhash,prevhash;
private int prodId,trid;
private double price;
private List<Transactions1> lsttrans;
Connection con;
CallableStatement csmt;
PreparedStatement smt;
ResultSet rs;
public String getUserid() {
	return userid;
}
public void setUserid(String userid) {
	this.userid = userid;
}
public String getTransids() {
	return transids;
}

public void setTransids(String transids) {
	this.transids = transids;
}
public String getSellerUserid() {
	return sellerUserid;
}
public void setSellerUserid(String sellerUserid) {
	this.sellerUserid = sellerUserid;
}
public String getSellerName() {
	return sellerName;
}
public void setSellerName(String sellerName) {
	this.sellerName = sellerName;
}
public String getProdName() {
	return prodName;
}
public void setProdName(String prodName) {
	this.prodName = prodName;
}
public String getDt() {
	return dt;
}
public void setDt(String dt) {
	this.dt = dt;
}
public String getTm() {
	return tm;
}
public void setTm(String tm) {
	this.tm = tm;
}
public String getTrhash() {
	return trhash;
}
public void setTrhash(String trhash) {
	this.trhash = trhash;
}
public String getPrevhash() {
	return prevhash;
}
public void setPrevhash(String prevhash) {
	this.prevhash = prevhash;
}
public int getProdId() {
	return prodId;
}
public void setProdId(int prodId) {
	this.prodId = prodId;
}
public int getTrid() {
	return trid;
}
public void setTrid(int trid) {
	this.trid = trid;
}
public double getPrice() {
	return price;
}

public String getUsername() {
	return username;
}
public void setUsername(String username) {
	this.username = username;
}
public String getUtype() {
	return utype;
}
public void setUtype(String utype) {
	this.utype = utype;
}
public String getSellertype() {
	return sellertype;
}
public void setSellertype(String sellertype) {
	this.sellertype = sellertype;
}
public String getUnit() {
	return unit;
}
public void setUnit(String unit) {
	this.unit = unit;
}
public void setPrice(double price) {
	this.price = price;
}
public List<Transactions1> getLsttrans() {
	return lsttrans;
}
public void setLsttrans(List<Transactions1> lsttrans) {
	this.lsttrans = lsttrans;
} 
public List<Transactions1> getTransactions(List<Transactions> lst1)
{
	boolean flag=false;
	JavaFuns jf=new JavaFuns();
	List <Transactions1> lsttrans=new ArrayList();
    try
    {  
           for(int i=0;i<lst1.size();i++)
           {
        	   Transactions tr=(Transactions)lst1.get(i);
        	Transactions1 msg1=new Transactions1();
        	msg1.setTrid(tr.getTrid());
        	msg1.setTrhash(tr.getTrhash());
        	msg1.setPrevhash(tr.getPrevhash());
        	msg1.setUserid(tr.getUserid());
        	msg1.setDt(tr.getDt());
        	msg1.setTm(tr.getTm());
        	msg1.setProdId(tr.getProdId());
        	msg1.setProdName(tr.getProdName());
        	msg1.setPrice(tr.getPrice());
        	msg1.setSellerUserid(tr.getSellerUserid());
        	msg1.setSellerName(tr.getSellerName());
        	Vector v=jf.getValue("select username,utype from users where userid='"+tr.getUserid().trim()+"'", 2);
        	msg1.setUtype(v.elementAt(1).toString().trim());
        	msg1.setUsername(v.elementAt(0).toString().trim());
        	Vector v1=jf.getValue("select utype from users where userid='"+tr.getSellerUserid().trim()+"'", 1);
        	
        	msg1.setSellertype(v1.elementAt(0).toString().trim());
        	Vector v2=jf.getValue("select unit from products where prodId="+tr.getProdId(), 1);
        	msg1.setUnit(v2.elementAt(0).toString().trim());
        	lsttrans.add(msg1);
           }
         System.out.println("size="+lsttrans.size());
    }
       
     
    catch(Exception ex)
    {
        System.out.println("err in api="+ex.getMessage());
         
    }
    return lsttrans;
}
}
