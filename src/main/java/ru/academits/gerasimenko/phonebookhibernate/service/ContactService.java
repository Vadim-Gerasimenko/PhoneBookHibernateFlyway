package ru.academits.gerasimenko.phonebookhibernate.service;

import ru.academits.gerasimenko.phonebookhibernate.entity.Contact;

import java.util.List;

public interface ContactService {
    List<Contact> getContacts(String term);

    Contact getContact(int id);

    void createContact(Contact contact);

    void updateContact(Contact contact);

    void deleteContact(int id);
}