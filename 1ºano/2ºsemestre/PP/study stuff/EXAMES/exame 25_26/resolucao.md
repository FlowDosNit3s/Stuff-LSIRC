# Resolução – Exame de Paradigmas de Programação 2025/2026
**Época Normal | 15-06-2026 | Duração: 2 horas**

---

# PARTE 1 – Perguntas Teóricas

---

## Pergunta 1 (1,5 valores)
**Explique detalhadamente as diferenças entre classes abstratas e interfaces em Java. Em que situações é mais adequado optar por uma classe abstrata e em que situações é preferível uma interface? Justifique a sua resposta e ilustre cada caso com um exemplo prático que evidencie as características distintivas de cada mecanismo.**

### Resposta:

**Classes Abstratas:**
- Podem ter métodos concretos (com implementação) e métodos abstratos (sem implementação).
- Podem ter construtores.
- Podem ter atributos de instância (variáveis de estado) com qualquer modificador de acesso (`private`, `protected`, `public`).
- Uma classe só pode estender **uma** classe abstrata (herança simples).
- Podem ter métodos `static` e `final`.
- Servem para representar uma **relação "é-um"** (is-a) com partilha de código comum.

**Interfaces:**
- Antes do Java 8, só podiam ter métodos abstratos e constantes (`public static final`).
- A partir do Java 8, podem ter métodos `default` e `static` com implementação.
- Não podem ter construtores.
- Não podem ter atributos de instância (apenas constantes).
- Uma classe pode implementar **múltiplas** interfaces (herança múltipla de tipo).
- Servem para definir um **contrato** de comportamento.

**Quando usar classe abstrata:**
Quando existe código comum a partilhar entre subclasses e/ou quando é necessário manter estado (atributos) nas subclasses.

```java
// Exemplo: classe abstrata para partilhar código comum
public abstract class Animal {
    private String nome;
    
    public Animal(String nome) {
        this.nome = nome;
    }
    
    public String getNome() {
        return nome;
    }
    
    // Método abstrato – cada animal tem o seu som
    public abstract String fazerSom();
    
    // Método concreto – comum a todos
    public void dormir() {
        System.out.println(nome + " está a dormir.");
    }
}

public class Cão extends Animal {
    public Cão(String nome) {
        super(nome);
    }
    
    @Override
    public String fazerSom() {
        return "Ão Ão!";
    }
}
```

**Quando usar interface:**
Quando se pretende definir um contrato que várias classes (possivelmente sem relação hierárquica) devem cumprir, ou quando se precisa de "herança múltipla" de tipo.

```java
// Exemplo: interface como contrato
public interface Serializavel {
    String serializar();
}

public interface Imprimivel {
    void imprimir();
}

// Uma classe pode implementar múltiplas interfaces
public class Documento implements Serializavel, Imprimivel {
    private String conteudo;
    
    @Override
    public String serializar() {
        return conteudo;
    }
    
    @Override
    public void imprimir() {
        System.out.println(conteudo);
    }
}
```

---

## Pergunta 2 (1,5 valores)
**Descreva detalhadamente o modo como Java realiza a passagem de argumentos para os métodos, distinguindo o comportamento aplicado a tipos primitivos do comportamento aplicado a referências de objetos. Esclareça os equívocos frequentes associados a este mecanismo e fundamente a sua explicação com exemplos concretos que demonstrem os efeitos sobre os valores e os estados dos objetos.**

### Resposta:

Em Java, **todos os argumentos são passados por valor** (*pass-by-value*). Não existe passagem por referência em Java.

**Tipos primitivos (int, double, boolean, etc.):**
- É passada uma **cópia do valor** para o método.
- Alterações ao parâmetro dentro do método **não afetam** a variável original.

```java
public static void alterarValor(int x) {
    x = 100; // altera apenas a cópia local
}

public static void main(String[] args) {
    int a = 5;
    alterarValor(a);
    System.out.println(a); // imprime 5 – o original não foi alterado
}
```

**Referências de objetos:**
- É passada uma **cópia da referência** (o endereço de memória do objeto).
- Como ambas as referências (a original e a cópia) apontam para o **mesmo objeto**, alterações ao **estado do objeto** dentro do método **são visíveis** fora dele.
- Porém, reatribuir a referência dentro do método (fazer `param = new Objeto()`) **não afeta** a referência original.

