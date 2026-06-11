# 🎯 BD — TOP Perguntas para Exame (Prioridade Máxima)

> Estas perguntas cobrem ~90% da componente teórica dos exames de BD.
> Ordenadas por frequência de aparecimento nos exames (2004–2025).
> 📌 **Atualizado com TODOS os exames disponíveis incluindo 2024/2025 e o ficheiro "BD - Resumos & Perguntas".**

---

## ⭐⭐⭐ PERGUNTA 1 — Integridade Referencial + ON DELETE / ON UPDATE
**Saiu em: 8+ exames incluindo 2024/2025, 2022/2023, EN2021, Normal 07/08, Especial 07/08**

### Pergunta:
Explique o que entende por Integridade Referencial. Identifique e descreva quais as ações que se podem utilizar nas subcláusulas ON DELETE e ON UPDATE.

### Resposta:
A **INTEGRIDADE REFERENCIAL** é uma regra do modelo relacional que garante a consistência das ligações entre tabelas. Assegura que os valores de uma chave estrangeira (foreign key) em uma tabela correspondem a valores existentes na chave primária da tabela relacionada.

**Exemplo:** Se a tabela Encomendas tiver uma coluna ID_Cliente como chave estrangeira, este valor deve existir na tabela Clientes como ID.

As subcláusulas que podem ser usadas ON DELETE e em ON UPDATE são:

- **CASCADE**: apaga a linha da tabela pai e linhas correspondentes das tabelas filhas, e assim sucessivamente em cascata. Em UPDATE, a alteração é propagada automaticamente para a tabela dependente.
- **SET NULL**: apaga a linha da tabela pai e muda todas as colunas FK na tabela filha para NULL. Só é válido se as colunas FK não estiverem a NOT NULL.
- **SET DEFAULT**: apaga a linha da tabela pai e muda cada componente da FK da tabela filha para o valor default especificado. Só é válido se houver um valor DEFAULT especificado para as colunas FK.
- **NO ACTION** / **RESTRICT**: rejeita a operação da tabela pai. Não permite a alteração se houver registos dependentes. É o comportamento por defeito (Default).

---

## ⭐⭐⭐ PERGUNTA 2 — Normalização: Objetivos e Impacto no Desempenho
**Saiu em: 8+ exames incluindo 2024/2025, Recurso 23/24, Normal 08/09, Normal 07/08**

### Pergunta:
No contexto do modelo relacional de bases de dados, quais os objetivos da normalização de dados? De que forma o processo de normalização poderá afetar, posteriormente, o desempenho da respetiva implementação?

### Resposta:
O **OBJETIVO DA NORMALIZAÇÃO** é analisar uma relação com base na sua chave primária e nas dependências funcionais entre atributos, com os seguintes objetivos:
1. **Eliminar redundância de dados** — evita a repetição desnecessária
2. **Evitar anomalias de atualização** — corrige problemas de inserção, remoção e modificação
3. **Melhorar a integridade e consistência** — garante organização correta e dependências respeitadas
4. **Organizar os dados de forma lógica e eficiente** — torna o modelo mais compreensível

### Definições das Formas Normais:

| Forma Normal | Definição |
|:---:|---|
| **FNN** | Uma tabela que contém um ou mais grupos repetidos |
| **1FN** | Uma relação em que a intersecção entre uma linha e uma coluna contenha um e um só valor (valores atómicos) |
| **2FN** | Uma relação que está na 1FN e todos os atributos não pertencentes à chave primária são totalmente dependentes de qualquer chave candidata (sem dependências parciais) |
| **3FN** | Uma relação que está na 1FN e na 2FN e na qual nenhum atributo não pertencente à chave primária depende transitivamente de qualquer chave candidata (sem dependências transitivas) |
| **BCNF** | Uma relação na 3FN em que, para todas as dependências funcionais, o determinante é superchave |

### Impacto no desempenho:
**Vantagens:** Menos dados repetidos → menor ocupação de espaço. Evita atualizações desnecessárias → menos operações de escrita.

**Desvantagens:** Os dados ficam distribuídos por várias tabelas. Consultas complexas exigem mais junções (JOIN). O número de acessos ao disco pode aumentar, afetando o tempo de resposta.

