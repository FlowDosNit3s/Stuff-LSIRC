# 🖥️**Introdução aos Sistemas Operativos**
## 📌 **1. Conceitos Introdutórios**

### ❓ O que é um Sistema Operativo?

- 🏷️ **Não há consenso** sobre sua definição.
- 🏢 A Microsoft chegou a afirmar que o Internet Explorer era parte do SO.
- ✅ **Definição possível**:
    - É um **programa de controlo** que suporta a interação entre o utilizador e o hardware, permitindo a execução eficiente de programas.

---

## 🔑 **2. Conceitos Principais**

- 🏗️ **Processo** → Instância em execução de um programa.
- 🧠 **Memória Virtual** → Simulação de mais memória do que fisicamente disponível, combinando RAM e disco.
- 📁 **Sistema de Ficheiros** → Organização de dados em ficheiros e diretórios.
- 🎛️ **Periféricos** → Hardware controlado por drivers para comunicação entre utilizador e sistema.
- 👤 **Utilizadores** → Gestão de acessos, autenticação e quotas.
- 🎨 **Interfaces** → Textual (CLI) ou Gráfica (GUI).
- 🏢 **Máquina Virtual** → Emulação de hardware via software.

---

## 🏛️ **3. Evolução dos Sistemas Operativos**

### 🖥️ **Primeiros Computadores (1950)**

- 📏 Eram de grande dimensão e de difícil utilização.
- 🏗️ Programas inseridos por cartões perfurados.

### 🎛️ **Monitores de Controlo**

- ❌ Não eram verdadeiros sistemas operativos.
- ⚙️ Executavam utilitários básicos como tradutores e editores.
- ⏳ **Ineficientes**, pois o processador ficava inativo frequentemente.

### 📦 **Tratamento por Lotes (Batch Processing)**

- ✅ Melhorava a eficiência da CPU ao executar programas sem interação do utilizador.
- 💾 Introduziu o **spooling** → armazenamento temporário de operações em disco.

### 🔄 **Multi-programação**

- 🔄 **Execução intercalada** de múltiplos programas com interrupções.
- 📋 Introdução de **escalonamento de tarefas** (scheduling).

#### 🖥️ **Sistemas Multi-programados**

- 🏁 **Interativos** → Permitem interação do utilizador em tempo real.
- 🏎️ **Paralelos** → Utilizam múltiplos processadores.
- 🌍 **Distribuídos** → Computação distribuída por vários computadores via rede.

---

## 🎯 **Perguntas e Respostas**

### ❓ O que é um sistema operativo?

✅ Um programa que controla a interação entre utilizadores e hardware, permitindo a execução eficiente de programas.

### ❓ Qual a diferença entre um **processo** e um **programa**?

- 📁 **Programa**: Arquivo armazenado no sistema de ficheiros (passivo).
- ⚙️ **Processo**: Execução ativa de um programa na memória.

### ❓ Como funciona a **memória virtual**?

🧠 A memória virtual combina a RAM e o disco para permitir a execução de mais programas do que caberiam fisicamente na RAM.

### ❓ Qual a função do **spooling** nos sistemas de lote?

💾 O **spooling** usa o disco como um buffer para armazenar temporariamente operações de entrada/saída, aumentando o desempenho global.

### ❓ O que distingue **sistemas operativos paralelos** dos distribuídos?

- ⚡ **Paralelos** → Usam múltiplos processadores **no mesmo computador**.
- 🌍 **Distribuídos** → Computação distribuída **entre vários computadores** conectados via rede.

___
# 🖥️ **Processos nos Sistemas Operativos**

## 📌 **1. Introdução**

- 🕹️ **Inicialmente**, apenas um programa era executado por vez, com acesso total ao hardware.
- 🔄 **Hoje em dia**, vários programas podem ser executados simultaneamente.
- 🛠️ **Execução concorrente** exige mecanismos de controlo.
- 🏗️ **Processo** → Programa em execução, unidade fundamental dos sistemas operativos modernos.

---

## 🔑 **2. O que é um Processo?**

### 📂 **Representação de um Processo**

- 📜 Contém código do programa.
- 🗂️ **Stack** → Armazena variáveis locais e argumentos de funções.
- 📍 **Program Counter** → Indica a próxima instrução a executar.
- 📊 **Recursos associados** → Tabelas de ficheiros, sinais, entre outros.

### 🔄 **Estados de um Processo**

1️⃣ **Novo (New)** → Criado, mas ainda não executado.  
2️⃣ **Execução (Running)** → Está a ser processado pela CPU.  
3️⃣ **Espera (Waiting)** → Aguarda operação de I/O.  
4️⃣ **Pronto (Ready)** → Apto para execução, mas aguardando a CPU.  
5️⃣ **Terminado (Terminated)** → Finalizou a execução.

### 🗃️ **PCB (Process Control Block)**

- 📊 **Estrutura de dados** usada pelo SO para armazenar informações do processo.
- Contém:
    - Estado atual.
    - Program Counter.
    - Registos da CPU.
    - Informação de escalonamento.
    - Memória usada.
    - Ficheiros abertos e dispositivos I/O.

---

## ⚖️ **3. Escalonamento de Processos**

### 🎛️ **O que é Escalonamento?**

- 🏗️ **Sistemas multi-programados** alternam entre processos em execução.
- 🎯 O **dispatcher** escolhe a ordem de execução dos processos.
- 🖥️ **Apenas um processo por núcleo da CPU pode ser executado ao mesmo tempo!**

### 📋 **Filas de Escalonamento**

- **Processos aguardam em filas** até serem executados.
- 🔄 Tipos de filas:
    - 📌 **Fila de processos prontos** → Processos aptos para execução.
    - ⏳ **Fila de espera** → Processos aguardando operações de I/O.

### ⏳ **Tipos de Escalonamento**

- **Curto prazo** → Decide qual processo executa a seguir (milissegundos).
- **Médio prazo** → Decide quais processos devem permanecer em memória.
- **Longo prazo** → Controla quantos processos podem estar ativos.

### 🔄 **Mudança de Contexto**

- 🏗️ Processo ativo **é substituído** por outro.
- 🕑 Pode demorar **1 a 1000 µs**.
- 🎯 **Threads** minimizam essa latência.

---

## 🧮 **4. Algoritmos de Escalonamento**

### 🏁 **First-Come, First-Served (FCFS)**

- 🛣️ **Executa os processos por ordem de chegada**.
- 🚦 Simples, mas pode levar a **longos tempos de espera**.

### ⏳ **Shortest-Job-First (SJF)**

- 📊 Prioriza **processos mais curtos primeiro**.
- 📉 **Menor tempo médio de espera**.
- 🔄 Versão preemptiva → **Shortest-Remaining-Time-First (SRTF)**.

### 🎖️ **Escalonamento por Prioridade**

- 🏅 Cada processo recebe uma **prioridade**.
- ⛔ Problema → **Starvation** (processos de baixa prioridade podem nunca ser executados).
- 🔄 Solução → **Aging** (aumenta a prioridade ao longo do tempo).

### 🔄 **Round-Robin (RR)**

- 🏗️ **Cada processo recebe um tempo fixo (quantum)**.
- 🔄 **Se não terminar a tempo, volta para o final da fila**.
- 🏎️ Pequeno quantum → Muitas mudanças de contexto.
- 🏗️ Grande quantum → Comportamento semelhante ao FCFS.

---

## 🎯 **Perguntas e Respostas**

### ❓ O que é um **Processo**?

