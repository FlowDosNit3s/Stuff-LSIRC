# 🛠️ BD — Exercícios Práticos para Exame

> Estes tipos de exercícios saem em **TODOS** os exames.
> Ficheiro com padrões, templates e exemplos reais extraídos dos exames.

---

## 1. 📋 NORMALIZAÇÃO DE FATURA/DOCUMENTO (~100% dos exames teóricos, 3-4 val.)

### Como o professor pergunta:
> "Observe atentamente o documento que representa uma fatura. Escreva a definição da estrutura – nomes e atributos – das tabelas necessárias para representar estes dados num sistema de gestão de bases de dados relacional. Deve garantir que todas as tabelas estão na forma normal mais adequada. Não esqueça de indicar quais os campos que são chave primária e quais os que são chave estrangeira. Enuncie as definições de cada Forma Normal à medida que faz a normalização e identifique as dependências funcionais verificadas."

### Método passo-a-passo:

#### Passo 1: Identificar TODOS os dados do documento
Listar todos os campos presentes no documento (cabeçalho, linhas, rodapé).

#### Passo 2: Forma Não Normalizada (FNN)
> Definição: Uma tabela que contém um ou mais grupos repetidos.

Colocar tudo numa única tabela, identificando os grupos repetidos.

#### Passo 3: Primeira Forma Normal (1FN)
> Definição: Uma relação em que a intersecção entre uma linha e uma coluna contenha um e um só valor.

- Eliminar grupos repetidos
- Identificar a **Chave Primária**
- Identificar **Dependências Parciais** (atributos que dependem de parte da chave)

#### Passo 4: Segunda Forma Normal (2FN)
> Definição: Uma relação que está na 1FN e todos os atributos não pertencentes à chave primária são totalmente dependentes de qualquer chave candidata.

- Separar tabelas com base nas dependências parciais
- Cada tabela nova terá como PK a parte da chave da qual depende
- Identificar **Dependências Transitivas**

#### Passo 5: Terceira Forma Normal (3FN)
> Definição: Uma relação que está na 1FN e na 2FN e na qual nenhum atributo não pertencente à chave primária depende transitivamente de qualquer chave candidata.

- Separar tabelas com base nas dependências transitivas

---

### Exemplo Real (Pauta de Notas — dos exames):

**Dados do documento:**
```
Ano Lectivo, Código Curso, Curso, Código Docente, Docente,
Código Disciplina, Disciplina, Ano, Código Tipo Prova, Tipo de Prova,
Nº Matrícula, Nome do Aluno, Nota, Obs, Descrição Obs, Data, Horário Consulta
```

**1FN — Chave Primária:** `(Ano Letivo, Código Disciplina, Código Tipo Prova, Nº Matrícula, Obs)`

**Dependências Parciais encontradas:**
```
Código Disciplina → Código Curso, Curso, Disciplina, Ano
Código Tipo Prova → Tipo de Prova
Nº Matrícula → Nome do Aluno
Obs → Descrição Obs
Ano Letivo, Código Disciplina → Código Docente, Docente
Ano Letivo, Código Disciplina, Código Tipo Prova → Data, Horário Consulta
Ano Letivo, Código Disciplina, Código Tipo Prova, Nº Matrícula → Nota
```

**2FN — Tabelas resultantes:**
```
Disciplina  (Código Disciplina, Disciplina, Código Curso, Curso, Ano)
TipoProva   (Código Tipo Prova, Tipo de Prova)
Aluno       (Nº Matrícula, Nome do Aluno)
Observações (Obs, Descrição Obs)
Regente     (Ano Letivo, Código Disciplina, Código Docente, Docente)
Consulta    (Ano Letivo, Código Disciplina, Código Tipo Prova, Data, Horário Consulta)
Nota        (Ano Letivo, Código Disciplina, Código Tipo Prova, Nº Matrícula, Nota)
Pauta       (Ano Letivo, Código Disciplina, Código Tipo Prova, Nº Matrícula, Obs)
```

**Dependências Transitivas encontradas:**
```
Código Curso → Curso           (em Disciplina)
Código Docente → Docente       (em Regente)
```

