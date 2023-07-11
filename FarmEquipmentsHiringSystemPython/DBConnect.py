import mysql.connector as mycon

def connect() : 
    con=mycon.connect(host='localhost',user='root',password='crosspolo',database='crop_prediction_db')
    return con