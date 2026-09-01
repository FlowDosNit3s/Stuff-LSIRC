# 🔑 SOLUÇÕES TEÓRICAS MODELO (20 VALORES) — DIAS 1 E 2

> **Instruções:** Respostas diretas, concisas ("sem palha"), com os conceitos essenciais em tópicos e excerto de código em cada questão.

---

## 📌 RESPOSTAS MODELO DO DIA 1 (FUNDAMENTOS & CORE POO)

### 📌 Resposta Pergunta 1 (1,5v) — Encapsulamento & Modificadores
- **Conceito:** Ocultar o estado interno de um objeto e controlar o seu acesso através de métodos.
- **4 Modificadores (do mais restritivo ao mais permissivo):**
  1. `private`: Própria classe.
  2. *(default / package-private)*: Mesmas classes do pacote.
  3. `protected`: Mesmo pacote e subclasses noutros pacotes.
  4. `public`: Qualquer classe em qualquer pacote.
- **Atributos `public`:** Violam o encapsulamento, permitindo alterações externas sem validação e criando forte acoplamento.

```java
public class ContainerImpl {
    private double capacity; // Encapsulado
    public void setCapacity(double capacity) {
        if (capacity > 0) this.capacity = capacity; // Validação
    }
}
```

---

### 📌 Resposta Pergunta 2 (1,5v) — Classes Abstratas vs Interfaces
- **Diferenças:** Classes abstratas (`extends`) suportam apenas herança simples, contêm atributos mutáveis e construtores. Interfaces (`implements`) suportam herança múltipla, não têm construtores e apenas possuem constantes `public static final`.
- **Escolha:** Usar **Classe Abstrata** na relação "É UM" com código/estado partilhado; usar **Interface** para contratos de comportamento "É CAPAZ DE" entre classes não relacionadas.

```java
public interface RefrigeratedVehicle { double getMaxKilometers(); }

public abstract class Vehicle {
    private String code;
    public Vehicle(String code) { this.code = code; }
    public abstract double getMaxCapacity();
}
```

---

### 📌 Resposta Pergunta 3 (1,5v) — Passagem de Argumentos em Java
- Em Java a passagem é **exclusivamente por valor**.
- **Primitivos:** O valor é copiado. Alterações locais não afetam a variável original.
- **Objetos:** O valor do endereço de memória (referência) é copiado. Reatribuir a variável no método não afeta o chamador. Contudo, chamar métodos modificadores altera o objeto na Heap.

```java
public static void testar(int x, Container c) {
    x = 99;                 // Não afeta a variável original
    c.setCapacity(500.0);   // Altera o estado do objeto na Heap!
}
```

---

### 📌 Resposta Pergunta 4 (1,5v) — Identidade vs Igualdade
- **Identidade (`==`):** Compara se apontam para o mesmo endereço de memória Heap.
- **Igualdade (`equals()`):** Compara o conteúdo lógico. Redefinir `equals()` exige redefinir `hashCode()` e `toString()`.

```java
public class VehicleImpl implements Vehicle {
    private String code;

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || !(obj instanceof Vehicle)) return false;
        Vehicle other = (Vehicle) obj;
        return this.code != null && this.code.equals(other.getCode());
    }

    @Override
    public String toString() { return "VehicleImpl{code='" + code + "'}"; }
}
```

---

### 📌 Resposta Pergunta 5 (1,5v) — Membros `static`
- Atributos e métodos `static` pertencem à classe e não a instâncias.
- **Restrições:** Métodos `static` não podem aceder diretamente a membros de instância nem usar `this` ou `super` (executam sem contexto de instância na Heap).

```java
public class MathUtils {
    public static int contador = 0;
    public static void incrementar() { contador++; } // OK
}
```

---

### 📌 Resposta Pergunta 6 (1,5v) — O Modificador `final`
- **Variável:** Torna o valor constante. Em objetos, a referência é imutável, mas o estado interno do objeto pode ser alterado.
- **Método:** Impede a sobreposição (*overriding*) em subclasses.
- **Classe:** Impede a herança/criação de subclasses.

```java
final int MAX = 10;
final Vehicle v = new VehicleImpl("V01");
v.setCapacity(500.0); // Permitido alterar estado interno!
// v = new VehicleImpl("V02"); // ERRO de compilação!
```

---

### 📌 Resposta Pergunta 7 (1,5v) — Contrato `equals()` e `hashCode()`
- O contrato exige: se `a.equals(b)` for `true`, `a.hashCode()` DEVE ser igual a `b.hashCode()`.
- Se não for redefinido, coleções baseadas em *hash tables* (`HashSet`, `HashMap`) falham a pesquisa de objetos logicamente iguais.

```java
@Override
public int hashCode() {
    return (code == null) ? 0 : code.hashCode();
}
```

---

### 📌 Resposta Pergunta 8 (1,5v) — Inicialização de Variáveis
- **Atributos de Instância:** Inicializados automaticamente com valores por defeito (`0`, `0.0`, `false`, `null`).
- **Variáveis Locais:** **Não são inicializadas por defeito**. Causam erro de compilação se lidas sem inicialização prévia.

```java
public void exemplo() {
    int x;
    // System.out.println(x); // ERRO: variável não inicializada!
}
```

---

### 📌 Resposta Pergunta 9 (1,5v) — Construtores em Java
- Bloco especial sem tipo de retorno que inicializa o estado de um novo objeto com `new`.
- Se a classe não declarar nenhum construtor, o compilador gera um **construtor público por defeito sem argumentos**.

