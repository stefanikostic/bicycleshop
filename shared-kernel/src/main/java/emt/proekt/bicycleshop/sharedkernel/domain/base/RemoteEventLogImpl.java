package emt.proekt.bicycleshop.sharedkernel.domain.base;

import emt.proekt.bicycleshop.sharedkernel.infra.eventlog.StoredDomainEvent;
import org.springframework.http.ResponseEntity;

import java.util.List;

public class RemoteEventLogImpl implements RemoteEventLog {

    private final ResponseEntity<List<StoredDomainEvent>> events;

    public RemoteEventLogImpl(ResponseEntity<List<StoredDomainEvent>> events) {
        this.events = events;
    }

    @Override
    public List<StoredDomainEvent> events() {
        return events;
    }
}
