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
14. [Exercícios de Aprendizagem de Conceitos (Aprender Conceitos)](Exercicios_Aprendizagem_Conceitos_BD.md)
15. [Exercícios Tipo Exame (Praticar Exames)](Exercicios_Exames_BD.md)

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


### 📝 Exercícios Reais de Exames Anteriores
##### ❓ Pergunta Real de Exame: LMD Procedimentais vs Não-Procedimentais
7. **LMD Procedimentais vs Não-Procedimentais**
   - *Origem:* Exame 2024/2025 (Normal - Q1), Exame 2025/2026 (Normal - Q2), Exame Modelo 2 (Q4).
   - *Enunciado:* Explique as diferenças existentes entre Linguagens de Manipulação de Dados (LMD) procedimentais e não-procedimentais (declarativas). Dê exemplos de linguagens/construções que conheça para cada tipo.

<details>
<summary><b>🔑 Ver Resolução Oficial</b></summary>

7. **LMD Procedimentais vs Não-Procedimentais:**
   - **LMD Procedimentais:** O utilizador especifica **como** obter os dados, detalhando o fluxo lógico de processamento passo a passo. Atua sob o modelo de processamento registo a registo (*one-record-at-a-time*). Exemplos: Álgebra Relacional, blocos de código procedimentais e cursores (T-SQL/PL-SQL).
   - **LMD Não-Procedimentais (Declarativas):** O utilizador especifica apenas **o que** quer obter, sem indicar o caminho físico. O otimizador de consultas do SGBD determina o melhor plano físico. Atua sob o modelo de conjunto de dados (*set-at-a-time*). Exemplos: Instrução SELECT de SQL, Cálculo Relacional.
</details>


##### ❓ Pergunta Real de Exame: Definições Fundamentais: BD, SGBD e Metadados
12. **Definições Fundamentais: BD, SGBD e Metadados**
    - *Origem:* Exame 2025/2026 (Normal - Q1).
    - *Enunciado:* Defina os seguintes termos fundamentais no contexto de base de dados: a) Bases de Dados; b) Sistema de Gestão de Bases de Dados (identificando os seus componentes); c) Metadados.

<details>
<summary><b>🔑 Ver Resolução Oficial</b></summary>

12. **Definições Fundamentais: BD, SGBD e Metadados:**
    - **Bases de Dados (BD):** Coleção partilhada e logicamente organizada de dados inter-relacionados, concebida para satisfazer as necessidades de informação de uma organização.
    - **SGBD:** Sistema de software intermédio que permite definir, criar, manter e controlar o acesso à base de dados. Os seus 5 componentes fundamentais são: Hardware, Software, Dados, Utilizadores e Procedimentos.
    - **Metadados (System Catalog):** Dados que descrevem a estrutura e as características de outros dados (esquemas de tabelas, tipos de colunas, restrições, permissões), servindo de base para o funcionamento do SGBD.
</details>


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


### 📝 Exercícios Reais de Exames Anteriores
##### ❓ Pergunta Real de Exame: Conceito de Independência de Dados
1. **Conceito de Independência de Dados**
   - *Origem:* Exame 2022/2023 (Normal - Q1), Exame Recurso 2023/2024 (Q1), Exame Modelo 1 (Q1), Exame Modelo 2 (Q1).
   - *Enunciado:* Descreva o conceito de independência de dados e a sua importância num ambiente de bases de dados. Diferencie entre independência física e independência lógica de dados, fornecendo um exemplo prático para cada tipo.

<details>
<summary><b>🔑 Ver Resolução Oficial</b></summary>

1. **Conceito de Independência de Dados e sua Importância:**
   - **Independência de Dados:** É a capacidade de alterar o esquema de uma base de dados num determinado nível de abstração da arquitetura ANSI/SPARC sem a necessidade de reestruturar os níveis superiores (especialmente as aplicações e queries do utilizador).
   - **Independência Física de Dados:** Capacidade de modificar as estruturas de armazenamento físico (ex: criar um índice na tabela `Cliente` ou migrar ficheiros de dados para outro disco) sem necessidade de alterar o esquema lógico conceitual ou reescrever o código SQL das aplicações.
   - **Independência Lógica de Dados:** Capacidade de alterar a estrutura lógica da base de dados (ex: adicionar um atributo ou dividir a tabela `Funcionario` em duas tabelas distintas) sem quebrar o funcionamento das aplicações. Isto é habitualmente garantido recorrendo a vistas (views) que simulam o comportamento da tabela original.
   - **Importância:** Reduz significativamente os custos de manutenção de software, aumenta a flexibilidade evolutiva da base de dados e permite otimizar a performance física de forma transparente para os utilizadores e desenvolvedores.
</details>


##### ❓ Pergunta Real de Exame: Arquitetura Cliente-Servidor (2 vs 3 níveis)
2. **Arquitetura Cliente-Servidor (2 vs 3 níveis)**
   - *Origem:* Exame 2022/2023 (Normal - Q2), Exame Modelo 1 (Q2), Exame Modelo 2 (Q2).
   - *Enunciado:* Compare a arquitetura cliente-servidor de dois níveis com a de três níveis e identifique, justificando, qual a mais adequada para o ambiente Web.

<details>
<summary><b>🔑 Ver Resolução Oficial</b></summary>

2. **Arquitetura Cliente-Servidor (2 vs 3 níveis) e Adequabilidade para a Web:**
   - **Arquitetura de 2 Níveis (2-tier):** O cliente (fat client) comunica diretamente com o servidor de bases de dados, alojando a interface gráfica e processando as regras de negócio. O servidor apenas processa e valida os comandos SQL.
   - **Arquitetura de 3 Níveis (3-tier):** Introduz-se um servidor de aplicação intermédio entre o cliente (thin client / browser) e o servidor de bases de dados. O servidor aplicacional processa a lógica de negócio, e o cliente apenas renderiza a interface.
   - **Adequação para a Web:** A arquitetura de **3 níveis** é a única viável para a Web. Ela permite implementar **pooling de conexões** no servidor aplicacional, reutilizando conexões abertas com a BD para servir milhares de utilizadores concorrentes, enquanto no modelo de 2 níveis cada utilizador browser exigiria uma ligação permanente dedicada à BD, esgotando os recursos do SGBD de imediato. Além disso, centraliza as atualizações de lógica e protege os dados ao impedir o acesso direto das aplicações clientes à BD.
</details>


##### ❓ Pergunta Real de Exame: Desenho de BD com Múltiplas Vistas de Utilizadores
5. **Desenho de BD com Múltiplas Vistas de Utilizadores**
   - *Origem:* Exame 2022/2023 (Normal - Q5).
   - *Enunciado:* Enuncie e descreva sucintamente quais as três principais abordagens metodológicas para elaborar o desenho de uma base de dados quando existem múltiplas vistas de utilizadores.

<details>
<summary><b>🔑 Ver Resolução Oficial</b></summary>

5. **Desenho de BD com Múltiplas Vistas de Utilizadores:**
   - **Abordagem Centralizada:** Todos os requisitos de todas as vistas são recolhidos e fundidos numa lista única e global. A partir desta, desenha-se diretamente um esquema conceptual global unificado.
   - **Abordagem por Integração de Vistas:** Desenha-se um esquema conceptual local independente para cada vista ou departamento. Posteriormente, estes esquemas locais são integrados, harmonizados e fundidos num esquema conceptual global.
   - **Abordagem Mista:** Requisitos comuns e simples são consolidados centralizadamente no início, enquanto departamentos ou vistas altamente complexos e divergentes são desenhados de forma local e independente, integrando-se no modelo global na fase final.
</details>


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


### 📝 Exercícios Reais de Exames Anteriores
##### 💻 Exercício Real de Exame: Cenário Companhia Aérea e Reservas (Exame Modelo 1)
**Contexto / Esquema Relacional:**
```
Considere o seguinte esquema de dados de gestão de reservas de voos:
- `Aeroporto(codIATA, nome, cidade)`
- `Voo(numVoo, origem, destino, horaPartida, horaChegada)` -- FK: origem -> Aeroporto, destino -> Aeroporto
- `Passageiro(codPass, nome, email, pais)`
- `Reserva(codReserva, codPass, numVoo, dataViagem, classe, preco)`
```

**Enunciado da Questão:**
1. **Modelação / Integridade:** Identifique as chaves primária e estrangeiras da tabela `Reserva`. Justifique as suas escolhas com base no modelo relacional.

<details>
<summary><b>🔑 Ver Resolução Oficial</b></summary>

1. **Modelação / Integridade:**
   - **Chave Primária (PK):** `codReserva` na tabela `Reserva`. Identifica unicamente cada reserva de viagem.
   - **Chaves Estrangeiras (FK):** `codPass` que referencia `Passageiro(codPass)` para identificar qual o passageiro que viaja, e `numVoo` que referencia `Voo(numVoo)` para saber qual o voo reservado. Garantem a integridade referencial.
</details>


##### 💻 Exercício Real de Exame: Cenário Ginásio e Aulas (Exame Modelo 2)
**Contexto / Esquema Relacional:**
```
Considere o seguinte modelo de dados de gestão de sócios e aulas de um ginásio:
- `Socio(numSocio, nome, dataNasc, plano)` -- Planos: Básico, Premium, VIP
- `Instrutor(codInst, nome, especialidade)`
- `Aula(codAula, modalidade, diaSemana, horario, codInst)` -- FK: codInst -> Instrutor
- `Inscricao(numSocio, codAula, dataInscricao, presenca)` -- Presença: Sim/Não
```

**Enunciado da Questão:**
1. **Modelação / Integridade:** Identifique a chave primária e as chaves estrangeiras da tabela `Inscricao`. Justifique pormenorizadamente a escolha da chave primária composta.

<details>
<summary><b>🔑 Ver Resolução Oficial</b></summary>

1. **Modelação / Integridade:**
   - **Chave Primária (PK):** `(numSocio, codAula)` na tabela `Inscricao`. Justificação: Um sócio pode inscrever-se em várias aulas diferentes e uma aula pode acolher vários sócios (relacionamento M:N), mas cada sócio inscreve-se apenas uma vez em cada aula específica, tornando este par a chave mínima única.
   - **Chaves Estrangeiras (FK):** `numSocio` que referencia `Socio(numSocio)` e `codAula` que referencia `Aula(codAula)`.
</details>


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


### 📝 Exercícios Reais de Exames Anteriores
##### ❓ Pergunta Real de Exame: Tipos de Junção em Álgebra Relacional
17. **Tipos de Junção em Álgebra Relacional**
    - *Origem:* Exame 2020/2021 (Normal - Q5).
    - *Enunciado:* No contexto de Álgebra Relacional, explique pormenorizadamente as diferenças entre as operações de: Theta Join, Equijoin, Natural Join, Outer Join e Semijoin.

<details>
<summary><b>🔑 Ver Resolução Oficial</b></summary>

17. **Tipos de Junção em Álgebra Relacional:**
    - **Theta Join:** Combina linhas de duas tabelas com base numa condição geral ($=, >, <, \ge, \le, \ne$).
    - **Equijoin:** Caso particular do Theta Join onde a condição de correspondência usa exclusivamente a igualdade ($=$), mantendo ambas as colunas comparadas no resultado.
    - **Natural Join:** Junção por igualdade realizada automaticamente nas colunas homónimas das duas tabelas, removendo a coluna duplicada no resultado.
    - **Outer Join:** Junção que preserva os registos que não encontram correspondência na tabela relacionada, preenchendo as colunas vazias com `NULL` (`LEFT`, `RIGHT` ou `FULL`).
    - **Semijoin:** Devolve apenas os registos da primeira tabela que possuem correspondência na segunda tabela, sem expor as colunas da segunda no resultado.
</details>


##### 💻 Exercício Real de Exame: Cenário Artigos, Armazéns e Fornecedores (Exame 2020/2021)
**Contexto / Esquema Relacional:**
```
Considere o seguinte esquema de base de dados relacional:
- `Artigos(Código, Designação, Unidade, Preço)`
- `Armazéns(Código, Designação, Localização)`
- `Unidades(Código, Designação)`
- `ArtigosArmazéns(Artigos, Armazém, Localização, Stock)` -- FK: Artigos -> Artigos, Armazém -> Armazéns
- `Fornecedores(Número, Nome)`
- `FornecedoresArtigos(Fornecedor, Artigo)` -- FK: Fornecedor -> Fornecedores, Artigo -> Artigos
```

**Enunciado da Questão:**
1. **Álgebra Relacional:**
   - a) Apresente a expressão em Álgebra Relacional para listar os armazéns e respetivas localizações onde estão armazenados os artigos "Papel" e "Tinta" (em simultâneo).
   - b) Apresente a expressão em Álgebra Relacional para indicar quais os artigos (código) que não estão armazenados no "Armazém de reciclagem".

<details>
<summary><b>🔑 Ver Resolução Oficial</b></summary>

1. **Álgebra Relacional:**
   - a) Armazéns e localizações onde estão armazenados os artigos "Papel" e "Tinta":
     $$ArmazPapel \leftarrow \pi_{Armazém, Localização}(ArtigosArmazéns \bowtie ( \sigma_{Designação = 'Papel'}(Artigos) ))$$
     $$ArmazTinta \leftarrow \pi_{Armazém, Localização}(ArtigosArmazéns \bowtie ( \sigma_{Designação = 'Tinta'}(Artigos) ))$$
     $$Resultado \leftarrow ArmazPapel \cap ArmazTinta$$
   - b) Artigos que não estão no "Armazém de reciclagem":
     $$ArtigosReciclagem \leftarrow \pi_{Artigos}(ArtigosArmazéns \bowtie ( \sigma_{Designação = 'Armazém de reciclagem'}(Armazéns) ))$$
     $$TodosArtigos \leftarrow \pi_{Código}(Artigos)$$
     $$Resultado \leftarrow TodosArtigos - ArtigosReciclagem$$
</details>


##### 💻 Exercício Real de Exame: Cenário Estufas e Plantações (Exame Normal 2022/2023 e Recurso 2023/2024)
**Contexto / Esquema Relacional:**
```
Considere a base de dados simplista de uma empresa que gere estufas, secções e plantações de produtos:
- `Estufa(codE, descricao, capacidade, cidade)`
- `Secção(codigoS, tipo, estufa)` -- FK: estufa -> Estufa(codE)
- `Produto(codP, nome, stock, tipo)`
- `Plantação(codP, produto, codS, data_início, data_fim)` -- FK: produto -> Produto(codP), codS -> Secção(codigoS)
```

**Enunciado da Questão:**
2. **Álgebra Relacional (Normal 2022/2023):** Escreva a expressão em Álgebra Relacional para identificar quais as secções (código) que nunca tiveram qualquer plantação.

<details>
<summary><b>🔑 Ver Resolução Oficial</b></summary>

2. **Álgebra Relacional (Normal 2022/2023):** Secções que nunca tiveram plantações:
   $$SeccoesComPlantacao \leftarrow \pi_{codS}(Plantação)$$
   $$TodasSeccoes \leftarrow \pi_{codigoS}(Secção)$$
   $$Resultado \leftarrow TodasSeccoes - SeccoesComPlantacao$$
</details>


