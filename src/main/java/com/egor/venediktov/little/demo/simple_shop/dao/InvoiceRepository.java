package com.egor.venediktov.little.demo.simple_shop.dao;

import com.egor.venediktov.little.demo.simple_shop.model.Invoice;
import org.springframework.data.repository.CrudRepository;

public interface InvoiceRepository extends CrudRepository<Invoice, Long> {
}
