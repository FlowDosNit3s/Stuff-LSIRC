# 📝 Exame Modelo A — PAW 2024/2025

> **Curso**: LEI / LSIRC | **UC**: Programação em Ambiente Web  
> **Duração**: 1h30min  
> **Nota**: Não é autorizada consulta a qualquer tipo de documento

---

## Parte 1 — Escolha Múltipla (8 valores)

> Cada pergunta vale 1 valor. Indicar **todas** as opções corretas. Opção incorreta resulta em penalização de 0.5 valores.

---

### Pergunta 1
**Considere a ferramenta NPM utilizada no desenvolvimento de projetos web. Indique todas as afirmações verdadeiras:**

- a. Permite compilar ficheiros TypeScript em JavaScript;
- b. Permite executar scripts definidos no ficheiro `package.json`;
- c. Pode ser utilizado para instalar a framework Express num projeto Node.js;
- d. Permite criar novos projetos Angular com o comando `npm new my-app`.

---

### Pergunta 2
**Indique todas as afirmações verdadeiras sobre o desenvolvimento de aplicações web:**

- a. Node.js é uma framework de backend que permite criar servidores web;
- b. Uma página HTML pode submeter dados para um servidor sem utilizar JavaScript;
- c. O protocolo HTTPS garante que a comunicação entre cliente e servidor é encriptada;
- d. O método HTTP GET deve ser utilizado para enviar credenciais de login ao servidor.

---

### Pergunta 3
**Considere a framework Angular. Indique as afirmações verdadeiras:**

- a. Angular é uma framework fullstack que permite desenvolver frontend e backend;
- b. A linguagem de programação utilizada no Angular é o TypeScript;
- c. Cada componente Angular gera automaticamente 4 ficheiros: `.ts`, `.html`, `.css` e `.spec.ts`;
- d. Os serviços Angular utilizam Observables para comunicação assíncrona com APIs REST.

---

### Pergunta 4
**Considere o seguinte excerto de código. Indique as afirmações verdadeiras:**

```javascript
app.use(function(req, res, next) {
  console.log('Pedido recebido:', req.method, req.path);
  next();
});

app.get('/products', productController.getAll);
app.post('/products', productController.create);
app.put('/product/:id', productController.update);
app.delete('/product/:id', productController.delete);
```

- a. O código apresentado pertence a uma aplicação Angular;
- b. A primeira função é um middleware que regista informação sobre cada pedido HTTP recebido;
- c. O parâmetro `:id` nas rotas PUT e DELETE é acedido no controller através de `req.query.id`;
- d. A função `next()` é necessária para que o pedido seja processado pelas rotas seguintes.

---

### Pergunta 5
**Considere o excerto de código presente na figura abaixo. Indique as afirmações verdadeiras:**

```html
<!DOCTYPE html>
<html>
<head>
  <style>
    .destaque { color: blue; font-weight: bold; }
  </style>
</head>
<body>
  <p class="destaque">Primeiro parágrafo</p>
  <p id="info">Segundo parágrafo</p>
  <p class="destaque">Terceiro parágrafo</p>
  <script>
    var elems = document.getElementsByClassName('destaque');
    elems[0].style.color = 'red';
  </script>
</body>
</html>
```

- a. Ao abrir a página, todos os parágrafos ficam com a cor vermelha;
- b. Ao abrir a página, apenas o texto "Primeiro parágrafo" fica com a cor vermelha;
- c. A função `getElementsByClassName` retorna uma coleção de elementos com a classe indicada;
- d. O CSS está a ser aplicado de forma inline nesta página.

---

### Pergunta 6
**Considere o seguinte excerto de código. Indique as afirmações verdadeiras:**

```typescript
@Component({
  selector: 'app-product-list',
  templateUrl: './product-list.component.html',
  styleUrls: ['./product-list.component.css']
})
export class ProductListComponent implements OnInit {
  products: Product[] = [];

  constructor(private restService: RestService) {}

  ngOnInit(): void {
    this.restService.getProducts().subscribe(data => {
      this.products = data;
    });
  }
}
```

