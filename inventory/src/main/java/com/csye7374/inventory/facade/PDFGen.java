package com.csye7374.inventory.facade;

import com.csye7374.inventory.model.Invoice;
import com.csye7374.inventory.repository.InvoiceRepository;

import java.util.Optional;

public class PDFGen extends Facade{

	@Override
	protected void udpTrigger(String msg) {
	}

	@Override
	protected void pdfGen(int invoiceID, InvoiceRepository invoiceRepo) {
		Optional<Invoice> insertedInvoice = invoiceRepo.findById(invoiceID);
		PDFGenerator pdf = new PDFGenerator();
		pdf.generatePDF(insertedInvoice.get());
	}

	/**
	 *
	 * @param invoiceID
	 * @param invoiceRepo
	 *
	 * Client should not worry about internal logic and just call this facade function
	 */
	public static void pdfGenerator(int invoiceID, InvoiceRepository invoiceRepo) {
		new PDFGen().pdfGen(invoiceID, invoiceRepo);
	}
}
