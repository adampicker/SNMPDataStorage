package corp.netizen.datastore.converters;

import corp.netizen.datastore.dto.ConfigurationDTO;
import corp.netizen.datastore.dto.MibDTO;
import corp.netizen.datastore.dto.MibValuesDTO;
import corp.netizen.datastore.model.Client;
import corp.netizen.datastore.model.Configuration;
import corp.netizen.datastore.model.Mib;
import corp.netizen.datastore.model.MibValue;

import java.util.LinkedList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class ConfigurationConverter  implements GenericConverter<Configuration, ConfigurationDTO> {

    private MibConverter mibConverter;

    public ConfigurationConverter(MibConverter mibConverter) {
        this.mibConverter = mibConverter;
    }

    @Override
    public Configuration createFromDto(ConfigurationDTO configuration) {
        return new Configuration();
    }

    @Override
    public ConfigurationDTO createFromEntity(Configuration configuration) {

        ConfigurationDTO dto = new ConfigurationDTO();
        dto.setId(configuration.getId());
        dto.setConfigurationName(configuration.getConfigurationName());
        dto.setDefaultConfiguration(configuration.isDefaultConfiguration());
        List<MibDTO> dtos = new LinkedList<>();
        configuration.getMib().forEach(mib -> {
            dtos.add(this.mibConverter.createFromEntity(mib));
        });
        dto.setMib(dtos.stream().collect(Collectors.toSet()));
        dto.setClient(configuration.getClient() == null ? null : configuration.getClient().getId());
        return dto;
    }

    @Override
    public Configuration updateEntity(Configuration conf, ConfigurationDTO dto) {
        return null;
    }
}
