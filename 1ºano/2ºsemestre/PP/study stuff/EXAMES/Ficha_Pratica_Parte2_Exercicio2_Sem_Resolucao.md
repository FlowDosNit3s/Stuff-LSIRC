# 📝 Ficha Prática de Exercícios — Parte 2 (Exercício 2)
## Paradigmas de Programação (PP) — P.PORTO ESTG / LEI & LSIRC

> **Instruções**: Resolve os exercícios num editor de código ou em papel. No final do documento, podes consultar as soluções detalhadas para verificar a tua resolução.

---

## 🎯 Exercício 1 — Sistema de Inspeção de Qualidade de Alimentos

### Contexto
Um centro de logística humanitária necessita de emitir relatórios de inspeção sanitária sobre os bens perecíveis armazenados nas suas caixas de suprimentos (`AidBox`).

### Interfaces Fornecidas

```java
public enum ItemType {
    PERISHABLE_FOOD,
    NON_PERISHABLE_FOOD,
    CLOTHING,
    MEDICINE
}

public interface Measurement {
    double getValue(); // Temperatura registada em ºC
}

public interface Container {
    String getCode();
    ItemType getType();
    double getCapacity();
    Measurement getLastMeasurement();
}

public interface AidBox {
    String getCode();
    String getZone();
    Container[] getContainers();
}

public interface InspectionReport {
    String getReportCode();
    int getExpiredCount();
}

public interface IInstitution {
    AidBox[] getAidBoxes();
}
```

---

### **Pergunta 1a (4,0 valores)**
Na classe `QualityInspectorImpl`, implementa os seguintes métodos auxiliares:

1. `boolean isContainerExpired(Container container)`
   - Devolve `true` se o contentor **não for nulo**, for do tipo `ItemType.PERISHABLE_FOOD` **E** a sua última medição for nula ou o valor da sua última medição (`getValue()`) for **superior a 15.0 ºC** (temperatura crítica de deterioração). Caso contrário, devolve `false`.

2. `int countExpiredContainersInAidBox(AidBox aidbox)`
   - Devolve o número total de contentores na `AidBox` que se encontram em estado de expiração (utilizando o método `isContainerExpired`). Se a `AidBox` for nula ou não possuir contentores, deve devolver `0`.

---

### **Pergunta 1b (5,0 valores)**
Na classe `QualityInspectorImpl`, implementa o método:

```java
InspectionReport[] generateInspectionReports(IInstitution inst)
```

**Regras a respeitar:**
- O método deve percorrer todas as `AidBoxes` devolvidas por `inst.getAidBoxes()`.
- Para cada `AidBox`, deve contar os contentores expirados recorrendo a `countExpiredContainersInAidBox`.
- Se uma `AidBox` possuir **pelo menos 1 contentor expirado**, deve criar uma nova instância de `InspectionReportImpl` com o código `"REP-" + aidbox.getCode()` e o número de contentores expirados.
- O array devolvido deve conter **apenas os relatórios gerados (sem posições nulas)**. Se nenhuma `AidBox` tiver contentores expirados ou os parâmetros forem nulos, deve devolver um array de tamanho 0.

---

## 🎯 Exercício 2 — Otimização de Carga por Capacidade Volumétrica

### Contexto
A instituição pretende selecionar quais as caixas de suprimentos (`AidBox`) que cumprem os requisitos de volume e tipo para serem carregadas num camião de transporte (`Vehicle`).

### Interfaces Fornecidas

```java
public interface Vehicle {
    String getCode();
    ItemType getSupplyType();
    double getMaxCapacity();
}

public interface Container {
    ItemType getType();
    double getCapacity();
}

public interface AidBox {
    String getCode();
    Container[] getContainers();
}

public interface TruckPlanner {
    AidBox[] selectBoxesForTruck(IInstitution inst, Vehicle truck, double targetVolume);
}
```

---

### **Pergunta 2a (4,0 valores)**
Na classe `TruckPlannerImpl`, implementa os seguintes métodos auxiliares:

1. `double calculateAidBoxTotalVolume(AidBox aidbox)`
   - Devolve a soma da capacidade (`getCapacity()`) de todos os contentores presentes na `AidBox`. Se a `AidBox` for nula ou não possuir contentores, devolve `0.0`.

