package com.csye7374.inventory.controller;

import com.csye7374.inventory.dao.InventoryStrategy;
import com.csye7374.inventory.dao.ProductPOStrategy;
import com.csye7374.inventory.model.ProductPO;
import com.csye7374.inventory.repository.ProductPORepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/productPO")
public class ProductPOController {

    @Autowired
    private ProductPORepository productPORepo;

    @GetMapping("/getAll")
    public List<ProductPO> getAll() {
        return productPORepo.findAll();
    }

    @GetMapping("/getProductPO/{id}")
    public ProductPO getProductPO(@PathVariable int id) {
        return productPORepo.findById(id).get();
    }

    @PutMapping("/update/{id}")
    public void update(@RequestBody ProductPO productPO, @PathVariable int id) {
        InventoryStrategy strategy = new InventoryStrategy(new ProductPOStrategy(productPORepo, productPO));
        strategy.executeUpdate(id);
    }

    @PostMapping("/save")
    public void save(@RequestBody ProductPO productPO) {
        InventoryStrategy strategy = new InventoryStrategy(new ProductPOStrategy(productPORepo, productPO));
        strategy.executeAdd();
    }

    @DeleteMapping("/delete/{id}")
    public void deletebyID(@PathVariable int id) {
        InventoryStrategy strategy = new InventoryStrategy(new ProductPOStrategy(productPORepo, id));
        strategy.executeDelete();
    }
}
