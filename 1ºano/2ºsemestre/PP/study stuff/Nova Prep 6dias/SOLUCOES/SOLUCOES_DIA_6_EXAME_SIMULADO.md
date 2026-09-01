# 🔑 SOLUÇÃO MODELO (20 VALORES) — EXAME SIMULADO ÉPOCA ESPECIAL (DIA 6)

> **Ficheiro de Resolução do Exame Simulado:** Respostas diretas, sem "palha", com excertos de código em todas as questões teóricas e anotações `@Override` nas práticas.

---

## 📄 RESOLUÇÃO DA PARTE 1 (TEORIA - 6,0 VALORES)

### Resposta Pergunta 1 (1,5v) — Exceções & `try-catch-finally`
- **Checked Exceptions (subclasses de `Exception` sem `RuntimeException`):** Erros de ambiente previsíveis; compilador **obriga** a tratar com `try-catch` ou declarar com `throws`.
- **Unchecked Exceptions (subclasses de `RuntimeException`):** Erros de lógica/bugs; compilador não exige declaração.
- **Bloco `finally`:** Executa **sempre**. Se o `try` contiver uma instrução `return`, o `finally` executa **antes** do método devolver o valor ao chamador.

```java
public int testarFinally() throws IOException {
    try {
        if (true) throw new IOException("Erro de I/O");
        return 1;
    } catch (IOException e) {
        return 0;
    } finally {
        System.out.println("Finally executado!"); // Executa sempre antes de retornar!
    }
}
```

---

### Resposta Pergunta 2 (1,5v) — Encapsulamento & Modificadores
- **Conceito:** Ocultar os detalhes de implementação e controlar o acesso através de métodos.
- **4 Modificadores (do mais restritivo ao mais permissivo):** `private` -> default (package-private) -> `protected` -> `public`.
- **Atributos `public`:** Violam o encapsulamento, permitindo alterações externas sem validação e criando forte acoplamento.

```java
public class AidBoxImpl {
    private String code; // Atributo privado encapsulado
    public String getCode() { return code; }
}
```

---

### Resposta Pergunta 3 (1,5v) — Overloading vs Overriding
- **Overloading (Sobrecarga):** Mesma classe, nomes iguais, parâmetros diferentes. Decisão em **tempo de compilação** (*static binding*).
- **Overriding (Sobreposição):** Subclasse redefine método herdado com a mesma assinatura. Decisão em **tempo de execução** (*dynamic binding*) pela JVM com base no tipo real da instância alocada na Heap.

```java
public class Base { public void emitir() { System.out.println("Base"); } }
public class Sub extends Base { 
    @Override public void emitir() { System.out.println("Sub"); } // Overriding
}
```

---

### Resposta Pergunta 4 (1,5v) — Membros `static`
- Membros estáticos pertencem à classe e não a instâncias específicas.
- **Restrições:** Métodos estáticos não podem aceder diretamente a atributos de instância nem utilizar as palavras-chave `this` ou `super`.

```java
public class Config {
    public static int TOTAL = 0;
    public static void incrementar() { TOTAL++; } // Estático
}
```

---

## 💻 RESOLUÇÃO DA PARTE 2 (PRÁTICA - 14,0 VALORES)

### Pergunta 1a (3,0v) — `AidBoxFullException` e `AidBoxImpl`

```java
public class AidBoxFullException extends Exception {
    public AidBoxFullException(String msg) { super(msg); }
}

public class AidBoxImpl implements AidBox {
    private String code;
    private String zone;
    private Container[] containers;
    private int numberOfContainers;
    private static final int MAX_CAPACITY = 4;

    public AidBoxImpl(String code, String zone) {
        if (code == null || zone == null) throw new IllegalArgumentException("Nulos não permitidos.");
        this.code = code;
        this.zone = zone;
        this.containers = new Container[MAX_CAPACITY];
        this.numberOfContainers = 0;
    }

    @Override public String getCode() { return this.code; }
    @Override public String getZone() { return this.zone; }

    @Override
    public Container[] getContainers() {
        Container[] result = new Container[this.numberOfContainers];
        for (int i = 0; i < this.numberOfContainers; i++) result[i] = this.containers[i];
        return result;
    }

    public boolean addContainer(Container container) throws AidBoxFullException {
        if (container == null) return false;
        if (this.numberOfContainers >= MAX_CAPACITY) {
            throw new AidBoxFullException("Capacidade máxima atingida!");
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
        return this.code.equals(other.getCode()) && this.zone.equals(other.getZone());
    }

    @Override
    public String toString() {
        return "AidBoxImpl{code='" + code + "', zone='" + zone + "'}";
    }
}
```

