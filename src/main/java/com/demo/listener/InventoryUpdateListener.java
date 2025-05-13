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
    public void initInventario() {
        inventario.put("Café", 10);
        inventario.put("Azúcar", 15);
        inventario.put("Leche", 8);
        logger.info("Inventario inicializado: {}", inventario);
    }

    @Async
    @EventListener
    public void handleOrderCreated(OrderCreatedEvent event) {
        List<String> productos = event.getProductos();
        List<Integer> cantidades = event.getCantidad();

        for (int i = 0; i < productos.size(); i++) {
            String producto = productos.get(i);
            int cantidad = cantidades.get(i);

            inventario.computeIfPresent(producto, (key, stockActual) -> {
                int nuevoStock = stockActual - cantidad;
                logger.info("Producto '{}' vendido ({} unidades). Stock: {} → {}", key, cantidad, stockActual, nuevoStock);
                return Math.max(nuevoStock, 0); // Evita que el stock sea negativo
            });

            inventario.computeIfAbsent(producto, p -> {
                logger.warn("❗Producto '{}' no está registrado en el inventario. No se pudo actualizar.", p);
                return 0;
            });
        }

        logger.info("Estado actual del inventario: {}", inventario);
    }
}
