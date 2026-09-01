# 📋 CHECKLIST DE AUTOAVALIAÇÃO PARA 20 VALORES — ÉPOCA ESPECIAL PP

> **Instruções:** Utilize esta checklist para testar os seus conhecimentos de 0% a 100%. À medida que for capaz de explicar um conceito teórico sem consultar ou resolver um exercício prático sozinho, marque a caixa correspondente com `[x]`.

---

## 🎯 TEORIA — PARTE 1 (6,0 VALORES)

### 📘 Módulo 1: Fundamentos & POO Core (Dia 1)
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

### 📙 Módulo 2: Tópicos Avançados, Polimorfismo, Exceções e I/O (Dia 2)
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

---

## 💻 PRÁTICA — PARTE 2 (14,0 VALORES)

### 📗 Módulo 3: Algoritmia em Arrays Nativos sem Collections (Dia 3)
- [ ] Sei desenhar e explicar o modelo de domínio do TP (Ajuda Humanitária: `IInstitution`, `AidBox`, `Container`, `Vehicle`, `RefrigeratedVehicle`, `Route`, `Strategy`).
- [ ] Sei implementar o algoritmo de **Redimensionamento Dinâmico** de um array nativo quando a capacidade se esgota.
- [ ] Sei implementar o algoritmo de **Compactação de Array** (remover elementos `null` e devolver array com dimensão exata).
- [ ] Sei filtrar um array de objetos com base em múltiplos critérios (ex: tipo de item + medição > 80%).
- [ ] Sei a diferença entre **Deep Copy** e **Shallow Copy** de arrays e sei como evitar fugas de encapsulamento.
- [ ] Sei remover um elemento de um array por índice e reorganizar as posições restantes.
- [ ] Sei ordenar um array nativo de objetos por um atributo numérico (ex: `Bubble Sort` por capacidade).

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
