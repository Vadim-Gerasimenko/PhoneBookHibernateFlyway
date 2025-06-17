package ru.academits.gerasimenko.phonebookhibernate.utilities;

import ru.academits.gerasimenko.phonebookhibernate.dao.ContactRepository;
import ru.academits.gerasimenko.phonebookhibernate.entity.Contact;
import ru.academits.gerasimenko.phonebookhibernate.exception.ContactNotFoundException;
import ru.academits.gerasimenko.phonebookhibernate.exception.ContactProcessingException;
import ru.academits.gerasimenko.phonebookhibernate.exception.ExistingContactNumberException;
import ru.academits.gerasimenko.phonebookhibernate.exception.IncorrectContactDataException;

import java.util.Objects;
import java.util.Optional;

public class ContactProcessor {
    private ContactProcessor() {
    }

    public static Contact processContact(Contact contact) {
        Contact processedContact = new Contact();

        processedContact.setId(contact.getId());
        processedContact.setName(TextUtilities.getEscapedHtmlText(contact.getName()));
        processedContact.setSurname(TextUtilities.getEscapedHtmlText(contact.getSurname()));
        processedContact.setPhone(TextUtilities.getEscapedHtmlText(contact.getPhone()));

        return processedContact;
    }

    public static void checkContactForCorrectness(Contact contact) {
        if (contact == null) {
            throw new ContactProcessingException("Contact is null");
        }

        if (TextUtilities.isNullOrEmpty(contact.getName())) {
            throw new IncorrectContactDataException("Contact name is incorrect");
        }

        if (TextUtilities.isNullOrEmpty(contact.getSurname())) {
            throw new IncorrectContactDataException("Contact surname is incorrect");
        }

        if (TextUtilities.isNullOrEmpty(contact.getPhone())) {
            throw new IncorrectContactDataException("Contact phone is incorrect");
        }
    }

    public static void checkContactForNotExisting(ContactRepository contactRepository, Contact contact) {
        if (contactRepository.getByPhone(contact.getPhone()) != null) {
            throw new ExistingContactNumberException("Phone number '" + contact.getPhone() + "' already exists");
        }
    }

    public static void checkContactForNotExistingOrSame(ContactRepository contactRepository, Contact contact) {
        Contact existingContact = contactRepository.getByPhone(contact.getPhone());

        if (existingContact != null && !Objects.equals(existingContact.getId(), contact.getId())) {
            throw new ExistingContactNumberException("Phone number '" + contact.getPhone() + "' already exists");
        }
    }

    public static Contact checkContactForExistingById(ContactRepository contactRepository, int id) {
        Optional<Contact> optionalContact = contactRepository.findById(id);

        if (optionalContact.isEmpty()) {
            throw new ContactNotFoundException("Contact with id = " + id + " not found");
        }

        return optionalContact.get();
    }
}