✅ Um **programa em execução**, que contém código, dados, memória e estado.

### ❓ Qual a diferença entre **Processo e Programa**?

- 📁 **Programa** → Arquivo armazenado no sistema de ficheiros (passivo).
- ⚙️ **Processo** → Instância ativa de um programa na memória.

### ❓ O que é um **PCB** e para que serve?

📊 **Process Control Block (PCB)** é uma estrutura de dados que contém todas as informações do processo, como estado, registradores e prioridade.

### ❓ O que é **Mudança de Contexto**?

🔄 O processo de **substituir um processo em execução por outro**, salvando o estado do primeiro e carregando o estado do próximo.

### ❓ Qual a vantagem do **Round-Robin (RR)**?

⚡ Permite um **uso mais justo da CPU**, alternando rapidamente entre processos.

### ❓ Qual o problema do **Escalonamento por Prioridade**?

⛔ **Starvation** → Processos de baixa prioridade podem nunca ser executados.

___
# 🔄 **Comunicação entre Processos**

## 📌 **1. Conceitos Introdutórios**

### 🤝 **O que é um Processo Cooperativo?**

- Processo que **partilha dados** com outros processos.
- Pode ser afetado ou afetar outros processos.

### 📂 **Partilha de Memória vs. Troca de Mensagens**

- 🗂️ **Partilha de Memória** → Apenas entre processos no mesmo PC.
- 📡 **Troca de Mensagens** → Utilizada quando processos não compartilham o mesmo espaço de memória.
- 🌍 **Exemplo Global** → Internet utiliza **pacotes IP** para troca de mensagens.

---

## ✉️ **2. Troca de Mensagens**

- Mecanismo que **permite a comunicação entre processos sem partilha de memória**.
- 📤 **Principais operações**:
    - `enviar(mensagem)`
    - `receber(mensagem)`
- 📝 As mensagens podem ter **tamanho fixo** (mais fácil de implementar) ou **tamanho variável** (mais flexível).

### 🔗 **Ligação entre Processos**

- Para trocar mensagens, os processos precisam de uma **ligação**.
- Tipos de comunicação:
    - 📍 **Direta ou Indireta**
    - ⏳ **Síncrona ou Assíncrona**
    - 📦 **Com Buffering ou Sem Buffering**

---

## 📍 **3. Endereçamento**

### 🎯 **Endereçamento Direto**

- Cada processo deve **conhecer o endereço** do outro processo.
- 🔄 Dois tipos:
    - **Simétrico** → Ambos precisam conhecer os endereços um do outro.
    - **Assimétrico** → Processo pode receber mensagens **sem conhecer o emissor**.

### 📡 **Endereçamento Indireto**

- Utiliza um **repositório de mensagens** (buffer externo).
    
- 📤 Exemplo:
    
    - `enviar(repositório, mensagem)` → Armazena a mensagem.
    - `receber(repositório, mensagem)` → Recupera a mensagem.
- ⚡ **Características**:
    
    - Processos só comunicam se **partilharem um repositório**.
    - Um repositório pode ser usado por **vários processos**.
    - 🔧 **Repositórios podem ser geridos pelo sistema operativo ou pelos processos**.

---

## ⏳ **4. Sincronismo**

- 📩 A comunicação ocorre por meio das funções `enviar()` e `receber()`.
    
- **Dois tipos de implementação**:  
    1️⃣ **Bloqueante (Síncrona)**
    
    - `enviar()` bloqueia até a mensagem ser recebida.
    - `receber()` bloqueia até haver uma mensagem disponível.  
        2️⃣ **Não bloqueante (Assíncrona)**
    - `enviar()` envia e continua a execução.
    - `receber()` verifica se há mensagens, mas não espera.
- 🔄 **Se ambas as funções forem bloqueantes → ocorre o "Rendezvous"** (os dois processos devem estar ativos para comunicar).
    

---

## 📦 **5. Buffering**

- 🎛️ **Buffering** → Utiliza **filas (queues)** para armazenar mensagens temporariamente.
- 📌 **Tipos de Filas**:  
    1️⃣ **Capacidade Zero**
    - Nenhuma mensagem armazenada.
    - ❗ O emissor **bloqueia** até que a mensagem seja lida.
    - **Implica rendezvous**.  
        2️⃣ **Capacidade Limitada**
    - Armazena **um número finito** de mensagens.
    - Se a fila estiver cheia, o **emissor precisa esperar**.  
        3️⃣ **Capacidade Ilimitada**
    - Armazena **mensagens sem limite**.
    - 🚀 **O emissor nunca bloqueia**.

---

## 🎯 **Perguntas e Respostas**

### ❓ O que é um **processo cooperativo**?

✅ Um processo que **partilha dados** com outros processos e pode ser afetado por eles.

### ❓ Como se comunicam processos que não compartilham memória?

📡 Através de **troca de mensagens**, utilizando `enviar()` e `receber()`.

### ❓ Qual a diferença entre **endereçamento direto e indireto**?

- 📍 **Direto** → Processos comunicam **diretamente** entre si.
- 🗂️ **Indireto** → Utilizam **um repositório externo** para armazenar mensagens.

### ❓ Qual a diferença entre **comunicação síncrona e assíncrona**?

- ⏳ **Síncrona (bloqueante)** → O processo espera até a comunicação ocorrer.
- 🚀 **Assíncrona (não bloqueante)** → O processo continua a execução mesmo sem receber a resposta imediatamente.

### ❓ Quais são os **tipos de buffering**?

- 📌 **Capacidade Zero** → Mensagem só é enviada se for lida imediatamente.
- 📦 **Capacidade Limitada** → Armazena um número fixo de mensagens.
- 🚀 **Capacidade Ilimitada** → Armazena infinitas mensagens sem bloquear o emissor.

___
# 🔄 **Sincronização de Processos**

## 📌 **1. Introdução**

- 🏗️ **Processos e threads** podem compartilhar espaço de endereçamento, memória, ficheiros, etc.
- ⚠️ Quando vários processos acedem ao mesmo recurso simultaneamente, ocorre uma **situação de competição** (**race condition**).
- 🛠️ Para evitar problemas, é necessário **sincronizar a execução** dos processos concorrentes.

---

## ⚔️ **2. Situações de Competição (Race Condition)**

- 🚦 O resultado da execução pode ser **imprevisível**, dependendo da ordem em que os processos acessam o recurso.
- 💡 **Exemplo (Java)**
    - Dois processos tentam alterar a mesma variável ao mesmo tempo, causando **inconsistência de dados**.

📌 **Solução** → Necessário **controlar o acesso à secção crítica** para evitar erros.

---

## 🔐 **3. Secção Crítica**

### 🔍 **O que é a Secção Crítica?**

- Trecho do código onde **vários processos ou threads acessam um recurso partilhado**.
- Se mais de um processo executar essa secção simultaneamente → **Erro!**

### ✅ **Requisitos para resolver o problema da Secção Crítica**

1️⃣ **Exclusão Mútua** → Apenas **um processo por vez** pode executar a secção crítica.  
2️⃣ **Progresso** → Se nenhum processo está na secção crítica, um deles deve poder entrar **sem atrasos desnecessários**.  
3️⃣ **Espera Limitada** → Deve haver um **tempo máximo de espera** para cada processo acessar a secção crítica.

### ⚠️ **Problemas Possíveis**

