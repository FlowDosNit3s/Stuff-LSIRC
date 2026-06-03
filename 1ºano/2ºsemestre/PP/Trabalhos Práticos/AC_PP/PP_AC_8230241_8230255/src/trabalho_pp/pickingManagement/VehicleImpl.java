/*
Nome: Jose Pedro Vieira Barbosa
Número: 8230241
Turma: LEIT3
*
Nome: Carlos Alberto Moreira Barbosa
Número: 8230255
Turma: LSIRCT2
*/

package trabalho_pp.pickingManagement;

import com.estg.core.ItemType;
import com.estg.pickingManagement.Vehicle;
import com.estg.core.exceptions.VehicleException;

/** Class responsible for the Vehicle implementation */
public abstract class VehicleImpl implements Vehicle {
    
    /** The vehicles's item type*/
    private ItemType itemType;
    
    /** The vehicles's max capacity*/
    private double maxCapacity;
    
    /** The vehicle's current status */
    private boolean status;
    
    /**
     * Constructor for the Vehicle
     * @param itemType - item type of the Vehicle
     * @param maxCapacity - max capacity of the Vehicle
     * The vehicle status is set to true
     */
    public VehicleImpl(ItemType itemType, double maxCapacity) throws VehicleException {
        if (maxCapacity <= 0) {
            throw new VehicleException("Max capacity needs to be positive!");
        }

        this.itemType = itemType;
        this.maxCapacity = maxCapacity;
        this.status = true;
    }
    
    /**
     * Gets the item type of the Vehicle
     * @return ItemType
     */
    @Override
    public ItemType getSupplyType(){
        return this.itemType;
    }
    
    /**
     * Gets max capacity of the Vehicle
     * @return double
     */
    @Override
    public double getMaxCapacity(){
        return this.maxCapacity;
    }
    
    /**
     * Setter for the status of the Vehicle
     * @param status is the status that the vehicle will have
     */
    public void setStatus(boolean status){
        this.status = status;
    }
    
    /**
     * Getter for the status of the Vehicle
     * @return boolean
     */
    public boolean getStatus(){
        return this.status;
    }

    /**
     * Create a detailed string with all the vehicle information.
     * @return String
     */
    public String toString(){
        String s = "Item Type: " + getSupplyType() + '\n';
        s += "Max Capacity: " + getMaxCapacity() + '\n';

        return s;
    }
}