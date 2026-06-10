# 📝 Exame de Preparação Modelo B (Época de Recurso / Época B) — PP 2025/2026

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

### Pergunta 1 — Casting e Ciclo de Vida de Objetos (2.0 valores)
*   **a)** Explique a diferença entre **Upcasting** e **Downcasting** em Java. Indique em que situações o downcasting é estritamente necessário e qual a consequência de realizar um downcasting inválido em tempo de execução. *(1.0 val)*
*   **b)** O que é e para que serve o **Garbage Collector** em Java? De que forma o Java deteta que a memória ocupada por um determinado objeto pode ser libertada? *(1.0 val)*

### Pergunta 2 — Ciclo de Execução e Construtores (2.0 valores)
*   **a)** Indique qual a ordem de execução de blocos e construtores quando uma classe derivada é instanciada pela primeira vez em Java (considere blocos estáticos e construtor da superclasse). *(1.0 val)*
*   **b)** O que acontece se uma subclasse declarar um construtor que não invoque explicitamente `super()`, mas a superclasse não contiver um construtor sem argumentos? Como se resolve? *(1.0 val)*

### Pergunta 3 — I/O Streams e Persistência (2.5 valores)
*   **a)** Diferencie as classes de **Byte Streams** (ex: `FileInputStream`) das classes de **Character Streams** (ex: `FileReader`), apresentando os casos de uso de cada uma delas. *(1.25 val)*
*   **b)** Explique a finalidade do modificador **`transient`** em variáveis de instância de uma classe em Java. Em que contexto (ex: serialização) é utilizado? *(1.25 val)*

---

## PARTE II — Perguntas Práticas (13.5 valores)

### Pergunta 4 — Medições e Histórico de Carga (4.5 valores)
Implemente em Java as seguintes classes sem recorrer ao *Java Collections Framework*:
*   **a) Classe `Measurement`:** Atributos privados: `date` (`LocalDateTime`) e `value` (double). Construtor que inicializa ambos os atributos e lança `IllegalArgumentException` se o valor for negativo ou a data for nula. Getters correspondentes. *(2.0 val)*
*   **b) Classe `ContainerLogger`:** Atributos privados: `measurements` (array de `Measurement`), `count` (contador real de leituras) e `maxCapacity` (double). Construtor recebe a `maxCapacity` do contentor e cria o array com tamanho base 5. Método `void addMeasurement(Measurement m)` que adiciona uma medição ao array (duplicando o tamanho do array caso esteja cheio). Este método deve lançar `IllegalArgumentException` se o valor da medição exceder a capacidade máxima, e `IllegalStateException` se a data for anterior à última medição registada. Método `double getAverageLoadSince(LocalDateTime since)` para calcular o valor médio de peso medido a partir da data de corte. *(2.5 val)*

### Pergunta 5 — Hierarquia de Centros de Recolha e Exceções (4.5 valores)
Implemente as classes para mapear os pontos geográficos:
*   **a) Classe Abstrata `Facility` e Subclasses:** Atributos privados: `id` (String), `latitude` (double) e `longitude` (double). Crie uma exceção personalizada Checked `InvalidLocationException`. O construtor de `Facility` deve validar se a latitude está no intervalo $[-90.0, 90.0]$ e a longitude em $[-180.0, 180.0]$, lançando a exceção em caso de valores inválidos. Crie a subclasse `CentralBase` (sem dados adicionais) e a subclasse `CollectionCenter` com o atributo `acceptsType` (`ItemType` enum). *(2.25 val)*
*   **b) Método de Distância:** Implemente nas classes concretas o método `double getDistance(Facility other)` que retorna a distância euclidiana aproximada multiplicada por $111\,000$ (para converter graus em metros):
    $$\text{Distância} = \sqrt{(\Delta\text{latitude})^2 + (\Delta\text{longitude})^2} \times 111\,000$$
    *(2.25 val)*

