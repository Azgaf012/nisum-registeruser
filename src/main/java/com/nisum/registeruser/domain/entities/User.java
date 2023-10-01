package com.nisum.registeruser.domain.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "Userapp")
public class User {
    @Id
    private UUID id;
    private String name;
    private String email;
    private String password;
    private LocalDateTime created;
    private LocalDateTime updated;
    private LocalDateTime last_login;
    private String token;
    private boolean isActive;
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "userid")
    private List<Phone> phones;
}
