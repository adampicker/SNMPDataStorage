package corp.netizen.datastore.service;

import java.util.List;

import corp.netizen.datastore.dto.ClientDTO;
import corp.netizen.datastore.model.Client;

public interface ClientService {
	
	List<ClientDTO> listAll();

    Client getById(Long id);

    Client saveOrUpdate(Client product);

    void delete(Long id);

    void sendStatusMessage(Long id, int status);
    
    Client getByMac(String mac);

    ClientDTO convert(Client client);

}
