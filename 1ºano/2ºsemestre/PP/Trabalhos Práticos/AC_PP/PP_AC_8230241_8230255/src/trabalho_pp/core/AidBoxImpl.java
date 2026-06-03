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

import com.estg.core.*;
import com.estg.core.exceptions.*;
import trabalho_pp.distances.Distances;

/**
 * Class responsible for the AidBox implementation
 */
public class AidBoxImpl implements AidBox {

    /**
     * The aidbox's code
     */
    private String code;

    /**
     * The aidbox's zone
     */
    private String zone;

    /**
     * The aidbox's coordinates
     */
    private GeographicCoordinates coordinates;

    /**
     * The aidbox's containers array
     */
    private Container[] containers;

    /**
     * The aidbox's number of containers
     */
    private int nContainers;

    /**
     * The aidbox's initial number of containers
     */
    private static final int INITIAL_CONTAINERS = 10;

    /**
     * Constructor for the AidBox
     *
     * @param code - code of the AidBox
     * @param zone - zone of the AidBox
     * @param coordinates - geographic coordinates of the AidBox.
     * The nContainers is set as 0, the containers array is initialized with the initial size of 10.
     */
    public AidBoxImpl(String code, String zone, GeographicCoordinates coordinates) {
        this.code = code;
        this.zone = zone;
        this.coordinates = coordinates;
        this.nContainers = 0;
        this.containers = new Container[INITIAL_CONTAINERS];
    }

    /**
     * Another constructor for the AidBox
     *
     * @param code - code of the AidBox
     * @param zone - zone of the AidBox
     * @param coordinates - geographic coordinates of the AidBox The nContainers
     * @param containers - Array of containers
     *
     * A constructor that gets an array of containers and copy them to the
     * current AidBox containers collection.
     */
    public AidBoxImpl(String code, String zone, GeographicCoordinates coordinates, Container[] containers) {
        this(code, zone, coordinates);

        int counter = 0;
        Container[] temp = new Container[containers.length];

        for (int i = 0; i < containers.length; i++) {
            if (containers[i] != null) {
                temp[counter++] = containers[i];
            }
        }

        this.nContainers = counter;

        for (int i = 0; i < this.nContainers; i++) {
            this.containers[i] = temp[i];
        }
    }

    /**
     * Gets the code of the AidBox
     *
     * @return String
     */
    @Override
    public String getCode() {
        return this.code;
    }

    /**
     * Gets the zone of the AidBox
     *
     * @return String
     */
    @Override
    public String getZone() {
        return this.zone;
    }

    /**
     * Gets the latitude and longitude of the AidBox
     *
     * @return String
     */
    @Override
    public String getRefLocal() {
        return "Latitude: " + this.coordinates.getLatitude() + "\n" + "Longitude: " + this.coordinates.getLongitude();
    }

    /**
     * Gets the latitude of the AidBox
     *
     * @return double
     */
    public double getLatitude() {
        return this.coordinates.getLatitude();
    }

    /**
     * Gets the longitude of the AidBox
     *
     * @return double
     */
    public double getLongitude() {
        return this.coordinates.getLongitude();
    }

    /**
     * Gets the distance between AidBox's using get distance method.
     *
     * @param paramAidBox Gets other AidBox in order to calculate distance.
     * @throws AidBoxException If the provided AidBox does not exist.
     * @return double
     */
    @Override
    public double getDistance(AidBox paramAidBox) throws AidBoxException {
        Distances distances = Distances.getInstance();
        if (paramAidBox == null) {
            throw new AidBoxException("The AidBox can't be null");
        }
        
        return distances.getDistance(this, paramAidBox);        
    }

    /**
     * Gets the duration between AidBox's using get duration method.
     *
     * @param paramAidBox Gets other AidBox in order to calculate duration.
     * @throws AidBoxException If the provided AidBox does not exist.
     * @return double
     */
    @Override
    public double getDuration(AidBox paramAidBox) throws AidBoxException {
        Distances distances = Distances.getInstance();
        if (paramAidBox == null) {
            throw new AidBoxException("The AidBox can't be null");
        }

        return distances.getDuration(this, paramAidBox);

    }

