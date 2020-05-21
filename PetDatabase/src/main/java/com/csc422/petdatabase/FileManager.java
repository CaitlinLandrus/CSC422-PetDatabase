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

import java.io.File;
import java.io.IOException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.LinkedList;
 

public class FileManager {
    private final File file = new File("database.txt");
    
    public FileManager(){
        createDirectory();
    }
    
    /**
     * Creates the file in the directory if it doesn't already exist
     */
    private void createDirectory(){
        if(!file.exists()){
            try {
                file.createNewFile();
            } catch (IOException ex) {
                System.out.println("Failed to create file " + file);
            }
        }
    }
    
    /**
     * Loads the serialized linked list of pets back into the system
     * @return the linked list
     */
    public LinkedList<Pet> loadFile(){
        //https://examples.javacodegeeks.com/core-java/io/file/how-to-read-an-object-from-file-in-java/
        //https://www.geeksforgeeks.org/serialization-in-java/
        
        LinkedList<Pet> list = new LinkedList<>();
        try{
            //if statement to avoid EOFException if file didn't exist previously
            if(file.length() != 0){
                //read in the serialized objects from the txt file
                FileInputStream fileIn = new FileInputStream(file);
                ObjectInputStream objectIn = new ObjectInputStream(fileIn);
                list = (LinkedList<Pet>) objectIn.readObject();
            }
            
        }
        catch(ClassNotFoundException  e){
            System.out.println("ClassNotFoundException caught");
        }
        catch(IOException e){
            System.out.println("IOException caught " + e);
        }
        
        return list;   
    }
    
    /**
     * Saves the database linked list as a serialized object to database.txt
     * @param db
     * @return true if successfully saved, else false
     */
    public boolean saveFile(Database db){
        //https://www.geeksforgeeks.org/serialization-in-java/
        
        //using linked list so it is in the format we want coming back in from the file
        LinkedList<Pet> listToSave = new LinkedList<>();
        
        //gather pet records from database
        for(Pet pet: db){
            listToSave.add(pet);
        }
        
        //Save to the file
        try{
            try (FileOutputStream fileOut = new FileOutputStream(file); ObjectOutputStream objectOut = new ObjectOutputStream(fileOut)) {
                objectOut.writeObject(listToSave);   
                return true;
            }
        }
        catch(IOException e){
            System.out.println("IOException caught");
        }     
        
        return false;        
    }
    
    
}
