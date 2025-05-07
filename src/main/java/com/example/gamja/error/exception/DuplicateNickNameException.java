package com.example.gamja.error.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class DuplicateNickNameException extends Exception {
    public DuplicateNickNameException(String name) {
        super("This nickname is already in use : " + name);
    }
}
