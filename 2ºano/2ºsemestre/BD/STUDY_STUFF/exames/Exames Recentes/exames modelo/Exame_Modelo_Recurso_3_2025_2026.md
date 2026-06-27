# 📝 Exame Modelo de Recurso — Bases de Dados (Modelo 3)

**📅 Ano Letivo:** 2025/2026 | **📆 Época:** Recurso (Modelo)  
**🎓 Curso:** Engenharia Informática / Segurança Informática em Redes de Computadores  
**🏫 Instituição:** P.PORTO — ESTG  
**📖 Unidade Curricular:** Bases de Dados  
**⏱️ Duração:** 2 horas | **📊 Cotação Total:** 20 valores

---

> **Instruções:** Responda a todas as questões de forma clara e objetiva. Fundamente as suas respostas sempre que possível. Use notação adequada para SQL e Álgebra Relacional.

---

## Pergunta 1 — Conceitos Fundamentais (2 val.)

Defina os seguintes termos no contexto de sistemas de bases de dados:
- **Base de Dados**
- **Sistema de Gestão de Bases de Dados (SGBD)**, identificando os seus principais componentes
- **Metadados**

---

## Pergunta 2 — Abordagem de Ficheiros vs SGBD (2 val.)

Descreva em que situações será preferível a abordagem de Sistemas de Ficheiros comparativamente à abordagem de Sistemas de Bases de Dados, tendo em atenção as principais desvantagens da utilização de um SGBD.

---

## Pergunta 3 — Sublinguagens de Dados (2 val.)

O que são sublinguagens de dados? Identifique e descreva sucintamente as quatro principais sublinguagens de dados no SQL (DDL, DML, DCL, TCL), apresentando exemplos de comandos para cada uma delas e explicando as diferenças entre DDL e DML.

---

## Pergunta 4 — Esquemas de Bases de Dados ANSI/SPARC (2 val.)

Explique o conceito de *Database Schema* e descreva detalhadamente os três tipos de esquema definidos na arquitetura de três níveis ANSI/SPARC (Esquema Externo, Esquema Conceptual e Esquema Interno).

---

## Pergunta 5 — Operações da Álgebra Relacional (2 val.)

Defina as cinco operações básicas/principais da Álgebra Relacional. Adicionalmente, demonstre matematicamente como as operações derivadas de Junção (⨝) e Interseção (∩) podem ser expressas através destas cinco operações básicas.

---

## Pergunta 6 — Anomalias de Atualização (2 val.)

Descreva os três tipos de anomalias de atualização (inserção, eliminação e modificação) que podem ocorrer numa relação que contém dados redundantes, fornecendo exemplos para ilustrar cada uma delas.

---

## Pergunta 7 — Normalização de Fatura (3 val.)

Observe atentamente o documento abaixo, que representa uma fatura de estadia simplificada emitida por um hotel. Escreva a definição da estrutura — nomes e atributos — das tabelas necessárias para representar estes dados num sistema de gestão de bases de dados relacional que suporte a faturação do hotel. Deve garantir que todas as tabelas estão na forma normal mais adequada. Não esqueça de indicar quais os campos que são chave primária e quais os que são chave estrangeira. Enuncie as definições de cada Forma Normal à medida que faz a normalização e identifique as dependências funcionais verificadas.

---

### 📄 FATURA — Grand Plaza Hotel

