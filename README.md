# Tech Challenge - Fase 1: Gerenciador de Usuários

API RESTful desenvolvida em Java com Spring Boot para o gerenciamento básico de usuários (CRUD, troca de senha e validação de login). O projeto utiliza Docker e Docker Compose para conteinerização da aplicação e do banco de dados PostgreSQL, facilitando a configuração e execução do ambiente.

Este projeto foi desenvolvido como parte da Fase 1 do Tech Challenge da Pós-Graduação em Arquitetura de Soluções da FIAP.

## Tecnologias Utilizadas

*   **Linguagem:** Java 17
*   **Framework:** Spring Boot 3.x
    *   Spring Web (para APIs REST)
    *   Spring Data JPA (para persistência de dados)
    *   Spring Security (para hash de senhas com BCrypt)
    *   Spring Validation (para validação de DTOs)
*   **Build Tool:** Maven
*   **Banco de Dados:** PostgreSQL 14 (executando em Docker)
*   **Conteinerização:** Docker & Docker Compose
*   **Utilitários:**
    *   Lombok (para reduzir código boilerplate)
    *   ModelMapper (para mapeamento entre DTOs e Entidades)

## Funcionalidades Implementadas

*   **Cadastro de Usuários:** Criação de novos usuários com nome, email, login, senha (armazenada com hash BCrypt) e endereço.
*   **Consulta de Usuários:**
    *   Listagem de todos os usuários cadastrados.
    *   Busca de um usuário específico pelo seu ID.
*   **Atualização de Usuários:** Modificação dos dados cadastrais (nome, email, endereço) de um usuário existente (não permite alterar login ou senha por este endpoint).
*   **Deleção de Usuários:** Remoção de um usuário pelo seu ID.
*   **Troca de Senha:** Atualização segura da senha de um usuário existente (armazenando o hash da nova senha).
*   **Validação de Login:** Verificação das credenciais (login e senha) de um usuário, comparando a senha fornecida com o hash armazenado no banco.

## Como Executar o Projeto (via Docker)

1.  **Pré-requisitos:**
    *   Docker instalado: [https://docs.docker.com/get-docker/](https://docs.docker.com/get-docker/)
    *   Docker Compose instalado (geralmente vem junto com o Docker Desktop).
    *   Git instalado (para clonar o repositório).
2.  **Clonar o Repositório:**
    ```bash
    git clone <URL_DO_SEU_REPOSITORIO_GIT>
    cd <NOME_DA_PASTA_DO_PROJETO>
    ```
3.  **Subir os Contêineres:** Abra um terminal na pasta raiz do projeto (onde estão os arquivos `Dockerfile` e `docker-compose.yml`) e execute o comando:
    ```bash
    docker compose up --build
    ```
    *   A opção `--build` garante que a imagem da aplicação seja construída (ou reconstruída se houver alterações).
    *   Aguarde o download das imagens e a inicialização dos contêineres (`techchallenge-db` e `techchallenge-app`). Você verá os logs da aplicação Spring Boot no terminal.
4.  **Acessar a API:** A aplicação estará rodando e acessível em `http://localhost:8080`.

## Endpoints da API

O endereço base para todos os endpoints é `http://localhost:8080/usuarios`.

*   **Criar Usuário:**
    *   Método: `POST`
    *   URL: `/usuarios`
    *   Corpo (JSON): 
        ```json
        {
            "nome": "Nome Completo",
            "email": "email@exemplo.com",
            "login": "usuario_login",
            "senha": "senha_forte_123",
            "endereco": "Rua Exemplo, 123"
        }
        ```
    *   Resposta Sucesso: `201 Created` com os dados do usuário criado (sem senha).
*   **Listar Todos os Usuários:**
    *   Método: `GET`
    *   URL: `/usuarios`
    *   Resposta Sucesso: `200 OK` com uma lista de usuários (sem senha).
*   **Buscar Usuário por ID:**
    *   Método: `GET`
    *   URL: `/usuarios/{id}` (substitua `{id}` pelo ID numérico do usuário)
    *   Resposta Sucesso: `200 OK` com os dados do usuário (sem senha).
    *   Resposta Erro (Não encontrado): `404 Not Found`.
*   **Atualizar Usuário:**
    *   Método: `PUT`
    *   URL: `/usuarios/{id}`
    *   Corpo (JSON):
        ```json
        {
            "nome": "Novo Nome",
            "email": "novo_email@exemplo.com",
            "endereco": "Nova Rua, 456"
        }
        ```
    *   Resposta Sucesso: `200 OK` com os dados atualizados do usuário.
    *   Resposta Erro (Não encontrado): `404 Not Found`.
*   **Deletar Usuário:**
    *   Método: `DELETE`
    *   URL: `/usuarios/{id}`
    *   Resposta Sucesso: `204 No Content`.
    *   Resposta Erro (Não encontrado): `404 Not Found`.
*   **Trocar Senha:**
    *   Método: `POST`
    *   URL: `/usuarios/{id}/trocar-senha`
    *   Corpo (JSON):
        ```json
        {
            "novaSenha": "outra_senha_segura_456"
        }
        ```
    *   Resposta Sucesso: `200 OK`.
    *   Resposta Erro (Não encontrado): `404 Not Found`.
*   **Validar Login:**
    *   Método: `POST`
    *   URL: `/usuarios/login`
    *   Corpo (JSON):
        ```json
        {
            "login": "usuario_login",
            "senha": "senha_correta"
        }
        ```
    *   Resposta Sucesso: `200 OK` com a mensagem "Login bem-sucedido.".
    *   Resposta Erro (Credenciais inválidas): `401 Unauthorized` com a mensagem "Login ou senha inválidos.".

## Testando a API

Recomenda-se o uso de ferramentas como Postman ou Insomnia para testar os endpoints da API.

https://1drv.ms/u/c/cd3d5ca97011a997/EbmnInT95wRJuF6-nZzpB7UBPU1bO-e_B63eicJYx7NbeA?e=Q8q2JV

