package emt.proekt.bicycleshop.product.integration;

import com.fasterxml.jackson.databind.ObjectMapper;
import emt.proekt.bicycleshop.sharedkernel.domain.base.DomainEvent;
import emt.proekt.bicycleshop.sharedkernel.infra.eventlog.RemoteEventTranslator;
import emt.proekt.bicycleshop.sharedkernel.infra.eventlog.StoredDomainEvent;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class OrderItemAddedEventTranslator implements RemoteEventTranslator {

    private final ObjectMapper objectMapper;

    public OrderItemAddedEventTranslator(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    public boolean supports(StoredDomainEvent remoteEvent) {
        return remoteEvent.domainEventClassName().equals("emt.proekt.bicycleshop.order.domain.event.OrderItemAdded");
    }

    @Override
    public Optional<DomainEvent> translate(StoredDomainEvent remoteEvent) {
        return Optional.of(remoteEvent.toDomainEvent(objectMapper,OrderItemAddedEvent.class));
    }
}
