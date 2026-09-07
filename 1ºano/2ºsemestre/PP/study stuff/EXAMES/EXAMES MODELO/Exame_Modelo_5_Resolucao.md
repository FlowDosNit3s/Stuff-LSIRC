# Resolução do Exame Modelo 5 — Paradigmas de Programação (Época Especial)

## PARTE 1 (6,0 VALORES) — RESPOSTA ABERTA TEÓRICA

### Pergunta 1 (1,5 valores)

#### Resposta Teórica:
A memória gerida pela JVM divide-se em duas regiões fundamentais:
1. **Stack (Pilha)**: Armazena variáveis locais, primitivos e referências a objetos no âmbito da execução de métodos por cada thread. A memória é libertada automaticamente quando o método termina (escopo LIFO).
2. **Heap (Monte)**: Área de memória global partilhada onde todos os objetos e arrays são alocados dinamicamente com o operador `new`.

**Ciclo de Vida dos Objetos e GC:**
Um objeto reside na Heap e torna-se **elegível para o Garbage Collector (GC)** assim que deixa de ser alcançável por qualquer cadeia de referências ativas a partir das raízes do GC (*GC Roots*, como variáveis locais ativas na Stack ou campos estáticos).

**Tipos de Referências em Java (`java.lang.ref`):**
- **Referências Fortes (*Strong References*)**: A referência padrão (ex.: `Object obj = new Object()`). O GC nunca recolhe o objeto enquanto houver uma referência forte ativa.
- **`SoftReference`**: O objeto é mantido na memória e só é recolhido pelo GC se a JVM estiver prestes a esgotar a memória livre (Memory Pressure). Útil para *caches*.
- **`WeakReference`**: O objeto é recolhido no próximo ciclo de coleta do GC, independentemente do nível de memória livre. Utilizado para mapeamentos fracos (ex.: `WeakHashMap`).

**Descontinuação de `finalize()`:**
O método `finalize()` foi depreciado devido à falta de garantias de tempo de execução, problemas de segurança e degradação de performance. A alternativa moderna é o uso da interface `AutoCloseable` com `try-with-resources`.

---

### Pergunta 2 (1,5 valores)

#### Resposta Teórica:
A resolução de chamadas a métodos sobrecarregados (*Overloading Resolution*) é efetuada pelo compilador em tempo de compilação (**Resolução Estática**), respeitando a seguinte ordem rigorosa de prioridade com base nos tipos declarativos dos argumentos:
1. **Correspondência Exata de Tipos**: Procura um método cujos parâmetros coincidam exatamente com os argumentos.
2. **Promoção Primitiva (*Widening*)**: Converte tipos primitivos mais pequenos para maiores (ex.: `int` para `long` ou `double`).
3. **Autoboxing / Unboxing**: Converte primitivos nos seus respetivos wrappers (ex.: `int` para `Integer`).
4. **Argumentos Variáveis (*Varargs* `...`)**: A menor prioridade; acionada se nenhuma das anteriores for aplicável.

**Comparação com Despacho Dinâmico (*Overriding*):**
Enquanto a sobrecarga é resolvida estaticamente na compilação, a sobreposição (*Overriding*) é resolvida em tempo de execução pela JVM (**Despacho Dinâmico**), executando a implementação da subclasse real alocada na Heap.

---

### Pergunta 3 (1,5 valores)

#### Resposta Teórica:
- **Strategy**: Encapsula uma família de algoritmos em classes separadas que implementam uma interface comum. Permite alterar dinamicamente o algoritmo utilizado por um objeto sem modificar a sua classe.
- **Factory Method**: Define uma interface/método abstrato para criação de objetos, delegando às subclasses a decisão de qual classe concreta instanciar.

**Princípio Open/Closed (SOLID):**
Ao aplicar o padrão **Strategy**, se surgirem novas regras de cálculo ou seleção de rotas, basta criar uma nova classe que implemente a interface da estratégia. A classe cliente permanece **fechada para modificação**, mas o sistema fica **aberto para extensão**.

#### Exemplo Prático:

```java
public interface AlgoritmoCalculo {
    double calcular(double base);
}

public class CalculoUrgente implements AlgoritmoCalculo {
    @Override
    public double calcular(double base) { return base * 1.5; }
}

public class ProcessadorRotas {
    private final AlgoritmoCalculo algoritmo;
    public ProcessadorRotas(AlgoritmoCalculo algoritmo) { this.algoritmo = algoritmo; }
    public double executar(double v) { return algoritmo.calcular(v); }
}
```

---

### Pergunta 4 (1,5 valores)

#### Resposta Teórica:
O contrato obrigatório entre `equals()` e `hashCode()` exige que:
1. **Se dois objetos forem iguais segundo o método `equals()`, eles DEVEM obrigatoriamente devolver o mesmo valor no método `hashCode()`.**
2. Se dois objetos tiverem o mesmo `hashCode()`, não é garantido que sejam iguais (colisão de hash).

