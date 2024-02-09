package com.example.zart.dto.auth;

import com.example.zart.enums.LoginStatusEnum;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class LoginResponse {
    String jwt;
    LoginStatusEnum status;
}