```java
public class AidBoxImpl {
    public AidBoxImpl() {} // Construtor sem argumentos
}
```

---

### 📌 Resposta Pergunta 10 (1,5v) — O Operador `this`
- Referência implícita para a própria instância em execução na Heap.
- **Usos:** (1) Diferenciar atributos de parâmetros (`this.code = code`), (2) Chamar outro construtor (`this(...)`), (3) Passar a própria instância.

```java
public AidBoxImpl(String code) {
    this.code = code; // Diferenciação de escopo
}
```

---

## 📌 RESPOSTAS MODELO DO DIA 2 (POLIMORFISMO, EXCEÇÕES & I/O)

### 📌 Resposta Pergunta 1 (1,5v) — Checked vs Unchecked Exceptions
- **Checked (subclasses de `Exception` sem `RuntimeException`):** Erros de ambiente previsíveis (ex: `IOException`). Compilador **obriga** a tratar com `try-catch` ou declarar com `throws`.
- **Unchecked (subclasses de `RuntimeException`):** Erros de lógica/bugs (ex: `NullPointerException`). Compilador não exige tratamento.

```java
public void lerFicheiro() throws IOException { // Checked Exception declarada
    FileReader fr = new FileReader("teste.txt");
}
```

---

### 📌 Resposta Pergunta 2 (1,5v) — Fluxo `try-catch-finally`
- O `try` executa o código, o `catch` captura exceções.
- O bloco `finally` executa **sempre** (mesmo que haja uma instrução `return` no `try` ou `catch`).

```java
public int testarFinally() {
    try { return 1; } 
    finally { System.out.println("Finally executado!"); } // Executa antes de retornar!
}
```

---

### 📌 Resposta Pergunta 3 (1,5v) — Overloading vs Overriding
- **Overloading (Sobrecarga):** Mesma classe, nomes iguais, parâmetros diferentes. Decisão em **tempo de compilação** (*static binding*).
- **Overriding (Sobreposição):** Subclasse redefine método herdado com a mesma assinatura. Decisão em **tempo de execução** (*dynamic binding*) pela JVM.

```java
class A { void somar(int x) {} }
class B extends A { @Override void somar(int x) {} } // Overriding
```

---

### 📌 Resposta Pergunta 4 (1,5v) — Upcasting vs Downcasting
- **Upcasting:** Conversão para superclasse (automática e segura).
- **Downcasting:** Conversão para subclasse (explícita). Risco de `ClassCastException`. Usar `instanceof`.

```java
Vehicle v = new RefrigeratedVehicleImpl("V1", ItemType.PERISHABLE_FOOD, 500, 100);
if (v instanceof RefrigeratedVehicle) {
    RefrigeratedVehicle rv = (RefrigeratedVehicle) v; // Downcasting seguro
}
```

---

### 📌 Resposta Pergunta 5 (1,5v) — Composição vs Herança
- Herança ("É UM") cria acoplamento forte (problema da superclasse frágil).
- Composição ("TEM UM") preserva o encapsulamento, reduz o acoplamento e permite alterar comportamentos em tempo de execução.

```java
public class InstitutionImpl {
    private AidBox[] aidBoxes; // Composição ("TEM UM")
}
```

---

### 📌 Resposta Pergunta 6 (1,5v) — Estrutura de Enums
- Tipos especiais cujas constantes são instâncias únicas e imutáveis.
- Podem ter atributos, métodos e construtores. O construtor é **obrigatoriamente privado (`private`)**.

```java
public enum ItemType {
    FOOD("Alimentos");
    private final String desc;
    private ItemType(String desc) { this.desc = desc; } // Construtor privado
}
```

---

### 📌 Resposta Pergunta 7 (1,5v) — Wrappers e Autoboxing
- Wrappers envolvem primitivos em objetos (`int` -> `Integer`). Autoboxing converte automaticamente.
- **Risco:** `==` compara endereços de memória na Heap. Falha fora da cache Integer (-128 a 127). Usar sempre `.equals()`.

```java
Integer a = 200, b = 200;
System.out.println(a.equals(b)); // true! (nunca usar a == b)
```

---

### 📌 Resposta Pergunta 8 (1,5v) — Byte Streams vs Character Streams
- **Byte Streams (`InputStream`/`OutputStream`):** Manipulam bytes (8 bits). Dados binários (imagens, áudio, zip).
- **Character Streams (`Reader`/`Writer`):** Manipulam caracteres Unicode (16 bits). Ficheiros de texto legível (.txt, .json).

```java
FileReader reader = new FileReader("dados.json"); // Character Stream
```

---

### 📌 Resposta Pergunta 9 (1,5v) — Serialização
- Converte o estado de um objeto em bytes. Exige `java.io.Serializable`.
- `serialVersionUID` valida a compatibilidade de versões da classe. `transient` omite atributos da serialização.

```java
public class User implements Serializable {
    private static final long serialVersionUID = 1L;
    private transient String password; // Não serializado!
}
```

---

### 📌 Resposta Pergunta 10 (1,5v) — Desenho de APIs e Exceções
- Checked Exceptions forçam a documentação de falhas externas onde o chamador pode recuperar.
- Unchecked Exceptions sinalizam bugs do programador que devem ser corrigidos no código e não capturados.

```java
public void sacar(double valor) throws InsufficientBalanceException { // Checked
    if (valor <= 0) throw new IllegalArgumentException("Valor <= 0"); // Unchecked
}
```
