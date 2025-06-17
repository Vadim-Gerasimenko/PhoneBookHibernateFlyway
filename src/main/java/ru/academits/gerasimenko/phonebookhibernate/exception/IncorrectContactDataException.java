package ru.academits.gerasimenko.phonebookhibernate.exception;

public class IncorrectContactDataException extends ContactProcessingException {
    public IncorrectContactDataException(String message) {
        super(message);
    }
}