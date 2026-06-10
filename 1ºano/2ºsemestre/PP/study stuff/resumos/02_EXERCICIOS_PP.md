# 💪 Exercícios Práticos Tipo Exame — PP 2025/2026

> **Contexto:** Os exercícios estão organizados por temas curriculares, formatados como perguntas de exame com cotação e focados no domínio prático (AidBox/Veículos/Medições). As resoluções completas encontram-se compiladas no final do documento.

---

### ⚠️ NOTA DE ENQUADRAMENTO COM O TRABALHO PRÁTICO REAL
Os exercícios práticos apresentados abaixo foram simplificados (ex: usando `ContainerType` em vez de `ItemType` ou gestão estática de arrays) para simular o que é habitualmente pedido nas folhas de exame escrito.

Para referência com a tua implementação real, consulta o mapeamento no ficheiro de preparação [03_EXAME_PREPARACAO_PP.md](file:///c:/Users/diogo/Documents/GitHub/Stuff-LSIRC/1ºano/2ºsemestre/PP/study%20stuff/resumos/03_EXAME_PREPARACAO_PP.md).

---

## 📋 Tema 1 — Fundamentos: Tipos, Controlo de Fluxo e Arrays

### 1.1) Tipos Primitivos, Literais e Operadores (Cotação: 1.5 valores)
Indique o tipo de dados, o valor final de cada variável e o output exato impresso na consola pelas seguintes instruções:
```java
int a = 10;
double b = a;
float c = 3.14f;
char d = 65;
boolean e = (a > 5) && (c < 4.0f);
int f = 0x1F;
int g = 032;

int x = 5;
System.out.println(x++);
System.out.println(++x);

int z = x++ + ++a;
System.out.println("x=" + x + " a=" + a + " z=" + z);
```

### 1.2) Estruturas de Controlo e Ciclos (Cotação: 1.5 valores)
Escreva um programa em Java que, dado o seguinte array de inteiros representando pesos de contentores recolhidos:
`int[] weights = {120, 50, 80, 210, 90, 140};`
Calcule e imprima na consola:
*   a) A soma total e a média dos pesos.
*   b) O maior e o menor peso presentes no array.
*   c) A contagem de contentores com peso par (usando ciclo `for`) e peso ímpar (usando ciclo `while`).

### 1.3) Arrays Dinâmicos com Gestão Manual (Cotação: 2.0 valores)
Crie uma classe `IntArray` que simule uma coleção dinâmica de inteiros gerida manualmente (sem usar ArrayList):
*   Atributos privados: `int[] elements` (capacidade inicial de 5) e `int count` (contador de elementos reais).
*   Métodos:
    *   `void add(int value)` — adiciona um elemento no fim. Se estiver cheio, duplica a capacidade do array antes de adicionar.
    *   `boolean remove(int value)` — remove a primeira ocorrência do valor, deslocando os elementos à direita para a esquerda. Retorna true se encontrou e removeu.
    *   `int size()` e `int get(int index)`.

---

## 📋 Tema 2 — Classes, Objetos e Encapsulamento

### 2.1) A Classe Container (Cotação: 1.5 valores)
Implemente a classe `Container` com os seguintes requisitos:
*   Atributos privados: `id` (String), `type` (ContainerType — enum), `maxCapacity` (double) e `currentLoad` (double).
*   Construtor parametrizado que inicializa `id`, `type` e `maxCapacity`.
*   Getters para todos e Setter para `currentLoad` (que deve validar se o peso está entre 0 e `maxCapacity`).
*   Métodos auxiliares: `boolean isFull()` e `double getOccupancyRate()` (taxa de 0.0 a 1.0).

