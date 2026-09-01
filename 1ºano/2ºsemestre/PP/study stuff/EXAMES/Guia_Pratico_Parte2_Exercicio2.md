# Guia Prático & Ficha de Treino — Parte 2 (Exercício 2)
## Paradigmas de Programação (PP) — P.PORTO ESTG

---

## 💡 1. Entender o Padrão do Exercício 2 (A Lógica por Detrás)

Nos exames de PP, o **Exercício 2 da Parte 2** tem sempre uma estrutura intencional dividida em duas subperguntas:

- **Pergunta 2a (4,0 valores)**: Construção dos **Métodos Auxiliares ("Tijolos")**.
  - São 2 métodos pequenos e focados.
  - O **Método 1** atua sobre **1 objeto individual** (ex: testar se um `Container` tem carga recolhível ou se um `Vehicle` é compatível).
  - O **Método 2** atua sobre **uma operação focalizada ou agregação** (ex: validar e adicionar uma caixa a uma rota com tratamento de exceções).
- **Pergunta 2b (5,0 valores)**: Construção do **Método Principal / Gestor ("A Construção")**.
  - Este método **DEVE obrigatoriamente chamar os dois métodos desenvolvidos na 2a**.
  - O objetivo é percorrer a coleção de topo (normalmente `IInstitution.getVehicles()` ou `getAidBoxes()`), aplicar as validações de 2a e devolver um resultado (seja um total calculado ou um array final **sem elementos nulos nem posições vazias**).

---

## 🛠️ 2. O Algoritmo de Resolução em 4 Passos

```java
// ESTRUTURA PADRÃO EM JAVA PARA ARRAYS SEM NULOS (Exemplo de Gerador de Rotas)
public Route[] generate(IInstitution inst, RouteValidator validator) {
    // PASSO 1: Programação Defensiva
    if (inst == null || validator == null || inst.getVehicles() == null || inst.getAidBoxes() == null) {
        return new Route[0]; // Retorna array de tamanho 0 em vez de null
    }

    Vehicle[] vehicles = inst.getVehicles();
    AidBox[] boxes = inst.getAidBoxes();
    Route[] tempRoutes = new Route[vehicles.length]; // Tamanho máximo possível
    int routeCount = 0;

    // PASSO 2: Percorrer todos os veículos (Coleção principal)
    for (Vehicle vehicle : vehicles) {
        if (vehicle == null) continue;

        Route currentRoute = new RouteImpl(vehicle);
        boolean hasAddedAnyBox = false;

        // PASSO 3: Percorrer caixas e aplicar OS DOIS MÉTODOS de 2a!
        for (AidBox box : boxes) {
            if (box == null) continue;

            // Método Auxiliar 1 de 2a
            if (hasCollectableContainer(vehicle, box)) {
                // Método Auxiliar 2 de 2a
                if (addAidBoxToRoute(currentRoute, box, validator)) {
                    hasAddedAnyBox = true;
                }
            }
        }

        // Se a rota não ficou vazia, guarda-a
        if (hasAddedAnyBox) {
            tempRoutes[routeCount++] = currentRoute;
        }
    }

    // PASSO 4: Truncar o array para conter exatamente 'routeCount' elementos
    return Arrays.copyOf(tempRoutes, routeCount);
}
```

---

## ⭐️ 3. EXAME DE ÉPOCA NORMAL 2025/2026 — Resolução Detalhada

Este é o exame oficial da **Época Normal de 2025/2026** do teu semestre:

### 📄 Enunciado do Exercício 2:

Considera a interface `Strategy`:
```java
public interface Strategy {
    Route[] generate(IInstitution inst, RouteValidator validator);
}
```

---

### **Pergunta 2a (4,0 valores)**:
Implementa na classe `StrategyImpl`:

1. `boolean hasCollectableContainer(Vehicle vehicle, AidBox aidbox)`:
   - Devolve `true` caso exista pelo menos um `Container` na `AidBox` cujo tipo seja igual ao do veículo (`vehicle.getSupplyType()`) **E** cuja última medição registada seja superior a **80% da sua capacidade**.