##### 💻 Exercício Real de Exame: Cenário Estufas e Plantações (Exame Normal 2022/2023 e Recurso 2023/2024)
**Contexto / Esquema Relacional:**
```
Considere a base de dados simplista de uma empresa que gere estufas, secções e plantações de produtos:
- `Estufa(codE, descricao, capacidade, cidade)`
- `Secção(codigoS, tipo, estufa)` -- FK: estufa -> Estufa(codE)
- `Produto(codP, nome, stock, tipo)`
- `Plantação(codP, produto, codS, data_início, data_fim)` -- FK: produto -> Produto(codP), codS -> Secção(codigoS)
```

**Enunciado da Questão:**
4. **Álgebra Relacional (Recurso 2023/2024):** Escreva a expressão em Álgebra Relacional para listar quais as estufas que tiveram mais de 3 plantações em todas as suas secções.

<details>
<summary><b>🔑 Ver Resolução Oficial</b></summary>

4. **Álgebra Relacional (Recurso 2023/2024):** Estufas com mais de 3 plantações em todas as suas secções (Método da dupla negação):
   $$SeccoesMaisDe3 \leftarrow \sigma_{Count > 3}( _{codS}\mathcal{G}_{Count(produto)}(Plantação) )$$
   $$Seccoes3OuMenos \leftarrow \pi_{codigoS}(Secção) - \pi_{codS}(SeccoesMaisDe3)$$
   $$EstufasComSeccaoInsuficiente \leftarrow \pi_{estufa}(Secção \bowtie_{codigoS = codigoS} Seccoes3OuMenos)$$
   $$TodasEstufas \leftarrow \pi_{codE}(Estufa)$$
   $$Resultado \leftarrow TodasEstufas - EstufasComSeccaoInsuficiente$$
</details>


##### 💻 Exercício Real de Exame: Cenário Fábrica e Ordens de Fabrico (Exame Normal 2024/2025)
**Contexto / Esquema Relacional:**
```
Considere o seguinte cenário de uma fábrica: as Ordens de Fabrico são associadas a um cliente (um cliente pode ter várias ordens). Cada Ordem de Fabrico contém uma lista de produtos a fabricar, registando a quantidade e a data de entrega prevista para cada um deles. Um produto pode ser fabricado em várias ordens e inclusive várias vezes na mesma ordem desde que a data de entrega seja diferente.
Estruturas básicas:
- `Cliente(CódigoCliente, Nome, NIF, DataCriação, Morada, País)`
- `OrdemFabrico(Número, Data, Cliente)` -- FK: Cliente -> Cliente
- `Produto(CódigoProduto, Nome, Familia)`
```

**Enunciado da Questão:**
3. **Álgebra Relacional:** Escreva a expressão em Álgebra Relacional para apresentar as famílias de produtos que não tiveram qualquer ordem de fabrico no primeiro trimestre de 2025 (1 de Janeiro a 31 de Março de 2025).

<details>
<summary><b>🔑 Ver Resolução Oficial</b></summary>

3. **Álgebra Relacional:** Famílias de produtos sem ordens no 1º trimestre de 2025:
   $$OrdensT1 \leftarrow \sigma_{Data \ge '2025-01-01' \wedge Data \le '2025-03-31'}(OrdemFabrico)$$
   $$ProdutosT1 \leftarrow \pi_{CódigoProduto}(OrdemFabricoProduto \bowtie OrdensT1)$$
   $$FamiliasComOrdem \leftarrow \pi_{Familia}(Produto \bowtie ProdutosT1)$$
   $$TodasFamilias \leftarrow \pi_{Familia}(Produto)$$
   $$Resultado \leftarrow TodasFamilias - FamiliasComOrdem$$
</details>


##### 💻 Exercício Real de Exame: Cenário Companhia Aérea e Reservas (Exame Modelo 1)
**Contexto / Esquema Relacional:**
```
Considere o seguinte esquema de dados de gestão de reservas de voos:
- `Aeroporto(codIATA, nome, cidade)`
- `Voo(numVoo, origem, destino, horaPartida, horaChegada)` -- FK: origem -> Aeroporto, destino -> Aeroporto
- `Passageiro(codPass, nome, email, pais)`
- `Reserva(codReserva, codPass, numVoo, dataViagem, classe, preco)`
```

**Enunciado da Questão:**
3. **Álgebra Relacional:** Escreva a expressão em Álgebra Relacional para indicar quais os aeroportos (código) que nunca foram destino de nenhum voo que tenha registado reservas.

<details>
<summary><b>🔑 Ver Resolução Oficial</b></summary>

3. **Álgebra Relacional:** Aeroportos que nunca foram destino de voos com reservas:
   $$TodosAeroportos \leftarrow \pi_{codIATA}(Aeroporto)$$
   $$VoosComReserva \leftarrow Voo \bowtie_{Voo.numVoo = Reserva.numVoo} Reserva$$
   $$DestinosComReserva \leftarrow \pi_{destino}(VoosComReserva)$$
   $$Resultado \leftarrow TodosAeroportos - DestinosComReserva$$
</details>


##### 💻 Exercício Real de Exame: Cenário Ginásio e Aulas (Exame Modelo 2)
**Contexto / Esquema Relacional:**
```
Considere o seguinte modelo de dados de gestão de sócios e aulas de um ginásio:
- `Socio(numSocio, nome, dataNasc, plano)` -- Planos: Básico, Premium, VIP
- `Instrutor(codInst, nome, especialidade)`
- `Aula(codAula, modalidade, diaSemana, horario, codInst)` -- FK: codInst -> Instrutor
- `Inscricao(numSocio, codAula, dataInscricao, presenca)` -- Presença: Sim/Não
```

**Enunciado da Questão:**
3. **Álgebra Relacional:** Escreva a expressão em Álgebra Relacional para identificar quais os sócios (número de sócio) com plano "VIP" que nunca se inscreveram em nenhuma aula da modalidade "Spinning".

---

<details>
<summary><b>🔑 Ver Resolução Oficial</b></summary>

3. **Álgebra Relacional:** Sócios VIP que nunca se inscreveram em Spinning:
   $$SociosVIP \leftarrow \pi_{numSocio}( \sigma_{plano = 'VIP'}(Socio) )$$
   $$AulasSpinning \leftarrow \sigma_{modalidade = 'Spinning'}(Aula)$$
   $$SociosComSpinning \leftarrow \pi_{numSocio}( Inscricao \bowtie AulasSpinning )$$
   $$Resultado \leftarrow SociosVIP - SociosComSpinning$$

---
</details>


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


### 📝 Exercícios Reais de Exames Anteriores
##### ❓ Pergunta Real de Exame: Subquery vs Junção (JOIN)
3. **Subquery vs Junção (JOIN)**
   - *Origem:* Exame 2022/2023 (Normal - Q3), Exame Modelo 1 (Q4).
   - *Enunciado:* Qual a diferença entre uma subquery e uma junção? Em que situações não é possível reescrever ou utilizar uma subquery em vez de uma junção? Ilustre com um exemplo prático.

<details>
<summary><b>🔑 Ver Resolução Oficial</b></summary>

3. **Subquery vs Junção (JOIN) e Limitações:**
   - **Diferença:** Uma subquery é uma instrução SELECT aninhada dentro de outra consulta externa para obter dados de suporte ou filtros temporários, enquanto uma junção combina dados de múltiplas tabelas na mesma linha com base numa condição de ligação.
   - **Limitações:** Não é possível utilizar uma subquery em situações em que o resultado final da consulta exige a **projeção simultânea de colunas pertencentes a tabelas distintas**. A subquery apenas atua filtrando registos; a projeção no SELECT exterior fica restrita à tabela principal. Também não pode ser usada em cenários de junções externas totais complexas que requeiram a preservação de dados órfãos de ambas as relações simultaneamente.
</details>


##### ❓ Pergunta Real de Exame: Restrições de Funções de Agregação e Valores Nulos
13. **Restrições de Funções de Agregação e Valores Nulos**
    - *Origem:* Exame Recurso 2023/2024 (Q2), Exame 2025/2026 (Normal - Q4).
    - *Enunciado:* Quais as restrições aplicadas ao uso de funções de agregação no comando SELECT? De que forma os valores nulos (NULL) afetam as funções de agregação?

<details>
<summary><b>🔑 Ver Resolução Oficial</b></summary>

13. **Restrições de Funções de Agregação e Valores Nulos:**
    - **Restrições:** Não podem ser usadas diretamente na cláusula `WHERE` (pois esta filtra linhas individuais antes do agrupamento). Se o SELECT projetar colunas individuais juntamente com funções de agregação, essas colunas individuais devem constar obrigatoriamente na cláusula `GROUP BY`.
    - **Comportamento com NULLs:** A função `COUNT(*)` contabiliza todas as linhas (incluindo nulos). Todas as restantes funções agregadas (`SUM`, `AVG`, `MIN`, `MAX`, `COUNT(coluna)`) ignoram completamente os valores nulos nos seus cálculos lógicos e matemáticos.
</details>


##### 💻 Exercício Real de Exame: Cenário Artigos, Armazéns e Fornecedores (Exame 2020/2021)
**Contexto / Esquema Relacional:**
```
Considere o seguinte esquema de base de dados relacional:
- `Artigos(Código, Designação, Unidade, Preço)`
- `Armazéns(Código, Designação, Localização)`
- `Unidades(Código, Designação)`
- `ArtigosArmazéns(Artigos, Armazém, Localização, Stock)` -- FK: Artigos -> Artigos, Armazém -> Armazéns
- `Fornecedores(Número, Nome)`
- `FornecedoresArtigos(Fornecedor, Artigo)` -- FK: Fornecedor -> Fornecedores, Artigo -> Artigos
```

**Enunciado da Questão:**
- b) Para os fornecedores que fornecem mais do que 5 artigos no total da base de dados, apresente o nome do fornecedor e o número de artigos fornecidos por esse fornecedor que possuem a unidade com designação "Caixa" (representada na tabela Artigos pela abreviatura correspondente). Ordene o resultado pelo número de artigos fornecidos de forma decrescente.

<details>
<summary><b>🔑 Ver Resolução Oficial</b></summary>

- b) Fornecedores com > 5 artigos no total com unidade 'Caixa':
     ```sql
     SELECT f.Nome AS Fornecedor, COUNT(fa.Artigo) AS NumArtigosCaixa
     FROM Fornecedores f
     INNER JOIN FornecedoresArtigos fa ON f.Número = fa.Fornecedor
     INNER JOIN Artigos a ON fa.Artigo = a.Código
     WHERE a.Unidade = (SELECT Código FROM Unidades WHERE Designação = 'Caixa')
       AND f.Número IN (
           SELECT fa2.Fornecedor
           FROM FornecedoresArtigos fa2
           GROUP BY fa2.Fornecedor
           HAVING COUNT(*) > 5
       )
     GROUP BY f.Número, f.Nome
     ORDER BY NumArtigosCaixa DESC;
     ```
</details>


##### 💻 Exercício Real de Exame: Cenário Estufas e Plantações (Exame Normal 2022/2023 e Recurso 2023/2024)
**Contexto / Esquema Relacional:**
```
Considere a base de dados simplista de uma empresa que gere estufas, secções e plantações de produtos:
- `Estufa(codE, descricao, capacidade, cidade)`
- `Secção(codigoS, tipo, estufa)` -- FK: estufa -> Estufa(codE)
- `Produto(codP, nome, stock, tipo)`
- `Plantação(codP, produto, codS, data_início, data_fim)` -- FK: produto -> Produto(codP), codS -> Secção(codigoS)
```

**Enunciado da Questão:**
1. **SQL (Normal 2022/2023):** Apresente a query em SQL para listar as estufas (código e descrição) que tiveram mais que 10 plantações do mesmo produto.

<details>
<summary><b>🔑 Ver Resolução Oficial</b></summary>

1. **SQL (Normal 2022/2023):** Estufas com mais que 10 plantações do mesmo produto:
   ```sql
   SELECT e.codE, e.descricao
   FROM Estufa e
   INNER JOIN Secção s ON e.codE = s.estufa
   INNER JOIN Plantação p ON s.codigoS = p.codS
   GROUP BY e.codE, e.descricao, p.produto
   HAVING COUNT(*) > 10;
   ```
</details>


##### 💻 Exercício Real de Exame: Cenário Estufas e Plantações (Exame Normal 2022/2023 e Recurso 2023/2024)
**Contexto / Esquema Relacional:**
```
Considere a base de dados simplista de uma empresa que gere estufas, secções e plantações de produtos:
- `Estufa(codE, descricao, capacidade, cidade)`
- `Secção(codigoS, tipo, estufa)` -- FK: estufa -> Estufa(codE)
- `Produto(codP, nome, stock, tipo)`
- `Plantação(codP, produto, codS, data_início, data_fim)` -- FK: produto -> Produto(codP), codS -> Secção(codigoS)
```

**Enunciado da Questão:**
3. **SQL (Recurso 2023/2024):** Escreva a query em SQL para apresentar quantas secções (número total de secções distintas) tiveram plantações de produtos do tipo "Fruta" cuja duração de cultivo (diferença entre `data_fim` e `data_início`) foi estritamente inferior a 28 dias.

<details>
<summary><b>🔑 Ver Resolução Oficial</b></summary>

3. **SQL (Recurso 2023/2024):** Secções com plantações de "Fruta" e duração < 28 dias:
   ```sql
   SELECT COUNT(DISTINCT p.codS) AS TotalSeccoes
   FROM Plantação p
   INNER JOIN Produto pr ON p.produto = pr.codP
   WHERE pr.tipo = 'Fruta'
     AND DATEDIFF(day, p.data_início, p.data_fim) < 28;
   ```
</details>


##### 💻 Exercício Real de Exame: Cenário Fábrica e Ordens de Fabrico (Exame Normal 2024/2025)
**Contexto / Esquema Relacional:**
```
Considere o seguinte cenário de uma fábrica: as Ordens de Fabrico são associadas a um cliente (um cliente pode ter várias ordens). Cada Ordem de Fabrico contém uma lista de produtos a fabricar, registando a quantidade e a data de entrega prevista para cada um deles. Um produto pode ser fabricado em várias ordens e inclusive várias vezes na mesma ordem desde que a data de entrega seja diferente.
Estruturas básicas:
- `Cliente(CódigoCliente, Nome, NIF, DataCriação, Morada, País)`
- `OrdemFabrico(Número, Data, Cliente)` -- FK: Cliente -> Cliente
- `Produto(CódigoProduto, Nome, Familia)`
```

**Enunciado da Questão:**
2. **SQL:** Escreva a query em SQL para apresentar os Países que têm mais de 10 clientes que colocaram Ordens de Fabrico no ano de 2024.

<details>
<summary><b>🔑 Ver Resolução Oficial</b></summary>

2. **SQL:** Países com mais de 10 clientes com Ordens de Fabrico em 2024:
   ```sql
   SELECT c.País, COUNT(DISTINCT c.CódigoCliente) AS TotalClientes
   FROM Cliente c
   INNER JOIN OrdemFabrico o ON c.CódigoCliente = o.Cliente
   WHERE o.Data >= '2024-01-01' AND o.Data <= '2024-12-31'
   GROUP BY c.País
   HAVING COUNT(DISTINCT c.CódigoCliente) > 10;
   ```
</details>


