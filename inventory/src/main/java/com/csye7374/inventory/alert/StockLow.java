package com.csye7374.inventory.alert;

import com.csye7374.inventory.facade.SendMessage;
import com.csye7374.inventory.model.Product;
import com.csye7374.inventory.repository.ProductRepository;

public class StockLow implements StateAPI {
    Product product;
    ProductRepository productRepo;

    public StockLow(Product product, ProductRepository productRepo) {
        this.product = product;
        this.productRepo = productRepo;
    }

    @Override
    public void increaseStock(int stock) {
        System.out.println("Err! low state, cannot increase stock ");
    }

    @Override
    public void alertStock(int stock) {
        SendMessage.message("\n******\nLOW STOCK for " + this.product.getProductName() + "\n*****\n");
    }
}

