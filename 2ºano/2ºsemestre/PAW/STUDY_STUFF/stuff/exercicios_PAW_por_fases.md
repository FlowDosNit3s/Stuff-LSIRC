# 🏋️ Exercícios de Treino PAW — Por Fases

> **Baseado no** [guia_estudo_PAW_20.md](file:///c:/Users/diogo/Documents/GitHub/Stuff-LSIRC/2%C2%BAano/2%C2%BAsemestre/PAW/STUDY_STUFF/stuff/guia_estudo_PAW_20.md)
> Cada fase contém exercícios de **escolha múltipla**, **verdadeiro/falso**, **resposta aberta** e **extras** para treinar a fundo.

---

# FASE 1 — Fundamentos (HTML, CSS, Web)

---

## 📝 Escolha Múltipla

**1.1** No modelo cliente-servidor, quem inicia a comunicação?

- a) O servidor
- b) A base de dados
- c) O cliente (browser)
- d) O middleware

<details><summary>✅ Resposta</summary>

**c)** O cliente (browser) inicia a comunicação enviando pedidos HTTP ao servidor.
</details>

---

**1.2** Qual destes NÃO é um método HTTP válido?

- a) GET
- b) POST
- c) CREATE
- d) DELETE

<details><summary>✅ Resposta</summary>

**c)** CREATE não é um método HTTP. É uma operação CRUD. O método HTTP equivalente é POST.
</details>

---

**1.3** Qual o código de status HTTP correto para "utilizador não autenticado"?

- a) 400 Bad Request
- b) 401 Unauthorized
- c) 403 Forbidden
- d) 404 Not Found

<details><summary>✅ Resposta</summary>

**b)** 401 Unauthorized = não autenticado (falta login). 403 Forbidden = autenticado mas sem permissão.
</details>

---

**1.4** Qual das seguintes afirmações é verdadeira?

- a) HTML é uma linguagem de programação
- b) CSS é uma linguagem de estilos
- c) Node.js é uma linguagem de programação
- d) MongoDB é uma base de dados relacional

<details><summary>✅ Resposta</summary>

**b)** CSS é uma linguagem de estilos (apresentação). HTML é de marcação, Node.js é um runtime, MongoDB é NoSQL.
</details>

---

**1.5** Qual é o método HTTP idempotente usado para atualizar dados?

- a) POST
- b) PUT
- c) PATCH
- d) CREATE

<details><summary>✅ Resposta</summary>

**b)** PUT é idempotente e é o método HTTP equivalente à operação CRUD "Update". POST não é idempotente. CREATE não é um método HTTP.
</details>

---

**1.6 (EXTRA)** O que se encontra dentro da tag `<head>` de um documento HTML?

- a) Conteúdo visível da página
- b) Metadados como charset, title e links CSS
- c) Formulários de input
- d) Scripts que manipulam o DOM

<details><summary>✅ Resposta</summary>

**b)** O `<head>` contém metadados (meta charset, title, link para CSS, etc.). O conteúdo do `<head>` NÃO é visível na página.
</details>

---

**1.7 (EXTRA)** Qual destes elementos HTML é inline?

- a) `<div>`
- b) `<p>`
- c) `<span>`
- d) `<table>`

<details><summary>✅ Resposta</summary>

**c)** `<span>` é inline — não inicia nova linha, ocupa só o espaço necessário. Os restantes são block-level.
</details>

---

## ✅❌ Verdadeiro ou Falso

**1.8** "HTML é uma linguagem de programação porque permite criar páginas web."

<details><summary>✅ Resposta</summary>

**FALSO.** HTML é uma linguagem de **marcação** (HyperText Markup Language). Define a estrutura/conteúdo, mas não tem lógica de programação (loops, condições, etc.).
</details>

---

**1.9** "Um formulário HTML pode submeter dados ao servidor sem JavaScript."

<details><summary>✅ Resposta</summary>

**VERDADEIRO.** Basta usar `<form action="/rota" method="POST">` com um botão `<button type="submit">`. O browser submete nativamente.
</details>

---

**1.10** "É seguro usar o método GET para enviar passwords num formulário de login."

<details><summary>✅ Resposta</summary>

**FALSO.** NUNCA usar GET para dados sensíveis. O GET expõe os dados na query string do URL (visível no histórico, logs, etc.). Usar sempre POST para login.
</details>

---

**1.11** "O código HTTP 403 Forbidden indica que o utilizador não está autenticado."

<details><summary>✅ Resposta</summary>

**FALSO.** 403 Forbidden = autenticado mas **sem permissão** (autorização). 401 Unauthorized = não autenticado (falta login). São diferentes!
</details>

---

**1.12 (EXTRA)** "A tag `<img>` requer uma tag de fecho `</img>` para ser válida."

<details><summary>✅ Resposta</summary>

**FALSO.** `<img>` é um elemento self-closing (vazio). A sintaxe correta é `<img src="caminho" alt="descrição">` sem tag de fecho.
</details>

---

**1.13 (EXTRA)** "No Box Model do CSS, o padding está entre o content e o border."

<details><summary>✅ Resposta</summary>

**VERDADEIRO.** A ordem de dentro para fora é: Content → Padding → Border → Margin.
</details>

---

## 📖 Resposta Aberta

**1.14** Explica o modelo cliente-servidor na web, incluindo os papéis de cada parte e o fluxo típico de comunicação. *(1-1.5 val)*

<details><summary>✅ Resposta Modelo</summary>

O modelo **cliente-servidor** é a arquitetura base da web, com dois intervenientes:

**Cliente (browser):** Executa no computador do utilizador (Chrome, Firefox, etc.). É responsável pela interface (frontend), executando HTML, CSS e JavaScript. Inicia a comunicação enviando pedidos HTTP ao servidor. Não deve ter acesso direto a bases de dados nem a credenciais privadas.

**Servidor (ex: Node.js + Express):** Aplicação que corre num computador remoto. Recebe pedidos HTTP, processa-os e devolve respostas. Acede a bases de dados e recursos protegidos. Responsável pela lógica de negócio, segurança e dados (backend).

**Fluxo típico:**
1. O utilizador escreve um URL ou clica num link → o browser envia um pedido HTTP ao servidor.
2. O servidor processa o pedido (middleware → controller → model/BD).
3. O servidor devolve uma resposta HTTP (HTML, JSON, código de status).
4. O browser renderiza a resposta ao utilizador.
</details>

---

**1.15** Indica as 3 formas de adicionar CSS a uma página HTML. Qual é a recomendada e porquê? *(1-1.5 val)*

<details><summary>✅ Resposta Modelo</summary>

As 3 formas de adicionar CSS:

1. **Inline** — Diretamente no elemento HTML via atributo `style`: `<p style="color: red;">Texto</p>`.
2. **Internal** — Dentro de uma tag `<style>` no `<head>` do documento HTML.
3. **External** — Num ficheiro `.css` externo, referenciado com `<link rel="stylesheet" href="ficheiro.css">` no `<head>`.

A forma **recomendada** é a **External** porque:
- Separa a apresentação (CSS) da estrutura (HTML) — princípio de separação de responsabilidades.
- O mesmo ficheiro CSS pode ser reutilizado em múltiplas páginas.
- O browser pode fazer cache do ficheiro CSS, melhorando a performance.
- Facilita a manutenção em projetos maiores.
</details>

---

**1.16 (EXTRA)** O que são Media Queries? Dá um exemplo prático de como tornarias uma página responsiva para telemóveis. *(1 val)*

<details><summary>✅ Resposta Modelo</summary>

**Media Queries** são regras CSS que permitem aplicar estilos diferentes conforme as características do dispositivo (largura do ecrã, orientação, etc.). São a base do design web responsivo.

Exemplo para telemóveis:
```css
/* Estilos base (desktop) */
.container {
  display: flex;
  flex-direction: row;
  gap: 20px;
}

/* Ecrãs até 480px (telemóveis) */
@media (max-width: 480px) {
  .container {
    flex-direction: column;  /* Empilhar verticalmente */
    gap: 10px;
  }
  .sidebar {
    display: none;  /* Esconder sidebar em mobile */
  }
}
```

Isto faz com que em ecrãs de telemóvel, os elementos fiquem empilhados verticalmente em vez de lado a lado, e a sidebar seja escondida para poupar espaço.
</details>

---

# FASE 2 — JavaScript & DOM

---

## 📝 Escolha Múltipla

**2.1** Qual é a diferença entre `==` e `===` em JavaScript?

- a) Não há diferença
- b) `==` compara valor e tipo, `===` só compara valor
- c) `===` compara valor e tipo, `==` compara só valor (com coerção)
- d) `==` é para strings, `===` é para números

<details><summary>✅ Resposta</summary>

**c)** `===` (strict equality) compara valor E tipo. `==` (loose equality) compara apenas o valor, fazendo coerção de tipos. Boa prática: usar sempre `===`.
</details>

---

**2.2** O que retorna `document.getElementById("xyz")`?

- a) Todos os elementos com id "xyz"
- b) Uma coleção de elementos
- c) Um único elemento (o primeiro)
- d) null, porque IDs não existem

<details><summary>✅ Resposta</summary>

**c)** `getElementById` retorna **UM ÚNICO** elemento — o primeiro com esse ID. IDs devem ser únicos no HTML.
</details>

---

**2.3** Qual é a diferença entre `localStorage` e `sessionStorage`?

- a) Não há diferença
- b) `localStorage` persiste permanentemente; `sessionStorage` dura apenas a sessão/tab
- c) `sessionStorage` persiste permanentemente; `localStorage` dura apenas a sessão
- d) Ambos persistem permanentemente

<details><summary>✅ Resposta</summary>

**b)** `localStorage` sobrevive ao fechar do browser (permanente). `sessionStorage` é eliminado quando a tab/sessão é fechada.
</details>

---

**2.4** Qual a diferença entre `var`, `let` e `const`?

- a) São exatamente iguais
- b) `var` tem scope de função + hoisting; `let` tem scope de bloco; `const` é constante
- c) `let` tem scope de função; `var` tem scope de bloco
- d) `const` pode ser reatribuído após a declaração

<details><summary>✅ Resposta</summary>

**b)** `var` tem scope de função e sofre hoisting. `let` e `const` (ES6) têm scope de bloco. `const` não pode ser reatribuído.
</details>

---

**2.5** O que faz o AJAX?

- a) Recarrega toda a página para atualizar conteúdo
- b) Permite atualizar partes da página sem recarregar toda a página
- c) É uma linguagem de programação
- d) É um servidor web

<details><summary>✅ Resposta</summary>

**b)** AJAX (Asynchronous JavaScript and XML) permite fazer pedidos HTTP assíncronos ao servidor e atualizar partes da página sem a recarregar por completo.
</details>

---

**2.6 (EXTRA)** Qual dos seguintes estados NÃO é um estado válido de uma Promise?

- a) pending
- b) fulfilled
- c) loading
- d) rejected

<details><summary>✅ Resposta</summary>

**c)** Os estados de uma Promise são: **pending** (a aguardar), **fulfilled** (resolvida com sucesso) ou **rejected** (rejeitada com erro). "loading" não existe.
</details>

---

**2.7 (EXTRA)** Qual é a diferença entre `innerHTML` e `textContent`?

- a) São iguais
- b) `innerHTML` interpreta HTML; `textContent` trata como texto puro
- c) `textContent` interpreta HTML; `innerHTML` trata como texto puro
- d) Ambos interpretam HTML

<details><summary>✅ Resposta</summary>

**b)** `innerHTML` interpreta o conteúdo como HTML (renderiza tags). `textContent` trata tudo como texto puro (não renderiza tags, mostra-as como texto).
</details>

---

## ✅❌ Verdadeiro ou Falso

**2.8** "Em JavaScript, `var` sofre hoisting mas `let` e `const` não."

<details><summary>✅ Resposta</summary>

**VERDADEIRO.** A declaração `var` "sobe" ao topo da função (hoisting). `let` e `const` (ES6) não sofrem hoisting — são block-scoped.
</details>

---

**2.9** "AJAX requer sempre que a página inteira seja recarregada para funcionar."

<details><summary>✅ Resposta</summary>

**FALSO.** AJAX permite atualizar partes da página **sem recarregar** toda a página. É exatamente essa a sua finalidade.
</details>

---

**2.10** "`JSON.parse()` converte um objeto JavaScript numa string JSON."

<details><summary>✅ Resposta</summary>

**FALSO.** `JSON.parse()` converte uma **string JSON → objeto JavaScript**. O inverso (`JSON.stringify()`) converte objeto → string JSON.
</details>

---

**2.11 (EXTRA)** "Uma arrow function `() => {}` e uma function declaration `function() {}` comportam-se de forma idêntica em todos os contextos."

<details><summary>✅ Resposta</summary>

**FALSO.** Arrow functions não têm o seu próprio `this` (herdam do scope exterior), não sofrem hoisting, e não podem ser usadas como construtores (`new`). Function declarations sofrem hoisting e têm o seu próprio `this`.
</details>

---

**2.12 (EXTRA)** "O `readyState == 4` numa chamada XMLHttpRequest indica que a resposta está completa."

<details><summary>✅ Resposta</summary>

**VERDADEIRO.** `readyState == 4` significa que a operação está completa. Deve-se verificar também `status == 200` para garantir que foi bem-sucedida.
</details>

---

## 📖 Resposta Aberta

**2.13** O seguinte código HTML/JS tem **3 erros**. Identifica e corrige cada um. *(1-2 val)*

```html
<html>
<body>
  <h1>Click Counter</h1>
  <p>Click Number: <span id="contador">0</span></p>
  <button>Click Me</button>
  <script>
    function contarClique() {
      numeroCliques++;
      document.getElementById(counter).textContent = numeroCliques;
    }
  </script>
</body>
</html>
```

