package ru.academits.gerasimenko.phonebookhibernate.converter;

import org.springframework.stereotype.Service;
import ru.academits.gerasimenko.phonebookhibernate.dto.ContactDto;
import ru.academits.gerasimenko.phonebookhibernate.entity.Contact;

@Service
public class ContactDtoToContactConverter implements Converter<ContactDto, Contact> {
    @Override
    public Contact convert(ContactDto contactDto) {
        Contact contact = new Contact();

        contact.setId(contactDto.getId());
        contact.setName(contactDto.getName());
        contact.setSurname(contactDto.getSurname());
        contact.setPhone(contactDto.getPhone());

        return contact;
    }
}