### 2.2) A Classe AidBox (Cotação: 2.0 valores)
Implemente a classe `AidBox` para gerir um conjunto limitado de contentores:
*   Atributos privados: `id` (String), `zone` (String), `containers` (array de `Container` de tamanho fixo 4) e `containerCount` (int).
*   Construtor que recebe `id` e `zone`.
*   Métodos:
    *   `boolean addContainer(Container c)` — adiciona se houver espaço e se não existir duplicado de ID ou do mesmo tipo.
    *   `Container getContainer(String containerId)` — procura pelo ID.
    *   `Container[] getContainers()` — retorna uma cópia defensiva sem elementos nulos.

### 2.3) Demonstração e Instanciação (Cotação: 1.0 valores)
Crie uma classe `AidBoxDemo` com o método `main()` que instancie 3 contentores de tipos distintos, os adicione a uma nova `AidBox`, tente adicionar um contentor com tipo duplicado mostrando a falha, e imprima a representação em string da caixa.

---

## 📋 Tema 3 — Herança e Polimorfismo

### 3.1) Hierarquia de Veículos (Cotação: 1.5 valores)
Implemente a seguinte hierarquia de classes:
*   Classe base: `Vehicle` com atributos privados `id` (String), `maxCapacity` (double) e `itemType` (ItemType — enum). Construtor e getters.
*   Subclasse: `NormalVehicle` que estende `Vehicle` (sem novos atributos).
*   Subclasse: `RefrigeratedVehicle` que estende `Vehicle` com atributo `maxKmWithLoad` (double) e construtor correspondente.
*   Implemente os construtores adequados e redefina o método `toString()` em cada classe.

### 3.2) Override de Métodos e Consumo Especializado (Cotação: 1.5 valores)
Adicione um método de estimativa de consumo de combustível à hierarquia:
*   Na classe `Vehicle`, implemente o método `double getFuelConsumption(double distance)` que calcula o consumo base como `distance * 0.15` (litros).
*   Na classe `RefrigeratedVehicle`, faça override de `getFuelConsumption` somando um consumo fixo adicional de `5.0` litros (energia da câmara de frio) se a distância percorrida for maior que zero.

### 3.3) Polimorfismo Dinâmico e Casting (Cotação: 2.0 valores)
Crie uma classe `VehicleDemo` com o método `main()` que:
1.  Crie um array `Vehicle[]` contendo 2 veículos normais e 2 refrigerados.
2.  Itere o array e imprima o consumo estimado de cada um para uma viagem de 120 km.
3.  Use o operador `instanceof` e downcasting para imprimir o valor de `maxKmWithLoad` apenas para os veículos refrigerados.

---

## 📋 Tema 4 — Classes Abstratas

### 4.1) A Classe Abstrata Shape (Cotação: 1.5 valores)
Implemente uma classe abstrata `Shape` com atributo privado `name` (String), getters, construtor e os métodos abstratos `double area()` e `double perimeter()`. Desenhe as subclasses concretas `Circle` (recebe raio) e `Rectangle` (recebe largura e altura) com as respetivas fórmulas.

### 4.2) Abstração de Sensores do Trabalho Prático (Cotação: 2.0 valores)
Crie a classe abstrata `Sensor`:
*   Atributos privados: `id` (String) e `isActive` (boolean). Construtor e getters/setters.
*   Método abstrato: `boolean needsMaintenance()`.
*   Subclasse `WeightSensor` (atributo `currentWeight` e `maxCapacity`, precisa de manutenção se o peso for superior a 95% do limite).
*   Subclasse `TemperatureSensor` (atributo `currentTemp`, precisa de manutenção se a temperatura for superior a 10.0°C).

### 4.3) Uso de abstract e final (Cotação: 1.0 valores)
Demonstre a proteção de código contra herança e override:
*   Crie uma classe utilitária final `SensorUtility` (não pode ser herdada).
*   Adicione um método final `final void reset()` na classe abstrata `Sensor` para impedir que as subclasses redefinam a lógica de reset.

---

## 📋 Tema 5 — Interfaces

