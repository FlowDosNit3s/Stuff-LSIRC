package trabalho_pp.pickingManagement;

import com.estg.core.AidBox;
import com.estg.core.Container;
import com.estg.core.exceptions.AidBoxException;
import com.estg.pickingManagement.Route;
import com.estg.pickingManagement.Vehicle;
import com.estg.pickingManagement.exceptions.RouteException;

public class RouteImpl implements Route {
    private AidBox[] aidBoxes;
    private int nAidBoxes;
    private Vehicle vehicle;
    private static final int INITIAL_CAPACITY = 10;

    public RouteImpl(Vehicle vehicle) {
        this.vehicle = vehicle;
        this.nAidBoxes = 0;
        this.aidBoxes = new AidBox[INITIAL_CAPACITY];
    }

    @Override
    public void addAidBox(AidBox aidBox) throws RouteException {
        if (aidBox == null) {
            throw new RouteException("AidBox cannot be null");
        }

        // Allow multiple "Base" instances in the route (e.g. start and end)
        if (!aidBox.getCode().equalsIgnoreCase("Base")) {
            if (containsAidBox(aidBox)) {
                throw new RouteException("AidBox is already in the route");
            }
        }

        // Verify compatibility: AidBox must contain a container of the vehicle's supply type
        // Unless it is the BaseCollection
        if (!aidBox.getCode().equalsIgnoreCase("Base")) {
            Container c = aidBox.getContainer(this.vehicle.getSupplyType());
            if (c == null) {
                throw new RouteException("AidBox is not compatible with the vehicle's supply type: " + this.vehicle.getSupplyType());
            }
        }

        if (this.nAidBoxes == this.aidBoxes.length) {
            expand();
        }

        this.aidBoxes[this.nAidBoxes++] = aidBox;
    }

    private void expand() {
        AidBox[] temp = new AidBox[this.aidBoxes.length * 2];
        System.arraycopy(this.aidBoxes, 0, temp, 0, this.aidBoxes.length);
        this.aidBoxes = temp;
    }

    @Override
    public AidBox removeAidBox(AidBox aidBox) throws RouteException {
        if (aidBox == null) {
            throw new RouteException("AidBox cannot be null");
        }

        int index = findAidBox(aidBox);
        if (index == -1) {
            throw new RouteException("AidBox is not in the route");
        }

        AidBox removed = this.aidBoxes[index];
        for (int i = index; i < this.nAidBoxes - 1; i++) {
            this.aidBoxes[i] = this.aidBoxes[i + 1];
        }
        this.aidBoxes[--this.nAidBoxes] = null;
        return removed;
    }

    private int findAidBox(AidBox aidBox) {
        for (int i = 0; i < this.nAidBoxes; i++) {
            if (this.aidBoxes[i].equals(aidBox)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public boolean containsAidBox(AidBox aidBox) {
        return findAidBox(aidBox) != -1;
    }

    @Override
    public void replaceAidBox(AidBox from, AidBox to) throws RouteException {
        if (from == null || to == null) {
            throw new RouteException("AidBox parameters cannot be null");
        }

        int indexFrom = findAidBox(from);
        if (indexFrom == -1) {
            throw new RouteException("AidBox to replace is not in the route");
        }

        if (!to.getCode().equalsIgnoreCase("Base") && containsAidBox(to)) {
            throw new RouteException("New AidBox is already in the route");
        }

        // Verify compatibility
        if (!to.getCode().equalsIgnoreCase("Base")) {
            Container c = to.getContainer(this.vehicle.getSupplyType());
            if (c == null) {
                throw new RouteException("New AidBox is not compatible with the vehicle's supply type: " + this.vehicle.getSupplyType());
            }
        }

        this.aidBoxes[indexFrom] = to;
    }

    @Override
    public void insertAfter(AidBox after, AidBox toInsert) throws RouteException {
        if (after == null || toInsert == null) {
            throw new RouteException("AidBox parameters cannot be null");
        }

        int indexAfter = findAidBox(after);
        if (indexAfter == -1) {
            throw new RouteException("The 'after' AidBox is not in the route");
        }

        if (!toInsert.getCode().equalsIgnoreCase("Base") && containsAidBox(toInsert)) {
            throw new RouteException("The AidBox to insert is already in the route");
        }

        // Verify compatibility
        if (!toInsert.getCode().equalsIgnoreCase("Base")) {
            Container c = toInsert.getContainer(this.vehicle.getSupplyType());
            if (c == null) {
                throw new RouteException("The AidBox to insert is not compatible with the vehicle's supply type: " + this.vehicle.getSupplyType());
            }
        }

        if (this.nAidBoxes == this.aidBoxes.length) {
            expand();
        }

        // Shift elements to make room
        for (int i = this.nAidBoxes - 1; i > indexAfter; i--) {
            this.aidBoxes[i + 1] = this.aidBoxes[i];
        }

        this.aidBoxes[indexAfter + 1] = toInsert;
        this.nAidBoxes++;
    }

    @Override
    public AidBox[] getRoute() {
        AidBox[] copy = new AidBox[this.nAidBoxes];
        System.arraycopy(this.aidBoxes, 0, copy, 0, this.nAidBoxes);
        return copy;
    }

    @Override
    public Vehicle getVehicle() {
        return this.vehicle;
    }

    @Override
    public double getTotalDistance() {
        double totalDistance = 0.0;
        try {
            for (int i = 0; i < this.nAidBoxes - 1; i++) {
                AidBox current = this.aidBoxes[i];
                AidBox next = this.aidBoxes[i + 1];

                if (current.getCode().equalsIgnoreCase("Base")) {
                    totalDistance += next.getDistance(new BaseCollection());
                } else if (next.getCode().equalsIgnoreCase("Base")) {
                    totalDistance += current.getDistance(new BaseCollection());
                } else {
                    totalDistance += current.getDistance(next);
                }
            }
        } catch (AidBoxException e) {
            // If some distance is not found, return 0.0 or print warning
            return 0.0;
        }
        return totalDistance;
    }

    @Override
    public double getTotalDuration() {
        double totalDuration = 0.0;
        try {
            for (int i = 0; i < this.nAidBoxes - 1; i++) {
                AidBox current = this.aidBoxes[i];
                AidBox next = this.aidBoxes[i + 1];

                if (current.getCode().equalsIgnoreCase("Base")) {
                    totalDuration += next.getDuration(new BaseCollection());
                } else if (next.getCode().equalsIgnoreCase("Base")) {
                    totalDuration += current.getDuration(new BaseCollection());
                } else {
                    totalDuration += current.getDuration(next);
                }
            }
        } catch (AidBoxException e) {
            return 0.0;
        }
        return totalDuration;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Route [Vehicle: ").append(this.vehicle.getSupplyType()).append(" (Cap: ").append(this.vehicle.getMaxCapacity()).append(" kg)]\n");
        sb.append("Path: ");
        for (int i = 0; i < this.nAidBoxes; i++) {
            sb.append(this.aidBoxes[i].getCode());
            if (i < this.nAidBoxes - 1) {
                sb.append(" -> ");
            }
        }
        sb.append("\nTotal Distance: ").append(getTotalDistance()).append(" m, Total Duration: ").append(getTotalDuration()).append(" s\n");
        return sb.toString();
    }
}
