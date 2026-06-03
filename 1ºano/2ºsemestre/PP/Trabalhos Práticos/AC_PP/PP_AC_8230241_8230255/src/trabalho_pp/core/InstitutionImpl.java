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

import trabalho_pp.exceptions.TypeError;
import trabalho_pp.distances.*;
import trabalho_pp.pickingManagement.*;
import com.estg.core.*;
import com.estg.pickingManagement.Vehicle;
import com.estg.pickingManagement.PickingMap;
import com.estg.core.exceptions.*;
import java.time.LocalDateTime;
import java.time.LocalDate;

/**
 * Class responsible for the Institution implementation
 */
public class InstitutionImpl implements Institution {

    /**
     * The Institution's name
     */
    private String name;

    /**
     * The Institution's aidBoxes array
     */
    private AidBox[] aidBoxes;

    /**
     * The institution's number of aidBoxes
     */
    private int nAidBoxes;

    /**
     * The institution's initial number of aidBoxes
     */
    private static final int INITIAL_AIDBOXES = 10;

    /**
     * The Institution's Vehicles array
     */
    private Vehicle[] vehicles;

    /**
     * The institution's number of vehicles
     */
    private int nVehicles;

    /**
     * The institution's initial number of vehicles
     */
    private static final int INITIAL_VEHICLES = 10;

    /**
     * The Institution's Picking Maps array
     */
    private PickingMap[] pickingmaps;

    /**
     * The institution's number of picking maps
     */
    private int nPickingMaps;

    /**
     * The institution's initial number of picking maps
     */
    private static final int INITIAL_PICKING_MAPS = 10;

    /**
     * Constructor for the Institution
     *
     * @param name - institution name The nAidBoxes, nVehicles, nPickingMaps is
     * set as 0, the aidboxes, vehicles and picking maps array is initialized
     * with the initial size of 10.
     */
    public InstitutionImpl(String name) {
        this.name = name;
        this.nAidBoxes = 0;
        this.nVehicles = 0;
        this.nPickingMaps = 0;
        this.aidBoxes = new AidBox[INITIAL_AIDBOXES];
        this.vehicles = new Vehicle[INITIAL_VEHICLES];
        this.pickingmaps = new PickingMap[INITIAL_PICKING_MAPS];
    }

    /**
     * Gets the name of the Institution
     *
     * @return String
     */
    @Override
    public String getName() {
        return this.name;
    }

    /**
     * Adds a aidBox to the aidBoxes collection
     *
     * @param aidbox Gets a aidBox.
     * @throws AidBoxException If the provided aidBox is null, and if the aidbox
     * have duplicated containers with same item type.
     * @return boolean
     */
    @Override
    public boolean addAidBox(AidBox aidbox) throws AidBoxException {
        if (aidbox == null) {
            throw new AidBoxException("The AidBox can't be null");
        }

        try {
            sameContainerType(aidbox);
        } catch (TypeError e) {
            throw new AidBoxException("The given aidbox has duplicated containers with same item type");
        }

        int index = findAidBox(aidbox);

        if (index != -1) {
            return false;
        }

        try {
            if (this.nAidBoxes == this.aidBoxes.length) {
                increaseAidBoxesArray();
            }
        } catch (OutOfMemoryError error) {
            throw new OutOfMemoryError("Was not possible to increase the aidBoxes array.");
        }

        this.aidBoxes[this.nAidBoxes++] = aidbox;
        return true;
    }

    /**
     * Adds a aidBox to the aidBoxes collection
     *
     * @param paramMeasurement Gets a measurement.
     * @param paramContainer Gets a container.
     * @throws ContainerException If the provided container is null.
     * @throws MeasurementException If the provided measurement is null.
     * @return boolean
     */
    @Override
    public boolean addMeasurement(Measurement paramMeasurement, Container paramContainer) throws ContainerException, MeasurementException {
        if (paramMeasurement.getValue() < 0 || paramMeasurement.getValue() > paramContainer.getCapacity()) {
            throw new MeasurementException("The measurement value is less than 0 or higher to the capacity!");
        }

        Container index = null;

        for (int i = 0; i < this.nAidBoxes; i++) {
            for (Container container : aidBoxes[i].getContainers()) {
                if (container != null && container.equals(paramContainer)) {
                    index = container;
                    break;
                }
            }
            if (index != null) {
                break;
            }
        }

        if (index == null) {
            throw new ContainerException("Container does not exist in the aid box.");
        }

        for (Measurement measurements : index.getMeasurements()) {
            if (measurements != null) {
                if (measurements.getDate().equals(paramMeasurement.getDate())) {
                    return false;
                }
            }
        }

        index.addMeasurement(paramMeasurement);
        return true;
    }

