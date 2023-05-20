package com.example.Controller;

import com.example.ApiResponse.ApiResponse;
import com.example.Model.MerchantStock;
import com.example.Service.MerchantStockService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;

@RestController
@RequestMapping("api/v1/merchantstock")
@RequiredArgsConstructor
public class MerchantStockController {

    private final MerchantStockService merchantStockService;

    @GetMapping("/get")
    public ResponseEntity getMerchantStock() {
        ArrayList<MerchantStock> merchantStocks = merchantStockService.getStock();
        return ResponseEntity.status(200).body(merchantStocks);
    }

    @PostMapping("/add")
    public ResponseEntity addMerchantStock(@Valid @RequestBody MerchantStock merchantStock, Errors errors) {
        if (errors.hasErrors()) {
            String massage = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(new ApiResponse(massage));
        }
        int isAdded = merchantStockService.addMerchantStock(merchantStock);
        if (isAdded==0){
            return ResponseEntity.status(200).body("merchantStock added");
        }
        else if (isAdded==1) {
            return ResponseEntity.status(400).body("product id not existed");
        }
        return ResponseEntity.status(400).body(" merchant id not existed");
    }


    @PutMapping("/update/{id}")
    public ResponseEntity updateStock(@Valid @RequestBody MerchantStock merchantStock,Errors errors,@PathVariable int id) {
        if (errors.hasErrors()) {
            String massage = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(new ApiResponse(massage));
        }
        int isUpdated = merchantStockService.updateMerchantStock(id, merchantStock);
        if (isUpdated == 0) {
            return ResponseEntity.status(200).body("MerchantStock updated");
        }
        else if (isUpdated == -1) {
            return ResponseEntity.status(400).body("MerchantStock id not in the system");
        }
        else if (isUpdated == -2) {
            return ResponseEntity.status(400).body("Product id not in the system");
        }
        return ResponseEntity.status(400).body("Product not existed");
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteStock(@PathVariable int id){
        boolean isDeleted=merchantStockService.deleteMerchantStock(id);
        if (isDeleted){
            return ResponseEntity.status(200).body("MerchantStock deleted");
        }
        else return ResponseEntity.status(400).body("MerchantStock not existed");
    }

}
