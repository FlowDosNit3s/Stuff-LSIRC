# 📝 Exame Modelo de Recurso — Bases de Dados (Modelo 1)

**📅 Ano Letivo:** 2025/2026 | **📆 Época:** Recurso (Modelo)  
**🎓 Curso:** Engenharia Informática / Segurança Informática em Redes de Computadores  
**🏫 Instituição:** P.PORTO — ESTG  
**📖 Unidade Curricular:** Bases de Dados  
**⏱️ Duração:** 2 horas | **📊 Cotação Total:** 20 valores

---

> **Instruções:** Responda a todas as questões de forma clara e objetiva. Fundamente as suas respostas sempre que possível. Use notação adequada para SQL e Álgebra Relacional.

---

## Pergunta 1 — Independência de Dados (2 val.)

Descreva o conceito de independência de dados e a sua importância num ambiente de bases de dados. Diferencie entre independência física e independência lógica de dados, dando um exemplo prático de cada uma.

---

## Pergunta 2 — Integridade Referencial e Ações ON DELETE / ON UPDATE (2 val.)

Explique o que entende por Integridade Referencial. Identifique e descreva quais as ações que se podem utilizar nas subcláusulas ON DELETE e ON UPDATE.

---

## Pergunta 3 — Triggers de Bases de Dados (2 val.)

O que são triggers de bases de dados e para que servem? Quais as vantagens e desvantagens da utilização de triggers? Identifique os diferentes tipos de triggers quanto ao momento de execução.

---

## Pergunta 4 — Subquery vs Junção (2 val.)

Qual a diferença entre uma subquery e uma junção? Em que situações não é possível usar uma subquery? Dê um exemplo SQL que ilustre a necessidade de usar uma junção em vez de uma subquery.

---

## Pergunta 5 — Materialização de Vistas (2 val.)

Explique o conceito de materialização de vistas (Materialized Views / Indexed Views). Quais as vantagens e desvantagens desta abordagem em comparação com as vistas tradicionais? Em que contextos é recomendável a sua utilização?

---

## Pergunta 6 — Data Warehouses (2 val.)

Descreva os principais benefícios e problemas associados aos Data Warehouses. Distinga entre um Data Warehouse e um Data Mart.

---

## Pergunta 7 — Normalização de Fatura (3 val.)

Observe atentamente o documento abaixo, que representa uma fatura simplificada de uma loja de eletrónica online. Escreva a definição da estrutura — nomes e atributos — das tabelas necessárias para representar estes dados num sistema de gestão de bases de dados relacional que suporte a emissão das faturas da empresa. Deve garantir que todas as tabelas estão na forma normal mais adequada. Não esqueça de indicar quais os campos que são chave primária e quais os que são chave estrangeira. Enuncie as definições de cada Forma Normal à medida que faz a normalização e identifique as dependências funcionais verificadas.

---

### 📄 FATURA — TecnoShop, Lda.

```
╔══════════════════════════════════════════════════════════════════════════════╗
║                           FATURA SIMPLIFICADA                              ║
╠══════════════════════════════════════════════════════════════════════════════╣
║  TecnoShop, Lda.                                                           ║
║  NIF Empresa: 509 123 456                                                  ║
║  Rua das Tecnologias, 42 — 4610-175 Felgueiras                            ║
║                                                                            ║
║  Fatura Nº: FS 2026/1847        Data: 15-06-2026      Hora: 14:32          ║
║  ATCUD: ABCD1234-1847                                                      ║
╠══════════════════════════════════════════════════════════════════════════════╣
║  DADOS DO CLIENTE                                                          ║
║  NIF Cliente: 234 567 890       Nome: Ana Pereira                          ║
║  Morada: Av. da Liberdade, 100, 4000-322 Porto                            ║
╠══════════════════════════════════════════════════════════════════════════════╣
║  Método de Envio: CTT Expresso   Código Envio: ENV03                       ║
║  Custo de Envio: 4,99€           Prazo Estimado: 2-3 dias úteis           ║
╠══════════════════════════════════════════════════════════════════════════════╣
║  ARTIGOS                                                                   ║
║                                                                            ║
║  Ref.    Descrição               Categ.   Qtd.  Preço Un.  IVA   Subtotal  ║
║  ──────  ──────────────────────  ──────   ────  ─────────  ────  ──────── ║
║  A1001   Rato sem fios MX3       Perif.    2     29,99€    23%    73,77€  ║
║  A2045   Teclado mecânico K70    Perif.    1     89,99€    23%   110,69€  ║
║  A3012   Cabo HDMI 2.1 (2m)     Cabos     3      9,99€    23%    36,86€  ║
║  A5500   Webcam HD Pro           Perif.    1     54,99€    23%    67,64€  ║
║                                                                            ║
╠══════════════════════════════════════════════════════════════════════════════╣
║  RESUMO DE IVA                                                             ║
║                                                                            ║
║  Taxa IVA    Incidência     Valor IVA                                      ║
║  ─────────   ──────────     ─────────                                      ║
║    23%        234,95€        54,04€                                        ║
╠══════════════════════════════════════════════════════════════════════════════╣
║  Subtotal (s/IVA):    234,95€                                              ║
║  Total IVA:            54,04€                                              ║
║  Envio:                 4,99€                                              ║
║  ─────────────────────────────                                             ║
║  TOTAL:               293,98€                                              ║
║                                                                            ║
║  Método Pagamento: Cartão Visa                                             ║
╚══════════════════════════════════════════════════════════════════════════════╝
```

---

## Pergunta 8 — Modelação, SQL e Álgebra Relacional (5 val.)

Uma companhia aérea pretende informatizar a gestão de reservas de voos. O diagrama E/R simplificado contempla as seguintes entidades e relacionamentos:

- Existem **Aeroportos** identificados por um código IATA (3 letras), com nome e cidade.
- Existem **Voos** regulares, cada um com um número de voo, aeroporto de origem, aeroporto de destino, hora de partida e hora de chegada.
- Existem **Passageiros** com código, nome, e-mail e país de residência.
- Cada passageiro pode efetuar **Reservas** em vários voos. Uma reserva regista a data de viagem, a classe (Económica, Business, Primeira) e o preço pago.

A definição de cada tabela é dada a seguir:

- **Aeroporto** = ( codIATA, nome, cidade )
- **Voo** = ( numVoo, origem, destino, horaPartida, horaChegada )
- **Passageiro** = ( codPass, nome, email, pais )
- **Reserva** = ( codReserva, codPass, numVoo, dataViagem, classe, preco )

> **Nota:** `origem` e `destino` são chaves estrangeiras para `Aeroporto(codIATA)`.

---

### a) Identifique a chave primária e as chaves estrangeiras da tabela Reserva. Justifique. (1 val.)

---

### b) SQL: Quais os países que têm mais de 5 passageiros com reservas em voos para a cidade do Porto no ano de 2026? (2 val.)

---

### c) Álgebra Relacional: Quais os aeroportos que nunca foram destino de nenhum voo com reservas? (2 val.)

---

*Bom trabalho!*
