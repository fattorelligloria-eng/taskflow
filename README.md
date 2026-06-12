TaskFlow

Basicamente é um gerenciador de tarefas em CLI que criei em Java. A ideia é organizar e priorizar tarefas de forma automática. Tá bem no começo ainda, mas já tá funcionando com banco de dados e tudo.

 Como funciona?

Você cria tarefas, atribui pra usuários, e o sistema faz o gerenciamento no banco pra você. Nada muito complexo, mas é um bom exercício de Java com orientação a objetos mesmo.

Tecnologias

Java, Maven, SQLite, Eclipse JEE. Bem direto.


 Pra rodar


   git bash
git clone https://github.com/fattorigloria-eng/taskflow.git
cd taskflow
mvn clean compile
mvn exec:java -Dexec.mainClass="com.taskflow.Main"


Ou só abre no Eclipse e roda direto.

 Estrutura

src/
├── java/com/taskflow/
│   ├── Main.java
│   ├── Task.java
│   ├── User.java
│   ├── TaskManager.java
│   └── DatabaseManager.java
```

Cada classe tem sua responsabilidade. Task cuida da tarefas, User cuida dos usuários, TaskManager orquestra tudo, DatabaseManager faz a conexão com o SQLite.

 

Dúvidas ou sugestões? Me chama:

 fattorelligloria@gmail.com  
https://www.linkedin.com/in/glóriafattorellicarrion