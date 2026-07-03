# 🎯 Perguntas Teóricas Possíveis — Recurso PP 2025/2026

> **Premissa:** O professor **NÃO repete** as perguntas da Época Normal no Recurso.
> Este ficheiro exclui os temas das perguntas que saíram no Exame Teórico da Época Normal 2025/2026 e foca-se nos restantes tópicos do programa.

---

## ❌ Temas JÁ ELIMINADOS (Saíram na Época Normal 2025/2026)

| # | Tema | Pergunta EN 25/26 |
|---|------|--------------------|
| 1 | Classes Abstratas vs Interfaces | Diferenças estruturais, construtores, estado e quando escolher cada um. |
| 2 | Passagem de Argumentos | Passagem por valor em tipos primitivos vs cópia da referência em objetos. |
| 3 | Conversão de Tipos (*Casting*) | Upcasting (automático/seguro) vs Downcasting (explícito, `instanceof` e `ClassCastException`). |
| 4 | Identidade vs Igualdade | Comparação física (`==`) vs lógica (`equals()`), e redefinição de `toString()`. |

---

## ✅ PERGUNTAS COM MAIOR PROBABILIDADE DE SAIR NO RECURSO

### 🔥🔥🔥 PRIORIDADE MÁXIMA (Temas centrais de POO não avaliados na EN 25/26)

---

### 📌 P1 — Tratamento de Exceções: Checked vs Unchecked e o bloco `try-catch-finally`
**Frequência:** Altíssima | **Aula Relacionada:** Aula 11 (Exceções)

**Pergunta esperada:**
> O que são exceções em Java e como se dividem na hierarquia da linguagem? Distinga detalhadamente Checked Exceptions de Unchecked Exceptions (Runtime Exceptions), indicando o comportamento do compilador perante cada uma. Explique adicionalmente qual o fluxo de execução do bloco `try-catch-finally` e em que circunstâncias o bloco `finally` é executado.

**Resposta Rápida:**
* **Exceção:** É um evento/erro que ocorre durante a execução do programa e interrompe o seu fluxo normal de instruções. Todas derivam da classe `Throwable`.
* **Checked Exceptions (Verificadas):**
  * Subclasses de `Exception` (excluindo as que estendem `RuntimeException`). Exemplos: `IOException`, `FileNotFoundException`, `SQLException`.
  * **Comportamento:** O compilador obriga a tratá-las explicitamente com um bloco `try-catch` ou a declará-las na assinatura do método com a cláusula `throws`. Representam condições de erro previsíveis e das quais a aplicação deve tentar recuperar.
* **Unchecked Exceptions / Runtime Exceptions (Não Verificadas):**
  * Subclasses de `RuntimeException`. Exemplos: `NullPointerException`, `ArrayIndexOutOfBoundsException`, `IllegalArgumentException`.
  * **Comportamento:** O compilador não exige o seu tratamento ou declaração. Geralmente indicam erros de programação e lógica do código que devem ser corrigidos pelo programador.
* **Fluxo `try-catch-finally`:**
  * O código no bloco `try` é executado. Se ocorrer uma exceção, o fluxo salta imediatamente para o correspondente bloco `catch` compatível.
  * O bloco **`finally` é executado sempre**, quer tenha ocorrido uma exceção (e tenha sido capturada ou não), quer a execução tenha terminado sem erros. É tipicamente utilizado para limpeza e libertação de recursos (ex: fechar streams de ficheiros, sockets ou conexões a bases de dados). Mesmo que o `try` ou o `catch` contenham uma instrução `return`, o bloco `finally` será executado antes do método retornar.

---

### 📌 P2 — Modificadores de Acesso e o Princípio de Encapsulamento
**Frequência:** Alta | **Aula Relacionada:** Aula 05 (Encapsulamento)

**Pergunta esperada:**
> Explique o conceito de encapsulamento no paradigma orientado a objetos e discuta a sua importância no desenho de software robusto. Identifique e descreva os quatro modificadores de acesso do Java, ordenando-os do mais restritivo para o mais permissivo.

