package corp.netizen.datastore.model;

import java.util.ArrayList;
import java.util.Set;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;

@Getter
@Entity
@Table(name = "mib")
public class Mib {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @JsonProperty("oid")
    private String oid;
    @JsonProperty("unit")
    private String unit;
    @JsonProperty("description")
    private String description;
    @JsonProperty("telnetShortcut")
    private String telnetShortcut;

    @OneToOne(mappedBy = "mib")
    private MibValue mibValue;

    @ManyToMany(fetch = FetchType.EAGER,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            },
            mappedBy = "mib")
    private Set<Configuration> configuration;


    public Mib() {
    }

    public Mib(String oid, String unit, String description, String telnetShortcut) {
        this.oid = oid;
        this.unit = unit;
        this.description = description;
        this.telnetShortcut = telnetShortcut;
    }

    public void addConfiguration(Configuration configuration) {
        this.configuration.add(configuration);
        configuration.getMib().add(this);
    }

    public void removeConfiguration(Configuration configuration) {
        this.configuration.remove(configuration);
        configuration.getMib().remove(this);
    }

    public void remove() {
        for (Configuration conf : new ArrayList<>(this.configuration)) removeConfiguration(conf);
    }

    @Override
    public String toString() {
        return "Mib{" +
                "id=" + id +
                ", oid='" + oid + '\'' +
                ", unit='" + unit + '\'' +
                ", description='" + description + '\'' +
                ", telnetShortcut='" + telnetShortcut + '\'' +
                ", mibValue=" + mibValue +
                ", configuration=" + configuration +
                '}';
    }
}
