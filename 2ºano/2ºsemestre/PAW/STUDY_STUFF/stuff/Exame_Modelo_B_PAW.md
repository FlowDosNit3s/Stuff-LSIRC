# 📝 Exame Modelo B — PAW 2024/2025

> **Curso**: LEI / LSIRC | **UC**: Programação em Ambiente Web  
> **Duração**: 1h30min  
> **Nota**: Não é autorizada consulta a qualquer tipo de documento

---

## Parte 1 — Escolha Múltipla (8 valores)

> Cada pergunta vale 1 valor. Indicar **todas** as opções corretas. Opção incorreta resulta em penalização de 0.5 valores.

---

### Pergunta 1
**Indique todas as afirmações verdadeiras sobre as tecnologias utilizadas no desenvolvimento web:**

- a. HTML é uma linguagem de programação que permite estruturar páginas web;
- b. CSS é uma linguagem de estilos utilizada para formatar a apresentação de páginas HTML;
- c. JavaScript é uma linguagem de programação que pode ser executada tanto no frontend como no backend;
- d. TypeScript é uma linguagem de programação independente que não tem qualquer relação com JavaScript.

---

### Pergunta 2
**Considere as seguintes afirmações sobre a framework ExpressJS. Indique as verdadeiras:**

- a. O ExpressJS permite utilizar template engines como o EJS para gerar páginas HTML dinamicamente no servidor;
- b. O Express Generator cria automaticamente uma pasta chamada `views` onde os templates são guardados;
- c. As funções JavaScript do ExpressJS podem ser executadas diretamente no browser do utilizador;
- d. No ExpressJS, o middleware de erros distingue-se por receber 4 parâmetros: `err, req, res, next`.

---

### Pergunta 3
**Considere o seguinte excerto de código. Indique as afirmações verdadeiras:**

```javascript
const mongoose = require('mongoose');

const userSchema = new mongoose.Schema({
  username: { type: String, required: true },
  email: { type: String, required: true },
  password: { type: String, required: true },
  role: { type: String, default: 'USER' }
});

module.exports = mongoose.model('User', userSchema);
```

- a. Este código pertence a uma aplicação Angular desenvolvida em TypeScript;
- b. O código define um schema e um modelo para interagir com uma coleção MongoDB;
- c. O campo `role` terá o valor `'USER'` se não for especificado na criação do documento;
- d. Este código permite criar diretamente uma interface gráfica para gerir utilizadores.

---

### Pergunta 4
**Indique as afirmações verdadeiras sobre aplicações SPA (Single Page Application) e a framework Angular:**

- a. Numa SPA, cada navegação entre páginas provoca um reload completo do browser;
- b. As aplicações Angular executam no runtime Node.js do servidor;
- c. O Angular permite dividir a interface em componentes reutilizáveis com o decorador `@Component`;
- d. Para passar dados de um componente pai para um componente filho utiliza-se o decorador `@Input()`.

---

### Pergunta 5
**Considere o seguinte excerto de código HTML aberto num browser. Indique as afirmações verdadeiras:**

```html
<!DOCTYPE html>
<html>
<body>
  <h1>Calculadora</h1>
  <input type="number" id="num1" value="5">
  <input type="number" class="num2" value="3">
  <p id="resultado">0</p>
  <button id="calcular">Calcular</button>
  <script>
    document.getElementById('calcular').addEventListener('click', function() {
      var a = document.getElementById('num1').value;
      var b = document.getElementById('num2').value;
      document.getElementById('resultado').textContent = a * b;
    });
  </script>
</body>
</html>
```

- a. Ao clicar no botão "Calcular", o resultado apresentado será 15;
- b. Existe um erro no código: o segundo input usa `class="num2"` mas o JavaScript tenta selecioná-lo com `getElementById('num2')`;
- c. A função `addEventListener` é a forma recomendada de associar eventos a elementos HTML;
- d. A propriedade `textContent` permite alterar o conteúdo HTML de um elemento, incluindo tags.

---

