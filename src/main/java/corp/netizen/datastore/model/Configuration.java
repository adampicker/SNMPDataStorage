package corp.netizen.datastore.model;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "configuration")
public class Configuration {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonProperty("id")
    private Long id;

    private String configurationName;
    private boolean defaultConfiguration;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    @JoinTable(name = "configuration_mib",
            joinColumns = {@JoinColumn(name = "configuration_id")},
            inverseJoinColumns = {@JoinColumn(name = "mib_id")})
    private Set<Mib> mib;

    @OneToOne(mappedBy = "configuration")
    private Client client;

    public Configuration() {
        this.mib = new HashSet<>();
    }

    public Configuration(Set<Mib> mibs) {
        this.mib = mibs;
    }


    @Override
    public String toString() {
        return "Configuration{" +
                "id=" + id +
                ", configurationName='" + configurationName + '\'' +
                ", defaultConfiguration=" + defaultConfiguration +
                ", mib=" + mib +
                ", client=" + client +
                '}';
    }

    public void addMib(Mib mib) {
        this.mib.add(mib);
        mib.getConfiguration().add(this);
    }

    public void removeMib(Mib mib) {
        this.mib.remove(mib);
        mib.getConfiguration().remove(this);
    }
}
