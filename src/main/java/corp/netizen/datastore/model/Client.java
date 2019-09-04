package corp.netizen.datastore.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "cities")
public class Client {
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;	
	private int status;
	private String macAddress;
	
	
	public Client() {}
	public Client(String macAddress) {
		this.macAddress = macAddress;
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
