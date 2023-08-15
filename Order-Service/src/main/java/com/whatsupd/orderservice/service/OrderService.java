package com.whatsupd.OrderService.service;

import com.whatsupd.OrderService.model.OrderRequest;

public interface OrderService {
  long placeOrder(OrderRequest orderRequest);
}
