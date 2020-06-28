package emt.proekt.bicycleshop.sharedkernel.infra.eventlog;

import emt.proekt.bicycleshop.sharedkernel.domain.base.DomainEvent;

import java.util.Optional;

public interface RemoteEventTranslator {

    boolean supports(StoredDomainEvent storedDomainEvent);

    Optional<DomainEvent> translate(StoredDomainEvent remoteEvent);

}