<details><summary>✅ Resposta Modelo</summary>

**Erro 1:** A variável `numeroCliques` é usada sem ser declarada. Deve-se declarar antes da função:
```javascript
var numeroCliques = 0;
```

**Erro 2:** `getElementById(counter)` — `counter` é uma variável undefined. O ID do span é "contador". Deve ser uma **string entre aspas**:
```javascript
document.getElementById("contador")
```

**Erro 3:** O botão não tem nenhum evento associado. Nada acontece quando se clica. Deve-se adicionar:
```html
<button onclick="contarClique()">Click Me</button>
```
Ou em alternativa, usar `addEventListener`:
```javascript
document.querySelector('button').addEventListener('click', contarClique);
```
</details>

---

**2.14** Explica como usar `localStorage` para manter dados entre recarregamentos de página. Dá um exemplo com código. *(1 val)*

<details><summary>✅ Resposta Modelo</summary>

O `localStorage` é uma API do browser que permite guardar dados no formato chave-valor de forma **permanente** (sobrevive ao fechar o browser). Os dados são strings, pelo que para objetos/arrays é preciso usar `JSON.stringify()` e `JSON.parse()`.

**Padrão típico:**

```javascript
// 1. Ao carregar a página, ler dados guardados
var dados = JSON.parse(localStorage.getItem('meusDados')) || valorPadrão;

// 2. Mostrar dados no HTML
document.getElementById('elemento').textContent = dados;

// 3. Ao alterar dados, guardar no localStorage
function atualizar(novoDado) {
  dados = novoDado;
  document.getElementById('elemento').textContent = dados;
  localStorage.setItem('meusDados', JSON.stringify(dados));
}
```

O `localStorage.getItem()` retorna `null` se a chave não existir, por isso usa-se `|| valorPadrão` como fallback.
</details>

---

**2.15 (EXTRA)** Escreve uma função JavaScript que faça um pedido AJAX (XMLHttpRequest) a uma API e mostre o resultado num elemento HTML. *(1.5 val)*

<details><summary>✅ Resposta Modelo</summary>

```javascript
function carregarDados() {
  var xhttp = new XMLHttpRequest();

  xhttp.onreadystatechange = function() {
    if (this.readyState == 4 && this.status == 200) {
      var dados = JSON.parse(this.responseText);
      document.getElementById("resultado").innerHTML = dados.nome;
    } else if (this.readyState == 4) {
      document.getElementById("resultado").innerHTML = "Erro ao carregar dados";
    }
  };

  xhttp.open("GET", "/api/dados", true); // true = assíncrono
  xhttp.send();
}
```

**Explicação:**
- `XMLHttpRequest()` cria um novo pedido HTTP.
- `onreadystatechange` é chamado cada vez que o estado muda.
- `readyState == 4` indica resposta completa.
- `status == 200` indica sucesso.
- `true` no `open()` torna o pedido assíncrono.
- `JSON.parse()` converte a resposta string em objeto JavaScript.
</details>

---

**2.16 (EXTRA)** Explica a diferença entre Promises e async/await em JavaScript. Quando usarias cada um? *(1 val)*

<details><summary>✅ Resposta Modelo</summary>

**Promises** e **async/await** são ambos mecanismos para lidar com código assíncrono em JavaScript:

**Promises:**
```javascript
fetch('/api/dados')
  .then(res => res.json())
  .then(dados => console.log(dados))
  .catch(err => console.error(err));
```
- Usam `.then()` para encadear operações e `.catch()` para erros.
- Podem tornar-se complexas com muitas operações encadeadas ("callback hell" atenuado).

**async/await (ES2017):**
```javascript
async function obterDados() {
  try {
    const res = await fetch('/api/dados');
    const dados = await res.json();
    console.log(dados);
  } catch (err) {
    console.error(err);
  }
}
```
- Sintaxe mais legível, parece código síncrono.
- Usa `try/catch` para tratamento de erros.
- A função deve ser marcada com `async`.
- `await` pausa a execução da função até a Promise resolver.

**Quando usar:** async/await é preferível para a maioria dos casos (mais legível). Promises com `.then()` são úteis para operações paralelas com `Promise.all()`.
</details>

---

# FASE 3 — Backend: Node.js, Express, BD & REST

---

## 📝 Escolha Múltipla

**3.1** O que é o Node.js?

- a) Uma linguagem de programação
- b) Um framework web
- c) Um runtime JavaScript para executar JS fora do browser
- d) Um servidor web pronto a usar

<details><summary>✅ Resposta</summary>

**c)** Node.js é um **runtime** JavaScript baseado no motor V8 do Chrome. Não é uma linguagem, nem um framework, nem um servidor web por si só — é preciso codificar o servidor HTTP.
</details>

---

**3.2** Qual a diferença entre `req.params`, `req.query` e `req.body`?

- a) São todos iguais
- b) `req.params` vem da rota (`:id`), `req.query` vem da query string (`?key=val`), `req.body` vem do corpo do pedido
- c) `req.body` vem do URL, `req.params` vem do corpo do pedido
- d) `req.query` vem do corpo do pedido POST

<details><summary>✅ Resposta</summary>

**b)** `req.params` → parâmetros de rota (ex: `/produto/:id`). `req.query` → query strings (ex: `?q=teste`). `req.body` → corpo do pedido (POST/PUT, dados de formulário ou JSON).
</details>

---

**3.3** O que o NPM NÃO faz?

- a) Instalar packages/módulos
- b) Gerir dependências
- c) Criar componentes Angular
- d) Executar scripts (npm start)

<details><summary>✅ Resposta</summary>

**c)** Criar componentes Angular é feito pelo Angular CLI (`ng generate component`), não pelo NPM. NPM instala packages, gere dependências e executa scripts.
</details>

---

**3.4** O que é middleware no Express?

- a) Uma base de dados
- b) Funções executadas entre o pedido e a resposta com acesso a req, res e next()
- c) O template engine EJS
- d) O motor de rendering do Angular

<details><summary>✅ Resposta</summary>

**b)** Middleware são funções que se executam entre o pedido HTTP e a resposta final, com acesso a `req`, `res` e `next()`. É fundamental chamar `next()` para passar ao próximo middleware.
</details>

---

**3.5** Qual tag EJS produz output HTML escaped?

- a) `<% %>`
- b) `<%= %>`
- c) `<%- %>`
- d) `<!-- %>`

<details><summary>✅ Resposta</summary>

**b)** `<%= %>` produz output com HTML escaping (seguro contra XSS). `<% %>` executa código sem output. `<%- %>` produz output sem escaping.
</details>

---

**3.6 (EXTRA)** No padrão MVC aplicado a Express, onde ficam os schemas/models Mongoose?

- a) Na pasta `views/`
- b) Na pasta `controllers/`
- c) Na pasta `models/`
- d) Na pasta `public/`

<details><summary>✅ Resposta</summary>

**c)** No MVC, os schemas/models Mongoose ficam na pasta `models/`. Views ficam em `views/` (templates EJS), e controllers em `controllers/` (lógica req/res).
</details>

---

**3.7 (EXTRA)** O que distingue uma BD relacional (SQL) de uma BD NoSQL como o MongoDB?

- a) SQL não suporta transações
- b) SQL tem schema rígido e bem definido; NoSQL é flexível e sem schema obrigatório
- c) NoSQL só guarda dados em formato XML
- d) SQL escala melhor horizontalmente

<details><summary>✅ Resposta</summary>

**b)** SQL tem schema rígido, transações ACID e escalabilidade vertical. NoSQL (MongoDB) tem schema flexível, consistência eventual e escalabilidade horizontal.
</details>

---

**3.8 (EXTRA)** Qual é o middleware de erros correto no Express?

- a) `app.use(function(req, res, next) { ... })`
- b) `app.use(function(err, req, res, next) { ... })`
- c) `app.error(function(err) { ... })`
- d) `app.catch(function(err, res) { ... })`

<details><summary>✅ Resposta</summary>

**b)** O middleware de erros no Express tem obrigatoriamente **4 parâmetros**: `(err, req, res, next)`. Os 4 parâmetros distinguem-no do middleware normal.
</details>

---

## ✅❌ Verdadeiro ou Falso

**3.9** "Express é um framework que executa no browser do utilizador."

<details><summary>✅ Resposta</summary>

**FALSO.** Express é um framework web **server-side** que corre em Node.js no servidor. Não executa no browser.
</details>

---

**3.10** "REST APIs comunicam APENAS em JSON."

<details><summary>✅ Resposta</summary>

**FALSO.** REST permite JSON **e** XML (e outros formatos de texto). Não está limitado a JSON. REST NÃO usa formato binário.
</details>

---

**3.11** "O Mongoose é uma base de dados NoSQL."

<details><summary>✅ Resposta</summary>

**FALSO.** Mongoose é um **driver/ODM** (Object Data Modeling) para MongoDB em Node.js. A base de dados é o **MongoDB**. Mongoose permite definir schemas, criar models e fazer CRUD.
</details>

---

**3.12** "JWT (JSON Web Token) obriga a usar uma REST API."

<details><summary>✅ Resposta</summary>

**FALSO.** JWT pode ser usado com qualquer tipo de aplicação web (REST API, Express + EJS, etc.). Não depende do tipo de rendering nem da arquitetura.
</details>

---

**3.13** "A ordem do middleware no Express é irrelevante."

<details><summary>✅ Resposta</summary>

**FALSO.** A **ordem importa**! O middleware é executado sequencialmente. Se um middleware de autenticação estiver depois de uma rota desprotegida, essa rota não será protegida.
</details>

---

**3.14 (EXTRA)** "O NPM pode ser usado para instalar o MongoDB no sistema."

<details><summary>✅ Resposta</summary>

**FALSO.** NPM instala o **driver** de MongoDB (ex: `mongoose`), não o próprio MongoDB. A instalação do MongoDB é feita separadamente.
</details>

---

**3.15 (EXTRA)** "A leitura bloqueante (síncrona) com `fs.readFileSync()` é a abordagem recomendada em Node.js."

<details><summary>✅ Resposta</summary>

**FALSO.** Em Node.js, a abordagem recomendada é **não bloqueante (assíncrona)** com `fs.readFile()` e callbacks/promises. A leitura síncrona bloqueia a thread única do Node.js, prejudicando a performance.
</details>

---

## 📖 Resposta Aberta

**3.16** Explica o padrão MVC e como se aplica numa aplicação Express com MongoDB. *(1.5 val)*

<details><summary>✅ Resposta Modelo</summary>

O padrão **MVC** (Model-View-Controller) é um padrão de arquitetura que separa a aplicação em três camadas:

- **Model (Modelo):** Responsável pelos dados e lógica de negócio. Em Express com MongoDB, corresponde aos **schemas e models Mongoose** na pasta `models/`.
  ```javascript
  const Produto = mongoose.model('Produto', new mongoose.Schema({ nome: String, preco: Number }));
  ```

- **View (Vista):** Responsável pela apresentação/interface. Em Express, são os **templates EJS** na pasta `views/`.
  ```html
  <h1><%= produto.nome %></h1>
  <p>Preço: <%= produto.preco %>€</p>
  ```

- **Controller (Controlador):** Liga o Model à View. Processa os pedidos HTTP (`req`/`res`), obtém dados do Model e envia-os para a View. Ficam na pasta `controllers/`.
  ```javascript
  exports.listarProdutos = async (req, res) => {
    const produtos = await Produto.find();
    res.render('produtos', { produtos });
  };
  ```

**Vantagem:** Separação de responsabilidades — cada camada pode ser modificada independentemente.
</details>

---

**3.17** Explica a diferença entre autenticação e autorização. Indica os códigos HTTP associados e dá exemplos práticos. *(1.5 val)*

<details><summary>✅ Resposta Modelo</summary>

| | Autenticação | Autorização |
|-|-------------|-------------|
| **Pergunta** | "Quem és tu?" | "O que podes fazer?" |
| **Quando** | No login | Após login, em cada pedido |
| **Código HTTP** | **401** Unauthorized | **403** Forbidden |

**Autenticação** — Verificar a identidade do utilizador. Exemplo: o utilizador faz login com email + password. O servidor verifica as credenciais e, se corretas, gera um **JWT** com `jwt.sign()`. Código 401 = "não sabemos quem és, faz login".

**Autorização** — Verificar se o utilizador autenticado tem permissão para aceder ao recurso. Exemplo: um utilizador normal tenta aceder à página `/admin`. O servidor verifica o JWT, confirma a identidade (autenticação ok), mas o `role` não é "ADMIN". Código 403 = "sabemos quem és, mas não tens permissão".

Em termos de implementação:
- **Autenticação:** `jsonwebtoken` (jwt.sign/verify), `passport`
- **Autorização:** Guards, ACL (Access Control Lists), verificação de roles no middleware
</details>

---

**3.18** Descreve as propriedades de uma REST API e como implementarias uma operação CRUD completa em Express. *(2 val)*

<details><summary>✅ Resposta Modelo</summary>

**Propriedades REST:**
- **Stateless** — cada pedido é independente; o servidor não guarda estado entre pedidos
- **Cacheable** — respostas podem ser armazenadas em cache
- **Interface uniforme** — URLs consistentes, métodos HTTP padronizados
- **Sistema em camadas** — cliente não sabe se comunica diretamente com o servidor final
- Dados em **texto** (JSON ou XML), nunca em binário

**CRUD em Express com Mongoose:**

