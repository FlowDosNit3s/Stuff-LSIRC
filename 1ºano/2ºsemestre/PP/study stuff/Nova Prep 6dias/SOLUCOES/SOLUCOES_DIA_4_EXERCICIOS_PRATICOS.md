# 🔑 SOLUÇÕES EXAMES MODELO PRÁTICOS (20 VALORES) — DIA 4

> **Ficheiro de Resoluções Práticas:** Código Java de 20 valores essencial, com anotação `@Override` em todos os métodos das interfaces e testes diretos em `main`.

---

# 📋 CONJUNTO PRÁTICO MODELO 1 — RESOLUÇÃO COMPLETA

---

### ✅ Conjunto 1 — Pergunta 1a: Exceção `AidBoxFullException` e `AidBoxImpl`

```java
// 1. Exceção Verificada (Checked Exception)
public class AidBoxFullException extends Exception {
    public AidBoxFullException(String message) {
        super(message);
    }
}

// 2. Classe AidBoxImpl
public class AidBoxImpl implements AidBox {
    private String code;
    private String zone;
    private Container[] containers;
    private int numberOfContainers;
    private static final int MAX_CAPACITY = 4;

    public AidBoxImpl(String code, String zone) {
        if (code == null || zone == null) {
            throw new IllegalArgumentException("Código e Zona não podem ser nulos.");
        }
        this.code = code;
        this.zone = zone;
        this.containers = new Container[MAX_CAPACITY];
        this.numberOfContainers = 0;
    }

    @Override
    public String getCode() { return this.code; }

    @Override
    public String getZone() { return this.zone; }

    @Override
    public Container[] getContainers() {
        Container[] result = new Container[this.numberOfContainers];
        for (int i = 0; i < this.numberOfContainers; i++) {
            result[i] = this.containers[i];
        }
        return result;
    }

    public boolean addContainer(Container container) throws AidBoxFullException {
        if (container == null) {
            throw new IllegalArgumentException("O contentor não pode ser nulo.");
        }
        if (this.numberOfContainers >= MAX_CAPACITY) {
            throw new AidBoxFullException("Capacidade máxima da AidBox (" + MAX_CAPACITY + ") atingida!");
        }
        this.containers[this.numberOfContainers] = container;
        this.numberOfContainers++;
        return true;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || !(obj instanceof AidBox)) return false;
        AidBox other = (AidBox) obj;
        return this.code != null && this.code.equals(other.getCode());
    }

    @Override
    public String toString() {
        return "AidBoxImpl{code='" + code + "', zone='" + zone + "', count=" + numberOfContainers + "}";
    }
}
```

---

### ✅ Conjunto 1 — Pergunta 1b: Classe de Teste `AidBoxTest`

```java
public class AidBoxTest {
    public static void main(String[] args) {
        AidBoxImpl box1 = new AidBoxImpl("AB01", "Zona-Norte");
        AidBoxImpl box2 = new AidBoxImpl("AB01", "Zona-Sul");
        AidBoxImpl box3 = new AidBoxImpl("AB02", "Zona-Norte");

        // Teste de Equals
        System.out.println("box1.equals(box2) [mesmo código]: " + box1.equals(box2)); // true
        System.out.println("box1.equals(box3) [código diferente]: " + box1.equals(box3)); // false

        // Teste de Adição e Exceção
        try {
            box1.addContainer(new ContainerImpl("C1", ItemType.MEDICINE, 100.0));
            box1.addContainer(new ContainerImpl("C2", ItemType.CLOTHING, 100.0));
            box1.addContainer(new ContainerImpl("C3", ItemType.PERISHABLE_FOOD, 100.0));
            box1.addContainer(new ContainerImpl("C4", ItemType.NON_PERISHABLE_FOOD, 100.0));
            System.out.println("4 contentores adicionados com sucesso.");

            // Tentar adicionar 5º (deve lançar exceção)
            box1.addContainer(new ContainerImpl("C5", ItemType.MEDICINE, 100.0));
        } catch (AidBoxFullException e) {
            System.out.println("Exceção capturada com sucesso: " + e.getMessage());
        }

        System.out.println("toString: " + box1.toString());
    }
}
```