---

### Pergunta 1b (2,0v) — `AidBoxTest`

```java
public class AidBoxTest {
    public static void main(String[] args) {
        AidBoxImpl box1 = new AidBoxImpl("AB01", "Zona-Norte");
        AidBoxImpl box2 = new AidBoxImpl("AB01", "Zona-Norte");
        System.out.println("Equals mesmo código e zona: " + box1.equals(box2)); // true

        try {
            box1.addContainer(new ContainerImpl("C1", ItemType.MEDICINE, 100.0));
            box1.addContainer(new ContainerImpl("C2", ItemType.CLOTHING, 100.0));
            box1.addContainer(new ContainerImpl("C3", ItemType.PERISHABLE_FOOD, 100.0));
            box1.addContainer(new ContainerImpl("C4", ItemType.NON_PERISHABLE_FOOD, 100.0));
            box1.addContainer(new ContainerImpl("C5", ItemType.MEDICINE, 100.0)); // Lança exceção
        } catch (AidBoxFullException e) {
            System.out.println("Exceção capturada: " + e.getMessage());
        }
    }
}
```

---

### Pergunta 2a (4,0v) — `RouteStatisticsUtils`

```java
public class RouteStatisticsUtils {

    public static int countCriticalContainers(AidBox aidbox, double thresholdPercentage) {
        if (aidbox == null || aidbox.getContainers() == null) return 0;
        Container[] containers = aidbox.getContainers();
        int count = 0;
        for (int i = 0; i < containers.length; i++) {
            Container c = containers[i];
            if (c != null && c.getCapacity() > 0) {
                Measurement last = c.getLastMeasurement();
                if (last != null && ((last.getValue() / c.getCapacity()) * 100.0) > thresholdPercentage) {
                    count++;
                }
            }
        }
        return count;
    }

    public static AidBox[] getPriorityAidBoxes(IInstitution inst, String targetZone, double thresholdPercentage) {
        if (inst == null || targetZone == null || inst.getAidBoxes() == null) return new AidBox[0];
        AidBox[] allBoxes = inst.getAidBoxes();

        int priorityCount = 0;
        for (int i = 0; i < allBoxes.length; i++) {
            AidBox box = allBoxes[i];
            if (box != null && targetZone.equals(box.getZone())) {
                if (countCriticalContainers(box, thresholdPercentage) >= 2) priorityCount++;
            }
        }

        AidBox[] result = new AidBox[priorityCount];
        int idx = 0;
        for (int i = 0; i < allBoxes.length; i++) {
            AidBox box = allBoxes[i];
            if (box != null && targetZone.equals(box.getZone())) {
                if (countCriticalContainers(box, thresholdPercentage) >= 2) {
                    result[idx] = box;
                    idx++;
                }
            }
        }
        return result;
    }
}
```

---

### Pergunta 2b (5,0v) — `OptimizedStrategyImpl`

```java
public class OptimizedStrategyImpl implements Strategy {

    @Override
    public Route[] generate(IInstitution inst, RouteValidator validator) {
        if (inst == null || validator == null) return new Route[0];

        Vehicle[] vehicles = inst.getVehicles();
        AidBox[] allBoxes = inst.getAidBoxes();
        if (vehicles == null || allBoxes == null) return new Route[0];

        Route[] tempRoutes = new Route[vehicles.length];
        int routeCount = 0;

        for (int i = 0; i < vehicles.length; i++) {
            Vehicle v = vehicles[i];
            if (v == null) continue;

            Route currentRoute = new RouteImpl(v);

            for (int j = 0; j < allBoxes.length; j++) {
                AidBox box = aidBoxes[j];
                if (box != null && hasSupplyType(box, v.getSupplyType())) {
                    if (validator.validate(currentRoute, box)) {
                        try {
                            currentRoute.addAidBox(box);
                        } catch (RouteException e) {
                            // Ignora
                        }
                    }
                }
            }

            if (currentRoute.getRoute() != null && currentRoute.getRoute().length > 0) {
                tempRoutes[routeCount] = currentRoute;
                routeCount++;
            }
        }

        Route[] finalRoutes = new Route[routeCount];
        for (int i = 0; i < routeCount; i++) finalRoutes[i] = tempRoutes[i];
        return finalRoutes;
    }

    private boolean hasSupplyType(AidBox box, ItemType type) {
        Container[] containers = box.getContainers();
        if (containers == null) return false;
        for (int i = 0; i < containers.length; i++) {
            if (containers[i] != null && containers[i].getType() == type) return true;
        }
        return false;
    }
}
```
