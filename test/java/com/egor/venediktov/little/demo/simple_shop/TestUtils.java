package com.egor.venediktov.little.demo.simple_shop;

import com.egor.venediktov.little.demo.simple_shop.model.Discount;
import com.egor.venediktov.little.demo.simple_shop.model.Invoice;
import com.egor.venediktov.little.demo.simple_shop.model.InvoiceItem;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;

public class TestUtils {

    public static Invoice getInvoiceWithItems() {
        Invoice invoice = new Invoice();
        invoice.setIssuanceDate(LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS));
        invoice.setItems(Arrays.asList(getInvoiceItem(invoice), getInvoiceItem(invoice)));
        return invoice;
    }

    public static InvoiceItem getInvoiceItem(Invoice invoice) {
        InvoiceItem invoiceItem = new InvoiceItem();
        invoiceItem.setAmount(1);
        invoiceItem.setName("Some Item");
        invoiceItem.setMinimalUnitsPrice(2);
        invoiceItem.setInvoice(invoice);
        return invoiceItem;
    }

}
