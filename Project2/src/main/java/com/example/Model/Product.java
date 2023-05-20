package com.example.Model;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class Product {

    @NotNull(message = "id can not be null")
    @Digits(integer = 3, fraction = 0,message = "id must to be 3 character long ")
    private int id;

    @NotEmpty(message = "name can not be empty")
    @Size(min = 3,message = "name must to be 3 character long")
    private String name;

    @NotNull(message = "price can not be null")
    @Positive(message = "price must be positive number ")
    private double price;

    @NotNull(message = "categoryID can not be null")
    @Digits(integer = 3, fraction = 0,message = "categoryID must to be 3 character long ")
    private int categoryID;

}
