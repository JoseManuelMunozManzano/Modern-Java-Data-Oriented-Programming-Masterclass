package com.jmunoz.orderservice.client;

import com.jmunoz.orderservice.model.customer.Customer;

public interface CustomerClient {

    Customer getCustomer(String customerId);
}
