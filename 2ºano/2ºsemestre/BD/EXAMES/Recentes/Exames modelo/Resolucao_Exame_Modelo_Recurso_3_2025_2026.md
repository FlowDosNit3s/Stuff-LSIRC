# 📚 Resolução do Exame Modelo de Recurso — Bases de Dados (Modelo 3)

**📅 Ano Letivo:** 2025/2026 | **📆 Época:** Recurso (Modelo)  
**🎓 Curso:** Engenharia Informática / Segurança Informática em Redes de Computadores  
**🏫 Instituição:** P.PORTO — ESTG  
**📖 Unidade Curricular:** Bases de Dados

---

## 1. ⚙️ Conceitos Fundamentais (2 val.)

> ❓ **Pergunta 1:** Defina os seguintes termos no contexto de sistemas de bases de dados:
> - **Base de Dados**
> - **Sistema de Gestão de Bases de Dados (SGBD)**, identificando os seus principais componentes
> - **Metadados**

**✍️ Resposta:**
- **Base de Dados:** É um conjunto organizado de dados logicamente relacionados entre si, armazenados de forma persistente, estruturada e com a menor redundância possível, concebido para facilitar o acesso, a manipulação, a gestão e a atualização dos dados por múltiplos utilizadores ou aplicações.
- **Sistema de Gestão de Bases de Dados (SGBD):** É uma coleção de programas de software (um motor de base de dados) que disponibiliza aos utilizadores as ferramentas necessárias para criar, gerir, aceder, controlar e manter bases de dados. Os seus componentes principais incluem:
  - *Motor de armazenamento:* Gere a escrita e leitura física dos dados no disco.
  - *Linguagem de Definição de Dados (DDL):* Permite definir a estrutura das tabelas e esquemas.
  - *Linguagem de Manipulação de Dados (DML):* Permite a inserção, consulta, modificação e eliminação de dados.
  - *Controlo de transações:* Garante o cumprimento das propriedades ACID.
  - *Controlo de acessos e segurança:* Define permissões e perfis de utilizadores.
  - *Interface com o utilizador e utilitários:* Permite a administração e monitorização do sistema.
- **Metadados:** São vulgarmente definidos como "dados sobre dados". Num SGBD relacional, os metadados descrevem a própria estrutura e características da base de dados (esquema). São armazenados no Dicionário de Dados (ou *System Catalog*) e incluem informações sobre os nomes das tabelas, colunas, tipos de dados, chaves primárias e estrangeiras, índices e restrições de integridade definidas.

---

## 2. 📁 Abordagem de Ficheiros vs SGBD (2 val.)

> ❓ **Pergunta 2:** Descreva em que situações será preferível a abordagem de Sistemas de Ficheiros comparativamente à abordagem de Sistemas de Bases de Dados, tendo em atenção as principais desvantagens da utilização de um SGBD.

**✍️ Resposta:**
A utilização de um SGBD nem sempre é a solução ideal, sendo a abordagem baseada em Sistemas de Ficheiros preferível nas seguintes situações:
- **Aplicações muito pequenas ou de uso pessoal:** Onde o volume de dados é extremamente reduzido e a complexidade do sistema não justifica a instalação de um SGBD.
- **Sistemas com acesso de utilizador único:** Onde não existe necessidade de partilha de dados em tempo real nem de controlo de concorrência.
- **Recursos computacionais limitados:** Dispositivos embebidos, microcontroladores ou sistemas móveis com pouca memória RAM, capacidade de processamento (CPU) ou armazenamento em disco.
- **Inexistência de requisitos de segurança complexos:** Quando os dados não são sensíveis e não exigem controlo de acessos rigoroso.
- **Operações simples e predefinidas:** Onde o acesso aos dados é linear e não são necessárias consultas complexas (queries ad-hoc) ou cruzamento de dados de múltiplas fontes.

Essas preferências justificam-se pelas desvantagens associadas à adoção de um SGBD:
- **Elevada complexidade:** Exige conhecimento técnico especializado para o desenho, implementação e administração.
- **Consumo elevado de recursos:** Requer hardware robusto devido à ocupação de memória e processamento do motor do SGBD.
- **Custos financeiros elevados:** Custos associados ao licenciamento de software (SGBD proprietários) e à formação de utilizadores e administradores.
- **Tempo de configuração inicial:** O planeamento lógico, a modelação e a criação física do esquema exigem um esforço temporal muito maior.

---

## 3. 🌐 Sublinguagens de Dados (2 val.)

