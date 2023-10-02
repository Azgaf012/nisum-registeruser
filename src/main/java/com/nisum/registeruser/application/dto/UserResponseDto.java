package com.nisum.registeruser.application.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Builder
@Data
public class UserResponseDto {
    String id;
    LocalDate created;
    LocalDate modified;
    LocalDate last_login;
    String token;
    Boolean isActive;
}
