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

import java.util.Iterator;
import java.util.LinkedList;

public class Database implements Iterable<Pet>{
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
    
    public Database(LinkedList<Pet> fileList){
        db = fileList;
    }
    
    /**
     * adds the pet to the database
     * 
     * @param p is a Pet object
     */
    public void add(Pet p){ 
        //add the pet to the array
        db.add(p);
    }
    
    /**
     * 
     * @return the size of the database
     */
    public int size(){
        return db.size();
    }
    
    /**
     * 
     * @param index of the pet in the list
     * @return the Pet at given index
     */
    public Pet get(int index){
       return db.get(index);
    }
    
    public Pet remove(int index){
        return db.remove(index);
    }

    
    
    static String hr = "+-----------------------+";
    
    /**
     * prints the header of the table
     */
    private void  printHeader(){
        System.out.println();
        System.out.println(hr);
        //header
        System.out.printf("|%3s | %-10s |%4s |\n", "ID","NAME", "AGE");
        System.out.println(hr);
        
    }
    
    /**
     * prints the body of the table
     * @param number the number of items that were in the table
     */
    private void printFooter(int number){
        System.out.println(hr);
        System.out.println(number + " rows in set.");    
        System.out.println();
    }
    
    /**
     * prints the individual row data for the pet
     * 
     * @param index integer value in the database
     * @param name of the pet
     * @param age of the pet
     */
    private void printRow(int index,String name, int age){
         System.out.printf("|%3d | %-10s |%4d |\n", index, name, age);
    }
    
    
    /**
     * Prints the entire database
     */
    public void print(){
        /*
            +----------------------+
            | ID | NAME      | AGE | 
            +----------------------+
            | 0 | Kitty      |   8 |
            | 1 | Bruno      |   7 |
            | 2 | Boomer     |   8 | 
            | 3 | Boomer     |   3 |
            | 4 | Fiesty     |   3 |
            +----------------------+
            5 rows in set. 
        */
        printHeader();
        
        //print body
        int currentIndex = 0;
        for(Pet pet : db){ 
            printRow(currentIndex, pet.getName(), pet.getAge());
            currentIndex++;  
        }
        
        printFooter(size());
    }
    
    
    /**
     * prints out all pets with the input name
     * 
     * @param name is a string 
     */
    public void search(String name){
        int currentIndex = 0;
        int found = 0;
        
        printHeader();
        
        //print body if there is a match
        for(Pet pet : db){ 
            if(pet.getName().equalsIgnoreCase(name)){
                printRow(currentIndex, pet.getName(), pet.getAge());
                found++;
            }
            currentIndex++;  
        }
        
        printFooter(found); 
    }
    
    
    /**
     * prints out the all pets with the input age
     * 
     * @param age is in an integer value
     */
    public void search(int age){
        int currentIndex = 0;
        int found = 0;
        
        printHeader();
                       
        //print body if there is a match
        for(Pet pet : db){ 
            if(pet.getAge()== age){
                 printRow(currentIndex, pet.getName(), pet.getAge());
                 found++;
            }
            currentIndex++;  
        }
        printFooter(found);   
    }
    
    /**
     * Implementing Linked List iterator for saving the database to a file
     * @return 
     */
    @Override
    public Iterator<Pet> iterator(){
        return  db.iterator();
    }
     
}
