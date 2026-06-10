package trabalho_pp.pickingManagement;

import com.estg.core.ItemType;
import com.estg.pickingManagement.Vehicle;

public abstract class VehicleImpl implements Vehicle, Cloneable {
    private String id;
    private ItemType supplyType;
    private double maxCapacity;
    private boolean active;

    public VehicleImpl(String id, ItemType supplyType, double maxCapacity) {
        this.id = id;
        this.supplyType = supplyType;
        this.maxCapacity = maxCapacity;
        this.active = true;
    }

    public String getId() {
        return this.id;
    }

    @Override
    public ItemType getSupplyType() {
        return this.supplyType;
    }

    @Override
    public double getMaxCapacity() {
        return this.maxCapacity;
    }

    public boolean isActive() {
        return this.active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || !(obj instanceof VehicleImpl)) return false;
        VehicleImpl other = (VehicleImpl) obj;
        return this.id.equals(other.id);
    }

    @Override
    public String toString() {
        return "Vehicle ID: " + id + " [" + supplyType + ", Max Cap: " + maxCapacity + " kg, Active: " + active + "]";
    }
}
