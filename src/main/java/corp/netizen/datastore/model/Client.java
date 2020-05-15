package corp.netizen.datastore.model;

import javax.persistence.*;

import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Getter
@Setter
@Entity
@Table(name = "clients")
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Status status;
    private String macAddress;
    private String type;
    private String port;
    private int pid;
    private String userName;


    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "configuration", referencedColumnName = "id")
    private Configuration configuration;

    @OneToOne(mappedBy = "client")
    private MibValue mibValue;


    public Client() {
    }

    public Client(String macAddress, Configuration defaultConfiguration) {
        this.macAddress = macAddress;
        this.configuration = defaultConfiguration;
        this.status = Status.INACTIVE;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Client client = (Client) o;
        return pid == client.pid &&
                Objects.equals(id, client.id) &&
                status == client.status &&
                Objects.equals(macAddress, client.macAddress) &&
                Objects.equals(type, client.type) &&
                Objects.equals(port, client.port) &&
                Objects.equals(userName, client.userName) &&
                Objects.equals(configuration, client.configuration) &&
                Objects.equals(mibValue, client.mibValue);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, status, macAddress, type, port, pid, userName, configuration, mibValue);
    }

    public enum Status {
        INACTIVE("INACTIVE"),
        ACTIVE("ACTIVE"),
        UPDATED("UPDATED"),
        ERROR("ERROR"),
        RESET("RESET");

        private String status;

        Status(String status) {
            this.status = status;
        }

        public String toString() {
            return this.status;
        }
    }

}