> ❓ **Pergunta 3:** O que são sublinguagens de dados? Identifique e descreva sucintamente as quatro principais sublinguagens de dados no SQL (DDL, DML, DCL, TCL), apresentando exemplos de comandos para cada uma delas e explicando as diferenças entre DDL e DML.

**✍️ Resposta:**
As **sublinguagens de dados** são subconjuntos de comandos dentro de uma linguagem de base de dados (como o SQL), agrupados de acordo com a função específica que desempenham no sistema. As quatro principais sublinguagens são:
1. **DDL (Data Definition Language):** Usada para definir, modificar ou eliminar a estrutura física e lógica da base de dados (tabelas, esquemas, visões, índices). Exemplos: `CREATE TABLE`, `ALTER TABLE`, `DROP VIEW`, `TRUNCATE`.
2. **DML (Data Manipulation Language):** Usada para interagir e manipular os dados que residem dentro das tabelas criadas. Permite a pesquisa e modificação de registos. Exemplos: `SELECT`, `INSERT`, `UPDATE`, `DELETE`.
3. **DCL (Data Control Language):** Usada para controlar as permissões de acesso e a segurança de utilizadores e papéis na base de dados. Exemplos: `GRANT` (conceder permissão), `REVOKE` (retirar permissão).
4. **TCL (Transaction Control Language):** Usada para gerir transações de dados, assegurando a consistência e a atomicidade do sistema. Exemplos: `COMMIT` (confirmar transação), `ROLLBACK` (reverter transação), `SAVEPOINT`.

### Diferenças entre DDL e DML:
- **Função principal:** A DDL atua sobre os metadados (define o "contentor" ou a estrutura física); a DML atua sobre os dados reais (manipula o "conteúdo" dentro das tabelas).
- **Efeito no sistema:** Comandos DDL alteram o catálogo do sistema e a estrutura lógica global; comandos DML afetam apenas os registos individuais armazenados nas tabelas.
- **Transacionalidade:** A DDL, na maioria dos SGBDs (como MySQL ou Oracle), causa um commit implícito e não permite operações de *rollback* (são alterações definitivas); a DML permite operações de *commit* e *rollback* explícitas para manter a consistência transacional.

---

## 4. 🔏 Esquemas de Bases de Dados ANSI/SPARC (2 val.)

> ❓ **Pergunta 4:** Explique o conceito de *Database Schema* e descreva detalhadamente os três tipos de esquema definidos na arquitetura de três níveis ANSI/SPARC (Esquema Externo, Esquema Conceptual e Esquema Interno).

**✍️ Resposta:**
Um **Database Schema** (esquema de base de dados) representa a descrição formal e a definição lógica da estrutura de uma base de dados. Não contém dados reais, mas sim a estrutura lógicas e as restrições que determinam como os dados se organizam (as tabelas, atributos, chaves primárias e estrangeiras, relações e tipos de dados).

A arquitetura de três níveis ANSI/SPARC divide a descrição da base de dados em três níveis de esquema para garantir a independência de dados:
1. **Esquema Externo (ou de Visão):** É o nível mais alto, que descreve as diferentes visões ou subconjuntos da base de dados apresentados a utilizadores específicos ou aplicações. Cada utilizador apenas visualiza e manipula os dados que lhe competem (por meio de `VIEWs`), ocultando a complexidade dos restantes dados.
2. **Esquema Conceptual (ou Lógico Global):** É o nível intermédio e central da arquitetura. Representa a visão global e a estrutura lógica de toda a base de dados para a organização. Descreve todas as entidades, atributos, relações e regras de integridade (chaves, CHECKs), omitindo detalhes físicos de armazenamento.
3. **Esquema Interno (ou Físico):** É o nível mais baixo, que descreve a organização e representação física dos dados no suporte de armazenamento. Detalha como as tabelas são gravadas em ficheiros, as estruturas de indexação (B-Trees), os tamanhos dos registos, compressão de dados e caminhos de acesso físico aos dados.

---

## 5. 🧮 Operações da Álgebra Relacional (2 val.)

> ❓ **Pergunta 5:** Defina as cinco operações básicas/principais da Álgebra Relacional. Adicionalmente, demonstre matematicamente como as operações derivadas de Junção (⨝) e Interseção (∩) podem ser expressas através destas cinco operações básicas.

