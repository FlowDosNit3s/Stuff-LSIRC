# 🎓 Guia Definitivo: Resolução da Pergunta 8
## 📐 Modelação, SQL Avançado (Agrupamentos) e Álgebra Relacional (Negação)

Este guia foi elaborado para te ajudar a **compreender a metodologia** por trás da **Pergunta 8** dos exames de Bases de Dados (ESTG-IPP), focando-se na modelação de chaves, queries SQL complexas com agrupamentos, e expressões de Álgebra Relacional baseadas na lógica de negação ("nunca", "não teve", "sem").

---

## 📌 1. Anatomia da Pergunta 8

A Pergunta 8 nos exames recentes segue um padrão extremamente consistente (normalmente cotada em **5 valores**):

*   **Parte A: Modelação e Chaves (1 val.)**
    *   Identificação de Chaves Primárias (PK) e Chaves Estrangeiras (FK) de uma tabela associativa (N:M).
    *   Desenho DDL (`CREATE TABLE`) com restrições (`PRIMARY KEY`, `FOREIGN KEY`, `CHECK`).
*   **Parte B: Consulta SQL com Agrupamento e Filtragem (2 val.)**
    *   Exige a junção de 3 ou mais tabelas.
    *   Filtragem por datas/anos (ex: "no ano de 2024").
    *   Agrupamento (`GROUP BY`) por atributos descritivos.
    *   Filtragem do grupo (`HAVING`) com condições de contagem/soma (ex: "mais de 10 clientes").
    *   **Ponto Crítico:** Distinguir contagem geral (`COUNT(*)`) de contagem de elementos únicos (`COUNT(DISTINCT ...)`).
*   **Parte C: Álgebra Relacional com Negação (2 val.)**
    *   Exige encontrar entidades que **não** realizaram determinada ação (ex: "secções que nunca tiveram plantações", "famílias de produtos que não tiveram ordens de fabrico").
    *   **Ponto Crítico:** Uso obrigatório da **Diferença de Conjuntos** ($-$). Filtragens diretas com $\neq$ ou $\neq$ em junções são incorretas.

---

## 🧩 2. Metodologia de Resolução Passo a Passo

### 🏗️ 2.1. Como Resolver a Parte A: Modelação e Chaves

1.  **Identificar o Tipo de Relação:** Se a entidade for associativa (gerada por uma relação Muitos-para-Muitos entre A e B), ela herdará as PKs das tabelas originais como FKs.
2.  **Definir a Chave Primária Composta:**
    *   A PK base de uma tabela associativa é a combinação das chaves estrangeiras: `(chave_A, chave_B)`.
    *   **Regra do Negócio Importante:** Se o enunciado disser que *"a entidade A pode relacionar-se com a entidade B múltiplas vezes, desde que em momentos/datas diferentes"*, a data/hora **deve fazer parte da Chave Primária**.
    *   *Exemplo:* `PRIMARY KEY (NúmeroOrdem, CódigoProduto, DataEntrega)`.
3.  **Definir Chaves Estrangeiras:** Ambas as chaves herdadas devem ter restrições `FOREIGN KEY REFERENCES`.
4.  **Adicionar Restrições CHECK:** Se houver valores que devam ser positivos (ex: `Quantidade > 0` ou `Preco > 0`), adiciona um `CONSTRAINT ... CHECK (...)`.

---

### 💻 2.2. Como Resolver a Parte B: SQL (Junções + GROUP BY + HAVING)

Para nunca errar a construção da query SQL, segue este esqueleto mental:

```sql
SELECT TabelaBase.AtributoAgrupado, COUNT(DISTINCT TabelaFiltro.ID)
FROM Tabela1
INNER JOIN Tabela2 ON ...
INNER JOIN Tabela3 ON ...
WHERE TabelaData.Data >= 'ANO-01-01' AND TabelaData.Data <= 'ANO-12-31'
GROUP BY TabelaBase.AtributoAgrupado
HAVING COUNT(DISTINCT TabelaFiltro.ID) > X;
```

