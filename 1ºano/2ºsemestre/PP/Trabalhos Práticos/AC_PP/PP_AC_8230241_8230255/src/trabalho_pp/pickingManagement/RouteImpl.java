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

import com.estg.core.AidBox;
import com.estg.core.Container;
import com.estg.core.exceptions.AidBoxException;
import com.estg.pickingManagement.Route;
import com.estg.pickingManagement.Vehicle;
import com.estg.pickingManagement.exceptions.RouteException;

/**
 * Class responsible for the Route implementation
 */
public class RouteImpl implements Route {

    /**
     * The Route's aidBoxes array
     */
    private AidBox[] aidBoxes;

    /**
     * The Route's number of aidBoxes
     */
    private int nAidBoxes;

    /**
     * The Route's initial number of aidBoxes
     */
    private static final int INITIAL_AIDBOXES = 10;

    /**
     * The Route's vehicle
     */
    private Vehicle vehicle;

    /**
     * Constructor for the Route. The nAidBoxes is set as 0, the aid boxes array
     * is initialized with the initial size of 10.
     */
    public RouteImpl(Vehicle vehicle) {
        this.vehicle = vehicle;
        this.nAidBoxes = 0;
        this.aidBoxes = new AidBox[INITIAL_AIDBOXES];
    }

    /**
     * Adds a aidBox to the aidBoxes collection
     *
     * @param paramAidBox Gets a aidBox.
     * @throws RouteException If the provided aidBox is null, if the aid box is
     * already in the route or if aid box is not compatible with Vehicle of the
     * route.
     */
    @Override
    public void addAidBox(AidBox paramAidBox) throws RouteException {
        if (paramAidBox == null) {
            throw new RouteException("The AidBox can't be null");
        }

        int index = this.findAidBox(paramAidBox);

        if (paramAidBox.getCode().equals("Base")) {
            index = -1;
        }

        if (index != -1) {
            throw new RouteException("The AidBox is already in the route");
        }

        boolean compatible = false;

        Container[] containers = paramAidBox.getContainers();

        for (Container container : containers) {
            if (container != null) {
                if (container.getType() == this.vehicle.getSupplyType()) {
                    compatible = true;
                    break;
                }
            }
        }

        if (!compatible) {
            throw new RouteException("The AidBox is not compatible with the Vehicle of the route");
        }

        try {
            if (this.nAidBoxes == this.aidBoxes.length) {
                increaseAidBoxesArray();
            }
        } catch (OutOfMemoryError error) {
            throw new OutOfMemoryError("Was not possible to increase the aidBoxes array.");
        }

        this.aidBoxes[this.nAidBoxes++] = paramAidBox;
    }

    /**
     * Remove a aidBox in the aidBoxes collection
     *
     * @param paramAidBox Gets a aidBox.
     * @throws RouteException If the provided aidBox is null or if the aid box is
     * not on the route.
     * @return AidBox
     */
    @Override
    public AidBox removeAidBox(AidBox paramAidBox) throws RouteException {
        if (paramAidBox == null) {
            throw new RouteException("The Aidbox can't be null");
        }

        int index = findAidBox(paramAidBox);

        if (index == -1) {
            throw new RouteException("The Aid Box is not in the route");
        }

        for (int i = index; i < this.nAidBoxes - 1; i++) {
            this.aidBoxes[i] = this.aidBoxes[i + 1];
        }

        this.aidBoxes[--this.nAidBoxes] = null;
        return paramAidBox;
    }

    /**
     * Checks if the aidbox is in the route and returns.
     *
     * @param paramAidBox Gets a AidBox.
     * @return boolean
     */
    @Override
    public boolean containsAidBox(AidBox paramAidBox) {
        return findAidBox(paramAidBox) != -1;
    }

    /**
     * Replace the first given aidbox1 with another aidbox2.
     *
     * @param paramAidBox1 Gets a aidBox that i want to replace.
     * @param paramAidBox2 Gets a aidBox that i want to replace with.
     * @throws RouteException If the provided aidBox is null, if the aidbox to
     * replace is not in the route, the aidbox to insert is already in the route
     * or if the aidbox to insert is not compatible with the vehicle of the
     * route.
     */
    @Override
    public void replaceAidBox(AidBox paramAidBox1, AidBox paramAidBox2) throws RouteException {

        if (paramAidBox1 == null || paramAidBox2 == null) {
            throw new RouteException("The Aidbox can't be null");
        }

        int index1 = findAidBox(paramAidBox1);
        int index2 = findAidBox(paramAidBox2);

        if (index1 == -1) {
            throw new RouteException("The Aid Box to replace is not in the route");
        }

        if (index2 != -1) {
            throw new RouteException("The Aid Box to insert is already in the route");
        }

        boolean compatible = false;

        Container[] containers = paramAidBox2.getContainers();

        for (Container container : containers) {
            if (container != null) {
                if (container.getType() == this.vehicle.getSupplyType()) {
                    compatible = true;
                    break;
                }
            }
        }

        if (!compatible) {
            throw new RouteException("The AidBox to insert is not compatible with the Vehicle of the route");
        }

        this.aidBoxes[index1] = paramAidBox2;
    }

