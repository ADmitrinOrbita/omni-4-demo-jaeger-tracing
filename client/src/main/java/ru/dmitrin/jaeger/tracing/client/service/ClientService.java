package ru.dmitrin.jaeger.tracing.client.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

@Service
@RequiredArgsConstructor
@Log4j2
public class ClientService {

    @Value("${remote.service.host}")
    public String url;

    @Value("${remote.service.port}")
    public String port;

    @Value("${remote.service.path}")
    public String path;

    private final RestTemplate restTemplate;

    public String getSomeInformationFromRemote() {
        UriComponents uri = UriComponentsBuilder.newInstance()
                .scheme("http")
                .host(url)
                .port(port)
                .path(path)
                .build();

        log.info("sending to remote uri: uri='{}'", uri.toUriString());

        return restTemplate.getForObject(uri.toUriString(), String.class);
    }
}