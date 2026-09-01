# 🎯 GUIA MESTRE — ESTRATÉGIAS PARA RESOLVER AS PERGUNTAS 2A E 2B (9,0 VALORES)

> **Importância:** As Perguntas 2a e 2b valem **9,0 dos 14,0 valores da Parte Prática** (2a = 4,0v | 2b = 5,0v). Dominando a receita deste guia, garante mais de 60% da nota do exame instantaneamente!

---

# ⚡ DICIONÁRIO MESTRE DE DECODIFICAÇÃO DE ENUNCIADOS
### ("Ao Ver Esta Frase no Enunciado do Professor -> Penso e Escrevo Este Código Instantaneamente")

| 📝 Frase / Palavra-Chave no Enunciado | 🧠 O que Deves Pensar Imediatamente | 💻 Código Exato a Escrever |
| :--- | :--- | :--- |
| **"Não deve conter posições nulas" / "Array compacto"** | Nunca devolver o array original com folgas nulas. Contar elementos válidos, criar array de tamanho `count` exato e copiar. | `Tipo[] res = new Tipo[count]; for(int i=0; i<count; i++) res[i] = temp[i]; return res;` |
| **"Caso a invocação origine uma RouteException, retornar false"** | Métodos que lançam Checked Exception DEVEM ser envolvidos em `try-catch` para que a alínea devolva `boolean`. | `try { route.addAidBox(box); return true; } catch(RouteException e) { return false; }` |
| **"Pelo menos um contentor..." / "Se existir ocorrência..."** | Ciclo `for` que devolve `true` no primeiro encontro elegível. Se o ciclo terminar sem encontrar nada, devolve `false`. | `for(...) { if(condicao) return true; } return false;` |
| **"Se a caixa for nula ou não possuir medições..."** | Evitar `NullPointerException` fazendo validações defensivas no topo do método. | `if (box == null \|\| box.getContainers() == null) return 0;` |
| **"Última medição registada..."** | Obter o objeto medição e testar se é `null` antes de extrair o valor com `.getValue()`. | `Measurement last = c.getLastMeasurement(); if(last == null) return false;` |
| **"Ocupação superior a X%"** | Percentagem = `(últimaMedição / capacidade) * 100`. | `(last.getValue() / container.getCapacity()) * 100.0 > X` |
| **"Para cada veículo... criar uma nova rota"** | Instanciar um objeto `RouteImpl` passando o veículo no construtor dentro do ciclo de veículos. | `for(Vehicle v : vehicles) { Route r = new RouteImpl(v); ... }` |
| **"Assuma que apenas existe um veículo para cada tipo"** | Comparar o tipo do veículo com o tipo do bem do contentor. | `if (c.getType() == vehicle.getSupplyType())` |
| **"Lançar a exceção AidBoxFullException se exceder a capacidade"** | Se o número de elementos atingir a capacidade máxima (`count >= MAX`), fazer `throw new AidBoxFullException(...)`. | `if (this.count >= MAX) throw new AidBoxFullException("Cheia!");` |
| **"Dois objetos são iguais se tiverem o mesmo código"** | Implementar `equals(Object obj)` testando `this == obj`, `instanceof` da interface e `this.code.equals(other.getCode())`. | `@Override public boolean equals(Object obj) { ... }` |
| **"Se o contentor for do tipo PERISHABLE_FOOD..."** | Alimentos perecíveis têm recolha urgente assim que há 1 medição. Exige um desvio `if` dedicado. | `if (c.getType() == ItemType.PERISHABLE_FOOD) return last != null;` |

---

## 💡 A ESTRUTURA MENTAL DAS PERGUNTAS 2A E 2B

```
 ┌────────────────────────────────────────────────────────────────────────┐
 │                      PERGUNTA 2A (4,0 VALORES)                         │
 │ 2 Métodos Auxiliares de apoio:                                         │
 │   - Método 1: Verificação Booleana (ex: hasCollectableContainer)      │
 │   - Método 2: Acção / Validação com Exceção (ex: addAidBoxToRoute)     │
 └───────────────────────────────────┬────────────────────────────────────┘
                                     │
                                     ▼ (Usados como blocos de construção)
 ┌────────────────────────────────────────────────────────────────────────┐
 │                      PERGUNTA 2B (5,0 VALORES)                         │
 │ Método Principal da Algoritmia (ex: generate / getTotalCollected):     │
 │   - Percorre a Instituição (Veículos x AidBoxes).                      │
 │   - Chama o Método 1 de 2a para filtrar.                              │
 │   - Chama o Método 2 de 2a para adicionar à Rota com try-catch.        │
 │   - Compacta o Array Final eliminando nulos e devolve!                 │
 └────────────────────────────────────────────────────────────────────────┘
```

