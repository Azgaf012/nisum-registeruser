package com.nisum.registeruser.application.usecases;

import com.nisum.registeruser.application.dto.UserRequestDto;
import com.nisum.registeruser.application.dto.UserResponseDto;
import com.nisum.registeruser.application.mappers.UserMapper;
import com.nisum.registeruser.domain.entities.User;
import com.nisum.registeruser.domain.ports.UserRepository;
import com.nisum.registeruser.infrastructure.service.JwtService;
import jakarta.transaction.Transactional;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
public class CreateUserUseCase {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    private final UserDetailsService userDetailsService;

    private final JwtService jwtService;


    public CreateUserUseCase(UserRepository userRepository, UserMapper userMapper, UserDetailsService userDetailsService, JwtService jwtService) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.userDetailsService = userDetailsService;
        this.jwtService = jwtService;
    }

    @Transactional
    public UserResponseDto execute(UserRequestDto userRequestDto) {
        User user = userMapper.convertDtoToEntity(userRequestDto);
        User savedUser = userRepository.save(user);
        UserDetails userDetails = userDetailsService.loadUserByUsername(savedUser.getEmail());
        String jwt = jwtService.generateToken(userDetails);
        UserResponseDto userResponseDto = userMapper.convertEntityToDto(savedUser);
        userResponseDto.setToken(jwt);
        return userResponseDto;
    }




}
