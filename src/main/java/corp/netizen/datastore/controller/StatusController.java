/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package corp.netizen.datastore.controller;

import corp.netizen.datastore.model.Client;
import corp.netizen.datastore.model.Mib;
import corp.netizen.datastore.service.ClientServiceImpl;

import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author adam.bunkowski
 */
@RestController
public class StatusController {
	
    private ClientServiceImpl clientService;

    @Autowired
    public void setClientService(ClientServiceImpl productService) {
        this.clientService = productService;
    }    
        
    @RequestMapping(path="/status/running/{id}", method = RequestMethod.PUT)
    public ResponseEntity<HttpStatus> setRunningStatus(@PathVariable("id") long id) {
        clientService.saveOrUpdate(new Client(id, 1));
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    
    @RequestMapping(path="/status/inactive/{id}", method = RequestMethod.PUT)
    public ResponseEntity<HttpStatus> setInactiveStatus(@PathVariable("id") long id) {
        /*HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("MyResponseHeader", "inactive: " + id);*/
        clientService.saveOrUpdate(new Client(id, 0));
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    
}
