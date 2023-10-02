package com.nisum.registeruser.application.usecases;

import com.nisum.registeruser.application.dto.UserRequestDto;
import com.nisum.registeruser.application.dto.UserResponseDto;
import com.nisum.registeruser.application.mappers.UserMapper;
import com.nisum.registeruser.application.utils.ValidationUtil;
import com.nisum.registeruser.domain.entities.User;
import com.nisum.registeruser.domain.ports.UserRepository;
import com.nisum.registeruser.infrastructure.exception.ValidationException;
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
        validateUserRequest(userRequestDto);
        User user = userMapper.convertDtoToEntity(userRequestDto);
        User savedUser = userRepository.save(user);
        UserDetails userDetails = userDetailsService.loadUserByUsername(savedUser.getEmail());
        String jwt = jwtService.generateToken(userDetails);
        return createUserResponse(savedUser, jwt);
    }

    public void validateUserRequest(UserRequestDto userRequestDto) {
        if (!ValidationUtil.isValidEmail(userRequestDto.email())) {
            throw new ValidationException("Invalid email");
        }
        if (!ValidationUtil.isValidPassword(userRequestDto.password())) {
            throw new ValidationException("Invalid password");
        }
    }

    private UserResponseDto createUserResponse(User savedUser, String jwt) {
        UserResponseDto userResponseDto = userMapper.convertEntityToDto(savedUser);
        userResponseDto.setToken(jwt);
        return userResponseDto;
    }

}