2. `boolean isAidBoxSuitableForTruck(Vehicle truck, AidBox aidbox)`
   - Devolve `true` se o veículo e a `AidBox` não forem nulos, se a `AidBox` possuir **pelo menos um contentor** cujo tipo seja igual ao tipo do veículo (`truck.getSupplyType()`), **E** se o volume total da `AidBox` (calculado por `calculateAidBoxTotalVolume`) for menor ou igual à capacidade máxima do veículo (`truck.getMaxCapacity()`). Caso contrário, devolve `false`.

---

### **Pergunta 2b (5,0 valores)**
Na classe `TruckPlannerImpl`, implementa o método:

```java
AidBox[] selectBoxesForTruck(IInstitution inst, Vehicle truck, double targetVolume)
```

**Regras a respeitar:**
- Percorre todas as `AidBoxes` de `inst.getAidBoxes()`.
- Apenas considera `AidBoxes` adequadas ao camião (utilizando `isAidBoxSuitableForTruck`).
- Das `AidBoxes` adequadas, seleciona apenas aquelas cujo volume total (utilizando `calculateAidBoxTotalVolume`) seja **maior ou igual** ao parâmetro `targetVolume`.
- O array devolvido deve conter **apenas as AidBoxes selecionadas (sem posições nulas)**. Se nada for selecionado ou se os argumentos forem nulos, devolve um array com tamanho 0.

---

## 🎯 Exercício 3 — Sistema de Despacho de Drones de Emergência (Com Truncamento & Exceções)

### Contexto
Em cenários de catástrofe, a instituição utiliza Drones para transportar medicamentos urgentes. As caixas de suprimentos só podem ser associadas à rota do drone se cumprirem as validações de peso e autonomia.

### Interfaces Fornecidas

```java
public interface Route {
    void addAidBox(AidBox aidBox) throws DroneException;
    AidBox[] getRoute();
}

public interface DroneValidator {
    boolean validate(Route route, AidBox aidbox);
}

public interface DroneDispatcher {
    Route[] dispatchDrones(IInstitution inst, DroneValidator validator);
}
```

---

### **Pergunta 3a (4,0 valores)**
Na classe `DroneDispatcherImpl`, implementa os seguintes métodos auxiliares:

1. `boolean hasUrgentMedicine(AidBox aidbox)`
   - Devolve `true` se a `AidBox` possuir pelo menos um contentor do tipo `ItemType.MEDICINE` cuja última medição (`getLastMeasurement()`) não seja nula e tenha um valor superior a `90.0` (indicador de urgência crítica). Se a `AidBox` for nula ou não tiver contentores nestas condições, devolve `false`.

2. `boolean assignAidBoxToDroneRoute(Route route, AidBox aidbox, DroneValidator validator)`
   - Efetua a validação invocando `validator.validate(route, aidbox)`. Se a validação devolver `true`, tenta adicionar a `AidBox` à rota através de `route.addAidBox(aidbox)` e devolve `true`.
   - Caso a invocação de `route.addAidBox` origine uma `DroneException`, o método deve capturar a exceção e retornar `false`.

---

### **Pergunta 3b (5,0 valores)**
Na classe `DroneDispatcherImpl`, implementa o método `dispatchDrones`:

**Regras a respeitar:**
- Para cada veículo/drone retornado por `inst.getVehicles()`, cria uma nova rota `new RouteImpl(drone)`.
- Percorre todas as `AidBoxes` de `inst.getAidBoxes()`.
- Utiliza **obrigatoriamente** os métodos `hasUrgentMedicine` e `assignAidBoxToDroneRoute` desenvolvidos na alínea anterior.
- Uma rota só deve ser incluída no resultado final se tiver pelo menos 1 `AidBox` associada com sucesso.
- O array devolvido deve conter **apenas as rotas com caixas (sem posições nulas nem rotas vazias)**.

---

## 🎯 Exercício 4 — Relatório Estatístico de Eficiência por Zona

### Contexto
Calcular a taxa média global de ocupação dos contentores de determinado tipo numa dada zona geográfica.

---

### **Pergunta 4a (4,0 valores)**
Na classe `EfficiencyCalculatorImpl`, implementa os métodos:

1. `double getContainerOccupancyRate(Container container)`
   - Devolve a percentagem de ocupação do contentor através da fórmula: `(últimaMedição / capacidade) * 100.0`. Se o contentor ou a medição forem nulos, devolve `0.0`.

