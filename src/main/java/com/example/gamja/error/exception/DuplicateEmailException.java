package com.example.gamja.error.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class DuplicateEmailException extends Exception{
    public DuplicateEmailException(String email) {
        super("This email is already in use : " + email);
    }
}
