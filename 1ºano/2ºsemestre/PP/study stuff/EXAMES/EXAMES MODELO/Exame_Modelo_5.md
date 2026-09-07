# Exame Modelo 5 — Paradigmas de Programação — Época Especial 2025/2026

| | |
|---|---|
| **Instituição** | P.PORTO — Escola Superior de Tecnologia e Gestão |
| **Tipo de Prova** | Exame Escrito — Época Especial |
| **Curso** | Licenciatura em Engenharia Informática / Licenciatura em Segurança Informática em Redes de Computadores |
| **Unidade Curricular** | Paradigmas de Programação |
| **Ano Letivo** | 2025/2026 |
| **Duração** | 2 horas |

---

## Observações

- Não é permitida a consulta.
- Não são permitidas questões relativas à Parte 2. Sempre que considerarem necessário, os alunos devem assumir os pressupostos que entenderem adequados, indicando-os explicitamente na resolução.

---

## PARTE 1 (6,0 VALORES) — RESPOSTA ABERTA TEÓRICA

### Pergunta 1 (1,5 valores)

Explique o funcionamento da Gestão de Memória na JVM (Java Virtual Machine), distinguindo a memória *Heap* da memória *Stack*. Descreva o ciclo de vida dos objetos, o conceito de elegibilidade para o *Garbage Collector* (GC) e as diferenças entre referências fortes (*Strong References*), `SoftReference` e `WeakReference`. Esclareça igualmente as razões que levaram à descontinuação (*deprecation*) do método `finalize()` da classe `Object`.

---

### Pergunta 2 (1,5 valores)

Descreva detalhadamente o mecanismo de resolução de chamadas a métodos sobrecarregados (*overloading resolution*) pelo compilador Java. Explique a ordem de prioridade aplicada na presença de promoção implícita de tipos primitivos, autoboxing/unboxing e argumentos variáveis (*varargs* `...`). Compare este processo estático em tempo de compilação com o despacho dinâmico (*dynamic dispatch*) associado à sobreposição de métodos (*overriding*) em tempo de execução. Ilustre com um exemplo prático.

---

### Pergunta 3 (1,5 valores)

Explique a aplicação dos Padrões de Desenho de Software (*Design Patterns*) em Java, focando a sua atenção nos padrões **Strategy** e **Factory Method**. Descreva como estes padrões tiram partido dos princípios fundamentais da POO (polimorfismo, encapsulamento e interfaces) para promover o desacoplamento de código e o Princípio do Aberto/Fechado (*Open/Closed Principle* do SOLID). Ilustre a implementação do padrão *Strategy* com um exemplo de código.

---

### Pergunta 4 (1,5 valores)

Explique o contrato estrito existente entre os métodos `equals()` e `hashCode()` em Java. Descreva detalhadamente as regras que devem ser respeitadas ao redefinir ambos os métodos numa classe. Analise as consequências desastrosas que ocorrem na aplicação quando o método `equals()` é redefinido sem redefinir o método `hashCode()`, especificamente ao utilizar a classe em coleções baseadas em *hashing* (como `HashSet` ou `HashMap`). Ilustre com um exemplo prático.

---

## PARTE 2 (14,0 VALORES) — PROGRAMAÇÃO PRÁTICA EM JAVA (DOMÍNIO TP)

1. Considere a interface `RefrigeratedContainer` do Trabalho Prático que especializa a interface `Container` para armazenar bens que exigem controlo de temperatura estrito.

```java
public interface Container {
    String getCode();
    ItemType getType();
    double getCapacity();
    Measurement getLastMeasurement();
    Measurement[] getMeasurements();
}
```

```java
public interface RefrigeratedContainer extends Container {
    double getMinTemperature();
    double getMaxTemperature();
    double getCurrentTemperature();
    void addMeasurement(Measurement measurement) throws ContainerException;
    boolean equals(Object obj);
}
```

---

### Pergunta 1a (3,0 valores)

Considere a interface `RefrigeratedContainer`. Implemente a interface numa classe denominada `RefrigeratedContainerImpl`.

