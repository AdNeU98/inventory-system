package com.csye7374.inventory.alert;

public class State implements StateAPI {

    public State() {
    }
    @Override
    public void increaseStock(int stock) {
        System.out.println("Err! Default state, cannot increase stock ");
    }

    @Override
    public void alertStock(int stock) {
        System.out.println("Err! Default state, cannot alert stock ");
    }
}