#### 🛡️ Checklist de Implementação:
1.  **O SELECT e o GROUP BY:** O que a pergunta pede para listar? (Ex: "os Países", "as Estufas", "os Instrutores"). Deves colocar essa coluna no `SELECT` e **obrigatoriamente** no `GROUP BY`.
2.  **As Junções (JOINs):** Lista as tabelas necessárias para ligar o atributo que queres listar (ex: País do cliente) até ao critério (ex: data da ordem de fabrico). Junta-as usando `INNER JOIN` nas chaves correspondentes.
3.  **Filtro de Data (WHERE):** Restringe o período temporal antes de agrupar.
    *   *Formato padrão e seguro:* `DataCol >= '2024-01-01' AND DataCol <= '2024-12-31'`
4.  **Agregação e Contagem Única (HAVING):**
    *   Se a pergunta pede: *"países com mais de 10 clientes"* $\rightarrow$ Um cliente pode fazer várias compras, mas só deve ser contado uma vez por país. Usa: `COUNT(DISTINCT CódigoCliente) > 10`.
    *   Se a pergunta pede: *"estufas com mais de 10 plantações"* $\rightarrow$ Cada plantação individual conta. Usa: `COUNT(*) > 10` ou `COUNT(idPlantacao) > 10`.
    *   **Regra de Ouro:**
        *   Pediu "mais de X **entidades** (clientes/passageiros/sócios)" $\rightarrow$ `COUNT(DISTINCT id)`
        *   Pediu "mais de X **ações/registos** (plantações/reservas/inscrições)" $\rightarrow$ `COUNT(*)` ou `COUNT(id_da_associativa)`

---

### 🔮 2.3. Como Resolver a Parte C: Álgebra Relacional (Lógica de Negação)

O maior erro dos estudantes é tentar resolver perguntas de negação (ex: *"aeroportos que nunca foram destino de voo"*) usando seleções de exclusão como $\sigma_{destino \neq 'Porto'}$. Isso está **ERRADO**, pois apenas seleciona voos para outros locais, mas não garante que o aeroporto nunca recebeu voos.

A única forma correta é usar a **Diferença de Conjuntos** ($-$).

#### 📐 A Fórmula Universal da Negação:
$$\text{Resultado} \leftarrow \text{Universo Total (Quem podia ter feito)} - \text{Conjunto Ativo (Quem fez na data/condição)}$$

#### 🚶 Passo a Passo Metodológico:
1.  **Passo 1: Definir o Universo Total ($T$).**
    *   Projeta o identificador único de todas as entidades possíveis.
    *   *Exemplo (Todas as famílias):* $TodasFamilias \leftarrow \pi_{Familia}(Produto)$
    *   *Exemplo (Todos os aeroportos):* $TodosAeroportos \leftarrow \pi_{codIATA}(Aeroporto)$
2.  **Passo 2: Definir o Conjunto Ativo ($A$) com a Condição.**
    *   Aplica a seleção de data/filtro desejada sobre a tabela de eventos (ex: Ordens de Fabrico em 2025, Reservas em 2026).
    *   Junta com as tabelas necessárias para obter o **mesmo atributo de junção** do Universo Total.
    *   Projeta esse atributo.
    *   *Exemplo (Famílias que tiveram ordens no período):*
        *   $OrdensPeriodo \leftarrow \sigma_{Data \ge '2025-01-01' \wedge Data \le '2025-03-31'}(OrdemFabrico)$
        *   $ProdutosPeriodo \leftarrow \pi_{CódigoProduto}(OrdemFabricoProduto \bowtie OrdensPeriodo)$
        *   $FamiliasAtivas \leftarrow \pi_{Familia}(Produto \bowtie ProdutosPeriodo)$
3.  **Passo 3: Fazer a Subtração ($R = T - A$).**
    *   $Resultado \leftarrow TodasFamilias - FamiliasAtivas$
