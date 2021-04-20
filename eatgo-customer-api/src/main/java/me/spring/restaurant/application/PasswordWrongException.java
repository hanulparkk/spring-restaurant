package me.spring.restaurant.application;

public class PasswordWrongException extends RuntimeException {

    PasswordWrongException() {
        super("Password is wrong");
    }
}
