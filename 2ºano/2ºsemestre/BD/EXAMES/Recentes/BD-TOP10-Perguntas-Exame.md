# 🎯 BD — TOP 15 Perguntas para Exame (Prioridade Máxima)

> Estas 15 perguntas cobrem a esmagadora maioria da componente teórica de desenvolvimento nos exames de BD.
> Ordenadas por frequência de aparecimento nos exames (2004–2025).
> 📌 **Atualizado com TODOS os exames disponíveis incluindo 2024/2025 e o ficheiro "BD - Resumos & Perguntas".**

---

## ⭐⭐⭐ PERGUNTA 1 — Integridade Referencial + ON DELETE / ON UPDATE
**Saiu em: 8+ exames incluindo 2024/2025, 2022/2023 (Normal 23/24), EN2021, Normal 07/08, Especial 07/08**

### Pergunta:
Explique o que entende por Integridade Referencial. Identifique e descreva quais as ações que se podem utilizar nas subcláusulas ON DELETE e ON UPDATE.

### Resposta Detalhada (Estudo):
A **INTEGRIDADE REFERENCIAL** é uma regra de integridade do modelo relacional que garante a consistência das ligações entre tabelas. Assegura que os valores de uma chave estrangeira (foreign key - FK) numa tabela correspondem a valores existentes na chave primária (primary key - PK) da tabela relacionada (tabela pai) ou sejam nulos.

**Exemplo:** Se a tabela Encomendas tiver a coluna FK `ID_Cliente` ligada à tabela Clientes, este valor deve obrigatoriamente existir na coluna PK `ID` de Clientes (ou ser NULL).

Para gerir a eliminação ou alteração de registos pais e evitar órfãos, utilizam-se as subcláusulas ON DELETE e ON UPDATE com as seguintes ações:
*   **CASCADE**: Propaga a operação automaticamente para os registos filhos (ex: ao apagar o pai, apaga os filhos; ao atualizar o ID do pai, atualiza nos filhos).
*   **SET NULL**: Altera a coluna FK de todos os registos filhos correspondentes para `NULL` (requer que a coluna FK permita valores nulos).
*   **SET DEFAULT**: Altera a coluna FK de todos os registos filhos para o valor por defeito especificado para essa coluna.
*   **NO ACTION / RESTRICT**: Rejeita a operação de eliminação/atualização no registo pai se houver registos dependentes na tabela filha. É o comportamento por defeito (Default).

### 📝 Resposta Rápida (Para o Exame):
A integridade referencial é uma regra do modelo relacional que garante a consistência lógica entre tabelas, obrigando a que os valores de uma chave estrangeira (FK) na tabela filha existam previamente na chave primária (PK) da tabela pai ou sejam nulos. Para gerir alterações, a ação CASCADE propaga a eliminação ou atualização do registo pai diretamente para os registos filhos, a ação SET NULL define a FK dos filhos como nula (caso a coluna o permita), a ação SET DEFAULT altera a FK dos filhos para o valor padrão configurado, e a ação NO ACTION (ou RESTRICT) rejeita a operação no registo pai caso existam registos filhos dependentes.

---

## ⭐⭐⭐ PERGUNTA 2 — Normalização: Objetivos e Impacto no Desempenho
**Saiu em: 8+ exames incluindo 2024/2025, Recurso 23/24, Normal 08/09, Normal 07/08**

### Pergunta:
No contexto do modelo relacional de bases de dados, quais os objetivos da normalização de dados? De que forma o processo de normalização poderá afetar, posteriormente, o desempenho da respetiva implementação?

### Resposta Detalhada (Estudo):
O **OBJETIVO DA NORMALIZAÇÃO** é decompor relações complexas em esquemas mais simples com base nas suas chaves primárias/candidatas e dependências funcionais, de forma a:
1.  **Minimizar a redundância de dados** — evita duplicar informação desnecessariamente.
2.  **Eliminar anomalias de atualização** — garante consistência em inserções, remoções e alterações.
3.  **Garantir a integridade e consistência lógica** — as dependências mantêm-se corretas.
4.  **Organizar os dados logicamente** — o modelo relacional fica mais limpo e legível.

### Definições das Formas Normais:
*   **FNN**: Tabela que contém um ou mais grupos repetidos.
*   **1FN**: Uma relação em que a intersecção entre uma linha e uma coluna contém um e um só valor (valores atómicos).
*   **2FN**: Está na 1FN e todos os atributos não primos dependem totalmente da chave primária (sem dependências parciais).
*   **3FN**: Está na 2FN e não possui dependências transitivas (nenhum atributo não primo depende de outro não primo).
*   **BCNF (Boyce-Codd)**: Relação na 3FN onde todo o determinante é uma chave candidata.

### Impacto no desempenho:
*   **Nas Operações de Leitura/Consulta (OLAP)**: O desempenho pode ser **prejudicado**. Como os dados são distribuídos por várias tabelas menores, as consultas necessitam de efetuar múltiplas junções (`JOIN`), aumentando o processamento de CPU e o número de acessos de E/S (leitura em disco).
*   **Nas Operações de Escrita/Atualização (OLTP)**: O desempenho é **otimizado**. Como as tabelas são mais estreitas e não há redundância, as escritas ocorrem num único registo de forma muito mais célere e segura, sem necessidade de atualizar réplicas em múltiplos locais.

### Desnormalização:
É o processo intencional de reverter parcialmente a normalização, introduzindo alguma redundância nos dados, com o objetivo de melhorar o desempenho. Exemplo: uma tabela de blog onde cada publicação é escrita uma vez mas lida constantemente beneficia de desnormalização.

### 📝 Resposta Rápida (Para o Exame):
Os objetivos da normalização de dados consistem em eliminar a redundância de dados, evitar anomalias de atualização e garantir a consistência e integridade lógica das relações. Este processo afeta o desempenho de forma mista: prejudica as operações de leitura e consulta porque a fragmentação dos dados exige a realização de mais junções (JOINs), que aumentam os acessos ao disco e o tempo de resposta; contudo, otimiza as operações de escrita e modificação porque as tabelas são mais estreitas, não há dados duplicados a sincronizar e a escrita ocorre de forma mais rápida num único local.

---

## ⭐⭐⭐ PERGUNTA 3 — Anomalias de Atualização
**Saiu em: 6+ exames incluindo EN2021, Especial 08/09**

### Pergunta:
Descreva os tipos de anomalias de atualização (dê exemplos) que podem ocorrer numa relação que contém dados redundantes.

