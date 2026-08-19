# 📚 Resolução do Exame Teórico de Bases de Dados (Época Normal)

**📅 Ano Letivo:** 2025/2026 | **📆 Data:** 16-06-2026  
**🎓 Curso:** Engenharia Informática / Segurança Informática em Redes de Computadores  
**🏫 Instituição:** P.PORTO - ESTG  
**📖 Unidade Curricular:** Bases de Dados  

---

## 1. 📖 Definição de Termos (2 val.)

> ❓ **Pergunta 1:** Defina os seguintes termos:
> a) Bases de Dados.  
> b) Sistema de Gestão de Bases de Dados identificando os seus componentes.  
> c) Metadados.

**✍️ Resposta:**
*   **a) Bases de Dados:** Uma base de dados é uma coleção integrada, logicamente coerente e estruturada de dados partilhados, concebida para satisfazer as necessidades de informação de uma ou mais organizações. Os seus dados são persistentes e armazenados fisicamente de forma a minimizar redundâncias e inconsistências.
*   **b) Sistema de Gestão de Bases de Dados (SGBD):** É o sistema de software que intermedeia o acesso entre as aplicações/utilizadores e os dados físicos na base de dados. O SGBD é constituído por componentes principais como:
    *   *Processador de Consultas / Query Processor* (Compilador DDL, Compilador DML e Otimizador de Consultas).
    *   *Gestor de Armazenamento / Storage Manager* (Gestor de Ficheiros, Gestor de Buffer, Gestor de Autorização e Integridade).
    *   *Gestor de Transações* (Garante o controlo de concorrência e recuperação em caso de falha).
    *   *Dicionário de Dados / Catálogo* (Onde estão guardados os metadados).
*   **c) Metadados:** São "dados sobre dados", ou seja, a definição e descrição de toda a estrutura da base de dados (nomes das tabelas, colunas, tipos de dados, restrições de integridade, índices, utilizadores e privilégios), armazenados de forma estruturada no dicionário de dados do SGBD.

---

## 2. ⚙️ LDD (DDL) vs LMD (DML) (2 val.)

> ❓ **Pergunta 2:** Quais as diferenças que existem entre LDD e LMD. Que operações espera encontrar em cada uma destas linguagens? (2 val.)

**✍️ Resposta:**
*   **LDD (Linguagem de Definição de Dados):** É utilizada para especificar o esquema ou estrutura da base de dados, permitindo a criação e modificação de tabelas, vistas, índices, etc. As suas instruções afetam diretamente o dicionário de dados (metadados).
    *   *Operações esperadas:* `CREATE`, `ALTER`, `DROP`, `TRUNCATE`.
*   **LMD (Linguagem de Manipulação de Dados):** É utilizada para ler, inserir, atualizar e apagar os dados reais contidos nas tabelas criadas pela LDD. Não altera a estrutura física ou lógica dos objetos, apenas manipula o seu conteúdo.
    *   *Operações esperadas:* `SELECT`, `INSERT`, `UPDATE`, `DELETE`.

---

## 3. 👁️ Vistas (Views) vs Relações Base (2 val.)

> ❓ **Pergunta 3:** O que é uma vista. Quais as diferenças entre uma vista e uma relação base? (2 val.)

**✍️ Resposta:**
Uma vista é uma relação virtual definida por uma consulta SQL (SELECT) que é gerada de forma dinâmica sempre que referenciada, enquanto uma relação base é uma tabela física cujos dados estão armazenados permanentemente em disco.
*   **Diferenças:**
    1.  *Armazenamento:* As tabelas base armazenam fisicamente os dados; as vistas armazenam apenas o texto da consulta SQL de definição no dicionário de dados.
    2.  *Espaço:* As tabelas base ocupam espaço físico substancial; as vistas ocupam espaço insignificante.
    3.  *DML Direto:* Tabelas base suportam livremente qualquer operação de inserção e atualização; vistas possuem restrições complexas de escrita (não permitem atualizações se contiverem agregação, `GROUP BY`, `DISTINCT` ou `JOINs` complexos).
    4.  *Atualização:* Alterações nas tabelas base refletem-se automaticamente nas vistas em tempo real.

