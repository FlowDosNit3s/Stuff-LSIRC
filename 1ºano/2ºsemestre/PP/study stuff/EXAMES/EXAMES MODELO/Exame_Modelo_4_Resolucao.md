# Resolução do Exame Modelo 4 — Paradigmas de Programação (Época Especial)

## PARTE 1 (6,0 VALORES) — RESPOSTA ABERTA TEÓRICA

### Pergunta 1 (1,5 valores)

#### Resposta Teórica:
Toda a hierarquia de exceções em Java deriva da classe `java.lang.Throwable`. Esta divide-se em dois ramos principais:
1. **Exceções Verificadas (*Checked Exceptions*)**: Derivam diretamente de `Exception` (excluindo `RuntimeException`). Representam situações anómalas das quais o programa pode recuperar (ex.: `AidBoxFullException`, `IOException`). O compilador Java **obriga** a que estas exceções sejam capturadas num bloco `try-catch` ou declaradas explicitamente na assinatura do método através da cláusula `throws`.
2. **Exceções Não Verificadas (*Unchecked Exceptions*)**: Derivam de `RuntimeException` (ex.: `NullPointerException`, `IllegalArgumentException`). Indicam geralmente erros de programação ou pré-condições violadas. O compilador não exige a sua captura ou declaração obrigatória.

**Fluxo `try-catch-finally` e a instrução `return`:**
- O bloco `try` contém o código suscetível de lançar exceções.
- O bloco `catch` captura e trata a exceção especificada.
- O bloco `finally` **executa SEMPRE**, quer ocorra uma exceção quer o bloco `try` termine com sucesso.
- **Comportamento com `return`**: Se existir uma instrução `return` dentro do bloco `try`, o valor a retornar é avaliado e guardado temporariamente, mas a execução salta imediatamente para o bloco `finally` antes de o método ser encerrado. Se o bloco `finally` contiver também um `return`, este irá sobrepor-se (*override*) ao `return` do `try`, alterando o valor final devolvido.

**`try-with-resources` e `AutoCloseable`:**
O `try-with-resources` garante que recursos (streams, ficheiros) são automaticamente fechados no final do bloco, desde que implementem a interface `java.lang.AutoCloseable`, eliminando a necessidade de invocar `.close()` manualmente no `finally`.

---

### Pergunta 2 (1,5 valores)

#### Resposta Teórica:
O **Encapsulamento** oculta os detalhes de implementação interna de uma classe e protege o seu estado contra modificações indevidas a partir de código externo, impondo uma interface pública controlada.

**Os 4 Modificadores de Acesso (por ordem crescente de permissividade):**
1. `private`: Acessível **apenas dentro da própria classe**.
2. *package-private* (default, sem modificador): Acessível por qualquer classe dentro do **mesmo pacote**.
3. `protected`: Acessível no **mesmo pacote** e por **subclasses** noutros pacotes.
4. `public`: Acessível por **qualquer classe** em qualquer pacote.

**Má Prática de Atributos `public`:**
Declarar atributos como `public` permite que código externo altere o estado do objeto para valores inválidos ou incoerentes (ex.: capacidade negativa), contornando qualquer regra de negócio ou validação. Além disso, acopla o código cliente à representação interna da classe.

#### Exemplo Prático:

```java
public class ContainerValidade {
    private double capacity; // private para garantir encapsulamento

    public double getCapacity() {
        return capacity;
    }

    public void setCapacity(double capacity) {
        if (capacity <= 0) {
            throw new IllegalArgumentException("A capacidade deve ser estritamente positiva (> 0).");
        }
        this.capacity = capacity;
    }
}
```

---

### Pergunta 3 (1,5 valores)