- ❌ **Deadlock (Impasse)** → Dois ou mais processos ficam bloqueados esperando por recursos.
- ❌ **Starvation (Míngua)** → Um processo **nunca recebe acesso** ao recurso porque outros processos de maior prioridade o impedem.

---

## 🛠️ **4. Soluções para Sincronização**

### 📜 **1️⃣ Soluções de Software**

- Programadores criam **algoritmos de sincronização**.
- Exemplo: **Algoritmo de Peterson** → Garante exclusão mútua usando **flags e variáveis de controle**.

### ⚙️ **2️⃣ Soluções de Hardware**

- Usa **instruções do processador** para bloquear acessos simultâneos.
- 🚫 **Problema** → Depende do hardware específico.

### 🖥️ **3️⃣ Soluções do Sistema Operativo**

- SO fornece **mecanismos nativos**, como **semáforos** e **monitores**.
- ✅ **Vantagem** → Mais eficientes, não exigem espera ativa.

---

## 🚦 **5. Semáforos**

- 📌 **Estrutura de sincronização** usada para controlar o acesso de múltiplos processos a um recurso partilhado.
- 🔄 **Funções principais**:
    - `wait()` → Bloqueia o processo até o recurso estar disponível.
    - `signal()` → Libera o recurso.
- 🚨 **Problema de Deadlock** → Pode ocorrer se os processos chamarem `wait()` em **ordens diferentes**.

📌 **Exemplo de Deadlock com Semáforos**

```cpp
P0: wait(Sem1); wait(Sem2);
P1: wait(Sem2); wait(Sem1);
```

→ ❗ Ambos os processos ficam **bloqueados indefinidamente**.

## 🤔 **6. Problemas Clássicos de Sincronização**

### 🍽️ **1️⃣ O Jantar dos Filósofos**

- 5 filósofos compartilham uma mesa e precisam de **dois garfos** para comer.
- ⚠️ Se todos pegarem um garfo ao mesmo tempo, ocorre **deadlock**.

📌 **Soluções**

- 🏁 **Sol. 1** → Esperar pelos dois garfos antes de pegar (pode gerar impasse).
- ⏳ **Sol. 2** → Esperar tempo aleatório antes de tentar novamente (**reduz, mas não elimina starvation**).
- ✅ **Sol. 3** → **Semáforos para coordenar o acesso**.

### 📖 **2️⃣ Problema dos Leitores e Escritores**

- 📝 **Leitores** podem acessar simultaneamente um recurso.
- ✍️ **Escritores** precisam de **acesso exclusivo**.
- ⚠️ Se houver **muitos leitores**, um escritor pode **ficar bloqueado indefinidamente**.

📌 **Solução** → **Semáforos para garantir prioridade** ao escritor quando necessário.

### 💈 **3️⃣ O Problema do Barbeiro Adormecido**

- 💇 O barbeiro dorme quando **não há clientes**.
- Se um cliente chega e **não há cadeiras vazias**, ele **vai embora**.
- ⚠️ Necessário **sincronizar** cliente e barbeiro para evitar falhas.

📌 **Solução** → **Semáforos garantem que o barbeiro dorme apenas quando não há clientes**.

---

## 🎯 **Perguntas e Respostas**

### ❓ O que é uma **situação de competição**?

✅ Quando múltiplos processos acessam um recurso partilhado **simultaneamente**, podendo gerar resultados imprevisíveis.

### ❓ O que é a **secção crítica**?

📌 Trecho do código onde **processos ou threads** acessam um recurso partilhado.

### ❓ Quais são os três requisitos para resolver o problema da **secção crítica**?

1️⃣ **Exclusão Mútua** → Apenas um processo pode acessar de cada vez.  
2️⃣ **Progresso** → Não pode haver atrasos desnecessários.  
3️⃣ **Espera Limitada** → Nenhum processo pode esperar indefinidamente.

### ❓ O que é um **deadlock**?

⚠️ Situação onde **dois ou mais processos ficam bloqueados indefinidamente**, esperando por um recurso que nunca será liberado.

### ❓ Como funcionam os **semáforos**?

🚦 São mecanismos que regulam o acesso a recursos partilhados, através das funções `wait()` (bloquear) e `signal()` (liberar).

### ❓ Como resolver o **problema dos leitores e escritores**?

📌 Utilizando **semáforos** para garantir que **escritores tenham prioridade**, evitando starvation.

### ❓ Qual o principal problema do **Jantar dos Filósofos**?

❗ Se todos pegarem um garfo ao mesmo tempo, pode ocorrer **deadlock**.

### ❓ Como evitar que o **barbeiro adormecido** perca clientes?

✅ **Semáforos** garantem que o barbeiro **só dorme se não houver clientes**.

___
# 🔄 **Deadlocks em Sistemas Operativos**

## 📌 **1. Introdução**

- 🏗️ **Deadlock (Bloqueio)** ocorre quando **processos concorrentes ficam bloqueados indefinidamente** porque cada um espera por um recurso retido por outro.
- 🔄 **Ciclo de Espera** → Nenhum processo pode continuar sem que outro libere um recurso.
- 🎛️ Recursos podem ser:
    - **Físicos** (impressora, memória, CPU).
    - **Lógicos** (ficheiros, secções críticas).

### 📌 **Exemplo Clássico: Semáforos**

- Dois processos (P1 e P2) usam dois semáforos (A e B), onde:
    - P1: `down(A); down(B);`
    - P2: `down(B); down(A);`
- ❗ Ocorre **deadlock** porque cada processo espera um recurso retido pelo outro.

---

## ⚙️ **2. Condições Necessárias para Deadlocks**

Um **deadlock só ocorre** se TODAS estas condições forem verdadeiras:

1️⃣ **Exclusão Mútua** → Um recurso pode ser usado por apenas **um processo por vez**.  
2️⃣ **Retenção e Espera** → Um processo que já detém recursos pode **esperar por mais**.  
3️⃣ **Não Preempção** → Um recurso **não pode ser retirado à força** de um processo.  
4️⃣ **Espera Circular** → Deve existir um ciclo **P1 → P2 → P3 → Pn → P1**, onde cada processo espera pelo próximo.

🔍 **Se pelo menos uma destas condições for evitada, o deadlock não acontece!**

---

## 🛠️ **3. Estratégias de Tratamento de Deadlocks**

1️⃣ **Prevenção** → **Garante que pelo menos uma das 4 condições nunca ocorre**.  
2️⃣ **Evitação** → **Permite** as condições, mas **evita estados perigosos**.  
3️⃣ **Deteção e Recuperação** → Permite que deadlocks ocorram e depois resolve o problema.  
4️⃣ **Ignorar o Problema** → Método usado pelo **UNIX**, onde apenas nega pedidos quando não há recursos disponíveis.

---

## 🚧 **4. Prevenção de Deadlocks**

### 🔥 **1️⃣ Eliminar Exclusão Mútua**

- **Impossível para certos recursos**, como impressoras.
- **Spooling** pode minimizar o problema (exemplo: enviar documentos para uma fila de impressão).

### 🛑 **2️⃣ Evitar Retenção e Espera**

- O processo deve **solicitar TODOS os recursos no início**.
- ❌ **Ineficiente** → Leva a **baixo aproveitamento** dos recursos.

### 🔄 **3️⃣ Permitir Preempção**

- Forçar a **libertação de recursos** quando há risco de deadlock.
- **Impossível em certos casos** (exemplo: não se pode interromper uma gravação de DVD sem perda de dados).

### 🔁 **4️⃣ Quebrar a Espera Circular**