```javascript
const Produto = require('./models/Produto');

// CREATE → POST
app.post('/api/produtos', async (req, res) => {
  const produto = new Produto(req.body);
  await produto.save();
  res.status(201).json(produto);
});

// READ → GET
app.get('/api/produtos', async (req, res) => {
  const produtos = await Produto.find();
  res.json(produtos);
});

// UPDATE → PUT
app.put('/api/produtos/:id', async (req, res) => {
  const produto = await Produto.findByIdAndUpdate(req.params.id, req.body, { new: true });
  res.json(produto);
});

// DELETE → DELETE
app.delete('/api/produtos/:id', async (req, res) => {
  await Produto.findByIdAndDelete(req.params.id);
  res.status(204).send();
});
```
</details>

---

**3.19 (EXTRA)** Escreve um middleware Express que registe (log) a data/hora, método e URL de cada pedido HTTP recebido. *(1 val)*

<details><summary>✅ Resposta Modelo</summary>

```javascript
// Middleware de logging - deve ser registado ANTES das rotas
app.use(function(req, res, next) {
  const agora = new Date().toISOString();
  console.log(`[${agora}] ${req.method} ${req.url}`);
  next(); // IMPORTANTE: chamar next() para passar ao próximo middleware!
});

// Exemplo de output:
// [2024-01-15T10:30:00.000Z] GET /api/produtos
// [2024-01-15T10:30:05.000Z] POST /api/produtos
```

**Pontos-chave:**
- O middleware tem acesso a `req`, `res` e `next()`.
- `next()` DEVE ser chamado, caso contrário o pedido fica "pendurado" e nunca chega à rota.
- A ordem importa: este middleware deve ser registado com `app.use()` **antes** das rotas para capturar todos os pedidos.
</details>

---

**3.20 (EXTRA)** Explica como funciona o JWT para autenticação. Escreve código para criar e verificar um token. *(1.5 val)*

<details><summary>✅ Resposta Modelo</summary>

**JWT (JSON Web Token)** é um mecanismo de autenticação stateless baseado em tokens assinados digitalmente.

**Fluxo:**
1. O utilizador faz login (envia email + password).
2. O servidor valida as credenciais e gera um JWT com `jwt.sign()`.
3. O token é enviado ao cliente, que o guarda (localStorage, cookie).
4. Em cada pedido seguinte, o cliente envia o token no header Authorization.
5. O servidor verifica o token com `jwt.verify()` antes de processar o pedido.

**Código:**

```javascript
const jwt = require('jsonwebtoken');
const SEGREDO = 'minha-chave-secreta';

// CRIAR token (no login)
app.post('/api/login', async (req, res) => {
  const user = await User.findOne({ email: req.body.email });
  if (!user || !verificarPassword(req.body.password, user.password)) {
    return res.status(401).json({ msg: 'Credenciais inválidas' });
  }
  const token = jwt.sign(
    { userId: user._id, role: user.role },
    SEGREDO,
    { expiresIn: '1h' }
  );
  res.json({ token });
});

// VERIFICAR token (middleware)
function verificarToken(req, res, next) {
  const token = req.headers['authorization']?.split(' ')[1]; // "Bearer TOKEN"
  if (!token) return res.status(401).json({ msg: 'Token ausente' });

  jwt.verify(token, SEGREDO, (err, decoded) => {
    if (err) return res.status(401).json({ msg: 'Token inválido' });
    req.user = decoded; // decoded contém { userId, role }
    next();
  });
}

// Proteger rotas
app.get('/api/admin', verificarToken, (req, res) => {
  res.json({ msg: 'Bem-vindo, admin!', user: req.user });
});
```
</details>

---

# FASE 4 — Angular & Full-Stack

---

## 📝 Escolha Múltipla

**4.1** Qual é a linguagem usada no Angular?

- a) JavaScript
- b) Java
- c) TypeScript
- d) CoffeeScript

<details><summary>✅ Resposta</summary>

**c)** Angular usa **TypeScript**, um superset de JavaScript criado pela Microsoft que adiciona tipos estáticos e compilação. Ficheiros `.ts` são compilados para `.js` com `tsc`.
</details>

---

**4.2** Qual o comando para criar um novo projeto Angular?

- a) `npm new my-app`
- b) `ng new my-app`
- c) `angular create my-app`
- d) `npm create angular my-app`

<details><summary>✅ Resposta</summary>

**b)** `ng new my-app` é o comando correto do Angular CLI. **`npm new` NÃO EXISTE!** É uma armadilha frequente de exame.
</details>

---

**4.3** Em Angular, qual sintaxe implementa two-way data binding?

- a) `{{ variavel }}`
- b) `[propriedade]="valor"`
- c) `(evento)="handler()"`
- d) `[(ngModel)]="variavel"`

<details><summary>✅ Resposta</summary>

**d)** `[(ngModel)]` é o two-way binding (bidirecional). `{{ }}` é interpolation (component→view). `[ ]` é property binding (component→view). `( )` é event binding (view→component).
</details>

---

**4.4** Os serviços Angular têm template HTML?

- a) Sim, todos os serviços têm template
- b) Não, apenas componentes têm template HTML
- c) Depende do tipo de serviço
- d) Sim, mas apenas se usarem @Injectable()

<details><summary>✅ Resposta</summary>

**b)** Serviços Angular são classes TypeScript puras com `@Injectable()`. **NÃO têm template HTML** — só componentes têm. Serviços são para lógica de negócio, HTTP e estado partilhado.
</details>

---

**4.5** Qual a diferença entre um Guard e um Interceptor em Angular?

- a) Não há diferença
- b) Guard protege rotas/páginas; Interceptor modifica pedidos HTTP
- c) Guard modifica pedidos HTTP; Interceptor protege rotas
- d) Ambos protegem rotas

<details><summary>✅ Resposta</summary>

**b)** Guard implementa `CanActivate` e protege **rotas/páginas** (decide se o utilizador pode navegar). Interceptor implementa `HttpInterceptor` e modifica **pedidos HTTP** (ex: adicionar token JWT ao header).
</details>

---

**4.6** Qual hook do ciclo de vida Angular é o mais usado e executado uma única vez após inicialização?

- a) `ngOnChanges()`
- b) `ngOnInit()`
- c) `ngOnDestroy()`
- d) `ngAfterViewInit()`

<details><summary>✅ Resposta</summary>

**b)** `ngOnInit()` é executado uma única vez após a inicialização do componente. É o mais usado para carregar dados iniciais (ex: chamar serviços). `ngOnChanges()` executa sempre que os inputs mudam. `ngOnDestroy()` executa antes da destruição.
</details>

---

**4.7 (EXTRA)** O que é uma SPA (Single Page Application)?

- a) Uma aplicação que usa apenas uma única tag HTML
- b) Uma aplicação com uma única página que atualiza conteúdo dinamicamente sem recarregar
- c) Uma aplicação sem CSS
- d) Uma aplicação que não usa JavaScript

<details><summary>✅ Resposta</summary>

**b)** SPA é uma aplicação web com uma única página HTML que carrega conteúdo dinamicamente via JavaScript, sem recarregar a página inteira. Angular, React e Vue são frameworks SPA.
</details>

---

**4.8 (EXTRA)** Como é que um componente filho recebe dados do componente pai em Angular?

- a) Via `@Output()`
- b) Via `@Input()`
- c) Via `localStorage`
- d) Via `req.body`

<details><summary>✅ Resposta</summary>

**b)** O decorador `@Input()` no componente filho permite receber dados do pai via property binding: `<app-filho [propriedade]="dadoDoPai">`. `@Output()` é para o filho enviar dados ao pai (via EventEmitter).
</details>

---

## ✅❌ Verdadeiro ou Falso

**4.9** "Angular é um framework fullstack que executa tanto no frontend como no backend."

<details><summary>✅ Resposta</summary>

**FALSO.** Angular é um framework **client-side** (frontend). Executa no **browser**, NÃO no runtime Node.js nem no servidor. Para backend usa-se Node.js + Express.
</details>

---

**4.10** "Em Angular, a rota de login deve ter `canActivate: [AuthGuard]` para ser segura."

<details><summary>✅ Resposta</summary>

**FALSO.** Rotas de `login` e `register` **NÃO devem ter** `canActivate`! Isso impediria utilizadores não-autenticados de fazerem login (o guard bloquearia o acesso por não terem token).
</details>

---

**4.11** "TypeScript é um superset de JavaScript — todo código JS válido é também TS válido."

<details><summary>✅ Resposta</summary>

**VERDADEIRO.** TypeScript é um superset de JavaScript. Todo código JavaScript válido é aceite pelo TypeScript. O TS adiciona tipos estáticos e compilação, mas mantém compatibilidade total com JS.
</details>

---

**4.12** "Serviços Angular podem ser injetados em múltiplos componentes."

<details><summary>✅ Resposta</summary>

**VERDADEIRO.** Serviços Angular usam Dependency Injection (DI) com `@Injectable({ providedIn: 'root' })`, criando uma instância singleton partilhada por todos os componentes que o injetam.
</details>

---

**4.13 (EXTRA)** "O `ng build` gera o build de produção na pasta `node_modules/`."

<details><summary>✅ Resposta</summary>

**FALSO.** `ng build` gera o build de produção na pasta `dist/`, não em `node_modules/`. A pasta `node_modules/` contém as dependências instaladas pelo NPM.
</details>

---

**4.14 (EXTRA)** "Um Observable em Angular retorna o valor imediatamente de forma síncrona."

<details><summary>✅ Resposta</summary>

**FALSO.** Observables são **assíncronos e não bloqueantes**. O valor só é obtido quando se chama `.subscribe()`. É programação reativa — o componente "reage" quando os dados chegam.
</details>

---

## 📖 Resposta Aberta

**4.15** Explica a diferença entre Guards e Interceptors em Angular. Dá exemplos de código de cada um. *(1.5 val)*

<details><summary>✅ Resposta Modelo</summary>

| | Guard | Interceptor |
|-|-------|-------------|
| **Protege** | **Rotas**/páginas | **Pedidos HTTP** |
| **Interface** | `CanActivate` | `HttpInterceptor` |
| **Quando age** | Antes de navegar para uma rota | Antes de enviar um pedido HTTP |
| **Exemplo** | Bloquear acesso a /admin | Adicionar token JWT ao header |

**Guard — AuthGuard:**
```typescript
@Injectable()
export class AuthGuard implements CanActivate {
  constructor(private router: Router) {}

  canActivate(): boolean {
    if (localStorage.getItem('token')) {
      return true;  // Permitir navegação
    }
    this.router.navigate(['/login']);
    return false;  // Bloquear navegação
  }
}

// No routing:
{ path: 'admin', component: AdminComponent, canActivate: [AuthGuard] }
```

**Interceptor — AuthInterceptor:**
```typescript
@Injectable()
export class AuthInterceptor implements HttpInterceptor {
  intercept(req: HttpRequest<any>, next: HttpHandler) {
    const token = localStorage.getItem('token');
    const cloned = req.clone({
      headers: req.headers.set('Authorization', 'Bearer ' + token)
    });
    return next.handle(cloned);  // Enviar pedido modificado
  }
}
```

O Guard decide se o utilizador **pode navegar** para uma página. O Interceptor **modifica** os pedidos HTTP antes de serem enviados (ex: adicionar automaticamente o token JWT a todos os pedidos).
</details>

---

**4.16** Como funciona um serviço Angular? Explica o que são `@Injectable()`, `HttpClient` e `Observable`. *(1.5 val)*

<details><summary>✅ Resposta Modelo</summary>

Um **serviço Angular** é uma classe TypeScript que encapsula lógica reutilizável (HTTP, estado, validação). Não tem template HTML.

**`@Injectable({ providedIn: 'root' })`** — Decorador que marca a classe como injetável via Dependency Injection. O `providedIn: 'root'` cria uma instância singleton partilhada em toda a aplicação.

**`HttpClient`** — Módulo Angular para fazer pedidos HTTP a APIs REST. Injetado no construtor do serviço. Retorna **Observables**.

**`Observable` (RxJS)** — Representa um fluxo de dados assíncrono. O componente usa `.subscribe()` para "ouvir" quando os dados chegam.

```typescript
@Injectable({ providedIn: 'root' })
export class ProductService {
  constructor(private http: HttpClient) {}

  getProducts(): Observable<Product[]> {
    return this.http.get<Product[]>('http://api/products');
  }
}

// No componente:
export class ProductListComponent implements OnInit {
  products: Product[] = [];

  constructor(private productService: ProductService) {} // DI

  ngOnInit() {
    this.productService.getProducts().subscribe(data => {
      this.products = data; // dados chegam de forma assíncrona
    });
  }
}
```

**Fluxo:** Componente injeta Serviço → Serviço usa HttpClient → HttpClient retorna Observable → Componente faz subscribe para obter dados.
</details>

---

**4.17 (EXTRA)** Explica os 4 tipos de data binding em Angular, com exemplos de código para cada um. *(1.5 val)*

<details><summary>✅ Resposta Modelo</summary>

Angular suporta 4 tipos de data binding entre o componente (TypeScript) e o template (HTML):

**1. Interpolation `{{ }}`** — Component → View (unidirecional)
```html
<h1>{{ titulo }}</h1>
<p>Bem-vindo, {{ nomeUtilizador }}!</p>
```
Mostra o valor da variável do componente no template.

**2. Property Binding `[ ]`** — Component → View (unidirecional)
```html
<img [src]="imagemUrl">
<button [disabled]="isLoading">Enviar</button>
```
Liga uma propriedade HTML a uma variável do componente.

**3. Event Binding `( )`** — View → Component (unidirecional)
```html
<button (click)="guardar()">Guardar</button>
<input (keyup)="pesquisar($event)">
```
Quando o evento ocorre no template, chama o método do componente.

**4. Two-Way Binding `[( )]`** — Bidirecional ⭐
```html
<input [(ngModel)]="nomeUtilizador">
<p>Olá, {{ nomeUtilizador }}</p>
```
Alterações no input atualizam a variável e vice-versa. Requer importar `FormsModule`.
</details>

---

