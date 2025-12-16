/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import view.login;


/**
 *
 * @author anubhavsilwal
 */
public class loginController {
    private final login login;
    public loginController(login login) {
        this.login = login;
        
    }
    
    public void open(){
        this.login.setVisible(true);
    }

}

