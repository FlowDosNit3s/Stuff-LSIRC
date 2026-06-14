# 🎯 BD — TOP 10 Perguntas para Exame (Prioridade Máxima)

> Estas 10 perguntas cobrem ~70-80% da componente teórica dos exames de BD.
> Ordenadas por frequência de aparecimento nos exames (2004–2025).

---

## ⭐⭐⭐ PERGUNTA 1 — Integridade Referencial + ON DELETE / ON UPDATE
**Saiu em: 6+ exames incluindo 2024/2025, 2022/2023, EN2021**

### Pergunta:
Explique o que entende por Integridade Referencial. Identifique e descreva quais as ações que se podem utilizar nas subcláusulas ON DELETE e ON UPDATE.

### Resposta:
A **INTEGRIDADE REFERENCIAL** preserva as relações definidas entre tabelas quando linhas são criadas ou excluídas.

As subcláusulas que podem ser usadas ON DELETE e em ON UPDATE são:

- **CASCADE**: apaga a linha da tabela pai e linhas correspondentes das tabelas filhas, e assim sucessivamente em cascata.
- **SET NULL**: apaga a linha da tabela pai e muda todas as colunas FK na tabela filha para NULL. Só é válido se as colunas FK não estiverem a NOT NULL.
- **SET DEFAULT**: apaga a linha da tabela pai e muda cada componente da FK da tabela filha para o valor default especificado. Só é válido se houver um valor DEFAULT especificado para as colunas FK.
- **NO ACTION**: rejeita a operação da tabela pai. É o comportamento por defeito (Default).

---

## ⭐⭐⭐ PERGUNTA 2 — Normalização: Objetivos e Impacto no Desempenho
**Saiu em: 5+ exames incluindo 2024/2025**

### Pergunta:
No contexto do modelo relacional de bases de dados, quais os objetivos da normalização de dados? De que forma o processo de normalização poderá afetar, posteriormente, o desempenho da respetiva implementação?

### Resposta:
O **OBJETIVO DA NORMALIZAÇÃO** é analisar uma relação com base na sua chave primária e nas dependências funcionais entre atributos. Consoante a normalização avança, as relações tornam-se mais fortes no formato e também menos vulneráveis a anomalias de atualização.

### Definições das Formas Normais:

- **FORMA NÃO NORMALIZADA (FNN)**: uma tabela que contém um ou mais grupos repetidos.

- **PRIMEIRA FORMA NORMAL (1FN)**: uma relação em que a intersecção entre uma linha e uma coluna contenha um e um só valor.

- **SEGUNDA FORMA NORMAL (2FN)**: uma relação que está na 1FN e todos os atributos não pertencentes à chave primária são totalmente dependentes de qualquer chave candidata.

- **TERCEIRA FORMA NORMAL (3FN)**: uma relação que está na 1FN e na 2FN e na qual nenhum atributo não pertencente à chave primária depende de qualquer chave candidata.

### Impacto no desempenho:
A normalização pode afetar o desempenho porque quanto mais normalizada estiver a BD, mais tabelas existem e, consequentemente, mais junções são necessárias para obter a informação pretendida, o que pode diminuir o desempenho de leitura/consulta.

### Desnormalização:
É o processo de tentar otimizar o desempenho de leitura/consulta a uma BD, adicionando dados redundantes. Por exemplo: uma tabela que armazena registos de publicações de um blog beneficia uma vez que cada publicação foi escrita uma vez e raramente será editada, mas estará constantemente a ser lida.

---

## ⭐⭐⭐ PERGUNTA 3 — Anomalias de Atualização
**Saiu em: 5 exames incluindo EN2021**

### Pergunta:
Descreva os tipos de anomalias de atualização (dê exemplos) que podem ocorrer numa relação que contém dados redundantes.

### Resposta:
- **INSERÇÃO**: inserir um funcionário num escritório que não existe.
- **REMOÇÃO**: ao apagar funcionário único, apaga também esse escritório e tudo o que está associado é perdido.
- **MODIFICAÇÃO**: ter informação duplicada e apenas atualizar parte desta informação, não atualizando a informação duplicada, cria inconsistência de dados.

---

## ⭐⭐⭐ PERGUNTA 4 — Triggers: Definição, Vantagens e Desvantagens
**Saiu em: EN2021 e 2024/2025**