- **Ordenar os recursos** e exigir que os processos os solicitem **sempre na mesma ordem**.
- 🚫 **Problema** → **Nem sempre é eficiente**, pois pode negar acesso a recursos desnecessariamente.

---

## 🚦 **5. Evitação de Deadlocks**

- Permite as **quatro condições**, mas impede que o sistema entre em um estado inseguro.
- 🎯 **Conceito de Estado Seguro**
    - O **sistema está seguro** se for **capaz de atender todas as requisições** sem criar um ciclo de espera.
    - Se houver risco de bloqueio → **Recursos não são concedidos**.

### 🔢 **Técnicas de Evitação**

1️⃣ **Grafo de Alocação de Recursos**

- **Verifica ciclos periodicamente**.
- **Funciona bem apenas para sistemas com uma única instância de cada recurso**.  
    2️⃣ **Algoritmo do Banqueiro**
- ✅ **Baseado em prever o uso futuro de recursos**.
- ❌ **Desvantagem** → Requer conhecimento prévio das necessidades máximas de cada processo.

---

## 🔍 **6. Deteção e Recuperação de Deadlocks**

- Permite que deadlocks ocorram, **detectando e resolvendo-os depois**.
- **Métodos de deteção**:
    - 🕵️‍♂️ **Grafo de Espera por Recursos** → Identifica ciclos (deadlocks).
    - 📊 **Algoritmo baseado no estado do sistema**.

### 🛑 **Métodos de Recuperação**

1️⃣ **Encerrar Processos**

- **Matar todos os processos envolvidos** ou **finalizar processos um por um**.
- 🔴 **Problema** → Pode causar perda de trabalho importante.  
    2️⃣ **Remover Recursos (Preempção)**
- **Retirar recursos de processos bloqueados**.
- ❌ **Problema** → Nem todos os recursos podem ser preemptados (exemplo: uma impressão em andamento).  
    3️⃣ **Rollback**
- 🕰️ **Reverter processos a um estado anterior**.
- 🚫 **Dificuldade** → Nem todos os sistemas suportam rollback eficiente.

---

## 🤝 **7. Estratégia Combinada**

- Nenhuma solução **é perfeita para todos os casos**.
- Sistemas modernos usam **várias abordagens em conjunto**.
- **Exemplo:**
    - UNIX ignora deadlocks em processos de utilizador, mas protege processos críticos do sistema.

---

## 🎯 **Perguntas e Respostas**

### ❓ O que é um **deadlock**?

✅ Situação onde **processos ficam bloqueados indefinidamente**, cada um esperando um recurso que outro detém.

### ❓ Quais são as **quatro condições necessárias** para um deadlock ocorrer?

1️⃣ **Exclusão Mútua** → Apenas um processo pode usar um recurso por vez.  
2️⃣ **Retenção e Espera** → Processos mantêm recursos enquanto esperam por mais.  
3️⃣ **Não Preempção** → Recursos não podem ser retirados à força.  
4️⃣ **Espera Circular** → Cada processo espera um recurso detido pelo próximo.

### ❓ Como prevenir um deadlock?

✅ **Eliminar pelo menos uma das 4 condições** → Exemplo: evitar espera circular, permitir preempção.

### ❓ Qual a diferença entre **prevenção** e **evitação** de deadlocks?

- **Prevenção** → **Garante** que pelo menos uma das quatro condições **nunca ocorre**.
- **Evitação** → **Aceita as condições**, mas **evita entrar em estados inseguros**.

### ❓ Como funciona o **Algoritmo do Banqueiro**?

📊 Avalia **se a atribuição de um recurso pode levar a um estado inseguro**.

- **Se houver risco de deadlock, a requisição é negada!**

### ❓ Como detectar um deadlock?

- 🔄 **Grafo de Espera por Recursos** → Identifica ciclos.
- 📊 **Monitoramento do estado do sistema**.

### ❓ Como recuperar de um deadlock?

1️⃣ **Encerrar processos** (remover processos bloqueados).  
2️⃣ **Remover recursos** (preempção).  
3️⃣ **Rollback** (reverter processos ao estado anterior).

___
# 🖥️ **Gestão de Memória em Sistemas Operativos**

## 📌 **1. Introdução**

- 🎛️ A **gestão de memória** é responsável por administrar a alocação e liberação da memória dos processos.
- 🛠️ O **Gestor de Memória** controla:
    - 📌 **Que partes da memória estão ocupadas e livres**.
    - 📌 **Alocação de memória para novos processos**.
    - 📌 **Liberação de memória de processos encerrados**.
    - 📌 **Troca de processos entre memória RAM e disco (Swapping)**.
- 🔍 **Técnicas modernas** incluem:
    - **Swapping** → Movimentação de processos entre RAM e disco.
    - **Paging (Memória Virtual)** → Garante melhor aproveitamento da RAM.

---

## ⚙️ **2. Técnicas de Gestão de Memória**

### 🟢 **1️⃣ Gestão de Memória Básica**

- 🖥️ Apenas um processo é executado de cada vez.
- 🔄 A memória é dividida em:
    - Parte para o **Sistema Operativo**.
    - Parte para o **processo do utilizador**.
- ❗ **Limitação** → Apenas um processo pode estar na memória de cada vez.

![[Pasted image 20250114094755.png]]

---

### 🟢 **2️⃣ Partições Fixas**

- 🏗️ Memória dividida em **partições de tamanho fixo**.
- 📌 Um processo só pode ser alocado a uma partição disponível.
- 🚫 **Desvantagem** → **Fragmentação interna** (espaço desperdiçado dentro das partições).

🔄 **Modos de Alocação**:  
1️⃣ **Múltiplas Filas** → Cada partição tem uma **fila própria** de processos esperando alocação.  
2️⃣ **Fila Única** → Todos os processos esperam em uma única fila e são alocados conforme disponibilidade.

![[Pasted image 20250114094737.png]]


---

### 🔵 **3️⃣ Swapping**

- 🔄 Permite alternar processos entre **RAM e disco** para maximizar o uso da memória.
- 🚀 **Swap In** → Processo movido do disco para a memória.
- ❌ **Swap Out** → Processo movido da memória para o disco.
- 🔴 **Problemas**:
    - **Realocação** → O endereço de memória precisa ser atualizado dinamicamente.
    - **Proteção** → Um processo **não pode acessar** a memória de outro.

---

### 🟡 **4️⃣ Partições Variáveis**

- 🏗️ Permite criar partições **de tamanho variável**, otimizando o espaço disponível.
- ❗ **Problema** → Pode causar **fragmentação externa** (pequenos espaços livres entre processos).
- 📌 **Boa prática** → Reservar **mais memória do que o necessário inicialmente** para evitar realocações constantes.
![[Pasted image 20250114094654.png]]


![[Pasted image 20250114094721.png]]


---

### 🔵 **5️⃣ Mapas de Bits (Bitmaps)**

- 🗺️ Memória dividida em **unidades de alocação**.
- 📊 Cada unidade é representada por um **bit** no mapa:
    - `1` → Ocupado
    - `0` → Livre
- 🚫 **Desvantagem** → Pesquisa por espaço livre pode ser lenta.

![[Pasted image 20250114094638.png]]

---

### 🟠 **6️⃣ Listas Ligadas**

