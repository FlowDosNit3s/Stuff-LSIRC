/*  
* Nome: Carlos Alberto Moreira Barbosa
* Número: 8230255
* Turma: LSIRC11T1
 */
package PP_ER_8230255.core;

import java.io.Serializable;

public class DistanceToOtherDestination implements Serializable {

    // The name of the destination
    private final String name;

    // The duration to the destination
    private final double duration;

    // The distance to the destination
    private final double distance;

    /**
     * Constructor for the distance to other aidbox
     *
     * @param name the destination aid box name
     * @param duration the duration between the 2 aidbox
     * @param distance the distance between the aidboxes
     */
    public DistanceToOtherDestination(String name, double duration, double distance) {
        this.name = name;
        this.duration = duration;
        this.distance = distance;
    }

    /**
     * Getter for the destination name
     *
     * @return the destination name
     */
    protected String getName() {
        return this.name;
    }

    /**
     * Getter for the duration
     *
     * @return the duration to the destination
     */
    protected double getDuration() {
        return this.duration;
    }

    /**
     * Getter for the distance
     *
     * @return the distance to the destination
     */
    protected double getDistance() {
        return this.distance;
    }
}
