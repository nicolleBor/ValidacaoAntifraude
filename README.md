

# Motor de Validação Antifraude

![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)
![Maven](https://img.shields.io/badge/Maven-4A4A55?style=for-the-badge&logo=apachemaven&logoColor=white)
![JUnit](https://img.shields.io/badge/JUnit-25A162?style=for-the-badge&logo=junit5&logoColor=white)
> Status do Projeto: :heavy_check_mark: Concluído


### Tópicos 

:small_blue_diamond: [Descrição do Projeto](#descrição-do-projeto)

:small_blue_diamond: [Tecnologias Utilizadas](#tecnologias-utilizadas)

:small_blue_diamond: [Estrutura do Projeto](#estrutura-do-projeto)

:small_blue_diamond: [Funcionalidades](#funcionalidades)

:small_blue_diamond: [Pré-requisitos](#pré-requisitos)

:small_blue_diamond: [Como Executar](#como-executar)

:small_blue_diamond: [Testes](#testes)

:small_blue_diamond: [Estrutura de Classes](#estrutura-de-classes)

:small_blue_diamond: [Referências](#referências)

:small_blue_diamond: [Desenvolvedores](#desenvolvedores)

:small_blue_diamond: [Licença](#licença)


## Descrição do Projeto

O projeto *Motor de Validação Antifraude* é um protótipo desenvolvido em aplicação Java com o objetivo de gerar um grau de confiabilidade de um determinado cliente à partir de dados requeridos.

Caso seja identificada alguma inconsistência nos dados fornecidos e/ou informações faltantes, o grau de confiabilidade será zero; caso não, gerará uma nota aleatória entre zero e dez. 

## Tecnologias Utilizadas

- **Java 9+** (Linguagem principal)
- **Maven** (Gerenciamento de dependências)
- **Swing (JFrame)** (Interface gráfica)
- **API ViaCEP** (Consulta de endereços)
- **JUnit** (Framework para testes)

## Estrutura do Projeto

A estrutura principal do projeto é a seguinte:

```bash
src/                                                                                                                                                                  
├── main/                                                                                                                                                             
│   ├── java/                                                                                                                                                         
│         └── desafio/                                                                                                                                                
│           └── conquista/                                                                                                                                            
│               └── setup/                                                                                                                                            
│                   └── itau/                                                                                                                                         
│                       ├── controller/                                                                                                                               
│                       │   ├── ValidacaoAntifraudeController.java                                                                                                    
│                       │   └── IntegracaoAPIController.java                                                                                                          
│                       ├── models/                                                                                                                                   
│                       │   ├── Cliente.java                                                                                                                          
│                       │   └── Endereco.java                                                                                                                         
│                       └── utils/                                                                                                                                    
│                           └── Utilities.java                                                                                                                                                                                                                                                                               
└── test/                                                                                                                                                             
    └── java/
        └── IntegracaoAPIControllerTest.java
        └── ValidacaoAntifraudeControllerTest.java
```                                                                                                                                                         
Os objetos do projeto são os seguintes:

![Diagrama de Classes](./diagrama-classes)

## Funcionalidades

:heavy_check_mark: *Validação de CPF:* Realiza validação do CPF conforme algoritmo disponibilizado pelo Ministério da Fazenda. 

:heavy_check_mark: *Validação de Demais Dados Pessoais:* Realiza validações de Nome Completo, Telefone, E-mail, Data de Nascimento e Nome da Mãe por meio de regex.

:heavy_check_mark:  *Consulta de Endereço via CEP:* Realiza validação de Endereço por meio de integração com a API ViaCEP, que obtém informações de endereço a partir do CEP digitado.

:heavy_check_mark:  *Interface Gráfica:* Utiliza JFrame para interação com o usuário.

## Pré-requisitos

- *Java 9 ou superior:* [Download do JDK](https://www.oracle.com/java/technologies/javase-jdk11-downloads.html)
- *Maven 3.6.0 ou superior:* [Download do Maven](https://maven.apache.org/download.cgi)

## Como Executar

1. *Clonar o Repositório:*

   ```bash
   git clone https://github.com/nicolleBor/ValidacaoAntifraude.git
   cd ValidacaoAntifraude
   ```
2. *Compilar o projeto:*

   ```bash
    mvn clean install
   ```

3. *Executar a Aplicação:*

   ```bash
   mvn exec:java 
   ```

## Testes

O projeto possui testes unitários para os métodos da *Controller* utilizando JUnit. Para executar os testes, rode:

```bash
mvn test
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

## Referências

- [Introdução à Programação Orientada a Objetos Usando Java](http://www.lac.inpe.br/~rafael.santos/java.html)
- [Algoritmo para Validar CPF](https://dicasdeprogramacao.com.br/algoritmo-para-validar-cpf/)
- [Como Criar READMEs? Guia do README Completo](https://www.youtube.com/watch?v=k4Rsy8GbKE0)
- [Como fazer Documentação no JAVA de forma Ágil e FÁCIL usando Anotações](https://www.youtube.com/watch?v=xsRcCPzeeLw)
- [Como desenvolver Testes Unitários no Java utilizando JUnit](https://www.youtube.com/watch?v=CcneEGCgMBI&t=1s)
- [Java PT-BR | Consumindo uma API de CEP](https://dev.to/eduardo_teixeira/java-pt-br-consumindo-uma-api-de-cep-2481)
- [MIT License](https://choosealicense.com/licenses/mit/#)
- [Regex 101](https://regex101.com/)

## Desenvolvedores

| [<img src="https://avatars.githubusercontent.com/u/155683365?s=400&u=18f0d539c431028608d0b599db0753f81a95f282&v=4" width=115><br><sub>Nicolle Borges</sub>](https://github.com/nicolleBor) |  
| :---: 

## Licença

Este projeto está licenciado sob a Licença MIT. Veja o arquivo [LICENSE](LICENSE) para mais detalhes.

Copyright :copyright: 2025 - Validação Antifraude
