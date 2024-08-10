package com.csye7374.inventory.dao;

import com.csye7374.inventory.model.ProductPO;
import com.csye7374.inventory.repository.ProductPORepository;

import java.util.Optional;

public class ProductPOStrategy implements StrategyAPI {
    private ProductPORepository productPORepo;
    private int id;
    private ProductPO productPO;

    public ProductPOStrategy(ProductPORepository productPORepo, int id) {
        this.productPORepo = productPORepo;
        this.id = id;
    }

    public ProductPOStrategy(ProductPORepository productPORepo, ProductPO productPO) {
        this.productPORepo = productPORepo;
        this.productPO = productPO;
    }

    @Override
    public void add() {
        this.productPORepo.save(this.productPO);
    }

    @Override
    public void update(int id) {
        Optional<ProductPO> productPO = this.productPORepo.findById(id);
        if (productPO.isEmpty()) {
            throw new RuntimeException("ProductPO does not exist");
        }
        productPO.get().setProduct(this.productPO.getProduct());
        productPO.get().setQuantity(this.productPO.getQuantity());
        productPO.get().setPurchaseOrder(this.productPO.getPurchaseOrder());

        this.productPORepo.save(productPO.get());
    }

    @Override
    public void delete() {
        Optional<ProductPO> productPO = this.productPORepo.findById(this.id);
        this.productPORepo.delete(productPO.get());
    }
}

