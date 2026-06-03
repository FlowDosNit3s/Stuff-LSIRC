/*  
* Nome: Carlos Alberto Moreira Barbosa
* Número: 8230255
* Turma: LSIRC11T1
 */
package PP_ER_8230255.pickingManagement;

import com.estg.pickingManagement.Report;
import java.io.Serializable;
import java.time.LocalDateTime;

public class ReportImpl implements Report, Cloneable, Serializable {

    // The Report Data
    private int usedVehicles;
    private int pickedContainers;
    private double totalDistance;
    private double totalDuration;
    private int nonPickedContainers;
    private int notUsedVehicles;
    private final LocalDateTime date;

    /**
     * the constructor for an picking map, all the variable are initilizate to 0
     */
    public ReportImpl() {
        this.usedVehicles = 0;
        this.pickedContainers = 0;
        this.totalDistance = 0;
        this.totalDuration = 0;
        this.nonPickedContainers = 0;
        this.notUsedVehicles = 0;
        this.date = LocalDateTime.now();
    }

    /**
     * set data in report
     * @param usedVehicles the number of used vehicles in the route
     * @param pickedContainers the number of picked containers in the route
     * @param totalDistance the total distance of the routes
     * @param totalDuration the total duration of the routes
     * @param nonPickedContainers the number of non picked containers
     * @param notUsedVehicles the number of non used vehicles
     */
    public void setData(int usedVehicles, int pickedContainers, double totalDistance, double totalDuration, int nonPickedContainers, int notUsedVehicles) {
        this.usedVehicles = usedVehicles;
        this.pickedContainers = pickedContainers;
        this.totalDistance = totalDistance;
        this.totalDuration = totalDuration;
        this.nonPickedContainers = nonPickedContainers;
        this.notUsedVehicles = notUsedVehicles;
    }

    /**
     * Getter for the number of used vehicles
     *
     * @return the number of used vehicles
     */
    @Override
    public int getUsedVehicles() {
        return this.usedVehicles;
    }

    /**
     * Getter for the number of picked containers
     *
     * @return the number of picked containers
     */
    @Override
    public int getPickedContainers() {
        return this.pickedContainers;
    }

    /**
     * Getter for the total distance covered by the vehicles
     *
     * @return the total distance covered by the vehicles
     */
    @Override
    public double getTotalDistance() {
        return this.totalDistance;
    }

    /**
     * Getter for the total duration of the routes
     *
     * @return the total duration of the routes
     */
    @Override
    public double getTotalDuration() {
        return this.totalDuration;
    }

    /**
     * Getter for the number of non-picked containers
     *
     * @return the number of non-picked containers
     */
    @Override
    public int getNonPickedContainers() {
        return this.nonPickedContainers;
    }

    /**
     * Getter for the number of not used vehicles
     *
     * @return the number of not used vehicles
     */
    @Override
    public int getNotUsedVehicles() {
        return this.notUsedVehicles;
    }

    /**
     * Getter for the date of the report
     *
     * @return the date of the report
     */
    @Override
    public LocalDateTime getDate() {
        return this.date;
    }

    /**
     * creates a clone of a report
     *
     * @return a copy of a report
     * @throws CloneNotSupportedException if the object class does not support
     * the interface(Cloneable)
     */
    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    /**
     * gets a string with all the report info
     *
     * @return an string with all the report info
     */
    public String toString() {
        String s = "REPORT:\n";
        s += "DATE: " + this.date + '\n';
        s += "USED VEHICLES: " + this.usedVehicles + '\n';
        s += "NON USED VEHICLES: " + this.notUsedVehicles + '\n';
        s += "PICKED CONTAINERS: " + this.pickedContainers + '\n';
        s += "NON PICKED CONTAINERS: " + this.nonPickedContainers + '\n';
        s += "TOTAL DISTANCE: " + this.totalDistance + '\n';
        s += "TOTAL DUTAION: " + this.totalDuration + '\n';

        return s;
    }

}
