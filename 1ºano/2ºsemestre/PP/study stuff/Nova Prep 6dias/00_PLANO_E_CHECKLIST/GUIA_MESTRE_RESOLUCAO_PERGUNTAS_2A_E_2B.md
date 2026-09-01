# 🎯 GUIA MESTRE — ESTRATÉGIAS PARA RESOLVER AS PERGUNTAS 2A E 2B (9,0 VALORES)

> **Importância:** As Perguntas 2a e 2b valem **9,0 dos 14,0 valores da Parte Prática** (2a = 4,0v | 2b = 5,0v). Dominando a receita deste guia, garante mais de 60% da nota do exame instantaneamente!

---

## 💡 A ESTRUTURA MENTALE DAS PERGUNTAS 2A E 2B

```
 ┌────────────────────────────────────────────────────────────────────────┐
 │                      PERGUNTA 2A (4,0 VALORES)                         │
 │ 2 Métodos Auxiliares de apoio:                                         │
 │   - Método 1: Verificação Booleana (ex: hasCollectableContainer)      │
 │   - Método 2: Acção / Validação com Exceção (ex: addAidBoxToRoute)     │
 └───────────────────────────────────┬────────────────────────────────────┘
                                     │
                                     ▼ (Usados como blocos de construção)
 ┌────────────────────────────────────────────────────────────────────────┐
 │                      PERGUNTA 2B (5,0 VALORES)                         │
 │ Método Principal da Algoritmia (ex: generate / getTotalCollected):     │
 │   - Percorre a Instituição (Veículos x AidBoxes).                      │
 │   - Chama o Método 1 de 2a para filtrar.                              │
 │   - Chama o Método 2 de 2a para adicionar à Rota com try-catch.        │
 │   - Compacta o Array Final eliminando nulos e devolve!                 │
 └────────────────────────────────────────────────────────────────────────┘
```

---

# 🧠 PARTE 1: RECEITA PASSO-A-PASSO PARA A PERGUNTA 2A (4,0 VALORES)

A Pergunta 2a exige **SEMPRE 2 métodos auxiliares**. Identifique a tipologia de cada método e aplique o modelo correspondente:

---

### 🔹 TIPOLOGIA 1: Método de Verificação Booleana (Devolve `boolean`)
*Exemplo:* `boolean hasCollectableContainer(Vehicle vehicle, AidBox aidbox)` ou `boolean isContainerFull(Container container, double threshold)`

#### 📝 Esqueleto Infalível de Código (Copiar e Adaptar):
```java
public boolean temElementoElegivel(Vehicle vehicle, AidBox aidbox) {
    // PASSO 1: Validar nulos nas entradas (se for null, devove false)
    if (vehicle == null || aidbox == null) {
        return false;
    }

    // PASSO 2: Obter o array interno de elementos e validar se é null
    Container[] containers = aidbox.getContainers();
    if (containers == null) {
        return false;
    }

    // PASSO 3: Ciclo for com validação de nulo em CADA elemento
    for (int i = 0; i < containers.length; i++) {
        Container c = containers[i];
        if (c != null && c.getType() == vehicle.getSupplyType()) { // Comparação de enum com ==
            Measurement last = c.getLastMeasurement();
            // Validar se a medição existe e se cumpre a condição % (ex: > 80% da capacidade)
            if (last != null && last.getValue() > (c.getCapacity() * 0.8)) {
                return true; // Encontrou pelo menos um que cumpre! Retorna imediatamente true.
            }
        }
    }

    // PASSO 4: Se o ciclo terminar sem encontrar nenhum elegível
    return false;
}
```

---

### 🔹 TIPOLOGIA 2: Método de Ação / Validação com Exceção (Devolve `boolean` ou `void`)
*Exemplo:* `boolean addAidBoxToRoute(Route route, AidBox aidbox, RouteValidator validator)`

#### 📝 Esqueleto Infalível de Código (Copiar e Adaptar):
```java
public boolean adicionarComValidacao(Route route, AidBox aidbox, RouteValidator validator) {
    // PASSO 1: Validar se qualquer um dos objetos recebidos é null
    if (route == null || aidbox == null || validator == null) {
        return false;
    }

    // PASSO 2: Invocar a validação prévia (ex: validator)
    if (!validator.validate(route, aidbox)) {
        return false; // Se a validação falhar, não adiciona e devolve false
    }

    // PASSO 3: Tentar adicionar dentro de um bloco try-catch para capturar a RouteException
    try {
        route.addAidBox(aidbox);
        return true; // Adicionado com sucesso!
    } catch (RouteException e) {
        // Se o método addAidBox lançar RouteException (ex: caixa cheia ou duplicada)
        return false; // Captura a exceção e devolve false
    }
}
```

---

### 🔹 TIPOLOGIA 3: Método de Cálculo Numérico / Média (Devolve `double` ou `int`)
*Exemplo:* `double getAverageOccupancy(AidBox aidbox)` ou `int countContainersByType(AidBox aidbox, ItemType type)`

