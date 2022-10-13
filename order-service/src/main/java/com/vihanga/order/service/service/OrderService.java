package com.vihanga.order.service.service;


import com.vihanga.order.service.dto.OrderRequest;

public interface OrderService {
    void placeOrder(OrderRequest orderRequest);
}
