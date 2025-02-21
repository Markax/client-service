package com.practicaWipay.client_service.service;

import com.practicaWipay.client_service.mappers.ClientMapper;
import com.practicaWipay.client_service.model.Client;
import com.practicaWipay.client_service.dto.ClientDTO;
import com.practicaWipay.client_service.repository.ClientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ClientServiceImplementation implements ClientService {

    private final ClientRepository clientRepository;
    private final ClientMapper clientMapper;

    public ClientDTO createClient(Client client) {
        clientRepository.save(client);
        return clientMapper.toDTO(client);
    }

    @Override
    public Optional<?> findById(String id, boolean simpleOutput){
        List<Client> foundClient =  clientRepository.findByValue(id, "PK");

        Optional<?> response = foundClient.isEmpty() ? Optional.empty() : Optional.ofNullable(foundClient.get(0));

        if(simpleOutput && response.isPresent())
            return Optional.of(id);
        else
            return response;

    }

    @Override
    public List<ClientDTO> findAll(String name){
        List<Client> clients = clientRepository.findAll();
        return clients.stream().map(clientMapper::toDTO).collect(Collectors.toList());

    }

    @Override
    public Optional<ClientDTO> findByEmail(String email){
        return clientRepository.findByEmail(email);
    }
}