**4.18 (EXTRA)** Descreve a arquitetura MEAN Stack. Porque é que o Angular NUNCA deve comunicar diretamente com a base de dados? *(1.5 val)*

<details><summary>✅ Resposta Modelo</summary>

**MEAN Stack** = **M**ongoDB + **E**xpress + **A**ngular + **N**ode.js

```
Angular (Frontend/Browser) ←→ Express + Node.js (Backend/Servidor) ←→ MongoDB (BD)
```

- **MongoDB** — Base de dados NoSQL (documentos JSON)
- **Express** — Framework web para Node.js (routing, middleware, API)
- **Angular** — Framework client-side (SPA no browser)
- **Node.js** — Runtime JavaScript para o servidor

**Porque Angular NUNCA deve comunicar diretamente com a BD:**

1. **Segurança:** O código Angular executa no **browser** do utilizador — é completamente visível e editável via DevTools. Credenciais da BD ficariam expostas.
2. **Código exposto:** Qualquer pessoa pode inspecionar o JavaScript no browser, incluindo connection strings, queries e lógica de negócio.
3. **Manipulação:** Um utilizador malicioso poderia modificar queries para aceder/eliminar dados indevidamente (SQL/NoSQL injection direta).
4. **Boa prática:** Usar o backend (Express) como **intermediário** (broker). Angular envia pedidos HTTP ao Express, que valida, processa e comunica com o MongoDB de forma segura.

```javascript
// No Express (servidor) - CORRETO
app.use(express.static('dist/angular-app'));
app.get('/api/produtos', async (req, res) => {
  const produtos = await Produto.find(); // Acesso à BD seguro no servidor
  res.json(produtos);
});
```
</details>

---

# FASE 5 — Segurança & Identificação de Tecnologia

---

## 📝 Escolha Múltipla

**5.1** Porque é que a validação deve ser feita no frontend E no backend?

- a) Porque o frontend é mais seguro que o backend
- b) Frontend para UX (feedback rápido), backend para segurança (utilizador pode contornar frontend)
- c) Porque o backend não consegue validar dados
- d) Porque a validação só funciona no frontend

<details><summary>✅ Resposta</summary>

**b)** Frontend: melhor UX (feedback imediato). Backend: **segurança** — o utilizador pode desativar JavaScript, enviar pedidos com Postman/curl, ou manipular o HTML nos DevTools. Confiar apenas no frontend é uma falha de segurança grave.
</details>

---

**5.2** Qual excerto de código identifica um **serviço Angular**?

- a) `req, res, next`, `app.use()`
- b) `@Injectable()`, `Observable`, `HttpClient`
- c) `mongoose.Schema`, `mongoose.model`
- d) `document.getElementById()`, `addEventListener`

<details><summary>✅ Resposta</summary>

**b)** `@Injectable()`, `Observable` e `HttpClient` são indicadores de um serviço Angular. a) é Express middleware, c) é Mongoose, d) é JavaScript DOM.
</details>

---

**5.3** Qual das seguintes afirmações sobre segurança é FALSA?

- a) Credenciais nunca devem estar no código frontend
- b) Passwords devem ser guardadas em formato hashed
- c) Validação apenas no frontend é suficiente para segurança
- d) HTTPS deve ser usado em produção

<details><summary>✅ Resposta</summary>

**c)** Validação apenas no frontend NÃO é suficiente. O utilizador pode contornar o frontend (desativar JS, usar Postman, manipular DevTools). A validação DEVE ser feita em AMBOS os lados.
</details>

---

**5.4 (EXTRA)** Que tecnologia usarias se visses `<%= %>` e `<% %>` num ficheiro?

- a) Angular Component
- b) React JSX
- c) EJS (Template Engine)
- d) Vue.js

<details><summary>✅ Resposta</summary>

**c)** `<%= %>` e `<% %>` são tags EJS (Embedded JavaScript). `<%= %>` produz output HTML escaped, `<% %>` executa código sem output, `<%- %>` produz output sem escape.
</details>

---

**5.5 (EXTRA)** Qual o principal risco de usar `innerHTML` em vez de `textContent`?

- a) Performance mais lenta
- b) Injeção de código HTML/JavaScript malicioso (XSS)
- c) Não funciona em todos os browsers
- d) Não há risco

<details><summary>✅ Resposta</summary>

**b)** `innerHTML` interpreta HTML, o que pode levar a ataques **XSS** (Cross-Site Scripting) se o conteúdo vier de input do utilizador. `textContent` é mais seguro pois trata tudo como texto puro.
</details>

---

## ✅❌ Verdadeiro ou Falso

**5.6** "MongoDB pode correr diretamente no frontend (browser)."

<details><summary>✅ Resposta</summary>

**FALSO.** Bases de dados correm no **servidor**, não no browser. O frontend comunica com a BD através do backend (Express/Node.js). Nunca ligar o frontend diretamente à BD.
</details>

---

**5.7** "JavaScript funciona apenas no frontend (browser)."

<details><summary>✅ Resposta</summary>

**FALSO.** JavaScript funciona no **frontend** (browser) E no **backend** (Node.js). Node.js é um runtime que permite executar JS no servidor.
</details>

---

**5.8** "Um serviço Angular pode ter um template HTML associado."

<details><summary>✅ Resposta</summary>

**FALSO.** Serviços Angular NÃO têm template HTML. São classes TypeScript puras com `@Injectable()`. Apenas **componentes** têm templates.
</details>

---

**5.9 (EXTRA)** "CORS (Cross-Origin Resource Sharing) é uma restrição imposta pelo servidor."

<details><summary>✅ Resposta</summary>

**FALSO.** CORS é imposto pelo **browser** (Same-Origin Policy). O browser bloqueia pedidos a domínios diferentes. A solução é configurar o **servidor** para enviar headers CORS que autorizem o acesso (`app.use(cors())`).
</details>

---

**5.10 (EXTRA)** "O Swagger/OpenAPI serve para executar APIs REST automaticamente."

<details><summary>✅ Resposta</summary>

**FALSO.** Swagger/OpenAPI é um padrão para **documentar** APIs REST. O `swagger-ui-express` gera documentação interativa e um ambiente de testes, mas não executa as APIs automaticamente.
</details>

---

## 📖 Resposta Aberta

**5.11** Explica porque é que a validação de dados deve ser feita no frontend E no backend. Dá 3 razões concretas para validar no backend. *(2-3 val)*

<details><summary>✅ Resposta Modelo</summary>

A validação de dados DEVE ser feita em **AMBOS** os lados:

**Frontend** — Para melhorar a **experiência do utilizador** (UX). Dá feedback imediato (sem esperar pela resposta do servidor). Usa atributos HTML (`required`, `minlength`) ou validação Angular. Reduz pedidos desnecessários ao servidor.

**Backend** — Para garantir **segurança**. O frontend é apenas uma "sugestão" — o utilizador pode contorná-lo.

**3 razões concretas para validar no backend:**

1. **JavaScript desativado:** O utilizador pode desativar JavaScript no browser, eliminando toda a validação frontend.
2. **Pedidos diretos:** O utilizador pode enviar pedidos diretamente ao servidor usando ferramentas como **Postman** ou **curl**, contornando completamente a interface web.
3. **Manipulação do código:** O utilizador pode abrir os DevTools do browser e **modificar** o HTML, remover atributos de validação, ou alterar o JavaScript antes de submeter.

**Conclusão:** Confiar apenas na validação frontend é uma **falha de segurança grave**. O backend é a última linha de defesa e deve validar TODOS os dados recebidos, independentemente de o frontend já os ter validado.

**Implementação:**
- Frontend: atributos HTML (`required`), validação Angular (`Validators.required`)
- Backend: middleware Express, Mongoose validators, sanitização de inputs
</details>

---

**5.12** Dado o seguinte excerto de código, identifica a tecnologia usada e explica o que o código faz: *(1.5 val)*

```typescript
@Injectable({ providedIn: 'root' })
export class AuthInterceptor implements HttpInterceptor {
  intercept(req: HttpRequest<any>, next: HttpHandler) {
    const token = localStorage.getItem('token');
    const cloned = req.clone({
      headers: req.headers.set('Authorization', 'Bearer ' + token)
    });
    return next.handle(cloned);
  }
}
```

<details><summary>✅ Resposta Modelo</summary>

**Tecnologia:** Este é um **Angular Interceptor** (interceptador HTTP).

**Identificação:** Presença de `@Injectable()`, `HttpInterceptor`, `intercept`, `HttpRequest`, `HttpHandler` — todos indicadores de um interceptor Angular.

**O que faz:**
1. É um serviço injetável (`@Injectable`) que implementa a interface `HttpInterceptor`.
2. O método `intercept()` é chamado automaticamente **antes de cada pedido HTTP** feito pela aplicação Angular.
3. Obtém o token JWT guardado em `localStorage`.
4. Clona o pedido original (`req.clone()`) — os pedidos HTTP são imutáveis em Angular.
5. Adiciona o header `Authorization: Bearer <token>` ao pedido clonado.
6. Envia o pedido modificado ao servidor com `next.handle(cloned)`.

**Propósito:** Automatizar a inclusão do token JWT em todos os pedidos HTTP, evitando ter de o adicionar manualmente em cada chamada ao serviço. Isto centraliza a lógica de autenticação.
</details>

---

**5.13** Descreve o fluxo completo de uma aplicação MEAN Stack, desde que o utilizador clica num botão até receber a resposta. *(1.5 val)*

<details><summary>✅ Resposta Modelo</summary>

**Fluxo completo MEAN Stack:**

1. **Utilizador interage** — Clica num botão no Angular (SPA no browser).

2. **Guard verifica acesso** — O `AuthGuard` verifica se o utilizador tem token JWT em `localStorage`. Se não tiver, redireciona para `/login`.

3. **Componente chama serviço** — O componente Angular usa um serviço (`@Injectable`) que faz um pedido HTTP via `HttpClient`.

4. **Interceptor modifica pedido** — O `AuthInterceptor` intercepta o pedido HTTP e adiciona automaticamente o token JWT no header `Authorization: Bearer <token>`.

5. **Express recebe pedido** — O servidor Node.js + Express recebe o pedido HTTP na rota correspondente.

6. **Middleware verifica JWT** — O middleware de autenticação verifica o token com `jwt.verify()`. Se inválido, retorna 401.

7. **Controller processa** — O controller (no padrão MVC) executa a lógica de negócio e interage com o MongoDB via Mongoose (Model).

8. **MongoDB responde** — A base de dados retorna os dados solicitados (ex: `Produto.find()`).

9. **Express envia resposta JSON** — O servidor responde ao Angular com dados JSON e código de status HTTP.

10. **Angular atualiza View** — O Observable retornado pelo `HttpClient` é resolvido. O componente recebe os dados via `.subscribe()` e a View é atualizada automaticamente via data binding.
</details>

---

**5.14 (EXTRA)** Identifica a tecnologia de cada um dos seguintes excertos de código: *(1 val)*

```
a) app.use(function(req, res, next) { next(); });
b) @Component({ selector: 'app-root', templateUrl: './app.component.html' })
c) const schema = new mongoose.Schema({ nome: String });
d) jwt.sign({ userId: 1 }, 'segredo', { expiresIn: '1h' });
e) this.http.get('/api/dados').subscribe(data => {});
```

<details><summary>✅ Resposta Modelo</summary>

- **a)** `req, res, next`, `app.use()` → **Express Middleware**
- **b)** `@Component`, `selector`, `templateUrl` → **Angular Component**
- **c)** `mongoose.Schema` → **Mongoose (MongoDB driver/ODM)**
- **d)** `jwt.sign()` → **JWT (jsonwebtoken)** — criação de token
- **e)** `this.http.get()`, `.subscribe()` → **Angular Service** (HttpClient + Observable/RxJS)
</details>

---

# 🔥 EXERCÍCIOS INTEGRADOS (Mistura de Fases)

> Estes exercícios simulam o formato real do exame, combinando múltiplos temas.

---

## 📝 Escolha Múltipla (Simulação de Exame)

**I.1** Qual das seguintes afirmações sobre o `express-generator` é verdadeira?

- a) Cria componentes Angular automaticamente
- b) Cria uma estrutura de projeto Express com pasta `views` para templates
- c) É um template engine como EJS
- d) Instala o MongoDB automaticamente

<details><summary>✅ Resposta</summary>

**b)** O `express-generator` cria automaticamente uma estrutura de projeto Express, incluindo a pasta `views/` para templates (EJS por defeito), `routes/`, `public/`, etc.
</details>

---

**I.2** Qual destas combinações de tecnologia está **errada**?

- a) Angular — Frontend no browser
- b) Express — Backend no servidor
- c) Mongoose — Template engine
- d) JWT — Autenticação por tokens

<details><summary>✅ Resposta</summary>

**c)** Mongoose NÃO é um template engine. É um **driver/ODM** para MongoDB. Templates engines são EJS, Pug, Mustache, etc.
</details>

---

**I.3** Em qual situação usarias `res.render()` vs `res.json()`?

- a) São iguais
- b) `res.render()` para server-side rendering com templates; `res.json()` para enviar dados JSON a uma API REST
- c) `res.json()` para templates; `res.render()` para APIs
- d) Ambos enviam HTML

<details><summary>✅ Resposta</summary>

**b)** `res.render('view', data)` renderiza um template EJS no servidor e envia HTML. `res.json(obj)` envia dados JSON, tipicamente numa API REST consumida por um frontend Angular/React.
</details>

---

## ✅❌ Verdadeiro ou Falso (Simulação de Exame)

**I.4** "O Angular pode aceder diretamente ao MongoDB usando Mongoose no browser."

<details><summary>✅ Resposta</summary>

