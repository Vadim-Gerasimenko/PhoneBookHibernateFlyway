package ru.academits.gerasimenko.phonebookhibernate.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.academits.gerasimenko.phonebookhibernate.dto.Contact;
import ru.academits.gerasimenko.phonebookhibernate.repository.PhoneBookRepository;
import ru.academits.gerasimenko.phonebookhibernate.utilities.ContactProcessor;

import java.util.List;

@Service
@Slf4j
public class PhoneBookServiceImpl implements PhoneBookService {
    private final PhoneBookRepository phoneBookRepository;

    public PhoneBookServiceImpl(PhoneBookRepository phoneBookRepository) {
        this.phoneBookRepository = phoneBookRepository;
    }

    @Override
    public List<Contact> getContacts(String term) {
        log.info("A contact list containing the term \"{}\" was returned.", term);
        return phoneBookRepository.getContacts(term);
    }

    @Override
    public void addContact(Contact contact) {
        Contact newContact = ContactProcessor.processContact(contact);
        log.info("Trying to add new contact");

        ContactProcessor.checkContactForCorrectness(newContact);
        ContactProcessor.checkContactForNotExisting(phoneBookRepository, newContact);

        phoneBookRepository.addContact(newContact);
        log.info("New contact added successfully");
    }

    @Override
    public void updateContact(Contact contact) {
        Contact newContact = ContactProcessor.processContact(contact);
        log.info("Trying to update contact with id = {}", newContact.getId());

        ContactProcessor.checkContactForCorrectness(newContact);
        ContactProcessor.checkForExistingContactById(phoneBookRepository, newContact.getId());
        ContactProcessor.checkContactForNotExistingOrSame(phoneBookRepository, newContact);

        phoneBookRepository.updateContact(contact);
        log.info("Contact with id = {} updated successfully", newContact.getId());
    }

    @Override
    public void deleteContact(int id) {
        log.info("Trying to delete contact with id = {}", id);

        ContactProcessor.checkForExistingContactById(phoneBookRepository, id);

        phoneBookRepository.deleteContact(id);
        log.info("Contact with id = {} deleted successfully", id);
    }
}