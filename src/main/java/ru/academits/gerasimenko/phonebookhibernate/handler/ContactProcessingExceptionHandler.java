package ru.academits.gerasimenko.phonebookhibernate.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import ru.academits.gerasimenko.phonebookhibernate.entity.Response;
import ru.academits.gerasimenko.phonebookhibernate.exception.ContactProcessingException;

@ControllerAdvice
@Slf4j
public class ContactProcessingExceptionHandler {
    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public Response catchContactProcessingException(ContactProcessingException e) {
        log.error("Exception caught: {}", e.getMessage());
        return new Response(false, e.getMessage());
    }
}