- a. Este código pertence a uma aplicação de backend escrita em ExpressJS;
- b. O componente utiliza Injeção de Dependências para aceder ao serviço `RestService`;
- c. A função `getProducts()` retorna os dados de forma síncrona, bloqueando até a resposta estar pronta;
- d. O método `ngOnInit()` é executado uma vez, após a inicialização do componente.

---

### Pergunta 7
**Indique as afirmações verdadeiras sobre bases de dados e armazenamento de dados em aplicações web:**

- a. MongoDB é uma base de dados relacional que utiliza tabelas e linhas;
- b. O módulo Mongoose permite definir schemas e modelos para interagir com MongoDB a partir de Node.js;
- c. É seguro ligar uma aplicação Angular diretamente ao MongoDB para guardar dados de forma persistente;
- d. O `localStorage` do browser permite guardar dados que persistem mesmo após fechar e reabrir o browser.

---

### Pergunta 8
**Considere o seguinte excerto de código. Indique as afirmações verdadeiras:**

```javascript
<% if (products && products.length > 0) { %>
  <ul>
    <% products.forEach(function(product) { %>
      <li><%= product.name %> - <%= product.price %>€</li>
    <% }); %>
  </ul>
<% } else { %>
  <p>Nenhum produto encontrado.</p>
<% } %>
```

- a. Este código é executado no browser do cliente utilizando JavaScript;
- b. Este código utiliza o template engine EJS para gerar HTML dinamicamente no servidor;
- c. Se a variável `products` estiver vazia, a mensagem "Nenhum produto encontrado." será exibida;
- d. O template engine Mongoose é responsável por processar este tipo de código.

---

## Parte 2 — Verdadeiro ou Falso (2 valores)

> Cada questão vale 0.5 valores. Justificar as afirmações falsas.

---

### 1. "O Node.js é uma framework de backend que compete diretamente com o ExpressJS no desenvolvimento de aplicações web."

---

### 2. "O objeto XMLHttpRequest em JavaScript permite enviar pedidos HTTP assíncronos ao servidor sem necessidade de recarregar a página."

---

### 3. "Em Angular, os serviços (@Injectable) devem ter obrigatoriamente um ficheiro de template HTML associado para apresentar dados ao utilizador."

---

### 4. "O método HTTP PUT é idempotente, o que significa que executar o mesmo pedido múltiplas vezes produz o mesmo resultado."

---

## Parte 3 — Resposta Aberta (10 valores)

---

### Pergunta 1 (1.5 valores)
**Explique a diferença entre autenticação e autorização no contexto do desenvolvimento de aplicações web. Na sua resposta, inclua exemplos de como estes conceitos são implementados na MEAN Stack.**

---

### Pergunta 2 (2 valores)
**Descreva o padrão de software MVC (Model-View-Controller). Explique como este padrão pode ser aplicado numa aplicação escrita com a framework ExpressJS e também numa aplicação Angular.**

---

### Pergunta 3.1 (1 valor)
**Identifique e corrija os erros presentes no seguinte excerto de código HTML/JavaScript, de forma a que a página funcione corretamente.**

```html
<html>
<head>
  <meta charset="UTF-8">
  <title>Lista de Tarefas</title>
</head>
<body>
  <h1>Lista de Tarefas</h1>
  <input type="text" id="novaTarefa">
  <button>Adicionar</button>
  <ul id="lista"></ul>
  <script>
    function adicionarTarefa() {
      var texto = document.getElementById(novaTarefa).value;
      var li = document.createElement("li");
      li.textContent = texto;
      document.getElementById("lista").appendChild(li);
    }
  </script>
</body>
</html>
```

---

### Pergunta 3.2 (1 valor)
**Como podemos melhorar a página anterior para que as tarefas adicionadas sejam mantidas mesmo após fechar e reabrir o browser? Indique uma solução baseada apenas na página HTML apresentada.**

---

### Pergunta 4 (2 valores)
**Considere o seguinte excerto de código de uma aplicação Angular. Explique a funcionalidade de cada bloco de código, indique que padrões de software estão a ser utilizados e como o serviço pode ser consumido por um componente Angular.**

