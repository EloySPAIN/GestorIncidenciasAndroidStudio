package com.example.gestorincidencies;

import android.annotation.SuppressLint;
import android.os.StrictMode;
import android.util.Log;
import java.sql.Connection;
import java.sql.DriverManager;

public class ConnexioBD{

    Connection conexio;
    String username, pass, ip, port, database;

    @SuppressLint("NewApi")
    public Connection connect(){
        ip="192.168.1.233";
        username="jordi";
        pass="123";
        port="3306";
        database="act2m8";

        Connection connection = null;
        String connectionURL = "";

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        try{
            Class.forName("com.mysql.jdbc.Driver");
            connectionURL="jdbc:mysql://"+ip+":"+port+"/"+database+"?allowPublicKeyRetrieval=true&useSSL=false";
            connection= DriverManager.getConnection(connectionURL, username, pass);
        }catch(Exception ex){
            Log.e("Error ", ex.getMessage());
        }
        return connection;
    }

}