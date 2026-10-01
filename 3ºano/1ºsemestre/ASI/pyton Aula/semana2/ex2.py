array = [8, 7, 8, 6, 10, 12, 14, 12, 18, 12, 17]

frequencia = {}

for i in array:
    if i in frequencia:
        frequencia[i]+=1
    else:
        frequencia[i]=1
        
print(frequencia)

