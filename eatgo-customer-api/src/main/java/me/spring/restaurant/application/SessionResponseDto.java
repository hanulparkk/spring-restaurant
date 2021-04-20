package me.spring.restaurant.application;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SessionResponseDto {

    private String accessToken;
}
