# 📚 Guia de Estudo — Exame Teórico de Bases de Dados 2025/2026

> **Formato do exame:** 8 perguntas de desenvolvimento (sem consulta, 2h00m)  
> **Cotação típica:** Perguntas de 2 valores (teóricas) + Pergunta de normalização (3 val.) + Pergunta prática SQL/Álgebra Relacional (5 val.)  
> **Baseado em:** Exame Época Normal 2024/2025 + BD-Todas-As-Perguntas + Slides PPS

---

## 📋 Índice

1. [Conceitos Fundamentais de BD](#1-conceitos-fundamentais-de-bd)
2. [Arquitetura ANSI/SPARC e Independência de Dados](#2-arquitetura-ansisparc-e-independência-de-dados)
3. [Modelo Relacional](#3-modelo-relacional)
4. [Álgebra Relacional](#4-álgebra-relacional)
5. [SQL – LMD (Linguagem de Manipulação de Dados)](#5-sql--lmd-linguagem-de-manipulação-de-dados)
6. [SQL – LDD (Linguagem de Definição de Dados)](#6-sql--ldd-linguagem-de-definição-de-dados)
7. [Integridade Referencial](#7-integridade-referencial)
8. [Vistas (Views)](#8-vistas-views)
9. [Triggers, Stored Procedures e Funções](#9-triggers-stored-procedures-e-funções)
10. [Normalização](#10-normalização)
11. [Desenho e Modelação de BD (Diagramas E/R)](#11-desenho-e-modelação-de-bd-diagramas-er)
12. [Data Warehousing](#12-data-warehousing)
13. [BD Distribuídas e Paralelas](#13-bd-distribuídas-e-paralelas)
14. [Exercícios Tipo Exame (Ver documento separado)](Exercicios_Exames_BD.md)

---

## 1. Conceitos Fundamentais de BD

### ❓ Pergunta típica: "Defina BD, SGBD e Metadados"

**BASE DE DADOS (BD):** Coleção partilhada de dados logicamente relacionados e a descrição desses dados, desenhada para satisfazer a necessidade de informação de uma organização.

**SISTEMA DE GESTÃO DE BASE DE DADOS (SGBD):** Sistema de software que permite aos utilizadores definir, criar, manter e controlar o acesso à base de dados.

**5 Componentes de um SGBD:**
| Componente | Descrição |
|---|---|
| **Hardware** | Parte física — servidores, discos, rede |
| **Software** | Sistema operativo + SGBD + aplicações |
| **Dados** | A informação armazenada na BD |
| **Procedimentos** | Regras de utilização e interação com a BD |
| **Pessoas** | Utilizadores finais, administradores, programadores |

**METADADOS:** Repositório de informação que descreve os dados na BD ("dados sobre dados"). Disponibiliza a descrição dos dados para obter aplicações independentes.

### ❓ "Diferenças entre Sistemas de Ficheiros e Sistemas de BD"

| Aspeto | Sistema de Ficheiros | Sistema de BD |
|---|---|---|
| Dados | Separados e isolados | Centralizados e partilhados |
| Redundância | Elevada | Controlada |
| Acesso simultâneo | Limitado | Multiutilizador |
| Independência dados/aplicações | Não existe | Existe |
| Custo | Baixo | Elevado |
| Complexidade | Baixa | Elevada |
| Impacto em falha | Menor | Maior |

**Quando preferir Sistemas de Ficheiros:** Quando a quantidade de informação é baixa, serve apenas um departamento, custo inferior, e o impacto em caso de falha é reduzido.

### Vantagens do SGBD
- Controlo sobre a redundância de dados
- Consistência e integridade dos dados
- Partilha de dados
- Mais segurança
- Economia de escala
- Serviços de backup e recuperação
- Independência de dados

### Desvantagens do SGBD
- Complexidade e tamanho
- Custo elevado (SGBD + hardware + conversão)
- Performance pode ser afetada
- Maior impacto em caso de falha

### ❓ "Sublinguagens de dados (DDL vs DML)"

**DDL (Data Definition Language):** Permite implementar a BD — criar tabelas, relações, restrições de integridade.
- Comandos: `CREATE`, `ALTER`, `DROP`

**DML (Data Manipulation Language):** Usada após a criação da BD — inserir, eliminar, atualizar e consultar dados.
- Comandos: `SELECT`, `INSERT`, `UPDATE`, `DELETE`

### ❓ "Diferenças entre LMD/DML Procedimentais e Não-Procedimentais (Declarativas)" ⭐ (Pergunta 1 do exame 2024/2025, EN 2021)

As Linguagens de Manipulação de Dados (LMD / DML) dividem-se em duas abordagens fundamentais sobre como interagem com a base de dados:

| Característica | LMD Procedimental | LMD Não-Procedimental (Declarativa) |
|---|---|---|
| **Foco da instrução** | Especifica **como** obter os dados (detalha a sequência de passos físicos e a lógica algorítmica de acesso). | Especifica apenas **que** (*o que*) dados obter, sem detalhar o caminho ou os passos de acesso físico. |
| **Grão de Processamento** | Manipula registos **um de cada vez** (*one-record-at-a-time*). Exige laços de repetição para percorrer registos. | Opera sobre **conjuntos de dados** (*set-at-a-time*). O SGBD processa múltiplos tuplos de uma vez. |
| **Controlo de Fluxo** | Exige controlo de fluxo algorítmico (estruturas condicionais `IF` e laços `WHILE` / `LOOP`). | Não possui estruturas de controlo de fluxo no comando; é uma instrução única declarativa. |
| **Otimização** | É manual, cabendo inteiramente ao programador estruturar o caminho de acesso mais rápido. | É automática, realizada pelo **otimizador de consultas do SGBD** (plano físico de execução). |
| **Complexidade** | Geralmente mais complexa de programar e ler, mas muito potente para regras de negócio refinadas. | Mais intuitiva, simples de ler e de manter. |
| **Exemplos** | Álgebra Relacional, blocos procedimentais com **cursores** em T-SQL ou PL/SQL. | Cláusula `SELECT` em SQL puro, Cálculo Relacional (de tuplos e de domínios). |

**Exemplos Académicos:**
- **Procedimental (Álgebra Relacional):**
  $$\text{Nomes} \leftarrow \pi_{\text{nome}}(\sigma_{\text{idade} > 18}(\text{Estudante}))$$
  *(Explicita uma sequência lógica de operações: primeiro filtra a relação `Estudante` usando a seleção $\sigma$ e depois projeta $\pi$ o atributo `nome`)*
- **Não-Procedimental (SQL Declarativo):**
  ```sql
  SELECT nome FROM Estudante WHERE idade > 18;
  ```
  *(Diz apenas ao SGBD o que pretende obter; o motor do SGBD decide se usa um index scan, table scan ou outra técnica física de procura)*

---

## 2. Arquitetura ANSI/SPARC e Independência de Dados

### ❓ "Identifique os três níveis da arquitetura ANSI/SPARC" ⭐ (Pergunta 1 do Exame Modelo 2)

```
┌─────────────────────┐
│   NÍVEL EXTERNO      │  → Vistas individuais/aplicações dos utilizadores
├─────────────────────┤
│   NÍVEL CONCEPTUAL   │  → Estrutura lógica global e regras de negócio (DBA)
├─────────────────────┤
│   NÍVEL INTERNO      │  → Organização física e armazenamento dos dados
└─────────────────────┘
```

| Nível | O que representa |
|---|---|
| **Externo** | A visão de cada utilizador individual ou aplicação sobre a base de dados. Cada utilizador interage apenas com uma porção relevante dos dados (através de vistas ou subsets de tabelas), permanecendo alheio ao resto da BD. |
| **Conceptual** | A **visão lógica e global de toda a base de dados** para a organização. É a camada intermédia central onde o Administrador de Bases de Dados (DBA) define a estrutura e regras de negócio de forma unificada. |
| **Interno** | A representação física da base de dados no computador. Descreve em detalhe como os dados são armazenados em disco (estruturas de ficheiros, partições, índices, caminhos de acesso e métodos de compressão). |

---

### ❓ "O que representa o Nível Conceptual da arquitetura ANSI/SPARC e qual a sua importância?" ⭐ (Pergunta 7 do TOP 15)

O nível conceptual representa a **estrutura lógica completa** da base de dados. Ele serve como uma camada de abstração que esconde os pormenores do armazenamento físico das aplicações clientes. 
**O que é representado neste nível:**
- A definição de todas as **entidades (tabelas)**, os seus **atributos (colunas)** e os **relacionamentos** entre elas.
- As **restrições de integridade** lógicas (como chaves primárias, chaves estrangeiras, restrições `CHECK` ou `UNIQUE`).
- As regras de **segurança, controlo de acesso e autorização** globais da base de dados.

---

### ❓ "O que é a Independência de Dados? Diferencie Independência Física de Lógica com exemplos práticos." ⭐ (Pergunta 13 do TOP 15)

A **independência de dados** é a capacidade de alterar o esquema de uma base de dados num determinado nível de abstração da arquitetura ANSI/SPARC sem a necessidade de reestruturar ou reescrever o código nos níveis superiores (esquemas superiores e aplicações).

#### 1. Independência Física de Dados
Consiste na capacidade de alterar a estrutura de armazenamento físico ou os métodos de acesso da base de dados (nível interno) **sem afetar** o esquema lógico (nível conceptual) nem as aplicações e consultas dos utilizadores (nível externo).
- *Propósito:* Otimização de desempenho físico e infraestrutura.
- *Exemplo prático:* O DBA decide criar um novo **índice** na coluna `email` da tabela `Clientes` (em disco) para acelerar as procuras. Todas as aplicações que usam a consulta `SELECT * FROM Clientes WHERE email = ...` continuam a funcionar sem qualquer alteração de código, embora agora beneficiem de maior rapidez.

#### 2. Independência Lógica de Dados
Consiste na capacidade de alterar o esquema lógico global (nível conceptual) **sem que seja necessário reescrever** as aplicações ou queries dos utilizadores existentes (nível externo).
- *Propósito:* Evolução e reestruturação da estrutura lógica da base de dados.
- *Exemplo prático:* O DBA decide **dividir** a tabela `Funcionario(codF, nome, morada, salario)` em duas novas tabelas por razões de normalização ou segurança: `Funcionario(codF, nome, morada)` e `Vencimento(codF, salario)`. Para evitar que as aplicações antigas deixem de funcionar, o DBA cria uma **vista (VIEW)** chamada `Funcionario` que realiza um `NATURAL JOIN` entre as duas novas tabelas, simulando a estrutura original para o nível externo.

---

### ❓ "Diferenças entre a Arquitetura Cliente-Servidor de 2 e 3 níveis" ⭐ (Pergunta 14 do TOP 15)

#### Arquitetura de 2 Níveis (2-tier)
A aplicação corre na máquina cliente (conhecida como *fat client* ou cliente pesado) e comunica **diretamente** com o servidor de bases de dados. A máquina cliente é responsável por renderizar a interface gráfica **e** processar toda a lógica e regras de negócio da aplicação. O servidor de dados apenas executa as queries SQL.

#### Arquitetura de 3 Níveis (3-tier)
Introduz-se uma camada intermédia dedicada: o **Servidor de Aplicação** (Application Server ou *Web Server*). O cliente corre apenas uma interface leve (designado por *thin client* ou cliente magro, tipicamente um browser Web). O Servidor de Aplicação centraliza o processamento de toda a lógica e regras de negócio, comunicando com o Servidor de Base de Dados para obter e persistir os dados físicos.

| Característica | Arquitetura de 2 Níveis (2-tier) | Arquitetura de 3 Níveis (3-tier) |
|---|---|---|
| **Comunicação do Cliente** | Direta com a Base de Dados | Intermediada pelo Servidor de Aplicação |
| **Local da Lógica de Negócio** | Instalada em cada máquina cliente | Centralizada no Servidor de Aplicação |
| **Tipo de Cliente** | Cliente Pesado (*fat client*) | Cliente Leve (*thin client* / browser) |
| **Escalabilidade** | Baixa (limitação física de conexões à BD) | Elevada ( pooling de conexões concorrentes) |
| **Segurança** | Menor (credenciais e acesso direto expostos) | Maior (BD isolada da rede pública) |
| **Manutenção** | Complexa (exige atualizar código em cada cliente) | Simples (atualizações feitas no servidor central) |

#### 🌐 Porque é a arquitetura de 3 níveis a mais adequada para a Web?
1. **Pooling de Conexões e Escalabilidade:** O servidor de aplicação mantém um pool fechado de conexões abertas com a BD, reutilizando-as de forma concorrente para servir milhares de utilizadores Web em simultâneo. Em 2 níveis, cada browser precisaria de abrir uma conexão direta e permanente ao SGBD, o que esgotaria os limites do SGBD de imediato.
2. **Segurança de Dados:** O servidor de bases de dados fica protegido atrás de uma firewall, sem exposição direta à Internet. O cliente Web nunca tem acesso direto às tabelas físicas ou às credenciais de acesso da BD.
3. **Facilidade de Distribuição e Manutenção:** Como a lógica de negócio reside no servidor de aplicação, qualquer alteração nas regras de negócio ou patches de segurança é implementada centralizadamente, sem necessidade de reconfigurar o código nos computadores dos utilizadores.

---

### ❓ "Quais as abordagens de desenho para bases de dados com múltiplas vistas de utilizadores?" ⭐ (Pergunta 15 do TOP 15)

Diferentes departamentos e utilizadores de uma organização possuem necessidades distintas de informação, interagindo com diferentes "vistas" lógicas da base de dados. Para projetar um esquema global coerente que atenda a todos os requisitos, o DBA pode seguir três abordagens:

1. **Abordagem Centralizada (Centralized Integration):**
   - *Funcionamento:* Os requisitos de todas as diferentes vistas e utilizadores são recolhidos, analisados e fundidos numa única lista consolidada de requisitos globais no início do projeto. A partir desta lista única e consolidada, desenha-se diretamente um único modelo conceptual global.
   - *Quando utilizar:* Recomendada para sistemas pequenos ou de média complexidade, onde as vistas dos utilizadores se sobrepõem substancialmente e não existem regras de negócio divergentes entre os setores.

2. **Abordagem por Integração de Vistas (View Integration):**
   - *Funcionamento:* Desenha-se um modelo conceptual local independente para cada vista de utilizador ou departamento. Posteriormente, estes modelos locais são fundidos e harmonizados através de um processo de integração (resolvendo sinónimos, homónimos e conflitos de tipos) para dar origem ao esquema conceptual global unificado.
   - *Quando utilizar:* Essencial para grandes organizações com sistemas complexos e com requisitos altamente específicos por setor, onde o desenvolvimento isolado de cada subsistema é mais produtivo.

3. **Abordagem Mista (Mixed Approach):**
   - *Funcionamento:* Combina os aspetos de ambas as abordagens. Os requisitos comuns e de fácil harmonização de todas as vistas são identificados e consolidados logo no início de forma centralizada. As vistas mais complexas e setoriais são tratadas como modelos conceituais locais independentes e integradas progressivamente na estrutura global durante a fase de modelação.

---

## 3. Modelo Relacional

### ❓ "Explique: Relação, Atributo, Domínio, Tuplo, Grau, Cardinalidade"

| Termo | Definição |
|---|---|
| **Relação** | Tabela com colunas e linhas (estrutura lógica) |
| **Atributo** | Nome de uma coluna de uma relação |
| **Domínio** | Conjunto de valores permitidos para um ou mais atributos |
| **Tuplo** | Uma linha de uma relação |
| **Grau** | Número de atributos de uma relação |
| **Cardinalidade** | Número de tuplos de uma relação |

### Propriedades das Relações
- Nome **único** no schema
- Cada atributo tem um nome **distinto**
- Valores pertencem ao **mesmo domínio**
- A ordem dos atributos **não tem significado**
- Cada tuplo é **diferente** (não há duplicados)
- A ordem dos tuplos **teoricamente** não interessa (na prática afeta performance)

### ❓ "Regras de Integridade do Modelo Relacional"

1. **Integridade da Entidade:** Nenhum tuplo pode ter valor `NULL` na **chave primária**
2. **Integridade Referencial:** Se um atributo é chave estrangeira, só pode assumir valores que existam na tabela onde é chave primária (ou `NULL`)
3. **Integridade Geral:** Restrições relativas ao negócio da BD

### ❓ "Chave Candidata, Chave Primária, Chave Estrangeira"

| Tipo | Definição |
|---|---|
| **Chave Candidata** | Um ou mais atributos que identificam unicamente um tuplo |
| **Chave Primária (PK)** | A chave candidata escolhida para identificação dos tuplos |
| **Chave Estrangeira (FK)** | Atributo numa relação que é PK/CK noutra relação; permite relacionar tuplos |

### 3 Gerações de SGBD
1. **Modelo Hierárquico** — pouca independência de dados, programas complexos
2. **Modelo Relacional** — resolve muitos problemas da 1ª geração
3. **SGBD OO e Objeto-Relacional** — novo paradigma

---

## 4. Álgebra Relacional

### ❓ "Defina as 5 operações básicas de álgebra relacional" ⭐ (MiniTeste e Exames)

A álgebra relacional é uma linguagem de consulta procedimental que opera sobre relações (tabelas) e produz novas relações como resultado. As 5 operações fundamentais e indivisíveis são:

| Operação | Símbolo | Definição Lógica e Descrição | Exemplo / Equivalência SQL |
|---|:---:|---|---|
| **Seleção** | $\sigma$ | Seleciona os tuplos (linhas) de uma relação que satisfazem a condição de seleção especificada. | $\sigma_{\text{idade} > 18}(\text{Cliente})$ <br> `SELECT * FROM Cliente WHERE idade > 18` |
| **Projeção** | $\pi$ | Cria uma nova relação que contém apenas o subconjunto de atributos (colunas) listados, eliminando linhas duplicadas. | $\pi_{\text{nome}, \text{NIF}}(\text{Cliente})$ <br> `SELECT DISTINCT nome, NIF FROM Cliente` |
| **Produto Cartesiano** | $\times$ | Combina cada tuplo de uma relação com todos os tuplos de outra relação, resultando numa relação com grau $G_1 + G_2$ e cardinalidade $C_1 \times C_2$. | $\text{Cliente} \times \text{Encomenda}$ <br> `SELECT * FROM Cliente, Encomenda` |
| **União** | $\cup$ | Combina os tuplos de duas relações compatíveis para união numa única relação, eliminando registos duplicados. | $\text{ClientesA} \cup \text{ClientesB}$ <br> `SELECT * FROM ClientesA UNION SELECT * FROM ClientesB` |
| **Diferença** | $-$ | Devolve uma relação com todos os tuplos que pertencem à primeira relação mas que **não estão presentes** na segunda relação. | $\text{ClientesA} - \text{ClientesB}$ <br> `SELECT * FROM ClientesA EXCEPT SELECT * FROM ClientesB` |

> ⚠️ **Compatibilidade de União (Union Compatible):** Para realizar operações de União ($\cup$), Diferença ($-$) e Intersecção ($\cap$), as duas relações têm obrigatoriamente de ter o **mesmo número de atributos** e os **domínios correspondentes têm de ser idênticos** (tipo de dados compatível na mesma ordem).

---

### Operações Derivadas (Úteis para simplificação)

| Operação | Símbolo e Definição Matemática | Descrição |
|---|:---:|---|
| **Junção (Join)** | $R \bowtie_{\text{cond}} S \equiv \sigma_{\text{cond}}(R \times S)$ | Combina registos de duas relações com base numa condição. É um produto cartesiano otimizado por um filtro. |
| **Intersecção** | $R \cap S \equiv R - (R - S)$ | Devolve apenas os tuplos que pertencem simultaneamente a ambas as relações. |
| **Divisão** | $R \div S$ | Usada em consultas do tipo **"quais os X que fazem TODOS os Y"**. Retorna os valores de atributos de $R$ que estão associados a *todos* os tuplos presentes em $S$. |

#### 🧠 Exemplo de Divisão (÷):
Seja a tabela `Tempo(codA, modalidade)` e `Modalidade(modalidade)`:
$$\text{AtletasComTodas} \leftarrow \pi_{\text{codA}, \text{modalidade}}(\text{Tempo}) \div \pi_{\text{modalidade}}(\text{Modalidade})$$
*Resultado:* Códigos dos atletas (`codA`) que praticam **todas** as modalidades listadas na tabela `Modalidade`.

---

### ❓ "Quais as diferenças entre os tipos de Junção?" ⭐ (EN 2021)

*   **Theta Join ($\bowtie_{\theta}$):** A junção geral. Combina duas relações através de uma condição baseada em qualquer operador de comparação lógico ($=$, $<$, $>$, $\le$, $\ge$, $\ne$). Mantém as colunas de ambas as tabelas (mesmo as homónimas duplicadas).
*   **Equijoin ($\bowtie_{=}$):** Caso especial de Theta Join onde a condição de junção utiliza exclusivamente o operador de igualdade ($=$). As colunas com dados redundantes de junção mantêm-se duplicadas no resultado.
*   **Natural Join ($\bowtie$):** Junção por igualdade realizada de forma automática sobre todos os atributos que têm o **mesmo nome** nas duas relações. O SGBD elimina automaticamente a coluna duplicada no resultado final.
*   **Outer Join (Junção Externa):** Mantém no resultado final os registos de uma relação que **não encontram correspondência** na outra relação, preenchendo as colunas vazias com valores `NULL`.
    *   *Left Outer Join ($\supset\bowtie$):* Mantém todas as linhas da tabela da esquerda.
    *   *Right Outer Join ($\bowtie\subset$):* Mantém todas as linhas da tabela da direita.
    *   *Full Outer Join ($\supset\bowtie\subset$):* Mantém todas as linhas de ambos os lados.
*   **Semijoin ($R \ltimes_{\text{cond}} S \equiv \pi_{\text{Atributos de } R}(R \bowtie_{\text{cond}} S)$):** Devolve apenas as colunas e tuplos da relação $R$ que têm correspondência em $S$. Não projeta atributos de $S$ e não duplica linhas de $R$ caso haja múltiplas correspondências em $S$. Muito eficiente em BD Distribuídas.

---

### ⚡ Regras de Otimização em Álgebra Relacional

Em exames práticos, as perguntas de álgebra relacional costumam exigir **resoluções otimizadas**. A regra de ouro é:

> 💡 **"Pushdown de Seleções e Projeções":** Realizar as operações de Seleção ($\sigma$) e Projeção ($\pi$) o **mais cedo possível** (antes das junções ou produtos cartesianos). Isto reduz drasticamente o tamanho das tabelas intermédias em memória, otimizando o CPU e acessos de disco.

#### Exemplo prático de Otimização:
*Objetivo:* Listar nomes de mecânicos que trabalharam em automóveis a 'Gasolina'.
*   **Fórmula NÃO Otimizada (Junta tudo primeiro, filtra no fim):**
    $$\pi_{\text{nome}}(\sigma_{\text{combustivel} = \text{'Gasolina'}}(\text{Mecanico} \bowtie \text{Manutencao} \bowtie \text{Automovel}))$$
*   **Fórmula OTIMIZADA (Filtra o Automóvel ANTES do Join):**
    $$\pi_{\text{nome}}(\text{Mecanico} \bowtie \text{Manutencao} \bowtie \sigma_{\text{combustivel} = \text{'Gasolina'}}(\text{Automovel}))$$

---

### ❓ "Como combinar resultados de duas consultas?"

Os resultados de duas subconsultas podem ser fundidos usando operadores de conjuntos (`UNION`, `INTERSECT`, `EXCEPT`), desde que as subconsultas resultantes sejam **compatíveis para união** (mesmo grau e domínios correspondentes).

---

## 5. SQL – LMD (Linguagem de Manipulação de Dados)

### ❓ "Explique cada cláusula do comando SELECT"

```sql
SELECT   atributos          -- OBRIGATÓRIO: colunas/funções que aparecerão no resultado
FROM     tabelas            -- OBRIGATÓRIO: tabelas de origem dos dados e os seus JOINs
WHERE    condição            -- Filtra LINHAS individuais (não aceita funções de agregação)
GROUP BY atributos          -- Agrupa linhas com mesmos valores em grupos de agregação
HAVING   condição_grupo      -- Filtra GRUPOS formados (aceita funções de agregação)
ORDER BY atributo [ASC|DESC] -- Ordena o resultado (por defeito ASC; executada por último)
```

---

### ❓ "Qual a diferença entre WHERE e HAVING?" ⭐ (Pergunta típica de exame)

| Característica | Cláusula `WHERE` | Cláusula `HAVING` |
|---|---|---|
| **Momento de Aplicação** | Filtragem das linhas **antes** de qualquer agrupamento (`GROUP BY`). | Filtragem dos grupos **depois** de realizado o agrupamento (`GROUP BY`). |
| **Funções de Agregação** | **Não aceita** funções agregadas (ex: `WHERE SUM(x) > 10` é inválido). | **Aceita** e filtra com base em funções agregadas (ex: `HAVING SUM(x) > 10`). |
| **Grão de Atuação** | Filtra **linhas individuais** da tabela. | Filtra **grupos de linhas** consolidados. |

---

### ❓ "Diferença entre Subquery e Junção. Em que situações NÃO é possível usar uma subquery?" ⭐ (Pergunta 4 e 10 do TOP 15)

*   **Subquery (Subconsulta):** É um comando `SELECT` aninhado dentro de uma consulta externa principal (nas cláusulas `WHERE`, `HAVING`, `FROM` ou `SELECT`), servendo para calcular valores intermédios ou filtros que alimentam a query principal.
*   **Junção (JOIN):** É uma operação que combina registos de duas ou mais tabelas na mesma linha de dados do resultado final, com base numa condição de correspondência (PK/FK).

#### ❌ Situações onde NÃO é possível usar uma subquery (exigindo Junção):
1. **Exibição Simultânea de Atributos de Tabelas Diferentes:** Quando a consulta exige projetar (listar no `SELECT` final) colunas que pertencem a tabelas distintas simultaneamente. A subquery atua apenas como um filtro ou fornecedor de dados intermédio; não consegue "expor" colunas da sua tabela interna ao lado das colunas da query principal.
2. **Junções Externas Totais (FULL OUTER JOIN):** Quando é necessário listar linhas sem correspondência mútua de ambas as tabelas envolvidas na operação de forma simultânea.

#### 📝 Exemplo Prático (Exame):
Dadas as tabelas `Aluno(codAluno, nome)` e `Inscricao(codAluno, disciplina, nota)`.

*   **Caso A (Exibir apenas o nome dos alunos inscritos):** *Subquery é possível.*
    ```sql
    SELECT nome FROM Aluno WHERE codAluno IN (SELECT codAluno FROM Inscricao);
    ```
*   **Caso B (Exibir o nome do aluno, a disciplina e a nota dele):** *Subquery NÃO é possível* (requer expor colunas de ambas as tabelas no resultado). **Solução obrigatória com JOIN:**
    ```sql
    SELECT A.nome, I.disciplina, I.nota
    FROM Aluno A
    INNER JOIN Inscricao I ON A.codAluno = I.codAluno;
    ```

---

### 📋 Padrões Comuns de Queries em Exames (Templates)

#### Padrão 1: "Qual o X com MAIS/MENOS Y" (Agregação Máxima com Subquery no HAVING)
*Objetivo:* Encontrar o atleta com o maior número de tempos registados.
```sql
SELECT A.codA, A.nome, COUNT(*) AS TotalTempos
FROM Atleta A
INNER JOIN Tempo T ON A.codA = T.codA
GROUP BY A.codA, A.nome
HAVING COUNT(*) >= ALL (
    SELECT COUNT(*) FROM Tempo GROUP BY codA
);
```

#### Padrão 2: "Quais os X que NUNCA Y" (Diferença de conjuntos)
*Objetivo:* Listar atletas que nunca registaram nenhum tempo durante o ano de 2026.
*   **Opção com `NOT IN` (Subquery):**
    ```sql
    SELECT codA, nome FROM Atleta
    WHERE codA NOT IN (SELECT DISTINCT codA FROM Tempo WHERE data >= '2026-01-01' AND data <= '2026-12-31');
    ```
*   **Opção com `EXCEPT` (Diferença):**
    ```sql
    SELECT codA, nome FROM Atleta
    EXCEPT
    SELECT A.codA, A.nome FROM Atleta A INNER JOIN Tempo T ON A.codA = T.codA 
    WHERE T.data >= '2026-01-01' AND T.data <= '2026-12-31';
    ```

#### Padrão 3: "Grupos com mais de N registros associados"
*Objetivo:* Países com mais de 5 passageiros distintos que fizeram reservas em voos para 'Porto' em 2026.
```sql
SELECT P.pais, COUNT(DISTINCT P.codPass) AS TotalPassageiros
FROM Passageiro P
INNER JOIN Reserva R ON P.codPass = R.codPass
INNER JOIN Voo V ON R.numVoo = V.numVoo
INNER JOIN Aeroporto A ON V.destino = A.codIATA
WHERE A.cidade = 'Porto' AND R.dataViagem BETWEEN '2026-01-01' AND '2026-12-31'
GROUP BY P.pais
HAVING COUNT(DISTINCT P.codPass) > 5;
```

---

### Funções de Agregação e Comportamento com NULLs

| Função | Descrição | Comportamento com Valores `NULL` |
|---|---|---|
| `COUNT(*)` | Conta a quantidade total de linhas que satisfazem a query. | **Inclui** nulos no cálculo (avalia o registo como um todo). |
| `COUNT(coluna)`| Conta a quantidade de valores preenchidos nessa coluna. | **Ignora** valores nulos (não os contabiliza). |
| `SUM(coluna)` | Soma todos os valores da coluna. | **Ignora** valores nulos. |
| `AVG(coluna)` | Calcula a média aritmética dos valores da coluna. | **Ignora** valores nulos (ex: `AVG(10, NULL, 20) = (10+20)/2 = 15`). |
| `MIN(coluna)` | Devolve o valor mínimo encontrado. | **Ignora** valores nulos. |
| `MAX(coluna)` | Devolve o valor máximo encontrado. | **Ignora** valores nulos. |

---

## 6. SQL – LDD (Linguagem de Definição de Dados)

### Comandos Principais

```sql
-- 1. CRIAR DOMÍNIOS (Tipos de dados personalizados com regras)
CREATE DOMAIN Dnome AS VARCHAR(50);
CREATE DOMAIN Dtelefone AS DECIMAL(9,0)
    CHECK (VALUE BETWEEN 100000000 AND 999999999); -- Valida telefone de 9 dígitos

-- 2. CRIAR TABELA (Deve seguir a ordem de dependência: criar tabelas independentes primeiro)
CREATE TABLE NomeTabela (
    coluna1 tipo [NOT NULL] [UNIQUE] [DEFAULT valor] [CHECK (condição)],
    coluna2 tipo,
    PRIMARY KEY (coluna1),
    FOREIGN KEY (coluna2) REFERENCES TabelaPai(colunaPK)
        [ON UPDATE ação] [ON DELETE ação]
);

-- 3. ALTERAR TABELA
ALTER TABLE NomeTabela ADD coluna tipo;
ALTER TABLE NomeTabela DROP COLUMN coluna;
ALTER TABLE NomeTabela ALTER COLUMN coluna SET DEFAULT valor;

-- 4. REMOVER TABELA
DROP TABLE NomeTabela [RESTRICT | CASCADE];

-- 5. CRIAR ÍNDICE (Melhoria de performance de pesquisa)
CREATE [UNIQUE] INDEX NomeIndice ON Tabela (coluna [ASC|DESC]);

-- 6. REMOVER ÍNDICE
DROP INDEX NomeIndice;
```

---

### 📋 Restrições de Integridade em SQL (DDL)

| Tipo de Restrição | Sintaxe / Comportamento | Exemplo de Implementação |
|---|---|---|
| **Preenchimento Obrigatório** | `NOT NULL` | `nome VARCHAR(50) NOT NULL` |
| **Valor Único** | `UNIQUE` (Cria chaves candidatas alternativas) | `email VARCHAR(50) UNIQUE` |
| **Valor por Defeito** | `DEFAULT valor` | `estado CHAR(1) DEFAULT 'A'` |
| **Restrição de Domínio / Intervalo** | `CHECK (condição)` | `idade INT CHECK (idade >= 18)` |
| **Restrição do Negócio / Limite Cardinalidade** | `CHECK (NOT EXISTS (SELECT ...))` | *Ver exemplo abaixo* |
| **Integridade de Entidade** | `PRIMARY KEY (colunas)` (Garante que a PK é única e não nula) | `PRIMARY KEY (numSocio, codAula)` |
| **Integridade Referencial** | `FOREIGN KEY (coluna) REFERENCES Pai(coluna)` | `FOREIGN KEY (codInst) REFERENCES Instrutor(codInst)` |

#### ⚡ Padrão Avançado: Limitar a Cardinalidade com CHECK e NOT EXISTS
Nos exames práticos de LDD, é muito frequente pedir restrições complexas como: *"Nenhum atleta pode registar tempos em mais de 5 modalidades distintas."*
Para implementar esta regra sem recorrer a triggers, utilizamos uma restrição `CHECK` com uma subquery `NOT EXISTS`:

```sql
CREATE TABLE Tempo (
    codA        CHAR(5) NOT NULL,
    modalidade  INT NOT NULL,
    tempo       DECIMAL(5,2) NOT NULL,
    PRIMARY KEY (codA, modalidade),
    FOREIGN KEY (codA) REFERENCES Atleta(codA),
    -- Garante que NÃO existe nenhum atleta com mais de 5 modalidades:
    CHECK (NOT EXISTS (
        SELECT codA FROM Tempo
        GROUP BY codA
        HAVING COUNT(DISTINCT modalidade) > 5
    ))
);
```

---

## 7. Integridade Referencial

### ❓ "Explique o conceito de Integridade Referencial e as ações das subcláusulas ON DELETE e ON UPDATE" ⭐ (Pergunta 1 do TOP 15 — Saiu em 8+ exames!)

A **integridade referencial** é uma regra fundamental do modelo relacional que garante a consistência lógica das ligações entre tabelas. Esta regra assegura que qualquer valor de uma chave estrangeira (FK) numa tabela filha deve obrigatoriamente:
1. Existir previamente na chave primária (PK) ou numa chave candidata (CK) da tabela relacionada (tabela pai).
2. Ou assumir o valor nulo (`NULL`), indicando que não há associação no momento (desde que a coluna FK não seja `NOT NULL`).

**Exemplo prático:** Se a tabela `Encomenda` tiver a coluna FK `codCliente` ligada à tabela `Cliente`, qualquer código de cliente introduzido em `Encomenda` deve existir previamente na coluna PK `codCliente` de `Cliente`, ou ser nulo.

---

### Ações nas subcláusulas ON DELETE e ON UPDATE:

Quando um registo na tabela pai é eliminado (`DELETE`) ou a sua chave primária é alterada (`UPDATE`), podem existir registos na tabela filha que referenciam essa linha. Para gerir esta situação e evitar que os registos filhos fiquem órfãos (inconsistentes), o SQL permite definir as seguintes ações:

| Ação | Comportamento no `DELETE` | Comportamento no `UPDATE` | Regras e Restrições |
|---|---|---|---|
| **`CASCADE`** | Apaga automaticamente todas as linhas correspondentes na tabela filha. | Atualiza automaticamente o valor da FK na tabela filha para o novo valor da PK do pai. | Propaga as alterações em cascata. Útil para relações forte-fraco (ex: apagar fatura apaga todas as suas linhas de artigo). |
| **`SET NULL`** | Define a FK de todas as linhas filhas correspondentes para `NULL`. | Define a FK de todas as linhas filhas correspondentes para `NULL`. | **Obrigatório:** A coluna da chave estrangeira na tabela filha deve aceitar valores nulos (não pode ser declarada como `NOT NULL`). |
| **`SET DEFAULT`** | Altera a FK de todas as linhas filhas correspondentes para o valor por defeito (`DEFAULT`) configurado na coluna. | Altera a FK de todas as linhas filhas correspondentes para o valor por defeito (`DEFAULT`) configurado. | **Obrigatório:** A coluna da chave estrangeira na tabela filha deve ter um valor padrão associado (`DEFAULT valor`). |
| **`NO ACTION`** *(ou `RESTRICT`)* | Rejeita a eliminação da linha pai. O SGBD gera um erro e aborta a operação. | Rejeita a atualização da linha pai. O SGBD gera um erro e aborta a operação. | **É o comportamento padrão (por defeito)** caso não seja especificado outro. A operação no pai só é permitida se não houver filhos. |

```sql
-- Exemplo Prático de Implementação DDL:
CREATE TABLE Encomenda (
    numEncomenda  INT,
    codCliente    INT,
    data          DATE,
    PRIMARY KEY (numEncomenda),
    FOREIGN KEY (codCliente) REFERENCES Cliente(codCliente)
        ON DELETE SET NULL    -- Se apagar o cliente, as suas encomendas ficam sem cliente (NULL)
        ON UPDATE CASCADE     -- Se o código do cliente mudar, propaga e atualiza na encomenda
);
```

---

## 8. Vistas (Views)

### ❓ "O que é uma vista? Indique as diferenças detalhadas entre uma vista e uma relação base." ⭐ (Pergunta 2 do Exame 2024/2025, Recurso 23/24)

Uma **vista (VIEW)** é uma tabela virtual cujo conteúdo é definido dinamicamente através de uma consulta SQL (`SELECT`) sobre uma ou mais tabelas físicas (relações base). 

As diferenças fundamentais são:

| Característica | Relação Base (Tabela) | Vista Tradicional (View) |
|---|---|---|
| **Armazenamento Físico** | Ocupa armazenamento físico permanente em disco para guardar os dados reais. | Não armazena dados físicos; guarda apenas a sua definição de consulta (o texto `SELECT`) nos metadados. |
| **Escrita / Atualizabilidade (DML)** | Permite qualquer operação de escrita direta (`INSERT`, `UPDATE`, `DELETE`) sem restrições lógicas. | Possui restrições severas. Só é atualizável se mapear uma única tabela base e não contiver junções, `GROUP BY`, `DISTINCT`, subqueries ou funções de agregação. |
| **Custo de Processamento** | O acesso aos dados é direto e rápido (leitura direta das páginas de dados em disco). | Exige que o SGBD execute a consulta SQL subjacente em tempo real sempre que a vista é consultada, o que pode degradar o desempenho se envolver junções complexas. |
| **Modo de Criação** | Criada com o comando `CREATE TABLE`. | Criada com o comando `CREATE VIEW`. |

---

### Vantagens das Vistas
- **Segurança e Privacidade:** Permite ocultar colunas confidenciais (ex: salário) ou linhas específicas de certos utilizadores, concedendo acesso apenas à vista.
- **Simplicidade:** Simplifica consultas complexas com múltiplas junções e agregações, ocultando a complexidade do utilizador final.
- **Independência Lógica:** Permite reestruturar o esquema conceptual (ex: dividir tabelas) mantendo as vistas antigas inalteradas para não quebrar o código das aplicações.

### Desvantagens das Vistas
- **Performance de Leitura:** Junções de tabelas gigantescas em vistas tradicionais são recalculadas a cada acesso, consumindo recursos de CPU e I/O.
- **Modificabilidade de Dados:** Não é possível atualizar diretamente dados de vistas que contenham junções ou resumos.

---

### ❓ "O que é a Materialização de Vistas? Quais as vantagens, desvantagens e contextos recomendados?" ⭐ (Pergunta 5 do Exame Modelo 1)

A **materialização de vistas** (conhecida como *Materialized Views* ou *Indexed Views*) consiste em pré-calcular e armazenar fisicamente em disco os resultados da consulta SQL que define a vista, como se de uma tabela física se tratasse. 

#### Vantagens:
- **Desempenho de leitura exponencialmente superior:** Consultas pesadas com junções e agregações de milhões de linhas são respondidas de imediato, uma vez que o resultado já está pré-calculado no disco.
- **Redução de carga no servidor:** Poupa recursos de CPU e memória por evitar cálculos repetitivos em tempo real.

#### Desvantagens:
- **Overhead nas operações de escrita:** Sempre que os dados das tabelas base originais são alterados (`INSERT`, `UPDATE`, `DELETE`), o SGBD necessita de atualizar ou recicular a vista materializada para a manter sincronizada.
- **Consumo de espaço em disco:** Ocupa espaço de armazenamento físico para guardar a cópia dos dados resultantes.
- **Desatualização temporária de dados:** Consoante a política de atualização (síncrona ou assíncrona), os dados da vista materializada podem sofrer um pequeno atraso (*delay*) em relação às tabelas base.

#### 📈 Contextos recomendados:
- Sistemas analíticos **OLAP** e de **Data Warehousing**, caracterizados por leituras frequentes de agregação e escritas raras (ex: relatórios diários de vendas).
- Consultas analíticas repetitivas complexas sobre tabelas base muito grandes.
- Dashboards estatísticos que não necessitam de consistência imediata ao segundo.

---

### Mecanismo de Resolução de Vistas (Query Modification)
Quando o utilizador faz uma pesquisa sobre uma vista tradicional, o SGBD executa os seguintes passos internamente:
1. Os nomes das colunas da vista são traduzidos para os atributos da tabela base correspondentes.
2. O nome da vista na cláusula `FROM` é substituído pelas tabelas base da definição.
3. A cláusula `WHERE` da query do utilizador é combinada com o `WHERE` da definição da vista usando um operador lógico `AND`.
4. Cláusulas de agregação (`GROUP BY` e `HAVING`) são copiadas da definição da vista.
5. A query final modificada é enviada ao otimizador de consultas para execução física.

```sql
-- Exemplo de criação de vista:
CREATE VIEW Manager3Staff AS
SELECT staffNo, nome, cargo, branchNo
FROM Staff
WHERE branchNo = 'B003';
```

---

## 9. Triggers, Stored Procedures e Funções

### ❓ "O que são Triggers de bases de dados e para que servem? Vantagens e desvantagens?" ⭐ (Pergunta 4 e 8 do TOP 15 — EN 2021, 2024/2025)

Um **trigger** (gatilho) é um bloco de código procedural armazenado no SGBD que é executado de forma automática e implícita em resposta a um evento de manipulação de dados DML (`INSERT`, `UPDATE` ou `DELETE`) numa tabela ou vista.

**Propósito de utilização:**
- Impor regras de negócio complexas que não podem ser expressas por restrições declarativas normais (CHECK, FK, UNIQUE).
- Criar auditorias lógicas e logs históricos automatizados (ex: gravar quem e quando alterou um salário).
- Atualizar valores derivados de forma automática.
- Reforçar restrições de integridade referencial complexa.

#### 📋 Tipos de Triggers quanto ao momento de execução:

| Tipo | Quando executa | Aplicação típica |
|---|---|---|
| **`BEFORE`** *(ou `PRE`)* | **Antes** da execução do comando DML e das validações de restrições. | Validação e formatação de dados de entrada antes de serem gravados. |
| **`AFTER`** *(ou `POST`)* | **Depois** do comando DML ter sido executado com sucesso e as restrições validadas. | Registo de auditorias (logs), envio de notificações ou atualização de tabelas resumo. |
| **`INSTEAD OF`** | **Em vez** da operação DML que disparou o trigger. | Tornar **vistas complexas atualizáveis** (o trigger interceta o INSERT/UPDATE na vista e faz as escritas nas tabelas base). |

#### 📂 Tabelas de Sistema `inserted` e `deleted` (T-SQL / SQL Server)
Durante a execução de um trigger DML, o SGBD disponibiliza duas tabelas lógicas temporárias em memória para aceder aos dados afetados:
- **`inserted`:** Armazena cópias das **novas linhas** inseridas (em `INSERT`) ou os novos valores atualizados (em `UPDATE`).
- **`deleted`:** Armazena cópias das **linhas eliminadas** (em `DELETE`) ou os valores antigos antes da alteração (em `UPDATE`).

```sql
-- Exemplo de Trigger T-SQL: Regista alterações de cargo e guarda histórico
CREATE TRIGGER [EmployeeUpdateAudit]
ON [Employee]
AFTER UPDATE
AS
BEGIN
    SET NOCOUNT ON;
    -- Se o cargo (JobRole) mudou, insere log na tabela de auditoria:
    INSERT INTO AuditEmployeeTable (EmployeeID, DataAlteracao, Utilizador, CargoAntigo, CargoNovo)
    SELECT i.EmployeeID, GETDATE(), SUSER_SNAME(), d.JobRole, i.JobRole
    FROM inserted i 
    INNER JOIN deleted d ON i.EmployeeID = d.EmployeeID
    WHERE d.JobRole != i.JobRole;
END
```

#### Vantagens:
- **Centralização lógica:** A integridade é garantida diretamente na BD, protegendo os dados independentemente de qual aplicação cliente efetue a escrita.
- **Automatização:** A execução é implícita e transparente para o utilizador, reduzindo código redundante nas aplicações.

#### Desvantagens:
- **Redução de Desempenho (Overhead):** Aumenta o tempo de processamento a cada operação de escrita.
- **Dificuldade de Depuração (Debug):** Por ser executado de forma implícita ("invisível"), pode gerar efeitos em cascata difíceis de rastrear.
- **Falta de Portabilidade:** A sintaxe dos triggers varia drasticamente entre os SGBDs (ex: SQL Server T-SQL vs Oracle PL/SQL).

---

### ❓ "Diferença entre Procedimento (Stored Procedure) e Função (UDF)" ⭐ (Pergunta 4 do Bónus TOP 15)

Stored Procedures e User-Defined Functions (UDF) são blocos de código procedimental compilados e guardados na BD, mas possuem propósitos e restrições muito diferentes:

| Característica | Procedimento (Stored Procedure) | Função (UDF) |
|---|---|---|
| **Valor de Retorno** | **Não é obrigatório** retornar valores (pode usar parâmetros `OUTPUT` ou retornar múltiplos recordsets). | **Obrigatoriamente** devolve um único valor (escalar ou tabela) através da cláusula `RETURN`. |
| **Invocação / Chamada** | Invocado de forma autónoma usando o comando `EXEC` ou `EXECUTE`. | Invocada diretamente dentro de comandos SQL (ex: no `SELECT`, `WHERE`, `HAVING`). |
| **Modificação de Dados** | **Pode alterar dados** em tabelas reais (`INSERT`, `UPDATE`, `DELETE`). | **Não pode alterar** o estado da base de dados (apenas leitura). |
| **Transações** | Permite iniciar, confirmar ou reverter transações (`COMMIT`, `ROLLBACK`). | **Não permite** controlo de transações no seu interior. |

```sql
-- Exemplo de Stored Procedure com transação interna:
CREATE PROCEDURE usp_AlterarCargo
    @empID INT,
    @novoCargo VARCHAR(50)
AS
BEGIN
    SET NOCOUNT ON;
    BEGIN TRAN;
    BEGIN TRY
        UPDATE Employee SET JobRole = @novoCargo WHERE EmployeeID = @empID;
        COMMIT TRAN;
    END TRY
    BEGIN CATCH
        ROLLBACK TRAN;
    END CATCH
END;

-- Exemplo de Função (UDF) Escalar:
CREATE FUNCTION ufi_ObterLocalidade (@codPostal INT)
RETURNS VARCHAR(50)
AS
BEGIN
    DECLARE @Localidade VARCHAR(50);
    SELECT @Localidade = Localidade FROM CodigosPostais WHERE Codigo = @codPostal;
    RETURN ISNULL(@Localidade, 'Desconhecida');
END;
```

---

### ❓ "O que é uma Transação e quais as propriedades ACID?" ⭐ (Pergunta 3 do Bónus TOP 15)

Uma **transação** é uma unidade lógica de processamento que agrupa uma ou mais instruções SQL. Para garantir que a base de dados permanece consistente, qualquer transação deve obedecer rigorosamente às propriedades **ACID**:

1.  **Atomicidade (Atomicity):** Princípio do "tudo ou nada". Todas as instruções que compõem a transação são executadas com sucesso total (confirmadas via **`COMMIT`**), ou, em caso de qualquer falha, todas as alterações são completamente desfeitas e revertidas (via **`ROLLBACK`**).
2.  **Consistência (Consistency):** A transação deve levar a base de dados de um estado consistente a outro estado igualmente consistente, respeitando todas as regras de integridade (chaves primárias, restrições CHECK, etc.).
3.  **Isolamento (Isolation):** As transações executadas de forma concorrente devem correr de forma independente, sem que uma transação intermédia interfira ou veja os dados parciais de outra transação ainda não concluída.
4.  **Durabilidade (Durability):** Uma vez que a transação é confirmada (`COMMIT`), as suas alterações tornam-se permanentes na base de dados, não se perdendo mesmo em caso de falha de energia ou colapso do servidor.

---

### ❓ "O que são Cursores SQL e qual o seu Ciclo de Vida?" ⭐ (Pergunta 3 do Exame Modelo 2)

Um **cursor** é uma estrutura de controlo mantida na memória do SGBD que funciona como um apontador lógico para iterar e manipular os registos resultantes de um `SELECT` **linha a linha** (procedimental, *one-record-at-a-time*). Contorna a natureza declarativa normal do SQL, que opera em conjuntos (*set-at-a-time*).

#### 📋 Fases do Ciclo de Vida do Cursor:

| Fase | Comando | Descrição do Processo |
|---|---|---|
| **1. Declaração** | `DECLARE` | Associa o cursor a uma instrução `SELECT` específica e define as suas variáveis de trabalho. |
| **2. Abertura** | `OPEN` | Executa o `SELECT` associado, aloca memória RAM no servidor e cria o conjunto de resultados ativo. |
| **3. Obtenção** | `FETCH` | Lê a linha corrente do conjunto, copia os dados para as variáveis e avança o apontador para a linha seguinte. (Geralmente executado dentro de um ciclo `WHILE` / `LOOP`). |
| **4. Fecho** | `CLOSE` | Fecha o cursor, invalida o conjunto de resultados em memória e liberta os bloqueios (*locks*) aplicados às tabelas. |
| **5. Desalocação** | `DEALLOCATE` | Remove a definição do cursor da memória de metadados do SGBD, libertando definitivamente os recursos. |

---

### Controlo de Acesso e Segurança em SQL
- **`GRANT`:** Concede privilégios de acesso (ex: `SELECT`, `UPDATE`) a tabelas ou vistas a utilizadores ou cargos.
- **`REVOKE`:** Retira privilégios previamente concedidos.
- Cada objeto (tabela, vista, procedure) tem um dono (*owner*) que tem controlo total sobre quem lhe acede.

---

## 10. Normalização

### ❓ "Quais os objetivos da Normalização de dados e o seu impacto no desempenho?" ⭐ (Pergunta 2 e 5 do TOP 15 — Saiu em 8+ exames!)

A **normalização** é o processo de organizar os dados no modelo relacional, decompondo relações complexas em esquemas de tabelas mais simples, com base nas chaves primárias/candidatas e nas dependências funcionais entre atributos.

#### Objetivos da Normalização:
1. **Minimizar a redundância de dados:** Evita a duplicação desnecessária de dados nas tabelas.
2. **Eliminar anomalias de atualização:** Garante a consistência dos dados nas operações de escrita.
3. **Preservar a integridade lógica:** Assegura que as dependências funcionais são mantidas e fáceis de validar.
4. **Simplificar a manutenção:** O modelo de dados torna-se mais flexível a evoluções futuras.

#### ⚡ Impacto no Desempenho (Relação OLTP vs OLAP):
O processo de normalização tem um impacto misto na performance física do sistema:
- **Nas operações de Leitura e Consulta (OLAP / Relatórios):** O desempenho pode ser **prejudicado**. Como a informação é distribuída por múltiplas tabelas mais pequenas e específicas, as consultas necessitam de efetuar muitas junções (`JOIN`), o que aumenta significativamente a carga de processamento do CPU e o número de operações de leitura física em disco (I/O).
- **Nas operações de Escrita e Modificação (OLTP / Transacional):** O desempenho é **otimizado**. Como não há dados redundantes (duplicados) para sincronizar, os comandos `INSERT`, `UPDATE` e `DELETE` são processados mais rapidamente, ocorrem num único local físico e reduzem o risco de bloqueios concorrentes em base de dados (*locks*).

---

### ❓ "Descreva os três tipos de Anomalias de Atualização com exemplos práticos." ⭐ (Pergunta 3 do TOP 15)

Quando um esquema relacional contém dados redundantes (não normalizados), podem ocorrer três anomalias operacionais graves no dia a dia:

1. **Anomalia de Inserção:** Ocorre quando é impossível introduzir determinada informação válida na BD por falta de outra informação independente.
   - *Exemplo:* Numa tabela que junta `Estudante` e `Disciplina`, não é possível registar a existência de uma nova disciplina na base de dados (ex: "Bases de Dados II") enquanto não houver pelo menos um estudante matriculado nessa disciplina.
2. **Anomalia de Remoção (Eliminação):** Ocorre quando a eliminação de um registo provoca, involuntariamente, a perda irreversível de outras informações úteis e distintas.
   - *Exemplo:* Se apagarmos o único estudante matriculado na disciplina de "Criptografia", o SGBD apagará a linha inteira da tabela, destruindo também os dados da própria disciplina (código, nome e docente).
3. **Anomalia de Modificação (Atualização):** Ocorre quando a alteração de um dado redundante exige a atualização de múltiplas linhas na base de dados. Se a atualização não for efetuada em todas as linhas correspondentes, a BD entra num estado inconsistente e contraditório.
   - *Exemplo:* Se o nome de um fornecedor estiver gravado em 500 linhas de produtos e o fornecedor mudar de nome, o sistema tem de atualizar as 500 linhas. Caso o processo falhe a meio, haverá dados inconsistentes sobre o mesmo fornecedor.

---

### ❓ "Defina e enuncie cada uma das Formas Normais (UNF a FNBC)" ⭐ (Pergunta 7 de exame — 3 val.!)

Nas provas de exame, **é obrigatório enunciar a definição teórica** da forma normal correspondente antes de resolver o exercício de fatura.

*   **Forma Não Normalizada (UNF / FNN):** Uma tabela que contém um ou mais **grupos repetitivos** (atributos multi-valor ou tabelas aninhadas).
*   **Primeira Forma Normal (1FN):**
    > *Definição:* Uma relação está na 1FN se e só se a intersecção entre qualquer linha e coluna contém **um e um só valor atómico** (indivisível). Não devem existir grupos repetitivos.
    *   *Como obter:* "Achatar" a tabela, repetindo os dados do cabeçalho para cada linha de detalhe do grupo repetido, e definir uma Chave Primária composta.
*   **Segunda Forma Normal (2FN):**
    > *Definição:* Uma relação está na 2FN se e só se está na 1FN e todos os atributos não primos (que não pertencem à chave primária) são **totalmente dependentes** de qualquer chave candidata da relação.
    *   *Como obter:* Identificar e extrair **dependências parciais** (atributos que dependem de apenas uma parte de uma chave primária composta) para novas tabelas.
*   **Terceira Forma Normal (3FN):**
    > *Definição:* Uma relação está na 3FN se e só se está na 2FN e nenhum atributo não primo depende **transitivamente** de qualquer chave candidata.
    *   *Como obter:* Identificar e extrair **dependências transitivas** (um atributo não primo determina outro atributo não primo, $A \rightarrow B \rightarrow C$) para tabelas separadas.
*   **Forma Normal Boyce-Codd (FNBC / BCNF):**
    > *Definição:* Uma relação está na FNBC se e só se para todas as dependências funcionais da forma $X \rightarrow Y$, o determinante **$X$ é uma chave candidata** da relação. (Uma 3FN mais restrita que resolve problemas de chaves primárias compostas sobrepostas).

---

### ❓ "O que é a Desnormalização de dados e em que cenários se justifica?"

A **desnormalização** é o processo intencional de reintroduzir alguma redundância controlada no esquema de base de dados já normalizado (revertendo etapas da 3FN/2FN).

*   **Objetivo principal:** Otimizar e acelerar o **desempenho das operações de leitura** (consultas).
*   **Como funciona:** Adiciona campos redundantes ou tabelas pré-agrupadas na BD, eliminando a necessidade de efetuar junções (`JOIN`s) complexas em tempo de execução.
*   **Sistemas típicos:**
    - Sistemas analíticos e Data Warehouses.
    - Aplicações com elevado rácio de leitura/escrita (ex: contadores de visualizações de artigos, feeds de redes sociais).

---

### 🧠 Como funciona a Normalização (Explicação Intuitiva)

Se nunca percebeste normalização, pensa nisto como **organizar o guarda-roupa para não teres coisas repetidas nem misturadas**:

- **UNF (O Caos):** Tens um papel gigante (a Fatura) onde escreveste tudo sobre a empresa, o cliente, e uma lista de 10 artigos comprados. O problema? Se o cliente comprar de novo amanhã, vais escrever o nome, morada e NIF dele tudo outra vez. Se quiseres mudar a morada do cliente, tens de procurar **todas** as faturas dele para mudar.
- **1FN (A Lista Plana):** A base de dados não lida bem com "listas de artigos" dentro de uma fatura. Então, na 1FN, "achatas" tudo. Se a fatura tem 3 artigos, passas a ter 3 linhas separadas para essa fatura na base de dados. Cada linha tem as informações da fatura TODAS repetidas + as informações de um dos artigos.
- **2FN (Cortar o que depende só de "metade"):** Reparas que a chave principal agora é composta por `(NumFatura + CodArtigo)` para identificar cada linha. Mas pensas: "Espera, o **Nome do Artigo** só depende do `CodArtigo`! Não faz sentido repeti-lo 50 vezes cada vez que ele aparece numa fatura diferente." Então, pegas no que **só depende do artigo** e crias a tabela `Artigo`. Fazes o mesmo para o que só depende da fatura (criando a tabela `Fatura`). 
  - *Resumo 2FN: O que só depende de uma parte da Chave Primária vai para a sua própria tabela.*
- **3FN (Tirar os "penduras"):** Olhas para a tua nova tabela `Fatura` e vês o NIF do Cliente, Nome do Cliente e Morada do Cliente. Tudo depende do `NumFatura`, certo? Sim, mas... o Nome e Morada do Cliente na verdade dependem do **NIF do Cliente**, e é o NIF que depende da Fatura! Isto é uma dependência "em cadeia" ou **transitiva** (`Fatura → NIF Cliente → Nome Cliente`). Então, pegas nos "penduras" e crias uma tabela `Cliente`.
  - *Resumo 3FN: Tudo tem de depender APENAS da Chave Primária e de mais nada. Se o atributo B depende do atributo A (e nenhum deles é chave), vão para uma tabela nova!*

### 📝 MÉTODO DO PROFESSOR: Normalização por Atributos (passo a passo)

> ⚠️ **O professor quer que identifiques os atributos (com letras), escrevas a chave primária, e mostres as dependências funcionais com setas (→) em cada passo. NÃO saltes direto para tabelas!**

#### Passo 0 — Analisar o documento e identificar TODOS os atributos

Olha para a fatura/documento e lista **todos** os dados que encontras. Atribui uma **letra** a cada atributo para simplificar.

**Exemplo com a Fatura do exame 2024/2025:**

| Letra | Atributo |
|:---:|---|
| A | NIF_Empresa |
| B | Nome_Empresa |
| C | Morada_Empresa |
| D | CodPostal_Empresa |
| E | NumFatura |
| F | Data |
| G | Hora |
| H | NIF_Cliente |
| I | Mesa |
| J | Empregado |
| K | CodArtigo |
| L | Descrição_Artigo |
| M | Quantidade |
| N | Preço_Unitário |
| O | TaxaIVA |
| P | Subtotal_Linha |
| Q | Total_Fatura |
| R | MetodoPagamento |
| S | ATCUD |
| T | Incidência_IVA |
| U | Valor_IVA |

**Forma Não Normalizada (UNF):**

```
Fatura(A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U)
```

> **Definição UNF:** Uma tabela que contém um ou mais grupos repetidos.

---

#### Passo 1 — Converter para 1ª Forma Normal (1FN)

> **Definição 1FN:** Uma relação em que a intersecção entre uma linha e uma coluna contenha **um e um só valor**.

**Remover grupos repetidos:** Os artigos (K, L, M, N, O, P) repetem-se em cada fatura → achatar a tabela.

**Identificar a Chave Primária:**
- Uma fatura (E) tem vários artigos (K)
- **PK = (E, K)**

```
Fatura_1FN( E, K, A, B, C, D, F, G, H, I, J, L, M, N, O, P, Q, R, S, T, U )
             ↑PK↑
```

**Identificar TODAS as Dependências Funcionais:**

```
E → A, B, C, D, F, G, H, I, J, Q, R, S       (dependem só do nº fatura)
K → L, N, O                                    (dependem só do código do artigo)
E, K → M, P                                    (dependem da combinação fatura+artigo)
A → B, C, D                                    (NIF empresa determina nome, morada, cod.postal)
O → T, U                                       (taxa IVA determina incidência e valor)
```

---

#### Passo 2 — Converter para 2ª Forma Normal (2FN)

> **Definição 2FN:** Uma relação na 1FN onde todos os atributos não pertencentes à PK são **totalmente dependentes** de qualquer chave candidata.

**Identificar Dependências PARCIAIS** (atributos que dependem de PARTE da PK):

```
DEPENDÊNCIAS PARCIAIS IDENTIFICADAS:
┌─────────────────────────────────────────────────┐
│  E → A, B, C, D, F, G, H, I, J, Q, R, S       │  ← depende só de E (parte da PK)
│  K → L, N, O                                    │  ← depende só de K (parte da PK)
└─────────────────────────────────────────────────┘

DEPENDÊNCIA TOTAL (fica na tabela original):
┌─────────────────────────────────────────────────┐
│  E, K → M, P                                    │  ← depende da PK completa ✅
└─────────────────────────────────────────────────┘
```

**Resultado — Separar em tabelas pela decomposição das dependências parciais:**

```
Fatura( E, A, B, C, D, F, G, H, I, J, Q, R, S )        PK: E
Artigo( K, L, N, O )                                     PK: K
LinhaFatura( E, K, M, P )                                PK: (E, K)
                                                          FK: E → Fatura, K → Artigo
```

**Agora identificar Dependências TRANSITIVAS** nas tabelas da 2FN:

```
DEPENDÊNCIAS TRANSITIVAS NA TABELA Fatura:
┌─────────────────────────────────────────────────┐
│  E → A  e  A → B, C, D                          │  ← B, C, D dependem de A,
│                                                   │     não diretamente de E!
│  (transitiva: E → A → B, C, D)                  │
└─────────────────────────────────────────────────┘

DEPENDÊNCIAS TRANSITIVAS NA TABELA Artigo:
┌─────────────────────────────────────────────────┐
│  K → O  e  O → T, U                             │  ← T, U dependem de O,
│                                                   │     não diretamente de K!
│  (transitiva: K → O → T, U)                     │
└─────────────────────────────────────────────────┘
```

---

#### Passo 3 — Converter para 3ª Forma Normal (3FN)

> **Definição 3FN:** Uma relação na 2FN onde nenhum atributo não pertencente à PK depende **transitivamente** da PK.

**Remover as dependências transitivas → criar novas tabelas:**

```
TABELAS FINAIS NA 3FN:
══════════════════════════════════════════════════════

Empresa( A, B, C, D )                    PK: A (NIF_Empresa)

Fatura( E, F, G, A, H, I, J, Q, R, S )  PK: E (NumFatura)
                                          FK: A → Empresa
                                          FK: H → Cliente (se existir tabela)

Artigo( K, L, N, O )                     PK: K (CodArtigo)
                                          FK: O → TaxaIVA

LinhaFatura( E, K, M, P )               PK: (E, K)
                                          FK: E → Fatura
                                          FK: K → Artigo

TaxaIVA( O, T, U )                      PK: O (TaxaIVA)
══════════════════════════════════════════════════════
```

**Escrevendo com os nomes reais dos atributos:**

```
Empresa(NIF_Empresa, Nome_Empresa, Morada, CodPostal)
    PK: NIF_Empresa

Fatura(NumFatura, Data, Hora, NIF_Empresa, NIF_Cliente, Mesa, Empregado, Total, MetodoPagamento, ATCUD)
    PK: NumFatura
    FK: NIF_Empresa → Empresa

Artigo(CodArtigo, Descricao, PrecoUnitario, TaxaIVA)
    PK: CodArtigo
    FK: TaxaIVA → TaxaIVA

LinhaFatura(NumFatura, CodArtigo, Quantidade, Subtotal)
    PK: (NumFatura, CodArtigo)
    FK: NumFatura → Fatura
    FK: CodArtigo → Artigo

TaxaIVA(Taxa, Incidencia, ValorIVA)
    PK: Taxa
```

---

### 📝 Exemplo 2: Normalização da Pauta (BD-Todas-As-Perguntas)

#### Passo 0 — Listar todos os atributos da Pauta

```
Pauta: AnoLetivo, CódigoCurso, Curso, CódigoDocente, Docente,
       CódigoDisciplina, Disciplina, Ano, CódigoTipoProva, TipoProva,
       NºMatricula, NomeAluno, Nota, Obs, DescriçãoObs, Data, HorárioConsulta
```

#### Passo 1 — 1FN

**Chave Primária:** `(AnoLetivo, CódigoDisciplina, CódigoTipoProva, NºMatricula, Obs)`

**Dependências Funcionais identificadas:**

```
DEPENDÊNCIAS PARCIAIS (dependem de PARTE da PK):
  CódigoDisciplina → CódigoCurso, Curso, Disciplina, Ano
  CódigoTipoProva → TipoProva
  NºMatricula → NomeAluno
  Obs → DescriçãoObs
  AnoLetivo, CódigoDisciplina → CódigoDocente, Docente
  AnoLetivo, CódigoDisciplina, CódigoTipoProva → Data, HorárioConsulta

DEPENDÊNCIA TOTAL (PK completa):
  AnoLetivo, CódigoDisciplina, CódigoTipoProva, NºMatricula → Nota
```

#### Passo 2 — 2FN (remover dependências parciais)

```
Disciplina(CódigoDisciplina, Disciplina, CódigoCurso, Curso, Ano)       PK: CódigoDisciplina
TipoProva(CódigoTipoProva, TipoProva)                                   PK: CódigoTipoProva
Aluno(NºMatricula, NomeAluno)                                            PK: NºMatricula
Observações(Obs, DescriçãoObs)                                           PK: Obs
Regente(AnoLetivo, CódigoDisciplina, CódigoDocente, Docente)             PK: (AnoLetivo, CódigoDisciplina)
Consulta(AnoLetivo, CódigoDisciplina, CódigoTipoProva, Data, Horário)    PK: (AnoLetivo, CódigoDisciplina, CódigoTipoProva)
Nota(AnoLetivo, CódigoDisciplina, CódigoTipoProva, NºMatricula, Nota)    PK: (AnoLetivo, CódigoDisciplina, CódigoTipoProva, NºMatricula)
Pauta(AnoLetivo, CódigoDisciplina, CódigoTipoProva, NºMatricula, Obs)    PK: (AnoLetivo, CódigoDisciplina, CódigoTipoProva, NºMatricula, Obs)
```

**Identificar dependências TRANSITIVAS:**
```
Na tabela Disciplina:  CódigoDisciplina → CódigoCurso → Curso
Na tabela Regente:     (AnoLetivo,CódigoDisciplina) → CódigoDocente → Docente
```

#### Passo 3 — 3FN (remover dependências transitivas)

```
NOVAS TABELAS criadas:
  Curso(CódigoCurso, Curso)            PK: CódigoCurso
  Docente(CódigoDocente, Docente)      PK: CódigoDocente

TABELAS ALTERADAS (removem-se os atributos transitivos):
  Disciplina(CódigoDisciplina, Disciplina, CódigoCurso, Ano)
      FK: CódigoCurso → Curso
  Regente(AnoLetivo, CódigoDisciplina, CódigoDocente)
      FK: CódigoDocente → Docente
```

---

### 🎯 Resumo do Método (cheat sheet para o exame)

```
┌─────────────────────────────────────────────────────────────────┐
│  1. IDENTIFICAR ATRIBUTOS do documento (atribuir letras)        │
│  2. UNF: escrever TODOS os atributos numa só relação            │
│                                                                  │
│  3. 1FN: Definição + Identificar a CHAVE PRIMÁRIA               │
│          + Listar TODAS as dependências funcionais               │
│                                                                  │
│  4. 2FN: Definição + Identificar DEPENDÊNCIAS PARCIAIS          │
│          (atributos que dependem de PARTE da PK)                 │
│          → Separar em novas tabelas                              │
│          + Identificar DEPENDÊNCIAS TRANSITIVAS                  │
│                                                                  │
│  5. 3FN: Definição + Remover DEPENDÊNCIAS TRANSITIVAS           │
│          (A → B → C, onde C depende de B e não de A)             │
│          → Criar novas tabelas para os determinantes             │
│                                                                  │
│  6. Escrever tabelas finais com PK e FK identificadas           │
└─────────────────────────────────────────────────────────────────┘

DICA: Em cada forma normal, ESCREVE SEMPRE A DEFINIÇÃO antes de aplicar!
      O professor pede explicitamente: "Enuncie as definições de cada
      Forma Normal à medida que faz a normalização"
```

### Desnormalização
Processo de **adicionar redundância** para otimizar performance de leitura. Exemplo: tabela de publicações de blog (escrita uma vez, lida constantemente).

---

## 11. Desenho e Modelação de BD (Diagramas E/R)

### ❓ "Quais as fases do Ciclo de Desenvolvimento e Desenho de Bases de Dados?" ⭐ (EN 2021)

O desenvolvimento de uma aplicação de BD segue uma metodologia estruturada:

#### 1. Ciclo de Vida da Aplicação de BD:
1. **Planeamento da BD:** Definição dos objetivos, âmbito do projeto e recursos necessários.
2. **Definição do Sistema:** Especificação dos limites e interfaces com outros sistemas da organização.
3. **Recolha e Análise de Requisitos:** Entrevistas com utilizadores para perceber as necessidades de dados.
4. **Desenho da BD (Database Design):**
   - **Desenho Conceptual:** Criação de um modelo abstrato independente de tecnologia (ex: Diagrama E/R).
   - **Desenho Lógico:** Mapeamento do modelo conceptual para o modelo relacional (tabelas e chaves), independente do SGBD físico.
   - **Desenho Físico:** Implementação prática das tabelas, índices e partição de discos no SGBD selecionado.
5. **Seleção do SGBD (opcional):** Escolha do software de gestão (ex: SQL Server, Oracle).
6. **Desenho da Aplicação:** Interface do utilizador e programas de acesso aos dados.
7. **Prototipagem (opcional):** Construção de um modelo funcional para testes iniciais.
8. **Implementação:** Criação das tabelas físicas (DDL) e carregamento inicial.
9. **Conversão e Alimentação de Dados:** Migração de dados de sistemas antigos para a nova BD.
10. **Testes:** Validação de segurança, performance e correção lógica.
11. **Manutenção Operacional:** Monitorização do sistema em produção e ajustes evolutivos.

---

### ❓ "Classifique os tipos de atributos num Diagrama ER e descreva a sua representação gráfica." ⭐ (Pergunta 6 e 11 do TOP 15 — Normal 23/24)

Os atributos descrevem as características e propriedades das entidades e relacionamentos no modelo E/R. Na **Notação de Chen**, são classificados e representados graficamente da seguinte forma:

| Tipo de Atributo | Definição Lógica | Exemplo Real | Representação Gráfica (Chen) |
|---|---|---|---|
| **Simples (ou Atómico)** | Atributo indivisível que contém um único valor. | NIF, Código do Produto | **Elipse simples** com o nome do atributo, ligada à entidade. |
| **Composto** | Atributo que pode ser decomposto em subatributos mais específicos e independentes. | Morada $\rightarrow$ (Rua, Localidade, Código Postal) | **Elipse ramificada:** a elipse principal liga-se a elipses secundárias. |
| **Multi-valor** | Atributo que pode conter vários valores diferentes para um mesmo tuplo. | Telefone (um sócio pode ter vários números), Hobbies | **Elipse com contorno duplo** (dois círculos concêntricos). |
| **Derivado** | Atributo cujo valor não é armazenado, mas sim calculado dinamicamente a partir de outros dados. | Idade (calculada a partir da Data de Nascimento). | **Elipse com linha tracejada**. |

---

### ❓ "Explique a diferença entre Especialização e Generalização no Modelo ER." ⭐ (Pergunta 5 do Bónus TOP 15)

São duas abordagens complementares para modelar relações de herança e superclasses/subclasses no modelo de dados:

#### 1. Especialização
É um processo **descendente (top-down)**. Parte-se de uma entidade genérica (superclasse) e dividem-se as suas ocorrências em entidades mais específicas (subclasses) com atributos ou relacionamentos próprios.
- *Propósito:* Destacar as propriedades exclusivas de um subgrupo.
- *Exemplo:* A superclasse `Funcionario` especializa-se nas subclasses `Engenheiro` (com atributo *numero_carteira*) e `Motorista` (com atributo *carta_conducao*).

#### 2. Generalização
É um processo **ascendente (bottom-up)**. Identificam-se propriedades e atributos comuns em várias entidades distintas, agrupando-as numa única entidade geral e abstrata (superclasse) para simplificar o modelo.
- *Propósito:* Eliminar a redundância de atributos idênticos em múltiplos objetos.
- *Exemplo:* As entidades `Carro` e `Camiao` são generalizadas na superclasse `Veiculo` (que herda os atributos comuns como *Matrícula* e *Marca*).

---

## 12. Data Warehousing

### ❓ "Benefícios e problemas associados aos Data Warehouses" ⭐ (Pergunta 6 e 8 do TOP 15 — Exame 2024/2025)

**Definição (Inmon, 1993):** Um Data Warehouse (DW) é uma coleção de dados **orientada a assuntos**, **integrada**, **variável no tempo** (histórica) e **não-volátil**, projetada especificamente para apoiar o processo de tomada de decisão da administração.

#### Benefícios:
- **Integração de Dados Heterogéneos:** Consolida informação limpa e padronizada proveniente de múltiplas fontes distintas (ficheiros, ERPs, BDs transacionais operacionais).
- **Isolamento de Performance (OLAP vs OLTP):** Evita que consultas analíticas pesadas e complexas de leitura (`OLAP`) degradem o desempenho dos sistemas operacionais transacionais do dia a dia (`OLTP`).
- **Análise de Tendências Históricas:** Permite avaliar o histórico a longo prazo (anos), ao contrário dos sistemas operacionais que apenas armazenam dados correntes/recentes.
- **Apoio Inteligente à Decisão:** Aumenta a qualidade das decisões de gestão através de dados padronizados e agregados.

#### Problemas e Desafios:
- **Custo e Tempo Elevados:** Projetos de longa duração com custos de infraestrutura física, licenciamento e implementação muito elevados.
- **Complexidade de ETL:** Os processos de Extração, Transformação e Carregamento (ETL) são complexos de modelar e muito propensos a falhas de consistência e limpeza de dados.
- **Manutenção Elevada e Contínua:** Dificuldade em manter os dados atualizados no DW sempre que os esquemas das tabelas dos sistemas operacionais de origem mudam.
- **Subestimação de Recursos:** Subestimação frequente do tempo e recursos necessários para o carregamento inicial de dados históricos.

---

### 5 Fluxos de Dados num Data Warehouse

| Fluxo | Descrição do Processo |
|---|---|
| **Inflow** | Extração, limpeza, transformação e carregamento dos dados das fontes para a BD analítica (ETL). |
| **Upflow** | Sumarização, agregação lógicas e distribuição dos dados para acelerar as consultas. |
| **Downflow** | Processos de arquivo histórico, compressão e realização de cópias de segurança (backups). |
| **Outflow** | Disponibilização final dos dados aos utilizadores (através de relatórios, dashboards, OLAP). |
| **Metaflow** | Gestão de metadados, ou seja, documentação de como as fontes são mapeadas no DW. |

---

### ❓ "Distinga um Data Warehouse de um Data Mart." ⭐ (Pergunta 6 do Exame Modelo 1)

| Característica | Data Warehouse (DW) | Data Mart (DM) |
|---|---|---|
| **Âmbito dos Dados** | Corporativo e global (toda a organização). | Focado num único departamento ou assunto específico (ex: Vendas, Finanças, Marketing). |
| **Fontes de Dados** | Muitas fontes heterogéneas. | Poucas fontes (normalmente extraído a partir do próprio DW central). |
| **Complexidade** | Elevada complexidade de desenho e manutenção. | Mais simples de modelar e implementar. |
| **Custo e Tempo** | Elevado investimento e desenvolvimento demorado. | Mais económico e rápido de implementar. |
| **Utilizadores** | Administradores, analistas globais de negócio. | Equipas setoriais específicas (ex: equipa de Vendas). |

---

## 13. BD Distribuídas e Paralelas

### 📋 4 Estratégias de Alocação de Dados (BD Distribuídas)
Consiste em decidir onde posicionar fisicamente os dados e fragmentos na rede de computadores:

| Estratégia | Descrição | Vantagens | Desvantagens |
|---|---|---|---|
| **Centralizada** | Uma única base de dados física e um SGBD localizados num único nó (*site*) da rede. Todos os outros nós acedem via rede. | Fácil de administrar e manter a consistência; custo reduzido. | Ponto único de falha; elevado tráfego de rede; problemas de lentidão. |
| **Particionada** | A base de dados é dividida em fragmentos disjuntos (sem interseção). Cada fragmento é colocado no nó onde é mais utilizado. | Dados perto do utilizador; processamento local rápido; sem duplicações. | Consultas globais lentas (requer ler dados de vários nós). |
| **Replicação Completa** | Guarda-se uma cópia idêntica da base de dados completa em todos os nós da rede. | Tolerância máxima a falhas; leituras locais extremamente rápidas. | Custo de escrita altíssimo (tem de atualizar em todos os nós de forma síncrona). |
| **Replicação Seletiva** | Combinação das anteriores: alguns dados críticos são replicados, outros são apenas particionados. | Equilíbrio e flexibilidade de recursos. | Gestão e desenho de software muito complexos. |

---

### ✂️ Fragmentação de Dados
Consiste em dividir uma relação lógica (tabela) em pedaços menores (fragmentos) para distribuição física:

1. **Fragmentação Horizontal:** Divide as linhas (tuplos) da tabela usando uma condição de seleção ($\sigma$).
   - *Exemplo:* Separar a tabela `Clientes` em `Clientes_Norte` e `Clientes_Sul`.
2. **Fragmentação Vertical:** Divide as colunas (atributos) da tabela usando uma projeção ($\pi$). Exige manter a PK original em todos os fragmentos para permitir a reconstrução da tabela.
   - *Exemplo:* Separar a tabela `Funcionarios` em `Func_DadosPessoais(codF, nome, morada)` e `Func_Vencimentos(codF, salario)`.
3. **Fragmentação Mista (Híbrida):** Combinação recursiva de fragmentações horizontais e verticais.

**Razões para fragmentar:**
- **Uso Local:** As aplicações acedem a vistas parciais (ex: cada agência só lê os seus clientes).
- **Eficiência e Performance:** Reduz o volume de dados a ler em cada nó e permite processamento paralelo.
- **Segurança:** Isola dados sensíveis num único nó controlado.

**Desvantagens:**
- Dificulta a validação de restrições de integridade referencial distribuídas.
- Junções de tabelas fragmentadas em nós diferentes deterioram a performance de rede.

---

### ❓ "Quais as arquiteturas físicas de SGBDs Paralelos?" ⭐ (TOP 15)
SGBDs paralelos procuram alta performance e disponibilidade combinando múltiplos processadores:

- **Memória Partilhada (Shared Memory):** Todos os processadores partilham uma única memória RAM comum e o mesmo conjunto de discos.
  - *Vantagem:* Muito fácil de programar; comunicação inter-processo muito rápida.
  - *Desvantagem:* Baixa escalabilidade (gargalo de barramento no acesso à memória).
- **Disco Partilhado (Shared Disk):** Cada processador tem a sua própria memória RAM privada, mas todos partilham o acesso aos mesmos discos de armazenamento.
  - *Vantagem:* Simplifica a tolerância a falhas (nós falham mas os dados em disco mantêm-se acessíveis).
  - *Desvantagem:* Gargalo na rede de armazenamento de discos.
- **Nada Partilhado (Shared Nothing):** Cada processador tem a sua própria memória RAM privada **e** o seu próprio disco rígido privado. Comunicam exclusivamente por troca de mensagens na rede de alta velocidade.
  - *Vantagem:* **Escalabilidade quase ilimitada** (adicionar nós aumenta linearmente a performance).
  - *Desvantagem:* Muito difícil de programar e gerir a consistência transacional distribuída.

---

### ❓ "Explique o funcionamento do Protocolo Two-Phase Commit (2PC)" ⭐ (Pergunta Clave de Transações Distribuídas)

O protocolo **Two-Phase Commit (2PC)** é um algoritmo de consenso usado em sistemas de bases de dados distribuídas para garantir a **atomicidade** das transações distribuídas (que afetam múltiplos nós físicos). Assegura que ou todos os nós gravam as alterações em disco (*commit*) ou todos revertem (*rollback*).

O processo é gerido por um nó **Coordenador** que comunica com vários nós **Participantes** em duas fases:

#### 1ª Fase: Preparação (Prepare Phase)
1. O Coordenador envia uma mensagem de `PREPARE` (preparar para commit) a todos os nós participantes.
2. Cada participante executa localmente a transação até ao ponto de escrita, grava as alterações de forma segura no seu log de transações local (na memória não-volátil), e decide se pode efetivar a operação.
3. Cada participante responde ao coordenador:
   - **`VOTE_COMMIT` (YES):** Se a operação local ocorreu sem erros e o nó está pronto para gravar em definitivo.
   - **`VOTE_ABORT` (NO):** Se ocorreu algum erro local (ex: falha de restrição ou falha física).

#### 2ª Fase: Confirmação (Commit Phase)
- **Cenário A (Sucesso - Todos votaram YES):**
  1. O Coordenador decide fazer `COMMIT` global e envia a mensagem a todos os participantes.
  2. Cada participante torna as alterações definitivas localmente, liberta os locks e responde `ACKNOWLEDGEMENT` (ACK) ao coordenador.
  3. A transação distribuída é concluída com sucesso.
- **Cenário B (Falha - Pelo menos um votou NO ou ocorreu timeout):**
  1. O Coordenador decide fazer `ABORT` global e envia a mensagem a todos os participantes.
  2. Cada participante desfaz e reverte localmente todas as escritas da transação (`ROLLBACK`), liberta os locks e responde `ACK` ao coordenador.
  3. A transação distribuída é abortada com segurança.

---

## 14. Exercícios Tipo Exame (com resolução)

> **Os exercícios foram movidos para um documento separado para facilitar o estudo.**
> 👉 **[Abrir Documento de Exercícios (Exercicios_Exames_BD.md)](Exercicios_Exames_BD.md)**

---

## 📊 Análise de Frequência — Perguntas que Saíram nos 3 Exames

| Tema | 2020/2021 | 2022/2023 | 2024/2025 | Frequência |
|---|:---:|:---:|:---:|:---:|
| **Integridade Referencial (ON DELETE/UPDATE)** | ✅ | ✅ | ✅ | **3/3** 🔴 |
| **Normalização de Fatura** | ✅ | ✅ | ✅ | **3/3** 🔴 |
| **SQL prático (SELECT + JOIN + GROUP BY)** | ✅ | ✅ | ✅ | **3/3** 🔴 |
| **Álgebra Relacional prática** | ✅ | ✅ | ✅ | **3/3** 🔴 |
| **Vistas (Views)** | ✅ | — | ✅ | **2/3** 🔴 |
| **Triggers** | ✅ | — | ✅ | **2/3** 🔴 |
| **Independência de Dados / Arq. ANSI/SPARC** | — | ✅ | — | **1/3** 🟡 |
| **Cliente-Servidor 2 vs 3 níveis** | — | ✅ | — | **1/3** 🟡 |
| **Subquery vs Junção** | — | ✅ | — | **1/3** 🟡 |
| **Anomalias de atualização** | ✅ | — | — | **1/3** 🟡 |
| **Tipos de Join (Natural, Theta, Outer)** | ✅ | — | — | **1/3** 🟡 |
| **Atributos em diag. E/R** | — | ✅ | — | **1/3** 🟡 |
| **Abordagens múltiplas vistas** | — | ✅ | — | **1/3** 🟡 |
| **LMD Procedimental vs Não-Procedimental** | — | — | ✅ | **1/3** 🟡 |
| **Normalização (teoria + objetivos)** | — | — | ✅ | **1/3** 🟡 |
| **Data Warehouses** | — | — | ✅ | **1/3** 🟡 |
| **Metodologia de desenvolvimento BD** | ✅ | — | — | **1/3** 🟡 |
| **Diag. E/R + tabela associativa** | — | ✅ | ✅ | **2/3** 🔴 |

---

## 📌 Resumo Rápido — O que Estudar por Prioridade

### 🔴 Prioridade MÁXIMA (saiu em TODOS os exames):
1. **Integridade Referencial** — ON DELETE / ON UPDATE (CASCADE, SET NULL, SET DEFAULT, NO ACTION)
2. **Normalização de Fatura** — UNF → 1FN → 2FN → 3FN com dependências funcionais (3 val.!)
3. **SQL prático** — SELECT com JOIN, GROUP BY, HAVING, subqueries
4. **Álgebra Relacional prática** — seleção, projeção, junção, diferença
5. **Diagrama E/R + Tabela Associativa** — identificar PK, FK, atributos

### 🟠 Prioridade Alta (saiu em 2 de 3 exames):
6. **Vistas (Views)** — definição, diferenças de relação base, materialização
7. **Triggers** — definição, tipos (BEFORE/AFTER/INSTEAD OF), vantagens/desvantagens

### 🟡 Prioridade Média (saiu em 1 de 3 exames — pode calhar!):
- LMD Procedimental vs Não-Procedimental
- Independência de dados / Arquitetura ANSI/SPARC
- Cliente-Servidor 2 vs 3 níveis
- Subquery vs Junção
- Anomalias de atualização
- Tipos de Join
- Atributos em diagramas E/R
- Data Warehouses
- Metodologia de desenvolvimento de BD
- Abordagens para múltiplas vistas de utilizadores

### 🟢 Complementar (nunca saiu nos 3 exames analisados, mas está na matéria):
- Conceitos fundamentais (BD, SGBD, Metadados, System Catalog)
- BD Distribuídas e Paralelas
- Cursores SQL
- SGBD Orientados a Objetos
- Stored Procedures vs Funções
- Controlo de concorrência
- Transações (COMMIT/ROLLBACK)

---

## 🧠 Dicas para o Exame

1. **Normalização vale SEMPRE 3 valores** — saiu nos 3 exames analisados! Pratica com faturas reais
2. **SQL + Álgebra Relacional valem 4-5 valores** — treina queries com JOIN, GROUP BY, HAVING
3. **Integridade Referencial sai SEMPRE** — memoriza as 4 ações (CASCADE, SET NULL, SET DEFAULT, NO ACTION)
4. **Nas perguntas teóricas:** dá definições claras + exemplos sempre que possível
5. **Tempo:** 2h para 8 perguntas ≈ 15 min/pergunta; a normalização e SQL/ÁR precisam de mais tempo
6. **Sem consulta** — memoriza as definições das Formas Normais e sabe fazer o processo passo a passo
7. **Padrão do exame:** 6 perguntas teóricas (2 val. cada = 12 val.) + normalização (3 val.) + SQL/ÁR (5 val.)
8. **Álgebra Relacional com DIFERENÇA (−)** apareceu em todos os exames — domina o padrão "quais os X que NÃO..."

---

> 💡 **Nota:** Este guia foi gerado a partir dos slides PPS (Aula 1–11), do documento "BD-Todas-As-Perguntas", dos helpers (normalização, T-SQL, fatura), e dos exames de **2020/2021**, **2022/2023** e **2024/2025** como referência.
