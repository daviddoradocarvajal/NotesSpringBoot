package com.indra.notesspringboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.indra.notesspringboot.*")
public class NotesSpringBootApplication {

	public static void main(String[] args) {
		SpringApplication.run(NotesSpringBootApplication.class, args);
	}

}
