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

import trabalho_pp.core.GeographicCoordinatesImpl;
import trabalho_pp.core.ContainerImpl;
import trabalho_pp.core.AidBoxImpl;
import com.estg.core.ItemType;
import com.estg.core.Container;

/**
 * Class responsible for the Base Collection implementation
 */
public class BaseCollection extends AidBoxImpl {

  /**
   * Constructor for the Base Collection
   * we create a default aidbox on the Base to simulate the start/finish of the route to help with the strategy implementation.
   *
   */
  public BaseCollection() {
    super("Base", "Institution", new GeographicCoordinatesImpl(0.0, 0.0),
            new Container[]{new ContainerImpl("CLOTHING", 0, ItemType.CLOTHING), new ContainerImpl("MEDICINE", 0, ItemType.MEDICINE),
                    new ContainerImpl("PERISHABLE_FOOD", 0, ItemType.PERISHABLE_FOOD), new ContainerImpl("NON_PERISHABLE_FOOD", 0, ItemType.NON_PERISHABLE_FOOD)});;
  }
}
