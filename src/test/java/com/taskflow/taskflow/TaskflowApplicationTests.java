package com.taskflow.taskflow;

import com.taskflow.taskflow.model.User;
import com.taskflow.taskflow.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class TaskflowApplicationTests {

	public static void main(String[] args) {
		SpringApplication.run(TaskflowApplication.class, args);
	}

	// CRIA UM USUARIO PADRAO NA PRIMEIRA VEZ
	@Bean
	public CommandLineRunner inicializar(UserRepository userRepository) {
		return args -> {
			if (userRepository.count() == 0) {
				User user = new User(
					"Gloria Fattorelli",
					"gloria@taskflow.com",
					"123.456.789-00",
					21,
					"taskflow123"
				);
				userRepository.save(user);
				System.out.println("Usuario padrao criado!");
				System.out.println("Email: gloria@taskflow.com");
				System.out.println("Senha: taskflow123");
			}
		};
	}
}