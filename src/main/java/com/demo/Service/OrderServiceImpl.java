package com.demo.Service;

import com.demo.OrderCreatedEvent;
import com.demo.domain.OrderEntity;
import com.demo.domain.OrderRepository;
import com.demo.dto.OrderRequestDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService {

    private final ApplicationEventPublisher eventPublisher;

    private final OrderRepository orderRepository;

    @Autowired
    public OrderServiceImpl(ApplicationEventPublisher eventPublisher, OrderRepository orderRepository) {
        this.eventPublisher = eventPublisher;
        this.orderRepository = orderRepository;
    }

    @Override
    public void processOrder(OrderRequestDTO orderRequestDTO) {
        OrderEntity order = new OrderEntity();
        order.setOrderId(orderRequestDTO.getOrderId());
        order.setEmail(orderRequestDTO.getEmail());
        order.setProductos(orderRequestDTO.getProductos());
        order.setCantidades(orderRequestDTO.getCantidad());
        orderRepository.save(order);

        OrderCreatedEvent event = new OrderCreatedEvent(
                this,
                orderRequestDTO.getOrderId(),
                orderRequestDTO.getEmail(),
                orderRequestDTO.getProductos(),
                orderRequestDTO.getCantidad()
        );
        eventPublisher.publishEvent(event);
        System.out.println("Orden procesada y persistida: " + order.getOrderId());
    }
}