### Desnormalização:
É o processo intencional de reverter parcialmente a normalização, introduzindo alguma redundância nos dados, com o objetivo de melhorar o desempenho. Exemplo: uma tabela de blog onde cada publicação é escrita uma vez mas lida constantemente beneficia de desnormalização.

**Quando usar desnormalização:**
- Muitas junções tornam consultas lentas
- Consultas frequentes sobre os mesmos dados agregados
- BDs orientadas à leitura (data warehouses)
- Sistemas com poucas atualizações e muitas leituras

---

## ⭐⭐⭐ PERGUNTA 3 — Anomalias de Atualização
**Saiu em: 6+ exames incluindo EN2021, Especial 08/09**

### Pergunta:
Descreva os tipos de anomalias de atualização (dê exemplos) que podem ocorrer numa relação que contém dados redundantes.

### Resposta:
- **INSERÇÃO**: Não é possível inserir certos dados porque falta outra informação redundante. Exemplo: inserir um novo estudante que não está inscrito em nenhuma disciplina numa tabela que junta Estudantes e Disciplinas.
- **REMOÇÃO (ELIMINAÇÃO)**: Ao apagar um registo, perdemos informação importante que estava repetida. Exemplo: ao apagar o único registo do aluno João na disciplina de Matemática, também apagamos todos os dados sobre a disciplina "Matemática".
- **MODIFICAÇÃO (ATUALIZAÇÃO)**: A mesma informação aparece em vários locais e uma atualização não é feita em todos, gerando inconsistência. Exemplo: se o nome de um professor estiver repetido em várias linhas e for atualizado numa só, ficamos com versões diferentes.

> A melhor forma de evitar estas anomalias é através da **normalização** do esquema da base de dados.

---

## ⭐⭐⭐ PERGUNTA 4 — Triggers: Definição, Vantagens e Desvantagens
**Saiu em: EN2021, 2024/2025**

### Pergunta:
O que são Triggers de bases de dados e para que servem? Quais as vantagens e desvantagens da utilização de triggers?

### Resposta:
Um **TRIGGER** (gatilho) é um objeto do SGBD que contém um conjunto de instruções SQL e que é executado automaticamente quando ocorre um determinado evento numa tabela (INSERT, UPDATE ou DELETE).

**Para que servem:**
- Executar ações automáticas em operações INSERT, UPDATE ou DELETE
- Aplicar regras de negócio
- Garantir a integridade dos dados
- Gerar logs ou auditorias
- Atualizar outras tabelas de forma automática

**Vantagens:**
- Automatização de tarefas (eliminação de código redundante)
- Reforço da integridade dos dados
- Centralização da lógica (código dentro da BD)
- Melhoria na segurança e controlo
- Boa junção com a arquitetura cliente-servidor

**Desvantagens:**
- Overhead do processador / redução de performance
- Execução implícita — efeitos indesejados sem que o utilizador perceba
- Possível efeito cascata
- Falta de possibilidade de agendar os Triggers
- Ordem de execução incerta (com múltiplas triggers para o mesmo evento)
- Diminuição da portabilidade (cada SGBD tem forma diferente de criar Triggers)
- Dificuldade de manutenção

### Diferença entre Triggers Before, After, Instead Of:

| Tipo | Quando é executada | Aplicação típica |
|------|-------------------|-----------------|
| **BEFORE** | Antes da operação | Validação ou alteração de dados |
| **AFTER** | Depois da operação | Ações complementares ou registos de auditoria |
| **INSTEAD OF** | Em vez da operação | Modificação de views ou controlo total da ação |

---

## ⭐⭐⭐ PERGUNTA 5 — Vistas (Views)
**Saiu em: EN2021, 2024/2025, Recurso 23/24**

### Pergunta:
O que é uma vista? Quais as diferenças entre uma vista e uma relação base?

### Resposta:
**VISTAS** são tabelas virtuais criadas a partir de uma ou mais consultas SELECT sobre tabelas reais. Não armazenam dados fisicamente — apenas guardam a definição da consulta.

Uma **RELAÇÃO BASE**, ao contrário de uma VISTA, existe fisicamente na BD e podem ser usadas na criação da vista.

