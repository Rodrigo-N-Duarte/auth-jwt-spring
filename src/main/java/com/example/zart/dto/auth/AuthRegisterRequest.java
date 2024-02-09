package com.example.zart.dto.auth;

import com.example.zart.enums.AuthStatusEnum;
import jakarta.validation.constraints.*;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public class AuthRegisterRequest implements Serializable {
    @NotBlank
    String name;
    @NotBlank
    String secondName;
    @Email
    @NotBlank
    String email;
    @NotBlank
    @Size(min = 8, message = "{validation.name.size.too_short}")
    @Size(max = 20, message = "{validation.name.size.too_long}")
    String password;
    LocalDateTime createdAt;
    LocalDateTime updatedAt;
    AuthStatusEnum status;
}
