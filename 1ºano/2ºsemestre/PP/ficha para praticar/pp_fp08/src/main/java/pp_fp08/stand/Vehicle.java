/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pp_fp08.stand;

import pp_fp08.enums.Origin;
import pp_fp08.enums.Condition;

/**
 *
 * @author hugol
 */
public abstract class Vehicle {
    
    private static int nextid = 1;
    
    private int id;
    private String vin;
    private String brand;
    private String model;
    private int manufactering_date;
    private Origin origin;
    private int kms;
    private Condition condition;
    private float price;
    
    
    public Vehicle(int id, String vin, String brand, String model, int manufactering_date, Origin origin, int kms, Condition condition, float price){
        
        this.id = nextid++;
        this.vin = vin;
        this.brand = brand;
        this.model = model;
        this.manufactering_date = manufactering_date;
        this.kms = kms;
        this.price = price;
        
    }
    
    
    @Override
    public String toString(){
        return "id: " + this.id + "Vin: " + this.vin + "brand: " + this.brand + "Model: " + this.model + "Manufactering date: " + this.manufactering_date + 
                "Origin: " + Origin.origin_to_string(origin) + "kms: " + this.kms + "Condition: " + Condition.condition_to_string(condition) + "Price: " + this.price;
    }
    
    
    
    
}
