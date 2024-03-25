package com.whatsupd.orderservice.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderResponse {
  private long orderId;
  private Instant orderDate;
  private String orderStatus;
  private long amount;
  private ProductDetails productDetails;

  //create a new class insted of static class
  @Data
  @AllArgsConstructor
  @NoArgsConstructor
  @Builder
  public static class ProductDetails {

    private String productName;
    private long productId;
    private long quantity;
    private long productPrice;
  }
}
