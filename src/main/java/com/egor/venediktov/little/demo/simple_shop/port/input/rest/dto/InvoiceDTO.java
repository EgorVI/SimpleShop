package com.egor.venediktov.little.demo.simple_shop.port.input.rest.dto;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class InvoiceDTO {

    private Long id;

    private LocalDateTime issuanceDate;

    private Integer finalMinimalUnitsPrice;

    private List<InvoiceItemDTO> items;
}
