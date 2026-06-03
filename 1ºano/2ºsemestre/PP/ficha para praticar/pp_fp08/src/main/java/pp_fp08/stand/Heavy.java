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
public class Heavy extends Vehicle{
    
    private int length;
    private int load;
    private Truck_type truck_type;
    
    
    public Heavy(int id, String vin, String brand, String model, int manufactering_date, Origin origin, int kms, Condition condition, float price, int length, int load){
        super( id, vin, brand, model, manufactering_date, origin, kms, condition, price);
        
        this.length = length;
        this.load = load;
                
    }
    
    public String toString(){
        return super.toString() + "Length: " + this.length + "Load: "  + this.load;
    }
    
}
