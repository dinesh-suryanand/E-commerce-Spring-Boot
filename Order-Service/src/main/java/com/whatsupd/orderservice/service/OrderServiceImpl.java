package com.whatsupd.orderservice.service;

import com.whatsupd.orderservice.entity.Order;
import com.whatsupd.orderservice.exception.CustomException;
import com.whatsupd.orderservice.external.client.PaymentService;
import com.whatsupd.orderservice.external.client.ProductService;
import com.whatsupd.orderservice.model.OrderRequest;
import com.whatsupd.orderservice.model.OrderResponse;
import com.whatsupd.orderservice.model.PaymentRequest;
import com.whatsupd.orderservice.repository.OrderRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.Instant;

@Service
@Slf4j
public class OrderServiceImpl implements OrderService{

  @Autowired
  private OrderRepository orderRepository;

  @Autowired
  private ProductService productService;

  @Autowired
  private PaymentService paymentService;

  @Autowired
  private RestTemplate restTemplate;

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

    log.info("calling payments service");

    PaymentRequest paymentRequest = PaymentRequest.builder()
            .paymentMode(orderRequest.getPaymentMode())
            .orderId(order.getId())
            .amount(orderRequest.getTotalAmount())
            .build();
    String orderStatus = null;
    try {
      paymentService.doPayment(paymentRequest);
      log.info("Payment done successfully");
      orderStatus = "PLACED";

    } catch (Exception e){
      log.error("Error occurred in payment. changing orderStatus ");
      orderStatus = "PAYMENT_FAILED";
    }

    order.setOrderStatus(orderStatus);
    orderRepository.save(order);

    log.info("order with id {} placed successfully",savedOrder.getId());

    return savedOrder.getId();
  }

  @Override
  public OrderResponse getOrderdetails(long orderId) {
    log.info("Getting order information for orderId : {}", orderId);

    Order order = orderRepository.findById(orderId)
            .orElseThrow(()-> new CustomException("Order not found for given id ", "NOT_FOUND", 404));

    log.info("invoking product service to fetch the product id {}",order.getProductId());

    Pro

    return OrderResponse.builder()
            .orderId(order.getId())
            .orderStatus(order.getOrderStatus())
            .amount(order.getAmount())
            .orderDate(order.getOrderDate())
            .build();
  }
}
