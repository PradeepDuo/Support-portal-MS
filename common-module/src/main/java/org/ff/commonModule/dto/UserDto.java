package org.ff.commonModule.dto;

import lombok.Data;

@Data
public class UserDto {
    private String name;
    private String email;
    private String role;
    private Long phoneNumber;
}