### Pergunta 6
**Considere o seguinte excerto de código. Indique as afirmações verdadeiras:**

```typescript
@Injectable()
export class AuthInterceptor implements HttpInterceptor {
  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    const token = localStorage.getItem('x-access-token');
    if (token) {
      const clonedReq = req.clone({
        headers: req.headers.set('x-access-token', token)
      });
      return next.handle(clonedReq);
    }
    return next.handle(req);
  }
}
```

- a. Este código é executado no servidor ExpressJS para verificar tokens de autenticação;
- b. O código implementa um Interceptor Angular que adiciona um token JWT ao header de cada pedido HTTP;
- c. O Interceptor é executado automaticamente antes de cada pedido HTTP feito pela aplicação Angular;
- d. Este código gere o acesso dos utilizadores às rotas da aplicação Angular.

---

### Pergunta 7
**Considere as seguintes afirmações sobre segurança em aplicações web. Indique as verdadeiras:**

- a. A validação de dados de input deve ser feita exclusivamente no frontend para garantir melhor experiência do utilizador;
- b. CORS (Cross-Origin Resource Sharing) é um mecanismo que permite ao servidor controlar pedidos de domínios diferentes;
- c. O módulo JWT pode ser utilizado com ExpressJS e o template engine EJS para autenticação;
- d. As credenciais de acesso à base de dados devem ser incluídas no código do frontend Angular para facilitar o acesso direto.

---

### Pergunta 8
**Considere o seguinte excerto de código Angular. Indique as afirmações verdadeiras:**

```typescript
const routes: Routes = [
  { path: 'home', component: HomeComponent },
  { path: 'login', component: LoginComponent },
  { path: 'register', component: RegisterComponent },
  { path: 'dashboard', component: DashboardComponent, canActivate: [AuthGuard] },
  { path: 'profile/:id', component: ProfileComponent, canActivate: [AuthGuard] },
  { path: '', redirectTo: '/home', pathMatch: 'full' }
];
```

- a. Este código configura o sistema de routing de uma aplicação Angular do tipo SPA;
- b. As rotas `login` e `register` estão corretamente configuradas sem proteção do Guard;
- c. O Guard `AuthGuard` impede que utilizadores não autenticados acedam às rotas `dashboard` e `profile`;
- d. O parâmetro `:id` na rota `profile/:id` é acedido no componente Angular através de `req.params.id`.

---

## Parte 2 — Verdadeiro ou Falso (2 valores)

> Cada questão vale 0.5 valores. Justificar as afirmações falsas.

---

### 1. "O sessionStorage permite guardar dados no browser que persistem mesmo após fechar e reabrir o browser, tal como o localStorage."

---

### 2. "É possível criar uma aplicação MEAN Stack funcional utilizando MongoDB, ExpressJS, Angular e Node.js, onde cada tecnologia desempenha um papel específico na arquitetura."

---

### 3. "CREATE e UPDATE são métodos HTTP válidos que podem ser utilizados em APIs REST para criar e atualizar recursos no servidor."

---

### 4. "Em ExpressJS, a ordem em que os middleware são declarados no código é irrelevante, pois o Express executa-os automaticamente pela ordem mais eficiente."

---

## Parte 3 — Resposta Aberta (10 valores)

---

### Pergunta 1 (1 valor)
**Indique o que entende pelo conceito de MEAN Stack no desenvolvimento de aplicações web. Identifique cada componente e o seu papel na arquitetura.**

---

### Pergunta 2 (1.5 valores)
**Indique as 3 formas possíveis de incluir CSS numa página HTML. Para cada uma, apresente um exemplo de código e indique qual é a forma recomendada e porquê.**

---

### Pergunta 3.1 (1.5 valores)
**Identifique e corrija todos os erros presentes no seguinte excerto de código HTML/JavaScript, de forma a que a página funcione corretamente. A página pretende converter graus Celsius para Fahrenheit quando o utilizador clica no botão.**