### Resposta Detalhada (Estudo):
Quando um esquema relacional não está normalizado e contém redundância, podem surgir três problemas graves no dia a dia operacional:
1.  **ANOMALIA DE INSERÇÃO**: Ocorre quando é impossível inserir determinados dados na base de dados por falta de outro dado independente.
    *   *Exemplo:* Numa tabela que combina `Estudantes` e `Disciplinas`, não é possível registar uma nova disciplina na BD se ainda não existir nenhum estudante matriculado nela.
2.  **ANOMALIA DE REMOÇÃO (ELIMINAÇÃO)**: Ocorre quando a eliminação de um registo provoca, involuntariamente, a perda de outras informações úteis e distintas.
    *   *Exemplo:* Ao apagar o único registo do estudante João na disciplina de Matemática, perde-se toda a informação sobre a própria disciplina "Matemática" (como nome do docente e créditos).
3.  **ANOMALIA DE MODIFICAÇÃO (ATUALIZAÇÃO)**: Ocorre quando a alteração de um valor exige a atualização redundante de várias linhas. Se o processo falhar nalgumas linhas, a BD entra num estado inconsistente.
    *   *Exemplo:* Se o nome do professor estiver duplicado em 100 registos de alunos e for alterado apenas em 50, a base de dados passa a ter dados contraditórios sobre o mesmo professor.

### 📝 Resposta Rápida (Para o Exame):
A anomalia de inserção ocorre quando é impossível registar dados na base de dados por falta de outra informação independente, como não conseguir introduzir uma disciplina sem ter alunos matriculados. A anomalia de remoção verifica-se quando a eliminação de um registo principal provoca a perda involuntária de dados secundários importantes, como apagar o único aluno inscrito e perder todos os dados da própria disciplina. Por fim, a anomalia de modificação surge quando a alteração de dados redundantes não é propagada a todos os registos idênticos, originando inconsistências de informação.

---

## ⭐⭐⭐ PERGUNTA 4 — Triggers: Definição, Vantagens e Desvantagens
**Saiu em: EN2021, 2024/2025**

### Pergunta:
O que são Triggers de bases de dados e para que servem? Quais as vantagens e desvantagens da utilização de triggers?

### Resposta Detalhada (Estudo):
Um **TRIGGER** (gatilho) é um objeto do SGBD que contém um conjunto de instruções SQL e que é executado automaticamente quando ocorre um determinado evento numa tabela (INSERT, UPDATE ou DELETE).

**Para que servem:**
*   Implementar regras de negócio complexas que não podem ser expressas por restrições normais (como PK, FK ou CHECK).
*   Manter a integridade referencial complexa entre tabelas.
*   Criar logs de auditoria e históricos de alteração automaticamente.
*   Atualizar campos calculados ou tabelas derivadas.

**Vantagens:**
*   **Centralização da Lógica**: A integridade é garantida ao nível da BD, protegendo os dados independentemente da aplicação cliente.
*   **Automatização**: Execução implícita e automática, eliminando código redundante nas aplicações.
*   **Rapidez**: Excelente integração com a arquitetura cliente-servidor.

**Desvantagens:**
*   **Redução de Performance (Overhead)**: Adiciona tempo de processamento a cada operação de escrita.
*   **Efeitos Ocultos**: Como a execução é implícita, pode desencadear efeitos em cascata indesejados difíceis de depurar.
*   **Portabilidade**: A sintaxe varia significativamente entre SGBDs (ex: Oracle PL/SQL vs SQL Server T-SQL).

### Diferença entre Triggers Before, After, Instead Of:

| Tipo | Quando é executada | Aplicação típica |
|------|-------------------|-----------------|
| **BEFORE** | Antes da operação | Validação ou alteração de dados |
| **AFTER** | Depois da operação | Ações complementares ou registos de auditoria |
| **INSTEAD OF** | Em vez da operação | Modificação de views ou controlo total da ação |

### 📝 Resposta Rápida (Para o Exame):
Um trigger é um bloco de código procedural executado de forma automática e implícita pelo SGBD em resposta a uma operação DML (INSERT, UPDATE ou DELETE) numa tabela, servindo para impor regras de negócio complexas, atualizar dados derivados ou criar logs de auditoria. A sua principal vantagem reside na centralização lógica dentro da base de dados e no reforço da integridade independentemente da aplicação cliente. Por outro lado, as desvantagens incluem o overhead de processamento que reduz a performance de escrita, a dificuldade de depuração devido ao disparo implícito e a falta de portabilidade por a sintaxe variar entre os SGBDs.

---

## ⭐⭐⭐ PERGUNTA 5 — Vistas (Views) vs Relações Base
**Saiu em: EN2021, 2024/2025, Recurso 23/24**

### Pergunta:
O que é uma vista? Quais as diferenças entre uma vista e uma relação base?

### Resposta Detalhada (Estudo):
Uma **VISTA (VIEW)** é uma tabela virtual cujo conteúdo é definido por uma consulta SQL (`SELECT`) sobre uma ou mais tabelas reais (relações base). A vista não armazena dados físicos; armazena apenas a sua definição de consulta nos metadados da base de dados.

Uma **RELAÇÃO BASE** é uma tabela física cujos registos são armazenados diretamente no disco do servidor.

### Principais Diferenças:
*   **Armazenamento de Dados**: A relação base consome espaço físico de armazenamento em disco. A vista tradicional não armazena dados (apenas a sua query de definição).
*   **Modificabilidade (DML)**: As tabelas base aceitam qualquer operação direta de escrita. As vistas têm restrições rígidas: só são atualizáveis se mapearem uma única tabela base e não contiverem junções, agrupamentos (`GROUP BY`), funções de agregação ou `DISTINCT`.
*   **Processamento**: O acesso à relação base lê os dados diretamente. A consulta à vista exige a execução da consulta SQL subjacente em tempo real.

### Materialização de Vistas:
Consiste em armazenar fisicamente os resultados da consulta da vista numa tabela temporária em disco (Indexed Views).
*   **Prós**: Acelera exponencialmente a leitura de consultas complexas ou agregados pesados.
*   **Contras**: Introduz overhead nas operações de escrita nas tabelas base, pois o SGBD necessita de recalcular a vista materializada para a manter sincronizada.

