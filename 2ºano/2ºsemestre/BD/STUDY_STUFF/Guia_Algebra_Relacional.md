# 🧮 Guia Completo: Álgebra Relacional

## Como Resolver Qualquer Exercício de Álgebra Relacional (BD — ESTG-IPP)

> Este guia foi construído a partir da análise de **todos os exames recentes** (2023/2024, 2024/2025, Modelos de Recurso 2025/2026) e cobre desde os operadores básicos até à resolução passo a passo de qualquer pergunta de exame.

---

## 📚 Parte 1 — Os Operadores Fundamentais

A Álgebra Relacional é uma **linguagem procedimental** (descreve-se *como* obter os dados). Existem **5 operações primitivas** a partir das quais tudo o resto deriva.

### 1.1 Operações Primitivas

| Operador | Símbolo | O que faz | Exemplo |
|----------|---------|-----------|---------|
| **Seleção** | $\sigma$ | Filtra **linhas** (tuplos) que satisfaçam uma condição | $\sigma_{cidade='Porto'}(Cliente)$ |
| **Projeção** | $\pi$ | Filtra **colunas** (atributos) — remove duplicados | $\pi_{nome, cidade}(Cliente)$ |
| **Produto Cartesiano** | $\times$ | Combina **todas** as linhas de A com **todas** as de B | $Cliente \times Encomenda$ |
| **União** | $\cup$ | Junta as linhas de A e B (devem ter o mesmo esquema) | $ClientesPT \cup ClientesES$ |
| **Diferença** | $-$ | Linhas de A que **não estão** em B | $TodosClientes - ClientesAtivos$ |

### 1.2 Operações Derivadas (compostas)

| Operador | Símbolo | Equivalência | O que faz |
|----------|---------|-------------|-----------|
| **Junção Natural** | $\bowtie$ | $\sigma_{cond}(A \times B)$ | Combina tabelas por atributos com o mesmo nome |
| **Theta Join** | $\bowtie_\theta$ | $\sigma_\theta(A \times B)$ | Junção com qualquer condição ($=$, $>$, $<$, etc.) |
| **Intersecção** | $\cap$ | $A - (A - B)$ | Linhas comuns a A e B |
| **Divisão** | $\div$ | $\pi_X(A) - \pi_X((\pi_X(A) \times B) - A)$ | "Para todos" — entidades que se relacionam com **todos** os elementos de B |

---

## 🔑 Parte 2 — Regras de Ouro (Memorizar!)

### Regra 1: A Projeção ($\pi$) elimina duplicados
$$\pi_{cidade}(Cliente) \text{ → devolve cada cidade UMA só vez}$$

### Regra 2: A Seleção ($\sigma$) usa operadores lógicos
- Conjunção (E): $\wedge$
- Disjunção (OU): $\vee$  
- Negação: $\neg$
- Comparação: $=$, $\neq$, $>$, $<$, $\ge$, $\le$

**Exemplo:** $\sigma_{Data \ge '2025-01-01' \wedge Data \le '2025-03-31'}(OrdemFabrico)$

### Regra 3: Na Junção Natural ($\bowtie$), os atributos comuns devem ter o mesmo nome
Se as tabelas partilham um atributo com o **mesmo nome**, a junção é automática. Se os nomes forem diferentes, usa-se **Theta Join** ou **renomeação** ($\rho$).

### Regra 4: Na Diferença ($-$), os conjuntos devem ter o **mesmo esquema**
> ⚠️ **Erro fatal:** Subtrair conjuntos com atributos diferentes. Antes de subtrair, **projeta sempre o mesmo atributo** de ambos os lados!

### Regra 5: A atribuição ($\leftarrow$) dá nome a resultados intermédios
$$Temp \leftarrow \sigma_{plano = 'VIP'}(Socio)$$
Isto é apenas "guardar" o resultado num nome temporário para o reutilizar.

---

## 🎯 Parte 3 — O Padrão de Negação (O que SAI SEMPRE no Exame!)

### 3.1 Quando usar este padrão?

