package com.whatsupd.OrderService.external.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "PRODUCT-SERVICE/products")
public interface ProductService {
  @PutMapping("reduceQuantity/{id}")
  ResponseEntity<Void> reduceProduct(@PathVariable long id, @RequestParam long quantity);
}