### 5.1) A Interface Measurable (Cotação: 1.5 valores)
Crie a interface `Measurable` com os métodos `double getMeasurementValue()` e `boolean isCritical()`. Modifique a classe `Container` para implementar a interface, onde `getMeasurementValue()` retorna a carga atual e `isCritical()` retorna true se a ocupação passar de 85%.

### 5.2) A Interface Exportable (JSON) (Cotação: 1.5 valores)
Crie a interface `Exportable` contendo o método `String toJsonString()`. Implemente a interface em `Container` e em `AidBox` de forma a que retornem representações JSON manuais estruturadas dos seus atributos.

### 5.3) Polimorfismo com Múltiplas Interfaces (Cotação: 2.0 valores)
Crie métodos estáticos na classe `DemoInterfaces` para demonstrar flexibilidade:
*   `void printJson(Exportable exp)` que imprime no ecrã a string JSON de qualquer objeto exportável.
*   `double sumCriticalLoads(Measurable[] items)` que calcula e retorna a soma dos valores de medição de todos os objetos em estado crítico do array.

---

## 📋 Tema 6 — Exceções

### 6.1) Exceções Personalizadas (Cotação: 1.5 valores)
Crie três classes de exceção do tipo Checked Exception:
*   `ContainerOverloadException` (lançada no carregamento excessivo).
*   `DuplicateContainerException` (lançada na inserção de duplicado).
*   `ContainerNotFoundException` (lançada na procura sem sucesso).

### 6.2) Lançamento de Exceções (Cotação: 2.0 valores)
Refatore as classes do Tema 2 para usarem exceções:
*   `Container.setCurrentLoad(...)` lança `ContainerOverloadException` se o limite for ultrapassado.
*   `AidBox.addContainer(...)` lança `DuplicateContainerException` se houver colisão de ID.
*   `AidBox.getContainer(...)` lança `ContainerNotFoundException` se o contentor não for encontrado.

### 6.3) Tratamento de Exceções com try-catch-finally (Cotação: 1.5 valores)
Implemente um teste na classe `ExceptionDemo` que demonstre a captura de múltiplas exceções de forma separada utilizando blocos `try-catch`, a propagação de exceções na assinatura dos métodos (`throws`) e a execução obrigatória de código no bloco `finally`.

---

## 📋 Tema 7 — Input/Output e Serialização

### 7.1) Leitura e Escrita em Ficheiros de Texto (Cotação: 1.5 valores)
Crie um programa em Java que peça ao utilizador (Scanner) dados de 3 caixas (ID e Zona), grave-os no ficheiro `caixas_output.txt` formatados como `ID;Zona` por linha, e de seguida leia o ficheiro de volta, imprimindo a informação formatada no ecrã.

### 7.2) Streams de Dados Binários (Cotação: 1.5 valores)
Escreva um programa em Java que armazene um array de medições de peso (`double[] readings = {12.4, 45.0, 99.8, 120.3};`) num ficheiro binário `sensor_data.dat` usando `DataOutputStream`. Leia as medições com `DataInputStream` e calcule o somatório e média das leituras.

### 7.3) Serialização de Objetos (Cotação: 2.0 valores)
Escreva as rotinas de persistência de objetos:
1.  Garanta que a classe `Container` implementa `java.io.Serializable`.
2.  Grave um array `Container[]` num ficheiro `backup_contentores.ser` usando `ObjectOutputStream`.
3.  Carregue os contentores de volta usando `ObjectInputStream`, faça o cast correto e imprima os seus dados.

---
---

## 🔑 Resoluções dos Exercícios (Consolidado)

Nesta secção encontram-se as respostas teóricas e o código Java completo para a resolução dos 21 exercícios práticos.

<details>
<summary>💡 Ver Resolução do Tema 1 (Fundamentos)</summary>

