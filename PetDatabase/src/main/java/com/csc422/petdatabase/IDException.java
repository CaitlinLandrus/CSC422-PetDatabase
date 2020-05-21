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
public class IDException extends InputException{
    public  IDException(int id){
       super("ID " + id + " does not exist.\n");
    }
}