---

## 4. 📈 Funções de Agregação e Valores Nulos (2 val.)

> ❓ **Pergunta 4:** Quais as restrições aplicadas ao uso de funções de agregação no comando SELECT? De que forma os valores nulos (NULL) afetam as funções de agregação? (2 val.)

**✍️ Resposta:**
*   **Restrições no SELECT:**
    1.  Não podem ser utilizadas diretamente na cláusula `WHERE`, dado que esta filtra registos individuais antes de os agrupar. Para filtrar agregações deve ser usada a cláusula `HAVING`.
    2.  Se a lista de projeção do `SELECT` contiver atributos não agregados misturados com funções de agregação, esses atributos não agregados têm obrigatoriamente de constar na cláusula `GROUP BY`.
*   **Impacto de valores nulos (NULL):**
    1.  As funções de agregação (como `SUM`, `AVG`, `MIN`, `MAX`, `COUNT(coluna)`) ignoram automaticamente os valores nulos. No caso da média (`AVG`), o denominador será apenas a contagem de elementos não nulos.
    2.  Exceção: A função `COUNT(*)` conta todas as linhas do conjunto de resultados, independentemente de haver valores nulos nas suas colunas.

---

## 5. 🔄 Mecanismo de Resolução de Vistas (2 val.)

> ❓ **Pergunta 5:** Descreva como funciona o mecanismo de resolução de vistas. (2 val.)

**✍️ Resposta:**
O mecanismo de resolução de vistas (*View Resolution* ou *Query Modification*) funciona substituindo a referência à vista na consulta do utilizador pela sua definição lógica subjacente guardada no catálogo do SGBD:
1.  O utilizador submete uma consulta SQL contendo uma vista no `FROM`.
2.  O analisador do SGBD pesquisa o dicionário de dados para obter a instrução `SELECT` que define essa vista.
3.  O SGBD realiza uma fusão da consulta do utilizador com a consulta da vista, reescrevendo-a numa única instrução SQL equivalente que acede diretamente às tabelas base originais.
4.  O otimizador calcula o plano de execução físico para a consulta reescrita e executa-a sobre as tabelas base físicas.

---

## 6. 🔍 Técnicas de Descoberta de Factos (2 val.)

> ❓ **Pergunta 6:** Descreva qual o propósito das técnicas de descoberta de factos. Enuncie cada uma das técnicas e explique sucintamente o que cada uma pretende atingir. (2 val.)

**✍️ Resposta:**
As técnicas de descoberta de factos (*Fact-Finding*) são métodos utilizados ao longo de todo o ciclo de vida do projeto de bases de dados (principalmente na análise de requisitos) para recolher sistematicamente informações e dados cruciais sobre a organização, os processos de negócio e as necessidades reais dos utilizadores.
*   **Técnicas e objetivos:**
    1.  **Exame de Documentação / Análise de Documentos:** Pretende compreender a dinâmica da organização, identificando as regras de negócio e a estrutura dos dados atuais através da análise de relatórios, faturas, formulários e manuais.
    2.  **Entrevistas:** Pretende recolher requisitos detalhados, preferências e opiniões de forma interativa junto dos utilizadores finais e gestores da organização.
    3.  **Observação do Funcionamento do Negócio:** Pretende validar os fluxos de trabalho reais e fluxos de dados no terreno, ajudando a identificar exceções ou processos ocultos não revelados nas entrevistas.
    4.  **Questionários:** Pretende recolher informações estatísticas e de larga escala de forma rápida e económica junto de um grande número de utilizadores dispersos.
    5.  **Pesquisa:** Pretende analisar soluções técnicas equivalentes no mercado, boas práticas ou especificações de sistemas semelhantes para guiar o desenho da base de dados.

---

## 7. 📋 Exercício de Normalização de Fatura (3 val.)

