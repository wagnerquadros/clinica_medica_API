# Clinica Medica API

![Java](https://img.shields.io/badge/Java-17-007396?logo=openjdk&logoColor=white)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-4.0.6-6DB33F?logo=springboot&logoColor=white)
![Spring Security](https://img.shields.io/badge/Spring%20Security-JWT-6DB33F?logo=springsecurity&logoColor=white)
![Spring Data JPA](https://img.shields.io/badge/Spring%20Data%20JPA-Hibernate-59666C?logo=hibernate&logoColor=white)
![MySQL](https://img.shields.io/badge/MySQL-8-4479A1?logo=mysql&logoColor=white)
![Flyway](https://img.shields.io/badge/Flyway-Migrations-CC0200?logo=flyway&logoColor=white)
![Maven](https://img.shields.io/badge/Maven-Wrapper-C71A36?logo=apachemaven&logoColor=white)
![OpenAPI](https://img.shields.io/badge/OpenAPI-Swagger-85EA2D?logo=swagger&logoColor=black)

API REST para gerenciamento de uma clinica medica. O projeto permite cadastrar, listar, atualizar e excluir logicamente medicos e pacientes, alem de agendar e cancelar consultas. A API usa autenticacao stateless com JWT e documentacao via Swagger/OpenAPI.

## Tecnologias

- Java 17
- Spring Boot 4.0.6
- Spring Web MVC
- Spring Security com JWT
- Spring Data JPA e Hibernate
- Bean Validation
- MySQL
- Flyway
- Lombok
- Springdoc OpenAPI / Swagger UI
- JUnit, MockMvc e Mockito para testes

## Funcionalidades

- Autenticacao por login e senha.
- Emissao de token JWT.
- CRUD de medicos.
- CRUD de pacientes.
- Agendamento de consultas com validacoes de regra de negocio.
- Cancelamento de consultas com motivo.
- Soft delete para medicos e pacientes pelo campo `ativo`.
- Migrations versionadas com Flyway.

## Requisitos

- Java 17 ou superior.
- MySQL em execucao.
- Maven Wrapper incluido no projeto.

## Configuracao

O arquivo principal usa variaveis de ambiente para conexao com o banco:

```properties
spring.datasource.url=${DB_URL}
spring.datasource.username=${DB_USERNAME}
spring.datasource.password=${DB_PASSWORD}
```

Exemplo no PowerShell:

```powershell
$env:DB_URL="jdbc:mysql://localhost:3306/clinica_medica_db?createDatabaseIfNotExist=true"
$env:DB_USERNAME="usuario"
$env:DB_PASSWORD="senha"
```

O profile `test` usa o banco `clinica_medica_db_test`:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/clinica_medica_db_test
spring.datasource.username=${DB_USERNAME:usuario}
spring.datasource.password=${DB_PASSWORD:senha}
```

## Executando

```powershell
.\mvnw.cmd spring-boot:run
```

Por padrao, a API sobe em:

```text
http://localhost:8080
```

## Testes

```powershell
.\mvnw.cmd test
```

Para uma execucao limpa:

```powershell
.\mvnw.cmd clean test
```

## Documentacao Swagger

Com a aplicacao rodando:

- Swagger UI: `http://localhost:8080/swagger-ui.html`
- OpenAPI JSON: `http://localhost:8080/v3/api-docs`

## Autenticacao

Somente `POST /login` e os endpoints do Swagger sao publicos. Os demais endpoints exigem:

```http
Authorization: Bearer <token>
```

### Login

```http
POST /login
Content-Type: application/json
```

Request:

```json
{
  "login": "admin@clinicamedica.com",
  "senha": "123456"
}
```

Response `200 OK`:

```json
{
  "token": "eyJhbGciOiJIUzI1NiJ9..."
}
```

## Endpoints

### Medicos

| Metodo | Endpoint | Descricao |
| --- | --- | --- |
| `POST` | `/medicos` | Cadastra um medico |
| `GET` | `/medicos` | Lista medicos ativos com paginacao |
| `GET` | `/medicos/{id}` | Detalha um medico |
| `PUT` | `/medicos` | Atualiza dados de um medico |
| `DELETE` | `/medicos/{id}` | Inativa um medico |

Especialidades aceitas:

```text
ORTOPEDIA, CARDIOLOGIA, GINECOLOGIA, DERMATOLOGIA
```

Cadastro:

```bash
curl -X POST http://localhost:8080/medicos \
  -H "Authorization: Bearer <token>" \
  -H "Content-Type: application/json" \
  -d '{
    "nome": "Dra. Ana Silva",
    "email": "ana.silva@clinicamedica.com",
    "telefone": "11999999999",
    "crm": "123456",
    "especialidade": "CARDIOLOGIA",
    "endereco": {
      "logradouro": "Rua das Flores",
      "bairro": "Centro",
      "cep": "01001000",
      "cidade": "Sao Paulo",
      "uf": "SP",
      "complemento": "Sala 12",
      "numero": "100"
    }
  }'
```

Response `201 Created`:

```json
{
  "id": 1,
  "nome": "Dra. Ana Silva",
  "email": "ana.silva@clinicamedica.com",
  "crm": "123456",
  "telefone": "11999999999",
  "especialidade": "CARDIOLOGIA",
  "endereco": {
    "logradouro": "Rua das Flores",
    "bairro": "Centro",
    "cep": "01001000",
    "cidade": "Sao Paulo",
    "uf": "SP",
    "complemento": "Sala 12",
    "numero": "100"
  }
}
```

Listagem:

```bash
curl -X GET "http://localhost:8080/medicos?page=0&size=10&sort=nome" \
  -H "Authorization: Bearer <token>"
```

Atualizacao:

```bash
curl -X PUT http://localhost:8080/medicos \
  -H "Authorization: Bearer <token>" \
  -H "Content-Type: application/json" \
  -d '{
    "id": 1,
    "nome": "Dra. Ana Souza",
    "telefone": "11888888888",
    "endereco": {
      "logradouro": "Avenida Paulista",
      "bairro": "Bela Vista",
      "cep": "01311000",
      "cidade": "Sao Paulo",
      "uf": "SP",
      "complemento": "Conjunto 101",
      "numero": "1000"
    }
  }'
```

Exclusao logica:

```bash
curl -X DELETE http://localhost:8080/medicos/1 \
  -H "Authorization: Bearer <token>"
```

Response `204 No Content`.

### Pacientes

| Metodo | Endpoint | Descricao |
| --- | --- | --- |
| `POST` | `/pacientes` | Cadastra um paciente |
| `GET` | `/pacientes` | Lista pacientes ativos com paginacao |
| `GET` | `/pacientes/{id}` | Detalha um paciente |
| `PUT` | `/pacientes` | Atualiza dados de um paciente |
| `DELETE` | `/pacientes/{id}` | Inativa um paciente |

Cadastro:

```bash
curl -X POST http://localhost:8080/pacientes \
  -H "Authorization: Bearer <token>" \
  -H "Content-Type: application/json" \
  -d '{
    "nome": "Carlos Oliveira",
    "email": "carlos.oliveira@email.com",
    "telefone": "11977777777",
    "cpf": "12345678901",
    "endereco": {
      "logradouro": "Rua Augusta",
      "bairro": "Consolacao",
      "cep": "01305000",
      "cidade": "Sao Paulo",
      "uf": "SP",
      "complemento": "Apto 32",
      "numero": "500"
    }
  }'
```

Response `201 Created`:

```json
{
  "id": 1,
  "nome": "Carlos Oliveira",
  "email": "carlos.oliveira@email.com",
  "cpf": "12345678901",
  "telefone": "11977777777",
  "endereco": {
    "logradouro": "Rua Augusta",
    "bairro": "Consolacao",
    "cep": "01305000",
    "cidade": "Sao Paulo",
    "uf": "SP",
    "complemento": "Apto 32",
    "numero": "500"
  }
}
```

Listagem:

```bash
curl -X GET "http://localhost:8080/pacientes?page=0&size=10&sort=nome" \
  -H "Authorization: Bearer <token>"
```

Atualizacao:

```bash
curl -X PUT http://localhost:8080/pacientes \
  -H "Authorization: Bearer <token>" \
  -H "Content-Type: application/json" \
  -d '{
    "id": 1,
    "nome": "Carlos O. Santos",
    "telefone": "11966666666",
    "endereco": {
      "logradouro": "Rua Vergueiro",
      "bairro": "Liberdade",
      "cep": "01504000",
      "cidade": "Sao Paulo",
      "uf": "SP",
      "complemento": null,
      "numero": "200"
    }
  }'
```

Exclusao logica:

```bash
curl -X DELETE http://localhost:8080/pacientes/1 \
  -H "Authorization: Bearer <token>"
```

Response `204 No Content`.

### Consultas

| Metodo | Endpoint | Descricao |
| --- | --- | --- |
| `POST` | `/consultas` | Agenda uma consulta |
| `DELETE` | `/consultas` | Cancela uma consulta |

Regras principais de agendamento:

- A data deve ser futura.
- A clinica atende de segunda a sabado, das 07:00 as 19:00.
- A consulta precisa ser agendada com pelo menos 30 minutos de antecedencia.
- O paciente deve estar ativo.
- O medico informado, quando houver, deve estar ativo.
- O paciente nao pode ter outra consulta no mesmo dia.
- O medico nao pode ter outra consulta no mesmo horario.
- Quando `medicoId` nao for informado, `especialidade` e obrigatoria para escolha automatica de medico disponivel.

Agendamento com medico especifico:

```bash
curl -X POST http://localhost:8080/consultas \
  -H "Authorization: Bearer <token>" \
  -H "Content-Type: application/json" \
  -d '{
    "medicoId": 1,
    "pacienteId": 1,
    "data": "20/06/2026 10:00",
    "especialidade": "CARDIOLOGIA"
  }'
```

Agendamento com escolha automatica de medico:

```bash
curl -X POST http://localhost:8080/consultas \
  -H "Authorization: Bearer <token>" \
  -H "Content-Type: application/json" \
  -d '{
    "pacienteId": 1,
    "data": "20/06/2026 10:00",
    "especialidade": "CARDIOLOGIA"
  }'
```

Response `200 OK`:

```json
{
  "id": 1,
  "medicoId": 1,
  "pacienteId": 1,
  "data": "2026-06-20T10:00:00"
}
```

Motivos de cancelamento aceitos:

```text
PACIENTE_DESISTIU, MEDICO_CANCELOU, OUTROS
```

Cancelamento:

```bash
curl -X DELETE http://localhost:8080/consultas \
  -H "Authorization: Bearer <token>" \
  -H "Content-Type: application/json" \
  -d '{
    "idConsulta": 1,
    "motivo": "PACIENTE_DESISTIU"
  }'
```

Response `204 No Content`.

## Codigos de resposta

| Status | Quando acontece |
| --- | --- |
| `200 OK` | Consulta, listagem, atualizacao ou login realizado com sucesso |
| `201 Created` | Medico ou paciente cadastrado |
| `204 No Content` | Exclusao logica ou cancelamento realizado |
| `400 Bad Request` | Dados invalidos ou regra de negocio violada |
| `401 Unauthorized` | Credenciais invalidas ou token ausente/invalido |
| `403 Forbidden` | Acesso negado |
| `404 Not Found` | Entidade nao encontrada |
| `500 Internal Server Error` | Erro inesperado |

## Estrutura do projeto

```text
src/main/java/com/wagnerquadros/clinicamedica
├── controller        # Controllers REST
├── entity            # Entidades, enums e DTOs
├── infra             # Excecoes, seguranca e Swagger
├── repository        # Repositories Spring Data JPA
└── service           # Regras de consulta e validadores

src/main/resources/db/migration
└── V*.sql            # Migrations Flyway
```

## Observacoes

- A API usa soft delete em medicos e pacientes, mantendo os registros no banco com `ativo = false`.
- O banco e atualizado automaticamente pelo Flyway na inicializacao.
- Para criar usuarios de login, insira registros na tabela `usuarios` com senha criptografada em BCrypt.