**Consequências de violar o contrato:**
Ao redefinir `equals()` sem redefinir `hashCode()`, objetos logicamente iguais terão hashes diferentes derivados do endereço de memória de `Object`. Em coleções baseadas em *hashing* (`HashSet`, `HashMap`), a coleção procurará o objeto no *bucket* errado, resultando na duplicação indevida de elementos em `Set`s e falhas de localização em `Map`s.

---

## PARTE 2 (14,0 VALORES) — PROGRAMAÇÃO PRÁTICA EM JAVA (DOMÍNIO TP)

### Pergunta 1a (3,0 valores)

```java
// Exceção personalizada verificada para Contentores
public class ContainerException extends Exception {
    public ContainerException(String message) {
        super(message);
    }
}

// Implementação da classe RefrigeratedContainerImpl
public class RefrigeratedContainerImpl implements RefrigeratedContainer {
    private final String code;
    private final ItemType type;
    private final double capacity;
    private final double minTemperature;
    private final double maxTemperature;
    private final Measurement[] measurements;
    private int numberOfMeasurements;

    public RefrigeratedContainerImpl(String code, ItemType type, double capacity, double minTemperature, double maxTemperature) {
        if (code == null || code.trim().isEmpty()) {
            throw new IllegalArgumentException("O código do contentor não pode ser nulo nem vazio.");
        }
        if (type == null) {
            throw new IllegalArgumentException("O tipo de item não pode ser nulo.");
        }
        if (capacity <= 0) {
            throw new IllegalArgumentException("A capacidade deve ser estritamente positiva.");
        }
        if (minTemperature >= maxTemperature) {
            throw new IllegalArgumentException("A temperatura mínima deve ser inferior à temperatura máxima.");
        }

        this.code = code;
        this.type = type;
        this.capacity = capacity;
        this.minTemperature = minTemperature;
        this.maxTemperature = maxTemperature;
        this.measurements = new Measurement[20]; // Capacidade máxima de 20 medições
        this.numberOfMeasurements = 0;
    }

    @Override
    public String getCode() {
        return this.code;
    }

    @Override
    public ItemType getType() {
        return this.type;
    }

    @Override
    public double getCapacity() {
        return this.capacity;
    }

    @Override
    public double getMinTemperature() {
        return this.minTemperature;
    }

    @Override
    public double getMaxTemperature() {
        return this.maxTemperature;
    }

    @Override
    public double getCurrentTemperature() {
        Measurement last = getLastMeasurement();
        return (last != null) ? last.getValue() : 0.0;
    }

    @Override
    public Measurement getLastMeasurement() {
        if (numberOfMeasurements == 0) {
            return null;
        }
        return measurements[numberOfMeasurements - 1];
    }

    @Override
    public Measurement[] getMeasurements() {
        Measurement[] copy = new Measurement[numberOfMeasurements];
        for (int i = 0; i < numberOfMeasurements; i++) {
            copy[i] = measurements[i];
        }
        return copy;
    }

    @Override
    public void addMeasurement(Measurement measurement) throws ContainerException {
        if (measurement == null) {
            throw new ContainerException("A medição a adicionar não pode ser nula.");
        }
        if (numberOfMeasurements >= 20) {
            throw new ContainerException("Capacidade máxima de 20 medições atingida no contentor " + code);
        }

        double val = measurement.getValue();
        if (val < minTemperature || val > maxTemperature) {
            throw new ContainerException("Violação de Limites Térmicos! Valor " + val + "°C fora da gama [" 
                    + minTemperature + "°C, " + maxTemperature + "°C].");
        }

        measurements[numberOfMeasurements] = measurement;
        numberOfMeasurements++;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof Container)) {
            return false;
        }
        Container other = (Container) obj;
        return this.code.equals(other.getCode());
    }
}
```

---

### Pergunta 1b (2,0 valores)

```java
public class RefrigeratedContainerTest {
    public static void main(String[] args) {
        System.out.println("=== Início do Teste RefrigeratedContainerTest (Pergunta 1b) ===");

        RefrigeratedContainer container = new RefrigeratedContainerImpl(
            "CONT-MED-01", ItemType.MEDICINE, 500.0, -25.0, -5.0
        );

        // 1. Adição bem-sucedida de medições válidas
        try {
            Measurement m1 = new Measurement() {
                @Override public double getValue() { return -15.0; }
                @Override public String getDate() { return "2026-09-07"; }
            };
            container.addMeasurement(m1);
            System.out.println("Medição válida adicionada: -15.0°C. Temp Atual=" + container.getCurrentTemperature());
        } catch (ContainerException e) {
            System.err.println("Erro inesperado: " + e.getMessage());
        }

        // 2. Tentativa de adicionar medição fora dos limites térmicos (Captura de ContainerException)
        try {
            Measurement mInvalida = new Measurement() {
                @Override public double getValue() { return 10.0; } // 10.0°C é maior que max -5.0°C!
                @Override public String getDate() { return "2026-09-07"; }
            };
            container.addMeasurement(mInvalida);
            System.err.println("ERRO: Devia ter lançado ContainerException por violação de temperatura!");
        } catch (ContainerException e) {
            System.out.println("Sucesso! Capturada a exceção esperada -> " + e.getMessage());
        }

        // 3. Verificação do método equals()
        RefrigeratedContainer container2 = new RefrigeratedContainerImpl(
            "CONT-MED-01", ItemType.MEDICINE, 1000.0, -30.0, 0.0
        );
        RefrigeratedContainer container3 = new RefrigeratedContainerImpl(
            "CONT-MED-02", ItemType.MEDICINE, 500.0, -25.0, -5.0
        );

        System.out.println("container.equals(container2) [Mesmo código]: " + container.equals(container2)); // true
        System.out.println("container.equals(container3) [Códigos diferentes]: " + container.equals(container3)); // false

        System.out.println("=== Todos os testes concluídos com sucesso! ===");
    }
}
```

