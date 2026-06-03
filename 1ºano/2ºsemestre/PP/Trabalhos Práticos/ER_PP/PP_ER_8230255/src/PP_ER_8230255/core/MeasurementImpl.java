/*  
* Nome: Carlos Alberto Moreira Barbosa
* Número: 8230255
* Turma: LSIRC11T1
 */
package PP_ER_8230255.core;

import com.estg.core.Measurement;
import java.io.Serializable;
import java.time.LocalDateTime;

public class MeasurementImpl implements Measurement, Cloneable, Serializable {

    // The Measurement Date
    private final LocalDateTime date;

    // The Measurement Value (Kg)
    private final double value;

    /**
     * the constructor for the measurement
     *
     * @param date the measurement date
     * @param value the value
     */
    public MeasurementImpl(LocalDateTime date, double value) {
        this.date = date;
        this.value = value;
    }

    /**
     * Getter for measurement date
     *
     * @return measurement date
     */
    @Override
    public LocalDateTime getDate() {
        return this.date;
    }

    /**
     * Getter for measurement value (Kg)
     *
     * @return measurement value (Kg)
     */
    @Override
    public double getValue() {
        return value;
    }

    /**
     * creates a clone of a measurement
     *
     * @return a copy of a measurement
     * @throws CloneNotSupportedException if the object class does not support
     * the interface(Cloneable)
     */
    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    /**
     * Returns the measurement info
     *
     * @return String with all the measurement info
     */
    @Override
    public String toString() {
        String s = "MEASUREMENT:\n";
        s += "DATA: " + this.date + "\n";
        s += "VALUE: " + this.value + "\n";

        return s;
    }
}
