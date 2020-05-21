/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.csc422.petdatabase;

/**
 * @author caitlin landrus
 * Course: CSC 422 Software Engineering
 * Course Week: 2
 * Assignment: Project Week 2 - GitHub issue and project
 * Date Created: 5/18/2020
 * Due Date: 5/24/2020
 */

import java.util.Scanner;

public class PetApp {
     private final Scanner input = new Scanner(System.in);
     private final Database db; 
     private final InputValidator validator;
     private final FileManager fileManager = new FileManager();
    
    public PetApp(){
        //load a new database with the database file
        db = new Database(fileManager.loadFile());
        validator = new InputValidator();
        
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

        /*  ------------- WEEK 1 --------------*/       
        /*
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
        */
                
        /*  ------------- WEEK 2 --------------*/ 

        do{
            //Main Menu
            System.out.println("What would you like to do?\n" +
                "   1) View all pets\n" +
                "   2) Add more pets\n" +
                "   3) Remove an existing pet\n" +
                "   4) Exit program "
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

                case "4": 
                    //exit program
                    System.out.println("\nGoodbye!");
                    
                    //save the database to the file
                    fileManager.saveFile(db);
                    break;

                default:
                    System.out.println("\nInvalid input.\n");       
            }

        }while(!choice.equals("4"));   
    }

    
    /**
     * Allows the user to keep adding pets to the database until they type 'done'.
     * Throws an exception if invalid data is entered.
     */
    private void addPets(){
        int currentIndex = db.size(); //tracks the starting index in the database
        int totalPetsAdded; 
        boolean full = false;
        
        String choice;
        System.out.println();
        do{
            // Continue adding new pets until user types done
            System.out.print("Add pet (name, age): ");
            choice = input.nextLine();
            
            try{
                // add the entered pet to the database
                if(!choice.equalsIgnoreCase("done")){
                    //make sure the input is valid and there is space in the database
                    validator.validatePet(choice);
                    validator.validateDatabase();
                    
                    String[] petData = choice.split(" ");
                    db.add(new Pet(petData[0], Integer.parseInt(petData[1])));
                }
            }catch(DatabaseException e){
                //exit the loop if the database is full
                System.out.println(e.getMessage());
                full = true;
                break;
            }
            catch(InputException e){
                System.out.println(e.getMessage());
            }
          
        }while(!choice.equalsIgnoreCase("done"));
        

        //only print if the db isn't full
        if(!full){
            // print out the number of new pets that were added to the database
            totalPetsAdded = db.size() - currentIndex;
            System.out.println(totalPetsAdded + " pets added.");
            System.out.println();
        }
    }
    
    /**
     * Allows the user to update a selected pet with desired information.
     * Throws an exception if invalid data is entered.
     */
    private void updatePet(){
        //get the pet to update
        System.out.print("Enter the pet ID you want to udpate: ");
        String inputData = input.nextLine();
            
       
        try{
            //verify the user entered a valid ID 
            validator.validateID(inputData);
            int index = Integer.parseInt(inputData);
            
            //build a temporary pet object
            Pet pet = db.get(index);
            Pet tempPet = new Pet(pet.getName(), pet.getAge());


            //get the new pet details
            System.out.print("Enter new name and new age: ");
            String newPetData = input.nextLine();
            
            //check the new pet data for correct input
            validator.validatePet(newPetData);
  
            String[] petData = newPetData.split(" ");

            //updating the existing pet object
            pet.setAge(Integer.parseInt(petData[1]));                        
            pet.setName(petData[0]);                     

            //The pet was updated
            System.out.println(tempPet.toString() + " changed to " + pet.toString() +".\n");  
        }
        catch(InputException e){
            System.out.println(e.getMessage() +"\n");
        }
    }
    
    /**
     * Allows the user to remove the pet from the database by index.
     * Throws an exception if an integer is not entered.
     */
    private void removePet(){                       
        System.out.print("Enter the pet ID to remove: ");
        try{
            //verify input is valid
            String inputID = input.nextLine();
            validator.validateID(inputID);
            
            //convert to int if valid
            int indexPetToRemove = Integer.parseInt(inputID);
            
            //remove from database
            Pet removedPet = db.remove(indexPetToRemove);
            System.out.println(removedPet.toString() + " is removed.\n");
            
        }
        catch(InputException e){
            System.out.println(e.getMessage());
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
    
    
    private class InputValidator{
        /**
         * Validates that the id entered is an integer and valid within the database.
         * 
         * @param s
         * @throws InputException 
         */
        private void validateID(String s) throws InputException{
            
            //id is not an integer
            if(!isInteger(s)){
                    throw new ArgumentException(s);
             }
            
            int id = Integer.parseInt(s);
            
            //id is not in the database
            if(id < 0 || id >= db.size()){
                throw new IDException(id);
            }
        }
              
        /**
         * Validates the Pet data entered is valid to create a Pet object.
         * 
         * @param s
         * @throws InputException 
         */
        private void validatePet(String s) throws InputException{
            String[] arr = s.split(" ");
            
            // there are too many or too few arguments entered
            if(arr.length != 2){
                throw new ArgumentException(s);
            }
                
            // second argument (age) is not an integer value
            else if(!isInteger(arr[1])){
                throw new ArgumentException(s);
            }
            
           
            int age = Integer.parseInt(arr[1]);
        
            // age is out of range. valid range is 1 - 20
            if(age < 1 || age > 20){
                throw new AgeException(age);
            }        
        }
        
        
        public void validateDatabase() throws InputException{
            if(db.size() >= 5){
                throw new DatabaseException();
            }
        }
        
        
        /**
         * Checks that a string argument can be converted to an integer 
         * @param s string
         * @return true if it can be converted to int, false otherwise.
         */
        private boolean isInteger(String s){
            try{
               Integer.parseInt(s);
               return true;
            }
            catch(NumberFormatException e){
               return false; 
            }
        }   
        
    }
    
}