**FALSO.** Angular executa no browser e **NUNCA** deve aceder diretamente à BD. Mongoose é um módulo Node.js que corre no **servidor**. Angular comunica com o backend via HTTP/REST, e o backend usa Mongoose para aceder ao MongoDB.
</details>

---

**I.5** "Node.js é single-threaded com I/O bloqueante."

<details><summary>✅ Resposta</summary>

**FALSO.** Node.js é single-threaded com I/O **não bloqueante** (assíncrono). É isso que permite lidar com muitas conexões simultâneas sem criar threads adicionais.
</details>

---

**I.6** "O `npm start` compila TypeScript e instala MongoDB."

<details><summary>✅ Resposta</summary>

**FALSO.** `npm start` executa o script "start" definido no `package.json`. Compilar TypeScript é `tsc`. Instalar MongoDB é feito separadamente (não via NPM). NPM instala o driver `mongoose`.
</details>

---

## 📖 Resposta Aberta (Simulação de Exame)

**I.7** Uma aplicação web tem um formulário de registo com campos nome, email e password. Descreve todas as camadas de segurança e validação que implementarias, desde o frontend até à base de dados. *(3 val)*

<details><summary>✅ Resposta Modelo</summary>

**1. Frontend (Angular) — UX e primeira validação:**
- Formulário com `method="POST"` (NUNCA GET para passwords)
- Validação com atributos: `required`, `minlength`, `type="email"`, `type="password"`
- Validação Angular: `Validators.required`, `Validators.email`, `Validators.minLength(8)`
- Feedback visual imediato (`*ngIf="campo.invalid && campo.touched"`)
- Desabilitar botão de submit se formulário inválido: `[disabled]="form.invalid"`

**2. Comunicação — HTTPS e JWT:**
- Usar **HTTPS** em produção (dados encriptados em trânsito)
- Após registo bem-sucedido, gerar JWT com `jwt.sign()` e enviar ao cliente
- Nunca enviar a password de volta ao frontend

**3. Backend (Express) — Segurança real:**
- Middleware para validar dados recebidos (verificar campos obrigatórios, formatos)
- **Sanitizar inputs** para prevenir injeção (XSS, NoSQL injection)
- Verificar se o email já existe na BD antes de criar
- **Hash da password** com bcrypt antes de guardar:
  ```javascript
  const hash = await bcrypt.hash(req.body.password, 10);
  ```
- Nunca guardar passwords em texto plano!

**4. Base de dados (MongoDB/Mongoose) — Última linha de defesa:**
- Schema com validação Mongoose:
  ```javascript
  const userSchema = new mongoose.Schema({
    nome: { type: String, required: true, minlength: 2 },
    email: { type: String, required: true, unique: true },
    password: { type: String, required: true, minlength: 8 }
  });
  ```
- `unique: true` no email previne duplicados

**Resumo:** Validação em 3 camadas (frontend → backend → BD) + HTTPS + password hashing + sanitização. Nunca confiar apenas no frontend.
</details>

---

**I.8** Corrige o seguinte código de um servidor Express que tem vários erros: *(2 val)*

```javascript
const express = require('express');
const app = express();

app.get('/api/produto/:id', (req, res) => {
  const id = req.query.id;
  const produto = Produto.find(id);
  res.send(produto);
});

app.use(express.json());

app.post('/login', (req, res) => {
  const password = req.params.password;
  // verificar password...
  res.status(200);
});

app.listen(3000);
```

<details><summary>✅ Resposta Modelo</summary>

**Erro 1:** `req.query.id` → Deve ser `req.params.id` (o `:id` está na rota como parâmetro de rota, não na query string).

**Erro 2:** `Produto.find(id)` → `find()` retorna uma coleção. Para buscar por ID: `Produto.findById(id)`. Além disso, falta `await` (é assíncrono) e `Produto` nunca foi importado/definido.

**Erro 3:** `express.json()` está **depois** das rotas → middleware deve ser registado **ANTES** das rotas que precisam dele.

**Erro 4:** `req.params.password` → Num POST de login, os dados vêm no **body**: `req.body.password`.

**Erro 5:** `res.status(200)` → Falta enviar uma resposta. Deve ser `res.status(200).json({ msg: 'OK' })` ou `res.send('OK')`.

**Código corrigido:**
```javascript
const express = require('express');
const Produto = require('./models/Produto');
const app = express();

app.use(express.json()); // ANTES das rotas

app.get('/api/produto/:id', async (req, res) => {
  const id = req.params.id;         // params, não query
  const produto = await Produto.findById(id);  // findById + await
  res.json(produto);
});

app.post('/login', (req, res) => {
  const password = req.body.password;  // body, não params
  // verificar password...
  res.status(200).json({ msg: 'Login OK' }); // enviar resposta
});

app.listen(3000);
```
</details>

---

# 📊 Resumo dos Exercícios

| Fase | Escolha Múltipla | V/F | Resposta Aberta | Total |
|------|:-:|:-:|:-:|:-:|
| **Fase 1** — Fundamentos | 7 | 6 | 3 | **16** |
| **Fase 2** — JavaScript & DOM | 7 | 5 | 4 | **16** |
| **Fase 3** — Backend & REST | 8 | 7 | 5 | **20** |
| **Fase 4** — Angular & Full-Stack | 8 | 6 | 4 | **18** |
| **Fase 5** — Segurança | 5 | 5 | 4 | **14** |
| **Integrados** | 3 | 3 | 2 | **8** |
| **Exames Reais** | 23 | 10 | 14 | **47** |
| **TOTAL** | **61** | **42** | **36** | **139 exercícios** |

> 💡 **Dica:** Começa pela Fase 1 e avança sequencialmente. Faz os exercícios **sem olhar para as respostas** primeiro. Depois verifica e anota os erros para rever. Os exercícios marcados **(EXTRA)** vão além do exame mas reforçam a compreensão.

---
---

# 📜 EXAMES REAIS — Perguntas de Anos Anteriores

> As perguntas que se seguem são **reais** dos exames de Época Normal de **2020/21**, **2021/22** e **2024/25**.
> Tenta resolver cada pergunta antes de ver a resposta!

---
---

# 🗓️ EXAME 2020/2021 — Época Normal

> **Duração**: 1h30min | **Parte 1**: 7 valores | **Parte 2**: 3 valores | **Parte 3**: 10 valores

---

## Parte 1 — Escolha Múltipla (7 valores)

> Cada pergunta vale 1 valor. Opção incorreta desconta 0.5 valores.

---

**P1.** Indique as afirmações verdadeiras:

- a. CSS é uma linguagem de programação para a web;
- b. Numa página da internet escrita em HTML, a informação escrita entre as tags `<head>...</head>` não é visível na página apresentada no browser;
- c. Podemos submeter informação para um servidor web sem utilizar JavaScript numa página escrita em HTML;
- d. O uso de CSS ou JavaScript não é obrigatório numa página HTML.

<details><summary>✅ Resposta</summary>

**Corretas: b, c, d**

- **a) FALSO** — CSS é uma linguagem de *estilos*, não de programação.
- **b) VERDADEIRO** — O `<head>` contém metadados, links para CSS e scripts, mas não o conteúdo visível (esse fica no `<body>`).
- **c) VERDADEIRO** — Podemos usar um formulário HTML padrão (`<form action="/rota" method="POST">`) que envia dados nativamente sem necessidade de JavaScript.
- **d) VERDADEIRO** — Uma página pode conter apenas puro HTML (embora fique sem estilos ou interatividade avançada).
</details>

---

**P2.** Entre as seguintes opções escolha os métodos HTTP que podem ser utilizados para enviar dados a um servidor numa API REST:

- a. GET
- b. DELETE
- c. CREATE
- d. UPDATE

<details><summary>✅ Resposta</summary>

**Corretas: a, b**

- **a, b)** GET e DELETE são métodos/verbos HTTP válidos. Embora GET e DELETE não enviem tradicionalmente um *body*, enviam dados via **parâmetros de rota** (ex: `/user/123`) ou **query strings** (ex: `?id=123`).
- **c, d)** CREATE e UPDATE são operações de base de dados (CRUD), **NÃO** são métodos HTTP. Os equivalentes HTTP seriam POST e PUT.
</details>

---

**P3.** Considerando a framework ExpressJS utilizada em Node.js, indique as afirmações verdadeiras:

- a. Podemos criar componentes com a linguagem de programação TypeScript e o padrão de software MVC;
- b. Podemos utilizar template engines para gerar páginas HTML dinamicamente no servidor;
- c. Podemos aceder diretamente a uma base de dados para enviar e guardar informação com o módulo mongoose;
- d. Podemos executar funções JavaScript da framework ExpressJS diretamente a partir de um browser de internet.

<details><summary>✅ Resposta</summary>

**Corretas: b, c**

- **a) FALSO** — A criação de "componentes com TypeScript" remete especificamente à framework Angular (frontend). O ExpressJS usa JavaScript padrão.
- **b) VERDADEIRO** — O Express suporta template engines como o EJS (`res.render()`).
- **c) VERDADEIRO** — O Mongoose é o módulo standard para ligar Node.js/Express ao MongoDB.
- **d) FALSO** — ExpressJS corre no runtime do Node.js (servidor), não no browser.
</details>

---

**P4.** Da seguinte lista selecione as afirmações verdadeiras sobre uma aplicação escrita com a framework Angular:

- a. Devemos manter as ligações diretas a uma base de dados (ex: MongoDB) para persistir informação da aplicação;
- b. Podemos utilizar ficheiros de texto para guardar informação da aplicação;
- c. Podemos utilizar a ferramenta npm para gerir dependências da aplicação;
- d. Podemos dividir as páginas de internet em componentes reutilizáveis;

<details><summary>✅ Resposta</summary>

**Corretas: c, d**

- **a) FALSO (Armadilha de segurança)** — Nunca se liga o Angular diretamente à base de dados, pois o código fonte fica exposto no browser (cliente).
- **b) FALSO** — Aplicações browser-side não têm acesso direto ao sistema de ficheiros do cliente. Para persistência usa-se localStorage ou o Backend.
- **c) VERDADEIRO** — O Angular usa o `package.json` e o `npm` para gerir bibliotecas.
- **d) VERDADEIRO** — O Angular é baseado numa arquitetura modular de componentes reutilizáveis.
</details>

---

**P5.** Indique as afirmações verdadeiras sobre o desenvolvimento de serviços REST:

- a. Podemos usar o módulo swagger para documentar e testar APIs REST;
- b. Os serviços REST enviam páginas HTML para um cliente;
- c. As APIs REST não respeitam a arquitetura cliente-servidor na maioria das aplicações web;
- d. É possível utilizar tokens JWT para garantir autenticação e autorização na API REST.

<details><summary>✅ Resposta</summary>

**Corretas: a, d**

- **a) VERDADEIRO** — O Swagger/OpenAPI gera documentação interativa e um ambiente de testes para APIs REST.
- **b) FALSO** — Serviços REST comunicam através de dados puros (tipicamente formato JSON), não páginas HTML completas.
- **c) FALSO** — REST é, por definição, um modelo que obriga estritamente à separação cliente-servidor.
- **d) VERDADEIRO** — JWT permite autenticação (verificar identidade) e autorização (verificar permissões) via tokens.
</details>

---

**P6.** Observe a figura e indique as afirmações verdadeiras:

*(Excerto de código: `authController.verifyToken = function(req, res, next)... jwt.verify...`)*

- a. Está a ser validado um token de autenticação de um utilizador numa aplicação de frontend;
- b. Está a ser validado um token de autenticação de um utilizador numa aplicação de backend;
- c. A função verifyToken não funcionará e resultará num erro 500 sempre que executada;
- d. A função verifyToken atua como função de middleware na aplicação web. Só no caso de o token ser válido é que o pedido é processado por outras funções;

<details><summary>✅ Resposta</summary>

**Corretas: b, d**

- **a) FALSO** — A função recebe `req, res, next`, parâmetros de middleware **Express** (Backend), não Angular.
- **b) VERDADEIRO** — É uma função de backend que valida tokens JWT.
- **c) FALSO** — A função funcionará se o token for válido.
- **d) VERDADEIRO** — É um **middleware** (tem parâmetro `next()`). Só quando a autenticação passa é que chama `next()` para o próximo handler.
</details>

---

**P7.** Tendo em consideração a figura, indique as afirmações verdadeiras:

*(Excerto de código: `@NgModule({ declarations: [AppComponent, PeopleComponent]...`)*

- a. Estamos perante uma aplicação desenvolvida na framework ExpressJS;
- b. Estamos perante uma aplicação desenvolvida na framework Angular;
- c. A aplicação contém apenas o componente criado por defeito em todas as aplicações e outro criado pelo utilizador;
- d. Esta aplicação está a ser desenvolvida para o backend de uma aplicação web.

<details><summary>✅ Resposta</summary>

**Corretas: b, c**

- **a) FALSO** — `@NgModule` é um decorador Angular, não Express.
- **b) VERDADEIRO** — O decorador `@NgModule` e TypeScript indicam que é o ficheiro `app.module.ts` do Angular (frontend).
- **c) VERDADEIRO** — `declarations` contém exatamente dois componentes: `AppComponent` (default) e `PeopleComponent` (criado pelo utilizador).
- **d) FALSO** — Angular é uma framework **client-side** (frontend), não backend.
</details>

---

## Parte 2 — Verdadeiro ou Falso (3 valores)

---

**V/F 1.** "Uma página da internet só pode enviar dados para um servidor backend utilizando o elemento form em HTML com o método POST ou GET."

<details><summary>✅ Resposta</summary>

**FALSO.** O browser pode enviar dados de forma assíncrona (sem recarregar a página) usando **AJAX** (via `XMLHttpRequest`) ou a **Fetch API** via JavaScript. Não precisa de formulários.
</details>

---

