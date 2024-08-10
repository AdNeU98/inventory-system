package com.csye7374.inventory.controller;

import com.csye7374.inventory.dao.InventoryStrategy;
import com.csye7374.inventory.dao.InvoiceStrategy;
import com.csye7374.inventory.model.Invoice;
import com.csye7374.inventory.repository.InvoiceRepository;
import com.csye7374.inventory.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @apiNote - REST Controller for Invoice
 */
@RestController
@RequestMapping("/invoice")
public class InvoiceController {

    @Autowired
    private InvoiceRepository invoiceRepo;

    @Autowired
    private OrderRepository purchaseOrderRepo;

    @GetMapping("/getAll")
    public List<Invoice> getAll() {
        return invoiceRepo.findAll();
    }

    @GetMapping("/getInvoice/{id}")
    public Invoice getInvoice(@PathVariable int id) {
        return invoiceRepo.findById(id).get();
    }

    @PostMapping("/generateInvoice/{id}")
    public void save(@PathVariable int id) {

        InventoryStrategy strategy = new InventoryStrategy(new InvoiceStrategy(invoiceRepo, id, purchaseOrderRepo));
        strategy.executeAdd();
    }

    @PutMapping("/update/{id}")
    public void update(@RequestBody Invoice invoice, @PathVariable int id) {
        InventoryStrategy strategy = new InventoryStrategy(new InvoiceStrategy(invoiceRepo, invoice));
        strategy.executeUpdate(id);
    }

    @DeleteMapping("/delete/{id}")
    public void deletebyID(@PathVariable int id) {
        InventoryStrategy strategy = new InventoryStrategy(new InvoiceStrategy(invoiceRepo, id));
        strategy.executeDelete();
    }
}
