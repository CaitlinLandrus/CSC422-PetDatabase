/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.csc422.petdatabase;

/**
 * @author caitlin landrus
 * Course: CSC 422 Software Engineering
 * Course Week: 1
 * Assignment: Project Week 1 - PetDatabase Version Control
 * Date Created: 5/10/2020
 * Due Date: 5/17/2020
 */

import java.util.Scanner;

public class PetApp {
    static Scanner input = new Scanner(System.in);
    static Database db = new Database();
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
             String choice;
        
        System.out.println("Pet database program.\n");
        
        do{
            System.out.println("What would you like to do?\n" +
                "\t1) View all pets\n" +
                "\t2) Add more pets\n" +
                "\t3) Update an existing pet\n" +
                "\t4) Remove an existing pet\n" +
                "\t5) Search pets by name\n" +
                "\t6) Search pets by age\n" +
                "\t7) Exit program "
            );
            
            
            System.out.print("Your choice: ");
            choice = input.nextLine();
            
            switch(choice){
                case "1": 
                    //print all pets
                    db.print();
                    break;
                case "2": 
                    // add more pets
                    addPets();
                    break;
                case "3": 
                    // update existing pet
                    //TODO: update pet
                    break;
                case "4": 
                    // remove existing pet
                    //TODO: remove pet
                    break;
                case "5": 
                    // search pet by name
                    //TODO: search by name
                    break;
                case "6": 
                    // search pet by age
                    //TODO: search by age
                    break;
                case "7": 
                    //exit program
                    break;
                default:
                    System.out.println("Invalid input.");
                    
            }
            
            
            
        }while(!choice.equals("7"));
 
        //exited program
        System.out.println("\nGoodbye!");
        
    }//end of main


    private static void addPets(){
        int currentIndex = db.size(); //tracks the starting index
        int totalPetsAdded; 
        
        String choice;
        System.out.println();
        do{
            // Continue adding new pets until user types done
            System.out.print("Add pet (name, age): ");
            choice = input.nextLine();
            
            try{
                if(!choice.equalsIgnoreCase("done")){
                    String[] petData = choice.split(" ");
                    db.add(new Pet(petData[0], Integer.parseInt(petData[1])));
                }
            }
            catch(NumberFormatException e){
                System.out.println("Invalid Input. Expected an integer as the second argument.");
            }
            catch(ArrayIndexOutOfBoundsException e){
                System.out.println("Invalid Input. Expected an two input values.");
            }
  
        }while(!choice.equalsIgnoreCase("done"));
        
        totalPetsAdded = db.size() - currentIndex;
 
        System.out.println(totalPetsAdded + " pets added.");
        System.out.println();
    }
    
}
