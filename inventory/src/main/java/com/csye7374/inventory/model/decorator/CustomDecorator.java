package com.csye7374.inventory.model.decorator;

import com.csye7374.inventory.InventoryCartAPI;
import com.csye7374.inventory.model.ProductPO;

public class CustomDecorator extends CartDecorator {

	int quantity;
	double price;
	
	public CustomDecorator(InventoryCartAPI cart, Product product, ProductPO proPo) {
		super(cart);
		this.quantity = proPo.getQuantity();
		this.price = product.getPrice();
	}

	public double getCost() {
		return super.getCost()+(this.quantity * this.price);
	}
}