### Pergunta:
O que são Triggers de bases de dados e para que servem? Quais as vantagens e desvantagens da utilização de triggers?

### Resposta:
Um **TRIGGER** "dispara" uma ação ou conjunto de ações que devem ser realizadas quando algum evento ocorre na aplicação.

**Vantagens:**
- Eliminação de código redundante
- Melhora da integridade de informação
- Maior facilidade na alteração das regras de integridade do negócio
- Boa junção com a arquitetura cliente-servidor

**Desvantagens:**
- Overhead do processador
- Possível efeito cascata
- Falta de possibilidade de agendar os Triggers
- Diminuição da portabilidade uma vez que geralmente cada SGBD possui uma forma diferente de criar Triggers

### Diferença entre Triggers Before, After, Instead Of:
- **TRIGGER BEFORE**: é executado antes da operação que o ativa (INSERT, UPDATE ou DELETE).
- **TRIGGER AFTER**: é feito após o término de uma das operações acima referidas.
- **TRIGGER INSTEAD OF**: é executado no lugar de uma operação SQL "normal".

---

## ⭐⭐⭐ PERGUNTA 5 — Vistas (Views)
**Saiu em: EN2021 e 2024/2025**

### Pergunta:
O que é uma vista? Quais as diferenças entre uma vista e uma relação base?

### Resposta:
**VISTAS** são relações virtuais que podem não existir fisicamente na BD e podem ser produzidas em tempo real mediante o pedido do utilizador.

Uma **RELAÇÃO BASE**, ao contrário de uma VISTA, existe fisicamente na BD e podem ser usadas na criação da vista.

### Importância:
Este mecanismo é importante pois:
- Oferece uma forma flexível de segurança, permitindo esconder partes da BD de certos utilizadores
- Permite aos utilizadores aceder à informação de forma personalizada
- Permite simplificar operações complexas nas relações base

### Vantagens:
- Segurança melhorada
- Complexidade reduzida
- Personalização

### Desvantagens:
- Restrições nas atualizações (uma vista poderá não ser atualizada)
- Restrições na estrutura (a sua estrutura é definida no momento da sua criação e para haver alterações é necessário criar uma nova)
- Problema de performance caso a vista envolva a junção de várias tabelas (essa junção terá que ser feita sempre que a vista é acedida)

### Restrições para vista atualizável:
O SGBD deve ser capaz de conseguir rastrear cada linha e coluna até aos seus equivalentes na tabela de origem. Isso pode ser garantido confirmando que na VISTA:
- Não existe GROUP BY ou HAVING
- O FROM apenas refere uma tabela
- Não é especificado DISTINCT
- Não há funções de agregação nem subqueries

### Mecanismo de Resolução de Vistas:
1. Os nomes das colunas da vista na lista SELECT são traduzidos para os nomes das colunas correspondentes na definição da vista.
2. Os nomes das vistas no FROM são substituídos pelas correspondentes da lista do FROM da definição da vista.
3. O WHERE da query do utilizador é combinado com o WHERE da definição da vista usando o AND.
4. As cláusulas GROUP BY e HAVING são copiadas da definição da vista.
5. O ORDER BY é copiado da query e o nome da coluna traduzido para o nome da coluna da definição da vista.
6. A query final é então executada para devolver o resultado.

### Materialização de Vistas:
Consiste no seu armazenamento numa tabela temporária na BD fazendo com que o acesso a essa vista seja muito mais rápido, aumentando a performance do sistema caso essa vista seja muitas vezes consultada.

---

## ⭐⭐ PERGUNTA 6 — Sistemas de BD vs Ficheiros + Vantagens/Desvantagens SGBD
**Saiu em: 5 exames**

### Pergunta:
Descreva as principais características de um Sistema BD e faça a comparação com os Sistemas Baseados em Ficheiros. Enuncie e explique sucintamente as principais vantagens e desvantagens de um SGBD.

### Resposta:
Os **SISTEMAS DE BD** surgiram com o intuito de colmatar as limitações dos **SISTEMAS BASEADOS EM FICHEIROS**.

Nos Sistemas de BD existe uma **centralização** da informação que pode ser acedida em simultâneo por diversos utilizadores, eliminando assim a separação e isolamento dos dados, bem como a redundância de informação, características dos Sistemas Baseados em Ficheiros.

