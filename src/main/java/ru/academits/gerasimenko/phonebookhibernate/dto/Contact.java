package ru.academits.gerasimenko.phonebookhibernate.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Contact {
    private int id;
    private String name;
    private String surname;
    private String phone;

    public Contact(Contact contact) {
        this.id = contact.getId();
        this.name = contact.getName();
        this.surname = contact.getSurname();
        this.phone = contact.getPhone();
    }
}