package com.example.redis.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO extends MainDTO<UserDTO> {

    private String username;

    private String password;

    private String fullName;

    private String phoneNumber;

}
