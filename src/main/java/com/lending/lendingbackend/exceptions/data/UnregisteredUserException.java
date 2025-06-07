package com.lending.lendingbackend.exceptions.data;

public class UnregisteredUserException extends RuntimeException {
    public UnregisteredUserException() {
        super("A user with that login and password was not found.");
    }
}