### 📝 Resposta Rápida (Para o Exame):
Uma vista é uma relação virtual gerada dinamicamente a partir de uma consulta SQL, ocupando apenas espaço nos metadados para a sua definição, ao passo que uma relação base é uma tabela física persistida no disco que armazena os dados reais. Ao contrário das tabelas base, as vistas não aceitam operações de escrita diretas se contiverem agrupamentos, junções ou funções agregadas, e as consultas a vistas tradicionais exigem a execução em tempo real da consulta SQL interna, ao contrário do acesso direto das relações base. O mecanismo de materialização de vistas armazena fisicamente o resultado da consulta da vista numa tabela temporária em disco para acelerar a velocidade de leitura, apresentando a desvantagem de exigir o recálculo e sincronização dos dados pelo SGBD sempre que ocorrem alterações nas tabelas base.

---

## ⭐⭐⭐ PERGUNTA 6 — Sistemas de BD vs Ficheiros + Vantagens/Desvantagens SGBD
**Saiu em: 7+ exames incluindo Normal 08/09, Especial 08/09, Especial 07/08**

### Pergunta:
Descreva as principais características de um Sistema BD e faça a comparação com os Sistemas Baseados em Ficheiros. Enuncie e explique sucintamente as principais vantagens e desvantagens de um SGBD.

### Resposta Detalhada (Estudo):
Um **SISTEMA DE BASE DE DADOS** centraliza o armazenamento dos dados de uma organização. O acesso e controlo destes dados são feitos por um intermediário de software chamado **SGBD** (Sistema de Gestão de Bases de Dados).

**Comparação com Sistemas Baseados em Ficheiros:**
*   **Independência de Dados**: Nos ficheiros, a estrutura dos dados está ligada ao código da aplicação (dependência física). Nas BDs, a estrutura lógica é independente do armazenamento (independência física e lógica).
*   **Redundância**: Os ficheiros descentralizados geram duplicação de dados e inconsistências. As BDs centralizam os dados, eliminando a redundância.
*   **Concorrência e Segurança**: Sistemas de ficheiros têm controlo de acessos e concorrência limitados. O SGBD implementa gestão concorrente robusta (locks/transações) e controlo de segurança refinado.

**Quando preferir Ficheiros**:
Em aplicações pessoais de baixo volume de dados, de utilizador único, com recursos computacionais severamente limitados e onde o custo ou a complexidade de manutenção de um SGBD seja injustificável.

**Vantagens do SGBD**: Controlo da redundância, partilha concorrente, integridade dos dados, segurança, standards e facilidade de cópias de segurança/recuperação.
**Desvantagens do SGBD**: Elevada complexidade de administração, custo de aquisição e hardware, e maior impacto na organização em caso de falha geral.

### 📝 Resposta Rápida (Para o Exame):
Os sistemas de bases de dados superam os sistemas de ficheiros porque centralizam os dados num único repositório comum, eliminando a duplicação descontrolada de dados e garantindo a independência entre a informação estrutural e as aplicações clientes. As principais vantagens da utilização de um SGBD residem no controlo da redundância, na consistência global dos dados, na segurança refinada de acessos e na gestão eficaz de concorrência com serviços de backup e recuperação. Em contrapartida, as desvantagens incluem a elevada complexidade técnica do sistema, o custo elevado de software e hardware, e o impacto operacional catastrófico em caso de falha geral do servidor de base de dados.

---

## ⭐⭐⭐ PERGUNTA 7 — Nível Conceptual da Arquitetura ANSI/SPARC
**Saiu em: Recurso 23/24, Recurso 08/09 + coberto no "Resumos & Perguntas"**

### Pergunta:
A arquitetura ANSI/SPARC identifica três níveis nos SGBD. Descreva pormenorizadamente o nível intermédio, identificando o seu nome, e o que se pretende que este nível represente.

### Resposta Detalhada (Estudo):
A arquitetura ANSI/SPARC divide a base de dados em três níveis de abstração para garantir a independência de dados: **Nível Externo** (visões dos utilizadores), **Nível Conceptual** (lógico global) e **Nível Interno** (físico/disco).

O nível intermédio designa-se **Nível Conceptual**:
*   Representa a **visão lógica global de toda a base de dados** para toda a organização. O nível conceptual abstrai os detalhes físicos de armazenamento (nível interno) e serve como base para a criação das vistas dos utilizadores (nível externo).
*   É o nível onde o Administrador da BD (DBA) desenha a estrutura da base de dados (esquema conceptual).

**O que este nível representa:**
*   A definição de todas as tabelas (entidades), colunas (atributos) e os relacionamentos existentes.
*   As regras e restrições de integridade impostas (chaves primárias, estrangeiras e restrições CHECK).
*   As regras de acesso, autorização e segurança lógica globais da base de dados.
*   Garante a **independência lógica dos dados**: alterações na estrutura física do disco (nível interno) são mapeadas no conceitual, evitando afetar as aplicações dos utilizadores.

### 📝 Resposta Rápida (Para o Exame):
O nível intermédio da arquitetura designa-se nível conceptual e representa a visão lógica e global de toda a base de dados para a organização. Este nível conceitual define a totalidade das tabelas lógicas, colunas, relacionamentos entre entidades, regras de segurança e restrições de integridade, tais como chaves primárias e estrangeiras. O seu principal propósito consiste em abstrair os utilizadores finais e as aplicações de desenvolvimento dos detalhes físicos de armazenamento no disco, servindo de base para garantir a independência lógica e física dos dados no sistema.

---

## ⭐⭐ PERGUNTA 8 — Data Warehouses: Benefícios e Problemas
**Saiu em: 4+ exames incluindo 2024/2025, Especial 07/08**

### Pergunta:
Descreva os principais benefícios e problemas associados aos Data Warehouses.

### Resposta Detalhada (Estudo):
Um **DATA WAREHOUSE (DW)** é um repositório de dados histórico, integrado, orientado a assuntos e não-volátil, projetado especificamente para apoiar o processo de tomada de decisão da administração.

**Benefícios:**
*   **Integração de Dados**: Reúne dados consolidados de fontes heterogéneas num único local.
*   **Análise Histórica**: Permite avaliar tendências a longo prazo através de dados variáveis no tempo.
*   **Isolamento de Performance**: Evita que consultas analíticas complexas e lentas (`OLAP`) degradem o desempenho dos sistemas transacionais operacionais do dia a dia (`OLTP`).
*   **Apoio à Decisão**: Aumenta a produtividade e a qualidade das decisões dos gestores.

