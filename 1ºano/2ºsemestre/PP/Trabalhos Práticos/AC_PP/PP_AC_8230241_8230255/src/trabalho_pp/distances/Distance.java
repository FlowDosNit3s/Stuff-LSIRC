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

/**
 * Class responsible for the Distance implementation
 */
public class Distance {

    /**
     * The "Source"
     */
    private String from;

    /**
     * The "Destination"
     */
    private String to;

    /**
     * The distance
     */
    private double distance;

    /**
     * The duration
     */
    private double duration;

    /**
     * The number of existing distances.
     */
    private static int numberDistances = 0;

    /**
     * Constructor for the Distance information
     *
     * @param from - The "source".
     * @param to - The "destination".
     * @param distance - The distance.
     * @param duration - The duration.
     *
     */
    public Distance(String from, String to, double distance, double duration) {
        this.from = from;
        this.to = to;
        this.distance = distance;
        this.duration = duration;
        Distance.numberDistances++;
    }

    /**
     * Gets the "source"
     *
     * @return String
     */
    public String getFrom() {
        return this.from;
    }

    /**
     * Gets the "destination"
     *
     * @return String
     */
    public String getTo() {
        return this.to;
    }

    /**
     * Gets the distance
     *
     * @return double
     */
    public double getDistance() {
        return this.distance;
    }

    /**
     * Gets the duration
     *
     * @return double
     */
    public double getDuration() {
        return this.duration;
    }

    /**
     * Gets the number of distances
     *
     * @return int
     */
    public int getNumberOfDistances() {
        return Distance.numberDistances;
    }

    /**
     * Create a detailed string with all the Distance information.
     *
     * @return String
     */
    public String toString(){
        String s = "From: " + this.getFrom() + '\n';
        s += "To: " + this.getTo() + '\n';
        s += "Distance: " + this.getDistance() + '\n';
        s += "Duration: " + this.getDuration() + '\n';
        
        return s;
    }
}