**✍️ Resposta:**
As cinco operações básicas e primitivas da Álgebra Relacional, a partir das quais todas as outras são derivadas, são:
1. **Seleção ($\sigma$):** Operação unária que filtra linhas (tuplos) de uma relação com base numa condição lógica (ex: $\sigma_{idade > 18}(Cliente)$).
2. **Projeção ($\pi$):** Operação unária que seleciona colunas (atributos) específicas de uma relação, eliminando automaticamente quaisquer tuplos duplicados resultantes (ex: $\pi_{nome, nif}(Cliente)$).
3. **União ($\cup$):** Operação binária que combina os tuplos de duas relações compatíveis (que possuem o mesmo número de colunas e domínios compatíveis), devolvendo os tuplos pertencentes a pelo menos uma das tabelas.
4. **Diferença de Conjuntos ($-$):** Operação binária que, dadas duas relações compatíveis $R$ e $S$, devolve os tuplos que existem em $R$ mas que não estão presentes em $S$.
5. **Produto Cartesiano ($\times$):** Operação binária que combina cada tuplo de uma relação com todos os tuplos de outra relação, resultando num conjunto cujo grau é a soma dos graus das relações originais.

### Demonstração das Operações Derivadas:
- **Junção ($\bowtie$):** Combina dados de duas relações com base numa condição de comparação comum. É expressa pelo Produto Cartesiano ($\times$) seguido de uma Seleção ($\sigma$):
  $$R \bowtie_{cond} S \equiv \sigma_{cond}(R \times S)$$
- **Interseção ($\cap$):** Devolve apenas os tuplos que existem em simultâneo nas duas relações compatíveis $R$ e $S$. É expressa através da Diferença de Conjuntos ($-$), subtraindo a diferença de $R$ e $S$ da própria relação $R$:
  $$R \cap S \equiv R - (R - S)$$

---

## 6. ⚠️ Anomalias de Atualização (2 val.)

> ❓ **Pergunta 6:** Descreva os três tipos de anomalias de atualização (inserção, eliminação e modificação) que podem ocorrer numa relação que contém dados redundantes, fornecendo exemplos para ilustrar cada uma delas.

**✍️ Resposta:**
As anomalias de atualização ocorrem em esquemas de bases de dados mal estruturados que contêm redundâncias excessivas (dados repetidos), dificultando a manutenção da consistência dos dados. Os três tipos são:
- **Anomalia de Inserção:** Ocorre quando é impossível introduzir uma determinada informação na base de dados devido à falta de outros dados associados na mesma relação.
  - *Exemplo:* Numa tabela que junta clientes e encomendas, `Fatura(NIF_Cliente, Nome, NumEncomenda, Data)`, se quisermos registar um novo cliente que ainda não efetuou nenhuma encomenda, não o poderemos fazer, pois `NumEncomenda` faz parte da chave primária composta e não aceita valores nulos (`NULL`).
- **Anomalia de Eliminação:** Ocorre quando a remoção de um determinado registo provoca, inadvertidamente, a perda irreversível de outra informação útil que estava incorretamente agregada ao mesmo registo.
  - *Exemplo:* Usando o caso anterior, se eliminarmos a única encomenda feita pelo cliente "Ricardo Sousa", acabamos por apagar também o registo do próprio cliente do sistema (Nome e NIF), pois residiam na mesma linha de dados física.
- **Anomalia de Modificação (ou Atualização):** Ocorre quando a alteração de um dado exige que o utilizador modifique múltiplas linhas redundantes em simultâneo. Caso alguma linha não seja atualizada, gera-se inconsistência de dados.
  - *Exemplo:* Se o cliente "Ana Pereira" morar na mesma rua e a sua morada estiver replicada em 20 encomendas diferentes na tabela única, e ela mudar de residência, teremos de efetuar a atualização nas 20 linhas. Se atualizarmos apenas 19, a base de dados ficará num estado inconsistente, fornecendo moradas diferentes para o mesmo cliente.

---

## 7. 📋 Exercício de Normalização de Fatura (3 val.)

> ❓ **Pergunta 7:** Normalização da fatura da estadia do Grand Plaza Hotel.

**✍️ Resposta:**

### Identificação dos Atributos

Identificamos os seguintes atributos a partir do documento da fatura:

| Letra | Atributo |
|:---:|:---|
| **A** | NIF_Hotel |
| **B** | Nome_Hotel |
| **C** | Morada_Hotel |
| **D** | CodPostal_Hotel |
| **E** | NumFatura |
| **F** | DataEmissao |
| **G** | HoraEmissao |
| **H** | ATCUD |
| **I** | QuartoReservado |
| **J** | NIF_Hospede |
| **K** | Nome_Hospede |
| **L** | Morada_Hospede |
| **M** | CodPostal_Hospede |
| **N** | DataInicio |
| **O** | DataFim |
| **P** | NumNoites |
| **Q** | RefConsumo |
| **R** | DescricaoConsumo |
| **S** | LocalConsumo |
| **T** | Quantidade |
| **U** | PrecoUnitario |
| **V** | TaxaIVA_Artigo |
| **W** | SubtotalLinha |
| **X** | TotalFinal |
| **Y** | MetodoPagamento |
| **Z** | TaxaIVA_Resumo |
| **AA** | Incidencia_IVA |
| **AB** | Valor_IVA |

