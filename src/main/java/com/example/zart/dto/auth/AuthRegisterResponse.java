package com.example.zart.dto.auth;

import com.example.zart.enums.AuthStatusEnum;
import lombok.Data;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public class AuthRegisterResponse implements Serializable {
    String name;
    String secondName;
    String email;
    LocalDateTime createdAt;
    LocalDateTime updatedAt;
    AuthStatusEnum status;
}
