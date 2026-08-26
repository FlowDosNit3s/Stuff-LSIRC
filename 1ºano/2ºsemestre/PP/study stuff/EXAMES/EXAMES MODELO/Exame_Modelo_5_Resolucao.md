# Resolução — Exame Modelo 5 — Paradigmas de Programação
**Época de Recurso | Ano Letivo: 2025/2026**

---

## PARTE 1 – Perguntas Teóricas

### Pergunta 1
A Máquina Virtual Java (JVM) organiza a memória atribuída às aplicações principalmente em duas áreas fundamentais:

1. **Stack (Pilha de Execução):**
   - **Propósito:** Armazena as chamadas de métodos (*frames* de pilha), variáveis locais e primitivos de curta duração.
   - **Características:** O acesso é extremamente rápido e a alocação/desalocação ocorre automaticamente à medida que os métodos entram e saem de escopo (LIFO — *Last-In, First-Out*). Cada *thread* possui a sua própria Stack isolada.
2. **Heap (Memória Dinâmica):**
   - **Propósito:** Armazena todas as instâncias de objetos e arrays criados dinamicamente com o operador `new`.
   - **Características:** É partilhada por todas as *threads* da aplicação. A sua gestão é efetuada pelo **Garbage Collector (GC)**, que identifica objetos aos quais já não é possível aceder a partir de nenhuma referência ativa na Stack (*unreachable objects*) e deita-os fora para libertar memória.

**Prevenção da `NullPointerException` (NPE):**
A exceção `NullPointerException` ocorre sempre que o programa tenta invocar um método ou aceder a um atributo através de uma variável de referência que contém o valor `null`.
- **Estratégias defensivas:**
  1. Validar explicitamente os argumentos recebidos nos construtores/métodos com `if (ref == null)`.
  2. Inverter comparações literais de String (ex: `"PERISHABLE".equals(tipo)` em vez de `tipo.equals("PERISHABLE")`).
  3. Inicializar coleções e arrays nos construtores em vez de os deixar como `null`.
  4. Retornar arrays de tamanho 0 (`new Elemento[0]`) em vez de retornar `null` em métodos que devolvem coleções.

```java
public class GestaoMemoria {
    public void exemploDefensivo(String texto) {
        // Prevenção de NPE com comparação invertida e validação prévia
        if (texto != null && "OK".equalsIgnoreCase(texto)) {
            System.out.println("Texto válido.");
        }
    }
}
```

---

### Pergunta 2
A classe `String` em Java é **imutável**, o que significa que uma vez criado o objeto String na memória, a sua sequência de caracteres não pode ser alterada. Qualquer operação de modificação (como `concat()`, `toUpperCase()` ou o operador `+`) cria um objeto `String` completamente novo na Heap.

- **String Pool:** É uma área de memória reservada na Heap onde a JVM armazena literais de texto. Se dois literais com o mesmo conteúdo forem declarados (ex: `String s1 = "ola"; String s2 = "ola";`), a JVM reutiliza a mesma referência do String Pool para poupar memória.
- **`String` vs `StringBuilder` vs `StringBuffer` em Ciclos:**
  - Se concatenar Strings dentro de um ciclo usando o operador `+`, a JVM cria milhares de objetos intermédios temporários na Heap, sobrecarregando o Garbage Collector e degradando o desempenho.
  - A classe `StringBuilder` é **mutável** e deve ser utilizada para concatenações intensivas em ciclos num único thread, pois modifica o seu buffer interno sem criar novos objetos.
  - A classe `StringBuffer` é equivalente ao `StringBuilder`, mas os seus métodos são **sincronizados (`synchronized`)**, garantindo segurança em ambientes multithread (*thread-safe*), embora com um pequeno custo de desempenho.

```java
public class TesteString {
    public static void main(String[] args) {
        // Má prática: cria N objetos String temporários
        String resultadoIncorreto = "";
        for (int i = 0; i < 1000; i++) {
            resultadoIncorreto += i;
        }

        // Boa prática: reutiliza o buffer interno do StringBuilder
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 1000; i++) {
            sb.append(i);
        }
        String resultadoCorreto = sb.toString();
    }
}
```

---

### Pergunta 3
Em Java, a instrução `package` permite agrupar e organizar classes e interfaces relacionadas em módulos lógicos, evitando conflitos de nomes entre bibliotecas.

**Visibilidade por Defeito (*Package-Private* / sem modificador):**
Quando não é especificado nenhum modificador de acesso (`public`, `protected` ou `private`) antes de uma classe, atributo ou método, este assume a visibilidade *package-private*.
- **Comportamento:** O membro ou classe fica acessível exclusivamente por classes que pertençam exatamente ao **mesmo pacote**. Classes localizadas noutros pacotes (mesmo que sejam subclasses) não conseguem aceder ao elemento.

**Vantagem na Arquitetura de Software:**
O encapsulamento ao nível de pacote permite ocultar os detalhes de implementação interna e as classes auxiliares de uma biblioteca, expondo apenas as interfaces públicas e classes principais da API. Isto previne a utilização indevida de partes internas pelo código cliente e permite refatorar a implementação interna do pacote sem quebrar a compatibilidade com o exterior.