4.  **Passo 4 (Se necessário): Recuperar dados descritivos.**
    *   Se a pergunta pedir o nome ou detalhes da entidade (e não apenas a chave/atributo do ID), faz uma junção final com a tabela original:
    *   $Final \leftarrow \pi_{Nome, Detalhes}(Resultado \bowtie TabelaOriginal)$

---

## 📝 3. Análise de Resoluções de Exames Anteriores

Vamos rever as soluções oficiais de exames passados para ver a teoria em prática.

### 🏢 Caso 1: Exame Época Normal 2024/2025 (Fábrica)
*   **Contexto:**
    *   `Cliente` (CódigoCliente, Nome, NIF, DataCriação, Morada, País)
    *   `OrdemFabrico` (Número, Data, Cliente)
    *   `Produto` (CódigoProduto, Nome, Familia)
    *   `OrdemFabricoProduto` (Número, CódigoProduto, Quantidade, DataEntrega)

#### 💻 Pergunta 8b (SQL)
> *"Identifique os Países que têm mais de 10 clientes que colocaram Ordens de Fabrico no ano de 2024."*

**Resolução Analisada:**
```sql
SELECT c.País, COUNT(DISTINCT c.CódigoCliente) AS TotalClientes
FROM Cliente c
INNER JOIN OrdemFabrico o ON c.CódigoCliente = o.Cliente
WHERE o.Data >= '2024-01-01' AND o.Data <= '2024-12-31'
GROUP BY c.País
HAVING COUNT(DISTINCT c.CódigoCliente) > 10;
```
*   **Porquê o `COUNT(DISTINCT c.CódigoCliente)`?** Porque um cliente pode ter feito 5 ordens em 2024. Se usássemos `COUNT(*)`, esse cliente seria contado 5 vezes, inflando o número de clientes do país. Queremos contar *clientes únicos*.
*   **Porquê o `WHERE`?** Filtra o ano de 2024 nas ordens de fabrico antes de fazer o agrupamento.

#### 🔮 Pergunta 8c (Álgebra Relacional)
> *"Quais as famílias de produtos que não tiveram qualquer ordem de fabrico no primeiro trimestre de 2025?"*

**Resolução Analisada:**
$$OrdensT1 \leftarrow \sigma_{Data \ge '2025-01-01' \wedge Data \le '2025-03-31'}(OrdemFabrico)$$
$$ProdutosT1 \leftarrow \pi_{CódigoProduto}(OrdemFabricoProduto \bowtie OrdensT1)$$
$$FamiliasComOrdem \leftarrow \pi_{Familia}(Produto \bowtie ProdutosT1)$$
$$TodasFamilias \leftarrow \pi_{Familia}(Produto)$$
$$Resultado \leftarrow TodasFamilias - FamiliasComOrdem$$

*   **Universo Total ($T$):** $TodasFamilias$ (todas as famílias existentes na tabela `Produto`).
*   **Conjunto Ativo ($A$):** $FamiliasComOrdem$ (famílias dos produtos associados a ordens criadas entre jan-2025 e mar-2025).

---

### 🌿 Caso 2: Exame Época Normal 2023/2024 (Estufas)
*   **Contexto:**
    *   `Estufa` (codE, descricao, capacidade, cidade)
    *   `Secção` (codigoS, tipo, estufa)
    *   `Produto` (codP, nome, stock, tipo)
    *   `Plantação` (codP, produto, codS, data_início, data_fim)

#### 💻 Pergunta 8a (SQL)
> *"Quais as estufas que tiveram mais que 10 plantações do mesmo produto?"*

**Resolução Analisada:**
```sql
SELECT e.codE, e.descricao
FROM Estufa e
INNER JOIN Secção s ON e.codE = s.estufa
INNER JOIN Plantação p ON s.codigoS = p.codS
GROUP BY e.codE, e.descricao, p.produto
HAVING COUNT(*) > 10;
```
*   **Porquê `GROUP BY e.codE, e.descricao, p.produto`?** Como a pergunta pede estufas que tiveram mais que 10 plantações *do mesmo produto*, temos de agrupar por estufa **E** por produto.
*   **Porquê `COUNT(*)`?** Porque cada registo em Plantação representa uma plantação individual. Queremos saber a contagem total de plantações daquele produto naquela estufa.

