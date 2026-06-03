/*  
* Nome: Carlos Alberto Moreira Barbosa
* Número: 8230255
* Turma: LSIRC11T1
 */
package PP_ER_8230255.pickingManagement;

import com.estg.core.ContainerType;
import java.io.Serializable;

public class Capacity implements Cloneable, Serializable {

    // The type of container
    private final ContainerType type;

    // The number of container that the vehicle can transport
    private final double capacity;

    /**
     * The constructor for the Capacity
     *
     * @param type The container type
     * @param capacity the number of container that the vehicle can transport
     */
    public Capacity(ContainerType type,double capacity) {
        this.type = type;
        this.capacity = capacity;
    }

    /**
     * Getter for type
     *
     * @return the container type
     */
    public ContainerType getType() {
        return type;
    }

    /**
     * Getter for the capacity
     *
     * @return the capacity
     */
    public double getCapacity() {
        return capacity;
    }

    /**
     * return a string with the capacity info
     *
     * @return a string with all the capacity info
     */
    @Override
    public String toString() {
        String s = "CAPACITY:\n";
        s += "TYPE: " + this.type + "\n";
        s += "VALUE: " + this.capacity + "\n";

        return s;
    }
    
    /**
     * creates a clone of a capacity
     *
     * @return a copy of a capacity
     * @throws CloneNotSupportedException if the object class does not support
     * the interface(Cloneable)
     */
    public Object clone() throws CloneNotSupportedException{
        return super.clone();
    }
}
