package com.demo.Service;

import com.demo.dto.OrderRequestDTO;

public interface OrderService {
    void processOrder(OrderRequestDTO orderRequestDTO);
}