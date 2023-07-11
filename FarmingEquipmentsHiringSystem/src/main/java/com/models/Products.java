package com.models;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.services.DBConnector;
public class Products {
	Connection con;
    CallableStatement csmt;
    ResultSet rs;
    private List<Products> lstprod;
    private String farmType,soilType, title,unit,prodType,userType,usernm,userid,dt,tm,desc,coverphoto;
    private double quantity,price,ebprice;
    private String transid;
    private MultipartFile file;
    public MultipartFile getFile() {
		return file;
	}



	public String getFarmType() {
		return farmType;
	}



	public void setFarmType(String farmType) {
		this.farmType = farmType;
	}



	public String getSoilType() {
		return soilType;
	}



	public void setSoilType(String soilType) {
		this.soilType = soilType;
	}



	public double getEbprice() {
		return ebprice;
	}



	public void setEbprice(double ebprice) {
		this.ebprice = ebprice;
	}



	public String getUnit() {
		return unit;
	}



	public void setUnit(String unit) {
		this.unit = unit;
	}



	public String getProdType() {
		return prodType;
	}



	public void setProdType(String prodType) {
		this.prodType = prodType;
	}



	public String getUserType() {
		return userType;
	}



	public void setUserType(String userType) {
		this.userType = userType;
	}



	public String getUsernm() {
		return usernm;
	}



	public void setUsernm(String usernm) {
		this.usernm = usernm;
	}



	public String getTransid() {
		return transid;
	}



	public void setTransid(String transid) {
		this.transid = transid;
	}



	public void setFile(MultipartFile file) {
		this.file = file;
	}

	private int prodId;
   public int getProdId() {
		return prodId;
	}



	public void setProdId(int prodId) {
		this.prodId = prodId;
	}



public Products() {}
      
      