| Característica | Vista (View) | Relação Base (Tabela Real) |
|---|---|---|
| Armazena dados | Não — só a consulta | Sim — armazena fisicamente |
| Definição | Criada com SELECT | Criada com CREATE TABLE |
| Modificável | Nem sempre | Sim — totalmente editável |

### Importância:
- Oferece uma forma flexível de segurança, permitindo esconder partes da BD
- Permite aos utilizadores aceder à informação de forma personalizada
- Permite simplificar operações complexas nas relações base
- Reutilização de lógica

### Vantagens:
- Segurança melhorada, Complexidade reduzida, Personalização

### Desvantagens:
- Restrições nas atualizações (uma vista poderá não ser atualizada)
- Restrições na estrutura (precisa criar nova vista para alterações)
- Problema de performance com junção de várias tabelas

### Restrições para vista atualizável:
O SGBD deve ser capaz de rastrear cada linha e coluna até à tabela de origem. Na VISTA:
- Não existe GROUP BY ou HAVING
- O FROM apenas refere uma tabela
- Não é especificado DISTINCT
- Não há funções de agregação nem subqueries
- Não há expressões calculadas ou colunas derivadas

### Mecanismo de Resolução de Vistas:
1. Os nomes das colunas da vista no SELECT são traduzidos para os nomes da definição da vista
2. Os nomes das vistas no FROM são substituídos pelos da definição da vista
3. O WHERE da query é combinado com o WHERE da definição da vista usando AND
4. GROUP BY e HAVING são copiados da definição da vista
5. ORDER BY é copiado e traduzido
6. A query final é executada

### Materialização de Vistas:
Consiste no armazenamento numa tabela temporária na BD, fazendo com que o acesso seja muito mais rápido. Os dados são pré-calculados e armazenados, reduzindo a carga sobre as tabelas base. **Desvantagem:** armazena dados duplicados, pode ficar desatualizada se não for refrescada.

---

## ⭐⭐⭐ PERGUNTA 6 — Sistemas de BD vs Ficheiros + Vantagens/Desvantagens SGBD
**Saiu em: 7+ exames incluindo Normal 08/09, Especial 08/09, Especial 07/08**

### Pergunta:
Descreva as principais características de um Sistema BD e faça a comparação com os Sistemas Baseados em Ficheiros. Enuncie e explique sucintamente as principais vantagens e desvantagens de um SGBD.

### Resposta:
**Características de um Sistema de BD:**
1. Independência de dados
2. Redução da redundância (dados centralizados)
3. Integridade dos dados
4. Segurança e controlo de acessos
5. Acesso simultâneo por vários utilizadores
6. Recuperação em caso de falha
7. Linguagens de consulta (como SQL)

**Comparação com Sistemas de Ficheiros:**
- Nos ficheiros, dados geridos por cada aplicação separadamente → duplicação
- Falta controlo de acessos e segurança centralizada
- Difícil partilhar dados entre programas e utilizadores
- Não existe independência de dados
- Recuperação de dados em caso de falha limitada ou inexistente

### Quando preferir Ficheiros:
Quando a quantidade de informação é baixa e tem o propósito de servir apenas um departamento, ou quando:
- Aplicações pequenas/pessoais com volume de dados reduzido
- Não exigem acesso simultâneo
- Não precisam de integridade/segurança rigorosa
- Recursos computacionais limitados

### Vantagens do SGBD:
- Controlo sobre redundância, Consistência, Partilha de dados
- Mais segurança, produtividade, concorrência
- Melhoria na manutenção (independência de dados)
- Integridade melhorada, Uso de standards
- Serviços de cópias de segurança e recuperação melhoradas
- Economia de escala

### Desvantagens do SGBD:
- Complexidade, Tamanho
- Custo do SGBD e hardware acrescido
- Custo de conversão, Performance
- Maior impacto em caso de falha

---

## ⭐⭐⭐ PERGUNTA 7 — Arquitetura ANSI/SPARC (3 Níveis)
**Saiu em: Recurso 23/24, Recurso 08/09 + coberto no "Resumos & Perguntas"**

