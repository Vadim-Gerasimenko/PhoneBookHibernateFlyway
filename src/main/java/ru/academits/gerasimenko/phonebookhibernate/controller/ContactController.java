package ru.academits.gerasimenko.phonebookhibernate.controller;

import org.springframework.web.bind.annotation.*;
import ru.academits.gerasimenko.phonebookhibernate.converter.ContactDtoToContactConverter;
import ru.academits.gerasimenko.phonebookhibernate.converter.ContactToContactDtoConverter;
import ru.academits.gerasimenko.phonebookhibernate.dto.ContactDto;
import ru.academits.gerasimenko.phonebookhibernate.dto.Response;
import ru.academits.gerasimenko.phonebookhibernate.service.ContactService;

import java.util.List;

@RestController
@RequestMapping("/api/contact")
public class ContactController {
    private final ContactService contactService;
    private final ContactDtoToContactConverter contactDtoToContactConverter;
    private final ContactToContactDtoConverter contactToContactDtoConverter;

    public ContactController(ContactService contactService,
                             ContactDtoToContactConverter contactDtoToContactConverter,
                             ContactToContactDtoConverter contactToContactDtoConverter) {
        this.contactService = contactService;
        this.contactDtoToContactConverter = contactDtoToContactConverter;
        this.contactToContactDtoConverter = contactToContactDtoConverter;
    }

    @GetMapping
    public List<ContactDto> getContacts(@RequestParam(required = false) String term) {
        return contactToContactDtoConverter.convert(contactService.getContacts(term));
    }

    @GetMapping("/{contactId}")
    public ContactDto getContact(@PathVariable("contactId") Integer id) {
        return contactToContactDtoConverter.convert(contactService.getContact(id));
    }

    @PostMapping
    public Response createContact(@RequestBody ContactDto contactDto) {
        contactService.createContact(contactDtoToContactConverter.convert(contactDto));
        return new Response(true, "Contact added successfully");
    }

    @PutMapping("/{contactId}")
    public Response updateContact(@PathVariable("contactId") int id, @RequestBody ContactDto contactDto) {
        contactDto.setId(id);
        contactService.updateContact(contactDtoToContactConverter.convert(contactDto));
        return new Response(true, "Contact updated successfully");
    }

    @DeleteMapping("/{contactId}")
    public Response deleteContact(@PathVariable("contactId") int id) {
        contactService.deleteContact(id);
        return new Response(true, "Contact deleted successfully");
    }
}