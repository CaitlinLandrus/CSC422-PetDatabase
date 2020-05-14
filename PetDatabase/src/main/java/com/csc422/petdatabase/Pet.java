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
public class Pet {

    private String name = "";
    private int age = 0;
    
    /** No Arg Constructor **/
    public Pet(){
        
    }
    
    /**
     *  Constructor 
     * @param name of pet
     * @param age  of pet
     */
    public Pet(String name, int age){
        this.name = name;
        this.age = age;
    }
     
    /** 
     * Sets the pet's age
     * @param age 
     */
    public void setAge(int age){
        this.age = age;
    }
    
    /**
     * Sets the pet's name
     * @param name 
     */
    public void setName(String name){
        this.name = name;
    }
        
    /**
     * 
     * @return the pet's name
     */
    public String getName(){
        return name;
    }
    
    /**
     * 
     * @return the pet's age
     */
    public int getAge(){
        return age;
    }  
    
    /**
     *  
     * @return a string representation of the pet.
     */
    @Override
    public String toString(){
        return name + " " + age;
    }
}
