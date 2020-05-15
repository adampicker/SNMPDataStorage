package corp.netizen.datastore.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter @Setter
public class ConfigurationSaveDto {

    String configurationName;
    boolean defaultConfiguration;
    List<MibDTO> mibs;

}
