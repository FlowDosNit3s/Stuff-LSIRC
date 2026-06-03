/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package pp_fp08.enums;

/**
 *
 * @author hugol
 */
public enum Condition {
    
    NEW,
    USED;
    
    public static String condition_to_string(Condition condition){
        
        switch(condition){
            case NEW:
                return "the vehicle is new";
            default:
                return "the vehicle is used";
        }
    }
    
}
