package org.ff.configModule.dto;

import lombok.*;

@Data
public class UserDto {
    private String name;
    private String email;
    private String role;
    private Long phoneNumber;
}
