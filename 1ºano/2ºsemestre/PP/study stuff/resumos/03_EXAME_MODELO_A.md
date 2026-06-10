# 📝 Exame de Preparação Modelo A (Época Normal) — PP 2025/2026

> **Estrutura da Prova:**
> *   **PARTE I (Teórica - 6.5 Valores):** 3 perguntas sobre os conceitos teóricos de POO em Java.
> *   **PARTE II (Prática - 13.5 Valores):** 3 perguntas práticas de programação baseadas no domínio do trabalho prático (2 simples + 1 complexa).
> *   **Duração:** 2:00 horas | **Resolução consolidada no fim do documento.**

---

### ⚠️ IMPORTANTE: Nota de Mapeamento com o Trabalho Prático Real

No exame escrito de PP, os enunciados costumam simplificar os nomes das classes e enums para facilitar a escrita manual na folha de exame. A tabela abaixo mapeia os conceitos das provas modelo com a tua implementação real:

| Conceito no Exame | Correspondente no Teu Trabalho Real | Descrição no Trabalho Real |
|-------------------|-------------------------------------|----------------------------|
| `ContainerType` (Enum) | [ItemType](file:///c:/Users/diogo/Documents/GitHub/Stuff-LSIRC/1ºano/2ºsemestre/PP/Trabalhos%20Práticos/25_26/libs/resources.jar) | Enumeração dos tipos de bens |
| `Container` (Classe) | [ContainerImpl](file:///c:/Users/diogo/Documents/GitHub/Stuff-LSIRC/1ºano/2ºsemestre/PP/Trabalhos%20Práticos/25_26/src/trabalho_pp/core/ContainerImpl.java) | Implementação concreta da interface `Container` |
| `AidBox` (Classe) | [AidBoxImpl](file:///c:/Users/diogo/Documents/GitHub/Stuff-LSIRC/1ºano/2ºsemestre/PP/Trabalhos%20Práticos/25_26/src/trabalho_pp/core/AidBoxImpl.java) | Implementação concreta da interface `AidBox` |
| `Vehicle` (Classe Abstrata) | [VehicleImpl](file:///c:/Users/diogo/Documents/GitHub/Stuff-LSIRC/1ºano/2ºsemestre/PP/Trabalhos%20Práticos/25_26/src/trabalho_pp/pickingManagement/VehicleImpl.java) | Classe base que implementa a interface `Vehicle` |
| `RefrigeratedVehicle` | [RefrigeratedVehiclesImpl](file:///c:/Users/diogo/Documents/GitHub/Stuff-LSIRC/1ºano/2ºsemestre/PP/Trabalhos%20Práticos/25_26/src/trabalho_pp/pickingManagement/RefrigeratedVehiclesImpl.java) | Subclasse com limite de quilómetros com carga |
| `CollectionManager` | [InstitutionImpl](file:///c:/Users/diogo/Documents/GitHub/Stuff-LSIRC/1ºano/2ºsemestre/PP/Trabalhos%20Práticos/25_26/src/trabalho_pp/core/InstitutionImpl.java) | Agregador central do sistema (`Institution`) |

---

## PARTE I — Perguntas Teóricas (6.5 valores)

### Pergunta 1 — Abstração e Polimorfismo (2.0 valores)
*   **a)** Explique a diferença entre **classe abstrata** e **interface** em Java. Indique em que cenários deve optar por usar cada uma delas. *(1.0 val)*
*   **b)** Defina **polimorfismo** e distinga as operações de **Overloading (Sobrecarga)** de **Overriding (Sobreposição/Redefinição)**, apresentando um pequeno exemplo de cada uma. *(1.0 val)*

### Pergunta 2 — Encapsulamento e Modificadores (2.0 valores)
*   **a)** Indique e caracterize o nível de visibilidade e acessibilidade de membros declarados com os modificadores: `public`, `protected`, `private` e *default* (sem modificador). *(1.0 val)*
*   **b)** Explique de que forma o princípio de **encapsulamento** ajuda a proteger a consistência do estado de um objeto, fazendo referência ao uso de atributos `private` e métodos de acesso públicos. *(1.0 val)*

### Pergunta 3 — Exceções e Tratamento de Erros (2.5 valores)
*   **a)** Qual a diferença fundamental entre **Checked Exceptions** e **Unchecked Exceptions** (Runtime Exceptions) em Java? Como se comportam em termos de obrigação de captura pelo programador? *(1.25 val)*
*   **b)** Indique o propósito dos blocos `try`, `catch` e `finally`, explicando em particular em que circunstâncias o código dentro do bloco `finally` é executado. *(1.25 val)*

---

## PARTE II — Perguntas Práticas (13.5 valores)

