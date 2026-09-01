# 📕 DIA 4 — Exercícios Práticos no Formato dos Exames Modelo (Parte 2 — 14,0 Valores)

> **Instituição:** P.PORTO — Escola Superior de Tecnologia e Gestão  
> **Unidade Curricular:** Paradigmas de Programação (PP)  
> **Curso:** LEI / LSIRC  
> **Modelo da Prova:** Resolução Prática de Exames Modelo (Parte 2 — 14,0 Valores)  
> 
> 🔒 **Nota de Estudo:** Resolva estes 3 Conjuntos Práticos de Exame Modelo escrevendo o código em papel ou no seu IDE com base nos excertos de interfaces fornecidos. De seguida, confirme o seu código com o guia de resoluções de 20 valores no ficheiro [SOLUCOES_DIA_4_EXERCICIOS_PRATICOS.md](file:///c:/Users/diogo/Documents/GitHub/Stuff-LSIRC/1ºano/2ºsemestre/PP/study%20stuff/Nova%20Prep%205dias/SOLUCOES/SOLUCOES_DIA_4_EXERCICIOS_PRATICOS.md).

---

# 📋 CONJUNTO PRÁTICO MODELO 1 (14,0 VALORES)

### Contexto e Interfaces Fornecidas
Considere as seguintes interfaces `AidBox`, `Container`, `Report` e `IInstitution`. A interface `AidBox` descreve as operações possíveis de uma caixa de suprimentos e `Container` o contrato de um contentor.

```java
public enum ItemType {
    PERISHABLE_FOOD,
    NON_PERISHABLE_FOOD,
    CLOTHING,
    MEDICINE
}
```

```java
public interface Measurement {
    double getValue();
    String getDate();
}
```

```java
public interface Container {
    String getCode();
    ItemType getType();
    double getCapacity();
    Measurement[] getMeasurements();
    Measurement getLastMeasurement();
}
```

```java
public interface AidBox {
    String getCode();
    String getZone();
    Container[] getContainers();
    boolean equals(Object obj);
}
```

```java
public interface Report {
    String generate(IInstitution inst);
}
```

---

### Pergunta 1a (3,0 valores) — Implementação da Classe `AidBoxImpl`

Considere a interface `AidBox` que representa uma caixa de suprimentos. Implemente a interface numa classe denominada `AidBoxImpl`.

**Especificações do Problema:**
- A `AidBox` deve possuir um código (`String`), uma zona (`String`) e um array de `Container` com capacidade máxima inicial de 4 posições.
- Crie a exceção verificada (*Checked Exception*) `AidBoxFullException`.
- Implemente o método `boolean addContainer(Container container) throws AidBoxFullException` que adicione um contentor à `AidBox`, lançando `AidBoxFullException` caso a capacidade máxima de 4 contentores seja atingida ou `IllegalArgumentException` se o contentor for `null`.
- Para a implementação do método `equals(Object obj)`, considere que **duas instâncias de `AidBox` são iguais se possuírem o mesmo código (devolvido através do método `getCode()`)**, independentemente da zona.
- Implemente também o método `toString()` de forma a devolver uma representação legível do código e zona da caixa.

👉 *Resolução:* [SOLUCOES_DIA_4_EXERCICIOS_PRATICOS.md#conjunto-1---pergunta-1a](file:///c:/Users/diogo/Documents/GitHub/Stuff-LSIRC/1ºano/2ºsemestre/PP/study%20stuff/Nova%20Prep%205dias/SOLUCOES/SOLUCOES_DIA_4_EXERCICIOS_PRATICOS.md)

---

### Pergunta 1b (2,0 valores) — Classe de Teste `AidBoxTest`

Desenvolva o código necessário para testar a classe implementada no contexto de um método `main` numa classe denominada `AidBoxTest`.

**Especificações do Teste:**
- Instancie pelo menos 3 caixas de suprimentos (duas com o mesmo código e uma com código diferente).
- Teste a adição de contentores até atingir a capacidade máxima, demonstrando a captura e tratamento da exceção `AidBoxFullException` com um bloco `try-catch`.
- Apresente um exemplo de teste imprimindo no consola os resultados do método `equals()` e do método `toString()`.

👉 *Resolução:* [SOLUCOES_DIA_4_EXERCICIOS_PRATICOS.md#conjunto-1---pergunta-1b](file:///c:/Users/diogo/Documents/GitHub/Stuff-LSIRC/1ºano/2ºsemestre/PP/study%20stuff/Nova%20Prep%205dias/SOLUCOES/SOLUCOES_DIA_4_EXERCICIOS_PRATICOS.md)

---

### Pergunta 2a (4,0 valores) — Métodos Auxiliares na Classe `ReportImpl`

Considere a existência de uma classe `ReportImpl` que implementa a interface `Report`. Implemente os seguintes métodos auxiliares que podem ser utilizados para a geração do relatório:

```java
int countContainersByType(AidBox aidbox, ItemType type);
```
- Este método deve devolver o número de contentores existentes na `AidBox` cujo tipo seja igual ao `ItemType` recebido como argumento. Se a `AidBox` for `null` ou não possuir contentores, devolva `0`.

```java
double getAverageOccupancy(AidBox aidbox);
```
- Este método deve devolver a percentagem média de ocupação de todos os contentores da `AidBox`.
- A ocupação de cada contentor é dada pela fórmula:  
  $$\text{Ocupação} = \left( \frac{\text{Última Medição}}{\text{Capacidade Máxima}} \right) \times 100$$
- Se um contentor não possuir medições (`getLastMeasurement() == null`) ou for `null`, deve ser ignorado no cálculo da média. Se a caixa for `null` ou não tiver medições válidas, devolva `0.0`.

👉 *Resolução:* [SOLUCOES_DIA_4_EXERCICIOS_PRATICOS.md#conjunto-1---pergunta-2a](file:///c:/Users/diogo/Documents/GitHub/Stuff-LSIRC/1ºano/2ºsemestre/PP/study%20stuff/Nova%20Prep%205dias/SOLUCOES/SOLUCOES_DIA_4_EXERCICIOS_PRATICOS.md)

---

### Pergunta 2b (5,0 valores) — Método `generate` em `ReportImpl`

Na classe `ReportImpl`, implemente o método `generate(IInstitution inst)`, gerando um relatório textual formatado.

**Regras a considerar:**
- O relatório deve percorrer todas as `AidBoxes` devolvidas pelo método `getAidBoxes()` da interface `IInstitution`.
- Para cada `AidBox`, deve incluir no texto a informação do seu código, zona e o número de contentores de cada tipo (utilizando o método `countContainersByType`).
- **Apenas devem ser incluídas no relatório final as `AidBoxes` cuja ocupação média (utilizando o método `getAverageOccupancy`) seja superior a 50%**.
- O método `generate` deve devolver uma `String` com todo o relatório formatado.

👉 *Resolução:* [SOLUCOES_DIA_4_EXERCICIOS_PRATICOS.md#conjunto-1---pergunta-2b](file:///c:/Users/diogo/Documents/GitHub/Stuff-LSIRC/1ºano/2ºsemestre/PP/study%20stuff/Nova%20Prep%205dias/SOLUCOES/SOLUCOES_DIA_4_EXERCICIOS_PRATICOS.md)

---

# 📋 CONJUNTO PRÁTICO MODELO 2 (14,0 VALORES)

### Contexto e Interfaces Fornecidas
Considere as seguintes interfaces `Vehicle`, `Route`, `CollectionManager` e `IInstitution`.

```java
public interface Vehicle {
    String getCode();
    ItemType getSupplyType();
    double getMaxCapacity();
}
```

```java
public interface Route {
    Vehicle getVehicle();
    void addAidBox(AidBox aidBox) throws RouteException;
    AidBox removeAidBox(AidBox aidBox) throws RouteException;
    AidBox[] getRoute();
    boolean equals(Object obj);
}
```

```java
public interface CollectionManager {
    double getTotalCollectedByType(IInstitution inst, ItemType type);
}
```

---

### Pergunta 1a (3,0 valores) — Implementação da Classe `RouteImpl`

Considere a interface `Route` que representa uma rota de recolha de bens. Implemente a interface numa classe denominada `RouteImpl`.

**Especificações do Problema:**
- A rota deve ser associada a um `Vehicle` no momento da sua criação através do construtor.
- Deve conter um array de `AidBox` com capacidade máxima fixa de 10 posições.
- O método `addAidBox(AidBox aidBox)` deve adicionar a caixa ao array e lançar `RouteException` se a `AidBox` for `null`, se a rota já estiver cheia (10 caixas) ou se a caixa já existir previamente na rota.
- O método `removeAidBox(AidBox aidBox)` deve remover a caixa do array, reorganizando as posições, e lançar `RouteException` se a caixa não for encontrada.
- O método `getRoute()` deve devolver um array **compactado (sem posições nulas)** com as caixas atualmente na rota.
- Para o método `equals(Object obj)`, **duas rotas são iguais se estiverem associadas ao mesmo veículo** (comparando o código dos veículos devolvido por `vehicle.getCode()`).

👉 *Resolução:* [SOLUCOES_DIA_4_EXERCICIOS_PRATICOS.md#conjunto-2---pergunta-1a](file:///c:/Users/diogo/Documents/GitHub/Stuff-LSIRC/1ºano/2ºsemestre/PP/study%20stuff/Nova%20Prep%205dias/SOLUCOES/SOLUCOES_DIA_4_EXERCICIOS_PRATICOS.md)

---

### Pergunta 1b (2,0 valores) — Classe de Teste `RouteTest`

Desenvolva a classe `RouteTest` contendo um método `main` para testar a classe `RouteImpl`.

**Especificações do Teste:**
- Instancie um veículo e crie uma `RouteImpl`.
- Teste a adição de caixas de suprimentos e a remoção de uma caixa.
- Demonstre o tratamento da exceção `RouteException` ao tentar adicionar uma caixa duplicada ou remover uma caixa inexistente.

👉 *Resolução:* [SOLUCOES_DIA_4_EXERCICIOS_PRATICOS.md#conjunto-2---pergunta-1b](file:///c:/Users/diogo/Documents/GitHub/Stuff-LSIRC/1ºano/2ºsemestre/PP/study%20stuff/Nova%20Prep%205dias/SOLUCOES/SOLUCOES_DIA_4_EXERCICIOS_PRATICOS.md)

---

### Pergunta 2a (4,0 valores) — Métodos Auxiliares em `CollectionManagerImpl`

Na classe `CollectionManagerImpl`, implemente os seguintes métodos auxiliares:

```java
double getContainerLoad(Container container);
```
- Devolve o valor da última medição do contentor (`container.getLastMeasurement().getValue()`). Se o contentor for `null` ou não possuir medições, devolva `0.0`.

```java
boolean isContainerFull(Container container, double thresholdPercentage);
```
- Devolve `true` se a ocupação da última medição do contentor for superior à percentagem `thresholdPercentage` da sua capacidade máxima. Devolva `false` se o contentor for `null` ou não tiver medição.

👉 *Resolução:* [SOLUCOES_DIA_4_EXERCICIOS_PRATICOS.md#conjunto-2---pergunta-2a](file:///c:/Users/diogo/Documents/GitHub/Stuff-LSIRC/1ºano/2ºsemestre/PP/study%20stuff/Nova%20Prep%205dias/SOLUCOES/SOLUCOES_DIA_4_EXERCICIOS_PRATICOS.md)

---

### Pergunta 2b (5,0 valores) — Método `getTotalCollectedByType` em `CollectionManagerImpl`

Implemente o método `getTotalCollectedByType(IInstitution inst, ItemType type)` na classe `CollectionManagerImpl`.

**Regras a considerar:**
- O método deve percorrer todas as `AidBoxes` devolvidas pelo método `getAidBoxes()` da instituição.
- Para cada `AidBox`, deve percorrer os seus contentores e somar os valores de carga (utilizando o método `getContainerLoad`) de todos os contentores cujo tipo corresponda ao `ItemType` recebido como argumento.
- **Apenas devem ser somados os contentores que estejam considerados cheios** (utilizando o método `isContainerFull` com um threshold de 75.0%).
- Devolva o total de carga acumulado.

👉 *Resolução:* [SOLUCOES_DIA_4_EXERCICIOS_PRATICOS.md#conjunto-2---pergunta-2b](file:///c:/Users/diogo/Documents/GitHub/Stuff-LSIRC/1ºano/2ºsemestre/PP/study%20stuff/Nova%20Prep%205dias/SOLUCOES/SOLUCOES_DIA_4_EXERCICIOS_PRATICOS.md)

---

# 📋 CONJUNTO PRÁTICO MODELO 3 (14,0 VALORES)

### Contexto e Interfaces Fornecidas
Considere as seguintes interfaces `Vehicle`, `RefrigeratedVehicle`, `Strategy` e `RouteValidator`.

```java
public enum VehicleStatus {
    ENABLED,
    DISABLED
}
```

```java
public interface RefrigeratedVehicle extends Vehicle {
    double getMaxKilometers();
    VehicleStatus getStatus();
    void setStatus(VehicleStatus status);
    boolean equals(Object obj);
}
```

```java
public interface RouteValidator {
    boolean validate(Route route, AidBox aidBox);
}
```

```java
public interface Strategy {
    Route[] generate(IInstitution inst, RouteValidator validator);
}
```

---

### Pergunta 1a (3,0 valores) — Implementação de `RefrigeratedVehicleImpl`

Considere a interface `RefrigeratedVehicle` que representa um veículo refrigerado para transporte de bens sensíveis com um limite máximo de quilómetros.

**Especificações do Problema:**
- Implemente a interface numa classe denominada `RefrigeratedVehicleImpl`.
- O veículo possui código (`String`), tipo de item (`ItemType`), capacidade máxima (`double`), distância máxima em km (`double`) e um estado enum (`VehicleStatus`).
- O estado inicial deve ser inicializado como `ENABLED` por defeito no construtor.
- Para o método `equals(Object obj)`, considere que **duas instâncias de `RefrigeratedVehicle` são iguais se possuírem o mesmo código** (devolvido por `getCode()`).

👉 *Resolução:* [SOLUCOES_DIA_4_EXERCICIOS_PRATICOS.md#conjunto-3---pergunta-1a](file:///c:/Users/diogo/Documents/GitHub/Stuff-LSIRC/1ºano/2ºsemestre/PP/study%20stuff/Nova%20Prep%205dias/SOLUCOES/SOLUCOES_DIA_4_EXERCICIOS_PRATICOS.md)

---

### Pergunta 1b (2,0 valores) — Método `main` de Teste em `VehicleTest`

Escreva o método `main` numa classe `VehicleTest` para testar a classe `RefrigeratedVehicleImpl`.

**Especificações do Teste:**
- Instancie dois veículos com o mesmo código e um veículo com código diferente.
- Teste a alteração de estado através do método `setStatus()` de `ENABLED` para `DISABLED`.
- Teste e imprima no consola os resultados da comparação com `equals()`.

👉 *Resolução:* [SOLUCOES_DIA_4_EXERCICIOS_PRATICOS.md#conjunto-3---pergunta-1b](file:///c:/Users/diogo/Documents/GitHub/Stuff-LSIRC/1ºano/2ºsemestre/PP/study%20stuff/Nova%20Prep%205dias/SOLUCOES/SOLUCOES_DIA_4_EXERCICIOS_PRATICOS.md)

---

### Pergunta 2a (4,0 valores) — Métodos Auxiliares na Classe `StrategyImpl`

Na classe `StrategyImpl`, implemente os seguintes métodos auxiliares:

```java
boolean hasCollectableContainer(Vehicle vehicle, AidBox aidbox);
```
- Devolve `true` caso exista na `AidBox` pelo menos um contentor cujo tipo seja igual ao tipo do veículo (`vehicle.getSupplyType()`) e cuja última medição seja superior a 80% da sua capacidade máxima.

```java
boolean addAidBoxToRoute(Route route, AidBox aidbox, RouteValidator validator);
```
- Efetua previamente a validação invocando `validator.validate(route, aidbox)`. Se validado com sucesso, adicione a caixa à rota invocando `route.addAidBox(aidbox)`.
- Se o método `validate` devolver `false` ou se a invocação de `addAidBox` lançar `RouteException`, o método `addAidBoxToRoute` deve capturar a exceção e devolver `false`. Se adicionado com sucesso, devolve `true`.

👉 *Resolução:* [SOLUCOES_DIA_4_EXERCICIOS_PRATICOS.md#conjunto-3---pergunta-2a](file:///c:/Users/diogo/Documents/GitHub/Stuff-LSIRC/1ºano/2ºsemestre/PP/study%20stuff/Nova%20Prep%205dias/SOLUCOES/SOLUCOES_DIA_4_EXERCICIOS_PRATICOS.md)

---

### Pergunta 2b (5,0 valores) — Método `generate` em `StrategyImpl`

Na classe `StrategyImpl`, implemente o método `generate(IInstitution inst, RouteValidator validator)`.

**Regras a considerar:**
- Para cada veículo devolvido por `inst.getVehicles()`, crie uma nova rota.
- Para cada caixa devolvida por `inst.getAidBoxes()`, verifique se é elegível utilizando o método `hasCollectableContainer`. Se elegível, tente adicioná-la à rota com `addAidBoxToRoute`.
- **O array final devolvido por `generate` deve conter apenas rotas que possuam `AidBoxes` (deve eliminar posições nulas e rotas vazias)**.

👉 *Resolução:* [SOLUCOES_DIA_4_EXERCICIOS_PRATICOS.md#conjunto-3---pergunta-2b](file:///c:/Users/diogo/Documents/GitHub/Stuff-LSIRC/1ºano/2ºsemestre/PP/study%20stuff/Nova%20Prep%205dias/SOLUCOES/SOLUCOES_DIA_4_EXERCICIOS_PRATICOS.md)