- 📑 Usa uma **lista de segmentos** para representar áreas de memória **livres e ocupadas**.
- 📌 Pode ser melhorada com **diferentes estratégias de alocação**:  
    1️⃣ **First Fit** → Aloca no primeiro espaço livre encontrado.  
    2️⃣ **Next Fit** → Como o First Fit, mas começa a busca a partir do último local alocado.  
    3️⃣ **Best Fit** → Encontra o menor espaço possível para o processo (causa fragmentação).  
    4️⃣ **Worst Fit** → Usa o maior bloco de memória disponível (pode ser mais eficiente).


![[Pasted image 20250114094449.png]]

---

### 🔴 **7️⃣ Algoritmo Buddy**

- 🔄 Baseia-se em dividir a memória em **potências de 2** (1KB, 2KB, 4KB, ...).
- 🏗️ Quando um processo precisa de memória:
    - **O menor bloco possível** é alocado.
    - Se necessário, um bloco maior é **dividido em dois** (buddies).
- 🚀 **Vantagem** → Fácil fusão de espaços livres.
- 🚫 **Desvantagem** → Pode levar a **desperdício de memória** devido ao arredondamento para potências de 2.

![[Pasted image 20250114094407.png]]

---

## 💾 **8. Reserva de Espaço em Disco**

- 🔍 Alguns sistemas reservam **espaço no disco** para processos que são movidos para swap.
- 📂 Métodos de armazenamento:
    - **Reserva fixa** → O espaço é alocado quando o processo é criado.
    - **Reserva dinâmica** → O espaço só é reservado quando necessário.
- 📌 Os algoritmos de **gestão de memória** são **os mesmos** para gestão de processos no disco.

---

## 🎯 **Perguntas e Respostas**

### ❓ O que faz o **Gestor de Memória**?

✅ Gerencia a alocação e liberação da memória para processos, garantindo eficiência e segurança.

### ❓ O que é **Swapping**?

🔄 Mecanismo que move processos entre **RAM e disco** para melhor aproveitamento da memória.

### ❓ Qual a diferença entre **partições fixas e variáveis**?

- **Fixas** → Partições **de tamanho fixo**, causando **fragmentação interna**.
- **Variáveis** → Partições **ajustáveis**, evitando fragmentação interna, mas gerando **fragmentação externa**.

### ❓ O que é **fragmentação interna e externa**?

- **Interna** → Quando um processo não usa todo o espaço de sua partição.
- **Externa** → Pequenos blocos de memória livres **não utilizáveis** para novos processos.

### ❓ Como funciona o **Algoritmo Buddy**?

🟢 Divide a memória em blocos de **potências de 2** e os fragmenta conforme necessário, facilitando a fusão de espaços livres.

### ❓ Como funciona a **alocação por listas ligadas**?

📂 A memória é gerenciada por uma **lista de segmentos livres e ocupados**. Métodos incluem:  
1️⃣ **First Fit** → Primeiro espaço livre.  
2️⃣ **Best Fit** → Espaço mais próximo do tamanho necessário.  
3️⃣ **Worst Fit** → Maior espaço disponível.

### ❓ Qual a vantagem e a desvantagem do **uso de mapas de bits**?

✅ **Vantagem** → Simples e direto.  
❌ **Desvantagem** → **Lento**, pois precisa **percorrer toda a memória** para encontrar espaço disponível.

---
# 🖥️ **Memória Virtual em Sistemas Operativos**

## 📌 **1. Introdução**

- 📂 A **Memória Virtual** permite que programas **maiores que a memória física** sejam executados.
- 🏗️ **Conceito criado em 1961**, baseia-se em **carregar apenas partes do programa na RAM**, mantendo o restante no disco.
- 🚀 **Benefícios**:
    - **Permite multiprogramação** (vários programas em execução).
    - **Expande artificialmente a memória RAM** usando o disco.

📌 **Exemplo** → Um programa de 1MB pode ser executado em um sistema com **apenas 256KB** de RAM, desde que se carreguem apenas **as partes necessárias**.

---

## ⚙️ **2. Técnicas de Memória Virtual**

### 🟢 **1️⃣ Overlay (Sobreposição)**

- 📦 **Primeira técnica usada para memória virtual**.
- 📌 **Divide um programa em segmentos menores** (overlays).
- 🔄 O **Sistema Operativo carrega e substitui** segmentos dinamicamente conforme necessário.
- 🚨 **Problema** → O **programador** era responsável pela divisão dos overlays, o que tornava o processo **manual e complexo**.

---

### 🔵 **2️⃣ Paging (Paginação)**

- 📌 **Técnica mais comum de memória virtual**.
- 🏗️ Baseia-se na divisão da memória em **páginas (pages)** e **quadros de página (page frames)**.
- 📊 **Tamanho padrão** → Entre **512 bytes e 8 KB** por página.
- 🎯 **Funciona com a MMU (Memory Management Unit)**, que **converte endereços virtuais em físicos**.

📌 **Exemplo** →  
1️⃣ Um programa **gera um endereço virtual** (ex: 1000).  
2️⃣ A **MMU traduz** esse endereço para um **endereço físico** na RAM.  
3️⃣ Se a página não estiver na RAM → **Ocorre um Page Fault** (interrupção para carregar a página do disco).

![[Pasted image 20250114095009.png]]


![[Pasted image 20250114095050.png]]



---

## 🖥️ **3. Funcionamento do Paging**

### 🔍 **Espaço de Endereçamento Virtual**

- 📂 Os endereços são divididos em **páginas virtuais**.
- 🏗️ Cada página virtual **é associada a um quadro físico** na RAM.
- 🗂️ **O Sistema Operativo mantém uma tabela de páginas** para rastrear as associações.

📌 **Exemplo (64K de Memória Virtual, 32K de RAM)**:

- Um processo tenta acessar **o endereço virtual 0**.
- A MMU identifica que **a página virtual 0 corresponde ao quadro físico 2 (8192-12287)**.
- O **endereço virtual é convertido em um endereço físico** (exemplo: 8192).

---

## ⚠️ **4. Page Fault (Falha de Página)**

- 📌 Ocorre quando um programa tenta acessar **uma página que não está na RAM**.
- 🚨 **O Sistema Operativo deve carregar a página do disco para a memória RAM**.
- 🔄 Para liberar espaço, a SO **substitui uma página menos usada**.

📌 **Solução**:

- O SO **ajusta o espaço de endereçamento virtual** para garantir que o programa continue executando normalmente.

---

## 🎛️ **5. MMU (Memory Management Unit)**

- 🖥️ Componente do processador **responsável pela conversão de endereços virtuais em físicos**.
- 📌 **Funções principais**:
    - 🎯 Mapeia **endereços virtuais para físicos**.
    - 🔄 **Controla acesso à memória** para evitar erros de proteção.
    - 🏗️ Garante a execução eficiente do paging.

📌 **Exemplo de Tradução**:  
1️⃣ **Endereço virtual**: 8196 (0010000000000100)  
2️⃣ **MMU converte para endereço físico**: 24580 (110000000000100)  
3️⃣ **O barramento de memória recebe o endereço físico e acessa os dados na RAM**.

---

## 🎯 **Perguntas e Respostas**

### ❓ O que é **Memória Virtual**?

✅ Técnica que **permite rodar programas maiores que a RAM disponível**, carregando apenas partes do programa na memória e armazenando o restante no disco.

### ❓ Qual a diferença entre **Overlay e Paging**?

- **Overlay** → Exige que o **programador divida o programa** em segmentos pequenos.
- **Paging** → **Automático**, o SO gerencia páginas e quadros dinamicamente.

### ❓ O que é um **Page Fault**?