```html
<html>
<head>
  <meta charset="UTF-8">
  <title>Conversor de Temperatura</title>
</head>
<body>
  <h1>Conversor °C → °F</h1>
  <label>Temperatura em Celsius:</label>
  <input type="number" id="celsius">
  <button>Converter</button>
  <p>Resultado: <span class="resultado">-</span></p>
  <script>
    function converter() {
      var celsius = document.getElementById(celsius).value;
      var fahrenheit = celsius * 9/5 + 32;
      document.getElementById('resultado').innerHTML = fahrenheit + ' °F';
    }
  </script>
</body>
</html>
```

---

### Pergunta 3.2 (1 valor)
**A fórmula de conversão Celsius→Fahrenheit pode originar valores decimais extensos (ex: 37.7778). Proponha uma melhoria ao código JavaScript corrigido que limite o resultado a 2 casas decimais. Adicionalmente, como podemos garantir que o utilizador não submete um campo vazio?**

---

### Pergunta 4 (1.5 valores)
**Quais as funções dos componentes Guard e Interceptor numa aplicação escrita com a framework Angular? Na sua resposta inclua exemplos de código que demonstrem a utilização de cada um.**

---

### Pergunta 5 (1.5 valores)
**Considere a segurança de aplicações web. Onde e de que forma devemos validar a informação submetida por utilizadores? Justifique a necessidade de validação em múltiplas camadas e dê exemplos concretos de como implementar essa validação.**

---

### Pergunta 6 (1 valor)
**Observe o seguinte excerto de código de uma aplicação backend. Descreva a sua funcionalidade, identifique erros e proponha correções.**

```javascript
const authController = {};

authController.login = function(req, res) {
  User.findOne({ username: req.body.username }, function(err, user) {
    if (err) return res.status(500).send('Erro no servidor');
    if (!user) return res.status(404).send('Utilizador não encontrado');

    var passwordValid = bcrypt.compareSync(req.body.password, user.password);
    if (!passwordValid) return res.status(401).send({ auth: false, token: null });

    var token = jwt.sign({ id: user._id, role: user.role }, config.secret, {
      expiresIn: 86400 // 24 horas
    });

    res.status(200).send({ auth: true, token: token });
  });
};

authController.verifyToken = function(req, res, next) {
  var token = req.headers['x-access-token'];
  if (!token)
    return res.status(403).send({ auth: false, message: 'No token provided.' });

  jwt.verify(token, config.secret, function(err, decoded) {
    if (err)
      return res.status(500).send({ auth: false, message: 'Failed to authenticate token.' });
    
    req.userId = decoded.id;
    req.userRole = decoded.role;
    next();
  });
};

module.exports = authController;
```

---
---
---

# ✅ Soluções — Exame Modelo B

---

## Parte 1 — Escolha Múltipla

---

### Pergunta 1
#### ✅ Respostas corretas: **b, c**

> **Justificação**:
> - **a) FALSO** — HTML **não** é uma linguagem de programação. É uma linguagem de **marcação** (markup) que descreve a **estrutura** do conteúdo. Não possui lógica, variáveis, condições ou ciclos.
> - **b) VERDADEIRO** — CSS (Cascading Style Sheets) é uma linguagem de **estilos** que define como os elementos HTML são apresentados visualmente (cores, tamanhos, posicionamento, animações).
> - **c) VERDADEIRO** — JavaScript pode ser executado no **frontend** (browser) e no **backend** (Node.js runtime). É a única linguagem que corre nativamente em ambos.
> - **d) FALSO** — TypeScript é um **superset** de JavaScript, criado pela Microsoft. Todo código JavaScript válido é também TypeScript válido. TypeScript adiciona tipagem estática e é compilado para JavaScript com `tsc`.

---

### Pergunta 2
#### ✅ Respostas corretas: **a, b, d**