    /**
     * Gets the coordinates of the AidBox
     *
     * @return GeographicCoordinates
     */
    @Override
    public GeographicCoordinates getCoordinates() {
        return this.coordinates;
    }

    /**
     * Adds a container to the containers collection
     *
     * @param paramContainer Receives a Container.
     * @throws ContainerException If the provided Container is null or if there is
     * already a container with same given type.
     * @return boolean
     */
    @Override
    public boolean addContainer(Container paramContainer) throws ContainerException {
        if (paramContainer == null) {
            throw new ContainerException("The Container can't be null");
        }

        int index = findContainer(paramContainer);

        if (index != -1) {
            return false;
        }

        try {
            if (nContainers == this.containers.length) {
                increaseContainerArray();
            }
        } catch (OutOfMemoryError error) {
            throw new OutOfMemoryError("Was not possible to increase the containers array.");
        }

        for (int i = 0; i < nContainers; i++) {
            if (this.containers[i].getType() == paramContainer.getType()) {
                throw new ContainerException("There is already an existing container with that item type");
            }
        }

        this.containers[this.nContainers++] = paramContainer;
        return true;
    }

    /**
     * Try to get a container of the given ItemType if it does not exist then
     * returns null.
     *
     * @param paramItemType Gets a specific ItemType.
     * @return Container
     */
    @Override
    public Container getContainer(ItemType paramItemType) {
        for (int i = 0; i < nContainers; i++) {
            if (this.containers[i].getType() == paramItemType) {
                return containers[i];
            }
        }
        return null;
    }

    /**
     * Creates a temporary array then copy the current array containers[] to the
     * temporary one and return.
     *
     * @return Container[]
     */
    @Override
    public Container[] getContainers() {
        Container[] tmpContainers = new Container[this.nContainers];

        for (int i = 0; i < this.nContainers; i++) {
            tmpContainers[i] = this.containers[i];
        }

        return tmpContainers;
    }

    /**
     * Increase the capacity of the array containers[], to the double of the
     * current capacity in order to do this we create a temporary array with the
     * new size, and then we copy the old array to the newest one.
     *
     * @throws OutOfMemoryError when the programs runs out of memory
     */
    private void increaseContainerArray() throws OutOfMemoryError {
        Container[] tmpContainers = new Container[this.nContainers * 2];

        try {
            for (int i = 0; i < nContainers; i++) {
                tmpContainers[i] = this.containers[i];
            }
        } catch (OutOfMemoryError error) {
            throw new OutOfMemoryError();
        }

        this.containers = tmpContainers;
    }

    /**
     * Try to find if a specific container is in the containers array and
     * returns his index.
     *
     * @param paramContainer Container
     * @throws ContainerException If the provided Container is null.
     * @return int
     */
    private int findContainer(Container paramContainer) throws ContainerException {
        if (paramContainer == null) {
            throw new ContainerException("The Container can't be null");
        }

        for (int i = 0; i < nContainers; i++) {
            if (containers[i].equals(paramContainer)) {
                return i;
            }
        }

        return -1;
    }

    /**
     * Create a detailed string with all the aid box information.
     *
     * @return String
     */
    public String toString() {
        String s = "Code: " + getCode() + '\n';
        s += "Zone: " + getZone() + '\n';
        s += "Coordinates: " + getRefLocal() + '\n';

        for (int i = 0; i < nContainers; i++) {
            s += this.containers[i].toString();
        }

        return s;
    }

    /**
     * Compare if the aid box code is equals
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
        if (!(obj instanceof AidBox)) {
            return false;
        }

        AidBox aidBox = (AidBox) obj;
        return this.code.equals(aidBox.getCode());
    }

}