package corp.netizen.datastore.model;

import java.time.Instant;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
@Entity
@Table(name="mib_value")
public class MibValue {
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
	
	private Instant timestamp;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="mib", referencedColumnName="id")
	private Mib mib;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="client", referencedColumnName="id")
	private Client client;
	
	private String value;
	

}
