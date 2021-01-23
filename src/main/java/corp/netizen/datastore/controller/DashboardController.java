package corp.netizen.datastore.controller;

import corp.netizen.datastore.dto.DashboardDataDTO;
import corp.netizen.datastore.model.Client;
import corp.netizen.datastore.model.Configuration;
import corp.netizen.datastore.service.ClientServiceImpl;
import corp.netizen.datastore.service.ConfigurationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import corp.netizen.datastore.service.MibService;
import corp.netizen.datastore.model.Mib;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;


@RequestMapping("/users")
@RestController
public class DashboardController {


    public static Logger logger = LoggerFactory.getLogger(MonitorConfigurationController.class);
    @Autowired
    ConfigurationService configurationService;
    @Autowired
    ClientServiceImpl clientService;
    @Autowired
    MibService mibService;


    @RequestMapping(path = "/get-dashboard-data", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<DashboardDataDTO> getDashboardData() {
        logger.info("Got GET dashboard data request");
        DashboardDataDTO dashboardData = new DashboardDataDTO();

        dashboardData.setActiveClients(this.clientService.getAmountOfActiveClients());
        dashboardData.setInactiveClients(this.clientService.getAmountOfInactiveClients());

        List<Mib> mibs = this.mibService.listAll();
        List<Configuration> confs = this.configurationService.listAll();
        AtomicInteger notInCofiguration = new AtomicInteger(0);
        AtomicBoolean found = new AtomicBoolean(false);

        for (Mib mib : mibs){
            for (int i=0; i<confs.size(); i++) {
                if (confs.get(i).getMib().contains(mib))  found.set(true);
                if (found.get()) break;
            }
            if (!found.get()) {
                notInCofiguration.getAndIncrement();
            } else {
                found.set(false);
                continue;
            }
        };

        dashboardData.setMibAdded(mibs.size());
        dashboardData.setMibsNotInConfiguration(notInCofiguration.get());
        dashboardData.setCreatedConfigurations(confs.size());

        AtomicInteger configurationsNotAssigned = new AtomicInteger(0);
        boolean confFound = false;
        List<Client> allClients = this.clientService.listAll();
        confs.forEach(conf ->{
            Optional<Client> c = allClients.stream()
                    .filter(client -> client.getConfiguration().getId() == conf.getId()).findAny();
            if (c.isEmpty()) configurationsNotAssigned.incrementAndGet();
        });
        dashboardData.setConfigurationsNotAssigned(configurationsNotAssigned.get());
        return new ResponseEntity<>(dashboardData, HttpStatus.OK);
    }

}