```typescript
@Injectable({
  providedIn: 'root'
})
export class TaskService {
  private tasksSource = new BehaviorSubject<Task[]>([]);
  tasks$ = this.tasksSource.asObservable();

  constructor(private http: HttpClient) {}

  loadTasks(): void {
    this.http.get<Task[]>('http://localhost:3000/api/tasks')
      .subscribe(data => {
        this.tasksSource.next(data);
      });
  }

  addTask(task: Task): Observable<Task> {
    return this.http.post<Task>('http://localhost:3000/api/tasks', 
      JSON.stringify(task), httpOptions);
  }
}
```

---

### Pergunta 5 (1.5 valores)
**Explique o que são serviços REST no âmbito do desenvolvimento de aplicações para a web. Na sua resposta inclua as propriedades fundamentais de uma API REST e o mapeamento entre métodos HTTP e operações CRUD.**

---

### Pergunta 6 (1 valor)
**Considere a seguinte afirmação: "Numa aplicação MEAN Stack, o Angular pode comunicar diretamente com o MongoDB para guardar e ler dados, tornando desnecessário o uso de um backend com ExpressJS." Comente esta afirmação, indicando se é verdadeira ou falsa e justificando a sua resposta.**

---
---
---

# ✅ Soluções — Exame Modelo A

---

## Parte 1 — Escolha Múltipla

---

### Pergunta 1
#### ✅ Respostas corretas: **b, c**

> **Justificação**:
> - **a) FALSO** — O NPM é um gestor de packages, **não** compila TypeScript. A compilação de TypeScript é feita pelo compilador `tsc` (TypeScript Compiler).
> - **b) VERDADEIRO** — `npm start`, `npm test`, etc., executam os scripts definidos na secção `"scripts"` do ficheiro `package.json`.
> - **c) VERDADEIRO** — `npm install express --save` instala a framework Express como dependência do projeto Node.js.
> - **d) FALSO** — O comando `npm new my-app` **não existe**! Para criar um projeto Angular, usa-se o Angular CLI: `ng new my-app`.

---

### Pergunta 2
#### ✅ Respostas corretas: **b, c**

> **Justificação**:
> - **a) FALSO** — Node.js **não** é uma framework. É um **runtime** (ambiente de execução) para JavaScript. A framework de backend utilizada sobre o Node.js é o **ExpressJS**.
> - **b) VERDADEIRO** — Um formulário HTML padrão (`<form action="/rota" method="POST">`) submete dados nativamente ao servidor sem necessidade de JavaScript.
> - **c) VERDADEIRO** — HTTPS (HTTP + SSL/TLS) encripta a comunicação entre cliente e servidor, protegendo dados sensíveis como passwords e tokens.
> - **d) FALSO** — O método GET expõe os dados no URL (query string), ficando visíveis no histórico do browser e nos logs do servidor. Para credenciais de login, deve-se usar **sempre POST** com HTTPS.

---

### Pergunta 3
#### ✅ Respostas corretas: **b, c, d**

> **Justificação**:
> - **a) FALSO** — Angular é uma framework **client-side** (frontend). Para uma solução fullstack é necessário combinar Angular com um backend (Express + MongoDB = MEAN Stack).
> - **b) VERDADEIRO** — O Angular é escrito nativamente em **TypeScript**, um superset de JavaScript com tipagem estática criado pela Microsoft.
> - **c) VERDADEIRO** — O comando `ng generate component nome` gera 4 ficheiros: `nome.component.ts` (lógica), `nome.component.html` (template), `nome.component.css` (estilos) e `nome.component.spec.ts` (testes).
> - **d) VERDADEIRO** — Os serviços Angular utilizam `HttpClient` que retorna **Observables** (RxJS), permitindo comunicação assíncrona e reativa com APIs REST.

---

### Pergunta 4
#### ✅ Respostas corretas: **b, d**

> **Justificação**:
> - **a) FALSO** — O código usa `app.use()`, `app.get()`, `req, res, next` — são padrões da framework **ExpressJS** (backend Node.js), não de Angular.
> - **b) VERDADEIRO** — A primeira função é um **middleware** que regista (log) o método HTTP e o caminho de cada pedido recebido no servidor. Tem acesso a `req`, `res` e `next`.
> - **c) FALSO** — Os parâmetros de rota (`:id`) são acedidos via `req.params.id`, **não** via `req.query.id`. O `req.query` é usado para query strings (`?key=val`).
> - **d) VERDADEIRO** — O `next()` é essencial no middleware para passar o controlo ao próximo middleware ou rota. Sem `next()`, o pedido ficaria "preso" e nunca chegaria às rotas definidas abaixo.

