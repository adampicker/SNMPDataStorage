package corp.netizen.datastore.controller;

import corp.netizen.datastore.dto.ClientDTO;
import corp.netizen.datastore.dto.UpdateClientsConfigurationDto;
import corp.netizen.datastore.model.Client;
import corp.netizen.datastore.model.Configuration;
import corp.netizen.datastore.service.ClientService;
import corp.netizen.datastore.service.ClientServiceImpl;
import corp.netizen.datastore.service.ConfigurationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class ClientController {

    public static Logger logger = LoggerFactory.getLogger(ClientController.class);

    @Autowired
    private ClientServiceImpl clientService;
    @Autowired
    private ConfigurationService configurationService;

    @RequestMapping(path = "/get-clients", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<List<ClientDTO>> getAllClients() {
        return new ResponseEntity<List<ClientDTO>>(this.clientService.listAll(), HttpStatus.OK);
    }

    @RequestMapping(path = "/get-client/{id}", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<ClientDTO> getClientDetails(@PathVariable("id") Long id) {
        ClientDTO client = this.clientService.getByIdDTO(id);
        if (client == null) {
            logger.info("Client details not found");
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        }
        logger.info("Client details found for id: " + id);
        return new ResponseEntity<>(client, HttpStatus.OK);
    }

    @RequestMapping(path = "/update-clients-configuration/{id}", method = RequestMethod.PUT, produces = "application/json")
    public ResponseEntity<ClientDTO> updateClientsConfiguration(@PathVariable("id") Long id,
                                                                @RequestBody UpdateClientsConfigurationDto confToSet) {
        logger.info("Got update-clients-configuration request for client: " + id);
        Client clientToUpdate = this.clientService.getById(id);
        Configuration conf = this.configurationService.getById(confToSet.getConfigurationId());
        if (clientToUpdate == null) {
            logger.error("Client not found");
            return new ResponseEntity<>(null, HttpStatus.NOT_MODIFIED);
        }
        if (conf == null) {
            logger.error("Configuration not found");
            return new ResponseEntity<>(null, HttpStatus.NOT_MODIFIED);
        }
        try {
            clientToUpdate.setConfiguration(conf);
            clientToUpdate.setStatus(Client.Status.UPDATED);
            this.clientService.saveOrUpdate(clientToUpdate);
        } catch (Exception e){
            logger.error("Error while updating clients configuration");
            return new ResponseEntity<>(this.clientService.convert(clientToUpdate), HttpStatus.NOT_MODIFIED);
        }
        return new ResponseEntity<>(this.clientService.convert(clientToUpdate), HttpStatus.OK);
    }

   
}
