package emt.proekt.bicycleshop.product.integration;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import emt.proekt.bicycleshop.product.domain.model.OrderId;
import emt.proekt.bicycleshop.product.domain.model.OrderItemId;
import emt.proekt.bicycleshop.product.domain.model.ProductId;
import emt.proekt.bicycleshop.sharedkernel.domain.base.DomainEvent;
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

    @JsonProperty("occurredOn")
    private final Instant occurredOn;

    @JsonCreator
    public OrderItemDeletedEvent(OrderId orderId, OrderItemId orderItemId, ProductId productId, int quantity, Instant occurredOn) {
        this.orderId = orderId;
        this.orderItemId = orderItemId;
        this.productId = productId;
        this.quantity = quantity;
        this.occurredOn = occurredOn;
    }

    @Override
    public @NonNull Instant occurredOn() {
        return occurredOn;
    }
}
