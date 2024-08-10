package com.csye7374.inventory;

import com.csye7374.inventory.model.Product;
import com.csye7374.inventory.model.ProductPO;

public abstract class InventoryCartAPI {
    public InventoryCartAPI(InventoryCartAPI cart, Product product, ProductPO proPO) {
    }

    public InventoryCartAPI() {

    }

    public abstract double getCost();
}
