package ru.academits.gerasimenko.phonebookhibernate.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.academits.gerasimenko.phonebookhibernate.dao.ContactRepository;
import ru.academits.gerasimenko.phonebookhibernate.entity.Contact;
import ru.academits.gerasimenko.phonebookhibernate.utilities.ContactProcessor;

import java.util.List;

@Service
@Slf4j
public class ContactServiceImpl implements ContactService {
    private final ContactRepository contactRepository;

    public ContactServiceImpl(ContactRepository contactRepository) {
        this.contactRepository = contactRepository;
    }

    @Override
    public List<Contact> getContacts(String term) {
        String processedTerm = term;

        if (processedTerm == null || processedTerm.isEmpty()) {
            return contactRepository.findAll();
        }

        processedTerm = processedTerm.trim().toLowerCase();

        log.info("A contact list containing the term \"{}\" was returned.", processedTerm);
        return contactRepository.getByNameContainsIgnoreCaseOrSurnameContainsIgnoreCaseOrPhoneContainsIgnoreCase(processedTerm, processedTerm, processedTerm);
    }

    @Override
    public Contact getContact(int id) {
        log.info("Trying to get contact with id = {}", id);
        Contact contact = ContactProcessor.checkContactForExistingById(contactRepository, id);

        log.info("Contact with id = {} got successfully", id);
        return contact;
    }

    @Override
    public void createContact(Contact contact) {
        Contact processedContact = ContactProcessor.processContact(contact);
        processedContact.setId(null);

        log.info("Trying to add new contact");

        ContactProcessor.checkContactForCorrectness(processedContact);
        ContactProcessor.checkContactForNotExisting(contactRepository, processedContact);

        contactRepository.save(processedContact);
        log.info("New contact added successfully");
    }

    @Override
    public void updateContact(Contact contact) {
        Contact processedContact = ContactProcessor.processContact(contact);
        log.info("Trying to update contact with id = {}", processedContact.getId());

        ContactProcessor.checkContactForCorrectness(processedContact);
        ContactProcessor.checkContactForExistingById(contactRepository, processedContact.getId());
        ContactProcessor.checkContactForNotExistingOrSame(contactRepository, processedContact);

        contactRepository.save(processedContact);
        log.info("Contact with id = {} updated successfully", processedContact.getId());
    }

    @Override
    public void deleteContact(int id) {
        log.info("Trying to delete contact with id = {}", id);
        ContactProcessor.checkContactForExistingById(contactRepository, id);

        contactRepository.deleteById(id);
        log.info("Contact with id = {} deleted successfully", id);
    }
}