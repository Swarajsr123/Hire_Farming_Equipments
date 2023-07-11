/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.models;
 
  
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
 
import java.util.Vector;

import com.services.DBConnector;

 
 
  
/**
 *
 * @author megha
 */
public class JavaFuns {
        Connection con;
     PreparedStatement pst;
     CallableStatement cst;
     ResultSet rs;
     public int FetchMax(String fldnm,String tblnm)
     {
        int maxid=1000;
     try
     {
     	  DBConnector obj=new  DBConnector();
           con=obj.connect();
         String qr="select max("+fldnm+") as mxid from "+tblnm;
         pst=con.prepareStatement(qr);
         rs=pst.executeQuery();
         while(rs.next())
         maxid=rs.getInt("mxid");
         if(maxid==0)
             maxid=1000;
     }
     catch(Exception ex)
     {
         System.out.println("err="+ex.getMessage());
     maxid=1000;
     }
     finally
     {
         try{
         con.close();}catch(Exception ex){}
     }
     return (maxid+1);
     }
    public Vector getValue(String qr,int nocol)
    {    
        Vector v=new Vector();
        Connection con=null;
        Statement st;
        ResultSet rs;
        v.clear();
         try{ 
              DBConnector obj=new  DBConnector();
             con=obj.connect();
           st=con.createStatement();
           System.out.println("query="+qr);
           rs=st.executeQuery(qr);
          
           while(rs.next())
           {
               for(int i=1;i<=nocol;i++)
               {
               v.addElement(rs.getString(i));
               System.out.println(rs.getString(i));
               }              
           }
         }
         catch(Exception e)
        {
            System.out.println("Error in processing due to "+e.getMessage());
        }   
          finally
         {
             try{
             con.close();}catch(Exception ex){}
         }
        return(v);        
    }
    public boolean execute(String qr)
    {
         Boolean sts=false;
         try
        {
        	 DBConnector obj=new DBConnector();
            con=obj.connect();
            Statement st=con.createStatement();
            st.executeUpdate(qr);
            sts=true;
        }
        catch(Exception ex)
        {
        sts=false;
        }
         finally
         {
             try{
             con.close();}catch(Exception ex){}
         }
        return sts;  
        
    }  
}