Quando a pergunta contém palavras como:
- ❌ **"nunca"** — *"secções que nunca tiveram plantações"*
- ❌ **"não"** — *"famílias que não tiveram ordens de fabrico"*
- ❌ **"sem"** — *"aeroportos sem reservas"*
- ❌ **"nenhum"** — *"sócios que nunca se inscreveram em nenhuma aula"*

### 3.2 O Erro Fatal (NÃO fazer isto!)

> 🚫 **ERRADO:** Usar seleções com $\neq$ para resolver negações.  
> Exemplo errado: $\sigma_{destino \neq 'Porto'}(Voo)$ — Isto dá voos para OUTROS destinos, mas **não garante** que Porto nunca recebeu voos!

### 3.3 A Fórmula Universal ⭐

$$\boxed{Resultado = \text{Universo Total (quem PODIA ter feito)} - \text{Conjunto Ativo (quem FEZ)}}$$

### 3.4 Passo a Passo Metodológico

```
PASSO 1 → Definir o UNIVERSO TOTAL (T)
           Projeta o identificador de TODAS as entidades possíveis.

PASSO 2 → Definir o CONJUNTO ATIVO (A)
           Aplica filtros (seleções de data, condições, etc.)
           Faz junções até chegar ao MESMO atributo do Passo 1
           Projeta esse atributo.

PASSO 3 → SUBTRAIR: Resultado = T - A

PASSO 4 → (Opcional) Recuperar dados descritivos
           Se a pergunta pedir nome/detalhes, faz JOIN com a tabela original.
```

---

## 📝 Parte 4 — Todos os Exercícios dos Exames (Resolvidos e Comentados)

---

### 📌 Exercício 1: Secções sem plantações (EN 2023/2024)

**Esquema:**
- `Estufa` (codE, descricao, capacidade, cidade)
- `Secção` (codigoS, tipo, estufa)
- `Plantação` (codP, produto, codS, data_início, data_fim)

**Pergunta:** *Quais as secções que nunca tiveram plantações?*

**Tipo:** Negação simples (sem filtro de data)

#### Resolução:

| Passo | Raciocínio | Expressão |
|-------|------------|-----------|
| **T** | Todas as secções existentes | $TodasSeccoes \leftarrow \pi_{codigoS}(Secção)$ |
| **A** | Secções que aparecem em Plantação | $SeccoesComPlantacao \leftarrow \pi_{codS}(Plantação)$ |
| **T - A** | Subtrair | $Resultado \leftarrow TodasSeccoes - SeccoesComPlantacao$ |

**Expressão final:**
$$SeccoesComPlantacao \leftarrow \pi_{codS}(Plantação)$$
$$TodasSeccoes \leftarrow \pi_{codigoS}(Secção)$$
$$Resultado \leftarrow TodasSeccoes - SeccoesComPlantacao$$

> 💡 **Nota:** Este é o caso mais simples — não há filtro de data nem junções intermédias. Basta projetar o mesmo atributo de ambos os lados e subtrair.

> ⚠️ **Atenção aos nomes!** Em `Secção` o atributo chama-se `codigoS`, em `Plantação` chama-se `codS`. Como os nomes são diferentes, a diferença funciona corretamente porque compara os **valores**, não os nomes.

---

### 📌 Exercício 2: Famílias sem ordens de fabrico no 1º trimestre (EN 2024/2025)

**Esquema:**
- `Cliente` (CódigoCliente, Nome, NIF, DataCriação, Morada, País)
- `OrdemFabrico` (Número, Data, Cliente)
- `Produto` (CódigoProduto, Nome, Familia)
- `OrdemFabricoProduto` (Número, CódigoProduto, Quantidade, DataEntrega)

**Pergunta:** *Quais as famílias de produtos que não tiveram qualquer ordem de fabrico no primeiro trimestre de 2025?*

**Tipo:** Negação com filtro de data + junções intermédias

#### Resolução:

