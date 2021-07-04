package com.lamdbasys.jwt.spring.security.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class InvalidUsernameOrPasswordException extends Exception {

    public InvalidUsernameOrPasswordException(String message){
        super(message);
    }

}
