/*  
* Nome: Carlos Alberto Moreira Barbosa
* Número: 8230255
* Turma: LSIRC11T1
 */
package PP_ER_8230255.core;

import com.estg.core.*;
import com.estg.core.exceptions.*;
import com.estg.pickingManagement.*;
import PP_ER_8230255.pickingManagement.*;
import java.io.Serializable;
import java.time.LocalDateTime;

public class InstitutionImpl implements Institution {

    // The Institution instance
    private static InstitutionImpl instance;

    // The Institution name
    private final String name;

    // The Institution aid boxes
    private AidBox[] aidboxes;

    // The number of aid boxes
    private int nAidboxes;

    // The Institution vehicles
    private Vehicle[] vehicles;

    // The number of vehicles
    private int nVehicles;

    // The Institution picking maps
    private PickingMap[] pickingmaps;

    // The number of picking maps
    private int nPickingmaps;

    // The initial size of the arrays
    private final int INITIAL_AIDBOXES_SIZE = 5;
    private final int INITIAL_VEHICLES_SIZE = 5;
    private final int INITIAL_PICKINGMAPS_SIZE = 5;

    private static ContainerType[] containersType;
    private Container[] freeContiners;

    /**
     * Constructor for the institution, the aidboxes, vehicles and picking maps
     * are initialized to zero
     *
     * @param name the institution name
     */
    private InstitutionImpl(String name) {
        this.name = name;
        this.aidboxes = new AidBox[INITIAL_AIDBOXES_SIZE];
        this.nAidboxes = 0;
        this.vehicles = new Vehicle[INITIAL_VEHICLES_SIZE];
        this.nVehicles = 0;
        this.pickingmaps = new PickingMap[INITIAL_PICKINGMAPS_SIZE];
        this.nPickingmaps = 0;
    }

    /**
     * Returns the instance of the Institution. If the instance does not already
     * exist, it creates a new InstitutionImpl with the provided name.
     *
     * @param name the name of the institution to create if it does not exist
     * @return the existing or newly created instance of Institution
     */
    public static Institution getInstance(String name) {
        if (instance == null) {
            instance = new InstitutionImpl(name);
        }
        return instance;
    }

    /**
     * Getter for Institution name
     *
     * @return Institution name
     */
    @Override
    public String getName() {
        return this.name;
    }

    /**
     * Adds a new Aid Box to the Institution
     *
     * @param aidbox Aid Box
     * @return true if the Aid Box was inserted in the collection storing all
     * Aid Box and false if the Aid Box already exists
     *
     * @throws AidBoxException The Aid Box is null or The Aid Box is invalid,
     * i.e. if the Aid Box have duplicate containers of a certain waste type
     */
    @Override
    public boolean addAidBox(AidBox aidbox) throws AidBoxException {
        incAidBoxesArray();
        if (aidbox == null) {
            throw new AidBoxException("The Aid Box can't be null");
        }

        if (!validAidBox(aidbox)) {
            throw new AidBoxException("The Aid Box is not valid");
        }

        int index = findAidBox(aidbox);
        if (index != -1) {
            return false;
        }

        this.aidboxes[this.nAidboxes++] = aidbox;
        return true;
    }

    /**
     * Adds a new measurement to the Institution considering the Aid Box and
     * container
     *
     * @param msrmnt Measurement considering the Aid Box and container
     * @param cntnr The Container for the measurement
     *
     * @return true if the measurement was inserted in the collection storing
     * all measurements or false if the measurement already exists for a given
     * date. (Each container has only one measurement for a given date)
     *
     * @throws ContainerException if the container doesn't exist
     * @throws MeasurementException if the value is lesser than 0 and higher to
     * the capacity
     */
    @Override
    public boolean addMeasurement(Measurement msrmnt, Container cntnr) throws ContainerException, MeasurementException {
        if (msrmnt == null || cntnr == null) {
            throw new MeasurementException("The arguments can't be null");
        }

        for (AidBox aidbox : this.aidboxes) {
            if (aidbox != null) {
                Container[] containers = aidbox.getContainers();
                for (Container container : containers) {
                    if (container != null) {
                        if (container.equals(cntnr)) {
                            ((AidBoxImpl) aidbox).addMeasurement(cntnr, msrmnt);
                            return true;
                        }
                    }
                }

            }

        }
        throw new ContainerException("The Container does not exists");
    }

