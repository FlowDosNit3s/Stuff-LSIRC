/*
Nome: Jose Pedro Vieira Barbosa
Número: 8230241
Turma: LEIT3
*
Nome: Carlos Alberto Moreira Barbosa
Número: 8230255
Turma: LSIRCT2
 */

package trabalho_pp.distances;

import com.estg.core.AidBox;
import com.estg.core.Institution;
import com.estg.core.exceptions.AidBoxException;

/**
 * Class responsible for the Distances Collection implementation
 */
public class Distances {

    /**
     * Singleton instance of the Distances class, to limit the creation of more instances than 1.
     */
    public static Distances instance;

    /**
     * The distance's array
     */
    private Distance[] distances;

    /**
     * The number of distances
     */
    private int nDistances;

    /**
     * The aidbox's initial number of containers
     */
    private static final int INITIAL_DISTANCES = 10;

    /**
     * Constructor for the Distances collection
     *
     * The nDistances is set as 0, the distances array is initialized with the initial size of 10.
     */
    private Distances() {
        this.nDistances = 0;
        this.distances = new Distance[INITIAL_DISTANCES];
    }

    /**
     * Method to check if there is already a created instance of Distances, if not creates one and return the instance.
     *
     * @return Distances
     */
    public static Distances getInstance() {
        if (instance == null) {
            instance = new Distances();
        }
        return instance;
    }

    /**
     * Method to add a given distance to the distances collection, if the array is full increases.
     * @param distance distance, and duratin between 2 aidboxes
     *
     */
    public void addDistance(Distance distance) {
        if (this.nDistances == this.distances.length) {
            increaseArray();
        }
        if (distance != null) {
            this.distances[this.nDistances++] = distance;
        }
    }

    /**
     * Creates a temporary array then copy the current array distances[] to the
     * temporary one and return.
     *
     * @return Distance[]
     */
    public Distance[] getDistances() {
        Distance[] tmpDistances = new Distance[this.nDistances];

        for (int i = 0; i < this.nDistances; i++) {
            tmpDistances[i] = this.distances[i];
        }

        return tmpDistances;
    }

    /**
     * Get distance between two given aid boxes.
     * 
     * @param aidBoxFrom The given aidboxFrom (source)
     * @param aidBoxTo The given aidboxTo (destination)
     * @return double
     * @throws AidBoxException if the distance between the aid boxes was not found.
     */
    public double getDistance(AidBox aidBoxFrom, AidBox aidBoxTo) throws AidBoxException {
        for (int i = 0; i < this.distances[0].getNumberOfDistances(); i++) {
            if (this.distances[i].getFrom().equals(aidBoxFrom.getCode()) && this.distances[i].getTo().equals(aidBoxTo.getCode())) {
                return this.distances[i].getDistance();
            }
        }

        throw new AidBoxException("The distance between this aid box was not found");
    }

     /**
     * Get distance between a given aid box and a given institution.
     * 
     * @param aidBoxFrom The given aidboxFrom (source)
     * @param institutionTo The given institution (destination)
     * @return double
     * @throws AidBoxException if the distance between the aid box and institution was not found.
     */
    public double getDistance(AidBox aidBoxFrom, Institution institutionTo) throws AidBoxException {
        for (int i = 0; i < this.distances[0].getNumberOfDistances(); i++) {
            if (this.distances[i].getFrom().equals(aidBoxFrom.getCode()) && this.distances[i].getTo().equals("Base")) {
                return this.distances[i].getDistance();
            }
        }

        throw new AidBoxException("The distance between this aid box and institution was not found");
    }

    /**
     * Get duration between two given aid boxes.
     * 
     * @param aidBoxFrom The given aidboxFrom (source)
     * @param aidBoxTo The given aidboxTo (destination)
     * @return double
     * @throws AidBoxException if the duration between the aid boxes was not found.
     */
    public double getDuration(AidBox aidBoxFrom, AidBox aidBoxTo) throws AidBoxException {
        for (int i = 0; i < this.distances[0].getNumberOfDistances(); i++) {
            if (this.distances[i].getFrom().equals(aidBoxFrom.getCode()) && this.distances[i].getTo().equals(aidBoxTo.getCode())) {
                return this.distances[i].getDuration();
            }
        }

        throw new AidBoxException("The duration between this aid box was not found");
    }


    /**
     * Increase the capacity of the distances array, to the double of the
     * current capacity in order to do this we create a temporary array with the
     * new size, and then we copy the old array to the newest one.
     *
     * @throws OutOfMemoryError when the programs runs out of memory
     */
    public void increaseArray() {
        Distance[] tmpDistances = new Distance[this.nDistances * 2];

        try {
            for (int i = 0; i < this.nDistances; i++) {
                tmpDistances[i] = this.distances[i];
            }
        } catch (OutOfMemoryError error) {
            throw new OutOfMemoryError();
        }

        this.distances = tmpDistances;
    }

    /**
     * Create a detailed string with all the distances information from collection.
     *
     * @return String
     */
    @Override
    public String toString() {
        String s = "";
        for (Distance distance : this.distances) {
            if (distance != null) {
                s += distance.toString();
                s += "---\n";
            }
        }
        return s;
    }
}
