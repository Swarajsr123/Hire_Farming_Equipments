/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.services;


 
 
import java.sql.*;
 
 
 
public class DBConnector {
    
 public Connection con;
    public DBConnector() 
    {
    }
    
    public Connection connect()
    {
    
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
         
           con=DriverManager.getConnection("jdbc:mysql://udnefhpxqoxdptqc:eVu9L2YDxHdbUSk43hIt@bsuum6x82botyg6qjayl-mysql.services.clever-cloud.com:3306/bsuum6x82botyg6qjayl");         
            System.out.println("Connected..");
        }
        catch(Exception e)
        {
            System.out.println("Error in connection : "+e.getMessage());
        }
        
    return con;
    }
    
    
    
}

