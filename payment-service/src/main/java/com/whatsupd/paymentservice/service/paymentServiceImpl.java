package com.whatsupd.paymentservice.service;

import com.whatsupd.paymentservice.entity.TransactionDetails;
import com.whatsupd.paymentservice.model.PaymentRequest;
import com.whatsupd.paymentservice.repository.TransactionDetailsRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
@Slf4j
public class paymentServiceImpl implements PaymentService{

  @Autowired
  private TransactionDetailsRepo transactionDetailsRepo;

  @Override
  public long doPayment(PaymentRequest paymentRequest) {
    log.info("Recording payment details {}",paymentRequest);

    TransactionDetails transactionDetails =
            TransactionDetails.builder()
                    .paymentDate(Instant.now())
                    .paymentMode(paymentRequest.getPaymentMode().name())
                    .status("SUCCESS")
                    .orderId(paymentRequest.getOrderId())
                    .referenceNumber(paymentRequest.getReferenceNumber())
                    .amount(paymentRequest.getAmount())
                    .build();

    transactionDetailsRepo.save(transactionDetails);

    log.info("Transaction Completed with Id: {}",transactionDetails.getId());

    return transactionDetails.getId();
  }
}
