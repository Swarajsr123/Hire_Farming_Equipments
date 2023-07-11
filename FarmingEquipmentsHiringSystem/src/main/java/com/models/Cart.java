package com.models;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Vector;

import javax.servlet.http.HttpSession;

import com.services.DBConnector;

 

public class Cart {
	Connection con;
    CallableStatement csmt;
    ResultSet rs;
    private String title,category,selleruid,sellerutype,sellerunm,subcategory,page,dt,userid;
    public String getDt() {
		return dt;
	}
	public void setDt(String dt) {
		this.dt = dt;
	}
	private int searchCount,prodId,quantity,cartid;
    private double price,ebprice,availQuantity,totalprice,totalShopPrice;
   private List<Cart> lstcart = new ArrayList<Cart>();
  
	  
public double getEbprice() {
	return ebprice;
}
public void setEbprice(double ebprice) {
	this.ebprice = ebprice;
}
public double getAvailQuantity() {
	return availQuantity;
}
public void setAvailQuantity(double availQuantity) {
	this.availQuantity = availQuantity;
}
public String getSelleruid() {
	return selleruid;
}
public void setSelleruid(String selleruid) {
	this.selleruid = selleruid;
}
public String getSellerutype() {
	return sellerutype;
}
public void setSellerutype(String sellerutype) {
	this.sellerutype = sellerutype;
}
public String getSellerunm() {
	return sellerunm;
}
public void setSellerunm(String sellerunm) {
	this.sellerunm = sellerunm;
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

public List<Cart> getLstcart() {
	return lstcart;
}
public void setLstcart(List<Cart> lstcart) {
	this.lstcart = lstcart;
}
public Cart()
{
	
}
public Cart(ResultSet rs) 
{
	try
	{
	title=rs.getString("prodTitle").toString().trim();
	category=rs.getString("category").toString().trim();
	subcategory=rs.getString("subcategory").toString().trim();
	dt=rs.getString("dt").toString().trim();
	price=rs.getDouble("price");
	totalprice=rs.getDouble("totalprice");
	prodId=rs.getInt("prodid");
	JavaFuns jf=new JavaFuns();
	Vector v=jf.getValue("select availQuantity from products where prodId="+prodId, 1);
	availQuantity=Double.parseDouble(v.elementAt(0).toString().trim());
	quantity=rs.getInt("quantity");
	cartid=rs.getInt("cartId");
	System.out.println("title="+title);
	}
	catch (Exception e) {
		// TODO: handle exception
		System.out.println("err="+e.getMessage());
	}
}
	public void getCartDetails()
	    {
	        try
	        {
	             DBConnector obj=new  DBConnector();
	            con=obj.connect();
	            csmt=con.prepareCall("{call getCartDetails(?)}");
	           csmt.setString(1,userid);
	             csmt.execute();
	             rs=csmt.getResultSet();
	            lstcart=new ArrayList<Cart>();            
	            while(rs.next())
	            { System.out.println("true");
	            lstcart.add(new Cart(rs));
	                  
	            }
	        } 
	        catch(Exception ex)
	        {
	            System.out.println("err="+ex.getMessage());
	             
	        }
	    }
	public boolean checkProductInCartORNOT(int prodid1,String userid1)
    {
		boolean flag=false;
        try
        {
             DBConnector obj=new  DBConnector();
            con=obj.connect();
            csmt=con.prepareCall("{call checkProductInCart(?,?)}");
           csmt.setInt(1, prodid1);
           csmt.setString(2, userid1);
             csmt.execute();
             rs=csmt.getResultSet();
            lstcart=new ArrayList<Cart>();            
            while(rs.next())
            { System.out.println("exist");
            flag=true;
                  
            }
            try{con.close();}catch(Exception ex){}
        } 
        catch(Exception ex)
        {
            System.out.println("err="+ex.getMessage());
             
        }
        return flag;
    }
	public int totalItemsInCart(String userid1)
    {
		int toalItems=0;
        try
        {
             DBConnector obj=new  DBConnector();
            con=obj.connect();
            csmt=con.prepareCall("{call getTotalItemsInCart(?)}");
            csmt.setString(1, userid1);
             csmt.execute();
             rs=csmt.getResultSet();
            lstcart=new ArrayList<Cart>();            
            while(rs.next())
            { System.out.println("items");
             
            toalItems=rs.getInt("items");
            }
            try{con.close();}catch(Exception ex){}
        } 
        catch(Exception ex)
        {
            System.out.println("err="+ex.getMessage());
             
        }
        return toalItems;
    }
	public boolean addToCart(HttpSession ses)
    {
        try
        {
        	JavaFuns jf=new JavaFuns();
        	Vector v=jf.getValue("select income from farmer_income where userid='"+ses.getAttribute("userid").toString().trim()+"' and income<=150000 and sts='approved'" , 1);
        	String bodycond="NA";
             DBConnector obj=new  DBConnector();
            con=obj.connect();
            Date dt1=new Date();
            dt=dt1.getDate()+"/"+(dt1.getMonth()+1)+"/"+(dt1.getYear()+1900);
            csmt=con.prepareCall("{call insertCart(?,?,?,?,?,?,?,?,?,?,?,?)}");
            csmt.setString(1, userid);
            csmt.setString(2, title);
            csmt.setInt(3, prodId);
            quantity=1;
            csmt.setInt(4, quantity);
            if(v.size()>0)
            {
            	  csmt.setDouble(5, ebprice);
                  
                  csmt.setDouble(6, ebprice);
            }
            else
            {
            	  csmt.setDouble(5, price);
                  
                  csmt.setDouble(6, price);
            }
          
            csmt.setString(7, dt);
            csmt.setString(8, category); 
            csmt.setString(9, subcategory); 
            csmt.setString(10, selleruid);
            csmt.setString(11, sellerunm);
            csmt.setString(12, sellerutype);
            
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
	public boolean removeItems()
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
	public boolean updateItems()
    {
        try
        { 
             DBConnector obj=new  DBConnector();
            con=obj.connect();
            Date dt1=new Date();
            dt=dt1.getDate()+"/"+(dt1.getMonth()+1)+"/"+(dt1.getYear()+1900);
            csmt=con.prepareCall("{call updateCartItem(?,?)}");
            csmt.setInt(1, cartid); 
            csmt.setInt(2, quantity);  
        
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
}