#### 🔮 Pergunta 8b (Álgebra Relacional)
> *"Quais as secções que nunca tiveram plantações?"*

**Resolução Analisada:**
$$SeccoesComPlantacao \leftarrow \pi_{codS}(Plantação)$$
$$TodasSeccoes \leftarrow \pi_{codigoS}(Secção)$$
$$Resultado \leftarrow TodasSeccoes - SeccoesComPlantacao$$

*   **Universo Total ($T$):** Todas as secções criadas na tabela `Secção` ($\pi_{codigoS}(Secção)$).
*   **Conjunto Ativo ($A$):** Secções referenciadas na tabela `Plantação` ($\pi_{codS}(Plantação)$).

---

## 🏋️ 4. Banco de Exercícios Inéditos para Treino (com Resoluções)

Utiliza estes exercícios para testar os teus conhecimentos. Tenta resolver sozinho antes de olhar para a resolução!

### 📚 Exercício 1: Sistema de Empréstimos de Livraria
**Esquema:**
*   `Leitor` (idLeitor, nome, cidade, dataAdesao)
*   `Livro` (idLivro, titulo, categoria, preco)
*   `Emprestimo` (idLeitor, idLivro, dataEmprestimo, dataDevolucao)

*Nota: Um leitor pode requisitar o mesmo livro várias vezes, mas em datas de empréstimo diferentes.*

---

#### ❓ Pergunta 1a (Modelação/DDL)
Identifique a chave primária (PK) e as chaves estrangeiras (FK) da tabela `Emprestimo`. Escreva o comando SQL DDL para criar esta tabela, garantindo que o `idLeitor` e o `idLivro` estão corretamente associados e que a `dataEmprestimo` suporta requisições repetidas.

<details>
<summary><b>💡 Ver Resposta (Modelação)</b></summary>

**Chaves:**
*   **PK:** `(idLeitor, idLivro, dataEmprestimo)` - A data de empréstimo tem de fazer parte da chave porque o mesmo leitor pode requisitar o mesmo livro em datas diferentes.
*   **FKs:**
    *   `idLeitor` refere-se a `Leitor(idLeitor)`
    *   `idLivro` refere-se a `Livro(idLivro)`

**DDL SQL:**
```sql
CREATE TABLE Emprestimo (
    idLeitor INT,
    idLivro INT,
    dataEmprestimo DATE,
    dataDevolucao DATE,
    PRIMARY KEY (idLeitor, idLivro, dataEmprestimo),
    FOREIGN KEY (idLeitor) REFERENCES Leitor(idLeitor),
    FOREIGN KEY (idLivro) REFERENCES Livro(idLivro)
);
```
</details>

---

#### ❓ Pergunta 1b (SQL)
Quais os leitores (idLeitor e nome) da cidade de "Braga" que requisitaram mais de 5 livros **diferentes** da categoria "Ficção" no ano de 2025?

<details>
<summary><b>💡 Ver Resposta (SQL)</b></summary>

**Metodologia:**
1.  **Onde estão os dados?** `Leitor` (cidade, nome), `Emprestimo` (dataEmprestimo) e `Livro` (categoria, idLivro). Precisa de `INNER JOIN` entre as 3.
2.  **Filtros (WHERE):** Cidade = "Braga", Categoria = "Ficção" e ano de 2025.
3.  **Agrupamento:** Por leitor (`l.idLeitor`, `l.nome`).
4.  **Agregação (HAVING):** Mais de 5 livros *diferentes* $\rightarrow$ `COUNT(DISTINCT idLivro) > 5`.

