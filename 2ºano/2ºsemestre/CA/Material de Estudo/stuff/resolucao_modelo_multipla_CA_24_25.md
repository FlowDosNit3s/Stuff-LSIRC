# 📝 Respostas Modelo (com variações) — Exame CA 2024/2025

Este ficheiro contém as respostas ao exame, mas focado em dar-te **diferentes formas válidas de responder** às perguntas de desenvolvimento ou que exigem justificação. Podes escolher o estilo que te for mais fácil de memorizar!

Para as perguntas de resposta direta/única, a resposta é mantida simples.

---

## Parte I

### 1.1. A aplicação de métodos criptográficos à mensagem original gera um ______.
**Resposta Única:** Criptograma *(ou Texto Cifrado)*.

### 1.2. Que mecanismo faz mais sentido utilizar para tentar quebrar uma cifra AES: análise de frequência ou força bruta? Justifique.
* **Exemplo 1 (Curta e Direta):** Força bruta. O algoritmo AES é uma cifra forte que não deixa padrões no texto cifrado, tornando a análise de frequência inútil.
* **Exemplo 2 (Completa/Detalhada):** Força bruta. A análise de frequência baseia-se na identificação de padrões da linguagem original no criptograma. Como o AES destrói as propriedades estatísticas da linguagem através de difusão e confusão (gerando um output pseudoaleatório uniforme), a única forma teórica de quebrar a cifra é testar exaustivamente todas as chaves possíveis através de um ataque de força bruta.
* **Exemplo 3 (Em Tópicos):** 
  - Mecanismo: Força bruta.
  - Justificação: O AES distribui os dados de forma pseudoaleatória. Não há padrões estatísticos a explorar, logo a análise de frequência não funciona. Resta tentar todas as chaves.

### 2.1. No "One Time Pad" qual deve ser o tamanho mínimo da chave...
**Resposta Única:** A chave deve ter um tamanho igual ou superior ao tamanho da mensagem (para garantir o sigilo perfeito / *perfect secrecy*).

### 3.1. Qual o modo, nas cifras de blocos, em que o gerador de chaves recebe a mistura de um contador e um vetor de inicialização? Qual a vantagem dessa cifra?
* **Exemplo 1 (Direta):** Modo CTR (Counter). A sua principal vantagem é permitir que a cifra e decifra sejam processadas em paralelo, tornando-o muito mais rápido e eficiente.
* **Exemplo 2 (Completa):** Modo CTR. As vantagens são: transforma uma cifra de blocos numa cifra de fluxo (*stream cipher*), permite o processamento em paralelo (ao contrário do modo CBC), tem acesso aleatório direto a partes do ficheiro sem ter de decifrar o ficheiro todo, e não necessita de *padding*.

### 3.2. Numa cifra por blocos, que algoritmo de padding poderia gerar o resultado `... 03 04 05`?
**Resposta Única:** Algoritmo ISO 10126-2 (pois adiciona bytes aleatórios seguidos de um byte final com o tamanho do padding).

### 4. Criptografia Assimétrica (V/F)
- **4.1.** Verdadeiro (para encriptar usa-se a chave pública do destinatário).
- **4.2.** Falso (para decifrar usa-se a chave privada do próprio).
- **4.3.** Verdadeiro (a chave pública do assinante valida a assinatura que foi feita com a chave privada dele).

### 5.1. Indique tudo o que necessitamos ter para efetuarmos a validação de uma assinatura.
* **Exemplo 1 (Tópicos):** 1) O documento original; 2) A assinatura digital gerada; 3) A chave pública de quem assinou; 4) Conhecimento do algoritmo de hash utilizado.
* **Exemplo 2 (Texto corrido):** Para validar, necessitamos do documento em claro e da respetiva assinatura digital anexada. Precisamos também da chave pública do emissor para decifrar a assinatura e obter o hash original, e saber qual o algoritmo de hash a aplicar à mensagem em claro para podermos comparar ambos os valores.

### 5.2. Qual considera ser a opção mais segura? Justifique!
* **Exemplo 1 (Curta):** Opção B (SHA-2 com RSA de 2048). O SHA-1 já tem colisões conhecidas, as chaves RSA de 512 bits já foram fatorizadas/quebradas, e o SHA-4 não existe. A opção B usa algoritmos e tamanhos recomendados e seguros hoje em dia.
* **Exemplo 2 (Completa):** A opção correta é a alínea b). Justifica-se por eliminação: a alínea a) usa SHA-1 que está obsoleto por vulnerabilidades de colisão; a alínea c) usa chaves RSA de 512 bits que são computacionalmente quebraveis nos dias de hoje; a alínea d) refere SHA-4, que não é um standard existente. O SHA-2 e o RSA 2048 são a norma segura atual da indústria.

