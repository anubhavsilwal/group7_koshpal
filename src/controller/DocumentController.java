package controller;

import model.Document;  // ADD THIS IMPORT
import java.util.*;

public class DocumentController {
    private List<Document> documents = new ArrayList<>();
    
    public DocumentController() {
        loadSampleData();
    }
    
    private void loadSampleData() {
        documents.add(new Document("Rent Paid", "Utilities", "May 20 2025", "2.3mb"));
        documents.add(new Document("Subscription", "Life Style", "Feb 10 2025", "2.3mb"));
        documents.add(new Document("Doctor Visit", "Health", "June 30 2025", "2.3mb"));
        documents.add(new Document("Saving", "Financial", "May 20 2025", "2.3mb"));
        documents.add(new Document("Emergency Fund", "Other", "Jan 21 2025", "2.3mb"));
    }
    
    public List<Document> getAllDocuments() {
        return new ArrayList<>(documents);
    }
    
    public List<Document> getDocumentsByCategory(String category) {
        List<Document> filtered = new ArrayList<>();
        for (Document doc : documents) {
            if (doc.getCategory().equals(category)) {
                filtered.add(doc);
            }
        }
        return filtered;
    }
    
    public void addDocument(String title, String category, String date, String size) {
        documents.add(new Document(title, category, date, size));
        System.out.println("Document added: " + title);
    }
    
    public List<Document> searchDocuments(String query) {
        List<Document> results = new ArrayList<>();
        for (Document doc : documents) {
            if (doc.getTitle().toLowerCase().contains(query.toLowerCase())) {
                results.add(doc);
            }
        }
        return results;
    }
    
    public void deleteDocument(int index) {
        if (index >= 0 && index < documents.size()) {
            documents.remove(index);
        }
    }
}