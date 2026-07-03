# 🎯 Perguntas Teóricas Possíveis — Recurso BD 2025/2026

> **Premissa:** O professor **NÃO repete** as perguntas da Época Normal no Recurso.
> Este ficheiro exclui todas as perguntas que saíram no Exame Teórico da Época Normal 2025/2026.

---

## ❌ Perguntas JÁ ELIMINADAS (Saíram na Época Normal 2025/2026)

| # | Tema | Pergunta EN 25/26 |
|---|------|--------------------|
| 1 | Definição de Termos | Defina: BD, SGBD (componentes), Metadados |
| 2 | LDD vs LMD | Diferenças entre LDD e LMD + operações |
| 3 | Vistas vs Relações Base | O que é uma vista + diferenças com relação base |
| 4 | Funções Agregação + NULLs | Restrições das funções de agregação + efeito dos NULLs |
| 5 | Mecanismo de Resolução de Vistas | Como funciona o mecanismo de resolução de vistas |
| 6 | Técnicas de Descoberta de Factos | Propósito e descrição de cada técnica |

> ⚠️ **Nota:** A Pergunta 7 (Normalização de Fatura) e a Pergunta 8 (Modelação + SQL + Álgebra) saem **SEMPRE**, mas com enunciados diferentes (documento/fatura e cenário novos).

---

## ✅ PERGUNTAS COM MAIOR PROBABILIDADE DE SAIR NO RECURSO

### 🔥🔥🔥 PRIORIDADE MÁXIMA (Frequência Altíssima + Não saíram na EN 25/26)

---

### 📌 P1 — Integridade Referencial + ON DELETE / ON UPDATE
**Frequência:** 8+ exames | **Aparece nos Modelos de Recurso:** ✅ Modelo 1

**Pergunta esperada:**
> Explique o que entende por Integridade Referencial. Identifique e descreva quais as ações que se podem utilizar nas subcláusulas ON DELETE e ON UPDATE.

**Resposta Rápida:**
A integridade referencial é uma regra do modelo relacional que garante a consistência lógica entre tabelas, obrigando a que os valores de uma chave estrangeira (FK) na tabela filha existam previamente na chave primária (PK) da tabela pai ou sejam nulos. Para gerir alterações, as ações disponíveis são:
- **CASCADE** — propaga a eliminação ou atualização do registo pai diretamente para os registos filhos.
- **SET NULL** — define a FK dos filhos como nula (caso a coluna o permita).
- **SET DEFAULT** — altera a FK dos filhos para o valor padrão configurado.
- **NO ACTION / RESTRICT** — rejeita a operação no registo pai caso existam registos filhos dependentes (comportamento por defeito).

---

### 📌 P2 — Normalização: Objetivos e Impacto no Desempenho
**Frequência:** 8+ exames | **Aparece nos Modelos de Recurso:** ✅ Modelo 2

**Pergunta esperada:**
> No contexto do modelo relacional, quais os objetivos da normalização de dados? De que forma o processo de normalização poderá afetar, posteriormente, o desempenho da implementação?

**Resposta Rápida:**
Os objetivos são: minimizar a redundância de dados, eliminar anomalias de atualização (inserção, remoção e modificação) e garantir a consistência/integridade lógica das relações. O impacto no desempenho é misto:
- **Leitura (OLAP):** Prejudicada — a fragmentação exige mais JOINs, aumentando acessos ao disco.
- **Escrita (OLTP):** Otimizada — tabelas mais estreitas, sem dados duplicados, escrita rápida num único local.

**Definições das Formas Normais:**
- **FNN:** Contém grupos repetidos.
- **1FN:** Valores atómicos (sem grupos repetidos).
- **2FN:** 1FN + sem dependências parciais (todos os atributos não primos dependem totalmente da PK).
- **3FN:** 2FN + sem dependências transitivas.
- **BCNF:** 3FN + todo determinante é chave candidata.

---

### 📌 P3 — Anomalias de Atualização
**Frequência:** 6+ exames | **Aparece nos Modelos de Recurso:** ✅ Modelo 3