	public List<Products> getLstprod() {
	return lstprod;
}



public void setLstprod(List<Products> lstprod) {
	this.lstprod = lstprod;
}



public String getTitle() {
	return title;
}



public void setTitle(String title) {
	this.title = title;
}



public String getUserid() {
	return userid;
}



public void setUserid(String userid) {
	this.userid = userid;
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



public String getDesc() {
	return desc;
}



public void setDesc(String desc) {
	this.desc = desc;
}



public String getCoverphoto() {
	return coverphoto;
}



public void setCoverphoto(String coverphoto) {
	this.coverphoto = coverphoto;
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



	public Products(ResultSet rs)
	{
		try
		{
		title=rs.getString("title").toString().trim();
		desc=rs.getString("prodDesc").toString().trim();
		dt=rs.getString("dt").toString().trim();
		tm=rs.getString("tm").toString().trim();
		userid=rs.getString("userid").toString().trim();
		quantity=Double.parseDouble(rs.getString("availQuantity").toString().trim());
		price=Double.parseDouble(rs.getString("price").toString().trim());
		ebprice=Double.parseDouble(rs.getString("ebprice").toString().trim());
		userid=rs.getString("userid").toString().trim();
		usernm=rs.getString("username").toString().trim();
		userType=rs.getString("usertype").toString().trim();
		prodType=rs.getString("prodType").toString().trim();
		prodId=Integer.parseInt(rs.getString("prodId").toString().trim());
		transid= (rs.getString("transId").toString().trim());
		coverphoto=rs.getString("coverPhoto").toString().trim();
		System.out.println("cover="+coverphoto);
		unit=rs.getString("unit").toString().trim();
		
		}
		catch (Exception e) {
			// TODO: handle exception
			System.out.println("err="+e.getMessage());
		}
	}
	public void getProducts()
	{
	    try
	    {
	         DBConnector obj=new  DBConnector();
	        con=obj.connect();
	        csmt=con.prepareCall("{call getProducts(?)}");
	        csmt.setString(1, userid);
	         
	        lstprod=new ArrayList<Products>();
	         
	         csmt.execute();
	         rs=csmt.getResultSet();
	                     
	        while(rs.next())
	        { System.out.println("true");
	        lstprod.add(new Products(rs));
	              
	        }
	        try { con.close();}catch (Exception e) {}
	    }
	       
	     
	    catch(Exception ex)
	    {
	        System.out.println("err="+ex.getMessage());
	         
	    }
	}
	
	public void getProducts2()
	{
	    try
	    {
	         DBConnector obj=new  DBConnector();
	        con=obj.connect();
	        csmt=con.prepareCall("{call getProducts2()}");
	         
	         
	        lstprod=new ArrayList<Products>();
	         
	         csmt.execute();
	         rs=csmt.getResultSet();
	                     
	        while(rs.next())
	        { System.out.println("true");
	        lstprod.add(new Products(rs));
	              
	        }
	        try { con.close();}catch (Exception e) {}
	    }
	       
	     
	    catch(Exception ex)
	    {
	        System.out.println("err="+ex.getMessage());
	         
	    }
	}
	public void getProducts3(String farmType,String soiltype)
	{
	    try
	    {
	         DBConnector obj=new  DBConnector();
	        con=obj.connect();
	        csmt=con.prepareCall("{call getProducts3(?,?)}");
	         csmt.setString(1, farmType);
	         csmt.setString(2, soiltype);
	         
	        lstprod=new ArrayList<Products>();
	         
	         csmt.execute();
	         rs=csmt.getResultSet();
	                     
	        while(rs.next())
	        { System.out.println("true");
	        lstprod.add(new Products(rs));
	              
	        }
	        try { con.close();}catch (Exception e) {}
	    }
	       
	     
	    catch(Exception ex)
	    {
	        System.out.println("err="+ex.getMessage());
	         
	    }
	}
	public void getId()
	{
	    try
	    {
	         DBConnector obj=new  DBConnector();
	        con=obj.connect();
	        csmt=con.prepareCall("{call getMaxIdProd()}");
	       
	         csmt.execute();
	         rs=csmt.getResultSet();
	                    
	        boolean auth=false;
	        while(rs.next())
	        { System.out.println("true");
	            auth=true;
	            
	            prodId=rs.getInt("mxid");
	            if(prodId==0 || prodId==1000)
	            	prodId=1001;
	            else
	            	prodId+=1;
	              
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
	            csmt=con.prepareCall("{call insertProducts(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
	            csmt.setString(1, userid);
	            csmt.setString(2, title);
	            csmt.setString(3, desc);
	            csmt.setString(4, coverphoto);
	            csmt.setDouble(5, price);
	            csmt.setString(6, unit);
	            csmt.setString(7, dt);
	            csmt.setString(8, tm);
	            csmt.setDouble(9, quantity);
	            csmt.setString(10, prodType);
	            csmt.setString(11, userType);
	            csmt.setString(12, usernm);
	            csmt.setString(13, transid);
	            csmt.setDouble(14, ebprice);
	            csmt.setString(15, farmType);
	            csmt.setString(16, soilType);
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
	
	public boolean updateQuan()
    {
        try
        { 
             DBConnector obj=new  DBConnector();
            con=obj.connect();
            csmt=con.prepareCall("{call updateProducts(?,?)}");
            csmt.setInt(1, prodId);
            csmt.setDouble(2, quantity);
              
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
	public boolean updatePrice()
    {
        try
        { 
             DBConnector obj=new  DBConnector();
            con=obj.connect();
            csmt=con.prepareCall("{call updateProductPrice(?,?,?)}");
            csmt.setInt(1, prodId);
            csmt.setDouble(2, price);
            csmt.setDouble(3, ebprice);
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
	public boolean deleteProd()
    {
        try
        { 
             DBConnector obj=new  DBConnector();
            con=obj.connect();
            csmt=con.prepareCall("{call deleteProducts(?)}");
            csmt.setInt(1, prodId); 
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
