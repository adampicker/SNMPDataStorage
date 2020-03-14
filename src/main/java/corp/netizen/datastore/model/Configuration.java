package corp.netizen.datastore.model;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@Entity
@Table(name="configuration")
public class Configuration {
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	@JsonProperty("id")
    private Long id;

	@ManyToMany(fetch = FetchType.EAGER,
			cascade = {
					CascadeType.PERSIST,
					CascadeType.MERGE
			})
	@JoinTable(name = "configuration_mib",
			joinColumns = { @JoinColumn(name = "configuration_id") },
			inverseJoinColumns = { @JoinColumn(name = "mib_id") })
	private Set<Mib> mib;
	
	@OneToOne(mappedBy= "configuration")
	private Client client;

	public Configuration(){};
	public Configuration(Set<Mib> mibs) {
		this.mib = mibs;
	}

}