> **Justificação**:
> - **a) VERDADEIRO** — Express suporta template engines como **EJS**, Pug e Mustache. O EJS permite gerar HTML dinâmico no servidor com `res.render()`.
> - **b) VERDADEIRO** — O `express-generator` (`express --view ejs myapp`) cria automaticamente uma pasta `views/` onde os templates EJS são guardados por defeito.
> - **c) FALSO** — ExpressJS é uma framework **server-side** que corre no runtime Node.js. O código Express executa **apenas** no servidor, nunca diretamente no browser. O browser apenas faz pedidos HTTP ao servidor.
> - **d) VERDADEIRO** — O middleware de erros em Express distingue-se por receber **4 parâmetros** (`err, req, res, next`), ao contrário do middleware normal que recebe apenas 3 (`req, res, next`).

---

### Pergunta 3
#### ✅ Respostas corretas: **b, c**

> **Justificação**:
> - **a) FALSO** — O código usa `require()` (CommonJS), `mongoose.Schema` e `module.exports` — todos padrões de **Node.js/JavaScript**, não de Angular/TypeScript. Angular usa `import/export` e decoradores (`@Injectable`).
> - **b) VERDADEIRO** — O código define um **schema Mongoose** (`mongoose.Schema`) que especifica a estrutura dos documentos, e cria um **modelo** (`mongoose.model('User', userSchema)`) que permite interagir com a coleção `users` no MongoDB.
> - **c) VERDADEIRO** — O campo `role` tem `default: 'USER'`, o que significa que se não for especificado na criação do documento, terá automaticamente o valor `'USER'`.
> - **d) FALSO** — Este código apenas define a estrutura de dados (Model). **Não** cria nenhuma interface gráfica. A interface seria criada na View (templates EJS) ou num frontend separado (Angular).

---

### Pergunta 4
#### ✅ Respostas corretas: **c, d**

> **Justificação**:
> - **a) FALSO** — Numa SPA, a navegação entre "páginas" **não** provoca reload. O Angular atualiza dinamicamente o conteúdo da página única, alterando apenas os componentes necessários. Este é o conceito central de uma SPA.
> - **b) FALSO** — As aplicações Angular executam no **browser** do utilizador (client-side), **não** no Node.js. O Node.js é usado apenas como ferramenta de desenvolvimento (Angular CLI) para compilar e gerar a build do projeto.
> - **c) VERDADEIRO** — O decorador `@Component` é usado para definir componentes Angular reutilizáveis, cada um com a sua lógica (`.ts`), template (`.html`) e estilos (`.css`).
> - **d) VERDADEIRO** — O decorador `@Input()` permite que um componente filho receba dados do componente pai através de property binding: `<app-filho [dado]="valor"></app-filho>`.

---

### Pergunta 5
#### ✅ Respostas corretas: **b, c**

> **Justificação**:
> - **a) FALSO** — O código **não** funciona corretamente! Existe um erro: o segundo input usa `class="num2"` mas o JavaScript tenta selecioná-lo com `document.getElementById('num2')`. Como não existe nenhum elemento com `id="num2"`, `b` será `null` e a multiplicação falhará.
> - **b) VERDADEIRO** — O segundo `<input>` tem `class="num2"` (atributo class), mas o código JavaScript usa `getElementById('num2')` que procura por **id**, não por class. Para funcionar, deveria ser `id="num2"` no HTML, ou usar `getElementsByClassName('num2')[0]` no JavaScript.
> - **c) VERDADEIRO** — `addEventListener` é a forma **recomendada** de associar eventos em JavaScript moderno, pois permite múltiplos handlers no mesmo evento e melhor separação entre HTML e JS.
> - **d) FALSO** — `textContent` trata o conteúdo como **texto puro** (sem interpretar HTML). Para alterar conteúdo incluindo tags HTML, usaríamos `innerHTML`. No entanto, `textContent` é mais seguro pois previne injeção de HTML.

---

### Pergunta 6
#### ✅ Respostas corretas: **b, c**