    /**
     * Create another aidbox after the aidbox that i want
     *
     * @param after Gets a aidBox that i want to be my index.
     * @param toInsert Gets a aidBox that i will insert after my index.
     */
    @Override
    public void insertAfter(AidBox after, AidBox toInsert) throws RouteException {
        if (after == null || toInsert == null) {
            throw new RouteException("The Aidbox can't be null");
        }

        int index = findAidBox(after);
        int index1 = findAidBox(toInsert);

        if (index == -1) {
            throw new RouteException("The Aid Box is not in the route");
        }
        if (index1 != -1) {
            throw new RouteException("The Aid Box is already in the route");
        }

        boolean compatible = false;

        Container[] containers = toInsert.getContainers();

        for (Container container : containers) {
            if (container != null) {
                if (container.getType() == this.vehicle.getSupplyType()) {
                    compatible = true;
                    break;
                }
            }
        }

        if (!compatible) {
            throw new RouteException("The AidBox to insert is not compatible with the Vehicle of the route");
        }

        try {
            if (this.nAidBoxes == this.aidBoxes.length) {
                increaseAidBoxesArray();
            }
        } catch (OutOfMemoryError error) {
            throw new OutOfMemoryError("Was not possible to increase the aidBoxes array.");
        }

        for (int i = this.nAidBoxes - 1; i > index; i--) {
            this.aidBoxes[i + 1] = this.aidBoxes[i];
        }

        this.aidBoxes[index + 1] = toInsert;
        this.nAidBoxes++;
    }

    /**
     * Creates a temporary array then copy the current array aidBoxes[] to the
     * temporary one and return.
     *
     * @return AidBox[]
     */
    @Override
    public AidBox[] getRoute() {
        AidBox[] tmpAidBoxes = new AidBox[this.nAidBoxes];

        for (int i = 0; i < this.nAidBoxes; i++) {
            tmpAidBoxes[i] = this.aidBoxes[i];
        }

        return tmpAidBoxes;
    }

    /**
     * Gets the vehicle of the Route
     *
     * @return Vehicle
     */
    @Override
    public Vehicle getVehicle() {
        return this.vehicle;
    }

    /**
     * Gets the total distance between all AidBoxes
     *
     * @return double
     */
    @Override
    public double getTotalDistance() {
        double distance = 0;

        try {
            for (int i = 0; i < nAidBoxes - 1; i++) {
                if (this.aidBoxes[i].getCode().equals("Base")) {
                    distance += this.aidBoxes[i + 1].getDistance(new BaseCollection());
                } else if (this.aidBoxes[i + 1].getCode().equals("Base")) {
                    distance += this.aidBoxes[i].getDistance(new BaseCollection());
                } else {
                    distance += this.aidBoxes[i].getDistance(this.aidBoxes[i + 1]);
                }
            }
        } catch (AidBoxException ex) {
            System.out.println("ERROR" + ex.getMessage());
            return 0;
        }

        return distance;
    }

    /**
     * Gets the total duration between all AidBoxes
     *
     * @return double
     */
    @Override
    public double getTotalDuration() {
        double duration = 0;

        try {
            for (int i = 0; i < nAidBoxes - 1; i++) {
                if (this.aidBoxes[i].getCode().equals("Base")) {
                    duration += this.aidBoxes[i + 1].getDuration(new BaseCollection());
                } else if (this.aidBoxes[i + 1].getCode().equals("Base")) {
                    duration += this.aidBoxes[i].getDuration(new BaseCollection());
                } else {
                    duration += this.aidBoxes[i].getDuration(this.aidBoxes[i + 1]);
                }
            }
        } catch (AidBoxException ex) {
            return 0;
        }

        return duration;
    }

    /**
     * Increase the capacity of the array aidBoxes[], to the double of the
     * current capacity in order to do this we create a temporary array with the
     * new size, and then we copy the old array to the newest one.
     *
     * @throws OutOfMemoryError When the programs runs out of memory.
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
     * @return int
     */
    private int findAidBox(AidBox paramAidBox) {

        for (int i = 0; i < nAidBoxes; i++) {
            if (aidBoxes[i].equals(paramAidBox)) {
                return i;
            }
        }

        return -1;
    }

    /**
     * Create a detailed string with all the routes information.
     *
     * @return String
     */
    public String toString() {
        return RouteImpl.printContainersRoute(this);
    }

    /**
     * Create a detailed string with the generated routes with the selected
     * vehicle and aid boxes to collect.
     *
     * @return String
     */
    public static String printContainersRoute(Route route) {

        Vehicle ve = route.getVehicle();
        int i = 0;

        AidBox[] aidboxes = route.getRoute();

        String s = "";
        s += "--------------------------\n";
        s += "SELECTED VEHICLE: " + '\n';
        s += "--------------------------\n";
        s += ve.toString();
        s += "--------------------------\n";
        s += "AIDBOXES TO COLLECT: " + '\n';
        s += "--------------------------\n";

        for (AidBox aid : aidboxes) {
            if (aid != null) {
                s += ++i + "- " + aid.getCode() + "\n";
            }
        }
        s += "-------";

        return s;
    }

}