**Pergunta esperada:**
> Descreva os tipos de anomalias de atualização que podem ocorrer numa relação com dados redundantes. Dê exemplos.

**Resposta Rápida:**
- **Anomalia de Inserção:** Impossível registar dados sem outra informação independente. *Ex:* Não se pode inserir uma disciplina sem ter alunos matriculados.
- **Anomalia de Remoção:** A eliminação de um registo provoca perda involuntária de dados distintos. *Ex:* Apagar o único aluno inscrito perde os dados da disciplina.
- **Anomalia de Modificação:** Alterar dados redundantes em apenas alguns registos cria inconsistências. *Ex:* Mudar o nome do professor em 50 de 100 registos gera contradições.

---

### 📌 P4 — Triggers de Bases de Dados
**Frequência:** 4+ exames | **Aparece nos Modelos de Recurso:** ✅ Modelo 1

**Pergunta esperada:**
> O que são triggers e para que servem? Quais as vantagens e desvantagens? Identifique os tipos quanto ao momento de execução.

**Resposta Rápida:**
Um trigger é um bloco de código procedural executado automaticamente pelo SGBD em resposta a operações DML (INSERT, UPDATE, DELETE). Serve para impor regras de negócio complexas, manter dados derivados e criar logs de auditoria.
- **Vantagens:** Centralização lógica na BD, automatização, reforço de integridade independente da aplicação.
- **Desvantagens:** Overhead de processamento, dificuldade de depuração (disparo implícito), falta de portabilidade.
- **Tipos:** BEFORE (antes da operação), AFTER (depois da operação), INSTEAD OF (em vez da operação — usado em vistas).

---

### 🔥🔥 PRIORIDADE ALTA (Frequência Moderada-Alta + Não saíram na EN 25/26)

---

### 📌 P5 — Sistemas de BD vs Ficheiros + Vantagens/Desvantagens SGBD
**Frequência:** 7+ exames | **Aparece nos Modelos de Recurso:** ✅ Modelo 3

**Pergunta esperada:**
> Descreva as principais características de um Sistema BD e compare com os Sistemas de Ficheiros. Enuncie as vantagens e desvantagens de um SGBD.

**Resposta Rápida:**
Os sistemas de BD centralizam os dados com acesso intermediado pelo SGBD, enquanto os ficheiros são descentralizados com dados ligados ao código. Diferenças: independência de dados (BD tem, ficheiros não), controlo de redundância (BD centraliza, ficheiros duplicam), concorrência e segurança (BD tem gestão robusta, ficheiros limitados).
- **Vantagens SGBD:** Controlo de redundância, partilha concorrente, integridade, segurança, backup/recuperação.
- **Desvantagens SGBD:** Complexidade de administração, custo elevado, maior impacto em caso de falha geral.
- **Quando usar Ficheiros:** Aplicações pessoais, baixo volume, utilizador único, recursos limitados.

---

### 📌 P6 — Arquitetura ANSI/SPARC (Nível Conceptual)
**Frequência:** 5+ exames | **Aparece nos Modelos de Recurso:** ✅ Modelo 2

**Pergunta esperada:**
> A arquitetura ANSI/SPARC identifica três níveis nos SGBD. Descreva pormenorizadamente o nível intermédio.

**Resposta Rápida:**
O nível intermédio é o **Nível Conceptual**. Representa a visão lógica e global de toda a base de dados para a organização. Define a totalidade das tabelas, colunas, relacionamentos, regras de segurança e restrições de integridade (PK, FK). O seu propósito é abstrair os utilizadores dos detalhes de armazenamento físico, garantindo a independência lógica e física dos dados.

**Variante mais completa (Modelo 3):** Pode pedir para descrever os **três tipos de esquema** (Externo, Conceptual, Interno):
- **Esquema Externo:** Visão personalizada de cada utilizador/grupo.
- **Esquema Conceptual:** Estrutura lógica global (tabelas, atributos, relacionamentos, restrições).
- **Esquema Interno:** Organização física no disco (índices, ficheiros, blocos de memória).

---