    /**
     * Return a copy of a existing Aid Boxes
     *
     * @return a copy of a existing Aid Boxes
     */
    @Override
    public AidBox[] getAidBoxes() {
        AidBox[] temp = new AidBox[this.nAidboxes];
        int counter = 0;

        for (int i = 0; i < this.nAidboxes; i++) {
            if (this.aidboxes[i] != null) {
                AidBoxImpl aidbox = (AidBoxImpl) this.aidboxes[i];
                try {
                    temp[counter++] = (AidBox) aidbox.clone();
                } catch (CloneNotSupportedException ex) {
                    ex.printStackTrace();
                }
            }
        }
        return temp;
    }

    /**
     * Return a copy of an existing container from a Aid Boxes and container
     * type
     *
     * @param aidbox Aid Box for getting measurements
     * @param ct item Type for getting measurements
     *
     * @return a copy of a existing containers
     *
     * @throws ContainerException if the Aid Box doesn't exist, or if a
     * container with the given container type doesn't exist
     */
    @Override
    public Container getContainer(AidBox aidbox, ContainerType ct) throws ContainerException {

        if (aidbox == null || ct == null) {
            throw new ContainerException("The arguments cant be null");
        }

        int index = findAidBox(aidbox);
        if (index == -1) {
            throw new ContainerException("The AidBox dont exist");
        }

        Container cont = this.aidboxes[index].getContainer(ct);
        if (cont == null) {
            throw new ContainerException("The Aid Box does not contain a container with this type");
        }

        return cont;
    }

    /**
     * Getter for a (deep) copy of the vehicles in the institution
     *
     * @return a (deep) copy of the vehicles in the institution
     */
    @Override
    public Vehicle[] getVehicles() {
        Vehicle[] temp = new Vehicle[this.nVehicles];
        int counter = 0;

        for (int i = 0; i < this.vehicles.length; i++) {
            if (this.vehicles[i] != null) {
                temp[counter++] = new VehicleImpl(this.vehicles[i].getCode(),
                        ((VehicleImpl) this.vehicles[i]).getCapacities(),
                        ((VehicleImpl) this.vehicles[i]).getStatus());
            }
        }
        return temp;
    }

    /**
     * Adds a new vehicle to the institution
     *
     * @param vhcl vehicle to be added to the institution
     *
     * @return true if the vehicle was inserted in the collection storing all
     * vehicles false if the vehicle already exists
     *
     * @throws VehicleException the vehicle is null
     */
    @Override
    public boolean addVehicle(Vehicle vhcl) throws VehicleException {
        incVehiclesArray();
        if (vhcl == null) {
            throw new VehicleException("The Vehicle can't be null");
        }

        int index = findVehicle(vhcl);
        if (index != -1) {
            return false;
        }

        this.vehicles[this.nVehicles++] = vhcl;
        return true;
    }

    /**
     * Disables a vehicle in the institution
     *
     * @param vhcl vehicle to be disabled
     *
     * @throws VehicleException if the vehicle does not exist or if the vehicle
     * is already disabled
     */
    @Override
    public void disableVehicle(Vehicle vhcl) throws VehicleException {
        for (Vehicle vehicle : this.vehicles) {
            if (vhcl.equals(vehicle)) {
                ((VehicleImpl) vehicle).setStatus(false);
            }
        }
    }

    /**
     * Enables a vehicle in the institution
     *
     * @param vhcl vehicle to be enabled
     *
     * @throws VehicleException if the vehicle does not exist or if the vehicle
     * is already enabled
     */
    @Override
    public void enableVehicle(Vehicle vhcl) throws VehicleException {
        for (Vehicle vehicle : this.vehicles) {
            if (vhcl.equals(vehicle)) {
                ((VehicleImpl) vehicle).setStatus(true);
            }
        }
    }

