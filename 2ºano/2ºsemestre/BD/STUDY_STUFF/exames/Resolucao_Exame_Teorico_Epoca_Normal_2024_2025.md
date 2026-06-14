# 📚 Resolução do Exame Teórico de Bases de Dados (Época Normal)

**📅 Ano Letivo:** 2024/2025 | **📆 Data:** 20-06-2025  
**🎓 Curso:** Engenharia Informática / Segurança Informática em Redes de Computadores  
**🏫 Instituição:** P.PORTO - ESTG  
**📖 Unidade Curricular:** Bases de Dados  

---

## 1. ⚙️ LMD Procedimentais vs Não-Procedimentais (2 val.)

> ❓ **Pergunta 1:** Explique as diferenças existentes entre LMD procedimentais e não-procedimentais. Dê exemplos de linguagens que conheça. (2 val.)

**✍️ Resposta:**
As LMD procedimentais exigem que o utilizador especifique **como** obter os dados, detalhando a sequência de passos físicos e processando registo a registo (*one-record-at-a-time*), como acontece na Álgebra Relacional ou no uso de cursores em PL/SQL e T-SQL. Em contrapartida, as LMD não-procedimentais (ou declarativas) exigem apenas a especificação do **que** obter, definindo filtros e condições, cabendo ao otimizador do SGBD definir o plano físico e processar os dados em conjunto (*set-at-a-time*), sendo a instrução SELECT do SQL e o Cálculo Relacional os exemplos padrão.

---

## 2. 👁️ Vistas (Views) vs Relações Base (2 val.)

> ❓ **Pergunta 2:** O que é uma vista. Quais as diferenças entre uma vista e uma relação base. (2 val.)

**✍️ Resposta:**
Uma vista é uma relação virtual definida por uma consulta SQL (SELECT) e gerada dinamicamente, enquanto uma relação base é uma tabela física com dados armazenados permanentemente em disco. As principais diferenças são que a relação base ocupa espaço de armazenamento físico e a vista armazena apenas a sua definição de consulta nos metadados; as tabelas base aceitam qualquer operação DML direta, ao passo que as vistas possuem restrições estritas de escrita (não permitindo atualizações se contiverem junções, agrupamentos, DISTINCT ou agregados); e o acesso à relação base é direto, enquanto a vista exige a execução em tempo real da consulta subjacente, o que pode degradar o desempenho.

---

## 3. 🔗 Integridade Referencial e Ações ON DELETE/ON UPDATE (2 val.)

> ❓ **Pergunta 3:** Explique o que entende por Integridade Referencial. Identifique e descreva quais as ações que se podem utilizar nas subcláusulas ON DELETE e ON UPDATE. (2 val.).

**✍️ Resposta:**
A integridade referencial garante a consistência entre tabelas, obrigando a que os valores de uma chave estrangeira (FK) na tabela filha existam na chave primária (PK) da tabela pai ou sejam nulos. Para gerir alterações na tabela pai, as subcláusulas ON DELETE e ON UPDATE suportam quatro ações: CASCADE, que propaga a eliminação ou atualização do registo pai diretamente para os registos filhos; SET NULL, que define a FK dos filhos como nula (exigindo que a coluna permita nulos); SET DEFAULT, que altera a FK dos filhos para o valor padrão configurado; e NO ACTION (ou RESTRICT), que rejeita a operação no registo pai caso existam registos filhos dependentes.

---

## 4. ⚡ Triggers de Bases de Dados (2 val.)

> ❓ **Pergunta 4:** O que são triggers de bases de dados e para que servem? Quais as vantagens e desvantagens da utilização de triggers? (2 val.)

**✍️ Resposta:**
Um trigger é um bloco de código procedural executado automaticamente pelo SGBD em resposta a operações DML (INSERT, UPDATE ou DELETE) numa tabela. Serve para impor regras de negócio complexas, manter dados derivados e registar logs de auditoria. A sua principal vantagem é a centralização e automatização da consistência na base de dados, protegendo a integridade independentemente da aplicação cliente. As desvantagens residem no overhead de processamento que atrasa as escritas, na dificuldade de depuração (devido a disparos implícitos e possíveis efeitos em cascata) e na perda de portabilidade do código, visto que a sintaxe varia entre os SGBDs.

