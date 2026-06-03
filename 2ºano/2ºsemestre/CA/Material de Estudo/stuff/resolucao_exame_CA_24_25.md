# ✅ Resolução — Exame Criptografia Aplicada (CA) 2024/2025

> **Curso**: LEI / LSIRC | **UC**: Segurança Informática em Redes de Computadores (Criptografia Aplicada)
> **Duração**: 1h00m + 15m extra

---

## Parte I (11,5 valores)

### 1. Criptografia
**1.1.** A aplicação de métodos criptográficos à mensagem original gera um **criptograma** (ou texto cifrado). *(0,5v)*

**1.2.** Que mecanismo faz mais sentido utilizar para tentar quebrar uma cifra AES: análise de frequência ou força bruta? Justifique. *(1v)*
**Força bruta**. 
> **Justificação:** O AES é um algoritmo robusto concebido para ser imune à análise de frequência, pois produz um criptograma com uma distribuição pseudoaleatória perfeitamente uniforme. Assim, a única via teórica viável (ainda que impraticável computacionalmente se a chave for longa) é testar todas as chaves possíveis através de força bruta.

### 2. Criptografia Clássica/Moderna
**2.1.** No "One Time Pad" qual deve ser o tamanho mínimo da chave por forma a termos garantias de haver uma cifra forte ou até impossível de quebrar? *(0,5v)*
O tamanho da chave deve ser **igual (ou superior) ao tamanho da própria mensagem** que se pretende cifrar (garantindo assim o chamado "Perfect Secrecy").

### 3. Criptografia Simétrica
**3.1.** Qual o modo, nas cifras de blocos, em que o gerador de chaves recebe a mistura de um contador e um vetor de inicialização? Qual a vantagem dessa cifra? *(0,5v)*
**Modo CTR (Counter Mode).**
> **Vantagens:** Permite paralelismo no processo de cifragem/decifragem (tornando-o muito mais rápido), transforma uma cifra de blocos numa cifra de fluxo (stream cipher), e não requer *padding* no último bloco.

**3.2.** Numa cifra por blocos, que algoritmo de padding poderia gerar o seguinte resultado? `DD D2 5E AC 03 04 05` *(0,5v)*
O algoritmo **ISO 10126-2**.
> **Justificação:** No `ISO 10126-2`, o padding é feito adicionando bytes com valores aleatórios (ex: `03 04`), em que o último byte (`05`) indica explicitamente a quantidade total de bytes de padding adicionados.

### 4. Criptografia Assimétrica (Verdadeiro ou Falso)
**4.1.** Para encriptar utilizo a chave pública do destinatário. — **Verdadeiro** *(0,5v)*
**4.2.** Para decifrar dados encriptados, utilizo a minha chave pública. — **Falso** *(0,5v)* *(Utilizo a minha chave privada)*.
**4.3.** Para validar uma assinatura utilizo a chave pública do assinante. — **Verdadeiro** *(0,5v)*

### 5. Hash, MAC e Assinatura
**5.1.** Indique, no esquema de assinatura (RSA), tudo o que necessitamos ter em nossa posse para efetuarmos a correta validação de uma assinatura? *(1v)*
Necessitamos de 4 elementos:
1. O **Documento / Mensagem original**.
2. A **Assinatura Digital** do documento.
3. A **Chave Pública do assinante**.
4. Ter conhecimento do **algoritmo de Hash** que foi utilizado (para recalcularmos localmente e compararmos com o desencriptado da assinatura).

**5.2.** Qual considera ser a opção mais segura? Justifique! *(0,5v)*
a) SHA-1 with RSA 4096 bits
b) SHA-2 with RSA 2048 bits
c) SHA-3 with RSA 512 bits
d) SHA-4 with RSA 16384 bits

A opção mais segura é a **b) Esquema de assinatura SHA-2 with RSA com chaves de 2048 bits**.
> **Justificação:** O SHA-1 hoje em dia é inseguro por vulnerabilidade a colisões (exclui a). O RSA com 512 bits é completamente inseguro e facilmente quebrável/fatorizável (exclui c). O algoritmo SHA-4 não existe enquanto standard oficial (exclui d). Assim, SHA-2 emparelhado com RSA 2048 oferece a conjugação segura recomendada atualmente.

**5.3.** O MAC permite assegurar o não repúdio? Porquê? *(0,5v)*
**Não.** 
> **Justificação:** O MAC baseia-se em criptografia simétrica, ou seja, usa uma chave secreta partilhada entre o emissor e o recetor. Como ambas as partes conhecem a chave, qualquer uma delas pode gerar um MAC válido. Logo, perante um terceiro, é impossível provar irrefutavelmente quem gerou a mensagem (ou seja, não garante o "não repúdio", propriedade que só a Assinatura Digital assimétrica alcança).

