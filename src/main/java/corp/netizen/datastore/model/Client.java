package corp.netizen.datastore.model;

import javax.persistence.*;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "clients")
public class Client {
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;	
	private int status;
	private String macAddress;
	
	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name="configuration", referencedColumnName="id" )
	private Configuration configuration;
	
	@OneToOne(mappedBy= "client")
	private MibValue mibValue;
	
	
	public Client() {}
	public Client(String macAddress, Configuration defaultConfiguration) {
		this.macAddress = macAddress;
		this.configuration = defaultConfiguration;
	}
	
	public Long getId() {
		return this.id;
	}
	
	public int getStatus() {
		return this.status;
	}
	
	public String getMacAddress() {
		return this.macAddress;
	}
	
	public void setStatus(int status) {
		this.status = status;
	}
	
	public void setMacAddress(String mac) {
		this.macAddress = mac;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((macAddress == null) ? 0 : macAddress.hashCode());
		result = prime * result + status;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Client other = (Client) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (macAddress == null) {
			if (other.macAddress != null)
				return false;
		} else if (!macAddress.equals(other.macAddress))
			return false;
		if (status != other.status)
			return false;
		return true;
	}

}
