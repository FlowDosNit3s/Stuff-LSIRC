# Exame Modelo 5 — Paradigmas de Programação — Época de Recurso 2025/2026

| | |
|---|---|
| **Instituição** | P.PORTO — Escola Superior de Tecnologia e Gestão |
| **Tipo de Prova** | Exame Escrito — Época de Recurso |
| **Curso** | Licenciatura em Engenharia Informática / Licenciatura em Segurança Informática em Redes de Computadores |
| **Unidade Curricular** | Paradigmas de Programação |
| **Ano Letivo** | 2025/2026 |
| **Duração** | 2 horas |

---

## Observações

- Não é permitida a consulta.
- Não são permitidas questões relativas à Parte 2. Sempre que considerarem necessário, os alunos devem assumir os pressupostos que entenderem adequados, indicando-os explicitamente na resolução.

---

## Parte 1

### Pergunta 1 (1,5 valores)

Explique o modelo de gestão de memória da Máquina Virtual Java (JVM), distinguindo detalhadamente a utilidade da **Stack** da utilidade da **Heap**. Descreva como o **Garbage Collector** identifica e elimina objetos inalcançáveis (*unreachable objects*). Esclareça adicionalmente quais são as causas mais comuns para a ocorrência da exceção `NullPointerException` (NPE) e apresente estratégias de programação defensiva para a prevenir.

---

### Pergunta 2 (1,5 valores)

Explique o conceito de imutabilidade na classe `String` em Java e descreva o funcionamento do mecanismo de **String Pool** na Heap da JVM. Compare detalhadamente a utilização da classe `String` com as classes `StringBuilder` e `StringBuffer` no âmbito da manipulação de texto em ciclos intensivos, justificando os impactos de desempenho e a segurança em ambientes multithread. Ilustre com um exemplo prático de código.

---

### Pergunta 3 (1,5 valores)

Explique o conceito de organização modular por pacotes (`package`) em Java. Descreva o comportamento do modificador de acesso por defeito (*package-private* / sem modificador explícito) relativamente às variáveis, métodos e classes. Justifique em que situações o encapsulamento ao nível de pacote é vantajoso no desenho da arquitetura de uma biblioteca de software, ilustrando a sua resposta com um exemplo de estrutura de pacotes.

---

### Pergunta 4 (1,5 valores)

Compare o conceito de **Interfaces Marcadoras** (*Marker Interfaces*) com a utilização de **Métodos `default`** (introduzidos no Java 8) em interfaces. Explique como os métodos `default` permitem evoluir os contratos de interfaces existentes sem quebrar implementações pré-existentes, e descreva a regra de resolução aplicada pela JVM quando uma classe implementa duas interfaces com métodos `default` com a mesma assinatura (conflito do problema do diamante). Ilustre com um exemplo prático.

---

## Parte 2

1. Considere as seguintes interfaces `ShipmentBatch` e `Container`. A interface `ShipmentBatch` descreve o contrato de um lote de transporte de suprimentos organizado por um centro de logística humanitária. A interface `Container` descreve um contentor de recolha associado a uma caixa de suprimentos.

```java
public interface ShipmentBatch {
    String getBatchCode();
    ItemType getItemType();
    Container[] getContainers();
    void addContainer(Container container) throws ShipmentException;
    double getTotalWeight();
    boolean equals(Object obj);
}
```

```java
public enum ItemType {
    PERISHABLE_FOOD,
    NON_PERISHABLE_FOOD,
    CLOTHING,
    MEDICINE
}
```

```java
public interface Container {
    String getCode();
    ItemType getType();
    double getCapacity();
    Measurement getLastMeasurement();
}
```

```java
public interface Measurement {
    double getValue();
}
```

---

### Pergunta 1a (3 valores)

Considere a interface `ShipmentBatch` que representa um lote de transporte de mercadorias associado a um determinado tipo de bem (`ItemType`). Implemente a interface numa classe denominada `ShipmentBatchImpl`. A classe deve ter uma capacidade máxima estática de **6 contentores**. O método `addContainer` deve adicionar um contentor ao lote, lançando uma exceção `ShipmentException` caso:
- O contentor a adicionar seja `null`;
- O tipo do contentor (`getType()`) não coincida com o `ItemType` do lote;
- A capacidade máxima de 6 contentores já tenha sido atingida.

O método `getTotalWeight()` deve devolver a soma dos valores da última medição (`getLastMeasurement().getValue()`) de todos os contentores do lote (ignorando contentores sem medição). Para o método `equals`, dois lotes são iguais se possuírem o mesmo código (`getBatchCode()`).

---

### Pergunta 1b (2 valores)

Desenvolva o código necessário para testar a classe implementada (por exemplo, no contexto de um método `main`). Apresente um exemplo de teste para cada método implementado, incluindo o teste de adição de contentores com tipo incompatível e a captura de exceções.

---

2. Considere a existência de uma classe `LoadPlannerImpl` que implemente a interface `LoadPlanner`.

```java
public interface LoadPlanner {
    AidBox[] getPriorityAidBoxesForVehicle(IInstitution inst, Vehicle vehicle, double minimumCapacityRequired);
}
```

```java
public interface Vehicle {
    String getCode();
    ItemType getSupplyType();
    double getMaxCapacity();
}
```

---

### Pergunta 2a (4 valores)

Implemente os seguintes métodos na classe `LoadPlannerImpl`:

```java
double calculateTotalCapacityBySupplyType(AidBox aidbox, ItemType type);
```

- Este método deve devolver a soma da capacidade máxima (`getCapacity()`) de todos os contentores presentes na `AidBox` cujo tipo seja igual ao `ItemType` recebido como argumento. Se a `AidBox` for nula ou não possuir contentores do tipo especificado, deve devolver 0.

```java
boolean isVehicleCompatible(Vehicle vehicle, AidBox aidbox);
```

- Este método deve devolver `true` se o veículo e a `AidBox` não forem nulos, e se a `AidBox` possuir pelo menos um contentor cujo tipo seja igual ao tipo de suprimento do veículo (`vehicle.getSupplyType()`). Caso contrário, devolve `false`.

---

### Pergunta 2b (5 valores)

Na classe `LoadPlannerImpl`, implemente o método `getPriorityAidBoxesForVehicle`, selecionando as `AidBoxes` prioritárias a serem recolhidas por um veículo.

**Regras a considerar:**

- O método deve percorrer todas as `AidBoxes` devolvidas pelo método `getAidBoxes()` da interface `IInstitution`.
- Apenas devem ser consideradas `AidBoxes` que sejam compatíveis com o veículo (utilizando o método `isVehicleCompatible`).
- Das `AidBoxes` compatíveis, apenas devem ser incluídas no resultado aquelas cuja capacidade total para o tipo do veículo (utilizando o método `calculateTotalCapacityBySupplyType`) seja **maior ou igual** ao parâmetro `minimumCapacityRequired`.
- O array devolvido deve conter **apenas as AidBoxes selecionadas (sem posições nulas)**. Se nenhuma `AidBox` cumprir os requisitos ou os argumentos forem nulos, deve devolver um array com tamanho 0.

---

### Excertos de Código Fornecidos

```java
public class InstitutionImpl implements IInstitution {
    (...)
    private AidBox[] aidBoxes;
    private int numberOfAidBoxes;
    private Vehicle[] vehicles;
    private int numberOfVehicles;
    (...)

    public AidBox[] getAidBoxes() {
        (...)
    }

    public Vehicle[] getVehicles() {
        (...)
    }
}
```

```java
public interface AidBox {
    String getCode();
    String getZone();
    Container[] getContainers();
}
```
