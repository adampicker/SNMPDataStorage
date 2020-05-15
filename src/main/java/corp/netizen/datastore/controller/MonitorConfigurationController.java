package corp.netizen.datastore.controller;

import corp.netizen.datastore.dto.ConfigurationDTO;
import corp.netizen.datastore.dto.ConfigurationSaveDto;
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

@RequestMapping("/users")
@RestController
public class MonitorConfigurationController {

    public static Logger logger = LoggerFactory.getLogger(MonitorConfigurationController.class);
    @Autowired
    ConfigurationService configurationService;
    @Autowired
    ClientServiceImpl clientService;

    @RequestMapping(path = "/add-configuration", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity addConfiguration(@RequestBody ConfigurationSaveDto configuration) {
        logger.info("Configuration saving");

        Configuration saved = configurationService.saveConfigurationSaveDto(configuration);
        logger.info(saved != null ? "Configuration saved with success" : "Configuration not saved.");
        return new ResponseEntity<>(saved != null ? HttpStatus.OK : HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(path = "/configurations", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<List<ConfigurationDTO>> getConfiguration() {
        logger.info("Got configuration GET request ");
        return new ResponseEntity(this.configurationService.listAllDTO(), HttpStatus.OK);
    }

    @RequestMapping(path = "/update-configuration", method = RequestMethod.PUT, produces = "application/json")
    public ResponseEntity updateConfiguration(@RequestBody ConfigurationDTO configurationToUpdate) {
        logger.info("Got configuration UPDATE request for: " + configurationToUpdate.getId());
        Configuration updated = this.configurationService.updateConfiguration(configurationToUpdate);
        this.clientService.configurationUpdated(updated.getId());
        return updated == null ?
                new ResponseEntity<>(HttpStatus.BAD_REQUEST) :
                new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(path = "/delete-configuration/{id}", method = RequestMethod.DELETE, produces = "application/json")
    public ResponseEntity updateConfiguration(@PathVariable("id") Long id) {
        logger.info("Got configuration DELETE request for: " + id);
        this.configurationService.delete(id);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }


}
