package com.nisum.registeruser.application.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.regex.Pattern;

@Component
public class ValidationUtil {

    private final Pattern emailPattern;
    private final Pattern passwordPattern;

    public ValidationUtil(
            @Value("${validation.email-pattern}") String emailRegex,
            @Value("${validation.password-pattern}") String passwordRegex) {
        emailPattern = Pattern.compile(emailRegex);
        passwordPattern = Pattern.compile(passwordRegex);
    }

    public boolean isValidEmail(String email) {
        return emailPattern.matcher(email).matches();
    }

    public boolean isValidPassword(String password) {
        return passwordPattern.matcher(password).matches();
    }
}
