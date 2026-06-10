package trabalho_pp.distances;

import com.estg.core.exceptions.AidBoxException;

public class Distances {
    private static Distances instance;
    private Distance[] distances;
    private int count;
    private static final int INITIAL_CAPACITY = 100;

    private Distances() {
        this.distances = new Distance[INITIAL_CAPACITY];
        this.count = 0;
    }

    public static synchronized Distances getInstance() {
        if (instance == null) {
            instance = new Distances();
        }
        return instance;
    }

    public void addDistance(Distance distance) {
        if (distance == null) return;
        
        // Expand if full
        if (this.count == this.distances.length) {
            expand();
        }
        this.distances[this.count++] = distance;
    }

    private void expand() {
        Distance[] temp = new Distance[this.distances.length * 2];
        System.arraycopy(this.distances, 0, temp, 0, this.distances.length);
        this.distances = temp;
    }

    public double getDistance(String from, String to) throws AidBoxException {
        if (from.equals(to)) return 0.0;
        for (int i = 0; i < this.count; i++) {
            if (this.distances[i].getFrom().equalsIgnoreCase(from) && this.distances[i].getTo().equalsIgnoreCase(to)) {
                return this.distances[i].getDistance();
            }
        }
        throw new AidBoxException("Distance not found between " + from + " and " + to);
    }

    public double getDuration(String from, String to) throws AidBoxException {
        if (from.equals(to)) return 0.0;
        for (int i = 0; i < this.count; i++) {
            if (this.distances[i].getFrom().equalsIgnoreCase(from) && this.distances[i].getTo().equalsIgnoreCase(to)) {
                return this.distances[i].getDuration();
            }
        }
        throw new AidBoxException("Duration not found between " + from + " and " + to);
    }

    public double getDistance(com.estg.core.AidBox aidBoxFrom, com.estg.core.AidBox aidBoxTo) throws AidBoxException {
        return getDistance(aidBoxFrom.getCode(), aidBoxTo.getCode());
    }

    public double getDuration(com.estg.core.AidBox aidBoxFrom, com.estg.core.AidBox aidBoxTo) throws AidBoxException {
        return getDuration(aidBoxFrom.getCode(), aidBoxTo.getCode());
    }

    public double getDistance(com.estg.core.AidBox aidBoxFrom, com.estg.core.Institution institutionTo) throws AidBoxException {
        return getDistance(aidBoxFrom.getCode(), "Base");
    }

    public double getDistance(com.estg.core.Institution institutionFrom, com.estg.core.AidBox aidBoxTo) throws AidBoxException {
        return getDistance("Base", aidBoxTo.getCode());
    }

    public int getCount() {
        return this.count;
    }

    public void clear() {
        this.count = 0;
        this.distances = new Distance[INITIAL_CAPACITY];
    }
}