```
╔══════════════════════════════════════════════════════════════════════════════╗
║                             FATURA DE ESTADIA                              ║
╠══════════════════════════════════════════════════════════════════════════════╣
║  Grand Plaza Hotel, Lda.                                                   ║
║  NIF Hotel: 502 987 111                                                    ║
║  Av. Central, 120 — 4610-222 Felgueiras                                    ║
║                                                                            ║
║  Fatura Nº: FS 2026/0491        Data Emissão: 20-06-2026    Hora: 11:15    ║
║  ATCUD: GPH9876-0491            Quarto Reservado: 204                      ║
╠══════════════════════════════════════════════════════════════════════════════╣
║  DADOS DO HÓSPEDE                                                          ║
║  NIF Hóspede: 211 098 765       Nome: João Sousa                           ║
║  Morada: Rua das Flores, 15, 4610-100 Felgueiras                           ║
╠══════════════════════════════════════════════════════════════════════════════╣
║  Período Estadia: 18-06-2026 a 20-06-2026 (2 noites)                       ║
╠══════════════════════════════════════════════════════════════════════════════╣
║  CONSUMOS & SERVIÇOS                                                       ║
║                                                                            ║
║  Ref.    Descrição               Local    Qtd.  Preço Un.  IVA   Subtotal  ║
║  ──────  ──────────────────────  ──────   ────  ─────────  ────  ──────── ║
║  S102    Acomodação Quarto Dbl  Quarto    2      85,00€     6%   180,20€  ║
║  C405    Room Service Jantar     Restaur.  1      25,00€    23%    30,75€  ║
║  S220    Acesso Circuito SPA     SPA       2      15,00€    23%    36,90€  ║
║                                                                            ║
╠══════════════════════════════════════════════════════════════════════════════╣
║  RESUMO DE IVA                                                             ║
║                                                                            ║
║  Taxa IVA    Incidência     Valor IVA                                      ║
║  ─────────   ──────────     ─────────                                      ║
║     6%        170,00€        10,20€                                        ║
║    23%         55,00€        12,65€                                        ║
╠══════════════════════════════════════════════════════════════════════════════╣
║  Subtotal (s/IVA):    225,00€                                              ║
║  Total IVA:            22,85€                                              ║
║  ─────────────────────────────                                             ║
║  TOTAL FINAL:         247,85€                                              ║
║                                                                            ║
║  Método Pagamento: Cartão Multibanco                                       ║
╚══════════════════════════════════════════════════════════════════════════════╝
```

---

## Pergunta 8 — Modelação, SQL e Álgebra Relacional (5 val.)

Uma empresa de reparação de dispositivos eletrónicos pretende informatizar a sua gestão operacional. O diagrama conceptual simplificado do sistema contempla as seguintes entidades e regras de negócio:

- Os **Clientes** (particulares ou empresariais) possuem um código, nome, NIF e tipo de cliente.
- Os **Técnicos** são identificados por um código e possuem nome, nível de certificação (Júnior, Sénior, Especialista) e respetivo valor/hora.
- Os **Dispositivos** eletrónicos pertencem a um cliente e possuem um número de série único, marca, modelo, categoria (ex.: smartphone, portátil, consola) e o código do proprietário.
- As **Ordens de Reparação** registam os trabalhos iniciados, contendo código da ordem, número de série do dispositivo, código do técnico responsável, data de início da intervenção, estado atual (Em diagnóstico, Agendada, Em curso, Concluído) e o total de horas de trabalho efetivas registadas.

A definição de cada tabela é apresentada a seguir:

- **Cliente** = ( codCliente, nome, nif, tipoCliente )
- **Tecnico** = ( codTecnico, nome, nivelCertificacao, valorHora )
- **Dispositivo** = ( numSerie, marca, modelo, categoria, codCliente )
- **OrdemReparacao** = ( codOrdem, numSerie, codTecnico, dataInicio, estado, totalHoras )

> **Nota:** `codCliente` em *Dispositivo* referencia *Cliente(codCliente)*; `numSerie` em *OrdemReparacao* referencia *Dispositivo(numSerie)*; e `codTecnico` em *OrdemReparacao* referencia *Tecnico(codTecnico)*.

---

### a) Identifique a chave primária e as chaves estrangeiras da tabela OrdemReparacao. Justifique o relacionamento com as restantes tabelas e a necessidade das chaves estrangeiras. (1 val.)

---

### b) SQL: Quais os clientes (nome e NIF) de tipo 'Particular' que têm mais de 3 ordens de reparação com estado 'Concluído' associadas a dispositivos da marca 'Apple' iniciadas no ano de 2026? (2 val.)

---

### c) Álgebra Relacional: Quais os técnicos (nome e nível de certificação) que nunca foram associados a qualquer ordem de reparação para dispositivos da categoria 'Smartphones'? (2 val.)

---

*Bom trabalho!*
