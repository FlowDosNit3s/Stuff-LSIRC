# 📚 Resolução do Exame Teórico de Bases de Dados (Época Normal)

**📅 Ano Letivo:** 2022/2023 | **📆 Data:** 27-06-2024  
**🎓 Curso:** Engenharia Informática / Segurança Informática em Redes de Computadores  
**🏫 Instituição:** P.PORTO - ESTG  
**📖 Unidade Curricular:** Bases de Dados  

---

## 1. ⚙️ Independência de Dados (2 val.)

> ❓ **Pergunta 1:** Descreva o conceito de independência de dados e a sua importância num ambiente de bases de dados. (2 val.)

**✍️ Resposta:**
A independência de dados é a capacidade de alterar o esquema de uma base de dados num determinado nível de abstração sem a necessidade de reestruturar os níveis superiores. Divide-se em independência física, que permite alterar o armazenamento físico (como índices ou ficheiros) sem alterar o esquema lógico ou as aplicações; e independência lógica, que permite alterar o esquema conceptual (como adicionar colunas ou dividir tabelas) sem reescrever as queries das aplicações existentes. A sua importância reside na simplificação do desenvolvimento, no aumento da flexibilidade evolutiva e na redução dos custos de manutenção de software.

---

## 2. 🏢 Arquitetura Cliente-Servidor (2 val.)

> ❓ **Pergunta 2:** Compare a arquitetura cliente-servidor de dois níveis com a de três-níveis e identifique justificando qual a mais adequada para a Web. (2 val.)

**✍️ Resposta:**
Numa arquitetura de dois níveis (2-tier), a aplicação cliente comunica diretamente com o servidor de bases de dados, acumulando a interface e as regras de negócio. Na arquitetura de três níveis (3-tier), introduz-se um servidor de aplicação intermédio entre o cliente (tipicamente um browser leve) e a base de dados, isolando a lógica de negócio do armazenamento físico. A arquitetura de três níveis é a mais adequada para a Web porque centraliza a lógica de negócio no servidor intermédio, facilita o pooling de conexões concorrentes para milhares de utilizadores e previne o acesso direto e inseguro ao servidor de dados.

---

## 3. 🔍 Subquery vs Junção (2 val.)

> ❓ **Pergunta 3:** Qual a diferença entre uma subquery e uma junção? Em que situações não é possível usar uma subquery? (2 val.)

**✍️ Resposta:**
Uma subquery é uma instrução SELECT aninhada dentro de outra consulta principal para calcular dados temporários de suporte, enquanto uma junção (JOIN) combina registos de múltiplas tabelas na mesma linha com base numa condição. Não é possível usar uma subquery em situações onde a lógica exige a exibição simultânea no resultado final de atributos pertencentes a tabelas distintas (as subqueries só filtram ou projetam dados da tabela principal). Também se torna inviável em comparações e agregações correlacionadas cuja cardinalidade não possa ser resolvida de forma escalar simples pela query externa.

---

## 4. 🔗 Integridade Referencial e Ações ON DELETE/ON UPDATE (2 val.)

> ❓ **Pergunta 4:** Explique o que entende por Integridade Referencial. Identifique e descreva quais as ações que se podem utilizar nas subcláusulas ON DELETE e ON UPDATE. (2 val.).

**✍️ Resposta:**
A integridade referencial garante a consistência entre tabelas, obrigando a que os valores de uma chave estrangeira (FK) na tabela filha existam na chave primária (PK) da tabela pai ou sejam nulos. Para gerir alterações na tabela pai, as subcláusulas ON DELETE e ON UPDATE suportam quatro ações: CASCADE, que propaga a eliminação ou atualização do registo pai diretamente para os registos filhos; SET NULL, que define a FK dos filhos como nula (exigindo que a coluna permita nulos); SET DEFAULT, que altera a FK dos filhos para o valor padrão configurado; e NO ACTION (ou RESTRICT), que rejeita a operação no registo pai caso existam registos filhos dependentes.

---

## 5. 📐 Abordagens para Múltiplas Vistas (2 val.)

> ❓ **Pergunta 5:** Enuncie quais as principais abordagens para elaborar o desenho de uma base de dados com múltiplas vistas de utilizadores. (2 val.)

**✍️ Resposta:**
Para elaborar o desenho de uma base de dados com múltiplas vistas, existem três abordagens clássicas. A abordagem centralizada consiste em recolher e fundir os requisitos de todas as vistas numa única lista global antes de iniciar a modelação conceptual. A abordagem por integração de vistas constrói modelos conceituais locais independentes para cada vista de utilizador, fundindo-os posteriormente num esquema global unificado. A abordagem mista combina as anteriores, agregando os requisitos comuns no início e tratando vistas altamente complexas de forma isolada antes da consolidação final.

