package com.models;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.services.DBConnector;
 

public class Transactions {
private String userid,transids,sellerUserid,sellerName,prodName,dt,tm,trhash,prevhash;
private int prodId,trid;
private double price;
private List<Transactions> lsttrans;
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
public void setPrice(double price) {
	this.price = price;
}
public List<Transactions> getLsttrans() {
	return lsttrans;
}
public void setLsttrans(List<Transactions> lsttrans) {
	this.lsttrans = lsttrans;
}
 
}
