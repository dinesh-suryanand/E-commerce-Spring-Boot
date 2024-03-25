package com.whatsupd.orderservice.controller;

import com.whatsupd.orderservice.model.OrderRequest;
import com.whatsupd.orderservice.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/order")
@Slf4j
public class OrderController {

  @Autowired
  private OrderService orderService;


@PostMapping("/placeOrder") //comment
  public ResponseEntity<Long> placeOrder(@RequestBody OrderRequest orderRequest) {
    long orderId = orderService.placeOrder(orderRequest);
    log.info("order is created with id {}",orderId);

    return new  ResponseEntity<>(orderId, HttpStatus.OK);
  }

  //comment on this 
}
