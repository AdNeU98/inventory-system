package com.csye7374.inventory.facade;

import com.csye7374.inventory.model.Invoice;
import com.csye7374.inventory.model.ProductPO;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.*;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class PDFGenerator {

	private Invoice invoice;

	public void generatePDF(Invoice invoice) {
		this.invoice = invoice;
		Document document = new Document();

		try {
			String filename = "Invoice_" + invoice.getId() + "_" + invoice.getPurchaseOrder().getBuyer().getCompanyName() + ".pdf";
			PdfWriter.getInstance(document, new FileOutputStream(filename));

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (DocumentException e) {
			throw new RuntimeException(e);
		}
		document.open();

		Font font1 = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 20, BaseColor.BLACK);
		Font font2 = FontFactory.getFont(FontFactory.HELVETICA, 16, BaseColor.BLACK);
		Paragraph p1 = new Paragraph("Invoice for your Purchase", font1);

		Paragraph p2 = new Paragraph("Payment Date: " + invoice.getPaymentDate(), font2);

		PdfPTable table = new PdfPTable(4);
		addTableHeader(table);
		addRows(table);
		try {
			document.add(p1);
			document.add(p2);
			document.add( Chunk.NEWLINE );
			document.add(table);
		} catch (DocumentException e) {
			e.printStackTrace();
		}
		document.close();
	}

	private void addTableHeader(PdfPTable table) {
		Stream.of("Product", "Quantity", "Unit Price", "Total Price").forEach(columnTitle -> {
			PdfPCell header = new PdfPCell();
			header.setBackgroundColor(BaseColor.LIGHT_GRAY);
			header.setBorderWidth(2);
			header.setPhrase(new Phrase(columnTitle));
			table.addCell(header);
		});
	}

	private void addRows(PdfPTable table) {

		List<ProductPO> productPOs = invoice.getPurchaseOrder().getProducts();

		List<ProductPO> distinctProductList = productPOs.stream().distinct().toList();
		for(ProductPO productPO : distinctProductList) {
			table.addCell(productPO.getProduct().getProductName());
			table.addCell(String.valueOf(productPO.getQuantity()));
			table.addCell("$" + productPO.getProduct().getPrice());
			double result = productPO.getProduct().getPrice() * productPO.getQuantity();
			table.addCell("$" + result);
		}

		table.addCell("Total");
		table.addCell("---");
		table.addCell("---");
		table.addCell("$" + invoice.getPurchaseOrder().getTotalAmount());
	}
}