---

# 🏆 PARTE 1: EXAME OFICIAL DA ÉPOCA NORMAL 2025/2026 (EXAME DESTE ANO — StrategyImpl)

### 📜 ENUNCIADO OFICIAL ÍPSIS VERBIS DO PROFESSOR:

**Pergunta 2a (4 valores):**
Implemente os seguintes métodos que podem ser utilizados para a geração da rota na classe `StrategyImpl`:
```java
boolean hasCollectableContainer(Vehicle vehicle, AidBox aidbox);
```
- Este método deve devolver `true` caso exista a ocorrência de, pelo menos, um container cujo tipo seja igual ao do veículo e se a sua última medição registada tiver um valor superior a 80% da sua capacidade.

```java
boolean addAidBoxToRoute(Route route, AidBox aidbox, RouteValidator validator);
```
- O método deve devolver `true` caso a AidBox seja adicionada à rota.
- Para adicionar a AidBox à rota, deve previamente efetuar uma validação com recurso ao método `validator.validate(Route route, AidBox aidbox)`:
  - Se validado, a AidBox deve ser adicionada à rota utilizando o método `void addAidBox(AidBox aidBox)` da classe `Route`.
  - Caso a invocação do método `addAidBox` origine uma `RouteException`, o método `addAidBoxToRoute` deve retornar `false`.

**Pergunta 2b (5 valores):**
Na classe `StrategyImpl`, implemente o método `generate`, gerando as rotas necessárias.
Regras a considerar:
- Para cada veículo devolvido pelo método `getVehicles()` da interface `Institution`, deve ser criada uma nova rota. Assuma que só existe um veículo para cada tipo.
- As AidBoxes existentes são as devolvidas pelo método `getAidBoxes()` da interface `Institution`.
- Deve utilizar os métodos desenvolvidos na alínea anterior. Se não os implementou anteriormente, assuma que os métodos já existem.
- O array devolvido pelo método `generate` deve conter rotas com as AidBoxes (sem posições nulas ou rotas vazias).

---

### 💡 ANÁLISE DO PORQUÊ DA RESOLUÇÃO DO EXAME DESTE ANO:
1. **Em `hasCollectableContainer`:**
   - Testamos se o tipo do contentor coincide com o do veículo (`c.getType() == vehicle.getSupplyType()`).
   - Verificamos se existe última medição (`last != null`) e se o valor é estritamente superior a 80% da capacidade (`last.getValue() > (c.getCapacity() * 0.8)`).
2. **Em `addAidBoxToRoute`:**
   - Invocamos `validator.validate(route, aidbox)`. Se for `false`, devolvemos `false`.
   - Envolvemos `route.addAidBox(aidbox)` num `try-catch`. Se a chamada lançar `RouteException`, capturamos a exceção e devolvemos `false` sem crashar a aplicação.
3. **Em `generate`:**
   - Criamos o array temporário `tempRoutes` com capacidade `vehicles.length`.
   - Dentro do ciclo de veículos, instanciamos `Route currentRoute = new RouteImpl(v)`.
   - Iteramos pelas AidBoxes e chamamos `addAidBoxToRoute(currentRoute, box, validator)`.
   - Guardamos a rota apenas se não estiver vazia (`currentRoute.getRoute().length > 0`).
   - No final, compactamos o array `finalRoutes` com o tamanho `count` para **remover todas as posições nulas**.