### Resolução 1.1:
*   `b`: Tipo `double`, valor `10.0`.
*   `d`: Tipo `char`, valor `'A'` (código ASCII 65).
*   `e`: Tipo `boolean`, valor `true` (`10 > 5` é true; `3.14 < 4.0f` é true).
*   `f`: Tipo `int`, valor `31` em decimal.
*   `g`: Tipo `int`, valor `26` em decimal (032 octal = $3 \times 8^1 + 2 \times 8^0 = 26$).
*   **Outputs na consola:**
    *   Primeiro `println(x++)` imprime `5` e incrementa `x` para `6`.
    *   Segundo `println(++x)` incrementa `x` para `7` e imprime `7`.
    *   `z` recebe `x++` (7) mais `++a` (11), totalizando `18`.
    *   A última linha imprime: `x=8 a=11 z=18`.

### Resolução 1.2:
```java
public class Tema1Ex2 {
    public static void main(String[] args) {
        int[] weights = {120, 50, 80, 210, 90, 140};
        
        // a) Soma e Média
        int sum = 0;
        for (int w : weights) {
            sum += w;
        }
        double avg = (double) sum / weights.length;
        System.out.println("Soma: " + sum + " | Média: " + avg);
        
        // b) Maior e Menor
        int max = weights[0];
        int min = weights[0];
        for (int i = 1; i < weights.length; i++) {
            if (weights[i] > max) max = weights[i];
            if (weights[i] < min) min = weights[i];
        }
        System.out.println("Máximo: " + max + " | Mínimo: " + min);
        
        // c) Pares (for) e Ímpares (while)
        int evens = 0;
        for (int i = 0; i < weights.length; i++) {
            if (weights[i] % 2 == 0) evens++;
        }
        
        int odds = 0;
        int idx = 0;
        while (idx < weights.length) {
            if (weights[idx] % 2 != 0) odds++;
            idx++;
        }
        System.out.println("Pares (for): " + evens + " | Ímpares (while): " + odds);
    }
}
```

### Resolução 1.3:
```java
public class IntArray {
    private int[] elements;
    private int count;

    public IntArray() {
        this.elements = new int[5];
        this.count = 0;
    }

    public void add(int value) {
        if (count == elements.length) {
            int[] temp = new int[elements.length * 2];
            System.arraycopy(elements, 0, temp, 0, elements.length);
            elements = temp;
        }
        elements[count++] = value;
    }

    public boolean remove(int value) {
        int index = -1;
        for (int i = 0; i < count; i++) {
            if (elements[i] == value) {
                index = i;
                break;
            }
        }
        if (index == -1) return false;
        
        for (int i = index; i < count - 1; i++) {
            elements[i] = elements[i + 1];
        }
        count--;
        return true;
    }

    public int size() { return count; }
    
    public int get(int index) {
        if (index < 0 || index >= count) {
            throw new IndexOutOfBoundsException("Índice inválido");
        }
        return elements[index];
    }
}
```
</details>

<details>
<summary>💡 Ver Resolução do Tema 2 (Classes, Objetos e Encapsulamento)</summary>

### Resolução 2.1:
```java
public enum ContainerType {
    PERISHABLE_FOOD, NON_PERISHABLE_FOOD, CLOTHING, MEDICINE
}

public class Container {
    private String id;
    private ContainerType type;
    private double maxCapacity;
    private double currentLoad;

    public Container(String id, ContainerType type, double maxCapacity) {
        this.id = id;
        this.type = type;
        this.maxCapacity = maxCapacity;
        this.currentLoad = 0.0;
    }

    public String getId() { return id; }
    public ContainerType getType() { return type; }
    public double getMaxCapacity() { return maxCapacity; }
    public double getCurrentLoad() { return currentLoad; }

    public void setCurrentLoad(double load) {
        if (load >= 0 && load <= maxCapacity) {
            this.currentLoad = load;
        }
    }

    public boolean isFull() { return currentLoad >= maxCapacity; }
    
    public double getOccupancyRate() {
        if (maxCapacity == 0) return 0.0;
        return currentLoad / maxCapacity;
    }
}
```

