package emt.proekt.bicycleshop.bicyclepart.client;

import emt.proekt.bicycleshop.sharedkernel.domain.base.RemoteEventLog;
import emt.proekt.bicycleshop.sharedkernel.infra.eventlog.RemoteEventLogService;
import emt.proekt.bicycleshop.sharedkernel.infra.eventlog.StoredDomainEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Collections;
import java.util.List;

public class RemoteEventLogServiceClient implements RemoteEventLogService {

    private static final Logger LOGGER = LoggerFactory.getLogger(RemoteEventLogServiceClient.class);

    private final String serverUrl;
    private final RestTemplate restTemplate;

    public RemoteEventLogServiceClient(String serverUrl, int connectionTimeout, int readTimeout) {
        this.serverUrl = serverUrl;
        this.restTemplate = new RestTemplate();
        var requestFactory = new SimpleClientHttpRequestFactory();
        requestFactory.setConnectTimeout(connectionTimeout);
        requestFactory.setReadTimeout(readTimeout);
        restTemplate.setRequestFactory(requestFactory);
    }

    private UriComponentsBuilder uri() {
        return UriComponentsBuilder.fromUriString(serverUrl);
    }

    @Override
    public String source() {
        return serverUrl;
    }

    @Override
    public RemoteEventLog currentLog(long lastProcessedId) {
        List<StoredDomainEvent> events;
        try{
             events = restTemplate.exchange(uri().path("/api/event-log/" + lastProcessedId).build().toUri(), HttpMethod.GET, null,
                    new ParameterizedTypeReference<List<StoredDomainEvent>>() {
                    }).getBody();
        } catch (Exception ex){
            LOGGER.error("Error retrieving events",ex);
            return new RemoteEventLogImpl(Collections.emptyList());
        }
        return new RemoteEventLogImpl(events);
    }
}