```java
public static void alterarEstado(int[] arr) {
    arr[0] = 999; // altera o estado do objeto – visível fora do método
}

public static void reatribuirReferencia(int[] arr) {
    arr = new int[]{1, 2, 3}; // reatribui a cópia local da referência
    // a referência original continua a apontar para o array antigo
}

public static void main(String[] args) {
    int[] numeros = {10, 20, 30};
    
    alterarEstado(numeros);
    System.out.println(numeros[0]); // imprime 999 – estado alterado
    
    reatribuirReferencia(numeros);
    System.out.println(numeros[0]); // imprime 999 – referência original inalterada
}
```

**Equívoco frequente:** Muitas pessoas dizem que "objetos são passados por referência" em Java. Isto é **falso**. O que é passado é uma **cópia da referência** (o valor do "ponteiro"), não a referência em si. A prova disso é que reatribuir o parâmetro dentro do método não altera a variável original.

---

## Pergunta 3 (1,5 valores)
**Explique o conceito de conversão de tipos (casting) no contexto da herança e do polimorfismo. Discuta os riscos associados a conversões incorretas e as situações em que cada tipo de conversão é apropriado. Ilustre com um exemplo prático que demonstre as conversões.**

### Resposta:

Existem dois tipos de casting em Java:

**1. Upcasting (conversão implícita / widening):**
- Converter de uma subclasse para uma superclasse.
- É **sempre seguro** e feito automaticamente pelo compilador.
- Perde-se acesso aos métodos específicos da subclasse (mas o polimorfismo continua a funcionar).

```java
Animal animal = new Cão("Rex"); // Upcasting implícito (Cão → Animal)
animal.fazerSom(); // Polimorfismo – chama o método de Cão
// animal.abanarCauda(); // ERRO – método de Cão não acessível via referência Animal
```

**2. Downcasting (conversão explícita / narrowing):**
- Converter de uma superclasse para uma subclasse.
- Requer cast explícito e é **potencialmente perigoso**.
- Se o objeto não for realmente do tipo para o qual se está a fazer cast, lança `ClassCastException` em tempo de execução.

```java
Animal animal = new Cão("Rex");

// Downcasting seguro – o objeto é realmente um Cão
Cão cão = (Cão) animal; // OK
cão.abanarCauda(); // Funciona

// Downcasting perigoso – o objeto NÃO é um Gato
Animal animal2 = new Cão("Rex");
// Gato gato = (Gato) animal2; // ClassCastException em runtime!
```

**Boas práticas – uso de `instanceof`:**
Para evitar `ClassCastException`, deve-se verificar o tipo antes de fazer downcasting:

```java
if (animal instanceof Cão) {
    Cão cão = (Cão) animal;
    cão.abanarCauda();
}
```

**Riscos de conversões incorretas:**
- `ClassCastException` em tempo de execução (o compilador não apanha este erro).
- Código frágil e difícil de manter.
- Viola o princípio do polimorfismo – se se está a fazer muitos downcasts, provavelmente o design pode ser melhorado.

---

## Pergunta 4 (1,5 valores)
**Distinga os conceitos de identidade e de igualdade de objetos em Java, esclarecendo a diferença entre o operador `==` e o método `equals()`. Explique igualmente o papel do método `toString()`. Forneça um exemplo prático que demonstre a redefinição correta dos métodos `equals()` e `toString()` numa classe.**

### Resposta:

**Identidade (`==`):**
- Para tipos primitivos, compara os **valores**.
- Para objetos, compara as **referências** (endereços de memória) – verifica se duas variáveis apontam para o **mesmo objeto** na memória.

**Igualdade (`equals()`):**
- Método da classe `Object` que, por defeito, tem o mesmo comportamento que `==` (compara referências).
- Deve ser **redefinido** para comparar o **conteúdo/estado** dos objetos (igualdade semântica).

**`toString()`:**
- Método da classe `Object` que, por defeito, retorna o nome da classe + `@` + hashCode em hexadecimal.
- Deve ser redefinido para fornecer uma **representação textual legível** do objeto.
- É chamado automaticamente por `System.out.println()` e na concatenação de Strings.

**Exemplo prático:**

```java
public class Aluno {
    private String numero;
    private String nome;
    
    public Aluno(String numero, String nome) {
        this.numero = numero;
        this.nome = nome;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;                    // mesma referência
        if (obj == null) return false;                   // null check
        if (getClass() != obj.getClass()) return false;  // tipo diferente
        Aluno outro = (Aluno) obj;                       // downcast seguro
        return this.numero.equals(outro.numero);         // comparar por número
    }
    
    @Override
    public String toString() {
        return "Aluno{numero='" + numero + "', nome='" + nome + "'}";
    }
    
    public static void main(String[] args) {
        Aluno a1 = new Aluno("12345", "João");
        Aluno a2 = new Aluno("12345", "João Silva");
        Aluno a3 = a1;
        
        System.out.println(a1 == a2);      // false – referências diferentes
        System.out.println(a1 == a3);      // true – mesma referência
        System.out.println(a1.equals(a2)); // true – mesmo número
        System.out.println(a1);            // Aluno{numero='12345', nome='João'}
    }
}
```