2. `double getAidBoxAverageOccupancyByType(AidBox aidbox, ItemType type)`
   - Devolve a média da taxa de ocupação (usando `getContainerOccupancyRate`) de todos os contentores da `AidBox` cujo tipo seja igual ao parâmetro `type`. Se a `AidBox` não possuir contentores desse tipo, devolve `0.0`.

---

### **Pergunta 4b (5,0 valores)**
Na classe `EfficiencyCalculatorImpl`, implementa o método:

```java
double getOverallEfficiencyByZone(IInstitution inst, String zone, ItemType type)
```

**Regras a respeitar:**
- Percorre todas as `AidBoxes` de `inst.getAidBoxes()`.
- Considera apenas as `AidBoxes` pertencentes à zona especificada (`aidbox.getZone().equalsIgnoreCase(zone)`).
- Calcula a média global da ocupação das `AidBoxes` dessa zona para o `ItemType` fornecido (somando o resultado de `getAidBoxAverageOccupancyByType` de cada caixa da zona e dividindo pelo número de caixas consideradas na zona).
- Devolve o valor `double` da média final. Se nenhuma caixa for encontrada para a zona, devolve `0.0`.

---

<br/><br/>

---

# 🔑 SOLUÇÕES E RESOLUÇÕES DETALHADAS

*(Consulta apenas depois de tentares resolver por ti próprio!)*

<details>
<summary>👉 Clica aqui para ver as Soluções do Exercício 1</summary>

```java
public class QualityInspectorImpl implements QualityInspector {

    // 1a - Método 1
    public boolean isContainerExpired(Container container) {
        if (container == null) return false;
        if (container.getType() != ItemType.PERISHABLE_FOOD) return false;
        
        Measurement last = container.getLastMeasurement();
        if (last == null || last.getValue() > 15.0) {
            return true;
        }
        return false;
    }

    // 1a - Método 2
    public int countExpiredContainersInAidBox(AidBox aidbox) {
        if (aidbox == null || aidbox.getContainers() == null) return 0;
        
        int count = 0;
        for (Container c : aidbox.getContainers()) {
            if (isContainerExpired(c)) {
                count++;
            }
        }
        return count;
    }

    // 1b - Método Principal
    public InspectionReport[] generateInspectionReports(IInstitution inst) {
        if (inst == null || inst.getAidBoxes() == null) {
            return new InspectionReport[0];
        }

        AidBox[] boxes = inst.getAidBoxes();
        InspectionReport[] tempReports = new InspectionReport[boxes.length];
        int count = 0;

        for (AidBox box : boxes) {
            if (box == null) continue;

            int expiredCount = countExpiredContainersInAidBox(box);
            if (expiredCount > 0) {
                String reportCode = "REP-" + box.getCode();
                tempReports[count++] = new InspectionReportImpl(reportCode, expiredCount);
            }
        }

        return Arrays.copyOf(tempReports, count);
    }
}
```
</details>

<details>
<summary>👉 Clica aqui para ver as Soluções do Exercício 2</summary>

```java
public class TruckPlannerImpl implements TruckPlanner {

    // 2a - Método 1
    public double calculateAidBoxTotalVolume(AidBox aidbox) {
        if (aidbox == null || aidbox.getContainers() == null) return 0.0;
        
        double totalVolume = 0.0;
        for (Container c : aidbox.getContainers()) {
            if (c != null) {
                totalVolume += c.getCapacity();
            }
        }
        return totalVolume;
    }

    // 2a - Método 2
    public boolean isAidBoxSuitableForTruck(Vehicle truck, AidBox aidbox) {
        if (truck == null || aidbox == null || aidbox.getContainers() == null) return false;

        boolean hasMatchingContainer = false;
        for (Container c : aidbox.getContainers()) {
            if (c != null && c.getType() == truck.getSupplyType()) {
                hasMatchingContainer = true;
                break;
            }
        }

        if (!hasMatchingContainer) return false;

        double totalVol = calculateAidBoxTotalVolume(aidbox);
        return totalVol <= truck.getMaxCapacity();
    }

    // 2b - Método Principal
    public AidBox[] selectBoxesForTruck(IInstitution inst, Vehicle truck, double targetVolume) {
        if (inst == null || inst.getAidBoxes() == null || truck == null) {
            return new AidBox[0];
        }

        AidBox[] boxes = inst.getAidBoxes();
        AidBox[] temp = new AidBox[boxes.length];
        int count = 0;

        for (AidBox box : boxes) {
            if (box == null) continue;

            if (isAidBoxSuitableForTruck(truck, box)) {
                double vol = calculateAidBoxTotalVolume(box);
                if (vol >= targetVolume) {
                    temp[count++] = box;
                }
            }
        }

        return Arrays.copyOf(temp, count);
    }
}
```
</details>

