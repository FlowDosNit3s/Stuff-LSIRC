# Resolução — Exame Modelo 4 — Paradigmas de Programação
**Época de Recurso | Ano Letivo: 2025/2026**

---

## PARTE 1 – Perguntas Teóricas

### Pergunta 1
A palavra reservada `final` em Java é utilizada para declarar elementos inalteráveis, possuindo comportamentos distintos consoante o contexto de aplicação:

1. **Aplicada a uma Classe (`final class`):** Impede que a classe seja herdada/especializada por qualquer outra classe (proíbe o uso de `extends`). Garante a imutabilidade da hierarquia e a segurança de tipos (por exemplo, a classe `java.lang.String` é `final`).
2. **Aplicada a um Método (`final void metodo()`):** Impede que o método seja sobreposto (*overridden*) por subclasse alguma. É utilizado para assegurar que a implementação de um comportamento crítico não pode ser alterada por especializações.
3. **Aplicada a uma Variável / Atributo (`final int x`):** Transforma a variável numa constante de atribuição única. Após ser inicializada com um valor, qualquer tentativa subsequente de reatribuição resulta num erro de compilação.

**Diferença entre Tipos Primitivos e Referências de Objetos:**
- **Tipo Primitivo:** O valor em si armazenado na variável fica selado e inalterável (ex: `final int MAX = 10;` — a variável `MAX` conterá permanentemente o valor 10).
- **Tipo de Referência (Objeto):** O **endereço de memória** (referência) contido na variável é inalterável, o que significa que a variável não pode ser reatribuída para apontar para outro objeto na Heap. Contudo, **o estado interno do objeto apontado pode ser modificado**, desde que a classe do objeto forneça métodos mutadores (setters) ou atributos acessíveis.

```java
public final class ExemploFinal {
    private final int limitePrimitivo = 100;
    private final int[] numeros = new int[]{1, 2, 3};

    public void demonstrar() {
        // limitePrimitivo = 200; // ERRO DE COMPILAÇÃO! Não pode alterar o valor primitivo.

        // numeros = new int[]{4, 5, 6}; // ERRO DE COMPILAÇÃO! Não pode reatribuir a referência.

        numeros[0] = 99; // PERMITIDO! O conteúdo do array apontado pela referência pode ser alterado.
    }
}
```

---

### Pergunta 2
As **classes Wrapper** (como `Integer`, `Double`, `Boolean`, `Character`) são classes utilitárias que encapsulam os tipos primitivos correspondentes em objetos Java. O seu propósito fundamental é permitir a utilização de tipos primitivos em contextos da linguagem onde apenas objetos são aceites, tais como no âmbito do mecanismo de Generics e nas coleções da Java Collections Framework (por exemplo, `ArrayList<Integer>`).

- **Autoboxing:** Conversão automática realizada pelo compilador do tipo primitivo para a respetiva classe Wrapper (ex: `Integer obj = 10;`).
- **Unboxing:** Conversão automática inversa, onde o compilador extrai o valor primitivo do interior do objeto Wrapper (ex: `int num = obj;`).

**Riscos da Comparação com `==` vs `equals()`:**
O operador `==` compara a **identidade de referências de memória** (se ambas as variáveis apontam para a mesma posição na Heap) e não a igualdade de valores contidos nos objetos. Em Java, a JVM mantém uma cache interna de objetos `Integer` para valores no intervalo de **-128 a 127**. Assim, para números dentro desse intervalo, o autoboxing devolve a mesma instância reutilizada da cache, fazendo com que `==` devolva surpreendentemente `true`. No entanto, para valores fora desse intervalo, a JVM aloca objetos distintos na Heap, fazendo com que `==` devolva `false`, mesmo que os números sejam matematicamente idênticos. Para comparar os valores lógicos de objetos Wrapper de forma segura, deve utilizar-se **sempre o método `equals()`**.

```java
public class TesteWrapper {
    public static void main(String[] args) {
        Integer a = 100;
        Integer b = 100;
        System.out.println(a == b); // true (reutiliza cache de -128 a 127)

        Integer x = 200;
        Integer y = 200;
        System.out.println(x == y); // false (instâncias distintas na Heap!)
        System.out.println(x.equals(y)); // true (compara os valores lógicos corretamente)
    }
}
```

---

### Pergunta 3
A **Serialização** é o mecanismo em Java que permite converter o estado completo de um objeto (dados dos seus atributos na Heap) numa sequência contínua de bytes. Esta sequência de bytes pode ser armazenada em ficheiros de disco (persistência) ou transmitida através da rede (comunicação remota). O processo inverso denomina-se Deserialização.

Para que uma classe seja elegível para serialização, deve implementar a interface marcadora `java.io.Serializable` (que não possui métodos).

