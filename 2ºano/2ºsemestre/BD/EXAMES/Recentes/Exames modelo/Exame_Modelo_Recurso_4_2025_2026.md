# 📝 Exame Modelo de Recurso — Bases de Dados (Modelo 4)

**📅 Ano Letivo:** 2025/2026 | **📆 Época:** Recurso (Modelo)  
**🎓 Curso:** Engenharia Informática / Segurança Informática em Redes de Computadores  
**🏫 Instituição:** P.PORTO — ESTG  
**📖 Unidade Curricular:** Bases de Dados  
**⏱️ Duração:** 2 horas | **📊 Cotação Total:** 20 valores

---

> **Instruções:** Responda a todas as questões de forma clara e objetiva. Fundamente as suas respostas sempre que possível. Use notação adequada para SQL e Álgebra Relacional.

---

## Pergunta 1 — Componentes do Ambiente de um SGBD (2 val.)

Descreva os cinco componentes principais do ambiente de um Sistema de Gestão de Bases de Dados (SGBD) e explique sumariamente como eles se relacionam entre si.

---

## Pergunta 2 — Conceitos do Modelo Relacional (2 val.)

No contexto do modelo relacional de bases de dados, explique detalhadamente o significado de cada um dos seguintes termos:
- **Relação**
- **Atributo**
- **Domínio**
- **Tuplo**
- **Grau**
- **Cardinalidade**

---

## Pergunta 3 — Operações de Junção (2 val.)

Descreva as diferenças existentes entre as seguintes cinco operações de junção no modelo relacional, indicando o operador lógico/comparação utilizado e se a operação resulta na duplicação ou eliminação de colunas equivalentes:
- **Theta Join** ($\theta$-Join)
- **Equijoin**
- **Natural Join**
- **Outer Join**
- **Semijoin**

---

## Pergunta 4 — Procedimentos vs Funções (2 val.)

Qual a diferença entre um Procedimento Armazenado (*Stored Procedure*) e uma Função Definida pelo Utilizador (*User-Defined Function*) numa base de dados relacional? Aponte três diferenças fundamentais e indique em que situações é preferível cada um.

---

## Pergunta 5 — Vistas Atualizáveis (2 val.)

Quais as restrições e condições necessárias para garantir que uma vista (*view*) tradicional seja atualizável diretamente através de instruções DML (como INSERT, UPDATE ou DELETE) sobre as tabelas base sem recorrer a triggers?

---

## Pergunta 6 — Especialização vs Generalização no Modelo ER (2 val.)

Explique as diferenças entre o processo de especialização e de generalização no contexto da modelação de dados com o diagrama Entidade-Relacionamento (ER), fornecendo exemplos práticos para cada um.

---

## Pergunta 7 — Normalização de Fatura (3 val.)

Observe atentamente o documento abaixo, que representa um recibo/fatura simplificado de serviços de saúde emitido por uma clínica médica. Escreva a definição da estrutura — nomes e atributos — das tabelas necessárias para representar estes dados num sistema de gestão de bases de dados relacional. Deve garantir que todas as tabelas estão na forma normal mais adequada. Não esqueça de indicar quais os campos que são chave primária e quais os que são chave estrangeira. Enuncie as definições de cada Forma Normal à medida que faz a normalização e identifique as dependências funcionais verificadas.

---

### 📄 RECIBO/FATURA — Clínica Geral do Norte

