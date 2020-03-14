package com.netizen.datastore.converters;

import com.netizen.datastore.dto.ClientDTO;
import corp.netizen.datastore.model.Client;

public class ClientConverter implements GenericConverter<Client, ClientDTO> {


    @Override
    public Client createFromDto(ClientDTO dto) {
        Client client = new Client();

        return client;
    }

    @Override
    public ClientDTO createFromEntity(Client entity) {
        ClientDTO dto = new ClientDTO();
        dto.setId(entity.getId());
        dto.setMacAddress(entity.getMacAddress());
        dto.setStatus(entity.getStatus());
        return dto;
    }

    @Override
    public Client updateEntity(Client entity, ClientDTO dto) {
        return null;
    }
}