**Mecanismos Associados:**
- **Modificador `transient`:** É aplicado a atributos de uma classe que **não devem ser incluídos** no processo de serialização. É utilizado para omitir informação sensível (como palavras-passe), dados temporários ou referências a objetos não serializáveis (evitando a exceção `NotSerializableException`).
- **Identificador `serialVersionUID`:** É uma constante estática de 64 bits (`private static final long serialVersionUID`) que atua como um número de versão do contrato da classe. Durante a deserialização, a JVM compara o `serialVersionUID` do fluxo de bytes com o da classe carregada na aplicação. Se os identificadores forem diferentes, a JVM lança uma exceção `InvalidClassException`, prevenindo a corrupção de memória decorrente de alterações incompatíveis na estrutura da classe.

```java
import java.io.Serializable;

public class Utilizador implements Serializable {
    private static final long serialVersionUID = 1L;

    private String username;
    private transient String password; // Não será gravado/serializado no ficheiro
    private String email;

    public Utilizador(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
    }
}
```

---

### Pergunta 4
A biblioteca de Input/Output (`java.io`) em Java divide o processamento de fluxos de dados em duas famílias fundamentais:

1. **Byte Streams (orientados a bytes):**
   - **Tamanho dos dados:** Operam ao nível de **bytes em bruto (8 bits)**.
   - **Classes base:** `InputStream` e `OutputStream` (e subclasses como `FileInputStream`, `FileOutputStream`, `BufferedInputStream`).
   - **Cenários adequados:** Apropriados para qualquer tipo de dados binários ou ficheiros sem codificação de texto legível, tais como imagens (`.png`, `.jpg`), áudio/vídeo, ficheiros executáveis, ficheiros comprimidos (`.zip`) ou streams de sockets na rede.

2. **Character Streams (orientados a caracteres):**
   - **Tamanho dos dados:** Operam ao nível de **caracteres Unicode (16 bits)**.
   - **Classes base:** `Reader` e `Writer` (e subclasses como `FileReader`, `FileWriter`, `BufferedReader`, `PrintWriter`).
   - **Cenários adequados:** Concebidos especificamente para a leitura e escrita de texto legível por humanos (`.txt`, `.json`, `.xml`, `.csv`). Efetuam a tradução automática entre os bytes no suporte físico e a codificação de caracteres configurada no sistema (ex: UTF-8).

```java
import java.io.*;

public class ExemploIO {
    public void copiarFicheiroBinario(File origem, File destino) throws IOException {
        try (InputStream in = new FileInputStream(origem);
             OutputStream out = new FileOutputStream(destino)) {
            byte[] buffer = new byte[1024];
            int bytesLidos;
            while ((bytesLidos = in.read(buffer)) != -1) {
                out.write(buffer, 0, bytesLidos);
            }
        }
    }

    public void lerFicheiroTexto(File ficheiro) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(ficheiro))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                System.out.println(linha);
            }
        }
    }
}
```

---

## PARTE 2 – Programação em Java

### Pergunta 1a
```java
public class AlertImpl implements Alert {
    private String code;
    private AlertType type;
    private String description;
    private int severityLevel;

    public AlertImpl(String code, AlertType type, String description, int severityLevel) {
        if (code == null || code.trim().isEmpty()) {
            throw new IllegalArgumentException("O codigo do alerta nao pode ser nulo ou vazio.");
        }
        if (type == null) {
            throw new IllegalArgumentException("O tipo de alerta nao pode ser nulo.");
        }
        if (severityLevel < 1 || severityLevel > 5) {
            throw new IllegalArgumentException("O nivel de gravidade deve estar entre 1 e 5.");
        }
        this.code = code;
        this.type = type;
        this.description = (description != null) ? description : "";
        this.severityLevel = severityLevel;
    }

    @Override
    public String getCode() {
        return this.code;
    }

    @Override
    public AlertType getType() {
        return this.type;
    }

    @Override
    public String getDescription() {
        return this.description;
    }

    @Override
    public int getSeverityLevel() {
        return this.severityLevel;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof Alert)) {
            return false;
        }
        Alert other = (Alert) obj;
        return this.code.equals(other.getCode());
    }

    @Override
    public String toString() {
        return "AlertImpl [Codigo: " + code + " | Tipo: " + type + " | Gravidade: " + severityLevel + " | Descricao: " + description + "]";
    }
}
```

---

