package com.csye7374.inventory.model.decorator;

import com.csye7374.inventory.InventoryCartAPI;

public class Product extends InventoryCartAPI {

	double totalCost;
	
	public Product() {
		this.totalCost = 0;
	}

	@Override
	public double getCost() {
		return this.totalCost;
	}

	public double getPrice() {
		return 0.0;
	}
}
