package corp.netizen.datastore.listener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ch.qos.logback.classic.Logger;
import corp.netizen.datastore.repository.MockRepository;

@Component
public class StatusMessageListener {
	

	private MockRepository saveToDb;
	
	//private static final Logger log = LogManager.getLogger(StatusMessageListener.class);
	
	@Autowired
	public StatusMessageListener(MockRepository rep) {
		this.saveToDb = rep; // bean of jparepository
	}
	
	/**
     * This method is invoked whenever any new message is put in the queue.
     * @param message
     */
    public void receiveMessage(int message) {
        /*log.info("Received <" + message + ">");
        Long id = Long.valueOf(message.get("id"));
        Product product = productRepository.findById(id).orElse(null);
        product.setMessageReceived(true);
        product.setMessageCount(product.getMessageCount() + 1);

        productRepository.save(product);
        log.info("Message processed...");*/
    	System.out.println("Got message, saving to db");
    }

}