**Resposta Rápida:**
* **Encapsulamento:** Consiste em ocultar os detalhes de implementação e a estrutura interna de dados de uma classe, expondo apenas uma interface pública controlada (métodos). A sua importância reside na proteção do estado dos objetos contra modificações arbitrárias ou inválidas e no isolamento do código, o que facilita alterações futuras na lógica sem impactar as classes clientes.
* **Modificadores de Acesso (do mais restritivo para o mais permissivo):**
  1. **`private`:** O membro só é visível/acedido dentro da própria classe onde foi declarado.
  2. **Sem modificador (Package-Private / default):** O membro é visível apenas por classes pertencentes ao mesmo pacote (*package*).
  3. **`protected`:** O membro é visível para classes do mesmo pacote e também para subclasses (classes derivadas), mesmo que estas estejam localizadas em pacotes diferentes.
  4. **`public`:** O membro é visível e acessível por qualquer classe em qualquer pacote.

---

### 📌 P3 — Polimorfismo: Sobrecarga (*Overloading*) vs Sobreposição (*Overriding*)
**Frequência:** Altíssima | **Aula Relacionada:** Aula 05 / Aula 06 / Aula 10

**Pergunta esperada:**
> O polimorfismo assume diferentes formas em Java. Diferencie detalhadamente os conceitos de Sobrecarga (Overloading) e Sobreposição (Overriding) de métodos. Na sua resposta, distinga as fases do ciclo de desenvolvimento em que a assinatura e a implementação são decididas e ilustre cada um dos conceitos com um breve excerto de código Java.

**Resposta Rápida:**
* **Sobrecarga (Overloading) - Polimorfismo de Compilação / Estático:**
  * **Definição:** Ocorre na mesma classe quando existem múltiplos métodos com o mesmo nome, mas com assinaturas diferentes (número, ordem ou tipo de parâmetros diferentes). O tipo de retorno sozinho não serve para sobrecarregar um método.
  * **Decisão:** Ocorre em **tempo de compilação** (*compile-time* / ligação estática). O compilador analisa os argumentos passados na invocação para decidir qual o método a executar.
  * *Exemplo:*
    ```java
    public int somar(int a, int b) { return a + b; }
    public double somar(double a, double b) { return a + b; }
    ```
* **Sobreposição (Overriding) - Polimorfismo de Execução / Dinâmico:**
  * **Definição:** Ocorre numa relação de herança quando uma subclasse redefine um método herdado da superclasse, mantendo exatamente a mesma assinatura (nome, parâmetros e tipo de retorno compatível). Utiliza-se a anotação `@Override`.
  * **Decisão:** Ocorre em **tempo de execução** (*runtime* / ligação dinâmica). A JVM determina qual a versão do método a chamar com base no tipo real da instância alocada na Heap, e não no tipo da variável de referência.
  * *Exemplo:*
    ```java
    class Animal {
        void emitirSom() { System.out.println("Som genérico"); }
    }
    class Cao extends Animal {
        @Override
        void emitirSom() { System.out.println("Ladrar"); }
    }
    ```

---

### 🔥🔥 PRIORIDADE ALTA (Mecanismos específicos da linguagem e boas práticas de modelação)

---

### 📌 P4 — Membros Estáticos (`static`): Variáveis e Métodos
**Frequência:** Alta | **Aula Relacionada:** Aula 03 (Conceito de Classe)

**Pergunta esperada:**
> Explique detalhadamente o significado e o comportamento do modificador `static` quando aplicado a variáveis (atributos) e a métodos de uma classe Java. Discuta adicionalmente as restrições que um método estático possui relativamente ao acesso a membros de instância (não-estáticos) e o uso das palavras-chave `this` e `super`.

**Resposta Rápida:**
* **Variáveis Estáticas (`static`):**
  * Pertencem à classe em si e não às suas instâncias. Existe apenas **uma cópia** da variável em memória (alocada na área de classes - Metaspace), partilhada por todos os objetos criados a partir dessa classe. Modificar o valor numa instância altera-o para todas as outras.
* **Métodos Estáticos (`static`):**
  * Pertencem à classe e podem ser invocados diretamente através do nome da classe (ex: `Math.sqrt()`), sem a necessidade de instanciar previamente um objeto.