**3FN — Tabelas finais:**
```
Disciplina  (Código Disciplina, Disciplina, Código Curso, Ano)
Curso       (Código Curso, Curso)
TipoProva   (Código Tipo Prova, Tipo de Prova)
Aluno       (Nº Matrícula, Nome do Aluno)
Observações (Obs, Descrição Obs)
Regente     (Ano Letivo, Código Disciplina, Código Docente)
Docente     (Código Docente, Docente)
Consulta    (Ano Letivo, Código Disciplina, Código Tipo Prova, Data, Horário Consulta)
Nota        (Ano Letivo, Código Disciplina, Código Tipo Prova, Nº Matrícula, Nota)
Pauta       (Ano Letivo, Código Disciplina, Código Tipo Prova, Nº Matrícula, Obs)
```
> PK sublinhada, FK em itálico nos exames. Indicar sempre quais são PK e FK!

---

### Exemplo Real (Encomenda de Sapatos — Exame Normal 08/09 e Especial 08/09):

**Dados do documento:**

Cabeçalho:
```
Data da encomenda, Número da encomenda, Código do cliente, Nome do cliente,
Rua do Cliente, Código de condições de pagamento, Texto descritivo das condições,
Código do vendedor, Data de entrega, Nome do vendedor
```
Linhas:
```
Número de Linha, Código do artigo, Nome do artigo,
Tamanho, Quantidade por tamanho, Preço unitário, Valor total do artigo
```
Rodapé:
```
Valor total da encomenda, Local de entrega
```

**Tabelas após normalização (3FN):**
```
Cliente            (Código Cliente, Nome, Rua)
CondiçõesPagamento (Código Condições, Texto Descritivo)
Vendedor           (Código Vendedor, Nome Vendedor)
Encomenda          (Número Encomenda, Data, Código Cliente, Código Condições,
                    Código Vendedor, Data Entrega, Valor Total, Local Entrega)
Artigo             (Código Artigo, Nome Artigo, Preço Unitário)
LinhaEncomenda     (Número Encomenda, Número Linha, Código Artigo, Valor Total Artigo)
TamanhoLinha       (Número Encomenda, Número Linha, Tamanho, Quantidade)
```

---

## 2. 🗃️ SQL LDD — CREATE TABLE (~100% dos exames práticos, 6-7 val.)

### Template padrão:

```sql
-- 1. Criar DOMÍNIOS primeiro
CREATE DOMAIN Dnome AS VARCHAR(30);
CREATE DOMAIN Dmorada AS VARCHAR(50);
CREATE DOMAIN DcodX AS DECIMAL(5,0)
    CHECK (VALUE BETWEEN 1 AND 99999);

-- 2. Criar tabelas (das mais independentes para as mais dependentes)
CREATE TABLE NomeTabela (
    atributo1    Dominio     NOT NULL,
    atributo2    Dominio     NOT NULL,
    atributo3    Dominio,                          -- NULL permitido (facultativo)
    atributo4    Dominio     NOT NULL DEFAULT 'X',  -- valor por defeito
    atributo5    Dominio     NOT NULL UNIQUE,        -- valor único

    PRIMARY KEY (atributo1),

    FOREIGN KEY (atributoFK) REFERENCES OutraTabela
        ON UPDATE CASCADE
        ON DELETE SET NULL,

    -- Restrições CHECK
    CHECK (NOT EXISTS (
        SELECT atributoFK
        FROM NomeTabela
        GROUP BY atributoFK
        HAVING COUNT(*) > 5
    ))
);
```

### Padrões de restrições mais comuns nos exames:

| Restrição do enunciado | SQL correspondente |
|------------------------|--------------------|
| "numérico entre X e Y" | `CREATE DOMAIN D AS DECIMAL(n,0) CHECK (VALUE BETWEEN X AND Y)` |
| "N caracteres" | `VARCHAR(N)` ou `CHAR(N)` se fixo |
| "um dos valores (A, B, C)" | `CHAR(1) CHECK (VALUE IN ('A','B','C'))` |
| "preenchimento obrigatório" | `NOT NULL` |
| "preenchimento facultativo" | (não colocar NOT NULL) |
| "valor por defeito X" | `DEFAULT 'X'` |
| "valor único" | `UNIQUE` |
| "não mais de N registos" | `CHECK (NOT EXISTS (SELECT ... HAVING COUNT(*) > N))` |
| "se eliminar → eliminar relacionados" | `ON DELETE CASCADE` |
| "se atualizar → atualizar relacionados" | `ON UPDATE CASCADE` |
| "se eliminar → manter (FK a NULL)" | `ON DELETE SET NULL` |
| "não eliminar se existirem registos" | `ON DELETE NO ACTION` (ou não colocar nada) |
| "telefone 9 dígitos" | `DECIMAL(9,0) CHECK (VALUE BETWEEN 100000000 AND 999999999)` |
| "numérico positivo com N dígitos" | `DECIMAL(N,0) CHECK (VALUE > 0)` |