> **Justificação**:
> - **a) FALSO** — Este código usa `@Injectable()`, `HttpInterceptor`, `HttpRequest`, `Observable` — todos específicos de **Angular** (frontend), não de ExpressJS (backend).
> - **b) VERDADEIRO** — O Interceptor lê o token do `localStorage`, clona o pedido HTTP original e adiciona o token no header `'x-access-token'`. É usado para autenticação via JWT.
> - **c) VERDADEIRO** — Uma vez registado no `app.module.ts`, o Interceptor é executado **automaticamente** antes de cada pedido HTTP feito pelo `HttpClient` da aplicação Angular.
> - **d) FALSO** — Os Interceptors **não** gerem acesso a rotas. Essa é a função dos **Guards** (`CanActivate`). Os Interceptors apenas modificam **pedidos e respostas HTTP**.

---

### Pergunta 7
#### ✅ Respostas corretas: **b, c**

> **Justificação**:
> - **a) FALSO** — A validação de dados **deve ser feita em ambos os lados** — frontend **E** backend. A validação frontend melhora a UX, mas pode ser contornada (desativando JS, usando Postman). A validação backend é **obrigatória** por razões de segurança.
> - **b) VERDADEIRO** — CORS é um mecanismo que permite ao **servidor** enviar headers (`Access-Control-Allow-Origin`) para indicar que aceita pedidos de domínios diferentes. Em Express: `npm install cors` → `app.use(cors())`.
> - **c) VERDADEIRO** — JWT pode ser usado com qualquer tipo de aplicação web, incluindo ExpressJS com EJS. O token é verificado no servidor independentemente de como as páginas são renderizadas (template engine ou API REST).
> - **d) FALSO** — As credenciais de acesso à BD **nunca** devem estar no código do frontend! O código Angular executa no browser e fica **exposto** ao utilizador. As credenciais devem estar apenas no **backend** (servidor).

---

### Pergunta 8
#### ✅ Respostas corretas: **a, b, c**

> **Justificação**:
> - **a) VERDADEIRO** — O código define um array de `Routes` que configura o **routing** de uma aplicação Angular SPA, mapeando URLs para componentes.
> - **b) VERDADEIRO** — As rotas de `login` e `register` **não** devem ter `canActivate` (Guard), pois isso impediria utilizadores não autenticados de fazerem login/registo. Estão corretamente configuradas sem proteção.
> - **c) VERDADEIRO** — O `AuthGuard` com `canActivate` protege as rotas `dashboard` e `profile`, bloqueando o acesso de utilizadores não autenticados e redirecionando-os (tipicamente para login).
> - **d) FALSO** — `req.params.id` é a sintaxe de **ExpressJS** (backend). Em Angular, os parâmetros de rota são acedidos através do serviço **`ActivatedRoute`**: `this.route.snapshot.paramMap.get('id')` ou via `subscribe` no Observable `this.route.params`.

---

## Parte 2 — Verdadeiro ou Falso

---

### 1. "O sessionStorage permite guardar dados no browser que persistem mesmo após fechar e reabrir o browser..."
#### ❌ FALSO

> **Justificação**: O `sessionStorage` **NÃO** persiste após fechar o browser. Os dados guardados no `sessionStorage` são eliminados assim que a tab ou sessão do browser é fechada. Para persistência permanente, deve-se usar o **`localStorage`**, que mantém os dados mesmo após fechar e reabrir o browser. Ambos têm uma capacidade de ~5-10 MB e são específicos do domínio.

---

### 2. "É possível criar uma aplicação MEAN Stack funcional..."
#### ✅ VERDADEIRO

> **Justificação**: A MEAN Stack é uma arquitetura fullstack baseada inteiramente em JavaScript/TypeScript:
> - **M**ongoDB — Base de dados NoSQL (armazena dados em documentos JSON/BSON)
> - **E**xpressJS — Framework web para o backend (routing, middleware, API REST)
> - **A**ngular — Framework frontend (SPA, componentes, serviços)
> - **N**ode.js — Runtime JavaScript para o servidor
>
> Cada tecnologia tem um papel específico: Angular (frontend/browser) → Express (backend/servidor) → MongoDB (base de dados), tudo ligado via pedidos HTTP REST.

---

