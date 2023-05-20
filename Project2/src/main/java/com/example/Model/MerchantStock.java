package com.example.Model;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class MerchantStock {

    @NotNull(message = "id can not be null")
    @Digits(integer = 3, fraction = 0,message = "id must to be 3 character long ")
    private int id;

    @NotEmpty(message = "productid can not be empty")
    @Digits(integer = 3, fraction = 0,message = "productid must to be 3 character long ")
    private int productid;

    @NotNull(message = "merchantid can not be null")
    @Digits(integer = 3, fraction = 0,message = "merchantid must to be 3 character long ")
    private int merchantid;

    @NotNull(message = "stock can not be Null")
    @Digits(integer = 11, fraction = 0,message = "stock must to be 11 character long ")
    private int stock;

}
