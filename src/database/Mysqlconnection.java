package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 *
 * @author samee
 */
public class Mysqlconnection implements Database {

    @Override
    public Connection openConnection() {
       try{
           String username = "root";
           String password = "Ainubruv@123";
           String database = "koshpal";
           Connection connection;
           connection = DriverManager.getConnection(
           "jdbc:mysql://localhost:3306/" + database, username, password);
           if(connection == null){
               System.out.println("Connection failed");
           }else{
               System.out.println("Connection successful");
           }
           return connection;
       }catch(Exception e){
           System.out.println("Error in openConnection: " + e.getMessage());
           return null;
       }
    }

    @Override
    public void closeConnection(Connection conn) {
        try{
            if(conn != null && !conn.isClosed()){
                conn.close();
                System.out.println("Connection closed");
            }
        }catch(Exception e){
            System.out.println("Error in closeConnection: " + e.getMessage());
        }
    }

    @Override
    public ResultSet runQuery(Connection conn, String query) {
        try{
            Statement stmp = conn.createStatement();
            ResultSet result = stmp.executeQuery(query);
            return result;
        }catch(Exception e){
            System.out.println("Error in runQuery: " + e.getMessage());
            return null;
        }
    }

    @Override
    public int executeUpdate(Connection conn, String query) {
        try{
            Statement stmp = conn.createStatement();
            int result = stmp.executeUpdate(query);
            return result;
        }catch(Exception e){
            System.out.println("Error in executeUpdate: " + e.getMessage());
            return -1;
        }
    }
}