### Pergunta 6 — Planeamento e Validação de Rota (Complexa) (4.5 valores)
Implemente a classe `RoutePlanner` que planeia e valida uma rota de recolha de um veículo:
*   Atributos privados: `vehicle` (`Vehicle` — classe base do Exame A), `stops` (array de `Facility` de tamanho fixo 30), `stopCount` e `maxRouteDistance` (double).
*   **Métodos obrigatórios:**
    *   Construtor parametrizado que recebe o veículo e a distância máxima em metros.
    *   `void addStop(Facility f) throws RouteException` (Checked Exception customizada):
        1.  Impede a adição se o array estiver cheio.
        2.  Impede a adição se o centro de recolha `f` for incompatível com o tipo de bens que o veículo transporta (lança exceção).
        3.  Valida se a distância total projetada (incluindo o regresso à base central, caso exista) excede o limite de `maxRouteDistance` da rota, lançando exceção se ultrapassar.
    *   `double calculateTotalDistance()` — calcula a distância total percorrida na sequência atual.
    *   `String getPlannerReport()` — gera uma string com o ID do veículo, total de paragens, distância percorrida e a lista sequencial de paragens.

---

## 🔑 Resolução do Exame Modelo B

### PARTE I — Respostas Teóricas

#### Resposta 1:
*   **a) Upcasting vs Downcasting:**
    *   **Upcasting:** Cast de uma subclasse para uma superclasse (implícito). Útil para polimorfismo, pois podemos agrupar objetos em arrays de tipos genéricos.
    *   **Downcasting:** Cast de uma superclasse para subclasse (explícito). É necessário para aceder a propriedades exclusivas da classe filha (ex: `RefrigeratedVehicle.getMaxKmWithLoad()`). Se o objeto em causa não for do tipo de destino do cast, gera um erro de execução: `java.lang.ClassCastException`.
*   **b) Garbage Collector:**
    *   Mecanismo automático da JVM que monitoriza a memória Heap e liberta o espaço de objetos que já não estão a ser referenciados por nenhuma variável ativa da aplicação, evitando fugas de memória (*memory leaks*).

#### Resposta 2:
*   **a) Ordem de Inicialização:**
    1.  Membros e blocos estáticos da Superclasse.
    2.  Membros e blocos estáticos da Subclasse.
    3.  Inicializadores e construtor da Superclasse.
    4.  Inicializadores e construtor da Subclasse.
*   **b) Falta de construtor sem argumentos:**
    *   O Java tenta chamar implicitamente `super()` sem argumentos na primeira linha do construtor da subclasse. Se esse construtor não existir na superclasse, dá erro de compilação. Resolve-se invocando explicitamente o construtor correto na primeira linha usando `super(args...)`.

#### Resposta 3:
*   **a) Byte Streams vs Character Streams:**
    *   **Byte Streams:** Tratam os dados em blocos binários puros de 8-bits. Usados para dados não-texto (imagens, PDF, serialização).
    *   **Character Streams:** Tratam dados em caracteres Unicode de 16-bits. Aplicam descodificação automática de páginas de código (ex: UTF-8) para ficheiros de texto.
*   **b) transient:**
    *   Evita que o atributo associado seja guardado/persistido durante o processo de serialização de objetos. No restauro, o atributo volta com o valor por defeito.

---

### PARTE II — Resoluções Práticas (Código)