    /**
     * Getter for the picking maps in the institution
     *
     * @return the picking maps in the institution
     */
    @Override
    public PickingMap[] getPickingMaps() {
        PickingMap[] temp = new PickingMap[this.nPickingmaps];
        int counter = 0;

        for (PickingMap pick : this.pickingmaps) {
            if (pick != null) {
                temp[counter++] = this.getPickingMapClone(pick);
            }
        }
        return temp;
    }

    /**
     * Getter for the picking maps in the institution
     *
     * @param ldt start date
     * @param ldt1 end date
     * @return the picking maps in the institution
     */
    @Override
    public PickingMap[] getPickingMaps(LocalDateTime ldt, LocalDateTime ldt1) {
        PickingMap[] temp = new PickingMap[this.nPickingmaps];
        int counter = 0;

        for (PickingMap pm : this.pickingmaps) {
            if ((pm.getDate().isAfter(ldt) || pm.getDate().isEqual(ldt)) && (pm.getDate().isBefore(ldt1) || pm.getDate().isEqual(ldt1))) {
                temp[counter++] = this.getPickingMapClone(pm);
            }
        }
        return temp;
    }

    /**
     * Getter for the current (most recent) picking map in the institution
     *
     * @return the current picking map in the institution
     *
     * @throws PickingMapException if there are no picking maps in the
     * institution
     */
    @Override
    public PickingMap getCurrentPickingMap() throws PickingMapException {
        if (this.nPickingmaps == 0) {
            throw new PickingMapException("The institution don't have picking maps");
        }

        return this.getPickingMapClone(this.pickingmaps[this.nPickingmaps - 1]);

    }

    /**
     * Adds a new picking map to the institution
     *
     * @param pm the picking map to be added to the institution
     *
     * @return true if the picking map was inserted in the collection storing
     * all picking maps false if the picking map already exists
     *
     * @throws PickingMapException if the picking map is null
     */
    @Override
    public boolean addPickingMap(PickingMap pm) throws PickingMapException {
        incPickingMapsArray();
        if (pm == null) {
            throw new PickingMapException("The Picking Map can't be null");
        }

        if (findPickingMap(pm) != -1) {
            return false;
        }

        this.pickingmaps[this.nPickingmaps++] = pm;
        return true;
    }

    /**
     * Getter for Distance between the instituition and parameter Aid Box.
     *
     * @param aidbox to the target.
     *
     * @return the distance.
     *
     * @throws AidBoxException if aid box does not exist.
     */
    @Override
    public double getDistance(AidBox aidbox) throws AidBoxException {
        for (AidBox aid : this.aidboxes) {
            if (aid.equals(aidbox)) {
                return ((AidBoxImpl) aid).getDistance(this);
            }
        }
        throw new AidBoxException("The AidBox does not exist");
    }

