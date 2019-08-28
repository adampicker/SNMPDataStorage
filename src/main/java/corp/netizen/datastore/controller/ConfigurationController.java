package corp.netizen.datastore.controller;
import java.util.ArrayList;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import corp.netizen.datastore.model.Mib;

@RestController
public class ConfigurationController {
	
	@RequestMapping(path="/configuration", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<ArrayList<Mib>> getConfiguration() {
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.set("MyResponseHeader", "SIEMANKO");
		return new ResponseEntity<>(Mib.getTmpArrayOfMibs(), responseHeaders, HttpStatus.OK);
	}

}
