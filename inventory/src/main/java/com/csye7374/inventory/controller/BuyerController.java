package com.csye7374.inventory.controller;

import com.csye7374.inventory.dao.BuyerStrategy;
import com.csye7374.inventory.dao.InventoryStrategy;
import com.csye7374.inventory.model.Buyer;
import com.csye7374.inventory.repository.BuyerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @apiNote - Controller for Buyers
 */
@RestController
@RequestMapping("/buyer")
public class BuyerController {

    @Autowired
    private BuyerRepository buyerRepo;

    @GetMapping("/getAll")
    public List<Buyer> getAll() {
        return buyerRepo.findAll();
    }

    @PostMapping("/save")
    public void save(@RequestBody Buyer buyer) {
        InventoryStrategy strategy = new InventoryStrategy(new BuyerStrategy(buyerRepo, buyer));
        strategy.executeAdd();
    }

    @GetMapping("/getBuyer/{id}")
    public Buyer getBuyer(@PathVariable int id) {
        return buyerRepo.findById(id).get();
    }

    @DeleteMapping("/delete/{id}")
    public void deletebyID(@PathVariable int id) {
        InventoryStrategy strategy = new InventoryStrategy(new BuyerStrategy(buyerRepo, id));
        strategy.executeDelete();
    }

    @PutMapping("/update/{id}")
    public void update(@RequestBody Buyer buyer, @PathVariable int id) {
        InventoryStrategy strategy = new InventoryStrategy(new BuyerStrategy(buyerRepo, buyer));
        strategy.executeUpdate(id);
    }

}