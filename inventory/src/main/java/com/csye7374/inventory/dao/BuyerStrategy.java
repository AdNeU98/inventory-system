package com.csye7374.inventory.dao;


import com.csye7374.inventory.model.Buyer;
import com.csye7374.inventory.repository.BuyerRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

public class BuyerStrategy implements StrategyAPI {
    private int id;
    private final BuyerRepository buyerRepo;
    private Buyer buyer;

    public BuyerStrategy(BuyerRepository buyerRepo, Buyer buy) {
        this.buyerRepo = buyerRepo;
        this.buyer = buy;
    }

    public BuyerStrategy(BuyerRepository buyerRepo, int id) {
        this.id = id;
        this.buyerRepo = buyerRepo;
    }
    @Override
    public void add() {
        if (this.buyerRepo.findByCompanyName(this.buyer.getCompanyName()).isPresent()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Company already exists");
        } else {
            this.buyerRepo.save(this.buyer);
        }
    }

    @Override
    public void update(int id) {

        Optional<Buyer> buyer = this.buyerRepo.findById(id);
        if (buyer.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Buyer does not exist");
        }
        buyer.get().setCompanyName(this.buyer.getCompanyName());
        buyer.get().setOrders(this.buyer.getOrders());
        buyer.get().setZipcode(this.buyer.getZipcode());
        buyer.get().setOwnerName(this.buyer.getOwnerName());
        this.buyerRepo.save(buyer.get());
    }
    @Override
    public void delete() {
        Optional<Buyer> buyer = this.buyerRepo.findById(this.id);
        if (buyer.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Buyer does not exist");
        }
        this.buyerRepo.delete(buyer.get());
    }
}

