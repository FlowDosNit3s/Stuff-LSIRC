datas = ["12/08/2014", "21/06/1955", "05/06/2010", "03/01/1986", "21/05/2019"]

for i in datas:
    ano = int(i.split("/")[2])
    idade = 2025 - ano
    if 0 <= idade <= 12:
        classificacao = "Criança"
    elif 13 <= idade <= 17:
        classificacao = "Juvenil"
    elif 18 <= idade <= 64:
        classificacao = "Adulto"
    else:
        classificacao = "Sénior"
    print(f"Idade em 2025: {idade} anos: {classificacao}")

'''
 Passo a passo:
É o texto original: "12/08/2014".
.split("/")

Corta o texto em pedaços onde houver uma barra / e cria uma lista:
"12/08/2014".split("/") $\rightarrow$ ["12", "08", "2014"]
[2]

Vai buscar o elemento na posição 2 dessa lista (lembrando que as posições em Python começam no 0):
[0] é o dia: "12"
[1] é o mês: "08"
[2] é o ano: "2014" (ainda é texto/string)
int(...)

Converte o texto "2014" num número inteiro 2014.
Sem o int(), não conseguirias fazer a conta 2025 - ano (daria erro porque não dá para subtrair um número de um texto).
'''
