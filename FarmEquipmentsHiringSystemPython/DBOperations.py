from getpass import getuser
from DBConnect import *
import base64

from DecisionTreeAlgo import decisiontree

def insertRecommCrop(userid="NA") : 
    conn = connect()    
    cursor = conn.cursor()
     
    args = [userid]
    args1=cursor.callproc('insertRecommCrop', args)
    print("Return value:", args1)
    #for result in cursor.stored_results():
     #       print(result.fetchall())
    cnt=cursor.rowcount 
    conn.commit()
     
def updateReading(result="NA",id="NA") : 
    conn = connect()    
    cursor = conn.cursor()
     
    args = [result,id]
    args1=cursor.callproc('updatePrediction', args)
    print("Return value:", args1)
    #for result in cursor.stored_results():
     #       print(result.fetchall())
    cnt=cursor.rowcount 
    conn.commit()
     

def getScores(userid='na') :
    try: 
        lst=[]
        conn = connect()    
        cursor = conn.cursor()
        #cursor.execute("select* from userprofile where userid='"+uid+"'")
        print("select * from decisionTreeInput where userid='"+userid+"'")
        sql_select_query = "select * from decisionTreeInput where userid='"+userid+"'"
        cursor.execute(sql_select_query)
        record = cursor.fetchall()
        #record = cursor.fetchall()
        final_result = [list(i) for i in record]
        print(final_result)
        lst=[]
        uid="na"
        
        for row in final_result: 
            print("fiinal")
            id=row[0] 
            print(row[1])
            lst.append(row[2])
            lst.append(row[3])
            lst.append(row[4])
            lst.append(row[5])
            
            print(lst)
            ypred=decisiontree(lst)
            print("pred="+str(ypred[0]))
            pred=str(ypred[0])
            updateReading(pred,id)
            lst.clear()
        print(id)
        print(lst)
    except Exception as e:
         print('NA')
    
 
def convertToBase64(message='NA') :
    message_bytes = message.encode('ascii')
    base64_bytes = base64.b64encode(message_bytes)
    base64_message = base64_bytes.decode('ascii')
    print(base64_message)
    return base64_message

def convertFromBase64(base64_message='NA') :
    base64_bytes = base64_message.encode('ascii')
    message_bytes = base64.b64decode(base64_bytes)
    message = message_bytes.decode('ascii')
    print(message)
    return message
    
