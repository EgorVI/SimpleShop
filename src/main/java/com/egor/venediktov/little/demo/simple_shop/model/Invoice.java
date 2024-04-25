package com.egor.venediktov.little.demo.simple_shop.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

import static jakarta.persistence.FetchType.EAGER;

@Entity
@Table(name = "invoice")
@Data
public class Invoice {

    public Invoice() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private LocalDateTime issuanceDate;

    private Integer finalMinimalUnitsPrice;

    @OneToMany(mappedBy = "invoice", fetch = EAGER)
    private List<InvoiceItem> items;

}