Para além disso, num Sistema de BD existe a **independência entre informação e as aplicações** que fazem uso dela, mais uma vez melhorando o Sistema de Ficheiros, onde as aplicações eram desenhadas para um tipo específico de ficheiros.

### Quando preferir Ficheiros:
Quando a quantidade de informação armazenada é baixa e tem o propósito de servir apenas um departamento é preferível um sistema menos complexo tal como um **SISTEMA DE FICHEIROS**. Para além do tamanho e complexidade serem baixos, o custo comparativamente a um SGBD é também muito inferior e em caso de falha o impacto é inferior.

### Vantagens do SGBD:
- Controlo sobre a redundância de dados
- Consistência de dados
- Partilha de dados
- Mais segurança
- Mais produtividade
- Mais concorrência
- Mais informação tendo em conta os mesmos dados
- Melhoria na manutenção através da independência de dados
- Integridade de dados melhorada
- Implica uso de standards
- Acessibilidade aos dados e rapidez de resposta melhorados
- Requisitos conflituosos balanceados
- Serviços de cópias de segurança e de recuperação de falhas melhoradas
- Economia de escala

### Desvantagens do SGBD:
- Complexidade
- Tamanho
- Custo do SGBD
- Custo do hardware acrescido
- Custo de conversão
- Performance
- Maior impacto em caso de falha

---

## ⭐⭐ PERGUNTA 7 — Data Warehouses: Benefícios e Problemas
**Saiu em: 3 exames incluindo 2024/2025**

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
- Dar aos utilizadores acesso aos dados que precisam de analisar mais frequentemente
- Providenciar os dados que coincidam com a vista coletiva de um grupo de utilizadores
- Melhorar o tempo de resposta aos utilizadores finais
- Construir um DATA MART é mais simples que construir um data warehouse
- O custo de implementação é normalmente inferior

---

## ⭐⭐ PERGUNTA 8 — DML Procedimentais vs Não Procedimentais
**Saiu em: 2024/2025 (tendência recente)**

### Pergunta:
Apresente as diferenças entre DML Procedimentais e DML Não Procedimentais.

### Resposta:
A grande diferença entre as DML referidas é o seu propósito final:

- **DML PROCEDIMENTAIS** são utilizadas quando é necessário trabalhar **a forma como** os dados são apresentados.
- **DML NÃO PROCEDIMENTAIS** apenas se "preocupam" com **quais** os dados que serão apresentados.

### Contexto adicional — Sublinguagens de Dados:
São a forma de comunicação existente com a BD. Geralmente dividem-se em duas vertentes:

- **DDL (Linguagem de Definição de Dados)**: permite a implementação da própria BD, bem como de todas as relações existentes e possíveis restrições de integridade associadas.
- **DML (Linguagem de Manipulação de Dados)**: usada após a criação da BD que permite inserir, eliminar, atualizar e consultar a informação presente na BD.

---

## ⭐⭐ PERGUNTA 9 — Tipos de Junção (Joins)
**Saiu em: EN2021, MiniTeste 08/09**

### Pergunta:
Descreva as diferenças entre as cinco operações de junção: Theta Join, Equijoin, Natural Join, Outer Join e Semijoin. Dê exemplos para suportar a sua resposta.

### Resposta:
- **THETA JOIN**: é usado quando os atributos de junção não são homónimos.
- **EQUIJOIN**: é usado quando a condição contém apenas igualdade.
- **NATURAL JOIN**: é a junção normal feita através de um produto cartesiano seguido de uma seleção.
- **OUTER JOIN**: serão mostrados toda a informação de uma relação e eventualmente do outro caso haja junção.
- **SEMIJOIN**: apenas aparecerão os tuplos de uma relação (que participam na junção).

### Exemplo THETA JOIN:
```
TABELA ESTUDANTE          TABELA CADEIRA
ID  NOME      TURMA       TURMA  CADEIRA
1   Carlos    111         111    Base de Dados
                          111    LP2
2   Teixeira  222         222    Física
                          222    Engenharia

Resultado (Estudante.Turma = Cadeira.Turma):
ID  NOME      TURMA  TURMA  CADEIRA
1   Carlos    111    111    Base de Dados
1   Carlos    111    111    LP2
2   Teixeira  222    222    Física
2   Teixeira  222    222    Engenharia
```

### Exemplo EQUI JOIN:
```sql
SELECT Estudante.nome, Professor.nome, Professor.classe
FROM   Estudante, Professor
WHERE  Estudante.turma = Professor.classe
```

