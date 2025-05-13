package com.demo.listener;

import com.demo.OrderCreatedEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
public class EmailNotificationListener {
    private static final Logger logger = LogManager.getLogger(EmailNotificationListener.class);

    @Async
    @EventListener
    public void handleOrderCreated(OrderCreatedEvent event) {
        logger.info("Enviando correo a {} por el pedido {}", event.getEmail(), event.getOrderId());
    }
}
