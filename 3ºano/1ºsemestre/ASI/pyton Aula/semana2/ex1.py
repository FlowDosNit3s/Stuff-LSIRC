idades =  [16, 18, 10, 28, 24, 26, 30, 46, 72, 65, 91]

print(min(idades))
print(max(idades))
print(sum(idades) / len(idades))

soma = 0
contador = 0

for i in idades:
    if i >= 18 and i  <= 65:
        soma += i
        contador += 1
print(soma / contador)


filtradas1 = [idade for idade in idades if 18 <= idade <= 65]
print(sum(filtradas1) / len(filtradas1))