```
╔══════════════════════════════════════════════════════════════════════════════╗
║                          RECIBO DE SERVIÇOS CLÍNICOS                         ║
╠══════════════════════════════════════════════════════════════════════════════╣
║  Clínica Geral do Norte, S.A.                                              ║
║  NIF Clínica: 503 111 222                                                  ║
║  Rua da Saúde, 500 — 4610-300 Felgueiras                                   ║
║                                                                            ║
║  Recibo Nº: FS 2026/1102        Data Emissão: 22-06-2026    Hora: 17:45    ║
║  ATCUD: CGN1234-1102            Nº Utente SNS: 987 654 321                 ║
╠══════════════════════════════════════════════════════════════════════════════╣
║  DADOS DO PACIENTE                                                         ║
║  NIF Paciente: 255 444 333      Nome: Sofia Monteiro                       ║
║  Seguradora: Médis              Código do Plano: MED02                     ║
╠══════════════════════════════════════════════════════════════════════════════╣
║  SERVIÇOS PRESTADOS                                                        ║
║                                                                            ║
║  Ref.  Descrição          Médico / Técnico  Preço  Copag.  Utente  IVA     ║
║  ────  ─────────────────  ────────────────  ─────  ──────  ──────  ───     ║
║  S402  Cons. Pediatria    Dr. Rui Silva     60,00€ 45,00€  15,00€   0%     ║
║  A105  Análise de Sangue  LabNorte (L05)    20,00€ 16,00€   4,00€   0%     ║
║  M001  Adm. Medicamento   Enf. Ana Martins  10,00€  0,00€  12,30€  23%     ║
║                                                                            ║
╠══════════════════════════════════════════════════════════════════════════════╣
║  RESUMO DE IVA                                                             ║
║                                                                            ║
║  Taxa IVA    Incidência     Valor IVA                                      ║
║  ─────────   ──────────     ─────────                                      ║
║     0%         80,00€         0,00€                                        ║
║    23%         10,00€         2,30€                                        ║
╠══════════════════════════════════════════════════════════════════════════════╣
║  Total Encargo Seguradora: 61,00€                                          ║
║  Total Encargo Utente:     31,30€ (com IVA)                                ║
║  ─────────────────────────────                                             ║
║  TOTAL GERAL DO RECIBO:    92,30€                                          ║
║                                                                            ║
║  Método Pagamento Utente: MBWay                                            ║
╚══════════════════════════════════════════════════════════════════════════════╝
```

---

## Pergunta 8 — Modelação, SQL e Álgebra Relacional (5 val.)

Uma empresa de reparação de dispositivos eletrónicos pretende informatizar o seu controlo de consumos de stock em intervenções técnicas. O modelo de dados físico simplificado contempla as seguintes relações:

- A tabela **Dispositivo** regista os aparelhos dos clientes:  
  **Dispositivo** = ( numSerie, marca, modelo, categoria )
- A tabela **OrdemReparacao** regista as ordens abertas para os dispositivos:  
  **OrdemReparacao** = ( codOrdem, numSerie, dataConclusao, estado, precoMaoObra )
- A tabela **Peca** descreve o inventário de peças sobresselentes disponíveis:  
  **Peca** = ( codPeca, descricao, categoria, precoVenda, stock )
- A tabela **PecaUtilizada** regista a aplicação efetiva de peças em cada reparação, com indicação da quantidade e do preço unitário que foi efetivamente debitado ao cliente:  
  **PecaUtilizada** = ( codOrdem, codPeca, quantidade, precoDebitado )

> **Nota:** `numSerie` em *OrdemReparacao* referencia *Dispositivo(numSerie)*; `codOrdem` em *PecaUtilizada* referencia *OrdemReparacao(codOrdem)*; e `codPeca` em *PecaUtilizada* referencia *Peca(codPeca)*.

---

### a) Identifique a chave primária e as chaves estrangeiras da tabela PecaUtilizada. Justifique se é necessário ter uma chave primária composta nesta tabela. (1 val.)

---

### b) SQL: Quais as peças (código e descrição) da categoria 'Ecrãs' que foram utilizadas em mais de 10 reparações diferentes concluídas no primeiro semestre de 2026 (até 30-06-2026) e cujo preço debitado foi superior ao preço de venda padrão da peça? (2 val.)

---

### c) Álgebra Relacional: Quais os dispositivos (marca e modelo) que foram reparados (estado = 'Concluído') mas para os quais nunca foi utilizada nenhuma peça da categoria 'Baterias'? (2 val.)

---

*Bom trabalho!*