---

### Pergunta 5
#### ✅ Respostas corretas: **b, c**

> **Justificação**:
> - **a) FALSO** — Apenas o "Primeiro parágrafo" fica vermelho. O código altera apenas `elems[0]` (o primeiro elemento da coleção com classe "destaque").
> - **b) VERDADEIRO** — `getElementsByClassName('destaque')` retorna uma coleção. `elems[0]` é o primeiro `<p>` com a classe "destaque" ("Primeiro parágrafo"). O seu `style.color` é alterado para 'red', sobrepondo o CSS da classe.
> - **c) VERDADEIRO** — `getElementsByClassName` retorna uma **coleção** (HTMLCollection) de todos os elementos que possuem a classe indicada.
> - **d) FALSO** — O CSS está a ser aplicado de forma **internal** (tag `<style>` dentro do `<head>`), não inline. CSS inline seria usar o atributo `style` diretamente na tag HTML.

---

### Pergunta 6
#### ✅ Respostas corretas: **b, d**

> **Justificação**:
> - **a) FALSO** — O código usa decoradores Angular (`@Component`), TypeScript, `OnInit`, e `subscribe` — tudo específico de **Angular** (frontend), não de ExpressJS.
> - **b) VERDADEIRO** — O `RestService` é injetado no construtor do componente via **Dependency Injection** (DI), um padrão fundamental do Angular: `constructor(private restService: RestService)`.
> - **c) FALSO** — `getProducts()` retorna um `Observable`, que é **assíncrono** (não bloqueante). Os dados só são obtidos quando se faz `.subscribe()`, através de um callback.
> - **d) VERDADEIRO** — `ngOnInit()` é um **lifecycle hook** do Angular que é executado **uma vez** após a inicialização do componente. É o local ideal para carregar dados iniciais.

---

### Pergunta 7
#### ✅ Respostas corretas: **b, d**

> **Justificação**:
> - **a) FALSO** — MongoDB é uma base de dados **NoSQL** (não relacional), orientada a **documentos** (formato JSON/BSON). Utiliza coleções e documentos, não tabelas e linhas.
> - **b) VERDADEIRO** — Mongoose é o ODM (Object Document Mapper) para Node.js que permite definir schemas, criar modelos e realizar operações CRUD no MongoDB.
> - **c) FALSO** — **Nunca** se deve ligar o Angular (frontend) diretamente a uma base de dados. O código do frontend fica **exposto** no browser, comprometendo credenciais e segurança. O frontend deve comunicar com o backend via API REST, que por sua vez acede ao MongoDB.
> - **d) VERDADEIRO** — O `localStorage` é uma API do browser que guarda dados de forma **persistente** (sobrevive ao fechar e reabrir o browser), ao contrário do `sessionStorage` que é eliminado ao fechar a tab/sessão.

---

### Pergunta 8
#### ✅ Respostas corretas: **b, c**

> **Justificação**:
> - **a) FALSO** — Este código utiliza tags EJS (`<% %>`, `<%= %>`) que são processadas pelo **servidor** (Express), não pelo browser. O EJS gera HTML estático no servidor e envia-o para o cliente.
> - **b) VERDADEIRO** — O código usa a sintaxe do template engine **EJS** (Embedded JavaScript): `<% %>` para código JS sem output e `<%= %>` para output com HTML escaping.
> - **c) VERDADEIRO** — O bloco `else` garante que quando `products` está vazio ou não existe, a mensagem "Nenhum produto encontrado." é renderizada no HTML enviado ao cliente.
> - **d) FALSO** — Mongoose é um **driver/ODM** para MongoDB, **não** um template engine. O template engine responsável por processar este código é o **EJS**.

---

## Parte 2 — Verdadeiro ou Falso

---

### 1. "O Node.js é uma framework de backend que compete diretamente com o ExpressJS..."
#### ❌ FALSO

