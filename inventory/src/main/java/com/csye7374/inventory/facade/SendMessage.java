package com.csye7374.inventory.facade;

import com.csye7374.inventory.async.factory.CommunicationInstanceFactory;
import com.csye7374.inventory.repository.InvoiceRepository;

public class SendMessage extends Facade{

	@Override
	protected void udpTrigger(String msg) {
		new CommunicationInstanceFactory().getObject().triggerServerClient(msg);
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
	}
	
	public static void message(String msg) {
		
		SendMessage send = new SendMessage();
		send.udpTrigger(msg);
	
	}

	@Override
	protected void pdfGen(int id, InvoiceRepository invoiceRepo) {
	}

}
