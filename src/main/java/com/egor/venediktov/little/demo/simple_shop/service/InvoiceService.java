package com.egor.venediktov.little.demo.simple_shop.service;

import com.egor.venediktov.little.demo.simple_shop.dao.DiscountRepository;
import com.egor.venediktov.little.demo.simple_shop.dao.InvoiceRepository;
import com.egor.venediktov.little.demo.simple_shop.exception.NotFoundException;
import com.egor.venediktov.little.demo.simple_shop.model.Discount;
import com.egor.venediktov.little.demo.simple_shop.model.Invoice;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


@Service
@Slf4j
public class InvoiceService {

    private static final String SEASONAL_DISCOUNT_TYPE = "seasonal";
    public static final int ONE_HUNDRED_PERCENT = 100;

    InvoiceRepository invoiceRepository;

    DiscountRepository discountRepository;

    public InvoiceService(InvoiceRepository invoiceRepository, DiscountRepository discountRepository) {
        this.invoiceRepository = invoiceRepository;
        this.discountRepository = discountRepository;
    }

    public Invoice getInvoiceById(Long invoiceId) throws NotFoundException {
        log.info("getInvoiceById start, invoiceId: " + invoiceId);
        Invoice invoice = invoiceRepository.findById(invoiceId)
                .orElseThrow(() -> new NotFoundException(Invoice.class, invoiceId));
        defineFinalPrice(invoice);
        log.info("getInvoiceById end, calculated invoice: " + invoice);
        return invoice;
    }

    private void defineFinalPrice(Invoice invoice) {
        if (invoice.getFinalMinimalUnitsPrice() != null )
            return;
        Discount discount = discountRepository.getSeasonalDiscount(SEASONAL_DISCOUNT_TYPE);
        int totalItemPrice = invoice.getItems().stream()
                .mapToInt(invoiceItem -> invoiceItem.getMinimalUnitsPrice() * invoiceItem.getAmount())
                .sum();
        invoice.setFinalMinimalUnitsPrice(totalItemPrice - (totalItemPrice * discount.getPercentAmount() / ONE_HUNDRED_PERCENT));
    }
}