> **Justificação**: Node.js **não** é uma framework — é um **runtime** (ambiente de execução) que permite correr JavaScript fora do browser. Node.js e ExpressJS **não** competem: são complementares. O Express é uma **framework web** que corre **sobre** o Node.js. A relação é: Node.js fornece o runtime, e Express fornece a estrutura para criar aplicações web (routing, middleware, etc.). Analogia: Node.js é o "motor" e Express é o "carro".

---

### 2. "O objeto XMLHttpRequest em JavaScript permite enviar pedidos HTTP assíncronos..."
#### ✅ VERDADEIRO

> **Justificação**: Esta é a definição do conceito de **AJAX** (Asynchronous JavaScript And XML). O `XMLHttpRequest` permite enviar pedidos HTTP ao servidor de forma assíncrona, recebendo respostas sem necessidade de recarregar toda a página. Isto permite atualizar partes específicas da interface do utilizador dinamicamente.

---

### 3. "Em Angular, os serviços (@Injectable) devem ter obrigatoriamente um ficheiro de template HTML..."
#### ❌ FALSO

> **Justificação**: Os serviços Angular **NÃO** têm template HTML. São classes TypeScript puras com o decorador `@Injectable()`, que contêm apenas lógica de negócio, comunicação HTTP e gestão de estado. Apenas os **componentes** (`@Component`) possuem ficheiros de template HTML (`.component.html`). Os serviços são injetados nos componentes via Dependency Injection.

---

### 4. "O método HTTP PUT é idempotente..."
#### ✅ VERDADEIRO

> **Justificação**: Um método HTTP é **idempotente** quando executar o mesmo pedido múltiplas vezes produz exatamente o mesmo resultado. O PUT é idempotente: se enviarmos `PUT /product/1 { nome: "X", preco: 10 }` várias vezes, o recurso terá sempre o mesmo estado final. Os métodos GET, PUT e DELETE são idempotentes. O POST **não** é idempotente (cada execução pode criar um novo recurso).

---

## Parte 3 — Resposta Aberta

---

### Pergunta 1 (1.5 valores)

#### Resposta:

**Autenticação** e **Autorização** são dois conceitos distintos mas complementares na segurança de aplicações web:

- **Autenticação** — "Quem és tu?" — É o processo de verificar a **identidade** do utilizador. Tipicamente feita através de credenciais (username + password) no login.

- **Autorização** — "O que podes fazer?" — É o processo de verificar as **permissões** do utilizador. Determina a que recursos o utilizador autenticado pode aceder.

**Implementação na MEAN Stack**:

| Camada | Autenticação | Autorização |
|--------|-------------|-------------|
| **Backend (Express)** | Recebe credenciais via POST → verifica na BD → gera token JWT com `jwt.sign({id, role}, secret)` | Middleware verifica token com `jwt.verify()` → verifica `decoded.role` → se ADMIN, `next()`; senão, 403 |
| **Frontend (Angular)** | Serviço de login comunica com API REST → guarda token no `localStorage` | **Guard** (`CanActivate`) — verifica se token existe antes de permitir navegação. **Interceptor** — adiciona token JWT ao header de todos os pedidos HTTP automaticamente |

**Códigos HTTP associados**: 401 (Unauthorized) para falha de autenticação; 403 (Forbidden) para falha de autorização.

---

### Pergunta 2 (2 valores)

#### Resposta:

O **MVC (Model-View-Controller)** é um padrão de design de software que separa a aplicação em três camadas lógicas:

- **Model** — Representa os dados e a lógica de acesso a dados. Define a estrutura e como os dados são armazenados e manipulados.
- **View** — Responsável pela apresentação visual. Define como os dados são exibidos ao utilizador.
- **Controller** — Intermediário entre Model e View. Recebe inputs, processa lógica de negócio e atualiza a View.

**Aplicação em ExpressJS**:

| Camada MVC | Implementação Express | Pasta/Ficheiro |
|------------|----------------------|----------------|
| **Model** | Schemas e modelos Mongoose | `models/produto.js` → `mongoose.model('Produto', schema)` |
| **View** | Templates EJS com dados dinâmicos | `views/produtos.ejs` → HTML com `<%= produto.nome %>` |
| **Controller** | Funções que processam `req/res`, acedem ao Model e renderizam a View | `controllers/produtoController.js` → `Produto.find()` + `res.render()` |

**Aplicação em Angular**:

