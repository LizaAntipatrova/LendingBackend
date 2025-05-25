package com.lending.lendingbackend.auth.exceptions.auth;

public class InvalidCookieException extends RuntimeException {
    public InvalidCookieException() {
        super("Invalid Cookie");
    }
}
