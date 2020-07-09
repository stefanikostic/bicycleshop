package emt.proekt.bicycleshop.sharedkernel.domain.base;

import lombok.NonNull;

import java.time.Instant;

public interface DomainEvent {

    @NonNull
    Instant occurredOn();
}