| Passo | Raciocínio | Expressão |
|-------|------------|-----------|
| **Filtro** | Selecionar ordens do 1º trim. 2025 | $OrdensT1 \leftarrow \sigma_{Data \ge '2025-01-01' \wedge Data \le '2025-03-31'}(OrdemFabrico)$ |
| **Junção** | Ligar ordens filtradas aos produtos | $ProdutosT1 \leftarrow \pi_{CódigoProduto}(OrdemFabricoProduto \bowtie OrdensT1)$ |
| **A** | Obter as famílias desses produtos | $FamiliasComOrdem \leftarrow \pi_{Familia}(Produto \bowtie ProdutosT1)$ |
| **T** | Todas as famílias existentes | $TodasFamilias \leftarrow \pi_{Familia}(Produto)$ |
| **T - A** | Subtrair | $Resultado \leftarrow TodasFamilias - FamiliasComOrdem$ |

**Expressão final:**
$$OrdensT1 \leftarrow \sigma_{Data \ge '2025-01-01' \wedge Data \le '2025-03-31'}(OrdemFabrico)$$
$$ProdutosT1 \leftarrow \pi_{CódigoProduto}(OrdemFabricoProduto \bowtie OrdensT1)$$
$$FamiliasComOrdem \leftarrow \pi_{Familia}(Produto \bowtie ProdutosT1)$$
$$TodasFamilias \leftarrow \pi_{Familia}(Produto)$$
$$Resultado \leftarrow TodasFamilias - FamiliasComOrdem$$

> 💡 **Nota:** Aqui a dificuldade está em "navegar" de `OrdemFabrico` até `Familia`. O caminho é: `OrdemFabrico` → `OrdemFabricoProduto` (junção por Número) → `Produto` (junção por CódigoProduto) → projetar `Familia`.

---

### 📌 Exercício 3: Aeroportos nunca destino de voos com reservas (Modelo Recurso 1)

**Esquema:**
- `Aeroporto` (codIATA, nome, cidade)
- `Voo` (numVoo, origem, destino, horaPartida, horaChegada)
- `Passageiro` (codPass, nome, email, pais)
- `Reserva` (codReserva, codPass, numVoo, dataViagem, classe, preco)

**Pergunta:** *Quais os aeroportos que nunca foram destino de nenhum voo com reservas?*

**Tipo:** Negação com junção para filtrar "com reservas"

#### Resolução:

| Passo | Raciocínio | Expressão |
|-------|------------|-----------|
| **Junção** | Voos que têm reservas | $VoosComReserva \leftarrow Voo \bowtie_{Voo.numVoo = Reserva.numVoo} Reserva$ |
| **A** | Destinos desses voos | $DestinosComReserva \leftarrow \pi_{destino}(VoosComReserva)$ |
| **T** | Todos os aeroportos | $TodosAeroportos \leftarrow \pi_{codIATA}(Aeroporto)$ |
| **T - A** | Subtrair | $Resultado \leftarrow TodosAeroportos - DestinosComReserva$ |

**Expressão final:**
$$TodosAeroportos \leftarrow \pi_{codIATA}(Aeroporto)$$
$$VoosComReserva \leftarrow Voo \bowtie_{Voo.numVoo = Reserva.numVoo} Reserva$$
$$DestinosComReserva \leftarrow \pi_{destino}(VoosComReserva)$$
$$Resultado \leftarrow TodosAeroportos - DestinosComReserva$$

> 💡 **Nota:** A subtileza aqui é que não basta verificar se o aeroporto é destino de algum voo — precisa de ser destino de voos **com reservas**. Por isso juntamos `Voo` com `Reserva` primeiro.

---

### 📌 Exercício 4: Sócios VIP sem inscrições em Spinning (Modelo Recurso 2)

**Esquema:**
- `Socio` (numSocio, nome, dataNasc, plano)
- `Instrutor` (codInst, nome, especialidade)
- `Aula` (codAula, modalidade, diaSemana, horario, codInst)
- `Inscricao` (numSocio, codAula, dataInscricao, presenca)

**Pergunta:** *Quais os sócios com plano VIP que nunca se inscreveram em nenhuma aula de Spinning?*

**Tipo:** Negação com **pré-filtro** no universo total (apenas VIP)

#### Resolução:

| Passo | Raciocínio | Expressão |
|-------|------------|-----------|
| **T** | Sócios VIP (universo restrito!) | $SociosVIP \leftarrow \pi_{numSocio}(\sigma_{plano = 'VIP'}(Socio))$ |
| **Filtro** | Aulas de Spinning | $AulasSpinning \leftarrow \sigma_{modalidade = 'Spinning'}(Aula)$ |
| **A** | Sócios inscritos em Spinning | $SociosComSpinning \leftarrow \pi_{numSocio}(Inscricao \bowtie AulasSpinning)$ |
| **T - A** | Subtrair | $Resultado \leftarrow SociosVIP - SociosComSpinning$ |

