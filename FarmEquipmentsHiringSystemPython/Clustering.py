#!C:\Users\Spider Projects\AppData\Local\Programs\Python\Python310\python
import cgi
import mysql.connector as mycon
import base64
from DBOperations import *
from kmeans import KMeans
import os
cnt=0
cnt1=0
print("Content-type: text/html")
print()

form=cgi.FieldStorage()
userid=form.getvalue("userid")
UPLOAD_DIR=os.getcwd()+"\\Clustering_DataSet\\" 
#print("userid="+uid+" usershare="+requesterUserid)
try:
    #calculateReviewsScore1(message)
    #lstcate=getScores1(userid)
    print("clustering")
    insertMarks()
    exportToDat()
    ikmeans = KMeans(UPLOAD_DIR+"\\outfile.dat", 3)
    ikmeans.process()
    ikmeans.flush(UPLOAD_DIR+"\\k-means-out.dat")
    print("<html>")
    print("<head>")
    print("<meta http-equiv='refresh' content='0;url=http://localhost:8080/fromPythonClust'/>")
    print("</head>")
    print("</html>")
    
except Exception as e:
        print(e)
        print("Variable x is not defined"+ NameError)