* **Restrições dos Métodos Estáticos:**
  * **Não podem aceder diretamente** a atributos de instância (não-estáticos) ou invocar métodos de instância da classe, uma vez que não são executados no contexto de um objeto específico. Para acederem a membros de instância, devem primeiro criar ou receber uma referência a um objeto.
  * **Não podem utilizar as palavras-chave `this` ou `super`**, dado que estas representam, respetivamente, a instância corrente e a superclasse da instância, conceitos inexistentes no escopo estático da classe.

---

### 📌 P5 — A Palavra Reservada `final` em Java (Classes, Métodos e Variáveis)
**Frequência:** Média-Alta | **Aula Relacionada:** Aula 08 (Classes Abstratas)

**Pergunta esperada:**
> Descreva o impacto e a finalidade da utilização da palavra reservada `final` quando aplicada a: (1) uma classe, (2) um método e (3) uma variável. Esclareça adicionalmente a diferença de comportamento de uma variável `final` quando esta armazena um tipo primitivo versus quando armazena um tipo de referência (objeto).

**Resposta Rápida:**
1. **Classe `final`:** Impede que a classe seja herdada/especializada. Garante a segurança e a imutabilidade da estrutura (exemplo: a classe `String` em Java é `final`).
2. **Método `final`:** Impede que o método seja sobreposto (*overridden*) nas subclasses. Assegura que o comportamento original do método não pode ser alterado por especializações.
3. **Variável `final`:** Transforma a variável numa constante. Após a atribuição do primeiro valor (inicialização), este não pode ser modificado ou reatribuído.
* **Primitivos vs Referências:**
  * **Tipo Primitivo:** O valor em si é constante e inalterável (ex: `final int X = 10;` - o valor de `X` será sempre 10).
  * **Tipo de Referência (Objeto):** O **endereço de memória** contido na variável é constante (não pode ser reatribuído para apontar para outro objeto na Heap). Contudo, **o estado interno do objeto pode ser modificado** (ex: é possível alterar atributos ou adicionar elementos num array declarado como `final`, desde que o objeto em si seja mutável).

---

### 📌 P6 — Composição vs Herança
**Frequência:** Média | **Aula Relacionada:** Aula 06 (Associação, Composição e Agregação)

**Pergunta esperada:**
> No desenho de sistemas orientados a objetos, a reutilização de código pode ser conseguida através de Herança ou de Composição. Distinga detalhadamente estas duas abordagens, explicando as relações lógicas que representam (ex: "É Um" vs "Tem Um") e discuta por que razão muitos autores recomendam preferir a composição em detrimento da herança (problema do forte acoplamento).

**Resposta Rápida:**
* **Herança (Reutilização "White-box"):**
  * Representa uma relação lógica de tipo **"É UM"** (*is-a*). Ex: Um `Camiao` *é um* `Veiculo`.
  * **Características:** A subclasse herda diretamente o estado e o comportamento da superclasse. É uma forma rápida de especializar comportamento.
  * **Desvantagem:** Cria um acoplamento extremamente forte. Se a superclasse sofrer alterações nos seus atributos ou comportamento, isso pode quebrar o funcionamento das subclasses (problema da superclasse frágil). Quebra o encapsulamento, uma vez que as subclasses dependem dos detalhes da classe pai.
* **Composição (Reutilização "Black-box"):**
  * Representa uma relação lógica de tipo **"TEM UM"** ou **"FAZ PARTE DE"** (*has-a* / *part-of*). Ex: Um `Veiculo` *tem um* `Motor`.
  * **Características:** Uma classe obtém novas funcionalidades contendo instâncias de outras classes como seus atributos, delegando-lhes as chamadas de métodos.
  * **Vantagens:** Acoplamento fraco. Os detalhes internos de cada classe são mantidos encapsulados. O comportamento pode ser alterado dinamicamente em tempo de execução (por exemplo, alterando o motor associado ao veículo por polimorfismo). Facilita a realização de testes unitários recorrendo a objetos simulados (*mocks*).

---

### 🔥 PRIORIDADE MODERADA (Mecanismos de I/O, enums e tipos estruturados)

---

### 📌 P7 — Tipos Enumerados (Enum) em Java
**Frequência:** Média | **Aula Relacionada:** Aula 05 (Enumerações)

