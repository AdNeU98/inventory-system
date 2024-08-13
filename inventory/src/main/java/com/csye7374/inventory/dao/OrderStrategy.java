package com.csye7374.inventory.dao;

import com.csye7374.inventory.InventoryCartAPI;
import com.csye7374.inventory.alert.StateAPI;
import com.csye7374.inventory.alert.StockLow;
import com.csye7374.inventory.alert.StockPurchase;
import com.csye7374.inventory.model.ProductPO;
import com.csye7374.inventory.model.PurchaseOrder;
import com.csye7374.inventory.repository.OrderRepository;
import com.csye7374.inventory.repository.ProductRepository;

import java.util.Iterator;
import java.util.List;
import java.util.Optional;

public class OrderStrategy implements StrategyAPI {
    private final OrderRepository orderRepo;
    private ProductRepository productRepo;
    private int id;
    private PurchaseOrder purchaseOrder;
    private PurchaseOrder insertedPO;

    public OrderStrategy(OrderRepository orderRepo, ProductRepository productRepo, PurchaseOrder insertedPO, PurchaseOrder purchaseOrder) {
        this.orderRepo = orderRepo;
        this.productRepo = productRepo;
        this.purchaseOrder = purchaseOrder;
        this.insertedPO = insertedPO;
    }

    public OrderStrategy(OrderRepository orderRepo, PurchaseOrder purchaseOrder) {
        this.orderRepo = orderRepo;
        this.purchaseOrder = purchaseOrder;
    }

    public OrderStrategy(OrderRepository orderRepo, int id) {
        this.orderRepo = orderRepo;
        this.id = id;
    }
    @Override
    public void add() {
        InventoryCartAPI cart = new InventoryCartAPI() {
            @Override
            public double getCost() {
                return 0;
            }
        };
        System.out.println("Inserted Product Order ID " + this.insertedPO.getId());
        List<ProductPO> productPOs = this.insertedPO.getProducts();
        Iterator var4 = productPOs.iterator();

        while(var4.hasNext()) {
            ProductPO proPO = (ProductPO)var4.next();
            StateAPI state;
            com.csye7374.inventory.model.Product product = proPO.getProduct();
            cart = new InventoryCartAPI((InventoryCartAPI) cart, product, proPO) {
                @Override
                public double getCost() {
                    return 0;
                }
            };
            product.setQuantity(product.getQuantity() - proPO.getQuantity());
            productRepo.save(product);

            int difference = product.getQuantity() - proPO.getQuantity();
            int count;

            if (difference <= 0.25 * product.getQuantity()) {
                state = new StockLow(product, this.productRepo);
                count = difference;
                state.alertStock(count);
            } else {
                state = new StockPurchase(product, this.productRepo);
                count = proPO.getQuantity()/2;
                state.increaseStock(count);
            }
        }
    }

    @Override
    public void update(int id) {
        Optional<PurchaseOrder> purchaseOrder = this.orderRepo.findById(id);
        if (purchaseOrder.isEmpty()) {
            throw new RuntimeException("Purchase Order does not exist");
        }
        purchaseOrder.get().setProducts(this.purchaseOrder.getProducts());
        purchaseOrder.get().setBuyer(this.purchaseOrder.getBuyer());
        purchaseOrder.get().setTotalAmount(this.purchaseOrder.getTotalAmount());
        purchaseOrder.get().setPaid(this.purchaseOrder.isPaid());
        purchaseOrder.get().setInvoice(this.purchaseOrder.getInvoice());
        purchaseOrder.get().setPaymentDueDate(this.purchaseOrder.getPaymentDueDate());
        this.orderRepo.save(purchaseOrder.get());
    }

    @Override
    public void delete() {
        Optional<PurchaseOrder> purchaseOrder = this.orderRepo.findById(this.id);
        this.orderRepo.delete(purchaseOrder.get());
    }
}
