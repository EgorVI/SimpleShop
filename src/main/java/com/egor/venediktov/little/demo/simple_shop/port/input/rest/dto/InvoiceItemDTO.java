package com.egor.venediktov.little.demo.simple_shop.port.input.rest.dto;

import lombok.Data;

@Data
public class InvoiceItemDTO {

    private Long id;

    private String name;

    private Integer amount;

    private Integer minimalUnitsPrice;

}