### Resolução 2.2:
```java
public class AidBox {
    private String id;
    private String zone;
    private Container[] containers;
    private int containerCount;

    public AidBox(String id, String zone) {
        this.id = id;
        this.zone = zone;
        this.containers = new Container[4];
        this.containerCount = 0;
    }

    public boolean addContainer(Container c) {
        if (c == null || containerCount >= 4) return false;
        
        // Valida ID duplicado e tipo duplicado
        for (int i = 0; i < containerCount; i++) {
            if (containers[i].getId().equals(c.getId()) || containers[i].getType() == c.getType()) {
                return false;
            }
        }
        containers[containerCount++] = c;
        return true;
    }

    public Container getContainer(String containerId) {
        for (int i = 0; i < containerCount; i++) {
            if (containers[i].getId().equals(containerId)) {
                return containers[i];
            }
        }
        return null;
    }

    public Container[] getContainers() {
        Container[] copy = new Container[containerCount];
        System.arraycopy(containers, 0, copy, 0, containerCount);
        return copy;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("AidBox: ").append(id).append(" (Zona: ").append(zone).append(")\n");
        for (int i = 0; i < containerCount; i++) {
            sb.append("  - ").append(containers[i].getId()).append(" (")
              .append(containers[i].getType()).append("): ")
              .append(containers[i].getCurrentLoad()).append("/")
              .append(containers[i].getMaxCapacity()).append(" kg\n");
        }
        return sb.toString();
    }
}
```

### Resolução 2.3:
```java
public class AidBoxDemo {
    public static void main(String[] args) {
        Container c1 = new Container("C1", ContainerType.PERISHABLE_FOOD, 500);
        Container c2 = new Container("C2", ContainerType.CLOTHING, 800);
        Container c3 = new Container("C3", ContainerType.MEDICINE, 300);
        
        AidBox box = new AidBox("AB01", "Porto");
        System.out.println("Adicionou C1? " + box.addContainer(c1));
        System.out.println("Adicionou C2? " + box.addContainer(c2));
        System.out.println("Adicionou C3? " + box.addContainer(c3));
        
        // Tipo duplicado (deve falhar)
        Container cDuplicado = new Container("C4", ContainerType.MEDICINE, 200);
        System.out.println("Adicionou duplicado? " + box.addContainer(cDuplicado)); // false
        
        System.out.println("\n" + box.toString());
    }
}
```
</details>

<details>
<summary>💡 Ver Resolução do Tema 3 (Herança e Polimorfismo)</summary>

### Resolução 3.1:
```java
public enum ItemType {
    PERISHABLE_FOOD, NON_PERISHABLE_FOOD, CLOTHING, MEDICINE
}

public class Vehicle {
    private String id;
    private double maxCapacity;
    private ItemType itemType;

    public Vehicle(String id, double maxCapacity, ItemType itemType) {
        this.id = id;
        this.maxCapacity = maxCapacity;
        this.itemType = itemType;
    }

    public String getId() { return id; }
    public double getMaxCapacity() { return maxCapacity; }
    public ItemType getItemType() { return itemType; }

    public double getFuelConsumption(double distance) {
        return distance * 0.15;
    }

    @Override
    public String toString() {
        return "Veículo: " + id + " [Tipo: " + itemType + ", Cap: " + maxCapacity + " kg]";
    }
}

public class NormalVehicle extends Vehicle {
    public NormalVehicle(String id, double maxCapacity, ItemType itemType) {
        super(id, maxCapacity, itemType);
    }
}

public class RefrigeratedVehicle extends Vehicle {
    private double maxKmWithLoad;

    public RefrigeratedVehicle(String id, double maxCapacity, double maxKmWithLoad) {
        super(id, maxCapacity, ItemType.PERISHABLE_FOOD);
        this.maxKmWithLoad = maxKmWithLoad;
    }

    public double getMaxKmWithLoad() { return maxKmWithLoad; }

    @Override
    public double getFuelConsumption(double distance) {
        if (distance > 0) {
            return super.getFuelConsumption(distance) + 5.0; // acresce 5L fixos da câmara de frio
        }
        return 0.0;
    }

    @Override
    public String toString() {
        return super.toString() + " [Refrigerado | Dist. Máx: " + maxKmWithLoad + " km]";
    }
}
```

