package ru.academits.gerasimenko.phonebookhibernate.exception;

public class ExistingContactNumberException extends ContactProcessingException {
    public ExistingContactNumberException(String message) {
        super(message);
    }
}