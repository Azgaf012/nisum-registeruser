package com.nisum.registeruser.application.usecases;

import com.nisum.registeruser.application.dto.UserRequestDto;
import com.nisum.registeruser.application.dto.UserResponseDto;
import com.nisum.registeruser.application.mappers.UserMapper;
import com.nisum.registeruser.domain.entities.User;
import com.nisum.registeruser.domain.ports.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class CreateUserUseCase {

    private final UserRepository userRepository;
    private final UserMapper userMapper;


    public CreateUserUseCase(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;

        this.userMapper = userMapper;
    }

    @Transactional
    public UserResponseDto execute(UserRequestDto userRequestDto) {
        User user = userMapper.convertDtoToEntity(userRequestDto);
        User savedUser = userRepository.save(user);
        return userMapper.convertEntityToDto(savedUser);
    }

}