**Problemas:**
*   **Custo e Tempo**: Projetos de longa duração e com custos de implementação e hardware muito elevados.
*   **Complexidade de ETL**: O processo de Extração, Transformation e Carregamento é complexo e propenso a erros para garantir a qualidade de dados.
*   **Manutenção Continua**: Dificuldade em manter o DW atualizado quando os sistemas operacionais fonte sofrem alterações estruturais.

### Data Mart vs Data Warehouse:
Um **DATA MART** é um subconjunto de um **DATA WAREHOUSE** que suporta os requisitos de um determinado departamento ou função de negócio, apresentando a vantagem de ser mais simples e barato de implementar.

### 📝 Resposta Rápida (Para o Exame):
Um Data Warehouse é um repositório histórico, integrado e não-volátil cujo benefício principal consiste em consolidar dados limpos de múltiplas fontes heterogéneas e isolar a carga de processamento, garantindo que consultas analíticas pesadas (OLAP) não afetem a performance transacional (OLTP) do dia a dia. Por outro lado, este sistema apresenta problemas relacionados com o elevado custo e duração do projeto, com a complexidade de desenvolvimento dos fluxos de ETL para tratar e validar dados, e com a necessidade de manutenção constante face a alterações nas fontes originais. Adicionalmente, um Data Mart constitui um subconjunto desse repositório focado num único departamento, sendo uma alternativa mais barata e rápida de construir.

---

## ⭐⭐ PERGUNTA 9 — DML Procedimentais vs Não Procedimentais
**Saiu em: 2024/2025 + coberto no "Resumos & Perguntas"**

### Pergunta:
Apresente as diferenças entre DML Procedimentais e DML Não Procedimentais.

### Resposta Detalhada (Estudo):
As DML (Linguagens de Manipulação de Dados) dividem-se em duas abordagens fundamentais:

*   **DML PROCEDIMENTAIS**:
    *   O utilizador especifica **como** os dados devem ser obtidos.
    *   Exigem a definição de um algoritmo com passos sequenciais e controlo de fluxo (como laços e condicionais).
    *   Operam registo a registo (*one-record-at-a-time*).
    *   *Exemplos:* Álgebra Relacional, blocos de código procedimentais e uso de cursores em PL/SQL e T-SQL.
*   **DML NÃO PROCEDIMENTAIS (DECLARATIVAS)**:
    *   O utilizador especifica apenas **o que** quer obter, sem detalhar o caminho físico.
    *   Não há definição de fluxo lógico no comando; o otimizador do SGBD encarrega-se de escolher o plano físico de execução mais rápido.
    *   Operam em conjuntos de dados (*set-at-a-time*).
    *   *Exemplos:* Instrução SELECT em SQL e Cálculo Relacional.

### 📝 Resposta Rápida (Para o Exame):
As DML procedimentais exigem que o utilizador especifique detalhadamente a sequência de passos físicos e a lógica algorítmica de como obter a informação, efetuando o processamento de forma individual sobre um registo de cada vez, como se verifica na Álgebra Relacional ou na manipulação de cursores. Em contrapartida, as DML não-procedimentais são declarativas e exigem apenas que o utilizador especifique quais os dados que pretende ver apresentados, delegando ao otimizador do SGBD a definição do plano de acesso físico e o processamento de todos os registos em conjunto, como sucede na instrução SELECT de SQL.

---

## ⭐⭐ PERGUNTA 10 — Subquery vs Junção
**Saiu em: 2022/2023 (Normal 23/24) + coberto no "Resumos & Perguntas"**

### Pergunta:
Qual a diferença entre uma subquery e uma junção? Em que situações não é possível usar uma subquery?

### Resposta Detalhada (Estudo):
*   **SUBQUERY (SUBCONSULTA)**: É uma instrução SELECT aninhada (embutida) dentro de outra consulta externa (nas cláusulas `WHERE`, `HAVING`, `FROM` ou `SELECT`). Serve para obter dados temporários ou escalares que alimentam a query principal.
*   **JUNÇÃO (JOIN)**: É uma operação que combina registos de duas ou mais tabelas na mesma linha de dados do resultado final, com base numa condição de igualdade ou lógica comum.

**Em que situações NÃO é possível usar uma subquery:**
*   **Exibição Simultânea de Atributos**: Quando a consulta final exige a projeção (exibição) de colunas pertencentes a tabelas diferentes. Como a subquery apenas atua como filtro ou fonte secundária, só a cláusula `INNER/OUTER JOIN` permite expor colunas de múltiplas tabelas lado a lado no SELECT principal.
*   **Junções Externas Totais**: Em queries que requerem comportamento de `FULL OUTER JOIN` em que a cardinalidade mútua de linhas sem correspondência precisa de ser preservada de ambos os lados da operação.

### 📝 Resposta Rápida (Para o Exame):
Uma subquery consiste numa consulta SELECT interna aninhada dentro de uma instrução SQL externa para calcular valores ou filtros temporários, ao passo que uma junção combina dados de duas ou mais tabelas na mesma linha de resultado com base numa condição. Não é possível utilizar uma subquery em situações em que a consulta exige exibir colunas pertencentes a tabelas distintas simultaneamente na query de resultado, uma vez que as subqueries limitam a projeção apenas às colunas declaradas na tabela da query externa principal.

---

## ⭐⭐ PERGUNTA 11 — Atributos num Diagrama ER
**Saiu em: 2022/2023 (Normal 23/24) + coberto no "Resumos & Perguntas"**

### Pergunta:
Descreva o que representam os atributos num diagrama ER e dê exemplos de atributos simples, compostos, multi-valor e derivados.

### Resposta Detalhada (Estudo):
Num diagrama Entidade-Relacionamento (ER), os atributos representam as **propriedades ou características individuais** que descrevem uma entidade ou relacionamento.

### Classificação e Exemplos (Notação de Chen):
*   **SIMPLES (ATÓMICO)**: Contém um valor único e indivisível.
    *   *Exemplo:* Número de contribuinte (NIF) ou Código do Produto.
    *   *Representação:* Elipse simples ligada à entidade.
*   **COMPOSTO**: Pode ser decomposto em subatributos independentes e mais simples.
    *   *Exemplo:* `Morada` (decomposta em Rua, Localidade e Código Postal).
    *   *Representação:* Elipse principal ligada a elipses secundárias.
*   **MULTI-VALOR**: Pode conter mais do que um valor para uma mesma entidade.
    *   *Exemplo:* `Telefone` (uma pessoa pode ter vários contactos) ou Hobbies.
    *   *Representação:* Dois círculos concêntricos (elipse dupla).
