package trabalho_pp.core;

import com.estg.core.AidBox;
import com.estg.core.Container;
import com.estg.core.GeographicCoordinates;
import com.estg.core.ItemType;
import com.estg.core.exceptions.AidBoxException;
import com.estg.core.exceptions.ContainerException;
import trabalho_pp.distances.Distances;

public class AidBoxImpl implements AidBox {
    private String code;
    private String zone;
    private String refLocal;
    private GeographicCoordinates coordinates;
    private Container[] containers;
    private int containerCount;
    private static final int INITIAL_CAPACITY = 4; // usually at most 4 containers

    public AidBoxImpl(String code, String zone, GeographicCoordinates coordinates) {
        this.code = code;
        this.zone = zone;
        this.refLocal = "Lat: " + coordinates.getLatitude() + ", Lng: " + coordinates.getLongitude();
        this.coordinates = coordinates;
        this.containers = new Container[INITIAL_CAPACITY];
        this.containerCount = 0;
    }

    public AidBoxImpl(String code, String zone, GeographicCoordinates coordinates, String refLocal) {
        this.code = code;
        this.zone = zone;
        this.refLocal = refLocal;
        this.coordinates = coordinates;
        this.containers = new Container[INITIAL_CAPACITY];
        this.containerCount = 0;
    }

    @Override
    public String getCode() {
        return this.code;
    }

    @Override
    public String getZone() {
        return this.zone;
    }

    @Override
    public String getRefLocal() {
        return this.refLocal;
    }

    @Override
    public GeographicCoordinates getCoordinates() {
        return this.coordinates;
    }

    @Override
    public double getDistance(AidBox aidBox) throws AidBoxException {
        if (aidBox == null) {
            throw new AidBoxException("AidBox target cannot be null");
        }
        return Distances.getInstance().getDistance(this, aidBox);
    }

    @Override
    public double getDuration(AidBox aidBox) throws AidBoxException {
        if (aidBox == null) {
            throw new AidBoxException("AidBox target cannot be null");
        }
        return Distances.getInstance().getDuration(this, aidBox);
    }

    @Override
    public boolean addContainer(Container container) throws ContainerException {
        if (container == null) {
            throw new ContainerException("Container cannot be null");
        }

        // Check if a container with the same code already exists
        for (int i = 0; i < this.containerCount; i++) {
            if (this.containers[i].equals(container)) {
                return false;
            }
        }

        // Check if container type already exists in this AidBox
        for (int i = 0; i < this.containerCount; i++) {
            if (this.containers[i].getType() == container.getType()) {
                throw new ContainerException("AidBox already contains a container of type: " + container.getType());
            }
        }

        // Expand array if full
        if (this.containerCount == this.containers.length) {
            expandContainers();
        }

        this.containers[this.containerCount++] = container;
        return true;
    }

    private void expandContainers() {
        Container[] temp = new Container[this.containers.length * 2];
        System.arraycopy(this.containers, 0, temp, 0, this.containers.length);
        this.containers = temp;
    }

    @Override
    public Container getContainer(ItemType itemType) {
        for (int i = 0; i < this.containerCount; i++) {
            if (this.containers[i].getType() == itemType) {
                return this.containers[i];
            }
        }
        return null;
    }

    @Override
    public Container[] getContainers() {
        Container[] copy = new Container[this.containerCount];
        for (int i = 0; i < this.containerCount; i++) {
            try {
                // If clone is implemented on ContainerImpl, use it
                copy[i] = (Container) ((ContainerImpl) this.containers[i]).clone();
            } catch (CloneNotSupportedException e) {
                copy[i] = this.containers[i];
            }
        }
        return copy;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        AidBoxImpl cloned = (AidBoxImpl) super.clone();
        cloned.containers = new Container[this.containers.length];
        for (int i = 0; i < this.containerCount; i++) {
            cloned.containers[i] = (Container) ((ContainerImpl) this.containers[i]).clone();
        }
        cloned.containerCount = this.containerCount;
        cloned.coordinates = new GeographicCoordinatesImpl(this.coordinates.getLatitude(), this.coordinates.getLongitude());
        return cloned;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || !(obj instanceof AidBox)) return false;
        AidBox other = (AidBox) obj;
        return this.code.equals(other.getCode());
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("AidBox: ").append(code).append(" (Zone: ").append(zone).append(", ").append(refLocal).append(")\n");
        sb.append("Containers:\n");
        for (int i = 0; i < this.containerCount; i++) {
            sb.append("  - ").append(this.containers[i].toString().replace("\n", "\n  ")).append("\n");
        }
        return sb.toString();
    }
}