#### Código das Perguntas 4 e 5:
```java
import java.time.LocalDateTime;

// Pergunta 4.a
public class Measurement {
    private LocalDateTime date;
    private double value;

    public Measurement(LocalDateTime date, double value) {
        if (date == null) throw new IllegalArgumentException("Data nula.");
        if (value < 0) throw new IllegalArgumentException("Medição negativa.");
        this.date = date;
        this.value = value;
    }

    public LocalDateTime getDate() { return date; }
    public double getValue() { return value; }
}

// Pergunta 4.b
public class ContainerLogger {
    private Measurement[] measurements;
    private int count;
    private double maxCapacity;

    public ContainerLogger(double maxCapacity) {
        this.maxCapacity = maxCapacity;
        this.measurements = new Measurement[5];
        this.count = 0;
    }

    public void addMeasurement(Measurement m) {
        if (m == null) return;
        if (m.getValue() > maxCapacity) {
            throw new IllegalArgumentException("Peso excede capacidade máxima.");
        }
        if (count > 0 && m.getDate().isBefore(measurements[count - 1].getDate())) {
            throw new IllegalStateException("Data anterior à última medição.");
        }

        if (count == measurements.length) {
            Measurement[] temp = new Measurement[measurements.length * 2];
            System.arraycopy(measurements, 0, temp, 0, measurements.length);
            measurements = temp;
        }
        measurements[count++] = m;
    }

    public double getAverageLoadSince(LocalDateTime since) {
        if (count == 0 || since == null) return 0.0;
        double sum = 0.0;
        int matchingCount = 0;
        for (int i = 0; i < count; i++) {
            if (!measurements[i].getDate().isBefore(since)) {
                sum += measurements[i].getValue();
                matchingCount++;
            }
        }
        return (matchingCount == 0) ? 0.0 : sum / matchingCount;
    }
}

// Pergunta 5.a
public class InvalidLocationException extends Exception {
    public InvalidLocationException(String msg) { super(msg); }
}

public abstract class Facility {
    private String id;
    private double latitude;
    private double longitude;

    public Facility(String id, double latitude, double longitude) throws InvalidLocationException {
        if (latitude < -90.0 || latitude > 90.0) throw new InvalidLocationException("Latitude inválida.");
        if (longitude < -180.0 || longitude > 180.0) throw new InvalidLocationException("Longitude inválida.");
        this.id = id;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public String getId() { return id; }
    public double getLatitude() { return latitude; }
    public double getLongitude() { return longitude; }

    public abstract double getDistance(Facility other);
}

public class CentralBase extends Facility {
    public CentralBase(String id, double latitude, double longitude) throws InvalidLocationException {
        super(id, latitude, longitude);
    }

    @Override
    public double getDistance(Facility other) {
        if (other == null) return 0.0;
        double dLat = this.getLatitude() - other.getLatitude();
        double dLng = this.getLongitude() - other.getLongitude();
        return Math.sqrt(dLat * dLat + dLng * dLng) * 111000.0;
    }
}

public class CollectionCenter extends Facility {
    private ItemType acceptsType;

    public CollectionCenter(String id, double latitude, double longitude, ItemType acceptsType) 
            throws InvalidLocationException {
        super(id, latitude, longitude);
        this.acceptsType = acceptsType;
    }

    public ItemType getAcceptsType() { return acceptsType; }

    @Override
    public double getDistance(Facility other) {
        if (other == null) return 0.0;
        double dLat = this.getLatitude() - other.getLatitude();
        double dLng = this.getLongitude() - other.getLongitude();
        return Math.sqrt(dLat * dLat + dLng * dLng) * 111000.0;
    }
}
```

#### Código da Pergunta 6 (RoutePlanner):
```java
public class RouteException extends Exception {
    public RouteException(String msg) { super(msg); }
}

public class RoutePlanner {
    private Vehicle vehicle;
    private Facility[] stops;
    private int stopCount;
    private double maxRouteDistance;

    public RoutePlanner(Vehicle vehicle, double maxRouteDistance) {
        this.vehicle = vehicle;
        this.maxRouteDistance = maxRouteDistance;
        this.stops = new Facility[30];
        this.stopCount = 0;
    }

    public void addStop(Facility f) throws RouteException {
        if (f == null) return;
        if (stopCount >= 30) throw new RouteException("Limite de paragens atingido.");

        if (f instanceof CollectionCenter) {
            CollectionCenter cc = (CollectionCenter) f;
            if (cc.getAcceptsType() != vehicle.getItemType()) {
                throw new RouteException("Incompatibilidade de bens.");
            }
        }

        double projectedDist = calculateProjectedDistance(f);
        if (projectedDist > maxRouteDistance) {
            throw new RouteException("Excede a distância limite: " + projectedDist + "m");
        }

        stops[stopCount++] = f;
    }

    private double calculateProjectedDistance(Facility nextStop) {
        if (stopCount == 0) return 0.0;
        double dist = 0.0;
        for (int i = 0; i < stopCount - 1; i++) {
            dist += stops[i].getDistance(stops[i+1]);
        }
        dist += stops[stopCount - 1].getDistance(nextStop);
        if (stops[0] instanceof CentralBase) {
            dist += nextStop.getDistance(stops[0]);
        }
        return dist;
    }

    public double calculateTotalDistance() {
        if (stopCount < 2) return 0.0;
        double total = 0.0;
        for (int i = 0; i < stopCount - 1; i++) {
            total += stops[i].getDistance(stops[i+1]);
        }
        return total;
    }

    public String getPlannerReport() {
        StringBuilder sb = new StringBuilder();
        sb.append("Plano de Rota - Veículo: ").append(vehicle.getId()).append("\n");
        sb.append("Total Paragens: ").append(stopCount).append("\n");
        sb.append("Distância Total: ").append(calculateTotalDistance()).append(" m\n");
        for (int i = 0; i < stopCount; i++) {
            sb.append(" -> ").append(stops[i].getId());
        }
        return sb.toString();
    }
}
```
</details>
