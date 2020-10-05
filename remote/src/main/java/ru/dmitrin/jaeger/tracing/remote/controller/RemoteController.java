package ru.dmitrin.jaeger.tracing.remote.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.dmitrin.jaeger.tracing.remote.service.RemoteService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/remote")
public class RemoteController {

    private final RemoteService remoteService;

    @GetMapping
    public String getInformation() {
        return remoteService.getSomeInformation();
    }
}