### Exemplo Real (BD Atletas — Exame Normal Prático 07/08):

```sql
CREATE DOMAIN DcodA AS CHAR(5);
CREATE DOMAIN Dnome AS VARCHAR(30);
CREATE DOMAIN Dmorada AS VARCHAR(50);
CREATE DOMAIN Ddata AS DATE;
CREATE DOMAIN Dtelefone AS DECIMAL(9,0)
    CHECK (VALUE BETWEEN 100000000 AND 999999999);
CREATE DOMAIN Dmodalidade AS DECIMAL(2,0)
    CHECK (VALUE BETWEEN 1 AND 99);
CREATE DOMAIN Ddescricao AS VARCHAR(50);
CREATE DOMAIN Dtempo AS DECIMAL(4,0)
    CHECK (VALUE >= 0 AND VALUE <= 9999);

CREATE TABLE Atleta (
    codA            DcodA       NOT NULL,
    nome            Dnome       NOT NULL,
    morada          Dmorada,
    dataNascimento  Ddata       NOT NULL,
    telefone        Dtelefone,
    PRIMARY KEY (codA)
);

CREATE TABLE Modalidade (
    modalidade  Dmodalidade NOT NULL,
    descricao   Ddescricao  NOT NULL,
    PRIMARY KEY (modalidade)
);

CREATE TABLE Tempo (
    codA        DcodA        NOT NULL,
    modalidade  Dmodalidade  NOT NULL,
    data        Ddata        NOT NULL,
    tempo       Dtempo       NOT NULL,
    PRIMARY KEY (codA, modalidade, data),
    FOREIGN KEY (codA) REFERENCES Atleta
        ON UPDATE CASCADE
        ON DELETE CASCADE,
    FOREIGN KEY (modalidade) REFERENCES Modalidade
        ON UPDATE CASCADE
        ON DELETE CASCADE,
    CHECK (NOT EXISTS (
        SELECT codA FROM Tempo
        GROUP BY codA
        HAVING COUNT(DISTINCT modalidade) > 5
    ))
);
```

---

## 3. 📝 SQL LMD — Queries SELECT (~100% dos exames, 2-5 val.)

### Padrões mais comuns:

#### Padrão 1: "Qual o X com mais/menos Y" (MAX/MIN com GROUP BY)
```sql
-- Qual o atleta com o maior número de tempos registados
SELECT A.codA, A.nome, COUNT(*) AS NTempos
FROM Atleta A, Tempo T
WHERE A.codA = T.codA
GROUP BY A.codA, A.nome
HAVING COUNT(*) >= ALL (
    SELECT COUNT(*)
    FROM Tempo
    GROUP BY codA
);
```

#### Padrão 2: "Lista com mais de N" (HAVING COUNT)
```sql
-- Modalidades com mais de 100 registos de tempos no ano de 2008
SELECT M.modalidade, M.descricao, COUNT(*) AS NRegistos
FROM Modalidade M, Tempo T
WHERE M.modalidade = T.modalidade
  AND T.data >= '2008-01-01' AND T.data <= '2008-12-31'
GROUP BY M.modalidade, M.descricao
HAVING COUNT(*) > 100
ORDER BY NRegistos DESC;
```

#### Padrão 3: "Lista ordenada por ordem decrescente de..."
```sql
-- Modalidades mais praticadas por atletas nascidos em 1995
SELECT M.descricao, COUNT(*) AS NVezes
FROM Modalidade M, Tempo T, Atleta A
WHERE M.modalidade = T.modalidade
  AND T.codA = A.codA
  AND A.dataNascimento >= '1995-01-01'
  AND A.dataNascimento <= '1995-12-31'
GROUP BY M.descricao
ORDER BY NVezes DESC;
```

#### Padrão 4: "Quantos/Quantas..." (COUNT simples ou com condição)
```sql
-- Quantas pizzas utilizam mais de 4 ingredientes
SELECT COUNT(*) FROM (
    SELECT codP
    FROM Composicao
    GROUP BY codP
    HAVING COUNT(*) > 4
) AS PizzasMais4;
```

#### Padrão 5: "Quais os X que nunca Y" (NOT IN / NOT EXISTS / EXCEPT)
```sql
-- Atletas que não registaram nenhum tempo durante 2007
SELECT A.codA, A.nome
FROM Atleta A
WHERE A.codA NOT IN (
    SELECT T.codA FROM Tempo T
    WHERE T.data >= '2007-01-01' AND T.data <= '2007-12-31'
);

-- Alternativa com EXCEPT:
SELECT codA FROM Atleta
EXCEPT
SELECT codA FROM Tempo WHERE data >= '2007-01-01' AND data <= '2007-12-31';
```

