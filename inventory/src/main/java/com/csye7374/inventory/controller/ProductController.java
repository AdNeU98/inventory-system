package com.csye7374.inventory.controller;

import com.csye7374.inventory.dao.InventoryStrategy;
import com.csye7374.inventory.dao.ProductStrategy;
import com.csye7374.inventory.model.Product;
import com.csye7374.inventory.repository.BuyerRepository;
import com.csye7374.inventory.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {

	@Autowired
	private BuyerRepository buyerRepo;
	
	@Autowired
	private ProductRepository productRepo;

	@GetMapping("/getAll")
	public List<Product> getAll() {
		return productRepo.findAll();
	}
	
	@GetMapping("/getProduct/{id}")
	public Product getProduct(@PathVariable int id) {
		return productRepo.findById(id).get();
	}

	@PostMapping("/save")
	public void save(@RequestBody Product product) {
		InventoryStrategy strategy = new InventoryStrategy(new ProductStrategy(productRepo, product, buyerRepo));
		strategy.executeAdd();
	}
	
	@PutMapping("/update/{id}")
	public void update(@RequestBody Product product, @PathVariable int id) {
		InventoryStrategy strategy = new InventoryStrategy(new ProductStrategy(productRepo, product));
		strategy.executeUpdate(id);
	}
	
	@DeleteMapping("/delete/{id}")
	public void deletebyID(@PathVariable int id) {
		InventoryStrategy strategy = new InventoryStrategy(new ProductStrategy(productRepo, id));
		strategy.executeDelete();
	}

}