### Pergunta:
A arquitetura ANSI/SPARC identifica três níveis nos SGBD. Descreva pormenorizadamente o nível intermédio, identificando o seu nome, e o que se pretende que este nível represente.

### Resposta:
A arquitetura ANSI/SPARC define três níveis: **interno**, **conceptual** e **externo**.

#### Objetivos da arquitetura:
1. **Separar os diferentes níveis de abstração dos dados**
2. **Garantir a independência dos dados** (física e lógica)
3. **Facilitar a segurança e controlo de acessos**
4. **Melhorar a flexibilidade e manutenção**

#### Nível intermédio: Nível Conceptual
O nível conceptual é o nível intermédio e representa a **visão global e lógica da base de dados** para toda a organização, independentemente de como os dados são armazenados (nível interno) ou apresentados aos utilizadores (nível externo).

**O que representa:**
- **Estrutura lógica da BD**: todas as entidades, atributos e relações
- **Regras de integridade**: restrições e dependências (PK, FK)
- **Segurança e permissões globais**: regras de acesso gerais
- **Independência lógica dos dados**: permite modificar a estrutura sem afetar aplicações

---

## ⭐⭐ PERGUNTA 8 — Data Warehouses: Benefícios e Problemas
**Saiu em: 4+ exames incluindo 2024/2025, Especial 07/08**

### Pergunta:
Descreva os principais benefícios e problemas associados aos Data Warehouses.

### Resposta:

**Benefícios:**
- Grande potencial do retorno sobre o investimento
- Vantagem competitiva
- Incremento de produtividade dos decision-makers

**Problemas:**
- Subestimar os recursos necessários ao carregamento dos dados
- Problemas escondidos nos sistemas fonte
- Dados necessários não capturados
- Crescimento dos pedidos dos utilizadores finais
- Homogeneização dos Dados
- Precisa de grandes recursos
- Dados proprietários
- Manutenção Elevada
- Projetos de Longa Duração
- Complexidade da Integração

### Data Mart vs Data Warehouse:
Um **DATA MART** é um subconjunto de um **DATA WAREHOUSE** que suporta os requisitos de um determinado departamento ou função de negócio.

Um **DATA WAREHOUSE** é uma coleção de dados orientada a assuntos, integrada, variável no tempo e não-volátil em suporte ao processo de tomada de decisão da administração.

**Razões para criar um Data Mart:**
- Dar acesso aos dados que precisam de analisar mais frequentemente
- Providenciar dados que coincidam com a vista coletiva de um grupo
- Melhorar o tempo de resposta aos utilizadores finais
- Construção mais simples e custo inferior que um data warehouse

---

## ⭐⭐ PERGUNTA 9 — DML Procedimentais vs Não Procedimentais
**Saiu em: 2024/2025 + coberto no "Resumos & Perguntas"**

### Pergunta:
Apresente as diferenças entre DML Procedimentais e DML Não Procedimentais.

### Resposta:

| Característica | DML Procedimental | DML Não Procedimental |
|---|---|---|
| **Foco** | **Como** os dados são obtidos (passos detalhados) | **Quais** os dados que serão apresentados |
| **Exemplo** | COBOL, PL/SQL (percorrer registos passo a passo) | SQL (comando SELECT) |
| **Complexidade** | Mais complexo, maior controlo | Mais simples, próximo da linguagem natural |
| **Operação** | Com laços, condições, etc. | Basta declarar os dados pretendidos |

### Contexto — Sublinguagens de Dados:
São a forma de comunicação existente com a BD:
- **DDL (Linguagem de Definição de Dados)**: CREATE, ALTER, DROP — implementação da BD e relações
- **DML (Linguagem de Manipulação de Dados)**: SELECT, INSERT, UPDATE, DELETE — manipulação dos dados
- **DCL (Data Control Language)**: GRANT, REVOKE — controlo de acessos
- **TCL (Transaction Control Language)**: COMMIT, ROLLBACK — gestão de transações

### Diferenças DDL vs DML:
- **DDL** altera a **estrutura** da BD, normalmente não permite rollback
- **DML** altera o **conteúdo** (dados) da BD, permite rollback e commit

---

## ⭐⭐ PERGUNTA 10 — Tipos de Junção (Joins)
**Saiu em: EN2021, MiniTeste 08/09**