*   **DERIVADO**: Não é armazenado fisicamente; é calculado a partir de outros atributos existentes.
    *   *Exemplo:* `Idade` (calculada a partir da Data de Nascimento e da data atual).
    *   *Representação:* Elipse com contorno tracejado.

### 📝 Resposta Rápida (Para o Exame):
Num diagrama ER, os atributos representam as propriedades que descrevem as entidades ou relacionamentos do sistema. Como exemplos práticos e respetivas notações, um atributo simples é atómico e indivisível (como o NIF) e desenha-se por uma elipse simples; um composto divide-se em subatributos (como a Morada, decomposta em rua e localidade) e mostra-se por elipses interligadas; um multi-valor admite mais do que um valor para o mesmo registo (como os Telefones) e representa-se por uma elipse de contorno duplo; e um derivado resulta de cálculos de outros campos (como a Idade a partir da data de nascimento) e representa-se por uma elipse com linha tracejada.

---

## ⭐⭐ PERGUNTA 12 — Funções de Agregação e NULLs
**Saiu em: Recurso 23/24, Recurso 08/09**

### Pergunta:
Quais as restrições aplicadas ao uso de funções de agregação no comando SELECT? De que forma os valores nulos (NULL) afetam as funções de agregação?

### Resposta Detalhada (Estudo):
As funções de agregação (`COUNT`, `SUM`, `AVG`, `MIN`, `MAX`) operam sobre um conjunto de valores para devolver um único valor resumo.

**Restrições no Comando SELECT:**
1.  **Impossibilidade de uso na cláusula WHERE**: O `WHERE` filtra linhas individuais antes do agrupamento. Para filtrar o resultado de funções agregadas, deve utilizar-se a cláusula **HAVING** (que executa após o `GROUP BY`).
2.  **Obrigação de GROUP BY**: Se misturarmos colunas individuais não agregadas com funções de agregação no SELECT, todas as colunas individuais devem, obrigatoriamente, constar na cláusula `GROUP BY`.

**Efeito dos Valores Nulos (NULLs):**
*   **`COUNT(*)`**: É a única função que **inclui** e conta valores nulos, pois avalia a linha completa.
*   **`COUNT(coluna)`**: Conta apenas os valores não nulos nessa coluna.
*   **`SUM`, `AVG`, `MIN`, `MAX`**: **Ignoram** completamente os valores nulos no cálculo. Exemplo: se fizermos `AVG` de 10, NULL e 20, o SGBD calcula a média como `(10+20)/2 = 15`.

### 📝 Resposta Rápida (Para o Exame):
As restrições ao uso de funções de agregação em SQL exigem que estas nunca sejam declaradas na cláusula WHERE, devendo a filtragem ser efetuada no HAVING, e obrigam a que qualquer atributo não agregado projetado no SELECT conste obrigatoriamente na cláusula GROUP BY. Relativamente ao comportamento face a valores nulos, a função COUNT(*) contabiliza todas as linhas da relação incluindo nulos, ao passo que todas as restantes funções de agregação, tais como SUM, AVG, MIN, MAX e COUNT de coluna, ignoram inteiramente os valores NULL nos seus cálculos matemáticos.

---

## ⭐ PERGUNTA 13 — Independência de Dados
**Saiu em: 2022/2023 (Normal 23/24), Recurso Adicional 08/09 + coberto no "Resumos & Perguntas"**

### Pergunta:
Descreva o conceito de independência de dados e a sua importância num ambiente de bases de dados.

### Resposta Detalhada (Estudo):
A **INDEPENDÊNCIA DE DADOS** é a propriedade que permite alterar os esquemas de dados de um nível inferior de abstração da arquitetura ANSI/SPARC sem a necessidade de reescrever as estruturas e aplicações situadas nos níveis superiores.

Divide-se em dois tipos:
1.  **Independência Física de Dados**:
    *   Capacidade de alterar o armazenamento físico dos dados (como mudar o disco, reorganizar ficheiros, criar índices ou mudar partições) sem afetar o esquema conceptual (tabelas lógicas) ou as aplicações do utilizador.
2.  **Independência Lógica de Dados**:
    *   Capacidade de alterar o esquema conceptual (como adicionar colunas, criar novas tabelas ou dividir tabelas existentes) sem que seja necessário reescrever o código das queries SQL das aplicações existentes.
    *   *Nota:* Para garantir esta independência face a alterações lógicas, recorre-se normalmente à criação de **vistas** para simular as tabelas originais.

**Importância**: Permite que a base de dados sofra manutenções e otimizações contínuas de forma muito mais barata e flexível, impedindo o impacto em cascata no código das aplicações clientes.

### 📝 Resposta Rápida (Para o Exame):
A independência de dados consiste na capacidade de modificar os esquemas estruturais de um nível inferior de abstração do SGBD sem que seja necessário alterar os esquemas ou as aplicações clientes nos níveis superiores. A independência física permite alterar as configurações de armazenamento físico e indexação em disco sem modificar o esquema lógico ou o código das consultas, ao passo que a independência lógica permite alterar o desenho lógico global das tabelas sem quebrar o funcionamento das aplicações. A sua importância reside na redução significativa dos custos de manutenção de software e na flexibilidade para a evolução da BD.

---

## ⭐ PERGUNTA 14 — Arquitetura Cliente-Servidor (2 vs 3 níveis)
**Saiu em: 2022/2023 (Normal 23/24) + coberto no "Resumos & Perguntas"**

### Pergunta:
Compare a arquitetura cliente-servidor de dois níveis com a de três-níveis e identifique, justificando, qual a mais adequada para a Web.

### Resposta Detalhada (Estudo):
*   **Arquitetura de 2 Níveis (2-tier)**:
    *   A aplicação corre numa máquina cliente (fat client) e comunica diretamente com o servidor de bases de dados.
    *   A máquina cliente é responsável por renderizar a interface gráfica e processar todas as regras de negócio. O servidor de dados apenas executa as queries SQL.
*   **Arquitetura de 3 Níveis (3-tier)**:
    *   Introduz-se uma camada intermédia dedicada: o **Servidor de Aplicação** (Application Server).
    *   O cliente corre uma interface leve (thin client / browser). O Servidor de Aplicação executa toda a lógica e regras de negócio, e comunica com o Servidor de Base de Dados para leitura e gravação dos dados físicos.