#### Padrão 6: "Países/Regiões com mais de N clientes que fizeram X em Y"
```sql
-- Países com mais de 10 clientes que colocaram Ordens de Fabrico em 2024
SELECT C.pais, COUNT(DISTINCT C.codigoCliente) AS NClientes
FROM Cliente C, OrdemFabrico OF
WHERE C.codigoCliente = OF.cliente
  AND OF.data >= '2024-01-01' AND OF.data <= '2024-12-31'
GROUP BY C.pais
HAVING COUNT(DISTINCT C.codigoCliente) > 10;
```

#### Padrão 7: Vendedores/Produtos com filtros combinados
```sql
-- Vendedores que venderam produtos de mais de 3 famílias diferentes
SELECT V.codvend, V.nome
FROM Vendedores V, Vendas VE, Produtos P
WHERE V.codvend = VE.codvend
  AND VE.codP = P.codprod
GROUP BY V.codvend, V.nome
HAVING COUNT(DISTINCT P.familia) > 3;
```

---

## 4. 📐 ÁLGEBRA RELACIONAL (~100% dos exames, 2-5 val.)

### Operadores:
```
σ  (sigma)    = SELEÇÃO       → filtra linhas (como WHERE)
π  (pi)       = PROJEÇÃO      → escolhe colunas (como SELECT)
×             = PROD. CART.   → combina todas as linhas
∪             = UNIÃO         → junta resultados
−             = DIFERENÇA     → linhas de A que não estão em B
⋈  (bowtie)   = JUNÇÃO        → junção natural
÷             = DIVISÃO       → "para todos"
```

### Padrões mais comuns:

#### Padrão 1: "Quais os X que nunca Y" (DIFERENÇA)
```
π(codA)(Atleta) − π(codA)(σ(data >= '2007-01-01' ∧ data <= '2007-12-31')(Tempo))
```
> "Atletas que não registaram tempos em 2007"

#### Padrão 2: Query simples com seleção + junção + projeção
```
π(nome)(σ(data >= '2008-07-01' ∧ data <= '2008-07-15')(Tempo) ⋈ Atleta)
```
> "Nomes dos atletas com tempos na 1ª quinzena de Julho 2008"

#### Padrão 3: "X que fizeram TODOS os Y" (DIVISÃO)
```
π(codA, modalidade)(Tempo) ÷ π(modalidade)(Modalidade)
```
> "Atletas que praticaram todas as modalidades"

#### Padrão 4: "Famílias/Regiões que NÃO tiveram X" (DIFERENÇA com projeção)
```
π(familia)(Produto) − π(familia)(Produto ⋈ OrdemFabrico_Produto ⋈ σ(data >= '2025-01-01' ∧ data <= '2025-03-31')(OrdemFabrico))
```
> "Famílias que não tiveram ordens de fabrico no 1º trimestre 2025" (Exame 24/25)

#### Padrão 5: Junção com seleção e projeção (otimizada)
```
-- NÃO otimizada:
π(nome)(σ(combustível = 'Gasolina')(Mecânico ⋈ Manutenção ⋈ Automóvel))

-- OTIMIZADA (selecionar ANTES de juntar):
π(nome)(Mecânico ⋈ Manutenção ⋈ σ(combustível = 'Gasolina')(Automóvel))
```
> A otimização vale 50% da cotação em muitos exames!

### Dica de otimização:
Aplicar **seleções (σ) o mais cedo possível** para reduzir o número de tuplos antes das junções. Aplicar **projeções (π) para eliminar colunas desnecessárias**.

---

## 5. 👁️ CRIAÇÃO DE VISTAS (~80% dos exames, 2-3 val.)

### Template:
```sql
CREATE VIEW NomeDaVista AS
    SELECT ...
    FROM ...
    WHERE ...
    GROUP BY ...
    HAVING ...;
```

### Padrões mais comuns:

#### Padrão 1: "Lista dos X com mais de N de Y no mês Z"
```sql
-- Mecânicos com menos de 10 manutenções em Janeiro 2008
CREATE VIEW MecMenosManutJan08 AS
SELECT M.mecânico, M.nome
FROM Mecânico M, Manutenção MA
WHERE M.mecânico = MA.mecânico
  AND MA.data >= '2008-01-01' AND MA.data <= '2008-01-31'
GROUP BY M.mecânico, M.nome
HAVING COUNT(*) < 10;
```

