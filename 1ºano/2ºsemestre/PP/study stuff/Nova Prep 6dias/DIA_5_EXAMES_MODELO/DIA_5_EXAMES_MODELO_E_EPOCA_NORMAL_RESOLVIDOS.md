# 📓 DIA 5 — EXAME DA ÉPOCA NORMAL 2025/2026 E TODOS OS 5 EXAMES MODELO RESOLVIDOS (20 VALORES)

Neste ficheiro encontras a **resolução integral modelo de 20 valores** para o **Exame Oficial da Época Normal 2025/2026** e para os **5 Exames Modelo oficiais da ESTG/P.PORTO**.

---

## 🏆 1. EXAME OFICIAL DA ÉPOCA NORMAL 2025/2026 (RESOLUÇÃO 20/20)

### 📘 PARTE 1 — TEORIA CORE (6,0 VALORES — 1,5v CADA)

#### Pergunta 1 (1,5v) — Classes Abstratas vs Interfaces
**Enunciado:** Explique detalhadamente as diferenças entre classes abstratas e interfaces em Java. Em que situações é mais adequado optar por uma classe abstrata e em que situações é preferível uma interface? Justifique a sua resposta e ilustre cada caso com um exemplo prático.

**Resolução Modelo (20v):**
* **Hierarquia de Herança:** Uma classe abstrata utiliza a palavra-chave `extends` e suporta apenas herança simples (uma classe só pode estender uma superclasse). Uma interface utiliza `implements` e permite herança múltipla de tipos.
* **Estado e Atributos:** Classes abstratas podem possuir atributos de instância mutáveis (`private`, `protected`, etc.). As interfaces só podem possuir constantes `public static final`.
* **Construtores:** Classes abstratas possuem construtores (invocados via `super()`). Interfaces **não possuem construtores**.
* **Quando Usar:**
  * **Classe Abstrata:** Quando existe uma relação conceptual de "É UM" (IS-A) com partilha de código e estado entre subclasses relacionadas (ex: `Vehicle` -> `RefrigeratedVehicle`).
  * **Interface:** Quando se pretende definir um contrato de comportamento ("É CAPAZ DE") para ser implementado por classes não relacionadas (ex: `RouteValidator`, `Serializable`).

```java
// Exemplo prático de Classe Abstrata (partilha de estado e código)
public abstract class Vehicle {
    private String code;
    public Vehicle(String code) { this.code = code; }
    public String getCode() { return this.code; }
    public abstract double getMaxCapacity();
}

// Exemplo prático de Interface (contrato de comportamento)
public interface RouteValidator {
    boolean validate(Route route, AidBox box);
}
```

---

#### Pergunta 2 (1,5v) — Passagem de Argumentos por Valor
**Enunciado:** Descreva detalhadamente o modo como Java realiza a passagem de argumentos para os métodos, distinguindo o comportamento aplicado a tipos primitivos do comportamento aplicado a referências de objetos.

**Resolução Modelo (20v):**
* Em Java, a passagem de argumentos é **exclusivamente por valor** (*pass-by-value*).
* **Tipos Primitivos:** Copia-se o valor numérico literal. Alterações dentro do método **não afetam** a variável original do chamador.
* **Referências a Objetos:** Copia-se o **endereço de memória (referência)** que aponta para o objeto na Heap. Não é possível alterar a referência original para apontar para outro objeto, mas é possível **alterar o estado interno do objeto na Heap** através dos seus métodos mutadores.

```java
public class TestePassagem {
    public static void alterar(int valor, AidBox box) {
        valor = 99; // Não afeta a variável original
        // box.setZone("Zona 2"); -> Altera o estado do objeto na Heap!
    }
}
```

---

#### Pergunta 3 (1,5v) — Conversão de Tipos (Casting) e instanceof
**Enunciado:** Explique o conceito de conversão de tipos (*casting*) no contexto da herança e do polimorfismo. Discuta os riscos associados e a utilização de `instanceof`.

**Resolução Modelo (20v):**
* **Upcasting:** Conversão de uma subclasse para uma superclasse ou interface. É automático e 100% seguro.
* **Downcasting:** Conversão de uma superclasse para uma subclasse. É explícito e de alto risco. Se o objeto na Heap não for da subclasse indicada, a JVM lança uma `ClassCastException`.
* **Proteção com `instanceof`:** Deve-se testar sempre a classe com `instanceof` antes de efetuar o downcasting.

```java
public void processarVeiculo(Vehicle v) {
    if (v instanceof RefrigeratedVehicle) { // Proteção obrigatória
        RefrigeratedVehicle rv = (RefrigeratedVehicle) v; // Downcasting seguro
        System.out.println("Km máx: " + rv.getMaxKilometers());
    }
}
```

---

#### Pergunta 4 (1,5v) — Identidade vs Igualdade (== vs equals)
**Enunciado:** Distinga os conceitos de identidade e de igualdade de objetos em Java, esclarecendo a diferença entre o operador `==` e o método `equals()`. Explique o papel do método `toString()`.

**Resolução Modelo (20v):**
* **Identidade (`==`):** Verifica se duas variáveis de referência apontam para a **mesma posição de memória Heap**.
* **Igualdade (`equals()`):** Compara se dois objetos têm o **mesmo conteúdo lógico** (ex: o mesmo código).
* **Método `toString()`:** Devolve a representação textual do objeto.

```java
public class AidBoxImpl implements AidBox {
    private String code;
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || !(obj instanceof AidBox)) return false;
        AidBox other = (AidBox) obj;
        return this.code != null && this.code.equals(other.getCode());
    }

    @Override
    public String toString() {
        return "AidBox{code='" + this.code + "'}";
    }
}
```

