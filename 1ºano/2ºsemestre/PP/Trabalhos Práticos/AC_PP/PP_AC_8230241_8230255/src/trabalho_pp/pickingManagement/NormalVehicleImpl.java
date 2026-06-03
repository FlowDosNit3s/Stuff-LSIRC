/*
Nome: Jose Pedro Vieira Barbosa
Número: 8230241
Turma: LEIT3
*
Nome: Carlos Alberto Moreira Barbosa
Número: 8230255
Turma: LSIRCT2
*/
package trabalho_pp.pickingManagement;

import com.estg.core.ItemType;
import com.estg.core.exceptions.VehicleException;

/**
 * Class responsible for the Normal Vehicle implementation
 */
public class NormalVehicleImpl extends VehicleImpl {

    /**
     * Constructor for the Normal Vehicle
     *
     * @param type - the vehicle item type
     * @param maxCapacity - max capacity of the vehicle.
     */
    public NormalVehicleImpl(ItemType type, double maxCapacity) throws VehicleException {
        super(validateItemType(type), maxCapacity);
    }

    /**
     * Validator for the item type, in order to check if the vehicle item type is Perishable food.
     *
     * @throws VehicleException If the provided vehicle item type is Perishable Food ,which is not allowed for Normal Vehicles.
     * @param type - The vehicle item type
     */
    private static ItemType validateItemType(ItemType type) throws VehicleException {
        if (type == ItemType.PERISHABLE_FOOD) {
            throw new VehicleException("PERISHABLE FOOD items are not allowed in Normal Vehicles.");
        }
        return type;
    }
}

