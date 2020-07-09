package emt.proekt.bicycleshop.sharedkernel.port.client;

import emt.proekt.bicycleshop.sharedkernel.domain.base.RemoteEventLog;
import emt.proekt.bicycleshop.sharedkernel.domain.base.RemoteEventLogImpl;
import emt.proekt.bicycleshop.sharedkernel.infra.eventlog.RemoteEventLogService;
import emt.proekt.bicycleshop.sharedkernel.infra.eventlog.StoredDomainEvent;
import lombok.NonNull;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Objects;

public class RemoteEventLogServiceClient implements RemoteEventLogService {

    private final String source;
    private final String serverUrl;
    private final RestTemplate restTemplate;

    public RemoteEventLogServiceClient(@NonNull String serverUrl, int connectTimeout, int readTimeout) {
        this.source = Objects.requireNonNull(serverUrl, "serverUrl must not be null");
        this.serverUrl = serverUrl;
        this.restTemplate = new RestTemplate();
        var requestFactory = new SimpleClientHttpRequestFactory();
        requestFactory.setConnectTimeout(connectTimeout);
        requestFactory.setReadTimeout(readTimeout);
        restTemplate.setRequestFactory(requestFactory);
    }

    @Override
    @NonNull
    public String source() {
        return source;
    }

    @Override
    public RemoteEventLog currentLog(long lastProcessedId) {
        URI currentLogUri = UriComponentsBuilder.fromUriString(serverUrl).path(String.format("/api/event-log/%d", lastProcessedId)).build().toUri();
        return retrieveLog(currentLogUri);
    }

    @NonNull
    private RemoteEventLog retrieveLog(@NonNull URI currentLogUri) {
        ResponseEntity<List<StoredDomainEvent>> responseEntity = restTemplate.exchange(currentLogUri, HttpMethod.GET, null, new ParameterizedTypeReference<List<StoredDomainEvent>>() {
        });
        if (responseEntity.getStatusCode() != HttpStatus.OK) {
            throw new IllegalArgumentException("Couldn't retrieve log from URI " + currentLogUri);
        }
        return new RemoteEventLogImpl(responseEntity);
    }
}