**Expressão final:**
$$SociosVIP \leftarrow \pi_{numSocio}(\sigma_{plano = 'VIP'}(Socio))$$
$$AulasSpinning \leftarrow \sigma_{modalidade = 'Spinning'}(Aula)$$
$$SociosComSpinning \leftarrow \pi_{numSocio}(Inscricao \bowtie AulasSpinning)$$
$$Resultado \leftarrow SociosVIP - SociosComSpinning$$

> 💡 **Nota Importante:** O universo total aqui **NÃO são todos os sócios** — são apenas os VIP! A pergunta pede "sócios VIP que nunca...". Tens de aplicar o filtro `plano = 'VIP'` **antes** de definir T.

---

## 🧠 Parte 5 — Árvore de Decisão (Que padrão usar?)

```
A pergunta pede para ENCONTRAR entidades que...
│
├── ✅ FIZERAM algo (positivo)?
│   └── Usa: σ (seleção) + ⋈ (junção) + π (projeção)
│   └── Exemplo: "Quais os clientes que fizeram encomendas em 2024?"
│
├── ❌ NÃO FIZERAM / NUNCA fizeram algo (negação)?
│   └── Usa: T - A (Diferença de Conjuntos)
│   │
│   ├── Há filtro de data/condição?
│   │   ├── SIM → Aplica σ ANTES de construir A
│   │   └── NÃO → Constrói A diretamente com π
│   │
│   ├── A pergunta restringe o universo? (ex: "sócios VIP que nunca...")
│   │   ├── SIM → Aplica σ no universo T também
│   │   └── NÃO → T = π de toda a tabela
│   │
│   └── A pergunta pede detalhes (nome, descrição)?
│       ├── SIM → Faz ⋈ final para recuperar dados
│       └── NÃO → Resultado da subtração é suficiente
│
└── 🔄 TODOS fizeram algo (universal)?
    └── Usa: ÷ (Divisão)
    └── Exemplo: "Clientes que compraram TODOS os produtos"
```

---

## 🏋️ Parte 6 — Exercícios de Treino (com Resolução)

### Exercício A: Sistema de Empréstimos

**Esquema:**
- `Leitor` (idLeitor, nome, cidade, dataAdesao)
- `Livro` (idLivro, titulo, categoria, preco)
- `Emprestimo` (idLeitor, idLivro, dataEmprestimo, dataDevolucao)

**Pergunta:** *Quais as categorias de livros que não registaram qualquer empréstimo no 2º semestre de 2024?*

<details>
<summary><b>💡 Ver Resolução</b></summary>

$$Emp2S2024 \leftarrow \sigma_{dataEmprestimo \ge '2024-07-01' \wedge dataEmprestimo \le '2024-12-31'}(Emprestimo)$$
$$CategoriasAtivas \leftarrow \pi_{categoria}(Livro \bowtie \pi_{idLivro}(Emp2S2024))$$
$$TodasCategorias \leftarrow \pi_{categoria}(Livro)$$
$$Resultado \leftarrow TodasCategorias - CategoriasAtivas$$

**Raciocínio:**
1. Filtrar empréstimos do 2º semestre 2024
2. Juntar com Livro para obter a categoria
3. Projetar todas as categorias existentes
4. Subtrair
</details>

---

### Exercício B: Consultas Hospitalares

**Esquema:**
- `Medico` (idMedico, nome, especialidade, numCedula)
- `Paciente` (idPaciente, nome, cidade, dataNascimento)
- `Consulta` (idMedico, idPaciente, dataConsulta, horaConsulta, custo)

**Pergunta:** *Quais os Pacientes (idPaciente e nome) que nunca tiveram qualquer consulta na especialidade de "Cardiologia"?*

<details>
<summary><b>💡 Ver Resolução</b></summary>

