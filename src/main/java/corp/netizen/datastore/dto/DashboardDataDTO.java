package corp.netizen.datastore.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DashboardDataDTO {

    Integer inactiveClients;
    Integer activeClients;
    Integer mibAdded;
    Integer mibsNotInConfiguration;
    Integer createdConfigurations;
    Integer configurationsNotAssigned;

}