---

### Pergunta 2a (4,0 valores)

```java
public class DistributionPlannerImpl implements DistributionPlanner {

    public double calculateAidBoxTotalVolume(AidBox aidbox) {
        if (aidbox == null) {
            return 0.0;
        }

        Container[] containers = aidbox.getContainers();
        if (containers == null) {
            return 0.0;
        }

        double totalVolume = 0.0;
        for (Container c : containers) {
            if (c != null) {
                Measurement last = c.getLastMeasurement();
                if (last != null) {
                    totalVolume += last.getValue();
                }
            }
        }
        return totalVolume;
    }

    public boolean isHighPriorityAidBox(AidBox aidbox, ItemType priorityType, double threshold) {
        if (aidbox == null || priorityType == null) {
            return false;
        }

        Container[] containers = aidbox.getContainers();
        if (containers == null) {
            return false;
        }

        for (Container c : containers) {
            if (c != null && c.getType() == priorityType) {
                Measurement last = c.getLastMeasurement();
                if (last != null && c.getCapacity() > 0) {
                    double percentage = (last.getValue() / c.getCapacity()) * 100.0;
                    if (percentage > threshold) {
                        return true; // Encontrou pelo menos um contentor de prioridade crítico
                    }
                }
            }
        }
        return false;
    }

    @Override
    public Route[] planDistribution(IInstitution inst, RouteValidator validator) {
        // Implementado na Pergunta 2b
        return null;
    }
}
```

---

### Pergunta 2b (5,0 valores)

```java
public class DistributionPlannerImpl implements DistributionPlanner {

    public double calculateAidBoxTotalVolume(AidBox aidbox) {
        // (Código da Pergunta 2a)
        if (aidbox == null) return 0.0;
        Container[] containers = aidbox.getContainers();
        if (containers == null) return 0.0;
        double sum = 0.0;
        for (Container c : containers) {
            if (c != null && c.getLastMeasurement() != null) {
                sum += c.getLastMeasurement().getValue();
            }
        }
        return sum;
    }

    public boolean isHighPriorityAidBox(AidBox aidbox, ItemType priorityType, double threshold) {
        // (Código da Pergunta 2a)
        if (aidbox == null || priorityType == null) return false;
        Container[] containers = aidbox.getContainers();
        if (containers == null) return false;
        for (Container c : containers) {
            if (c != null && c.getType() == priorityType) {
                Measurement last = c.getLastMeasurement();
                if (last != null && c.getCapacity() > 0) {
                    if ((last.getValue() / c.getCapacity()) * 100.0 > threshold) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    @Override
    public Route[] planDistribution(IInstitution inst, RouteValidator validator) {
        if (inst == null || validator == null) {
            return new Route[0];
        }

        Vehicle[] vehicles = inst.getVehicles();
        AidBox[] aidBoxes = inst.getAidBoxes();

        if (vehicles == null || aidBoxes == null) {
            return new Route[0];
        }

        Route[] tempRoutes = new Route[vehicles.length];
        int validRouteCount = 0;

        for (Vehicle v : vehicles) {
            if (v == null) {
                continue;
            }

            Route currentRoute = new RouteImpl(v);

            for (AidBox box : aidBoxes) {
                if (box != null) {
                    // USO OBRIGATÓRIO DO MÉTODO 2 DE 2A
                    if (isHighPriorityAidBox(box, v.getSupplyType(), 80.0)) {
                        
                        // Validação com RouteValidator
                        if (validator.validate(currentRoute, box)) {
                            try {
                                currentRoute.addAidBox(box);
                            } catch (RouteException e) {
                                // Trata a exceção caso a adição falhe
                            }
                        }

                    }
                }
            }

            // Apenas adicionar a rota se ela contiver pelo menos uma AidBox
            AidBox[] assignedBoxes = currentRoute.getRoute();
            if (assignedBoxes != null && assignedBoxes.length > 0) {
                tempRoutes[validRouteCount] = currentRoute;
                validRouteCount++;
            }
        }

        // Construir o array final com dimensão exata sem posições nulas nem rotas vazias
        Route[] finalRoutes = new Route[validRouteCount];
        for (int i = 0; i < validRouteCount; i++) {
            finalRoutes[i] = tempRoutes[i];
        }

        return finalRoutes;
    }
}
```
