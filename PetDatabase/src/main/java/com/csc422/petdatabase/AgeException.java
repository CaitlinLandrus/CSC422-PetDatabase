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
public class AgeException extends InputException{
    public AgeException(int age){
        super(age + " is not a valid age.");
    }
}
