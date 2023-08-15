package com.whatsupd.OrderService.service;

import com.whatsupd.OrderService.entity.Order;
import com.whatsupd.OrderService.external.client.ProductService;
import com.whatsupd.OrderService.model.OrderRequest;
import com.whatsupd.OrderService.repository.OrderRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
@Slf4j
public class OrderServiceImpl implements OrderService{

  @Autowired
  private OrderRepository orderRepository;

  @Autowired
  private ProductService productService;

  @Override
  public long placeOrder(OrderRequest orderRequest) {
    //1 Order Entity -> save the data with Status Order Created.
    //2 call Product Service -> to reduce quantity.  implemented before creating order line 31
    //3 call Payment Service -> to complete the tansation
    // payment Success -> Complete , payment Failed -> Cancelled

    //rest api call using feign client
    productService.reduceProduct(orderRequest.getProductId(),orderRequest.getQuantity());

    log.info("placing order request");
    Order order = Order.builder()
            .amount(orderRequest.getTotalAmount())
            .orderStatus("CREATED")
            .productId(orderRequest.getProductId())
            .orderDate(Instant.now())
            .quantity(orderRequest.getQuantity())
            .build();
    Order savedOrder = orderRepository.save(order);
    log.info("order with id {} placed successfully",savedOrder.getId());

    return savedOrder.getId();
  }
}
