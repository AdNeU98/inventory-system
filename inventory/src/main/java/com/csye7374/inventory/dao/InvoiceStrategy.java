package com.csye7374.inventory.dao;


import com.csye7374.inventory.facade.FacadeClient;
import com.csye7374.inventory.facade.PDFGen;
import com.csye7374.inventory.model.Invoice;
import com.csye7374.inventory.model.PurchaseOrder;
import com.csye7374.inventory.repository.InvoiceRepository;
import com.csye7374.inventory.repository.OrderRepository;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;

public class InvoiceStrategy implements StrategyAPI {
    private InvoiceRepository invoiceRepo;
    private int id;
    private Invoice invoice;
    private OrderRepository orderRepo;

    public InvoiceStrategy(InvoiceRepository invoiceRepo, Invoice invoice) {
        this.invoiceRepo = invoiceRepo;
        this.invoice = invoice;
    }

    public InvoiceStrategy(InvoiceRepository invoiceRepo, int id, OrderRepository orderRepo) {
        this.invoiceRepo = invoiceRepo;
        this.id = id;
        this.orderRepo = orderRepo;
    }

    public InvoiceStrategy(InvoiceRepository invoiceRepo, int id) {
        this.invoiceRepo = invoiceRepo;
        this.id = id;
    }
    @Override
    public void add() {
        Optional<PurchaseOrder> po = this.orderRepo.findById(this.id);
        if (po.isEmpty()) {
            throw new RuntimeException("Purchase order does not exist");
        }
        po.get().setPaid(true);
        this.orderRepo.save(po.get());
        SimpleDateFormat formatter = new SimpleDateFormat("MM-dd-yyyy");
        Date date = new Date();
        String paymentDate = formatter.format(date);
        Invoice invoice = new Invoice();
        invoice.setPaymentDate(paymentDate);
        invoice.setPurchaseOrder(po.get());
        int invoiceID = this.invoiceRepo.save(invoice).getId();
        FacadeClient facadeClient = new FacadeClient(new PDFGen());
        facadeClient.generatePDF(invoiceID, this.invoiceRepo);
    }
    @Override
    public void update(int id) {
        Optional<Invoice> invoice = this.invoiceRepo.findById(id);
        if (invoice.isEmpty()) {
            throw new RuntimeException("Invoice does not exist");
        }
        invoice.get().setPaymentDate(this.invoice.getPaymentDate());
        invoice.get().setPurchaseOrder(this.invoice.getPurchaseOrder());
        this.invoiceRepo.save(invoice.get());
    }

    @Override
    public void delete() {
        Optional<Invoice> invoice = this.invoiceRepo.findById(this.id);
        this.invoiceRepo.delete(invoice.get());
    }
}

