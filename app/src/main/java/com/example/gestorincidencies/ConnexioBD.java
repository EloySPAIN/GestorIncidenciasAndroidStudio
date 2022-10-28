package com.example.gestorincidencies;

import android.annotation.SuppressLint;
import android.util.Log;
import android.widget.Toast;
import java.sql.Connection;
import java.sql.DriverManager;

public class ConnexioBD{

    Connection conexio;
    String username, pass, ip, port, database;

    @SuppressLint("NewApi")
    public Connection connect(){
        ip="192.168.5.63";
        username="jordi";
        pass="1234";
        port="3306";
        database="act2m8";

        //falta StrictMode

        Connection connection = null;
        String connectionURL = null;

        try{
            Class.forName("com.mysql.jdbc.Driver");
//          connectionURL="jdbc:mysql://"+ip+":"+port+";"+"databasename="+database+";user=" + username + ";password=" + pass + ";";
            connectionURL="jdbc:mysql://192.168.5.63:3306/act2m8";
            connection= DriverManager.getConnection(connectionURL, username, pass);
        }catch(Exception ex){
            Log.e("Error ", ex.getMessage());
        }
        return connection;
    }

}