### 3. "CREATE e UPDATE são métodos HTTP válidos..."
#### ❌ FALSO

> **Justificação**: CREATE e UPDATE **não** são métodos HTTP. São operações **CRUD** (Create, Read, Update, Delete). Os métodos HTTP válidos correspondentes são:
> - CREATE → **POST**
> - READ → **GET**
> - UPDATE → **PUT**
> - DELETE → **DELETE**
>
> Os métodos HTTP padronizados são: GET, POST, PUT, DELETE, PATCH, HEAD, OPTIONS, etc. "CREATE" e "UPDATE" não existem como verbos HTTP.

---

### 4. "Em ExpressJS, a ordem em que os middleware são declarados no código é irrelevante..."
#### ❌ FALSO

> **Justificação**: A ordem dos middleware em Express é **fundamental** e segue a ordem em que são declarados no código. O Express executa os middleware **sequencialmente**, de cima para baixo. Se um middleware de autenticação for declarado **depois** de uma rota, essa rota não será protegida. Exemplo: `app.use(cors())` deve vir antes das rotas para que o CORS seja aplicado a todos os pedidos. O middleware de erros (4 parâmetros) deve ser declarado por **último**.

---

## Parte 3 — Resposta Aberta

---

### Pergunta 1 (1 valor)

#### Resposta:

**MEAN Stack** é uma arquitetura de desenvolvimento de software baseada em JavaScript de ponta a ponta (full-stack). As siglas representam:

| Componente | Tecnologia | Papel na Arquitetura |
|------------|-----------|---------------------|
| **M** | **MongoDB** | Base de dados **NoSQL** orientada a documentos (JSON/BSON). Armazena dados de forma persistente no servidor. |
| **E** | **ExpressJS** | **Framework web** para Node.js. Gere routing, middleware, APIs REST e comunicação com a base de dados. Atua como backend/servidor. |
| **A** | **Angular** | **Framework frontend** (client-side). Cria interfaces SPA no browser com componentes reutilizáveis, serviços e routing. |
| **N** | **Node.js** | **Runtime** JavaScript para o servidor. Permite executar JavaScript fora do browser, servindo como base para o Express. |

**Fluxo**: O Angular (browser) faz pedidos HTTP/REST ao Express (servidor), que acede ao MongoDB via Mongoose. As respostas JSON voltam pelo Express até ao Angular, que atualiza a interface via data binding.

---

### Pergunta 2 (1.5 valores)

#### Resposta:

Existem **3 formas** de adicionar CSS a uma página HTML:

**1. Inline** — Usando o atributo `style` diretamente na tag HTML:
```html
<p style="color: red; font-size: 16px;">Texto vermelho</p>
```
- **Desvantagem**: Mistura estrutura (HTML) com apresentação (CSS). Difícil de manter em páginas grandes.

**2. Internal (Embedded)** — Colocando código CSS dentro de uma tag `<style>` na secção `<head>`:
```html
<head>
  <style>
    p { color: blue; font-size: 14px; }
    .destaque { font-weight: bold; }
  </style>
</head>
```
- **Desvantagem**: Os estilos aplicam-se apenas a essa página. Não pode ser reutilizado noutras páginas.

**3. External (Externo)** ⭐ — Referenciando um ficheiro CSS externo com a tag `<link>`:
```html
<head>
  <link rel="stylesheet" href="styles.css">
</head>
```
- **Forma RECOMENDADA** porque:
  - Separa completamente estrutura (HTML) de apresentação (CSS)
  - O mesmo ficheiro CSS pode ser reutilizado em múltiplas páginas
  - O browser pode guardar o ficheiro em **cache**, melhorando o desempenho
  - Facilita a manutenção e o trabalho em equipa

---

### Pergunta 3.1 (1.5 valores)

#### Erros identificados e correções:

**Erro 1**: `document.getElementById(celsius)` — `celsius` é usado como variável (undefined), mas deveria ser a **string** com o id do input.
- **Correção**: Mudar para `document.getElementById("celsius")`.