---
---

# PARTE 2 – Programação em Java

## Interfaces fornecidas no enunciado:

```java
public interface Vehicle {
    String getCode();
    ItemType getSupplyType();
    double getMaxCapacity();
}

public interface RefrigeratedVehicle extends Vehicle {
    double getMaxKilometers();
    boolean equals(Object obj);
}

public interface Container {
    ItemType getType();
    double getCapacity();
    Measurement getLastMeasurement();
    ...
}

public interface Measurement {
    double getValue();
    ...
}

public interface Route {
    void addAidBox(AidBox aidBox) throws RouteException;
    AidBox removeAidBox(AidBox aidBox) throws RouteException;
    AidBox[] getRoute();
}

public interface AidBox {
    Container[] getContainers();
    ...
}

public interface Strategy {
    Route[] generate(IInstitution inst, RouteValidator validator);
}

public interface IInstitution {
    // (inferido do código fornecido)
}

public class InstitutionImpl implements IInstitution {
    private AidBox[] aidBoxes;
    private int numberOfAidBoxes;
    private Vehicle[] vehicles;
    private int numberOfVehicles;
    
    @Override
    public int getTotalContainersByType(ItemType type) { ... }
    
    public Vehicle[] getVehicles() { ... }
    
    public AidBox[] getAidBoxes() { ... }
}
```

---

## Pergunta 1a (3 valores)
**Implementar a interface `RefrigeratedVehicle` numa classe `RefrigeratedVehicleImpl`. O veículo deve possuir um estado (ENABLED, DISABLED) e deve ser inicializado como ENABLED por defeito. Para o método `equals`, duas instâncias são iguais se possuírem o mesmo código (`getCode()`).**

### Resposta:

```java
public enum VehicleState {
    ENABLED, DISABLED
}
```

```java
public class RefrigeratedVehicleImpl implements RefrigeratedVehicle {
    private String code;
    private ItemType supplyType;
    private double maxCapacity;
    private double maxKilometers;
    private VehicleState state;

    public RefrigeratedVehicleImpl(String code, ItemType supplyType, 
                                    double maxCapacity, double maxKilometers) {
        this.code = code;
        this.supplyType = supplyType;
        this.maxCapacity = maxCapacity;
        this.maxKilometers = maxKilometers;
        this.state = VehicleState.ENABLED; // estado por defeito
    }

    @Override
    public String getCode() {
        return code;
    }

    @Override
    public ItemType getSupplyType() {
        return supplyType;
    }

    @Override
    public double getMaxCapacity() {
        return maxCapacity;
    }

    @Override
    public double getMaxKilometers() {
        return maxKilometers;
    }

    public VehicleState getState() {
        return state;
    }

    public void setState(VehicleState state) {
        this.state = state;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (!(obj instanceof RefrigeratedVehicle)) return false;
        RefrigeratedVehicle other = (RefrigeratedVehicle) obj;
        return this.code.equals(other.getCode());
    }

    @Override
    public String toString() {
        return "RefrigeratedVehicleImpl{" +
                "code='" + code + '\'' +
                ", supplyType=" + supplyType +
                ", maxCapacity=" + maxCapacity +
                ", maxKilometers=" + maxKilometers +
                ", state=" + state +
                '}';
    }
}
```

---

## Pergunta 1b (2 valores)
**Desenvolva o código necessário para testar a classe implementada (por exemplo, no contexto de um método `main`). Apresente um exemplo de teste para cada método implementado.**

### Resposta:

