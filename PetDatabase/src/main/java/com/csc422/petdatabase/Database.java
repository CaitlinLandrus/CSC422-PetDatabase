/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.csc422.petdatabase;

/**
 *
 * @author caitlin landrus
 */

import java.util.LinkedList;

public class Database {
    /* 
        Using LinkedList because the user can choose to remove any pet from the database, 
        and the LinkedList will be more efficient than ArrayList to do this.
        We also need this to be a list because we need the user to select the index for the
        pet that they want to delete. 
    */ 
    private final LinkedList<Pet> db;

    public Database(){
        db = new LinkedList<>();
    }
    
    public void add(Pet p){
        
        //add the pet to the array
        db.add(p);
    }
    
    public int size(){
        return db.size();
    }
    
    public void print(){
        /*
            +----------------------+
            | ID | NAME | AGE | 
            +----------------------+
            | 0 | Kitty | 8 |
            | 1 | Bruno | 7 |
            | 2 | Boomer | 8 | 
            | 3 | Boomer | 3 |
            | 4 | Fiesty | 3 |
            +----------------------+
            5 rows in set. 
        */
        String hr = "+-----------------------+";
        
        System.out.println();
        System.out.println(hr);
        //header
        System.out.printf("|%3s | %-10s |%4s |\n", "ID","NAME", "AGE");
        System.out.println(hr);
        
        //body
        int currentIndex = 0;
        for(Pet pet : db){ 
            System.out.printf("|%3d | %-10s |%4d |\n", currentIndex, pet.getName(), pet.getAge());
            currentIndex++;
            
        }
        
        System.out.println(hr);
        
        //footer
        System.out.println(db.size() + " rows in set.");    
        System.out.println();
    }
       
}
