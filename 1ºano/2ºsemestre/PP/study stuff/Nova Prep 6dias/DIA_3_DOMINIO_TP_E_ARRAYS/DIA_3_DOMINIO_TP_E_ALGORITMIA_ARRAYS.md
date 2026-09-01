# 📗 DIA 3 — Domínio do Trabalho Prático & Algoritmia de Arrays (Parte 2 — 14,0 Valores)

> **Objetivo do Dia:** Dominar a 100% o Modelo de Domínio do Trabalho Prático (Caixas de Suprimentos / Ajuda Humanitária) e dominar os algoritmos de manipulação de **Arrays Nativos em Java** (sem a utilização do Java Collections Framework).

---

## 📌 PARTE A: MODELO DE DOMÍNIO DO TRABALHO PRÁTICO (TP 2025/2026)

```
                       +------------------------+
                       |      IInstitution      |
                       +------------------------+
                       | getAidBoxes(): AidBox[]|
                       | getVehicles(): Vehicle[]
                       +------------------------+
                                   |
            +----------------------+----------------------+
            |                                             |
            v                                             v
  +-------------------+                         +--------------------+
  |      AidBox       |                         |      Vehicle       |
  +-------------------+                         +--------------------+
  | getCode(): String |                         | getCode(): String  |
  | getZone(): String |                         | getSupplyType()    |
  | getContainers()   |                         | getMaxCapacity()   |
  +-------------------+                         +--------------------+
            |                                             ^
            v                                             | (extends)
  +-------------------+                         +--------------------+
  |     Container     |                         |RefrigeratedVehicle |
  +-------------------+                         +--------------------+
  | getCode(): String |                         | getMaxKilometers() |
  | getType():ItemType|                         | getStatus()        |
  | getCapacity()     |                         +--------------------+
  | getLastMeasurement|
  +-------------------+
            |
            v
  +-------------------+
  |    Measurement    |
  +-------------------+
  | getValue(): double|
  | getDate(): String |
  +-------------------+
```

---

## 📌 PARTE B: ALGORITMIA ESSENCIAL DE ARRAYS NATIVOS (SEM COLLECTIONS)

### Pattern 1: Redimensionamento Dinâmico de Arrays
```java
private void resize() {
    Container[] temp = new Container[this.containers.length * 2];
    for (int i = 0; i < this.numberOfContainers; i++) {
        temp[i] = this.containers[i];
    }
    this.containers = temp;
}
```

### Pattern 2: Compactação de Arrays e Remoção de Nulos
```java
public Container[] getContainers() {
    int count = 0;
    for (int i = 0; i < this.numberOfContainers; i++) {
        if (this.containers[i] != null) count++;
    }

    Container[] result = new Container[count];
    int index = 0;
    for (int i = 0; i < this.numberOfContainers; i++) {
        if (this.containers[i] != null) {
            result[index] = this.containers[i];
            index++;
        }
    }
    return result;
}
```

### Pattern 3: Pesquisa e Filtragem por Critérios Múltiplos
```java
public Container[] getCollectableContainers(AidBox aidBox, ItemType vehicleType) {
    if (aidBox == null || aidBox.getContainers() == null) return new Container[0];
    Container[] allContainers = aidBox.getContainers();
    
    int matchCount = 0;
    for (int i = 0; i < allContainers.length; i++) {
        Container c = allContainers[i];
        if (c != null && c.getType() == vehicleType) {
            Measurement last = c.getLastMeasurement();
            if (last != null && last.getValue() > (c.getCapacity() * 0.8)) {
                matchCount++;
            }
        }
    }

    Container[] matchArray = new Container[matchCount];
    int current = 0;
    for (int i = 0; i < allContainers.length; i++) {
        Container c = allContainers[i];
        if (c != null && c.getType() == vehicleType) {
            Measurement last = c.getLastMeasurement();
            if (last != null && last.getValue() > (c.getCapacity() * 0.8)) {
                matchArray[current] = c;
                current++;
            }
        }
    }

    return matchArray;
}
```

### Pattern 4: Deep Copy (Cópia Profunda) vs Shallow Copy
```java
public Container[] getContainersDeepCopy() {
    if (this.containers == null) return null;
    Container[] copy = new Container[this.numberOfContainers];
    for (int i = 0; i < this.numberOfContainers; i++) {
        copy[i] = this.containers[i]; 
    }
    return copy;
}
```