**Pergunta esperada:**
> O que são enums em Java? Identifique três vantagens da sua utilização face ao uso tradicional de constantes inteiras (`int`) ou de texto (`String`). Explique de forma sumária se e como um enum em Java pode conter construtores e atributos específicos.

**Resposta Rápida:**
* **Enum:** Tipo de dados especial em Java que define um conjunto fixo e predefinido de constantes (ex: dias da semana, estados de um veículo). Cada constante é implicitamente uma instância única (singleton) do tipo enum.
* **Vantagens:**
  1. **Segurança de tipos (*Type Safety*):** O compilador garante que apenas valores definidos no enum podem ser atribuídos à variável, evitando valores inválidos e erros de digitação comuns com Strings.
  2. **Legibilidade e Manutenção:** Facilita a leitura do código e centraliza os valores válidos num só local.
  3. **Suporte Avançado:** Podem ser usados nativamente em blocos `switch`, coleções especializadas (`EnumSet`, `EnumMap`) e comparados de forma eficiente e nula-segura com o operador `==`.
* **Construtores e Atributos:**
  * Os enums em Java comportam-se como classes. Podem declarar atributos de instância (de preferência `final` para manter a imutabilidade) e métodos.
  * Podem possuir **construtores, que são obrigatoriamente privados** (`private`). O construtor é invocado implicitamente aquando da definição de cada constante do enum para inicializar os atributos associados (ex: `ENABLED("Ativo")`).

---

### 📌 P8 — Classes Wrapper, Autoboxing e Unboxing
**Frequência:** Média | **Aula Relacionada:** Aula 12 (Wrapper types)

**Pergunta esperada:**
> O que são classes Wrapper (de envolvimento) em Java e qual o seu propósito? Explique os conceitos de Autoboxing e Unboxing e discuta um problema comum de desempenho e um risco de comparação de valores (`==` vs `equals()`) associado a este mecanismo.

**Resposta Rápida:**
* **Classes Wrapper:** Classes que encapsulam/envolvem os tipos primitivos em objetos correspondentes (ex: `int` -> `Integer`, `double` -> `Double`). O seu propósito principal é permitir que os tipos primitivos sejam usados onde apenas objetos são permitidos (ex: em estruturas de coleções como `ArrayList<Integer>` ou no uso de Generics).
* **Autoboxing:** Conversão automática realizada pelo compilador de um tipo primitivo para o seu respetivo Wrapper (ex: atribuir um `int` a um `Integer`).
* **Unboxing:** Conversão automática inversa, extraindo o valor primitivo do interior do objeto Wrapper (ex: atribuir um `Integer` a uma variável `int`).
* **Riscos e Problemas:**
  * **Desempenho:** O autoboxing excessivo dentro de ciclos intensivos cria desnecessariamente milhares de instâncias na Heap, degradando o desempenho e aumentando a pressão sobre o Garbage Collector.
  * **Comparação com `==`:** O operador `==` compara a identidade de referências (endereços) e não os valores lógicos quando aplicado a Wrappers. Embora o Java faça cache de instâncias de `Integer` para pequenos valores (tipicamente entre -128 e 127), a comparação `==` com valores fora desta gama falhará mesmo que os números sejam matematicamente iguais. Deve-se **sempre usar o método `equals()`** para comparar o valor de Wrappers.

---

### 📌 P9 — Serialização de Objetos em Java
**Frequência:** Média-Baixa | **Aula Relacionada:** Aula 12 (Serialização)

**Pergunta esperada:**
> O que é o mecanismo de Serialização de objetos em Java e qual a sua finalidade? Explique como se sinaliza que uma classe é elegível para serialização e descreva detalhadamente a finalidade do modificador `transient` e do identificador `serialVersionUID`.

