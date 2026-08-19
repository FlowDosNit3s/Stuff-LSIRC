# 📝 Exame Modelo de Recurso — Bases de Dados (Modelo 2)

**📅 Ano Letivo:** 2025/2026 | **📆 Época:** Recurso (Modelo)  
**🎓 Curso:** Engenharia Informática / Segurança Informática em Redes de Computadores  
**🏫 Instituição:** P.PORTO — ESTG  
**📖 Unidade Curricular:** Bases de Dados  
**⏱️ Duração:** 2 horas | **📊 Cotação Total:** 20 valores

---

> **Instruções:** Responda a todas as questões de forma clara e objetiva. Fundamente as suas respostas sempre que possível. Use notação adequada para SQL e Álgebra Relacional.

---

## Pergunta 1 — Arquitetura ANSI/SPARC (2 val.)

A arquitetura ANSI/SPARC identifica três níveis nos SGBD. Descreva pormenorizadamente o nível intermédio, identificando o seu nome, e o que se pretende que este nível represente. Explique de que forma este nível contribui para a independência de dados.

---

## Pergunta 2 — Arquitetura Cliente-Servidor (2 val.)

Compare a arquitetura cliente-servidor de dois níveis com a de três níveis e identifique, justificando, qual a mais adequada para a Web.

---

## Pergunta 3 — Cursores SQL (2 val.)

O que são cursores SQL? Qual o propósito da sua utilização? Descreva o ciclo de vida típico de um cursor, identificando cada uma das suas fases.

---

## Pergunta 4 — LMD Procedimentais vs Não-Procedimentais (2 val.)

Explique as diferenças existentes entre LMD procedimentais e não-procedimentais. Dê exemplos de linguagens que conheça para cada tipo.

---

## Pergunta 5 — Normalização: Objetivos e Impacto no Desempenho (2 val.)

No contexto do modelo relacional de bases de dados, quais os objetivos da normalização de dados? De que forma o processo de normalização poderá afetar, posteriormente, o desempenho da respetiva implementação?

---

## Pergunta 6 — Atributos no Modelo Entidade-Relacionamento (2 val.)

Descreva o que representam os atributos num diagrama ER e dê exemplos de atributos simples, compostos, multi-valor e derivados. Identifique a representação gráfica (notação de Chen) de cada tipo.

---

## Pergunta 7 — Normalização de Contrato (3 val.)

Observe atentamente o documento abaixo, que representa um contrato simplificado de aluguer de viaturas. Escreva a definição da estrutura — nomes e atributos — das tabelas necessárias para representar estes dados num sistema de gestão de bases de dados relacional. Deve garantir que todas as tabelas estão na forma normal mais adequada. Não esqueça de indicar quais os campos que são chave primária e quais os que são chave estrangeira. Enuncie as definições de cada Forma Normal à medida que faz a normalização e identifique as dependências funcionais verificadas.

---

### 📄 CONTRATO DE ALUGUER — AutoFlex Rent-a-Car

```
╔══════════════════════════════════════════════════════════════════════════════╗
║                      CONTRATO DE ALUGUER DE VIATURA                        ║
╠══════════════════════════════════════════════════════════════════════════════╣
║  AutoFlex Rent-a-Car                                                       ║
║  NIF Empresa: 501 987 654                                                  ║
║  Av. dos Aliados, 200 — 4000-064 Porto                                    ║
║                                                                            ║
║  Contrato Nº: CT-2026/0342      Data Início: 10-07-2026                   ║
║  Agência de Levantamento: AGP01 — Porto Aeroporto                         ║
║  Agência de Devolução: AGL03 — Lisboa Centro                              ║
╠══════════════════════════════════════════════════════════════════════════════╣
║  DADOS DO CONDUTOR PRINCIPAL                                               ║
║  NIF: 287 654 321               Nome: Ricardo Sousa                        ║
║  Carta de Condução: PT-543210   Categoria: B                              ║
║                                                                            ║
║  CONDUTORES ADICIONAIS                                                     ║
║  NIF: 298 111 222   Nome: Maria Sousa    Carta: PT-654321   Cat.: B       ║
╠══════════════════════════════════════════════════════════════════════════════╣
║  VIATURA                                                                   ║
║  Matrícula: AA-01-BB            Marca: Toyota                              ║
║  Modelo: Corolla                Categoria: C (Compacto)                    ║
║  Preço Diário: 35,00€           Combustível: Gasolina                      ║
╠══════════════════════════════════════════════════════════════════════════════╣
║  EXTRAS CONTRATADOS                                                        ║
║                                                                            ║
║  CodExtra   Descrição                    Preço/Dia                         ║
║  ────────   ────────────────────────     ─────────                         ║
║  EX01       GPS Navegação                 5,00€                            ║
║  EX03       Cadeira de Bebé               3,50€                            ║
║  EX05       Seguro Super All-Risk        12,00€                            ║
║                                                                            ║
╠══════════════════════════════════════════════════════════════════════════════╣
║  RESUMO FINANCEIRO                                                         ║
║                                                                            ║
║  Duração do Aluguer: 5 dias                                               ║
║  Custo Viatura (5 × 35,00€):          175,00€                             ║
║  Custo Extras:                         102,50€                             ║
║  Condutor Adicional (5 × 7,50€):        37,50€                            ║
║  Taxa de Entrega Diferente:             25,00€                             ║
║  ──────────────────────────────                                            ║
║  Subtotal:                             340,00€                             ║
║  IVA (23%):                             78,20€                             ║
║  TOTAL:                                418,20€                             ║
║                                                                            ║
║  Método Pagamento: MBWay                                                   ║
║  Data Devolução Prevista: 15-07-2026                                       ║
╚══════════════════════════════════════════════════════════════════════════════╝
```

---

## Pergunta 8 — Modelação, SQL e Álgebra Relacional (5 val.)

Um ginásio pretende informatizar a gestão dos seus sócios e aulas. O modelo de dados simplificado é o seguinte:

- Existem **Sócios** identificados por um número, com nome, data de nascimento e tipo de plano (Básico, Premium, VIP).
- Existem **Aulas** com código, nome da modalidade (Yoga, Spinning, CrossFit, etc.), dia da semana e horário.
- Cada aula é dada por um **Instrutor**, identificado por código, nome e especialidade.
- Os sócios podem **Inscrever-se** em várias aulas, registando-se a data de inscrição e a presença (Sim/Não).

A definição de cada tabela é dada a seguir:

- **Socio** = ( numSocio, nome, dataNasc, plano )
- **Instrutor** = ( codInst, nome, especialidade )
- **Aula** = ( codAula, modalidade, diaSemana, horario, codInst )
- **Inscricao** = ( numSocio, codAula, dataInscricao, presenca )

> **Nota:** `codInst` em Aula é chave estrangeira para `Instrutor(codInst)`.

---

### a) Identifique a chave primária e as chaves estrangeiras da tabela Inscricao. Justifique a escolha da chave primária. (1 val.)

---

### b) SQL: Quais os instrutores que dão mais de 3 aulas diferentes com pelo menos 20 inscrições cada? (2 val.)

---

### c) Álgebra Relacional: Quais os sócios com plano VIP que nunca se inscreveram em nenhuma aula de Spinning? (2 val.)

---

*Bom trabalho!*