> ❓ **Pergunta 7:** Observe atentamente o documento que acompanha o enunciado e que representa uma fatura. Escreva a definição da estrutura – nomes e atributos - das tabelas necessárias para representar estes dados num sistema de gestão de bases de dados relacional que suporte a emissão das faturas da empresa. Deve garantir que todas as tabelas estão na forma normal mais adequada. Não esqueça de indicar quais os campos que são chave primária e quais os que são chave estrangeira. Enuncie as definições de cada Forma Normal à medida que faz a normalização e identifique as dependências funcionais verificadas. (3 val.)

**✍️ Resposta:**
Analisando a fatura/recibo do estabelecimento "**Momento Surpresa**", identificamos os seguintes atributos lógicos relevantes:
*   `NIF_Empresa` (A), `Nome_Empresa` (B), `Morada_Empresa` (C), `CodPostalLocalidade` (D)
*   `NumFatura` (E), `Data` (F), `Hora` (G), `ATCUD` (H), `Mesa` (I), `Empregado` (J), `MetodoPagamento` (K), `Total_Fatura` (L), `Incidencia_IVA` (M), `Valor_IVA` (N)
*   `NIF_Cliente` (O)
*   `CodArtigo` (P), `Descricao_Artigo` (Q), `Quantidade` (R), `PrecoUnitario` (S), `TaxaIVA` (T), `Subtotal_Linha` (U)

### 1️⃣ Forma Não Normalizada (UNF)
Agrupamos os itens repetitivos que representam cada linha de artigo dentro do talão de fatura:
`Fatura_UNF(NIF_Empresa, Nome_Empresa, Morada_Empresa, CodPostalLocalidade, NumFatura, Data, Hora, ATCUD, Mesa, Empregado, MetodoPagamento, Total_Fatura, Incidencia_IVA, Valor_IVA, NIF_Cliente, (CodArtigo, Descricao_Artigo, Quantidade, PrecoUnitario, TaxaIVA, Subtotal_Linha))`

### 2️⃣ 1ª Forma Normal (1FN)
*   *Definição:* Uma relação está na 1FN se todos os seus atributos contiverem apenas valores atómicos (não permitindo grupos repetitivos).
*   *Processo:* Expandimos o grupo repetitivo, tornando a chave primária composta por `(NumFatura, CodArtigo)`.
*   *Esquema:* `Fatura_1FN(NumFatura, CodArtigo, NIF_Empresa, Nome_Empresa, Morada_Empresa, CodPostalLocalidade, Data, Hora, ATCUD, Mesa, Empregado, MetodoPagamento, Total_Fatura, Incidencia_IVA, Valor_IVA, NIF_Cliente, Descricao_Artigo, Quantidade, PrecoUnitario, TaxaIVA, Subtotal_Linha)`
*   *Chave Primária:* `(NumFatura, CodArtigo)`
*   *Dependências Funcionais (DFs):*
    *   $NumFatura \rightarrow NIF\_Empresa, Nome\_Empresa, Morada\_Empresa, CodPostalLocalidade, Data, Hora, ATCUD, Mesa, Empregado, MetodoPagamento, Total\_Fatura, Incidencia\_IVA, Valor\_IVA, NIF\_Cliente$
    *   $CodArtigo \rightarrow Descricao\_Artigo, PrecoUnitario, TaxaIVA$
    *   $NumFatura, CodArtigo \rightarrow Quantidade, Subtotal\_Linha$
    *   $NIF\_Empresa \rightarrow Nome\_Empresa, Morada\_Empresa, CodPostalLocalidade$
    *   $TaxaIVA \rightarrow Incidencia\_IVA, Valor\_IVA$