**Erro 2**: O botão não tem **nenhum evento associado** — a função `converter()` nunca é chamada.
- **Correção**: Adicionar `onclick="converter()"` ao botão, ou usar `addEventListener`.

**Erro 3**: `document.getElementById('resultado')` — Não existe nenhum elemento com `id="resultado"`. O `<span>` usa `class="resultado"` (atributo class, não id).
- **Correção**: Mudar o HTML para `id="resultado"` em vez de `class="resultado"`.

#### Código corrigido:
```html
<html>
<head>
  <meta charset="UTF-8">
  <title>Conversor de Temperatura</title>
</head>
<body>
  <h1>Conversor °C → °F</h1>
  <label>Temperatura em Celsius:</label>
  <input type="number" id="celsius">
  <button onclick="converter()">Converter</button>
  <p>Resultado: <span id="resultado">-</span></p>
  <script>
    function converter() {
      var celsius = document.getElementById("celsius").value;
      var fahrenheit = celsius * 9/5 + 32;
      document.getElementById('resultado').innerHTML = fahrenheit + ' °F';
    }
  </script>
</body>
</html>
```

---

### Pergunta 3.2 (1 valor)

#### Resposta:

**Limitar a 2 casas decimais**: Usar o método `toFixed(2)` do JavaScript:

```javascript
function converter() {
  var celsius = document.getElementById("celsius").value;

  // Validar que o campo não está vazio
  if (celsius === "" || celsius === null) {
    alert("Por favor, introduza um valor de temperatura.");
    return;
  }

  var fahrenheit = parseFloat(celsius) * 9/5 + 32;
  // Limitar a 2 casas decimais
  document.getElementById('resultado').innerHTML = fahrenheit.toFixed(2) + ' °F';
}
```

**Melhorias implementadas**:
1. **`toFixed(2)`** — Limita o resultado a 2 casas decimais (ex: `99.86` em vez de `99.86000000000001`)
2. **Validação de campo vazio** — Verifica se o input está vazio antes de calcular, mostrando um alerta ao utilizador
3. **`parseFloat(celsius)`** — Converte explicitamente o valor do input (que é sempre uma string) para número decimal, garantindo precisão no cálculo

Adicionalmente, podemos usar o atributo HTML `required` no input (`<input type="number" id="celsius" required>`) para validação nativa do browser.

---

### Pergunta 4 (1.5 valores)

#### Resposta:

#### **Guards (Guardas de Rota)**

Os **Guards** são serviços Angular que implementam a interface `CanActivate` e controlam o **acesso a rotas** da aplicação. Verificam se um utilizador tem permissão para aceder a uma página antes de a carregar.

**Funcionamento**: Antes de navegar para uma rota protegida, o Angular executa o Guard. Se retornar `true`, a navegação prossegue. Se retornar `false`, a navegação é bloqueada.

```typescript
@Injectable()
export class AuthGuard implements CanActivate {
  constructor(private router: Router) {}

  canActivate(): boolean {
    if (localStorage.getItem('token')) {
      return true;  // Utilizador autenticado, permite acesso
    }
    this.router.navigate(['/login']);  // Redireciona para login
    return false;
  }
}

// No routing:
{ path: 'admin', component: AdminComponent, canActivate: [AuthGuard] }
```

**Caso de uso**: Proteger páginas que requerem autenticação (painel admin, perfil, dashboard).

---

#### **Interceptors (Interceptadores HTTP)**

Os **Interceptors** são serviços Angular que implementam a interface `HttpInterceptor` e permitem **modificar pedidos HTTP** antes de serem enviados ao servidor, e/ou modificar respostas antes de chegarem aos componentes.

```typescript
@Injectable()
export class AuthInterceptor implements HttpInterceptor {
  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    const token = localStorage.getItem('token');
    const clonedReq = req.clone({
      headers: req.headers.set('x-access-token', token || '')
    });
    return next.handle(clonedReq);
  }
}
```

**Caso de uso**: Adicionar automaticamente o token JWT a todos os pedidos HTTP, sem ter de o fazer manualmente em cada serviço.

