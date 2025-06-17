package ru.academits.gerasimenko.phonebookhibernate.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.academits.gerasimenko.phonebookhibernate.entity.Contact;

import java.util.List;

public interface ContactRepository extends JpaRepository<Contact, Integer> {
    Contact getByPhone(String phone);
    List<Contact> getByNameContainsIgnoreCaseOrSurnameContainsIgnoreCaseOrPhoneContainsIgnoreCase(String name, String surname, String phone);
}