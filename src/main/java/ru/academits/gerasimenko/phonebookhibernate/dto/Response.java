package ru.academits.gerasimenko.phonebookhibernate.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Response {
    private boolean success;
    private String message;
}