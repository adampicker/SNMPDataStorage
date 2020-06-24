package corp.netizen.datastore.converters;

import corp.netizen.datastore.dto.ClientDTO;
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
        dto.setStatus(entity.getStatus().toString());
        dto.setType(entity.getType());
        dto.setPort(entity.getPort());
        dto.setPid(entity.getPid());
        dto.setUserName(entity.getUserName());
        dto.setConfiguration(entity.getConfiguration().getId());
        return dto;
    }

    @Override
    public Client updateEntity(Client entity, ClientDTO dto) {
        return null;
    }
}