### Exemplo NATURAL JOIN:
```sql
SELECT *
FROM   Item
NATURAL JOIN Empresa;
-- Junta automaticamente pelas colunas com o mesmo nome
```

### Exemplo OUTER JOIN:
Mostra TODA a informação de uma tabela, mesmo sem correspondência na outra (linhas sem match ficam com NULL).

### Exemplo SEMI JOIN:
Só mostra tuplos de uma das tabelas que têm correspondência na outra.

---

## ⭐ PERGUNTA 10 — Atributos num Diagrama ER
**Saiu em: 2022/2023**

### Pergunta:
Descreva o que representam os atributos num diagrama ER e dê exemplos de atributos simples, compostos, multi-valor e derivados.

### Resposta:
Num diagrama ER os atributos representam a **propriedade de uma entidade ou de um tipo de relação**.

- **SIMPLES**: número de cartão de cidadão.
- **COMPOSTO**: o atributo endereço pode ser decomposto em morada, cidade, código postal.
- **MULTI-VALOR**: pode tomar um ou mais valores para cada entidade, por exemplo o atributo grau académico pode ser: licenciado, mestre, doutorado.
- **DERIVADO**: pode ser determinado a partir de outro atributo. Por exemplo o atributo idade pode ser calculado a partir da data de nascimento.

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

**Diferença WHERE vs HAVING:** WHERE filtra linhas individuais, HAVING filtra grupos.

---

### Independência de Dados
O conceito de **INDEPENDÊNCIA DE DADOS** significa que mudanças relacionadas aos níveis inferiores (lógico ou físico), não afetam os níveis superiores.

---

### Arquitetura Cliente-Servidor (2 vs 3 níveis)
- **2 camadas**: apenas um servidor responsável pelo SGBD e um cliente que corre as aplicações.
- **3 camadas**: servidor de base de dados, servidor de aplicação e uma interface de utilizador que corre no cliente. Mais aplicável ao modelo web, uma vez que o servidor de aplicação poderá estar incluído num servidor web. Esta arquitetura dá suporte à acessibilidade das BD através do balanceamento de carga.

---

### Subquery vs Junção
- **SUBQUERY**: é uma query embebida noutra query.
- **JUNÇÃO**: é a união entre várias tabelas.
- Não é possível utilizar uma SUBQUERY como um operador numa expressão.

**3 tipos de subqueries:**
- **ESCALAR**: devolve um valor singular.
- **DE LINHA**: devolve apenas um tuplo.
- **DE TABELA**: retorna uma relação.

---

### Ciclo de Vida de uma Aplicação de BD
1. Planeamento da Base de Dados
2. Definição do Sistema
3. Recolha e Análise de Requisitos
4. Desenho da BD
5. Seleção do SGBD (opcional)
6. Desenho da Aplicação
7. Prototipagem (opcional)
8. Implementação
9. Conversão e Alimentação de Dados
10. Testes
11. Manutenção Operacional

---

### Chave Candidata, Primária, Estrangeira
- **CHAVE CANDIDATA**: é um ou mais atributos que identificam unicamente um tuplo na relação.
- **CHAVE PRIMÁRIA**: consiste na chave candidata escolhida para a identificação dos tuplos.
- **CHAVE ESTRANGEIRA**: é um atributo de uma relação que é chave candidata/primária numa outra relação e que permite relacionar tuplos de relações diferentes.

---

### 5 Operações Básicas de Álgebra Relacional
- **SELEÇÃO (σ)**: seleciona tuplos que satisfaçam à condição de seleção
- **PROJEÇÃO (π)**: projeta as colunas solicitadas
- **PRODUTO CARTESIANO (×)**: combina tuplos de duas relações
- **UNIÃO (∪)**: une duas tabelas
- **DIFERENÇA (−)**: dá uma tabela com todas as linhas de A que não estão em B

A partir destas:
- **JUNÇÃO** = Produto Cartesiano + Seleção
- **INTERSECÇÃO** = A − (A − B)
- **DIVISÃO** = mostra todos os valores de um atributo de A que fazem referência a todos os valores de um atributo de B

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

> 💡 **Dica final:** O exercício de **normalização de uma fatura** sai em TODOS os exames teóricos (vale 3-4 val.). Pratica com os exemplos dos exames anteriores!
