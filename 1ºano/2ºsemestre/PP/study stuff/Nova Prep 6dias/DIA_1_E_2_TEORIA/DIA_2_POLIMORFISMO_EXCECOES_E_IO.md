# 📙 DIA 2 — Polimorfismo, Exceções e I/O (Parte 1 — 6,0 Valores)

> **Objetivo do Dia:** Dominar os tópicos teóricos avançados (Polimorfismo, Exceções, Enums, Wrappers e Input/Output).
> 
> 🔒 **Nota:** As resoluções completas destas 10 perguntas teóricas encontram-se no ficheiro [SOLUCOES_DIA_1_E_2_TEORIA.md](file:///c:/Users/diogo/Documents/GitHub/Stuff-LSIRC/1ºano/2ºsemestre/PP/study%20stuff/Nova%20Prep%205dias/SOLUCOES/SOLUCOES_DIA_1_E_2_TEORIA.md).

---

## 📌 REVISÃO TEÓRICA DE TÓPICOS AVANÇADOS

### 1. Polimorfismo: Overloading vs Overriding
- **Sobrecarga (*Overloading*):** Mesma classe, assinaturas diferentes; decisão em **tempo de compilação** (*static binding*).
- **Sobreposição (*Overriding*):** Subclasse redefine método herdado; decisão em **tempo de execução** (*dynamic binding*).

---

### 2. Casting e `instanceof`
- **Upcasting:** Conversão para superclasse (automático e seguro).
- **Downcasting:** Conversão para subclasse (explícito; risco de `ClassCastException`). Usar `instanceof`.

---

### 3. Composição vs Herança
- Herança ("É UM") gera acoplamento forte. Composição ("TEM UM") preserva encapsulamento e flexibilidade.

---

### 4. Tratamento de Exceções
- **Checked Exceptions:** Subclasses de `Exception`. Compilador **obriga** a tratar com `try-catch` ou declarar com `throws`.
- **Unchecked Exceptions:** Subclasses de `RuntimeException`. Erros de lógica; não exigem tratamento obrigatório.
- **`finally`:** Executa **sempre** (mesmo que haja `return` no `try` ou `catch`).

---

### 5. Enums, Wrappers e I/O
- **Enum:** Construtor privado (`private`), atributos e métodos.
- **Wrappers:** `Integer`, `Double`. Autoboxing/Unboxing. Usar `.equals()` para comparar.
- **I/O:** Byte Streams (`InputStream`/`OutputStream`) vs Character Streams (`Reader`/`Writer`).
- **Serialização:** `Serializable`, `transient`, `serialVersionUID`.

---

## 🎯 10 PERGUNTAS TEÓRICAS MODELO (DIA 2)

1. **Pergunta 1:** Distinga Checked Exceptions de Unchecked Exceptions. Indique o comportamento do compilador perante cada uma.
2. **Pergunta 2:** Explique a ordem de execução de `try-catch-finally`. O `finally` executa se houver um `return` no `try`?
3. **Pergunta 3:** Diferencie Sobrecarga (*Overloading*) de Sobreposição (*Overriding*). Quando ocorre a decisão de invocação?
4. **Pergunta 4:** Explique Upcasting e Downcasting. Quais os riscos do Downcasting e como usá-lo com `instanceof`?
5. **Pergunta 5:** Compare Herança e Composição. Por que razão a literatura recomenda preferir a composição?
6. **Pergunta 6:** Descreva a estrutura de um `enum` em Java. Qual o modificador obrigatório do construtor e porquê?
7. **Pergunta 7:** O que são Wrappers, Autoboxing e Unboxing? Apresente o risco de comparar Wrappers com `==`.
8. **Pergunta 8:** Distinga Byte Streams de Character Streams na biblioteca de I/O de Java.
9. **Pergunta 9:** Explique o mecanismo de Serialização de objetos, `Serializable`, `serialVersionUID` e `transient`.
10. **Pergunta 10:** Por que razão o Java obriga a tratar Checked Exceptions mas não Unchecked Exceptions?

👉 *Resoluções de 20 valores disponíveis em:* [SOLUCOES_DIA_1_E_2_TEORIA.md](file:///c:/Users/diogo/Documents/GitHub/Stuff-LSIRC/1ºano/2ºsemestre/PP/study%20stuff/Nova%20Prep%205dias/SOLUCOES/SOLUCOES_DIA_1_E_2_TEORIA.md)
