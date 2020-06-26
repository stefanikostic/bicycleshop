package emt.proekt.bicycleshop.order.domain.event;

import com.fasterxml.jackson.annotation.JsonProperty;
import emt.proekt.bicycleshop.order.domain.model.OrderId;
import emt.proekt.bicycleshop.order.domain.model.OrderItemId;
import emt.proekt.bicycleshop.sharedkernel.domain.base.DomainEvent;
import emt.proekt.bicycleshop.sharedkernel.domain.base.ProductId;
import lombok.Getter;
import lombok.NonNull;

import java.time.Instant;

@Getter
public class OrderItemAdded implements DomainEvent {

    @JsonProperty("orderId")
    private final OrderId orderId;

    @JsonProperty
    private final OrderItemId orderItemId;

    @JsonProperty
    private final ProductId productId;

    @JsonProperty("quantity")
    private final int quantity;

    @JsonProperty("occurredDate")
    private final Instant occurredDate;

    public OrderItemAdded(OrderId orderId, OrderItemId orderItemId, ProductId productId, int quantity, Instant occurredDate) {
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
