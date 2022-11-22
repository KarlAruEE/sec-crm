package com.karlaru.seccrm;

import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping(path = "/api/contacts")
public class ContactController {

    private ContactRepository contactRepository;

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

    @GetMapping(produces = "application/json")
    public Iterable<Contact> getContacts(@RequestParam(value = "s", required = false) String search){
        if (search == null || search.isEmpty()) {
            return contactRepository.findAll();
        }
        else {
            return contactRepository.findByAnything(search);
        }
    }

    @PostMapping(consumes = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public Contact postContact(@RequestBody Contact contact){
        return contactRepository.save(contact);
    }
}
