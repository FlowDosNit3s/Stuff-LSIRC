# 📘 DIA 1 — Fundamentos de POO & Teoria Core (Parte 1 — 6,0 Valores)

> **Objetivo do Dia:** Dominar os pilares fundamentais da Programação Orientada a Objetos (POO) em Java e responder com nota máxima a perguntas teóricas de exame.
> 
> 🔒 **Nota:** As perguntas modelo deste ficheiro não incluem as resoluções diretas para permitir um treino autêntico. Consulte as soluções de 20 valores em [SOLUCOES_DIA_1_E_2_TEORIA.md](file:///c:/Users/diogo/Documents/GitHub/Stuff-LSIRC/1ºano/2ºsemestre/PP/study%20stuff/Nova%20Prep%205dias/SOLUCOES/SOLUCOES_DIA_1_E_2_TEORIA.md).

---

## 📌 REVISÃO TEÓRICA DE FUNDAMENTOS

### 1. Modificadores de Acesso & Encapsulamento
- **Encapsulamento:** Ocultar detalhes internos e expor métodos de acesso controlados.
- **4 Modificadores (do mais restritivo ao mais permissivo):**
  1. `private`: Apenas a própria classe.
  2. *(default / package-private)*: Apenas o mesmo pacote.
  3. `protected`: Mesmo pacote e subclasses noutros pacotes.
  4. `public`: Qualquer classe em qualquer pacote.

---

### 2. Classes Abstratas vs Interfaces em Java

| Característica | Classe Abstrata | Interface |
| :--- | :--- | :--- |
| **Herança** | `extends` 1 classe. | `implements` múltiplas interfaces. |
| **Atributos / Estado** | Atributos de instância mutáveis e construtores. | Apenas `public static final`. Sem construtores. |
| **Métodos** | Métodos abstratos e concretos. | Métodos abstratos, `default` e `static`. |
| **Semântica** | Relação "É UM". | Contrato de comportamento "É CAPAZ DE". |

---

### 3. Membros Estáticos (`static`) e O Modificador `final`
- **`static`:** Pertencem à classe. Métodos estáticos **não acedem a membros de instância** nem usam `this`/`super`.
- **`final`:**
  - Variável: Constante. Em tipos de referência, a referência é constante mas o objeto interno pode ser alterado.
  - Método: Impede sobreposição (`overriding`).
  - Classe: Impede herança.

---

### 4. Identidade vs Igualdade & Passagem de Argumentos
- **Identidade (`==`):** Mesma posição de memória Heap.
- **Igualdade (`equals()`):** Mesmo conteúdo lógico. Redefinir `equals()` exige redefinir `hashCode()`.
- **Passagem de Argumentos:** **SEMPRE por valor** (*pass-by-value*). Para objetos, copia-se o valor do endereço de memória.

---

## 🎯 10 PERGUNTAS TEÓRICAS MODELO (DIA 1)

1. **Pergunta 1:** Explique o princípio do encapsulamento em Java e ordene os quatro modificadores de acesso. Justifique a má prática de atributos `public`.
2. **Pergunta 2:** Diferencie detalhadamente classes abstratas de interfaces em Java. Em que cenários usar cada uma?
3. **Pergunta 3:** Explique a passagem de argumentos em Java para primitivos vs objetos. Ilustre com código.
4. **Pergunta 4:** Distinga identidade (`==`) de igualdade (`equals()`). Mostre um exemplo de redefinição de `equals()` e `toString()`.
5. **Pergunta 5:** Explique o significado de `static` em atributos e métodos e as suas restrições quanto a `this` e `super`.
6. **Pergunta 6:** Descreva o impacto do modificador `final` em variáveis (primitivos vs objetos), métodos e classes.
7. **Pergunta 7:** O que acontece se uma classe redefinir `equals()` mas não `hashCode()`?
8. **Pergunta 8:** Distinga variáveis locais de atributos de instância quanto à inicialização por defeito.
9. **Pergunta 9:** Explique a finalidade dos construtores e o comportamento do construtor por defeito.
10. **Pergunta 10:** O que é o operador `this` e quais os três contextos principais de utilização?

👉 *Resoluções de 20 valores disponíveis em:* [SOLUCOES_DIA_1_E_2_TEORIA.md](file:///c:/Users/diogo/Documents/GitHub/Stuff-LSIRC/1ºano/2ºsemestre/PP/study%20stuff/Nova%20Prep%205dias/SOLUCOES/SOLUCOES_DIA_1_E_2_TEORIA.md)