---

### 📕 PARTE 2 — PRÁTICA DE PROGRAMAÇÃO (14,0 VALORES)

#### Pergunta 1a (3,0v) — RefrigeratedVehicleImpl
```java
public class RefrigeratedVehicleImpl implements RefrigeratedVehicle {
    private String code;
    private ItemType supplyType;
    private double maxCapacity;
    private double maxKilometers;
    private VehicleStatus status;

    public RefrigeratedVehicleImpl(String code, ItemType supplyType, double maxCapacity, double maxKilometers) {
        if (code == null || supplyType == null || maxCapacity <= 0 || maxKilometers <= 0) {
            throw new IllegalArgumentException("Parâmetros de construtor inválidos.");
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
    @Override public void setStatus(VehicleStatus status) { if (status != null) this.status = status; }

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

#### Pergunta 1b (2,0v) — Método main de Teste
```java
public class VehicleTest {
    public static void main(String[] args) {
        RefrigeratedVehicle v1 = new RefrigeratedVehicleImpl("V01", ItemType.MEDICINE, 500.0, 300.0);
        RefrigeratedVehicle v2 = new RefrigeratedVehicleImpl("V01", ItemType.MEDICINE, 500.0, 300.0);

        System.out.println("Getters: " + v1.getCode() + " | " + v1.getStatus());
        System.out.println("Equals mesmo código: " + v1.equals(v2)); // true

        try {
            RefrigeratedVehicle vInvalido = new RefrigeratedVehicleImpl(null, null, -1, 0);
        } catch (IllegalArgumentException e) {
            System.out.println("Exceção capturada com sucesso: " + e.getMessage());
        }
    }
}
```

---

#### Pergunta 2a (4,0v) — Métodos Auxiliares em StrategyImpl
```java
public boolean hasCollectableContainer(Vehicle vehicle, AidBox aidbox) {
    if (vehicle == null || aidbox == null) return false;
    Container[] containers = aidbox.getContainers();
    if (containers == null) return false;

    for (int i = 0; i < containers.length; i++) {
        Container c = containers[i];
        if (c != null && c.getType() == vehicle.getSupplyType()) {
            Measurement last = c.getLastMeasurement();
            if (last != null && last.getValue() > (c.getCapacity() * 0.8)) return true;
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
```

---

#### Pergunta 2b (5,0v) — Método generate em StrategyImpl
```java
public class StrategyImpl implements Strategy {
    @Override
    public Route[] generate(IInstitution inst, RouteValidator validator) {
        if (inst == null || validator == null) return new Route[0];

        Vehicle[] vehicles = inst.getVehicles();
        AidBox[] aidBoxes = inst.getAidBoxes();
        if (vehicles == null || aidBoxes == null) return new Route[0];

        Route[] tempRoutes = new Route[vehicles.length];
        int count = 0;

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
                tempRoutes[count++] = currentRoute;
            }
        }

        Route[] finalRoutes = new Route[count];
        for (int i = 0; i < count; i++) finalRoutes[i] = tempRoutes[i];
        return finalRoutes;
    }
}
```

---

## 📑 2. SÍNTESE E RESOLUÇÃO DOS 5 EXAMES MODELO

### 🎯 Exame Modelo 1 (AidBoxImpl + ReportImpl)
* **Pergunta 1a:** `AidBoxImpl` com capacidade de 4 contentores e exceção `AidBoxFullException`.
* **Pergunta 2a:** `countContainersByType(AidBox, ItemType)` e `getAverageOccupancy(AidBox)`.
* **Pergunta 2b:** `generate(IInstitution)` gerando relatório textual formatado para AidBoxes com ocupação > 50%.

### 🎯 Exame Modelo 2 (RouteImpl + CollectionManagerImpl)
* **Pergunta 1a:** `RouteImpl` com capacidade de 10 AidBoxes e exceção `RouteException`.
* **Pergunta 2a:** `isContainerFull(Container)` e `hasCollectableContainers(AidBox)`.
* **Pergunta 2b:** `collectFromInstitution(IInstitution)` alocando veículos e construindo rotas.

### 🎯 Exame Modelo 3 (ContainerImpl + PickingMapImpl)
* **Pergunta 1a:** `ContainerImpl` com capacidade de 50 medições e exceção `ContainerException`.
* **Pergunta 2a:** `hasContainerOfType(AidBox, ItemType)` e `needsCollection(Container)`.
* **Pergunta 2b:** `getPickingMap(IInstitution, ItemType)` retornando o array compactado de AidBoxes a recolher.

### 🎯 Exame Modelo 4 (AlertImpl + AlertManagerImpl)
* **Pergunta 1a:** `AlertImpl` com validações no construtor [1, 5] e `equals()` por código.
* **Pergunta 2a:** `isContainerInCriticalState(Container)` (> 95% ou sem medições) e `countCriticalContainersInAidBox(AidBox)`.
* **Pergunta 2b:** `generateMaintenanceAlerts(IInstitution)` criando alertas `CAPACITY_OVERFLOW` sem nulos.

### 🎯 Exame Modelo 5 (RefrigeratedVehicleImpl + StrategyImpl)
* **Pergunta 1a:** `RefrigeratedVehicleImpl` estendendo `Vehicle` com o atributo `status`.
* **Pergunta 2a:** `hasCollectableContainer(Vehicle, AidBox)` e `addAidBoxToRoute(Route, AidBox, RouteValidator)`.
* **Pergunta 2b:** `generate(IInstitution, RouteValidator)` construindo rotas válidas para veículos refrigerados.
