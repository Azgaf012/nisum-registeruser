package com.nisum.registeruser.application.usecases;

import com.nisum.registeruser.application.dto.UserRequestDto;
import com.nisum.registeruser.application.dto.UserResponseDto;
import com.nisum.registeruser.application.mappers.UserMapper;
import com.nisum.registeruser.application.utils.ValidationUtil;
import com.nisum.registeruser.domain.entities.User;
import com.nisum.registeruser.domain.ports.UserRepository;
import com.nisum.registeruser.infrastructure.exception.ValidationException;
import com.nisum.registeruser.infrastructure.service.JwtService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CreateUserUseCaseTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private UserMapper userMapper;

    @Mock
    private UserDetailsService userDetailsService;

    @Mock
    private JwtService jwtService;

    @Mock
    private ValidationUtil validationUtil;

    @InjectMocks
    private CreateUserUseCase createUserUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void execute_ShouldSaveUserAndReturnUserResponseDto() {

        UserRequestDto userRequestDto = Mockito.mock(UserRequestDto.class,RETURNS_MOCKS);
        User user = Mockito.mock(User.class,RETURNS_MOCKS);
        User savedUser = Mockito.mock(User.class,RETURNS_MOCKS);
        UserDetails userDetails = Mockito.mock(org.springframework.security.core.userdetails.User.class,RETURNS_MOCKS);
        String jwt = "some.jwt.token";
        UserResponseDto expectedResponse = Mockito.mock(UserResponseDto.class,RETURNS_MOCKS);

        when(userMapper.convertDtoToEntity(userRequestDto)).thenReturn(user);
        when(userRepository.save(user)).thenReturn(savedUser);
        when(userDetailsService.loadUserByUsername(savedUser.getEmail())).thenReturn(userDetails);
        when(jwtService.generateToken(userDetails)).thenReturn(jwt);
        when(userMapper.convertEntityToDto(savedUser)).thenReturn(expectedResponse);
        when(validationUtil.isValidEmail(anyString())).thenReturn(true);
        when(validationUtil.isValidPassword(anyString())).thenReturn(true);

        UserResponseDto actualResponse = createUserUseCase.execute(userRequestDto);

        assertEquals(expectedResponse, actualResponse);
        verify(userRepository).save(user);
        verify(jwtService).generateToken(userDetails);
    }

    @Test
    void execute_ShouldThrowValidationException_ForInvalidEmail() {

        UserRequestDto userRequestDto = Mockito.mock(UserRequestDto.class,RETURNS_MOCKS);
        when(userRequestDto.email()).thenReturn("invalid-email");
        when(validationUtil.isValidEmail("invalid-email")).thenReturn(false);

        ValidationException thrownException = assertThrows(
                ValidationException.class,
                () -> createUserUseCase.execute(userRequestDto)
        );

        assertEquals("Invalid email", thrownException.getMessage());
    }

    @Test
    void execute_ShouldThrowValidationException_ForInvalidPassword() {

        UserRequestDto userRequestDto = Mockito.mock(UserRequestDto.class,RETURNS_MOCKS);
        when(userRequestDto.password()).thenReturn("short");
        when(validationUtil.isValidPassword("short")).thenReturn(false);
        when(validationUtil.isValidEmail(anyString())).thenReturn(true);
        ValidationException thrownException = assertThrows(
                ValidationException.class,
                () -> createUserUseCase.execute(userRequestDto)
        );

        assertEquals("Invalid password", thrownException.getMessage());
    }

}