### Pergunta:
Descreva as diferenças entre as cinco operações de junção: Theta Join, Equijoin, Natural Join, Outer Join e Semijoin.

### Resposta:

| Tipo de Junção | Operador | Inclui dados sem correspondência? | Remove duplicados? |
|---|---|---|---|
| **Theta Join** | Qualquer operador (=, >, <, >=, <=, !=) | Não | Não |
| **Equijoin** | Igualdade (=) | Não | Não |
| **Natural Join** | Igualdade automática | Não | Sim |
| **Outer Join** | Igualdade (=) | Sim (depende do tipo) | Não |
| **Semijoin** | Igualdade (via subquery) | Não (só da 1.ª tabela) | – |

- **THETA JOIN**: tipo mais geral, usa qualquer operador de comparação
- **EQUIJOIN**: caso especial do Theta Join com apenas igualdade, não remove colunas duplicadas
- **NATURAL JOIN**: baseada na igualdade automática, usa atributos com o mesmo nome e remove colunas duplicadas
- **OUTER JOIN**: inclui registos sem correspondência (LEFT, RIGHT, FULL — com NULL onde não há match)
- **SEMIJOIN**: retorna apenas registos da primeira tabela com correspondência na segunda, sem combinar dados

### Exemplo OUTER JOIN:
Três tipos:
- **Left Outer Join**: mantém todos os registos da tabela da esquerda
- **Right Outer Join**: mantém os da direita
- **Full Outer Join**: mantém todos de ambas, com NULL onde não há correspondência

---

## ⭐⭐ PERGUNTA 11 — Atributos num Diagrama ER
**Saiu em: 2022/2023 + coberto no "Resumos & Perguntas"**

### Pergunta:
Descreva o que representam os atributos num diagrama ER e dê exemplos de atributos simples, compostos, multi-valor e derivados.

### Resposta:
Num diagrama ER os atributos representam a **propriedade de uma entidade ou de um tipo de relação**.

| Tipo | Característica | Exemplo | Representação ER |
|---|---|---|---|
| **SIMPLES** | Valor único e indivisível | Número de cartão de cidadão | Elipse simples |
| **COMPOSTO** | Pode ser dividido em subpartes | Endereço → rua, cidade, código postal | Elipses ligadas |
| **MULTI-VALOR** | Pode ter mais do que um valor | Grau académico (licenciado, mestre, doutorado) | Dois círculos concêntricos |
| **DERIVADO** | Calculado a partir de outro atributo | Idade (a partir da data de nascimento) | Linhas tracejadas |

---

## ⭐⭐ PERGUNTA 12 — Funções de Agregação e NULLs
**Saiu em: Recurso 23/24, Recurso 08/09**

### Pergunta:
Quais as restrições aplicadas ao uso de funções de agregação no comando SELECT? De que forma os valores nulos (NULL) afetam as funções de agregação?

### Resposta:

**Restrições:**
1. **Não podem ser usadas na cláusula WHERE** — esta atua antes da agregação. Para filtrar com base no resultado da agregação, usar **HAVING**
2. **Devem ser usadas com GROUP BY** quando se misturam com colunas não agregadas — as colunas não agregadas devem aparecer no GROUP BY

**Efeito dos valores NULL:**

| Função | Resultado com NULL | Exemplo (10, 20, NULL, 30) |
|--------|-------------------|---------------------------|
| `COUNT(*)` | Conta todas as linhas **incluindo** NULL | 4 |
| `COUNT(coluna)` | Conta apenas valores **não-NULL** | 3 |
| `SUM(coluna)` | **Ignora** NULLs | 60 |
| `AVG(coluna)` | **Ignora** NULLs | 20 |
| `MAX(coluna)` | **Ignora** NULLs | 30 |
| `MIN(coluna)` | **Ignora** NULLs | 10 |

---

## ⭐⭐ PERGUNTA 13 — Cursores SQL
**Saiu em: Recurso 23/24**

### Pergunta:
O que são cursores SQL? Qual o propósito da sua utilização?

### Resposta:
Um **CURSOR SQL** é um mecanismo que permite processar os resultados de uma consulta **linha a linha** (tuplo a tuplo), em vez de processar o conjunto completo de resultados de uma só vez.

