package trabalho_pp.distances;

public class Distance {
    private String from;
    private String to;
    private double distance;
    private double duration;

    public Distance(String from, String to, double distance, double duration) {
        this.from = from;
        this.to = to;
        this.distance = distance;
        this.duration = duration;
    }

    public String getFrom() {
        return this.from;
    }

    public String getTo() {
        return this.to;
    }

    public double getDistance() {
        return this.distance;
    }

    public double getDuration() {
        return this.duration;
    }

    @Override
    public String toString() {
        return "From: " + from + " -> To: " + to + " (Distance: " + distance + "m, Duration: " + duration + "s)";
    }
}
