/*  
* Nome: Carlos Alberto Moreira Barbosa
* Número: 8230255
* Turma: LSIRC11T1
 */
package PP_ER_8230255.core;

import com.estg.core.ContainerType;
import java.io.Serializable;

public class ContainerTypeImpl implements ContainerType, Cloneable, Serializable{

    private final String type;

    /**
     * Constructor for Container Type
     *
     * @param type container type
     */
    public ContainerTypeImpl(String type) {
        this.type = type;
    }

    /**
     * Returns the container info
     *
     * @return the container type
     */
    @Override
    public String toString() {
        return type;
    }

    /**
     * compare if 2 object are equals, return true if the type is the same
     * otherwise return false
     *
     * @param obj object to compare
     * @return if it is equals or not
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof ContainerTypeImpl)) {
            return false;
        }

        ContainerTypeImpl containerType = (ContainerTypeImpl) obj;
        return this.type.equals(containerType.toString());
    }
    
    /**
     * creates a clone of a container type
     *
     * @return a copy of a container type
     * @throws CloneNotSupportedException if the object class does not support
     * the interface(Cloneable)
     */
    public Object clone() throws CloneNotSupportedException{
        return super.clone();
    }
}