### 3️⃣ 2ª Forma Normal (2FN)
*   *Definição:* Uma relação está na 2FN se estiver na 1FN e todos os atributos não chave dependerem totalmente da chave primária (ausência de dependências parciais).
*   *Processo:* Decompomos os atributos que dependem apenas de parte da chave composta.
*   *Esquema:*
    *   `Fatura_2FN(NumFatura, NIF_Empresa, Nome_Empresa, Morada_Empresa, CodPostalLocalidade, Data, Hora, ATCUD, Mesa, Empregado, MetodoPagamento, Total_Fatura, Incidencia_IVA, Valor_IVA, NIF_Cliente)` | PK: `NumFatura`
    *   `Artigo_2FN(CodArtigo, Descricao_Artigo, PrecoUnitario, TaxaIVA, Incidencia_IVA, Valor_IVA)` | PK: `CodArtigo`
    *   `LinhaFatura_2FN(NumFatura, CodArtigo, Quantidade, Subtotal_Linha)` | PK: `(NumFatura, CodArtigo)`

### 4️⃣ 3ª Forma Normal (3FN)
*   *Definição:* Uma relação está na 3FN se estiver na 2FN e não contiver dependências transitivas (nenhum atributo não chave pode depender de outro atributo não chave).
*   *Processo:* Isolamos os dados da empresa (que transitivamente dependem do `NIF_Empresa`), os dados do cliente (que dependem de `NIF_Cliente`) e as contas e incidências do IVA (que dependem da `TaxaIVA`).
*   *Esquema Final:*

**Empresa** (Armazena os dados do emitente da fatura)
*   `NIF_Empresa` (PK)
*   `Nome_Empresa`
*   `Morada_Empresa`
*   `CodPostalLocalidade`

**Cliente** (Identifica o cliente da transação)
*   `NIF_Cliente` (PK)

**Fatura** (Cabeçalho da fatura)
*   `NumFatura` (PK)
*   `Data`
*   `Hora`
*   `ATCUD`
*   `Mesa`
*   `Empregado`
*   `MetodoPagamento`
*   `Total_Fatura`
*   `NIF_Empresa` (FK $\rightarrow$ Empresa)
*   `NIF_Cliente` (FK $\rightarrow$ Cliente)

**Artigo** (Ficha técnica do produto)
*   `CodArtigo` (PK)
*   `Descricao_Artigo`
*   `PrecoUnitario`
*   `TaxaIVA` (FK $\rightarrow$ ImpostoIVA)

**LinhaFatura** (Corpo da fatura / quantidade vendida)
*   `NumFatura` (PK, FK $\rightarrow$ Fatura)
*   `CodArtigo` (PK, FK $\rightarrow$ Artigo)
*   `Quantidade`
*   `Subtotal_Linha`

**ImpostoIVA** (Tabela auxiliar de taxas de IVA)
*   `TaxaIVA` (PK)
*   `Incidencia_IVA`
*   `Valor_IVA`

---

## 8. 📐 Modelação Entidade-Relacionamento, SQL e Álgebra Relacional (3 val.)

> ❓ **Enunciado do Problema 8:** O diagrama E/R a seguir pretende demonstrar o relacionamento existente entre diversas entidades de uma base de dados de um clube de futebol de formação. Os Treinos estão associados a um Escalão, sendo que um Escalão poderá ter vários Treinos ao longo de uma época desportiva. Cada Treino contém uma lista de Jogadores que compareceram ao treino, onde está registado o estado da presença e a avaliação do seu desempenho. Naturalmente, um Jogador poderá participar em vários Treinos do seu escalão e inclusive treinar mais do que uma vez no mesmo treino desde que o Bloco de Treino (ex: 'Físico', 'Tático', 'Técnico') seja diferente.
> 
> Os dados a armazenar de cada entidade são:
> * **Escalao** – `IDEscalao`, `Nome`, `Categoria`, `EpocaDesportiva`, `TreinadorPrincipal`
> * **Treino** – `IDTreino`, `Data`, `Campo`, `Escalao`
> * **Jogador** – `IDJogador`, `Nome`, `DataNascimento`, `NIF`, `Cidade`, `Posicao`
> 
> Naturalmente e atendendo ao relacionamento entre `Treino` e `Jogador` será necessária uma tabela associativa que armazene os dados necessários para o cumprimento integral do funcionamento de negócio descrito anteriormente.

