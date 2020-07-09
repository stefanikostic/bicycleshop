package emt.proekt.bicycleshop.sharedkernel.infra.eventlog;

import emt.proekt.bicycleshop.sharedkernel.domain.base.DomainEvent;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface RemoteEventTranslator {

    boolean supports(StoredDomainEvent storedDomainEvent);

    Optional<DomainEvent> translate(StoredDomainEvent remoteEvent);

}
