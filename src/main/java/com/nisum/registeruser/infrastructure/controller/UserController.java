package com.nisum.registeruser.infrastructure.controller;

import com.nisum.registeruser.application.dto.UserRequestDto;
import com.nisum.registeruser.application.dto.UserResponseDto;
import com.nisum.registeruser.application.usecases.CreateUserUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private CreateUserUseCase createUserUseCase;

    @PostMapping
    public ResponseEntity<UserResponseDto> registerUser(@RequestBody UserRequestDto userRequestDto){
        UserResponseDto userResponseDto = createUserUseCase.execute(userRequestDto);
        return ResponseEntity.ok(userResponseDto);
    }

}
