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

import com.netizen.datastore.dto.MibValuesDTO;

import corp.netizen.datastore.model.Client;
import corp.netizen.datastore.repository.MibValuesRepository;


@RestController
public class DataController {

	public static Logger logger = LoggerFactory.getLogger(DataController.class);

	@Autowired
	MibValuesService mibValuesService;
	@Autowired
	ClientService clientService;
	

	@RequestMapping(path="/data/{id}", method = RequestMethod.POST)
    public ResponseEntity<HttpStatus>  setRunningStatus(@PathVariable("id") long id, @RequestBody MibValuesDTO data) {
		logger.debug("Got data from: " + id);
		System.out.println(HttpStatus.NOT_MODIFIED.value());
		Client client = this.clientService.getById(id);
		if (client == null) {
			logger.error("Data from unrecognized client, id: " + id);
			logger.error("Refuse to save data. Sending response HttpStatus.NOT_MODIFIED(" + HttpStatus.NOT_MODIFIED.value() + ")");
			return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
		}
		else {
			logger.debug("Extracting and saving data from: " + id);
			mibValuesService.saveData(data);
		}
		return new ResponseEntity<>(HttpStatus.OK);
		
	}

}