2. `boolean addAidBoxToRoute(Route route, AidBox aidbox, RouteValidator validator)`:
   - Devolve `true` caso a `AidBox` seja adicionada à rota.
   - Deve efetuar a validação prévia com `validator.validate(route, aidbox)`. Se for válido, tenta adicionar a caixa com `route.addAidBox(aidbox)`.
   - Caso `route.addAidBox` lance uma `RouteException`, o método deve capturar a exceção e retornar `false`.

#### **Código de 2a**:
```java
public class StrategyImpl implements Strategy {

    private boolean hasCollectableContainer(Vehicle vehicle, AidBox aidbox) {
        // 1. Defesa contra null
        if (vehicle == null || aidbox == null || aidbox.getContainers() == null) {
            return false;
        }
        
        // 2. Percorrer os contentores da AidBox
        for (Container c : aidbox.getContainers()) {
            if (c == null) continue;
            
            // Verificar se o tipo coincide com o do veículo
            if (c.getType() == vehicle.getSupplyType()) {
                Measurement last = c.getLastMeasurement();
                // Verificar se tem medição e se excede 80% da capacidade
                if (last != null && last.getValue() > (c.getCapacity() * 0.80)) {
                    return true; // Encontrou pelo menos um!
                }
            }
        }
        return false; // Nenhum cumpriu os requisitos
    }

    private boolean addAidBoxToRoute(Route route, AidBox aidbox, RouteValidator validator) {
        // 1. Defesa contra null
        if (route == null || aidbox == null || validator == null) {
            return false;
        }
        
        // 2. Validar com o RouteValidator
        if (validator.validate(route, aidbox)) {
            try {
                route.addAidBox(aidbox);
                return true; // Adicionado com sucesso
            } catch (RouteException e) {
                return false; // Exceção capturada defensivamente
            }
        }
        return false; // Validação falhou
    }
```

---

### **Pergunta 2b (5,0 valores)**:
Implementa o método `generate`:
- Cria uma rota para cada veículo em `inst.getVehicles()`.
- Percorre as `AidBoxes` de `inst.getAidBoxes()`.
- Utiliza obrigatoriamente `hasCollectableContainer` e `addAidBoxToRoute`.
- Devolve um array de `Route` **sem posições nulas nem rotas vazias**.

#### **Código de 2b**:
```java
    @Override
    public Route[] generate(IInstitution inst, RouteValidator validator) {
        // 1. Defesa contra entradas nulas
        if (inst == null || validator == null) {
            return new Route[0];
        }

        Vehicle[] vehicles = inst.getVehicles();
        AidBox[] aidBoxes = inst.getAidBoxes();

        if (vehicles == null || aidBoxes == null || vehicles.length == 0 || aidBoxes.length == 0) {
            return new Route[0];
        }

        // 2. Array temporário com o tamanho máximo de veículos
        Route[] tempRoutes = new Route[vehicles.length];
        int routeCount = 0;

        // 3. Iterar por cada veículo
        for (Vehicle vehicle : vehicles) {
            if (vehicle == null) continue;

            Route currentRoute = new RouteImpl(vehicle);
            boolean routeHasAidBoxes = false;

            // 4. Iterar por cada AidBox e aplicar OS DOIS MÉTODOS DE 2a
            for (AidBox box : aidBoxes) {
                if (box == null) continue;

                // Chama Método 1 de 2a
                if (hasCollectableContainer(vehicle, box)) {
                    // Chama Método 2 de 2a
                    if (addAidBoxToRoute(currentRoute, box, validator)) {
                        routeHasAidBoxes = true;
                    }
                }
            }

            // Apenas adiciona a rota se contiver pelo menos 1 AidBox
            if (routeHasAidBoxes) {
                tempRoutes[routeCount] = currentRoute;
                routeCount++;
            }
        }

        // 5. Truncar array para conter exatamente o número de rotas válidas
        return Arrays.copyOf(tempRoutes, routeCount);
    }
}
```

---

## 📚 Outros Exemplos de Exames Modelo

---

### 🟢 EXAME MODELO 5 — Gestão de Carga Humanitária

#### **Pergunta 2a**:
- `calculateTotalCapacityBySupplyType(AidBox aidbox, ItemType type)`
- `isVehicleCompatible(Vehicle vehicle, AidBox aidbox)`

