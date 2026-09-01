# 🔑 SOLUÇÕES EXAMES MODELO & ÉPOCA NORMAL — DIA 5

> **Ficheiro de Resoluções de Exames Passados:** Respostas diretas, sem "palha", com excertos de código em todas as questões teóricas e anotações `@Override` nas práticas.

---

## 📌 RESOLUÇÃO INTEGRAL DO EXAME DA ÉPOCA NORMAL 2025/2026

---

### 📄 PARTE 1 — TEORIA (6,0 VALORES)

#### Pergunta 1 (1,5v) — Classes Abstratas vs Interfaces

**Diferenças Fundamentais:**
- **Herança & Estado:** Uma classe apenas herda de 1 classe abstrata (`extends`), mas pode implementar múltiplas interfaces (`implements`). Classes abstratas têm atributos de instância mutáveis e construtores; interfaces têm apenas constantes `public static final` e não têm construtores.
- **Métodos:** Classes abstratas misturam métodos abstratos e concretos. Interfaces declaram contratos públicos (além de métodos `default` ou `static`).
- **Escolha:** Usar **Classe Abstrata** na relação conceptual "É UM" com estado partilhado; usar **Interface** para contratos de comportamento "É CAPAZ DE" entre classes não relacionadas.

**Exemplo de Código:**
```java
// Interface: Contrato de comportamento
public interface RefrigeratedVehicle {
    double getMaxKilometers();
}

// Classe Abstrata: Hierarquia com estado partilhado e construtor
public abstract class Vehicle {
    private String code;
    public Vehicle(String code) { this.code = code; }
    public String getCode() { return code; }
    public abstract double getMaxCapacity();
}
```

---

#### Pergunta 2 (1,5v) — Passagem de Argumentos em Java

**Funcionamento Essencial:**
- Em Java a passagem de argumentos é **exclusivamente por valor** (*pass-by-value*).
- **Tipos Primitivos:** O valor numérico/lógico é copiado. Alterações no método não afetam a variável original.
- **Referências de Objetos:** O valor copiado é o **endereço de memória** do objeto na Heap. Reatribuir a referência no método altera apenas a cópia local. Contudo, invocar métodos modificadores no objeto altera o seu estado interno na Heap.

**Exemplo de Código:**
```java
public class PassagemDemo {
    public static void testar(int x, Container c) {
        x = 99;                 // Não afeta a variável original
        c.setCapacity(500.0);   // Altera o estado do objeto na Heap!
        c = new ContainerImpl();// Reatribuição local: não afeta a variável original
    }
}
```

---

#### Pergunta 3 (1,5v) — Conversão de Tipos (*Casting*)

**Funcionamento Essencial:**
- **Upcasting:** Conversão para superclasse. Automático e 100% seguro.
- **Downcasting:** Conversão para subclasse. Requer cast explícito e pode lançar `ClassCastException` se o objeto na Heap não for do tipo pretendido.
- **Segurança:** Utilizar sempre o operador `instanceof` antes de realizar downcasting.

**Exemplo de Código:**
```java
Vehicle v = new RefrigeratedVehicleImpl("V01", ItemType.PERISHABLE_FOOD, 500.0, 150.0); // Upcasting

if (v instanceof RefrigeratedVehicle) {
    RefrigeratedVehicle rv = (RefrigeratedVehicle) v; // Downcasting seguro
    System.out.println(rv.getMaxKilometers());
}
```

---

#### Pergunta 4 (1,5v) — Identidade vs Igualdade & `toString()`

**Funcionamento Essencial:**
- `==` compara **identidade física** (se apontam para a mesma posição de memória Heap).
- `equals()` compara **igualdade lógica de conteúdo**. Por defeito em `Object` usa `==`, devendo ser redefinido.
- `toString()` devolve a representação legível do estado do objeto.

**Exemplo de Código:**
```java
public class VehicleImpl implements Vehicle {
    private String code;

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || !(obj instanceof Vehicle)) return false;
        Vehicle other = (Vehicle) obj;
        return this.code != null && this.code.equals(other.getCode());
    }

    @Override
    public String toString() {
        return "VehicleImpl{code='" + code + "'}";
    }
}
```

---

### 💻 PARTE 2 — PRÁTICA (14,0 VALORES)

