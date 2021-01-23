package corp.netizen.datastore.controller;

import corp.netizen.datastore.service.ClientService;
import corp.netizen.datastore.service.MibValuesService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import corp.netizen.datastore.dto.MibValuesDTO;

import corp.netizen.datastore.model.Client;


@RestController
@RequestMapping("/clients")
public class DataController {

    public static Logger logger = LoggerFactory.getLogger(DataController.class);

    @Autowired
    MibValuesService mibValuesService;
    @Autowired
    ClientService clientService;


    @RequestMapping(path = "/data/{id}", method = RequestMethod.POST)
    public ResponseEntity<HttpStatus> setRunningStatus(@PathVariable("id") long id, @RequestBody MibValuesDTO data) {
        logger.debug("Got data from: " + id);
        Client client = this.clientService.getById(id);

        if (client == null) {
            logger.error("Data from unrecognized client, id: " + id);
            logger.error("Refuse to save data. Sending response HttpStatus.NOT_MODIFIED(" + HttpStatus.NOT_MODIFIED.value() + ")");
                return new ResponseEntity<>(HttpStatus.NOT_MODIFIED); //304
        } else {
            logger.debug("Extracting and saving data from: " + id);
            mibValuesService.saveData(data);
        }
        if (client.getStatus().toString() == Client.Status.UPDATED.toString()) {
            client.setStatus(Client.Status.ACTIVE);
            this.clientService.saveOrUpdate(client);
            logger.info("Clients configuration changed. Sending 205 status to trigger configuration update on client");
            return new ResponseEntity<>(HttpStatus.RESET_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.OK);

    }

}