---

## 5. 🎯 Objetivos da Normalização e Impacto no Desempenho (2 val.)

> ❓ **Pergunta 5:** No contexto do modelo relacional de bases de dados, quais os objetivos da normalização de dados? De que forma o processo de normalização poderá afetar, posteriormente, o desempenho da respetiva implementação? (2 val.)

**✍️ Resposta:**
No modelo relacional, a normalização visa organizar os dados com base em chaves e dependências funcionais para minimizar a redundância e eliminar anomalias de atualização (inserção, modificação e remoção). O seu impacto no desempenho é misto: prejudica as operações de leitura, pois as consultas exigem mais junções (JOINs) entre tabelas menores, elevando o custo de processamento; porém, otimiza as operações de escrita, uma vez que as tabelas são mais estreitas, não há dados duplicados a sincronizar e as atualizações ocorrem num único local de forma mais rápida.

---

## 6. 🏢 Benefícios e Problemas dos Data Warehouses (2 val.)

> ❓ **Pergunta 6:** Descreva os principais benefícios e problemas associados aos Data Warehouses. (2 val.)

**✍️ Resposta:**
Um Data Warehouse é um repositório analítico integrado, histórico e não-volátil. Os seus benefícios incluem a centralização de dados consolidados de fontes heterogéneas, o suporte à análise de tendências através de histórico temporal e o isolamento de performance (evitando que consultas analíticas OLAP pesadas degradem os sistemas operacionais transacionais OLTP). Os principais problemas são o elevado custo e duração do projeto, a complexidade no desenvolvimento de processos de ETL (Extração, Transformação e Carregamento) para garantir a qualidade dos dados, e a necessidade de manutenção constante face a alterações nas fontes originais.

---

## 7. 📋 Exercício de Normalização de Fatura (3 val.)

> ❓ **Pergunta 7:** Observe atentamente o documento que acompanha o enunciado e que representa uma fatura. Escreva a definição da estrutura – nomes e atributos - das tabelas necessárias para representar estes dados num sistema de gestão de bases de dados relacional que suporte a emissão das faturas da empresa. Deve garantir que todas as tabelas estão na forma normal mais adequada. Não esqueça de indicar quais os campos que são chave primária e quais os que são chave estrangeira. Enuncie as definições de cada Forma Normal à medida que faz a normalização e identifique as dependências funcionais verificadas. (3 val.)

**✍️ Resposta:**
Identificamos no documento os atributos: NIF_Empresa (A), Nome_Empresa (B), Morada_Empresa (C), CodPostal_Empresa (D), NumFatura (E), Data (F), Hora (G), NIF_Cliente (H), Nome_Cliente (I), Mesa (J), Empregado (K), CodArtigo (L), Descricao_Artigo (M), Quantidade (N), PrecoUnitario (O), TaxaIVA (P), Subtotal_Linha (Q), Total_Fatura (R), MetodoPagamento (S), ATCUD (T), Incidencia_IVA (U) e Valor_IVA (V). Na Forma Não Normalizada (UNF), todos os atributos residem numa única relação com o grupo repetitivo de artigos: `Fatura_UNF(A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V)`.

1️⃣ A 1FN define que uma relação não deve conter grupos repetidos e todas as intersecções devem conter valores atómicos. Achatando o grupo de artigos, a PK passa a ser composta por (NumFatura, CodArtigo). O esquema obtido na 1FN é:
```text
Fatura_1FN(NumFatura, CodArtigo, NIF_Empresa, Nome_Empresa, Morada_Empresa, CodPostal_Empresa, Data, Hora, NIF_Cliente, Nome_Cliente, Mesa, Empregado, Descricao_Artigo, Quantidade, PrecoUnitario, TaxaIVA, Subtotal_Linha, Total_Fatura, MetodoPagamento, ATCUD, Incidencia_IVA, Valor_IVA)
PK: (NumFatura, CodArtigo)
```
As Dependências Funcionais (DFs) associadas são:
*   $NumFatura \rightarrow NIF\_Empresa, Nome\_Empresa, Morada\_Empresa, CodPostal\_Empresa, Data, Hora, NIF\_Cliente, Nome\_Cliente, Mesa, Empregado, Total\_Fatura, MetodoPagamento, ATCUD$
*   $CodArtigo \rightarrow Descricao\_Artigo, PrecoUnitario, TaxaIVA$
*   $NumFatura, CodArtigo \rightarrow Quantidade, Subtotal\_Linha$
*   $NIF\_Empresa \rightarrow Nome\_Empresa, Morada\_Empresa, CodPostal\_Empresa$
*   $NIF\_Cliente \rightarrow Nome\_Cliente$
*   $TaxaIVA \rightarrow Incidencia\_IVA, Valor\_IVA$