```java
public class StrategyImpl implements Strategy {

    // 2a (1) - Método de Verificação Booleana (4,0v)
    public boolean hasCollectableContainer(Vehicle vehicle, AidBox aidbox) {
        if (vehicle == null || aidbox == null) return false;
        Container[] containers = aidbox.getContainers();
        if (containers == null) return false;

        for (int i = 0; i < containers.length; i++) {
            Container c = containers[i];
            if (c != null && c.getType() == vehicle.getSupplyType()) {
                Measurement last = c.getLastMeasurement();
                if (last != null && last.getValue() > (c.getCapacity() * 0.8)) {
                    return true; // Encontrou pelo menos um que cumpre a regra dos 80%!
                }
            }
        }
        return false;
    }

    // 2a (2) - Método de Ação com Exceção try-catch (4,0v)
    public boolean addAidBoxToRoute(Route route, AidBox aidbox, RouteValidator validator) {
        if (route == null || aidbox == null || validator == null) return false;
        if (!validator.validate(route, aidbox)) return false;

        try {
            route.addAidBox(aidbox);
            return true; // Adicionada com sucesso!
        } catch (RouteException e) {
            return false; // Captura exceção e devolve false
        }
    }

    // 2b - Método Principal generate com Compactação (5,0v)
    @Override
    public Route[] generate(IInstitution inst, RouteValidator validator) {
        if (inst == null || validator == null) return new Route[0];

        Vehicle[] vehicles = inst.getVehicles();
        AidBox[] aidBoxes = inst.getAidBoxes();
        if (vehicles == null || aidBoxes == null) return new Route[0];

        Route[] tempRoutes = new Route[vehicles.length];
        int count = 0;

        for (int i = 0; i < vehicles.length; i++) {
            Vehicle v = vehicles[i];
            if (v == null) continue;

            Route currentRoute = new RouteImpl(v);
            for (int j = 0; j < aidBoxes.length; j++) {
                AidBox box = aidBoxes[j];
                if (box != null && hasCollectableContainer(v, box)) {
                    addAidBoxToRoute(currentRoute, box, validator);
                }
            }

            // Apenas guarda se a rota tiver AidBoxes adicionadas
            if (currentRoute.getRoute() != null && currentRoute.getRoute().length > 0) {
                tempRoutes[count++] = currentRoute;
            }
        }

        // Compactação do array final (remoção de nulos)
        Route[] finalRoutes = new Route[count];
        for (int i = 0; i < count; i++) finalRoutes[i] = tempRoutes[i];
        return finalRoutes;
    }
}
```

---

# 📚 PARTE 2: ESTUDOS DE CASO DOS EXAMES MODELO 1, 2 E 3

## 🎯 EXAME MODELO 1 — ReportImpl (Relatório Textual)

### 📜 ENUNCIADO OFICIAL DO PROFESSOR:
**Pergunta 2a (4 valores):**
1. `int countContainersByType(AidBox aidbox, ItemType type)`: Devolver o número de contentores na AidBox do tipo recebido.
2. `double getAverageOccupancy(AidBox aidbox)`: Devolver a média de ocupação de todos os contentores da AidBox `(últimaMedição / capacidade) * 100`.

**Pergunta 2b (5 valores):**
`generate(IInstitution inst)`: Gerar um relatório textual percorrendo as AidBoxes da instituição. Incluir apenas AidBoxes cuja ocupação média seja **superior a 50%**.

### 💡 PORQUÊ DESTA RESOLUÇÃO:
- `countContainersByType` incrementa o contador quando o tipo coincide.
- `getAverageOccupancy` evita divisão por zero.
- `generate` filtra por `> 50.0` e concatena o texto com `StringBuilder`.

```java
public class ReportImpl implements Report {
    public int countContainersByType(AidBox aidbox, ItemType type) {
        if (aidbox == null || type == null || aidbox.getContainers() == null) return 0;
        Container[] containers = aidbox.getContainers();
        int count = 0;
        for (int i = 0; i < containers.length; i++) {
            if (containers[i] != null && containers[i].getType() == type) count++;
        }
        return count;
    }

    public double getAverageOccupancy(AidBox aidbox) {
        if (aidbox == null || aidbox.getContainers() == null) return 0.0;
        Container[] containers = aidbox.getContainers();
        double sum = 0.0;
        int validCount = 0;
        for (int i = 0; i < containers.length; i++) {
            Container c = containers[i];
            if (c != null && c.getCapacity() > 0) {
                Measurement last = c.getLastMeasurement();
                if (last != null) {
                    sum += (last.getValue() / c.getCapacity()) * 100.0;
                    validCount++;
                }
            }
        }
        if (validCount == 0) return 0.0;
        return sum / validCount;
    }

    @Override
    public String generate(IInstitution inst) {
        if (inst == null || inst.getAidBoxes() == null) return "";
        AidBox[] boxes = inst.getAidBoxes();
        StringBuilder sb = new StringBuilder();
        sb.append("=== RELATÓRIO DE RECOLHA HUMANITÁRIA ===\n");

        for (int i = 0; i < boxes.length; i++) {
            AidBox box = boxes[i];
            if (box != null && getAverageOccupancy(box) > 50.0) {
                sb.append("AidBox: ").append(box.getCode()).append(" | Zona: ").append(box.getZone()).append("\n");
                sb.append(" - Perecíveis: ").append(countContainersByType(box, ItemType.PERISHABLE_FOOD)).append("\n");
                sb.append(" - Medicamentos: ").append(countContainersByType(box, ItemType.MEDICINE)).append("\n");
                sb.append(" - Ocupação Média: ").append(String.format("%.2f", getAverageOccupancy(box))).append("%\n\n");
            }
        }
        return sb.toString();
    }
}
```
