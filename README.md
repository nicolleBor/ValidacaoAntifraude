

# Motor de Validação Antifraude

<img src="https://img.shields.io/badge/Java-cfcfcf?style=for-the-badge&logo=**java**&logoColor=black"/>

> Status do Projeto: :heavy_check_mark: Concluído


### Tópicos 

:small_blue_diamond: [Descrição do Projeto](#descrição-do-projeto)

:small_blue_diamond: [Estrutura do Projeto](#estrutura-do-projeto)

:small_blue_diamond: [Funcionalidades](#funcionalidades)

:small_blue_diamond: [Pré-requisitos](#pré-requisitos)

:small_blue_diamond: [Como Executar](#como-executar)

:small_blue_diamond: [Estrutura de Classes](#estrutura-de-classes)

:small_blue_diamond: [Contato](#contato)

:small_blue_diamond: [Licença](#licença)


## Descrição do Projeto

O projeto *Motor de Validação Antifraude* é um protótipo desenvolvido em aplicação Java com o objetivo de gerar um grau de confiabilidade de um determinado cliente à partir de dados requeridos. Caso seja identificada alguma inconsistência nos dados fornecidos e/ou informações faltantes, o grau de confiabilidade será zero; caso não, gerará uma nota aleatória entre zero e dez. 

Ele utiliza uma interface gráfica com JFrame e integra-se a uma API de consulta de CEP para verificar endereços.

## Estrutura do Projeto

A estrutura principal do projeto é a seguinte:

```bash
src/                                                                                                                                                                  
├── main/                                                                                                                                                             
│   ├── java/                                                                                                                                                         
│   │     └── desafio/                                                                                                                                                
│   │       └── conquista/                                                                                                                                            
│   │           └── setup/                                                                                                                                            
│   │               └── itau/                                                                                                                                         
│   │                   ├── controller/                                                                                                                               
│   │                   │   ├── ValidacaoAntifraudeController.java                                                                                                    
│   │                   │   └── IntegracaoAPIController.java                                                                                                          
│   │                   ├── models/                                                                                                                                   
│   │                   │   ├── Cliente.java                                                                                                                          
│   │                   │   └── Endereco.java                                                                                                                         
│   │                   └── utils/                                                                                                                                    
│   │                       └── Utilities.java                                                                                                                        
│   └── resources/                                                                                                                                                    
└── test/                                                                                                                                                             
    └── java/
```                                                                                                                                                         


## Funcionalidades

:heavy_check_mark: *Validações de Dados:* Realiza validações de CPF, Nome Completo, Telefone, E-mail, Data de Nascimento, Endereço e Nome da Mãe.

:heavy_check_mark:  *Consulta de Endereço via CEP:* Integração com a API ViaCEP para obter informações de endereço a partir do CEP digitado.

:heavy_check_mark:  *Interface Gráfica:* Utiliza JFrame para interação com o usuário.

## Pré-requisitos

- *Java 8 ou superior:* [Download do JDK](https://www.oracle.com/java/technologies/javase-jdk11-downloads.html)
- *Maven 3.6.0 ou superior:* [Download do Maven](https://maven.apache.org/download.cgi)

## Como Executar

1. *Clonar o Repositório:*

   ```bash
   git clone https://github.com/nicolleBor/ValidacaoAntifraude.git
   cd ValidacaoAntifraude
   ```

2. *Executar a Aplicação:*

   ```bash
   mvn exec:java -Dexec.mainClass="desafio.conquista.setup.itau.view.Principal"
   ```

## Estrutura de Classes

### Models

- *Cliente.java:* Representa os dados do cliente, incluindo Nome Completo, CPF, Telefone, E-mail, Data de Nascimento, Endereço e Nome da Mãe.
- *Endereco.java:* Contém informações de endereço, incluindo CEP, Logradouro, Bairro, Localidade, UF e Unidade e Complemento.

### Controllers

- *ValidacaoAntifraudeController.java:* Controlador principal que gerencia a validação dos dados.
- *IntegracaoAPIController.java:* Responsável por integrar-se com a API ViaCEP para consulta de endereços.

### Utils

- *Utilities.java:* Classe utilitária com métodos auxiliares, como conversão de JSON e limpeza de caracteres especiais.


## Contato

| [<img src="https://avatars.githubusercontent.com/u/155683365?s=400&u=18f0d539c431028608d0b599db0753f81a95f282&v=4" width=115><br><sub>Nicolle Borges</sub>](https://github.com/nicolleBor) |  
| :---: 

## Licença

The [MIT License]() (MIT)

Copyright :copyright: 2025 - Validação Antifraude
