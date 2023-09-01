package com.whatsupd.orderservice.external.client;

import com.whatsupd.orderservice.model.PaymentRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "PAYMENTS-SERVICE/payment")
public interface PaymentService {
  @PostMapping
  public ResponseEntity<Long> doPayment(@RequestBody PaymentRequest request);
}
