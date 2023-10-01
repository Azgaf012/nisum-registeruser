package com.nisum.registeruser.domain.ports;

import com.nisum.registeruser.domain.entities.Phone;
import com.nisum.registeruser.domain.entities.User;

import java.util.List;

public interface PhoneRepository {
    Phone save(Phone phone);

    List<Phone> findByUser(User user);
}