---

### 🏗️ a) Tabela Associativa para o Clube Formação (1 val.)

> ❓ **Pergunta 8a:** Identifique o nome, os atributos dessa tabela e a sua chave primária. (1 val.)

**✍️ Resposta:**
Para modelar a relação M:N, cria-se a tabela associativa **`PresencaTreino`** com os atributos `IDTreino`, `IDJogador`, `BlocoTreino`, `EstadoPresenca` e `Avaliacao`.
A chave primária (PK) deve ser composta por **`(IDTreino, IDJogador, BlocoTreino)`**, dado que um jogador pode participar no mesmo treino várias vezes, desde que o bloco de treino seja diferente.

```sql
CREATE TABLE PresencaTreino (
    IDTreino INT,
    IDJogador INT,
    BlocoTreino VARCHAR(50),      -- Ex: 'Físico', 'Tático', 'Técnico'
    EstadoPresenca VARCHAR(30) NOT NULL, -- Ex: 'Presente', 'Falta'
    Avaliacao INT,                -- Nota/Avaliação do desempenho
    PRIMARY KEY (IDTreino, IDJogador, BlocoTreino),
    FOREIGN KEY (IDTreino) REFERENCES Treino(IDTreino),
    FOREIGN KEY (IDJogador) REFERENCES Jogador(IDJogador),
    CONSTRAINT chk_avaliacao CHECK (Avaliacao BETWEEN 1 AND 10)
);
```

---

### 💻 b) SQL: Cidades com mais de 15 Jogadores com Treinos em 2025 (2 val.)

> ❓ **Pergunta 8b (SQL):** Identifique as Cidades que têm mais de 15 jogadores que realizaram/registaram presenças em Treinos no ano de 2025? (2 val.)

**✍️ Resposta:**
Efetuamos a junção (`INNER JOIN`) entre `Jogador`, `PresencaTreino` e `Treino`, aplicando o filtro de data no `WHERE` para o ano de 2025. Agrupamos por `Cidade` do jogador e filtramos no `HAVING` garantindo que o número total de jogadores distintos seja maior que 15.

```sql
SELECT j.Cidade, COUNT(DISTINCT j.IDJogador) AS TotalJogadores
FROM Jogador j
INNER JOIN PresencaTreino pt ON j.IDJogador = pt.IDJogador
INNER JOIN Treino t ON pt.IDTreino = t.IDTreino
WHERE t.Data >= '2025-01-01' AND t.Data <= '2025-12-31'
GROUP BY j.Cidade
HAVING COUNT(DISTINCT j.IDJogador) > 15;
```

---

### 🔮 c) Álgebra Relacional: Posições de Jogadores Sem Treino no 1º Trimestre de 2026 (2 val.)

> ❓ **Pergunta 8c (Álgebra Relacional):** Quais as posições de jogadores (ex: 'Guarda-redes', 'Defesa', 'Médio', 'Avançado') que não tiveram qualquer presença em treinos no primeiro trimestre de 2026? (2 val.)

**✍️ Resposta:**
Selecionamos os treinos do primeiro trimestre de 2026 em `Treino`, fazemos a junção natural com `PresencaTreino` e, de seguida, com `Jogador` para obter e projetar as posições que treinaram. Subtraímos esse conjunto do total de posições registadas na tabela `Jogador`:

$$TreinosT1 \leftarrow \sigma_{Data \ge '2026-01-01' \wedge Data \le '2026-03-31'}(Treino)$$
$$PresencasT1 \leftarrow \pi_{IDJogador}(PresencaTreino \bowtie TreinosT1)$$
$$PosicoesComTreino \leftarrow \pi_{Posicao}(Jogador \bowtie PresencasT1)$$
$$TodasPosicoes \leftarrow \pi_{Posicao}(Jogador)$$
$$Resultado \leftarrow TodasPosicoes - PosicoesComTreino$$
