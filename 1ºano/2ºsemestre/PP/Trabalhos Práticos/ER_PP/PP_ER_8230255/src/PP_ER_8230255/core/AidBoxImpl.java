/*  
* Nome: Carlos Alberto Moreira Barbosa
* Número: 8230255
* Turma: LSIRC11T1
 */
package PP_ER_8230255.core;

import com.estg.core.*;
import com.estg.core.exceptions.AidBoxException;
import com.estg.core.exceptions.ContainerException;
import com.estg.core.exceptions.MeasurementException;
import java.io.Serializable;

public class AidBoxImpl implements AidBox, Serializable {

    // The Aid Box code
    private final String code;

    // The Aid Box zone
    private final String zone;

    // The Aid Box containers
    private Container[] containers;

    // The number of containers
    private int nContainers;

    // The Distance between the aidbox to another
    private DistanceToOtherDestination[] distancesToOtherDestinations;

    // The initial size of the array containers
    private final int INITIAL_CONTAINERS_SIZE = 5;


    /**
     * the constuctor for an aid box
     *
     * @param code the aid box code
     * @param zone the aid box zone
     */
    public AidBoxImpl(String code, String zone) {
        this.code = code;
        this.zone = zone;
        this.containers = new Container[INITIAL_CONTAINERS_SIZE];
        this.nContainers = 0;
    }

    /**
     * Getter Aid Box code
     *
     * @return Aid Box code
     */
    @Override
    public String getCode() {
        return this.code;
    }

    /**
     * Getter for zone.
     *
     * @return Aid Box zone
     */
    @Override
    public String getZone() {
        return this.zone;
    }

    /**
     * Getter for Distance between current Aid Box and parameter Aid Box.
     *
     * @param aidbox to the target.
     * @return the distance.
     * @throws AidBoxException if aid box does not exist.
     */
    @Override
    public double getDistance(AidBox aidbox) throws AidBoxException {
        for (DistanceToOtherDestination dst : this.distancesToOtherDestinations) {
            if (dst.getName().equals(aidbox.getCode())) {
                return dst.getDistance();
            }
        }
        throw new AidBoxException("The Aid Box does not exist");
    }

    /**
     * Getter for Duration between current Aid Box and parameter Aid Box.
     *
     * @param aidbox to the target.
     * @return the duration.
     * @throws AidBoxException if aid box does not exist.
     */
    @Override
    public double getDuration(AidBox aidbox) throws AidBoxException {
        for (DistanceToOtherDestination dst : this.distancesToOtherDestinations) {
            if (dst.getName().equals(aidbox.getCode())) {
                return dst.getDuration();
            }
        }
        throw new AidBoxException("The Aid Box does not exist");
    }

    /**
     * Adds a new container to the aid box
     *
     * @param cntnr the container
     * @return true if the container was inserted in the collection storing all
     * containers and false if the container already exists
     * @throws ContainerException container is null and if the AidBox already
     * have a container from a given type
     */
    @Override
    public boolean addContainer(Container cntnr) throws ContainerException {
        incContainersArray();
        // if the container is null
        if (cntnr == null) {
            throw new ContainerException("The Container can't be null");
        }

        // if the container already exists
        if (findContainer(cntnr) != -1) {
            return false;
        }

        // if the aidbox already has a container with that type
        if (getContainer(cntnr.getType()) != null) {
            throw new ContainerException("The Container Type Already Exists");
        }

        this.containers[this.nContainers++] = cntnr;
        return true;
    }

    /**
     * Considering a container type, returns the correspondent container
     *
     * @param ct item type
     * @return the container or null if the container doesn't exist
     */
    @Override
    public Container getContainer(ContainerType ct) {
        for (Container container : this.containers) {
            if (container != null) {
                if (container.getType().equals(ct)) {
                    return this.getContainerClone(container);
                }
            }
        }
        return null;
    }

    /**
     * Return a copy of a existing containers
     *
     * @return a copy of the existing containers
     */
    @Override
    public Container[] getContainers() {
        Container[] temp = new Container[this.nContainers];
        int counter = 0;

        for (int i = 0; i < this.nContainers; i++) {
            if (this.containers[i] != null) {
                temp[counter++] = this.getContainerClone(this.containers[i]);
            }
        }
        return temp;
    }

    /**
     * Removes a container
     *
     * @param cntnr containers to remove
     * @throws AidBoxException if the container does not exists or if the
     * container is null
     */
    @Override
    public void removeContainer(Container cntnr) throws AidBoxException {
        // if the container is null
        if (cntnr == null) {
            throw new AidBoxException("The Container can't be null");
        }

        // find the given container
        int index = findContainer(cntnr);
        // if the conatiner dont exists
        if (index == -1) {
            throw new AidBoxException("The Container does not exist");
        }

        this.containers[index] = this.containers[this.nContainers - 1];
        this.containers[--this.nContainers] = null;
    }

