Exame 24_25 PEI RESOLUÇÃO
1.
1.1.

<?xml version="1.0" encoding="UTF-8"?>
<ResumoRegistoClinico DataInicio="2024-06-01" DataFim="2024-06-30"
    xmlns="http://www.medsync.com/resumo/v1"
    xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
    xsi:schemaLocation="http://www.medsync.com/resumo/v1 RegistoClinicoTypes.xsd">
    <InformacaoGeral>
        <NomeHospital>Hospital de Exemplo</NomeHospital>
        <Morada>Rua Exemplo, 123, Cidade, País</Morada>
    </InformacaoGeral>
    <Pacientes>
        <Paciente>
            <Id>123456</Id>
            <Nome>João Silva</Nome>
            <DataNascimento>1980-05-15</DataNascimento>
            <Genero>Masculino</Genero>
            <NumeroIdentificacao>987654321</NumeroIdentificacao>
            <Contacto>
                <Telefone>912345678</Telefone>
                <Email>joao.silva@example.com</Email>
            </Contacto>
            <TipoPaciente>Internamento</TipoPaciente>
        </Paciente>
    </Pacientes>
    <RegistosClinicos>
        <RegistoClinico codigo="ICD-10">
            <Paciente>123456</Paciente>
            <DataHora>2024-06-15T10:30:00</DataHora>
            <Especialidade>Cardiologia</Especialidade>
        </RegistoClinico>
    </RegistosClinicos>
    <Resumo>
        <NumeroPacientes>1</NumeroPacientes>
        <NumeroTratamentosRealizados>1</NumeroTratamentosRealizados>
    </Resumo>
</ResumoRegistoClinico>

1.2.

   <xs:element name="Resumo">
    <xs:complexType>
        <xs:all>
            <xs:element name="NumeroPacientes" type="xs:int"/>
            <xs:element name="NumeroTratamentosRealizados" type="xs:int"/>
            <xs:element name="NumeroPorEspecialidade">
                <xs:complexType>
                    <xs:sequence>
                        <xs:element name="Especialidade" maxOccurs="unbounded">
                            <xs:complexType>
                                <xs:sequence>
                                    <xs:element name="Nome" type="xs:string"/>
                                    <xs:element name="Numero" type="xs:int"/>
                                </xs:sequence>
                            </xs:complexType>
                        </xs:element>
                    </xs:sequence>
                </xs:complexType>
            </xs:element> 
        </xs:all>
    </xs:complexType>
</xs:element>

2.1.
Esta estrutura de coleções existentes não é a mais dequada para responder a este conjunto de
questões, uma vez que causa redundancia e dificulta a consulta  efeciente dos pacientes, pois 
se a paciente "Kathleen Ortiz" tiver 10 consultas, os seus daos pessoais serão repetidos 10 vezes.
Isto leva ao consumo excessivo de armazenamento, em consequencia de dados se o telefone e o email
alterarem , temos de atualizar todos os registos anteriores, em vez de apenas um documento só e ira
apresnetar uma dificuldade na consultas propostas, pois se filtrar os registos por ano de nascimento
ira encontrar muitos registos repetidos, o que pode levar a erros na contagem de pacientes unicos.
Em solução:
    -Coleção Pacientes: Armazenar apenas os dados pessoais dos pacientes, com um identificador unico.
    -Coleção RegistoClinico: Armazenar os registos clinicos, referenciando o paciente pelo seu identificador unico.    

2.2.1
$lookup: Realiza uma junção entre a coleção atual e a coleção Tratamentos. Usa o mesmo campo ID_Registo_Clinico e
para corresponder os documentos e colocar o resultado num array chamado "Tratamentos".

$project: Filtra e formata o output. Seleciona apenas ID do resgito, a descrição do diagonostoico restrurura o array 
"Tratamentos" para mostrar apenas Tipo_Tratamento e se foi realizado.

{
    "_id": ObjectId('676470c82851a6f24e3085e6'),
    "ID_Registo_Clinico": "RC1001",
    "Diagonostico":{
        "Descricao": "Epilepsia"
    }
    "Tratamentos": [
        {
            "Tipo_Tratamento":  "Paracetamol 1g",
            "Realizado": sim
        }
    ]
}

2.2.2 falta

3.

let $dados:= getFromMongo() 
return
<Atendimentos>
{
    for $registo in $dados/json/_
    return
    <Paciente>
     <Id>{ data($registo/Paciente/Id) }</Id>
     <Nome>{ data($registo/Paciente/Nome) }</Nome>
     <DataNacimento>{ data($registo/Paciente/DataNascimento) }</DataNascimento>
     <Contacto>
      <Telefone>{ data(&registo/Pacinete/Telefone)}</Telefone>
      <Email>{ data($registo/Paciente/Email)}</Email>
     </Contacto>
     <Diagonostico>
      <Tipo>{ data($registo/Diagonostico/Tipo)} </Tipo>
     <Descricao>{ data($registo/Diagnostico/Descricao) }</Descricao>
    </Paciente>
}