---

### ✅ Conjunto 1 — Pergunta 2a e 2b: Classe `ReportImpl`

```java
public class ReportImpl implements Report {

    public int countContainersByType(AidBox aidbox, ItemType type) {
        if (aidbox == null || aidbox.getContainers() == null || type == null) return 0;
        Container[] containers = aidbox.getContainers();

        int count = 0;
        for (int i = 0; i < containers.length; i++) {
            if (containers[i] != null && containers[i].getType() == type) {
                count++;
            }
        }
        return count;
    }

    public double getAverageOccupancy(AidBox aidbox) {
        if (aidbox == null || aidbox.getContainers() == null) return 0.0;
        Container[] containers = aidbox.getContainers();

        double sum = 0.0;
        int count = 0;

        for (int i = 0; i < containers.length; i++) {
            Container c = containers[i];
            if (c != null && c.getCapacity() > 0) {
                Measurement last = c.getLastMeasurement();
                if (last != null) {
                    sum += (last.getValue() / c.getCapacity()) * 100.0;
                    count++;
                }
            }
        }
        return (count == 0) ? 0.0 : sum / count;
    }

    @Override
    public String generate(IInstitution inst) {
        if (inst == null || inst.getAidBoxes() == null) return "Relatório Vazio.";

        AidBox[] boxes = inst.getAidBoxes();
        StringBuilder sb = new StringBuilder();
        sb.append("=== RELATÓRIO DE OCUPAÇÃO DE AIDBOXES (> 50%) ===\n\n");

        for (int i = 0; i < boxes.length; i++) {
            AidBox box = boxes[i];
            if (box != null && getAverageOccupancy(box) > 50.0) {
                sb.append("AidBox Código: ").append(box.getCode()).append("\n");
                sb.append("Zona: ").append(box.getZone()).append("\n");
                sb.append("Ocupação Média: ").append(getAverageOccupancy(box)).append("%\n");
                sb.append("Contentores Perecíveis: ").append(countContainersByType(box, ItemType.PERISHABLE_FOOD)).append("\n");
                sb.append("--------------------------------------------------\n");
            }
        }

        return sb.toString();
    }
}
```

---

# 📋 CONJUNTO PRÁTICO MODELO 2 — RESOLUÇÃO COMPLETA

---

### ✅ Conjunto 2 — Pergunta 1a: Classe `RouteImpl`

```java
public class RouteImpl implements Route {
    private Vehicle vehicle;
    private AidBox[] aidBoxes;
    private int numberOfAidBoxes;
    private static final int MAX_CAPACITY = 10;

    public RouteImpl(Vehicle vehicle) {
        if (vehicle == null) {
            throw new IllegalArgumentException("O veículo não pode ser nulo.");
        }
        this.vehicle = vehicle;
        this.aidBoxes = new AidBox[MAX_CAPACITY];
        this.numberOfAidBoxes = 0;
    }

    @Override
    public Vehicle getVehicle() { return this.vehicle; }

    @Override
    public void addAidBox(AidBox aidBox) throws RouteException {
        if (aidBox == null) throw new RouteException("AidBox nula.");
        if (this.numberOfAidBoxes >= MAX_CAPACITY) throw new RouteException("Rota cheia.");

        for (int i = 0; i < this.numberOfAidBoxes; i++) {
            if (this.aidBoxes[i].equals(aidBox)) {
                throw new RouteException("AidBox já existe na rota.");
            }
        }

        this.aidBoxes[this.numberOfAidBoxes] = aidBox;
        this.numberOfAidBoxes++;
    }

    @Override
    public AidBox removeAidBox(AidBox aidBox) throws RouteException {
        if (aidBox == null) throw new RouteException("AidBox nula.");

        int targetIndex = -1;
        for (int i = 0; i < this.numberOfAidBoxes; i++) {
            if (this.aidBoxes[i].equals(aidBox)) {
                targetIndex = i;
                break;
            }
        }

        if (targetIndex == -1) throw new RouteException("AidBox não encontrada.");

        AidBox removed = this.aidBoxes[targetIndex];
        for (int i = targetIndex; i < this.numberOfAidBoxes - 1; i++) {
            this.aidBoxes[i] = this.aidBoxes[i + 1];
        }
        this.aidBoxes[this.numberOfAidBoxes - 1] = null;
        this.numberOfAidBoxes--;

        return removed;
    }

    @Override
    public AidBox[] getRoute() {
        AidBox[] result = new AidBox[this.numberOfAidBoxes];
        for (int i = 0; i < this.numberOfAidBoxes; i++) {
            result[i] = this.aidBoxes[i];
        }
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || !(obj instanceof Route)) return false;
        Route other = (Route) obj;
        return this.vehicle != null && other.getVehicle() != null &&
               this.vehicle.getCode().equals(other.getVehicle().getCode());
    }
}
```