**V/F 2.** "JavaScript é uma linguagem de programação que pode ser utilizada no frontend e backend."

<details><summary>✅ Resposta</summary>

**VERDADEIRO.** JavaScript corre no browser (frontend) e em servidores (backend) graças a runtimes como o **Node.js**.
</details>

---

**V/F 3.** "JavaScript é a linguagem de programação utilizada em aplicações escritas com a framework Angular."

<details><summary>✅ Resposta</summary>

**FALSO.** A linguagem utilizada no Angular é o **TypeScript** (que é um superset do JavaScript, com tipagem estática e compilação obrigatória).
</details>

---

**V/F 4.** "Node.js é uma framework para escrita de aplicações no backend."

<details><summary>✅ Resposta</summary>

**FALSO.** Node.js **não** é uma framework — é um **runtime** (ambiente de execução) que permite correr JavaScript fora do browser. A framework utilizada sobre o Node.js para aplicações backend é o **ExpressJS**.
</details>

---

**V/F 5.** "O padrão de software MVC não pode ser utilizado em aplicações escritas com a framework Angular."

<details><summary>✅ Resposta</summary>

**FALSO.** O Angular usa intrinsecamente o padrão MVC (ou MVVM): o "Model" são as classes/serviços, a "View" é o template HTML, e o "Controller" é a classe TypeScript do componente (`*.component.ts`).
</details>

---

**V/F 6.** "CSS é uma linguagem utilizada para formatar o aspeto de páginas da internet."

<details><summary>✅ Resposta</summary>

**VERDADEIRO.** É a função exata do CSS — formatar e estilizar a apresentação visual de páginas web. (Embora não seja uma linguagem de programação.)
</details>

---

## Parte 3 — Resposta Aberta (10 valores)

---

**Aberta 1.** Indique o que entende pelo conceito cliente-servidor em aplicações web. *(1.5 val)*

<details><summary>✅ Resposta Modelo</summary>

É o modelo de arquitetura base da web composto por dois intervenientes:
- O **Cliente** (browser do utilizador) que executa a interface gráfica (frontend com HTML/CSS/JS) e inicia a comunicação enviando um **pedido HTTP**.
- O **Servidor** (computador remoto, ex: Node.js) que recebe o pedido HTTP, executa a lógica de negócio (backend), acede a bases de dados se necessário, e devolve uma **resposta HTTP** ao cliente.

**Fluxo:** Cliente envia pedido HTTP → Servidor processa → Servidor devolve resposta HTTP → Cliente renderiza.
</details>

---

**Aberta 2.** Indique as diferentes formas possíveis para incluir CSS numa página de internet escrita em HTML. *(1.5 val)*

<details><summary>✅ Resposta Modelo</summary>

Existem 3 formas de adicionar CSS:
1. **Inline**: Usando o atributo `style` diretamente na tag HTML (ex: `<p style="color:red;">`).
2. **Internal**: Colocando código CSS dentro de uma tag `<style>` na secção `<head>` do documento HTML.
3. **External**: Referenciando um ficheiro de estilos externo usando a tag `<link rel="stylesheet" href="style.css">` na secção `<head>` (método **recomendado**).
</details>

---

**Aberta 3.** Observe a figura. Descreva em que tipo de aplicações podemos encontrar este excerto de código e o seu objetivo. Indique as funcionalidades e que correções poderão ser implementadas. *(2 val)*

*(O código mostra um array `const routes: Routes = [...]` com rotas login, register, profile/:id e aboutus, todas com canActivate: [AuthGuardService])*

<details><summary>✅ Resposta Modelo</summary>

- **Tipo de Aplicação**: Aplicação Frontend do tipo SPA (Single Page Application) desenvolvida em **Angular**.
- **Objetivo**: Configurar o **Routing** (navegação) da aplicação, mapeando URLs para componentes Angular sem recarregar a página.
- **Funcionalidades**: Login, register, profile com ID dinâmico (`:id`), e about us. Usa um **Guard** (`AuthGuardService`) para proteger rotas.
- **Correções necessárias**:
  1. Falta uma vírgula `,` após `component: LoginComponent`.
  2. Faltam as aspas iniciais nas strings das rotas (`path: register'` → `path: 'register'`).
  3. **Erro lógico**: As rotas de `login` e `register` **NÃO devem** ter `canActivate: [AuthGuardService]`, pois isso impediria utilizadores não-autenticados de fazerem login/registo! O Guard só deve proteger a rota do `profile`.
</details>

---

**Aberta 4.** Observe a figura e descreva de forma sucinta a funcionalidade implementada na página HTML. Caso identifique erros, indique como podem ser resolvidos. *(2 val)*

*(A página obtém dois números de inputs, multiplica-os ao clicar num botão e mostra o resultado. Segundo input usa `class="arg2"` mas JS usa `getElementById("arg2")`. Botão não tem ID mas JS referencia `getElementById("calcular")`.)*

<details><summary>✅ Resposta Modelo</summary>

- **Funcionalidade**: A página pretende obter dois números a partir de inputs do utilizador, multiplicá-los quando se clica num botão e exibir o resultado numa div inferior através de JavaScript DOM manipulation.
- **Erros identificados e resoluções**:
  1. O segundo input usa o atributo `class="arg2"`, mas o JavaScript tenta selecioná-lo usando `document.getElementById("arg2")`. **Resolução**: Alterar `class="arg2"` para `id="arg2"` no HTML.
  2. O script tenta adicionar o evento de clique num elemento com ID "calcular" (`document.getElementById("calcular")`), mas o botão não tem nenhum ID atribuído. **Resolução**: Adicionar `id="calcular"` na tag `<button>`.
</details>

---

**Aberta 5.** Onde e de que forma devemos validar a informação submetida por utilizadores em aplicações web. *(3 val)*

<details><summary>✅ Resposta Modelo</summary>

A validação de dados deve ser **OBRIGATORIAMENTE** implementada em ambos os lados:

**No Frontend (Cliente):** Serve para melhorar a Experiência do Utilizador (UX), dando feedback imediato. Pode ser feito usando validações nativas de HTML5 (atributos como `required`, `minlength`, `type="email"`) ou via validação reativa do Angular.

**No Backend (Servidor):** Serve para garantir a **Segurança** do sistema. A validação frontend pode ser facilmente contornada por:
- Desativar JavaScript no browser
- Enviar pedidos diretamente com Postman ou curl
- Manipular o HTML/JS nos DevTools

O servidor deve SEMPRE validar e sanitizar toda a informação recebida antes de interagir com a base de dados (ex: usando middlewares no ExpressJS ou validações do schema do Mongoose) para evitar injeção de código ou dados corrompidos.

**Conclusão:** Confiar apenas no frontend é uma **falha de segurança grave**.
</details>

---
---

# 🗓️ EXAME 2021/2022 — Época Normal

> **Duração**: 1h40min | **Parte 1**: 8 valores | **Parte 2**: 2 valores | **Parte 3**: 10 valores

---

## Parte 1 — Escolha Múltipla (8 valores)

> Cada pergunta vale 1 valor. Opção incorreta desconta 0.5 valores.

---

**P1.** Indique as afirmações verdadeiras:

- a. HTML é uma linguagem de programação para a web;
- b. CSS permite estruturar o conteúdo de página web;
- c. JavaScript apenas permite formatar os elementos de página web;
- d. É possível submeter um formulário numa página HTML sem utilizar CSS ou JavaScript.

<details><summary>✅ Resposta</summary>

**Corretas: d**

- **a) FALSO** — HTML é uma linguagem de *marcação*, não de programação.
- **b) FALSO** — CSS serve para *apresentar/estilizar*. O HTML é que estrutura o conteúdo.
- **c) FALSO** — JS adiciona *lógica e interatividade*, não "apenas formata" (que é o papel do CSS).
- **d) VERDADEIRO** — Um formulário `<form>` HTML padrão submete dados para um servidor sem precisar de qualquer CSS ou JS ativo.
</details>

---

**P2.** Indique as afirmações verdadeiras sobre template engines:

- a. Angular possui um template engine que gera as páginas diretamente no browser, a partir dos componentes presentes na página apresentada ao utilizador;
- b. Podemos utilizar o template engine mongoose para gerar páginas HTML dinamicamente no servidor;
- c. O template engine EJS, num projeto gerado através do express generator, guarda os templates dentro da pasta views do projeto;
- d. Para utilizar template engines numa aplicação web, necessitamos de utilizar, obrigatoriamente, APIs REST e operações CRUD.

<details><summary>✅ Resposta</summary>

**Corretas: a, c**

- **a) VERDADEIRO** — O Angular compila e renderiza os seus templates HTML diretamente no cliente (browser).
- **b) FALSO** — Mongoose é um **driver de base de dados** (ODM), NÃO um template engine.
- **c) VERDADEIRO** — No ExpressJS, o EJS guarda as views na pasta `views/` por defeito.
- **d) FALSO** — Template engines (como EJS) geram HTML no servidor (Server-Side Rendering) sem precisar de APIs REST.
</details>

---

**P3.** Atendendo às características da framework ExpressJS, identifique as afirmações verdadeiras:

- a. ExpressJS é uma framework para desenvolver aplicações que correm exclusivamente no browser;
- b. O package manager npm pode ser utilizado para gerir as dependências da nossa aplicação;
- c. O ficheiro package.json guarda, entre outros, informação sobre as dependências da nossa aplicação e informação sobre comandos para serem executados no terminal com a ferramenta npm;
- d. ExpressJS é uma framework que cria aplicações que devem correr com um runtime de JavaScript como o NodeJS.

<details><summary>✅ Resposta</summary>

**Corretas: b, c, d**

- **a) FALSO** — ExpressJS corre estritamente no **servidor** via o runtime Node.js, NÃO no browser.
- **b) VERDADEIRO** — O `npm` é usado para instalar e gerir dependências.
- **c) VERDADEIRO** — O `package.json` contém dependências, scripts (`npm start`, etc.) e metadados do projeto.
- **d) VERDADEIRO** — Express é uma framework para Node.js (runtime JavaScript).
</details>

---

**P4.** Da seguinte lista selecione as afirmações verdadeiras sobre a framework Angular:

- a. Angular é uma framework para desenvolvimento de aplicações no backend;
- b. Angular apenas permite o uso de um componente por página/rota;
- c. Em angular não é possível utilizar os formulários de HTML com o elemento `<form></form>`;
- d. A linguagem de programação de uma aplicação em Angular é o TypeScript.

<details><summary>✅ Resposta</summary>

**Corretas: d**

- **a) FALSO** — Angular é estritamente **Frontend** (client-side).
- **b) FALSO** — Permite compor páginas com dezenas de componentes reutilizáveis.
- **c) FALSO** — Angular suporta e incentiva ativamente o uso de `<form>` (Template-driven e Reactive forms).
- **d) VERDADEIRO** — Todo o código Angular é escrito nativamente em **TypeScript**.
</details>

---

**P5.** Indique as afirmações verdadeiras sobre o desenvolvimento de serviços REST:

- a. Em serviços REST a informação é trocada em formato binário entre cliente e servidor para melhor performance;
- b. Serviços REST não permitem o uso do formato XML para troca de informação entre cliente e servidor;
- c. É possível utilizar os métodos HTTP como GET, POST, PUT, DELETE para mapear operações CRUD sobre uma base de dados;
- d. O módulo swagger-ui permite documentar e criar um ambiente de testes para APIs REST na framework ExpressJS.

<details><summary>✅ Resposta</summary>

**Corretas: c, d**

- **a) FALSO** — REST troca informação em **texto** (JSON ou XML), NÃO em formato binário.
- **b) FALSO** — REST **permite** JSON e XML. Não está limitado a um formato.
- **c) VERDADEIRO** — GET→Read, POST→Create, PUT→Update, DELETE→Delete.
- **d) VERDADEIRO** — O `swagger-ui-express` cria documentação visual e interativa para testes.
</details>

---

**P6.** Observe o excerto de código (template EJS com iteração `<% newsList.forEach... %>`) e indique as afirmações verdadeiras:

- a. O código presente na figura é executado por um template engine no servidor gerando um ficheiro HTML que é enviado para o cliente;
- b. O código presente na figura é enviado para o cliente e depois executado para mostrar a informação no browser;
- c. Se a variável newsList estiver vazia nenhuma informação é mostrada no browser do cliente;
- d. Independentemente do número de news dentro da variável newsList, apenas a primeira é mostrada no browser do cliente.

<details><summary>✅ Resposta</summary>

**Corretas: a, c**

- **a) VERDADEIRO** — É um **template EJS** que corre inteiramente no **servidor** (Express). O servidor compila o HTML com os dados e envia HTML estático para o browser.
- **b) FALSO** — O código EJS NÃO é enviado para o cliente. Apenas o HTML resultante é enviado.
- **c) VERDADEIRO** — O código tem um `if (newsList && newsList.length > 0)` que previne a renderização se estiver vazio.
- **d) FALSO** — O bloco `.forEach` percorre **todos** os elementos, não só o primeiro.
</details>

---

**P7.** Tendo em consideração o excerto de código (`mongoose.Schema({ name: String... })`), indique as afirmações verdadeiras:

- a. Estamos perante uma aplicação desenvolvida na framework Angular;
- b. Estamos perante uma aplicação desenvolvida na framework ExpressJS;
- c. O excerto de código cria um modelo de dados e um objeto para interagir com uma coleção da base de dados;
- d. O excerto de código representa a criação de uma classe de modelo de dados em TypeScript.

<details><summary>✅ Resposta</summary>

**Corretas: c**

- **a) FALSO** — O código usa `mongoose`, que é um módulo de backend (Node.js), não Angular.
- **b) PARCIAL** — Mongoose é tipicamente usado com Express, mas o código per se não referencia Express diretamente.
- **c) VERDADEIRO** — O código define um **Schema** e exporta um **Model** Mongoose para interagir com uma coleção MongoDB.
- **d) FALSO** — O código usa JavaScript (CommonJS com `require`), não TypeScript.
</details>