2️⃣ A 2FN exige que a relação esteja na 1FN e todos os atributos não primos dependam totalmente da chave primária (sem dependências parciais). Decompondo as dependências parciais sobre a chave composta (NumFatura, CodArtigo), dividimos a tabela original em três relações:
*   `Fatura_2FN(NumFatura, NIF_Empresa, Nome_Empresa, Morada_Empresa, CodPostal_Empresa, Data, Hora, NIF_Cliente, Nome_Cliente, Mesa, Empregado, Total_Fatura, MetodoPagamento, ATCUD)` | PK: `NumFatura`
*   `Artigo_2FN(CodArtigo, Descricao_Artigo, PrecoUnitario, TaxaIVA, Incidencia_IVA, Valor_IVA)` | PK: `CodArtigo`
*   `LinhaFatura_2FN(NumFatura, CodArtigo, Quantidade, Subtotal_Linha)` | PK: `(NumFatura, CodArtigo)`

3️⃣ A 3FN exige que a relação esteja na 2FN e não possua dependências transitivas (atributos não primos não dependem de outros não primos). Detetamos e removemos as dependências transitivas em Fatura_2FN (dados do cliente dependem de NIF_Cliente; dados da empresa dependem de NIF_Empresa) e em Artigo_2FN (valores de IVA dependem da TaxaIVA), resultando no esquema final:
```text
Empresa(NIF_Empresa, Nome_Empresa, Morada_Empresa, CodPostal_Empresa)
    PK: NIF_Empresa

Cliente(NIF_Cliente, Nome_Cliente)
    PK: NIF_Cliente

Fatura(NumFatura, Data, Hora, NIF_Empresa, NIF_Cliente, Mesa, Empregado, Total_Fatura, MetodoPagamento, ATCUD)
    PK: NumFatura
    FK: NIF_Empresa → Empresa(NIF_Empresa)
    FK: NIF_Cliente → Cliente(NIF_Cliente)

Artigo(CodArtigo, Descricao_Artigo, PrecoUnitario, TaxaIVA)
    PK: CodArtigo
    FK: TaxaIVA → TaxaIVA(TaxaIVA)

LinhaFatura(NumFatura, CodArtigo, Quantidade, Subtotal_Linha)
    PK: (NumFatura, CodArtigo)
    FK: NumFatura → Fatura(NumFatura)
    FK: CodArtigo → Artigo(CodArtigo)

TaxaIVA(TaxaIVA, Incidencia_IVA, Valor_IVA)
    PK: TaxaIVA
```

---

## 8. 📐 Modelação Entidade-Relacionamento, SQL e Álgebra Relacional (3 val.)

> ❓ **Enunciado do Problema 8:** O diagrama E/R a seguir pretende demonstrar o relacionamento existente entre diversas entidades de uma base de dados simplista de uma fábrica. As Ordens de Fabrico estão associadas a um cliente, sendo que um cliente poderá ter várias Ordens de Fabrico. Cada Ordem de fabrico contém uma lista de Produtos a fabricar onde está identificada a quantidade e a data de entrega prevista para cada Produto a fabricar. Naturalmente, um Produto poderá ser fabricado em várias Ordens de Fabrico e inclusive várias vezes na mesma ordem de fabrico desde que a data de entrega seja diferente.
> 
> Os dados a armazenar de cada entidade são:
> * **Cliente** – CódigoCliente, Nome, NIF, DataCriação, Morada, País
> * **OrdemFabrico** – Número, Data, Cliente
> * **Produto** – CódigoProduto, Nome, Familia
> 
> Naturalmente e atendendo ao relacionamento entre OrdemFabrico e Produto será necessária uma tabela associativa que armazene os dados necessários para o cumprimento integral do funcionamento de negócio descrito anteriormente.

