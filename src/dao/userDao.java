/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import database.Mysqlconnection;
import model.userModel;
import java.sql.*;

/**
 *
 * @author anubhavsilwal
 */
public class userDao {
    Mysqlconnection mysql = new Mysqlconnection();
    
    public void signup(userModel user){
        Connection conn = mysql.openConnection();
        String sql = "insert into users (username, email, password) values (?,?,?)";
        try(PreparedStatement pstm = conn.prepareStatement (sql)){
            pstm.setString(1, user.getUsername());
            pstm.setString(2, user.getEmail());
            pstm.setString(3, user.getPassword());
            pstm.executeUpdate();
        }catch(Exception ex){
            System.out.println(ex);
        }finally{
            mysql.closeConnection(conn);
        }
    }


public boolean check(userModel user){
        Connection conn = mysql.openConnection();
        String sql = "select * from users where email = ? or username = ?";
        try(PreparedStatement pstm = conn.prepareStatement (sql)){
            pstm.setString(1, user.getEmail());
            pstm.setString(2, user.getUsername());
            ResultSet result = pstm.executeQuery();
            return result.next();
        }catch(Exception ex){
            System.out.println(ex);
        }finally{
            mysql.closeConnection(conn);
        }
        return false;
    }
    // ============ ADD THIS LOGIN METHOD ============
    public userModel login(String email, String password) {
        Connection conn = mysql.openConnection();
        String sql = "SELECT * FROM users WHERE email = ? AND password = ?";
        try (PreparedStatement pstm = conn.prepareStatement(sql)) {
            pstm.setString(1, email);
            pstm.setString(2, password);
            ResultSet result = pstm.executeQuery();
            
            if (result.next()) {
                userModel user = new userModel(
                result.getString("username"),
                result.getString("email"),     // CORRECT - email 2nd
                result.getString("password")   // CORRECT - password 3rd
);
                user.setId(result.getInt("user_id"));
                return user;
            }
        } catch (Exception ex) {
            System.out.println("Login error: " + ex);
        } finally {
            mysql.closeConnection(conn);
        }
        return null;
    }
    // ============ END OF ADDED METHOD ============
}
