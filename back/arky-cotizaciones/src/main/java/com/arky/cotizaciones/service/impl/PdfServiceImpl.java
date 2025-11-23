package com.arky.cotizaciones.service.impl;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.arky.cotizaciones.model.Item;
import com.arky.cotizaciones.model.Quotation;
import com.arky.cotizaciones.repository.QuotationRepository;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

@Service
public class PdfServiceImpl {

	@Autowired
    private QuotationRepository quotationRepository;

    public PdfServiceImpl(QuotationRepository quotationRepository) {
        this.quotationRepository = quotationRepository;
    }


    // Metodo para generar el PDF  de la cotizacion
    public ByteArrayInputStream generatePdf(int quotationId) throws DocumentException {

        // Recuperar la cotización con sus relaciones
        Quotation quotation = quotationRepository.findById(quotationId)
                .orElseThrow(() -> new RuntimeException("Cotización no encontrada"));

        Document document = new Document();
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        PdfWriter.getInstance(document, out);
        document.open();

        // Agregar información básica de la cotización
        document.add(new Paragraph("Cotización ID: " + quotation.getQuotationId()));
        document.add(new Paragraph("Remitente: " + quotation.getRemitter()));
        document.add(new Paragraph("Cliente: " + quotation.getCustomer().getName()));
        document.add(new Paragraph("Usuario: " + quotation.getUser().getFirstname()+ quotation.getUser().getLastname()));
        document.add(new Paragraph("Fecha de Cotización: " + quotation.getDateQuotation()));
        document.add(new Paragraph("Fecha de Evento: " + quotation.getDateEvent()));
        document.add(new Paragraph("Referencia: " + quotation.getReference()));

        // Agregar información del descuento, si aplica
        if (quotation.getDiscount() != null) {
            document.add(new Paragraph("Descuento: " + quotation.getDiscount().getPercentage() + "%"));
        }

        document.add(new Paragraph("Neto: " + quotation.getNet()));
        document.add(new Paragraph("IVA: " + quotation.getIva()));
        document.add(new Paragraph("Total: " + quotation.getTotal()));

        // Añadir los ítems de la cotización
        document.add(new Paragraph("\nItems:"));
        for (Item item : quotation.getItems()) {
            document.add(new Paragraph("Producto: " + item.getProduct().getName()));
            document.add(new Paragraph("Cantidad: " + item.getQuantity()));
            document.add(new Paragraph("Valor unitario: " + item.getUnitValue()));
            document.add(new Paragraph("Valor total: " + item.getTotalValue()));
        }

        document.close();

        return new ByteArrayInputStream(out.toByteArray());
    }
}
