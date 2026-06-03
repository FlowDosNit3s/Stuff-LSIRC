/*  
* Nome: Carlos Alberto Moreira Barbosa
* Número: 8230255
* Turma: LSIRC11T1
 */
package PP_ER_8230255.core;

import com.estg.core.*;
import com.estg.core.exceptions.MeasurementException;
import java.io.Serializable;
import java.time.LocalDate;

public class ContainerImpl implements Container, Serializable {

    // The Container Code
    private final String code;

    // The Container Capacity
    private final double capacity;

    // The Container Type
    private final ContainerType containerType;

    // The Container Measurements
    private Measurement[] measurements;

    // The number of measurements
    private int nMeasurements;

    // The initial size of the array measurements
    private static final int INITIAL_MEASUREMENTS_SIZE = 5;

    /**
     * The constructor for container
     *
     * @param code the container code
     * @param capacity the container capacity
     * @param containerType the container type
     */
    public ContainerImpl(String code, double capacity, ContainerType containerType) {
        this.code = code;
        this.capacity = capacity;
        this.containerType = containerType;
        this.measurements = new Measurement[INITIAL_MEASUREMENTS_SIZE];
        this.nMeasurements = 0;
    }

    /**
     * Getter for container code.
     *
     * @return The container code
     */
    @Override
    public String getCode() {
        return this.code;
    }

    /**
     * Getter for container capacity (Kg)
     *
     * @return the container capacity (Kg)
     */
    @Override
    public double getCapacity() {
        return this.capacity;
    }

    /**
     * Getter for Container type
     *
     * @return the Container type
     */
    @Override
    public ContainerType getType() {
        return this.containerType;
    }

    /**
     * Return a deep copy of a existing measurements
     *
     * @return a deep copy of a existing measurements
     */
    @Override
    public Measurement[] getMeasurements() {
        Measurement[] temp = new Measurement[this.nMeasurements];
        int counter = 0;

        for (int i = 0; i < this.nMeasurements; i++) {
            temp[counter++] = getMeasurementClone(this.measurements[i]);
        }
        return temp;
    }

    /**
     * Return a deep copy of an existing measurements for a given date
     *
     * @param ld date to search for measurements
     * @return a deep copy of an existing measurements for a given date
     */
    @Override
    public Measurement[] getMeasurements(LocalDate ld) {
        Measurement[] temp = new Measurement[this.nMeasurements];
        int counter = 0;

        for (int i = 0; i < this.nMeasurements; i++) {
            if (this.measurements[i].getDate().toLocalDate().isEqual(ld)) {
                temp[counter++] = getMeasurementClone(this.measurements[i]);
            }
        }

        if (counter == 0) {
            return null;
        }
        return temp;
    }

    /**
     * Adds a new measurement to the container
     *
     * @param msrmnt measurement to be added to the container
     *
     * @return true if the measurement was inserted in the collection storing
     * all measurements and false if the measurement already exists for a given
     * date
     *
     * @throws MeasurementException if the measurement is null, if the value is
     * lesser than 0, if the date is before the existing last measurement date,
     * if the for a given date the measurement already exists but the values are
     * different
     */
    @Override
    public boolean addMeasurement(Measurement msrmnt) throws MeasurementException {
        incMeasurementsArray();

        // throw an error if the measurent is null or if the value is invalid
        if (msrmnt == null || msrmnt.getValue() < 0) {
            throw new MeasurementException("The Measurement can't be null");
        }

        if (msrmnt.getValue() > this.capacity) {
            throw new MeasurementException("The Measurement can't be higher to the capacity");
        }

        // if the container already has measurements
        if (this.nMeasurements != 0) {
            // get the last measurement
            Measurement lastMeasurement = this.getLastMeasuremnt();

            // throw an error if the container already has a more recent measurement
            if (lastMeasurement.getDate().isAfter(msrmnt.getDate())) {
                throw new MeasurementException("There is already a more recent measurement");
            }

            // throw an error if for a given date the value is diferent else return false
            if (lastMeasurement.getDate().isEqual(msrmnt.getDate())) {
                if (lastMeasurement.getValue() != msrmnt.getValue()) {
                    throw new MeasurementException("There is already a meassurement with that date but with a diferent value");
                } else {
                    return false;
                }
            }
        }

        this.measurements[this.nMeasurements++] = msrmnt;
        return true;
    }

    /**
     * Getter for the last container measurement
     *
     * @return the last measurement
     * @throws MeasurementException if the container dont have measurements
     */
    public Measurement getLastMeasuremnt() throws MeasurementException {
        if (this.nMeasurements == 0) {
            throw new MeasurementException("The container does not have measurements");
        }

        return this.measurements[this.nMeasurements - 1];
    }

    /**
     * Duplicate the array size by 2 if the array of the measurements are full
     */
    private void incMeasurementsArray() {
        if (this.nMeasurements == this.measurements.length) {
            Measurement[] temp = new Measurement[this.nMeasurements * 2];
            if (this.nMeasurements == 0) {
                temp = new Measurement[INITIAL_MEASUREMENTS_SIZE];
            }
            System.arraycopy(this.measurements, 0, temp, 0, this.nMeasurements);
            this.measurements = temp;
        }
    }

    /**
     * gets a copy of an given measurement
     *
     * @param measurement the measurement to copy
     * @return a copy of an measurement
     */
    private Measurement getMeasurementClone(Measurement measurement) {
        try {
            return (Measurement) ((MeasurementImpl) measurement).clone();
        } catch (CloneNotSupportedException ex) {
            System.out.println("ERROR WHILE CLONING MEASUREMENTS");
            return null;
        }
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
        if (!(obj instanceof ContainerImpl)) {
            return false;
        }

        Container container = (ContainerImpl) obj;
        return this.getCode().equals(container.getCode());
    }

    /**
     * returns the container info
     *
     * @return a string with all the container info
     */
    @Override
    public String toString() {
        String s = "CONTAINER:\n";
        s += "CODE: " + this.code + '\n';
        s += "CAPACITY: " + this.capacity + '\n';
        s += "TYPE: " + this.containerType.toString() + '\n';

        for (Measurement meas : this.measurements) {
            if (meas != null) {
                s += meas.toString();
            }
        }
        return s;
    }
}
