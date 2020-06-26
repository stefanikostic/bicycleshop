package emt.proekt.bicycleshop.sharedkernel.infra.eventlog;

import com.fasterxml.jackson.databind.ObjectMapper;
import emt.proekt.bicycleshop.sharedkernel.domain.base.DomainEvent;
import lombok.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class DomainEventLogService {

    private final StoredDomainEventRepository storedDomainEventRepository;
    private final ObjectMapper objectMapper;

    public DomainEventLogService(StoredDomainEventRepository storedDomainEventRepository, ObjectMapper objectMapper) {
        this.storedDomainEventRepository = storedDomainEventRepository;
        this.objectMapper = objectMapper;
    }


    @Transactional(propagation = Propagation.MANDATORY)
    public void append(@NonNull DomainEvent domainEvent){
        var storedEvent= new StoredDomainEvent(domainEvent, objectMapper);
        storedDomainEventRepository.saveAndFlush(storedEvent);
    }

    @NonNull
    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public List<StoredDomainEvent> retrieveLog(long lastProcessedEventId) {
        var max = storedDomainEventRepository.findHighestDomainEventId();
        if (max == null) {
            max = 0L;
        }
        return storedDomainEventRepository.findEventsBetween(lastProcessedEventId,max);
    }
}