🔄 **Falha de Página** ocorre quando um processo **tenta acessar uma página que não está na RAM**, exigindo que o SO **a carregue do disco**.

### ❓ Qual a função da **MMU (Memory Management Unit)?**

📌 **Traduz endereços virtuais em físicos** e controla o acesso à memória, garantindo que os processos **não acessem áreas não permitidas**.

### ❓ Como o sistema operacional resolve um Page Fault?

1️⃣ **Escolhe uma página menos usada** para ser removida.  
2️⃣ **Carrega a nova página do disco para a RAM**.  
3️⃣ **Atualiza a tabela de páginas** e reinicia a execução do processo.

### ❓ Como funciona a conversão de endereços na **Memória Virtual**?

📌 Um **endereço virtual** é mapeado para um **endereço físico na RAM** através da MMU e da **tabela de páginas**.

___
# 🔒 **Princípios de Segurança em Sistemas Operativos**

## 📌 **1. Introdução**

- 🖥️ Os **Sistemas Operativos** devem garantir **segurança** na partilha de recursos entre utilizadores.
- 🔍 **Objetivos principais da segurança em SO**:
    - **Proteção da memória** → Evitar acesso não autorizado.
    - **Modos CPU** → Separação entre processos do sistema e do utilizador.
    - **Autenticação de utilizador** → Garantir que apenas usuários autorizados acessem o sistema.
    - **Controlo de acessos** → Definir permissões sobre arquivos e recursos.

---

## ⚠️ **2. Modelos de Ameaça em Segurança**

- 🌍 Com a evolução dos computadores, as **ameaças de segurança também evoluíram**:  
    1️⃣ **Computadores compartilhados** → Separação entre processos de diferentes utilizadores.  
    2️⃣ **Computadores em rede** → Ataques através da Internet.  
    3️⃣ **Dispositivos móveis** → Aplicações maliciosas podem comprometer a segurança pessoal.

---

## 🛡️ **3. Mecanismos de Segurança em Sistemas Operativos**

🔐 Para mitigar ameaças, os SO utilizam:

- ✅ **Autenticação** → Verificação da identidade do utilizador.
- ✅ **Controlo de Acessos** → Define **quem pode acessar o quê**.
- ✅ **Comunicação Segura** → Criptografia protege informações confidenciais.
- ✅ **Registos e Auditoria** → Monitoramento de atividades suspeitas.
- ✅ **Prevenção e Deteção de Intrusões** → Proteção contra ataques.
- ✅ **Recuperação** → Medidas para restaurar o sistema após um ataque.

---

## 🏗️ **4. Proteção da Memória**

### 🔍 **Objetivo**:

- 🚫 Impedir que processos **acessam áreas de memória** de outros processos.
- ✅ **Mecanismos utilizados**:
    - **Realocação de memória** → Cada processo recebe seu próprio espaço protegido.
    - **Registos base/limites** → Definem o espaço acessível pelo processo.
    - **Segmentação e Paginação** → Organizam a memória de forma segura.

---

## ⚙️ **5. Modos de CPU**

- 🎛️ A CPU pode operar em diferentes **modos de execução**, garantindo segurança:
    - 🔵 **Modo Kernel (Modo Supervisor)**
        - Pode acessar **todos os recursos do sistema**.
        - Executa **funções críticas do SO**.
        - Pode **habilitar/desabilitar interrupções**.
    - 🟢 **Modo Utilizador**
        - **Restringido** (não pode acessar a memória do sistema).
        - Não pode modificar **registos críticos da CPU**.
        - Só pode aceder a recursos do SO através de **Chamadas ao Sistema**.

📌 **Mudança de Modo**

- A CPU **troca do Modo Utilizador para o Modo Kernel** através de **Chamadas ao Sistema**.

---

## 📞 **6. Chamadas ao Sistema (System Calls)**

🔗 **Chamadas ao sistema** são a **única forma de um programa em Modo Utilizador acessar funções do SO**.

- 📌 **Funcionamento**:  
    1️⃣ O programa faz uma chamada ao sistema.  
    2️⃣ A CPU gera uma **interrupção** para mudar para o **Modo Kernel**.  
    3️⃣ O SO verifica se o pedido é permitido.  
    4️⃣ Se autorizado, o sistema executa a ação e retorna ao **Modo Utilizador**.

📂 **Exemplo de chamadas ao sistema**:

- `open()` → Abrir ficheiro.
- `read()` → Ler dados.
- `write()` → Escrever em ficheiro.
- `exit()` → Encerrar processo.

---

## 🔑 **7. Controlo de Acessos**

📂 O **controlo de acessos** define **quem pode acessar quais recursos**.

### 🔍 **Modelos de Controlo de Acessos**

1️⃣ **DAC (Discretionary Access Control)**

- O **proprietário** do recurso define **quem pode acessar**.
- Exemplo: Ficheiros no Windows/Linux com permissões atribuídas manualmente.  
    2️⃣ **MAC (Mandatory Access Control)**
- O **sistema** define **políticas de segurança fixas**.
- Exemplo: Sistemas militares onde apenas usuários com **certas credenciais** acessam dados confidenciais.  
    3️⃣ **RBAC (Role-Based Access Control)**
- Acesso concedido com base em **funções** (Administrador, Funcionário, Convidado).
- Exemplo: Empresas onde **apenas gerentes** podem modificar certos dados.  
    4️⃣ **ABAC (Attribute-Based Access Control)**
- Acesso baseado em **atributos dinâmicos** (localização, dispositivo, hora do dia).
- Exemplo: Permitir login apenas **dentro da empresa** e em **horário comercial**.

---

## 🎯 **Perguntas e Respostas**

### ❓ O que são os **modos da CPU**?

✅ Modos de operação da CPU que garantem segurança ao sistema:

- **Modo Kernel** → Controle total sobre o hardware e sistema.
- **Modo Utilizador** → Restrito, sem acesso direto a recursos críticos.

### ❓ O que é uma **chamada ao sistema**?

📌 Um **método usado por programas para interagir com o SO**, permitindo acessar recursos restritos de forma segura.

### ❓ Qual a diferença entre **DAC e MAC**?

- **DAC (Discretionary Access Control)** → O **proprietário** decide quem acessa os recursos.
- **MAC (Mandatory Access Control)** → O **sistema** impõe políticas rígidas de acesso.

### ❓ O que acontece quando ocorre uma **chamada ao sistema**?

1️⃣ O programa solicita um serviço do SO.  
2️⃣ O **modo da CPU muda** para Modo Kernel.  
3️⃣ O SO executa a operação.  
4️⃣ O sistema retorna ao **Modo Utilizador**.

### ❓ Como o **controlo de acessos** protege os sistemas operativos?

✅ Define **quem pode acessar** quais recursos, reduzindo riscos de **violação de dados, malware e ataques cibernéticos**.

### ❓ Qual a diferença entre **RBAC e ABAC**?

- **RBAC (Role-Based Access Control)** → Baseado em **funções (Ex: Administrador, Utilizador)**.
- **ABAC (Attribute-Based Access Control)** → Baseado em **condições dinâmicas (Ex: Localização, Hora, Dispositivo)**.

---
# 📄 **Exame de Sistemas Operativos**

**Ano Letivo:** 2024/2025  
**Curso:** Licenciatura em Engenharia Informática / Licenciatura em Segurança Informática e Redes de Computadores  
**Unidade Curricular:** Sistemas Operativos  
**Duração:** 2h10m

📌 **Observações:**

