/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package pp_fp08.enums;

/**
 *
 * @author hugol
 */
public enum Origin {
    
        NATIONAL,
        IMPORTED;
    
    public static String origin_to_string(Origin origin){
        
        switch(origin){
            case NATIONAL:
                return "The vehicle is national";
            default:
                return "The vehicle is imported";
        }
        
    }
    
}
