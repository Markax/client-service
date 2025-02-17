package com.practicaWipay.client_service.repository;

import com.practicaWipay.client_service.model.Client;

import java.util.List;
import java.util.Optional;

public interface ClientRepository  {
    Optional<Client> findById(String id);
    List<Client> findByName(String name);
    Optional<Client> findByEmail(String email);
}