| Camada MVC | Implementação Angular | Ficheiro |
|------------|----------------------|----------|
| **Model** | Classes/Interfaces TypeScript e Serviços (`@Injectable`) | `*.model.ts`, `*.service.ts` |
| **View** | Template HTML do componente com data binding | `*.component.html` + `*.component.css` |
| **Controller** | Classe TypeScript do componente (`@Component`) | `*.component.ts` |

Em Angular, o **data binding bidirecional** (`[(ngModel)]`) garante que alterações na View são automaticamente refletidas no Controller e vice-versa. A separação é enforçada pela própria estrutura do Angular, pois cada componente criado com `ng generate component` gera ficheiros separados para lógica, vista e estilos.

---

### Pergunta 3.1 (1 valor)

#### Erros identificados e correções:

**Erro 1**: `document.getElementById(novaTarefa)` — `novaTarefa` é usado como variável (undefined), mas deveria ser a **string** com o id do input.
- **Correção**: Mudar para `document.getElementById("novaTarefa")`.

**Erro 2**: O botão não tem **nenhum evento associado** — a função `adicionarTarefa()` nunca é chamada.
- **Correção**: Adicionar `onclick="adicionarTarefa()"` ao botão, ou usar `addEventListener`.

#### Código corrigido:
```html
<html>
<head>
  <meta charset="UTF-8">
  <title>Lista de Tarefas</title>
</head>
<body>
  <h1>Lista de Tarefas</h1>
  <input type="text" id="novaTarefa">
  <button onclick="adicionarTarefa()">Adicionar</button>
  <ul id="lista"></ul>
  <script>
    function adicionarTarefa() {
      var texto = document.getElementById("novaTarefa").value;
      var li = document.createElement("li");
      li.textContent = texto;
      document.getElementById("lista").appendChild(li);
    }
  </script>
</body>
</html>
```

---

### Pergunta 3.2 (1 valor)

#### Resposta:

Podemos usar a **Web Storage API**, especificamente o **`localStorage`**, para guardar as tarefas de forma persistente no browser. O `localStorage` mantém os dados mesmo após fechar e reabrir o browser.

#### Solução:
```html
<script>
  // Ao carregar a página, recuperar as tarefas guardadas
  var tarefas = JSON.parse(localStorage.getItem("tarefas")) || [];

  // Renderizar tarefas guardadas
  tarefas.forEach(function(texto) {
    var li = document.createElement("li");
    li.textContent = texto;
    document.getElementById("lista").appendChild(li);
  });

  function adicionarTarefa() {
    var texto = document.getElementById("novaTarefa").value;
    var li = document.createElement("li");
    li.textContent = texto;
    document.getElementById("lista").appendChild(li);

    // Guardar no localStorage
    tarefas.push(texto);
    localStorage.setItem("tarefas", JSON.stringify(tarefas));

    // Limpar o input
    document.getElementById("novaTarefa").value = "";
  }
</script>
```

**Explicação**:
- `localStorage.getItem("tarefas")` — recupera as tarefas guardadas anteriormente
- `JSON.parse(...) || []` — converte a string JSON para array; se não existir, usa array vazio
- `localStorage.setItem("tarefas", JSON.stringify(tarefas))` — guarda o array atualizado como string JSON
- Como `localStorage` **persiste** entre sessões do browser (ao contrário de `sessionStorage`), as tarefas mantêm-se mesmo após fechar e reabrir a página

---

### Pergunta 4 (2 valores)

#### Resposta:

O excerto apresenta um **Serviço Angular** (`TaskService`) com o decorador `@Injectable()`.

**Funcionalidade de cada bloco**:

1. **`@Injectable({ providedIn: 'root' })`** — Define que este serviço é um **Singleton** (uma única instância partilhada por toda a aplicação). É registado automaticamente no nível raiz da aplicação.

2. **`BehaviorSubject<Task[]>` + `tasks$`** — Implementa o padrão **Observer/Publish-Subscribe** (RxJS). O `BehaviorSubject` é um Observable especial que guarda o último valor emitido e partilha-o com todos os subscritores. `tasks$` é exposto como Observable para que os componentes possam subscrever às mudanças.