- Exame com **consulta de documentação própria**.
- A cotação atribuída a cada pergunta está indicada entre parêntesis **[ ]**.

---

## **Parte I (Teórica)**

### **Questão I [3,0 valores]**

Considere que apenas deverá selecionar **uma das opções apresentadas**. Cada resposta certa vale **1,0 valor** e cada resposta errada resulta numa **penalização de -0,5 valores**.

#### **Questão I.1**:

Em sistemas operativos, a **memória virtual**:  
a) Permite executar programas maiores do que a memória física.  
b) Não necessita de uma unidade de gestão de memória (MMU).  
c) Elimina completamente a necessidade de memória RAM.  
d) Torna desnecessário o uso de swap.

#### **Questão I.2**:

O **escalonamento de processos** em sistemas operativos tem como objetivo:  
a) Priorizar sempre os processos mais curtos, independentemente de critérios adicionais.  
b) Controlar a execução de processos para otimizar o uso do processador.  
c) Bloquear processos que usam mais memória do que o permitido.  
d) Interromper processos que fazem uso da memória virtual.

#### **Questão I.3**:

No contexto da **sincronização de processos**, um **deadlock** ocorre quando:  
a) Um processo consome todos os recursos do sistema.  
b) Um conjunto de processos fica permanentemente bloqueado, aguardando recursos uns dos outros.  
c) Todos os processos do sistema são encerrados abruptamente.  
d) O escalonador remove processos que estão em espera há muito tempo.

---

## **Parte II (Desenvolvimento)**

### **Questão II [2,0 valores]**

📌 Explique o **problema da secção crítica** e os **três requisitos fundamentais** para garantir uma solução correta.

---

### **Questão III [1,5 valores]**

📌 Assuma um sistema com **256 KB de memória virtual por paging**, com **páginas de 32 KB**.  
Considere a tabela de mapeamento de páginas abaixo e responda:

|Endereço Virtual|Mapeado para Endereço Físico?|
|---|---|
|000 1|Sim|
|101 0|Não|
|010 1|Sim|
|011 1|Sim|
|100 0|Não|
|110 1|Sim|

#### **Perguntas:**

1️⃣ A que **endereço físico (em decimal)** corresponde o endereço virtual **34816**? **[0,75 valores]**  
2️⃣ O endereço virtual **8192** gera um **page fault**? Justifique. **[0,75 valores]**

---

### **Questão IV [2,0 valores]**

📌 Considere o seguinte conjunto de processos, onde o instante de chegada e a duração são indicados na tabela abaixo:

|Processo|Instante de Chegada|Duração|
|---|---|---|
|P1|0.0|0.3|
|P2|0.1|0.1|
|P3|0.2|0.4|
|P4|0.3|0.2|
|P5|0.5|0.3|

1️⃣ **Calcule o tempo médio de espera** usando o algoritmo **SJF (Shortest Job First)**.  
2️⃣ **Se usássemos Round-Robin com quantum = 0.2**, a ordem dos processos e o tempo médio de espera seriam diferentes? Justifique.

---

### **Questão V [1,5 valores]**

📌 Considere um computador com **1 MB de memória** que utiliza o **algoritmo Buddy** para gestão de memória.

🖥️ **Simule a alocação de memória** para os seguintes eventos:  
1️⃣ Novo processo **P1** com **256 KB**.  
2️⃣ Novo processo **P2** com **128 KB**.  
3️⃣ Novo processo **P3** com **64 KB**.  
4️⃣ Novo processo **P4** com **256 KB**.  
5️⃣ **Término** de **P1 e P2**.  
6️⃣ Novo processo **P5** com **96 KB**.  
7️⃣ **Término** de **P3**.

📌 **Desenhe a divisão da memória após todas as operações** e explique como o algoritmo **Buddy** resolve a fusão de blocos.

---

## **Questão VI [2,0 valores]**

📌 No contexto da **segurança em sistemas operativos**, explique os seguintes conceitos e forneça exemplos:  
1️⃣ **Modos de CPU** e sua importância na proteção do sistema.  
2️⃣ **Controlo de acessos baseado em papéis (RBAC)** e **controlo de acessos baseado em atributos (ABAC)**.  
3️⃣ **Page Fault** e seu impacto no desempenho do sistema.

---

## 🎯 **Fim do Exame**

✅ **O exame cobre todos os temas que enviaste, incluindo:**

- Processos e escalonamento
- Deadlocks
- Memória virtual e paginação
- Sincronização e secção crítica
- Segurança e controlo de acessos



___

# Exercicio buddy
![[Pasted image 20250114235715.png]]

## **2. Worst-Fit**

O **Worst-Fit** aloca o maior bloco livre disponível, deixando o máximo de espaço restante.

### **Vantagens**

✅ **Menos fragmentação externa** – Deixa blocos maiores disponíveis, o que pode ajudar na alocação de processos futuros.  
✅ **Processo de busca mais rápido** – Basta procurar o maior bloco livre, que pode ser encontrado de forma mais eficiente (por exemplo, mantendo a lista ordenada por tamanho).

### **Desvantagens**

❌ **Maior fragmentação interna** – Como aloca blocos grandes, pode haver muito espaço desperdiçado dentro dos blocos alocados.  
❌ **Desperdício de grandes blocos** – Os maiores blocos podem ser divididos de forma ineficiente, tornando difícil alocar processos grandes depois.

![[Pasted image 20250114235802.png]]

## **1. Best-Fit**

O **Best-Fit** seleciona o menor bloco de memória disponível que seja suficiente para acomodar o processo.

### **Vantagens**

✅ **Menos fragmentação interna** – Como escolhe o menor bloco adequado, o desperdício dentro do bloco é menor.  
✅ **Melhor utilização da memória a curto prazo** – Normalmente usa o espaço disponível de forma eficiente, deixando espaços livres maiores para processos futuros.

### **Desvantagens**

❌ **Maior fragmentação externa** – Pequenos blocos de memória podem ser deixados espalhados, dificultando o uso futuro.  
❌ **Processo de busca mais lento** – Pode exigir percorrer toda a lista de blocos livres para encontrar o menor possível, aumentando o tempo de execução.
![[Pasted image 20250115000314.png]]

## **4. Conclusão**

Em **geral**, o **Best-Fit é mais eficiente** na maioria dos casos, pois reduz a fragmentação interna e melhora a utilização da memória. No entanto, pode levar à fragmentação externa significativa ao longo do tempo. O **Worst-Fit** pode ser útil se houver necessidade de manter grandes blocos livres para processos futuros, mas geralmente desperdiça mais espaço.

💡 **Se for necessário um balanceamento entre tempo de execução e eficiência da memória, um algoritmo como First-Fit pode ser uma alternativa intermediária!** 🚀

___

# Round-Robin

![[Pasted image 20250115004443.png]]

#### **2. Execução com Quantum = 0.2**

Agora seguimos a ordem de chegada e aplicamos o quantum:

1. **Tempo 0.0 - 0.2**: **P0** executa e finaliza (já tinha 0.2).
2. **Tempo 0.2 - 0.3**: **P1** executa 0.2 (resta 0.3).
3. **Tempo 0.3 - 0.4**: **P2** executa e finaliza (já tinha 0.1).
4. **Tempo 0.4 - 0.6**: **P3** executa 0.2 (resta 0.2).
5. **Tempo 0.6 - 0.8**: **P4** executa e finaliza (já tinha 0.2).
6. **Tempo 0.8 - 1.0**: **P1** executa mais 0.2 (resta 0.1).
7. **Tempo 1.0 - 1.2**: **P5** executa 0.2 (resta 0.1).
8. **Tempo 1.2 - 1.3**: **P3** executa mais 0.1 e finaliza.
9. **Tempo 1.3 - 1.4**: **P5** executa e finaliza.
10. **Tempo 1.4 - 1.6**: **P1** executa e finaliza.

