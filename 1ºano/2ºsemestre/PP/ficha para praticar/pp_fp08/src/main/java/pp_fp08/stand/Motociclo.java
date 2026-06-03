/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pp_fp08.stand;

import pp_fp08.enums.Condition;
import pp_fp08.enums.Origin;

/**
 *
 * @author hugol
 */
public class Motociclo extends Vehicle{
    
    private int cubic_capacity;
    private int wheel_size;
    
    public Motociclo(int id, String vin, String brand, String model, int manufactering_date, Origin origin, int kms, Condition condition, float price, int cubic_capacity, int wheel_size){
        super( id, vin, brand, model, manufactering_date, origin, kms, condition, price);
        
        this.cubic_capacity = cubic_capacity;
        this.wheel_size = wheel_size;
        
    }
    
    public String toString(){
        return super.toString() + "cubic capacity: " + this.cubic_capacity + "Wheel size" + this.wheel_size;
    }
    
}
