package utilities;

import java.sql.*;

public class DatabaseUtils {
    static String url = ConfigReader.getConfigProperty("db.url");
    static String userName = ConfigReader.getConfigProperty("db.userName");
    static String password = ConfigReader.getConfigProperty("db.password");
    static Connection con;
    static Statement stmt;
    static ResultSet rs;


       public static void initializeDBProperties() {

        try {
            //1. Get the connection - One Time Setup activity
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(url, userName, password);
             stmt = con.createStatement();
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public static ResultSet executeQuery(String query) {
        //2. We are going to use whenever we want to execute the query
        try {
            rs = stmt.executeQuery(query);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }

    public static void closeConnection() {
        //4. Close the statement - One Time closing activity

        try {
            stmt.close();
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}

