package com.demo.controller;

import com.demo.OrderCreatedEvent;
import com.demo.dto.OrderRequestDTO;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orders")
public class ControllerOrder {
    private final ApplicationEventPublisher publisher;

    public ControllerOrder(ApplicationEventPublisher publisher) {
        this.publisher = publisher;
    }

    @PostMapping
    public ResponseEntity<String> createOrder(@RequestBody OrderRequestDTO request) {
        publisher.publishEvent(new OrderCreatedEvent(
                this,
                request.getOrderId(),
                request.getEmail(),
                request.getProductos(),
                request.getCantidad()
        ));
        return ResponseEntity.ok("Pedido recibido y evento publicado.");
    }

}
