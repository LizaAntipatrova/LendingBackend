package com.lending.lendingbackend.exceptions.auth;

public class InvalidCookieException extends RuntimeException {
    public InvalidCookieException() {
        super("Invalid Cookie");
    }
}
