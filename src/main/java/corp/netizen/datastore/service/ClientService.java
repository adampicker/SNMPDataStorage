package corp.netizen.datastore.service;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import corp.netizen.datastore.DatastoreApplication;

@Service
public class ClientService {
	
	private RabbitTemplate rabbitTemplate;

    @Autowired
    public ClientService(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }
	
	public void sendStatusMessage(int id) {
		System.out.println("Sending sth");
		rabbitTemplate.convertAndSend(DatastoreApplication.QUEUE_NAME, id); // to powinno być co przesyłam, mapa..
	}

}
