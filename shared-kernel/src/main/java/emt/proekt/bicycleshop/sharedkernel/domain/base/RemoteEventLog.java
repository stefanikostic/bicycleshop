package emt.proekt.bicycleshop.sharedkernel.domain.base;

import emt.proekt.bicycleshop.sharedkernel.infra.eventlog.StoredDomainEvent;

import java.util.List;

public interface RemoteEventLog {
    List<StoredDomainEvent> events();

}
