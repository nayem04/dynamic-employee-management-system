package com.dynamicemployeemanagementsystem.common.exceptions;

public class InvalidCredentialException extends RuntimeException {
    public InvalidCredentialException() {
    }

    public InvalidCredentialException(String msg) {
        super(msg);
    }
}