$$MedicosCardio \leftarrow \sigma_{especialidade = 'Cardiologia'}(Medico)$$
$$PacientesAtivos \leftarrow \pi_{idPaciente}(Consulta \bowtie MedicosCardio)$$
$$TodosPacientes \leftarrow \pi_{idPaciente}(Paciente)$$
$$PacientesSemCardio \leftarrow TodosPacientes - PacientesAtivos$$
$$Resultado \leftarrow \pi_{idPaciente, nome}(PacientesSemCardio \bowtie Paciente)$$

**Raciocínio:**
1. Filtrar médicos de Cardiologia
2. Juntar com Consulta → projetar pacientes que consultaram esses médicos
3. Projetar todos os pacientes
4. Subtrair
5. **Passo extra:** a pergunta pede nome → JOIN final para recuperar dados
</details>

---

### Exercício C: Plataforma de Streaming

**Esquema:**
- `Utilizador` (idUtilizador, nome, pais, tipoPlano)
- `Filme` (idFilme, titulo, genero, anoLancamento)
- `Visualizacao` (idUtilizador, idFilme, dataVisualizacao, duracaoMinutos)

**Pergunta:** *Quais os Filmes (idFilme e titulo) que nunca foram visualizados por utilizadores do país "Brasil"?*

<details>
<summary><b>💡 Ver Resolução</b></summary>

$$UtilizadoresBrasil \leftarrow \sigma_{pais = 'Brasil'}(Utilizador)$$
$$FilmesVisualizadosBr \leftarrow \pi_{idFilme}(Visualizacao \bowtie UtilizadoresBrasil)$$
$$TodosFilmes \leftarrow \pi_{idFilme}(Filme)$$
$$FilmesNaoVisualizados \leftarrow TodosFilmes - FilmesVisualizadosBr$$
$$Resultado \leftarrow \pi_{idFilme, titulo}(FilmesNaoVisualizados \bowtie Filme)$$
</details>

---

### Exercício D: Rent-a-Car

**Esquema:**
- `Cliente` (nif, nome, telefone, cartaConducao)
- `Veiculo` (matricula, marca, modelo, categoria)
- `Aluguer` (nif, matricula, dataInicio, dataFim, valorTotal)

**Pergunta:** *Quais os clientes (nif e nome) que nunca alugaram nenhum veículo da categoria "SUV"?*

<details>
<summary><b>💡 Ver Resolução</b></summary>

$$VeiculosSUV \leftarrow \sigma_{categoria = 'SUV'}(Veiculo)$$
$$ClientesSUV \leftarrow \pi_{nif}(Aluguer \bowtie VeiculosSUV)$$
$$TodosClientes \leftarrow \pi_{nif}(Cliente)$$
$$ClientesSemSUV \leftarrow TodosClientes - ClientesSUV$$
$$Resultado \leftarrow \pi_{nif, nome}(ClientesSemSUV \bowtie Cliente)$$
</details>

---

## 🚀 Parte 7 — Resumo Mental para o Dia do Exame

### Checklist Rápida (30 segundos):

1. **Lê a pergunta** → procura palavras-chave: "nunca", "não", "sem", "nenhum"
2. **Se encontraste** → É padrão de **Negação** → Usa **T - A**
3. **Define T** → Projeta o ID de TODAS as entidades possíveis
   - Se a pergunta restringe (ex: "sócios VIP"), aplica $\sigma$ no T
4. **Define A** → "Navega" pelas tabelas até chegar ao mesmo atributo de T
   - Se há filtro de data → aplica $\sigma$ primeiro
   - Se precisas de "atravessar" tabelas → faz $\bowtie$
   - No final → $\pi$ do mesmo atributo que usaste em T
5. **Subtrai** → $R = T - A$
6. **Se pedem nome/detalhes** → Faz $\bowtie$ final com a tabela original

### Símbolos para Copiar Rapidamente:
```
σ  π  ×  ∪  -  ⋈  ←  ∧  ∨  ≥  ≤  ≠  ÷
```

### A Fórmula que Resolve 95% dos Exercícios:
$$\pi_{atributo}(\text{TabelaTotal}) - \pi_{atributo}(\text{TabelaEvento} \bowtie \sigma_{condição}(\text{TabelaFiltro}))$$
