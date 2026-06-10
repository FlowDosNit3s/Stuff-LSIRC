package trabalho_pp.core;

import com.estg.core.AidBox;
import com.estg.core.Container;
import com.estg.core.Institution;
import com.estg.core.ItemType;
import com.estg.core.Measurement;
import com.estg.core.exceptions.AidBoxException;
import com.estg.core.exceptions.ContainerException;
import com.estg.core.exceptions.InstitutionException;
import com.estg.core.exceptions.MeasurementException;
import com.estg.core.exceptions.PickingMapException;
import com.estg.core.exceptions.VehicleException;
import com.estg.pickingManagement.PickingMap;
import com.estg.pickingManagement.Vehicle;
import java.time.LocalDateTime;
import java.time.LocalDate;
import trabalho_pp.pickingManagement.VehicleImpl;
import trabalho_pp.distances.Distances;

public class InstitutionImpl implements Institution {
    private String name;
    private AidBox[] aidBoxes;
    private int aidBoxCount;
    private Vehicle[] vehicles;
    private int vehicleCount;
    private PickingMap[] pickingMaps;
    private int pickingMapCount;

    private static final int INITIAL_CAPACITY = 10;

    public InstitutionImpl(String name) {
        this.name = name;
        this.aidBoxes = new AidBox[INITIAL_CAPACITY];
        this.aidBoxCount = 0;
        this.vehicles = new Vehicle[INITIAL_CAPACITY];
        this.vehicleCount = 0;
        this.pickingMaps = new PickingMap[INITIAL_CAPACITY];
        this.pickingMapCount = 0;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public boolean addAidBox(AidBox aidBox) throws AidBoxException {
        if (aidBox == null) {
            throw new AidBoxException("AidBox cannot be null");
        }

        // Validate that the AidBox has no duplicate container types
        Container[] containers = aidBox.getContainers();
        for (int i = 0; i < containers.length; i++) {
            for (int j = i + 1; j < containers.length; j++) {
                if (containers[i].getType() == containers[j].getType()) {
                    throw new AidBoxException("AidBox has duplicate containers of type: " + containers[i].getType());
                }
            }
        }

        // Check if already exists
        for (int i = 0; i < this.aidBoxCount; i++) {
            if (this.aidBoxes[i].equals(aidBox)) {
                return false;
            }
        }

        if (this.aidBoxCount == this.aidBoxes.length) {
            expandAidBoxes();
        }

        this.aidBoxes[this.aidBoxCount++] = aidBox;
        return true;
    }

    private void expandAidBoxes() {
        AidBox[] temp = new AidBox[this.aidBoxes.length * 2];
        System.arraycopy(this.aidBoxes, 0, temp, 0, this.aidBoxes.length);
        this.aidBoxes = temp;
    }

    @Override
    public boolean addMeasurement(Measurement measurement, Container container) throws ContainerException, MeasurementException {
        if (measurement == null) {
            throw new MeasurementException("Measurement cannot be null");
        }
        if (container == null) {
            throw new ContainerException("Container cannot be null");
        }
        if (measurement.getValue() < 0 || measurement.getValue() > container.getCapacity()) {
            throw new MeasurementException("Measurement value must be between 0 and container capacity");
        }

        // Verify container belongs to an AidBox in this Institution
        Container targetContainer = null;
        for (int i = 0; i < this.aidBoxCount; i++) {
            Container[] boxContainers = this.aidBoxes[i].getContainers();
            for (Container c : boxContainers) {
                if (c.equals(container)) {
                    targetContainer = c;
                    break;
                }
            }
            if (targetContainer != null) break;
        }

        if (targetContainer == null) {
            throw new ContainerException("Container does not exist in any AidBox of this institution");
        }

        return targetContainer.addMeasurement(measurement);
    }

    @Override
    public AidBox[] getAidBoxes() {
        AidBox[] copy = new AidBox[this.aidBoxCount];
        for (int i = 0; i < this.aidBoxCount; i++) {
            try {
                copy[i] = (AidBox) ((AidBoxImpl) this.aidBoxes[i]).clone();
            } catch (CloneNotSupportedException e) {
                copy[i] = this.aidBoxes[i];
            }
        }
        return copy;
    }

    @Override
    public Container getContainer(AidBox aidBox, ItemType itemType) throws ContainerException {
        if (aidBox == null) {
            throw new ContainerException("AidBox cannot be null");
        }

        // Check if aidBox is part of this institution
        boolean found = false;
        for (int i = 0; i < this.aidBoxCount; i++) {
            if (this.aidBoxes[i].equals(aidBox)) {
                found = true;
                break;
            }
        }
        if (!found) {
            throw new ContainerException("AidBox is not registered in this institution");
        }

        Container container = aidBox.getContainer(itemType);
        if (container == null) {
            throw new ContainerException("No container of type " + itemType + " in this AidBox");
        }
        return container;
    }

    @Override
    public Vehicle[] getVehicles() {
        Vehicle[] copy = new Vehicle[this.vehicleCount];
        for (int i = 0; i < this.vehicleCount; i++) {
            try {
                copy[i] = (Vehicle) ((VehicleImpl) this.vehicles[i]).clone();
            } catch (CloneNotSupportedException e) {
                copy[i] = this.vehicles[i];
            }
        }
        return copy;
    }

    @Override
    public boolean addVehicle(Vehicle vehicle) throws VehicleException {
        if (vehicle == null) {
            throw new VehicleException("Vehicle cannot be null");
        }

        for (int i = 0; i < this.vehicleCount; i++) {
            if (this.vehicles[i].equals(vehicle)) {
                return false;
            }
        }

        if (this.vehicleCount == this.vehicles.length) {
            expandVehicles();
        }

        this.vehicles[this.vehicleCount++] = vehicle;
        return true;
    }

    private void expandVehicles() {
        Vehicle[] temp = new Vehicle[this.vehicles.length * 2];
        System.arraycopy(this.vehicles, 0, temp, 0, this.vehicles.length);
        this.vehicles = temp;
    }

    @Override
    public void disableVehicle(Vehicle vehicle) throws VehicleException {
        if (vehicle == null) {
            throw new VehicleException("Vehicle cannot be null");
        }

        int index = -1;
        for (int i = 0; i < this.vehicleCount; i++) {
            if (this.vehicles[i].equals(vehicle)) {
                index = i;
                break;
            }
        }

        if (index == -1) {
            throw new VehicleException("Vehicle does not exist in the institution");
        }

        VehicleImpl v = (VehicleImpl) this.vehicles[index];
        if (!v.isActive()) {
            throw new VehicleException("Vehicle is already disabled");
        }
        v.setActive(false);
    }

    @Override
    public void enableVehicle(Vehicle vehicle) throws VehicleException {
        if (vehicle == null) {
            throw new VehicleException("Vehicle cannot be null");
        }

        int index = -1;
        for (int i = 0; i < this.vehicleCount; i++) {
            if (this.vehicles[i].equals(vehicle)) {
                index = i;
                break;
            }
        }

        if (index == -1) {
            throw new VehicleException("Vehicle does not exist in the institution");
        }

        VehicleImpl v = (VehicleImpl) this.vehicles[index];
        if (v.isActive()) {
            throw new VehicleException("Vehicle is already enabled");
        }
        v.setActive(true);
    }

    @Override
    public PickingMap[] getPickingMaps() {
        PickingMap[] copy = new PickingMap[this.pickingMapCount];
        System.arraycopy(this.pickingMaps, 0, copy, 0, this.pickingMapCount);
        return copy;
    }

    @Override
    public PickingMap[] getPickingMaps(LocalDateTime from, LocalDateTime to) {
        int count = 0;
        for (int i = 0; i < this.pickingMapCount; i++) {
            LocalDateTime d = this.pickingMaps[i].getDate();
            if ((d.isAfter(from) || d.isEqual(from)) && (d.isBefore(to) || d.isEqual(to))) {
                count++;
            }
        }
        PickingMap[] copy = new PickingMap[count];
        int idx = 0;
        for (int i = 0; i < this.pickingMapCount; i++) {
            LocalDateTime d = this.pickingMaps[i].getDate();
            if ((d.isAfter(from) || d.isEqual(from)) && (d.isBefore(to) || d.isEqual(to))) {
                copy[idx++] = this.pickingMaps[i];
            }
        }
        return copy;
    }

    @Override
    public PickingMap getCurrentPickingMap() throws PickingMapException {
        if (this.pickingMapCount == 0) {
            throw new PickingMapException("No picking maps available in the institution");
        }
        // Most recent picking map
        PickingMap recent = this.pickingMaps[0];
        for (int i = 1; i < this.pickingMapCount; i++) {
            if (this.pickingMaps[i].getDate().isAfter(recent.getDate())) {
                recent = this.pickingMaps[i];
            }
        }
        return recent;
    }

    @Override
    public boolean addPickingMap(PickingMap pickingMap) throws PickingMapException {
        if (pickingMap == null) {
            throw new PickingMapException("PickingMap cannot be null");
        }

        for (int i = 0; i < this.pickingMapCount; i++) {
            if (this.pickingMaps[i].equals(pickingMap)) {
                return false;
            }
        }

        if (this.pickingMapCount == this.pickingMaps.length) {
            expandPickingMaps();
        }

        this.pickingMaps[this.pickingMapCount++] = pickingMap;
        return true;
    }

    private void expandPickingMaps() {
        PickingMap[] temp = new PickingMap[this.pickingMaps.length * 2];
        System.arraycopy(this.pickingMaps, 0, temp, 0, this.pickingMaps.length);
        this.pickingMaps = temp;
    }

    @Override
    public double getDistance(AidBox aidBox) throws AidBoxException {
        if (aidBox == null) {
            throw new AidBoxException("AidBox cannot be null");
        }
        // Distance from Base to the given AidBox
        return Distances.getInstance().getDistance("Base", aidBox.getCode());
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Institution: ").append(name).append("\n");
        sb.append("AidBoxes Registered: ").append(aidBoxCount).append("\n");
        sb.append("Vehicles Registered: ").append(vehicleCount).append("\n");
        sb.append("PickingMaps Generated: ").append(pickingMapCount).append("\n");
        return sb.toString();
    }
}
