package com.karlaru.seccrm.contact;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

        String friendlySearch = friendlyString(search);

        Iterable<Contact> result = contactRepository.findAnyLikeSearch(friendlySearch);
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

    private static String friendlyString(String value){
        Pattern pattern = Pattern.compile("[<>$@\"\']");
        Matcher matcher = pattern.matcher(value);
        return matcher.replaceAll("");
    }
}