### Pergunta 4 — Contentores e Caixas de Suprimentos (4.5 valores)
Implemente em Java as seguintes classes simplificadas respeitando o encapsulamento e a restrição de não utilização do *Java Collections Framework* (use arrays manuais):
*   **a) Classe `Container`:** Atributos privados: `id` (String), `type` (`ContainerType` enum), `maxCapacity` (double) e `currentLoad` (double). Construtor parametrizado. O setter de `currentLoad` apenas deve aceitar valores entre 0 e `maxCapacity`. Adicione o método `double getOccupancyRate()` (retorna a taxa de ocupação de 0.0 a 1.0) e `boolean isFull()`. *(2.0 val)*
*   **b) Classe `AidBox`:** Atributos privados: `id` (String), `zone` (String), `containers` (array de `Container` com limite de 4) e `containerCount` (int). Construtor recebe `id` e `zone`. Método `boolean addContainer(Container c)` que adiciona se não estiver cheio e se o ID e o tipo de contentor não forem repetidos (retorna sucesso). Método `Container getContainer(String id)` que procura e retorna o contentor ou null. *(2.5 val)*

### Pergunta 5 — Hierarquia de Veículos e Exceções (4.5 valores)
Crie o suporte para a frota de veículos com base nos seguintes requisitos:
*   **a) Classe Abstrata `Vehicle` e Subclasses:** Atributos privados `id` (String), `maxCapacity` (double) e `itemType` (`ItemType` enum). Subclasse `RefrigeratedVehicle` com atributo `maxKmWithLoad` (int). Implemente os construtores adequados e o método abstrato `boolean canTransport(ContainerType type)` na classe base. O veículo refrigerado apenas pode transportar `PERISHABLE_FOOD`; o veículo standard transporta qualquer bem exceto perecível. *(2.25 val)*
*   **b) Exceção e Procura:** Crie uma exceção personalizada Checked `NoSuitableVehicleException`. Implemente um método `Vehicle findVehicle(Vehicle[] vehicles, int count, ContainerType type, double requiredCap) throws NoSuitableVehicleException` que percorre a frota e retorna o primeiro veículo ativo adequado para transportar o tipo de contentor com capacidade suficiente. Se não encontrar, lança a exceção. *(2.25 val)*

### Pergunta 6 — Agregação e Relatórios (Avançada) (4.5 valores)
Implemente a classe `CollectionManager` que agrega caixas de suprimentos e veículos (use arrays manuais):
*   Atributos privados: `aidBoxes` (array, max 50), `aidBoxCount`, `vehicles` (array, max 20) e `vehicleCount`. Construtor sem parâmetros.
*   **Métodos obrigatórios:**
    *   `boolean addAidBox(AidBox box)` e `boolean addVehicle(Vehicle v)` (sem duplicados de ID).
    *   `Container[] getAllContainersNeedingCollection()` — retorna um array com todos os contentores cujo nível de ocupação seja superior a 80% (ou que sejam do tipo `PERISHABLE_FOOD` com qualquer carga > 0).
    *   `String generateStatusReport()` — retorna um relatório legível em String contendo o total de caixas registadas, o total de veículos (contando quantos são refrigerados), a ocupação média dos contentores em percentagem e a listagem de IDs de caixas.

---

## 🔑 Resolução do Exame Modelo A

### PARTE I — Respostas Teóricas

#### Resposta 1:
*   **a) Classe Abstrata vs Interface:**
    *   **Classe Abstrata:** Pode conter variáveis de instância, construtores e métodos com implementação (concretos) e sem implementação (abstratos). Uma subclasse só pode herdar de uma classe abstrata (`extends`). Optar quando existe uma clara relação de "é um" e comportamento ou atributos comuns para partilhar na hierarquia.
    *   **Interface:** Apenas define assinaturas de métodos (`public abstract`), constantes (`public static final`) e métodos default/static. Uma classe pode implementar múltiplas interfaces. Optar para definir contratos comuns entre classes de hierarquias distintas (ex: `Serializable`).
*   **b) Polimorfismo, Overloading e Overriding:**
    *   **Polimorfismo:** Capacidade de referenciar objetos de subclasses através do tipo da superclasse, permitindo comportamentos diferentes dependendo do tipo concreto no tempo de execução.
    *   **Overloading:** Métodos com o mesmo nome, mas assinaturas (parâmetros) diferentes na mesma classe. Ex: `void recolha()` e `void recolha(int id)`.
    *   **Overriding:** Redefinição de um método herdado de uma superclasse na subclasse, mantendo a mesma assinatura. Ex: redefinir `toString()` na classe `RefrigeratedVehicle`.

