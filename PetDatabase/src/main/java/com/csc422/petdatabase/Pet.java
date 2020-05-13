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
    
    public Pet(){
        
    }
    
    public Pet(String name, int age){
        this.name = name;
        this.age = age;
    }
     
    public void setAge(int age){
        this.age = age;
    }
    
    public void setName(String name){
        this.name = name;
    }
        
    public String getName(){
        return name;
    }
    
    public int getAge(){
        return age;
    }  
}