##### 💻 Exercício Real de Exame: Cenário Companhia Aérea e Reservas (Exame Modelo 1)
**Contexto / Esquema Relacional:**
```
Considere o seguinte esquema de dados de gestão de reservas de voos:
- `Aeroporto(codIATA, nome, cidade)`
- `Voo(numVoo, origem, destino, horaPartida, horaChegada)` -- FK: origem -> Aeroporto, destino -> Aeroporto
- `Passageiro(codPass, nome, email, pais)`
- `Reserva(codReserva, codPass, numVoo, dataViagem, classe, preco)`
```

**Enunciado da Questão:**
2. **SQL:** Escreva a query em SQL para apresentar quais os países que têm mais de 5 passageiros distintos com reservas em voos que tenham como destino a cidade do "Porto" no ano de 2026.

<details>
<summary><b>🔑 Ver Resolução Oficial</b></summary>

2. **SQL:** Países com mais de 5 passageiros para o Porto em 2026:
   ```sql
   SELECT p.pais, COUNT(DISTINCT p.codPass) AS TotalPassageiros
   FROM Passageiro p
   INNER JOIN Reserva r ON p.codPass = r.codPass
   INNER JOIN Voo v ON r.numVoo = v.numVoo
   INNER JOIN Aeroporto a ON v.destino = a.codIATA
   WHERE a.cidade = 'Porto'
     AND r.dataViagem >= '2026-01-01' AND r.dataViagem <= '2026-12-31'
   GROUP BY p.pais
   HAVING COUNT(DISTINCT p.codPass) > 5;
   ```
</details>


##### 💻 Exercício Real de Exame: Cenário Ginásio e Aulas (Exame Modelo 2)
**Contexto / Esquema Relacional:**
```
Considere o seguinte modelo de dados de gestão de sócios e aulas de um ginásio:
- `Socio(numSocio, nome, dataNasc, plano)` -- Planos: Básico, Premium, VIP
- `Instrutor(codInst, nome, especialidade)`
- `Aula(codAula, modalidade, diaSemana, horario, codInst)` -- FK: codInst -> Instrutor
- `Inscricao(numSocio, codAula, dataInscricao, presenca)` -- Presença: Sim/Não
```

**Enunciado da Questão:**
2. **SQL:** Escreva a query em SQL para identificar quais os instrutores (código e nome) que lecionam mais do que 3 aulas diferentes que tenham registado pelo menos 20 inscrições cada uma delas.

<details>
<summary><b>🔑 Ver Resolução Oficial</b></summary>

2. **SQL:** Instrutores com mais de 3 aulas com pelo menos 20 inscrições cada:
   ```sql
   SELECT codInst, nome
   FROM (
       SELECT i.codInst, i.nome, a.codAula
       FROM Instrutor i
       INNER JOIN Aula a ON i.codInst = a.codInst
       INNER JOIN Inscricao ins ON a.codAula = ins.codAula
       GROUP BY i.codInst, i.nome, a.codAula
       HAVING COUNT(*) >= 20
   ) AS AulasPopulares
   GROUP BY codInst, nome
   HAVING COUNT(*) > 3;
   ```
</details>


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


### 📝 Exercícios Reais de Exames Anteriores
##### 💻 Exercício Real de Exame: Cenário Fábrica e Ordens de Fabrico (Exame Normal 2024/2025)
**Contexto / Esquema Relacional:**
```
Considere o seguinte cenário de uma fábrica: as Ordens de Fabrico são associadas a um cliente (um cliente pode ter várias ordens). Cada Ordem de Fabrico contém uma lista de produtos a fabricar, registando a quantidade e a data de entrega prevista para cada um deles. Um produto pode ser fabricado em várias ordens e inclusive várias vezes na mesma ordem desde que a data de entrega seja diferente.
Estruturas básicas:
- `Cliente(CódigoCliente, Nome, NIF, DataCriação, Morada, País)`
- `OrdemFabrico(Número, Data, Cliente)` -- FK: Cliente -> Cliente
- `Produto(CódigoProduto, Nome, Familia)`
```

**Enunciado da Questão:**
1. **Modelação / DDL:** Identifique o nome, atributos e chave primária da tabela associativa necessária para modelar o relacionamento M:N descrito. Apresente o comando SQL DDL correspondente, garantindo a integridade referencial e que a quantidade seja estritamente positiva.

<details>
<summary><b>🔑 Ver Resolução Oficial</b></summary>

1. **Modelação / DDL:** A tabela associativa chama-se `OrdemFabricoProduto` e a sua chave primária é composta por `(Número, CódigoProduto, DataEntrega)`, uma vez que o mesmo produto pode ser fabricado na mesma ordem de fabrico desde que a data de entrega seja diferente.
   ```sql
   CREATE TABLE OrdemFabricoProduto (
       Número INT,
       CódigoProduto VARCHAR(50),
       Quantidade INT NOT NULL,
       DataEntrega DATE,
       PRIMARY KEY (Número, CódigoProduto, DataEntrega),
       FOREIGN KEY (Número) REFERENCES OrdemFabrico(Número),
       FOREIGN KEY (CódigoProduto) REFERENCES Produto(CódigoProduto),
       CONSTRAINT chk_quantidade_fabrico CHECK (Quantidade > 0)
   );
   ```
</details>


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


### 📝 Exercícios Reais de Exames Anteriores
##### ❓ Pergunta Real de Exame: Integridade Referencial e Ações Referenciais
4. **Integridade Referencial e Ações Referenciais**
   - *Origem:* Exame 2020/2021 (Normal - Q4), Exame 2022/2023 (Normal - Q4), Exame Modelo 1 (Q2).
   - *Enunciado:* Explique o que entende por Integridade Referencial. Identifique e descreva quais as ações que se podem utilizar nas subcláusulas `ON DELETE` e `ON UPDATE` de uma chave estrangeira.

<details>
<summary><b>🔑 Ver Resolução Oficial</b></summary>

4. **Integridade Referencial e Ações Referenciais:**
   - **Integridade Referencial:** Regra que garante a consistência das ligações entre tabelas, obrigando a que os valores de uma chave estrangeira (FK) na tabela filha existam previamente na chave primária (PK) da tabela pai, ou sejam nulos (caso a coluna o permita).
   - **Ações Referenciais (ON DELETE / ON UPDATE):**
     - **CASCADE:** Propaga a alteração ao registo pai diretamente para os registos filhos (ex: ao apagar o pai, apaga os filhos associados; ao alterar a PK do pai, atualiza a FK nos filhos).
     - **SET NULL:** Define a coluna FK de todos os registos filhos como nula (exige que a coluna permita nulos).
     - **SET DEFAULT:** Define a coluna FK de todos os registos filhos com o valor padrão (default) configurado.
     - **NO ACTION / RESTRICT:** Rejeita a eliminação ou atualização do registo pai caso existam registos filhos dependentes na base de dados.
</details>


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


### 📝 Exercícios Reais de Exames Anteriores
##### ❓ Pergunta Real de Exame: Vistas (Views) vs Relações Base
8. **Vistas (Views) vs Relações Base**
   - *Origem:* Exame 2020/2021 (Normal - Q1), Exame 2024/2025 (Normal - Q2), Exame 2025/2026 (Normal - Q3).
   - *Enunciado:* O que é uma vista (view) e quais as diferenças estruturais, lógicas e operacionais entre uma vista e uma relação base (tabela). Apresente duas razões que justifiquem o seu uso.

<details>
<summary><b>🔑 Ver Resolução Oficial</b></summary>

8. **Vistas (Views) vs Relações Base:**
   - **Vista:** Relação virtual definida por uma consulta SQL (SELECT) que é calculada dinamicamente pelo SGBD. Não consome armazenamento físico de dados, apenas metadados para guardar a definição da query.
   - **Relação Base:** Tabela física cujos dados são armazenados permanentemente em páginas físicas no disco.
   - **Razões de Uso:** a) **Segurança:** oculta colunas confidenciais de determinados utilizadores (ex: salários); b) **Simplificação:** abstrai queries complexas (com múltiplos JOINs) expondo-as de forma simples para os programadores.
</details>


##### ❓ Pergunta Real de Exame: Mecanismo de Resolução de Vistas (Query Modification)
14. **Mecanismo de Resolução de Vistas (Query Modification)**
    - *Origem:* Exame 2025/2026 (Normal - Q5).
    - *Enunciado:* Descreva como funciona o mecanismo de resolução de vistas (frequentemente designado por modificação de consultas ou query modification) no motor do SGBD.

<details>
<summary><b>🔑 Ver Resolução Oficial</b></summary>

14. **Mecanismo de Resolução de Vistas (Query Modification):**
    - O SGBD não armazena os dados da vista fisicamente. Quando o utilizador faz uma query à vista, o SGBD realiza uma **modificação de consulta (query modification)**, fundindo o SELECT do utilizador com a query de definição da vista guardada nos metadados. O plano de execução final é gerado a partir desta query fundida, que atua diretamente sobre as tabelas base.
</details>


##### ❓ Pergunta Real de Exame: Materialização de Vistas
19. **Materialização de Vistas**
    - *Origem:* Exame Recurso 2023/2024 (Q3), Exame Modelo 1 (Q5).
    - *Enunciado:* Explique o conceito de materialização de vistas. Quais as vantagens e desvantagens desta abordagem face às vistas tradicionais? Em que contextos é recomendável?

<details>
<summary><b>🔑 Ver Resolução Oficial</b></summary>

19. **Materialização de Vistas:**
    - Consiste em pré-calcular e armazenar fisicamente os resultados de uma query de vista em disco (Indexed/Materialized Views).
    - *Vantagens:* Leituras analíticas OLAP extremamente rápidas e redução drástica da carga de processamento.
    - *Desvantagens:* Overhead na escrita (o SGBD tem de atualizar e sincronizar a vista materializada a cada alteração nas tabelas base) e consumo extra de espaço de armazenamento físico.
    - *Recomendação:* Sistemas OLAP de BI/Data Warehousing com tabelas gigantescas que são muito consultadas e pouco modificadas.
</details>


##### 💻 Exercício Real de Exame: Cenário Artigos, Armazéns e Fornecedores (Exame 2020/2021)
**Contexto / Esquema Relacional:**
```
Considere o seguinte esquema de base de dados relacional:
- `Artigos(Código, Designação, Unidade, Preço)`
- `Armazéns(Código, Designação, Localização)`
- `Unidades(Código, Designação)`
- `ArtigosArmazéns(Artigos, Armazém, Localização, Stock)` -- FK: Artigos -> Artigos, Armazém -> Armazéns
- `Fornecedores(Número, Nome)`
- `FornecedoresArtigos(Fornecedor, Artigo)` -- FK: Fornecedor -> Fornecedores, Artigo -> Artigos
```

**Enunciado da Questão:**
- a) Crie uma vista que apresente, por artigo (código e designação), a quantidade total armazenada em cada armazém (código e designação).

<details>
<summary><b>🔑 Ver Resolução Oficial</b></summary>

- a) Vista de stock por artigo e armazém:
     ```sql
     CREATE VIEW v_StockArtigoArmazem AS
     SELECT a.Código AS CodArtigo, a.Designação AS Artigo, 
            am.Código AS CodArmazem, am.Designação AS Armazem,
            aa.Stock AS Quantidade
     FROM Artigos a
     INNER JOIN ArtigosArmazéns aa ON a.Código = aa.Artigos
     INNER JOIN Armazéns am ON aa.Armazém = am.Código;
     ```
</details>


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


### 📝 Exercícios Reais de Exames Anteriores
##### ❓ Pergunta Real de Exame: Triggers: Definição, Vantagens e Desvantagens
9. **Triggers: Definição, Vantagens e Desvantagens**
   - *Origem:* Exame 2020/2021 (Normal - Q3), Exame 2024/2025 (Normal - Q4), Exame Modelo 1 (Q3).
   - *Enunciado:* O que são triggers de bases de dados e para que servem? Indique as principais vantagens e desvantagens da sua utilização e identifique os diferentes tipos de triggers quanto ao momento de execução.

<details>
<summary><b>🔑 Ver Resolução Oficial</b></summary>

9. **Triggers: Definição, Vantagens e Desvantagens:**
   - **Definição:** Blocos de código procedural armazenados no SGBD que são executados automática e implicitamente como resposta a um evento DML (INSERT, UPDATE ou DELETE) numa tabela.
   - **Vantagens:** Centralização e automação de regras de negócio complexas, auditoria automática de dados e garantia rigorosa de consistência independentemente da aplicação cliente.
   - **Desvantagens:** Overhead de processamento que reduz a performance de escrita, possibilidade de efeitos em cascata difíceis de depurar e perda de portabilidade do código entre SGBDs.
   - **Momentos de Execução:** `BEFORE` (antes da validação/gravação), `AFTER` (após a gravação física) e `INSTEAD OF` (em vez da operação, usado para tornar vistas complexas atualizáveis).
</details>


##### ❓ Pergunta Real de Exame: Cursores SQL: Propósito e Ciclo de Vida
20. **Cursores SQL: Propósito e Ciclo de Vida**
    - *Origem:* Exame Recurso 2023/2024 (Q4), Exame Modelo 2 (Q3).
    - *Enunciado:* O que são cursores SQL? Qual o propósito da sua utilização? Descreva o ciclo de vida típico de um cursor, detalhando o que ocorre em cada uma das suas 5 fases.

<details>
<summary><b>🔑 Ver Resolução Oficial</b></summary>

20. **Cursores SQL: Propósito e Ciclo de Vida:**
    - **Propósito:** Permitir o processamento de registos de forma individual, linha a linha (registo a registo), no âmbito de blocos procedimentais.
    - **Ciclo de Vida (Fases):**
      1. *DECLARE:* Define o nome do cursor e a query SELECT associada.
      2. *OPEN:* Executa o SELECT e aloca memória para os registos obtidos.
      3. *FETCH:* Recupera a linha atual e avança o apontador para a seguinte.
      4. *CLOSE:* Fecha o cursor e liberta os bloqueios de escrita ativos.
      5. *DEALLOCATE:* Remove a definição do cursor da memória de forma definitiva.
</details>


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


### 📝 Exercícios Reais de Exames Anteriores
##### ❓ Pergunta Real de Exame: Objetivos da Normalização e Desempenho
10. **Objetivos da Normalização e Desempenho**
    - *Origem:* Exame Recurso 2023/2024 (Q5), Exame 2024/2025 (Normal - Q5), Exame Modelo 2 (Q5).
    - *Enunciado:* No contexto do modelo relacional, quais os objetivos da normalização de dados? De que forma o processo de normalização poderá afetar, posteriormente, o desempenho das leituras (OLAP) e das escritas (OLTP)?

<details>
<summary><b>🔑 Ver Resolução Oficial</b></summary>

10. **Objetivos da Normalização e Desempenho:**
    - **Objetivos:** Decompor tabelas complexas para eliminar a redundância de dados, evitar anomalias de atualização (inserção, remoção, modificação) e garantir a integridade das dependências funcionais.
    - **Impacto no Desempenho:**
      - **Leitura (OLAP):** O desempenho pode ser **prejudicado**, uma vez que os dados espalhados por tabelas menores exigem a realização de mais operações de junção (`JOIN`), aumentando o consumo de processamento e E/S de disco.
      - **Escrita (OLTP):** O desempenho é **otimizado**, porque as tabelas são mais estreitas, não existem duplicações a sincronizar e as atualizações ocorrem num único local.
