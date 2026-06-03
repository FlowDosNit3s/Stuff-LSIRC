/*  
* Nome: Carlos Alberto Moreira Barbosa
* Número: 8230255
* Turma: LSIRC11T1
 */
package PP_ER_8230255.pickingManagement;

import com.estg.pickingManagement.Vehicle;
import com.estg.core.ContainerType;
import com.estg.core.exceptions.VehicleException;
import java.io.Serializable;

public class VehicleImpl implements Vehicle, Serializable {

    // The vehicle code
    private final String code;

    // The Vehicle capacities for every type
    private final Capacity[] capacities;

    // The vehicle status
    private boolean status;

    /**
     * Constuctor for vehicle, the vehicle status is defined true when the
     * vehicle are created
     *
     * @param code the vehicle code
     * @param capacities the vehicle capacities
     */
    public VehicleImpl(String code, Capacity[] capacities) {
        this.code = code;
        this.capacities = capacities;
        this.status = true;
    }

    /**
     * Constuctor for vehicle, the vehicle info is set to the given info, this
     * constructor is used to get a copy of the vehicle
     *
     * @param code the vehicle code
     * @param capacities the vehicle capacities
     * @param status the current status of vehicle
     */
    public VehicleImpl(String code, Capacity[] capacities, boolean status) {
        this.code = code;
        this.capacities = capacities;
        this.status = status;
    }

    /**
     * Getter for the vehicle code
     *
     * @return the vehicle code
     */
    @Override
    public String getCode() {
        return code;
    }

    /**
     * Get the number of container of a given type a Vehicle can transport
     *
     * @param ct the container type
     * @return the number of container of a given type a Vehicle can transport
     */
    @Override
    public double getCapacity(ContainerType ct) {
        for (Capacity capacity : capacities) {
            if (capacity.getType().equals(ct)) {
                return capacity.getCapacity();
            }
        }
        return 0;
    }

    /**
     * Sets the status of the vehicle. Throws a VehicleException if the new
     * status is the same as the current status.
     *
     * @param status the new status of the vehicle (true for active, false for
     * inactive)
     * @throws VehicleException if the new status is the same as the current
     * status
     */
    public void setStatus(boolean status) throws VehicleException {
        if (this.status == status) {
            throw new VehicleException("The Vehicle status is already " + status);
        }
        this.status = status;
    }

    /**
     * getter for the current vehicle status
     *
     * @return the vehicle status
     */
    public boolean getStatus() {
        return this.status;
    }

    /**
     * this function returns a copy of all the vehicle capacity
     * @return a copy of all vehicle capacities
     */
    public Capacity[] getCapacities() {
        Capacity[] temp = new Capacity[this.capacities.length];
        int counter = 0;

        for (Capacity capacity : this.capacities) {
            if (capacity != null) {
                try {
                    temp[counter] = (Capacity) capacity.clone();
                    counter++;
                } catch (CloneNotSupportedException ex) {
                    System.out.println("CAPACITY CAN'T BE CLONED");
                }

            }
        }
        
        Capacity[] resizedTemp = new Capacity[counter];
        System.arraycopy(temp, 0, resizedTemp, 0, counter);
        return resizedTemp;
    }

    /**
     * returns the vehicle info
     *
     * @return a string whith all the vehicle info
     */
    @Override
    public String toString() {
        String s = "VEHICLE:\n";
        s += "CODE: " + this.code + '\n';
        s += "STATUS: " + this.status + '\n';

        for (Capacity capacity : this.capacities) {
            if (capacity != null) {
                s += capacity.toString();
            }
        }
        return s;
    }

    /**
     * Compare if the given object is equal to this object
     *
     * @param obj object to compare
     * @return true if the object is the same or false if the object is not the
     * same
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof VehicleImpl)) {
            return false;
        }

        Vehicle vehicle = (VehicleImpl) obj;
        return this.code.equals(vehicle.getCode());
    }

}
