package ru.academits.gerasimenko.phonebookhibernate.controller;

import org.springframework.web.bind.annotation.*;
import ru.academits.gerasimenko.phonebookhibernate.dto.Contact;
import ru.academits.gerasimenko.phonebookhibernate.entity.Response;
import ru.academits.gerasimenko.phonebookhibernate.service.PhoneBookService;

import java.util.List;

@RestController
@RequestMapping("/api/contacts")
public class PhoneBookController {
    private final PhoneBookService phoneBookService;

    public PhoneBookController(PhoneBookService phoneBookService) {
        this.phoneBookService = phoneBookService;
    }

    @GetMapping
    public List<Contact> getContacts(@RequestParam(required = false) String term) {
        return phoneBookService.getContacts(term);
    }

    @PostMapping
    public Response addContact(@RequestBody Contact contact) {
        phoneBookService.addContact(contact);
        return new Response(true, "Contact added successfully");
    }

    @PutMapping("/{contactId}")
    public Response updateContact(@PathVariable("contactId") int id, @RequestBody Contact contact) {
        contact.setId(id);
        phoneBookService.updateContact(contact);
        return new Response(true, "Contact updated successfully");
    }

    @DeleteMapping("/{contactId}")
    public Response deleteContact(@PathVariable("contactId") int id) {
        phoneBookService.deleteContact(id);
        return new Response(true, "Contact deleted successfully");
    }
}