package corp.netizen.datastore.controller;
import java.util.ArrayList;

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
	
	private ClientServiceImpl clientService;

    @Autowired
    public void setClientService(ClientServiceImpl productService) {
        this.clientService = productService;
    }
	
	@RequestMapping(path="/access/{mac}", method = RequestMethod.GET)
	public ResponseEntity<HttpStatus> getAccess(@PathVariable("mac") String macAddress) {
		Client tmp = clientService.getByMac(macAddress);
		if (tmp == null) clientService.saveOrUpdate(new Client(macAddress));
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@RequestMapping(path="/configuration", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<ArrayList<Mib>> getConfiguration() {
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.set("MyResponseHeader", "SIEMANKO");
		return new ResponseEntity<>(Mib.getTmpArrayOfMibs(), responseHeaders, HttpStatus.OK);
	}

}
