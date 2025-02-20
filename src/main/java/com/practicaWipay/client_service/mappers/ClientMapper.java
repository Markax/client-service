package com.practicaWipay.client_service.mappers;

import com.practicaWipay.client_service.model.Client;
import com.practicaWipay.client_service.dto.ClientDTO;
import org.mapstruct.Mapper;

@Mapper (componentModel = "spring")
public interface ClientMapper {
    ClientDTO toDTO(Client client);
    Client toEntity(ClientDTO clientDTO);
}
