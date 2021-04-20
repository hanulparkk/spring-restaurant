package me.spring.restaurant.application;

import lombok.Data;

@Data
public class SessionRequestDto {

    private String email;

    private String password;
}
