package com.nisum.registeruser.application.mappers;

import com.nisum.registeruser.application.dto.PhoneDto;
import com.nisum.registeruser.application.dto.UserRequestDto;
import com.nisum.registeruser.application.dto.UserResponseDto;
import com.nisum.registeruser.domain.entities.Phone;
import com.nisum.registeruser.domain.entities.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class UserMapperTest {

    @InjectMocks
    private UserMapper userMapper;

    @Spy
    private List<PhoneDto> phoneDtos = Arrays.asList(
            new PhoneDto("12345", 10, 1),
            new PhoneDto("67890", 20, 2)
    );

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void convertDtoToEntity_ShouldReturnCorrectUser() {
        UserRequestDto userRequestDto = new UserRequestDto(
                "John Doe",
                "john.doe@example.com",
                "password123",
                phoneDtos
        );
        User convertedUser = userMapper.convertDtoToEntity(userRequestDto);

        assertEquals("John Doe", convertedUser.getName());
        assertEquals("john.doe@example.com", convertedUser.getEmail());
        assertEquals("password123", convertedUser.getPassword());
        assertEquals(phoneDtos.size(), convertedUser.getPhones().size());
    }

    @Test
    void convertEntityToDto_ShouldReturnCorrectUserResponseDto() {
        User user = User.builder()
                .id(UUID.randomUUID())
                .name("John Doe")
                .email("john.doe@example.com")
                .password("password123")
                .created(LocalDateTime.now())
                .updated(LocalDateTime.now())
                .last_login(LocalDateTime.now())
                .phones(Arrays.asList(
                        Phone.builder().number("12345").citycode(10).countrycode(1).build(),
                        Phone.builder().number("67890").citycode(20).countrycode(2).build()
                ))
                .isActive(true)
                .build();
        UserResponseDto convertedDto = userMapper.convertEntityToDto(user);

        assertEquals(user.getId().toString(), convertedDto.getId());
        assertEquals(user.isActive(), convertedDto.getIsActive());

    }

    @Test
    void convertPhoneDtosToEntities_ShouldReturnCorrectPhones() {
        List<Phone> convertedPhones = userMapper.convertPhoneDtosToEntities(phoneDtos);

        for (int i = 0; i < phoneDtos.size(); i++) {
            assertEquals(phoneDtos.get(i).number(), convertedPhones.get(i).getNumber());
            assertEquals(phoneDtos.get(i).cityCode(), convertedPhones.get(i).getCitycode());
            assertEquals(phoneDtos.get(i).countryCode(), convertedPhones.get(i).getCountrycode());
        }
    }
}