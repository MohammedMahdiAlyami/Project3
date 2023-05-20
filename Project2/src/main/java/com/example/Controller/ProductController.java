package com.example.Controller;

import com.example.ApiResponse.ApiResponse;
import com.example.Model.Product;
import com.example.Service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;

@RestController
@RequestMapping("api/v1/product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;


    @GetMapping("/get")
    public ResponseEntity getProduct() {
        ArrayList<Product> products = productService.getProducts();
        return ResponseEntity.status(200).body(products);
    }


    @PostMapping("/add")
    public ResponseEntity addProduct(@Valid @RequestBody Product product, Errors errors) {
        if (errors.hasErrors()) {
            String massage = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(new ApiResponse(massage));
        }
        boolean isAdded = productService.addProduct(product);
        if (isAdded) {
            return ResponseEntity.status(200).body("Product added");
        }else return ResponseEntity.status(400).body("No Category in product");
    }


    @PutMapping("/update/{id}")
    public ResponseEntity updateProduct(@Valid @RequestBody Product product,Errors errors,@PathVariable int id) {
        if (errors.hasErrors()) {
            String massage = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(new ApiResponse(massage));
        }
        int isUpdated = productService.updateProduct(id, product);
        if (isUpdated == 0) {
            return ResponseEntity.status(200).body("Product updated");
        } else if (isUpdated == -1) {
            return ResponseEntity.status(400).body("New product category not existed");
        }
        return ResponseEntity.status(400).body("product not existed");
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteProduct(@PathVariable int id){
        boolean isDeleted=productService.deleteProduct(id);
        if (isDeleted){
            return ResponseEntity.status(200).body("Product deleted");
        }
        else return ResponseEntity.status(400).body("Product not existed");
    }


}