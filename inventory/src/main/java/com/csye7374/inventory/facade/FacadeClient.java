package com.csye7374.inventory.facade;

import com.csye7374.inventory.repository.InvoiceRepository;

public class FacadeClient {
    private final Facade facade;
    public FacadeClient(Facade facadeAPI) {
        this.facade = facadeAPI;
    }
    public void sendMessage(String msg) {
    	facade.udpTrigger(msg);
    }
    public void generatePDF(int invoiceID, InvoiceRepository invoiceRepo) {
        facade.pdfGen(invoiceID, invoiceRepo);
    }
}
