package com.demo.dto;

import jdk.jfr.DataAmount;

import java.util.List;

public class OrderRequestDTO {
    private String orderId;
    private String email;
    private List<String> productos;
    private List<Integer> cantidad;

    public String getOrderId() {
        return orderId;
    }
    public List<Integer> getCantidad() {
        return cantidad;
    }

    public String getEmail() {
        return email;
    }

    public List<String> getProductos() {
        return productos;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setProductos(List<String> productos) {
        this.productos = productos;
    }
}
