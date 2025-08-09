# Gerenciador de Restaurantes - Tech Challenge Fase 2

## Descrição

Este projeto é a Fase 2 do Tech Challenge, focada na expansão de um sistema de gestão para incluir funcionalidades de tipos de usuário, restaurantes e itens de cardápio. O objetivo é desenvolver uma API robusta e bem estruturada, seguindo as melhores práticas de desenvolvimento.

## Funcionalidades Implementadas

- **Gestão de Tipos de Usuário:** CRUD completo para `TipoUsuario` (Dono de Restaurante, Cliente).
- **Gestão de Restaurantes:** CRUD completo para `Restaurante`, incluindo associação com `Dono de Restaurante`.
- **Gestão de Itens de Cardápio:** CRUD completo para `ItemCardapio`, associado a um `Restaurante`.

## Tecnologias Utilizadas

- Java 17
- Spring Boot 3.2.5
- Maven
- Banco de Dados: PostgreSQL
- Docker e Docker Compose
- JUnit 5
- Mockito
- SpringDoc OpenAPI (Swagger)

## Como Configurar e Rodar o Projeto

### Pré-requisitos

Certifique-se de ter as seguintes ferramentas instaladas:

- Java Development Kit (JDK) 17
- Maven
- Docker e Docker Compose

### Passos para Rodar

1.  **Clonar o Repositório:**
    ```bash
    git clone https://github.com/SEU_USUARIO/SEU_REPOSITORIO
    cd seu-projeto-gerenciador-restaurantes
    ```

2.  **Configurar o Banco de Dados:**
    - Se estiver usando Docker Compose, o banco de dados será iniciado automaticamente.
    - Caso contrário, configure as credenciais do banco de dados no arquivo `application.properties` ou `application.yml` (ex: `spring.datasource.url`, `spring.datasource.username`, `spring.datasource.password`).

3.  **Rodar com Docker Compose (Recomendado):**
    ```bash
    docker-compose up --build
    ```
    Isso irá construir a imagem da sua aplicação e iniciar o banco de dados. A aplicação estará disponível em `http://localhost:8080` (ou a porta configurada).

4.  **Rodar com Maven (Alternativo):**
    ```bash
    mvn spring-boot:run
    ```

## Endpoints da API

A documentação completa da API pode ser acessada via Swagger UI em `http://localhost:8080/swagger-ui.html` (após a aplicação estar rodando).

### Exemplos de Endpoints (CRUD)

#### Tipos de Usuário (`/api/tipos-usuario`)

-   **GET /api/tipos-usuario**: Lista todos os tipos de usuário.
-   **GET /api/tipos-usuario/{id}**: Busca um tipo de usuário por ID.
-   **POST /api/tipos-usuario**:
    ```json
    {
        "nome": "Dono de Restaurante"
    }
    ```
-   **PUT /api/tipos-usuario/{id}**:
    ```json
    {
        "nome": "Cliente VIP"
    }
    ```
-   **DELETE /api/tipos-usuario/{id}**: Deleta um tipo de usuário.

#### Restaurantes (`/api/restaurantes`)

-   **GET /api/restaurantes**: Lista todos os restaurantes.
-   **GET /api/restaurantes/{id}**: Busca um restaurante por ID.
-   **POST /api/restaurantes**:
    ```json
    {
        "nome": "Restaurante do Zé",
        "endereco": "Rua Principal, 123",
        "tipoCozinha": "Brasileira",
        "horarioFuncionamento": "10:00-22:00",
        "donoRestauranteId": 1 // ID de um usuário do tipo 'Dono de Restaurante'
    }
    ```
-   **PUT /api/restaurantes/{id}**:
    ```json
    {
        "nome": "Restaurante do Zé Atualizado",
        "endereco": "Rua Secundária, 456",
        "tipoCozinha": "Mineira",
        "horarioFuncionamento": "11:00-23:00",
        "donoRestauranteId": 1
    }
    ```
-   **DELETE /api/restaurantes/{id}**: Deleta um restaurante.

#### Itens do Cardápio (`/api/itens-cardapio`)

-   **GET /api/itens-cardapio**: Lista todos os itens do cardápio.
-   **GET /api/itens-cardapio/{id}**: Busca um item do cardápio por ID.
-   **POST /api/itens-cardapio**:
    ```json
    {
        "nome": "Feijoada Completa",
        "descricao": "Feijoada tradicional com todos os acompanhamentos",
        "preco": 45.00,
        "disponibilidadeApenasNoRestaurante": false,
        "caminhoFoto": "/imagens/feijoada.jpg",
        "restauranteId": 1 // ID do restaurante ao qual o item pertence
    }
    ```
-   **PUT /api/itens-cardapio/{id}**:
    ```json
    {
        "nome": "Feijoada Light",
        "descricao": "Feijoada com menos gordura",
        "preco": 40.00,
        "disponibilidadeApenasNoRestaurante": true,
        "caminhoFoto": "/imagens/feijoada_light.jpg",
        "restauranteId": 1
    }
    ```
-   **DELETE /api/itens-cardapio/{id}**: Deleta um item do cardápio.

## Arquitetura

Este projeto segue os princípios da Clean Architecture, com o código organizado nas seguintes camadas:

-   **Domain:** Contém as entidades de negócio (ex: `TipoUsuario`, `Restaurante`, `ItemCardapio`, `Usuario`).
-   **Application:** Contém a lógica de negócio e os casos de uso (Services).
-   **Infrastructure:** Lida com a persistência de dados (Repositories) e outras preocupações técnicas.
-   **Presentation:** Responsável pela exposição da API (Controllers).

## Testes

### Testes Unitários

Para rodar os testes unitários, execute:

```bash
# Para Maven
mvn test
```

### Testes de Integração

Para rodar os testes de integração, execute:

```bash
# Para Maven
mvn verify
```

## Collections Postman/Insomnia

As collections para testar a API podem ser encontradas na pasta `docs/postman`  deste repositório. Importe-as para o Postman/Insomnia para testar os endpoints facilmente.



## Contato

Fabrícia Graziele Ribeiro
fabriciarribeiro@gmail.com



