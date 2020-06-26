package emt.proekt.bicycleshop.sharedkernel.infra.eventlog;

import emt.proekt.bicycleshop.sharedkernel.domain.base.DomainEvent;
import lombok.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

@Service
public class DomainEventLogAppender {

    private final DomainEventLogService domainEventLogService;

    public DomainEventLogAppender(DomainEventLogService domainEventLogService) {
        this.domainEventLogService = domainEventLogService;
    }

    @TransactionalEventListener(phase = TransactionPhase.BEFORE_COMMIT)
    public void onDomainEvent(@NonNull DomainEvent domainEvent){
        domainEventLogService.append(domainEvent);
    }
}