**Propósito:**
- Navegar pelos resultados de uma consulta de forma sequencial
- Processar cada linha individualmente com lógica complexa
- Realizar operações que não são possíveis com instruções SQL simples baseadas em conjuntos

**Ciclo de vida de um cursor:**
1. **DECLARE** — definição do cursor e da consulta associada
2. **OPEN** — abertura do cursor e execução da consulta
3. **FETCH** — obtenção da linha atual e avanço para a próxima
4. **CLOSE** — fecho do cursor

> ⚠️ Os cursores devem ser usados com cuidado pois podem ter impacto negativo no desempenho comparativamente a operações baseadas em conjuntos.

---

## ⭐⭐ PERGUNTA 14 — Subquery vs Junção
**Saiu em: 2022/2023 + coberto no "Resumos & Perguntas"**

### Pergunta:
Qual a diferença entre uma subquery e uma junção? Em que situações não é possível usar uma subquery?

### Resposta:
- **SUBQUERY**: é uma instrução SELECT colocada dentro de outra instrução SQL (dentro de SELECT, FROM, WHERE ou HAVING). Devolve um valor ou conjunto de valores usado pela consulta principal.
- **JUNÇÃO**: combina dados de duas ou mais tabelas com base numa condição comum. Mostra colunas de várias tabelas ao mesmo tempo.

**Situações em que NÃO é possível usar subquery:**
- Quando a lógica requer múltiplas colunas de tabelas diferentes na mesma linha → junção é obrigatória
- Quando a subquery devolve mais de um valor onde só é esperado um valor escalar

**3 tipos de subqueries:**

| Tipo | Devolve | Usado em... |
|------|---------|------------|
| **Escalar** | 1 valor (1 linha, 1 coluna) | WHERE, SELECT |
| **De Linha/Tabela** | Múltiplas linhas/colunas | FROM |
| **De Conjunto** | 1 coluna, várias linhas | WHERE com IN, ANY, ALL, EXISTS |

---

## ⭐⭐ PERGUNTA 15 — Transações e Propriedades ACID
**Coberto no "Resumos & Perguntas"**

### Pergunta:
O que é uma transação? Dê exemplos.

### Resposta:
Uma **TRANSAÇÃO** é um conjunto de operações SQL que são tratadas como uma **unidade lógica de trabalho**. Ou todas as operações são executadas com sucesso, ou nenhuma é aplicada.

**Propriedades ACID:**
1. **Atomicidade**: Tudo ou nada — a transação é executada por completo ou revertida
2. **Consistência**: A BD passa de um estado consistente para outro
3. **Isolamento**: Transações simultâneas não interferem entre si
4. **Durabilidade**: Após confirmada, a transação mantém-se mesmo em caso de falha

**Exemplo — Transferência bancária:**
```sql
BEGIN TRANSACTION;
UPDATE Contas SET saldo = saldo - 100 WHERE conta = 'A';
UPDATE Contas SET saldo = saldo + 100 WHERE conta = 'B';
-- Se tudo correr bem:
COMMIT;
-- Se algo falhar:
ROLLBACK;
```

---

## ⭐⭐ PERGUNTA 16 — Procedimento vs Função
**Coberto no "Resumos & Perguntas"**

### Pergunta:
Qual a diferença entre procedimento e uma função?

### Resposta:

| Característica | Função | Procedimento |
|---|---|---|
| Devolve valor | Sim (obrigatoriamente) | Opcional |
| Uso em SELECT | Sim | Não |
| Altera dados | Não (em regra) | Sim (pode modificar dados) |
| Chamado com | Dentro de expressões SQL | CALL nome_procedimento(...) |
| Propósito | Cálculos, validações | Tarefas e operações de negócio |

---

## ⭐ PERGUNTA 17 — Independência de Dados
**Saiu em: 2022/2023, Recurso Adicional 08/09 + coberto no "Resumos & Perguntas"**

### Pergunta:
Descreva o conceito de independência de dados e a sua importância num ambiente de bases de dados.

### Resposta:
**INDEPENDÊNCIA DE DADOS** é a capacidade de modificar os níveis inferiores da BD (lógico ou físico) sem afetar os níveis superiores.

