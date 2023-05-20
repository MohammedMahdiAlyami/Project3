package com.example.Controller;

import com.example.ApiResponse.ApiResponse;
import com.example.Model.Category;
import com.example.Model.User;
import com.example.Service.CategoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;

@RestController
@RequestMapping("api/v1/category")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;
    private Category category;
    private Errors errors;

    @GetMapping("/get")
    public ResponseEntity get(){
        ArrayList<User> users= categoryService.getCategory();
        return ResponseEntity.status(200).body(users);
    }

    @PostMapping("/add")
    public ResponseEntity addCategory(@Valid @RequestBody Category category, Errors errors) {
        ResponseEntity result;
        this.category = category;
        this.errors = errors;
        if (errors.hasErrors()) {
            String massage = errors.getFieldError().getDefaultMessage();
            result = ResponseEntity.status(400).body(new ApiResponse(massage));
        }
        else {
            categoryService.addCategory(category);
            result = ResponseEntity.status(200).body("Category added");
        }
        return result;
    }

    @PutMapping("/update/{id}")
    public ResponseEntity updateBlog(@Valid @RequestBody Category category,Errors errors,@PathVariable int id){
        if(errors.hasErrors()) {
            String massage = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(new ApiResponse(massage));
        }
        boolean isUpdated=categoryService.updateCategory(id,category);
        if (isUpdated){
            return ResponseEntity.status(200).body("Category updated");
        }
        else return ResponseEntity.status(400).body("Category not existed");
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteCategory(@PathVariable int id){
        boolean isDeleted=categoryService.deleteCategory(id);
        if (isDeleted){
            return ResponseEntity.status(200).body("Can't add any product to this Category");
        }
        else return ResponseEntity.status(400).body("Category not existed");
    }

}