#### Resposta 2:
*   **a) Modificadores de Acesso:**
    *   `public`: Acesso permitido por qualquer classe em qualquer package.
    *   `protected`: Acesso permitido no mesmo package e por subclasses noutros packages.
    *   *default* (sem modificador): Acesso permitido apenas por classes no mesmo package.
    *   `private`: Acesso restrito apenas à própria classe.
*   **b) Princípio de Encapsulamento:**
    *   Protege os atributos de um objeto definindo-os como `private`, impedindo alterações externas diretas e arbitrárias. O acesso e modificação ocorrem exclusivamente através de métodos públicos (`getters` e `setters`), nos quais se podem injetar validações e regras de negócio para assegurar a consistência do estado (ex: impedir que a carga do contentor seja negativa ou superior à capacidade máxima).

#### Resposta 3:
*   **a) Checked vs Unchecked Exceptions:**
    *   **Checked Exceptions:** Estendem diretamente `Exception` (exceto `RuntimeException`). O compilador obriga a sua verificação (capturar com `try-catch` ou declarar na assinatura com `throws`).
    *   **Unchecked (Runtime) Exceptions:** Estendem `RuntimeException`. Ocorrem tipicamente por erros de lógica de programação (ex: `NullPointerException`, `IndexOutOfBoundsException`). Não obrigam a captura ou declaração explícita.
*   **b) Bloco try-catch-finally:**
    *   `try`: Contém o bloco de código que pode lançar exceções.
    *   `catch`: Captura e trata exceções específicas lançadas no bloco `try`.
    *   `finally`: Bloco que é executado **sempre**, quer tenha ocorrido uma exceção ou não (e mesmo que ocorra um `return` no try/catch). Essencial para libertação de recursos.

---

### PARTE II — Resoluções Práticas (Código)

#### Código das Perguntas 4 e 5:
```java
public enum ContainerType {
    PERISHABLE_FOOD, NON_PERISHABLE_FOOD, CLOTHING, MEDICINE
}

public enum ItemType {
    PERISHABLE_FOOD, NON_PERISHABLE_FOOD, CLOTHING, MEDICINE
}

// Pergunta 4.a
public class Container {
    private String id;
    private ContainerType type;
    private double maxCapacity;
    private double currentLoad;

    public Container(String id, ContainerType type, double maxCapacity) {
        this.id = id;
        this.type = type;
        this.maxCapacity = maxCapacity;
        this.currentLoad = 0.0;
    }

    public String getId() { return id; }
    public ContainerType getType() { return type; }
    public double getMaxCapacity() { return maxCapacity; }
    public double getCurrentLoad() { return currentLoad; }

    public void setCurrentLoad(double load) {
        if (load >= 0 && load <= maxCapacity) {
            this.currentLoad = load;
        }
    }

    public double getOccupancyRate() {
        if (maxCapacity == 0) return 0.0;
        return currentLoad / maxCapacity;
    }

    public boolean isFull() { return currentLoad >= maxCapacity; }
}

// Pergunta 4.b
public class AidBox {
    private String id;
    private String zone;
    private Container[] containers;
    private int containerCount;

    public AidBox(String id, String zone) {
        this.id = id;
        this.zone = zone;
        this.containers = new Container[4];
        this.containerCount = 0;
    }

    public String getId() { return id; }
    public String getZone() { return zone; }

    public boolean addContainer(Container c) {
        if (c == null || containerCount >= 4) return false;
        for (int i = 0; i < containerCount; i++) {
            if (containers[i].getId().equals(c.getId()) || containers[i].getType() == c.getType()) {
                return false;
            }
        }
        containers[containerCount++] = c;
        return true;
    }

    public Container getContainer(String containerId) {
        for (int i = 0; i < containerCount; i++) {
            if (containers[i].getId().equals(containerId)) {
                return containers[i];
            }
        }
        return null;
    }
    
    public int getContainerCount() { return containerCount; }
    public Container getContainerByIndex(int idx) { return containers[idx]; }
}

// Pergunta 5.a
public abstract class Vehicle {
    private String id;
    private double maxCapacity;
    private ItemType itemType;

    public Vehicle(String id, double maxCapacity, ItemType itemType) {
        this.id = id;
        this.maxCapacity = maxCapacity;
        this.itemType = itemType;
    }

    public String getId() { return id; }
    public double getMaxCapacity() { return maxCapacity; }
    public ItemType getItemType() { return itemType; }

    public abstract boolean canTransport(ContainerType type);

    @Override
    public String toString() {
        return "Veículo " + id + " (Capacidade: " + maxCapacity + "kg)";
    }
}

public class StandardVehicle extends Vehicle {
    public StandardVehicle(String id, double maxCapacity, ItemType itemType) {
        super(id, maxCapacity, itemType);
    }

    @Override
    public boolean canTransport(ContainerType type) {
        return type != ContainerType.PERISHABLE_FOOD;
    }
}

public class RefrigeratedVehicle extends Vehicle {
    private int maxKmWithLoad;

    public RefrigeratedVehicle(String id, double maxCapacity, int maxKmWithLoad) {
        super(id, maxCapacity, ItemType.PERISHABLE_FOOD);
        this.maxKmWithLoad = maxKmWithLoad;
    }

    public int getMaxKmWithLoad() { return maxKmWithLoad; }

    @Override
    public boolean canTransport(ContainerType type) {
        return type == ContainerType.PERISHABLE_FOOD;
    }

    @Override
    public String toString() {
        return super.toString() + " [Refrigerado | Autonomia: " + maxKmWithLoad + "km]";
    }
}

// Pergunta 5.b
public class NoSuitableVehicleException extends Exception {
    public NoSuitableVehicleException(String message) {
        super(message);
    }
}
```