### 📌 P7 — Independência de Dados
**Frequência:** 5+ exames | **Aparece nos Modelos de Recurso:** ✅ Modelo 1

**Pergunta esperada:**
> Descreva o conceito de independência de dados e a sua importância. Diferencie entre independência física e lógica.

**Resposta Rápida:**
Capacidade de alterar esquemas num nível inferior de abstração sem reescrever os níveis superiores.
- **Independência Física:** Alterar armazenamento físico (índices, discos, partições) sem afetar o esquema lógico ou aplicações.
- **Independência Lógica:** Alterar o esquema conceptual (adicionar colunas, dividir tabelas) sem reescrever o código SQL das aplicações. Recorre-se a vistas para simular as tabelas originais.
- **Importância:** Reduz custos de manutenção e permite evolução flexível da BD.

---

### 📌 P8 — Data Warehouses: Benefícios e Problemas
**Frequência:** 4+ exames | **Aparece nos Modelos de Recurso:** ✅ Modelo 1

**Pergunta esperada:**
> Descreva os benefícios e problemas dos Data Warehouses. Distinga entre Data Warehouse e Data Mart.

**Resposta Rápida:**
Um DW é um repositório histórico, integrado e não-volátil para apoio à decisão.
- **Benefícios:** Integração de dados de fontes heterogéneas, análise histórica de tendências, isolamento de performance (OLAP não degrada OLTP).
- **Problemas:** Custo/tempo elevados, complexidade de ETL, manutenção constante face a alterações nas fontes.
- **Data Mart:** Subconjunto do DW focado num departamento — mais simples e barato de implementar.

---

### 🔥 PRIORIDADE MODERADA (Menos frequentes mas presentes nos Modelos de Recurso)

---

### 📌 P9 — LMD Procedimentais vs Não-Procedimentais
**Frequência:** 2+ exames | **Aparece nos Modelos de Recurso:** ✅ Modelo 2

**Pergunta esperada:**
> Explique as diferenças entre LMD procedimentais e não-procedimentais. Dê exemplos.

**Resposta Rápida:**
- **Procedimentais:** Especificam **como** obter os dados, com algoritmo passo a passo, registo a registo (*one-record-at-a-time*). *Exemplos:* Álgebra Relacional, cursores em PL/SQL e T-SQL.
- **Não-Procedimentais (Declarativas):** Especificam **o que** obter, em conjunto (*set-at-a-time*), com o otimizador a definir o plano. *Exemplos:* SELECT em SQL, Cálculo Relacional.

---

### 📌 P10 — Subquery vs Junção
**Frequência:** 3+ exames | **Aparece nos Modelos de Recurso:** ✅ Modelo 1

**Pergunta esperada:**
> Qual a diferença entre uma subquery e uma junção? Em que situações não é possível usar uma subquery?

**Resposta Rápida:**
Uma subquery é um SELECT aninhado dentro de outra consulta (WHERE, HAVING, FROM, SELECT); uma junção combina registos de múltiplas tabelas na mesma linha. Não é possível usar subquery quando se quer exibir colunas de tabelas distintas simultaneamente no resultado — para isso é necessário JOIN.

---

### 📌 P11 — Arquitetura Cliente-Servidor (2 vs 3 Níveis)
**Frequência:** 3+ exames | **Aparece nos Modelos de Recurso:** ✅ Modelo 2

**Pergunta esperada:**
> Compare a arquitetura cliente-servidor de 2 e 3 níveis. Qual a mais adequada para a Web?

**Resposta Rápida:**
- **2-tier:** Cliente (fat client) comunica diretamente com o servidor de BD. Interface + regras de negócio no cliente.
- **3-tier:** Introduz servidor de aplicação intermédio. Cliente leve (browser), lógica no servidor de aplicação, dados no servidor de BD.
- **Para a Web:** 3-tier. Permite pooling de conexões para milhares de utilizadores, centraliza manutenção, previne acesso direto e inseguro à BD.

---

### 📌 P12 — Atributos no Modelo Entidade-Relacionamento
**Frequência:** 3+ exames | **Aparece nos Modelos de Recurso:** ✅ Modelo 2

