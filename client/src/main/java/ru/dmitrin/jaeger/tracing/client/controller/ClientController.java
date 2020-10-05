package ru.dmitrin.jaeger.tracing.client.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.dmitrin.jaeger.tracing.client.service.ClientService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/client")
public class ClientController {

    private final ClientService clientService;

    @GetMapping
    public String getSomeInformation() {
        return clientService.getSomeInformationFromRemote();
    }
}
