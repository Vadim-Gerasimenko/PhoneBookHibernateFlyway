package ru.academits.gerasimenko.phonebookhibernate.repository;

import ru.academits.gerasimenko.phonebookhibernate.dto.Contact;

import java.util.List;

public interface PhoneBookRepository {
    List<Contact> getContacts(String term);

    void addContact(Contact contact);

    void updateContact(Contact contact);

    void deleteContact(int id);
}