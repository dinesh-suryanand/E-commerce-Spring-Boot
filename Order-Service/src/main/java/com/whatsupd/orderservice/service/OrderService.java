package com.whatsupd.orderservice.service;

import com.whatsupd.orderservice.model.OrderRequest;

public interface OrderService {
  long placeOrder(OrderRequest orderRequest);
}