    /**
     * Creates a temporary array then copy the current array aidBoxes[] to the
     * temporary one and return.
     *
     * @return AidBox[]
     */
    @Override
    public AidBox[] getAidBoxes() {
        AidBox[] tempAidBoxes = new AidBox[this.nAidBoxes];

        for (int i = 0; i < this.nAidBoxes; i++) {
            tempAidBoxes[i] = this.aidBoxes[i];
        }

        return tempAidBoxes;
    }

    /**
     * Get a container from a specific aid box with the given item type
     *
     * @param paramAidBox Gets a aidBox.
     * @param paramItemType Gets a item type.
     * @throws ContainerException If the provided aidBox does not exist or if
     * there is no container on it with the given item type.
     * @return Container
     */
    @Override
    public Container getContainer(AidBox paramAidBox, ItemType paramItemType) throws ContainerException {

        try {
            int index = findAidBox(paramAidBox);
            if (index == -1) {
                throw new ContainerException("The aid box does not exist");
            }
        } catch (AidBoxException e) {
            throw new ContainerException("The aid box does not exist");
        }

        Container[] containers = paramAidBox.getContainers();

        for (Container cont : containers) {
            if (cont != null) {
                if (cont.getType() == paramItemType) {
                    return cont;
                }
            }
        }

        throw new ContainerException("There are no container with this type");
    }

    /**
     * Creates a temporary array then copy the current array vehicles[] to the
     * temporary one and return.
     *
     * @return Vehicle[]
     */
    @Override
    public Vehicle[] getVehicles() {
        Vehicle[] tmpVehicles = new Vehicle[this.nVehicles];

        for (int i = 0; i < this.nVehicles; i++) {
            tmpVehicles[i] = this.vehicles[i];
        }

        return tmpVehicles;
    }

    /**
     * Adds a vehicle to the vehicles collection
     *
     * @param paramVehicle Receives a Vehicle.
     * @throws VehicleException If the provided Vehicle is null.
     * @return boolean
     */
    @Override
    public boolean addVehicle(Vehicle paramVehicle) throws VehicleException {
        if (paramVehicle == null) {
            throw new VehicleException("The Vehicle can't be null");
        }

        int index = findVehicle(paramVehicle);

        if (index != -1) {
            return false;
        }

        try {
            if (nVehicles == this.vehicles.length) {
                increaseVehicleArray();
            }
        } catch (OutOfMemoryError error) {
            throw new OutOfMemoryError("Was not possible to increase the vehicles array.");
        }

        this.vehicles[this.nVehicles++] = paramVehicle;
        return true;
    }

    /**
     * Disable a vehicle status
     *
     * @param paramVehicle Receives a Vehicle.
     * @throws VehicleException If the provided Vehicle is null.
     */
    @Override
    public void disableVehicle(Vehicle paramVehicle) throws VehicleException {
        int index = findVehicle(paramVehicle);

        if (index == -1) {
            throw new VehicleException("The Vehicle does not exist.");
        }

        try {
            if (((VehicleImpl) this.vehicles[index]).getStatus()) {
                ((VehicleImpl) this.vehicles[index]).setStatus(false);
            } else {
                throw new VehicleException("The Vehicle is already disabled.");
            }
        } catch (VehicleException e) {
            throw new VehicleException(e.getMessage());
        }

    }

    /**
     * Enable a vehicle status
     *
     * @param paramVehicle Receives a Vehicle.
     * @throws VehicleException If the provided Vehicle is null.
     */
    @Override
    public void enableVehicle(Vehicle paramVehicle) throws VehicleException {
        int index = findVehicle(paramVehicle);

        if (index == -1) {
            throw new VehicleException("The Vehicle does not exist.");
        }

        try {
            if (!((VehicleImpl) this.vehicles[index]).getStatus()) {
                ((VehicleImpl) this.vehicles[index]).setStatus(true);
            } else {
                throw new VehicleException("The Vehicle is already enabled.");
            }
        } catch (VehicleException e) {
            throw new VehicleException(e.getMessage());
        }

    }

    /**
     * Creates a temporary array then copy the current array pickingmaps[] to
     * the temporary one and return.
     *
     * @return PickingMap[]
     */
    @Override
    public PickingMap[] getPickingMaps() {
        PickingMap[] tmpPickingMaps = new PickingMap[this.nPickingMaps];

        for (int i = 0; i < this.nPickingMaps; i++) {
            tmpPickingMaps[i] = this.pickingmaps[i];
        }

        return tmpPickingMaps;
    }

