package ru.academits.gerasimenko.phonebookhibernate.converter;

import org.springframework.stereotype.Service;
import ru.academits.gerasimenko.phonebookhibernate.dto.ContactDto;
import ru.academits.gerasimenko.phonebookhibernate.entity.Contact;

@Service
public class ContactToContactDtoConverter implements Converter<Contact, ContactDto> {
    @Override
    public ContactDto convert(Contact contact) {
        ContactDto contactDto = new ContactDto();

        contactDto.setId(contact.getId());
        contactDto.setName(contact.getName());
        contactDto.setSurname(contact.getSurname());
        contactDto.setPhone(contact.getPhone());

        return contactDto;
    }
}