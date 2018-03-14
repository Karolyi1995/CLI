/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reflectionhandler;

import exceptions.InvalidInput;
import commandlineinterface.CLI;
import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;


/**
 *
 * @author Student
 */
public class ReflectionHandler {
    
    public static void visibilityMode(Class c,String visibility,Field[] fields){
        if("non-private".equals(visibility)){
            for (Field field : fields) {
                if ((field.getModifiers() & Modifier.PRIVATE) == 0) {
                    System.out.println(field);
                }
            }
        }else{
            System.out.println(CLI.class.getFields());
        }
    }
    
    public static void describe(Class c,AccessibleObject o,String visibility,String Objectname,Class... type){
        try{
            if(Field.class.equals(o.getClass()) || Constructor.class.equals(o.getClass()) || Method.class.equals(o.getClass())){
                throw new InvalidInput("Object isn't a type of field, constructor or method");
            }
        
            if(Field.class.equals(o.getClass())){
                System.out.println(c.getField(Objectname));
            }
            if(Method.class.equals(o.getClass())){
                System.out.println(c.getMethod(Objectname,type));
            }
        }catch(NoSuchFieldException e){
            System.err.println("This field doesn't exist in the class");
        }catch(NoSuchMethodException e){
            System.err.println("This method doesn't exist");
        } catch (InvalidInput e) {
            System.err.println(e.getMessage());
        }
    }
    
    public static void details(Class c,AccessibleObject o,String FieldOrMethodname,Class... params) {
        
        try{
            if(Field.class.equals(o.getClass()) || Method.class.equals(o.getClass())){
                throw new InvalidInput("Object isn't a type of field or method");
            }
        
            if(Field.class.equals(o.getClass())){
                System.out.println(c.getField(FieldOrMethodname));
            }
            if(Method.class.equals(o.getClass())){
                System.out.println(c.getMethod(FieldOrMethodname,params).getName() + " " + c.getMethod(FieldOrMethodname,params).getTypeParameters());
            }
        }catch(NoSuchFieldException e){
            System.err.println("This field doesn't exist in the class");
        }catch(NoSuchMethodException e){
            System.err.println("This method doesn't exist");
        } catch (InvalidInput e) {
            System.err.println(e.getMessage());
        }
    }
    
}
