package ru.igsltk.chats;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("ru.igsltk.chats")
public class ChatsApplication {

	public static void main(String[] args) {
		SpringApplication.run(ChatsApplication.class, args);
	}

}
