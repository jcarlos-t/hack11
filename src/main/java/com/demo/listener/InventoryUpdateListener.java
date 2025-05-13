package com.demo.listener;

import com.demo.OrderCreatedEvent;
import jakarta.annotation.PostConstruct;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class InventoryUpdateListener {

    private static final Logger logger = LogManager.getLogger(InventoryUpdateListener.class);

    private final Map<String, Integer> inventario = new ConcurrentHashMap<>();

    @PostConstruct
    public void init() {
        // Inicializamos el inventario simulado
        inventario.put("Café", 10);
        inventario.put("Azúcar", 20);
        inventario.put("Pan", 15);
        inventario.put("Leche", 8);

        logger.info("Inventario inicial cargado: {}", inventario);
    }

    @Async
    @EventListener
    public void handleOrderCreated(OrderCreatedEvent event) {
        List<String> productos = event.getProductos();

        for (String producto : productos) {
            inventario.computeIfPresent(producto, (key, stockActual) -> {
                int nuevoStock = stockActual - 1;
                logger.info("Producto '{}' vendido. Stock: {} → {}", key, stockActual, nuevoStock);
                return Math.max(nuevoStock, 0);
            });

            // Si el producto no existe en el inventario
            inventario.computeIfAbsent(producto, p -> {
                logger.warn("❗Producto '{}' no está registrado en el inventario. No se pudo actualizar.", p);
                return 0;
            });
        }

        logger.info("Estado actual del inventario: {}", inventario);
    }
}
