
package reflectionhandler;

import exceptions.InvalidInput;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

public class ReflectionHandler {
    
    public static void visibilityMode(Class c,String visibility){
        printFields(c,visibility);
        printConstructor(c,visibility);
        printMethods(c,visibility); 
    }
    
    public static void describe(Class c,Class o,String visibility){
        if(!Field.class.equals(o.getClass()) && !Constructor.class.equals(o.getClass()) && !Method.class.equals(o.getClass())){
            printFields(c,visibility);
            printConstructor(c,visibility);
            printMethods(c,visibility);
        }
        if(Field.class.equals(o.getClass())){
            printFields(c,visibility);
        }   
        if(Constructor.class.equals(o.getClass())){
            printConstructor(c,visibility);
        }
        if(Method.class.equals(o.getClass())){
            printMethods(c,visibility);
        }
    }
    
    public static void details(Class c,Class o,String FieldOrMethodname,Class... params) {
        try{
            if(!Field.class.equals(o.getClass()) && !Method.class.equals(o.getClass())){
                throw new InvalidInput("Object isn't a type of field or method");
            }
            if(Field.class.equals(o.getClass())){
                Field f = c.getField(FieldOrMethodname);
                System.out.println(f.getAnnotations() + " " + f.getType() + " " + decodeModifiers(f.getModifiers()));
            }
            if(Method.class.equals(o.getClass())){
                Method m = c.getMethod(FieldOrMethodname, params);
                System.out.println(m.getAnnotations() + " " + m.getReturnType() + " " + m.getParameterTypes() + " " + decodeModifiers(m.getModifiers()));
            }
        }catch(NoSuchFieldException e){
            System.err.println("This field doesn't exist in the class");
        }catch(NoSuchMethodException e){
            System.err.println("This method doesn't exist");
        } catch (InvalidInput e) {
            System.err.println(e.getMessage());
        }
    }
    
    public static void invokeMethod(Object o,Method methodname,Object... methodparams){
        try{
            methodname.invoke(o, methodparams);
        } catch(IllegalAccessException | IllegalArgumentException | InvocationTargetException ex){
            System.err.println("Failed to invoke the method");
        }
    }
    
    private static String decodeModifiers(int mods){
        String Modifiers = "";
        if ((Modifier.PUBLIC&mods)==1){
            Modifiers += "public ";
        }
	if ((Modifier.PROTECTED&mods)==4){
            Modifiers += "protected ";
        }
	if ((Modifier.PRIVATE&mods)==2){
            Modifiers += "private ";
        }
	if ((Modifier.STATIC&mods)==8){
            Modifiers += "static ";
        }
	if ((Modifier.FINAL&mods)==16){
            Modifiers += "final ";
        }
	if ((Modifier.TRANSIENT&mods)==128){ 
            Modifiers += "transient ";
        }
	if ((Modifier.VOLATILE&mods)==64){
            Modifiers += "volatile ";
        }
	return Modifiers;
    }
    
    public static void printFields(Class c,String visibility){
        Field[] fields = c.getDeclaredFields();
        if("non-private".equals(visibility)){
            for(int i = 0;i<fields.length;i++){
                if((fields[i].getModifiers() & Modifier.PRIVATE) == 0){
                    System.out.println(decodeModifiers(fields[i].getModifiers()) + " " + fields[i].getType() + " " + fields[i].getName());
                }
            }
        }else{
            System.out.println(fields);
        }
    }
    
    public static void printConstructor(Class c,String visibility){
        Constructor[] constructors = c.getDeclaredConstructors();
        if("non-private".equals(visibility)){
            for(int i = 0;i<constructors.length;i++){
                Constructor co = constructors[i]; 
                if((co.getModifiers() & Modifier.PRIVATE) == 0){
                    System.out.println(decodeModifiers(co.getModifiers()) + " " + co.getName() + " " + co.getParameterTypes());
                }
            }
        }else{
            System.out.println(constructors);
        }
    }
    
    public static void printMethods(Class c,String visibility){
        Method[] methods = c.getDeclaredMethods();
        if("non-private".equals(visibility)){
            for(int i = 0;i<methods.length;i++){
                Method m = methods[i]; 
                if((m.getModifiers() & Modifier.PRIVATE) == 0){
                    System.out.println(decodeModifiers(m.getModifiers()) + " " + m.getReturnType() + " " + m.getName()
                    + " " + m.getParameterTypes() + " " + m.getAnnotations());
                }
            }
        }else{
            System.out.println(methods);
        }
    }
}
