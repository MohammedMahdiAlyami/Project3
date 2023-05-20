package com.example.Service;

import com.example.Model.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Service;
import java.util.ArrayList;

@Data
@AllArgsConstructor
@Service

public class ProductService {

    ArrayList<Product> products = new ArrayList<>();

    private final CategoryService categoryService;
    public ArrayList<Product> getProducts() {
        return products;
    }

    public boolean addProduct(Product product) {
        for (int i = 0; i < categoryService.categories.size(); i++) {
            if (product.getCategoryID() == categoryService.categories.get(i).getId()) {
                products.add(product);
                return true;
            }
        }
        return false;
    }


    public int updateProduct(int id, Product product) {
        for (int i = 0; i < categoryService.categories.size(); i++) {
            if (product.getCategoryID() == categoryService.categories.get(i).getId()) {
                for (int j = 0; j < products.size(); j++) {
                    if (products.get(j).getId() == id) {
                        products.set(j, product);
                        return 0;
                    }
                }
                return 1;
            }
        }
        return -1;
    }

    public boolean deleteProduct(int id) {
        for (int i = 0; i < products.size(); i++) {
            if (products.get(i).getId() == id) {
                products.remove(i);
                return true;
            }
        }
        return false;
    }

}

