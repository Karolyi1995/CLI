
package main;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Scanner;
import reflectionhandler.ReflectionHandler;

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
                case 2:describe();break;
                case 3:details();break;
                case 4:invokeMethod();break;
                case 5:System.exit(0);
            }
       }while(true);
    }
    
    private static void listing(){
        ReflectionHandler.visibilityMode(inputClass(),inputVisibility());
    }
    
    private static void describe(){
        ReflectionHandler.describe(inputClass(), inputAccessibleObject(), inputVisibility());
    }
    
    private static void details(){
        Class c = inputClass();
        Scanner sc = new Scanner(System.in);
        System.out.println("Method or Field details?");
        String input = sc.nextLine();
        if("Field".equals(input)){
            System.out.println("Fieldname: ");
            String name = sc.nextLine();
            ReflectionHandler.details(c,Field.class,name);
        }else if("Method".equals(input)){
            System.out.println("Methodname: ");
            String name = sc.nextLine();
            ReflectionHandler.details(c,Method.class,name);
        }else{
            System.err.println("Invalid input");
        }
    }
    
    private static void invokeMethod(){
        Scanner sc = new Scanner(System.in);
        Object o = null;
        Class c = inputClass();
        
        System.out.println("Write the constructor parameters: ");
        Class[] constparams = paramsForName(sc.nextLine().split(" "));
        
        System.out.println("Object to be created: ");
        String[] newinstance = sc.nextLine().split(" ");
        try {
            Constructor con = c.getConstructor(constparams);
            o = con.newInstance(newinstance);
            
        } catch (NoSuchMethodException ex) {
            System.err.println("No such constructor is found");
        } catch (ReflectiveOperationException ex){
            System.err.println("Failed to invoke constructor");
        }
        
        System.out.println("Write the method name to invoke: ");
        String methodname = sc.nextLine();
        System.out.println("Write the method parameters: ");
        Class[] methodparams = paramsForName(sc.nextLine().split(" "));
        
        try {
            ReflectionHandler.invokeMethod(o, Method.class.getMethod(methodname, methodparams));
        } catch (NoSuchMethodException ex) {
            System.err.println("No such method is found");
        } 
    }
    
    private static Class inputClass(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Write the class");
        Class classname = null;
        try {
            classname = Class.forName(sc.nextLine());
        } catch (ClassNotFoundException ex) {
            System.err.println("Class not found");
        }
        return classname;
    }
    
    private static String inputVisibility(){
        System.out.println("Visibility: non-private/all (Default: non-private will be given if visibility is invalid)");
        Scanner sc = new Scanner(System.in);
        if("all".equals(sc.nextLine())){
            return "all";
        }
        return "non-private";
    }
    
    private static Class inputAccessibleObject(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Describe: Field/Constructor/Method  Default: Everything");
        String s = sc.nextLine();
        if("Field".equals(s)){
            return Field.class;
        }
        if("Constructor".equals(s)){
            return Constructor.class;
        }
        if("Method".equals(s)){
            return Method.class;
        }
        return Object.class;
    }
     private static Class[] paramsForName(String[] params){
         Class[] parameters = null;
         try {
            for(int i = 0;i<params.length;i++){
                parameters[i] = Class.forName(params[i]);
            } 
         }catch (ClassNotFoundException ex) {
             System.err.println("Class not found.");
         }
         return parameters;
     }
    
}
