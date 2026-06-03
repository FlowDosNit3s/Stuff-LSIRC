/*  
* Nome: Carlos Alberto Moreira Barbosa
* Número: 8230255
* Turma: LSIRC11T1
 */
package PP_ER_8230255.pickingManagement;

import com.estg.pickingManagement.*;
import PP_ER_8230255.core.AidBoxImpl;
import com.estg.core.AidBox;
import com.estg.core.Container;
import com.estg.core.exceptions.AidBoxException;
import com.estg.pickingManagement.exceptions.RouteException;
import java.io.Serializable;

public class RouteImpl implements Route, Serializable {

    // The vehicle responsible for the route
    private Vehicle vehicle;

    // The aidboxes covered by teh route
    private AidBox[] aidboxes;

    // The number of aidboxes
    private int nAidBoxes;

    // The route report
    private Report report;

    // The initial size for the aid boxes array
    private final int INITIAL_AIDBOX_SIZE = 10;

    /**
     * The constructor for the routes
     *
     * @param vehicle the vehicle responsible for the route
     */
    public RouteImpl(Vehicle vehicle) {
        this.vehicle = vehicle;
        this.aidboxes = new AidBox[INITIAL_AIDBOX_SIZE];
        this.nAidBoxes = 0;
    }

    /**
     * The constructor for the routes, this constructor is used whem we need an
     * exact copy of an other route
     *
     * @param vehicle the vehicle responsible for the route
     * @param aidboxes the aidboxes in the route
     * @param report the route report
     */
    public RouteImpl(Vehicle vehicle, AidBox[] aidboxes, Report report) {
        this.vehicle = vehicle;
        this.aidboxes = aidboxes;
        this.nAidBoxes = aidboxes.length;
        this.report = report;
    }

    /**
     * The constructor for the routes
     *
     * @param vehicle the vehicle responsible for the route
     * @param aidboxes the aidboxes in the route
     */
    public RouteImpl(Vehicle vehicle, AidBox[] aidboxes) {
        this.vehicle = vehicle;
        this.aidboxes = aidboxes;
        this.nAidBoxes = aidboxes.length;
        this.report = new ReportImpl();
    }

    /**
     * Adds a new Aid Box to the route
     *
     * @param aidbox the Aid Box to add
     * @throws RouteException if the Aid Box is null, if the Aid Box is already
     * in the route, if the Aid Box is not compatible (doesn't have a container
     * that can be picked by the vehicle) with the Vehicle of the route
     */
    @Override
    public void addAidBox(AidBox aidbox) throws RouteException {
        incAidBoxesArray();
        if (aidbox == null) {
            throw new RouteException("The Aid Box can't be null");
        }

        if (containsAidBox(aidbox)) {
            throw new RouteException("The Aid Box already exists on the route");
        }

        if (!compatibleOnRoute(aidbox)) {
            throw new RouteException("The vehicle cant pick any container");
        }

        this.aidboxes[this.nAidBoxes++] = aidbox;
    }

    /**
     * Removes an Aid Box from the route
     *
     * @param paramAidBox the Aid Box to remove
     * @return AidBox the removed Aid Box
     * @throws RouteException if the Aid Box parameter is null or if the Aid Box
     * is not in the route
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

        AidBox removedAidBox = this.aidboxes[index];
        for (int i = index; i < this.nAidBoxes - 1; i++) {
            this.aidboxes[i] = this.aidboxes[i + 1];
        }

        this.aidboxes[--this.nAidBoxes] = null;
        return removedAidBox;
    }

    /**
     * Checks if the route contains an Aid Box
     *
     * @param aidbox the Aid Box to check
     * @return true if the route contains the Aid Box, false otherwise
     */
    @Override
    public boolean containsAidBox(AidBox aidbox) {
        return findAidBox(aidbox) != -1;
    }

    /**
     * Replaces an Aid Box from the route
     *
     * @param aidbox the Aid Box to replace
     * @param aidbox1 the Aid Box to replace with
     * @throws RouteException if the Aid Box cannot be added to the route: if
     * any Aid Box is null if the Aid Box to replace is not in the route, if the
     * Aid Box to insert is already in the route, if the Aid Box to insert is
     * not compatible (doesn't have a container that can be picked by the
     * vehicle) with the Vehicle of the route
     */
    @Override
    public void replaceAidBox(AidBox aidbox, AidBox aidbox1) throws RouteException {

        if (aidbox == null || aidbox1 == null) {
            throw new RouteException("The Aidbox can't be null");
        }

        int index1 = findAidBox(aidbox);
        int index2 = findAidBox(aidbox1);

        if (index1 == -1) {
            throw new RouteException("The Aid Box to replace is not in the route");
        }

        if (index2 != -1) {
            throw new RouteException("The Aid Box to insert is already in the route");
        }

        if (!compatibleOnRoute(aidbox1)) {
            throw new RouteException("The AidBox to insert is not compatible with the Vehicle of the route");
        }

        this.aidboxes[index1] = aidbox1;
    }

