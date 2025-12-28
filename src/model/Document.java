package model;

public class Document {
    private String title;
    private String category;
    private String date;
    private String size;
    
    public Document(String title, String category, String date, String size) {
        this.title = title;
        this.category = category;
        this.date = date;
        this.size = size;
    }
    
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    
    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }
    
    public String getDate() { return date; }
    public void setDate(String date) { this.date = date; }
    
    public String getSize() { return size; }
    public void setSize(String size) { this.size = size; }
    
    @Override
    public String toString() {
        return title + " | " + category + " | " + date + " | " + size;
    }
}