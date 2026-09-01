# ⚡ CHEATSHEET DE CÓDIGO & SINTAXE RÁPIDA — PP

> **Guia de Consulta Rápida:** Esqueletos de código, boas práticas e sintaxe Java para memorizar antes de entrar para o Exame de PP.

---

## 1. COMPACTAÇÃO DE ARRAYS (REMOVER NULOS) — TEMPLATE 20v

```java
public Tipo[] getElementosValidos(Tipo[] original, int countValido) {
    if (original == null) return new Tipo[0];
    
    int count = 0;
    for (int i = 0; i < countValido; i++) {
        if (original[i] != null) count++;
    }

    Tipo[] result = new Tipo[count];
    int idx = 0;
    for (int i = 0; i < countValido; i++) {
        if (original[i] != null) {
            result[idx] = original[i];
            idx++;
        }
    }
    return result;
}
```

---

## 2. REDIMENSIONAMENTO DINÂMICO DE ARRAY

```java
private void resize() {
    Tipo[] temp = new Tipo[this.array.length * 2];
    for (int i = 0; i < this.numberOfElements; i++) {
        temp[i] = this.array[i];
    }
    this.array = temp;
}
```

---

## 3. REDEFINIÇÃO PERFEITA DE `equals()` E `toString()`

```java
@Override
public boolean equals(Object obj) {
    if (this == obj) return true;
    if (obj == null || !(obj instanceof NomeDaClasse)) return false;
    NomeDaClasse other = (NomeDaClasse) obj;
    return this.code != null && this.code.equals(other.getCode());
}

@Override
public String toString() {
    return "NomeDaClasse{code='" + code + "', capacity=" + capacity + "}";
}
```

---

## 4. CRIAÇÃO DE EXCEÇÃO PERSONALIZADA (CHECKED EXCEPTION)

```java
public class MinhaExcecaoException extends Exception {
    private String detalhe;

    public MinhaExcecaoException(String mensagem) {
        super(mensagem);
    }

    public MinhaExcecaoException(String mensagem, String detalhe) {
        super(mensagem);
        this.detalhe = detalhe;
    }

    public String getDetalhe() { return detalhe; }
}
```

---

## 5. ESTRUTURA DE ENUM COM ATRIBUTOS E CONSTRUTOR PRIVADO

```java
public enum VehicleStatus {
    ENABLED("Veículo Ativo / Operacional"),
    DISABLED("Veículo Inativo / Manutenção");

    private final String description;

    private VehicleStatus(String description) {
        this.description = description;
    }

    public String getDescription() {
        return this.description;
    }
}
```

---

## 6. ESTRUTURA DE CLASSE COM HERANÇA E INTERFACE

```java
public class RefrigeratedVehicleImpl extends VehicleImpl implements RefrigeratedVehicle {
    private double maxKilometers;

    public RefrigeratedVehicleImpl(String code, ItemType type, double capacity, double maxKilometers) {
        super(code, type, capacity);
        if (maxKilometers <= 0) {
            throw new IllegalArgumentException("Quilómetros máximos devem ser > 0.");
        }
        this.maxKilometers = maxKilometers;
    }

    @Override
    public double getMaxKilometers() {
        return this.maxKilometers;
    }
}
```

---

## 7. ORDENAÇÃO NATIVA DE ARRAY DE OBJETOS (`BUBBLE SORT`)

```java
public void sortArrayByCapacity(Container[] array) {
    if (array == null || array.length <= 1) return;
    
    int n = array.length;
    for (int i = 0; i < n - 1; i++) {
        for (int j = 0; j < n - i - 1; j++) {
            if (array[j] != null && array[j + 1] != null) {
                if (array[j].getCapacity() > array[j + 1].getCapacity()) {
                    Container temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                }
            }
        }
    }
}
```

---

## 8. ESTRUTURA `try-catch-finally` E PROPAGAÇÃO DE EXCEÇÕES

```java
public boolean processarRota(Route route, AidBox box) {
    try {
        route.addAidBox(box);
        return true;
    } catch (RouteException e) {
        System.err.println("Erro ao adicionar caixa à rota: " + e.getMessage());
        return false;
    } finally {
        // Executado SEMPRE
    }
}
```