### Resolução 3.2:
*(Implementado acima na classe `RefrigeratedVehicle` através da sobreposição do método `getFuelConsumption`).*

### Resolução 3.3:
```java
public class VehicleDemo {
    public static void main(String[] args) {
        Vehicle[] fleet = new Vehicle[4];
        fleet[0] = new NormalVehicle("N-1", 1000, ItemType.CLOTHING);
        fleet[1] = new RefrigeratedVehicle("R-1", 600, 300);
        fleet[2] = new NormalVehicle("N-2", 1200, ItemType.MEDICINE);
        fleet[3] = new RefrigeratedVehicle("R-2", 500, 200);

        for (Vehicle v : fleet) {
            System.out.println(v.toString());
            System.out.println("  Consumo para 120 km: " + v.getFuelConsumption(120) + " litros");
            
            // Downcasting com instanceof
            if (v instanceof RefrigeratedVehicle) {
                RefrigeratedVehicle ref = (RefrigeratedVehicle) v;
                System.out.println("  [Especificidade] Autonomia com Carga: " + ref.getMaxKmWithLoad() + " km");
            }
        }
    }
}
```
</details>

<details>
<summary>💡 Ver Resolução do Tema 4 (Classes Abstratas)</summary>

### Resolução 4.1:
```java
public abstract class Shape {
    private String name;

    public Shape(String name) { this.name = name; }
    public String getName() { return name; }

    public abstract double area();
    public abstract double perimeter();
}

public class Circle extends Shape {
    private double radius;
    public Circle(double radius) {
        super("Círculo");
        this.radius = radius;
    }
    @Override
    public double area() { return Math.PI * radius * radius; }
    @Override
    public double perimeter() { return 2 * Math.PI * radius; }
}

public class Rectangle extends Shape {
    private double width, height;
    public Rectangle(double width, double height) {
        super("Retângulo");
        this.width = width;
        this.height = height;
    }
    @Override
    public double area() { return width * height; }
    @Override
    public double perimeter() { return 2 * (width + height); }
}
```

### Resolução 4.2:
```java
public abstract class Sensor {
    private String id;
    private boolean isActive;

    public Sensor(String id) {
        this.id = id;
        this.isActive = true;
    }

    public String getId() { return id; }
    public boolean isActive() { return isActive; }
    public void setActive(boolean active) { this.isActive = active; }

    public abstract boolean needsMaintenance();

    public final void reset() {
        this.isActive = true;
        System.out.println("Sensor " + id + " reinicializado.");
    }
}

public class WeightSensor extends Sensor {
    private double currentWeight;
    private double maxCapacity;

    public WeightSensor(String id, double maxCapacity) {
        super(id);
        this.maxCapacity = maxCapacity;
    }

    public void setWeight(double weight) { this.currentWeight = weight; }

    @Override
    public boolean needsMaintenance() {
        return currentWeight > (maxCapacity * 0.95);
    }
}

public class TemperatureSensor extends Sensor {
    private double currentTemp;

    public TemperatureSensor(String id) {
        super(id);
    }

    public void setTemperature(double temp) { this.currentTemp = temp; }

    @Override
    public boolean needsMaintenance() {
        return currentTemp > 10.0;
    }
}
```

### Resolução 4.3:
```java
public final class SensorUtility {
    private SensorUtility() {} // impede instanciação

    public static void printSensorState(Sensor s) {
        System.out.println("Sensor: " + s.getId() + " | Ativo: " + s.isActive());
    }
}
// Explicação: Tentar estender SensorUtility (ex: class SubClass extends SensorUtility) 
// gera um erro de compilação: "cannot inherit from final class".
// Tentar fazer override de reset() na subclasse WeightSensor também gera erro 
// porque o método reset() está marcado como final na classe abstrata.
```
</details>

