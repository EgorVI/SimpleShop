package com.egor.venediktov.little.demo.simple_shop.port.input.rest;

import com.egor.venediktov.little.demo.simple_shop.exception.NotFoundException;
import com.egor.venediktov.little.demo.simple_shop.model.Invoice;
import com.egor.venediktov.little.demo.simple_shop.port.input.rest.dto.InvoiceDTO;
import com.egor.venediktov.little.demo.simple_shop.service.InvoiceService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
public class BaseShopController {

    private final InvoiceService invoiceService;

    private final ModelMapper modelMapper;

    public BaseShopController(
            InvoiceService invoiceService,
            ModelMapper modelMapper
    ) {
        this.invoiceService = invoiceService;
        this.modelMapper = modelMapper;
    }

    @RequestMapping("/invoice/{id}")
    public InvoiceDTO getInvoiceById(@PathVariable("id") Long id) {
        try {
            return convertToDto(invoiceService.getInvoiceById(id));
        } catch (NotFoundException nfe){
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, nfe.getMessage(), nfe);
        }
    }

    private InvoiceDTO convertToDto(Invoice invoice) {
        return modelMapper.map(invoice, InvoiceDTO.class);
    }
}
