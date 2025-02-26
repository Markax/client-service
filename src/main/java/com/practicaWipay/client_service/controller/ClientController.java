package com.practicaWipay.client_service.controller;

import com.practicaWipay.client_service.client.MerchantClient;
import com.practicaWipay.client_service.dto.MerchantDTO;
import com.practicaWipay.client_service.model.Client;
import com.practicaWipay.client_service.service.ClientService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Email;
import java.util.*;

@RestController
@RequestMapping("/api/clients")
@RequiredArgsConstructor
@Validated
public class ClientController {
    private final ClientService clientService;
    private static final String SECRET_KEY = "c2VjcmV0YXNlY3JldGFzZWNyZXRhc2VjcmV0YXNlY3JldGE=";

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

    @GetMapping("/getmerchant/{id}")
    public ResponseEntity<MerchantDTO> getMerchantById(@PathVariable("id") String id) {
        MerchantDTO merchant = clientService.getMerchantById(id);
        return merchant != null ? ResponseEntity.ok(merchant) : ResponseEntity.notFound().build();
    }

    @GetMapping("/generateToken")
    public String generateToken(@RequestParam String name, @RequestParam int age) {
        System.out.println(SECRET_KEY);
        Map<String, Object> claims = new HashMap<>();
        claims.put("name", name);
        claims.put("age", age);

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60))
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                .compact();
    }
}
