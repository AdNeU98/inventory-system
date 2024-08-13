package com.csye7374.inventory.alert;

import com.csye7374.inventory.model.Product;
import com.csye7374.inventory.repository.ProductRepository;

public class StockPurchase implements StateAPI {
    Product product;
    ProductRepository productRepo;

    public StockPurchase(Product product, ProductRepository productRepo) {
        this.product = product;
        this.productRepo = productRepo;
    }

    @Override
    public void increaseStock(int stock) {
        this.productRepo.save(this.product);
        System.out.println("Order purchase successful ");
    }

    @Override
    public void alertStock(int stock) {
        System.out.println("Err! purchase state, cannot alert stock ");
    }
}