3. **`constructor(private http: HttpClient)`** — Usa **Injeção de Dependências** (DI) para injetar o `HttpClient` do Angular, que permite fazer pedidos HTTP.

4. **`loadTasks()`** — Faz um pedido HTTP GET à API REST do backend para obter todas as tarefas. Quando a resposta chega, emite os dados para todos os subscritores via `this.tasksSource.next(data)`.

5. **`addTask(task)`** — Faz um pedido HTTP POST à API REST para criar uma nova tarefa. Retorna um `Observable<Task>` que o componente deve subscrever.

**Padrões de software utilizados**:
- **Singleton** — `providedIn: 'root'` garante uma instância única
- **Observer/Reactive Programming** — Observables e BehaviorSubject (RxJS) para comunicação assíncrona publish/subscribe
- **Dependency Injection** — HttpClient injetado no construtor

**Como é consumido por um componente**:
```typescript
export class TaskListComponent implements OnInit {
  tasks: Task[] = [];
  
  constructor(private taskService: TaskService) {}  // DI

  ngOnInit() {
    // Subscrever ao Observable para receber atualizações
    this.taskService.tasks$.subscribe(data => {
      this.tasks = data;
    });
    // Carregar tarefas do backend
    this.taskService.loadTasks();
  }

  onAddTask(newTask: Task) {
    this.taskService.addTask(newTask).subscribe(created => {
      this.taskService.loadTasks(); // Recarregar lista
    });
  }
}
```

---

### Pergunta 5 (1.5 valores)

#### Resposta:

**REST** (Representational State Transfer) é um padrão arquitetural definido por **Roy Fielding** em 2000, que define restrições para a criação de **web services** baseados no protocolo HTTP.

Uma **API REST** é um conjunto de endpoints (URLs) que representam recursos, com operações realizadas através de métodos HTTP padronizados.

**Propriedades fundamentais REST**:
1. **Arquitetura cliente-servidor** — separação entre cliente e servidor
2. **Stateless** — cada pedido contém toda a informação necessária; o servidor não guarda estado entre pedidos
3. **Cacheable** — respostas podem ser guardadas em cache para melhor desempenho
4. **Interface uniforme** — URLs representam recursos, métodos HTTP definem operações
5. **Sistema em camadas** — o cliente não precisa saber se comunica diretamente com o servidor final

**Mapeamento Métodos HTTP ↔ CRUD**:

| Método HTTP | Operação CRUD | Exemplo |
|-------------|---------------|---------|
| **GET** | Read (ler) | `GET /products` — lista produtos |
| **POST** | Create (criar) | `POST /products` — cria produto |
| **PUT** | Update (atualizar) | `PUT /product/:id` — atualiza produto |
| **DELETE** | Delete (eliminar) | `DELETE /product/:id` — remove produto |

As respostas são tipicamente em formato **JSON** (embora XML também seja suportado), facilitando o consumo por aplicações frontend. Para documentação, pode-se usar o **Swagger/OpenAPI** (`swagger-ui-express`). Para testes, utiliza-se o **Postman**. Para segurança cross-origin, configura-se **CORS** (`app.use(cors())`).

---

### Pergunta 6 (1 valor)

#### Resposta:

A afirmação é **FALSA**.

O Angular **não** pode nem **não deve** comunicar diretamente com o MongoDB. Existem razões técnicas e de segurança para isso:

1. **Segurança** — O código do Angular executa no **browser** do utilizador (client-side), ficando completamente **exposto**. Se houvesse uma ligação direta ao MongoDB, as credenciais de acesso à base de dados (connection string, username, password) ficariam visíveis no código-fonte, criando uma **falha de segurança gravíssima**.

2. **Arquitetura** — Na MEAN Stack, o backend (Node.js + ExpressJS) atua como **intermediário** (broker) entre o Angular e o MongoDB. O Angular faz pedidos HTTP (via API REST) ao Express, que por sua vez acede ao MongoDB através do Mongoose. Esta separação garante que credenciais e lógica de negócio ficam protegidas no servidor.

3. **O backend é essencial** para: validação de dados no servidor, autenticação e autorização (JWT), proteção contra injeção de código, e controlo de acesso aos dados.

A arquitetura correta é: **Angular → (HTTP/REST) → Express → (Mongoose) → MongoDB**.