#### Padrão 2: "Lista de X desocupados/disponíveis"
```sql
-- Quartos desocupados hoje em hotéis de Portugal
CREATE VIEW QuartosLivresHoje AS
SELECT Q.codh, Q.codq
FROM Quartos Q, Hotel H
WHERE Q.codh = H.codh
  AND H.país = 'Portugal'
  AND NOT EXISTS (
    SELECT 1 FROM Estadias E
    WHERE E.codh = Q.codh AND E.codq = Q.codq
      AND E.dataent <= CURRENT_DATE
      AND (E.datasai IS NULL OR E.datasai >= CURRENT_DATE)
  );
```

#### Padrão 3: "Vista com preço de custo / totais agregados"
```sql
-- Preço de Custo das Pizzas com menos de 3 ingredientes
CREATE VIEW PrecoCustoPizzas AS
SELECT P.codP, P.descricao,
       SUM(C.qtde * I.precoG) AS PrecoCusto
FROM Pizza P, Composicao C, Ingrediente I
WHERE P.codP = C.codP AND C.codI = I.codI
GROUP BY P.codP, P.descricao
HAVING COUNT(*) < 3;
```

#### Padrão 4: "Vista por artigo com quantidade por armazém"
```sql
-- Por artigo, quantidade armazenada em cada armazém
CREATE VIEW StockPorArmazem AS
SELECT AA.Artigos AS CodArtigo, A.Designacao,
       AA.Armazem AS CodArmazem, AR.Designacao AS NomeArmazem,
       AA.Stock
FROM ArtigosArmazens AA, Artigos A, Armazens AR
WHERE AA.Artigos = A.Codigo
  AND AA.Armazem = AR.Codigo;
```

---

## 📎 Resumo de Cláusulas SQL (cola mental)

```
SELECT    [DISTINCT] coluna1, coluna2, função_agregação(coluna)
FROM      tabela1 [alias], tabela2 [alias]
WHERE     condição (NÃO pode ter funções de agregação)
GROUP BY  coluna1, coluna2
HAVING    condição com funções de agregação
ORDER BY  coluna [ASC|DESC]
```

### Funções de Agregação:
| Função | Descrição | Aceita NULL? |
|--------|-----------|:------------:|
| `COUNT(*)` | Conta linhas | Sim |
| `COUNT(coluna)` | Conta valores não-NULL | Não |
| `SUM(coluna)` | Soma | Não |
| `AVG(coluna)` | Média | Não |
| `MAX(coluna)` | Máximo | Não |
| `MIN(coluna)` | Mínimo | Não |

### Operações entre Queries (Union Compatible):
```sql
query1 UNION query2      -- une (sem duplicados)
query1 UNION ALL query2  -- une (com duplicados)
query1 INTERSECT query2  -- interseção
query1 EXCEPT query2     -- diferença (A - B)
```

---

## ⚡ Checklist Rápido para o Exame

### SQL LDD:
- [ ] Criar DOMÍNIOS antes das tabelas
- [ ] Criar tabelas na ordem correta (sem FKs para tabelas que ainda não existem)
- [ ] NOT NULL em campos obrigatórios
- [ ] DEFAULT onde indicado
- [ ] UNIQUE onde indicado
- [ ] CHECK para validações de intervalo
- [ ] CHECK com NOT EXISTS para limites de registos
- [ ] PRIMARY KEY em todas as tabelas
- [ ] FOREIGN KEY com ON UPDATE e ON DELETE corretos

### SQL LMD:
- [ ] Usar alias nas tabelas quando há JOINs
- [ ] GROUP BY deve incluir todas as colunas do SELECT que NÃO são agregações
- [ ] HAVING para filtrar grupos (não WHERE)
- [ ] Subqueries para "qual o máximo/mínimo"
- [ ] NOT IN / NOT EXISTS / EXCEPT para "nunca"

### Álgebra Relacional:
- [ ] Otimizar: σ antes de ⋈
- [ ] Diferença (−) para "nunca"
- [ ] Divisão (÷) para "todos"
- [ ] Projeção final para mostrar apenas colunas pedidas

### Normalização:
- [ ] Enunciar definição de cada FN à medida que normaliza
- [ ] Identificar dependências parciais (1FN → 2FN)
- [ ] Identificar dependências transitivas (2FN → 3FN)
- [ ] Indicar PK e FK em cada tabela final