</details>


##### ❓ Pergunta Real de Exame: Anomalias de Atualização
18. **Anomalias de Atualização**
    - *Origem:* Exame 2020/2021 (Normal - Q6).
    - *Enunciado:* Descreva os três tipos de anomalias de atualização que podem ocorrer numa relação que contém dados redundantes, acompanhando a descrição de exemplos práticos de como ocorrem.

<details>
<summary><b>🔑 Ver Resolução Oficial</b></summary>

18. **Anomalias de Atualização:**
    - **Inserção:** Impossibilidade de inserir dados úteis por falta de outra informação independente (ex: não conseguir registar uma nova disciplina na BD sem ter um aluno matriculado).
    - **Remoção:** Perda involuntária de informações valiosas ao eliminar um registo (ex: apagar o único aluno inscrito em Física e perder permanentemente a informação da própria disciplina, como os créditos).
    - **Modificação:** Inconsistência de dados se uma alteração não for propagada a todos os registos redundantes (ex: alterar a sala de um departamento para alguns funcionários mas falhar noutros).
</details>


##### 📋 Caso Real de Exame: Normalização - Fatura de Venda de Vinhos (Exame 2020/2021)
##### Caso III.1: Fatura de Venda de Vinhos (Exame 2020/2021)
Considere o seguinte exemplo de cabeçalho e linhas de uma fatura emitida por uma garrafeira:
- Fatura Nº: `24F347` | Data de emissão: `25 de Janeiro de 2017`
- NIF Cliente: `19293849` | Nome Cliente: `João Oliveira` | Cliente Sócio?: `Não`
- Emitido por: Funcionário `123 - João Castro`
- Produtos Faturados:
  - Código: `01FF` | Descrição: `Vinho de Porto` | Qtd: `4` | Preço Unitário: `8.5€`
  - Código: `03GG` | Descrição: `Vinho Moscatel` | Qtd: `3` | Preço Unitário: `7.5€`
- Resumo Financeiro:
  - Valor Total Bruto: `56.5€` (Nota: recalculado) | Desconto: `10%` | Valor Líquido a Cobrar: `14.4€` (Nota: no documento oficial, os valores servem apenas de exemplo ilustrativo)
  - Morada de entrega: `Rua de Lordelo, 4610, Felgueiras`
  - Método de pagamento: `Transferência`

<details>
<summary><b>🔑 Ver Resolução Oficial</b></summary>

##### Caso III.1 (Fatura de Venda de Vinhos)
1. **Identificação dos Atributos:**
   - `NumFatura` (A)
   - `Data` (B)
   - `NIF_Cliente` (C)
   - `Nome_Cliente` (D)
   - `Socio` (E)
   - `CodFuncionario` (F)
   - `NomeFuncionario` (G)
   - `CodProduto` (H)
   - `Descricao` (I)
   - `Quantidade` (J)
   - `PrecoUnitario` (K)
   - `Desconto` (L)
   - `ValorCobrar` (M)
   - `MoradaEntrega` (N)
   - `MetodoPagamento` (O)
2. **Forma Não Normalizada (UNF):**
   `Fatura_UNF(A, B, C, D, E, F, G, L, M, N, O, [H, I, J, K])`
3. **1ª Forma Normal (1FN):** Remoção de grupos repetidos. A PK passa a ser composta por `(NumFatura, CodProduto)`.
   `Fatura_1FN(NumFatura, CodProduto, B, C, D, E, F, G, J, K, I, L, M, N, O)`
   *Dependências Funcionais (DFs):*
   - $NumFatura \rightarrow B, C, D, E, F, G, L, M, N, O$
   - $CodProduto \rightarrow I, K$ (Assumindo que o preço unitário do vinho é fixo por artigo)
   - $NumFatura, CodProduto \rightarrow J$
   - $C \rightarrow D, E$ (Dados do cliente)
   - $F \rightarrow G$ (Dados do funcionário)
4. **2ª Forma Normal (2FN):** Remoção de dependências parciais sobre a PK `(NumFatura, CodProduto)`:
   - `Cabecalho_2FN(NumFatura, Data, NIF_Cliente, Nome_Cliente, Socio, CodFuncionario, NomeFuncionario, Desconto, ValorCobrar, MoradaEntrega, MetodoPagamento)` | PK: `NumFatura`
   - `Artigo_2FN(CodProduto, Descricao, PrecoUnitario)` | PK: `CodProduto`
   - `LinhaFatura_2FN(NumFatura, CodProduto, Quantidade)` | PK: `(NumFatura, CodProduto)`
5. **3ª Forma Normal (3FN):** Remoção de dependências transitivas no cabeçalho:
   - $NIF\_Cliente \rightarrow Nome\_Cliente, Socio$
   - $CodFuncionario \rightarrow NomeFuncionario$
   *Tabelas Finais na 3FN:*
   - **Cliente**(`NIF_Cliente`, `Nome_Cliente`, `Socio`) | PK: `NIF_Cliente`
   - **Funcionario**(`CodFuncionario`, `NomeFuncionario`) | PK: `CodFuncionario`
   - **Artigo**(`CodProduto`, `Descricao`, `PrecoUnitario`) | PK: `CodProduto`
   - **Fatura**(`NumFatura`, `Data`, `NIF_Cliente`, `CodFuncionario`, `Desconto`, `ValorCobrar`, `MoradaEntrega`, `MetodoPagamento`) | PK: `NumFatura` | FK: `NIF_Cliente` → Cliente, `CodFuncionario` → Funcionario
   - **LinhaFatura**(`NumFatura`, `CodProduto`, `Quantidade`) | PK: `(NumFatura, CodProduto)` | FK: `NumFatura` → Fatura, `CodProduto` → Artigo
</details>


##### 📋 Caso Real de Exame: Normalização - Fatura Tomatino (Exame Normal 2023/2024)
##### Caso III.2: Fatura Tomatino (Exame Normal 2023/2024)
Considere o recibo de refeição simplificado de um restaurante de centro comercial (Tomatino):
- Dados do Operador: Tomatino - Massas e Eventos, NIF `503456789`, Balcão 1, Empregado `MIGUEL`.
- Fatura Nº: `FT 2024/4915` | Data: `27-06-2024 13:15:02` | ATCUD: `MNO99999-4915`
- Consumo / Detalhe da Mesa: Mesa 12, NIF Cliente `999999999`, Pontos Acumulados `120`.
- Artigos Consumidos:
  - Linha 1: 1 x Menu Pasta Italiana (com Taxa IVA 13%, Subtotal 8.50€)
  - Linha 2: 1 x Bebida Copo 0.4L (com Taxa IVA 23%, Subtotal 1.80€)
- Resumo de IVA:
  - Taxa 13% | Base: 7.52€ | IVA: 0.98€ | Total: 8.50€
  - Taxa 23% | Base: 1.46€ | IVA: 0.34€ | Total: 1.80€
- Financeiro: Total Geral: `10.30€` | Método Pagamento: `Multibanco` | Código de Validação: `XYZ987` | Senha Acesso Wifi: `Toma2024`

<details>
<summary><b>🔑 Ver Resolução Oficial</b></summary>

##### Caso III.2 (Fatura Tomatino)
1. **Identificação dos Atributos:**
   - `NumFatura` (A) | `Data` (B) | `NIF_Empresa` (C) | `NIF_Cliente` (D) | `Mesa` (E) | `Empregado` (F) | `Total` (G) | `MetodoPagamento` (H) | `ATCUD` (I) | `SenhaWifi` (J) | `Pontos` (K) | `num_linha` (L) | `Artigo` (M) | `TaxaIVA` (N) | `Subtotal` (O) | `BaseIVA` (P) | `ValorIVA` (Q)
2. **Tabelas Finais na 3ª Forma Normal (3FN):**
   - **Empresa**(`NIF_Empresa`) | PK: `NIF_Empresa`
   - **Cliente**(`NIF_Cliente`, `Pontos`) | PK: `NIF_Cliente`
   - **Fatura**(`NumFatura`, `Data`, `NIF_Empresa`, `NIF_Cliente`, `Mesa`, `Empregado`, `Total`, `MetodoPagamento`, `ATCUD`, `SenhaWifi`) | PK: `NumFatura` | FK: `NIF_Empresa` → Empresa, `NIF_Cliente` → Cliente
   - **Artigo**(`Artigo`, `TaxaIVA`) | PK: `Artigo`
   - **LinhaFatura**(`NumFatura`, `num_linha`, `Artigo`, `Subtotal`) | PK: (`NumFatura`, `num_linha`) | FK: `NumFatura` → Fatura, `Artigo` → Artigo
   - **ResumoIVA**(`NumFatura`, `TaxaIVA`, `BaseIVA`, `ValorIVA`) | PK: (`NumFatura`, `TaxaIVA`) | FK: `NumFatura` → Fatura
   *Dependências Funcionais (DFs):*
   - $NumFatura \rightarrow Data, NIF\_Empresa, NIF\_Cliente, Mesa, Empregado, Total, MetodoPagamento, ATCUD, SenhaWifi$
   - $NIF\_Cliente \rightarrow Pontos$
   - $Artigo \rightarrow TaxaIVA$
   - $NumFatura, num\_linha \rightarrow Artigo, Subtotal$
   - $NumFatura, TaxaIVA \rightarrow BaseIVA, ValorIVA$
</details>


##### 📋 Caso Real de Exame: Normalização - Fatura TecnoShop (Exame Normal 2024/2025)
##### Caso III.3: Fatura TecnoShop (Exame Normal 2024/2025)
Considere a fatura de venda de eletrónica online:
- NIF Empresa: `509123456` | Nome: `TecnoShop, Lda.` | Morada: `Rua das Tecnologias, 42 — 4610-175 Felgueiras`
- Fatura Nº: `FS 2026/1847` | Data: `15-06-2026 14:32` | ATCUD: `ABCD1234-1847`
- NIF Cliente: `234567890` | Nome: `Ana Pereira` | Morada: `Av. da Liberdade, 100, 4000-322 Porto`
- Detalhe de Envio: Método: `CTT Expresso`, Código: `ENV03`, Custo: `4.99€`, Prazo: `2-3 dias úteis`
- Linhas de Artigos:
  - Rato MX3 (Ref A1001, Qtd 2, Preço Unit 29.99€, IVA 23%, Subtotal 73.77€)
  - Teclado K70 (Ref A2045, Qtd 1, Preço Unit 89.99€, IVA 23%, Subtotal 110.69€)
  - Cabo HDMI (Ref A3012, Qtd 3, Preço Unit 9.99€, IVA 23%, Subtotal 36.86€)
  - Webcam HD (Ref A5500, Qtd 1, Preço Unit 54.99€, IVA 23%, Subtotal 67.64€)
- Resumo Financeiro: Subtotal (s/IVA) 234.95€, Total IVA 54.04€ (Taxa 23%), Custo Envio 4.99€, Total Geral 293.98€, Método Pagamento: `Cartão Visa`.

<details>
<summary><b>🔑 Ver Resolução Oficial</b></summary>

##### Caso III.3 (Fatura TecnoShop)
1. **Tabelas Finais na 3ª Forma Normal (3FN):**
   - **Empresa**(`NIF_Empresa`, `Nome_Empresa`, `Morada_Empresa`, `CodPostal_Empresa`) | PK: `NIF_Empresa`
   - **Cliente**(`NIF_Cliente`, `Nome_Cliente`, `Morada_Cliente`, `CodPostal_Cliente`) | PK: `NIF_Cliente`
   - **MetodoEnvio**(`CodEnvio`, `MetodoEnvio`, `CustoEnvio`, `PrazoEstimado`) | PK: `CodEnvio`
   - **Artigo**(`RefArtigo`, `Descricao_Artigo`, `Categoria`, `PrecoUnitario`, `TaxaIVA`) | PK: `RefArtigo`
   - **Fatura**(`NumFatura`, `Data`, `Hora`, `ATCUD`, `NIF_Empresa`, `NIF_Cliente`, `CodEnvio`, `Total_Fatura`, `MetodoPagamento`) | PK: `NumFatura` | FK: `NIF_Empresa` → Empresa, `NIF_Cliente` → Cliente, `CodEnvio` → MetodoEnvio
   - **LinhaFatura**(`NumFatura`, `RefArtigo`, `Quantidade`, `Subtotal_Linha`) | PK: (`NumFatura`, `RefArtigo`) | FK: `NumFatura` → Fatura, `RefArtigo` → Artigo
   - **ResumoIVA**(`NumFatura`, `TaxaIVA`, `Incidencia_IVA`, `Valor_IVA`) | PK: (`NumFatura`, `TaxaIVA`) | FK: `NumFatura` → Fatura
   *Dependências Funcionais (DFs):*
   - $NumFatura \rightarrow Data, Hora, ATCUD, NIF\_Empresa, NIF\_Cliente, CodEnvio, Total\_Fatura, MetodoPagamento$
   - $NIF\_Empresa \rightarrow Nome\_Empresa, Morada\_Empresa, CodPostal\_Empresa$
   - $NIF\_Cliente \rightarrow Nome\_Cliente, Morada\_Cliente, CodPostal\_Cliente$
   - $CodEnvio \rightarrow MetodoEnvio, CustoEnvio, PrazoEstimado$
   - $RefArtigo \rightarrow Descricao\_Artigo, Categoria, PrecoUnitario, TaxaIVA$
   - $NumFatura, RefArtigo \rightarrow Quantidade, Subtotal\_Linha$
   - $NumFatura, TaxaIVA \rightarrow Incidencia\_IVA, Valor\_IVA$
</details>


