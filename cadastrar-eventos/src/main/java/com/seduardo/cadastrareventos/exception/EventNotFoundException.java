package com.seduardo.cadastrareventos.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class EventNotFoundException extends Exception{
    public EventNotFoundException(Long id){
        super("Event not found with ID: " + id);
    }
}