**Regras a considerar:**
- A classe deve armazenar o código do contentor, o tipo de item (`ItemType`), a capacidade, as temperaturas limite (`minTemperature` e `maxTemperature`) e um array de `Measurement` com capacidade máxima de **20 medições**.
- O método `addMeasurement(Measurement measurement)` adiciona uma medição ao histórico do contentor. Deve lançar a exceção personalizada verificada `ContainerException` caso a medição seja nula, se a capacidade de 20 medições tiver sido atingida, ou se o valor da medição (`getValue()`) estiver fora dos limites térmicos permitidos (menor que `minTemperature` ou maior que `maxTemperature`).
- O método `getCurrentTemperature()` deve devolver o valor da última medição adicionada ou 0.0 se não existirem medições.
- Para a implementação do método `equals(Object obj)`, duas instâncias de `RefrigeratedContainer` são iguais se possuírem o mesmo código (devolvido por `getCode()`).

---

### Pergunta 1b (2,0 valores)

Desenvolva a classe de teste `RefrigeratedContainerTest` com o método `main` para testar a classe implementada. O teste deve demonstrar obrigatoriamente:
1. A criação de um `RefrigeratedContainerImpl` e adição bem-sucedida de medições dentro dos limites térmicos.
2. A tentativa de adicionar uma medição fora dos limites de temperatura permitidos (ex.: medição de -35.0°C quando o mínimo é -20.0°C), demonstrando a captura obrigatória da exceção `ContainerException` através de um bloco `try-catch`.
3. A verificação experimental do método `equals()`.

---

2. Considere a existência de uma classe `DistributionPlannerImpl` que implementa a interface `DistributionPlanner`.

```java
public interface DistributionPlanner {
    Route[] planDistribution(IInstitution inst, RouteValidator validator);
}
```

---

### Pergunta 2a (4,0 valores)

Na classe `DistributionPlannerImpl`, implemente os seguintes métodos auxiliares baseados em **arrays nativos**:

```java
double calculateAidBoxTotalVolume(AidBox aidbox);
```

- Este método deve devolver o volume total ocupado na `AidBox`, calculado através da soma do valor da última medição de todos os contentores não nulos nela armazenados. Se a AidBox for nula ou não tiver contentores/medições, deve devolver 0.0.

```java
boolean isHighPriorityAidBox(AidBox aidbox, ItemType priorityType, double threshold);
```

- Este método deve devolver `true` se a `AidBox` possuir pelo menos um contentor cujo tipo seja igual ao `priorityType` recebido como argumento **E** cuja última medição de carga registada seja superior à percentagem `threshold` da capacidade desse contentor. Caso contrário, deve devolver `false`.

---

### Pergunta 2b (5,0 valores)

Na classe `DistributionPlannerImpl`, implemente o método `planDistribution`, gerando as rotas de distribuição de emergência para a instituição.

**Regras a considerar:**

- Para cada veículo devolvido por `getVehicles()` da interface `IInstitution`, deve ser criada uma nova rota (`Route`).
- As caixas de suprimentos disponíveis são as devolvidas por `getAidBoxes()` da interface `IInstitution`.
- **Deve utilizar obrigatoriamente os dois métodos desenvolvidos na alínea anterior (Pergunta 2a)**:
  - Para cada veículo e cada AidBox, deve verificar se a caixa é de alta prioridade invocando `isHighPriorityAidBox(box, vehicle.getSupplyType(), 80.0)`.
  - Se for elegível, deve tentar validar a adição com `validator.validate(route, box)` e adicionar a AidBox à rota com `route.addAidBox(box)`, capturando eventuais exceções `RouteException`.
- Apenas devem ser incluídas no array final devolvido as rotas que contenham pelo menos uma `AidBox` (não vazias).
- O array devolvido pelo método `planDistribution` não deve conter posições nulas nem rotas vazias, devendo apresentar a dimensão exata correspondente ao número de rotas válidas geradas.

---

### Excertos de Código Fornecidos

```java
public interface IInstitution {
    AidBox[] getAidBoxes();
    Vehicle[] getVehicles();
}
```

```java
public interface Vehicle {
    String getCode();
    ItemType getSupplyType();
    double getMaxCapacity();
}
```

```java
public interface AidBox {
    String getCode();
    String getZone();
    Container[] getContainers();
}
```

```java
public interface Measurement {
    double getValue();
    String getDate();
}
```

```java
public interface Route {
    Vehicle getVehicle();
    void addAidBox(AidBox aidBox) throws RouteException;
    AidBox removeAidBox(AidBox aidBox) throws RouteException;
    AidBox[] getRoute();
}
```

```java
public interface RouteValidator {
    boolean validate(Route route, AidBox aidBox);
}
```

```java
public class RouteException extends Exception {
    public RouteException(String message) { super(message); }
}
```

```java
public class ContainerException extends Exception {
    public ContainerException(String message) { super(message); }
}
```
