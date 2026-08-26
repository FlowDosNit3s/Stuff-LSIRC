# Exame Modelo 4 — Paradigmas de Programação — Época de Recurso 2025/2026

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

Explique detalhadamente o impacto e a finalidade da utilização da palavra reservada `final` em Java quando aplicada a: (1) uma classe, (2) um método e (3) uma variável. Esclareça adicionalmente a diferença de comportamento de uma variável `final` quando esta armazena um tipo primitivo versus quando armazena um tipo de referência (objeto). Ilustre com um exemplo prático que evidencie esta diferença.

---

### Pergunta 2 (1,5 valores)

Explique o conceito de classes Wrapper (de envolvimento) em Java e qual o seu propósito na linguagem. Descreva os mecanismos de Autoboxing e Unboxing automáticos e discuta os riscos associados à utilização do operador de igualdade `==` na comparação de objetos Wrapper (referindo o mecanismo de cache de `Integer` entre -128 e 127), por contraste com o método `equals()`. Ilustre com um exemplo prático de código.

---

### Pergunta 3 (1,5 valores)

Explique o mecanismo de Serialização de objetos em Java e a sua finalidade no desenvolvimento de software. Indique como se assinala que uma classe é elegível para serialização e descreva detalhadamente a utilidade e o comportamento do modificador `transient` e do identificador `serialVersionUID`. Forneça um exemplo de código de uma classe serializável que utilize ambos os mecanismos.

---

### Pergunta 4 (1,5 valores)

A biblioteca de Input/Output (I/O) do Java divide-se principalmente em classes orientadas a bytes (Streams) e classes orientadas a caracteres (Readers/Writers). Explique a diferença fundamental entre estas duas famílias de classes, indicando a dimensão dos dados processados por cada uma e descrevendo cenários práticos adequados para a utilização de cada abordagem. Ilustre com exemplos de classes representativas de cada família.

---

## Parte 2

1. Considere as seguintes interfaces `Alert` e `Container`. A interface `Alert` descreve as operações de registo de anomalias e manutenção no sistema de recolha de bens de uma instituição de ajuda humanitária. A interface `Container` define o contrato de um contentor associado a uma caixa de suprimentos (`AidBox`).

```java
public interface Alert {
    String getCode();
    AlertType getType();
    String getDescription();
    int getSeverityLevel();
    boolean equals(Object obj);
}
```

```java
public enum AlertType {
    CAPACITY_OVERFLOW,
    MISSING_MEASUREMENTS,
    INVALID_SENSOR
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

Considere a interface `Alert` que representa um alerta de manutenção emitido pelo sistema. Implemente a interface numa classe denominada `AlertImpl`. A classe deve armazenar o código do alerta (`code`), o tipo de alerta (`type`), uma descrição textual (`description`) e um nível de gravidade (`severityLevel`, numérico de 1 a 5). O construtor deve lançar `IllegalArgumentException` se o código ou o tipo forem nulos, ou se o nível de gravidade estiver fora do intervalo [1, 5]. Para a implementação do método `equals`, considere que **duas instâncias** de `Alert` são iguais se possuírem o mesmo código (devolvido por `getCode()`).

---

### Pergunta 1b (2 valores)

Desenvolva o código necessário para testar a classe implementada (por exemplo, no contexto de um método `main`). Apresente um exemplo de teste para cada método implementado, incluindo a verificação do método `equals` e a captura de exceções em caso de dados inválidos.

---

2. Considere a existência de uma classe `AlertManagerImpl` que implemente a interface `AlertManager`.

```java
public interface AlertManager {
    Alert[] generateMaintenanceAlerts(IInstitution inst);
}
```

---

### Pergunta 2a (4 valores)

Implemente os seguintes métodos na classe `AlertManagerImpl`:

```java
boolean isContainerInCriticalState(Container container);
```

- Este método deve devolver `true` caso o contentor seja nulo, a sua última medição seja nula (`getLastMeasurement() == null`), ou o valor da sua última medição exceda 95% da sua capacidade total. Caso contrário, devolve `false`.

```java
int countCriticalContainersInAidBox(AidBox aidbox);
```

- Este método deve devolver o número de contentores na `AidBox` que se encontram em estado crítico (utilizando o método `isContainerInCriticalState`). Se a `AidBox` for nula ou não possuir contentores, deve devolver 0.

---

### Pergunta 2b (5 valores)

Na classe `AlertManagerImpl`, implemente o método `generateMaintenanceAlerts`, gerando os alertas necessários para a instituição.

**Regras a considerar:**

- O método deve percorrer todas as `AidBoxes` devolvidas pelo método `getAidBoxes()` da interface `IInstitution`.
- Para cada `AidBox`, deve contar o número de contentores em estado crítico recorrendo ao método `countCriticalContainersInAidBox`.
- Se uma `AidBox` possuir pelo menos 1 contentor em estado crítico, deve ser criado e adicionado um novo alerta (`AlertImpl`) à lista de alertas emitidos.
  - O código do alerta deve ser formado pela concatenação `"ALT-"` com o código da `AidBox`.
  - O tipo de alerta deve ser `AlertType.CAPACITY_OVERFLOW`.
  - A descrição deve ser `"AidBox com " + numCriticos + " contentor(es) critico(s)."`.
  - O nível de gravidade deve ser igual ao número de contentores críticos (limitado ao máximo de 5).
- O array devolvido pelo método `generateMaintenanceAlerts` deve conter **apenas os alertas efetivamente gerados (sem posições nulas)**. Se nenhum alerta for gerado, deve devolver um array com tamanho 0.

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
