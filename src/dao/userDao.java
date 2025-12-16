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
}
