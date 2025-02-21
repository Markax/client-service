package com.practicaWipay.client_service.repository;

import com.practicaWipay.client_service.model.Client;

import java.util.List;
import java.util.Optional;

public interface ClientRepository{
    void save(Client client);
    List<Client> findByValue(String id, String field);
    List<Client> findAll();
    Optional<Client> findByEmail(String email);
}
