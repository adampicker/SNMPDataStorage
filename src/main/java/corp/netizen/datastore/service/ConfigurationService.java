package corp.netizen.datastore.service;

import corp.netizen.datastore.controller.MonitorConfigurationController;
import corp.netizen.datastore.converters.ClientConverter;
import corp.netizen.datastore.converters.ConfigurationConverter;
import corp.netizen.datastore.converters.MibConverter;
import corp.netizen.datastore.dto.ConfigurationDTO;
import corp.netizen.datastore.dto.ConfigurationSaveDto;
import corp.netizen.datastore.dto.MibDTO;
import corp.netizen.datastore.model.Client;
import corp.netizen.datastore.model.Configuration;
import corp.netizen.datastore.model.Mib;
import corp.netizen.datastore.repository.ConfigurationRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class ConfigurationService {

    public static Logger logger = LoggerFactory.getLogger(ConfigurationService.class);

    public ConfigurationRepository configurationRepository;
    private MibService mibService;
    private ConfigurationConverter configurationConverter;
    private ClientService clientService;

    @Autowired
    public ConfigurationService(ConfigurationRepository configurationRepository, MibService mibService, ClientServiceImpl clientService) {
        this.configurationRepository = configurationRepository;
        this.mibService = mibService;
        this.configurationConverter = new ConfigurationConverter(mibService.mibConverter);
        this.clientService = clientService;

    }

    public Configuration getById(Long id) {
        return this.configurationRepository.findById(id).orElse(null);
    }

    public List<MibDTO> getConfiguration(Long id) {
        Configuration configuration = this.getById(id);
        Set<Mib> mibInConfiguration;
        if (configuration == null) mibInConfiguration = Collections.EMPTY_SET;
        else {
            mibInConfiguration = configuration.getMib();
        }
        return this.mibService.mibConverter.createFromEntities(mibInConfiguration);
    }

    public Configuration getDefaultConfiguration() {
        return this.configurationRepository.getDefaultConfiguration();
    }

    public Configuration saveConfigurationSaveDto(ConfigurationSaveDto configurationSaveDto) {
        logger.info("Saving new configuation");
        Configuration newConfiguration = new Configuration();
        List<Mib> mibs = new LinkedList<Mib>();
        configurationSaveDto.getMibs().forEach(mib -> {
            Mib mibToAdd = this.mibService.getMibByOid((mib.getOid()));
            if (mibToAdd != null) {
                mibs.add(mibToAdd);
                mibToAdd.addConfiguration(newConfiguration);
            }
        });
        //newConfiguration.setMib(mibs.stream().collect(Collectors.toSet()));
        newConfiguration.setConfigurationName(configurationSaveDto.getConfigurationName());
        newConfiguration.setDefaultConfiguration(configurationSaveDto.isDefaultConfiguration());
        Configuration saved = this.save(newConfiguration);
        return saved;
    }

    private Configuration save(Configuration conf) {
        logger.debug("Saving configuration");
        Configuration savedConfiguration = this.configurationRepository.save(conf);
        if (savedConfiguration.isDefaultConfiguration()) {
            logger.info("Saved configuration is default. Running modifying query...");
            int modifiedRows = this.configurationRepository.setNonDefaultExcept(savedConfiguration.getId());
            logger.info("Modified rows: " + modifiedRows);

        }
        return savedConfiguration;
    }

    public List<ConfigurationDTO> listAllDTO() {
        List<ConfigurationDTO> dtos = new LinkedList<ConfigurationDTO>();
        this.configurationRepository.findAll().forEach(conf -> {
            dtos.add(this.configurationConverter.createFromEntity(conf));
        });
        return dtos;
    }

    public Configuration updateConfiguration(ConfigurationDTO configurationDto) {
        logger.info("Updating configuration, configuration id: " + configurationDto.getId());
        Configuration configurationToUpdate = this.configurationRepository.getOne(configurationDto.getId());
        if (configurationToUpdate == null) return null;
        logger.info("Configuration to update found");

        for (Mib mib : new ArrayList<>(configurationToUpdate.getMib())) {
            mib.getConfiguration().remove(configurationToUpdate);
        }
        List<Mib> mibs = new LinkedList<Mib>();
        configurationDto.getMib().forEach(mib -> {
            Mib mibToAdd = this.mibService.getMibByOid((mib.getOid()));
            if (mibToAdd != null) {
                mibs.add(mibToAdd);
                mibToAdd.addConfiguration(configurationToUpdate);
            }
        });
        configurationToUpdate.setDefaultConfiguration(configurationDto.isDefaultConfiguration());
        configurationToUpdate.setConfigurationName(configurationDto.getConfigurationName());
        configurationToUpdate.setMib(mibs.stream().collect(Collectors.toSet()));
        Configuration saved = this.configurationRepository.save(configurationToUpdate);
        if (saved.isDefaultConfiguration())
            this.configurationRepository.setNonDefaultExcept(saved.getId());

        return configurationToUpdate == null ? null : configurationToUpdate;
    }

    public void delete(Long id) {
        Configuration c = this.configurationRepository.findById(id).orElse(null);
        if (c == null) return;
        logger.info("Configuration to delete found. Deleting");
        for (Mib mib : new ArrayList<>(c.getMib())) {
            mib.removeConfiguration(c);
        }

        this.configurationRepository.delete(c);
    }

    public List<MibDTO> listAllMibDTOinGivenConfiguration(long id) {
        Configuration conf = this.configurationRepository.findById(id).orElse(null);
        if (conf == null) {
            logger.info("Configuration not fount. Returning empty set of mibs");
            return new ArrayList<>();
        }
        return this.mibService.convert(conf.getMib());

    }


}
