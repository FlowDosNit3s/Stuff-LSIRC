# 🔍 Verificação de Alinhamento — Material de Estudo vs Slides (PPS)

Este documento contém o relatório de verificação e correspondência entre os ficheiros de estudo gerados (**Guia_Estudo_Exame_BD.md** e **Exercicios_Exames_BD.md**) e o material pedagógico fornecido na pasta **PPS** (Aulas 1 a 11) e no repositório de perguntas (**BD-Todas-As-Perguntas.pdf**).

---

## 📋 Tabela de Correspondência: Slides vs Guia & Exercícios

A tabela seguinte detalha como cada tópico do guia e do caderno de exercícios se alinha com as aulas lecionadas (ficheiros PDF de slides na pasta `PPS`) e com as perguntas-chave da base de dados do exame.

| Tema no Guia / Exercícios | Slide (PPS) Correspondente | Conceitos Chave Alinhados com os Slides |
| :--- | :--- | :--- |
| **1. Conceitos Fundamentais** | **Aula 1** (Introdução) | Abordagem de ficheiros vs BD, DDL vs DML, LMD Procedimental vs Não-Procedimental. |
| **2. ANSI/SPARC e Independência** | **Aula 1** (Introdução) | Os 3 níveis (Externo, Conceptual, Interno), mapeamentos (mappings) e Independência de Dados Física/Lógica. |
| **3. Modelo Relacional** | **Aula 2** (Mod. Relacional) | Edgar Codd, terminologia (Relação, Atributo, Domínio, Tuplo, Grau, Cardinalidade) e as 3 regras de integridade. |
| **4. Álgebra Relacional** | **Aula 2 & 3** (Álgebra) | Seleção, Projeção, Junções (Theta, Natural, Outer), Divisão e Compatibilidade de União. |
| **5. SQL – LMD** | **Aula 4** (SQL-LMD) | SELECT (cláusulas, GROUP BY, HAVING vs WHERE), subqueries e predicados complexos. |
| **6. SQL – LDD** | **Aula 5** (SQL-LDD) | CREATE TABLE, ALTER, DROP, chaves e criação de Índices. |
| **7. Integridade Referencial** | **Aula 5** (SQL-LDD) | Ações de chaves estrangeiras (`ON DELETE/UPDATE` com `CASCADE`, `SET NULL`, `NO ACTION`). |
| **8. Vistas (Views)** | **Aula 5** (SQL-LDD) | CREATE/DROP VIEW, atualizabilidade de vistas e materialização. |
| **9. Triggers, SP e Funções** | **Aula 6** (SQL Avançado) | Extensões programáticas (T-SQL/PL-SQL), Triggers (`inserted`/`deleted`), SPs, Funções e Transações. |
| **10. Normalização** | **Aula 10** (Normalização) | Anomalias, dependências funcionais, Formas Normais (UNF, 1FN, 2FN, 3FN, FNBC). |
| **11. Modelação (Diagramas E/R)** | **Aula 7, 8 & 9** (ER/Metodol.) | Ciclo de vida da BD, Desenho Conceptual/Lógico/Físico, tipos de atributos de Chen, Entidades Fracas, Especialização/Generalização. |
| **12. Data Warehousing** | **Aula 11** (Data Warehousing) | Definição de Inmon (1993), Star vs Snowflake Schema, Kimball Lifecycle, ETL. |
| **13. BD Distribuídas e Paralelas** | **BD-Todas-As-Perguntas.pdf** | *Nota: Este tema não tem um PDF de slides na pasta PPS, mas é cobrado na base de dados oficial de perguntas do exame e nos exames anteriores.* Cobre fragmentação, alocação, Shared Memory/Disk/Nothing e o protocolo Two-Phase Commit (2PC). |

---

## 🔍 Notas de Validação Técnica e Pedagógica

### 1. Terminologia e Notações
* **Termos Académicos:** Toda a terminologia empregue nas explicações e resoluções coincide com os slides oficiais. Termos específicos como "Atributos Primos", "Lossless-Join", "União Compatível", "Dependências Parciais/Transitivas" e "Determinantes" foram respeitados de forma integral.
* **Metodologia de Normalização:** O caderno de exercícios detalha a resolução da normalização de faturas/recibos seguindo o **método por atributos (Passo 0 ao Passo 3 com codificação por letras e setas de dependências)**, conforme exigido explicitamente nos exames e na metodologia da UC.

### 2. Sintaxe de Programação (T-SQL / SQL Server)
* **Triggers:** As soluções usam a sintaxe do SQL Server (T-SQL) suportada nas aulas, fazendo a correta junção lógica entre as tabelas de sistema `inserted` e `deleted` para deteção de alterações em tempo real.
* **Stored Procedures e Funções (UDF):** Implementação de blocos estruturados `TRY...CATCH` com gestão de transações (`BEGIN TRAN`, `COMMIT`, `ROLLBACK`) em concordância com os exemplos práticos disponibilizados.

### 3. Cobertura da Matéria de Exame
* Todos os tópicos da pauta de exame foram contemplados tanto a nível teórico (no **Guia de Estudo**) como a nível de treino (com **4 exercícios dedicados por tema**).
* Os exercícios distribuídos e paralelos (Tema 13), embora ausentes dos slides na pasta `PPS`, foram concebidos com base direta nas perguntas típicas da base de dados oficial de exames (`BD-Todas-As-Perguntas.pdf`), garantindo que não há surpresas na prova.