**Adequabilidade para a Web**: A arquitetura de **3 níveis** é a única viável e adequada para a Web por:
1.  **Escalabilidade e Pooling de Conexões**: O servidor de aplicação mantém um pool fechado de conexões abertas com a BD, reutilizando-as concorrentemente para servir milhares de pedidos web. Em 2 níveis, cada browser precisaria de abrir uma conexão direta ao SGBD, o que esgotaria os limites de conexão da base de dados de imediato.
2.  **Facilidade de Manutenção**: Atualizações na lógica de negócio são feitas exclusivamente no servidor central de aplicação, sem necessidade de reconfigurar o código nos terminais dos utilizadores.
3.  **Segurança**: O cliente web não tem acesso às credenciais de administrador nem à BD direta, prevenindo ataques maliciosos à integridade dos dados.

### 📝 Resposta Rápida (Para o Exame):
Na arquitetura de dois níveis, a máquina cliente comunica diretamente com a base de dados e aloja a interface e as regras de negócio, enquanto na de três níveis se insere um servidor de aplicação intermédio encarregue de processar a lógica de negócio, libertando o cliente para correr uma interface leve. A arquitetura de três níveis é a mais adequada para o ambiente Web porque permite implementar o pooling de conexões no servidor intermédio para suportar milhares de acessos concorrentes, centraliza as tarefas de manutenção de código no servidor e impede o acesso direto ou inseguro dos utilizadores ao servidor de dados.

---

## ⭐ PERGUNTA 15 — Abordagens para Desenho de BD com Múltiplas Vistas de Utilizadores
**Saiu em: 2022/2023 (Normal 23/24) + coberto no "Resumos & Perguntas"**

### Pergunta:
Enuncie quais as principais abordagens para elaborar o desenho de uma base de dados com múltiplas vistas de utilizadores.

### Resposta Detalhada (Estudo):
Diferentes departamentos e utilizadores de uma empresa necessitam de ver e interagir com diferentes partes da base de dados (vistas). Para desenhar um esquema global coerente que satisfaça todos, utilizam-se três abordagens:

1.  **Abordagem Centralizada (Centralized Integration / Schema Integration)**:
    *   Os requisitos de todas as vistas dos utilizadores são recolhidos, analisados e fundidos numa única lista consolidada de requisitos globais.
    *   A partir desta lista unificada, desenha-se diretamente um único modelo conceitual global (esquema conceitual).
    *   *Ideal para*: Sistemas com poucas vistas ou onde a lógica de negócio não diverge muito entre departamentos.
2.  **Abordagem por Integração de Vistas (View Integration)**:
    *   Constrói-se um modelo conceitual local (esquema local) para cada vista ou departamento de forma independente.
    *   Numa fase posterior, estes esquemas locais são fundidos e harmonizados através de um processo de integração (resolvendo sinónimos, homónimos e conflitos de tipos) para gerar o esquema conceitual global unificado.
    *   *Ideal para*: Sistemas grandes, complexos e com requisitos altamente específicos por setor.
3.  **Abordagem Mista (Mixed Approach)**:
    *   Combina o melhor de ambas as abordagens. Os requisitos comuns e fáceis de consolidar de todas as vistas são fundidos centralizadamente desde o início do projeto.
    *   As visões mais complexas e divergentes são modeladas como esquemas locais separados, integrando-se no modelo global na fase de consolidação final.

### 📝 Resposta Rápida (Para o Exame):
A modelação com múltiplas vistas de utilizadores pode ser elaborada através de três abordagens de desenho. A abordagem centralizada consiste em recolher e consolidar os requisitos de todas as vistas numa lista unificada logo no início do projeto, gerando depois um único modelo conceitual global. A abordagem por integração de vistas constrói esquemas conceituais locais independentes para cada departamento e funde-os posteriormente através de integração e resolução de conflitos num modelo conceitual global final. Por fim, a abordagem mista combina os dois conceitos, fundindo as vistas simples centralizadamente no início e tratando os requisitos complexos localmente antes da consolidação.

---
---

## 📚 BÓNUS — Perguntas Complementares de Alta Frequência

### ⚙️ BÓNUS 1 — Tipos de Junção (Joins)
**Saiu em: EN2021, MiniTeste 08/09**

#### Pergunta:
Descreva as diferenças entre as cinco operações de junção: Theta Join, Equijoin, Natural Join, Outer Join e Semijoin.

#### Resposta Detalhada (Estudo):
*   **THETA JOIN**: Junção geral que combina duas relações com base numa condição que utiliza qualquer operador de comparação ($=$, $>$, $<$, $\ge$, $\le$, $\ne$).
*   **EQUIJOIN**: Caso especial do Theta Join onde a condição de correspondência utiliza estritamente o operador de igualdade ($=$). Não remove as colunas de junção duplicadas.
*   **NATURAL JOIN**: Junção por igualdade realizada automaticamente sobre atributos homónimos (com o mesmo nome). Remove automaticamente as colunas redundantes duplicadas no resultado final.
*   **OUTER JOIN**: Junção que mantém todos os registos de uma das tabelas (ou de ambas), mesmo que não tenham correspondência na outra tabela, preenchendo as colunas vazias com valores `NULL`. Divide-se em `LEFT`, `RIGHT` e `FULL`.
*   **SEMIJOIN**: Retorna apenas os registos da primeira tabela que possuem correspondência na segunda tabela, sem duplicar linhas e sem expor atributos da segunda tabela no resultado.

#### 📝 Resposta Rápida (Para o Exame):
No âmbito da álgebra relacional, a junção Theta Join combina tabelas usando qualquer operador de comparação lógico, o Equijoin restringe a condição à igualdade mantendo as colunas duplicadas, e o Natural Join junta tabelas por colunas homónimas removendo a redundância no resultado. Por sua vez, o Outer Join inclui todas as linhas de um ou de ambos os lados mesmo sem correspondência (LEFT, RIGHT ou FULL) preenchendo as lacunas com valores nulos, e a operação Semijoin devolve apenas os tuplos da primeira tabela que participam na junção com a segunda, sem combinar os seus atributos.

---

### 🗄️ BÓNUS 2 — Cursores SQL
**Saiu em: Recurso 23/24**

#### Pergunta:
O que são cursores SQL? Qual o propósito da sua utilização?

#### Resposta Detalhada (Estudo):
Um **CURSOR** é uma estrutura de controlo mantida pelo SGBD que funciona como um apontador lógico e permite às linguagens procedimentais iterar e processar os registos resultantes de uma consulta SELECT **linha a linha** (abordagem procedimental), contrariando a natureza declarativa natural do SQL que atua em conjuntos de dados (*set-at-a-time*).

