package com.csye7374.inventory.dao;

import com.csye7374.inventory.notify.Notify;
import com.csye7374.inventory.notify.UpdateBuyers;
import com.csye7374.inventory.notify.UpdateDB;
import com.csye7374.inventory.model.Product;
import com.csye7374.inventory.repository.BuyerRepository;
import com.csye7374.inventory.repository.ProductRepository;

import java.util.Optional;

public class ProductStrategy implements StrategyAPI {
    private ProductRepository productRepo;
    private int id;
    private Product product;
    private BuyerRepository buyerRepo;

    public ProductStrategy(ProductRepository productRepo, int id) {
        this.productRepo = productRepo;
        this.id = id;
    }

    public ProductStrategy(ProductRepository productRepo, Product product) {
        this.productRepo = productRepo;
        this.product = product;
    }

    public ProductStrategy(ProductRepository productRepo, Product product, BuyerRepository buyerRepo) {
        this.productRepo = productRepo;
        this.product = product;
        this.buyerRepo = buyerRepo;
    }

    @Override
    public void add() {
        Notify a = new Notify();
        new UpdateBuyers(a, this.buyerRepo);
        new UpdateDB(a, this.productRepo);
        a.setState(this.product);
    }

    @Override
    public void update(int id) {
        Optional<Product> product = this.productRepo.findById(id);
        if (product.isEmpty()) {
            throw new RuntimeException("Product does not exist");
        }
        product.get().setProductName(this.product.getProductName());
        product.get().setPrice(this.product.getPrice());
        product.get().setQuantity(this.product.getQuantity());
        product.get().setPrice(this.product.getPrice());
        product.get().setPurchaseOrders(this.product.getPurchaseOrders());
        this.productRepo.save(product.get());
    }

    @Override
    public void delete() {
        Optional<Product> product = this.productRepo.findById(this.id);
            this.productRepo.delete(product.get());
    }
}

