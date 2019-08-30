package corp.netizen.datastore.listener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


import corp.netizen.datastore.repository.MockRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Component
public class StatusMessageListener {
	

    private MockRepository saveToDb;
	
    private static final Logger log = LogManager.getLogger(StatusMessageListener.class);
	
        
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
        log.debug("Received a message");
    	System.out.println("Got message, saving to db");
    }

}
