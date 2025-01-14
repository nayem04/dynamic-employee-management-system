package com.dynamicemployeemanagementsystem.common.exceptions;

public class UserAlreadyExistsException extends AlreadyExistsException {
    public UserAlreadyExistsException() {
    }

    public UserAlreadyExistsException(String message) {
        super(message);
    }
}
