package com.example.gestorincidencies;

import android.annotation.SuppressLint;
import android.os.StrictMode;
import android.util.Log;
import java.sql.Connection;
import java.sql.DriverManager;

public class ConnexioBD{

    //Aqui declarem les variables que fem servir
    Connection conexio;
    String username, pass, ip, port, database;

    //Creem una funcio la qual criadrem des d'altres classes
    @SuppressLint("NewApi")
    public Connection connect(){
        //declarem les variables amb els parametres com la ip, el port...
        ip="192.168.1.233";
        username="jordi";
        pass="123";
        port="3306";
        database="act2m8";

        //declarem les variables de conexió a null
        Connection connection = null;
        String connectionURL = "";

        //declararem el strict mode per a poder connectar-nos sense problema a la BBDD
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        //farem un try catch per a si no funciona el connector a la BBDD
        try{
            Class.forName("com.mysql.jdbc.Driver");
            connectionURL="jdbc:mysql://"+ip+":"+port+"/"+database+"?allowPublicKeyRetrieval=true&useSSL=false";
            connection= DriverManager.getConnection(connectionURL, username, pass);
        }catch(Exception ex){
            //aqui ens mostrarà el missatge d'error en cas de que fallés
            Log.e("Error ", ex.getMessage());
        }
        return connection;
    }

}