package com.whatsupd.productservice.service;

import com.whatsupd.productservice.entity.Product;
import com.whatsupd.productservice.exception.ProductServiceCustomException;
import com.whatsupd.productservice.mode.ProductRequest;
import com.whatsupd.productservice.mode.ProductResponse;
import com.whatsupd.productservice.repository.ProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static org.springframework.beans.BeanUtils.copyProperties;

@Service
@Slf4j
public class ProductServiceImpl implements ProductService {

  @Autowired
  private ProductRepository productRepository;

  @Override
  public long addProduct(ProductRequest productRequest) {
    log.info("creating product");
    Product product = Product.builder()
            .productName(productRequest.getName())
            .productPrice(productRequest.getPrice())
            .quantity(productRequest.getQuantity())
            .build();

    productRepository.save(product);
    log.info("product created");

    return product.getProductId();

  }

  @Override
  public ProductResponse getProduct(long id) {
    Product product = productRepository.findById(id).orElseThrow(() ->
                    new ProductServiceCustomException("product with given id  not found", "PRODUCT_NOT_FOUND"));

    ProductResponse response = new ProductResponse();

    copyProperties(product, response);
    return response;
  }

  @Override
  public void reduceQuantity(long id, long quantity) {
    log.info("reducing quantity of {} for id {}",quantity,id);
    Product product = productRepository.findById(id)
            .orElseThrow(() -> new ProductServiceCustomException(
                    "product is with given id is not present" , "PRODUCT_NOT_FOUND"
            ));

    if (product.getQuantity() < quantity) {
      throw new ProductServiceCustomException(
              "product does not have enough quantity","INSUFFICIENT_QUANTITY"
      );
    }

    product.setQuantity(product.getQuantity() - quantity);
    log.info("Product quantity is updated successfully");

  }
}
