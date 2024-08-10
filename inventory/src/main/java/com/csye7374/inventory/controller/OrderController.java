package com.csye7374.inventory.controller;

import com.csye7374.inventory.dao.InventoryStrategy;
import com.csye7374.inventory.dao.OrderStrategy;
import com.csye7374.inventory.model.PurchaseOrder;
import com.csye7374.inventory.repository.OrderRepository;
import com.csye7374.inventory.repository.ProductPORepository;
import com.csye7374.inventory.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/purchaseOrder")
public class OrderController {

	@Autowired
	private ProductRepository productRepo;

	@Autowired
	private OrderRepository orderRepo;

	@Autowired
	private ProductPORepository productPORepo;

	@GetMapping("/getAll")
	public List<PurchaseOrder> getAll() {
		return orderRepo.findAll();
	}
	
	@GetMapping("/getPurchaseOrder/{id}")
	public PurchaseOrder getPurchaseOrder(@PathVariable int id) {
		return orderRepo.findById(id).get();
	}
	
	@PutMapping("/update/{id}")
	public void update(@RequestBody PurchaseOrder purchaseOrder, @PathVariable int id) {
		InventoryStrategy strategy = new InventoryStrategy(new OrderStrategy(orderRepo, purchaseOrder));
		strategy.executeUpdate(id);
	}
	
	@DeleteMapping("/delete/{id}")
	public void deletebyID(@PathVariable int id) {
		InventoryStrategy strategy = new InventoryStrategy(new OrderStrategy(orderRepo, id));
		strategy.executeDelete();
	}

	@PostMapping("/save")
	public void save(@RequestBody PurchaseOrder purchaseOrder) {
		PurchaseOrder insertedPO = orderRepo.save(purchaseOrder);
		InventoryStrategy strategy = new InventoryStrategy(new OrderStrategy
				(orderRepo, productRepo, insertedPO ,purchaseOrder));
		strategy.executeAdd();
	}
}