    /**
     * Find if a aidbox exists in the array
     *
     * @param ad a aidbox
     * @return the index of the aid box or -1 if the aid box dont exist
     */
    private int findAidBox(AidBox ad) {
        for (int i = 0; i < this.nAidboxes; i++) {
            if (ad.equals(this.aidboxes[i])) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Duplicate the array size by 2 if the array of the aidboxes are full
     */
    private void incAidBoxesArray() {
        if (this.nAidboxes == this.aidboxes.length) {
            AidBox[] temp = new AidBox[this.nAidboxes * 2];
            System.arraycopy(this.aidboxes, 0, temp, 0, this.nAidboxes);
            this.aidboxes = temp;
        }
    }

    /**
     * See if the given Aidd Box is valid
     *
     * @param ad aidbox to see if is valid
     * @return if is valid or not
     */
    private boolean validAidBox(AidBox ad) {
        Container[] containers = ad.getContainers();

        // if the aidbox has 2 containers with the same type, the aidbox is not valid
        for (int i = 0; i < containers.length - 1; i++) {
            for (int j = i + 1; j < containers.length - 1; j++) {
                if (containers[i].getType().equals(containers[j].getType())) {
                    return false;
                }
            }
        }

        return true;
    }

    /**
     * Find if a vehicle exists in the array
     *
     * @param ve a vehicle
     * @return the index of the aid box or -1 if the aid box dont exist
     */
    private int findVehicle(Vehicle ve) {
        for (int i = 0; i < this.nVehicles; i++) {
            if (ve.equals(this.vehicles[i])) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Duplicate the array size by 2 if the array of the vehicles are full
     */
    private void incVehiclesArray() {
        if (this.nVehicles == this.vehicles.length) {
            Vehicle[] temp = new Vehicle[this.nVehicles * 2];
            System.arraycopy(this.vehicles, 0, temp, 0, this.nVehicles);
            this.vehicles = temp;
        }
    }

    /**
     * Find if a picking map exists in the array
     *
     * @param ad a vehicle
     * @return the index of the aid box or -1 if the aid box dont exist
     */
    private int findPickingMap(PickingMap pm) {
        for (int i = 0; i < this.nPickingmaps; i++) {
            if (pm.equals(this.pickingmaps[i])) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Duplicate the array size by 2 if the array of the picking maps are full
     */
    private void incPickingMapsArray() {
        if (this.nPickingmaps == this.pickingmaps.length) {
            PickingMap[] temp = new PickingMap[this.nPickingmaps * 2];
            System.arraycopy(this.pickingmaps, 0, temp, 0, this.nPickingmaps);
            this.pickingmaps = temp;
        }
    }

    private PickingMap getPickingMapClone(PickingMap pickingMap) {
        return new PickingMapImpl(pickingMap.getDate(), pickingMap.getRoutes());
    }

    /**
     * sets the possible container types to the institution
     *
     * @param containerTypes the possible container types
     */
    public void setContainerTypes(ContainerType[] containerTypes) {
        ContainerType[] temp = new ContainerTypeImpl[containerTypes.length];
        int counter = 0;

        for (ContainerType type : containerTypes) {
            if (type != null) {
                temp[counter++] = type;
            }
        }

        InstitutionImpl.containersType = new ContainerType[counter];
        System.arraycopy(temp, 0, InstitutionImpl.containersType, 0, counter);
    }

    /**
     * get the possible container types
     *
     * @return the possible container types
     */
    public ContainerType[] getContainerTypes() {
        ContainerType[] temp = new ContainerTypeImpl[InstitutionImpl.containersType.length];
        int counter = 0;

        for (ContainerType type : InstitutionImpl.containersType) {
            if (type != null) {
                temp[counter++] = new ContainerTypeImpl(type.toString());
            }
        }

        ContainerType[] resizedTemp = new ContainerType[counter];
        System.arraycopy(temp, 0, resizedTemp, 0, counter);
        return resizedTemp;
    }

    /**
     * gets the container type, with the given string type
     *
     * @param type the string type of the container tye
     * @return the container type with that string type
     */
    public ContainerType getContainerType(String type) {
        for (ContainerType containerType : InstitutionImpl.containersType) {
            if (containerType != null) {
                ContainerType tempContType = new ContainerTypeImpl(type);
                if (containerType.equals(tempContType)) {
                    return tempContType;
                }
            }
        }
        return null;
    }

    /**
     * insert the given distance in the aidbox with the given code
     *
     * @param distancesToOtherDestinations the distances between the aidbox with
     * the given code and other aidboxes
     * @param from
     */
    public void importDistanceToAidBox(DistanceToOtherDestination[] distancesToOtherDestinations, String from) {
        for (AidBox aid : this.aidboxes) {
            if (aid.getCode().equals(from)) {
                ((AidBoxImpl) aid).setDestinations(distancesToOtherDestinations);
            }
        }
    }

    /**
     * returns the number of containers in the institution
     *
     * @return the number of containers
     */
    public int getNumberOfTotalContainers() {
        int counter = 0;
        for (AidBox aid : this.aidboxes) {
            if (aid != null) {
                for (Container cont : aid.getContainers()) {
                    if (cont != null) {
                        counter++;
                    }
                }
            }

        }
        return counter;
    }

    /**
     * Sets the free containers in the institution
     *
     * @param containersNotPicked the free containers
     */
    public void setNonPickedContainers(Container[] containersNotPicked) {
        this.freeContiners = containersNotPicked;
    }

    /**
     * gets an free containers with the given type, strips from the array and
     * returns the container
     *
     * @param ct the container type
     * @return an container with the given container type
     * @throws ContainerException if the institution dont have more free
     * containers with the given type
     */
    public Container getFreeContainer(ContainerType ct) throws ContainerException {
        for (int i = 0; i < this.freeContiners.length; i++) {
            if (this.freeContiners[i] != null) {
                if (this.freeContiners[i].getType().equals(ct)) {
                    try {
                        Container cont = (Container) ((ContainerImpl) this.freeContiners[i]).clone();
                        this.freeContiners[i] = null;
                        return cont;
                    } catch (CloneNotSupportedException ex) {
                        System.out.println("ERROR WHILE CLONING CONTAINERS");
                    }

                }
            }
        }
        throw new ContainerException("There are no free containers for this type");
    }

    /**
     * swap 2 containersin the given aidbox
     *
     * @param aidbox aidbox to swap containers
     * @param cont1 container to replace
     * @param cont2 container to replace with
     */
    public void swapContainersInAidBox(AidBox aidbox, Container cont1, Container cont2) {
        for (AidBox aid : this.aidboxes) {
            if (aid != null) {
                if (aid.equals(aidbox)) {
                    ((AidBoxImpl) aid).swapContainers(cont1, cont2);
                }
            }
        }
    }

    /**
     * returns a string with the institution info
     *
     * @return a string with all the institution info
     */
    @Override
    public String toString() {
        String s = "--INSTITUTION--\n\n";

        s += "-----------------\n";
        s += " FREE CONTAINERS \n";
        s += "-----------------\n";
        s += "\n\n";

        for (Container container : this.freeContiners) {
            if (container != null) {
                s += container.toString();
                s += "----\n";
            }
        }

        s += "-----------------\n";
        s += "    AID BOXES    \n";
        s += "-----------------\n";
        s += "\n\n";

        for (AidBox aidbox : this.aidboxes) {
            if (aidbox != null) {
                s += aidbox.toString();
                s += "----\n";
            }
        }

        s += "-----------------\n";
        s += "     VEHICLES    \n";
        s += "-----------------\n";
        s += "\n\n";

        for (Vehicle vehicle : this.vehicles) {
            if (vehicle != null) {
                s += vehicle.toString();
                s += "----\n";
            }
        }
        return s;
    }

    /**
     * prints the institution info
     *
     * @return a string with all the institution info
     */
    public String printInsitutionInfo1() {
        String s = "\n\n--  INSTITUTION  --\n";

        s += "\n\nFREE CONTAINERS:\n\n";
        for (Container container : this.freeContiners) {
            if (container != null) {
                s += "   - " + container.getCode() + "\n";
            }
        }

        s += "\n\nUSED CONTAINERS:\n\n";
        for (AidBox aidbox : this.aidboxes) {
            if (aidbox != null) {
                for (Container container : aidbox.getContainers()) {
                    if (container != null) {
                        s += "   - " + container.getCode() + "\n";
                    }
                }
            }
        }

        s += "\n\nAID BOXES:\n\n";
        for (AidBox aidbox : this.aidboxes) {
            if (aidbox != null) {
                s += "   - " + aidbox.getCode() + "\n";
            }
        }

        s += "\n\n";
        s += "VEHICLES:\n\n";
        for (Vehicle vehicle : this.vehicles) {
            if (vehicle != null) {
                s += "   - " + vehicle.getCode() + "\n";
            }
        }

        s += "\n\n";
        s += "PICKING MAPS:\n\n";
        for (PickingMap pickingMap : this.pickingmaps) {
            if (pickingMap != null) {
                s += "    - " + pickingMap.getDate() + "\n";
                for (Route route : pickingMap.getRoutes()) {
                    if (route != null) {
                        s += "        - " + route.getVehicle().getCode() + "\n";
                        for (AidBox aid : route.getRoute()) {
                            if (aid != null) {
                                s += "            - " + aid.getCode() + "\n";
                            }
                        }
                    }
                }

                s += pickingMap.getRoutes()[0].getReport();
            }
        }

        return s;
    }

}
