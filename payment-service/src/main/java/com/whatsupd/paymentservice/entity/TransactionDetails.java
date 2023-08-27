package com.whatsupd.paymentservice.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Entity
@Table(name = "TRANSACTION_DETAILS")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TransactionDetails {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private long id;
  @Column(name = "ORDER_ID")
  private long orderId;
  //change it to enum later
  @Column(name = "MODE")
  private String paymentMode;
  @Column(name = "REFERENCE_NUMBER")
  private String referenceNumber;
  @Column(name = "PAYMENT_DATE")
  private Instant paymentDate;
  //can be an enum
  @Column(name = "STATUS")
  private String status;
  @Column(name = "AMOUNT")
  private long amount;
}
