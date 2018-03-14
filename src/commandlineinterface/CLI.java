/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package commandlineinterface;


/**
 *
 * @author Iskola02
 */
public class CLI {

    public static int count = 0;
    private String string;
    private char c;
    
    public CLI(String string, char c) {
        this.string = string;
        this.c = c;
    }
    
    public void charsInString(){
        int counter = 0;
        for(int i = 0;i<string.length();i++){
            if(string.charAt(i)==c){
                count++;
            }
        }
        this.count = counter;
    }
    
}