    /**
     * Creates a temporary array of nPickingMaps positions, iterates the number
     * of existing picking maps and checks if date are between the two given
     * dates where paramLocalDateTime1 is the minimum date time and
     * paramLocalDateTime2 is the max date time, then we create a new array with
     * the necessary picking maps positions and return.
     *
     * @param paramLocalDateTime1 Receives a local date.
     * @param paramLocalDateTime2 Receives another local date.
     * @return PickingMap[]
     */
    @Override
    public PickingMap[] getPickingMaps(LocalDateTime paramLocalDateTime1, LocalDateTime paramLocalDateTime2) {
        int counter = 0;
        PickingMap[] tmpPickingMap = new PickingMap[this.nPickingMaps];

        for (int i = 0; i < nPickingMaps; i++) {
            if ((this.pickingmaps[i].getDate().isAfter(paramLocalDateTime1) || this.pickingmaps[i].getDate().isEqual(paramLocalDateTime1))
                    && (this.pickingmaps[i].getDate().isBefore(paramLocalDateTime2) || this.pickingmaps[i].getDate().isEqual(paramLocalDateTime2))) {

                tmpPickingMap[counter++] = this.pickingmaps[i];
            }
        }

        PickingMap[] finalPickingMap = new PickingMap[counter];

        for (int i = 0; i < counter; i++) {
            finalPickingMap[i] = tmpPickingMap[i];
        }

        return finalPickingMap;
    }

    /**
     * Gets the current day's picking map by iterating through all the picking
     * maps and checks if their date equals today´s date and return.
     *
     * @throws PickingMapException If there is no picking maps in the
     * institution or no picking map for today.
     * @return PickingMap
     */
    @Override
    public PickingMap getCurrentPickingMap() throws PickingMapException {
        if (this.nPickingMaps == 0) {
            throw new PickingMapException("There are no picking maps in the institution.");
        }

        LocalDate today = LocalDate.now();

        for (int i = 0; i < nPickingMaps; i++) {
            if ((this.pickingmaps[i].getDate()).toLocalDate().isEqual(today)) {
                return this.pickingmaps[i];
            }
        }

        throw new PickingMapException("There is no picking map for today.");
    }

    /**
     * Adds a picking map to the current picking maps collection
     *
     * @param paramPickingMap Receives a Picking Map.
     * @throws PickingMapException If the provided Picking Map is null.
     * @return boolean
     */
    @Override
    public boolean addPickingMap(PickingMap paramPickingMap) throws PickingMapException {
        if (paramPickingMap == null) {
            throw new PickingMapException("The Picking Map can't be null");
        }

        int index = findPickingMap(paramPickingMap);

        if (index != -1) {
            return false;
        }

        try {
            if (nPickingMaps == this.pickingmaps.length) {
                increasePickingMapsArray();
            }
        } catch (OutOfMemoryError error) {
            throw new OutOfMemoryError("Was not possible to increase the picking maps array.");
        }

        this.pickingmaps[this.nPickingMaps++] = paramPickingMap;
        return true;
    }

    /**
     * Gets the distance between a specific AidBox and Institution
     *
     * @param paramAidBox Gets other AidBox in order to calculate distance between the aid box and this institution.
     * @throws AidBoxException If the provided AidBox does not exist.
     * @return double
     */
    @Override
    public double getDistance(AidBox paramAidBox) throws AidBoxException {
        Distances distances = Distances.getInstance();

        return distances.getDistance(paramAidBox, this);
    }

    /**
     * Increase the capacity of the array pickingmaps[], to the double of the
     * current capacity in order to do this we create a temporary array with the
     * new size, and then we copy the old array to the newest one.
     *
     * @throws OutOfMemoryError when the programs runs out of memory
     */
    private void increasePickingMapsArray() throws OutOfMemoryError {
        PickingMap[] tmpPickingMap = new PickingMap[this.nPickingMaps * 2];

        try {
            for (int i = 0; i < this.nPickingMaps; i++) {
                tmpPickingMap[i] = this.pickingmaps[i];
            }
        } catch (OutOfMemoryError error) {
            throw new OutOfMemoryError();
        }

        this.pickingmaps = tmpPickingMap;
    }

    /**
     * Try to find if a specific picking map is in the PickingMaps array and
     * returns his index.
     *
     * @param paramPickingMap Picking Map
     * @throws PickingMapException If the provided Picking Map is null.
     * @return int
     */
    private int findPickingMap(PickingMap paramPickingMap) throws PickingMapException {
        if (paramPickingMap == null) {
            throw new PickingMapException("The Picking Map can't be null");
        }

        for (int i = 0; i < nPickingMaps; i++) {
            if (pickingmaps[i].equals(paramPickingMap)) {
                return i;
            }
        }

        return -1;
    }

