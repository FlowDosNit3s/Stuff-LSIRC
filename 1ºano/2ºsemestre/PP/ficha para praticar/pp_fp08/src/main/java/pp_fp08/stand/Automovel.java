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
public class Automovel extends Vehicle{
    
    private int occupants_number;
    private int doors_number;
    
    public Automovel(int id, String vin, String brand, String model, int manufactering_date, Origin origin, int kms, Condition condition, float price, int occupants_number, int doors_number){
        super( id, vin, brand, model, manufactering_date, origin, kms, condition, price);
        
        this.doors_number = doors_number;
        this.occupants_number = occupants_number;
        
    }
    
    public Automovel(int id, String vin, String brand, String model, int manufactering_date, Origin origin, int kms, Condition condition, float price, int occupants_number){
        super( id, vin, brand, model, manufactering_date, origin, kms, condition, price);
        
        this.doors_number = 3;
        this.occupants_number = occupants_number;
        
    }
    
    public String toString(){
        return super.toString() + "Doors number: " + this.doors_number + "Occupants number: " + this.occupants_number;
    }
    
}