##### 📋 Caso Real de Exame: Normalização - Fatura Momento Surpresa (Exame Normal 2025/2026)
##### Caso III.4: Fatura Momento Surpresa (Exame Normal 2025/2026)
Considere a fatura simplificada apresentada no [Exame 25-26 1ª parte.pdf](file:///c:/Users/diogo/Documents/GitHub/Stuff-LSIRC/2ºano/2ºsemestre/BD/STUDY_STUFF/exames/Exames%20Recentes/Exame%2025-26%201ª%20parte.pdf) (pág. 2):
- Dados Empresa: `Momento Surpresa - Eventos em Hotelaria, Unip. Lda`, Zona Industrial do Socorro 4820-000, NIF `PT509468268`.
- Contrato/Fatura-Recibo Nº: `FR S1/0033537` | Data: `2025-06-17 13:20:23` | ATCUD: `JFZXWGVZ-0033537`.
- Local/Evento: `Lote Z - 2 Quinchães, FAFE`.
- Cliente NIF: `515870358`.
- Detalhes de Consumo:
  - Quantidade: 1 | Item Principal: `DIARIA COM AGUA`
    - Sub-itens detalhados da diária:
      - `PRATO` (Taxa IVA 13%, Preço €6,50)
      - `SOPA` (Taxa IVA 13%, Preço €1,00)
      - `SOBREMESA` (Taxa IVA 13%, Preço €1,50)
- Resumo Financeiro: Total: `€9,00` | Método Pagamento: `Multibanco` | Pago: `€9,00`.
- Resumo de IVA: Taxa 13% | Incidência: €7,96 | Valor IVA: €1,04 | Total: €9,00.
- Operador: Empregado `MIGUEL`, Mesa `REDONDA`.

<details>
<summary><b>🔑 Ver Resolução Oficial</b></summary>

##### Caso III.4 (Fatura Momento Surpresa)
1. **Identificação dos Atributos:**
   - `NumFatura` (A) (FR S1/0033537)
   - `NIF_Empresa` (B) (PT509468268)
   - `Nome_Empresa` (C) (Momento Surpresa - Eventos em Hotelaria, Unip. Lda)
   - `Morada_Empresa` (D) (Zona Industrial do Socorro 4820-000)
   - `Data` (E) (2025-06-17)
   - `Hora` (F) (13:20:23)
   - `LoteEvento` (G) (Lote Z - 2 Quinchães, FAFE)
   - `NIF_Cliente` (H) (515870358)
   - `NomeMenu` (I) (DIARIA COM AGUA)
   - `QtdMenu` (J) (1)
   - `DescricaoItem` (K) (PRATO, SOPA, SOBREMESA)
   - `TaxaIVA` (L) (13%)
   - `PrecoItem` (M) (€6.50, €1.00, €1.50)
   - `Empregado` (N) (MIGUEL)
   - `Mesa` (O) (REDONDA)
   - `Total` (P) (€9.00)
   - `MetodoPagamento` (Q) (Multibanco)
   - `ATCUD` (R) (JFZXWGVZ-0033537)
   - `IncidenciaIVA` (S) (€7.96)
   - `ValorIVA` (T) (€1.04)
2. **Forma Não Normalizada (UNF):**
   `Fatura_UNF(A, B, C, D, E, F, G, H, I, J, N, O, P, Q, R, S, T, [K, L, M])`
3. **1ª Forma Normal (1FN):** A PK da tabela principal passa a ser composta por `(NumFatura, DescricaoItem)`.
   `Fatura_1FN(NumFatura, DescricaoItem, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T)`
4. **2ª Forma Normal (2FN):** Remoção de dependências parciais:
   - `Cabecalho_2FN(NumFatura, NIF_Empresa, Nome_Empresa, Morada_Empresa, Data, Hora, LoteEvento, NIF_Cliente, NomeMenu, QtdMenu, Empregado, Mesa, Total, MetodoPagamento, ATCUD, IncidenciaIVA, ValorIVA)` | PK: `NumFatura`
   - `Item_2FN(DescricaoItem, TaxaIVA, PrecoItem)` | PK: `DescricaoItem` (Se assumirmos que os itens têm preço fixo)
   - `LinhaFatura_2FN(NumFatura, DescricaoItem)` | PK: `(NumFatura, DescricaoItem)`
5. **3ª Forma Normal (3FN):** Remoção de dependências transitivas:
   - $NIF\_Empresa \rightarrow Nome\_Empresa, Morada\_Empresa$
   - $NumFatura \rightarrow TaxaIVA\_Resumo \rightarrow IncidenciaIVA, ValorIVA$ (Extraímos o resumo de IVA)
   *Tabelas Finais na 3FN:*
   - **Empresa**(`NIF_Empresa`, `Nome_Empresa`, `Morada_Empresa`) | PK: `NIF_Empresa`
   - **Cliente**(`NIF_Cliente`) | PK: `NIF_Cliente` (Apenas NIF consta na fatura)
   - **Menu**(`NomeMenu`) | PK: `NomeMenu`
   - **Item**(`DescricaoItem`, `PrecoItem`, `TaxaIVA`) | PK: `DescricaoItem`
   - **Fatura**(`NumFatura`, `Data`, `Hora`, `LoteEvento`, `NIF_Empresa`, `NIF_Cliente`, `NomeMenu`, `QtdMenu`, `Empregado`, `Mesa`, `Total`, `MetodoPagamento`, `ATCUD`) | PK: `NumFatura` | FK: `NIF_Empresa` → Empresa, `NIF_Cliente` → Cliente, `NomeMenu` → Menu
   - **LinhaFatura**(`NumFatura`, `DescricaoItem`) | PK: (`NumFatura`, `DescricaoItem`) | FK: `NumFatura` → Fatura, `DescricaoItem` → Item
   - **ResumoIVA**(`NumFatura`, `TaxaIVA`, `IncidenciaIVA`, `ValorIVA`) | PK: (`NumFatura`, `TaxaIVA`) | FK: `NumFatura` → Fatura
   *Dependências Funcionais (DFs):*
   - $NumFatura \rightarrow Data, Hora, LoteEvento, NIF\_Empresa, NIF\_Cliente, NomeMenu, QtdMenu, Empregado, Mesa, Total, MetodoPagamento, ATCUD$
   - $NIF\_Empresa \rightarrow Nome\_Empresa, Morada\_Empresa$
   - $DescricaoItem \rightarrow PrecoItem, TaxaIVA$
   - $NumFatura, TaxaIVA \rightarrow IncidenciaIVA, ValorIVA$
</details>


##### 📋 Caso Real de Exame: Normalização - Contrato AutoFlex (Exame Modelo 2)
##### Caso III.5: Contrato AutoFlex (Exame Modelo 2)
Considere o contrato de aluguer de viatura:
- Empresa: `AutoFlex Rent-a-Car`, NIF `501987654`, Porto.
- Contrato Nº: `CT-2026/0342` | Data Início: `10-07-2026` | Devolução Prevista: `15-07-2026`.
- Agências: Levantamento `AGP01` (Porto Aeroporto), Devolução `AGL03` (Lisboa Centro).
- Condutor Principal: NIF `287654321`, Nome `Ricardo Sousa`, Carta `PT-543210`, Cat. `B`.
- Condutores Adicionais: NIF `298111222`, Nome `Maria Sousa`, Carta `PT-654321`, Cat. `B`.
- Viatura: Matrícula `AA-01-BB`, Marca `Toyota`, Modelo `Corolla`, Categ. `C` (Compacto), Preço Diário `35.00€`, Combustível `Gasolina`.
- Extras Contratados: GPS (EX01, 5.00€/dia), Cadeira Bebé (EX03, 3.50€/dia), Seguro (EX05, 12.00€/dia).
- Resumo Financeiro: Duração 5 dias, Custo Viat 175.00€, Extras 102.50€, Cond. Adicional 37.50€, Taxa Dev Diferente 25.00€, Subtotal 340.00€, IVA (23%) 78.20€, Total Geral 418.20€, Pagamento: `MBWay`.

---
---

## ✅ Resoluções

### 📌 Resoluções dos Exercícios de Preparação

#### Resolução do Exercício 1.1 (Conceitos Fundamentais)
1. **Diferença Teórica:**
   - Numa **LMD procedimental**, o utilizador especifica *como* os dados devem ser obtidos, controlando o fluxo e processamento passo a passo, registo por registo (abordagem "one-record-at-a-time").
   - Numa **LMD não-procedimental**, o utilizador apenas especifica *que* dados quer obter, sem indicar o caminho físico para aceder aos mesmos (abordagem declarativa, "set-at-a-time"). O SGBD encarrega-se de determinar o melhor plano de acesso físico.
2. **Exemplo de abordagem:**
   - *Procedimental (pseudocódigo):*
     ```text
     abrir_ponteiro(cursor_empregados)
     enquanto cursor_empregados.tem_linhas() fazer
         linha = cursor_empregados.proxima_linha()
         se linha.salario > 1000 então
             guardar_ou_imprimir(linha.nome, linha.salario)
         fim_se
     fim_enquanto
     fechar_ponteiro(cursor_empregados)
     ```
   - *Não-Procedimental (SQL):*
     ```sql
     SELECT nome, salario FROM Empregados WHERE salario > 1000;
     ```

#### Resolução do Exercício 1.2 (Conceitos Fundamentais)
1. **Três limitações resolvidas:**
   - **Redundância e Inconsistência:** No Excel, o mesmo cliente pode estar escrito de várias formas em ficheiros diferentes. Na BD, os dados são centralizados e normalizados.
   - **Acesso Concorrente:** O Excel bloqueia o ficheiro inteiro se alguém estiver a editar. O SGBD gere acessos concorrentes ao nível do registo (linha) recorrendo a locks.
   - **Falta de Integridade:** No Excel pode escrever-se "Texto" numa coluna de preços. A BD impõe restrições de domínio, CHECK e Integridade Referencial.
2. **Quando preferir sistema de ficheiros:** Quando a dimensão do projeto é muito pequena, o custo inicial de instalação e administração do SGBD é proibitivo, a aplicação é de utilizador único, e não há requisitos de partilha concorrente ou de segurança avançada.

#### Resolução do Exercício 1.3 (Conceitos Fundamentais)
1. **Definição e Importância:** Metadados são dados que descrevem outros dados (como nomes das tabelas, colunas, tipos de dados, chaves e restrições). O Dicionário de Dados é o coração da BD porque todas as operações de validação de queries, permissões de utilizador e escolhas do otimizador de consultas baseiam-se na leitura imediata deste catálogo.
2. **Garantia de Independência:** As aplicações clientes interagem com a BD usando nomes lógicos de tabelas e colunas. Como a BD descreve estas tabelas nos metadados, o SGBD pode traduzir transparentemente os pedidos lógicos para as estruturas físicas de armazenamento. Se mudarmos os ficheiros físicos de disco, apenas os metadados são reajustados, sem tocar nas aplicações.

#### Resolução do Exercício 1.4 (Conceitos Fundamentais)
1. `ALTER TABLE Cliente ADD Telefone VARCHAR(15);` → **DDL** (modifica o esquema lógico da base de dados, adicionando um atributo).
2. `UPDATE Produto SET Stock = Stock - 1 WHERE Codigo = 101;` → **DML** (modifica o valor dos dados armazenados na tabela).
3. `DROP INDEX idx_nome_cliente;` → **DDL** (remove uma estrutura física/interna de indexação da BD).
4. `INSERT INTO Venda (Data, Total) VALUES (GETDATE(), 120.5);` → **DML** (insere novos dados numa tabela existente).

---

#### Resolução do Exercício 2.1 (ANSI/SPARC)
1. **Associação de Níveis:**
   - Visão do Diretor de Curso (média): **Nível Externo** (vista individual e parcial dos dados).
   - Administrador de BD (tabelas Alunos, Disciplinas): **Nível Conceptual** (visão lógica global da BD).
   - Ficheiro `academica.mdf` (páginas de 8KB): **Nível Interno** (visão física do armazenamento).
2. **Utilidade dos Mapeamentos (Mappings):** Garantir a independência de dados. O mapeamento *externo-conceptual* permite que alterações no esquema conceitual não afetem as vistas externas. O mapeamento *conceptual-interno* permite alterar a estrutura de armazenamento físico (ex: mover ficheiros ou mudar indexações) sem alterar as tabelas lógicas (esquema conceitual).

#### Resolução do Exercício 2.2 (ANSI/SPARC)
1. **Diferença:**
   - **Independência Física:** Capacidade de alterar a estrutura física dos ficheiros, discos ou índices sem alterar as tabelas lógicas ou as aplicações.
   - **Independência Lógica:** Capacidade de alterar a estrutura lógica global (esquema conceptual) como adicionar/dividir tabelas sem ter de reescrever as queries das aplicações existentes.
2. **Mais difícil na prática:** A **independência lógica** é muito mais difícil de manter. Se dividirmos uma tabela base em duas por motivos de performance, qualquer query directa a essa tabela vai falhar. A única forma de remediar é criar vistas (views) para simular o comportamento da tabela original, mas vistas com junções têm graves restrições para atualizações de dados.

#### Resolução do Exercício 2.3 (ANSI/SPARC)
1. **Lógica de negócio:**
   - Numa arquitetura de **2 níveis (2-tier)**, a lógica de negócio reside na máquina cliente (fat client).
   - Numa de **3 níveis (3-tier)**, reside num servidor aplicacional dedicado (Application Server) intermédio.
2. **Escalabilidade e Web:** A arquitetura **3 níveis** é imensamente superior na Web. Permite pooling de conexões (o servidor de aplicação mantém poucas conexões à BD e reutiliza-as de forma concorrente para milhares de utilizadores web), ao passo que em 2 níveis cada utilizador do browser exigiria uma ligação direta aberta à BD, esgotando os recursos do SGBD rapidamente.

#### Resolução do Exercício 2.4 (ANSI/SPARC)
1. A alteração ocorre no **Nível Interno** (esquema físico de armazenamento de dados).
2. **Nenhuma alteração** é necessária no código SQL. Devido à independência física de dados, o otimizador de consultas do SGBD decide em tempo de execução se deve utilizar o novo índice ou se deve fazer uma leitura total da tabela, sem que o programador precise de instruir a query a usar o índice.

---

#### Resolução do Exercício 3.1 (Modelo Relacional)
1. **Grau:** O número de atributos. Como a tabela possui 7 colunas (ID, Nome, DataNascimento, Cargo, NIF, Telefone, ID_Departamento), o grau é **7**.
2. **Cardinalidade:** O número de linhas. Há 150 empregados registados, logo a cardinalidade é **150**.
3. **Não altera.** Segundo a definição de Codd para o Modelo Relacional, uma tabela é um conjunto matemático de tuplos. Por definição de conjuntos, a ordem dos elementos não importa logicamente. Por isso, a alteração da ordem física no disco não afeta as propriedades lógicas da BD.

#### Resolução do Exercício 3.2 (Modelo Relacional)
1. **Violaria a Integridade da Entidade.** A coluna `NumeroAluno` é parte integrante da Chave Primária, logo, por regra de integridade de entidade, não pode aceitar valores nulos (`NULL`).
2. **Violaria a Integridade Referencial.** A coluna `NumeroAluno` é uma chave estrangeira que referencia `Aluno(Numero)`. Não se pode introduzir um número que não exista previamente na tabela referenciada.
3. **Violaria a Integridade Geral (ou Restrições de Domínio).** A nota 22 excede os limites de validação estipulados para a coluna `Nota` (intervalo de 0 a 20).

#### Resolução do Exercício 3.3 (Modelo Relacional)
1. **Chaves Candidatas:** `{NIF}`, `{NumCC}`, `{Email}` (qualquer uma delas identifica unicamente o cidadão de forma mínima).
2. **Superchave:** `{NIF, Nome}` ou `{NumCC, Email}` (um superconjunto que engloba uma chave candidata mas não é minimal).
3. **Escolha da Chave Primária:** De entre as candidatas, escolhe-se preferencialmente a mais curta, mais estável (cujo valor não mude ao longo do tempo) e que seja numérica. Neste caso, `NIF` ou `NumCC` seriam ideais (evitando o `Email`, pois o utilizador pode querer alterá-lo com frequência).

#### Resolução do Exercício 3.4 (Modelo Relacional)
Não se pode considerar uma relação devido a dois fatores:
1. **Falta de atomicidade (Não está na 1FN):** O campo "Telefones" possui dados agrupados e multivalor ("912345678; 963332211") numa única célula, o que viola o requisito de que todos os atributos devem possuir valores atómicos.
2. **Existência de duplicados:** No modelo relacional, todos os tuplos são obrigatoriamente únicos e distinguíveis por intermédio de chaves. O CSV com linhas duplicadas viola esta restrição fundamental de conjuntos.

---

#### Resolução do Exercício 4.1 (Álgebra Relacional)
```text
-- Filtrar alunos de Lisboa
AlunosLx ← σ_Cidade='Lisboa'(Alunos)

-- Obter IDs de todos os alunos de Lisboa
IdsLx ← π_NumA(AlunosLx)

-- Obter IDs dos alunos com inscrições
IdsInscritos ← π_NumA(Inscritos)

-- Obter IDs de alunos de Lisboa que NÃO têm inscrições
IdsAlvo ← IdsLx − IdsInscritos

-- Obter os nomes correspondentes através de junção com a relação Alunos original
Resultado ← π_Nome(IdsAlvo ⋈ Alunos)
```

#### Resolução do Exercício 4.2 (Álgebra Relacional)
1. **Natural Join:**
   ```text
   Resultado1 ← π_Nome, ID_Enc(Clientes ⋈ Encomendas)
   ```
2. **Left Outer Join:**
   ```text
   Resultado2 ← π_Nome, ID_Enc(Clientes ⟕ Encomendas)
   ```

#### Resolução do Exercício 4.3 (Álgebra Relacional)
Para expressar "todos", usamos a operação de divisão (÷):
```text
-- Isolar colunas necessárias na dividenda
RelacaoA ← π_NumE, CodC(Inscrito)

-- Isolar coluna necessária na divisora
RelacaoB ← π_CodC(CursoObrigatorio)

-- Efetuar a divisão
Resultado ← RelacaoA ÷ RelacaoB
```

#### Resolução do Exercício 4.4 (Álgebra Relacional)
1. **Motivo da Invalidez:** A união exige que as relações sejam **compatíveis com a união**. Isso obriga a que tenham o mesmo grau (número de atributos) e que os domínios das colunas na ordem correspondente coincidam. No caso, `EmpregadosPT` tem grau 3 e `EmpregadosUK` tem grau 4.
2. **Resolução:** Devemos projetar apenas as colunas desejadas de cada uma e renomear os cabeçalhos para ficarem coincidentes:
   ```text
   PT_Proj ← π_NIF, Nome(EmpregadosPT)
   UK_Proj ← ρ_(NIF, Nome)(π_NationalID, Name(EmpregadosUK))
   
   Resultado ← PT_Proj ∪ UK_Proj
   ```

---

#### Resolução do Exercício 5.1 (SQL)
```sql
SELECT m.Nome, m.Especialidade, SUM(c.Preco) AS FaturacaoTotal
FROM Medicos m
INNER JOIN Consultas c ON m.ID_Med = c.ID_Med
WHERE c.Data >= '2025-01-01' AND c.Data <= '2025-12-31'
GROUP BY m.ID_Med, m.Nome, m.Especialidade
HAVING SUM(c.Preco) > 5000;
```

#### Resolução do Exercício 5.2 (SQL)
- **Erro:** O SGBD não permite a utilização de funções agregadas (`COUNT(ID_Prod)`) no `WHERE`. O `WHERE` filtra registos individuais antes de estes serem agrupados em memória.
- **Correção:** O filtro de funções agregadas sobre dados já agrupados deve ser efetuado na cláusula `HAVING`.
- **Query corrigida:**
  ```sql
  SELECT Categoria, COUNT(ID_Prod) AS Total
  FROM Produtos
  GROUP BY Categoria
  HAVING COUNT(ID_Prod) > 10;
  ```

#### Resolução do Exercício 5.3 (SQL)
1. **Conversão para Join:**
   ```sql
   SELECT DISTINCT c.Nome
   FROM Clientes c
   INNER JOIN Encomendas e ON c.ID_Cli = e.ID_Cli
   WHERE e.ID_Prod = 99;
   ```
   *(Nota: O uso de DISTINCT é necessário para evitar que clientes que tenham feito múltiplas encomendas do produto 99 apareçam duplicados no resultado, emulando perfeitamente a subquery original com IN).*
2. **Quando não é possível:** Não é possível reescrever quando lidamos com subqueries escalares utilizadas para comparações agregadas genéricas (ex: `WHERE Salario > (SELECT AVG(Salario) FROM Empregados)`) ou subqueries correlacionadas em que a cardinalidade dos grupos é fundamental para a computação sem duplicar registos na tabela principal.

#### Resolução do Exercício 5.4 (SQL)
1. `Preco > ALL (...)`: O produto é selecionado apenas se o seu preço for maior do que o preço de **todos** os produtos na categoria 'Livros' (ou seja, maior do que o livro mais caro).
2. `Preco > ANY (...)`: O produto é selecionado se for mais caro do que pelo menos **um** dos produtos da categoria 'Livros' (ou seja, basta ser mais caro do que o livro mais barato).
3. `EXISTS (...)`: Retorna verdadeiro para a linha atual se a subquery encontrar pelo menos um registo correspondente na tabela de vendas. É um predicado booleano eficiente, pois o SGBD interrompe a avaliação da subquery assim que obtém o primeiro match (semijoin).

---

#### Resolução do Exercício 6.1 (SQL)
```sql
CREATE TABLE Veiculos (
    Matricula CHAR(8),
    Marca VARCHAR(50) NOT NULL,
    Modelo VARCHAR(50) NOT NULL,
    Ano INT CONSTRAINT chk_ano_limite CHECK (Ano > 1900),
    PrecoAluguer DECIMAL(8,2) DEFAULT 50.00,
    NumeroQuadro VARCHAR(30) UNIQUE,
    PRIMARY KEY (Matricula)
);
```

#### Resolução do Exercício 6.2 (SQL)
1. Adicionar coluna:
   ```sql
   ALTER TABLE Veiculos ADD DataAdquisicao DATE NULL;
   ```
2. Adicionar FK:
   ```sql
   ALTER TABLE Veiculos ADD ID_Proprietario INT;
   
   ALTER TABLE Veiculos ADD CONSTRAINT fk_veiculos_proprietarios 
       FOREIGN KEY (ID_Proprietario) REFERENCES Proprietarios(ID);
   ```
3. Alterar constraint CHECK:
   ```sql
   ALTER TABLE Veiculos DROP CONSTRAINT chk_ano_limite;
   
   ALTER TABLE Veiculos ADD CONSTRAINT chk_ano_limite CHECK (Ano > 2000);
   ```

#### Resolução do Exercício 6.3 (SQL)
- **Criação de Índice:**
  ```sql
  CREATE INDEX idx_vendas_cliente ON Vendas(Cliente);
  ```
- **Discussão:**
  - **Prós:** Acelera exponencialmente a leitura e filtragem de registos por cliente. Evita um Table Scan completo, localizando as vendas associadas ao cliente através de buscas em árvores B (Index Seek).
  - **Contras:** Aumenta o tempo necessário para executar operações de escrita (`INSERT`, `DELETE` ou `UPDATE` do ID do cliente), dado que o SGBD tem de recalcular a árvore do índice em disco. Ocupa também espaço físico adicional.

#### Resolução do Exercício 6.4 (SQL)
1. `DROP TABLE Clientes CASCADE;` → Elimina a tabela `Clientes` e todas as restrições que dependam dela noutras tabelas (ex: se `Encomendas` tiver uma chave estrangeira para `Clientes`, essa FK é automaticamente removida em cascata).
2. `DROP TABLE Clientes RESTRICT;` → O SGBD rejeita o comando caso existam tabelas ou objetos que dependam da tabela `Clientes`. O administrador é obrigado a apagar primeiro as referências (ex: FK em `Encomendas`) antes de conseguir apagar a tabela.

---

#### Resolução do Exercício 7.1 (Integridade Referencial)
1. **Após `UPDATE Departamentos SET ID_Dep = 15 WHERE ID_Dep = 10;`:**
   Como a FK tem `ON UPDATE CASCADE`, o ID alterado propaga-se aos trabalhadores 1 e 2:
   - `Departamentos`: `(15, 'RH'), (20, 'TI')`
   - `Trabalhadores`: `(1, 'Ana', 15), (2, 'Rui', 15), (3, 'Maria', 20)`
2. **Após `DELETE FROM Departamentos WHERE ID_Dep = 20;`:**
   Como a FK tem `ON DELETE CASCADE`, o departamento 20 é removido e o trabalhador 3 é apagado em cascata:
   - `Departamentos`: `(15, 'RH')`
   - `Trabalhadores`: `(1, 'Ana', 15), (2, 'Rui', 15)`

#### Resolução do Exercício 7.2 (Integridade Referencial)
1. **Ação Referencial:** `ON DELETE SET NULL`.
2. **Restrição da coluna:** A coluna `ID_Curso` na tabela `Aluno` **não pode** possuir a restrição `NOT NULL`. Tem de permitir valores nulos para que o SGBD consiga atualizar o campo para `NULL` no momento em que o curso associado for removido.

#### Resolução do Exercício 7.3 (Integridade Referencial)
- **`RESTRICT`**: O SGBD valida a integridade referencial **imediatamente antes** de iniciar a execução do comando de eliminação. Se detetar a presença de registos filhos, aborta a transação imediatamente.
- **`NO ACTION`**: O SGBD permite iniciar a operação de deleção, mas efetua a validação **no final da instrução SQL** (ou no final da transação, caso seja diferida). Se após a conclusão do ciclo ainda persistirem registos filhos órfãos, é efetuado um ROLLBACK total.

#### Resolução do Exercício 7.4 (Integridade Referencial)
```sql
CREATE TABLE Notas (
    ID_Nota INT PRIMARY KEY,
    NumAluno INT,
    CodDisciplina VARCHAR(10),
    AnoLetivo VARCHAR(9),
    ValorNota DECIMAL(4,2),
    CONSTRAINT fk_notas_inscricoes
        FOREIGN KEY (NumAluno, CodDisciplina, AnoLetivo)
        REFERENCES Inscricoes(NumAluno, CodDisciplina, AnoLetivo)
        ON UPDATE CASCADE
        ON DELETE NO ACTION
);
```

---

#### Resolução do Exercício 8.1 (Views)
1. **Vista de Segurança:**
   ```sql
   CREATE VIEW V_Colaboradores_Contacto AS
   SELECT ID, Nome, Morada, Telemovel, Cargo
   FROM Colaboradores;
   ```
2. **Permissões:**
   ```sql
   REVOKE ALL PRIVILEGES ON Colaboradores FROM EquipaLimpeza;
   GRANT SELECT ON V_Colaboradores_Contacto TO EquipaLimpeza;
   ```

#### Resolução do Exercício 8.2 (Views)
- **`Vista_A`**: **É atualizável**. Não contém agregações, subqueries, `DISTINCT` ou `GROUP BY` e seleciona campos de uma única tabela base.
- **`Vista_B`**: **Não é atualizável**. A vista contém cláusulas `GROUP BY` e funções de agregação (`AVG`). O SGBD é incapaz de mapear um comando UPDATE na média do grupo para atualizar as colunas dos registos individuais na tabela base.

#### Resolução do Exercício 8.3 (Views)
O motor de bases de dados funde a consulta do utilizador com a query de definição da vista:
```sql
SELECT Nome
FROM Clientes
WHERE Ativo = 1 AND Cidade = 'Porto';
```

#### Resolução do Exercício 8.4 (Views)
Uma Vista Materializada armazena o resultado da query de definição **fisicamente em disco**, mantendo-se em sincronia automática com as tabelas base.
- **Prós:** Aceleração fantástica na leitura e processamento de consultas OLAP complexas ou agregações pesadas.
- **Contras:** Overhead nas escritas das tabelas base, pois o SGBD necessita de recalcular e atualizar a vista materializada a cada inserção/atualização. Consome espaço em disco.

---

#### Resolução do Exercício 9.1 (Triggers/SP/Functions)
```sql
CREATE TRIGGER trg_AuditoriaPrecoProduto
ON Produtos
AFTER UPDATE
AS
BEGIN
    SET NOCOUNT ON;
    
    INSERT INTO HistoricoPrecos (ID_Prod, PrecoAntigo, PrecoNovo, DataAlteracao, Utilizador)
    SELECT i.ID, d.Preco, i.Preco, GETDATE(), SUSER_SNAME()
    FROM inserted i
    INNER JOIN deleted d ON i.ID = d.ID
    WHERE i.Preco <> d.Preco;
END;
```

#### Resolução do Exercício 9.2 (Triggers/SP/Functions)
```sql
CREATE PROCEDURE usp_RegistarVenda
    @ID_Prod INT,
    @Qtd INT
AS
BEGIN
    SET NOCOUNT ON;
    BEGIN TRANSACTION;
    
    BEGIN TRY
        DECLARE @StockAtual INT;
        
        -- Verificar se o produto existe e obter stock
        SELECT @StockAtual = Stock FROM Produtos WHERE ID = @ID_Prod;
        
        IF @StockAtual IS NULL
        BEGIN
            THROW 50001, 'Produto não registado na base de dados.', 1;
        END
        
        IF @StockAtual < @Qtd
        BEGIN
            THROW 50002, 'Operação abortada: stock insuficiente.', 1;
        END
        
        -- Atualizar o stock do produto
        UPDATE Produtos 
        SET Stock = Stock - @Qtd 
        WHERE ID = @ID_Prod;
        
        -- Registar a venda
        INSERT INTO Vendas (ID_Prod, Quantidade, Data) 
        VALUES (@ID_Prod, @Qtd, GETDATE());
        
        COMMIT TRANSACTION;
    END TRY
    BEGIN CATCH
        ROLLBACK TRANSACTION;
        DECLARE @Msg NVARCHAR(4000) = ERROR_MESSAGE();
        RAISERROR(@Msg, 16, 1);
    END CATCH
END;
```

#### Resolução do Exercício 9.3 (Triggers/SP/Functions)
```sql
CREATE FUNCTION ufn_CalcularDesconto (
    @PrecoOriginal DECIMAL(10,2),
    @AnosCliente INT
)
RETURNS DECIMAL(10,2)
AS
BEGIN
    DECLARE @PrecoFinal DECIMAL(10,2);
    
    IF @AnosCliente > 5
        SET @PrecoFinal = @PrecoOriginal * 0.85;
    ELSE IF @AnosCliente >= 2
        SET @PrecoFinal = @PrecoOriginal * 0.90;
    ELSE
        SET @PrecoFinal = @PrecoOriginal;
        
    RETURN @PrecoFinal;
END;
```

#### Resolução do Exercício 9.4 (Triggers/SP/Functions)
1. **Obrigatoriedade de Retorno:** A Função é obrigada a devolver um valor (`RETURNS`), enquanto o Procedimento pode ou não retornar valores (ou devolver múltiplos recordsets).
2. **Alteração de Dados:** Funções em SQL Server/SGBDs padrão são estritamente de leitura (não podem efetuar comandos DML de escrita `INSERT/UPDATE/DELETE` nas tabelas base). Stored Procedures não têm essa restrição.
3. **Invocação:** As funções podem ser incorporadas diretamente em expressões SQL normais (ex: no `SELECT` ou no `WHERE`), ao passo que os procedimentos têm de ser chamados explicitamente via comando `EXEC`.

---

#### Resolução do Exercício 10.1 (Normalização)
**Passo 0: Identificar Atributos com Letras**
- A: NifOficina
- B: NomeOficina
- C: CodReparacao
- D: Data
- E: Matricula
- F: ModeloCarro
- G: CodPeca
- H: DescricaoPeca
- I: QtdPeca
- J: PrecoPeca
- K: SubtotalPeca
- L: TotalReparacao

*Forma Não Normalizada (UNF):*
`Recibo(A, B, C, D, E, F, G, H, I, J, K, L)`
*Definição UNF:* Tabela que contém um ou mais grupos repetidos (aqui, a lista de peças associadas a cada reparação).

---

**Passo 1: 1ª Forma Normal (1FN)**
*Definição 1FN:* Uma relação está na 1FN se todas as interseções de linhas e colunas contiverem valores atómicos, não contendo grupos repetidos.
*Resolução:* Achatamos a tabela. A chave primária que identifica unicamente cada registo é composta por `(C, G)` (CodReparacao + CodPeca).
`Recibo_1FN(C, G, A, B, D, E, F, I, J, H, K, L)`

*Dependências Funcionais (DFs):*
- `C → A, B, D, E, F, L`
- `G → H, J`
- `C, G → I, K`
- `A → B`
- `E → F`

---

**Passo 2: 2ª Forma Normal (2FN)**
*Definição 2FN:* Relação na 1FN em que todos os atributos não-chave dependem inteiramente de toda a chave primária (ausência de dependências parciais).
*Resolução:* Extraímos o que depende apenas de partes da PK `(C, G)`:
- `Reparacao(C, A, B, D, E, F, L)` | PK: `C`
- `Peca(G, H, J)` | PK: `G`
- `LinhaReparacao(C, G, I, K)` | PK: (`C`, `G`) | FK: `C` → Reparacao, `G` → Peca

*Identificar Dependências Transitivas na 2FN:*
- Na tabela `Reparacao`: `C → A` e `A → B` (transitiva `C → A → B`)
- Na tabela `Reparacao`: `C → E` e `E → F` (transitiva `C → E → F`)

---

**Passo 3: 3ª Forma Normal (3FN)**
*Definição 3FN:* Relação na 2FN em que nenhum atributo não-chave depende de forma transitiva da chave primária (todos os determinantes devem ser chaves candidatas).
*Resolução:* Decompomos para eliminar as transitividades causadas por `A` e `E`.

*Tabelas Finais na 3FN:*
- **Oficina**(`NifOficina`, `NomeOficina`) | PK: `NifOficina`
- **Veiculo**(`Matricula`, `ModeloCarro`) | PK: `Matricula`
- **Reparacao**(`CodReparacao`, `Data`, `NifOficina`, `Matricula`, `TotalReparacao`) | PK: `CodReparacao` | FK: `NifOficina` → Oficina, `Matricula` → Veiculo
- **Peca**(`CodPeca`, `DescricaoPeca`, `PrecoPeca`) | PK: `CodPeca`
- **LinhaReparacao**(`CodReparacao`, `CodPeca`, `QtdPeca`, `SubtotalPeca`) | PK: (`CodReparacao`, `CodPeca`) | FK: `CodReparacao` → Reparacao, `CodPeca` → Peca

#### Resolução do Exercício 10.2 (Normalização)
1. **Anomalia de Inserção:** Não conseguimos inserir os dados de um novo departamento (ex: sala e nome de um novo departamento de Marketing) antes de termos pelo menos um funcionário contratado para trabalhar lá, porque o campo `NIF` (chave primária) não aceita valores nulos.
2. **Anomalia de Remoção:** Ao demitirmos o único trabalhador do departamento de Contabilidade, a sua linha na tabela é apagada e perdemos permanentemente a informação física de que esse departamento existia e qual a sua localização.
3. **Anomalia de Modificação (Inconsistência):** Se o departamento de Recursos Humanos for transferido de sala, teremos de atualizar a coluna `LocalizacaoDep` em todos os empregados do departamento. Se falharmos uma única linha, haverá dados inconsistentes na base de dados.

#### Resolução do Exercício 10.3 (Normalização)
- A propriedade chama-se **Decomposição Sem Perdas (Lossless-Join)**.
- **Validação:** Garante que, ao aplicar um `NATURAL JOIN` às tabelas decompostas, recuperamos exatamente a mesma informação e número de registos da tabela desnormalizada original (sem tuplos falsos ou espúrios). Aplica-se o teorema que dita que o atributo comum entre as relações divididas deve ser chave primária (ou superchave) de pelo menos uma delas.

#### Resolução do Exercício 10.4 (Normalização)
1. **DFs e Chaves Candidatas:**
   - DFs: `NumUtente, Especialidade → Medico` e `Medico → Especialidade`
   - Chaves Candidatas: `{NumUtente, Especialidade}` e `{NumUtente, Medico}`.
2. **Análise de Forma Normal:**
   - **Na 3FN:** Sim, pois todos os atributos da tabela (`NumUtente`, `Especialidade`, `Medico`) são atributos primos (fazem parte de pelo menos uma chave candidata).
   - **Na FNBC:** Não, porque para a DF `Medico → Especialidade`, o determinante `Medico` não é chave candidata.
   - **Normalização para FNBC:**
     - **MedicoEspecialidade**(`Medico`, `Especialidade`) | PK: `Medico`
     - **ConsultaUtente**(`NumUtente`, `Medico`) | PK: (`NumUtente`, `Medico`) | FK: `Medico` → MedicoEspecialidade

---

#### Resolução do Exercício 11.1 (Diagramas E/R)
Notação de Chen:
1. **NIF (Simples/Identificador):** Elipse com o texto "NIF" sublinhado, ligada por uma linha reta à entidade `Pessoa`.
2. **Nome Completo (Composto):** Elipse "Nome Completo" ligada à entidade, da qual partem duas elipses secundárias ("Nome Próprio" e "Apelido").
3. **Hobbies (Multi-valor):** Elipse com contorno duplo contendo "Hobbies", ligada à entidade.
4. **Idade (Derivado):** Elipse com linha tracejada contendo "Idade", ligada à entidade (calculada em runtime a partir da elipse normal da "Data de Nascimento").

#### Resolução do Exercício 11.2 (Diagramas E/R)
Trata-se de uma relação N:M.
- **Cliente**(`ID_Cliente`, `Nome`, `Telemovel`) | PK: `ID_Cliente`
- **Quarto**(`NumQuarto`, `Tipo`, `PrecoBase`) | PK: `NumQuarto`
- **Aluguer**(`ID_Cliente`, `NumQuarto`, `DataAluguer`, `PrecoPago`) | PK: (`ID_Cliente`, `NumQuarto`, `DataAluguer`) | FK: `ID_Cliente` → Cliente, `NumQuarto` → Quarto

#### Resolução do Exercício 11.3 (Diagramas E/R)
1. **Disjunção:**
   - *Disjunta (Disjoint):* O veículo é Carro ou é Mota, não podendo pertencer a ambas em simultâneo.
   - *Sobreposta (Overlapping):* Um veículo poderia pertencer a ambas em simultâneo (se as classes fossem, por exemplo, Veículo Comercial e Veículo a Diesel).
2. **Totalidade:**
   - *Total (Mandatory):* Todo o veículo genérico na BD tem de pertencer obrigatoriamente a uma subclasse (Carro ou Mota).
   - *Parcial (Optional):* Pode haver um veículo na BD que não é Carro nem Mota (ex: Camião), ficando apenas registado na tabela genérica de veículos.

#### Resolução do Exercício 11.4 (Diagramas E/R)
- **Característica:** Uma entidade fraca não tem atributos que consigam formar uma chave primária por si só. Depende da existência de uma entidade forte (entidade identificadora) e de um relacionamento identificador.
- **Exemplo Lógico:**
  - `Empregado(ID_Emp, Nome)` | PK: `ID_Emp`
  - `Dependente(ID_Emp, NomeDependente, Parentesco)` | PK: (`ID_Emp`, `NomeDependente`) | FK: `ID_Emp` → Empregado (ON DELETE CASCADE)

---

#### Resolução do Exercício 12.1 (Data Warehousing)
- **Star Schema:** As tabelas de dimensão encontram-se completamente desnormalizadas (redundantes).
  - *Impacto:* Leituras muito mais rápidas (OLAP) e queries mais simples porque exige menos junções (`JOINs`). Ocupa mais espaço em disco.
- **Snowflake Schema:** As tabelas de dimensão encontram-se normalizadas (divididas em sub-tabelas).
  - *Impacto:* Reduz a redundância de dados e poupa espaço de armazenamento. Contudo, degrada a performance de leitura pois as queries requerem múltiplos JOINs entre dimensões para aceder à informação.

#### Resolução do Exercício 12.2 (Data Warehousing)
1. **Fases ETL:**
   - *Extraction (Extração):* Captura e leitura dos dados brutos a partir das origens operacionais (ficheiros, bases de dados de produção).
   - *Transformation (Transformação):* Limpeza dos dados, remoção de duplicados, conversão de formatos e aplicação das regras de negócio.
   - *Loading (Carregamento):* Escrita e inserção física dos dados transformados nas tabelas de factos e dimensões do DW.
2. **Fluxo:** Corresponde ao fluxo de dados **Inflow** (entrada).

#### Resolução do Exercício 12.3 (Data Warehousing)
1. **Âmbito e Fontes:** O Data Warehouse é corporativo e abrange toda a organização, recolhendo dados de todas as áreas de negócio e fontes. O Data Mart é departamental, focando-se apenas num assunto (ex: Finanças).
2. **Custo e Complexidade:** O Data Mart é muito mais barato, simples de desenhar e rápido de implementar. O Data Warehouse é extremamente complexo, requer investimento pesado e pode demorar anos a concretizar.

#### Resolução do Exercício 12.4 (Data Warehousing)
1. **Orientado a Assuntos:** Dados agrupados por temas centrais do negócio (ex: clientes, vendas) e não por funções operacionais da aplicação.
2. **Integrado:** Unificação rigorosa de codificações e formatos de dados provenientes de fontes heterogéneas.
3. **Variável no Tempo:** Dados armazenam todo o histórico temporal (ex: últimos 5 anos de alterações) em vez de apenas o estado presente.
4. **Não-Volátil:** Dados carregados são apenas de leitura. Não sofrem modificações ou deleções habituais, mantendo a consistência dos relatórios analíticos.

---

#### Resolução do Exercício 13.1 (BD Distribuídas)
1. **Replicação Completa:**
   - *Vantagens:* Elevada tolerância a falhas (se Faro falhar, Lisboa e Porto continuam ativos autónomos) e rapidez na leitura local sem necessidade de tráfego de rede.
   - *Desvantagens:* Alto custo de armazenamento e lentidão nas escritas (overhead extremo para propagar e sincronizar alterações em todos os sites da rede).
2. **Particionada:**
   - *Vantagens:* Otimização de armazenamento e alta performance na escrita (cada site insere os seus dados locais rapidamente).
   - *Desvantagens:* Sem tolerância a falhas (se um nó cair, os seus dados ficam indisponíveis para a rede) e consultas globais lentas devido ao custo de transmissão de dados via rede.

#### Resolução do Exercício 13.2 (BD Distribuídas)
1. **Fragmentação Horizontal:** Divide as linhas (tuplos) da tabela com base numa condição de filtragem.
   - *Exemplo:*
     - Lisboa: $F_1 = \sigma_{Cidade='Lisboa'}(Clientes)$
     - Porto: $F_2 = \sigma_{Cidade='Porto'}(Clientes)$
2. **Fragmentação Vertical:** Divide as colunas (atributos) da tabela, projetando-as. A PK deve constar em todos os fragmentos.
   - *Exemplo:*
     - Lisboa: $F_A = \pi_{ID, Nome, Cidade}(Clientes)$
     - Porto: $F_B = \pi_{ID, LimiteCredito}(Clientes)$

#### Resolução do Exercício 13.3 (BD Distribuídas)
- **Shared Memory:** Processadores partilham a mesma memória RAM e discos. Rápido, mas muito fraca escalabilidade (gargalo no acesso à RAM).
- **Shared Disk:** Processadores têm a sua memória privada mas partilham os discos. Escalabilidade intermédia, limitada pelo acesso ao barramento do disco.
- **Shared Nothing:** Processadores têm a sua própria memória RAM e discos autónomos. É o modelo ideal para escalabilidade ilimitada e em larga escala (sistemas Big Data).

#### Resolução do Exercício 13.4 (BD Distribuídas)
- **Fase 1: Preparação (Prepare):** O nó Coordenador envia um pedido de preparação a todos os nós Participantes. Cada Participante simula a transação até ao ponto de commit, coloca os bloqueios de escrita e vota "Vote_Commit" (se tudo correr bem) ou "Vote_Abort" (se houver erro).
- **Fase 2: Decisão (Commit):** Se todos os Participantes votarem a favor, o Coordenador envia a mensagem "Global_Commit" e todos efetuam a escrita permanente. Se algum votar contra ou falhar o timeout, o Coordenador envia a mensagem "Global_Abort" e todos executam ROLLBACK das operações efetuadas.

---
---

### 📌 Resoluções dos Exercícios de Exame Originais

Nesta secção encontram-se as resoluções detalhadas de todos os exercícios de exames anteriores, divididos por grupos temáticos.

<details>
<summary><b>🔑 Ver Resolução Oficial</b></summary>

##### Caso III.5 (Contrato AutoFlex)
1. **Tabelas Finais na 3ª Forma Normal (3FN):**
   - **Empresa**(`NIF_Empresa`, `Nome_Empresa`, `Morada_Empresa`) | PK: `NIF_Empresa`
   - **Agencia**(`CodAgencia`, `NomeAgencia`) | PK: `CodAgencia`
   - **Viatura**(`Matricula`, `Marca`, `Modelo`, `CategoriaViat`, `PrecoDiario`, `Combustivel`) | PK: `Matricula`
   - **Condutor**(`NIF_Condutor`, `Nome_Condutor`, `CartaConducao`, `CategoriaCC`) | PK: `NIF_Condutor`
   - **Extra**(`CodExtra`, `DescricaoExtra`, `PrecoExtraDia`) | PK: `CodExtra`
   - **Contrato**(`NumContrato`, `DataInicio`, `NIF_Empresa`, `CodAgenciaLev`, `CodAgenciaDev`, `Matricula`, `Duracao`, `TotalFatura`, `MetodoPagamento`, `DataDevolucao`) | PK: `NumContrato` | FK: `NIF_Empresa` → Empresa, `CodAgenciaLev` → Agencia, `CodAgenciaDev` → Agencia, `Matricula` → Viatura
   - **ContratoCondutor**(`NumContrato`, `NIF_Condutor`) | PK: (`NumContrato`, `NIF_Condutor`) | FK: `NumContrato` → Contrato, `NIF_Condutor` → Condutor
   - **ContratoExtra**(`NumContrato`, `CodExtra`) | PK: (`NumContrato`, `CodExtra`) | FK: `NumContrato` → Contrato, `CodExtra` → Extra
   *Dependências Funcionais (DFs):*
   - $NumContrato \rightarrow DataInicio, NIF\_Empresa, CodAgenciaLev, CodAgenciaDev, Matricula, Duracao, TotalFatura, MetodoPagamento, DataDevolucao$
   - $NIF\_Empresa \rightarrow Nome\_Empresa, Morada\_Empresa$
   - $CodAgencia \rightarrow NomeAgencia$
   - $Matricula \rightarrow Marca, Modelo, CategoriaViat, PrecoDiario, Combustivel$
   - $NIF\_Condutor \rightarrow Nome\_Condutor, CartaConducao, CategoriaCC$
   - $CodExtra \rightarrow DescricaoExtra, PrecoExtraDia$
</details>


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


### 📝 Exercícios Reais de Exames Anteriores
##### ❓ Pergunta Real de Exame: Tipos de Atributos no Diagrama Entidade-Relacionamento
6. **Tipos de Atributos no Diagrama Entidade-Relacionamento**
   - *Origem:* Exame 2022/2023 (Normal - Q6), Exame Modelo 2 (Q6).
   - *Enunciado:* Descreva o que representam os atributos num diagrama ER e dê exemplos práticos de atributos simples, compostos, multi-valor e derivados, indicando também a sua representação gráfica na notação de Chen.

<details>
<summary><b>🔑 Ver Resolução Oficial</b></summary>

6. **Tipos de Atributos no Diagrama ER (Chen):**
   - **Simples (Atómico):** Propriedade indivisível (ex: NIF). Representação: Elipse simples.
   - **Composto:** Propriedade decomposta em subatributos (ex: Morada, decomposta em Rua, Localidade, CodPostal). Representação: Elipse principal ligada a elipses secundárias.
   - **Multi-valor:** Propriedade que admite vários valores no mesmo registo (ex: Telefone, Hobbies). Representação: Elipse de contorno duplo.
   - **Derivado:** Propriedade calculada a partir de outros atributos (ex: Idade, obtida da DataNascimento). Representação: Elipse com linha tracejada.
</details>


##### ❓ Pergunta Real de Exame: Técnicas de Descoberta de Factos (Fact-Finding)
15. **Técnicas de Descoberta de Factos (Fact-Finding)**
    - *Origem:* Exame 2025/2026 (Normal - Q6).
    - *Enunciado:* Descreva qual o propósito das técnicas de descoberta de factos (fact-finding) na fase de levantamento de requisitos. Enuncie as técnicas mais comuns e explique sucintamente o que cada uma pretende atingir.

<details>
<summary><b>🔑 Ver Resolução Oficial</b></summary>

15. **Técnicas de Descoberta de Factos (Fact-Finding):**
    - **Propósito:** Recolher de forma sistemática factos e requisitos de dados e processos junto dos utilizadores da organização.
    - **As 5 Técnicas:**
      1. *Exame de Documentação:* Analisar formulários, relatórios e manuais de procedimentos existentes.
      2. *Entrevistas:* Falar diretamente com utilizadores para compreender as suas necessidades e fluxos de trabalho.
      3. *Observação:* Acompanhar a operação diária dos utilizadores para verificar o fluxo real dos dados.
      4. *Questionários:* Distribuir perguntas estruturadas a um grande volume de utilizadores para recolha estatística.
      5. *Pesquisa / Benchmarking:* Estudar sistemas semelhantes ou standards de mercado.
</details>


##### ❓ Pergunta Real de Exame: Metodologia de Desenvolvimento: 3 Grandes Etapas
16. **Metodologia de Desenvolvimento: 3 Grandes Etapas**
    - *Origem:* Exame 2020/2021 (Normal - Q2).
    - *Enunciado:* A metodologia de desenvolvimento de Bases de Dados estudada ao longo do semestre engloba três grandes etapas. Identifique cada uma delas, focando-se no objetivo que cada uma pretende atingir, e apresente um exemplo prático que a caracterize.

<details>
<summary><b>🔑 Ver Resolução Oficial</b></summary>

16. **Metodologia de Desenvolvimento: 3 Grandes Etapas:**
    - **Desenho Conceptual:** Modelação abstrata e independente do SGBD (ex: Diagrama Entidade-Relacionamento com entidades como `Cliente` e `Encomenda`).
    - **Desenho Lógico:** Mapeamento do modelo conceptual para o modelo do SGBD (ex: Esquema Relacional de Tabelas com chaves primárias e estrangeiras).
    - **Desenho Físico:** Implementação prática das estruturas lógicas em disco (ex: definição de tipos de dados, criação de índices B-Tree e partições de ficheiros).
</details>


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


### 📝 Exercícios Reais de Exames Anteriores
##### ❓ Pergunta Real de Exame: Benefícios e Problemas de Data Warehouses
11. **Benefícios e Problemas de Data Warehouses**
    - *Origem:* Exame 2024/2025 (Normal - Q6), Exame Modelo 1 (Q6).
    - *Enunciado:* Descreva os principais benefícios e problemas associados ao desenvolvimento e utilização de Data Warehouses (DW). Distinga também um Data Warehouse de um Data Mart.

<details>
<summary><b>🔑 Ver Resolução Oficial</b></summary>

11. **Benefícios e Problemas de Data Warehouses (DW):**
    - **Benefícios:** Centralização de dados integrados de origens operacionais heterogéneas, capacidade de análise histórica a longo prazo e isolamento de performance (evita que queries analíticas OLAP degradem o sistema transacional OLTP de produção).
    - **Problemas:** Elevado custo e tempo de implementação, extrema complexidade nos processos de ETL para garantir a qualidade de dados e a necessidade de manutenção constante face a alterações estruturais nos sistemas operacionais de origem.
    - **Data Mart:** Subconjunto de um Data Warehouse focado exclusivamente num departamento ou área de negócio (ex: Marketing), sendo muito mais barato e simples de implementar.
</details>


##### ❓ Pergunta Real de Exame: Diferenças entre Data Warehouse e Data Mart
21. **Diferenças entre Data Warehouse e Data Mart**
    - *Origem:* Exame Recurso 2023/2024 (Q6).
    - *Enunciado:* Quais as diferenças organizacionais e estruturais entre um Data Mart e um Data Warehouse? Identifique também quais as razões principais para a criação e desenvolvimento de um Data Mart.

---

<details>
<summary><b>🔑 Ver Resolução Oficial</b></summary>

21. **Diferenças entre Data Warehouse e Data Mart:**
    - O Data Warehouse abrange os dados de toda a organização de forma centralizada e corporativa. O Data Mart é departamental, focando-se num assunto ou departamento específico (ex: Vendas).
    - **Razões para Data Mart:** Implementação muito mais económica, tempos de desenvolvimento curtos, maior simplicidade no desenho de esquemas e facilidade de personalização para os utilizadores de um determinado setor.

---
</details>


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


### 📝 Exercícios Reais de Exames Anteriores
> ⚠️ *Nota: Não foram cobradas perguntas teóricas ou práticas diretas sobre bases de dados distribuídas e paralelas nos exames recentes analisados. No entanto, sugere-se a revisão dos conceitos de fragmentação e do protocolo Two-Phase Commit (2PC) descritos na teoria.*


---

## 14. Exercícios Práticos e de Exame

> **Para facilitar o estudo, os exercícios estão organizados em dois documentos separados:**
> 👉 **[Exercícios de Aprendizagem de Conceitos (Aprender Conceitos)](Exercicios_Aprendizagem_Conceitos_BD.md)** — Focado na consolidação inicial dos conceitos teóricos e práticos básicos de cada capítulo.
> 👉 **[Exercícios Tipo Exame (Praticar Exames)](Exercicios_Exames_BD.md)** — Contém os enunciados e as resoluções de exercícios de exames anteriores para simulação real de prova.

---

## 📊 Análise de Frequência — Perguntas de Todos os Exames Recentes

Esta análise contabiliza a frequência com que cada tema foi cobrado nas partes teórica e prática em **todos os exames recentes** disponíveis na pasta @[Exames Recentes](file:///c:/Users/diogo/Documents/GitHub/Stuff-LSIRC/2%C2%BAano/2%C2%BAsemestre/BD/STUDY_STUFF/exames/Exames%20Recentes) (EN 20/21, EN 23/24, ER 23/24, EN 24/25, EN 25/26, Modelo 1 e Modelo 2).

| Tema / Pergunta | EN 20/21 | EN 23/24 | ER 23/24 | EN 24/25 | EN 25/26 | Mod 1 | Mod 2 | Frequência |
|---|:---:|:---:|:---:|:---:|:---:|:---:|:---:|:---:|
| **Normalização de Fatura (Prática)** | ✅ | ✅ | ✅ | ✅ | ✅ | ✅ | ✅ | **7/7 (100%)** 🔴 |
| **SQL Prático (SELECT/JOIN/GROUP)** | ✅ | ✅ | ✅ | ✅ | ✅ | ✅ | ✅ | **7/7 (100%)** 🔴 |
| **Álgebra Relacional Prática** | ✅ | ✅ | ✅ | ✅ | ✅ | ✅ | ✅ | **7/7 (100%)** 🔴 |
| **Vistas (Views) / Resolução / Mat.** | ✅ | — | ✅ | ✅ | ✅ | ✅ | — | **5/7 (71%)** 🔴 |
| **Integridade Referencial (IR)** | ✅ | ✅ | — | ✅ | — | ✅ | — | **4/7 (57%)** 🔴 |
| **Independência de Dados / ANSI/SPARC** | — | ✅ | ✅ | — | — | ✅ | ✅ | **4/7 (57%)** 🔴 |
| **Triggers (Teoria)** | ✅ | — | — | ✅ | — | ✅ | — | **3/7 (43%)** 🟠 |
| **Normalização (Teoria / Objetivos)** | — | — | ✅ | ✅ | — | — | ✅ | **3/7 (43%)** 🟠 |
| **Data Warehouses / Data Marts** | — | — | ✅ | ✅ | — | ✅ | — | **3/7 (43%)** 🟠 |
| **LMD Procedimental vs Declarativa** | — | — | — | ✅ | ✅ | — | ✅ | **3/7 (43%)** 🟠 |
| **Diag. E/R + Tabela Associativa / PK/FK** | — | — | — | ✅ | — | ✅ | ✅ | **3/7 (43%)** 🟠 |
| **Cliente-Servidor 2 vs 3 níveis** | — | ✅ | — | — | — | — | ✅ | **2/7 (29%)** 🟡 |
| **Subquery vs Junção (Teoria)** | — | ✅ | — | — | — | ✅ | — | **2/7 (29%)** 🟡 |
| **Atributos em diag. E/R (Teoria)** | — | ✅ | — | — | — | — | ✅ | **2/7 (29%)** 🟡 |
| **Cursores SQL (Teoria)** | — | — | ✅ | — | — | — | ✅ | **2/7 (29%)** 🟡 |
| **Funções de Agregação e NULLs** | — | — | ✅ | — | ✅ | — | — | **2/7 (29%)** 🟡 |
| **Anomalias de atualização** | ✅ | — | — | — | — | — | — | **1/7 (14%)** 🟡 |
| **Tipos de Join (Natural, Theta, Outer)** | ✅ | — | — | — | — | — | — | **1/7 (14%)** 🟡 |
| **Abordagens múltiplas vistas** | — | ✅ | — | — | — | — | — | **1/7 (14%)** 🟡 |
| **Metodologia de desenvolvimento BD** | ✅ | — | — | — | — | — | — | **1/7 (14%)** 🟡 |
| **Definições: BD, SGBD, Metadados** | — | — | — | — | ✅ | — | — | **1/7 (14%)** 🟡 |
| **Técnicas de descoberta de factos** | — | — | — | — | ✅ | — | — | **1/7 (14%)** 🟡 |

---

## 📌 Resumo Rápido — O que Estudar por Prioridade

### 🔴 Prioridade MÁXIMA (saiu na maioria ou totalidade dos exames):
1. **Normalização de Fatura (Prática)** — UNF → 1FN → 2FN → 3FN com dependências funcionais e definições (**7/7**) (Vale 3 val.!)
2. **SQL Prático** — Queries complexas usando SELECT com JOINs, GROUP BY, HAVING, DATEDIFF e subqueries (**7/7**) (Vale 3 val.!)
3. **Álgebra Relacional Prática** — Seleção, projeção, junção, e padrão de diferença com negação duplo (**7/7**) (Vale 2 val.!)
4. **Vistas (Views)** — Definição, view vs relação base, resolução e materialização (**5/7**) (Vale 2 val.)
5. **Integridade Referencial** — ON DELETE/UPDATE (CASCADE, SET NULL, SET DEFAULT, NO ACTION / RESTRICT) (**4/7**) (Vale 2 val.)
6. **Independência de Dados / ANSI/SPARC** — Níveis de schema, importância do Nível Conceptual e independências física/lógica (**4/7**) (Vale 2 val.)

### 🟠 Prioridade ALTA (saiu em cerca de metade dos exames):
7. **Triggers** — Definição, vantagens/desvantagens e momentos (BEFORE/AFTER/INSTEAD OF) (**3/7**)
8. **Normalização (Teoria)** — Objetivos do processo e o seu impacto no desempenho OLAP/OLTP (**3/7**)
9. **Data Warehouses & Data Marts** — Benefícios e problemas gerais, distinção e razões para criar um Data Mart (**3/7**)
10. **LMD Procedimental vs Declarativa (Não-Procedimental)** — Diferenças conceituais e exemplos (**3/7**)
11. **Modelação ER prático** — Chaves PK/FK e tabelas associativas M:N com data (ex: voos, ginásio, ordens fabrico) (**3/7**)

### 🟡 Prioridade MÉDIA (saiu em 1 ou 2 exames — pode calhar!):
12. **Cliente-Servidor 2 vs 3 níveis** — Localização da lógica e adequabilidade para a Web (**2/7**)
13. **Subquery vs Junção (Teoria)** — Diferenças conceituais e limitações das subqueries (**2/7**)
14. **Atributos em diag. E/R** — Simples, compostos, multi-valor e derivados (Chen) (**2/7**)
15. **Cursores SQL** — Propósito e ciclo de vida (DECLARE, OPEN, FETCH, CLOSE, DEALLOCATE) (**2/7**)
16. **Funções de Agregação e NULLs** — Filtros HAVING vs WHERE e impacto dos nulos nas funções (**2/7**)
17. **Anomalias de atualização** — Inserção, Remoção e Modificação em esquemas redundantes (**1/7**)
18. **Tipos de Join** — Diferença entre Theta Join, Equijoin, Natural Join, Outer Join e Semijoin (**1/7**)
19. **Técnicas de descoberta de factos** — Propósito e as 5 técnicas principais (**1/7**)
20. **Definições teóricas** — BD, SGBD (5 componentes) e Metadados (**1/7**)
21. **Metodologia de desenvolvimento** — As 3 grandes etapas (Conceptual, Lógico, Físico) (**1/7**)
22. **Abordagens múltiplas vistas** — Centralizada, integração de vistas e mista (**1/7**)

---

## 🧠 Dicas para o Exame

1. **Prática vale 50% da nota** — A normalização (3 val.) e SQL/ÁR (5 val.) perfazem 8 valores imediatos. Pratica muito com os casos reais estruturados no caderno.
2. **Gramática e Estrutura** — Os exames de recurso salientam explicitamente que as respostas teóricas devem seguir boas práticas gramaticais (com sujeito e predicado).
3. **Padrão dos 12 valores teóricos** — São tipicamente 6 perguntas teóricas de 2 valores cada. Muitas delas repetem-se diretamente entre Épocas Normais e Recursos.
4. **Álgebra Relacional com Diferença (−)** — Aparece sistematicamente para responder a restrições de negação ("quais os X que nunca..."). Domina o padrão da subtração de conjuntos.

---

> 💡 **Nota:** Este guia foi gerado a partir dos slides PPS (Aula 1–11), do documento "BD-Todas-As-Perguntas", dos helpers (normalização, T-SQL, fatura) e consolidado com a totalidade dos exames e modelos de **2020/2021** a **2025/2026** como referência.
