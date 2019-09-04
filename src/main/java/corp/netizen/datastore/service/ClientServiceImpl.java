package corp.netizen.datastore.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import corp.netizen.datastore.DatastoreApplication;
import corp.netizen.datastore.model.Client;
import corp.netizen.datastore.repository.ClientRepository;

@Service
public class ClientServiceImpl implements ClientService {
	
    private RabbitTemplate rabbitTemplate;
    private ClientRepository clientRepository;

    @Autowired
    public ClientServiceImpl(RabbitTemplate rabbitTemplate, ClientRepository clientRepository) {
        this.rabbitTemplate = rabbitTemplate;
        this.clientRepository = clientRepository;
    }
	

	@Override
	public List<Client> listAll() {
		List<Client> clients = new ArrayList<>();
		clientRepository.findAll().forEach(clients::add);
		return clients;
	}

	@Override
	public Client getById(Long id) {
		return clientRepository.findById(id).orElse(null);
	}

	@Override
	public Client saveOrUpdate(Client client) {
		clientRepository.save(client);
        return client;
	}

	@Override
	public void delete(Long id) {
		clientRepository.deleteById(id);
		
	}

	@Override
	public void sendStatusMessage(Long id, int status) {
		Map<Long, Integer> actionmap = new HashMap<>();
        actionmap.put(id, status);
		rabbitTemplate.convertAndSend(DatastoreApplication.QUEUE_NAME, actionmap); // to powinno być co przesyłam, mapa..
	}
	
	@Override
	public Client getByMac(String mac) {
		return clientRepository.findByMac(mac).orElse(null);
	}

}
