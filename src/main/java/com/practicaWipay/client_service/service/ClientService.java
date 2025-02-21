package com.practicaWipay.client_service.service;

import com.practicaWipay.client_service.model.Client;
import com.practicaWipay.client_service.dto.ClientDTO;

import java.util.List;
import java.util.Optional;

public interface ClientService {
    ClientDTO createClient(Client client);
    Optional<?> findById(String id, boolean simpleOutput);
    List<Client> findByName(String name);
    Optional<ClientDTO> findByEmail(String email);
}
