package emt.proekt.bicycleshop.order.domain.event;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import emt.proekt.bicycleshop.order.domain.model.OrderId;
import emt.proekt.bicycleshop.sharedkernel.domain.base.DomainEvent;
import org.springframework.lang.NonNull;

import java.time.Instant;
import java.util.Objects;

public class OrderCanceled implements DomainEvent {

    @JsonProperty("orderId")
    private final OrderId orderId;
    @JsonProperty("occurredDate")
    private final Instant occurredDate;

    @JsonCreator
    public OrderCanceled(@JsonProperty("orderId") @NonNull OrderId orderId,
                        @JsonProperty("occurredDate") @NonNull Instant occurredDate) {
        this.orderId = Objects.requireNonNull(orderId, "orderId must not be null");
        this.occurredDate = Objects.requireNonNull(occurredDate, "occurredDate must not be null");
    }

    @NonNull
    public OrderId orderId() {
        return orderId;
    }

    @Override
    @NonNull
    public Instant occurredDate() {
        return occurredDate();
    }

}