#### Pergunta 1a (3,0v) — Implementação da Classe `RefrigeratedVehicleImpl`

```java
public class RefrigeratedVehicleImpl implements RefrigeratedVehicle {
    private String code;
    private ItemType supplyType;
    private double maxCapacity;
    private double maxKilometers;
    private VehicleStatus status;

    public RefrigeratedVehicleImpl(String code, ItemType supplyType, double maxCapacity, double maxKilometers) {
        if (code == null || supplyType == null || maxCapacity <= 0 || maxKilometers <= 0) {
            throw new IllegalArgumentException("Parâmetros de inicialização inválidos.");
        }
        this.code = code;
        this.supplyType = supplyType;
        this.maxCapacity = maxCapacity;
        this.maxKilometers = maxKilometers;
        this.status = VehicleStatus.ENABLED; // ENABLED por defeito
    }

    @Override
    public String getCode() { return this.code; }

    @Override
    public ItemType getSupplyType() { return this.supplyType; }

    @Override
    public double getMaxCapacity() { return this.maxCapacity; }

    @Override
    public double getMaxKilometers() { return this.maxKilometers; }

    @Override
    public VehicleStatus getStatus() { return this.status; }

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

    @Override
    public String toString() {
        return "RefrigeratedVehicleImpl{code='" + code + "', status=" + status + ", maxKm=" + maxKilometers + "}";
    }
}
```

---

#### Pergunta 1b (2,0v) — Método `main` de Teste

```java
public class ExameTest {
    public static void main(String[] args) {
        // Instanciação de veículos para teste
        RefrigeratedVehicle v1 = new RefrigeratedVehicleImpl("V01", ItemType.PERISHABLE_FOOD, 500.0, 150.0);
        RefrigeratedVehicle v2 = new RefrigeratedVehicleImpl("V01", ItemType.PERISHABLE_FOOD, 500.0, 150.0);
        RefrigeratedVehicle v3 = new RefrigeratedVehicleImpl("V02", ItemType.PERISHABLE_FOOD, 800.0, 200.0);

        // Teste de getters
        System.out.println("Código V1: " + v1.getCode());
        System.out.println("Capacidade Máx V1: " + v1.getMaxCapacity());
        System.out.println("Km Máx V1: " + v1.getMaxKilometers());

        // Teste de alteração de estado enum
        System.out.println("Status Inicial: " + v1.getStatus());
        v1.setStatus(VehicleStatus.DISABLED);
        System.out.println("Status Modificado: " + v1.getStatus());

        // Teste de equals
        System.out.println("v1.equals(v2) [mesmo código V01]: " + v1.equals(v2)); // true
        System.out.println("v1.equals(v3) [código diferente]: " + v1.equals(v3)); // false
        System.out.println("v1.equals(null): " + v1.equals(null));               // false
    }
}
```

---

#### Pergunta 2a (4,0v) — Métodos Auxiliares

```java
public boolean hasCollectableContainer(Vehicle vehicle, AidBox aidbox) {
    if (vehicle == null || aidbox == null || aidbox.getContainers() == null) {
        return false;
    }

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
    if (route == null || aidbox == null || validator == null) {
        return false;
    }

    if (!validator.validate(route, aidbox)) {
        return false;
    }

    try {
        route.addAidBox(aidbox);
        return true;
    } catch (RouteException e) {
        return false;
    }
}
```

---

#### Pergunta 2b (5,0v) — Método `generate` em `StrategyImpl`

```java
public class StrategyImpl implements Strategy {

    @Override
    public Route[] generate(IInstitution inst, RouteValidator validator) {
        if (inst == null || validator == null) return new Route[0];

        Vehicle[] vehicles = inst.getVehicles();
        AidBox[] aidBoxes = inst.getAidBoxes();
        if (vehicles == null || aidBoxes == null) return new Route[0];

        Route[] tempRoutes = new Route[vehicles.length];
        int validRouteCount = 0;

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
                tempRoutes[validRouteCount] = currentRoute;
                validRouteCount++;
            }
        }

        Route[] finalRoutes = new Route[validRouteCount];
        for (int i = 0; i < validRouteCount; i++) {
            finalRoutes[i] = tempRoutes[i];
        }

        return finalRoutes;
    }
}
```