**Ciclo de Vida do Cursor**:
1.  `DECLARE`: Define a query SELECT associada ao cursor.
2.  `OPEN`: Executa a query e aloca os recursos de memória.
3.  `FETCH`: Obtém a linha corrente, copia os dados para variáveis e aponta para a linha seguinte.
4.  `CLOSE`: Fecha o cursor e liberta os locks ativos.
5.  `DEALLOCATE`: Remove a definição do cursor da memória (liberta recursos).

#### 📝 Resposta Rápida (Para o Exame):
Um cursor SQL é uma estrutura de controlo que funciona como um apontador lógico para permitir que as linguagens procedimentais manipulem e naveguem linha a linha pelos resultados de uma consulta declarativa SELECT. O propósito fundamental da sua utilização reside no desenvolvimento de algoritmos de manipulação complexos, validações individuais e processamento sequencial de tuplos, contornando a natureza declarativa normal do SQL baseado em conjuntos de dados.

---

### 💳 BÓNUS 3 — Transações e Propriedades ACID
**Coberto no "Resumos & Perguntas"**

#### Pergunta:
O que é uma transação? Descreva as propriedades ACID.

#### Resposta Detalhada (Estudo):
Uma **TRANSAÇÃO** é uma unidade lógica de processamento que agrupa um conjunto de instruções SQL. Deve ser executada na totalidade para manter a consistência da BD.

#### Propriedades ACID:
1.  **Atomicidade**: Princípio do "tudo ou nada". A transação é efetuada na totalidade com sucesso (`COMMIT`) ou todas as alterações são desfeitas e revertidas (`ROLLBACK`).
2.  **Consistência**: A transação deve levar a base de dados de um estado consistente a outro estado consistente, sem violar nenhuma regra de integridade.
3.  **Isolamento**: As transações em execução concorrente devem correr de forma independente, sem interferirem nos dados umas das outras até estarem finalizadas.
4.  **Durabilidade**: Uma vez concluída (efetuado o `COMMIT`), as alterações tornam-se permanentes na BD e não são perdidas mesmo em caso de falha do sistema.

#### 📝 Resposta Rápida (Para o Exame):
Uma transação constitui um conjunto de instruções SQL tratadas de forma unificada como uma única unidade lógica de trabalho indivisível que deve ser executada por completo. As propriedades ACID determinam que a transação deve ser atómica (execução total ou reversão total em caso de erro), consistente (respeito pelas restrições de integridade da base de dados), isolada (transações concorrentes executam sem interferências mútuas temporárias) e durável (persistência permanente dos dados confirmados após o commit).

---

### ⚙️ BÓNUS 4 — Stored Procedure vs Função (UDF)
**Coberto no "Resumos & Perguntas"**

#### Pergunta:
Qual a diferença entre um procedimento (Stored Procedure) e uma função (UDF)?

#### Resposta Detalhada (Estudo):
*   **Função (UDF)**:
    *   Deve obrigatoriamente devolver um valor (escalar ou tabela) via `RETURN`.
    *   Pode ser invocada diretamente dentro de instruções SQL normais (como `SELECT`, `WHERE`, `HAVING`).
    *   Não pode efetuar alterações no estado da base de dados (não permite `INSERT`, `UPDATE` ou `DELETE` em tabelas reais).
*   **Procedimento (Stored Procedure)**:
    *   Não é obrigado a devolver valores (embora possa retornar parâmetros `OUTPUT`).
    *   Not pode ser executado dentro de comandos SQL; é invocado com comandos específicos como `EXEC` ou `CALL`.
    *   Pode alterar dados livremente na base de dados (permite comandos de escrita) e pode gerir transações internas (`COMMIT` e `ROLLBACK`).

#### 📝 Resposta Rápida (Para o Exame):
Uma função (UDF) é obrigatoriamente desenhada para devolver um valor ou tabela de retorno, podendo ser embutida diretamente em consultas SQL tais como instruções SELECT, mas possui a limitação de não poder modificar os dados em disco. Por sua vez, um procedimento (Stored Procedure) não tem obrigatoriedade de retornar valores, é executado externamente através do comando EXEC ou CALL, e permite alterar os registos das tabelas assim como gerir transações internas através de commits e rollbacks.

---

### 🎨 BÓNUS 5 — Especialização e Generalização (Modelo ER)
**Coberto no "Resumos & Perguntas"**

#### Pergunta:
Explique as diferenças entre o processo de especialização e de generalização no contexto da modelação ER.

#### Resposta Detalhada (Estudo):
*   **ESPECIALIZAÇÃO**:
    *   Processo descendente (**top-down**).
    *   Consiste em dividir uma entidade genérica (superclasse) em subclasses com atributos ou relacionamentos específicos.
    *   *Exemplo:* A superclasse `Funcionário` especializa-se nas subclasses `Engenheiro` (com atributo ordem profissional) e `Motorista` (com atributo carta de condução).
*   **GENERALIZAÇÃO**:
    *   Processo ascendente (**bottom-up**).
    *   Consiste em agrupar várias entidades distintas com propriedades semelhantes numa entidade comum mais genérica (superclasse) para simplificar o esquema.
    *   *Exemplo:* As entidades `Aluno` e `Professor` são generalizadas na entidade `Pessoa` (partilhando atributos como NIF, Nome e Morada).

#### 📝 Resposta Rápida (Para o Exame):
A especialização constitui um processo de modelação descendente (top-down) em que uma superclasse geral é dividida em subclasses específicas de modo a atribuir propriedades e relacionamentos particulares, como segmentar Funcionário em Engenheiro ou Motorista. Por outro lado, a generalização constitui um processo ascendente (bottom-up) que consiste em extrair características e atributos idênticos de múltiplas entidades distintas para consolidar e criar uma superclasse mais abstrata, como agrupar as tabelas Aluno e Professor na tabela Pessoa.

---

### 📋 Outros Tópicos Rápidos (Bónus de Auxílio)

#### Cláusulas do SELECT
```
SELECT:    obrigatório, atributos que aparecerão
FROM:      obrigatório, tabela de origem
WHERE:     condição, não podem ser usadas funções de agregação
GROUP BY:  agrupar por atributo
HAVING:    filtrar grupos, podem ser funções de agregação
ORDER BY:  ordenação, última cláusula, por defeito ascendente
```

**Diferença WHERE vs HAVING:** WHERE filtra linhas individuais (antes do GROUP BY), HAVING filtra grupos (depois do GROUP BY).

---

