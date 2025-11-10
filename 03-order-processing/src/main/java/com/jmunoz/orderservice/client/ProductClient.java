package com.jmunoz.orderservice.client;

import com.jmunoz.orderservice.model.product.ProductStatus;

public interface ProductClient {

    ProductStatus getProductStatus(String productId);
}
