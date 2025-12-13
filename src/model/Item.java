/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author samee
 */
public class Item {
    private int itemId;
    private String itemname;     
    private String category;     
    private double value;        
    private String status;       
    private String imagepath; 
    
    
    //constructor naming
    public Item(int itemId,String itemname,String category,double value,String status,String imagepath){
        this.itemId = itemId;
        this.itemname = itemname;
        this.category = category;
        this.value = value;
        this.status = status;
        this.imagepath = imagepath;
    }
    public void setItemId(int itemId){
        this.itemId=itemId;
    }  
    public int getItemId() 
    { return itemId; }
    
    public void setItemName(String itemname){
        this.itemname=itemname;
    }
    public String getItemName() 
    { return itemname; }
    
    public void setCategory(String category){
        this.category=category;
    }
    public String getCategory() 
    { return category; }
    
    public void setValue(double value){
        this.value=value;
    }
    public double getValue()
    { return value; }
    
    public void setStatus(String status){
        this.status=status;
    }
    public String getStatus() 
    { return status; }
    
    public void setImagePath(String imagepath){
        this.imagepath=imagepath;
    }
    public String getImagePath()
    { return imagepath; }
}
