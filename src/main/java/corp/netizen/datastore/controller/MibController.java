package corp.netizen.datastore.controller;

import corp.netizen.datastore.dto.ClientDTO;
import corp.netizen.datastore.dto.MibDTO;
import corp.netizen.datastore.model.Mib;
import corp.netizen.datastore.service.ClientService;
import corp.netizen.datastore.service.MibService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/users")
public class MibController {


    @Autowired
    private MibService mibService;

    @RequestMapping(path="/get-mibs", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<List<MibDTO>> getAllMib() {
        return new ResponseEntity<List<MibDTO>>(this.mibService.listAllDTO(), HttpStatus.OK);
    }
}