```java
// Ficheiro: com/empresa/logistica/interna/MotorCalculo.java
package com.empresa.logistica.interna;

// Visibilidade de pacote: apenas visível dentro de com.empresa.logistica.interna
class MotorCalculo {
    void processar() {
        System.out.println("Processamento interno");
    }
}
```

---

### Pergunta 4
- **Interfaces Marcadoras (*Marker Interfaces*):** São interfaces que não declaram qualquer método ou constante (ex: `Serializable`, `Cloneable`). O seu propósito é assinalar ao compilador ou à JVM que a classe que a implementa possui uma determinada propriedade ou permissão especial.
- **Métodos `default` em Interfaces (Java 8+):** Permitem adicionar métodos com uma implementação por defeito diretamente no corpo de uma interface (utilizando a palavra reservada `default`). O seu objetivo principal é permitir a **evolução de interfaces pré-existentes** adicionando novas funcionalidades sem quebrar o código de classes antigas que já implementavam a interface.

**Resolução de Conflitos (Problema do Diamante em Métodos `default`):**
Se uma classe implementar duas interfaces distintas que declarem um método `default` com exatamente a mesma assinatura, o compilador exige obrigatoriamente que a classe sobreponha (`@Override`) o método conflitante para resolver explicitamente a ambiguidade.

```java
interface A {
    default void saudar() {
        System.out.println("Olá da Interface A");
    }
}

interface B {
    default void saudar() {
        System.out.println("Olá da Interface B");
    }
}

public class Servico implements A, B {
    // Obrigatório resolver a ambiguidade!
    @Override
    public void saudar() {
        A.super.saudar(); // Escolhe explicitamente a implementação de A (ou fornece uma nova)
    }
}
```

---

## PARTE 2 – Programação em Java

### Pergunta 1a
```java
public class ShipmentBatchImpl implements ShipmentBatch {
    private static final int MAX_CONTAINERS = 6;
    private String batchCode;
    private ItemType itemType;
    private Container[] containers;
    private int numberOfContainers;

    public ShipmentBatchImpl(String batchCode, ItemType itemType) {
        if (batchCode == null || batchCode.trim().isEmpty()) {
            throw new IllegalArgumentException("O codigo do lote nao pode ser nulo.");
        }
        if (itemType == null) {
            throw new IllegalArgumentException("O tipo de item do lote nao pode ser nulo.");
        }
        this.batchCode = batchCode;
        this.itemType = itemType;
        this.containers = new Container[MAX_CONTAINERS];
        this.numberOfContainers = 0;
    }

    @Override
    public String getBatchCode() {
        return this.batchCode;
    }

    @Override
    public ItemType getItemType() {
        return this.itemType;
    }

    @Override
    public Container[] getContainers() {
        Container[] result = new Container[numberOfContainers];
        for (int i = 0; i < numberOfContainers; i++) {
            result[i] = containers[i];
        }
        return result;
    }

    @Override
    public void addContainer(Container container) throws ShipmentException {
        if (container == null) {
            throw new ShipmentException("O contentor a adicionar nao pode ser nulo.");
        }
        if (container.getType() != this.itemType) {
            throw new ShipmentException("O tipo do contentor (" + container.getType() + ") e incompativel com o lote (" + this.itemType + ").");
        }
        if (numberOfContainers >= MAX_CONTAINERS) {
            throw new ShipmentException("Capacidade maxima do lote de " + MAX_CONTAINERS + " contentores atingida.");
        }
        containers[numberOfContainers] = container;
        numberOfContainers++;
    }

    @Override
    public double getTotalWeight() {
        double total = 0;
        for (int i = 0; i < numberOfContainers; i++) {
            if (containers[i] != null) {
                Measurement last = containers[i].getLastMeasurement();
                if (last != null) {
                    total += last.getValue();
                }
            }
        }
        return total;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof ShipmentBatch)) {
            return false;
        }
        ShipmentBatch other = (ShipmentBatch) obj;
        return this.batchCode.equals(other.getBatchCode());
    }

    @Override
    public String toString() {
        return "ShipmentBatchImpl [Codigo: " + batchCode + " | Tipo: " + itemType + " | Contentores: " + numberOfContainers + "/" + MAX_CONTAINERS + "]";
    }
}
```

---

