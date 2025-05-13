package com.demo.listener;

import com.demo.OrderCreatedEvent;
import jakarta.annotation.PostConstruct;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class InventoryUpdateListener {
    private static final Logger logger = LogManager.getLogger(InventoryUpdateListener.class);

    @PostConstruct
    public void init() {
        logger.info("🔥 Log4j está funcionando");
        logger.warn("⚠️ Esto es una advertencia");
        logger.error("❌ Esto es un error simulado");
    }

    @EventListener
    public void handleOrderCreated(OrderCreatedEvent event) {
        logger.info("📦 Actualizando inventario para los productos: {}", event.getProductos());
    }
}
