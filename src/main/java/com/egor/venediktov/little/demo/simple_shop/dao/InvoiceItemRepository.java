package com.egor.venediktov.little.demo.simple_shop.dao;

import com.egor.venediktov.little.demo.simple_shop.model.InvoiceItem;
import org.springframework.data.repository.CrudRepository;

public interface InvoiceItemRepository extends CrudRepository<InvoiceItem, Long> {
}
