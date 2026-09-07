# Exame Modelo 4 — Paradigmas de Programação — Época Especial 2025/2026

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

Explique detalhadamente o mecanismo de tratamento de exceções em Java. Distinga exceções verificadas (*Checked Exceptions*) de exceções não verificadas (*Unchecked Exceptions* / `RuntimeException`). Descreva o fluxo de execução do bloco `try-catch-finally` e explique o comportamento específico do bloco `finally` caso exista uma instrução `return` dentro do bloco `try`. Explicar igualmente a utilidade da instrução `try-with-resources` e a importância da interface `AutoCloseable`.

---

### Pergunta 2 (1,5 valores)

Explique o princípio do Encapsulamento em Programação Orientada a Objetos. Descreva detalhadamente os 4 modificadores de acesso do Java (`private`, *package-private* / default, `protected`, `public`) por ordem crescente de permissividade. Justifique por que razão a declaração de atributos como `public` é considerada uma má prática de programação e apresente um exemplo prático que demonstre a utilização correta de *getters* e *setters* com validação de dados.

---

### Pergunta 3 (1,5 valores)

Compare detalhadamente os conceitos de Sobrecarga (*Overloading*) e Sobreposição (*Overriding*) de métodos em Java. Descreva as regras que devem ser respeitadas em cada caso relativamente à lista de parâmetros, tipo de retorno e exceções declaradas. Explique o funcionamento do mecanismo de Despacho Dinâmico (*Dynamic Method Dispatch* / *Dynamic Binding*) executado pela JVM em tempo de execução para resolver chamadas a métodos sobrepostos. Ilustre com um exemplo prático.

---

### Pergunta 4 (1,5 valores)

Discuta o modificador `static` em Java aplicado a atributos e a métodos. Descreva as diferenças entre membros estáticos (de classe) e membros de instância no que diz respeito ao seu ciclo de vida, alocação de memória na JVM e partilha de informação entre objetos. Justifique explicitamente por que razão as palavras reservadas `this` e `super` não podem ser utilizadas dentro de métodos estáticos. Ilustre com exemplos de código.

---

## PARTE 2 (14,0 VALORES) — PROGRAMAÇÃO PRÁTICA EM JAVA (DOMÍNIO TP)

1. Considere o domínio do Trabalho Prático de Ajuda Humanitária. A interface `AidBox` descreve as operações de uma caixa de suprimentos que armazena contentores de bens (`Container`).

```java
public interface AidBox {
    String getCode();
    String getZone();
    Container[] getContainers();
    boolean addContainer(Container container) throws AidBoxFullException;
    boolean equals(Object obj);
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
public enum ItemType {
    PERISHABLE_FOOD,
    NON_PERISHABLE_FOOD,
    CLOTHING,
    MEDICINE
}
```

---

### Pergunta 1a (3,0 valores)

Considere a interface `AidBox` que representa uma caixa de suprimentos. Implemente a interface numa classe denominada `AidBoxImpl`.

**Regras a considerar:**
- A `AidBoxImpl` deve armazenar o código, a zona e possuir um array de `Container` com capacidade máxima fixada em **5 contentores**.
- O método `addContainer(Container container)` deve adicionar o contentor ao array. Caso o contentor a adicionar seja nulo ou se a capacidade máxima de 5 contentores já tiver sido atingida, o método deve lançar a exceção personalizada verificada `AidBoxFullException`.
- Para a implementação do método `equals(Object obj)`, considere que **duas instâncias** de `AidBox` são iguais se possuírem o mesmo código (devolvido por `getCode()`) **E** a mesma zona (devolvida por `getZone()`).

---

### Pergunta 1b (2,0 valores)

Desenvolva a classe de teste `AidBoxTest` com o método `main` para testar a classe implementada. O teste deve demonstrar obrigatoriamente:
1. A adição bem-sucedida de contentores a uma `AidBoxImpl`.
2. A tentativa de adicionar o 6º contentor, demonstrando a captura obrigatória da exceção `AidBoxFullException` através de um bloco `try-catch`.
3. A verificação experimental do comportamento do método `equals()` (testando duas caixas com mesmo código e zona versus caixas com código ou zona diferentes).

---

2. Considere a existência de uma classe `OptimizedStrategyImpl` que implementa a interface `Strategy`.

```java
public interface Strategy {
    Route[] generate(IInstitution inst, RouteValidator validator);
}
```

---

### Pergunta 2a (4,0 valores)

Na classe `OptimizedStrategyImpl`, implemente os seguintes métodos auxiliares baseados em **arrays nativos**:

```java
int countCriticalContainers(AidBox aidbox, double threshold);
```

- Este método deve devolver o número de contentores existentes na `AidBox` cuja última medição de carga registada (`getLastMeasurement()`) represente uma percentagem da capacidade do contentor superior a `threshold` (ex.: se a medição for 85.0 e a capacidade for 100.0, a percentagem é 85.0%). Se a AidBox for nula, não possuir contentores ou se um contentor não tiver medições, esse contentor deve ser ignorado.

```java
boolean isEligibleAidBox(AidBox aidbox, Vehicle vehicle, double threshold);
```

- Este método deve devolver `true` se a `AidBox` possuir pelo menos um contentor cujo tipo seja igual ao tipo de suprimento do veículo (`vehicle.getSupplyType()`) **E** se o número de contentores críticos na AidBox (obtido através da invocação do método `countCriticalContainers(aidbox, threshold)`) for estritamente superior a 0. Caso contrário, deve devolver `false`.

---

### Pergunta 2b (5,0 valores)

Na classe `OptimizedStrategyImpl`, implemente o método `generate`, gerando as rotas de recolha de bens para os veículos da instituição.

**Regras a considerar:**

- Para cada veículo devolvido por `getVehicles()` da interface `IInstitution`, deve ser criada uma nova rota (`Route`).
- As caixas de suprimentos disponíveis são as devolvidas por `getAidBoxes()` da interface `IInstitution`.
- **Deve utilizar obrigatoriamente os dois métodos desenvolvidos na alínea anterior (Pergunta 2a)**:
  - Para cada veículo e cada AidBox, deve verificar se a caixa é elegível invocando `isEligibleAidBox(box, vehicle, 75.0)`.
  - Se for elegível, deve tentar validar e adicionar a AidBox à rota com o `RouteValidator` e o método `addAidBox(box)` da interface `Route`. Caso o método `addAidBox` lance a exceção `RouteException`, essa AidBox não deve ser adicionada à rota.
- Apenas devem ser incluídas no array final devolvido as rotas que contenham pelo menos uma `AidBox` (rotas não vazias).
- O array devolvido pelo método `generate` não deve conter posições nulas nem rotas vazias, devendo ter a dimensão exata correspondente ao número de rotas válidas geradas.

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
