package com.example.zart.service.auth;

import com.example.zart.dto.auth.AuthRegisterRequest;
import com.example.zart.dto.auth.AuthRegisterResponse;
import com.example.zart.dto.auth.LoginRequest;
import com.example.zart.dto.auth.LoginResponse;
import com.example.zart.entity.User;
import com.example.zart.enums.AuthStatusEnum;
import com.example.zart.enums.LoginStatusEnum;
import com.example.zart.errors.ErrorsMessagesEnum;
import lombok.Data;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.example.zart.repository.UserRepository;
import org.springframework.web.server.ResponseStatusException;

@Service
@Data
public class AuthService {
    private final ModelMapper modelMapper;
    private final UserRepository userRepository;

    private final AuthValidationService authValidationService;

    public ResponseEntity<AuthRegisterResponse> registerUser(AuthRegisterRequest userBody) {
        this.authValidationService.validateRegisterFields(userBody);
        var userEntity = this.modelMapper.map(userBody, User.class);
        userEntity.setPassword(this.authValidationService.encryptPassword(userEntity.getPassword()));
        userEntity.setStatus(AuthStatusEnum.CREATED);
        this.userRepository.save(userEntity);
        var userResponse = this.modelMapper.map(userEntity, AuthRegisterResponse.class);
        return ResponseEntity.status(201).body(userResponse);
    }

    public LoginResponse loginUser(LoginRequest loginBody) {
        User user = this.userRepository.findOneByEmail(loginBody.getEmail());
        if (user != null) {
            if (!this.authValidationService.decryptPassword(loginBody.getPassword(), user.getPassword())) {
                throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, ErrorsMessagesEnum.INVALID_PASSWORD);
            }
            LoginResponse loginResponse = new LoginResponse();
            loginResponse.setJwt("123");
            loginResponse.setStatus(LoginStatusEnum.SUCCESS);
            return loginResponse;
        }
        throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, ErrorsMessagesEnum.INVALID_LOGIN);
    }
}
