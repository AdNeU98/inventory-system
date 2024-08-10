package com.csye7374.inventory.notify;

import com.csye7374.inventory.model.Product;
import com.csye7374.inventory.repository.BuyerRepository;

public class UpdateBuyers extends ObserverAPI{
    private final BuyerRepository buyerRepo;
    public UpdateBuyers(Notify notify, BuyerRepository buyerRepo) {
        this.notify = notify;
        this.notify.attach(this);
        this.buyerRepo = buyerRepo;
    }
    @Override
    public void update(Product product) {
        NotifyBuyers notify = new NotifyBuyers(product, this.buyerRepo);
        notify.notifyAllBuyers();
    }
}
