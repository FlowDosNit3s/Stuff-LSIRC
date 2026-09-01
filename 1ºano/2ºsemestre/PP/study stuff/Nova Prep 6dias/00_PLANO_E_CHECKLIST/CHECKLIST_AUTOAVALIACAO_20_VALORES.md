# 📋 CHECKLIST DE AUTOAVALIAÇÃO PARA 20 VALORES — ÉPOCA ESPECIAL PP

> **Instruções:** Utilize esta checklist para testar os seus conhecimentos de 0% a 100%. À medida que for capaz de explicar um conceito teórico sem consultar ou resolver um exercício prático sozinho, marque a caixa correspondente com `[x]`.

---

## 🎯 TEORIA — PARTE 1 (6,0 VALORES)

### 📘 Módulo 1: Fundamentos & POO Core (Dia 1)

#### Teoria
- [ ] Sei explicar o princípio do **Encapsulamento** e por que razão ter atributos `public` é uma má prática.
- [ ] Sei ordenar os 4 modificadores de acesso (`private`, default, `protected`, `public`) do mais restritivo ao mais permissivo.
- [ ] Sei explicar a diferença entre **Classes Abstratas** e **Interfaces** (herança, estado, construtores e métodos).
- [ ] Sei quando devo escolher uma Classe Abstrata vs quando devo preferir uma Interface.
- [ ] Sei o impacto do modificador `static` em atributos e métodos e conheço as restrições quanto a `this` e `super`.
- [ ] Sei o impacto de `final` em variáveis (primitivas vs referências), métodos e classes.
- [ ] Sei a diferença entre identidade (`==`) e igualdade lógica (`equals()`).
- [ ] Sei como redefinir corretamente os métodos `equals()`, `hashCode()` e `toString()`.
- [ ] Sei explicar por que razão a passagem de argumentos em Java é **estritamente por valor** (*pass-by-value*).
- [ ] Sei o que acontece na Heap quando um método reatribui a referência de um objeto vs quando altera o seu estado interno.

#### Treino Prático (Exercícios para Hoje)
- [ ] **Exercício Prático 1:** Criar uma Interface `Vehicle` e uma Classe Abstrata `BaseVehicle` com atributos encapsulados (demonstrar encapsulamento, getters/setters e construtores).
- [ ] **Exercício Prático 2:** Criar uma classe concreta (ex: `Truck`) que estende `BaseVehicle` e usar `super()` no construtor.
- [ ] **Exercício Prático 3:** Instanciar dois objetos `Truck` com os mesmos dados, e redefinir `.equals()` e `.hashCode()` para que sejam logicamente iguais (`obj1.equals(obj2)` deve retornar `true`).
- [ ] **Exercício Prático 4:** Criar um método estático (`static`) numa classe utilitária e tentar usar `this` (para ver o erro de compilação) e corrigir.

### 📙 Módulo 2: Tópicos Avançados, Polimorfismo, Exceções e I/O (Dia 2)

#### Teoria
- [ ] Sei a diferença entre **Sobrecarga (*Overloading*)** e **Sobreposição (*Overriding*)**.
- [ ] Sei em que momento (compilação vs execução) a JVM decide qual o método a invocar (*Dynamic Binding*).
- [ ] Sei a diferença entre **Upcasting** e **Downcasting** e o risco da exceção `ClassCastException`.
- [ ] Sei usar o operador `instanceof` para garantir a segurança no downcasting.
- [ ] Sei a diferença entre **Composição** ("TEM UM") e **Herança** ("É UM") e sei por que a composição reduz o acoplamento.
- [ ] Sei distinguir **Checked Exceptions** de **Unchecked Exceptions (`RuntimeException`)**.
- [ ] Sei a atitude do compilador perante Checked vs Unchecked Exceptions.
- [ ] Sei como funciona o fluxo do bloco `try-catch-finally` e sei que o `finally` executa **sempre** (mesmo com `return`).
- [ ] Sei declarar e estruturar um `enum` com construtor privado, atributos e métodos.
- [ ] Sei a diferença entre tipos primitivos e **Classes Wrapper** (`Integer`, `Double`) e a armadilha do `==` fora da cache (-128 a 127).
- [ ] Sei distinguir **Byte Streams** (`InputStream`/`OutputStream`) de **Character Streams** (`Reader`/`Writer`).
- [ ] Sei o papel de `Serializable`, `transient` e `serialVersionUID` na serialização de objetos.

