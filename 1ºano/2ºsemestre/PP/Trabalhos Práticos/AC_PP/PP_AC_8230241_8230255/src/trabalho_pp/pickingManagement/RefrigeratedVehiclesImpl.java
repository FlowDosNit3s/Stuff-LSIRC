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

/** Class responsible for the Refrigerated Vehicle implementation */
public class RefrigeratedVehiclesImpl extends VehicleImpl {
    /**
     * Constructor for the Refrigerated Vehicle
     * @param maxCapacity - max capacity of the Vehicle
     * The vehicle item type is automatically set to Perishable Food.
     */
    public RefrigeratedVehiclesImpl(double maxCapacity) throws VehicleException {
        super(ItemType.PERISHABLE_FOOD, maxCapacity);
    }

}
