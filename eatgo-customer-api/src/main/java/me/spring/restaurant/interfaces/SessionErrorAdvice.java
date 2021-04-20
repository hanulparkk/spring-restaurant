package me.spring.restaurant.interfaces;

import me.spring.restaurant.application.EmailNotExistedException;
import me.spring.restaurant.application.PasswordWrongException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class SessionErrorAdvice {

    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(EmailNotExistedException.class)
    public String handleEmailNotExisted() {
        return "{}";
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(PasswordWrongException.class)
    public String handlePasswordWrong() {
        return "{}";
    }
}