<details>
<summary>💡 Ver Resolução do Tema 5 (Interfaces)</summary>

### Resolução 5.1:
```java
public interface Measurable {
    double getMeasurementValue();
    boolean isCritical();
}

// Classe Container alterada
public class Container implements Measurable {
    private String id;
    private ContainerType type;
    private double maxCapacity;
    private double currentLoad;

    public Container(String id, ContainerType type, double maxCapacity) {
        this.id = id;
        this.type = type;
        this.maxCapacity = maxCapacity;
        this.currentLoad = 0.0;
    }

    public String getId() { return id; }
    public double getMaxCapacity() { return maxCapacity; }
    
    public void setCurrentLoad(double load) {
        if (load >= 0 && load <= maxCapacity) this.currentLoad = load;
    }

    @Override
    public double getMeasurementValue() {
        return this.currentLoad;
    }

    @Override
    public boolean isCritical() {
        if (maxCapacity == 0) return false;
        return (currentLoad / maxCapacity) > 0.85;
    }
}
```

### Resolução 5.2:
```java
public interface Exportable {
    String toJsonString();
}

// Alterações nas classes
// No Container:
// @Override
// public String toJsonString() {
//     return "{\"id\":\"" + id + "\",\"carga\":" + currentLoad + "}";
// }

// Na AidBox:
// @Override
// public String toJsonString() {
//     return "{\"caixaId\":\"" + id + "\",\"zona\":\"" + zone + "\"}";
// }
```

### Resolução 5.3:
```java
public class DemoInterfaces {
    public static void printJson(Exportable exp) {
        if (exp != null) {
            System.out.println("JSON Exportado: " + exp.toJsonString());
        }
    }

    public static double sumCriticalLoads(Measurable[] items) {
        double sum = 0.0;
        for (Measurable m : items) {
            if (m != null && m.isCritical()) {
                sum += m.getMeasurementValue();
            }
        }
        return sum;
    }
}
```
</details>

<details>
<summary>💡 Ver Resolução do Tema 6 (Exceções)</summary>

### Resolução 6.1:
```java
public class ContainerOverloadException extends Exception {
    public ContainerOverloadException(String msg) { super(msg); }
}

public class DuplicateContainerException extends Exception {
    public DuplicateContainerException(String msg) { super(msg); }
}

public class ContainerNotFoundException extends Exception {
    public ContainerNotFoundException(String msg) { super(msg); }
}
```

### Resolução 6.2:
```java
// Métodos alterados
public void setCurrentLoad(double load) throws ContainerOverloadException {
    if (load < 0 || load > maxCapacity) {
        throw new ContainerOverloadException("Erro: Excesso de carga para o limite " + maxCapacity);
    }
    this.currentLoad = load;
}

public boolean addContainer(Container c) throws DuplicateContainerException {
    if (c == null) return false;
    if (containerCount >= 4) return false;
    for (int i = 0; i < containerCount; i++) {
        if (containers[i].getId().equals(c.getId())) {
            throw new DuplicateContainerException("O contentor " + c.getId() + " já se encontra registado.");
        }
    }
    containers[containerCount++] = c;
    return true;
}

public Container getContainer(String containerId) throws ContainerNotFoundException {
    for (int i = 0; i < containerCount; i++) {
        if (containers[i].getId().equals(containerId)) {
            return containers[i];
        }
    }
    throw new ContainerNotFoundException("Nenhum contentor encontrado com ID: " + containerId);
}
```

