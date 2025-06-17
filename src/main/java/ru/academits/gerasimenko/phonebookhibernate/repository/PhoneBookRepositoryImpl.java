package ru.academits.gerasimenko.phonebookhibernate.repository;

import org.springframework.stereotype.Repository;
import ru.academits.gerasimenko.phonebookhibernate.dto.Contact;
import ru.academits.gerasimenko.phonebookhibernate.exception.ContactNotFoundException;

import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Stream;

@Repository
public class PhoneBookRepositoryImpl implements PhoneBookRepository {
    private final List<Contact> contacts;
    private final AtomicInteger newContactId;

    public PhoneBookRepositoryImpl(List<Contact> contacts) {
        this.contacts = contacts;
        newContactId = new AtomicInteger(1);
    }

    @Override
    public List<Contact> getContacts(String term) {
        synchronized (contacts) {
            String containedTerm = Objects.requireNonNullElse(term, "").toLowerCase();
            Stream<Contact> contactsStream = contacts.stream();

            if (!containedTerm.isEmpty()) {
                contactsStream = contactsStream.filter(c -> c.getName().toLowerCase().contains(containedTerm)
                        || c.getSurname().toLowerCase().contains(containedTerm)
                        || c.getPhone().toLowerCase().contains(containedTerm)
                );
            }

            return contactsStream.map(Contact::new).toList();
        }
    }

    @Override
    public void addContact(Contact contact) {
        synchronized (contacts) {
            int id = newContactId.getAndIncrement();
            contacts.add(new Contact(
                    id,
                    contact.getName(),
                    contact.getSurname(),
                    contact.getPhone()
            ));
        }
    }

    @Override
    public void updateContact(Contact contact) {
        synchronized (contacts) {
            int updatedContactId = contact.getId();

            Contact updatedContact = contacts.stream()
                    .filter(c -> c.getId() == updatedContactId)
                    .findFirst()
                    .orElse(null);

            if (updatedContact == null) {
                throw new ContactNotFoundException("Contact with id = " + updatedContactId + " not found");
            }

            updatedContact.setName(contact.getName());
            updatedContact.setSurname(contact.getSurname());
            updatedContact.setPhone(contact.getPhone());
        }
    }

    @Override
    public void deleteContact(int id) {
        synchronized (contacts) {
            if (contacts.stream().noneMatch(c -> c.getId() == id)) {
                throw new ContactNotFoundException("Contact with id = " + id + " not found");
            }

            contacts.removeIf(c -> c.getId() == id);
        }
    }
}