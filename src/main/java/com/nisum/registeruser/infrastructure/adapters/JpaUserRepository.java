package com.nisum.registeruser.infrastructure.adapters;

import com.nisum.registeruser.domain.entities.User;
import com.nisum.registeruser.domain.ports.UserRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface JpaUserRepository extends UserRepository, JpaRepository<User, UUID> {

}
