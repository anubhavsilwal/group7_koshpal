package model;

public class Document {

    private int id;
    private String title;
    private String category;
    private String date;
    private String amount;
    private String imagePath;

    // FOR INSERT
    public Document(String title, String category, String date, String amount, String imagePath) {
        this.title = title;
        this.category = category;
        this.date = date;
        this.amount = amount;
        this.imagePath = imagePath;
    }

    // FOR FETCH
    public Document(int id, String title, String category, String date, String amount, String imagePath) {
        this.id = id;
        this.title = title;
        this.category = category;
        this.date = date;
        this.amount = amount;
        this.imagePath = imagePath;
    }

    public int getId() { return id; }
    public String getTitle() { return title; }
    public String getCategory() { return category; }
    public String getDate() { return date; }
    public String getAmount() { return amount; }
    public String getImagePath() { return imagePath; }
}