**Resposta Rápida:**
* **Serialização:** Processo de conversão do estado de um objeto (dados na Heap) numa sequência de bytes, permitindo que o objeto seja gravado em suporte persistente (disco) ou transmitido através de uma rede de computadores. O processo inverso chama-se Deserialização.
* **Elegibilidade:** A classe deve implementar a interface marcadora (sem métodos) **`java.io.Serializable`**.
* **Modificador `transient`:** Aplicado a atributos de uma classe que **não devem ser serializados** (omitidos do fluxo de bytes). Exemplos de uso: dados sensíveis (passwords), dados derivados temporários (que podem ser recalculados) ou referências a objetos que não implementam `Serializable` (evitando assim exceções de serialização).
* **`serialVersionUID`:** É um identificador numérico único (gerado estaticamente) usado durante a deserialização para verificar se o emissor e o recetor de um objeto serializado possuem versões compatíveis da mesma classe. Se houver uma alteração na classe sem atualização do `serialVersionUID`, a JVM lança uma exceção `InvalidClassException`, prevenindo a corrupção de dados na memória.

---

### 📌 P10 — Input/Output (I/O) em Java: Byte Streams vs Character Streams
**Frequência:** Média-Baixa | **Aula Relacionada:** Aula 12 (Input/Output)

**Pergunta esperada:**
> A biblioteca de Input/Output (I/O) do Java divide-se principalmente em classes orientadas a bytes (Streams) e classes orientadas a caracteres (Readers/Writers). Explique a diferença fundamental entre estas duas famílias de classes e indique em que cenários práticos é adequada a utilização de cada uma delas.

**Resposta Rápida:**
* **Byte Streams (Streams de Bytes):**
  * **Definição:** Operam diretamente com bytes individuais (dados de 8 bits). As classes base são as abstratas `InputStream` e `OutputStream` (e as suas subclasses como `FileInputStream`, `FileOutputStream`).
  * **Cenário de Uso:** Ideais para manipulação de ficheiros e dados em bruto/binários, como ficheiros de imagem (.png, .jpg), ficheiros de áudio/vídeo, ficheiros compactados (.zip), ou transmissão de pacotes de dados pela rede.
* **Character Streams (Streams de Caracteres):**
  * **Definição:** Operam com caracteres Unicode (16 bits). As classes base são as abstratas `Reader` e `Writer` (e subclasses como `FileReader`, `FileWriter`). Efetuam a tradução automática entre o formato binário de bytes no disco físico e a codificação de caracteres do sistema (ex: UTF-8).
  * **Cenário de Uso:** Apropriados para qualquer processamento de texto legível (ficheiros de texto .txt, documentos formatados em .xml ou .json), pois gerem as codificações e os caracteres especiais/acentuação de forma segura e transparente.

---

## 📊 Matriz de Probabilidade para o Exame de Recurso

| Prioridade | Pergunta | Tema |
|:----------:|:--------:|------|
| 🔥🔥🔥 | P1 | Tratamento de Exceções: Checked vs Unchecked e try-catch-finally |
| 🔥🔥🔥 | P2 | Modificadores de Acesso (`private`, `protected`, `public`, default) e Encapsulamento |
| 🔥🔥🔥 | P3 | Polimorfismo: Sobrecarga (*Overloading*) vs Sobreposição (*Overriding*) |
| 🔥🔥 | P4 | Membros Estáticos (`static`): Variáveis e Métodos |
| 🔥🔥 | P5 | Modificador `final` (Variáveis, Métodos, Classes e Primitivos vs Referências) |
| 🔥🔥 | P6 | Composição vs Herança: Relações "É Um" / "Tem Um" e acoplamento |
| 🔥 | P7 | Tipos Enumerados (Enum): Estrutura, construtores e vantagens |
| 🔥 | P8 | Classes Wrapper, Autoboxing/Unboxing e Comparação |
| 🔥 | P9 | Serialização: `Serializable`, `transient` e `serialVersionUID` |
| 🔥 | P10 | Input/Output: Byte Streams vs Character Streams |

---

> 💡 **Dica de Estudo:** As perguntas teóricas de PP costumam valer cerca de **6 a 7 valores** do total da nota de exame (normalmente divididas em 4 ou 5 alíneas de 1,5 valores cada). Como o exame de PP contém uma parte prática pesada de desenvolvimento baseada no trabalho prático da época normal, dominar a teoria de exceções (Checked vs Unchecked) e os conceitos fundamentais de polimorfismo e herança é vital para passar sem sobressaltos!
