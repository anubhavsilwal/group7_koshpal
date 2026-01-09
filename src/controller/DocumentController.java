package controller;

import dao.DocumentDAO;
import model.Document;
import java.util.List;

public class DocumentController {

    private final DocumentDAO dao = new DocumentDAO();

    public List<Document> getAllDocuments() {
        return dao.getAllDocuments();
    }

    public void addDocument(String title, String category, String date, String amount, String imagePath) {
        Document doc = new Document(title, category, date, amount, imagePath);
        dao.insert(doc);
    }

    public List<Document> getDocumentsByCategory(String category) {
        return dao.getDocumentsByCategory(category);
    }

    public List<Document> searchDocuments(String query) {
        return dao.searchDocuments(query);
    }

    public void deleteDocument(int id) {
        dao.deleteById(id);
    }
}