    /**
     * Inserts an Aid Box before another Aid Box in the route
     *
     * @param after the Aid Box to insert before
     * @param toInsert the Aid Box to insert
     * @throws RouteException if any Aid Box is null, if the Aid Box to replace
     * is not in the route, if the Aid Box to insert is already in the route, if
     * the Aid Box to insert is not compatible (doesn't have a container that
     * can be picked by the vehicle) with the Vehicle of the route
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

        if (!compatibleOnRoute(toInsert)) {
            throw new RouteException("The AidBox to insert is not compatible with the Vehicle of the route");
        }

        incAidBoxesArray();

        for (int i = this.nAidBoxes - 1; i > index; i--) {
            this.aidboxes[i + 1] = this.aidboxes[i];
        }

        this.aidboxes[index + 1] = toInsert;
        this.nAidBoxes++;
    }

    /**
     * Getter for a deep copy of the route
     *
     * @return the route
     */
    @Override
    public AidBox[] getRoute() {
        AidBox[] temp = new AidBox[this.nAidBoxes];
        int counter = 0;

        for (AidBox aidbox : this.aidboxes) {
            if (aidbox != null) {
                try {
                    temp[counter++] = (AidBox) ((AidBoxImpl) aidbox).clone();
                } catch (CloneNotSupportedException ex) {
                    System.out.println("ERROR WHILE CLONING AID BOXES");
                }

            }
        }
        return temp;
    }

    /**
     * Getter for the vehicle of the route
     *
     * @return the vehicle of the route
     */
    @Override
    public Vehicle getVehicle() {
        Vehicle newVehicle = new VehicleImpl(this.vehicle.getCode(),
                ((VehicleImpl) this.vehicle).getCapacities(),
                ((VehicleImpl) this.vehicle).getStatus());

        return newVehicle;
    }

    /**
     * gets the total distance covered by the route
     *
     * @return the total distance
     */
    @Override
    public double getTotalDistance() {
        double totalDistance = 0;
        for (int i = 0; i < this.nAidBoxes - 1; i++) {
            try {
                totalDistance += this.aidboxes[i].getDistance(this.aidboxes[i + 1]);
            } catch (AidBoxException ex) {
                totalDistance += 0;
            }

        }
        return totalDistance;
    }

    /**
     * gets the total duration covered by the route
     *
     * @return the total duration
     */
    @Override
    public double getTotalDuration() {
        double totalDuration = 0;
        for (int i = 0; i < this.nAidBoxes - 1; i++) {
            try {
                totalDuration += this.aidboxes[i].getDuration(this.aidboxes[i + 1]);
            } catch (AidBoxException ex) {
                totalDuration += 0;
            }

        }
        return totalDuration;
    }

    /**
     * Getter for route report
     *
     * @return the generated route report
     */
    @Override
    public Report getReport() {
        try {
            return (Report) ((ReportImpl) this.report).clone();
        } catch (CloneNotSupportedException ex) {
            System.out.println("ERROR WHILE CLONING REPORT");
            return null;
        }
    }

    /**
     * Find if a Aid Box exists in the array
     *
     * @param ad a Aid Box
     * @return the index of the aid box or -1 if the aid box dont exist
     */
    private int findAidBox(AidBox ad) {
        for (int i = 0; i < this.nAidBoxes; i++) {
            if (ad.equals(this.aidboxes[i])) {
                return i;
            }
        }
        return -1;
    }

    /**
     * if the array is full, the function duplicates te array size by 2
     */
    public void incAidBoxesArray() {
        if (this.nAidBoxes == this.aidboxes.length) {
            AidBox[] temp = new AidBox[this.nAidBoxes * 2];
            System.arraycopy(this.aidboxes, 0, temp, 0, this.nAidBoxes);
            this.aidboxes = temp;
        }
    }

    /**
     * See if the aidbox is valid on the route
     *
     * @param ad a Aid Box
     * @return true if is valid or false if is not valid
     */
    private boolean compatibleOnRoute(AidBox ad) {
        Container[] conts = ad.getContainers();

        for (Container cont : conts) {
            if (this.vehicle.getCapacity(cont.getType()) > 0) {
                return true;
            }
        }
        return false;
    }

    /**
     * set the report info
     *
     * @param report the report to the route
     */
    public void setReport(Report report) {
        this.report = report;
    }

    /**
     * Gets a string with all the info
     *
     * @return an string with the route info
     */
    @Override
    public String toString() {
        String s = "ROUTE:\n";
        s += this.vehicle.toString();
        s += "AIDBOXES:\n";

        for (AidBox aid : this.aidboxes) {
            if (aid != null) {
                s += "- " + aid.getCode() + "\n";
            }
        }

        return s;
    }
}
