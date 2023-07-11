#!C:\Users\Spider Projects\AppData\Local\Programs\Python\Python39\python
import cgi
import mysql.connector as mycon
import base64
from DBOperations import *
 
cnt=0
cnt1=0
print("Content-type: text/html")
print()

form=cgi.FieldStorage()
userid=form.getvalue("userid") 
crop=form.getvalue("crop") 
sunlight=form.getvalue("sunlight") 
season=form.getvalue("season")
soiltype=form.getvalue("soiltype") 
#print("param val="+userid)
 
#print("userid="+uid+" usershare="+requesterUserid)
try:
    #calculateReviewsScore1(message)
    insertRecommCrop(userid)
    getScores(userid)
    print("classification")
    print(userid) 
    print(crop) 
    print(sunlight) 
    print(season) 
    print(soiltype)
      
     
    print("<html>")
    print("<head>")
    print("<meta http-equiv='refresh' content='0;url=http://localhost:8080/FromPython?userid="+userid+"&crop="+crop+"&sunlight="+sunlight+"&season="+season+"&soilType="+soiltype+"&sts=success'/>")
    print("</head>")
    print("</html>")
    
    
except Exception as e:
        print(e)
        print("Variable x is not defined"+ NameError)
        print("<html>")
        print("<head>")
        print("<meta http-equiv='refresh' content='0;url=http://localhost:8080/FromPython?userid="+userid+"&crop="+crop+"&sunlight="+sunlight+"&season="+season+"&soilType="+soiltype+"&sts=success'/>")
        print("</head>")
        print("</html>")