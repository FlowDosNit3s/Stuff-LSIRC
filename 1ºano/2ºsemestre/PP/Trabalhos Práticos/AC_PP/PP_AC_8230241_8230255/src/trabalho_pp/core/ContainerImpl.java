/*
Nome: Jose Pedro Vieira Barbosa
Número: 8230241
Turma: LEIT3
*
Nome: Carlos Alberto Moreira Barbosa
Número: 8230255
Turma: LSIRCT2
*/
package trabalho_pp.core;

import com.estg.core.Container;
import com.estg.core.ItemType;
import com.estg.core.Measurement;
import com.estg.core.exceptions.MeasurementException;
import java.time.LocalDate;

/**
 * Class responsible for the Container implementation
 */
public class ContainerImpl implements Container {

    /**
     * The container's code
     */
    private String code;

    /**
     * The container's capacity
     */
    private double capacity;

    /**
     * The container's item type
     */
    private ItemType itemType;

    /**
     * The container's measurements array
     */
    private Measurement[] measurements;

    /**
     * The container's number of measurements
     */
    private int nMeasurements;

    /**
     * The container's initial number of measurements
     */
    private static final int INITIAL_MEASUREMENTS = 10;

    /**
     * Constructor for the Container
     *
     * @param code - code of the Container
     * @param capacity - capacity of the Container
     * @param itemType - item type of the Container
     * The nMeasurements is set as 0, the measurements array is initialized with the initial size of 10.
     */
    public ContainerImpl(String code, double capacity, ItemType itemType) {
        this.code = code;
        this.capacity = capacity;
        this.itemType = itemType;
        this.nMeasurements = 0;
        this.measurements = new Measurement[INITIAL_MEASUREMENTS];
    }

    /**
     * Gets the code of the Container
     *
     * @return String
     */
    @Override
    public String getCode() {
        return this.code;
    }

    /**
     * Gets the capacity of the Container
     *
     * @return double
     */
    @Override
    public double getCapacity() {
        return this.capacity;
    }

    /**
     * Gets the item type of the Container
     *
     * @return ItemType
     */
    @Override
    public ItemType getType() {
        return this.itemType;
    }

    /**
     * Creates a temporary array then copy the current array measurements[] to
     * the temporary one.
     *
     * @return Measurement[]
     */
    @Override
    public Measurement[] getMeasurements() {
        Measurement[] tmpMeasurements = new Measurement[this.nMeasurements];
        
        for(int i = 0; i < this.nMeasurements; i++){
            tmpMeasurements[i] = this.measurements[i];
        }

        return tmpMeasurements;
    }

    /**
     * Compare all the measurements with the given date creates a temporary
     * array with all of them and return.
     *
     * @param paramLocalDate Gets a specific Date.
     * @return Measurement[]
     */
    @Override
    public Measurement[] getMeasurements(LocalDate paramLocalDate) {
        int counter = 0;

        for (int i = 0; i < nMeasurements; i++) {
            if ((this.measurements[i].getDate()).toLocalDate().equals(paramLocalDate)) {
                counter++;
            }
        }

        Measurement[] tmpMeasurements = new Measurement[counter];
        int index = 0;

        for (int i = 0; i < nMeasurements; i++) {
            if ((this.measurements[i].getDate()).toLocalDate().equals(paramLocalDate)) {
                tmpMeasurements[index++] = this.measurements[i];
            }
        }

        return tmpMeasurements;
    }

    /**
     * Adds a measurement to the measurements collection of the container.
     *
     * @param paramMeasurement Gets a Measurement.
     * @throws MeasurementException If the provided Measurement is null, if the
     * value is lesser than 0, if the date is before the existing last
     * measurement date and if the for a given date the measurement already
     * exists but the values are different.
     * @return boolean
     */
    @Override
    public boolean addMeasurement(Measurement paramMeasurement) throws MeasurementException {
        if (paramMeasurement == null) {
            throw new MeasurementException("The Measurement can't be null");
        }

        if (paramMeasurement.getValue() < 0) {
            throw new MeasurementException("The measurement value cant be less than 0.");
        }

        if (this.nMeasurements > 0 && paramMeasurement.getDate().isBefore(this.measurements[this.nMeasurements - 1].getDate())) {
            throw new MeasurementException("The measurement date is before the existing last measurement date.");
        }

        int index = findMeasurement(paramMeasurement);

        if (index != -1) {
            if (measurements[index].getValue() != paramMeasurement.getValue()) {
                throw new MeasurementException("For a given date, the measurement already exists but the values are different.");
            }
            return false;
        }

        try {
            if (nMeasurements == measurements.length) {
                increaseMeasurementArray();
            }
        } catch (OutOfMemoryError error) {
            throw new MeasurementException("Was not possible to increase the measurement array.");
        }

        measurements[nMeasurements++] = paramMeasurement;
        return true;
    }

    /**
     * Increase the capacity of the array measurements[], to the double of the
     * current capacity in order to do this we create a temporary array with the
     * new size, and then we copy the old array to the newest one.
     *
     * @throws OutOfMemoryError when the programs runs out of memory
     */
    private void increaseMeasurementArray() throws OutOfMemoryError {
        Measurement[] tmpMeasurements = new Measurement[this.nMeasurements * 2];

        try {
            for (int i = 0; i < nMeasurements; i++) {
                tmpMeasurements[i] = this.measurements[i];
            }
        } catch (OutOfMemoryError error) {
            throw new OutOfMemoryError();
        }

        this.measurements = tmpMeasurements;
    }

    /**
     * Try to find if a specific measurement is in the measurements array and
     * returns his index.
     *
     * @param paramMeasurement Measurement
     * @throws MeasurementException If the provided Measurement is null.
     * @return int
     */
    private int findMeasurement(Measurement paramMeasurement) throws MeasurementException {
        if (paramMeasurement == null) {
            throw new MeasurementException("The Measurement can't be null");
        }

        for (int i = 0; i < nMeasurements; i++) {
            if (measurements[i].equals(paramMeasurement)) {
                return i;
            }
        }

        return -1;
    }

    /**
     * Compare if the container code is equals
     *
     * @param obj - Object
     * @return boolean
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof Container)) {
            return false;
        }

        Container container = (Container) obj;
        return this.code.equals(container.getCode());
    }

    /**
     * Create a detailed string with all the container information.
     *
     * @return String
     */
    public String toString() {
        String s = "Code: " + getCode() + '\n';
        s += "Capacity: " + getCapacity() + '\n';
        s += "Item Type: " + getType() + '\n';

        for (int i = 0; i < nMeasurements; i++) {
            s += this.measurements[i].toString();
        }

        return s;
    }
}