**Pergunta esperada:**
> Descreva os atributos num diagrama ER. Dê exemplos de simples, compostos, multi-valor e derivados.

**Resposta Rápida:**
Propriedades que descrevem entidades/relacionamentos:
- **Simples:** Atómico, indivisível (NIF). Elipse simples.
- **Composto:** Decomponível (Morada → Rua + Localidade + CP). Elipses interligadas.
- **Multi-valor:** Múltiplos valores (Telefones). Elipse de contorno duplo.
- **Derivado:** Calculado (Idade a partir da Data de Nascimento). Elipse tracejada.

---

### 📌 P13 — Cursores SQL
**Frequência:** 2+ exames | **Aparece nos Modelos de Recurso:** ✅ Modelo 2

**Pergunta esperada:**
> O que são cursores SQL? Qual o propósito? Descreva o ciclo de vida.

**Resposta Rápida:**
Estrutura de controlo que funciona como apontador para processar registos linha a linha, contrariando a natureza declarativa do SQL.
- **Ciclo de vida:** `DECLARE` (define a query) → `OPEN` (executa e aloca recursos) → `FETCH` (lê linha corrente) → `CLOSE` (fecha e liberta locks) → `DEALLOCATE` (remove da memória).

---

### ⭐ PRIORIDADE SECUNDÁRIA (Menos frequentes, mas presentes em Modelos de Recurso específicos)

---

### 📌 P14 — Materialização de Vistas (Indexed/Materialized Views)
**Aparece nos Modelos de Recurso:** ✅ Modelo 1

**Pergunta esperada:**
> Explique o conceito de materialização de vistas. Vantagens e desvantagens vs vistas tradicionais? Em que contextos é recomendável?

**Resposta Rápida:**
Consiste em armazenar fisicamente os resultados da consulta da vista numa tabela em disco (Indexed Views).
- **Vantagens:** Acelera exponencialmente leituras de consultas complexas ou agregados pesados.
- **Desvantagens:** Overhead nas escritas das tabelas base (SGBD recalcula a vista para manter sincronização).
- **Contextos:** Ambientes OLAP com muitas leituras e poucas escritas, dashboards com agregados pesados.

---

### 📌 P15 — Operações de Junção (Theta, Equi, Natural, Outer, Semi)
**Aparece nos Modelos de Recurso:** ✅ Modelo 4

**Pergunta esperada:**
> Descreva as diferenças entre Theta Join, Equijoin, Natural Join, Outer Join e Semijoin.

**Resposta Rápida:**
- **Theta Join:** Condição com qualquer operador de comparação (=, >, <, ≥, ≤, ≠).
- **Equijoin:** Theta Join com apenas igualdade (=). Mantém colunas duplicadas.
- **Natural Join:** Igualdade em atributos homónimos. Remove colunas duplicadas automaticamente.
- **Outer Join:** Mantém registos sem correspondência (LEFT, RIGHT, FULL), preenchendo com NULL.
- **Semijoin:** Devolve apenas tuplos da 1ª tabela com correspondência na 2ª, sem expor atributos da 2ª.

---

### 📌 P16 — Stored Procedures vs Funções (UDF)
**Aparece nos Modelos de Recurso:** ✅ Modelo 4

**Pergunta esperada:**
> Diferença entre Stored Procedure e User-Defined Function? Três diferenças fundamentais.

**Resposta Rápida:**
- **Função (UDF):** Obrigatoriamente devolve valor via RETURN; pode ser usada dentro de SELECT/WHERE; **não pode** alterar dados (INSERT/UPDATE/DELETE).
- **Procedimento (SP):** Não é obrigado a devolver valor; invocado com EXEC/CALL (não dentro de SELECT); **pode** alterar dados e gerir transações (COMMIT/ROLLBACK).

---

### 📌 P17 — Sublinguagens de Dados (DDL, DML, DCL, TCL)
**Aparece nos Modelos de Recurso:** ✅ Modelo 3

**Pergunta esperada:**
> O que são sublinguagens de dados? Identifique e descreva DDL, DML, DCL, TCL com exemplos.

