package com.whatsupd.paymentservice.controller;

import com.whatsupd.paymentservice.model.PaymentRequest;
import com.whatsupd.paymentservice.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/payment")
public class PaymentController {

  @Autowired
  private PaymentService paymentService;

@PostMapping
  public ResponseEntity<Long> doPayment(@RequestBody PaymentRequest request){
  return new ResponseEntity<Long>(
          paymentService.doPayment(request),
          HttpStatus.OK
  );
}


}
