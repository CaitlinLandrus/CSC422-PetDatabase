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
     private final Scanner input = new Scanner(System.in);
     private final Database db; 
    
    public PetApp(){
        db = new Database();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //start the application
        PetApp app = new PetApp();
        app.start();
  
    }//end of main method
    
    /**
     * Runs the application until the user chooses to exit
     */
    private void start(){
        String choice;
        System.out.println("Pet database program.\n");
        
        do{
            //Main Menu
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
                    if(db.size() > 0){
                        //print current list of pets
                        db.print();
                        updatePet();
                    }
                    else{
                        System.out.println("\nThere are no pets in the database to update.\n");
                    }
                    break;
                    
                case "4": 
                    // remove existing pet
                    if(db.size() > 0){
                        //print current list of pets
                        db.print();
                        removePet();  
                    }
                    else{
                        System.out.println("\nThere are no pets in the database to remove.\n");
                    }
                    break;
                    
                case "5": 
                    // search pet by name
                    searchByName();
                    break;
                    
                case "6": 
                    // search pet by age
                    searchByAge();
                    break;
                    
                case "7": 
                    //exit program
                    System.out.println("\nGoodbye!");
                    break;
                    
                default:
                    System.out.println("\nInvalid input.\n");       
            }
   
        }while(!choice.equals("7"));
        
    }

    
    /**
     * Allows the user to keep adding pets to the database until they type 'done'.
     * Throws an exception if invalid data is entered.
     */
    private void addPets(){
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
                System.out.println("Invalid Input. Expected two input values.");
            }
  
        }while(!choice.equalsIgnoreCase("done"));
        
        // print out the number of new pets that were added to the database
        totalPetsAdded = db.size() - currentIndex;
        System.out.println(totalPetsAdded + " pets added.");
        System.out.println();
    }
    
    /**
     * Allows the user to update a selected pet with desired information.
     * Throws an exception if invalid data is entered.
     */
    private void updatePet(){
        
        try{
            //get the pet to update
            System.out.print("Enter the pet ID you want to udpate: ");
            int index = Integer.parseInt(input.nextLine());
            
            if(index >= 0 && index < db.size()){
                Pet pet = db.get(index);
                Pet tempPet = new Pet(pet.getName(), pet.getAge());


                //get the new pet details
                System.out.print("Enter new name and new age: ");
                String newPetData = input.nextLine();
                String[] petData = newPetData.split(" ");

                //updating age first because that is the one that would have errors
                pet.setAge(Integer.parseInt(petData[1]));                        
                pet.setName(petData[0]);                     

                //The pet was updated
                System.out.println(tempPet.toString() + " changed to " + pet.toString() +".\n");
            }
            else{
                System.out.println("Invlid ID entered.\n");
            }
         
        }
        catch(NumberFormatException e){
            System.out.println("Invalid input. Expected an integer\n");
        }
        catch(ArrayIndexOutOfBoundsException e){
            //happens on the array[]
            System.out.println("Invalid Input. Expected two input values.\n");
        }
    }
    
    /**
     * Allows the user to remove the pet from the database by index.
     * Throws an exception if an integer is not entered.
     */
    private void removePet(){                       
        System.out.print("Enter the pet ID to remove: ");
        try{
            int indexPetToRemove = Integer.parseInt(input.nextLine());
            if(indexPetToRemove >= 0 && indexPetToRemove < db.size()){
                Pet removedPet = db.remove(indexPetToRemove);
                System.out.println(removedPet.toString() + " is removed.\n");
            }
            else{
                System.out.println("Invlid ID entered.\n");
            }
        }
        catch(NumberFormatException e){
            System.out.println("Invlid input. Expected an integer.\n");
        }

        
    }
    
    /**
     * Allows the user to search for a pet by their age
     * Throws an exception if an integer is not entered.
     */
    private void searchByAge(){
        System.out.print("\nEnter age to search: ");
        try{
            int age = Integer.parseInt(input.nextLine());
            db.search(age);
        }catch(NumberFormatException e){
            System.out.println("Invalid input. Expected an integer");
        }
    }
    
    /**
     * Allows the user to search for a pet by their name. 
     */
    private void searchByName(){
        System.out.print("\nEnter a name to search: ");
        String name = input.nextLine();
        db.search(name); 
    }
    
}