#### Treino Prático
- [ ] **Exercício Prático 1:** Criar um array `Vehicle[]` e fazer *upcasting* inserindo objetos `Truck` e `Van`. Iterar o array invocando um método polimórfico (ex: `calcularCapacidade()`).
- [ ] **Exercício Prático 2:** Fazer *downcasting* seguro iterando o array anterior, usando `instanceof` antes de fazer o cast para `Truck` e chamar um método exclusivo dessa classe.
- [ ] **Exercício Prático 3:** Criar uma *Checked Exception* (ex: `InvalidCapacityException`) e um método que a lance. Usar um bloco `try-catch-finally` na main para a capturar.
- [ ] **Exercício Prático 4:** Implementar `Serializable` numa classe, marcar um atributo como `transient` e gravar o objeto num ficheiro `.dat` e voltar a ler (I/O).

---

## 💻 PRÁTICA — PARTE 2 (14,0 VALORES)

### 📗 Módulo 3: Algoritmia em Arrays Nativos sem Collections (Dia 3)

#### Teoria e Algoritmos
- [ ] Sei desenhar e explicar o modelo de domínio do TP (Ajuda Humanitária: `IInstitution`, `AidBox`, `Container`, `Vehicle`, `RefrigeratedVehicle`, `Route`, `Strategy`).
- [ ] Sei implementar o algoritmo de **Redimensionamento Dinâmico** de um array nativo quando a capacidade se esgota.
- [ ] Sei implementar o algoritmo de **Compactação de Array** (remover elementos `null` e devolver array com dimensão exata).
- [ ] Sei filtrar um array de objetos com base em múltiplos critérios (ex: tipo de item + medição > 80%).
- [ ] Sei a diferença entre **Deep Copy** e **Shallow Copy** de arrays e sei como evitar fugas de encapsulamento.
- [ ] Sei remover um elemento de array por índice e reorganizar as posições restantes (fazer *shift* para a esquerda).
- [ ] Sei ordenar um array nativo de objetos por um atributo numérico (ex: `Bubble Sort` por capacidade).

#### Treino Prático Aplicado
- [ ] **Exercício Prático 1:** Implementar a lógica de adição de um novo contentor a um array estático, fazendo o redimensionamento dinâmico automático (`resize`).
- [ ] **Exercício Prático 2:** Implementar um método que recebe um array de objetos (alguns nulos) e devolve um novo array sem posições nulas (compactação).
- [ ] **Exercício Prático 3:** Fazer uma cópia profunda (*Deep Copy*) num método getter (ex: `getVehicles()`), devolvendo um clone do array original para proteger o encapsulamento.

### 📕 Módulo 4: Caderno de 12 Exercícios Práticos (Dia 4)
- [ ] **Exercício 1:** Implementei a classe `ContainerImpl` com medições e redimensionamento sem consultar a solução.
- [ ] **Exercício 2:** Redefini o método `equals()` e `toString()` em `AidBoxImpl` garantindo a verificação de nulos e `instanceof`.
- [ ] **Exercício 3:** Implementei o método `getUrgentAidBoxes` filtrando caixas urgentes e compactando o array final.
- [ ] **Exercício 4:** Implementei o cálculo de ocupação média de uma rota tratando divisões por zero e nulos.
- [ ] **Exercício 5:** Criei uma Checked Exception personalizada (`ContainerCapacityExceededException`) e lancei-a com validação.
- [ ] **Exercício 6:** Implementei o método `generate` na classe `StrategyImpl` gerando rotas não-vazias sem nulos.
- [ ] **Exercício 7:** Escrevi um método `main` completo para testar classes do domínio com `try-catch`.

---

## 📓 MARATONA DE EXAMES & SIMULAÇÃO (DIAS 5 E 6)

### 📓 Módulo 5: Exames Modelo e Época Normal (Dia 5)
- [ ] Resolvi o **Exame Modelo 1** e validei com a resolução em `SOLUCOES_DIA_5_EXAMES_MODELO_E_EPOCA_NORMAL.md`.
- [ ] Resolvi o **Exame Modelo 2** e validei a resolução.
- [ ] Resolvi o **Exame Modelo 3** e validei a resolução.
- [ ] Resolvi o **Exame Modelo 4** e validei a resolução.
- [ ] Resolvi o **Exame Modelo 5** e validei a resolução.
- [ ] Resolvi integralmente o **Exame da Época Normal 2025/2026** e comparei com a solução de 20 valores.

### 📔 Módulo 6: Exame Simulado Inédito de Época Especial (Dia 6)
- [ ] Realizei o **Exame Simulado de Época Especial** em 2 horas cronometradas sem consultar resoluções.
- [ ] Validei a Parte 1 do Exame Simulado com o ficheiro `SOLUCOES_DIA_6_EXAME_SIMULADO.md`.
- [ ] Validei a Parte 2 do Exame Simulado com o ficheiro `SOLUCOES_DIA_6_EXAME_SIMULADO.md`.
- [ ] Memorizei a sintaxe dos templates do `CHEATSHEET_CODIGO_E_SINTAXE_QUICK_REF.md`.
