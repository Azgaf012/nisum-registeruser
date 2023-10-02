package com.nisum.registeruser.application.mappers;

import com.nisum.registeruser.application.dto.PhoneDto;
import com.nisum.registeruser.application.dto.UserRequestDto;
import com.nisum.registeruser.application.dto.UserResponseDto;
import com.nisum.registeruser.domain.entities.Phone;
import com.nisum.registeruser.domain.entities.User;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class UserMapper {

    public User convertDtoToEntity(UserRequestDto dto) {
        return User.builder()
                .id(UUID.randomUUID())
                .name(dto.name())
                .email(dto.email())
                .password(dto.password())
                .created(LocalDateTime.now())
                .updated(LocalDateTime.now())
                .last_login(LocalDateTime.now())
                .phones(convertPhoneDtosToEntities(dto.phones()))
                .isActive(Boolean.TRUE)
                .build();
    }

    public UserResponseDto convertEntityToDto(User user) {
        return UserResponseDto.builder()
                .id(user.getId().toString())
                .created(user.getCreated().toLocalDate())
                .modified(user.getUpdated().toLocalDate())
                .last_login(user.getLast_login().toLocalDate())
                .isActive(user.isActive())
                .build();
    }

    public List<Phone> convertPhoneDtosToEntities(List<PhoneDto> phoneDtos) {
        return phoneDtos.stream()
                .map(dto -> Phone.builder()
                        .number(dto.number())
                        .citycode(dto.cityCode())
                        .countrycode(dto.countryCode())
                        .build())
                .collect(Collectors.toList());
    }
}
