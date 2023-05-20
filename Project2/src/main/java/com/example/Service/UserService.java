package com.example.Service;

import com.example.Model.MerchantStock;
import com.example.Model.User;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.ArrayList;

@AllArgsConstructor
@Service
public class UserService {

    ArrayList<User> users = new ArrayList<>();


    private final MerchantService merchantService;
    private final ProductService productService;
    private final MerchantStockService merchantStockService;

    public ArrayList getUsers() {
        return users;
    }

    public void addUser(User user) {
        users.add(user);
    }

    public boolean updateUser(int id, User user) {
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getId() == id) {
                users.set(i, user);
                return true;
            }
        }
        return false;
    }

    public boolean deleteUser(int id) {
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getId() == id) {
                users.remove(i);
                return true;
            }
        }
        return false;
    }

    public int  buy(int userid,int productid ,int merchantid){
        for (int i =0;i<users.size();i++){
            if (users.get(i).getId()==userid){
                for (int j=0;j<merchantService.merchants.size();j++){
                    if (merchantService.merchants.get(j).getId()==merchantid){
                        for (int l=0;l<productService.products.size();l++){
                            if (productService.products.get(l).getId()==productid){
                                for (int e=0;e<merchantStockService.merchantStocks.size();e++){
                                    if (merchantid==merchantStockService.merchantStocks.get(e).getMerchantid()&&productid==merchantStockService.merchantStocks.get(e).getProductid()){
                                        if (users.get(i).getBalance()>=productService.products.get(l).getPrice()&&merchantStockService.merchantStocks.get(e).getStock()>0){
                                            merchantStockService.merchantStocks.get(e).setStock(merchantStockService.merchantStocks.get(e).getStock()-1);
                                            users.get(i).setBalance(users.get(i).getBalance()-productService.products.get(l).getPrice());
                                            return 0;
                                        }
                                        return -5;
                                    }
                                }
                                return -4 ;
                            }
                        }
                        return -3;
                    }
                }
                return -2;
            }
        }
        return -1;
    }

    public int addToStock(int productid, int merchantid , MerchantStock stock){
        for (int i=0;i<merchantService.merchants.size();i++){
            if (merchantService.merchants.get(i).getId()==merchantid){
                for (int j=0;j<merchantStockService.merchantStocks.size();j++){
                    if (merchantStockService.merchantStocks.get(j).getMerchantid()==merchantid && merchantStockService.merchantStocks.get(j).getProductid()==productid){
                        if (merchantStockService.merchantStocks.get(j).getStock()>=0){
                            merchantStockService.addMerchantStock(stock);
                            merchantStockService.merchantStocks.get(j).setStock( merchantStockService.merchantStocks.get(j).getStock()+1);
                            return 0;
                        }
                    }
                }
                return -2;
            }
        }
        return -1;
    }


}

