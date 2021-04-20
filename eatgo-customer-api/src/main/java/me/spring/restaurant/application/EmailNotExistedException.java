package me.spring.restaurant.application;

public class EmailNotExistedException extends RuntimeException {

    EmailNotExistedException(String email) {
        super("Email is not registered: " + email);
    }
}