**Resumo**:

| | Guard | Interceptor |
|-|-------|-------------|
| **Protege** | **Rotas**/páginas | **Pedidos HTTP** |
| **Interface** | `CanActivate` | `HttpInterceptor` |
| **Quando age** | Antes de navegar para uma rota | Antes de enviar um pedido HTTP |
| **Exemplo** | Bloquear acesso a /admin | Adicionar token JWT ao header |

---

### Pergunta 5 (1.5 valores)

#### Resposta:

A validação de informação submetida por utilizadores deve ser **obrigatoriamente** implementada em **ambos os lados** da aplicação — frontend **E** backend.

#### **No Frontend (Cliente)**

**Objetivo**: Melhorar a **Experiência do Utilizador** (UX) com feedback imediato.

**Como implementar**:
- **HTML5 nativo**: Atributos como `required`, `minlength`, `maxlength`, `type="email"`, `pattern`
- **Angular (template-driven)**: Validação reativa com `ngModel`:
```html
<input name="email" ngModel required email #email="ngModel">
<div *ngIf="email.invalid && email.touched">
  <span *ngIf="email.errors?.required">Campo obrigatório</span>
  <span *ngIf="email.errors?.email">Email inválido</span>
</div>
```

#### **No Backend (Servidor)**

**Objetivo**: Garantir a **Segurança** do sistema.

**Como implementar**:
- **Middleware Express**: Validar campos obrigatórios antes de processar o pedido
- **Mongoose validators**: Definir restrições no schema (`required: true`, `minlength`, `enum`)
```javascript
const userSchema = new mongoose.Schema({
  email: { type: String, required: true, match: /.+@.+\..+/ },
  password: { type: String, required: true, minlength: 8 }
});
```

#### **Porquê validar no backend?**

A validação do frontend pode ser **facilmente contornada**:
1. O utilizador pode **desativar JavaScript** no browser
2. Pode enviar pedidos diretamente com **Postman** ou **curl** (sem passar pelo frontend)
3. Pode **manipular** o HTML/JS no DevTools do browser
4. Bots e scripts maliciosos ignoram completamente o frontend

Confiar **apenas** na validação do frontend é uma **falha de segurança grave** que pode levar a injeção de código, dados corrompidos ou acesso não autorizado.

---

### Pergunta 6 (1 valor)

#### Resposta:

**Tipo de aplicação**: Este código pertence ao **backend** de uma aplicação web escrita com **ExpressJS** (Node.js).

**Funcionalidade**: O `authController` implementa um sistema de **autenticação e autorização** com JWT:

1. **`authController.login`**:
   - Recebe `username` e `password` via `req.body` (pedido POST)
   - Procura o utilizador na BD MongoDB via Mongoose (`User.findOne`)
   - Verifica a password usando **bcrypt** (`bcrypt.compareSync`) — comparação segura com hash
   - Se válido, gera um **token JWT** com `jwt.sign()`, incluindo o `id` e `role` do utilizador, com expiração de 24h
   - Retorna o token ao cliente (status 200)

2. **`authController.verifyToken`**:
   - É um **middleware** (tem `req, res, next`) que protege rotas
   - Extrai o token do header `'x-access-token'` do pedido
   - Se não existe token: retorna **403** (Forbidden)
   - Se o token é inválido: retorna **500** (Server Error)
   - Se válido: guarda `decoded.id` e `decoded.role` no objeto `req` e chama `next()` para passar ao próximo handler
   - Faz **autenticação** (verificar token válido) E prepara dados para **autorização** (guardar role)

**Erros/Melhorias identificados**: O código em si está funcionalmente correto e bem estruturado. Uma possível melhoria seria:
- Retornar **401** (Unauthorized) em vez de **500** quando o token é inválido, pois 500 indica erro do servidor, quando na verdade é um problema de autenticação do cliente
- Adicionar uma verificação de `role` para autorização (ex: `if (decoded.role !== 'ADMIN') return res.status(403)`)
