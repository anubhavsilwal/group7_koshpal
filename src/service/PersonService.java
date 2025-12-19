/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import model.Person;  // CORRECTED: Changed Pecison to Person
import java.util.List;
import java.util.ArrayList;  // ADDED for Java 8 compatibility

/**
 *
 * @author salmanansari.81
 */
public class PersonService {
    private int currentUserId;
    
    public void setCurrentUserId(int userId) {
        this.currentUserId = userId;
    }
    
    public List<Person> getActivePersons(int userId) {  // CORRECTED: Changed Pecison to Person, Persona to Persons
        // TODO: Implement database retrieval
        System.out.println("Getting active persons for user: " + userId);
        return new ArrayList<>(); // Changed from List.of() for Java 8 compatibility
    }
}
 
 