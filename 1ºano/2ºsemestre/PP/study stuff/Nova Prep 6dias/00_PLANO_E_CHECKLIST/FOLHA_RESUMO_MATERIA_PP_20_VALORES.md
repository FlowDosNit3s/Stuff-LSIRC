# 📜 FOLHA DE RESUMO COMPLETA DA MATÉRIA — PARADIGMAS DE PROGRAMAÇÃO (PP)

> **Documento Mestre de Consulta Rápida:** Síntese de 100% do programa teórico da unidade curricular de Paradigmas de Programação (PP) da ESTG/P.PORTO, cobrindo todos os conceitos dos Slides (APP01 a APP12) com resumos diretos e excertos de código Java.

---

## 📋 ÍNDICE DA MATÉRIA

1. [Conceitos de POO, Classe, Objeto e Instanciação](#1-conceitos-de-poo-classe-objeto-e-instanciação)
2. [Encapsulamento & Modificadores de Acesso](#2-encapsulamento--modificadores-de-acesso)
3. [Classes Abstratas vs Interfaces](#3-classes-abstratas-vs-interfaces)
4. [Membros Estáticos (`static`)](#4-membros-estáticos-static)
5. [O Modificador `final`](#5-o-modificador-final)
6. [Identidade vs Igualdade (`==` vs `equals()`)](#6-identidade-vs-igualdade--vs-equals)
7. [Passagem de Argumentos em Java (Pass-by-Value)](#7-passagem-de-argumentos-em-java-pass-by-value)
8. [Herança, Especialização e a Palavra-chave `super`](#8-herança-especialização-e-a-palavra-chave-super)
9. [Polimorfismo: Sobrecarga (*Overloading*) vs Sobreposição (*Overriding*)](#9-polimorfismo-sobrecarga-overloading-vs-sobreposição-overriding)
10. [Conversão de Tipos (*Casting*) e `instanceof`](#10-conversão-de-tipos-casting-e-instanceof)
11. [Composição vs Herança](#11-composição-vs-herança)
12. [Tratamento de Exceções (`try-catch-finally`, Checked vs Unchecked)](#12-tratamento-de-exceções-try-catch-finally-checked-vs-unchecked)
13. [Tipos Enumerados (`enum`)](#13-tipos-enumerados-enum)
14. [Classes Wrapper, Autoboxing e Unboxing](#14-classes-wrapper-autoboxing-e-unboxing)
15. [Input/Output (I/O) em Java: Byte Streams vs Character Streams](#15-inputoutput-io-em-java-byte-streams-vs-character-streams)
16. [Serialização de Objetos (`Serializable`, `transient`, `serialVersionUID`)](#16-serialização-de-objetos-serializable-transient-serialversionuid)
17. [Algoritmia de Arrays Nativos sem Collections Framework](#17-algoritmia-de-arrays-nativos-sem-collections-framework)

---

## 1. Conceitos de POO, Classe, Objeto e Instanciação
- **Paradigma Orientado a Objetos (POO):** Modelo de programação baseado na abstração do mundo real em entidades autónomas contendo estado (atributos) e comportamento (métodos).
- **Classe:** Molde/modelo abstrato que define a estrutura de dados e o comportamento de um tipo de objeto.
- **Objeto / Instância:** Entidade concreta alocada na memória Heap criada a partir de uma classe utilizando o operador `new`.

```java
AidBox box = new AidBoxImpl("AB01", "Norte"); // Instanciação na Heap
```

---

## 2. Encapsulamento & Modificadores de Acesso
- **Encapsulamento:** Ocultar a estrutura interna de dados de uma classe e controlar o seu acesso através de métodos públicos (`getters` e `setters` com validação).

### Tabela de Visibilidade dos Modificadores de Acesso:

| Modificador | Própria Classe | Mesmo Pacote | Subclasses (Outros Pacotes) | Qualquer Pacote (`public`) |
| :--- | :---: | :---: | :---: | :---: |
| `private` | **SIM** | NÃO | NÃO | NÃO |
| *(default)* | **SIM** | **SIM** | NÃO | NÃO |
| `protected` | **SIM** | **SIM** | **SIM** | NÃO |
| `public` | **SIM** | **SIM** | **SIM** | **SIM** |

```java
public class ContainerImpl {
    private double capacity; // Apenas acessível na classe

    public void setCapacity(double capacity) {
        if (capacity > 0) this.capacity = capacity; // Proteção do estado
    }
}
```

---

## 3. Classes Abstratas vs Interfaces

| Característica | Classe Abstrata | Interface |
| :--- | :--- | :--- |
| **Mecanismo de Herança** | `extends` (apenas 1 classe). | `implements` (múltiplas interfaces). |
| **Atributos de Instância** | Atributos mutáveis, `private`, `protected`, etc. | Apenas constantes `public static final`. |
| **Construtores** | Possui construtores (chamados via `super()`). | NÃO possui construtores. |
| **Métodos** | Métodos abstratos e métodos concretos. | Métodos abstratos, `default` e `static`. |
| **Semântica Lógica** | Relação conceptual "É UM" (*IS-A*). | Contrato de comportamento "É CAPAZ DE". |

```java
public interface RefrigeratedVehicle { double getMaxKilometers(); }

public abstract class Vehicle {
    private String code;
    public Vehicle(String code) { this.code = code; }
    public abstract double getMaxCapacity();
}
```

---

## 4. Membros Estáticos (`static`)
- Pertencem à **classe** e não às suas instâncias. Existe apenas **uma cópia** na memória partilhada por todas as instâncias.
- **Restrições:** Métodos estáticos **não podem aceder diretamente a atributos de instância** nem utilizar as palavras-chave `this` ou `super`.

```java
public class Config {
    public static int contador = 0; // Atributo de classe
    public static void incrementar() { contador++; } // Método estático
}
```

---

## 5. O Modificador `final`
- **Variável `final`:** Transforma a variável numa constante após a primeira atribuição.
  - *Primitivo:* Valor numérico inalterável (`final int X = 10;`).
  - *Objeto:* A referência é inalterável (não pode apontar para outro objeto), mas o estado interno do objeto pode ser modificado (`final Container c = new ContainerImpl(); c.setCapacity(500.0);`).
- **Método `final`:** Impede que o método seja sobreposto (*overridden*) nas subclasses.
- **Classe `final`:** Impede a criação de subclasses (não pode ser estendida, ex: `String`).

---

## 6. Identidade vs Igualdade (`==` vs `equals()`)
- **Operador `==` (Identidade Física):** Compara se duas variáveis apontam para o mesmo endereço de memória na Heap.
- **Método `equals()` (Igualdade Lógica):** Compara a igualdade de conteúdo.
- **Contrato com `hashCode()`:** Se `a.equals(b)` for `true`, então `a.hashCode()` DEVE devolver obrigatoriamente o mesmo valor inteiro.

```java
@Override
public boolean equals(Object obj) {
    if (this == obj) return true;
    if (obj == null || !(obj instanceof AidBox)) return false;
    AidBox other = (AidBox) obj;
    return this.code != null && this.code.equals(other.getCode());
}

@Override
public String toString() {
    return "AidBoxImpl{code='" + code + "'}";
}
```

---

## 7. Passagem de Argumentos em Java (Pass-by-Value)
- Em Java a passagem de argumentos é **exclusivamente por valor**.
- **Primitivos:** É copiado o valor primitivo. Alterações não afetam o chamador.
- **Objetos:** É copiado o valor do **endereço de memória (referência)**.
  - Reatribuir o parâmetro (`c = new ContainerImpl()`) altera apenas a cópia local.
  - Chamar métodos modificadores (`c.setCapacity(500)`) altera o objeto na Heap.

```java
public static void testar(int x, Container c) {
    x = 99;                 // Não altera a variável fora do método
    c.setCapacity(500.0);   // Altera o objeto na Heap!
}
```

---

## 8. Herança, Especialização e a Palavra-chave `super`
- Permite que uma subclasse herde atributos e métodos de uma superclasse.
- **`super`:** Utilizado no construtor da subclasse para invocar o construtor da superclasse (`super(code, capacity);`) ou para aceder a métodos sobrepostos da superclasse (`super.toString();`).

```java
public class RefrigeratedVehicleImpl extends VehicleImpl implements RefrigeratedVehicle {
    public RefrigeratedVehicleImpl(String code, double capacity) {
        super(code, capacity); // Invocação do construtor pai
    }
}
```

---

## 9. Polimorfismo: Sobrecarga (*Overloading*) vs Sobreposição (*Overriding*)

| Característica | Sobrecarga (*Overloading*) | Sobreposição (*Overriding*) |
| :--- | :--- | :--- |
| **Definição** | Métodos com mesmo nome mas parâmetros diferentes na mesma classe. | Subclasse redefine método herdado com a mesma assinatura. |
| **Momento de Decisão** | Tempo de **Compilação** (*Static Binding*). | Tempo de **Execução** (*Dynamic Binding* / JVM). |
| **Anotação** | Nenhuma. | `@Override` (obrigatória em exames!). |

```java
// Overloading (Compilação)
public int somar(int a, int b) { return a + b; }
public double somar(double a, double b) { return a + b; }

// Overriding (Execução)
@Override
public String getCode() { return this.code; }
```

---

## 10. Conversão de Tipos (*Casting*) e `instanceof`
- **Upcasting:** Conversão para superclasse (implícito, automático e seguro).
- **Downcasting:** Conversão para subclasse (explícito). Risco de `ClassCastException`.
- **`instanceof`:** Testa a compatibilidade antes do cast.

```java
Vehicle v = new RefrigeratedVehicleImpl("V01", ItemType.PERISHABLE_FOOD, 500.0, 150.0); // Upcasting

if (v instanceof RefrigeratedVehicle) {
    RefrigeratedVehicle rv = (RefrigeratedVehicle) v; // Downcasting seguro
    System.out.println(rv.getMaxKilometers());
}
```

---

## 11. Composição vs Herança
- **Herança ("É UM"):** Acoplamento forte. Alterações na superclasse podem quebrar as subclasses (superclasse frágil).
- **Composição ("TEM UM"):** Uma classe inclui instâncias de outras classes como seus atributos. Preserva o encapsulamento e reduz o acoplamento.

```java
public class InstitutionImpl implements IInstitution {
    private AidBox[] aidBoxes; // Composição ("TEM UM")
}
```

---

## 12. Tratamento de Exceções (`try-catch-finally`, Checked vs Unchecked)

```
                     java.lang.Throwable
                              |
             +----------------+----------------+
             |                                 |
       java.lang.Error               java.lang.Exception
                                               |
                             +-----------------+-----------------+
                             |                                   |
                   Checked Exceptions                 java.lang.RuntimeException
                (IOException, etc.)                     (Unchecked Exceptions)
```

- **Checked Exceptions:** Subclasses de `Exception` sem `RuntimeException`. Compilador **obriga** a tratar com `try-catch` ou declarar com `throws`.
- **Unchecked Exceptions:** Subclasses de `RuntimeException`. Erros de lógica/bugs. Compilador não exige declaração.
- **`finally`:** Executa **sempre**, mesmo se o `try` contiver uma instrução `return`.

```java
public void processar() throws RouteException { // Checked Exception declarada
    try {
        // código suscetível de erro
    } catch (IllegalArgumentException e) {
        // tratamento
    } finally {
        // executa SEMPRE para limpeza de recursos
    }
}
```

---

## 13. Tipos Enumerados (`enum`)
- Tipos cujas constantes são instâncias únicas (*singletons*) imutáveis.
- Podem declarar atributos, métodos e construtores. **Os construtores de um Enum são OBRIGATORIAMENTE PRIVADOS (`private`)**.

```java
public enum ItemType {
    PERISHABLE_FOOD("Perecível"),
    MEDICINE("Medicamentos");

    private final String description;
    private ItemType(String description) { this.description = description; }
    public String getDescription() { return description; }
}
```

---

## 14. Classes Wrapper, Autoboxing e Unboxing
- **Wrappers:** Envolvem tipos primitivos em objetos (`Integer`, `Double`).
- **Autoboxing / Unboxing:** Conversão automática realizada pelo compilador.
- **Risco de Comparação:** O operador `==` compara endereços na Heap e falha fora da cache de `Integer` (-128 a 127). Usar **SEMPRE `.equals()`**.

```java
Integer a = 200, b = 200;
System.out.println(a.equals(b)); // true! (nunca a == b)
```

---

## 15. Input/Output (I/O) em Java: Byte Streams vs Character Streams
- **Byte Streams (`InputStream` / `OutputStream`):** Manipulam bytes (8 bits). Dados binários (imagens, compactados, áudio).
- **Character Streams (`Reader` / `Writer`):** Manipulam caracteres Unicode (16 bits). Ficheiros de texto legível (.txt, .json).

```java
FileReader reader = new FileReader("dados.json"); // Character Stream
```

---

## 16. Serialização de Objetos (`Serializable`, `transient`, `serialVersionUID`)
- **Serialização:** Converte o estado de um objeto em bytes. A classe deve implementar a interface marcadora `java.io.Serializable`.
- **`serialVersionUID`:** Identificador estático de versão para validar compatibilidade na deserialização.
- **`transient`:** Sinaliza atributos que **não devem ser serializados** (ex: passwords, dados temporários).

```java
public class User implements Serializable {
    private static final long serialVersionUID = 1L;
    private transient String password; // Omitido da serialização
}
```

---

## 17. Algoritmia de Arrays Nativos sem Collections Framework

> ⚠️ **LEMBRETE FATOR DE EXAME:** Proibido utilizar `ArrayList`, `List` ou `HashMap`. Usar apenas `Tipo[]`.

### 17.1 Redimensionamento Dinâmico de Array:
```java
private void resize() {
    Tipo[] temp = new Tipo[this.array.length * 2];
    for (int i = 0; i < this.numberOfElements; i++) temp[i] = this.array[i];
    this.array = temp;
}
```

### 17.2 Compactação de Array (Remover Nulos):
```java
public Container[] getContainers() {
    int count = 0;
    for (int i = 0; i < this.numberOfContainers; i++) {
        if (this.containers[i] != null) count++;
    }

    Container[] result = new Container[count];
    int idx = 0;
    for (int i = 0; i < this.numberOfContainers; i++) {
        if (this.containers[i] != null) {
            result[idx] = this.containers[i];
            idx++;
        }
    }
    return result;
}
```

### 17.3 Ordenação Nativa (`Bubble Sort`):
```java
public void sortContainers(Container[] array) {
    if (array == null || array.length <= 1) return;
    int n = array.length;
    for (int i = 0; i < n - 1; i++) {
        for (int j = 0; j < n - i - 1; j++) {
            if (array[j] != null && array[j + 1] != null) {
                if (array[j].getCapacity() > array[j + 1].getCapacity()) {
                    Container temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                }
            }
        }
    }
}
```
