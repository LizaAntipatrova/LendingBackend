package com.lending.lendingbackend.exceptions.data;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException() {
        super("A user with that username was not found.");
    }
}
