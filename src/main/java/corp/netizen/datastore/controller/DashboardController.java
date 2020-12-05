package corp.netizen.datastore.controller;

import corp.netizen.datastore.dto.ConfigurationSaveDto;
import corp.netizen.datastore.dto.DashboardDataDTO;
import corp.netizen.datastore.model.Configuration;
import corp.netizen.datastore.service.ClientServiceImpl;
import corp.netizen.datastore.service.ConfigurationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RequestMapping("/users")
@RestController
public class DashboardController {


    public static Logger logger = LoggerFactory.getLogger(MonitorConfigurationController.class);
    @Autowired
    ConfigurationService configurationService;
    @Autowired
    ClientServiceImpl clientService;


    @RequestMapping(path = "/get-dashboard-data", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<DashboardDataDTO> getDashboardData() {
        logger.info("Got GET dashboard data request");
        DashboardDataDTO dashboardData = new DashboardDataDTO();

        dashboardData.setActiveClients(this.clientService.getAmountOfActiveClients());
        dashboardData.setInactiveClients(this.clientService.getAmountOfInactiveClients());
        return new ResponseEntity<>(dashboardData, HttpStatus.OK);
    }
}
