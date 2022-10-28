package com.example.gestorincidencies;

import android.util.Log;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnexioBD {

    Connection conexio;
    String username, pass, ip, port, database;

    public Connection connect(){
        ip="127.0.0.1";
        username="jordi";
        pass="1234";
        port="1433";
        database="act2m8";

        //falta StrictMode

        Connection connection = null;
        String connectionURL = null;

        try{
            Class.forName("net.sourceforge.jtds.jdbc.Driver");
            connectionURL="jdbc:jtds:sqlserver://"+ip+":"+port+";"+"databasename="+database+";user=" + username + ";password=" + pass + ";";
            connection= DriverManager.getConnection(connectionURL);
        }catch(Exception ex){
            Log.e("Error: ", ex.getMessage());
        }
        return connection;
    }

}
