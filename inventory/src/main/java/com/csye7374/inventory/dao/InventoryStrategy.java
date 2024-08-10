package com.csye7374.inventory.dao;


public class InventoryStrategy {
    private StrategyAPI strategy;

    public InventoryStrategy(StrategyAPI strategy) {
        this.strategy = strategy;
    }

    public void executeAdd() {
        this.strategy.add();
    }

    public void executeDelete() {
        this.strategy.delete();
    }

    public void executeUpdate(int id) {
        this.strategy.update(id);
    }
}
