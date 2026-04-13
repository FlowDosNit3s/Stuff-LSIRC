/**
 * Exercícios de Operações em JavaScript
 * Ficha Prática 2 - PAW
 */

// --- 1. Comparações Básicas ---
console.log("--- Comparações ---");

// 1 == "1" -> true (Coerção de tipo: a string "1" é convertida em número)
console.log('1 == "1":', 1 == "1"); 

// 1 === "1" -> false (Igualdade estrita: tipos diferentes, Number vs String)
console.log('1 === "1":', 1 === "1"); 

// "a" == 97 -> false (Não há conversão automática para ASCII em JS)
console.log('"a" == 97:', "a" == 97); 

// --- 2. Tipos e Variáveis ---
console.log("\n--- Tipos ---");

// 1 / 3 -> 0.333... (Divisão resulta em float por padrão)
console.log("1 / 3:", 1 / 3); 

// typeof de variável não declarada -> "undefined"
// Nota: Usamos typeof para evitar o erro de referência (ReferenceError)
console.log("typeof undeclaredVariable:", typeof undeclaredVariable);

var x = 12;
console.log("typeof x (12):", typeof x); // Retorna "number"

// --- 3. Arrays e Referências de Memória ---
console.log("\n--- Arrays (Objetos) ---");

// [] == [] -> false (Referências de memória diferentes)
console.log("[] == []:", [] == []); 

// [1] == [1] -> false (Conteúdo igual, mas instâncias diferentes)
console.log("[1] == [1]:", [1] == [1]); 

var a = [1, 2];
var b = a; // b aponta para a mesma referência de a
console.log("a == b:", a == b);   // true
console.log("a === b:", a === b); // true

// --- 4. Operadores Lógicos e Aritméticos ---
console.log("\n--- Lógica e Aritmética ---");

// 1 != 5 -> true (1 é diferente de 5)
console.log("1 != 5:", 1 != 5); 

// !(1 != 5) -> false (Inverte o valor verdadeiro)
console.log("!(1 != 5):", !(1 != 5)); 

// 11 % 2 -> 1 (Resto da divisão de 11 por 2)
console.log("11 % 2:", 11 % 2); 

// 11 / 2 -> 5.5 (Divisão decimal)
console.log("11 / 2:", 11 / 2);