**Código SQL:**
```sql
SELECT l.idLeitor, l.nome
FROM Leitor l
INNER JOIN Emprestimo e ON l.idLeitor = e.idLeitor
INNER JOIN Livro liv ON e.idLivro = liv.idLivro
WHERE l.cidade = 'Braga'
  AND liv.categoria = 'Ficção'
  AND e.dataEmprestimo >= '2025-01-01'
  AND e.dataEmprestimo <= '2025-12-31'
GROUP BY l.idLeitor, l.nome
HAVING COUNT(DISTINCT e.idLivro) > 5;
```
</details>

---

#### ❓ Pergunta 1c (Álgebra Relacional)
Quais as categorias de livros que não registaram qualquer empréstimo no segundo semestre do ano 2024?

<details>
<summary><b>💡 Ver Resposta (Álgebra Relacional)</b></summary>

**Metodologia (Negação):**
1.  **Universo Total ($T$):** Todas as categorias existentes na tabela `Livro`.
    *   $TodasCategorias \leftarrow \pi_{categoria}(Livro)$
2.  **Conjunto Ativo ($A$):** Categorias dos livros que foram emprestados no 2º semestre de 2024 (entre 2024-07-01 e 2024-12-31).
    *   $Emp2S2024 \leftarrow \sigma_{dataEmprestimo \ge '2024-07-01' \wedge dataEmprestimo \le '2024-12-31'}(Emprestimo)$
    *   $LivrosEmprestados \leftarrow \pi_{idLivro}(Emp2S2024)$
    *   $CategoriasAtivas \leftarrow \pi_{categoria}(Livro \bowtie LivrosEmprestados)$
3.  **Resultado ($T - A$):**
    *   $Resultado \leftarrow TodasCategorias - CategoriasAtivas$

**Expressão Final:**
$$Emp2S2024 \leftarrow \sigma_{dataEmprestimo \ge '2024-07-01' \wedge dataEmprestimo \le '2024-12-31'}(Emprestimo)$$
$$CategoriasAtivas \leftarrow \pi_{categoria}(Livro \bowtie \pi_{idLivro}(Emp2S2024))$$
$$TodasCategorias \leftarrow \pi_{categoria}(Livro)$$
$$Resultado \leftarrow TodasCategorias - CategoriasAtivas$$
</details>

---

### 🏥 Exercício 2: Sistema de Consultas Hospitalares
**Esquema:**
*   `Medico` (idMedico, nome, especialidade, numCedula)
*   `Paciente` (idPaciente, nome, cidade, dataNascimento)
*   `Consulta` (idMedico, idPaciente, dataConsulta, horaConsulta, custo)

*Nota: Um paciente pode ter consultas com o mesmo médico em datas/horas diferentes.*

---

#### ❓ Pergunta 2a (Modelação/DDL)
Identifique a PK da tabela `Consulta` sabendo que a hora da consulta varia. Escreva a DDL correspondente garantindo que o custo da consulta é sempre superior a zero.

<details>
<summary><b>💡 Ver Resposta (Modelação)</b></summary>

**Chaves:**
*   **PK:** `(idMedico, idPaciente, dataConsulta, horaConsulta)`
*   **FKs:**
    *   `idMedico` refere-se a `Medico(idMedico)`
    *   `idPaciente` refere-se a `Paciente(idPaciente)`

**DDL SQL:**
```sql
CREATE TABLE Consulta (
    idMedico INT,
    idPaciente INT,
    dataConsulta DATE,
    horaConsulta TIME,
    custo DECIMAL(6,2) NOT NULL,
    PRIMARY KEY (idMedico, idPaciente, dataConsulta, horaConsulta),
    FOREIGN KEY (idMedico) REFERENCES Medico(idMedico),
    FOREIGN KEY (idPaciente) REFERENCES Paciente(idPaciente),
    CONSTRAINT chk_custo_positivo CHECK (custo > 0)
);
```
</details>

---