    /**
     * Increase the capacity of the array vehicles[], to the double of the
     * current capacity in order to do this we create a temporary array with the
     * new size, and then we copy the old array to the newest one.
     *
     * @throws OutOfMemoryError when the programs runs out of memory
     */
    private void increaseVehicleArray() throws OutOfMemoryError {
        Vehicle[] tmpVehicle = new Vehicle[this.nVehicles * 2];

        try {
            for (int i = 0; i < this.nVehicles; i++) {
                tmpVehicle[i] = this.vehicles[i];
            }
        } catch (OutOfMemoryError error) {
            throw new OutOfMemoryError();
        }

        this.vehicles = tmpVehicle;
    }

    /**
     * Try to find if a specific vehicle is in the Vehicles array and returns
     * his index.
     *
     * @param paramVehicle Vehicle
     * @throws VehicleException If the provided Vehicle is null.
     * @return int
     */
    private int findVehicle(Vehicle paramVehicle) throws VehicleException {
        if (paramVehicle == null) {
            throw new VehicleException("The Vehicle can't be null");
        }

        for (int i = 0; i < nVehicles; i++) {
            if (vehicles[i].equals(paramVehicle)) {
                return i;
            }
        }

        return -1;
    }

    /**
     * Increase the capacity of the array aidBoxes[], to the double of the
     * current capacity in order to do this we create a temporary array with the
     * new size, and then we copy the old array to the newest one.
     *
     * @throws OutOfMemoryError when the programs runs out of memory
     */
    private void increaseAidBoxesArray() throws OutOfMemoryError {
        AidBox[] tmpAidBoxes = new AidBox[this.nAidBoxes * 2];

        try {
            for (int i = 0; i < this.nAidBoxes; i++) {
                tmpAidBoxes[i] = this.aidBoxes[i];
            }
        } catch (OutOfMemoryError error) {
            throw new OutOfMemoryError();
        }

        this.aidBoxes = tmpAidBoxes;
    }

    /**
     * Try to find if a specific aidBox is in the aidBoxes array and returns his
     * index.
     *
     * @param paramAidBox AidBox
     * @throws AidBoxException If the provided AidBox is null.
     * @return int
     */
    private int findAidBox(AidBox paramAidBox) throws AidBoxException {
        if (paramAidBox == null) {
            throw new AidBoxException("The AidBox can't be null");
        }

        for (int i = 0; i < nAidBoxes; i++) {
            if (aidBoxes[i].equals(paramAidBox)) {
                return i;
            }
        }

        return -1;
    }

    /**
     * Check if the given aid box have duplicated containers with same item type.
     *
     * @param aidbox AidBox
     * @throws TypeError If the provided AidBox have duplicated containers of a
     * certain item type.
     */
    private void sameContainerType(AidBox aidbox) throws TypeError {
        Container[] tmpContainers = aidbox.getContainers();

        for (int i = 0; i < tmpContainers.length; i++) {
            for (int j = i + 1; j < tmpContainers.length; j++) {
                if (tmpContainers[i].getType() == tmpContainers[j].getType()) {
                    throw new TypeError("This aid box have duplicated containers of a certain item type.");
                }
            }
        }
    }

    /**
     * Try to get a container of the given code if it does not exist then
     * returns null.
     *
     * @param code Container code.
     * @return Container
     */
    public Container getContainer(String code) {
        AidBox[] tmpAidBoxes = getAidBoxes();

        for (AidBox aidbox : tmpAidBoxes) {
            if (aidbox != null) {
                Container[] containers = aidbox.getContainers();
                for (Container container : containers) {
                    if (container != null) {
                        if (container.getCode().equals(code)) {
                            return container;
                        }
                    }
                }
            }
        }

        return null;
    }

    /**
     * Create a detailed string with all the institution information.
     *
     * @return String
     */
    @Override
    public String toString() {
        String s = "---------------------------\n";
        s += "Institution: " + getName() + "\n";
        s += "---------------------------\n";

        s += "---------------------------\n";
        s += "Aid Boxes:\n";
        s += "---------------------------\n";
        
        for (int i = 0; i < this.nAidBoxes; i++) {
            s += this.aidBoxes[i].getCode() + '\n';
        }

        s += "---------------------------\n";
        s += "Vehicles:\n";
        s += "---------------------------\n";

        for (int i = 0; i < this.nVehicles; i++) {
            s += this.vehicles[i].toString();
            s += "---------------------------\n";
        }

        s += "---------------------------\n";
        s += "Picking Maps:\n";
        s += "---------------------------\n";

        for (int i = 0; i < this.nPickingMaps; i++) {
            s += this.pickingmaps[i].toString();
            s += "---------------------------\n";
        }

        return s;
    }

}