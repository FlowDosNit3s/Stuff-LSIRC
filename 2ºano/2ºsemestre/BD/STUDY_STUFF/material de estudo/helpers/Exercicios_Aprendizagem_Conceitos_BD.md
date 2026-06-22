# 🎓 Exercícios de Aprendizagem de Conceitos — Bases de Dados 2025/2026

Este documento foi criado para servir de ponte entre o [Guia de Estudo](Guia_Estudo_Exame_BD.md) e os [Exercícios de Exames](Exercicios_Exames_BD.md). O seu objetivo é ajudar-te a **aprender e dominar os conceitos fundamentais** individualmente através de pequenos exercícios práticos de aplicação direta, explicações didáticas e soluções comentadas passo a passo.

---

## 📋 Índice Temático de Aprendizagem

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

---

## 1. Conceitos Fundamentais de BD

### 🎯 Foco de Aprendizagem:
*   Diferenciar **BD** (dados + descrição), **SGBD** (software que gere) e **Metadados** (catálogo que descreve).
*   Compreender as limitações dos **Sistemas de Ficheiros** frente aos **SGBD** (redundância, concorrência, integridade).
*   Distinguir **DDL** (estruturação) de **DML** (manipulação).
*   Distinguir LMD **Procedimentais** (especifica *como* obter, registo a registo) de LMD **Não-Procedimentais** (especifica *o que* obter, em conjunto).

---

#### ❓ Exercício 1.1: O Desafio do Consultório Médico
Um consultório médico quer registar as suas consultas. O programador propõe usar dois ficheiros de texto simples (`clientes.txt` e `consultas.txt`).
1.  Identifica duas anomalias ou problemas que podem ocorrer devido a acessos simultâneos (concorrência) ou falta de validação num sistema de ficheiros deste tipo.
2.  Como é que um SGBD resolveria estes problemas usando metadados e restrições?

<details>
<summary><b>💡 Ver Solução e Explicação</b></summary>

1.  **Problemas identificados:**
    *   **Inconsistência de Dados e Falha de Validação:** No ficheiro de texto, um utilizador pode escrever o nome do médico de forma diferente em várias linhas (ex: "Dr. Silva" e "Dr. M. Silva"), ou pode colocar texto num campo que devia ser uma data.
    *   **Perda de Atualizações (Concorrência):** Se duas secretárias tentarem marcar uma consulta à mesma hora para o mesmo médico ao mesmo tempo, ambas abrem o ficheiro `consultas.txt`. A que guardar por último vai sobrescrever as alterações da primeira, perdendo-se uma das consultas.
2.  **Resolução pelo SGBD:**
    *   O SGBD utiliza o seu **Catálogo (Metadados)** para impor que a coluna `Data` tem de ser do tipo `DATE` e a coluna `MedicoID` tem de referenciar uma chave válida (Integridade Referencial).
    *   O SGBD possui um **Mecanismo de Controlo de Concorrência** (usando locks/bloqueios ao nível da linha) que garante que transações concorrentes ocorrem de forma isolada (propriedade ACID).
</details>

---

