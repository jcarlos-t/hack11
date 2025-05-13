package com.demo.controller;

import com.demo.dto.OrderRequestDTO;
import com.demo.Service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/orders")
public class ControllerOrder {

    private final OrderService orderService;

    @Autowired
    public ControllerOrder(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping
    public void createOrder(@RequestBody OrderRequestDTO request) {
        orderService.processOrder(request);
    }
}
