package corp.netizen.datastore.controller;

import corp.netizen.datastore.dto.ClientDTO;
import corp.netizen.datastore.model.Client;
import corp.netizen.datastore.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class ClientController {

    @Autowired
    private ClientService clientService;

    @RequestMapping(path="/get-clients", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<List<ClientDTO>> getAllClients() {
        return new ResponseEntity<List< ClientDTO>>(this.clientService.listAll(), HttpStatus.OK);
    }
}
