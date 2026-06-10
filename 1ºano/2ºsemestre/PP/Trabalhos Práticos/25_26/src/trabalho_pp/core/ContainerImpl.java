package trabalho_pp.core;

import com.estg.core.Container;
import com.estg.core.ItemType;
import com.estg.core.Measurement;
import com.estg.core.exceptions.MeasurementException;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class ContainerImpl implements Container {
    private String code;
    private double capacity;
    private ItemType itemType;
    private Measurement[] measurements;
    private int measurementCount;
    private static final int INITIAL_CAPACITY = 10;

    public ContainerImpl(String code, double capacity, ItemType itemType) {
        this.code = code;
        this.capacity = capacity;
        this.itemType = itemType;
        this.measurements = new Measurement[INITIAL_CAPACITY];
        this.measurementCount = 0;
    }

    @Override
    public String getCode() {
        return this.code;
    }

    @Override
    public double getCapacity() {
        return this.capacity;
    }

    @Override
    public ItemType getType() {
        return this.itemType;
    }

    @Override
    public Measurement[] getMeasurements() {
        Measurement[] copy = new Measurement[this.measurementCount];
        for (int i = 0; i < this.measurementCount; i++) {
            Measurement m = this.measurements[i];
            copy[i] = new MeasurementImpl(m.getDate(), m.getValue());
        }
        return copy;
    }

    @Override
    public Measurement[] getMeasurements(LocalDate date) {
        int count = 0;
        for (int i = 0; i < this.measurementCount; i++) {
            if (this.measurements[i].getDate().toLocalDate().equals(date)) {
                count++;
            }
        }
        Measurement[] copy = new Measurement[count];
        int idx = 0;
        for (int i = 0; i < this.measurementCount; i++) {
            if (this.measurements[i].getDate().toLocalDate().equals(date)) {
                Measurement m = this.measurements[i];
                copy[idx++] = new MeasurementImpl(m.getDate(), m.getValue());
            }
        }
        return copy;
    }

    @Override
    public boolean addMeasurement(Measurement measurement) throws MeasurementException {
        if (measurement == null) {
            throw new MeasurementException("Measurement cannot be null");
        }
        if (measurement.getValue() < 0) {
            throw new MeasurementException("Measurement value cannot be negative");
        }
        if (this.measurementCount > 0) {
            LocalDateTime lastDate = this.measurements[this.measurementCount - 1].getDate();
            if (measurement.getDate().isBefore(lastDate)) {
                throw new MeasurementException("Measurement date is before the last recorded measurement date");
            }
        }

        // Check if measurement already exists for this date/time
        for (int i = 0; i < this.measurementCount; i++) {
            if (this.measurements[i].getDate().equals(measurement.getDate())) {
                if (this.measurements[i].getValue() != measurement.getValue()) {
                    throw new MeasurementException("Measurement already exists for this date but with a different value");
                }
                return false; // already exists with same value
            }
        }

        // Expand array if full
        if (this.measurementCount == this.measurements.length) {
            expandMeasurements();
        }

        this.measurements[this.measurementCount++] = measurement;
        return true;
    }

    private void expandMeasurements() {
        Measurement[] temp = new Measurement[this.measurements.length * 2];
        System.arraycopy(this.measurements, 0, temp, 0, this.measurements.length);
        this.measurements = temp;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        ContainerImpl cloned = (ContainerImpl) super.clone();
        cloned.measurements = new Measurement[this.measurements.length];
        for (int i = 0; i < this.measurementCount; i++) {
            cloned.measurements[i] = new MeasurementImpl(this.measurements[i].getDate(), this.measurements[i].getValue());
        }
        cloned.measurementCount = this.measurementCount;
        return cloned;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || !(obj instanceof Container)) return false;
        Container other = (Container) obj;
        return this.code.equals(other.getCode());
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Container: ").append(code).append(" (").append(itemType).append(", Capacity: ").append(capacity).append(" kg)\n");
        sb.append("  Measurements:\n");
        for (int i = 0; i < this.measurementCount; i++) {
            sb.append("    ").append(this.measurements[i].toString()).append("\n");
        }
        return sb.toString();
    }
}
