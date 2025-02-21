package com.practicaWipay.client_service.controller;

import com.practicaWipay.client_service.model.Client;
import com.practicaWipay.client_service.service.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.Option;
import javax.validation.constraints.Email;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/clients")
@RequiredArgsConstructor
@Validated
public class ClientController {
    private final ClientService clientService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createClient(@RequestBody Client client) {
        clientService.createClient(client);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable String id, @RequestParam(required = false) boolean simpleOutput) {
        return clientService.findById(id, simpleOutput).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<List<Client>> findByName(@PathVariable String name) {
        List<Client> clients = clientService.findByName(name);
        return ResponseEntity.ok(clients);
    }

    @GetMapping("/findByEmail")
    @Validated
    public ResponseEntity<?> findByEmail(@RequestParam @Email(message = "Invalid email format") String email) {
        Optional<?> clients = clientService.findByEmail(email);
        return clients.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

}
