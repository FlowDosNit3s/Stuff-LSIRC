package trabalho_pp.pickingManagement;

import com.estg.core.ItemType;

public class RefrigeratedVehiclesImpl extends VehicleImpl {
    private double maxKmWithLoad; // stored in meters
    private static final double DEFAULT_MAX_DIST = 30000.0; // 30 km

    public RefrigeratedVehiclesImpl(String id, double maxCapacity) {
        super(id, ItemType.PERISHABLE_FOOD, maxCapacity);
        this.maxKmWithLoad = DEFAULT_MAX_DIST;
    }

    public RefrigeratedVehiclesImpl(String id, double maxCapacity, double maxKmWithLoad) {
        super(id, ItemType.PERISHABLE_FOOD, maxCapacity);
        this.maxKmWithLoad = maxKmWithLoad;
    }

    public double getMaxKmWithLoad() {
        return this.maxKmWithLoad;
    }

    public void setMaxKmWithLoad(double maxKmWithLoad) {
        this.maxKmWithLoad = maxKmWithLoad;
    }

    @Override
    public String toString() {
        return super.toString() + " [Refrigerated, Max Dist with load: " + maxKmWithLoad + "m]";
    }
}
