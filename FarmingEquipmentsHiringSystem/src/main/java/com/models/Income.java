package com.models;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.services.DBConnector;
public class Income {
	Connection con;
    CallableStatement csmt;
    ResultSet rs;
    private List<Income> lstincome;
    private String userid,name,mobile,proof, year,sts;
    private double income;
    private int id;
    private MultipartFile file;
    public MultipartFile getFile() {
		return file;
	}
 

public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getMobile() {
		return mobile;
	}


	public void setMobile(String mobile) {
		this.mobile = mobile;
	}


	public int getId() {
		return id;
	}


public String getSts() {
		return sts;
	}


	public void setSts(String sts) {
		this.sts = sts;
	}


public List<Income> getLstincome() {
		return lstincome;
	}


	public void setLstincome(List<Income> lstincome) {
		this.lstincome = lstincome;
	}


	public String getUserid() {
		return userid;
	}


	public void setUserid(String userid) {
		this.userid = userid;
	}


	public String getProof() {
		return proof;
	}


	public void setProof(String proof) {
		this.proof = proof;
	}


	public String getYear() {
		return year;
	}


	public void setYear(String year) {
		this.year = year;
	}


	public double getIncome() {
		return income;
	}


	public void setIncome(double income) {
		this.income = income;
	}


	public void setId(int id) {
		this.id = id;
	}


	public void setFile(MultipartFile file) {
		this.file = file;
	}


public Income() {}
      
      
 

	public Income(ResultSet rs)
	{
		try
		{
		userid=rs.getString("userid").toString().trim();
		year=rs.getString("incomeyr").toString().trim();
		proof=rs.getString("proof").toString().trim();
		income=rs.getDouble("income");
		id=rs.getInt("id");
		sts=rs.getString("sts").toString().trim();
		name=rs.getString("name").toString().trim();
		mobile=rs.getString("mobile").toString().trim();
		
		}
		catch (Exception e) {
			// TODO: handle exception
			System.out.println("err="+e.getMessage());
		}
	}
	public void getPendingIncomes()
	{
	    try
	    {
	         DBConnector obj=new  DBConnector();
	        con=obj.connect();
	        csmt=con.prepareCall("{call getPendingIncome()}");
	        //csmt.setString(1, userid);
	         
	        lstincome=new ArrayList<Income>();
	         
	         csmt.execute();
	         rs=csmt.getResultSet();
	                     
	        while(rs.next())
	        { System.out.println("true");
	        lstincome.add(new Income(rs));
	              
	        }
	        try { con.close();}catch (Exception e) {}
	    }
	       
	     
	    catch(Exception ex)
	    {
	        System.out.println("err="+ex.getMessage());
	         
	    }
	}
	public void getIncomes(String uid)
	{
	    try
	    {
	         DBConnector obj=new  DBConnector();
	        con=obj.connect();
	        csmt=con.prepareCall("{call getIncome(?)}");
	         csmt.setString(1, uid);
	         
	        lstincome=new ArrayList<Income>();
	         
	         csmt.execute();
	         rs=csmt.getResultSet();
	                     
	        while(rs.next())
	        { System.out.println("true");
	        lstincome.add(new Income(rs));
	              
	        }
	        try { con.close();}catch (Exception e) {}
	    }
	       
	     
	    catch(Exception ex)
	    {
	        System.out.println("err="+ex.getMessage());
	         
	    }
	}

	/*public void getId()
	    {
	        try
	        {
	             DBConnector obj=new  DBConnector();
	            con=obj.connect();
	            csmt=con.prepareCall("{call getMaxIdUsers()}");
	           
	             csmt.execute();
	             rs=csmt.getResultSet();
	                        
	            boolean auth=false;
	            while(rs.next())
	            { System.out.println("true");
	                auth=true;
	                
	                mxid=rs.getInt("mxid");
	                  
	            }
	        }
	           
	         
	        catch(Exception ex)
	        {
	            System.out.println("err="+ex.getMessage());
	             
	        }
	    }*/
	public boolean registration()
	    {
	        try
	        { 
	             DBConnector obj=new  DBConnector();
	            con=obj.connect();
	          PreparedStatement  csmt=con.prepareStatement("insert into farmer_income values(?,?,?,?,?,?)");
	            csmt.setInt(1, id);
	            csmt.setString(2, userid);
	            csmt.setString(3,year);
	            csmt.setString(4, proof);
	            csmt.setDouble(5, income);
	            csmt.setString(6, "pending");
	           
	             int n=csmt.executeUpdate();
	             
	           //  JavaFuns jf=new JavaFuns();          
	           // double kgprice,qprice,gprice;
	          //  double literPrice,Miliprice,gallprice;
	            if(n>0)
	            {
	            	/*if(unit.trim().equals("Quintal")||unit.trim().equals("Kg")||unit.trim().equals("Gram"))
	            	{
	            		 if(unit.trim().equals("Quintal"))
	            		 {
	            			 kgprice=price/100;
	            			 gprice=kgprice/1000;
	            			 qprice=price;
	            		 }
	            		 else if(unit.trim().equals("Kg"))
	            		 {
	            			 kgprice=price;
	            			 gprice=kgprice/1000;
	            			 qprice=price*100;
	            		 }
	            		 else
	            		 {
	            			 gprice=price;
	            			 kgprice=gprice*1000; 
	            			 qprice=kgprice*100;
	            		 }
	            		 if(jf.execute("insert into prodPrices(prodId,unit,price) values("+prodId+",'Quintal',"+qprice+")"))
	            		 {}
	            		 if(jf.execute("insert into prodPrices(prodId,unit,price) values("+prodId+",'Kg',"+kgprice+")"))
	            		 {}
	            		 if(jf.execute("insert into prodPrices(prodId,unit,price) values("+prodId+",'Gram',"+gprice+")"))
	            		 {} 
	            	}
	            	else
	            	{

	            		/* if(unit.trim().equals("Gallon"))
	            		 {
	            			 literPrice=price/3.785;
	            			 Miliprice=literPrice/1000;
	            			 gallprice=price;
	            			 
	            		 }
	            		 else if(unit.trim().equals("Liters"))
	            		 {
	            			 literPrice=price;
	            			 Miliprice=literPrice/1000;
	            			 gallprice=price*3.785;
	            		 }
	            		 else
	            		 {
	            			 Miliprice=price;
	            			 literPrice=Miliprice*1000; 
	            			 gallprice=literPrice*3.785;
	            		 }
	            		 if(jf.execute("insert into prodPrices(prodId,unit,price) values("+prodId+",'Gallon',"+gallprice+")"))
	            		 {}
	            		 if(jf.execute("insert into prodPrices(prodId,unit,price) values("+prodId+",'Liters',"+literPrice+")"))
	            		 {}
	            		 if(jf.execute("insert into prodPrices(prodId,unit,price) values("+prodId+",'ml',"+Miliprice+")"))
	            		 {}
	            	}*/
	            	
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
