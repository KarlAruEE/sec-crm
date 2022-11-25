package com.karlaru.seccrm;

import com.karlaru.seccrm.contact.Contact;
import com.karlaru.seccrm.contact.ContactRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class SecCrmApplication {

	public static void main(String[] args) {
		SpringApplication.run(SecCrmApplication.class, args);
	}

	@Bean
	public CommandLineRunner dataLoader(ContactRepository contactRepository){
		return args -> {
			contactRepository.deleteAll();
			contactRepository.saveAll(List.of(
					new Contact("Karl Aru", "karu", "551234"),
					new Contact("Mike Lukk", "mukk", "533245"),
					new Contact("Markus Mets", "metsakutsu", "1123567")));
		};
	}
}