---

**P8.** Tendo em consideração o excerto de código (`JWTInterceptorService implements HttpInterceptor`), indique as afirmações verdadeiras:

- a. O excerto de código apresentado é executado sempre que existe um pedido HTTP na aplicação;
- b. O excerto de código cria um token JWT e envia para o cliente;
- c. O excerto de código cria um header com o nome 'x-access-token' num pedido HTTP;
- d. O excerto de código gere o acesso de um utilizador às rotas de uma página web.

<details><summary>✅ Resposta</summary>

**Corretas: a, c**

- **a) VERDADEIRO** — Um **Interceptor HTTP** do Angular é executado automaticamente em **todos** os pedidos HTTP feitos pelo `HttpClient`.
- **b) FALSO** — O interceptor **lê** o token do `localStorage`, não o "cria". A criação do token é feita no backend com `jwt.sign()`.
- **c) VERDADEIRO** — O interceptor adiciona o header `x-access-token` com o valor do token a cada pedido.
- **d) FALSO** — O acesso a rotas é gerido pelos **Route Guards** (`CanActivate`), não pelos Interceptors.
</details>

---

## Parte 2 — Verdadeiro ou Falso (2 valores)

---

**V/F 1.** "O objeto XMLHttpRequest em JavaScript permite obter informação de um servidor sem o uso de formulários e sem a necessidade de fazer reload a uma página da internet."

<details><summary>✅ Resposta</summary>

**VERDADEIRO.** Esta é a definição do conceito de **AJAX** (Asynchronous JavaScript and XML). Permite comunicar com o servidor de forma assíncrona sem recarregar a página.
</details>

---

**V/F 2.** "Um formulário para login de utilizadores pode utilizar o método GET para enviar informação de autenticação para o servidor."

<details><summary>✅ Resposta</summary>

**FALSO.** O método GET envia informação visível no URL (query string). Enviar passwords via GET compromete gravemente a segurança (ficam no histórico do browser e nos logs do servidor em plain text). Deve-se usar SEMPRE o método **POST** com **HTTPS**.
</details>

---

**V/F 3.** "As aplicações Angular executam no runtime NodeJS."

<details><summary>✅ Resposta</summary>

**FALSO.** As aplicações Angular são **client-side**, executando diretamente no **browser** do utilizador. O Node.js é utilizado apenas na máquina do developer como ferramenta (via Angular CLI) para compilar e gerar a build do projeto.
</details>

---

**V/F 4.** "Um serviço REST com o método PUT não deve permitir o envio de informação de um cliente para o servidor."

<details><summary>✅ Resposta</summary>

**FALSO.** O método PUT é utilizado especificamente para **Update** (atualizar) recursos. Ele envia explicitamente informação nova/atualizada no **body** do pedido HTTP do cliente para o servidor.
</details>

---

## Parte 3 — Resposta Aberta (10 valores)

---

**Aberta 1.** Descreva o significado dos conceitos backend e frontend no desenvolvimento de uma aplicação web. *(0.5 val)*

<details><summary>✅ Resposta Modelo</summary>

- **Frontend (Client-side)**: É a parte visual da aplicação que corre no browser do utilizador (interface). Desenvolvido com HTML, CSS, JavaScript (e frameworks como Angular). Responsável pela Experiência de Utilizador (UX).
- **Backend (Server-side)**: É a parte que corre num servidor remoto. Responsável pela lógica de negócio, comunicação com Bases de Dados, processamento seguro de autenticações e fornecimento de APIs.
</details>

---

**Aberta 2.** No desenvolvimento web indique o que entende pelo termo MEAN stack. *(0.5 val)*

<details><summary>✅ Resposta Modelo</summary>

É uma arquitetura de desenvolvimento de software baseada em JavaScript de "ponta a ponta" (Full-stack). As siglas significam:
- **M**ongoDB (Base de dados NoSQL)
- **E**xpressJS (Framework web backend)
- **A**ngular (Framework frontend)
- **N**ode.js (Runtime de execução no servidor)
</details>

---

**Aberta 3.** As operações CRUD estão relacionadas com o desenvolvimento de vários componentes web. Indique de que se tratam estas operações e 2 casos de uso. *(1 val)*

<details><summary>✅ Resposta Modelo</summary>

**CRUD** são as 4 operações básicas de armazenamento persistente:
- **C**reate (Criar)
- **R**ead (Ler)
- **U**pdate (Atualizar)
- **D**elete (Apagar)

**Casos de uso:**
1. Numa **API REST**: Mapeiam-se para os métodos HTTP — POST (Create), GET (Read), PUT (Update), DELETE (Delete).
2. Na **Base de Dados** (Mongoose): Correspondem a `save()` (Create), `find()` (Read), `findByIdAndUpdate()` (Update) e `findByIdAndDelete()` (Delete).
</details>

---

**Aberta 4.** Considere o padrão de software MVC. Descreva em que consiste este padrão e como o pode aplicar numa aplicação que utiliza ExpressJS. *(1.5 val)*

<details><summary>✅ Resposta Modelo</summary>

O Padrão **MVC (Model-View-Controller)** separa a aplicação em 3 áreas lógicas:

- **Model**: Gere a estrutura dos dados e interage com a Base de Dados. No Express, aplica-se criando ficheiros com schemas do **Mongoose** na pasta `models/`.
- **View**: A parte visual enviada ao cliente. No Express, usam-se template engines como **EJS** (na pasta `views/`) para gerar HTML dinâmico com os dados.
- **Controller**: O intermediário que liga Views aos Models. No Express, são ficheiros na pasta `controllers/` que processam pedidos (`req`, `res`), acedem ao Model e injetam o resultado no `res.render(view)`.

**Vantagem**: Separação de responsabilidades — cada camada pode ser desenvolvida e testada independentemente.
</details>

---

**Aberta 5.** Considere a segurança de aplicações web. Indique de forma detalhada como podemos adicionar autenticação e autorização a uma aplicação web que use frameworks para o frontend e backend. *(3 val)*

<details><summary>✅ Resposta Modelo</summary>

**1. Autenticação ("Quem és?"):**
- O backend recebe (via POST) as credenciais do utilizador.
- Se válidas, assina um token **JWT** com `jwt.sign({ userId, role }, segredo, { expiresIn: '1h' })`.
- Envia o token ao cliente Angular.
- O Angular guarda o token em `localStorage`.

**2. Manter estado e proteger rotas (Frontend):**
- **HTTP Interceptor**: Apanha cada pedido HTTP e injeta o token no header (`Authorization: Bearer <token>` ou `x-access-token`).
- **Guard** (`CanActivate`): No Router do Angular, impede que utilizadores não autenticados acedam a páginas restritas (ex: `/admin`).

**3. Autorização ("O que podes fazer?") (Backend):**
- O Express usa um **middleware** antes das rotas protegidas que faz `jwt.verify()` ao token enviado.
- Verifica não só que o utilizador está autenticado, como que tem as **roles** (permissões) necessárias.
- Se token inválido → 401. Se sem permissão → 403.
</details>

---

**Aberta 6.** Observe a figura que representa uma aplicação web. Escreva o conteúdo do ficheiro demo.html de forma a ficar funcional. *(1.5 val)*

*(O código backend processa `app.post('/result')` à espera de campos `username` e `password`)*

<details><summary>✅ Resposta Modelo</summary>

```html
<!DOCTYPE html>
<html>
<head><title>Página de Login</title></head>
<body>
  <h2>Login</h2>
  <!-- A ação aponta para a rota POST /result -->
  <form action="/result" method="POST">
    <!-- Os atributos 'name' correspondem ao que req.body espera -->
    <label>Username:</label>
    <input type="text" name="username" required><br>

    <label>Password:</label>
    <input type="password" name="password" required><br>

    <button type="submit">Entrar</button>
  </form>
</body>
</html>
```

**Pontos-chave:** `action="/result"`, `method="POST"`, `name="username"` e `name="password"` para corresponder aos campos que o Express espera em `req.body`.
</details>

---

**Aberta 7.** Observe o excerto de código de uma aplicação Angular. Indique a sua utilidade, como pode ser utilizado por componentes, e que padrões de software estão a ser utilizados. *(2 val)*

*(Excerto mostra um `@Injectable` RestService com `HttpClient`, funções `getItem()`, `addItem()` que retornam `Observable<Item>`)*

<details><summary>✅ Resposta Modelo</summary>

- **Utilidade/Funcionalidade**: É um **Angular Service** (`RestService`) criado com `@Injectable()`. Encapsula a comunicação com uma API REST do backend para gerir "Produtos" (GET por ID, POST novo produto, GET lista).

- **Como é utilizado por componentes**: Os componentes não instanciam a classe diretamente. Usam **Injeção de Dependências (DI)** pedindo-a no construtor:
  ```typescript
  constructor(private restService: RestService) {}
  ```
  Depois chamam as funções e fazem `.subscribe()` aos dados.

- **Padrões de Software em uso**:
  - **Singleton**: `providedIn: 'root'` garante uma única instância partilhada por toda a aplicação.
  - **Observer / Reactive Programming**: Usa **Observables** (RxJS) para comunicação HTTP assíncrona, com padrão publish/subscribe para reagir a streams de dados.
</details>

---
---

# 🗓️ EXAME 2024/2025 — Época Normal

> **Duração**: 1h30min | **Parte 1**: 8 valores | **Parte 2**: 2 valores | **Parte 3**: 10 valores

---

## Parte 1 — Escolha Múltipla (8 valores)

> Cada pergunta vale 1 valor. Indicar **todas** as opções corretas. Opção incorreta penaliza 0.5 valores.

---

**P1.** Considere o uso da ferramenta NPM nos projetos web desenvolvidos durante o semestre. Indique todas as afirmações que indicam funcionalidades desta ferramenta:

- a. Instalar e configurar uma base de dados MongoDB;
- b. Iniciar projetos web com o comando `npm start`;
- c. Permite gerir dependências de projetos web;
- d. Permite criar componentes em aplicações escritas com a framework Angular.

<details><summary>✅ Resposta</summary>

**Corretas: b, c**

- **a) FALSO** — O NPM instala *módulos/packages* JavaScript (como o `mongoose`), mas **não** instala nem configura a base de dados MongoDB em si.
- **b) VERDADEIRO** — `npm start` executa o script "start" definido no `package.json`, iniciando o projeto.
- **c) VERDADEIRO** — O NPM é um **gestor de packages** para JavaScript. Permite instalar, atualizar e gerir dependências.
- **d) FALSO** — Criar componentes Angular faz-se com o Angular CLI: `ng generate component nome`. O NPM apenas gere packages.
</details>

---

**P2.** Indique quais dos seguintes comandos podem ser executados numa aplicação da framework Angular:

- a. `ng generate component my-page;`
- b. `npm start;`
- c. `npm install mongoose --save;`
- d. `npm new my-app`

<details><summary>✅ Resposta</summary>

**Corretas: a, b, c**

- **a) VERDADEIRO** — `ng generate component my-page` é o comando Angular CLI para criar um novo componente.
- **b) VERDADEIRO** — `npm start` pode ser executado em qualquer projeto Node.js/Angular.
- **c) VERDADEIRO** — `npm install mongoose --save` instala o módulo mongoose (o comando em si pode ser executado).
- **d) FALSO** — O comando correto é `ng new my-app` (usa o CLI do Angular `ng`, não `npm`). **`npm new` NÃO EXISTE!**
</details>

---

**P3.** Indique as afirmações verdadeiras sobre os componentes do tipo serviço em Angular:

- a. Permitem ser injetados em múltiplos componentes Angular;
- b. Podemos manter o estado global da aplicação em variáveis internas de um serviço e partilhá-las com outros vários componentes;
- c. Este componente tem de ter sempre uma interface escrita em HTML;
- d. Podem ser utilizados em conjunto com o padrão de software *observable* para manter todos os componentes visuais de uma página atualizados com o último valor das variáveis guardadas no serviço.

<details><summary>✅ Resposta</summary>

**Corretas: a, b, d**

- **a) VERDADEIRO** — Serviços usam `@Injectable()` e são injetados via **Dependency Injection** em múltiplos componentes.
- **b) VERDADEIRO** — Serviços são **singletons** (uma instância partilhada) e podem guardar estado global (ex: `BehaviorSubject`).
- **c) FALSO** — Serviços **NÃO** têm template HTML. Apenas componentes têm `.html`. Serviços são classes TypeScript puras.
- **d) VERDADEIRO** — Usando **Observables** (RxJS) e `BehaviorSubject`, serviços propagam mudanças automaticamente via padrão publish/subscribe.
</details>

---

**P4.** Considerando a framework ExpressJS utilizada em Node.js, indique as afirmações verdadeiras:

- a. Podemos criar componentes com a linguagem de programação JavaScript e padrão de software MVC;
- b. Podemos utilizar *template engines* para gerar páginas HTML dinamicamente no servidor;
- c. Podemos aceder diretamente a uma base de dados para enviar e guardar informação com o módulo mongoose;
- d. Podemos executar funções JavaScript da framework ExpressJS diretamente a partir de um browser de internet.

<details><summary>✅ Resposta</summary>

**Corretas: a, b, c**

- **a) VERDADEIRO** — Express permite organizar aplicações com o padrão **MVC** usando JavaScript/Node.js.
- **b) VERDADEIRO** — Express suporta template engines como **EJS**, Pug, Mustache para gerar HTML dinâmico com `res.render()`.
- **c) VERDADEIRO** — Com o módulo **mongoose**, podemos ligar Express ao MongoDB para operações CRUD.
- **d) FALSO** — Express é **server-side**. O código Express executa no servidor Node.js, **NÃO** no browser.
</details>