### 5.3. O MAC permite assegurar o não repúdio? Porquê?
* **Exemplo 1 (Direta):** Não. O MAC usa criptografia simétrica. Como emissor e recetor partilham a mesma chave, qualquer um deles pode ter gerado o MAC, impossibilitando provar a terceiros quem foi o autor.
* **Exemplo 2 (Completa):** Não garante o não repúdio. O não repúdio significa que o autor não pode negar a autoria. Como o MAC depende de uma chave secreta partilhada entre o cliente e o servidor, o servidor também possui a chave e tem a capacidade de gerar uma mensagem válida fazendo-se passar pelo cliente. Só a assinatura digital assimétrica resolve este problema, porque a chave privada é exclusiva de quem assina.

### 6.1. Qual o principal objetivo do protocolo de estabelecimento de sessão?
* **Exemplo 1:** Acordar uma chave de sessão simétrica (secreta e temporária) de forma segura entre duas entidades para garantir a confidencialidade das mensagens seguintes.
* **Exemplo 2:** O objetivo é que duas partes consigam partilhar ou derivar uma chave secreta e temporária através de um meio inseguro, assegurando a autenticação das partes. Essa chave será depois usada em algoritmos simétricos (muito mais rápidos) para encriptar os dados da sessão (ex: TLS).

### 7. Java
- **7.1.** Devolve um objeto `SecretKey`.
- **7.2.** Pode ser utilizado em criptografia Assimétrica (guarda PublicKey e PrivateKey).
- **7.3.** O nome do algoritmo (ex: `"HmacSHA256"`).
- **7.4.** Um array de bytes (`byte[]`).

---

## Parte II

### 8.1. Qual a principal utilização dos dispositivos criptográficos seguros?
* **Exemplo 1 (Curta):** Guardar chaves privadas de forma inviolável e executar operações criptográficas localmente (ex: assinar documentos).
* **Exemplo 2 (Completa):** A sua utilização principal é o armazenamento seguro das chaves privadas e certificados. Eles garantem que a chave privada nunca sai do hardware, sendo as operações de assinatura (ou cifra) processadas no próprio chip do dispositivo (ex: Smartcard, HSM, eID), impedindo o roubo das chaves por malware no computador.

### 9. Certificados Digitais
- **9.1.** A relação entre uma identidade (dono) e a sua respetiva Chave Pública.
- **9.2.** O campo *Serial Number* (Número de Série).
- **9.3.** A Entidade Certificadora / CA (Certificate Authority).

### 10.1. Que documentos/políticas publicam as Entidades Certificadoras?
**Resposta Única:** A Declaração de Práticas de Certificação (DPC / CPS) e a Política de Certificação (PC).

### 11.1. De onde e como é obtida a CRL pelas aplicações?
**Resposta Única:** Através da leitura da extensão CDP (*CRL Distribution Point*) presente no próprio certificado, acedendo de seguida a um servidor HTTP ou LDAP aí indicado para fazer o download da lista.

### 12.1. Num serviço web é melhor utilizar CRL ou OCSP para validar certificados? Porquê?
* **Exemplo 1 (Curta):** O OCSP é melhor porque é mais leve e rápido. Permite consultar o estado de um único certificado em tempo real, sem ter de fazer o download da lista (CRL) inteira que consome largura de banda.
* **Exemplo 2 (Completa):** O protocolo OCSP. Enquanto que a CRL exige que o serviço faça download regular de um ficheiro extenso e pesado contendo todos os certificados revogados, o OCSP funciona por pedido/resposta para validar apenas o certificado específico que está a ser usado. Isso poupa largura de banda e garante a validação do estado em tempo-real.

### 13.1. Que objetos devem ser utilizados quando invocamos um serviço de Timestamp?
**Resposta Única:** O Hash (resumo da mensagem) do documento. Não se envia o documento completo.

### 14. Standards (PKCS)
- **14.1.** O PKCS#11 (Cryptoki).
- **14.2.** Contém um selo temporal (*Timestamp*), garantindo a data e hora oficial em que a assinatura decorreu.

### 15.1. Se utilizarmos HSTS num servidor, aumentamos ou diminuímos o nível de compatibilidade? Porquê?
* **Exemplo 1 (Curta):** Diminuímos a compatibilidade. O HSTS obriga ao uso exclusivo de HTTPS. Sistemas *legacy* ou browsers sem suporte para HSTS falharão a ligação se tentarem usar HTTP não encriptado.
* **Exemplo 2 (Completa):** A compatibilidade diminui. O cabeçalho HTTP Strict Transport Security instrui o browser a comunicar apenas por canais seguros (HTTPS). Isto significa que aplicações antigas, sistemas sem certificados válidos ou que forcem ligações HTTP puro não conseguirão comunicar com o servidor, sacrificando a compatibilidade em prol de uma maior segurança.
