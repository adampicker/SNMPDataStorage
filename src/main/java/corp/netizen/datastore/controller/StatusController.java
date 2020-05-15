/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package corp.netizen.datastore.controller;

import corp.netizen.datastore.dto.ClientDTO;
import corp.netizen.datastore.model.Client;
import corp.netizen.datastore.service.ClientServiceImpl;

import corp.netizen.datastore.service.ConfigurationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author adam.bunkowski
 */
@RestController
@RequestMapping("/clients")
public class StatusController {

    public static Logger logger = LoggerFactory.getLogger(StatusController.class);

    private ClientServiceImpl clientService;
    private ConfigurationService configurationService;

    @Autowired
    public void setClientService(ClientServiceImpl productService, ConfigurationService configurationService) {
        this.clientService = productService;
        this.configurationService = configurationService;
    }

    @RequestMapping(path = "/status/running/{id}", method = RequestMethod.PUT)
    public ResponseEntity<ClientDTO> setRunningStatus(@PathVariable("id") long id) {
        logger.info("Got active status from client id: " + id);
        Client client = clientService.getById(id);
        ClientDTO clientDto = null;
        if (client == null) {
            logger.warn("Client not found. Sending request HttpStatus.NO_CONTENT(" + HttpStatus.NO_CONTENT.value() + ")");
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        }
        client.setStatus(Client.Status.ACTIVE);
        clientDto = this.clientService.convert(this.clientService.saveOrUpdate(client));

        return new ResponseEntity<>(clientDto, HttpStatus.OK);
    }

    @RequestMapping(path = "/status/inactive/{id}", method = RequestMethod.PUT)
    public ResponseEntity<ClientDTO> setInactiveStatus(@PathVariable("id") long id) {
        logger.info("Got inactive status from client id: " + id);
        Client client = clientService.getById(id);
        ClientDTO clientDto = null;
        if (client == null) {
            logger.warn("Client not found. Sending request HttpStatus.NO_CONTENT(" + HttpStatus.NO_CONTENT.value() + ")");
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        }
        client.setStatus(Client.Status.INACTIVE);
        clientDto = this.clientService.convert(this.clientService.saveOrUpdate(client));

        return new ResponseEntity<>(clientDto, HttpStatus.OK);
    }

}