#### Chave Candidata, Primária, Estrangeira
- **CHAVE CANDIDATA**: conjunto mínimo de atributos que identifica univocamente cada tuplo na relação. Pode haver várias.
- **CHAVE PRIMÁRIA**: chave candidata escolhida para a identificação dos tuplos. Valores não podem repetir-se nem ser NULL.
- **CHAVE ESTRANGEIRA**: atributo que faz referência à chave primária de outra tabela. Permite relacionar tuplos de relações diferentes e garante integridade referencial.

---

#### 5 Operações Básicas de Álgebra Relacional
- **SELEÇÃO (σ)**: seleciona tuplos que satisfaçam à condição de seleção
- **PROJEÇÃO (π)**: projeta as colunas solicitadas
- **PRODUTO CARTESIANO (×)**: combina tuplos de duas relações
- **UNIÃO (∪)**: une duas tabelas
- **DIFERENÇA (−)**: dá uma tabela com todas as linhas de A que não estão em B

A partir destas:
- **JUNÇÃO** = Produto Cartesiano + Seleção: A ⋈ B ≡ σ(A × B)
- **INTERSECÇÃO** = A − (A − B)
- **DIVISÃO** = A ÷ B = π_X(A) − π_X((π_X(A) × B) − A)

---

#### Funções que um SGBD deve satisfazer
- Armazenamento, Pesquisa e Atualização de Dados
- Dicionário de Dados (System Catalog)
- Suporte a Transações
- Serviços de Controlo de Concorrência
- Serviços de Recuperação
- Serviços de Autenticação
- Suporte a Comunicação de Dados
- Serviços de Integridade
- Serviços que promovam a Independência de Dados
- Utilitários

---

#### Ciclo de Vida de uma Aplicação de BD
1. Planeamento da Base de Dados / Estudo e levantamento de requisitos
2. Definição do Sistema
3. Recolha e Análise de Requisitos
4. Desenho da BD (Conceptual → Lógico → Físico)
5. Seleção do SGBD (opcional)
6. Desenho da Aplicação
7. Prototipagem (opcional)
8. Implementação
9. Conversão e Alimentação de Dados
10. Testes e Validação
11. Em Operação e Manutenção

---

#### 5 Componentes do SGBD
1. **Hardware**: dispositivos físicos (servidores, discos, redes)
2. **Software**: SGBD, sistema operativo e programas
3. **Dados**: dados armazenados e metadados
4. **Utilizadores**: DBA, programadores, utilizadores finais
5. **Procedimentos**: regras e instruções de uso e funcionamento

---

#### System Catalog
Conjunto de tabelas de sistema e metadados mantido pelo SGBD que descreve a própria estrutura da base de dados.

---

#### Propriedades das Relações
1. Nome único
2. Tuplos distintos (não há linhas repetidas)
3. Sem ordem entre os tuplos
4. Sem ordem entre os atributos
5. Atributos atómicos (indivisíveis)
6. Cada atributo tem um nome único

---

#### Termos do Modelo Relacional
- **Relação**: tabela que armazena dados
- **Atributo**: coluna da tabela
- **Domínio**: conjunto de valores válidos para um atributo
- **Tuplo**: linha da tabela (um registo completo)
- **Grau**: número de atributos (colunas) de uma relação
- **Cardinalidade**: número de tuplos (linhas) de uma relação

---

#### Duas Regras de Integridade no Modelo Relacional
1. **Integridade da Entidade**: cada tabela deve ter PK cujos valores não podem ser nulos nem repetidos
2. **Integridade Referencial**: FK deve corresponder a um valor existente na PK da outra tabela (ou ser nula)

---

#### Importância do WHERE em UPDATE e DELETE
Definir quais os registos que são afetados. Sem WHERE, a instrução afeta **todos os registos** da tabela de forma irreversível.

---

#### 3 Gerações de SGBD
1. **1ª Geração** — Modelos Hierárquico e em Rede (anos 60-70)
2. **2ª Geração** — Modelo Relacional (anos 70-80 até hoje)
3. **3ª Geração** — Modelos Orientado a Objetos e Objeto-Relacional (anos 90+)

---

> 💡 **Dica final:** O exercício de **normalização de uma fatura** sai em TODOS os exames teóricos (vale 3-4 val.). Pratica com os exemplos dos exames anteriores!

---

## 📊 Mapa de Frequência de Perguntas por Exame

| Pergunta | 04-06 | 07/08 | 08/09 | EN2021 | 22/23 | R23/24 | 24/25 |
|----------|:-----:|:-----:|:-----:|:------:|:-----:|:------:|:-----:|
| 1. Integridade Referencial | — | ✅ | — | ✅ | ✅ | — | ✅ |
| 2. Normalização (Teoria) | — | ✅ | ✅ | — | — | ✅ | ✅ |
| 3. Anomalias Atualização | — | — | ✅ | ✅ | — | — | — |
| 4. Triggers | — | — | — | ✅ | — | — | ✅ |
| 5. Vistas (Views) vs Base | — | — | ✅ | ✅ | — | ✅ | ✅ |
| 6. Sist. BD vs Ficheiros | — | ✅ | ✅ | — | — | — | — |
| 7. ANSI/SPARC (Conceptual) | — | — | ✅ | — | — | ✅ | — |
| 8. Data Warehouses | — | ✅ | — | — | — | ✅ | ✅ |
| 9. DML Proc vs Não Proc | — | — | — | — | — | — | ✅ |
| 10. Subquery vs Junção | — | — | — | — | ✅ | — | — |
| 11. Atributos ER | — | — | — | — | ✅ | — | — |
| 12. Funções Agregação/NULL | — | — | ✅ | — | — | ✅ | — |
| 13. Independência Dados | — | — | ✅ | — | ✅ | — | — |
| 14. Arq. Cliente-Servidor (2 vs 3) | — | — | — | — | ✅ | — | — |
| 15. Abordagens múltiplas vistas | — | — | — | — | ✅ | — | — |
| BÓNUS 1. Tipos de Junção | — | — | — | ✅ | — | — | — |
| BÓNUS 2. Cursores SQL | — | — | — | — | — | ✅ | — |
| Prática: Normalização (fatura) | ✅ | ✅ | ✅ | ✅ | ✅ | ✅ | ✅ |
| Prática: Álgebra Relacional | ✅ | ✅ | ✅ | ✅ | ✅ | ✅ | ✅ |
| Prática: SQL LMD | ✅ | ✅ | ✅ | ✅ | ✅ | ✅ | ✅ |