### 6. Protocolo acordo de chave
**6.1.** Qual o principal objetivo do protocolo de estabelecimento de sessão? *(2v)*
Estabelecer (acordar) uma **chave de sessão simétrica temporária** de forma totalmente segura e, frequentemente, autenticada entre as partes. Isto permite que a restante transferência de dados (sessão) usufrua da enorme rapidez da criptografia simétrica, mas com a chave partilhada de forma inviolável (ex: protocolo Diffie-Hellman, TLS Handshake).

### 7. Criptografia em Java
**7.1.** Que tipo de dados devolve o método `generateKey()` da classe `KeyGenerator`? *(0,5v)*
Devolve um objeto do tipo **`SecretKey`** (chave simétrica).

**7.2.** O objeto `KeyPair` pode ser utilizado em Simétrica, Assimétrica ou em ambas? *(0,5v)*
Na criptografia **Assimétrica** (dado que agrupa uma Chave Privada e uma Chave Pública em par).

**7.3.** O que é passado como parâmetro na invocação `getInstance` da classe MAC? *(0,5v)*
O **algoritmo** que pretendemos instanciar (exemplo: `"HmacSHA256"`).

**7.4.** Na classe `Signature` que tipo de dados devolve o método `sign`? *(0,5v)*
Devolve um **array de bytes** (`byte[]`), que é a representação da assinatura digital calculada.

---

## Parte II (8,5 valores)

### 8. Segurança
**8.1.** Qual a principal utilização dos dispositivos criptográficos seguros? *(0,5v)*
São utilizados para **armazenar chaves privadas de forma inviolável** e para realizar as operações criptográficas internamente ao dispositivo (ex: assinar documentos num Cartão de Cidadão ou HSM), garantindo que a chave privada nunca sai ou é exposta ao Sistema Operativo do computador.

### 9. Certificados Digitais
**9.1.** Qual a principal associação/relação que os certificados digitais garantem? *(1v)*
Garantem a vinculação inequívoca entre uma **identidade** (pessoa, empresa, servidor) e uma **Chave Pública**, devidamente carimbada/atestada por uma Entidade Certificadora confiável (CA).

**9.2.** Qual o campo dos certificados que a CA tem de garantir ser diferente entre eles? *(1v)*
O **Serial Number** (Número de Série).

**9.3.** Quem assina o Certificado Digital (v3)? *(1v)*
A **Entidade Certificadora (CA)** emissora do certificado (através da sua chave privada).

### 10. Infraestrutura de Chave Pública (PKI)
**10.1.** Que documentos/políticas publicam as Entidades Certificadoras? *(0,5v)*
Publicam a **PC (Política de Certificação / Certificate Policy)** e a **DPC (Declaração de Práticas de Certificação / Certification Practice Statement)**.

### 11. Lista de Revogação de Certificados (CRL)
**11.1.** De onde e como é obtida a CRL pelas aplicações? *(0,5v)*
A aplicação deve analisar uma extensão do próprio certificado chamada **CDP (CRL Distribution Point)**. Neste campo encontra-se normalmente um link HTTP ou LDAP donde a aplicação fará o download do ficheiro com a lista de certificados revogados.

### 12. Online Certificate Status Protocol (OCSP)
**12.1.** Num serviço web é melhor utilizar CRL ou OCSP para validar certificados? Porquê? *(1v)*
O **OCSP**. 
> **Justificação:** As CRLs são listas que crescem ao longo do tempo (pesadas) e só são atualizadas periodicamente, obrigando a downloads custosos. O protocolo OCSP soluciona isto permitindo fazer uma simples query leve à CA para saber o status em tempo real (válido/revogado) de **um único certificado**, melhorando drasticamente a latência e fiabilidade no contexto web.

### 13. Validação Cronológica (Timestamping - TSA)
**13.1.** Que objetos devem ser utilizados quando invocamos um serviço de Timestamp? *(0,5v)*
Devemos enviar apenas o **Hash (o resumo / digest criptográfico)** do documento original, e não o documento completo (para garantir confidencialidade, reduzir a largura de banda e peso).

### 14. Standards (PKCS)
**14.1.** Qual o standard a utilizar se pretendermos aceder a estruturas de dados criptográficos (credenciais, ficheiros, datagroups, ...) contidos num dispositivo criptográfico? *(1v)*
O standard **PKCS#11** (Cryptoki).

**14.2.** Relativamente ao XaDES BES, o que contém a mais o XaDES-T? *(0,5v)*
O perfil `XAdES-T` acrescenta um campo de **selo temporal (Timestamp)** à assinatura digital gerada no nível BES.

### 15. Utilização
**15.1.** Se utilizarmos HTTP Strict Transport Security (HSTS) num servidor, aumentamos ou diminuímos o nível de compatibilidade? Porquê? (3 linhas máximo). *(1v)*
**Diminuímos o nível de compatibilidade**.
> **Justificação:** O HSTS obriga rigorosamente que os clientes comuniquem apenas através de canais seguros (HTTPS). Isto significa que aplicações web de legado (legacy) ou browsers muito antigos sem suporte ou que solicitem o acesso forçado via `http://` deixarão pura e simplesmente de conseguir interagir com esse servidor.