#### Código da Pergunta 6 (CollectionManager):
```java
public class CollectionManager {
    private AidBox[] aidBoxes;
    private int aidBoxCount;
    private Vehicle[] vehicles;
    private int vehicleCount;

    public CollectionManager() {
        this.aidBoxes = new AidBox[50];
        this.aidBoxCount = 0;
        this.vehicles = new Vehicle[20];
        this.vehicleCount = 0;
    }

    public boolean addAidBox(AidBox box) {
        if (box == null || aidBoxCount >= 50) return false;
        for (int i = 0; i < aidBoxCount; i++) {
            if (aidBoxes[i].getId().equals(box.getId())) return false;
        }
        aidBoxes[aidBoxCount++] = box;
        return true;
    }

    public boolean addVehicle(Vehicle v) {
        if (v == null || vehicleCount >= 20) return false;
        for (int i = 0; i < vehicleCount; i++) {
            if (vehicles[i].getId().equals(v.getId())) return false;
        }
        vehicles[vehicleCount++] = v;
        return true;
    }

    public Container[] getAllContainersNeedingCollection() {
        int totalNeeding = 0;
        for (int i = 0; i < aidBoxCount; i++) {
            AidBox box = aidBoxes[i];
            for (int j = 0; j < box.getContainerCount(); j++) {
                Container c = box.getContainerByIndex(j);
                if (c.getType() == ContainerType.PERISHABLE_FOOD && c.getCurrentLoad() > 0) {
                    totalNeeding++;
                } else if (c.getOccupancyRate() > 0.8) {
                    totalNeeding++;
                }
            }
        }

        Container[] result = new Container[totalNeeding];
        int idx = 0;
        for (int i = 0; i < aidBoxCount; i++) {
            AidBox box = aidBoxes[i];
            for (int j = 0; j < box.getContainerCount(); j++) {
                Container c = box.getContainerByIndex(j);
                if ((c.getType() == ContainerType.PERISHABLE_FOOD && c.getCurrentLoad() > 0) || (c.getOccupancyRate() > 0.8)) {
                    result[idx++] = c;
                }
            }
        }
        return result;
    }

    public Vehicle findVehicle(Vehicle[] vehicles, int count, ContainerType type, double requiredCap) 
            throws NoSuitableVehicleException {
        for (int i = 0; i < count; i++) {
            if (vehicles[i].canTransport(type) && vehicles[i].getMaxCapacity() >= requiredCap) {
                return vehicles[i];
            }
        }
        throw new NoSuitableVehicleException("Nenhum veículo disponível para o tipo " + type);
    }

    public String generateStatusReport() {
        int refrigCount = 0;
        for (int i = 0; i < vehicleCount; i++) {
            if (vehicles[i] instanceof RefrigeratedVehicle) {
                refrigCount++;
            }
        }

        double totalOccupancy = 0.0;
        int totalContainersCount = 0;
        StringBuilder boxList = new StringBuilder();

        for (int i = 0; i < aidBoxCount; i++) {
            AidBox box = aidBoxes[i];
            boxList.append(box.getId()).append(" ");
            for (int j = 0; j < box.getContainerCount(); j++) {
                totalOccupancy += box.getContainerByIndex(j).getOccupancyRate();
                totalContainersCount++;
            }
        }

        double avgOccupancyPct = (totalContainersCount == 0) ? 0.0 : (totalOccupancy / totalContainersCount) * 100;

        return "=== RELATÓRIO DE ESTADO ===\n" +
               "Total Caixas: " + aidBoxCount + "\n" +
               "Veículos: " + vehicleCount + " (Refrigerados: " + refrigCount + ")\n" +
               "Taxa de Ocupação Média: " + String.format("%.1f", avgOccupancyPct) + "%\n" +
               "Lista de Caixas: " + boxList.toString().trim() + "\n";
    }
}
```