### Pergunta 1b
```java
public class TestAlert {
    public static void main(String[] args) {
        System.out.println("=== Teste da Classe AlertImpl ===");

        // Teste de criação com dados válidos
        AlertImpl a1 = new AlertImpl("ALT-001", AlertType.CAPACITY_OVERFLOW, "Contentor acima de 95%", 4);
        AlertImpl a2 = new AlertImpl("ALT-001", AlertType.MISSING_MEASUREMENTS, "Sem medições registradas", 2);
        AlertImpl a3 = new AlertImpl("ALT-002", AlertType.INVALID_SENSOR, "Falha de comunicação", 5);

        System.out.println("a1 Getters: " + a1.getCode() + " | " + a1.getType() + " | Grav: " + a1.getSeverityLevel());
        System.out.println("a1 toString: " + a1.toString());

        // Teste de igualdade lógica por código
        System.out.println("a1.equals(a2) [mesmo código]: " + a1.equals(a2)); // Deve ser true
        System.out.println("a1.equals(a3) [códigos diferentes]: " + a1.equals(a3)); // Deve ser false
        System.out.println("a1.equals(null): " + a1.equals(null)); // Deve ser false

        // Teste de validação de argumentos inválidos (exceções)
        try {
            new AlertImpl(null, AlertType.CAPACITY_OVERFLOW, "Erro", 3);
        } catch (IllegalArgumentException e) {
            System.out.println("Exceção capturada (código nulo): " + e.getMessage());
        }

        try {
            new AlertImpl("ALT-999", AlertType.CAPACITY_OVERFLOW, "Erro", 10);
        } catch (IllegalArgumentException e) {
            System.out.println("Exceção capturada (gravidade inválida): " + e.getMessage());
        }
    }
}
```

---

### Pergunta 2a
```java
public class AlertManagerImpl implements AlertManager {

    public boolean isContainerInCriticalState(Container container) {
        if (container == null) {
            return true;
        }
        Measurement last = container.getLastMeasurement();
        if (last == null) {
            return true;
        }
        double capacity = container.getCapacity();
        if (capacity <= 0) {
            return true;
        }
        double occupancyPercentage = (last.getValue() / capacity) * 100;
        return occupancyPercentage > 95.0;
    }

    public int countCriticalContainersInAidBox(AidBox aidbox) {
        if (aidbox == null) {
            return 0;
        }
        Container[] containers = aidbox.getContainers();
        if (containers == null || containers.length == 0) {
            return 0;
        }
        int count = 0;
        for (int i = 0; i < containers.length; i++) {
            if (isContainerInCriticalState(containers[i])) {
                count++;
            }
        }
        return count;
    }

    @Override
    public Alert[] generateMaintenanceAlerts(IInstitution inst) {
        return new Alert[0];
    }
}
```

---

### Pergunta 2b
```java
public class AlertManagerImpl implements AlertManager {

    public boolean isContainerInCriticalState(Container container) {
        if (container == null) {
            return true;
        }
        Measurement last = container.getLastMeasurement();
        if (last == null) {
            return true;
        }
        double capacity = container.getCapacity();
        if (capacity <= 0) {
            return true;
        }
        double occupancyPercentage = (last.getValue() / capacity) * 100;
        return occupancyPercentage > 95.0;
    }

    public int countCriticalContainersInAidBox(AidBox aidbox) {
        if (aidbox == null) {
            return 0;
        }
        Container[] containers = aidbox.getContainers();
        if (containers == null || containers.length == 0) {
            return 0;
        }
        int count = 0;
        for (int i = 0; i < containers.length; i++) {
            if (isContainerInCriticalState(containers[i])) {
                count++;
            }
        }
        return count;
    }

    @Override
    public Alert[] generateMaintenanceAlerts(IInstitution inst) {
        if (inst == null) {
            return new Alert[0];
        }
        AidBox[] aidBoxes = inst.getAidBoxes();
        if (aidBoxes == null || aidBoxes.length == 0) {
            return new Alert[0];
        }

        // Primeiro passo: determinar quantos alertas serão gerados para alocar o array exato
        int totalAlertas = 0;
        for (int i = 0; i < aidBoxes.length; i++) {
            if (aidBoxes[i] != null) {
                int numCriticos = countCriticalContainersInAidBox(aidBoxes[i]);
                if (numCriticos > 0) {
                    totalAlertas++;
                }
            }
        }

        if (totalAlertas == 0) {
            return new Alert[0];
        }

        // Segundo passo: preencher o array sem posições nulas
        Alert[] result = new Alert[totalAlertas];
        int index = 0;

        for (int i = 0; i < aidBoxes.length; i++) {
            if (aidBoxes[i] != null) {
                int numCriticos = countCriticalContainersInAidBox(aidBoxes[i]);
                if (numCriticos > 0) {
                    String codigoAlerta = "ALT-" + aidBoxes[i].getCode();
                    String descricao = "AidBox com " + numCriticos + " contentor(es) critico(s).";
                    int gravidade = Math.min(numCriticos, 5);

                    result[index] = new AlertImpl(codigoAlerta, AlertType.CAPACITY_OVERFLOW, descricao, gravidade);
                    index++;
                }
            }
        }

        return result;
    }
}
```