#### ❓ Exercício 1.2: LMD Procedimental vs Declarativo em Ação
Dada a relação `Produto(codP, nome, preco, stock)`:
1.  Escreve uma instrução **declarativa (não-procedimental)** em SQL para obter o `nome` dos produtos com `preco` superior a 50€.
2.  Explica como um motor de SGBD executa esta instrução, contrastando com o que o programador teria de fazer se estivesse a usar uma linguagem procedimental pura (como C# ou Java a ler um ficheiro físico).

<details>
<summary><b>💡 Ver Solução e Explicação</b></summary>

1.  **Instrução SQL Declarativa:**
    ```sql
    SELECT nome FROM Produto WHERE preco > 50;
    ```
2.  **Diferença de Execução:**
    *   **Abordagem Declarativa (SQL):** Nós apenas dissemos *o que* queremos (os nomes dos produtos caros). O SGBD recebe a query, analisa-a, consulta os metadados e o **otimizador de consultas** decide o *como* (se usa um índice na coluna `preco`, ou se lê a tabela do início ao fim - *Table Scan*). O utilizador não controla os passos físicos.
    *   **Abordagem Procedimental:** O programador teria de escrever o algoritmo passo a passo (*como* obter):
        1. Abrir o ficheiro `Produto.dat`.
        2. Criar uma estrutura de repetição (`while` ou `for`) para percorrer linha a linha.
        3. Para cada linha, verificar se o valor do bytes correspondentes ao preço é maior que 50.
        4. Se sim, extrair os bytes do nome e adicioná-los a uma lista em memória.
        5. Fechar o ficheiro e retornar a lista.
</details>

---

## 2. Arquitetura ANSI/SPARC e Independência de Dados

### 🎯 Foco de Aprendizagem:
*   Dominar os 3 níveis: **Externo** (vistas das apps), **Conceptual** (esquema lógico global do DBA) e **Interno** (disco, caminhos de acesso).
*   Diferenciar **Independência Física** (mudar o disco/índices sem mexer no esquema lógico/queries) de **Independência Lógica** (mudar tabelas/esquema conceitual sem quebrar as apps antigas, usando Views).
*   Explicar porque é que a arquitetura de **3 níveis (3-tier)** é superior à de 2 níveis em ambientes Web (pooling de conexões, segurança centralizada, manutenção).

---

#### ❓ Exercício 2.1: Independência de Dados na Prática
O administrador da base de dados (DBA) de uma universidade precisa de efetuar duas alterações no sistema:
*   **Alteração A:** A tabela `Alunos` tornou-se gigante. O DBA decide mover o ficheiro físico de dados para um novo disco SSD mais rápido e criar um índice agrupado na coluna `NumeroAluno`.
*   **Alteração B:** Por razões de privacidade, a tabela única `Funcionarios(ID, Nome, Morada, Salario)` é dividida em duas: `DadosPessoais(ID, Nome, Morada)` e `DadosFinanceiros(ID, Salario)`.

1.  Classifica cada uma destas alterações como sendo do âmbito da **Independência Física** ou **Lógica**.
2.  Explica detalhadamente o que o DBA tem de fazer na **Alteração B** para garantir que as aplicações antigas (que faziam `SELECT * FROM Funcionarios`) continuem a funcionar sem dar erro e sem alterar uma única linha de código do programa do cliente.

<details>
<summary><b>💡 Ver Solução e Explicação</b></summary>

1.  **Classificação:**
    *   **Alteração A:** **Independência Física de Dados**. Ocorre ao nível interno (armazenamento, caminhos de acesso). Não altera a estrutura lógica das tabelas.
    *   **Alteração B:** **Independência Lógica de Dados**. Ocorre ao nível conceptual (divisão/reestruturação de esquemas lógicos de tabelas).
2.  **Resolução para a Alteração B:**
    Para garantir a independência lógica e não quebrar as aplicações antigas, o DBA deve criar uma **Vista (VIEW)** com o nome da tabela original (`Funcionarios`) que junte as duas novas tabelas lógicas:
    ```sql
    CREATE VIEW Funcionarios AS
    SELECT P.ID, P.Nome, P.Morada, F.Salario
    FROM DadosPessoais P
    INNER JOIN DadosFinanceiros F ON P.ID = F.ID;
    ```
    Desta forma, a aplicação cliente externa continua a chamar a "tabela" `Funcionarios` através da vista. O SGBD encarrega-se de reescrever a query por baixo da mesa (*Query Modification*). As aplicações não requerem qualquer reprogramação.
</details>

---

#### ❓ Exercício 2.2: O Colapso dos 2 Níveis (2-Tier)
Uma aplicação de homebanking foi desenhada em 2 níveis: a aplicação instalada no telemóvel do cliente liga-se diretamente através de IP ao servidor de base de dados SQL Server do banco.
1.  Identifica duas vulnerabilidades críticas de segurança neste cenário.
2.  Explica como a transição para uma arquitetura de 3 níveis (3-tier) resolve estas falhas.

<details>
<summary><b>💡 Ver Solução e Explicação</b></summary>

1.  **Vulnerabilidades críticas de 2 níveis:**
    *   **Exposição de Credenciais:** As credenciais de acesso à BD (utilizador e password) ou a própria ligação direta à BD ficam expostas no código do telemóvel do utilizador, facilitando engenharia reversa.
    *   **Acesso Direto à Rede da BD:** O porto do SGBD (ex: 1433) tem de estar aberto para a Internet pública, permitindo ataques de força bruta diretos ao motor de base de dados.
    *   **Falta de Escalabilidade:** Cada telemóvel aberto mantém uma ligação ativa persistente com o SGBD. Com 100.000 utilizadores ativos, o SGBD colapsa por falta de conexões disponíveis.
2.  **Resolução com 3 níveis (3-tier):**
    *   Introduz-se uma camada intermédia: o **Servidor de Aplicação** (Web API).
    *   O telemóvel do cliente (cliente leve/*thin client*) apenas comunica via HTTPS com o Servidor de Aplicação, sem saber que existe uma BD por trás.
    *   O Servidor de Aplicação centraliza a lógica de negócio, autentica o utilizador e comunica com a BD através de um porto seguro protegido por firewall (dentro da rede interna privada).
    *   O Servidor de Aplicação usa um **Pool de Conexões** (reutiliza um número pequeno e fixo de ligações à BD, ex: 100 conexões para servir milhares de pedidos concorrentes), resolvendo a escalabilidade.
</details>

---

## 3. Modelo Relacional

### 🎯 Foco de Aprendizagem:
*   Dominar terminologia: **Relação** (tabela), **Atributo** (coluna), **Tuplo** (linha), **Grau** (nº colunas), **Cardinalidade** (nº linhas).
*   Perceber que um tuplo é um conjunto de pares (atributo, valor) e que a ordem das linhas/colunas é irrelevante.
*   Conhecer as Restrições de Integridade: **Entidade** (PK $\neq$ NULL), **Referencial** (FK existente ou NULL) e **Gerais** (regras do negócio).
*   Distinguir **Chave Candidata** (superchave mínima), **Chave Primária** (escolhida) e **Superchave** (qualquer identificador único, mesmo com atributos extra).

---

#### ❓ Exercício 3.1: Identificação de Chaves e Integridade
Dada a relação de automóveis num stand:
`Carro(Matricula, NumeroQuadro, Marca, Modelo, Ano, Cor, NIFProprietario)`
Sabe-se que `Matricula` e `NumeroQuadro` são únicos para cada carro na base de dados. `NIFProprietario` aponta para a tabela `Proprietario(NIF)`.

1.  Identifica todas as **Chaves Candidatas** desta relação.
2.  Dá um exemplo de uma **Superchave** que não seja chave candidata.
3.  Se tentarmos inserir o registo: `(NULL, 'Q99912', 'Opel', 'Corsa', 2018, 'Cinza', 254112322)`
    *   Esta operação é permitida se `Matricula` for a Chave Primária? Justifica com base nas regras de integridade.
4.  Se tentarmos inserir o registo: `('AA-11-BB', 'Q88812', 'Renault', 'Clio', 2020, 'Preto', 999999999)`
    *   Sabendo que não existe nenhum proprietário com o NIF `999999999` na tabela `Proprietario`, que regra de integridade é violada? E em que circunstância esta inserção seria permitida sem violar regras?

<details>
<summary><b>💡 Ver Solução e Explicação</b></summary>

1.  **Chaves Candidatas:**
    *   `Matricula` (identifica unicamente um carro)
    *   `NumeroQuadro` (identifica unicamente um carro)
    *(Ambas são chaves candidatas porque são atributos mínimos que identificam unicamente cada tuplo).*
2.  **Superchave (não candidata):**
    *   `(Matricula, Cor)` ou `(NumeroQuadro, Marca, Modelo)`.
    *(Qualquer conjunto de atributos que inclua uma chave candidata é uma superchave, mas não é candidata porque não é minimal).*
3.  **Análise do registo com `Matricula = NULL`:**
    *   **Não é permitida**. Se `Matricula` for a Chave Primária (PK) escolhida, a regra de **Integridade da Entidade** proíbe terminantemente que qualquer atributo pertencente à chave primária assuma o valor `NULL`.
4.  **Análise do proprietário inexistente:**
    *   Violaria a regra de **Integridade Referencial** (a chave estrangeira `NIFProprietario` deve apontar para uma chave primária existente na tabela pai `Proprietario`).
    *   Esta inserção **seria permitida** se o campo `NIFProprietario` pudesse assumir o valor `NULL` (ou seja, se a coluna não estivesse declarada como `NOT NULL` na DDL da tabela), indicando que o carro não tem proprietário associado no momento.
</details>

---

## 4. Álgebra Relacional

### 🎯 Foco de Aprendizagem:
*   Saber escrever as 5 operações fundamentais: Seleção ($\sigma$), Projeção ($\pi$), Produto Cartesiano ($\times$), União ($\cup$), Diferença ($-$).
*   Dominar operações derivadas: Junção ($\bowtie$), Intersecção ($\cap$), Divisão ($\div$).
*   Dominar o **Padrão de Diferença** em Álgebra Relacional: "Quais os A que NUNCA fizeram B".
*   Aplicar otimizações: efetuar seleções e projeções o mais cedo possível (*pushdown*).

---

#### ❓ Exercício 4.1: O Desafio da Junção e da Diferença
Considera o seguinte esquema de base de dados de uma biblioteca:
*   `Leitor(numL, nome, cidade)`
*   `Livro(codLivro, titulo, autor)`
*   `Emprestimo(numL, codLivro, data)`

Escreve a expressão em Álgebra Relacional para resolver cada uma das seguintes questões:
1.  Listar o Nome dos leitores que moram no 'Porto' e que requisitaram o livro com `codLivro = 101` (usando junção natural).
2.  Listar o Nome de todos os leitores que **nunca** requisitaram nenhum livro.

<details>
<summary><b>💡 Ver Solução e Explicação</b></summary>

1.  **Query 1: Leitores do Porto com empréstimo do livro 101**
    *   *Passo 1 (Filtrar leitores locais):* $\sigma_{cidade = 'Porto'}(Leitor)$
    *   *Passo 2 (Filtrar empréstimo do livro):* $\sigma_{codLivro = 101}(Emprestimo)$
    *   *Passo 3 (Juntar e projetar o nome):*
    $$\pi_{nome} \left( \sigma_{cidade = 'Porto'}(Leitor) \bowtie \sigma_{codLivro = 101}(Emprestimo) \right)$$
    *(Nota: Esta versão já está otimizada porque as seleções foram empurradas para baixo antes do Join).*

2.  **Query 2: Leitores que NUNCA requisitaram livros (Padrão de Diferença)**
    *   *Atenção:* Em Álgebra Relacional, para fazer negações, usamos o operador Diferença ($-$). No entanto, as duas relações envolvidas no operador $-$ devem ser **compatíveis para união** (mesmos atributos na mesma ordem). Não podes fazer `Leitor - Emprestimo`.
    *   *Estratégia:*
        1. Obter todos os números de leitores existentes: $\pi_{numL}(Leitor)$
        2. Obter todos os números de leitores que já fizeram empréstimos: $\pi_{numL}(Emprestimo)$
        3. Subtrair os dois conjuntos para ter os "que nunca": $\pi_{numL}(Leitor) - \pi_{numL}(Emprestimo)$
        4. Fazer a junção com `Leitor` para resgatar o Nome associado a esses códigos:
    $$\pi_{nome} \left( (\pi_{numL}(Leitor) - \pi_{numL}(Emprestimo)) \bowtie Leitor \right)$$
</details>

---

#### ❓ Exercício 4.2: O Operador Divisão (÷) Decifrado
Tens as tabelas:
*   `Estudante(codE, nome)`
*   `Inscricao(codE, codD)`
*   `DisciplinasObrigatorias(codD)`

1.  Escreve a expressão que retorna os códigos dos estudantes (`codE`) que estão inscritos em **todas** as disciplinas obrigatórias.
2.  Explica intuitivamente o que a operação Divisão realiza neste cenário.

<details>
<summary><b>💡 Ver Solução e Explicação</b></summary>

1.  **Expressão em Álgebra Relacional:**
    $$\text{Resultado} \leftarrow \pi_{codE, codD}(Inscricao) \div \text{DisciplinasObrigatorias}$$
2.  **Explicação Intuitiva:**
    O operador divisão ($R \div S$) é o oposto do produto cartesiano. Ele procura correspondências universais.
    Neste exemplo:
    *   $R$ (à esquerda) é a relação de ligações `(Estudante, Disciplina)`.
    *   $S$ (à direita) é o conjunto de disciplinas alvo (as obrigatórias).
    *   O resultado da divisão conterá apenas os valores de `codE` que estão associados a **todos** os valores presentes na tabela $S$ dentro da tabela $R$. Se as disciplinas obrigatórias forem `{BD, Redes}`, o estudante só passa no filtro se tiver registos para `(codE, BD)` **e** `(codE, Redes)` na tabela de inscrições.
</details>

---

## 5. SQL – LMD (Linguagem de Manipulação de Dados)

### 🎯 Foco de Aprendizagem:
*   Compreender a ordem lógica de execução de uma consulta `SELECT`:
    `FROM` $\rightarrow$ `WHERE` $\rightarrow$ `GROUP BY` $\rightarrow$ `HAVING` $\rightarrow$ `SELECT` $\rightarrow$ `ORDER BY`.
*   Saber a diferença crucial entre `WHERE` (filtra linhas antes do agrupamento) e `HAVING` (filtra grupos após o agrupamento).
*   Dominar subconsultas e saber converter subconsultas para `JOINs`.
*   Perceber o comportamento de funções de agregação (`COUNT`, `AVG`, `SUM`) com valores `NULL` (são ignorados, exceto no `COUNT(*)`).

---

#### ❓ Exercício 5.1: Depuração de Agregação SQL
Um estudante escreveu a seguinte consulta para encontrar as categorias de produtos que têm um preço médio superior a 100€:
```sql
SELECT Categoria, AVG(Preco) AS Media
FROM Produtos
WHERE AVG(Preco) > 100
GROUP BY Categoria;
```
1.  Esta query está correta? Se não, explica detalhadamente o erro de execução lógica que ocorre.
2.  Corrige a query SQL.

<details>
<summary><b>💡 Ver Solução e Explicação</b></summary>

1.  **Análise da query e erro:**
    *   A query está **incorreta** e gerará um erro de compilação/execução no SGBD.
    *   **O Erro:** O estudante colocou a função de agregação `AVG(Preco)` dentro da cláusula `WHERE`. 
    *   **Explicação Lógica:** A cláusula `WHERE` serve para filtrar **linhas individuais** da tabela antes de ser feito qualquer agrupamento (`GROUP BY`). Como o SGBD ainda não agrupou os produtos por categoria na fase do `WHERE`, ele não consegue calcular a média aritmética para cada categoria nesse momento. Filtros baseados em agregações de grupos devem ser colocados na cláusula `HAVING`.
2.  **Query Corrigida:**
    ```sql
    SELECT Categoria, AVG(Preco) AS Media
    FROM Produtos
    GROUP BY Categoria
    HAVING AVG(Preco) > 100;
    ```
</details>

---

#### ❓ Exercício 5.2: O Enigma do COUNT e dos NULLs
Considera a tabela `Alunos` com 5 linhas:
| Numero | Nome | Especialidade | NotaExame |
|:---:|---|---|:---:|
| 1 | Ana | Engenharia | 15 |
| 2 | Rui | Engenharia | NULL |
| 3 | Maria | Medicina | 18 |
| 4 | João | Medicina | NULL |
| 5 | Pedro | NULL | 12 |

Qual será o resultado retornado por cada uma das seguintes queries?
1.  `SELECT COUNT(*) FROM Alunos;`
2.  `SELECT COUNT(Especialidade) FROM Alunos;`
3.  `SELECT COUNT(NotaExame) FROM Alunos;`
4.  `SELECT AVG(NotaExame) FROM Alunos;`

<details>
<summary><b>💡 Ver Solução e Explicação</b></summary>

1.  `SELECT COUNT(*) FROM Alunos;`
    *   **Resultado: 5**
    *   *Explicação:* O `COUNT(*)` conta todas as linhas devolvidas pela query, independentemente de terem ou não valores nulos em qualquer coluna.
2.  `SELECT COUNT(Especialidade) FROM Alunos;`
    *   **Resultado: 4**
    *   *Explicação:* O `COUNT(coluna)` ignora valores `NULL`. O aluno Pedro tem `Especialidade = NULL`, logo não é contabilizado.
3.  `SELECT COUNT(NotaExame) FROM Alunos;`
    *   **Resultado: 3**
    *   *Explicação:* Ignora as duas notas nulas (Rui e João), contando apenas os registos com nota (Ana, Maria, Pedro).
4.  `SELECT AVG(NotaExame) FROM Alunos;`
    *   **Resultado: 15**
    *   *Explicação:* A função `AVG` ignora os nulos. O cálculo efetuado é `(15 + 18 + 12) / 3 = 15`. Os nulos não contam nem para o numerador (soma) nem para o denominador (contagem de elementos).
</details>

---

## 6. SQL – LDD (Linguagem de Definição de Dados)

### 🎯 Foco de Aprendizagem:
*   Saber declarar restrições de integridade em tabelas: `PRIMARY KEY`, `FOREIGN KEY`, `UNIQUE`, `NOT NULL`, `DEFAULT`, `CHECK`.
*   Criar domínios (`CREATE DOMAIN`) para reutilização de tipos com regras de validação.
*   Dominar a sintaxe de modificação (`ALTER TABLE`) e remoção (`DROP TABLE CASCADE/RESTRICT`).
*   Conhecer o padrão avançado de restrição complexa com `CHECK (NOT EXISTS (SELECT ...))`.

---

#### ❓ Exercício 6.1: DDL Segura para Biblioteca
Escreve o código SQL DDL para criar duas tabelas (`Categoria` e `Livro`) de acordo com os seguintes requisitos:
1.  Cria um domínio chamado `D_CodigoLivro` que represente um inteiro positivo (deve ter um check para garantir que o valor é $> 0$).
2.  A tabela `Categoria` tem um código de categoria (inteiro, PK) e uma descrição (obrigatória, única).
3.  A tabela `Livro` possui:
    *   `codLivro`: usa o domínio `D_CodigoLivro` como chave primária.
    *   `titulo`: texto variável de até 100 caracteres, obrigatório.
    *   `codCategoria`: chave estrangeira a apontar para `Categoria`, não aceita nulos. Se a categoria for eliminada da base de dados, a operação deve ser impedida.
    *   `anoPublicacao`: inteiro. Deve existir um `CHECK` para garantir que o ano é maior ou igual a 1500.

<details>
<summary><b>💡 Ver Solução e Explicação</b></summary>

```sql
-- 1. Criação do Domínio
CREATE DOMAIN D_CodigoLivro AS INT
    CHECK (VALUE > 0);

-- 2. Tabela Categoria (tabela independente, criada primeiro)
CREATE TABLE Categoria (
    codCategoria   INT,
    descricao      VARCHAR(50) NOT NULL,
    PRIMARY KEY (codCategoria),
    CONSTRAINT UQ_Categoria_Descricao UNIQUE (descricao)
);

-- 3. Tabela Livro (tabela dependente)
CREATE TABLE Livro (
    codLivro       D_CodigoLivro,
    titulo         VARCHAR(100) NOT NULL,
    codCategoria   INT NOT NULL,
    anoPublicacao  INT,
    PRIMARY KEY (codLivro),
    FOREIGN KEY (codCategoria) REFERENCES Categoria(codCategoria)
        ON DELETE RESTRICT, -- Impede a eliminação da categoria pai caso existam livros nela
    CONSTRAINT CK_Livro_Ano CHECK (anoPublicacao >= 1500)
);
```
</details>

---

## 7. Integridade Referencial

### 🎯 Foco de Aprendizagem:
*   Dominar as 4 opções de propagação quando a PK do pai é alterada (`UPDATE`) ou removida (`DELETE`):
    *   `NO ACTION` / `RESTRICT`: Aborta a operação caso haja registos filhos.
    *   `CASCADE`: Propaga a alteração/eliminação em cascata nos filhos.
    *   `SET NULL`: Define a FK do filho para nulo (exige que a coluna admita nulos).
    *   `SET DEFAULT`: Define a FK do filho para o seu valor por defeito.

---

#### ❓ Exercício 7.1: O Efeito Dominó das Vendas
Considera as tabelas `Cliente(ID_Cliente, Nome)` e `Encomenda(ID_Enc, Data, ID_Cliente)`.
A FK de `Encomenda` tem as regras: `ON DELETE SET NULL` e `ON UPDATE CASCADE`.

Dado o estado inicial:
*   `Cliente`: `(1, 'Ana'), (2, 'Rui')`
*   `Encomenda`: `(101, '2026-06-22', 1), (102, '2026-06-22', 1), (103, '2026-06-22', 2)`

1.  Mostra o conteúdo das tabelas após a execução da instrução SQL:
    ```sql
    UPDATE Cliente SET ID_Cliente = 5 WHERE ID_Cliente = 1;
    ```
2.  A seguir à operação anterior, executa-se a instrução:
    ```sql
    DELETE FROM Cliente WHERE ID_Cliente = 2;
    ```
    Mostra o estado final de ambas as tabelas. O que aconteceria se a coluna `ID_Cliente` na tabela `Encomenda` estivesse declarada como `NOT NULL`?

<details>
<summary><b>💡 Ver Solução e Explicação</b></summary>

1.  **Após o `UPDATE` (Propagação `CASCADE`):**
    Como o `ON UPDATE` está definido como `CASCADE`, as alterações do código do cliente `1` propagam-se para as suas encomendas `101` e `102` (cujo `ID_Cliente` muda para `5`).
    *   `Cliente`: `(5, 'Ana'), (2, 'Rui')`
    *   `Encomenda`: `(101, '2026-06-22', 5), (102, '2026-06-22', 5), (103, '2026-06-22', 2)`

2.  **Após o `DELETE` (Propagação `SET NULL`):**
    Como o cliente `2` é apagado e a regra de `ON DELETE` é `SET NULL`, a encomenda `103` (que pertencia ao cliente `2`) tem o seu `ID_Cliente` alterado para `NULL`.
    *   `Cliente`: `(5, 'Ana')`
    *   `Encomenda`: `(101, '2026-06-22', 5), (102, '2026-06-22', 5), (103, '2026-06-22', NULL)`

    *Se a coluna `ID_Cliente` em `Encomenda` fosse `NOT NULL`:*
    A instrução `DELETE` daria **erro** e seria abortada pelo SGBD. A regra `SET NULL` não conseguiria escrever `NULL` numa coluna que obriga a ter valor (`NOT NULL`).
</details>

---

## 8. Vistas (Views)

### 🎯 Foco de Aprendizagem:
*   Distinguir Vistas de Tabelas base (Vistas guardam apenas o `SELECT` nos metadados, sem duplicar dados físicos).
*   Saber em que condições uma vista é atualizável (DML direta): apenas se apontar para uma tabela única simples, sem junções, agregações ou agrupamentos.
*   Dominar o mecanismo de **Modificação de Consultas (Query Modification)**.
*   Explicar as vantagens/desvantagens de **Vistas Materializadas** (dados pré-calculados em disco $\rightarrow$ leituras rápidas, escritas lentas).

---

#### ❓ Exercício 8.1: O Mecanismo Secreto do SGBD
Dada a tabela `Funcionario(codF, nome, cargo, salario)` e a seguinte vista criada pelo DBA:
```sql
CREATE VIEW V_Mecanicos AS
SELECT codF, nome, salario
FROM Funcionario
WHERE cargo = 'Mecânico';
```
Um utilizador executa a seguinte query sobre a vista:
```sql
SELECT nome 
FROM V_Mecanicos 
WHERE salario > 1500;
```
1.  Como é que o motor do SGBD transforma esta query utilizando o mecanismo de **Query Modification**? Escreve a query final gerada internamente pelo SGBD.
2.  Se o utilizador tentar fazer um `UPDATE V_Mecanicos SET salario = salario * 1.1 WHERE codF = 5;`, esta operação será executada com sucesso? Justifica.

<details>
<summary><b>💡 Ver Solução e Explicação</b></summary>

1.  **Query Modificada Internamente:**
    O SGBD funde a query do utilizador com a definição lógica da vista nos metadados:
    ```sql
    SELECT nome 
    FROM Funcionario 
    WHERE cargo = 'Mecânico' AND salario > 1500;
    ```
2.  **Análise do UPDATE:**
    *   **Sim, será executada com sucesso.**
    *   *Justificação:* A vista `V_Mecanicos` é uma **vista simples atualizável**. Ela referencia apenas uma única tabela base (`Funcionario`), não contém funções de agregação, nem agrupamentos (`GROUP BY`), nem palavras-chave que impeçam a atualização como `DISTINCT`. Por isso, o SGBD sabe perfeitamente redirecionar o `UPDATE` para a linha física da tabela `Funcionario`.
</details>

---

## 9. Triggers, Stored Procedures e Funções

### 🎯 Foco de Aprendizagem:
*   Dominar os momentos do trigger: `BEFORE` (validações/ajustes de dados), `AFTER` (auditoria, logs, reações após gravação), `INSTEAD OF` (intercetar escrita em vistas).
*   Diferenciar tabelas lógicas temporárias do SQL Server: `inserted` (novos valores de insert/update) e `deleted` (antigos valores de delete/update).
*   Comparar **Stored Procedures** (autónomas, alteram dados, aceitam transações, sem return obrigatório) com **Funções / UDF** (usadas dentro de selects, apenas leitura, return obrigatório, sem transações).
*   Descrever as 5 fases do ciclo de vida de um **Cursor**: `DECLARE` $\rightarrow$ `OPEN` $\rightarrow$ `FETCH` $\rightarrow$ `CLOSE` $\rightarrow$ `DEALLOCATE`.

---

#### ❓ Exercício 9.1: SP vs UDF — Qual escolher?
Precisas de implementar dois requisitos lógicos na base de dados de um ginásio:
*   **Requisito A:** Uma função matemática que recebe o peso e altura de um sócio e devolve o seu Índice de Massa Corporal (IMC) para ser exibido numa listagem de sócios.
*   **Requisito B:** Um processo que recebe o ID de dois sócios, transfere a inscrição de um deles para uma nova modalidade, atualiza o stock de vagas da modalidade e, caso o número de vagas fique negativo, faz o rollback de toda a operação.

Diz, justificando tecnicamente, qual a melhor escolha (`Stored Procedure` ou `User Defined Function`) para implementar cada requisito.

<details>
<summary><b>💡 Ver Solução e Explicação</b></summary>

*   **Requisito A: Deve usar uma Função (UDF) Escalar.**
    *   *Justificação:* O IMC é um cálculo derivado puramente matemático, que apenas lê dados de entrada e não efetua qualquer alteração física na base de dados. Além disso, as funções podem ser embutidas diretamente dentro de uma query `SELECT` (ex: `SELECT nome, ufn_CalcularIMC(peso, altura) FROM Socio`), o que facilita a exibição de listagens.
*   **Requisito B: Deve usar uma Stored Procedure (SP).**
    *   *Justificação:* Este requisito exige a execução de comandos DML de alteração de estado (`UPDATE`) em várias tabelas (tabela de sócios, tabela de vagas) e o controlo explícito de transações (`BEGIN TRAN`, `COMMIT`, `ROLLBACK`) em caso de exceções ou regras de negócio violadas (vagas negativas). As Funções (UDF) têm a restrição estrita de **não permitir alterações de dados** e **não permitir transações** no seu interior.
</details>

---

#### ❓ Exercício 9.2: Decifrar as Tabelas Virtuais do Trigger
Dada a tabela `Conta(codConta, saldo)` e o seguinte trigger ativo no SQL Server (T-SQL):
```sql
CREATE TRIGGER TRG_VerificarSaldo
ON Conta
AFTER UPDATE
AS
BEGIN
    IF EXISTS (
        SELECT 1 
        FROM inserted i
        INNER JOIN deleted d ON i.codConta = d.codConta
        WHERE i.saldo < 0 AND d.saldo >= 0
    )
    BEGIN
        PRINT 'Aviso: Conta entrou em saldo negativo!';
    END
END;
```
Explica detalhadamente o papel das tabelas `inserted` e `deleted` nesta query e que alteração exata no saldo das contas dispara a mensagem de aviso.

<details>
<summary><b>💡 Ver Solução e Explicação</b></summary>

*   **Tabela `inserted`:** É uma tabela temporária em memória gerada pelo SGBD que contém as linhas atualizadas com os **novos saldos** (após o UPDATE).
*   **Tabela `deleted`:** É uma tabela temporária em memória que contém as mesmas linhas mas com os **saldos antigos** (antes da execução do UPDATE).
*   **Condição de Disparo:**
    O trigger faz um `JOIN` entre as duas tabelas pelo `codConta` e avalia a condição `i.saldo < 0 AND d.saldo >= 0`. Isto significa que o aviso só será ativado se a conta tiver transitado de um saldo positivo ou nulo (`d.saldo >= 0`) para um saldo estritamente negativo (`i.saldo < 0`).
    Se a conta já estava negativa e o saldo apenas mudou de valor (ex: de -10€ para -20€), a condição não se verifica e o aviso não é impresso.
</details>

---

## 10. Normalização

### 🎯 Foco de Aprendizagem:
*   Perceber a essência das Formas Normais:
    *   **1FN:** Atributos atómicos (sem listas de valores). PK definida.
    *   **2FN:** Está na 1FN e não há **dependências parciais** (atributos não-chaves dependendo de apenas uma parte de uma PK composta).
    *   **3FN:** Está na 2FN e não há **dependências transitivas** (atributos não-chaves determinados por outros atributos também não-chaves).
    *   **FNBC:** Todas as dependências funcionais $X \rightarrow Y$ têm como determinante $X$ uma chave candidata.
*   Reconhecer as 3 anomalias de atualização: **Inserção** (não conseguir inserir X sem Y), **Remoção** (apagar Y apaga X por engano), **Modificação** (redundância exige atualizar muitas linhas, risco de inconsistência).

---

#### ❓ Exercício 10.1: O Caso das Inscrições na Escola
Uma escola armazena as inscrições dos alunos num formato plano (UNF):
`Inscricao(NumeroAluno, NomeAluno, CodigoDisciplina, NomeDisciplina, AnoLetivo, SalaExame)`
Sabe-se que:
*   Um aluno tem apenas um nome associado.
*   Uma disciplina tem apenas um nome associado.
*   Para um determinado ano letivo e disciplina, a sala de exame é fixa (ex: no ano 2026, o exame de 'BD' é sempre na 'Sala 101').

1.  Determina a Chave Primária da relação na 1FN.
2.  Identifica as dependências funcionais da relação.
3.  Normaliza o esquema até à 3FN, justificando cada passagem com base nas definições das formas normais.

<details>
<summary><b>💡 Ver Solução e Explicação</b></summary>

1.  **Chave Primária (1FN):**
    Para identificar unicamente cada linha de inscrição, necessitamos da combinação do aluno, da disciplina e do ano letivo correspondente.
    *   **PK = (NumeroAluno, CodigoDisciplina, AnoLetivo)**

2.  **Dependências Funcionais (DFs):**
    *   `NumeroAluno → NomeAluno` (o nome depende apenas do código do aluno)
    *   `CodigoDisciplina → NomeDisciplina` (o nome da disciplina depende apenas do código da disciplina)
    *   `(CodigoDisciplina, AnoLetivo) → SalaExame` (a sala de exame depende da disciplina e do ano letivo do exame)

3.  **Passos de Normalização:**

    *   **Passo 1: 1ª Forma Normal (1FN)**
        *   *Definição:* Todos os atributos são atómicos.
        *   *Relação:* `Inscricao_1FN(NumeroAluno, CodigoDisciplina, AnoLetivo, NomeAluno, NomeDisciplina, SalaExame)` com a PK definida.

    *   **Passo 2: 2ª Forma Normal (2FN)**
        *   *Definição:* Está na 1FN e não possui dependências parciais (atributos que dependem apenas de parte da PK composta).
        *   *Identificação de Dependências Parciais:*
            *   `NomeAluno` depende apenas de `NumeroAluno` (uma parte da PK).
            *   `NomeDisciplina` depende apenas de `CodigoDisciplina` (outra parte da PK).
            *   `SalaExame` depende de `(CodigoDisciplina, AnoLetivo)` (uma parte da PK de 3 atributos).
        *   *Decomposição (criar tabelas separadas):*
            *   `Aluno(NumeroAluno, NomeAluno)` — PK: `NumeroAluno`
            *   `Disciplina(CodigoDisciplina, NomeDisciplina)` — PK: `CodigoDisciplina`
            *   `Exame(CodigoDisciplina, AnoLetivo, SalaExame)` — PK: `(CodigoDisciplina, AnoLetivo)`
            *   `Inscricao(NumeroAluno, CodigoDisciplina, AnoLetivo)` — PK: `(NumeroAluno, CodigoDisciplina, AnoLetivo)`

    *   **Passo 3: 3ª Forma Normal (3FN)**
        *   *Definição:* Está na 2FN e não possui dependências transitivas (atributos não-chaves que determinam outros atributos não-chaves).
        *   *Verificação:*
            *   Em `Aluno`: a única DF é `NumeroAluno → NomeAluno` (não há transitivas).
            *   Em `Disciplina`: a única DF é `CodigoDisciplina → NomeDisciplina` (não há transitivas).
            *   Em `Exame`: a única DF é `(CodigoDisciplina, AnoLetivo) → SalaExame` (não há transitivas).
            *   Em `Inscricao`: não existem atributos não-chaves.
        *   *Resultado:* As tabelas já se encontram na 3FN.
</details>

---

## 11. Desenho e Modelação de BD (Diagramas E/R)

### 🎯 Foco de Aprendizagem:
*   Mapear atributos da Notação de Chen:
    *   **Simples:** Elipse comum.
    *   **Composto:** Elipses ligadas a outra elipse.
    *   **Multi-valor:** Elipse de traço duplo (geram tabelas independentes com PK composta no mapeamento).
    *   **Derivado:** Elipse tracejada (não geram colunas físicas nas tabelas base).
*   Saber mapear relações M:N (geram sempre uma tabela associativa contendo as PKs das entidades como FKs e uma PK composta).
*   Compreender herança/especialização:
    *   **Total/Parcial:** Se todas as instâncias da superclasse têm obrigatoriamente de pertencer a uma subclasse.
    *   **Disjunta/Sobreposta:** Se uma instância pode pertencer a apenas uma subclasse (disjunta) ou a várias em simultâneo (sobreposta).

---

#### ❓ Exercício 11.1: O Caso do Aluguer de Quartos
Considere o seguinte cenário: Um `Cliente` (NIF, Nome, Telefones) aluga `Quartos` (NumeroQuarto, PrecoBase). Um cliente pode alugar vários quartos ao longo do tempo, e um quarto pode ser alugado por vários clientes. Cada aluguer regista a `DataEntrada`, `DataSaida` e o `PrecoFinal` negociado.
Note que:
*   `Telefones` é um atributo multi-valor (o cliente pode registar vários números).
*   `PrecoFinal` é um atributo do próprio relacionamento.

1.  Apresente o mapeamento lógico (desenho de tabelas com PK/FK) para este relacionamento.
2.  Explique porque é que o atributo multi-valor `Telefones` não pode ficar diretamente na tabela `Cliente` e como deve ser mapeado.

<details>
<summary><b>💡 Ver Solução e Explicação</b></summary>

1.  **Mapeamento Lógico de Tabelas:**

    *   `Cliente(NIF, Nome)`
        *   PK: `NIF`
    *   `Quarto(NumeroQuarto, PrecoBase)`
        *   PK: `NumeroQuarto`
    *   `Aluguer(NIF, NumeroQuarto, DataEntrada, DataSaida, PrecoFinal)`
        *   PK: `(NIF, NumeroQuarto, DataEntrada)` *(inclui-se DataEntrada para permitir que o mesmo cliente alugue o mesmo quarto em datas diferentes).*
        *   FK: `NIF` $\rightarrow$ `Cliente`
        *   FK: `NumeroQuarto` $\rightarrow$ `Quarto`

2.  **Mapeamento de Atributos Multi-valor:**
    *   No Modelo Relacional, todos os atributos devem ser atómicos (1FN). Se colocássemos a coluna `Telefone` na tabela `Cliente`, violaríamos a 1FN caso tivéssemos uma lista (ex: "912345678; 961112222") ou teríamos de criar colunas artificiais vazias (`Telefone1`, `Telefone2`).
    *   **Solução:** O atributo multi-valor é extraído para uma nova tabela própria, cuja chave primária é a junção da chave estrangeira com o próprio valor:
        *   `ClienteTelefone(NIF, Telefone)`
            *   PK: `(NIF, Telefone)`
            *   FK: `NIF` $\rightarrow$ `Cliente`
</details>

---

## 12. Data Warehousing

### 🎯 Foco de Aprendizagem:
*   Conhecer a definição de DW (orientado a assuntos, integrado, histórico, não-volátil).
*   Distinguir **Star Schema** (tabelas de dimensão desnormalizadas ligadas a uma tabela de factos central $\rightarrow$ consultas simples e rápidas) de **Snowflake Schema** (dimensões normalizadas gerando mais tabelas $\rightarrow$ poupa espaço, mas requer mais JOINs).
*   Saber as 3 fases do **ETL**: *Extraction* (obtenção), *Transformation* (limpeza, padronização), *Loading* (carga).
*   Associar o ETL ao fluxo **Inflow** de dados.

---

#### ❓ Exercício 12.1: Star vs Snowflake na Tomada de Decisão
Um analista de Data Warehouse está a desenhar a dimensão `Geografia` para analisar as vendas de uma cadeia de supermercados. A dimensão contém: `ID_Loja`, `NomeLoja`, `Cidade`, `Distrito` e `Pais`.

1.  Como seria representada esta dimensão num **Star Schema**? Apresente a estrutura da tabela.
2.  Como seria representada a mesma dimensão num **Snowflake Schema**? Apresente as tabelas resultantes.
3.  Qual dos dois esquemas apresenta melhor desempenho para a extração de relatórios analíticos (`OLAP`) e porquê?

<details>
<summary><b>💡 Ver Solução e Explicação</b></summary>

1.  **Dimensão no Star Schema (Desnormalizado):**
    Uma única tabela contendo toda a hierarquia geográfica repetida:
    *   `Dim_Geografia(ID_Loja, NomeLoja, Cidade, Distrito, Pais)` — PK: `ID_Loja`

2.  **Dimensão no Snowflake Schema (Normalizado):**
    As relações geográficas são decompostas para remover redundâncias de escrita:
    *   `Dim_Loja(ID_Loja, NomeLoja, ID_Cidade)` — PK: `ID_Loja`, FK: `ID_Cidade` $\rightarrow$ `Dim_Cidade`
    *   `Dim_Cidade(ID_Cidade, NomeCidade, ID_Distrito)` — PK: `ID_Cidade`, FK: `ID_Distrito` $\rightarrow$ `Dim_Distrito`
    *   `Dim_Distrito(ID_Distrito, NomeDistrito, ID_Pais)` — PK: `ID_Distrito`, FK: `ID_Pais` $\rightarrow$ `Dim_Pais`
    *   `Dim_Pais(ID_Pais, NomePais)` — PK: `ID_Pais`

3.  **Comparação de Desempenho:**
    *   O **Star Schema** oferece muito melhor desempenho para consultas analíticas (`OLAP`).
    *   *Porquê:* O Star Schema reduz o número de operações de junção (`JOIN`) necessárias. Para cruzar as vendas de uma loja com o País, basta fazer 1 JOIN entre a tabela de factos `Facto_Vendas` e a tabela `Dim_Geografia`. No Snowflake, seriam necessários 4 JOINs seguidos, o que consome consideravelmente mais recursos de CPU e acessos a disco.
</details>

---

## 13. BD Distribuídas e Paralelas

### 🎯 Foco de Aprendizagem:
*   Comparar estratégias de alocação de dados:
    *   **Centralizada:** Uma só máquina.
    *   **Particionada (Sem replicação):** Dividida sem sobreposições.
    *   **Replicação Completa:** Cópia idêntica em todos os nós (leituras rápidas, escritas lentas).
*   Saber a diferença entre fragmentação **Horizontal** (dividir linhas, $\sigma$) e **Vertical** (dividir colunas, $\pi$ mantendo a PK).
*   Distinguir arquiteturas paralelas: **Shared Memory** (RAM e disco partilhados), **Shared Disk** (RAM privada, discos partilhados), **Shared Nothing** (tudo privado, escalabilidade máxima).
*   Explicar detalhadamente o funcionamento do protocolo **Two-Phase Commit (2PC)**:
    *   *Fase 1 (Prepare):* Coordenador envia prepare, participantes escrevem no log local e votam `YES`/`NO`.
    *   *Fase 2 (Commit):* Coordenador decide commit global se todos votaram `YES`, caso contrário envia abort global.

---

#### ❓ Exercício 13.1: Fragmentação e Distribuição
Considera a tabela de clientes de um banco nacional:
`Cliente(codC, nome, cidade, saldo, NIF)`

O banco tem duas agências principais: **Lisboa** e **Porto**.
1.  Como farias a fragmentação **horizontal** desta tabela para que cada agência guarde localmente apenas os clientes da respetiva cidade? Escreve as expressões matemáticas em Álgebra Relacional.
2.  Como farias a fragmentação **vertical** para separar as informações públicas do cliente (`codC`, `nome`) das informações privadas financeiras e fiscais (`codC`, `saldo`, `NIF`)? Escreve as expressões em Álgebra Relacional.
3.  Qual é o detalhe essencial que deve ser mantido em ambos os fragmentos verticais para que a tabela original possa ser reconstruída sem perda de informação? Qual é a operação de Álgebra Relacional usada para a reconstrução?

<details>
<summary><b>💡 Ver Solução e Explicação</b></summary>

1.  **Fragmentação Horizontal:**
    Dividimos as linhas através do operador de Seleção ($\sigma$):
    *   $\text{Fragmento\_Lisboa} \leftarrow \sigma_{cidade = 'Lisboa'}(Cliente)$
    *   $\text{Fragmento\_Porto} \leftarrow \sigma_{cidade = 'Porto'}(Cliente)$

2.  **Fragmentação Vertical:**
    Dividimos as colunas através do operador de Projeção ($\pi$):
    *   $\text{Fragmento\_Identificacao} \leftarrow \pi_{codC, nome}(Cliente)$
    *   $\text{Fragmento\_Financeiro} \leftarrow \pi_{codC, saldo, NIF}(Cliente)$

3.  **Reconstrução dos Fragmentos Verticais:**
    *   **Detalhe essencial:** A chave primária (`codC`) tem de ser mantida em ambos os fragmentos lógicos. Sem ela, torna-se impossível saber qual o saldo que pertence a qual nome de cliente.
    *   **Operação de Reconstrução:** A tabela original é reconstruída aplicando uma **Junção Natural (Natural Join)** entre os dois fragmentos verticais:
    $$\text{Cliente} \leftarrow \text{Fragmento\_Identificacao} \bowtie \text{Fragmento\_Financeiro}$$
</details>

---

#### ❓ Exercício 13.2: O Jogo do Two-Phase Commit (2PC)
Numa base de dados distribuída, uma transação financeira de transferência bancária envolve debitar 100€ na base de dados de Lisboa e creditar 100€ na base de dados do Porto. O Coordenador inicia o protocolo 2PC.
1.  Descreve um cenário que leve a transação a efetuar um **Abort Global**. Identifica os votos de cada participante e a ação do coordenador.
2.  Porque é que o protocolo 2PC é considerado seguro para garantir a propriedade de **Atomicidade** das transações? O que aconteceria se não existisse a Fase 1 (Prepare)?

<details>
<summary><b>💡 Ver Solução e Explicação</b></summary>

1.  **Cenário de Abort Global:**
    *   **Fase 1 (Prepare):** O Coordenador envia a mensagem de `PREPARE` a ambos os nós (Lisboa e Porto).
    *   **Votação:**
        *   O nó de **Lisboa** executa a verificação local, confirma que a conta tem saldo suficiente, grava temporariamente a operação no seu log e responde **`VOTE_COMMIT` (YES)**.
        *   O nó do **Porto** tenta preparar o crédito, mas deteta que a conta destino foi encerrada pelo cliente ou que o disco físico do servidor ficou sem espaço. O nó do Porto responde **`VOTE_ABORT` (NO)**.
    *   **Fase 2 (Decision):** O Coordenador recebe as votações. Como pelo menos um participante votou `NO` (Porto), o Coordenador toma a decisão de **Abort Global** e envia uma mensagem de `ABORT` a todos os nós.
    *   **Conclusão:** O nó de Lisboa reverte as suas alterações temporárias no saldo usando o log local (`ROLLBACK`). A transação é anulada em toda a rede.

2.  **Segurança e Importância da Fase 1:**
    *   O 2PC garante a **Atomicidade (Tudo ou Nada)** porque introduz uma fase de votação onde todos os participantes garantem previamente que a operação é realizável e que já têm os dados gravados de forma segura em armazenamento não-volátil (logs) antes de qualquer gravação definitiva ocorrer.
    *   *Se a Fase 1 não existisse:* O Coordenador enviaria diretamente ordens de `COMMIT`. Se o nó de Lisboa efetuasse o commit e depois o nó do Porto falhasse (por falta de rede ou disco cheio), a base de dados entraria em colapso de integridade — os 100€ teriam desaparecido da conta de Lisboa mas nunca teriam sido depositados no Porto, quebrando a consistência do banco.
</details>
