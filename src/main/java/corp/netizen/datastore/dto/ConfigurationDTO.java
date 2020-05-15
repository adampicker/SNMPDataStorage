package corp.netizen.datastore.dto;


import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter @Setter
public class ConfigurationDTO {

    Long id;
    String configurationName;
    boolean defaultConfiguration;
    Set<MibDTO> mib;
    Long client;

}
