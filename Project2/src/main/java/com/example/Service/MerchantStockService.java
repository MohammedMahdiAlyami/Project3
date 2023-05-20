package com.example.Service;

import com.example.Model.MerchantStock;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Service;
import java.util.ArrayList;

@Data
@AllArgsConstructor
@Service
public class MerchantStockService {

    ArrayList<MerchantStock> merchantStocks = new ArrayList<>();

    private final MerchantService merchantService;
    private final ProductService productService;

    public ArrayList<MerchantStock> getStock() {
        return merchantStocks;
    }

    public int addMerchantStock(MerchantStock merchantStock) {
        for (int i = 0; i < merchantService.merchants.size(); i++) {
            if (merchantStock.getMerchantid() == merchantService.merchants.get(i).getId()) {
                for (int j = 0; j < productService.products.size(); j++) {
                    if (merchantStock.getProductid() == productService.products.get(i).getId()) {
                        merchantStocks.add(merchantStock);
                        merchantStock.setStock(merchantStock.getStock());
                        return 0;
                    }
                }
                return 1;
            }
        }
        return 1;
    }


    public int updateMerchantStock(int id, MerchantStock merchantStock) {
        for (int l = 0; l < productService.products.size(); l++) {
            if (merchantStock.getProductid() == productService.products.get(l).getId()) {
                for (int i = 0; i < merchantService.merchants.size(); i++) {
                    if (merchantStock.getMerchantid() == merchantService.merchants.get(i).getId()) {
                        for (int j = 0; j < merchantStocks.size(); j++) {
                            if (merchantStocks.get(j).getId() == id) {
                                merchantStocks.set(j, merchantStock);
                                return 0;
                            }
                        }
                        return 1;
                    }
                }
                return -1;
            }
        }
        return -2;
    }

    public boolean deleteMerchantStock(int id) {
        for (int i = 0; i < merchantStocks.size(); i++) {
            if (merchantStocks.get(i).getId() == id) {
                merchantStocks.remove(i);
                return true;
            }
        }
        return false;
    }


}
