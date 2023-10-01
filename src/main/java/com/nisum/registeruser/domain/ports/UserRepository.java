package com.nisum.registeruser.domain.ports;

import com.nisum.registeruser.domain.entities.User;

import java.util.Optional;

public interface UserRepository {

    User save(User user);

    Optional<User> findByEmail(String username);
}
