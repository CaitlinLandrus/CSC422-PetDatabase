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
                "   1) View all pets\n" +
                "   2) Add more pets\n" +
                "   3) Update an existing pet\n" +
                "   4) Remove an existing pet\n" +
                "   5) Search pets by name\n" +
                "   6) Search pets by age\n" +
                "   7) Exit program "
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
                    System.out.print("\nEnter a name to search: ");
                    String name = input.nextLine();
                    db.searchName(name);
                    break;
                    
                case "6": 
                    // search pet by age
                    System.out.print("\nEnter age to search: ");
                    try{
                        int age = Integer.parseInt(input.nextLine());
                        db.searchAge(age);
                    }catch(NumberFormatException e){
                        System.out.println("Invalid input. Expected an integer");
                    }
                    break;
                    
                case "7": 
                    //exit program
                    System.out.println("\nGoodbye!");
                    break;
                    
                default:
                    System.out.println("Invalid input.");       
            }
   
        }while(!choice.equals("7"));
  
    }//end of main

    
    /**
     * Allows the user to keep adding pets to the database until they type 'done'
     */
    private static void addPets(){
        int currentIndex = db.size(); //tracks the starting index in the database
        int totalPetsAdded; 
        
        String choice;
        System.out.println();
        do{
            // Continue adding new pets until user types done
            System.out.print("Add pet (name, age): ");
            choice = input.nextLine();
            
            try{
                // add the entered pet to the database
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
        
        // print out the number of new pets that were added to the database
        totalPetsAdded = db.size() - currentIndex;
        System.out.println(totalPetsAdded + " pets added.");
        System.out.println();
    }
    
}
