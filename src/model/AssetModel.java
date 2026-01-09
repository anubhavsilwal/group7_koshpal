package model;

import java.util.Date;

public class AssetModel {
    private int asset_id;
    private int user_id;
    private String asset_name;
    private String category;
    private double purchase_value;
    private double current_value;
    private Date purchase_date;
    private String description;
    private String image_path;
    private boolean is_on_loan;
    
    // Constructor
    public AssetModel(int user_id, String asset_name, String category, 
                     double purchase_value, double current_value) {
        this.user_id = user_id;
        this.asset_name = asset_name;
        this.category = category;
        this.purchase_value = purchase_value;
        this.current_value = current_value;
    }
    
    // Getters and Setters
    public int getAssetId() { return asset_id; }
    public void setAssetId(int asset_id) { this.asset_id = asset_id; }
    
    public int getUserId() { return user_id; }
    public void setUserId(int user_id) { this.user_id = user_id; }
    
    public String getAssetName() { return asset_name; }
    public void setAssetName(String asset_name) { this.asset_name = asset_name; }
    
    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }
    
    public double getPurchaseValue() { return purchase_value; }
    public void setPurchaseValue(double purchase_value) { this.purchase_value = purchase_value; }
    
    public double getCurrentValue() { return current_value; }
    public void setCurrentValue(double current_value) { this.current_value = current_value; }
    
    public Date getPurchaseDate() { return purchase_date; }
    public void setPurchaseDate(Date purchase_date) { this.purchase_date = purchase_date; }
    
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    
    public String getImagePath() { return image_path; }
    public void setImagePath(String image_path) { this.image_path = image_path; }
    
    public boolean isOnLoan() { return is_on_loan; }
    public void setOnLoan(boolean is_on_loan) { this.is_on_loan = is_on_loan; }
}