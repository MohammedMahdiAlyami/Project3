package com.example.Model;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class Merchant {

    @NotNull(message = "id can not be null")
    @Digits(integer = 3, fraction = 0,message = "id must to be 3 character long ")
    private int id;

    @NotEmpty(message = "name can not be empty")
    @Size(min = 3,message = "name must to be 3 character long")
    private String name;
}