---

### Forma Não Normalizada (FNN)
Na FNN todos os atributos encontram-se numa relação única com dois grupos repetitivos (consumos e resumo de IVA):

```text
Fatura_FNN(E, A, B, C, D, F, G, H, I, J, K, L, M, N, O, P, X, Y,
           [Q, R, S, T, U, V, W],
           [Z, AA, AB])
```

---

### 1️⃣ Primeira Forma Normal (1FN)
> **Definição:** Uma relação está na 1FN se e só se todos os seus atributos contiverem apenas valores atómicos (sem grupos repetidos ou multivalor).

Para eliminar os grupos repetitivos, criamos uma chave composta que identifique de forma única cada linha da fatura e cada linha de resumo de impostos.

```text
Fatura_1FN(NumFatura, RefConsumo, TaxaIVA_Resumo,
           NIF_Hotel, Nome_Hotel, Morada_Hotel, CodPostal_Hotel,
           DataEmissao, HoraEmissao, ATCUD, QuartoReservado, NIF_Hospede,
           Nome_Hospede, Morada_Hospede, CodPostal_Hospede, DataInicio,
           DataFim, NumNoites, DescricaoConsumo, LocalConsumo, Quantidade,
           PrecoUnitario, TaxaIVA_Artigo, SubtotalLinha, TotalFinal,
           MetodoPagamento, Incidencia_IVA, Valor_IVA)
           
PK: (NumFatura, RefConsumo, TaxaIVA_Resumo)
```

**Dependências Funcionais (DF) identificadas:**
- $NumFatura \rightarrow NIF\_Hotel, Nome\_Hotel, Morada\_Hotel, CodPostal\_Hotel, DataEmissao, HoraEmissao, ATCUD, QuartoReservado, NIF\_Hospede, Nome\_Hospede, Morada\_Hospede, CodPostal\_Hospede, DataInicio, DataFim, NumNoites, TotalFinal, MetodoPagamento$
- $NumFatura, RefConsumo \rightarrow Quantidade, SubtotalLinha$
- $RefConsumo \rightarrow DescricaoConsumo, LocalConsumo, PrecoUnitario, TaxaIVA\_Artigo$
- $NumFatura, TaxaIVA\_Resumo \rightarrow Incidencia\_IVA, Valor\_IVA$
- $NIF\_Hotel \rightarrow Nome\_Hotel, Morada\_Hotel, CodPostal\_Hotel$
- $NIF\_Hospede \rightarrow Nome\_Hospede, Morada\_Hospede, CodPostal\_Hospede$

---

### 2️⃣ Segunda Forma Normal (2FN)
> **Definição:** Uma relação está na 2FN se estiver na 1FN e todos os atributos não-chave dependerem totalmente da chave primária (ausência de dependências parciais).

Decompondo a relação com base nas dependências parciais detetadas:

```text
Cabecalho_2FN(NumFatura, NIF_Hotel, Nome_Hotel, Morada_Hotel, CodPostal_Hotel,
              DataEmissao, HoraEmissao, ATCUD, QuartoReservado, NIF_Hospede,
              Nome_Hospede, Morada_Hospede, CodPostal_Hospede, DataInicio,
              DataFim, NumNoites, TotalFinal, MetodoPagamento)
    PK: NumFatura

Consumo_2FN(RefConsumo, DescricaoConsumo, LocalConsumo, PrecoUnitario, TaxaIVA_Artigo)
    PK: RefConsumo

LinhaFatura_2FN(NumFatura, RefConsumo, Quantidade, SubtotalLinha)
    PK: (NumFatura, RefConsumo)

ResumoIVA_2FN(NumFatura, TaxaIVA_Resumo, Incidencia_IVA, Valor_IVA)
    PK: (NumFatura, TaxaIVA_Resumo)
```

---

### 3️⃣ Terceira Forma Normal (3FN)
> **Definição:** Uma relação está na 3FN se estiver na 2FN e nenhum atributo não-chave depender transitivamente de uma chave candidata (ausência de dependências transitivas).

