package com.whatsupd.productservice.controller;

import com.whatsupd.productservice.mode.ProductRequest;
import com.whatsupd.productservice.mode.ProductResponse;
import com.whatsupd.productservice.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/products")
public class ProductController {

  @Autowired
  private ProductService productService;

  @GetMapping("/{id}")
  public ResponseEntity<ProductResponse> getProducts(@PathVariable long id){
    ProductResponse productResponse = productService.getProduct(id);
    return new ResponseEntity<>(productResponse,HttpStatus.OK);
  }

  @PostMapping()
  public ResponseEntity<Long> addProduct(@RequestBody ProductRequest productRequest) {
    long id = productService.addProduct(productRequest);
    return new ResponseEntity<>(id , HttpStatus.CREATED);
  }
}
