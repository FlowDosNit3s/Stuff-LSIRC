package trabalho_pp.pickingManagement;

import trabalho_pp.core.AidBoxImpl;
import trabalho_pp.core.GeographicCoordinatesImpl;
import trabalho_pp.core.ContainerImpl;
import com.estg.core.Container;
import com.estg.core.ItemType;
import com.estg.core.exceptions.ContainerException;

public class BaseCollection extends AidBoxImpl {

    public BaseCollection() {
        super("Base", "BaseStation", new GeographicCoordinatesImpl(0.0, 0.0));
        try {
            // Add dummy empty containers for all types to be compatible with any vehicle
            this.addContainer(new ContainerImpl("N-BASE", 0.0, ItemType.NON_PERISHABLE_FOOD));
            this.addContainer(new ContainerImpl("V-BASE", 0.0, ItemType.CLOTHING));
            this.addContainer(new ContainerImpl("M-BASE", 0.0, ItemType.MEDICINE));
            this.addContainer(new ContainerImpl("P-BASE", 0.0, ItemType.PERISHABLE_FOOD));
        } catch (ContainerException e) {
            // ignore
        }
    }
}
