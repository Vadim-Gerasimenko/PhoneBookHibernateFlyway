package ru.academits.gerasimenko.phonebookhibernate.utilities;

import ru.academits.gerasimenko.phonebookhibernate.dto.Contact;
import ru.academits.gerasimenko.phonebookhibernate.exception.ContactNotFoundException;
import ru.academits.gerasimenko.phonebookhibernate.exception.ContactProcessingException;
import ru.academits.gerasimenko.phonebookhibernate.exception.ExistingContactNumberException;
import ru.academits.gerasimenko.phonebookhibernate.exception.IncorrectContactDataException;
import ru.academits.gerasimenko.phonebookhibernate.repository.PhoneBookRepository;

public class ContactProcessor {
    private ContactProcessor() {
    }

    public static Contact processContact(Contact contact) {
        return new Contact(
                contact.getId(),
                TextUtilities.getEscapedHtmlText(contact.getName()),
                TextUtilities.getEscapedHtmlText(contact.getSurname()),
                TextUtilities.getEscapedHtmlText(contact.getPhone())
        );
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

    public static void checkContactForNotExisting(PhoneBookRepository phoneBook, Contact contact) {
        if (phoneBook.getContacts("").stream()
                .anyMatch(c -> c.getPhone().equals(contact.getPhone()))
        ) {
            throw new ExistingContactNumberException("Phone number '" + contact.getPhone() + "' already exists");
        }
    }

    public static void checkContactForNotExistingOrSame(PhoneBookRepository phoneBook, Contact contact) {
        if (phoneBook.getContacts("").stream()
                .anyMatch(c -> c.getId() != contact.getId() && c.getPhone().equals(contact.getPhone()))
        ) {
            throw new ExistingContactNumberException("Phone number '" + contact.getPhone() + "' already exists");
        }
    }

    public static void checkForExistingContactById(PhoneBookRepository phoneBook, int id) {
        if (phoneBook.getContacts("").stream().noneMatch(c -> c.getId() == id)
        ) {
            throw new ContactNotFoundException("Contact with id = " + id + " not found");
        }
    }
}