package com.nisum.registeruser.infrastructure.controller;

import com.nisum.registeruser.application.dto.UserRequestDto;
import com.nisum.registeruser.application.dto.UserResponseDto;
import com.nisum.registeruser.application.usecases.CreateUserUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
@RequiredArgsConstructor
public class UserController {

    private final CreateUserUseCase createUserUseCase;

    @PostMapping
    @RequestMapping("/register")
    public ResponseEntity<UserResponseDto> registerUser(@RequestBody UserRequestDto userRequestDto){
        UserResponseDto userResponseDto = createUserUseCase.execute(userRequestDto);

        return ResponseEntity.ok(userResponseDto);
    }

}
