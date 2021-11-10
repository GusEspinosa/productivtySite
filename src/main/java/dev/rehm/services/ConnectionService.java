package dev.rehm.services;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionService {

        public Connection establishConnection() throws SQLException {

        /*String testEnv = System.getenv("IS_TEST");
        if(Boolean.parseBoolean(testEnv)){
            return DriverManager.getConnection("jdbc:h2:~/test");
        }*/

                //registering our JDBC driver in the classpath
                try {
                        Class.forName("org.postgresql.Driver");
                } catch (ClassNotFoundException e){
                        e.printStackTrace();
                }
                String url = "jdbc:postgresql://localhost:5432/postgres";
        /*String host = null;//System.getenv("DB_HOST");
        if(host==null){
            url = "jdbc:postgresql://localhost:5432/postgres";
        } else {
            url = "jdbc:postgresql://"+host+":5432/postgres";
        }*/

                String username = "postgres";//System.getenv("DB_USER");
                String password = "Password1";//System.getenv("DB_PASS");
                return DriverManager.getConnection(url, username, password);
        }

}