Detetamos as seguintes dependências transitivas na tabela `Cabecalho_2FN`:
- $NIF\_Hotel \rightarrow Nome\_Hotel, Morada\_Hotel, CodPostal\_Hotel$ (via NumFatura)
- $NIF\_Hospede \rightarrow Nome\_Hospede, Morada\_Hospede, CodPostal\_Hospede$ (via NumFatura)

Extraindo estas tabelas para as suas próprias relações, obtemos o **Esquema Relacional Final (3FN)**:

```text
Hotel(NIF_Hotel, Nome_Hotel, Morada_Hotel, CodPostal_Hotel)
    PK: NIF_Hotel

Hospede(NIF_Hospede, Nome_Hospede, Morada_Hospede, CodPostal_Hospede)
    PK: NIF_Hospede

Consumo(RefConsumo, DescricaoConsumo, LocalConsumo, PrecoUnitario, TaxaIVA)
    PK: RefConsumo

Fatura(NumFatura, DataEmissao, HoraEmissao, ATCUD, QuartoReservado, 
       NIF_Hotel, NIF_Hospede, DataInicio, DataFim, NumNoites, TotalFinal, MetodoPagamento)
    PK: NumFatura
    FK: NIF_Hotel → Hotel(NIF_Hotel)
    FK: NIF_Hospede → Hospede(NIF_Hospede)

LinhaFatura(NumFatura, RefConsumo, Quantidade, SubtotalLinha)
    PK: (NumFatura, RefConsumo)
    FK: NumFatura → Fatura(NumFatura)
    FK: RefConsumo → Consumo(RefConsumo)

ResumoIVA(NumFatura, TaxaIVA, Incidencia_IVA, Valor_IVA)
    PK: (NumFatura, TaxaIVA)
    FK: NumFatura → Fatura(NumFatura)
```

---

## 8. 📐 Modelação, SQL e Álgebra Relacional (5 val.)

### ⚙️ a) Chave primária e chaves estrangeiras da tabela OrdemReparacao (1 val.)

- **Chave Primária (PK):** `codOrdem`.
  - *Justificação:* Identifica unicamente e de forma inequívoca cada ordem de reparação registada no sistema.
- **Chaves Estrangeiras (FK):**
  - `numSerie` que referencia `Dispositivo(numSerie)`.
    - *Justificação:* Garante a integridade referencial, impedindo a criação de uma ordem de reparação para um dispositivo inexistente.
  - `codTecnico` que referencia `Tecnico(codTecnico)`.
    - *Justificação:* Associa a ordem a um técnico válido, impedindo a atribuição de reparações a técnicos não registados.

---

### 💻 b) SQL (2 val.)

Pretende-se saber os clientes "Particular" com mais de 3 ordens "Concluído" em dispositivos "Apple" em 2026.

```sql
SELECT C.nome, C.nif
FROM Cliente C
INNER JOIN Dispositivo D ON C.codCliente = D.codCliente
INNER JOIN OrdemReparacao O ON D.numSerie = O.numSerie
WHERE C.tipoCliente = 'Particular'
  AND D.marca = 'Apple'
  AND O.estado = 'Concluído'
  AND O.dataInicio >= '2026-01-01'
  AND O.dataInicio <= '2026-12-31'
GROUP BY C.codCliente, C.nome, C.nif
HAVING COUNT(O.codOrdem) > 3;
```

*Nota:* O agrupamento inclui `C.codCliente` para garantir a distinção inequívoca de clientes com o mesmo nome e NIF.

---

### 📐 c) Álgebra Relacional (2 val.)

Pretende-se identificar os técnicos que **nunca** iniciaram nenhuma ordem de reparação para dispositivos da categoria "Smartphones".

Utilizamos o padrão de negação ($T - A$):
1. Projetamos o conjunto total de técnicos ($T$).
2. Selecionamos as ordens de reparação de dispositivos da categoria "Smartphones" ($A$) e projetamos os seus técnicos.
3. Efetuamos a diferença de conjuntos.

$$TodosTecnicos \leftarrow \pi_{codTecnico, nome, nivelCertificacao}(Tecnico)$$

$$Smartphones \leftarrow \sigma_{categoria = 'Smartphones'}(Dispositivo)$$

$$RepSmartphones \leftarrow OrdemReparacao \bowtie_{OrdemReparacao.numSerie = Smartphones.numSerie} Smartphones$$

$$TecnicosComSmartphones \leftarrow \pi_{codTecnico}(RepSmartphones)$$

$$TecnicosSemSmartphones \leftarrow \pi_{codTecnico}(Tecnico) - TecnicosComSmartphones$$

$$Resultado \leftarrow TodosTecnicos \bowtie TecnicosSemSmartphones$$
