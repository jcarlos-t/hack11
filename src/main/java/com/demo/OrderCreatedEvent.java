package com.demo;

import org.springframework.context.ApplicationEvent;

import java.util.List;

public class OrderCreatedEvent extends ApplicationEvent {
    private final String orderId;
    private final String email;
    private final List<String> productos;
    private final List<Integer> cantidad;

    public OrderCreatedEvent(Object source, String orderId, String email, List<String> productos, List<Integer> cantidad) {
        super(source);
        this.orderId = orderId;
        this.email = email;
        this.productos = productos;
        this.cantidad = cantidad;
    }

    public String getOrderId() {
        return orderId;
    }

    public String getEmail() {
        return email;
    }

    public List<String> getProductos() {
        return productos;
    }

    public List<Integer> getCantidad() {
        return cantidad;
    }
}