#### ❓ Pergunta 2b (SQL)
Identifique os Médicos (idMedico e nome) que realizaram consultas a mais de 10 pacientes **diferentes** no ano de 2024.

<details>
<summary><b>💡 Ver Resposta (SQL)</b></summary>

**Metodologia:**
1.  **Tabelas:** `Medico` (para nome e id) e `Consulta` (para data e paciente). `INNER JOIN` entre ambas.
2.  **Filtro temporal:** 2024.
3.  **Agrupamento:** Por médico.
4.  **Agregação:** Mais de 10 pacientes *diferentes* $\rightarrow$ `COUNT(DISTINCT idPaciente) > 10`.

**Código SQL:**
```sql
SELECT m.idMedico, m.nome
FROM Medico m
INNER JOIN Consulta c ON m.idMedico = c.idMedico
WHERE c.dataConsulta >= '2024-01-01' AND c.dataConsulta <= '2024-12-31'
GROUP BY m.idMedico, m.nome
HAVING COUNT(DISTINCT c.idPaciente) > 10;
```
</details>

---

#### ❓ Pergunta 2c (Álgebra Relacional)
Quais os Pacientes (idPaciente e nome) que nunca tiveram qualquer consulta na especialidade de "Cardiologia"?

<details>
<summary><b>💡 Ver Resposta (Álgebra Relacional)</b></summary>

**Metodologia (Negação):**
1.  **Universo Total ($T$):** Todos os pacientes.
    *   $TodosPacientes \leftarrow \pi_{idPaciente}(Paciente)$
2.  **Conjunto Ativo ($A$):** Pacientes que tiveram consultas com médicos da especialidade "Cardiologia".
    *   $MedicosCardio \leftarrow \sigma_{especialidade = 'Cardiologia'}(Medico)$
    *   $ConsultasCardio \leftarrow Consulta \bowtie MedicosCardio$
    *   $PacientesAtivos \leftarrow \pi_{idPaciente}(ConsultasCardio)$
3.  **Subtração ($T - A$):**
    *   $PacientesSemCardio \leftarrow TodosPacientes - PacientesAtivos$
4.  **Juntar Detalhes (Nome):** A pergunta pede os Pacientes (idPaciente e nome), por isso temos de resgatar o nome juntando com `Paciente`.
    *   $Resultado \leftarrow \pi_{idPaciente, nome}(PacientesSemCardio \bowtie Paciente)$

**Expressão Final:**
$$MedicosCardio \leftarrow \sigma_{especialidade = 'Cardiologia'}(Medico)$$
$$PacientesAtivos \leftarrow \pi_{idPaciente}(Consulta \bowtie MedicosCardio)$$
$$TodosPacientes \leftarrow \pi_{idPaciente}(Paciente)$$
$$PacientesSemCardio \leftarrow TodosPacientes - PacientesAtivos$$
$$Resultado \leftarrow \pi_{idPaciente, nome}(PacientesSemCardio \bowtie Paciente)$$
</details>

---

### 🎬 Exercício 3: Plataforma de Streaming de Filmes
**Esquema:**
*   `Utilizador` (idUtilizador, nome, pais, tipoPlano)
*   `Filme` (idFilme, titulo, genero, anoLancamento)
*   `Visualizacao` (idUtilizador, idFilme, dataVisualizacao, duracaoMinutos)

---

#### ❓ Pergunta 3a (SQL)
Quais os Países que têm mais de 50 utilizadores **Premium** que visualizaram filmes do género "Documentário" no ano de 2025?

<details>
<summary><b>💡 Ver Resposta (SQL)</b></summary>

**Código SQL:**
```sql
SELECT u.pais, COUNT(DISTINCT u.idUtilizador) AS TotalUtilizadores
FROM Utilizador u
INNER JOIN Visualizacao v ON u.idUtilizador = v.idUtilizador
INNER JOIN Filme f ON v.idFilme = f.idFilme
WHERE u.tipoPlano = 'Premium'
  AND f.genero = 'Documentário'
  AND v.dataVisualizacao >= '2025-01-01'
  AND v.dataVisualizacao <= '2025-12-31'
GROUP BY u.pais
HAVING COUNT(DISTINCT u.idUtilizador) > 50;
```
</details>