```java
@Override
public double calculateTotalCapacityBySupplyType(AidBox aidbox, ItemType type) {
    if (aidbox == null || type == null || aidbox.getContainers() == null) {
        return 0;
    }
    double totalCapacity = 0;
    for (Container container : aidbox.getContainers()) {
        if (container != null && container.getType() == type) {
            totalCapacity += container.getCapacity();
        }
    }
    return totalCapacity;
}

@Override
public boolean isVehicleCompatible(Vehicle vehicle, AidBox aidbox) {
    if (vehicle == null || aidbox == null || aidbox.getContainers() == null) {
        return false;
    }
    for (Container container : aidbox.getContainers()) {
        if (container != null && container.getType() == vehicle.getSupplyType()) {
            return true;
        }
    }
    return false;
}
```

#### **Pergunta 2b**:
```java
@Override
public AidBox[] getPriorityAidBoxesForVehicle(IInstitution inst, Vehicle vehicle, double minimumCapacityRequired) {
    if (inst == null || inst.getAidBoxes() == null || vehicle == null) {
        return new AidBox[0];
    }

    AidBox[] allBoxes = inst.getAidBoxes();
    AidBox[] temp = new AidBox[allBoxes.length];
    int count = 0;

    for (AidBox box : allBoxes) {
        if (box == null) continue;

        if (isVehicleCompatible(vehicle, box)) {
            double capacity = calculateTotalCapacityBySupplyType(box, vehicle.getSupplyType());
            if (capacity >= minimumCapacityRequired) {
                temp[count++] = box;
            }
        }
    }

    return Arrays.copyOf(temp, count);
}
```

---

### 🟡 EXAME MODELO 4 — Gestão de Alertas de Manutenção

#### **Pergunta 2a**:
- `isContainerInCriticalState(Container container)`
- `countCriticalContainersInAidBox(AidBox aidbox)`

```java
@Override
public boolean isContainerInCriticalState(Container container) {
    if (container == null || container.getLastMeasurement() == null) {
        return true;
    }
    return container.getLastMeasurement().getValue() > (container.getCapacity() * 0.95);
}

@Override
public int countCriticalContainersInAidBox(AidBox aidbox) {
    if (aidbox == null || aidbox.getContainers() == null) {
        return 0;
    }
    int count = 0;
    for (Container container : aidbox.getContainers()) {
        if (isContainerInCriticalState(container)) {
            count++;
        }
    }
    return count;
}
```

#### **Pergunta 2b**:
```java
@Override
public Alert[] generateMaintenanceAlerts(IInstitution inst) {
    if (inst == null || inst.getAidBoxes() == null) {
        return new Alert[0];
    }

    AidBox[] boxes = inst.getAidBoxes();
    Alert[] tempAlerts = new Alert[boxes.length];
    int count = 0;

    for (AidBox box : boxes) {
        if (box == null) continue;

        int numCriticos = countCriticalContainersInAidBox(box);
        if (numCriticos > 0) {
            int severity = Math.min(numCriticos, 5);
            String code = "ALT-" + box.getCode();
            String desc = "AidBox com " + numCriticos + " contentor(es) critico(s).";
            
            tempAlerts[count++] = new AlertImpl(code, AlertType.CAPACITY_OVERFLOW, desc, severity);
        }
    }

    return Arrays.copyOf(tempAlerts, count);
}
```

---

## ⚡ Resumo dos Cuidados Principais no Exame:

1. **Usar `Arrays.copyOf(temp, count)`** sempre que for devolvido um array sem posições nulas.
2. **Utilizar SEMPRE os métodos de 2a dentro do 2b** (o corretor penaliza se reescreveres o código).
3. **Tratamento de Exceções**: Se um dos métodos de 2a exige adicionar com `try-catch`, captura a exceção (`RouteException`) e retorna `false`.
4. **Null Checks em Cascata**:
   - `if (container == null || container.getLastMeasurement() == null)`
   - `if (inst == null || inst.getVehicles() == null)`
5. **Comparação de Enums**: Usa `==` diretamente (ex: `c.getType() == vehicle.getSupplyType()`).
