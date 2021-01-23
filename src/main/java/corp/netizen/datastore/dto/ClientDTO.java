package corp.netizen.datastore.dto;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClientDTO {

    Long id;
    String macAddress;
    String status;
    String type;
    String telnetPort;
    String pid;
    String userName;
    Long configuration;

}