<details>
<summary>👉 Clica aqui para ver as Soluções do Exercício 3</summary>

```java
public class DroneDispatcherImpl implements DroneDispatcher {

    // 3a - Método 1
    private boolean hasUrgentMedicine(AidBox aidbox) {
        if (aidbox == null || aidbox.getContainers() == null) return false;
        
        for (Container c : aidbox.getContainers()) {
            if (c != null && c.getType() == ItemType.MEDICINE) {
                Measurement m = c.getLastMeasurement();
                if (m != null && m.getValue() > 90.0) {
                    return true;
                }
            }
        }
        return false;
    }

    // 3a - Método 2
    private boolean assignAidBoxToDroneRoute(Route route, AidBox aidbox, DroneValidator validator) {
        if (route == null || aidbox == null || validator == null) return false;

        if (validator.validate(route, aidbox)) {
            try {
                route.addAidBox(aidbox);
                return true;
            } catch (DroneException e) {
                return false;
            }
        }
        return false;
    }

    // 3b - Método Principal
    public Route[] dispatchDrones(IInstitution inst, DroneValidator validator) {
        if (inst == null || validator == null || inst.getVehicles() == null || inst.getAidBoxes() == null) {
            return new Route[0];
        }

        Vehicle[] drones = inst.getVehicles();
        AidBox[] boxes = inst.getAidBoxes();
        Route[] tempRoutes = new Route[drones.length];
        int routeCount = 0;

        for (Vehicle drone : drones) {
            if (drone == null) continue;

            Route route = new RouteImpl(drone);
            boolean hasBoxes = false;

            for (AidBox box : boxes) {
                if (box == null) continue;

                if (hasUrgentMedicine(box)) {
                    if (assignAidBoxToDroneRoute(route, box, validator)) {
                        hasBoxes = true;
                    }
                }
            }

            if (hasBoxes) {
                tempRoutes[routeCount++] = route;
            }
        }

        return Arrays.copyOf(tempRoutes, routeCount);
    }
}
```
</details>

<details>
<summary>👉 Clica aqui para ver as Soluções do Exercício 4</summary>

```java
public class EfficiencyCalculatorImpl implements EfficiencyCalculator {

    // 4a - Método 1
    public double getContainerOccupancyRate(Container container) {
        if (container == null || container.getLastMeasurement() == null || container.getCapacity() <= 0) {
            return 0.0;
        }
        return (container.getLastMeasurement().getValue() / container.getCapacity()) * 100.0;
    }

    // 4a - Método 2
    public double getAidBoxAverageOccupancyByType(AidBox aidbox, ItemType type) {
        if (aidbox == null || aidbox.getContainers() == null || type == null) return 0.0;

        double sumOccupancy = 0.0;
        int count = 0;

        for (Container c : aidbox.getContainers()) {
            if (c != null && c.getType() == type) {
                sumOccupancy += getContainerOccupancyRate(c);
                count++;
            }
        }

        if (count == 0) return 0.0;
        return sumOccupancy / count;
    }

    // 4b - Método Principal
    public double getOverallEfficiencyByZone(IInstitution inst, String zone, ItemType type) {
        if (inst == null || inst.getAidBoxes() == null || zone == null || type == null) {
            return 0.0;
        }

        double totalAvgOccupancy = 0.0;
        int boxCountInZone = 0;

        for (AidBox box : inst.getAidBoxes()) {
            if (box != null && box.getZone() != null && box.getZone().equalsIgnoreCase(zone)) {
                totalAvgOccupancy += getAidBoxAverageOccupancyByType(box, type);
                boxCountInZone++;
            }
        }

        if (boxCountInZone == 0) return 0.0;
        return totalAvgOccupancy / boxCountInZone;
    }
}
```
</details>