#### Resposta Teórica:
- **Sobrecarga (*Overloading*)**: Ocorre na **mesma classe**. Consiste em ter métodos com o **mesmo nome**, mas obrigatoriamente com **listas de parâmetros diferentes** (número, tipos ou ordem de argumentos). O tipo de retorno e as exceções podem variar, mas não servem para distinguir métodos sobrecarregados. É resolvido em tempo de compilação (**Resolução Estática**).
- **Sobreposição (*Overriding*)**: Ocorre numa **subclasse**. Consiste em redefinir um método herdado de uma superclasse ou interface. O método redefinido **deve ter rigorosamente o mesmo nome, a mesma lista de parâmetros e um tipo de retorno compatível (covariante)**. Não pode aumentar a restritividade do modificador de acesso nem lançar exceções verificadas mais amplas.

**Despacho Dinâmico (*Dynamic Method Dispatch*):**
É o mecanismo através do qual a JVM determina, em **tempo de execução**, qual a implementação de um método sobreposto a invocar. A decisão é baseada no **tipo real do objeto** instanciado na memória Heap, e não no tipo declarativo da variável de referência.

#### Exemplo Prático:

```java
class Veiculo {
    public void buzinar() { System.out.println("Buzina genérica"); }
}

class Ambulancia extends Veiculo {
    @Override
    public void buzinar() { System.out.println("Sirene de Emergência!"); } // Overriding
}

public class TestePolimorfismo {
    public static void main(String[] args) {
        Veiculo v = new Ambulancia(); // Tipo declarativo Veiculo, tipo real Ambulancia
        v.buzinar(); // Dynamic Dispatch -> Imprime "Sirene de Emergência!"
    }
}
```

---

### Pergunta 4 (1,5 valores)

#### Resposta Teórica:
O modificador `static` indica que um membro (atributo ou método) pertence à **classe** em si, e não a uma instância específica dessa classe.

- **Atributos Estáticos**: São alocados uma única vez na memória quando a classe é carregada pela JVM. Todas as instâncias da classe partilham a mesma variável.
- **Métodos Estáticos**: Podem ser invocados sem instanciar a classe (utilizando `NomeDaClasse.metodo()`).

**Restrições sobre `this` e `super`:**
As palavras reservadas `this` (referência para o objeto corrente) e `super` (referência para a superclasse da instância corrente) dependem da existência de uma **instância concreta** criada na Heap. Como os métodos estáticos pertencem à classe e podem ser executados sem qualquer objeto instanciado, não existe contexto de instância (`this` ou `super`). Tentar utilizar `this` ou `super` dentro de um método `static` provoca um erro de compilação imediato.

#### Exemplo Prático:

```java
public class ContadorAidBox {
    private static int totalAidBoxes = 0; // Partilhado por todas as caixas
    private final String code;

    public ContadorAidBox(String code) {
        this.code = code;
        totalAidBoxes++; // Incrementa o contador estático
    }

    public static int getTotalAidBoxes() {
        // System.out.println(this.code); -> ERRO DE COMPILAÇÃO! Não é permitido usar this aqui.
        return totalAidBoxes;
    }
}
```

---

## PARTE 2 (14,0 VALORES) — PROGRAMAÇÃO PRÁTICA EM JAVA (DOMÍNIO TP)

### Pergunta 1a (3,0 valores)

