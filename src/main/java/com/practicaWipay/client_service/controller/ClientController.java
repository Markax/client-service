package com.practicaWipay.client_service.controller;

import com.practicaWipay.client_service.model.Client;
import com.practicaWipay.client_service.service.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/clients")
@RequiredArgsConstructor
public class ClientController {
    private final ClientService clientService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createClient(@RequestBody Client client) {
        clientService.createClient(client);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Client> findById(@PathVariable String id, @RequestParam(required = false) boolean simpleOutput) {
        return clientService.findById(id, simpleOutput).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

}
