package com.blog.payloads;

import java.util.HashSet;
import java.util.Set;

import com.blog.entities.Role;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class UserDto {
    private int id;

    @NotEmpty
    @Size(min = 4, message = "Username must be min of 4 character !!")
    private String name;

    @Email(message = "Email Address is not valid !!")
    private String email;

    @NotEmpty
    @Size(min = 4, max = 10, message = "password must be min of 4 chars and maximum of 10 char !!!")
    //@Pattern(regexp = )
    private String password;

    @NotEmpty
    private String about;

    private Set<RoleDto> roles = new HashSet<>();
}