```java
// Exceção personalizada verificada
public class AidBoxFullException extends Exception {
    public AidBoxFullException(String message) {
        super(message);
    }
}

// Implementação da classe AidBoxImpl
public class AidBoxImpl implements AidBox {
    private final String code;
    private final String zone;
    private final Container[] containers;
    private int numberOfContainers;

    public AidBoxImpl(String code, String zone) {
        if (code == null || code.trim().isEmpty()) {
            throw new IllegalArgumentException("O código da AidBox não pode ser nulo nem vazio.");
        }
        if (zone == null || zone.trim().isEmpty()) {
            throw new IllegalArgumentException("A zona da AidBox não pode ser nula nem vazia.");
        }

        this.code = code;
        this.zone = zone;
        this.containers = new Container[5]; // Capacidade máxima de 5 contentores
        this.numberOfContainers = 0;
    }

    @Override
    public String getCode() {
        return this.code;
    }

    @Override
    public String getZone() {
        return this.zone;
    }

    @Override
    public Container[] getContainers() {
        Container[] copy = new Container[numberOfContainers];
        for (int i = 0; i < numberOfContainers; i++) {
            copy[i] = containers[i];
        }
        return copy;
    }

    @Override
    public boolean addContainer(Container container) throws AidBoxFullException {
        if (container == null) {
            throw new AidBoxFullException("O contentor a adicionar não pode ser nulo.");
        }
        if (numberOfContainers >= 5) {
            throw new AidBoxFullException("Capacidade máxima de 5 contentores atingida na AidBox " + code);
        }

        containers[numberOfContainers] = container;
        numberOfContainers++;
        return true;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof AidBox)) {
            return false;
        }
        AidBox other = (AidBox) obj;
        
        boolean sameCode = this.code.equals(other.getCode());
        boolean sameZone = this.zone.equals(other.getZone());

        return sameCode && sameZone;
    }
}
```

---

### Pergunta 1b (2,0 valores)

```java
public class AidBoxTest {
    public static void main(String[] args) {
        System.out.println("=== Início do Teste AidBoxTest (Pergunta 1b) ===");

        // Instanciação da AidBox
        AidBox box1 = new AidBoxImpl("BOX-PORTO-01", "ZONA-NORTE");

        // 1. Adição com sucesso de contentores
        try {
            for (int i = 1; i <= 5; i++) {
                final int id = i;
                Container c = new Container() {
                    @Override public String getCode() { return "C-" + id; }
                    @Override public ItemType getType() { return ItemType.MEDICINE; }
                    @Override public double getCapacity() { return 100.0; }
                    @Override public Measurement getLastMeasurement() { return null; }
                };
                box1.addContainer(c);
                System.out.println("Adicionado contentor C-" + i + " com sucesso.");
            }
        } catch (AidBoxFullException e) {
            System.err.println("Erro inesperado ao adicionar contentores válidos: " + e.getMessage());
        }

        // 2. Tentativa de adicionar o 6º contentor (Demonstrar exceção AidBoxFullException)
        try {
            Container c6 = new Container() {
                @Override public String getCode() { return "C-6"; }
                @Override public ItemType getType() { return ItemType.CLOTHING; }
                @Override public double getCapacity() { return 50.0; }
                @Override public Measurement getLastMeasurement() { return null; }
            };
            box1.addContainer(c6);
            System.err.println("ERRO: Devia ter lançado AidBoxFullException ao tentar inserir o 6º contentor!");
        } catch (AidBoxFullException e) {
            System.out.println("Sucesso! Capturada a exceção esperada -> " + e.getMessage());
        }

        // 3. Verificação do método equals()
        AidBox box2 = new AidBoxImpl("BOX-PORTO-01", "ZONA-NORTE"); // Mesmo código e zona
        AidBox box3 = new AidBoxImpl("BOX-PORTO-01", "ZONA-SUL");   // Zona diferente

        System.out.println("box1.equals(box2) [Mesmo código e zona]: " + box1.equals(box2)); // Deve ser true
        System.out.println("box1.equals(box3) [Zona diferente]: " + box1.equals(box3));       // Deve ser false

        System.out.println("=== Todos os testes concluídos com sucesso! ===");
    }
}
```

---

### Pergunta 2a (4,0 valores)