---

#### ❓ Pergunta 3b (Álgebra Relacional)
Quais os Filmes (idFilme e titulo) que nunca foram visualizados por utilizadores do país "Brasil"?

<details>
<summary><b>💡 Ver Resposta (Álgebra Relacional)</b></summary>

**Expressão Final:**
$$UtilizadoresBrasil \leftarrow \sigma_{pais = 'Brasil'}(Utilizador)$$
$$FilmesVisualizadosBr \leftarrow \pi_{idFilme}(Visualizacao \bowtie UtilizadoresBrasil)$$
$$TodosFilmes \leftarrow \pi_{idFilme}(Filme)$$
$$FilmesNaoVisualizados \leftarrow TodosFilmes - FilmesVisualizadosBr$$
$$Resultado \leftarrow \pi_{idFilme, titulo}(FilmesNaoVisualizados \bowtie Filme)$$
</details>

---

### 🚗 Exercício 4: Aluguer de Carros (Rent-a-Car)
**Esquema:**
*   `Cliente` (nif, nome, telefone, cartaConducao)
*   `Veiculo` (matricula, marca, modelo, categoria)
*   `Aluguer` (nif, matricula, dataInicio, dataFim, valorTotal)

---

#### ❓ Pergunta 4a (SQL)
Quais as categorias de veículos que tiveram mais de 100 alugueres no ano de 2024?

<details>
<summary><b>💡 Ver Resposta (SQL)</b></summary>

*Nota: Um aluguer representa um registo físico único. Como queremos contar o número de alugueres (ações) e não de veículos distintos, usamos `COUNT(*)` ou `COUNT(matricula)`.*

**Código SQL:**
```sql
SELECT v.categoria, COUNT(*) AS TotalAlugueres
FROM Veiculo v
INNER JOIN Aluguer a ON v.matricula = a.matricula
WHERE a.dataInicio >= '2024-01-01' AND a.dataInicio <= '2024-12-31'
GROUP BY v.categoria
HAVING COUNT(*) > 100;
```
</details>

---

#### ❓ Pergunta 4b (Álgebra Relacional)
Quais os clientes (nif e nome) que nunca alugaram nenhum veículo da categoria "SUV"?

<details>
<summary><b>💡 Ver Resposta (Álgebra Relacional)</b></summary>

**Expressão Final:**
$$VeiculosSUV \leftarrow \sigma_{categoria = 'SUV'}(Veiculo)$$
$$AlugueresSUV \leftarrow Aluguer \bowtie VeiculosSUV$$
$$ClientesSUV \leftarrow \pi_{nif}(AlugueresSUV)$$
$$TodosClientes \leftarrow \pi_{nif}(Cliente)$$
$$ClientesSemSUV \leftarrow TodosClientes - ClientesSUV$$
$$Resultado \leftarrow \pi_{nif, nome}(ClientesSemSUV \bowtie Cliente)$$
</details>

---

## 🚀 Resumo Mental para o Dia do Exame

1.  **Se for SQL com agrupamentos:**
    *   Vê o que é pedido $\rightarrow$ põe no `SELECT` e `GROUP BY`.
    *   Vê as condições de contagem $\rightarrow$ põe no `HAVING`.
    *   Lembra-te do `COUNT(DISTINCT ...)` se a contagem for de entidades (clientes, médicos, sócios) e não de transações.
2.  **Se for Álgebra Relacional com "nunca/não":**
    *   Não inventes seleções complexas com $\neq$.
    *   Faz sempre: **Total - Ativo**.
    *   Projeta sempre o mesmo atributo antes de subtrair!
    *   Se pedir detalhes no fim, faz um `JOIN` com a tabela principal para recuperar o nome/descrição.
