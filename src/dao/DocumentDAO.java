package dao;

import database.MysqlConnection;
import model.Document;
import java.sql.*;
import java.util.*;

public class DocumentDAO {

    // INSERT
    public void insert(Document doc) {
        String sql = "INSERT INTO documents (title, category, doc_date, amount, image_path) VALUES (?, ?, ?, ?, ?)";

        try (Connection con = MysqlConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, doc.getTitle());
            ps.setString(2, doc.getCategory());
            ps.setString(3, doc.getDate());
            ps.setString(4, doc.getAmount());
            ps.setString(5, doc.getImagePath());

            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // FETCH ALL
    public List<Document> getAllDocuments() {
        List<Document> list = new ArrayList<>();
        String sql = "SELECT * FROM documents ORDER BY id DESC";

        try (Connection con = MysqlConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                list.add(new Document(
                        rs.getInt("id"),
                        rs.getString("title"),
                        rs.getString("category"),
                        rs.getString("doc_date"),
                        rs.getString("amount"),
                        rs.getString("image_path")
                ));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    // FILTER
    public List<Document> getDocumentsByCategory(String category) {
        List<Document> list = new ArrayList<>();
        String sql = "SELECT * FROM documents WHERE category=?";

        try (Connection con = MysqlConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, category);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                list.add(new Document(
                        rs.getInt("id"),
                        rs.getString("title"),
                        rs.getString("category"),
                        rs.getString("doc_date"),
                        rs.getString("amount"),
                        rs.getString("image_path")
                ));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    // SEARCH
    public List<Document> searchDocuments(String query) {
        List<Document> list = new ArrayList<>();
        String sql = "SELECT * FROM documents WHERE title LIKE ? OR category LIKE ?";

        try (Connection con = MysqlConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            String q = "%" + query + "%";
            ps.setString(1, q);
            ps.setString(2, q);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Document(
                        rs.getInt("id"),
                        rs.getString("title"),
                        rs.getString("category"),
                        rs.getString("doc_date"),
                        rs.getString("amount"),
                        rs.getString("image_path")
                ));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    // DELETE
    public void deleteById(int id) {
        String sql = "DELETE FROM documents WHERE id=?";

        try (Connection con = MysqlConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
