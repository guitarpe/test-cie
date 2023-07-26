#  **Registro de temperatura e velocidade do vento**
**Descrição do Projeto**

Esta aplicação consiste em registrar, mediante um Job scheduler, a temperatura e velocidade do vento de uma cidade.

**Instalação**

Esta aplicação está configurada para a utilização da porta 8089 e utiliza as seguintes versões 

*Spring Boot - 2.4.11*  
*Java - 1.8*  
*Oauth - 2.1.0.RELEASE*  

**Como Testar os Endpoints**

Para esta aplicação é necessário que seja gerado um token que é gerado apenas por usuários aptos para execução. Existe um usuário pré-cadastrado que dá acesso a listagem de usuários cadastrados e o cadastro de um novo.

O endpoint de obtenção do token de acesso com base no usuário pré cadastrado é este:

1. Token: /oauth/token
Este endpoint é responsável por obter o token a ser usado nas requisições.

Método: POST

URL: http://localhost:8089/oauth/token

Parâmetros - Header:

*Content-type: multipart/form-data*  
*Chache-Control: no-cache*  

Parâmetros - Corpo (via form-data):

*grant_type: password*  
*username: flavio.sousa*  
*password: firula* 

Retorno:
```json
{
    "access_token": "b7171b50-f712-4911-8ac2-aa916c833130",
    "token_type": "bearer",
    "refresh_token": "n4531122-g424-7845-8ac2-dc4565g4r54",
    "expires_in": 86399,
    "scope": "read write trust"
}
```

2. Lista de Usuários: /rest/api-climate/data/controls/users
Este endpoint é responsável por obter uma lista de usuários.

Método: GET

URL: http://localhost:8089/rest/api-climate/data/controls/users

Parâmetros - Header:

*Content-type: application/json*  
*Authorization: Bearer {access_token gerado anteriormente}*  

Retorno:
```json
{
    "code": 200,
    "sucesso": true,
    "timestamp": "2023-07-26T00:19:53.215134",
    "message": "Realizado com Sucesso!",
    "data": [
        {
            "id": 1,
            "username": "user1",
            "password": "123456",
            "status": "A",
            "role": "admin"
        },
        {
            "id": 2,
            "username": "user2",
            "password": "123456",
            "status": "A",
            "role": "admin"
        },
        ...
    ]
}
```

3. Cadastro de Usuários: /rest/api-climate/data/controls/user/save
Este endpoint é responsável cadastrar um usuário que terá acesso ao endpoint

Método: POST

URL: http://localhost:8089/rest/api-climate/data/controls/user/save

Parâmetros - Header:

*Content-type: application/json*  
*Authorization: Bearer {access_token gerado anteriormente}*  

Parâmetros - Corpo:
```json
{
    "username": "user3",
    "password": "123456",
    "status": "A",
    "perfil": "admin"
}
``` 

Retorno:
```json
{
    "code": 200,
    "sucesso": true,
    "timestamp": "2023-07-26T00:19:53.215134",
    "message": "Realizado com Sucesso!",
    "data": [
        {
            "id": 1,
            "username": "user1",
            "password": "123456",
            "status": "A",
            "role": "admin"
        }
    ]
}
```

4. Registros da Cidade: /rest/api-climate/data/controls/cities
Este endpoint é responsável por obter os registros de temperatura de uma cidade a partir de um range de datas.

Método: POST

URL: http://localhost:8089/rest/api-climate/data/controls/cities

Parâmetros - Header:

*Content-type: application/json*  
*Authorization: Bearer {access_token gerado anteriormente}*  

Parâmetros - Corpo:
```json
{
    "cidade": "Maceió",
    "uf": "AL",
    "dataInicio": "2023-07-01",
    "dataFinal": "2023-07-12"
}
``` 

Retorno:
```json
{
    "code": 200,
    "sucesso": true,
    "timestamp": "2023-07-26T00:19:53.215134",
    "message": "Realizado com Sucesso!",
    "data": [
        {
            "id": 1,
            "name": "Maceió",
            "state": "Alagoas",
            "country": "BR",
            "date": "2023-07-01",
            "tempmin": 15.0,
            "tempmax": 16.5,
            "windSpeed": 10.0,
            "winddeg": 5.0
            "windgust": 6.0
        },
        {
            "id": 1,
            "name": "Maceió",
            "state": "Alagoas",
            "country": "BR",
            "date": "2023-07-01",
            "tempmin": 15.0,
            "tempmax": 16.5,
            "windSpeed": 10.0,
            "winddeg": 5.0
            "windgust": 6.0
        },
        ...
    ]
}
```

5. Start do Job: /rest/api-climate/jobs/controls/start
Este endpoint é responsável iniciar o registro das temperatura e velocidade do vento mediante parâmetros informados na requisição

Método: POST

URL: http://localhost:8089/rest/api-climate/jobs/controls/start

Parâmetros - Header:

*Content-type: application/json*  
*Authorization: Bearer {access_token gerado anteriormente}*  

Parâmetros - Corpo (intervalo em milisegundos):
```json
{
    "cidade": "Maceió",
    "uf": "AL",
    "intervalo": 300000
}
``` 

Retorno:
```json
{
    "code": 200,
    "sucesso": true,
    "timestamp": "2023-07-26T00:19:53.215134",
    "message": "Job Iniciado com Sucesso!",
    "data": null
}
```

6. Stop do Job: /rest/api-climate/jobs/controls/stop
Este endpoint é responsável finalizar o registro das temperatura e velocidade do vento mediante parâmetros informados na requisição

Método: POST

URL: http://localhost:8089/rest/api-climate/jobs/controls/stop

Parâmetros - Header:

*Content-type: application/json*  
*Authorization: Bearer {access_token gerado anteriormente}*  

Parâmetros - Corpo:
```json
{
    "cidade": "Maceió",
    "uf": "AL",
    "intervalo": 300000
}
``` 

Retorno:
```json
{
    "code": 200,
    "sucesso": true,
    "timestamp": "2023-07-26T00:19:53.215134",
    "message": "Job Finalizado com Sucesso!",
    "data": null
}
```

**Considerações Finais**  
Esta aplicação trata-se de um teste, nela é utilizado a API da OpenWeather para obtenção dos dados referentes a temperatura e também a utilização do ambiente Google Cloud para acesso ao banco de dados postgree.

Obs.: Nesta aplicação não fui utilizada o conceito de Thread para execução de vários Jobs ao mesmo tempo, possibilitando o start/stop de forma específica. Os arquivos de configuração para acesso ao banco de dados e criação das tabelas estão em src\main\resources