### **4. Cálculo de Tempo de Espera e Turnaround**

Agora calculamos:

- **Turnaround Time (TAT) = Tempo de término - Tempo de chegada**
- **Tempo de Espera (WT) = Turnaround - Tempo de execução**

|Processo|Chegada|Duração|Término|Turnaround (TAT)|Espera (WT)|
|---|---|---|---|---|---|
|P0|0.0|0.2|0.2|0.2 - 0.0 = 0.2|0.2 - 0.2 = 0.0|
|P1|0.1|0.5|1.6|1.6 - 0.1 = 1.5|1.5 - 0.5 = 1.0|
|P2|0.2|0.1|0.4|0.4 - 0.2 = 0.2|0.2 - 0.1 = 0.1|
|P3|0.3|0.4|1.3|1.3 - 0.3 = 1.0|1.0 - 0.4 = 0.6|
|P4|0.6|0.2|0.8|0.8 - 0.6 = 0.2|0.2 - 0.2 = 0.0|
|P5|1.0|0.3|1.4|1.4 - 1.0 = 0.4|0.4 - 0.3 = 0.1|

**Tempo médio de espera (WT médio):**

0.0+1.0+0.1+0.6+0.0+0.16=0.3\frac{0.0 + 1.0 + 0.1 + 0.6 + 0.0 + 0.1}{6} = 0.360.0+1.0+0.1+0.6+0.0+0.1​=0.3

---

### **5. Comparação com o SRTF**

Agora podemos comparar os tempos médios:

- **SRTF**:
    
    - **WT médio** = 0.2
    - **TAT médio** = menor
- **Round-Robin (Quantum = 0.2)**:
    
    - **WT médio** = 0.3 (um pouco maior)
    - **TAT médio** = maior porque há mais trocas de contexto

A principal diferença entre os dois algoritmos é que **SRTF minimiza o tempo médio de espera**, enquanto **RR garante tempo de resposta justo para todos os processos**, evitando que processos longos monopolizem a CPU.

### **6. Conclusão**

Se o objetivo for **minimizar o tempo médio de espera e turnaround**, **SRTF** é melhor. Mas, se quisermos **evitar a inanição de processos e garantir equidade**, **Round-Robin** é mais adequado.


___

# Shortest Remaining Time First (SRTF)
![[Pasted image 20250115100148.png]]
![[Pasted image 20250115100251.png]]

#### **2. Simulação do Escalonamento com SRTF**

- **Tempo 0.0**: Apenas **P0** chegou, então ele começa a executar.
- **Tempo 0.2**: **P0 termina**. **P1 e P2 já chegaram**. Escolhemos **P2** porque tem a menor duração restante (0.1).
- **Tempo 0.3**: **P2 termina**. Agora temos **P1 e P3** prontos. Como **P3 tem 0.4** e **P1 tem 0.5**, escolhemos **P3**.
- **Tempo 0.6**: **P3 termina**. Agora temos **P1 e P4**. Escolhemos **P4** porque tem apenas 0.2 restantes.
- **Tempo 0.8**: **P4 termina**. Só restam **P1 e P5**. Como **P5 acabou de chegar e tem 0.3**, enquanto **P1 tem 0.5 restantes**, **P5 é escolhido**.
- **Tempo 1.1**: **P5 termina**. Agora **P1 é o único restante e é executado até o final**.
- **Tempo 1.6**: **P1 termina**. Todos os processos foram concluídos.


#### **4. Cálculo do Tempo de Espera e Turnaround**

Agora podemos calcular:

- **Turnaround Time (TAT)** = **Tempo de término - Tempo de chegada**
- **Tempo de Espera (WT)** = **Turnaround - Tempo de execução**

|Processo|Chegada|Duração|Término|Turnaround (TAT)|Espera (WT)|
|---|---|---|---|---|---|
|P0|0.0|0.2|0.2|0.2 - 0.0 = 0.2|0.2 - 0.2 = 0.0|
|P1|0.1|0.5|1.6|1.6 - 0.1 = 1.5|1.5 - 0.5 = 1.0|
|P2|0.2|0.1|0.3|0.3 - 0.2 = 0.1|0.1 - 0.1 = 0.0|
|P3|0.3|0.4|0.6|0.6 - 0.3 = 0.3|0.3 - 0.4 = -0.1 (não pode ser negativo, então 0)|
|P4|0.6|0.2|0.8|0.8 - 0.6 = 0.2|0.2 - 0.2 = 0.0|
|P5|1.0|0.3|1.1|1.1 - 1.0 = 0.1|0.1 - 0.3 = -0.2 (ajustamos para 0)|

#### **5. Conclusão**

O algoritmo SRTF minimiza o tempo médio de espera, pois sempre escolhe o processo com menor tempo restante. Com essa análise detalhada e o diagrama de Gantt, o exercício está resolvido!

___
# MMU

![[Pasted image 20250115101245.png]]

Sistema de memória 128KB = 1KB X 128 (=) 2^10 X 2^7 (=) 2^17
Paging com páginas de 16KB = 1KB X 16 (=) 2^10 X 2^4 (=) 2^14

Passa-mos o endereço virtual para binário:

25820 em binário = 110 0100 1101 1100

mas como temos de equiparar o numero de elementos do código binário com o do sistema de memoria, temos de adicionar 0 a esquerda de acordo com os elementos em falta. A memoria é 2^17 e o nosso numero binário tem 15 elementos, logo 17-15 = 2. Temos de adicionar dois 0 a esquerda no nosso numero em binário.

Fica 0 0110 0100 1101 1100.

Os bits mais importantes são os que estão a esquerda. E a quantidade que selecionamos é definida com a potencia do sistema da memória menos a potencia do paging. 17-14 = 3.

Selecionamos então os 3 bits mais a esquerda do nosso numero binário, ficando assim com 001.

Após isso, passamos o numero binário 001 para numero decimal, ficando com o resultado de 1 em decimal. Agora vamos a tabela do stor e selecionamos a posição 1 da tabela.

Posição 0 -> 110	1
Posição 1 -> 101	1   Posição selecionada
Posição 2 -> 001	0
Posição 3 -> 001	1
Posição 4 -> 000	0
Posição 5 -> 110	1

Pegamos no nosso numero binário e substituímos os 3 bits mais á esquerda pelos bits da posição selecionada.

Fica 1 0110 0100 1101 1100.

Passamos este numero binário para decimal.

Fica 91356.

Entao endereço físico do endereço virtual 25820 é o endereço físico 91356.

___

# Algoritmo do banqueiro

![[Pasted image 20250115110447.png]]

### **✅ Resumo Final para Apontamentos**

1. **Calcular a Matriz Need**: Need=Max−Alocado\text{Need} = \text{Max} - \text{Alocado}Need=Max−Alocado
2. **Selecionar um processo cujo** `Need ≤ Available`.
3. **Executar o processo**, liberando seus recursos e atualizando `Available`.
4. **Repetir até que todos os processos sejam atendidos**.
5. **Se todos forem atendidos, o sistema está seguro**.
6. **Sequência segura encontrada**: **P1 → P3 → P4 → P0 → P2**.
