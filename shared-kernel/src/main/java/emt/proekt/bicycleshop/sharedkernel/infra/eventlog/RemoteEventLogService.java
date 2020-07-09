package emt.proekt.bicycleshop.sharedkernel.infra.eventlog;

import emt.proekt.bicycleshop.sharedkernel.domain.base.RemoteEventLog;
import org.springframework.stereotype.Service;

@Service
public interface RemoteEventLogService {
    String source();

    RemoteEventLog currentLog(long lastProcessedId);


}
