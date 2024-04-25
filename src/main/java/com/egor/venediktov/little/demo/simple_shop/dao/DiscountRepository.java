package com.egor.venediktov.little.demo.simple_shop.dao;

import com.egor.venediktov.little.demo.simple_shop.model.Discount;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface DiscountRepository extends CrudRepository<Discount, Long> {

    @Query("from Discount d where d.discountType =:discountType")
    Discount getSeasonalDiscount(String discountType);

}
