package com.whatsupd.paymentservice.service;

import com.whatsupd.paymentservice.model.PaymentRequest;
import org.springframework.http.HttpStatusCode;

public interface PaymentService {
  long doPayment(PaymentRequest paymentRequest);
}
