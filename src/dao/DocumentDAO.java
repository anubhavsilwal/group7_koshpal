/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

/**
 *
 * @author ACER
 */

import database.MySQLConnection;
import model.Document;
import java.sql.Connection;
import java.sql.PreparedStatement;

public class DocumentDAO {

    public void insert(Document doc) {

        String sql = "INSERT INTO documents (title, category, file_path, file_size, upload_date) "
                   + "VALUES (?, ?, ?, ?, ?)";

        try {
            Connection con = new MySQLConnection().getConnection();
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, doc.getTitle());
            ps.setString(2, doc.getCategory());
            ps.setString(3, doc.getFilePath());
            ps.setDouble(4, doc.getFileSize());
            ps.setDate(5, new java.sql.Date(doc.getUploadDate().getTime()));

            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