**Resposta Rápida:**
- **DDL (Data Definition Language):** Define a estrutura. `CREATE`, `ALTER`, `DROP`, `TRUNCATE`.
- **DML (Data Manipulation Language):** Manipula os dados. `SELECT`, `INSERT`, `UPDATE`, `DELETE`.
- **DCL (Data Control Language):** Controla acessos e privilégios. `GRANT`, `REVOKE`.
- **TCL (Transaction Control Language):** Gere transações. `COMMIT`, `ROLLBACK`, `SAVEPOINT`.

---

### 📌 P18 — 5 Operações Básicas da Álgebra Relacional
**Aparece nos Modelos de Recurso:** ✅ Modelo 3

**Pergunta esperada:**
> Defina as cinco operações básicas da Álgebra Relacional. Demonstre como Junção e Interseção são derivadas.

**Resposta Rápida:**
As 5 operações básicas:
1. **Seleção (σ):** Seleciona tuplos que satisfazem uma condição.
2. **Projeção (π):** Projeta as colunas solicitadas.
3. **Produto Cartesiano (×):** Combina todos os tuplos de duas relações.
4. **União (∪):** Une duas tabelas compatíveis.
5. **Diferença (−):** Tuplos de A que não estão em B.

Operações derivadas:
- **Junção:** A ⋈ B ≡ σ_condição(A × B)
- **Interseção:** A ∩ B ≡ A − (A − B)

---

### 📌 P19 — Componentes do Ambiente de um SGBD
**Aparece nos Modelos de Recurso:** ✅ Modelo 4

**Pergunta esperada:**
> Descreva os 5 componentes principais do ambiente de um SGBD.

**Resposta Rápida:**
1. **Hardware:** Dispositivos físicos (servidores, discos, redes).
2. **Software:** SGBD, sistema operativo e aplicações.
3. **Dados:** Dados armazenados e metadados (dicionário de dados).
4. **Utilizadores:** DBA, programadores, utilizadores finais.
5. **Procedimentos:** Regras e instruções de uso e funcionamento.

---

### 📌 P20 — Conceitos do Modelo Relacional
**Aparece nos Modelos de Recurso:** ✅ Modelo 4

**Pergunta esperada:**
> Explique: Relação, Atributo, Domínio, Tuplo, Grau e Cardinalidade.

**Resposta Rápida:**
- **Relação:** Tabela que armazena dados.
- **Atributo:** Coluna da tabela.
- **Domínio:** Conjunto de valores válidos para um atributo.
- **Tuplo:** Linha da tabela (um registo completo).
- **Grau:** Número de atributos (colunas).
- **Cardinalidade:** Número de tuplos (linhas).

---

### 📌 P21 — Vistas Atualizáveis
**Aparece nos Modelos de Recurso:** ✅ Modelo 4

**Pergunta esperada:**
> Quais as restrições para uma vista ser atualizável diretamente via DML?

**Resposta Rápida:**
Uma vista é atualizável se:
- Mapear **uma única tabela base** (sem JOINs).
- **Não** contiver funções de agregação (SUM, AVG, COUNT, etc.).
- **Não** contiver `GROUP BY`, `HAVING`, `DISTINCT` ou `UNION`.
- **Não** contiver subqueries correlacionadas no SELECT.
- Incluir todos os atributos NOT NULL da tabela base que não tenham valor DEFAULT.

---

### 📌 P22 — Especialização vs Generalização (Modelo ER)
**Aparece nos Modelos de Recurso:** ✅ Modelo 4

**Pergunta esperada:**
> Diferenças entre especialização e generalização no diagrama ER. Dê exemplos.

**Resposta Rápida:**
- **Especialização (Top-Down):** Dividir uma superclasse em subclasses específicas. *Ex:* Funcionário → Engenheiro + Motorista.
- **Generalização (Bottom-Up):** Agrupar entidades semelhantes numa superclasse. *Ex:* Aluno + Professor → Pessoa.

---

### 📌 P23 — Transações e Propriedades ACID
**Coberto nos resumos do professor**

