package com.csye7374.inventory.notify;

import com.csye7374.inventory.facade.FacadeClient;
import com.csye7374.inventory.facade.SendMessage;
import com.csye7374.inventory.model.Buyer;
import com.csye7374.inventory.model.Product;
import com.csye7374.inventory.repository.BuyerRepository;

import java.util.Iterator;
import java.util.List;

public class NotifyBuyers extends Buyer{
    private List<Buyer> buyers;
    private Product product;
    private BuyerRepository buyerRepo;

    public NotifyBuyers(Product product, BuyerRepository buyerRepo) {
        this.product = product;
        this.buyerRepo = buyerRepo;
    }

    public void notifyAllBuyers() {
        StringBuilder sb = new StringBuilder();
        this.buyers = this.buyerRepo.findAll();

        for (Buyer buyer : this.buyers) {
            System.out.println("Notification sent to " + buyer.getOwnerName() + " regarding the " + this.product.getProductName() + " addition");
            sb.append("Hello ").append(buyer.getOwnerName()).append(", ");
            sb.append(this.product.getProductName()).append(" is available for purchase now\n");
            FacadeClient facadeClient = new FacadeClient(new SendMessage());
            facadeClient.sendMessage(sb.toString());
        }

    }
}
