# 📚 Resumo de Estudo — Paradigmas de Programação (PP) 2025/2026

> **Nota:** Este resumo cobre toda a matéria lecionada (Aulas 01-12) e está adaptado ao trabalho prático deste ano (AidBox – Recolha de Bens Humanitários).

---

## 📌 Índice
1. [Fundamentos Java](#1-fundamentos-java)
2. [Operadores e Controlo de Fluxo](#2-operadores-e-controlo-de-fluxo)
3. [Classes e Objetos](#3-classes-e-objetos)
4. [Construtores e Strings](#4-construtores-e-strings)
5. [Enumerações, Métodos e Encapsulamento](#5-enumerações-métodos-e-encapsulamento)
6. [Herança e Relações entre Classes](#6-herança-e-relações-entre-classes)
7. [Classes Abstratas e Final](#7-classes-abstratas-e-final)
8. [Interfaces](#8-interfaces)
9. [Pilares da POO](#9-pilares-da-poo)
10. [Exceções](#10-exceções)
11. [Input/Output e Serialização](#11-inputoutput-e-serialização)
12. [Contexto do Trabalho Prático](#12-contexto-do-trabalho-prático)

---

## 1. Fundamentos Java

### 1.1 Tipos Primitivos

| Tipo      | Tamanho        | Valor por defeito | Exemplo             |
|-----------|----------------|-------------------|---------------------|
| `boolean` | true/false     | `false`           | `boolean x = true;` |
| `byte`    | 8 bits         | `0`               | `byte b = 100;`     |
| `char`    | 16 bits Unicode| `'\u0000'`        | `char c = 'A';`     |
| `short`   | 16 bits        | `0`               | `short s = 10000;`  |
| `int`     | 32 bits        | `0`               | `int i = 100000;`   |
| `long`    | 64 bits        | `0L`              | `long l = 100L;`    |
| `float`   | 32 bits IEEE   | `0.0f`            | `float f = 1.2f;`   |
| `double`  | 64 bits IEEE   | `0.0d`            | `double d = 3.14;`  |

### 1.2 Literais
```java
int decVal = 26;       // Decimal
int octVal = 032;      // Octal (prefixo 0)
int hexVal = 0x1a;     // Hexadecimal (prefixo 0x)
double d1 = 123.4;
double d2 = 1.234e2;   // Notação científica
float f1 = 123.4f;     // Sufixo f obrigatório para float
```

### 1.3 Constantes
```java
final int MAX_SIZE = 100; // Palavra-chave final → constante
```

### 1.4 Arrays
```java
// Declaração e criação
int[] anArray = new int[10];
anArray[0] = 100;

// Declaração e inicialização numa linha
int[] nums = {3, 4, 9, 2};

// Propriedade .length para saber o tamanho
System.out.println(nums.length); // 4
```

> ⚠️ **ATENÇÃO:** O tamanho de um array é fixo após criação. Não pode ser alterado.

---

## 2. Operadores e Controlo de Fluxo

### 2.1 Operadores Multifunção
| Operador | Exemplo  | Equivalente    |
|----------|----------|----------------|
| `++`     | `x++`    | `x = x + 1`   |
| `--`     | `x--`    | `x = x - 1`   |
| `+=`     | `x += y` | `x = x + y`   |
| `-=`     | `x -= y` | `x = x - y`   |
| `*=`     | `x *= y` | `x = x * y`   |
| `/=`     | `x /= y` | `x = x / y`   |
| `%=`     | `x %= y` | `x = x % y`   |

> ⚠️ **`++x` vs `x++`:** Pré-incremento (`++x`) incrementa ANTES da avaliação. Pós-incremento (`x++`) incrementa DEPOIS.

```java
int x = 9;
if (++x == 10) // TRUE → x é incrementado antes da comparação
if (x++ == 10) // Compara primeiro, incrementa depois
```

### 2.2 Execução Condicional
```java
// if-else
if (condition) {
    // ...
} else if (condition2) {
    // ...
} else {
    // ...
}

// switch
switch (variable) {
    case valor1: /* ... */ break;
    case valor2: /* ... */ break;
    default: /* ... */ break;
}

// Operador ternário
int max = (a > b) ? a : b;
```

### 2.3 Execução Iterativa
```java
// while — usar quando NÃO sabemos quantas vezes iterar
while (condition) { /* ... */ }

// do-while — executa PELO MENOS uma vez
do { /* ... */ } while (condition);

// for — usar quando sabemos o número de iterações
for (int i = 0; i < n; i++) { /* ... */ }
```

---

## 3. Classes e Objetos

### 3.1 Conceito de Classe
- Uma **classe** é um módulo de software que define **atributos** (características) e **métodos** (comportamentos)
- Permite **reutilizar código**
- Os **objetos** são construídos (instanciados) a partir das classes

### 3.2 Modificadores de Acesso

| Modificador   | Classe | Package | Subclasse | Mundo |
|---------------|--------|---------|-----------|-------|
| `public`      | ✅     | ✅      | ✅        | ✅    |
| `protected`   | ✅     | ✅      | ✅        | ❌    |
| *(nenhum)*    | ✅     | ✅      | ❌        | ❌    |
| `private`     | ✅     | ❌      | ❌        | ❌    |

### 3.3 Modificador `static`
- Membros `static` são **membros de classe** (partilhados por todas as instâncias)
- Não precisam de um objeto para serem acedidos: `NomeDaClasse.membro`
- Membros sem `static` são **membros de instância** (cada objeto tem o seu)

```java
public class Counter {
    static int count = 0;      // Partilhado por todos os objetos
    int instanceId;             // Cada objeto tem o seu

    Counter() {
        count++;
        instanceId = count;
    }
}
```

---

## 4. Construtores e Strings

### 4.1 Criar Objetos
```java
// Operador new + Construtor
Dog fido = new Dog();
Dog spot = new Dog("Spot", 3);
```

### 4.2 Métodos Construtores
- Têm o **mesmo nome da classe**
- **Não** têm tipo de retorno
- Podem ter **múltiplos construtores** (sobrecarga)
- Se não definir nenhum, Java cria um **construtor vazio** por defeito

```java
public class Dog {
    String name;
    int age;

    // Construtor 1 — só nome
    Dog(String name) {
        this.name = name;
    }

    // Construtor 2 — nome e idade
    Dog(String name, int age) {
        this.name = name;
        this.age = age;
    }
}
```

### 4.3 Strings
- `String` é um tipo **não primitivo** (classe do `java.lang`)
- As instâncias de String são **imutáveis**
- O operador `+` concatena Strings
- Métodos comuns: `.length()`, `.charAt()`, `.equals()`, `.substring()`, `.compareTo()`

```java
String nome = "João";                    // Forma simplificada
String nome2 = new String("João");       // Forma explícita
```

---

## 5. Enumerações, Métodos e Encapsulamento

### 5.1 Enumerações (Enums)
- Conjunto de **constantes fixas**
- Definidas com a palavra `enum`

```java
public enum ContainerType {
    PERISHABLE_FOOD, NON_PERISHABLE_FOOD, CLOTHING, MEDICINE
}

// Usar em switch:
switch (type) {
    case PERISHABLE_FOOD: /* ... */ break;
    case CLOTHING: /* ... */ break;
}

// Iterar sobre valores:
for (ContainerType ct : ContainerType.values()) {
    System.out.println(ct.name());
}
```

### 5.2 Métodos
```java
// Procedimento (sem retorno)
void bark() {
    System.out.println(barkSound);
}

// Função (com retorno)
double getArea() {
    return width * height;
}

// Invocar: instância.método()
fido.bark();
```

### 5.3 Sobrecarga de Métodos (Overloading)
- Múltiplos métodos com o **mesmo nome** mas **assinaturas diferentes** (parâmetros diferentes)
- O tipo de retorno **NÃO** diferencia métodos

```java
void bark() { System.out.println(barkSound); }
void bark(String sound) { System.out.println(sound); }
```

### 5.4 Encapsulamento
- Atributos devem ser **`private`**
- Acesso através de métodos **getters** e **setters**
- Usa-se `this` para distinguir entre parâmetro e variável de instância

```java
public class Container {
    private double capacity;
    private ContainerType type;

    public double getCapacity() { return this.capacity; }
    public void setCapacity(double capacity) { this.capacity = capacity; }

    public ContainerType getType() { return this.type; }
    // Getter booleano pode usar 'is' em vez de 'get'
    public boolean isFull() { return currentLoad >= capacity; }
}
```

---

## 6. Herança e Relações entre Classes

### 6.1 Herança (`extends`)
- Uma subclasse **herda** atributos e métodos da superclasse
- Java **NÃO permite herança múltipla** (apenas uma superclasse direta)
- Todas as classes herdam de `Object`

```java
public class Vehicle { /* atributos e métodos comuns */ }
public class RefrigeratedVehicle extends Vehicle { /* especializações */ }
```

### 6.2 `super`
- Chama o construtor da superclasse (deve ser a **primeira linha** do construtor)
- Também permite chamar métodos da superclasse: `super.metodo()`

```java
public class RefrigeratedVehicle extends Vehicle {
    private int maxKmWithLoad;

    public RefrigeratedVehicle(String id, int capacity, int maxKm) {
        super(id, capacity);  // Chama construtor de Vehicle
        this.maxKmWithLoad = maxKm;
    }
}
```

### 6.3 Casting de Objetos e `instanceof`
```java
Object obj = new RefrigeratedVehicle(...);
if (obj instanceof RefrigeratedVehicle) {
    RefrigeratedVehicle rv = (RefrigeratedVehicle) obj;
}
```

### 6.4 Relações entre Classes

| Relação         | Descrição                                                       | Ciclo de vida     |
|-----------------|-----------------------------------------------------------------|-------------------|
| **Associação**  | Objetos têm ciclo de vida independente, sem propriedade          | Independente      |
| **Agregação**   | Ciclo de vida independente, mas há propriedade                  | Independente      |
| **Composição**  | Ciclo de vida dependente — se o pai morre, filhos morrem também | Dependente        |
| **Herança**     | "é um tipo de" — especialização/generalização                   | —                 |

> **Exemplo TP:** Uma `AidBox` é **composta** por `Container`s (composição) — se a AidBox for eliminada, os contentores também. Um `Vehicle` é **associado** a uma `Route` (associação).

### 6.5 Generalização vs Especialização
- **Generalização**: combinar classes semelhantes numa superclasse mais geral
- **Especialização**: criar subclasses com atributos/comportamentos mais específicos

---

## 7. Classes Abstratas e Final

### 7.1 Classes Abstratas
- Têm pelo menos **um método abstrato** (sem implementação)
- **NÃO podem ser instanciadas** (`new AbstractClass()` → ERRO)
- As subclasses são **obrigadas** a implementar os métodos abstratos
- Podem ter métodos concretos (com implementação)

```java
abstract class Vehicle {
    private String id;
    private int maxCapacity;

    abstract double calculateLoadCapacity();  // Sem implementação

    // Método concreto — as subclasses herdam
    public String getId() { return id; }
}

class RefrigeratedVehicle extends Vehicle {
    @Override
    double calculateLoadCapacity() {
        // Implementação obrigatória
        return maxCapacity * 0.8;
    }
}
```

### 7.2 `final`
- **`final` em variável** → constante (não pode ser reatribuída)
- **`final` em método** → não pode ser sobreposto (override) nas subclasses
- **`final` em classe** → não pode ser herdada (sem subclasses)

```java
final class Constants { /* Não pode ter subclasses */ }

class A {
    final void critical() { /* Não pode ser @Override nas subclasses */ }
}
```

---

## 8. Interfaces

### 8.1 Conceito
- Uma interface é um **contrato** — define O QUE fazer, mas não COMO
- Próxima de uma classe **100% abstrata**
- Resolve o problema da **herança múltipla** em Java
- Todos os métodos são `public abstract` por defeito
- Uma classe pode implementar **múltiplas interfaces**

```java
public interface Measurable {
    double getMeasurement();
    boolean isOverCapacity();
}

public class Container implements Measurable {
    @Override
    public double getMeasurement() { /* implementação */ }

    @Override
    public boolean isOverCapacity() { /* implementação */ }
}
```

### 8.2 Interface vs Classe Abstrata

| Característica              | Interface                   | Classe Abstrata             |
|-----------------------------|-----------------------------|-----------------------------|
| Métodos                     | Todos abstratos*            | Concretos e abstratos       |
| Herança múltipla            | ✅ Sim                      | ❌ Não                      |
| Construtores                | ❌ Não                      | ✅ Sim                      |
| Variáveis de instância      | ❌ (só constantes)          | ✅ Sim                      |
| Palavra-chave               | `implements`                | `extends`                   |

\* A partir do Java 8, interfaces podem ter métodos `default` com implementação.

### 8.3 Quando Usar
- **Interface**: quando classes de hierarquias diferentes precisam de um comportamento comum
- **Classe Abstrata**: quando há atributos e métodos concretos a partilhar entre subclasses

---

## 9. Pilares da POO

### 9.1 Abstração
Ignorar características irrelevantes e enfatizar as relevantes para o problema.

### 9.2 Encapsulamento
Dados privados, acesso controlado via métodos públicos (getters/setters).

### 9.3 Herança
Personalizar uma classe para um propósito específico sem modificar a original.

### 9.4 Polimorfismo ("muitas formas")
| Tipo                            | Descrição                                    |
|---------------------------------|----------------------------------------------|
| **Overloading** (Sobrecarga)    | Mesmo nome, assinaturas diferentes           |
| **Overriding** (Sobreposição)   | Redefinir método herdado na subclasse         |
| **Paramétrico**                 | Método genérico que trabalha com vários tipos |

```java
// Overriding — o sistema chama o método correto
Vehicle[] vehicles = new Vehicle[2];
vehicles[0] = new RefrigeratedVehicle(...);
vehicles[1] = new StandardVehicle(...);

for (Vehicle v : vehicles) {
    // Chama o método da subclasse correta (polimorfismo)
    System.out.println(v.calculateLoadCapacity());
}
```

---

## 10. Exceções

### 10.1 Conceito
- Uma exceção é um evento que interrompe o fluxo normal de execução
- Representadas por **classes** que herdam de `Exception`
- Três tipos: **Checked Exception**, **Error**, **Runtime Exception**

### 10.2 Try-Catch-Finally
```java
try {
    // Código que pode gerar exceção
    int result = array[10]; // ArrayIndexOutOfBoundsException
} catch (ArrayIndexOutOfBoundsException e) {
    // Tratar a exceção
    System.out.println("Índice fora dos limites!");
} catch (ArithmeticException e) {
    System.out.println("Erro aritmético!");
} finally {
    // SEMPRE executado (com ou sem exceção)
    System.out.println("Bloco finally");
}
```

### 10.3 Criar Exceções Próprias
```java
public class ContainerFullException extends Exception {
    public ContainerFullException() {
        super();
    }
    public ContainerFullException(String message) {
        super(message);
    }
}
```

### 10.4 Lançar Exceções (`throw` / `throws`)
```java
// Na assinatura do método: declarar que pode lançar
public void addItem(double weight) throws ContainerFullException {
    if (currentLoad + weight > capacity) {
        throw new ContainerFullException("Contentor cheio!");
    }
    currentLoad += weight;
}

// Quem chama o método deve tratar:
try {
    container.addItem(50.0);
} catch (ContainerFullException e) {
    System.out.println(e.getMessage());
}
```

---

## 11. Input/Output e Serialização

### 11.1 Streams
- **Streams de Bytes**: `InputStream`, `OutputStream`, `FileInputStream`, `FileOutputStream`
- **Streams de Caracteres**: `Reader`, `Writer`, `FileReader`, `FileWriter`, `BufferedReader`

### 11.2 Ler da Consola
```java
BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
String line = br.readLine();
```

### 11.3 Ler/Escrever Ficheiros
```java
// Ler ficheiro
FileInputStream fin = new FileInputStream("dados.bin");
int b;
while ((b = fin.read()) != -1) { System.out.print((char) b); }
fin.close();

// Escrever ficheiro
FileWriter fw = new FileWriter("output.txt");
fw.write("Hello World\r\n");
fw.close();

// Ler com BufferedReader (linha a linha)
BufferedReader br = new BufferedReader(new FileReader("test.txt"));
String s;
while ((s = br.readLine()) != null) { System.out.println(s); }
br.close();
```

### 11.4 Dados Binários
```java
// Escrever dados binários
DataOutputStream out = new DataOutputStream(new FileOutputStream("data.bin"));
out.writeInt(42);
out.writeDouble(3.14);
out.writeBoolean(true);
out.close();

// Ler dados binários
DataInputStream in = new DataInputStream(new FileInputStream("data.bin"));
int i = in.readInt();
double d = in.readDouble();
boolean b = in.readBoolean();
in.close();
```

### 11.5 Serialização
- Permite gravar objetos diretamente em ficheiro
- A classe deve implementar `java.io.Serializable`

```java
// Escrever objeto
ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("obj.dat"));
oos.writeObject(myObject);
oos.close();

// Ler objeto
ObjectInputStream ois = new ObjectInputStream(new FileInputStream("obj.dat"));
MyClass obj = (MyClass) ois.readObject();
ois.close();
```

### 11.6 Wrapper Types (Conversão de Strings)
| Wrapper   | Método                         |
|-----------|--------------------------------|
| `Integer` | `Integer.parseInt(str)`        |
| `Double`  | `Double.parseDouble(str)`      |
| `Float`   | `Float.parseFloat(str)`        |
| `Long`    | `Long.parseLong(str)`          |
| `Boolean` | `Boolean.parseBoolean(str)`    |

```java
String s = "42";
int n = Integer.parseInt(s); // Pode lançar NumberFormatException
```

---

## 12. Contexto do Trabalho Prático

### Domínio: Sistema de Recolha de Bens Humanitários (Real)

O trabalho prático deste ano (25/26) é definido pelas interfaces no `resources.jar` e envolve:

- **AidBox** (Caixa de Suprimentos): composta por vários `Container`, implementado em `AidBoxImpl`
- **Container**: de um determinado `ItemType` (`PERISHABLE_FOOD`, `NON_PERISHABLE_FOOD`, `CLOTHING`, `MEDICINE`), implementado em `ContainerImpl`
- **Measurement**: leituras dos sensores de cada contentor (peso), implementado em `MeasurementImpl`
- **Vehicle**: veículos de recolha com carga máxima e tipo de item, especializado por `NormalVehicleImpl`
- **RefrigeratedVehicles**: especialização de `Vehicle` para alimentos perecíveis (km máximo com carga), em `RefrigeratedVehiclesImpl`
- **Route**: rota entre AidBoxes, implementado em `RouteImpl`
- **Collection**: registo de cada recolha (veículo + rota)
- **Institution**: gere as AidBoxes, veículos e mapas de recolha, implementado em `InstitutionImpl`
- **PickingMap**: mapa de recolha num dado instante contendo as rotas geradas, em `PickingMapImpl`
- **Strategy**: algoritmo de geração de rotas (heurística), em `StrategyImpl`
- **RouteValidator**: valida regras de negócio das rotas, em `RouteValidatorImpl`
- **RouteGenerator**: coordena a geração e gera relatórios, em `RouteGeneratorImpl`
- **Importer**: importa dados a partir de ficheiros JSON, em `ImporterImpl`

### Conceitos POO aplicados ao TP:

| Conceito            | Exemplo no TP                                                   |
|---------------------|----------------------------------------------------------------|
| Herança             | `RefrigeratedVehiclesImpl extends VehicleImpl`                 |
| Classes Abstratas   | `VehicleImpl` é abstrata e implementa `Vehicle`                |
| Interfaces          | Contratos no `resources.jar` (ex: `Institution`, `AidBox`)     |
| Enums               | `ItemType` (apenas `ItemType` é utilizado no projeto real)     |
| Composição          | `AidBoxImpl` contém `Container[]`                              |
| Encapsulamento      | Todos os atributos `private` + getters/setters                 |
| Exceções            | Customizadas: `AidBoxException`, `ContainerException`, etc.    |
| I/O e JSON          | Leitura de `AidBoxes.json` e `Distances.json` com `json-simple` |
| Clone / Deep Copy   | `clone()` implementado em `ContainerImpl` e `AidBoxImpl`       |

### Restrições do TP:
- ❌ **NÃO** usar Java Collections Framework (ArrayList, HashMap, etc.)
- ❌ **NÃO** usar APIs não lecionadas sem autorização
- ✅ Usar arrays geridos manualmente (redimensionamento manual com `System.arraycopy`)
- ✅ Usar json-simple para parsing de JSON
- ✅ Documentação JavaDoc obrigatória
- ✅ JDK 25 obrigatório devido ao `resources.jar` (major version 69)

---

## 🧠 Dicas Finais para o Exame

1. **Sempre encapsular**: atributos `private`, métodos de acesso `public`
2. **Construtores**: usar `this` para variáveis de instância, `super()` na primeira linha
3. **Override vs Overload**: Override = mesma assinatura na subclasse; Overload = mesmo nome, parâmetros diferentes
4. **Interfaces**: usadas para definir contratos, permitem "herança múltipla"
5. **abstract**: classe não instanciável, método sem corpo → obrigatório nas subclasses
6. **final**: impede herança (classe), override (método) ou reatribuição (variável)
7. **Exceções**: `try-catch-finally`, `throw` para lançar, `throws` na assinatura
8. **Coleções manuais**: como não podem usar Collections, praticar gestão de arrays (adicionar, remover, listar)
9. **instanceof**: usar para verificar tipo antes de cast
10. **Ler bem o enunciado** e mapear para os conceitos: herança, polimorfismo, interfaces, exceções
