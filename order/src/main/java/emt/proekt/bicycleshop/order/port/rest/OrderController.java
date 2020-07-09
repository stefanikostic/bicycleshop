package emt.proekt.bicycleshop.order.port.rest;

import emt.proekt.bicycleshop.order.application.OrderCatalog;
import emt.proekt.bicycleshop.order.domain.model.Order;
import emt.proekt.bicycleshop.order.domain.model.OrderId;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/api/orders")
public class OrderController {
    private final OrderCatalog orderCatalog;

    public OrderController(OrderCatalog orderCatalog) {
        this.orderCatalog = orderCatalog;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Order> findById(@PathVariable("id") String orderId) {
        return orderCatalog.findById(new OrderId(orderId))
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public List<Order> findAll() {
        return orderCatalog.findAll();
    }
}

