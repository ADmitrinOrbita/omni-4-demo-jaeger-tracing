package ru.dmitrin.jaeger.tracing.remote.service;

import io.opentracing.Span;
import io.opentracing.Tracer;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Log4j2
@Service
public class RemoteService {

    @Autowired
    private Tracer tracer;

    public String getSomeInformation() {
        log.info("-- Getting some very important information --");

//        Span serverSpan = tracer.activeSpan();
//
//        Span span = tracer.buildSpan("localSpan")
//                .asChildOf(serverSpan.context())
//                .start();
//        span.setTag("hello-to", "User");
//        span.finish();

/*        if (rootSpan != null) {
            log.info("added span tag");
            rootSpan.setTag("http.status_code", 200);
            Span span = tracer.buildSpan("getting information").asChildOf(rootSpan).start();

            span.setTag("http.status_code", 200);
            Map<String, String> map = new HashMap<>();
            map.put("event", "getting information event");
            span.log(map);
            span.finish();
        } else {
            log.error("No active Span");
        }*/

        return ("Some information from remote service");
    }
}