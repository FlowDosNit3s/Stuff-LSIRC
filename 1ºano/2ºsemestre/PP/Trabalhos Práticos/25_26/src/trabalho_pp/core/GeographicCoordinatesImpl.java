package trabalho_pp.core;

import com.estg.core.GeographicCoordinates;

public class GeographicCoordinatesImpl implements GeographicCoordinates {
    private double latitude;
    private double longitude;

    public GeographicCoordinatesImpl(double latitude, double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    @Override
    public double getLatitude() {
        return this.latitude;
    }

    @Override
    public double getLongitude() {
        return this.longitude;
    }

    @Override
    public String toString() {
        return "Lat: " + latitude + ", Lng: " + longitude;
    }
}
