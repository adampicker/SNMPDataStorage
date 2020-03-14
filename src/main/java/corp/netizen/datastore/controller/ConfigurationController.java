package corp.netizen.datastore.controller;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.netizen.datastore.converters.ClientConverter;
import com.netizen.datastore.dto.ClientDTO;
import com.netizen.datastore.dto.MibDTO;
import corp.netizen.datastore.DatastoreApplication;
import corp.netizen.datastore.model.Configuration;
import corp.netizen.datastore.service.ConfigurationService;
import corp.netizen.datastore.service.MibService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import corp.netizen.datastore.model.Client;
import corp.netizen.datastore.model.Mib;
import corp.netizen.datastore.service.ClientServiceImpl;

@RestController
public class ConfigurationController {

	public static Logger logger  = LoggerFactory.getLogger(ConfigurationController.class);
	
	private ClientServiceImpl clientService;
	private MibService mibService;
	private ConfigurationService configurationService;

    @Autowired
    public void setClientService(ClientServiceImpl productService, MibService mibService, ConfigurationService configurationService){
        this.clientService = productService;
        this.mibService = mibService;
        this.configurationService = configurationService;
    }
	
	@RequestMapping(path="/access/{mac}", method = RequestMethod.PATCH, produces = "application/json")
	public ResponseEntity<ClientDTO> getAccess(@PathVariable("mac") String macAddress) {
		boolean accountCreated = false;
		Client client = clientService.getByMac(macAddress); // creates new client if one with given mac not exists
		if (client == null) {
			logger.info("New client with mac: " + macAddress);
			accountCreated = true;
			client = clientService.saveOrUpdate(new Client(macAddress, this.configurationService.getDefaultConfiguration()));
		}
		logger.info("Returning client with params:  id:" + client.getId() + " mac: " + client.getMacAddress() + " configurationId: " + client.getConfiguration().getId() );

		return new ResponseEntity<ClientDTO>(this.clientService.convert(client), accountCreated ? HttpStatus.CREATED : HttpStatus.OK);
	}
	
	@RequestMapping(path="/configuration/{id}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<List<MibDTO>> getConfiguration(@PathVariable("id") long id) {
    	logger.info("Getting configuration");
		Client client = clientService.getById(id);
		if (client == null ) {
			logger.warn("No configuration for given id: " + id + ", return HttpStatus.FORBIDDEN("+HttpStatus.FORBIDDEN.value()+")");
			return  new ResponseEntity<>(null, HttpStatus.FORBIDDEN);
		}
		Configuration configuration = client.getConfiguration();
		if (configuration == null){
			logger.warn("Configuration for user: " + client.getId() + " not settled. Setting default configuration");
			client.setConfiguration(this.configurationService.getDefaultConfiguration());
			this.clientService.saveOrUpdate(client);
		}

/*		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.set("MyResponseHeader", "SIEMANKO");*/
		return new ResponseEntity<>(this.configurationService.getConfiguration(client.getConfiguration().getId()), null, HttpStatus.OK);
	}



}