---

### ✅ Conjunto 2 — Pergunta 1b: Classe de Teste `RouteTest`

```java
public class RouteTest {
    public static void main(String[] args) {
        Vehicle v1 = new VehicleImpl("V01", ItemType.PERISHABLE_FOOD, 500.0);
        RouteImpl route = new RouteImpl(v1);
        AidBox box1 = new AidBoxImpl("AB01", "Norte");

        try {
            route.addAidBox(box1);
            System.out.println("Box1 adicionada.");
            route.addAidBox(box1); // Duplicado
        } catch (RouteException e) {
            System.out.println("Exceção capturada (duplicado): " + e.getMessage());
        }
    }
}
```

---

### ✅ Conjunto 2 — Pergunta 2a e 2b: Classe `CollectionManagerImpl`

```java
public class CollectionManagerImpl implements CollectionManager {

    public double getContainerLoad(Container container) {
        if (container == null) return 0.0;
        Measurement last = container.getLastMeasurement();
        return (last == null) ? 0.0 : last.getValue();
    }

    public boolean isContainerFull(Container container, double thresholdPercentage) {
        if (container == null || container.getCapacity() <= 0) return false;
        Measurement last = container.getLastMeasurement();
        if (last == null) return false;
        double pct = (last.getValue() / container.getCapacity()) * 100.0;
        return pct > thresholdPercentage;
    }

    @Override
    public double getTotalCollectedByType(IInstitution inst, ItemType type) {
        if (inst == null || inst.getAidBoxes() == null || type == null) return 0.0;

        AidBox[] boxes = inst.getAidBoxes();
        double totalSum = 0.0;

        for (int i = 0; i < boxes.length; i++) {
            AidBox box = boxes[i];
            if (box != null && box.getContainers() != null) {
                Container[] containers = box.getContainers();
                for (int j = 0; j < containers.length; j++) {
                    Container c = containers[j];
                    if (c != null && c.getType() == type) {
                        if (isContainerFull(c, 75.0)) {
                            totalSum += getContainerLoad(c);
                        }
                    }
                }
            }
        }

        return totalSum;
    }
}
```

---

# 📋 CONJUNTO PRÁTICO MODELO 3 — RESOLUÇÃO COMPLETA

---

### ✅ Conjunto 3 — Pergunta 1a: Classe `RefrigeratedVehicleImpl`

