package com.models;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.services.DBConnector;

 
public class FarmDetails {
	Connection con;
    CallableStatement csmt;
    ResultSet rs;
    private double lat,lng;
    private List<FarmDetails> lstfarms;
    private String title,farmType,addr,pincode,state,city,village,soiltype,userid,weather;
   public FarmDetails() {}
      
         
	public String getFarmType() {
	return farmType;
}


public void setFarmType(String farmType) {
	this.farmType = farmType;
}


	public String getPincode() {
	return pincode;
}


public void setPincode(String pincode) {
	this.pincode = pincode;
}


	public double getLat() {
	return lat;
}


public void setLat(double lat) {
	this.lat = lat;
}


public double getLng() {
	return lng;
}


public void setLng(double lng) {
	this.lng = lng;
}


public List<FarmDetails> getLstfarms() {
	return lstfarms;
}


public void setLstfarms(List<FarmDetails> lstfarms) {
	this.lstfarms = lstfarms;
}


public String getTitle() {
	return title;
}


public void setTitle(String title) {
	this.title = title;
}


public String getAddr() {
	return addr;
}


public void setAddr(String addr) {
	this.addr = addr;
}


public String getState() {
	return state;
}


public void setState(String state) {
	this.state = state;
}


public String getCity() {
	return city;
}


public void setCity(String city) {
	this.city = city;
}


public String getVillage() {
	return village;
}


public void setVillage(String village) {
	this.village = village;
}


public String getSoiltype() {
	return soiltype;
}


public void setSoiltype(String soiltype) {
	this.soiltype = soiltype;
}


public String getUserid() {
	return userid;
}


public void setUserid(String userid) {
	this.userid = userid;
}


public String getWeather() {
	return weather;
}


public void setWeather(String weather) {
	this.weather = weather;
}


	public FarmDetails(ResultSet rs)
	{
		try
		{
		addr=rs.getString("addr").toString().trim();
		title=rs.getString("title").toString().trim();
		userid=rs.getString("userid").toString().trim();
		soiltype=rs.getString("soiltype").toString().trim();
		city=rs.getString("city").toString().trim();
		state=rs.getString("state").toString().trim();
		village=rs.getString("village").toString().trim();
		lat=rs.getDouble("lat");
		lng=rs.getDouble("lng");
		userid=rs.getString("userid").toString().trim();
		farmType=rs.getString("farmType").toString().trim();
		}
		catch (Exception e) {
			// TODO: handle exception
			System.out.println("err="+e.getMessage());
		}
	}
	public void getFarmDetails()
	{
	    try
	    {
	         DBConnector obj=new  DBConnector();
	        con=obj.connect();
	        csmt=con.prepareCall("{call getFarms(?)}");
	        csmt.setString(1, userid);  
	        lstfarms=new ArrayList<FarmDetails>();
	         
	         csmt.execute();
	         rs=csmt.getResultSet();
	                     
	        while(rs.next())
	        { System.out.println("true");
	        lstfarms.add(new FarmDetails(rs));
	              
	        }
	    }
	       
	     
	    catch(Exception ex)
	    {
	        System.out.println("err="+ex.getMessage());
	         
	    }
	} 
	public boolean registration()
	    {
	        try
	        { 
	             DBConnector obj=new  DBConnector();
	            con=obj.connect();
	            csmt=con.prepareCall("{call insertFarms(?,?,?,?,?,?,?,?,?,?)}");
	            csmt.setString(1, userid);
	            csmt.setString(2, addr);
	            csmt.setString(3, state);
	            csmt.setString(4, city);
	            csmt.setString(5, village);
	            csmt.setString(6, soiltype);
	            csmt.setDouble(7, lat);
	            csmt.setDouble(8, lng);
	             csmt.setString(9, title);
	             csmt.setString(10, farmType);
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

