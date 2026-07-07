# TaskFlow

Gerenciador de tarefas em linha de comando (CLI) desenvolvido em Java, com persistência em SQLite. O sistema organiza e prioriza tarefas automaticamente, associando cada uma a um usuário.

## Funcionalidades

- Criação e gerenciamento de tarefas via CLI
- Atribuição de tarefas a usuários
- Priorização automática com base nos padrões de uso
- Persistência de dados com SQLite

## Tecnologias

- Java
- Maven
- SQLite
- Eclipse JEE

## Como executar

```bash
git clone https://github.com/fattorelligloria-eng/taskflow.git
cd taskflow
mvn clean compile
mvn exec:java -Dexec.mainClass="com.taskflow.Main"
```

Ou importe o projeto no Eclipse e execute a classe `Main`.

## Estrutura do projeto

Cada classe tem uma responsabilidade única:

- `Task` — representação e regras das tarefas
- `User` — representação dos usuários
- `TaskManager` — orquestra a lógica de negócio
- `DatabaseManager` — conexão e operações com o SQLite

## 🇬🇧 About (English)

TaskFlow is a command-line task manager built with Java, Maven and SQLite. It organizes and prioritizes tasks automatically, assigning them to users. Personal project built during my Software Engineering degree to practice OOP, data persistence and clean code.

## Contato

- LinkedIn: [linkedin.com/in/gloriafattorelli](https://www.linkedin.com/in/gloriafattorelli)
- E-mail: fattorelligloria@gmail.com
