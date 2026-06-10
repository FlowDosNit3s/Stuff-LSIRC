package trabalho_pp.core;

import com.estg.core.Measurement;
import java.time.LocalDateTime;

public class MeasurementImpl implements Measurement {
    private LocalDateTime date;
    private double value;

    public MeasurementImpl(LocalDateTime date, double value) {
        this.date = date;
        this.value = value;
    }

    @Override
    public LocalDateTime getDate() {
        return this.date;
    }

    @Override
    public double getValue() {
        return this.value;
    }

    @Override
    public String toString() {
        return "[Date: " + date + ", Value: " + value + " kg]";
    }
}
