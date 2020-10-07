package ru.vtb.jaeger.tracing.remote.service;

import io.opentracing.Span;
import io.opentracing.Tracer;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Log4j2
@Service
public class RemoteService {

    @Autowired
    private Tracer tracer;

    /**
     * Демонстрация отправки данных удалённым сервисом
     *
     * Дополнительно трассировка обогащается новым span-ом, который создан дочерним по отношению к active span
     */
    public String getRemoteServiceInformation() {
        log.info("-- Getting important information from remote service --");

        if (tracer.activeSpan() != null) {
            Span span = tracer.buildSpan("localSpan")
                    .asChildOf(tracer.activeSpan())
                    .start();

            span.setTag("remote-service-custom-tag", "remote-service-custom-tag-value");

            Map<String, String> map = new HashMap<>();
            map.put("remote-service-event", "remote-service get information event");
            span.log(map);

            span.finish();
        }

        return ("Information from remote service");
    }
}