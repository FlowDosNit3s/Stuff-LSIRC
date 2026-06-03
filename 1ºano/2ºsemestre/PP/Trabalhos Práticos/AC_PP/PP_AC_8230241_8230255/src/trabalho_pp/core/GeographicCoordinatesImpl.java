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

import com.estg.core.GeographicCoordinates;

/** Class responsible for the Geographic Coordinates */
public class GeographicCoordinatesImpl implements GeographicCoordinates {
    
    /** The geographic latitude */
    private double latitude;
    
    /** The geographic longitude */
    private double longitude;
    
    /**
     * Constructor for the Geographic Coordinates
     * @param latitude - geographic latitude
     * @param longitude - geographic longitude
     */
    public GeographicCoordinatesImpl(double latitude, double longitude){
        this.latitude = latitude;
        this.longitude = longitude;
    }
    
    /**
     * Gets the geographic latitude 
     * @return double
     */
    @Override
    public double getLatitude(){
        return this.latitude;
    }
    
     /**
     * Gets the geographic longitude 
     * @return double
     */
    @Override
    public double getLongitude(){
        return this.longitude;
    }
    
}