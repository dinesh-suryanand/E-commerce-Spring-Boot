package com.whatsupd.productservice.service;

import com.whatsupd.productservice.mode.ProductRequest;
import com.whatsupd.productservice.mode.ProductResponse;
import org.springframework.stereotype.Service;

@Service
public interface ProductService {

  public long addProduct(ProductRequest productRequest);

  ProductResponse getProduct(long id);
}
