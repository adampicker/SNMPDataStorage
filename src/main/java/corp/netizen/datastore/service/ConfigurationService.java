package corp.netizen.datastore.service;

import com.netizen.datastore.converters.MibConverter;
import com.netizen.datastore.dto.MibDTO;
import corp.netizen.datastore.model.Configuration;
import corp.netizen.datastore.model.Mib;
import corp.netizen.datastore.repository.ConfigurationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Set;

@Service
public class ConfigurationService {

    public ConfigurationRepository configurationRepository;
    private MibConverter mibConverter;

    @Autowired
    public ConfigurationService(ConfigurationRepository configurationRepository){
        this.configurationRepository = configurationRepository;
        this.mibConverter = new MibConverter();

    }

    public Configuration getById(Long id){
        return this.configurationRepository.findById(id).orElse(null);
    }

    public List<MibDTO> getConfiguration(Long id){
        Configuration configuration  = this.getById(id);
        Set<Mib> mibInConfiguration;
        if (configuration == null) mibInConfiguration =  Collections.EMPTY_SET;
        else {
            mibInConfiguration =  configuration.getMib();
        }
        return this.mibConverter.createFromEntities(mibInConfiguration);
    }

    public Configuration getDefaultConfiguration(){
        return this.configurationRepository.findById((long)0).orElse(null);
    }


}