---

### 🏗️ a) Tabela Associativa para a Fábrica (1 val.)

> ❓ **Pergunta 8a:** Identifique o nome, os atributos dessa tabela e a sua chave primária. (1 val.)

**✍️ Resposta:**
Para modelar o relacionamento M:N, cria-se a tabela associativa `OrdemFabricoProduto` com os atributos `Número`, `CódigoProduto`, `Quantidade` e `DataEntrega`. A chave primária (PK) deve ser `(Número, CódigoProduto, DataEntrega)`, uma vez que o mesmo produto pode ser fabricado várias vezes na mesma ordem de fabrico desde que a data de entrega seja diferente. O comando SQL DDL correspondente é:

```sql
CREATE TABLE OrdemFabricoProduto (
    Número INT,
    CódigoProduto VARCHAR(50),
    Quantidade INT NOT NULL,
    DataEntrega DATE,
    PRIMARY KEY (Número, CódigoProduto, DataEntrega),
    FOREIGN KEY (Número) REFERENCES OrdemFabrico(Número),
    FOREIGN KEY (CódigoProduto) REFERENCES Produto(CódigoProduto),
    CONSTRAINT chk_quantidade_positiva CHECK (Quantidade > 0)
);
```

---

### 💻 b) SQL: Países com mais de 10 Clientes com Ordens de Fabrico em 2024 (2 val.)

> ❓ **Pergunta 8b (SQL):** Identifique os Países que têm mais de 10 clientes que colocaram Ordens de Fabrico no ano de 2024? (2 val.)

**✍️ Resposta:**
Efetuamos uma junção (INNER JOIN) entre `Cliente` e `OrdemFabrico`, filtrando a data pelo ano 2024 no `WHERE`. Agrupamos por `País` e aplicamos o `HAVING` com `COUNT(DISTINCT CódigoCliente) > 10` para selecionar as nações com mais de 10 clientes únicos com ordens efetuadas nesse ano. O código SQL correspondente é:

```sql
SELECT c.País, COUNT(DISTINCT c.CódigoCliente) AS TotalClientes
FROM Cliente c
INNER JOIN OrdemFabrico o ON c.CódigoCliente = o.Cliente
WHERE o.Data >= '2024-01-01' AND o.Data <= '2024-12-31'
GROUP BY c.País
HAVING COUNT(DISTINCT c.CódigoCliente) > 10;
```

---

### 🔮 c) Álgebra Relacional: Famílias de Produtos Sem Ordens no 1º Trimestre de 2025 (2 val.)

> ❓ **Pergunta 8c (Álgebra Relacional):** Quais as famílias de produtos que não tiveram qualquer ordem de fabrico no primeiro trimestre de 2025? (2 val.)

**✍️ Resposta:**
Selecionamos ($\sigma$) as ordens do primeiro trimestre de 2025 em `OrdemFabrico`, juntamos ($\bowtie$) com `OrdemFabricoProduto` e depois com `Produto` para extrair e projetar ($\pi$) a lista das famílias de produtos vendidas. Subtraímos ($-$) este conjunto do total de famílias de produtos projetado de `Produto`, obtendo o resultado por diferença de conjuntos:

$$OrdensT1 \leftarrow \sigma_{Data \ge '2025-01-01' \wedge Data \le '2025-03-31'}(OrdemFabrico)$$
$$ProdutosT1 \leftarrow \pi_{CódigoProduto}(OrdemFabricoProduto \bowtie OrdensT1)$$
$$FamiliasComOrdem \leftarrow \pi_{Familia}(Produto \bowtie ProdutosT1)$$
$$TodasFamilias \leftarrow \pi_{Familia}(Produto)$$
$$Resultado \leftarrow TodasFamilias - FamiliasComOrdem$$