```java
public class TestRefrigeratedVehicle {

    public static void main(String[] args) {
        
        // Criar instâncias
        RefrigeratedVehicleImpl v1 = new RefrigeratedVehicleImpl(
            "VH001", ItemType.PERISHABLE_FOOD, 500.0, 200.0
        );
        RefrigeratedVehicleImpl v2 = new RefrigeratedVehicleImpl(
            "VH001", ItemType.PERISHABLE_FOOD, 600.0, 300.0
        );
        RefrigeratedVehicleImpl v3 = new RefrigeratedVehicleImpl(
            "VH002", ItemType.MEDICINE, 400.0, 150.0
        );

        // Testar getCode()
        System.out.println("=== Teste getCode() ===");
        System.out.println("Código de v1: " + v1.getCode()); // VH001
        
        // Testar getSupplyType()
        System.out.println("\n=== Teste getSupplyType() ===");
        System.out.println("Tipo de v1: " + v1.getSupplyType()); // PERISHABLE_FOOD
        
        // Testar getMaxCapacity()
        System.out.println("\n=== Teste getMaxCapacity() ===");
        System.out.println("Capacidade máxima de v1: " + v1.getMaxCapacity()); // 500.0
        
        // Testar getMaxKilometers()
        System.out.println("\n=== Teste getMaxKilometers() ===");
        System.out.println("Km máximos de v1: " + v1.getMaxKilometers()); // 200.0
        
        // Testar estado por defeito (ENABLED)
        System.out.println("\n=== Teste estado por defeito ===");
        System.out.println("Estado de v1: " + v1.getState()); // ENABLED
        
        // Testar setState()
        System.out.println("\n=== Teste setState() ===");
        v1.setState(VehicleState.DISABLED);
        System.out.println("Estado de v1 após desativar: " + v1.getState()); // DISABLED
        v1.setState(VehicleState.ENABLED);
        
        // Testar equals() – mesmo código
        System.out.println("\n=== Teste equals() ===");
        System.out.println("v1.equals(v2): " + v1.equals(v2)); // true (mesmo código VH001)
        System.out.println("v1.equals(v3): " + v1.equals(v3)); // false (código diferente)
        System.out.println("v1.equals(null): " + v1.equals(null)); // false
        System.out.println("v1.equals(v1): " + v1.equals(v1)); // true (mesma referência)
        
        // Testar toString()
        System.out.println("\n=== Teste toString() ===");
        System.out.println(v1);
    }
}
```

---

## Pergunta 2a (4 valores)
**Implementar os métodos auxiliares para a geração da rota na classe `StrategyImpl`.**

### Método `hasCollectableContainer`:
> Deve devolver `true` caso exista a ocorrência de, pelo menos, um container cujo tipo seja igual ao do veículo e se a sua última medição registada tiver um valor superior a 80% da sua capacidade.

### Método `addAidBoxToRoute`:
> Deve devolver `true` caso a AidBox seja adicionada à rota. Para adicionar, deve validar previamente com `validator.validate(Route route, AidBox aidBox)`. Se validado, adicionar a AidBox à rota usando `addAidBox(AidBox aidBox)` da classe Route. Caso a invocação do método `addAidBox` origine uma `RouteException`, o método `addAidBoxToRoute` deve retornar `false`.

### Resposta:

```java
public class StrategyImpl implements Strategy {

    /**
     * Verifica se a AidBox tem pelo menos um container do mesmo tipo que o veículo
     * cuja última medição é superior a 80% da sua capacidade.
     */
    private boolean hasCollectableContainer(Vehicle vehicle, AidBox aidbox) {
        Container[] containers = aidbox.getContainers();
        
        for (int i = 0; i < containers.length; i++) {
            if (containers[i].getType() == vehicle.getSupplyType()) {
                Measurement lastMeasurement = containers[i].getLastMeasurement();
                if (lastMeasurement != null) {
                    double percentagem = lastMeasurement.getValue();
                    double capacidade = containers[i].getCapacity();
                    if (percentagem > capacidade * 0.8) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    /**
     * Tenta adicionar uma AidBox à rota após validação.
     * Retorna true se adicionada com sucesso, false caso contrário.
     */
    private boolean addAidBoxToRoute(Route route, AidBox aidbox, RouteValidator validator) {
        // Primeiro validar com o validador
        if (validator.validate(route, aidbox)) {
            try {
                route.addAidBox(aidbox);
                return true;
            } catch (RouteException e) {
                return false;
            }
        }
        return false;
    }

    // ... (método generate implementado na pergunta 2b)
}
```

---

## Pergunta 2b (5 valores)
**Na classe `StrategyImpl`, implementar o método `generate`, gerando as rotas necessárias.**

**Regras a considerar:**
- Para cada veículo devolvido pelo método `getVehicles()` da interface `Institution`, deve ser criada uma nova rota. Assumir que só existe um veículo para cada tipo.
- As AidBoxes existentes são as devolvidas pelo método `getAidBoxes()` da interface `Institution`.
- Deve utilizar os métodos desenvolvidos na alínea anterior.
- O array devolvido pelo método `generate` deve conter rotas com as AidBoxes (sem posições nulas ou rotas vazias).

### Resposta:

