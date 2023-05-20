package com.example.Model;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class User {


    @NotNull(message = "id can not be null")
    @Digits(integer = 3, fraction = 0,message = "id must to be 3 character long ")
    private int id;

    @NotEmpty(message = "username can not be empty")
    @Size(min = 5,message = "username must to be 5 character long")
    private String username;

    @NotEmpty(message = "password can not be empty")
    @Size(min = 6,message = "password must to be 6 character long")
    private String password;

    @NotEmpty(message = "email can not be empty")
    @Email(message = "must be valid email")
    private String email;

    @NotEmpty(message = "role can not be empty")
    @Pattern(regexp = "Admin|Customer", message = "role must be [ Admin ] or [ Customer ] only")
    private String role;

    @NotNull(message = "balance can not be null")
    @Positive(message = "balance must be positive number ")
    private double balance;



}