- **Independência física**: mudanças na forma como os dados são armazenados não afetam a estrutura lógica
- **Independência lógica**: alterações na estrutura lógica (adicionar tabela/campo) não afetam os programas

**Importância:** Permite manter e evoluir o sistema de forma mais flexível e económica, sem ter de reescrever aplicações sempre que há mudanças internas.

---

## ⭐ PERGUNTA 18 — Especialização e Generalização (Modelo ER)
**Coberto no "Resumos & Perguntas"**

### Pergunta:
Explique as diferenças entre o processo de especialização e de generalização no contexto da modelação ER.

### Resposta:

- **ESPECIALIZAÇÃO**: Dividir uma entidade genérica em subconjuntos mais específicos. Cria subentidades com atributos e relacionamentos próprios. Relação "é um tipo de".
  - Exemplo: Funcionário → Engenheiro, Administrativo, Motorista

- **GENERALIZAÇÃO**: Processo inverso — agrupar duas ou mais entidades específicas numa entidade mais geral. Identifica semelhanças entre entidades distintas.
  - Exemplo: Aluno e Professor → Pessoa (com atributos comuns nome e morada)

---

## 📚 BÓNUS — Perguntas Complementares de Alta Frequência

### Cláusulas do SELECT
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

### Arquitetura Cliente-Servidor (2 vs 3 níveis)
- **2 camadas**: apenas um servidor responsável pelo SGBD e um cliente que corre as aplicações.
- **3 camadas**: servidor de base de dados, servidor de aplicação e uma interface de utilizador que corre no cliente. Mais aplicável ao modelo web, uma vez que o servidor de aplicação poderá estar incluído num servidor web. Esta arquitetura dá suporte à acessibilidade das BD através do balanceamento de carga.

---

### Chave Candidata, Primária, Estrangeira
- **CHAVE CANDIDATA**: conjunto mínimo de atributos que identifica univocamente cada tuplo na relação. Pode haver várias.
- **CHAVE PRIMÁRIA**: chave candidata escolhida para a identificação dos tuplos. Valores não podem repetir-se nem ser NULL.
- **CHAVE ESTRANGEIRA**: atributo que faz referência à chave primária de outra tabela. Permite relacionar tuplos de relações diferentes e garante integridade referencial.

---

### 5 Operações Básicas de Álgebra Relacional
- **SELEÇÃO (σ)**: seleciona tuplos que satisfaçam à condição de seleção
- **PROJEÇÃO (π)**: projeta as colunas solicitadas
- **PRODUTO CARTESIANO (×)**: combina tuplos de duas relações
- **UNIÃO (∪)**: une duas tabelas
- **DIFERENÇA (−)**: dá uma tabela com todas as linhas de A que não estão em B

A partir destas:
- **JUNÇÃO** = Produto Cartesiano + Seleção: A ⋈ B ≡ σ(A × B)
- **INTERSECÇÃO** = A − (A − B)
- **DIVISÃO** = mostra todos os valores de um atributo de A que fazem referência a todos os valores de B: A ÷ B = π_X(A) − π_X((π_X(A) × B) − A)

---

### Funções que um SGBD deve satisfazer
- Armazenamento, Pesquisa e Atualização de Dados
- Dicionário de Dados
- Suporte a Transações
- Serviços de Controlo de Concorrência
- Serviços de Recuperação
- Serviços de Autenticação
- Suporte a Comunicação de Dados
- Serviços de Integridade
- Serviços que promovam a Independência de Dados
- Utilitários

---

### Abordagens para BD com Múltiplas Vistas de Utilizadores
- **CENTRALIZADA**: requisitos de cada vista agregados numa só coleção; criado um modelo global baseado neste conjunto.
- **INTEGRAÇÃO DE VISTAS**: requisitos de cada vista criam um modelo local separado; os modelos locais são depois fundidos para produzir o modelo global.
- **COMBINAÇÃO DAS DUAS ANTERIORES**

---

### Ciclo de Vida de uma Aplicação de BD
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
11. Manutenção Operacional e Evolução

---