---

**P5.** Indique todas as afirmações verdadeiras:

- a. Angular é uma framework de desenvolvimento *fullstack*;
- b. HTML é uma linguagem criada para formatar conteúdos em páginas web;
- c. Node.js é a linguagem de programação mais utilizada no desenvolvimento de aplicações Web;
- d. Angular permite a reutilização de componentes no desenvolvimento de aplicações web.

<details><summary>✅ Resposta</summary>

**Corretas: d**

- **a) FALSO** — Angular é **client-side** (frontend). Para fullstack precisa-se de backend (Express + MongoDB = MEAN Stack).
- **b) FALSO** — HTML é uma linguagem de **marcação** (markup), não de formatação. A formatação é feita com **CSS**.
- **c) FALSO** — Node.js **não** é uma linguagem de programação — é um **runtime** para JavaScript.
- **d) VERDADEIRO** — Angular permite **reutilização de componentes** via `@Input()`, selectors e serviços.
</details>

---

**P6.** Considere o excerto HTML com 3 elementos usando `id="mvp"` e JavaScript com `getElementById('mvp')`. Indique as afirmações verdadeiras:

```html
<p id="mvp">Change Me!</p>
<p id="mvp">Change Me Again!</p>
<button id="mvp">Executa</button>
<script>
  function executa() {
    var elem = document.getElementById('mvp');
    elem.style.color = 'red';
  }
  document.getElementsByTagName('button')[0].addEventListener('click', executa);
</script>
```

- a. Ao clicar no botão Executa a página não é alterada;
- b. Ao clicar no botão Executa o texto "Change Me!" altera a sua cor para vermelho;
- c. A função `document.getElementById("mvp")` retorna todos os elementos da página com o id igual ao valor "mvp";
- d. Ao clicar no botão Executa todo o texto da página fica com a cor vermelho.

<details><summary>✅ Resposta</summary>

**Corretas: b**

- **a) FALSO** — A página **é** alterada: ao clicar, `executa()` é chamada e muda a cor de um elemento.
- **b) VERDADEIRO** — `getElementById('mvp')` retorna o **primeiro** elemento com `id="mvp"`, que é o `<p>Change Me!</p>`. A sua cor é alterada para vermelho.
- **c) FALSO** — `getElementById` retorna **apenas UM** elemento (o primeiro encontrado). Para obter múltiplos seria preciso `querySelectorAll`. (Nota: ter múltiplos IDs iguais é HTML inválido.)
- **d) FALSO** — Apenas o **primeiro** `<p>` ("Change Me!") fica vermelho, não todo o texto.
</details>

---

**P7.** Considere o excerto de código de um middleware Express com JWT (`jwt.verify(token, config.secret)`, verificação de `decoded.role !== 'ADMIN'`). Indique as afirmações verdadeiras:

- a. Está a ser usada a framework Angular;
- b. A função usa o módulo JWT para verificação de um token para efeitos de autenticação e autorização;
- c. A função verify é a última a ser executada numa rota da aplicação ExpressJS e retorna para o browser o id do utilizador ou um erro 500;
- d. Por estarmos a utilizar autenticação com tokens, estamos obrigatoriamente a utilizar uma API REST.

<details><summary>✅ Resposta</summary>

**Corretas: b**

- **a) FALSO** — O código usa `req, res, next` — é **middleware Express** (backend), não Angular.
- **b) VERDADEIRO** — Usa `jwt.verify()` para **autenticação** (verificar token) E **autorização** (verificar `decoded.role !== 'ADMIN'`).
- **c) FALSO** — É um **middleware** (tem `next()`), não a última função. Quando autenticação passa, chama `next()`. Guarda o id em `req.userId`, não o retorna ao browser.
- **d) FALSO** — JWT pode ser usado com **qualquer tipo** de aplicação web, não apenas REST APIs.
</details>

---

**P8.** Observe o excerto de código (`@Injectable`, `HttpClient`, `getItem(): Observable<Item>`, `addItem(): Observable<Item>`). Indique as afirmações verdadeiras:

- a. Está demonstrado o consumo de uma API REST por uma aplicação escrita em ExpressJS;
- b. Está demonstrado o consumo de uma API REST por uma aplicação escrita em Angular;
- c. A função getItem retorna imediatamente com o valor do Item guardado no servidor bloqueando até estar completa;
- d. O código permite aferir com certeza absoluta que está a ser utilizada uma base de dados no backend.

<details><summary>✅ Resposta</summary>

**Corretas: b**

- **a) FALSO** — O código usa `@Injectable`, `HttpClient`, `Observable` — tudo específico de **Angular**, não Express.
- **b) VERDADEIRO** — É um **serviço Angular** que consome uma API REST via `HttpClient`.
- **c) FALSO** — Retorna um `Observable<Item>`, que é **assíncrono** (não bloqueante). O valor só é obtido com `.subscribe()`.
- **d) FALSO** — O código mostra pedidos HTTP a endpoints. Não se pode afirmar que existe BD — o backend pode guardar dados de qualquer forma.
</details>

---

## Parte 2 — Verdadeiro ou Falso (2 valores)

> Cada questão vale 0.5 valores. Justificar as afirmações falsas.

---

**V/F 1.** "A validação de dados de input deve ser apenas realizada no frontend de uma aplicação web para garantir que só dados corretos chegam ao backend."

<details><summary>✅ Resposta</summary>

**FALSO.** A validação deve ser feita em **AMBOS** os lados — frontend (UX) **E** backend (segurança). O utilizador pode contornar o frontend (desativar JS, usar Postman, manipular pedidos HTTP). Confiar apenas no frontend é uma **falha de segurança grave**.
</details>

---

**V/F 2.** "CSS é considerada uma linguagem de programação pois permite formatar e realizar animações em páginas HTML."

<details><summary>✅ Resposta</summary>

**FALSO.** CSS é uma linguagem de **estilos**, não de programação. Não possui lógica de controlo geral (if/else), funções definidas pelo programador, nem manipulação de dados. CSS descreve como os elementos devem ser **apresentados** — é uma linguagem **declarativa de estilo**.
</details>

---

**V/F 3.** "O módulo JWT não é adequado para garantir autenticação e autorização em páginas web codificadas com a framework ExpressJS e o template engine EJS."

<details><summary>✅ Resposta</summary>

**FALSO.** JWT **é adequado** e pode ser usado com ExpressJS e EJS. JWT funciona como middleware no Express para autenticação (verificar token) e autorização (verificar roles). Não depende do template engine — funciona com EJS, Pug, ou qualquer outro.
</details>

---

**V/F 4.** "É possível utilizar a base de dados MongoDB no frontoffice de uma aplicação web para guardar dados da aplicação de forma persistente."

<details><summary>✅ Resposta</summary>

**FALSO.** MongoDB executa no **servidor** (backend), não no frontend. Ligar diretamente o frontend ao MongoDB é impossível e inseguro — o código do frontend fica **exposto**, as credenciais ficariam visíveis, e representa um risco de segurança enorme. Para persistência no frontend usa-se `localStorage`.
</details>

---

## Parte 3 — Resposta Aberta (10 valores)

---

**Aberta 1.** Indique o que entende pelo conceito cliente-servidor em aplicações web. *(1 val)*

<details><summary>✅ Resposta Modelo</summary>

O modelo **cliente-servidor** é uma arquitetura de software com dois intervenientes:

- **Cliente**: O browser do utilizador, que executa HTML, CSS e JavaScript (frontend). Inicia a comunicação enviando pedidos HTTP.
- **Servidor**: Aplicação remota (ex: Node.js + Express) que recebe pedidos, processa lógica de negócio, acede a bases de dados, e devolve respostas (HTML, JSON, etc.).

**Fluxo**: Cliente envia pedido HTTP → Servidor processa (middleware → controller → model/BD) → Servidor devolve resposta HTTP → Cliente renderiza.

Na MEAN Stack: Angular = cliente, Node.js/Express = servidor.
</details>

---

**Aberta 2.** Explique o que são serviços REST no âmbito do desenvolvimento de aplicações para a web. *(2 val)*

<details><summary>✅ Resposta Modelo</summary>

**REST** (Representational State Transfer) é um padrão arquitetural que define restrições para a criação de web services baseados em HTTP.

Uma **API REST** é um conjunto de endpoints (URLs) que permitem comunicação entre sistemas via métodos HTTP padronizados:

| Método HTTP | Operação CRUD | Exemplo |
|-------------|---------------|---------|
| **GET** | Read | `GET /products` — lista produtos |
| **POST** | Create | `POST /products` — cria produto |
| **PUT** | Update | `PUT /product/:id` — atualiza |
| **DELETE** | Delete | `DELETE /product/:id` — remove |

**Propriedades REST:**
1. **Stateless** — cada pedido contém toda a informação necessária
2. **Cacheable** — respostas podem ser guardadas em cache
3. **Interface uniforme** — URLs representam recursos
4. **Cliente-servidor** — separação clara
5. **Sistema em camadas**

Respostas em **JSON** (ou XML). Para testes usa-se **Postman**, para documentação **Swagger/OpenAPI**, para segurança **CORS** e **JWT**.
</details>

---

**Aberta 3.1.** Identifique e corrija os erros presentes no código do Click Counter. *(1 val)*

```html
<html>
<head>
  <meta charset="UTF-8">
  <title>Click Counter</title>
</head>
<body>
  <h1>Click Counter</h1>
  <p>Click Number: <span id="contador">0</span></p>
  <button>Click Me</button>
  <script>
    function contarClique() {
      numeroCliques++;
      document.getElementById(counter).textContent = numeroCliques;
    }
  </script>
</body>
</html>
```

<details><summary>✅ Resposta Modelo</summary>

**Erro 1:** `numeroCliques` nunca é **declarado nem inicializado** → Adicionar `var numeroCliques = 0;`

**Erro 2:** `getElementById(counter)` — `counter` é variável undefined → Deve ser **string**: `getElementById("contador")`

**Erro 3:** O botão não tem **evento associado** → Adicionar `onclick="contarClique()"` ou usar `addEventListener`

**Código corrigido:**
```html
<button onclick="contarClique()">Click Me</button>
<script>
  var numeroCliques = 0;
  function contarClique() {
    numeroCliques++;
    document.getElementById("contador").textContent = numeroCliques;
  }
</script>
```
</details>

---

**Aberta 3.2.** Como podemos melhorar a página para que a contagem de cliques continue e não seja reiniciada ao reabrir a página? *(1 val)*

<details><summary>✅ Resposta Modelo</summary>

Usar **`localStorage`** para guardar o valor do contador de forma persistente:

```html
<script>
  // Recuperar valor guardado (ou 0 se não existir)
  var numeroCliques = parseInt(localStorage.getItem("contadorCliques")) || 0;
  document.getElementById("contador").textContent = numeroCliques;

  function contarClique() {
    numeroCliques++;
    document.getElementById("contador").textContent = numeroCliques;
    // Guardar o novo valor
    localStorage.setItem("contadorCliques", numeroCliques);
  }
</script>
```

O `localStorage` persiste entre sessões (sobrevive ao fechar o browser), ao contrário do `sessionStorage`.
</details>

---

**Aberta 4.** Comente a frase: "Os componentes criados na framework Angular cumprem o padrão MVC". *(1.5 val)*

<details><summary>✅ Resposta Modelo</summary>

A afirmação é **verdadeira**. Os componentes Angular cumprem o padrão MVC:

| Camada MVC | Componente Angular | Ficheiro |
|------------|-------------------|----------|
| **Model** | Classes/Interfaces e **Serviços** (`@Injectable`) | `*.model.ts`, `*.service.ts` |
| **View** | Template HTML do componente | `*.component.html` + `*.component.css` |
| **Controller** | Classe TypeScript (`@Component`) | `*.component.ts` |

**Na prática:**
- O `component.ts` (Controller) contém a lógica e comunica com serviços
- O `component.html` (View) apresenta dados usando data binding (`{{ }}`, `[prop]`, `(event)`, `[(ngModel)]`)
- Os serviços (Model) gerem dados, comunicam com APIs REST via `HttpClient`
- O Angular suporta data binding bidirecional
- `ng generate component` cria automaticamente ficheiros separados (`.ts`, `.html`, `.css`), promovendo esta separação
</details>

---

**Aberta 5.** Quais as funções dos componentes Guard e Intercept numa aplicação escrita com a framework Angular? *(1.5 val)*

<details><summary>✅ Resposta Modelo</summary>

**Guards (Guardas de Rota):**
- Implementam `CanActivate`
- Controlam o **acesso a rotas/páginas**
- Se retornam `true`, navegação prossegue; `false`, bloqueia
- Exemplo: Verificar se existe token em `localStorage`; se não, redirecionar para `/login`
```typescript
{ path: 'admin', component: AdminComponent, canActivate: [AuthGuard] }
```

**Interceptors (Interceptadores HTTP):**
- Implementam `HttpInterceptor`
- **Interceptam e modificam pedidos HTTP** antes de serem enviados
- Executados automaticamente em todos os pedidos do `HttpClient`
- Exemplo: Adicionar token JWT ao header de todos os pedidos
```typescript
const clonedReq = req.clone({
  headers: req.headers.set('Authorization', 'Bearer ' + token)
});
return next.handle(clonedReq);
```

**Resumo:** Guards protegem **rotas/páginas** (autorização de navegação). Interceptors modificam **pedidos HTTP** (ex: adicionar headers de autenticação). Ambos são fundamentais para autenticação/autorização em Angular.
</details>

---

> 🎯 **Dica final:** Pratica estes exames em condições de tempo real (1h30min). Faz cada exame completo sem consultar as respostas. Depois corrige e anota os pontos fracos para rever no guia de estudo.