```java
public class RefrigeratedVehicleImpl implements RefrigeratedVehicle {
    private String code;
    private ItemType supplyType;
    private double maxCapacity;
    private double maxKilometers;
    private VehicleStatus status;

    public RefrigeratedVehicleImpl(String code, ItemType supplyType, double maxCapacity, double maxKilometers) {
        if (code == null || supplyType == null || maxCapacity <= 0 || maxKilometers <= 0) {
            throw new IllegalArgumentException("Parâmetros inválidos.");
        }
        this.code = code;
        this.supplyType = supplyType;
        this.maxCapacity = maxCapacity;
        this.maxKilometers = maxKilometers;
        this.status = VehicleStatus.ENABLED;
    }

    @Override public String getCode() { return this.code; }
    @Override public ItemType getSupplyType() { return this.supplyType; }
    @Override public double getMaxCapacity() { return this.maxCapacity; }
    @Override public double getMaxKilometers() { return this.maxKilometers; }
    @Override public VehicleStatus getStatus() { return this.status; }

    @Override
    public void setStatus(VehicleStatus status) {
        if (status != null) this.status = status;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || !(obj instanceof RefrigeratedVehicle)) return false;
        RefrigeratedVehicle other = (RefrigeratedVehicle) obj;
        return this.code != null && this.code.equals(other.getCode());
    }
}
```

---

### ✅ Conjunto 3 — Pergunta 1b: Classe de Teste `VehicleTest`

```java
public class VehicleTest {
    public static void main(String[] args) {
        RefrigeratedVehicle v1 = new RefrigeratedVehicleImpl("V01", ItemType.PERISHABLE_FOOD, 500.0, 150.0);
        RefrigeratedVehicle v2 = new RefrigeratedVehicleImpl("V01", ItemType.PERISHABLE_FOOD, 500.0, 150.0);

        System.out.println("v1.equals(v2): " + v1.equals(v2)); // true
        System.out.println("Status inicial: " + v1.getStatus()); // ENABLED
        v1.setStatus(VehicleStatus.DISABLED);
        System.out.println("Status modificado: " + v1.getStatus()); // DISABLED
    }
}
```

---

### ✅ Conjunto 3 — Pergunta 2a e 2b: Classe `StrategyImpl`

```java
public class StrategyImpl implements Strategy {

    public boolean hasCollectableContainer(Vehicle vehicle, AidBox aidbox) {
        if (vehicle == null || aidbox == null || aidbox.getContainers() == null) return false;
        Container[] containers = aidbox.getContainers();

        for (int i = 0; i < containers.length; i++) {
            Container c = containers[i];
            if (c != null && c.getType() == vehicle.getSupplyType()) {
                Measurement last = c.getLastMeasurement();
                if (last != null && last.getValue() > (c.getCapacity() * 0.8)) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean addAidBoxToRoute(Route route, AidBox aidbox, RouteValidator validator) {
        if (route == null || aidbox == null || validator == null) return false;
        if (!validator.validate(route, aidbox)) return false;

        try {
            route.addAidBox(aidbox);
            return true;
        } catch (RouteException e) {
            return false;
        }
    }

    @Override
    public Route[] generate(IInstitution inst, RouteValidator validator) {
        if (inst == null || validator == null) return new Route[0];

        Vehicle[] vehicles = inst.getVehicles();
        AidBox[] aidBoxes = inst.getAidBoxes();
        if (vehicles == null || aidBoxes == null) return new Route[0];

        Route[] tempRoutes = new Route[vehicles.length];
        int routeCount = 0;

        for (int i = 0; i < vehicles.length; i++) {
            Vehicle v = vehicles[i];
            if (v == null) continue;

            Route currentRoute = new RouteImpl(v);

            for (int j = 0; j < aidBoxes.length; j++) {
                AidBox box = aidBoxes[j];
                if (box != null && hasCollectableContainer(v, box)) {
                    addAidBoxToRoute(currentRoute, box, validator);
                }
            }

            if (currentRoute.getRoute() != null && currentRoute.getRoute().length > 0) {
                tempRoutes[routeCount] = currentRoute;
                routeCount++;
            }
        }

        Route[] finalRoutes = new Route[routeCount];
        for (int i = 0; i < routeCount; i++) {
            finalRoutes[i] = tempRoutes[i];
        }

        return finalRoutes;
    }
}
```