```java
    @Override
    public Route[] generate(IInstitution inst, RouteValidator validator) {
        Vehicle[] vehicles = inst.getVehicles();
        AidBox[] aidBoxes = inst.getAidBoxes();
        
        // Array temporário para guardar as rotas (máximo = nº de veículos)
        Route[] tempRoutes = new Route[vehicles.length];
        int routeCount = 0;

        // Para cada veículo, criar uma rota
        for (int i = 0; i < vehicles.length; i++) {
            if (vehicles[i] == null) continue;
            
            // Criar uma nova rota para este veículo
            Route route = new RouteImpl(vehicles[i]); // Pressuposto: existe um construtor que associa a rota ao veículo
            boolean routeHasAidBoxes = false;

            // Percorrer todas as AidBoxes
            for (int j = 0; j < aidBoxes.length; j++) {
                if (aidBoxes[j] == null) continue;
                
                // Verificar se a AidBox tem containers recolhíveis para este veículo
                if (hasCollectableContainer(vehicles[i], aidBoxes[j])) {
                    // Tentar adicionar a AidBox à rota (com validação)
                    if (addAidBoxToRoute(route, aidBoxes[j], validator)) {
                        routeHasAidBoxes = true;
                    }
                }
            }

            // Só adicionar a rota ao array se tiver pelo menos uma AidBox
            if (routeHasAidBoxes) {
                tempRoutes[routeCount] = route;
                routeCount++;
            }
        }

        // Criar array final sem posições nulas
        Route[] result = new Route[routeCount];
        for (int i = 0; i < routeCount; i++) {
            result[i] = tempRoutes[i];
        }

        return result;
    }
```

### Classe `StrategyImpl` completa:

```java
public class StrategyImpl implements Strategy {

    private boolean hasCollectableContainer(Vehicle vehicle, AidBox aidbox) {
        Container[] containers = aidbox.getContainers();
        
        for (int i = 0; i < containers.length; i++) {
            if (containers[i].getType() == vehicle.getSupplyType()) {
                Measurement lastMeasurement = containers[i].getLastMeasurement();
                if (lastMeasurement != null) {
                    double valor = lastMeasurement.getValue();
                    double capacidade = containers[i].getCapacity();
                    if (valor > capacidade * 0.8) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private boolean addAidBoxToRoute(Route route, AidBox aidbox, RouteValidator validator) {
        if (validator.validate(route, aidbox)) {
            try {
                route.addAidBox(aidbox);
                return true;
            } catch (RouteException e) {
                return false;
            }
        }
        return false;
    }

    @Override
    public Route[] generate(IInstitution inst, RouteValidator validator) {
        Vehicle[] vehicles = inst.getVehicles();
        AidBox[] aidBoxes = inst.getAidBoxes();
        
        Route[] tempRoutes = new Route[vehicles.length];
        int routeCount = 0;

        for (int i = 0; i < vehicles.length; i++) {
            if (vehicles[i] == null) continue;
            
            Route route = new RouteImpl(vehicles[i]);
            boolean routeHasAidBoxes = false;

            for (int j = 0; j < aidBoxes.length; j++) {
                if (aidBoxes[j] == null) continue;
                
                if (hasCollectableContainer(vehicles[i], aidBoxes[j])) {
                    if (addAidBoxToRoute(route, aidBoxes[j], validator)) {
                        routeHasAidBoxes = true;
                    }
                }
            }

            if (routeHasAidBoxes) {
                tempRoutes[routeCount] = route;
                routeCount++;
            }
        }

        Route[] result = new Route[routeCount];
        for (int i = 0; i < routeCount; i++) {
            result[i] = tempRoutes[i];
        }

        return result;
    }
}
```

---

## Resumo de Cotações

| Questão | Cotação | Tema |
|---------|---------|------|
| Parte 1 – Q1 | 1,5 val | Classes abstratas vs Interfaces |
| Parte 1 – Q2 | 1,5 val | Passagem de argumentos (por valor) |
| Parte 1 – Q3 | 1,5 val | Casting (upcasting/downcasting) |
| Parte 1 – Q4 | 1,5 val | Identidade vs Igualdade (`==` vs `equals`) |
| Parte 2 – 1a | 3 val | `RefrigeratedVehicleImpl` |
| Parte 2 – 1b | 2 val | Testes no `main` |
| Parte 2 – 2a | 4 val | `hasCollectableContainer` + `addAidBoxToRoute` |
| Parte 2 – 2b | 5 val | Método `generate` da `StrategyImpl` |
| **Total** | **20 val** | |