### 5 Componentes do Ambiente de um SGBD
1. **Hardware**: dispositivos físicos (servidores, discos, redes)
2. **Software**: SGBD, sistema operativo e programas
3. **Dados**: dados armazenados e metadados
4. **Utilizadores**: DBA, programadores, utilizadores finais
5. **Procedimentos**: regras e instruções de uso e funcionamento

---

### System Catalog
O **System Catalog** (Catálogo do Sistema) é um conjunto de tabelas e metadados mantido pelo SGBD que descreve a própria base de dados. Sem ele, o SGBD não conseguiria interpretar nem gerir os dados.

---

### Propriedades das Relações no Modelo Relacional
1. Nome único
2. Tuplos distintos (não há linhas repetidas)
3. Sem ordem entre os tuplos
4. Sem ordem entre os atributos
5. Atributos atómicos (indivisíveis)
6. Cada atributo tem um nome único

---

### Termos do Modelo Relacional
- **Relação**: tabela que armazena dados
- **Atributo**: coluna da tabela
- **Domínio**: conjunto de valores válidos para um atributo
- **Tuplo**: linha da tabela (um registo completo)
- **Grau**: número de atributos (colunas) de uma relação
- **Cardinalidade**: número de tuplos (linhas) de uma relação

---

### Duas Regras de Integridade no Modelo Relacional
1. **Integridade da Entidade**: cada tabela deve ter PK cujos valores não podem ser nulos nem repetidos
2. **Integridade Referencial**: FK deve corresponder a um valor existente na PK da outra tabela (ou ser nula)

---

### Importância do WHERE em UPDATE e DELETE
A cláusula WHERE é **essencial** em UPDATE e DELETE pois define quais os registos afetados. Sem WHERE, a instrução afeta **todos os registos** da tabela, o que pode causar perda de dados ou alterações indesejadas.

---

### 3 Gerações de SGBD
1. **1ª Geração** — Modelos Hierárquico e em Rede (anos 60-70): estruturas rígidas, pouco flexíveis
2. **2ª Geração** — Modelo Relacional (anos 70-80 até hoje): tabelas, SQL, o mais usado atualmente
3. **3ª Geração** — Modelos Orientado a Objetos e Objeto-Relacional (anos 90+): dados complexos (imagens, vídeos)

---

> 💡 **Dica final:** O exercício de **normalização de uma fatura** sai em TODOS os exames teóricos (vale 3-4 val.). Pratica com os exemplos dos exames anteriores!

---

## 📊 Mapa de Frequência de Perguntas por Exame

| Pergunta | 04-06 | 07/08 | 08/09 | EN2021 | 22/23 | R23/24 | 24/25 |
|----------|:-----:|:-----:|:-----:|:------:|:-----:|:------:|:-----:|
| Integridade Referencial | — | ✅ | — | ✅ | ✅ | — | ✅ |
| Normalização | — | ✅ | ✅ | — | — | ✅ | ✅ |
| Anomalias Atualização | — | — | ✅ | ✅ | — | — | — |
| Triggers | — | — | — | ✅ | — | — | ✅ |
| Vistas | — | — | ✅ | ✅ | — | ✅ | ✅ |
| Sist. BD vs Ficheiros | — | ✅ | ✅ | — | — | — | — |
| ANSI/SPARC | — | — | ✅ | — | — | ✅ | — |
| Data Warehouses | — | ✅ | — | — | — | ✅ | ✅ |
| DML Proc/Não Proc | — | — | — | — | — | — | ✅ |
| Tipos de Junção | — | — | — | ✅ | — | — | — |
| Atributos ER | — | — | — | — | ✅ | — | — |
| Funções Agregação/NULL | — | — | ✅ | — | — | ✅ | — |
| Cursores SQL | — | — | — | — | — | ✅ | — |
| Subquery vs Junção | — | — | — | — | ✅ | — | — |
| Independência Dados | — | — | ✅ | — | ✅ | — | — |
| Normalização (fatura) | ✅ | ✅ | ✅ | ✅ | ✅ | ✅ | ✅ |
| Álgebra Relacional | ✅ | ✅ | ✅ | ✅ | ✅ | ✅ | ✅ |
| SQL LMD | ✅ | ✅ | ✅ | ✅ | ✅ | ✅ | ✅ |
