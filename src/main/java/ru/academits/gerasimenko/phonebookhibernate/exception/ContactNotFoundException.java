package ru.academits.gerasimenko.phonebookhibernate.exception;

public class ContactNotFoundException extends ContactProcessingException {
    public ContactNotFoundException(String message) {
        super(message);
    }
}