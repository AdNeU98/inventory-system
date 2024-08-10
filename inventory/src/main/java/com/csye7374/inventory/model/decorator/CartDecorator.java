package com.csye7374.inventory.model.decorator;

import com.csye7374.inventory.InventoryCartAPI;

public class CartDecorator extends InventoryCartAPI{
	
	InventoryCartAPI cart;
		
	public CartDecorator(InventoryCartAPI cart) {
		this.cart = cart;
	}

	@Override
	public double getCost() {
		return this.cart.getCost();
	}

}
