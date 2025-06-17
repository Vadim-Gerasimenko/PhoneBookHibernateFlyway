package ru.academits.gerasimenko.phonebookhibernate.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ContactDto {
    private Integer id;
    private String name;
    private String surname;
    private String phone;
}