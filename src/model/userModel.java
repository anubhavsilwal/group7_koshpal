package model;


public class userModel {
    private int user_id;
    private String username;
    private String password;
    private String email;
    
    public userModel (String username, String email, String password){
        this.username = username;
        this.email = email;
        this.password = password;
}
    
    public void setId(int user_id){
        this.user_id = user_id;
    }
    public int getId(){
        return user_id;
    }
    public void setUsername(String username){
        this.username = username;
    }
    public String getUsername(){
        return username;
    }
    public void setPassword(String password){
        this.password = password;
    }
    public String getPassword(){
        return password;
    }
    public void setEmail(String email){
        this.email = email;
    }
    public String getEmail(){
        return email;
    }
}
