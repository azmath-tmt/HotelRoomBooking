package com.hotelbooking.dao;

import com.hotelbooking.model.Discount;

import java.util.List;

public interface DiscountDao {
    Discount getDiscount(String discountCode);

    List<Discount> availableDiscount();


}