### Resolução 6.3:
```java
public class ExceptionDemo {
    public static void main(String[] args) {
        AidBox box = new AidBox("AB-X", "Sul");
        Container c1 = new Container("C1", ContainerType.MEDICINE, 100);

        try {
            box.addContainer(c1);
            box.addContainer(c1); // lança DuplicateContainerException
        } catch (DuplicateContainerException e) {
            System.out.println("Capturado Erro: " + e.getMessage());
        } finally {
            System.out.println("Executou bloco finally de limpeza de recursos.");
        }

        try {
            c1.setCurrentLoad(9999); // lança ContainerOverloadException
        } catch (ContainerOverloadException e) {
            System.err.println("Erro crítico de carga: " + e.getMessage());
        }
    }
}
```
</details>

<details>
<summary>💡 Ver Resolução do Tema 7 (Input/Output e Serialização)</summary>

### Resolução 7.1:
```java
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.IOException;
import java.util.Scanner;

public class TextoIO {
    public static void main(String[] args) {
        // Escrita
        try (Scanner sc = new Scanner(System.in);
             PrintWriter writer = new PrintWriter(new FileWriter("caixas_output.txt"))) {
            for (int i = 1; i <= 3; i++) {
                System.out.print("ID da Caixa " + i + ": ");
                String id = sc.nextLine();
                System.out.print("Zona da Caixa " + i + ": ");
                String zona = sc.nextLine();
                writer.println(id + ";" + zona);
            }
        } catch (IOException e) {
            System.out.println("Erro na escrita: " + e.getMessage());
        }

        // Leitura
        try (BufferedReader reader = new BufferedReader(new FileReader("caixas_output.txt"))) {
            String line;
            System.out.println("\n--- Dados Lidos do Ficheiro ---");
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(";");
                System.out.println("Caixa: " + parts[0] + " | Local: " + parts[1]);
            }
        } catch (IOException e) {
            System.out.println("Erro na leitura: " + e.getMessage());
        }
    }
}
```

### Resolução 7.2:
```java
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class BinarioIO {
    public static void main(String[] args) {
        double[] readings = {12.4, 45.0, 99.8, 120.3};
        
        // Escrita Binária
        try (DataOutputStream dos = new DataOutputStream(new FileOutputStream("sensor_data.dat"))) {
            dos.writeInt(readings.length);
            for (double d : readings) {
                dos.writeDouble(d);
            }
        } catch (IOException e) {
            System.out.println("Erro na escrita binária: " + e.getMessage());
        }

        // Leitura Binária
        try (DataInputStream dis = new DataInputStream(new FileInputStream("sensor_data.dat"))) {
            int length = dis.readInt();
            double sum = 0.0;
            for (int i = 0; i < length; i++) {
                double val = dis.readDouble();
                sum += val;
            }
            System.out.println("Soma das Medições Binárias: " + sum + " | Média: " + (sum / length));
        } catch (IOException e) {
            System.out.println("Erro na leitura binária: " + e.getMessage());
        }
    }
}
```

### Resolução 7.3:
```java
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class SerializacaoDemo {
    public static void main(String[] args) {
        Container[] contentores = new Container[3];
        contentores[0] = new Container("C1", ContainerType.MEDICINE, 100);
        contentores[1] = new Container("C2", ContainerType.CLOTHING, 200);
        contentores[2] = new Container("C3", ContainerType.PERISHABLE_FOOD, 300);

        // Serializar
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("backup_contentores.ser"))) {
            oos.writeObject(contentores);
            System.out.println("Objetos serializados com sucesso!");
        } catch (IOException e) {
            System.out.println("Erro ao serializar: " + e.getMessage());
        }

        // Desserializar
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("backup_contentores.ser"))) {
            Container[] backups = (Container[]) ois.readObject();
            System.out.println("\n--- Contentores Recuperados ---");
            for (Container c : backups) {
                System.out.println("ID: " + c.getId() + " | Tipo: " + c.getType() + " | Cap: " + c.getMaxCapacity());
            }
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Erro ao desserializar: " + e.getMessage());
        }
    }
}
```
</details>