---

## 6. 📊 Atributos no Modelo ER (2 val.)

> ❓ **Pergunta 6:** Descreva o que representam os atributos num diagrama ER e dê exemplos de atributos simples, compostos, multi-valor e derivados. (2 val.)

**✍️ Resposta:**
Os atributos representam as propriedades ou características que descrevem uma entidade ou relacionamento no modelo ER. Como exemplos práticos: os atributos simples contêm valores atómicos indivisíveis (como o NIF); os compostos dividem-se em campos independentes (como a Morada, decomposta em Rua, Localidade e Código Postal); os multi-valor admitem múltiplos valores para uma mesma linha (como os Hobbies ou Telefones de um indivíduo); e os derivados resultam de cálculos baseados em outros dados (como a Idade calculada a partir da Data de Nascimento).

---

## 7. 📋 Exercício de Normalização de Fatura (3 val.)

> ❓ **Pergunta 7:** Observe atentamente o documento que acompanha o enunciado e que representa uma fatura. Escreva a definição da estrutura – nomes e atributos - das tabelas necessárias para representar estes dados num sistema de gestão de bases de dados relacional que suporte a emissão das faturas da empresa. Deve garantir que todas as tabelas estão na forma normal mais adequada. Não esqueça de indicar quais os campos que são chave primária e quais os que são chave estrangeira. Enuncie as definições de cada Forma Normal à medida que faz a normalização e identifique as dependências funcionais verificadas. (3 val.)

**✍️ Resposta:**
Mapeamos os atributos da fatura da Tomatino atribuindo letras: NIF_Empresa (A), NumFatura (B), Data (C), Hora (D), Quantidade (E), Artigo (F), TaxaIVA_Artigo (G), Total_Linha (H), Total_Fatura (I), Balcao (J), Empregado (K), TaxaIVA_Resumo (L), Base_Resumo (M), IVA_Resumo (N), TotalIVA_Resumo (O), Consultas (P), Mesa (Q), Pontos (R), IDTrans (S), IDTerm (T), ATCUD (U), cod_Validacao (V), Senha (Y) e num_linha (X) (gerado para identificar as linhas de artigo). Na Forma Não Normalizada (UNF), todos os atributos residem numa relação com dois grupos repetitivos (artigos consumidos e taxas do resumo): `Fatura_UNF(B, A, C, D, I, J, K, P, Q, R, S, T, U, V, Y, [X, E, F, G, H], [L, M, N, O])`.

1️⃣ A 1FN define que uma relação não deve conter grupos repetidos e todas as intersecções devem conter valores atómicos. Achatando os grupos repetitivos, a PK passa a ser composta por `(NumFatura, num_linha, TaxaIVA_Resumo)`. O esquema na 1FN é:
```text
Fatura_1FN(NumFatura, num_linha, TaxaIVA_Resumo, NIF_Empresa, Data, Hora, Total_Fatura, Balcao, Empregado, Quantidade, Artigo, TaxaIVA_Artigo, Total_Linha, Base_Resumo, IVA_Resumo, TotalIVA_Resumo, Consultas, Mesa, Pontos, IDTrans, IDTerm, ATCUD, cod_Validacao, Senha)
PK: (NumFatura, num_linha, TaxaIVA_Resumo)
```
As Dependências Funcionais (DFs) verificadas são:
*   $NumFatura \rightarrow NIF\_Empresa, Data, Hora, Total\_Fatura, Balcao, Empregado, Consultas, Mesa, Pontos, IDTrans, IDTerm, ATCUD, cod\_Validacao, Senha$
*   $NumFatura, num\_linha \rightarrow Quantidade, Artigo, TaxaIVA\_Artigo, Total\_Linha$
*   $NumFatura, TaxaIVA\_Resumo \rightarrow Base\_Resumo, IVA\_Resumo, TotalIVA\_Resumo$
*   $Artigo \rightarrow TaxaIVA\_Artigo$

