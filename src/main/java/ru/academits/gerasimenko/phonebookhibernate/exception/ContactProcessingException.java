package ru.academits.gerasimenko.phonebookhibernate.exception;

public class ContactProcessingException extends RuntimeException {
  public ContactProcessingException(String message) {
    super(message);
  }
}