package emt.proekt.bicycleshop.bicyclepart.integration;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import emt.proekt.bicycleshop.bicyclepart.domain.model.OrderId;
import emt.proekt.bicycleshop.bicyclepart.domain.model.OrderItemId;
import emt.proekt.bicycleshop.sharedkernel.domain.base.DomainEvent;
import emt.proekt.bicycleshop.sharedkernel.domain.base.ProductId;
import lombok.Getter;
import lombok.NonNull;

import java.time.Instant;

@Getter
public class OrderItemDeletedEvent implements DomainEvent {
    @JsonProperty("orderId")
    private final OrderId orderId;

    @JsonProperty("orderItemId")
    private final OrderItemId orderItemId;

    @JsonProperty("productId")
    private final ProductId productId;

    @JsonProperty("quantity")
    private final int quantity;

    @JsonProperty("occurredDate")
    private final Instant occurredDate;

    @JsonCreator
    public OrderItemDeletedEvent(OrderId orderId, OrderItemId orderItemId, ProductId productId, int quantity, Instant occurredDate) {
        this.orderId = orderId;
        this.orderItemId = orderItemId;
        this.productId = productId;
        this.quantity = quantity;
        this.occurredDate = occurredDate;
    }

    @Override
    public @NonNull Instant occurredDate() {
        return occurredDate;
    }
}
