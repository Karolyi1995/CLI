/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.util.Scanner;
import reflectionhandler.ReflectionHandler;

/**
 *
 * @author Iskola02
 */
public class Main {
    
    public static void main(String[] args) {
       Scanner sc = new Scanner(System.in);
       do{
            System.out.println("1: List non-private or all members in a class.");
            System.out.println("2: Describe a method, field or constructor");
            System.out.println("3: Show Details of a method or a field");
            System.out.println("4: Invoke a method");
            System.out.println("5: Exit");
            System.out.println("\nWrite the number of the command");
            
            switch(Integer.parseInt(sc.nextLine())){
                case 1:listing();break;
                case 2:
                case 3:
                case 4:
                case 5:    
            }
       }while(true);
    }
    
    @SuppressWarnings("null")
    private static void listing(){
        Scanner sc = new Scanner(System.in);
        Class classname = null;
        
        
        System.out.println("Write the class.");
        try {
            classname = Class.forName(sc.nextLine());
        } catch (ClassNotFoundException ex) {
            System.err.println("Class not found");
        }
        ReflectionHandler.visibilityMode(classname,visibility(),classname.getFields());
    }
    
    private static void describe(){
        
    }
    
    private static String visibility(){
        System.out.println("Visibility: non-private/all (Default: non-private will be given if visibility is invalid)");
        Scanner sc = new Scanner(System.in) ;
        if("all".equals(sc.nextLine())){
            return "all";
        }else{
            return "non-private";
        }
    }
    
}
