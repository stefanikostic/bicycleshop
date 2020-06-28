package emt.proekt.bicycleshop.bicyclepart.client;

import emt.proekt.bicycleshop.sharedkernel.domain.base.RemoteEventLog;
import emt.proekt.bicycleshop.sharedkernel.infra.eventlog.StoredDomainEvent;

import java.util.List;

public class RemoteEventLogImpl implements RemoteEventLog {
    private final List<StoredDomainEvent> storedDomainEvents;

    public RemoteEventLogImpl(List<StoredDomainEvent> storedDomainEvents) {
        this.storedDomainEvents = storedDomainEvents;
    }

    @Override
    public List<StoredDomainEvent> events() {
        return storedDomainEvents;
    }
}