#### 📝 Esqueleto Infalível de Código (Copiar e Adaptar):
```java
public double calcularMediaOccupancy(AidBox aidbox) {
    // PASSO 1: Validar nulos nas entradas
    if (aidbox == null || aidbox.getContainers() == null) {
        return 0.0;
    }

    Container[] containers = aidbox.getContainers();
    double somaTotal = 0.0;
    int contadorValidos = 0;

    // PASSO 2: Percorrer os elementos
    for (int i = 0; i < containers.length; i++) {
        Container c = containers[i];
        if (c != null && c.getCapacity() > 0) {
            Measurement last = c.getLastMeasurement();
            if (last != null) {
                // Cálculo da percentagem individual
                double percentagem = (last.getValue() / c.getCapacity()) * 100.0;
                somaTotal += percentagem;
                contadorValidos++;
            }
        }
    }

    // PASSO 3: Devolver salvaguardando a divisão por zero
    if (contadorValidos == 0) return 0.0;
    return somaTotal / contadorValidos;
}
```

---

# 🧠 PARTE 2: RECEITA PASSO-A-PASSO PARA A PERGUNTA 2B (5,0 VALORES)

A Pergunta 2b é a grande pergunta de algoritmia. Pede tipicamente o método `generate` na classe `StrategyImpl` ou o método `generateReport` na classe `ReportImpl`.

---

### 🚀 ALGORITMO INFALÍVEL PARA `Route[] generate(IInstitution inst, RouteValidator validator)`

Aplique estritamente estes **5 PASSOS OBRIGATÓRIOS**:

```java
public class StrategyImpl implements Strategy {

    @Override
    public Route[] generate(IInstitution inst, RouteValidator validator) {
        
        // =========================================================================
        // PASSO 1: VALIDAÇÃO DE NULOS NAS ENTRADAS DA INSTITUIÇÃO E VALIDATOR
        // =========================================================================
        if (inst == null || validator == null) {
            return new Route[0]; // NUNCA devolva null! Devolva um array de tamanho 0.
        }

        Vehicle[] vehicles = inst.getVehicles();
        AidBox[] aidBoxes = inst.getAidBoxes();

        if (vehicles == null || aidBoxes == null) {
            return new Route[0];
        }

        // =========================================================================
        // PASSO 2: ALOCAR ARRAY TEMPORÁRIO (TAMANHO MÁXIMO POSSÍVEL = Nº VEÍCULOS)
        // =========================================================================
        Route[] tempRoutes = new Route[vehicles.length];
        int rotasValidasCount = 0; // Contador de rotas criadas que possuem caixas

        // =========================================================================
        // PASSO 3: CICLO DUPLO (PERCORRER VEÍCULOS X PERCORRER AIDBOXES)
        // =========================================================================
        for (int i = 0; i < vehicles.length; i++) {
            Vehicle v = vehicles[i];
            if (v == null) continue; // Ignora veículos nulos

            // 3.1 Instanciar uma nova Rota para o veículo atual
            Route RotaAtual = new RouteImpl(v);

            // 3.2 Percorrer todas as AidBoxes disponíveis
            for (int j = 0; j < aidBoxes.length; j++) {
                AidBox box = aidBoxes[j];

                // 3.3 Usar o PRIMEIRO método de 2a para saber se a caixa é elegível!
                if (box != null && hasCollectableContainer(v, box)) {
                    
                    // 3.4 Usar o SEGUNDO método de 2a para tentar adicionar à rota!
                    addAidBoxToRoute(RotaAtual, box, validator);
                }
            }

            // =========================================================================
            // PASSO 4: APENAS GUARDAR A ROTA SE ELA NÃO ESTIVER VAZIA (TIVER AIDBOXES)
            // =========================================================================
            if (RotaAtual.getRoute() != null && RotaAtual.getRoute().length > 0) {
                tempRoutes[rotasValidasCount] = RotaAtual;
                rotasValidasCount++;
            }
        }

        // =========================================================================
        // PASSO 5: COMPACTAÇÃO FINAL DO ARRAY (ELIMINAR POSIÇÕES NULL)
        // =========================================================================
        Route[] rotasFinais = new Route[rotasValidasCount];
        for (int i = 0; i < rotasValidasCount; i++) {
            rotasFinais[i] = tempRoutes[i];
        }

        return rotasFinais; // Array 100% limpo sem nulos nem posições sobrantes!
    }
}
```

---

## ⚡ CHECKLIST DE SOBREVIVÊNCIA PARA AS PERGUNTAS 2A E 2B

Quando terminares de escrever a Pergunta 2a e 2b, faz esta verificação mental em 15 segundos:

1. [ ] Colocaste `if (parametro == null) return ...;` no início de TODOS os métodos?
2. [ ] No ciclo `for`, validaste `if (array[i] != null)` antes de chamar qualquer getter?
3. [ ] Em 2b, usaste o tamanho de `vehicles.length` para criar o array temporário?
4. [ ] Em 2b, invocaste os métodos que criaste na 2a para filtrar e adicionar?
5. [ ] Em 2b, verificaste se a rota não estava vazia (`getRoute().length > 0`) antes de guardar?
6. [ ] Em 2b, criaste o array final `rotasFinais` com o tamanho `rotasValidasCount` para **remover todos os nulos**?

Se cumprires estes 6 pontos, tens **9,0 em 9,0 valores garantidos** nas Perguntas 2a e 2b!
