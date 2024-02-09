package com.example.zart.service.auth;

import com.example.zart.dto.auth.AuthRegisterRequest;
import com.example.zart.entity.User;
import com.example.zart.errors.ErrorsMessagesEnum;
import com.example.zart.repository.UserRepository;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import java.util.regex.Pattern;

@Component
@Data
public class AuthValidationService {
    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public void validateRegisterFields(AuthRegisterRequest userBody) {
        if (!this.verifyPasswordPatternMatches(userBody.getPassword())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ErrorsMessagesEnum.INVALID_PASSWORD);
        }
        if (this.verifyEmailAlreadyExists(userBody.getEmail())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ErrorsMessagesEnum.EMAIL_EXISTS);
        }
    }
    public Boolean verifyPasswordPatternMatches(String password) {
        String regex = "^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,20}$";
        Pattern p = Pattern.compile(regex);
        return p.matcher(password).matches();
    }

    public Boolean verifyEmailAlreadyExists(String email) {
        User userEntity = this.userRepository.findOneByEmail(email);
        return userEntity != null;
    }

    public String encryptPassword(String passwordToEncrypt) {
        return this.passwordEncoder.encode(passwordToEncrypt);
    }
    public Boolean decryptPassword(String rawPassword, String encryptedPassword) {
        return this.passwordEncoder.matches(rawPassword, encryptedPassword);
    }
}
