package com.csye7374.inventory.alert;

public interface StateAPI {
    void increaseStock(int stock);
    void alertStock(int stock);
}