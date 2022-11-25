package com.karlaru.seccrm.contact;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping(path = "/api/v1/contacts")
public class ContactController {

    private ContactRepository contactRepository;

    @GetMapping(produces = "application/json")
    public Iterable<Contact> getContacts(){
        return contactRepository.findAll();
    }

    @GetMapping(path = "/search/{search}", produces = "application/json")
    public ResponseEntity<Iterable<Contact>> searchContacts(@PathVariable("search") String search){

        Iterable<Contact> result = contactRepository.findAnyLikeSearch(search);
        if (result.iterator().hasNext())
            return ResponseEntity.ok(result);
        else
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(List.of());
    }

    @PostMapping(consumes = "application/json", produces = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public Contact postContact(@RequestBody Contact contact){
        return contactRepository.save(contact);
    }
}
