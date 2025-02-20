package com.practicaWipay.client_service.repository;

import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

public interface ClientRepository<Client, String> {
    void save(Client client);
    Optional<Client> findById(String id);
    List<Client> findAll();
    Optional<Client> findByEmail(String email);
}
