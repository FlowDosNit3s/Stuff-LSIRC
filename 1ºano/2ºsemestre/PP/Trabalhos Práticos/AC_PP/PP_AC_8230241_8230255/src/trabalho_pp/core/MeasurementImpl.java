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

import java.time.LocalDateTime;
import com.estg.core.Measurement;

/** Class responsible for the Measurement implementation */
public class MeasurementImpl implements Measurement {
    
    /** The measurement's date */
    private LocalDateTime date;
    
    /** The measurements's value */
    private double value;
    
    /**
     * Constructor for the Measurement
     * @param date - date of the Measurement
     * @param value - value of the Measurement
     */
    public MeasurementImpl(LocalDateTime date, double value){
        this.date = date;
        this.value = value;
    }
    
    /**
     * Gets the date of the Measurement
     * @return LocalDateTime
     */
    @Override
    public LocalDateTime getDate(){
        return this.date;
    }
    
    /**
     * Gets the value of the Measurement
     * @return double
     */
    @Override
    public double getValue(){
        return this.value;
    }
    
    /**
     * Compare if the measurement date is equals
     * @param obj - Object
     * @return boolean
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Measurement)) {
            return false;
        }

        Measurement measurement = (Measurement) obj;
        return this.date.equals(measurement.getDate());
    }

    /**
     * Create a detailed string with all the measurement information.
     * @return String
     */
    public String toString(){
        String s = "Date: " + getDate() + '\n';
        s += "Value: " + getValue() + '\n';

        return s;
    }
}