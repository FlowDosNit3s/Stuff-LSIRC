
str="8200123;Ana Maria;931221012;12/05/2000"

#Alinea a
lista = str.split(";")
print(lista)

#Alinea b
print(len(lista))

#Alinea c
lista.append("SOL")
print(lista)


#Alinea d
str_final = ",".join(lista)
print (str_final)