```java
public class OptimizedStrategyImpl implements Strategy {

    public int countCriticalContainers(AidBox aidbox, double threshold) {
        if (aidbox == null) {
            return 0;
        }

        Container[] containers = aidbox.getContainers();
        if (containers == null) {
            return 0;
        }

        int criticalCount = 0;
        for (Container c : containers) {
            if (c != null && c.getCapacity() > 0) {
                Measurement last = c.getLastMeasurement();
                if (last != null) {
                    double percentage = (last.getValue() / c.getCapacity()) * 100.0;
                    if (percentage > threshold) {
                        criticalCount++;
                    }
                }
            }
        }
        return criticalCount;
    }

    public boolean isEligibleAidBox(AidBox aidbox, Vehicle vehicle, double threshold) {
        if (aidbox == null || vehicle == null) {
            return false;
        }

        Container[] containers = aidbox.getContainers();
        if (containers == null) {
            return false;
        }

        boolean hasMatchingType = false;
        for (Container c : containers) {
            if (c != null && c.getType() == vehicle.getSupplyType()) {
                hasMatchingType = true;
                break;
            }
        }

        if (!hasMatchingType) {
            return false;
        }

        // Deve possuir pelo menos 1 contentor crítico
        int criticalCount = countCriticalContainers(aidbox, threshold);
        return criticalCount > 0;
    }

    @Override
    public Route[] generate(IInstitution inst, RouteValidator validator) {
        // Implementado na Pergunta 2b
        return null;
    }
}
```

---

### Pergunta 2b (5,0 valores)

```java
public class OptimizedStrategyImpl implements Strategy {

    public int countCriticalContainers(AidBox aidbox, double threshold) {
        // (Código da Pergunta 2a)
        if (aidbox == null) return 0;
        Container[] containers = aidbox.getContainers();
        if (containers == null) return 0;
        int count = 0;
        for (Container c : containers) {
            if (c != null && c.getCapacity() > 0) {
                Measurement last = c.getLastMeasurement();
                if (last != null && (last.getValue() / c.getCapacity()) * 100.0 > threshold) {
                    count++;
                }
            }
        }
        return count;
    }

    public boolean isEligibleAidBox(AidBox aidbox, Vehicle vehicle, double threshold) {
        // (Código da Pergunta 2a)
        if (aidbox == null || vehicle == null) return false;
        Container[] containers = aidbox.getContainers();
        if (containers == null) return false;
        boolean hasType = false;
        for (Container c : containers) {
            if (c != null && c.getType() == vehicle.getSupplyType()) {
                hasType = true;
                break;
            }
        }
        return hasType && countCriticalContainers(aidbox, threshold) > 0;
    }

    @Override
    public Route[] generate(IInstitution inst, RouteValidator validator) {
        if (inst == null || validator == null) {
            return new Route[0];
        }

        Vehicle[] vehicles = inst.getVehicles();
        AidBox[] aidBoxes = inst.getAidBoxes();

        if (vehicles == null || aidBoxes == null) {
            return new Route[0];
        }

        Route[] tempRoutes = new Route[vehicles.length];
        int routeCount = 0;

        for (Vehicle v : vehicles) {
            if (v == null) {
                continue;
            }

            Route currentRoute = new RouteImpl(v);

            for (AidBox box : aidBoxes) {
                if (box != null) {
                    // USO OBRIGATÓRIO DO MÉTODO 2 DE 2A (que por sua vez consome o Método 1)
                    if (isEligibleAidBox(box, v, 75.0)) {
                        
                        // Validação prévia com RouteValidator
                        if (validator.validate(currentRoute, box)) {
                            try {
                                currentRoute.addAidBox(box);
                            } catch (RouteException e) {
                                // Captura a exceção caso a adição falhe, prosseguindo com as restantes
                            }
                        }

                    }
                }
            }

            // Apenas incluir rotas que não estejam vazias (possuam pelo menos 1 AidBox)
            AidBox[] routeBoxes = currentRoute.getRoute();
            if (routeBoxes != null && routeBoxes.length > 0) {
                tempRoutes[routeCount] = currentRoute;
                routeCount++;
            }
        }

        // Construir o array final de dimensões exatas sem nulos nem rotas vazias
        Route[] finalRoutes = new Route[routeCount];
        for (int i = 0; i < routeCount; i++) {
            finalRoutes[i] = tempRoutes[i];
        }

        return finalRoutes;
    }
}
```