2️⃣ A 2FN exige que a relação esteja na 1FN e todos os atributos não primos dependam totalmente da chave primária (sem dependências parciais). Decompondo as dependências parciais sobre a chave composta `(NumFatura, num_linha, TaxaIVA_Resumo)`, dividimos o esquema em três relações:
*   `Cabecalho_2FN(NumFatura, NIF_Empresa, Data, Hora, Total_Fatura, Balcao, Empregado, Consultas, Mesa, Pontos, IDTrans, IDTerm, ATCUD, cod_Validacao, Senha)` | PK: `NumFatura`
*   `Linhas_2FN(NumFatura, num_linha, Quantidade, Artigo, TaxaIVA_Artigo, TotalLinha)` | PK: `(NumFatura, num_linha)`
*   `ResumoIVA_2FN(NumFatura, TaxaIVA_Resumo, Base_Resumo, IVA_Resumo, TotalIVA_Resumo)` | PK: `(NumFatura, TaxaIVA_Resumo)`

3️⃣ A 3FN exige que a relação esteja na 2FN e não possua dependências transitivas. Detetamos a dependência transitiva `Artigo → TaxaIVA_Artigo` em `Linhas_2FN`. Extraímos esta dependência criando a tabela `Artigo`. Adicionalmente, `num_linha` e `TaxaIVA_Resumo` representam dependências multi-valor independentes de `NumFatura` ($NumFatura \twoheadrightarrow num\_linha$ e $NumFatura \twoheadrightarrow TaxaIVA\_Resumo$). A decomposição já efetuada evita redundâncias associadas a produtos cartesianos (satisfazendo a 4FN).

O esquema final normalizado é:
```text
Artigo(Artigo, TaxaIVA_Artigo)
    PK: Artigo

Cabecalho_Fatura(NumFatura, NIF_Empresa, Data, Hora, Total_Fatura, Balcao, Empregado, Consultas, Mesa, Pontos, IDTrans, IDTerm, ATCUD, cod_Validacao, Senha)
    PK: NumFatura

Linhas_Fatura(NumFatura, num_linha, Quantidade, Artigo, TotalLinha)
    PK: (NumFatura, num_linha)
    FK: NumFatura → Cabecalho_Fatura(NumFatura)
    FK: Artigo → Artigo(Artigo)

Resumo_IVA(NumFatura, TaxaIVA_Resumo, Base_Resumo, IVA_Resumo, TotalIVA_Resumo)
    PK: (NumFatura, TaxaIVA_Resumo)
    FK: NumFatura → Cabecalho_Fatura(NumFatura)
```

---

## 8. 📐 Modelação Entidade-Relacionamento, SQL e Álgebra Relacional (3 val.)

> ❓ **Enunciado do Problema 8:** O diagrama E/R a seguir pretende demonstrar o relacionamento existente entre diversas entidades de uma base de dados simplista de uma empresa que gere estufas. Existem estufas compostas por secções. Existem Produtos que são plantados numa secção de uma estufa.
> 
> A definição de cada tabela é dada a seguir, identificando os atributos, o seu tipo e quais as chaves primárias.
> 
> * **Estufa** = ( codE, descricao, capacidade, cidade )
> * **Secção** = ( codigoS, tipo, estufa )
> * **Produto** = ( codP, nome, stock, tipo )
> * **Plantação** = ( codP, produto, codS, data_início, data_fim )

---

### 💻 a) SQL: Estufas com mais que 10 plantações do mesmo produto (3 val.)

> ❓ **Pergunta 8a (SQL):** Quais as estufas que tiveram mais que 10 plantações do mesmo produto? (3 val.)

**✍️ Resposta:**
Juntamos (`INNER JOIN`) as tabelas `Estufa`, `Secção` e `Plantação`. Agrupamos por estufa (`e.codE`, `e.descricao`) e por produto (`p.produto`) e filtramos no `HAVING` as contagens superiores a 10:

```sql
SELECT e.codE, e.descricao
FROM Estufa e
INNER JOIN Secção s ON e.codE = s.estufa
INNER JOIN Plantação p ON s.codigoS = p.codS
GROUP BY e.codE, e.descricao, p.produto
HAVING COUNT(*) > 10;
```

---

### 📐 b) Álgebra Relacional: Secções sem plantações (2 val.)

> ❓ **Pergunta 8b (Álgebra Relacional):** Quais as secções que nunca tiveram plantações? (2 val.)

**✍️ Resposta:**
Projetamos ($\pi$) todos os códigos de secção existentes na tabela `Secção` e subtraímos ($-$) os códigos de secção que possuem registos de plantações projetados da tabela `Plantação`, obtendo a resposta por diferença:

$$SeccoesComPlantacao \leftarrow \pi_{codS}(Plantação)$$
$$TodasSeccoes \leftarrow \pi_{codigoS}(Secção)$$
$$Resultado \leftarrow TodasSeccoes - SeccoesComPlantacao$$
