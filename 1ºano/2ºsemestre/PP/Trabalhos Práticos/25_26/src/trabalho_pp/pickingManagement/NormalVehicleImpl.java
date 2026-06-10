package trabalho_pp.pickingManagement;

import com.estg.core.ItemType;
import com.estg.core.exceptions.VehicleException;

public class NormalVehicleImpl extends VehicleImpl {

    public NormalVehicleImpl(String id, ItemType supplyType, double maxCapacity) throws VehicleException {
        super(id, validateSupplyType(supplyType), maxCapacity);
    }

    private static ItemType validateSupplyType(ItemType type) throws VehicleException {
        if (type == ItemType.PERISHABLE_FOOD) {
            throw new VehicleException("Normal vehicles cannot transport PERISHABLE_FOOD");
        }
        return type;
    }
}
