package com.whatsupd.orderservice.service;

import com.whatsupd.orderservice.model.OrderRequest;
import com.whatsupd.orderservice.model.OrderResponse;

public interface OrderService {
  long placeOrder(OrderRequest orderRequest);

  OrderResponse getOrderdetails(long id);
}