**Pergunta esperada:**
> O que é uma transação? Descreva as propriedades ACID.

**Resposta Rápida:**
Uma transação é uma unidade lógica de processamento — conjunto de instruções SQL tratadas como indivisíveis.
- **Atomicidade:** Tudo ou nada (COMMIT total ou ROLLBACK total).
- **Consistência:** BD vai de um estado consistente a outro, sem violar restrições.
- **Isolamento:** Transações concorrentes não interferem entre si.
- **Durabilidade:** Após COMMIT, as alterações são permanentes mesmo com falha do sistema.

---

### 📌 P24 — Abordagens para Múltiplas Vistas de Utilizadores
**Frequência:** 3+ exames

**Pergunta esperada:**
> Enuncie as abordagens para desenho de BD com múltiplas vistas de utilizadores.

**Resposta Rápida:**
1. **Centralizada:** Recolher todos os requisitos e fundir numa lista global antes de modelar.
2. **Integração de Vistas:** Criar esquemas locais independentes por departamento e fundi-los depois.
3. **Mista:** Combina as duas — requisitos comuns fundidos no início; vistas complexas tratadas separadamente.

---

## 📊 Resumo: Probabilidade por Pergunta para o Recurso

| Prioridade | Pergunta | Tema |
|:----------:|:--------:|------|
| 🔥🔥🔥 | P1 | Integridade Referencial + ON DELETE/UPDATE |
| 🔥🔥🔥 | P2 | Normalização: Objetivos + Desempenho |
| 🔥🔥🔥 | P3 | Anomalias de Atualização |
| 🔥🔥🔥 | P4 | Triggers (Definição + Vantagens/Desvantagens) |
| 🔥🔥 | P5 | Sistemas BD vs Ficheiros + SGBD |
| 🔥🔥 | P6 | Arquitetura ANSI/SPARC (Nível Conceptual) |
| 🔥🔥 | P7 | Independência de Dados |
| 🔥🔥 | P8 | Data Warehouses |
| 🔥 | P9 | LMD Procedimentais vs Não-Procedimentais |
| 🔥 | P10 | Subquery vs Junção |
| 🔥 | P11 | Arquitetura Cliente-Servidor (2 vs 3) |
| 🔥 | P12 | Atributos no Modelo ER |
| 🔥 | P13 | Cursores SQL |
| ⭐ | P14 | Materialização de Vistas |
| ⭐ | P15 | Operações de Junção (5 tipos) |
| ⭐ | P16 | Stored Procedures vs Funções (UDF) |
| ⭐ | P17 | Sublinguagens (DDL, DML, DCL, TCL) |
| ⭐ | P18 | 5 Operações Básicas de Álgebra Relacional |
| ⭐ | P19 | Componentes do Ambiente SGBD |
| ⭐ | P20 | Conceitos do Modelo Relacional |
| ⭐ | P21 | Vistas Atualizáveis |
| ⭐ | P22 | Especialização vs Generalização (ER) |
| ⭐ | P23 | Transações e ACID |
| ⭐ | P24 | Abordagens Múltiplas Vistas |

---

> 💡 **Dica final:** O exercício de **normalização** (P7 no exame, vale 3 val.) e a **modelação com SQL + Álgebra Relacional** (P8, vale 5 val.) saem **SEMPRE** — mas com documentos e cenários diferentes. Pratica com os exames modelo de recurso!

> 📁 **Exames modelo de recurso disponíveis para praticar:**
> - [Modelo 1](./exames%20modelo/Exame_Modelo_Recurso_1_2025_2026.md) — TecnoShop + Companhia Aérea
> - [Modelo 2](./exames%20modelo/Exame_Modelo_Recurso_2_2025_2026.md) — AutoFlex Rent-a-Car + Ginásio
> - [Modelo 3](./exames%20modelo/Exame_Modelo_Recurso_3_2025_2026.md) — Grand Plaza Hotel + Reparação Eletrónica
> - [Modelo 4](./exames%20modelo/Exame_Modelo_Recurso_4_2025_2026.md) — Clínica Geral do Norte + Stock Peças