### Pergunta 1b
```java
public class TestShipmentBatch {
    public static void main(String[] args) {
        System.out.println("=== Teste da Classe ShipmentBatchImpl ===");

        ShipmentBatchImpl lote1 = new ShipmentBatchImpl("BATCH-100", ItemType.MEDICINE);
        ShipmentBatchImpl lote2 = new ShipmentBatchImpl("BATCH-100", ItemType.MEDICINE);
        ShipmentBatchImpl lote3 = new ShipmentBatchImpl("BATCH-200", ItemType.CLOTHING);

        System.out.println("Codigo Lote 1: " + lote1.getBatchCode());
        System.out.println("Tipo Lote 1: " + lote1.getItemType());
        System.out.println("Contentores Iniciais: " + lote1.getContainers().length);

        // Teste de Igualdade
        System.out.println("lote1.equals(lote2) [mesmo código]: " + lote1.equals(lote2)); // true
        System.out.println("lote1.equals(lote3) [códigos dif]: " + lote1.equals(lote3)); // false

        // Captura de exceção ao adicionar null ou tipo incompatível
        try {
            lote1.addContainer(null);
        } catch (ShipmentException e) {
            System.out.println("Exceção capturada (null): " + e.getMessage());
        }

        System.out.println("Peso Total inicial lote1: " + lote1.getTotalWeight());
    }
}
```

---

### Pergunta 2a
```java
public class LoadPlannerImpl implements LoadPlanner {

    public double calculateTotalCapacityBySupplyType(AidBox aidbox, ItemType type) {
        if (aidbox == null || type == null) {
            return 0;
        }
        Container[] containers = aidbox.getContainers();
        if (containers == null || containers.length == 0) {
            return 0;
        }
        double somaCapacidade = 0;
        for (int i = 0; i < containers.length; i++) {
            if (containers[i] != null && containers[i].getType() == type) {
                somaCapacidade += containers[i].getCapacity();
            }
        }
        return somaCapacidade;
    }

    public boolean isVehicleCompatible(Vehicle vehicle, AidBox aidbox) {
        if (vehicle == null || aidbox == null) {
            return false;
        }
        ItemType tipoVeiculo = vehicle.getSupplyType();
        if (tipoVeiculo == null) {
            return false;
        }
        Container[] containers = aidbox.getContainers();
        if (containers == null || containers.length == 0) {
            return false;
        }
        for (int i = 0; i < containers.length; i++) {
            if (containers[i] != null && containers[i].getType() == tipoVeiculo) {
                return true;
            }
        }
        return false;
    }

    @Override
    public AidBox[] getPriorityAidBoxesForVehicle(IInstitution inst, Vehicle vehicle, double minimumCapacityRequired) {
        return new AidBox[0];
    }
}
```

---

### Pergunta 2b
```java
public class LoadPlannerImpl implements LoadPlanner {

    public double calculateTotalCapacityBySupplyType(AidBox aidbox, ItemType type) {
        if (aidbox == null || type == null) {
            return 0;
        }
        Container[] containers = aidbox.getContainers();
        if (containers == null || containers.length == 0) {
            return 0;
        }
        double somaCapacidade = 0;
        for (int i = 0; i < containers.length; i++) {
            if (containers[i] != null && containers[i].getType() == type) {
                somaCapacidade += containers[i].getCapacity();
            }
        }
        return somaCapacidade;
    }

    public boolean isVehicleCompatible(Vehicle vehicle, AidBox aidbox) {
        if (vehicle == null || aidbox == null) {
            return false;
        }
        ItemType tipoVeiculo = vehicle.getSupplyType();
        if (tipoVeiculo == null) {
            return false;
        }
        Container[] containers = aidbox.getContainers();
        if (containers == null || containers.length == 0) {
            return false;
        }
        for (int i = 0; i < containers.length; i++) {
            if (containers[i] != null && containers[i].getType() == tipoVeiculo) {
                return true;
            }
        }
        return false;
    }

    @Override
    public AidBox[] getPriorityAidBoxesForVehicle(IInstitution inst, Vehicle vehicle, double minimumCapacityRequired) {
        if (inst == null || vehicle == null) {
            return new AidBox[0];
        }
        AidBox[] aidBoxes = inst.getAidBoxes();
        if (aidBoxes == null || aidBoxes.length == 0) {
            return new AidBox[0];
        }

        ItemType tipoVeiculo = vehicle.getSupplyType();

        // 1º Passo: Contar AidBoxes elegíveis para criar o array com tamanho exato
        int selecionadas = 0;
        for (int i = 0; i < aidBoxes.length; i++) {
            if (aidBoxes[i] != null && isVehicleCompatible(vehicle, aidBoxes[i])) {
                double capacidadeTipo = calculateTotalCapacityBySupplyType(aidBoxes[i], tipoVeiculo);
                if (capacidadeTipo >= minimumCapacityRequired) {
                    selecionadas++;
                }
            }
        }

        if (selecionadas == 0) {
            return new AidBox[0];
        }

        // 2º Passo: Preencher o array sem posições nulas
        AidBox[] result = new AidBox[selecionadas];
        int index = 0;

        for (int i = 0; i < aidBoxes.length; i++) {
            if (aidBoxes[i] != null && isVehicleCompatible(vehicle, aidBoxes[i])) {
                double capacidadeTipo = calculateTotalCapacityBySupplyType(aidBoxes[i], tipoVeiculo);
                if (capacidadeTipo >= minimumCapacityRequired) {
                    result[index] = aidBoxes[i];
                    index++;
                }
            }
        }

        return result;
    }
}
```