    /**
     * Add a measurement to the given container
     *
     * @param cont the container that i want to add a measurement
     * @param meas measurement to add
     * @return true if the measurement was added or false if the container does
     * not exists
     * @throws MeasurementException if the measurement value is lesser than 0 or
     * higher to the capacity, or if the container already has measurements with
     * the same date or more recent dates
     */
    public boolean addMeasurement(Container cont, Measurement meas) throws MeasurementException {
        // throw an error if the measurement is invalid
        if (meas.getValue() <= 0 || meas.getValue() > cont.getCapacity()) {
            throw new MeasurementException("The measurement value cant be lesser than 0 or higher to the capacity");
        }

        // finds the container
        int index = findContainer(cont);
        // if the container exists
        if (index != -1) {
            /*
            tries to add the measurement if the measurement is impossible to 
            add the function conatiner.addMeasurement throws an error or if 
            container already has a equal measurement then this function throws 
            an error
             */
            if (!this.containers[index].addMeasurement(meas)) {
                throw new MeasurementException("The container already has this measurement");
            }
            return true;
        } else {
            return false;
        }
    }

    /**
     * Gets a copy of an given container
     *
     * @param container container to copy
     * @return a copy of an container
     */
    public Container getContainerClone(Container container) {
        try {
            return (Container) ((ContainerImpl) container).clone();
        } catch (CloneNotSupportedException ex) {
            System.out.println("ERROR WHILE CLONING CONTAINERS");
            return null;
        }
    }

    /**
     * Find if a container exists in the array
     *
     * @param cntnr a container
     * @return the index of the container or -1 if the container dont exist
     */
    private int findContainer(Container cntnr) {
        for (int i = 0; i < this.nContainers; i++) {
            if (this.containers[i].equals(cntnr)) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Duplicate the array size by 2 if the array of the containers are full
     */
    private void incContainersArray() {
        if (this.nContainers == this.containers.length) {
            Container[] temp = new Container[this.nContainers * 2];
            if (this.nContainers == 0) {
                temp = new Container[INITIAL_CONTAINERS_SIZE];
            }
            System.arraycopy(this.containers, 0, temp, 0, this.nContainers);
            this.containers = temp;
        }
    }

    /**
     * Sets the distances to other destinations. This method updates the
     * distances to various other destinations.
     *
     * @param distancesToOtherDestinations an array of
     * DistanceToOtherDestination objects representing the distances to other
     * destinations
     */
    public void setDestinations(DistanceToOtherDestination[] distancesToOtherDestinations) {
        DistanceToOtherDestination[] temp = new DistanceToOtherDestination[distancesToOtherDestinations.length];
        int counter = 0;

        for (DistanceToOtherDestination distanceToOtherDestination : distancesToOtherDestinations) {
            if (distanceToOtherDestination != null) {
                temp[counter++] = distanceToOtherDestination;
            }
        }

        this.distancesToOtherDestinations = new DistanceToOtherDestination[counter];
        System.arraycopy(temp, 0, this.distancesToOtherDestinations, 0, counter);
    }

    /**
     * Getter for Distance between current Aid Box and the base.
     *
     * @param inst to the target.
     * @return the distance.
     * @throws AidBoxException if aid box does not exist.
     */
    public double getDistance(Institution inst) throws AidBoxException {
        for (DistanceToOtherDestination dst : this.distancesToOtherDestinations) {
            if (dst.getName().equals("Base")) {
                return dst.getDistance();
            }
        }
        throw new AidBoxException("The instituion does not exist, wait WHAT?");
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
        if (!(obj instanceof AidBoxImpl)) {
            return false;
        }

        AidBoxImpl aidBox = (AidBoxImpl) obj;
        return this.getCode().equals(aidBox.getCode());
    }

    /**
     * returns a string with the aidbox info
     *
     * @return a string with all the aidbox info
     */
    @Override
    public String toString() {
        String s = "AID BOX: \n----\n";

        s += "Code: " + this.code + "\n";
        s += "Zone: " + this.zone + "\n\n";
        for (Container container : this.containers) {
            if (container != null) {
                s += container.toString() + "\n";
            }

        }
        return s;
    }

    /**
     * creates a clone of a container
     *
     * @return a copy of a container
     * @throws CloneNotSupportedException if the object class does not support
     * the interface(Cloneable)
     */
    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    /**
     * swap the given container to other container
     *
     * @param cont1 container to replace
     * @param cont2 container to replace with
     */
    void swapContainers(Container cont1, Container cont2) {
        for (Container container : this.containers) {
            if (container != null) {
                if (container.equals(cont1)) {
                    container = cont2;
                }
            }

        }
    }
}
