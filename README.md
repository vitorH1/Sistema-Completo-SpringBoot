# 📌 Meu Projeto Spring Boot

Este é um sistema desenvolvido com **Spring Boot**, incluindo autenticação de usuários, controle de permissões e integração com banco de dados.

![Java](https://img.shields.io/badge/Java-17-blue)  
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-2.7.5-green)  
![Maven](https://img.shields.io/badge/Maven-3.8.6-orange)  
![PostgreSQL](https://img.shields.io/badge/PostgreSQL-14-blue)  
![License](https://img.shields.io/badge/Licença-MIT-brightgreen)  

## 🚀 Tecnologias Utilizadas

- [Java 17](https://www.oracle.com/java/)
- [Spring Boot](https://spring.io/projects/spring-boot)
- [Spring Security](https://spring.io/projects/spring-security)
- [Hibernate](https://hibernate.org/)
- [PostgreSQL](https://www.postgresql.org/)
- [Maven](https://maven.apache.org/)

## 📂 Estrutura do Projeto

```
/meu-projeto
│── src/
│   ├── main/
│   │   ├── java/com/example/demo
│   │   ├── resources/
│   ├── test/
│── pom.xml
│── README.md
│── application.properties
```

## ⚙️ Requisitos

Antes de rodar o projeto, certifique-se de ter instalado:

- Java 17+
- Maven 3.8+
- Banco de Dados (PostgreSQL/MySQL)
- IDE: IntelliJ IDEA, VS Code ou Eclipse
- Postman (para testar APIs)

## 🛠️ Configuração e Execução

1. Clone o repositório:
   ```sh
   git clone [https://github.com/vitorH1/Sistema-Completo-SpringBoot]
   ```

2. Entre no diretório do projeto:
   ```sh
   cd NOME_DO_REPO
   ```

3. Configure o banco de dados no `application.properties` ou `application.yml`.

4. Execute o projeto com:
   ```sh
   mvn spring-boot:run
   ```


## 🔧 Endpoints da API

### 🔑 Autenticação
- `POST /auth/login` → Autentica usuário (envia `{ "email": "user@email.com", "senha": "adm" }`)

### 👤 Usuários
- `GET /usuarios` → Lista todos os usuários  
- `GET /usuarios/{id}` → Busca um usuário pelo ID  
- `POST /usuarios` → Cria um novo usuário  
- `PUT /usuarios/{id}` → Atualiza um usuário  
- `DELETE /usuarios/{id}` → Deleta um usuário  

## 👤 Usuários e Permissões

- **Administrador**: Acesso total ao sistema.
- **Usuário Comum**: Acesso limitado.


## 📌 Histórico de Versões

### [1.0.0] - 2025-02-25
- 🚀 Primeira versão do sistema  
- ✅ Implementado login e autenticação  
- ✅ CRUD de usuários  

## 📄 Licença

Este projeto está sob a licença MIT - veja o arquivo [LICENSE](LICENSE) para mais detalhes.
