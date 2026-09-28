# PL01 — Sockets TCP em Java: Respostas e Explicações

---

## 1. Pergunta do Passo 3: Gestão de Recursos com `try-with-resources`

### Questão:
> *Que recursos fecha o `try-with-resources` da linha do `ServerSocket`, e o de dentro do ciclo?*

### Resposta e Explicação:

* **`try-with-resources` exterior (nível do `ServerSocket`):**
  * **Recurso fechado:** O objeto `ServerSocket`.
  * **Explicação:** Fecha o socket de escuta do servidor e liberta o porto TCP associado no sistema operativo (por omissão, o porto `5050`). Garante que, se ocorrer uma falha crítica ou se o processo terminar, o porto não fica retido pelo SO, prevenindo erros futuros de `BindException`.

* **`try-with-resources` interior (dentro do ciclo `while`):**
  * **Recursos fechados:** O `Socket` do cliente aceite (`clientSocket`), o fluxo de leitura (`BufferedReader in`) e o fluxo de escrita (`PrintWriter out`).
  * **Explicação:** Ao terminar a comunicação com um cliente (seja por receção de `null`, comando de saída ou erro de I/O), este bloco garante o fecho automático da ligação TCP (envio dos pacotes `FIN`/`ACK`) e a libertação dos descritores de ficheiros e buffers de memória associados, sem necessidade de recorrer a blocos `finally` manuais.

---

## 2. Pergunta de Reflexão do Passo 6: Limitações do Servidor Sequencial

### Questão:
> *Porque é que o segundo cliente consegue "ligar-se" mas não recebe resposta enquanto o primeiro está ativo? Que linha do código do servidor é responsável por este comportamento?*

### Resposta e Explicação:

* **Por que razão o segundo cliente consegue ligar-se:**
  * O estabelecimento da ligação TCP (*three-way handshake*: SYN $\rightarrow$ SYN-ACK $\rightarrow$ ACK) é gerido diretamente pela pilha de rede do Sistema Operativo.
  * O kernel aceita a ligação e coloca-a numa fila de espera (*backlog queue*) associada ao porto do servidor.
  * Como o *handshake* foi concluído com sucesso ao nível do SO, a chamada `new Socket(host, port)` no segundo cliente termina sem lançar qualquer exceção e apresenta a mensagem de que está ligado.

* **Por que razão não recebe resposta:**
  * O servidor é **sequencial** (opera numa única thread).
  * Enquanto o primeiro cliente não fechar a sua sessão, a thread de execução do servidor continua presa no ciclo de leitura desse primeiro cliente:
    ```java
    while ((line = in.readLine()) != null) { ... }
    ```
  * Os dados enviados pelo segundo cliente ficam acumulados no buffer de receção TCP do SO e nunca chegam à aplicação até que o servidor volte a ter disponibilidade.

* **Linha de código responsável:**
  * A chamada ao método bloqueante:
    ```java
    Socket clientSocket = serverSocket.accept();
    ```
  * Enquanto a execução do servidor estiver bloqueada dentro do ciclo do primeiro cliente, a linha `accept()` não volta a ser executada para retirar o segundo cliente da fila e iniciar o respetivo atendimento.

---

## 3. Explicação dos Erros Provocados (Passo 5)

### 3.1 Cliente sem Servidor à Escuta (`ConnectException`)
* **Erro observado:** `java.net.ConnectException: Connection refused`
* **Explicação:** O cliente tenta estabelecer uma ligação enviando um segmento TCP SYN para o par `IP:porto`. Como não existe nenhum processo com um `ServerSocket` registado a escutar nesse porto específico, o sistema operativo de destino devolve imediatamente um pacote de reinicialização (`RST`). Ao receber o `RST`, o Java lança uma `ConnectException`.
* **Conceito teórico subjacente:** Demonstra a falácia de que "a rede é fiável" (a contraparte pode simplesmente estar inativa ou inacessível).

### 3.2 Dois Servidores no Mesmo Porto (`BindException`)
* **Erro observado:** `java.net.BindException: Address already in use`
* **Explicação:** Numa máquina, cada porto da camada de transporte só pode ser registado e controlado por um único processo de cada vez (para o mesmo protocolo TCP e interface de rede).
* **Conceito teórico subjacente:** Quando o segundo servidor tenta executar `new ServerSocket(5050)`, a chamada ao sistema `bind()` falha porque a porta já está alocada ao primeiro processo, originando a exceção.

---

## 4. Explicação do Funcionamento dos Exercícios Práticos

### 4.1 Exercício 1 — Protocolo Maiúsculas e Comando `BYE`
* **Transformação de texto:** O servidor lê uma linha com `in.readLine()` e devolve `out.println(line.toUpperCase())`.
* **Protocolo de terminação acordado:**
  * Ao receber a mensagem de texto `"BYE"` (avaliada com `.equalsIgnoreCase("BYE")` para ignorar a capitalização), o servidor responde com `"BYE"` e executa um `break` para sair do ciclo de leitura.
  * O fecho do ciclo do cliente provoca o encerramento do `try-with-resources` interno, terminando a ligação desse cliente e permitindo que o ciclo principal volte ao `accept()`.
  * O cliente, ao enviar `"BYE"`, lê a resposta de confirmação e também termina o seu próprio ciclo sem gerar erros de interrupção inesperada.

### 4.2 Exercício 3 — Servidor de Comandos (`TIME` e `ADD`)
* **Processamento de mensagens:**
  * A linha recebida é dividida pelos espaços usando expressões regulares (`input.split("\\s+")`).
  * O primeiro elemento corresponde ao comando (`TIME` ou `ADD`).
* **Validação e robustez:**
  * Se for `TIME` e o número de argumentos for exatamente 1, calcula a hora do sistema com `LocalTime.now()`.
  * Se for `ADD` e o número de argumentos for 3, converte os operandos para valores numéricos e efetua a soma.
  * Caso surja uma exceção de conversão (`NumberFormatException`) ou o número de argumentos não corresponda à especificação do protocolo, o servidor responde de forma determinística com `ERRO`, mantendo a ligação aberta sem que o processo crashe.